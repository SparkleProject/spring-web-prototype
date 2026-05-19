import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2, Key } from 'lucide-react'
import * as staffApi from '@/api/staff'
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
import type { Staff } from '@/types/api'

export function StaffPage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [passwordDialogOpen, setPasswordDialogOpen] = useState(false)
  const [editingStaff, setEditingStaff] = useState<Staff | null>(null)
  const [formData, setFormData] = useState<Staff>({})
  const [newPassword, setNewPassword] = useState('')
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['staff', page, pageSize],
    queryFn: () => staffApi.list(page, pageSize),
  })

  const createMutation = useMutation({
    mutationFn: (staff: Staff) => staffApi.create(staff),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['staff'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, staff }: { id: number; staff: Staff }) =>
      staffApi.update(id, staff),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['staff'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => staffApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['staff'] })
    },
  })

  const passwordMutation = useMutation({
    mutationFn: ({ id, password }: { id: number; password: string }) =>
      staffApi.changePassword(id, password),
    onSuccess: () => {
      setPasswordDialogOpen(false)
      setNewPassword('')
    },
  })

  const toggleEnabledMutation = useMutation({
    mutationFn: ({ id, enabled }: { id: number; enabled: number }) =>
      staffApi.toggleEnabled(id, enabled),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['staff'] })
    },
  })

  const openCreateDialog = () => {
    setEditingStaff(null)
    setFormData({})
    setDialogOpen(true)
  }

  const openEditDialog = (staff: Staff) => {
    setEditingStaff(staff)
    setFormData(staff)
    setDialogOpen(true)
  }

  const openPasswordDialog = (staff: Staff) => {
    setEditingStaff(staff)
    setNewPassword('')
    setPasswordDialogOpen(true)
  }

  const handleSubmit = () => {
    if (editingStaff?.id) {
      updateMutation.mutate({ id: editingStaff.id, staff: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (staff: Staff) => {
    if (staff.id && confirm('Are you sure you want to delete this staff?')) {
      deleteMutation.mutate(staff.id)
    }
  }

  const handlePasswordSubmit = () => {
    if (editingStaff?.id && newPassword) {
      passwordMutation.mutate({ id: editingStaff.id, password: newPassword })
    }
  }

  const columns: Column<Staff>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'loginName', header: 'Login Name' },
    { key: 'name', header: 'Name' },
    { key: 'mobile', header: 'Mobile' },
    { key: 'email', header: 'Email' },
    {
      key: 'enabled',
      header: 'Enabled',
      render: (row) => (
        <Checkbox
          checked={row.enabled === 1}
          onCheckedChange={(checked) =>
            row.id && toggleEnabledMutation.mutate({ id: row.id, enabled: checked ? 1 : 0 })
          }
        />
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
          <Button variant="ghost" size="icon" onClick={() => openPasswordDialog(row)}>
            <Key className="h-4 w-4" />
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
        <h1 className="text-2xl font-bold text-slate-800">Staff Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Staff
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
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>{editingStaff ? 'Edit Staff' : 'Add Staff'}</DialogTitle>
          </DialogHeader>
          <div className="grid grid-cols-2 gap-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="loginName">Login Name</Label>
              <Input
                id="loginName"
                value={formData.loginName || ''}
                onChange={(e) => setFormData({ ...formData, loginName: e.target.value })}
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
            {!editingStaff && (
              <div className="space-y-2">
                <Label htmlFor="password">Password</Label>
                <Input
                  id="password"
                  type="password"
                  value={formData.password || ''}
                  onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                />
              </div>
            )}
            <div className="space-y-2">
              <Label htmlFor="mobile">Mobile</Label>
              <Input
                id="mobile"
                value={formData.mobile || ''}
                onChange={(e) => setFormData({ ...formData, mobile: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="email">Email</Label>
              <Input
                id="email"
                type="email"
                value={formData.email || ''}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="address">Address</Label>
              <Input
                id="address"
                value={formData.address || ''}
                onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleSubmit}>
              {editingStaff ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <Dialog open={passwordDialogOpen} onOpenChange={setPasswordDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Change Password</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <p className="text-sm text-slate-500">
              Changing password for: {editingStaff?.name || editingStaff?.loginName}
            </p>
            <div className="space-y-2">
              <Label htmlFor="newPassword">New Password</Label>
              <Input
                id="newPassword"
                type="password"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setPasswordDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handlePasswordSubmit}>Change Password</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
