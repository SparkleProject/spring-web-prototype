import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2 } from 'lucide-react'
import * as codeTypesApi from '@/api/code-types'
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
import type { CodeType } from '@/types/api'

export function CodeTypePage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingCodeType, setEditingCodeType] = useState<CodeType | null>(null)
  const [formData, setFormData] = useState<CodeType>({})
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['code-types', page, pageSize],
    queryFn: () => codeTypesApi.list(page, pageSize),
  })

  const createMutation = useMutation({
    mutationFn: (ct: CodeType) => codeTypesApi.create(ct),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['code-types'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, ct }: { id: number; ct: CodeType }) =>
      codeTypesApi.update(id, ct),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['code-types'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => codeTypesApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['code-types'] })
    },
  })

  const openCreateDialog = () => {
    setEditingCodeType(null)
    setFormData({})
    setDialogOpen(true)
  }

  const openEditDialog = (ct: CodeType) => {
    setEditingCodeType(ct)
    setFormData(ct)
    setDialogOpen(true)
  }

  const handleSubmit = () => {
    if (editingCodeType?.id) {
      updateMutation.mutate({ id: editingCodeType.id, ct: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (ct: CodeType) => {
    if (ct.id && confirm('Are you sure you want to delete this code type?')) {
      deleteMutation.mutate(ct.id)
    }
  }

  const columns: Column<CodeType>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'name', header: 'Name' },
    { key: 'code', header: 'Code' },
    { key: 'seq', header: 'Sequence' },
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
        <h1 className="text-2xl font-bold text-slate-800">Code Type Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Code Type
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
            <DialogTitle>{editingCodeType ? 'Edit Code Type' : 'Add Code Type'}</DialogTitle>
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
              <Label htmlFor="seq">Sequence</Label>
              <Input
                id="seq"
                type="number"
                value={formData.seq || ''}
                onChange={(e) => setFormData({ ...formData, seq: parseInt(e.target.value) || 0 })}
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
              {editingCodeType ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
