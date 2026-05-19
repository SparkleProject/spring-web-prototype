import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2 } from 'lucide-react'
import * as codesApi from '@/api/codes'
import { DataTable, type Column } from '@/components/features/DataTable'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Checkbox } from '@/components/ui/checkbox'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from '@/components/ui/dialog'
import type { Code } from '@/types/api'

export function CodePage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingCode, setEditingCode] = useState<Code | null>(null)
  const [formData, setFormData] = useState<Code>({})
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['codes', page, pageSize],
    queryFn: () => codesApi.list(page, pageSize),
  })

  const createMutation = useMutation({
    mutationFn: (code: Code) => codesApi.create(code),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['codes'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, code }: { id: number; code: Code }) =>
      codesApi.update(id, code),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['codes'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => codesApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['codes'] })
    },
  })

  const openCreateDialog = () => {
    setEditingCode(null)
    setFormData({ enable: true })
    setDialogOpen(true)
  }

  const openEditDialog = (code: Code) => {
    setEditingCode(code)
    setFormData(code)
    setDialogOpen(true)
  }

  const handleSubmit = () => {
    if (editingCode?.id) {
      updateMutation.mutate({ id: editingCode.id, code: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (code: Code) => {
    if (code.id && confirm('Are you sure you want to delete this code?')) {
      deleteMutation.mutate(code.id)
    }
  }

  const columns: Column<Code>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'type', header: 'Type' },
    { key: 'code', header: 'Code' },
    { key: 'name', header: 'Name' },
    { key: 'value', header: 'Value' },
    { key: 'seq', header: 'Sequence' },
    {
      key: 'enable',
      header: 'Enabled',
      render: (row) => (
        <Checkbox checked={row.enable} disabled />
      ),
    },
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
        <h1 className="text-2xl font-bold text-slate-800">Code Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Code
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
            <DialogTitle>{editingCode ? 'Edit Code' : 'Add Code'}</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="type">Type</Label>
              <Input
                id="type"
                value={formData.type || ''}
                onChange={(e) => setFormData({ ...formData, type: e.target.value })}
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
              <Label htmlFor="name">Name</Label>
              <Input
                id="name"
                value={formData.name || ''}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="value">Value</Label>
              <Input
                id="value"
                value={formData.value || ''}
                onChange={(e) => setFormData({ ...formData, value: e.target.value })}
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
            <div className="flex items-center gap-2">
              <Checkbox
                id="enable"
                checked={formData.enable}
                onCheckedChange={(checked) => setFormData({ ...formData, enable: checked === true })}
              />
              <Label htmlFor="enable">Enabled</Label>
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleSubmit}>
              {editingCode ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
