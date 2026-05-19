import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2 } from 'lucide-react'
import * as functionsApi from '@/api/functions'
import { DataTable, type Column } from '@/components/features/DataTable'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from '@/components/ui/dialog'
import type { FunctionEntity } from '@/types/api'

export function FunctionPage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingFunction, setEditingFunction] = useState<FunctionEntity | null>(null)
  const [formData, setFormData] = useState<FunctionEntity>({})
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['functions', page, pageSize],
    queryFn: () => functionsApi.list(page, pageSize),
  })

  const createMutation = useMutation({
    mutationFn: (fn: FunctionEntity) => functionsApi.create(fn),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['functions'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, fn }: { id: number; fn: FunctionEntity }) =>
      functionsApi.update(id, fn),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['functions'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => functionsApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['functions'] })
    },
  })

  const openCreateDialog = () => {
    setEditingFunction(null)
    setFormData({})
    setDialogOpen(true)
  }

  const openEditDialog = (fn: FunctionEntity) => {
    setEditingFunction(fn)
    setFormData(fn)
    setDialogOpen(true)
  }

  const handleSubmit = () => {
    if (editingFunction?.id) {
      updateMutation.mutate({ id: editingFunction.id, fn: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (fn: FunctionEntity) => {
    if (fn.id && confirm('Are you sure you want to delete this function?')) {
      deleteMutation.mutate(fn.id)
    }
  }

  const columns: Column<FunctionEntity>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'name', header: 'Name' },
    { key: 'code', header: 'Code' },
    { key: 'description', header: 'Description' },
    {
      key: 'actions',
      header: 'Actions',
      render: (row) => (
        <div className="flex gap-2">
          <Button variant="ghost" size="icon" onClick={() => openEditDialog(row)}>
            <Pencil className="h-4 w-4" />
          </Button>
          <Button variant="ghost" size="icon" onClick={() => handleDelete(row)}>
            <Trash2 className="h-4 w-4 text-red-500" />
          </Button>
        </div>
      ),
    },
  ]

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-slate-800">Function Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Function
        </Button>
      </div>

      <DataTable
        columns={columns}
        data={data?.rows || []}
        total={data?.total || 0}
        page={page}
        pageSize={pageSize}
        onPageChange={setPage}
        isLoading={isLoading}
      />

      <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>{editingFunction ? 'Edit Function' : 'Add Function'}</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="name">Name</Label>
              <Input
                id="name"
                value={formData.name || ''}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="code">Code</Label>
              <Input
                id="code"
                value={formData.code || ''}
                onChange={(e) => setFormData({ ...formData, code: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="description">Description</Label>
              <Input
                id="description"
                value={formData.description || ''}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleSubmit}>
              {editingFunction ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
