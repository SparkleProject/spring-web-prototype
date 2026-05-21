import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2, Menu, Zap } from 'lucide-react'
import * as rolesApi from '@/api/roles'
import { DataTable, type Column } from '@/components/features/DataTable'
import { TreeView } from '@/components/features/TreeView'
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
import { ScrollArea } from '@/components/ui/scroll-area'
import type { Role, TreeNode } from '@/types/api'

function collectCheckedIds(nodes: TreeNode[], set: Set<number | string>) {
  for (const node of nodes) {
    if (node.checked) {
      set.add(node.id)
    }
    if (node.children) {
      collectCheckedIds(node.children, set)
    }
  }
}

export function RolePage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [menuDialogOpen, setMenuDialogOpen] = useState(false)
  const [funcDialogOpen, setFuncDialogOpen] = useState(false)
  const [editingRole, setEditingRole] = useState<Role | null>(null)
  const [formData, setFormData] = useState<Role>({})
  const [menuChecked, setMenuChecked] = useState<Set<number | string>>(new Set())
  const [funcChecked, setFuncChecked] = useState<Set<number | string>>(new Set())
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['roles', page, pageSize],
    queryFn: () => rolesApi.list(page, pageSize),
  })

  const { data: menuTree } = useQuery({
    queryKey: ['role-menus', editingRole?.id],
    queryFn: () => rolesApi.loadMenus(editingRole!.id!),
    enabled: !!editingRole?.id && menuDialogOpen,
  })

  const { data: funcTree } = useQuery({
    queryKey: ['role-functions', editingRole?.id],
    queryFn: () => rolesApi.loadFunctions(editingRole!.id!),
    enabled: !!editingRole?.id && funcDialogOpen,
  })

  const createMutation = useMutation({
    mutationFn: (role: Role) => rolesApi.create(role),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['roles'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, role }: { id: number; role: Role }) =>
      rolesApi.update(id, role),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['roles'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => rolesApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['roles'] })
    },
  })

  const saveMenusMutation = useMutation({
    mutationFn: ({ roleId, menuIds }: { roleId: number; menuIds: number[] }) =>
      rolesApi.saveMenus(roleId, menuIds),
    onSuccess: () => {
      setMenuDialogOpen(false)
    },
  })

  const saveFuncsMutation = useMutation({
    mutationFn: ({ roleId, funcIds }: { roleId: number; funcIds: number[] }) =>
      rolesApi.saveFunctions(roleId, funcIds),
    onSuccess: () => {
      setFuncDialogOpen(false)
    },
  })

  const openCreateDialog = () => {
    setEditingRole(null)
    setFormData({})
    setDialogOpen(true)
  }

  const openEditDialog = (role: Role) => {
    setEditingRole(role)
    setFormData(role)
    setDialogOpen(true)
  }

  const openMenuDialog = (role: Role) => {
    setEditingRole(role)
    setMenuChecked(new Set())
    setMenuDialogOpen(true)
  }

  const openFuncDialog = (role: Role) => {
    setEditingRole(role)
    setFuncChecked(new Set())
    setFuncDialogOpen(true)
  }

  // Initialize checked state when menu tree loads
  if (menuTree && menuDialogOpen && menuChecked.size === 0) {
    const initial = new Set<number | string>()
    collectCheckedIds(menuTree, initial)
    if (initial.size > 0) {
      setMenuChecked(initial)
    }
  }

  if (funcTree && funcDialogOpen && funcChecked.size === 0) {
    const initial = new Set<number | string>()
    collectCheckedIds(funcTree, initial)
    if (initial.size > 0) {
      setFuncChecked(initial)
    }
  }

  const handleSubmit = () => {
    if (editingRole?.id) {
      updateMutation.mutate({ id: editingRole.id, role: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (role: Role) => {
    if (role.id && confirm('Are you sure you want to delete this role?')) {
      deleteMutation.mutate(role.id)
    }
  }

  const handleMenuCheck = (node: TreeNode, checked: boolean) => {
    const newSet = new Set(menuChecked)
    if (checked) {
      newSet.add(node.id)
    } else {
      newSet.delete(node.id)
    }
    setMenuChecked(newSet)
  }

  const handleFuncCheck = (node: TreeNode, checked: boolean) => {
    const newSet = new Set(funcChecked)
    if (checked) {
      newSet.add(node.id)
    } else {
      newSet.delete(node.id)
    }
    setFuncChecked(newSet)
  }

  const handleSaveMenus = () => {
    if (editingRole?.id) {
      saveMenusMutation.mutate({
        roleId: editingRole.id,
        menuIds: Array.from(menuChecked).map(Number),
      })
    }
  }

  const handleSaveFuncs = () => {
    if (editingRole?.id) {
      saveFuncsMutation.mutate({
        roleId: editingRole.id,
        funcIds: Array.from(funcChecked).map(Number),
      })
    }
  }

  const columns: Column<Role>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'code', header: 'Code' },
    { key: 'name', header: 'Name' },
    { key: 'description', header: 'Description' },
    {
      key: 'actions',
      header: 'Actions',
      render: (row) => (
        <div className="flex gap-2">
          <Button variant="ghost" size="icon" onClick={() => openEditDialog(row)}>
            <Pencil className="h-4 w-4" />
          </Button>
          <Button variant="ghost" size="icon" onClick={() => openMenuDialog(row)} title="Assign Menus">
            <Menu className="h-4 w-4" />
          </Button>
          <Button variant="ghost" size="icon" onClick={() => openFuncDialog(row)} title="Assign Functions">
            <Zap className="h-4 w-4" />
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
        <h1 className="text-2xl font-bold text-slate-800">Role Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Role
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
            <DialogTitle>{editingRole ? 'Edit Role' : 'Add Role'}</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
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
              {editingRole ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <Dialog open={menuDialogOpen} onOpenChange={setMenuDialogOpen}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Assign Menus - {editingRole?.name}</DialogTitle>
          </DialogHeader>
          <ScrollArea className="h-[400px] border rounded-md">
            {menuTree && (
              <TreeView
                data={menuTree}
                checkedIds={menuChecked}
                onCheck={handleMenuCheck}
                showCheckbox
              />
            )}
          </ScrollArea>
          <DialogFooter>
            <Button variant="outline" onClick={() => setMenuDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleSaveMenus}>Save</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <Dialog open={funcDialogOpen} onOpenChange={setFuncDialogOpen}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Assign Functions - {editingRole?.name}</DialogTitle>
          </DialogHeader>
          <ScrollArea className="h-[400px] border rounded-md">
            {funcTree && (
              <TreeView
                data={funcTree}
                checkedIds={funcChecked}
                onCheck={handleFuncCheck}
                showCheckbox
              />
            )}
          </ScrollArea>
          <DialogFooter>
            <Button variant="outline" onClick={() => setFuncDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={handleSaveFuncs}>Save</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
