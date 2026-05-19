import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Trash2 } from 'lucide-react'
import * as menusApi from '@/api/menus'
import { TreeView } from '@/components/features/TreeView'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { ScrollArea } from '@/components/ui/scroll-area'
import type { Menu, TreeNode } from '@/types/api'

export function MenuPage() {
  const [selectedNode, setSelectedNode] = useState<TreeNode | null>(null)
  const [formData, setFormData] = useState<Menu>({})
  const queryClient = useQueryClient()

  const { data: menuTree, isLoading } = useQuery({
    queryKey: ['menus'],
    queryFn: () => menusApi.list(),
  })

  const createMutation = useMutation({
    mutationFn: (menu: Menu) => menusApi.create(menu),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['menus'] })
      setFormData({})
      setSelectedNode(null)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, menu }: { id: number; menu: Menu }) =>
      menusApi.update(id, menu),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['menus'] })
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => menusApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['menus'] })
      setSelectedNode(null)
      setFormData({})
    },
  })

  const handleNodeSelect = (node: TreeNode) => {
    setSelectedNode(node)
    setFormData({
      id: node.id as number,
      pid: node.pid as number,
      text: node.text,
      url: node.attributes?.url as string,
      icon: node.attributes?.icon as string,
      seq: node.seq,
    })
  }

  const handleAddNew = () => {
    setSelectedNode(null)
    setFormData({
      pid: selectedNode?.id as number || 0,
    })
  }

  const handleSubmit = () => {
    if (formData.id) {
      updateMutation.mutate({ id: formData.id, menu: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = () => {
    if (selectedNode && confirm('Are you sure you want to delete this menu?')) {
      deleteMutation.mutate(selectedNode.id as number)
    }
  }

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-slate-800">Menu Management</h1>
        <Button onClick={handleAddNew} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Menu
        </Button>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-lg shadow p-4">
          <h2 className="text-lg font-semibold mb-4">Menu Tree</h2>
          {isLoading ? (
            <div className="text-slate-500">Loading...</div>
          ) : (
            <ScrollArea className="h-[500px]">
              <TreeView
                data={menuTree || []}
                selectedId={selectedNode?.id}
                onSelect={handleNodeSelect}
              />
            </ScrollArea>
          )}
        </div>

        <div className="bg-white rounded-lg shadow p-4">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold">
              {formData.id ? 'Edit Menu' : 'Add Menu'}
            </h2>
            {selectedNode && (
              <Button variant="destructive" size="sm" onClick={handleDelete} className="gap-2">
                <Trash2 className="h-4 w-4" />
                Delete
              </Button>
            )}
          </div>

          <div className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="text">Name</Label>
              <Input
                id="text"
                value={formData.text || ''}
                onChange={(e) => setFormData({ ...formData, text: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="url">URL</Label>
              <Input
                id="url"
                value={formData.url || ''}
                onChange={(e) => setFormData({ ...formData, url: e.target.value })}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="icon">Icon</Label>
              <Input
                id="icon"
                value={formData.icon || ''}
                onChange={(e) => setFormData({ ...formData, icon: e.target.value })}
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
              <Label htmlFor="pid">Parent ID</Label>
              <Input
                id="pid"
                type="number"
                value={formData.pid || ''}
                onChange={(e) => setFormData({ ...formData, pid: parseInt(e.target.value) || 0 })}
              />
            </div>

            <div className="flex gap-2 pt-4">
              <Button onClick={handleSubmit}>
                {formData.id ? 'Update' : 'Create'}
              </Button>
              <Button
                variant="outline"
                onClick={() => {
                  setSelectedNode(null)
                  setFormData({})
                }}
              >
                Clear
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
