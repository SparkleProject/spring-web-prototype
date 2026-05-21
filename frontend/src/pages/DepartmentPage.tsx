import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Trash2 } from 'lucide-react'
import * as departmentsApi from '@/api/departments'
import { TreeView } from '@/components/features/TreeView'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { ScrollArea } from '@/components/ui/scroll-area'
import type { Department, TreeNode } from '@/types/api'

export function DepartmentPage() {
  const [selectedNode, setSelectedNode] = useState<TreeNode | null>(null)
  const [formData, setFormData] = useState<Department>({})
  const queryClient = useQueryClient()

  const { data: deptTree, isLoading } = useQuery({
    queryKey: ['departments'],
    queryFn: () => departmentsApi.list(),
  })

  const createMutation = useMutation({
    mutationFn: (dept: Department) => departmentsApi.create(dept),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['departments'] })
      setFormData({})
      setSelectedNode(null)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, dept }: { id: number; dept: Department }) =>
      departmentsApi.update(id, dept),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['departments'] })
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => departmentsApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['departments'] })
      setSelectedNode(null)
      setFormData({})
    },
  })

  const handleNodeSelect = (node: TreeNode) => {
    setSelectedNode(node)
    setFormData({
      id: node.id as number,
      pid: node.pid as number,
      name: node.text,
      seq: node.seq,
      description: node.attributes?.description as string,
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
      updateMutation.mutate({ id: formData.id, dept: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = () => {
    if (selectedNode && confirm('Are you sure you want to delete this department?')) {
      deleteMutation.mutate(selectedNode.id as number)
    }
  }

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-slate-800">Department Management</h1>
        <Button onClick={handleAddNew} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Department
        </Button>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-lg shadow p-4">
          <h2 className="text-lg font-semibold mb-4">Department Tree</h2>
          {isLoading ? (
            <div className="text-slate-500">Loading...</div>
          ) : (
            <ScrollArea className="h-[500px]">
              <TreeView
                data={deptTree || []}
                selectedId={selectedNode?.id}
                onSelect={handleNodeSelect}
              />
            </ScrollArea>
          )}
        </div>

        <div className="bg-white rounded-lg shadow p-4">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold">
              {formData.id ? 'Edit Department' : 'Add Department'}
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
              <Label htmlFor="name">Name</Label>
              <Input
                id="name"
                value={formData.name || ''}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
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
            <div className="space-y-2">
              <Label htmlFor="description">Description</Label>
              <Input
                id="description"
                value={formData.description || ''}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
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
