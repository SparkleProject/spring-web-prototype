import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Plus, Pencil, Trash2, Play, Square, Pause, RotateCcw } from 'lucide-react'
import * as tasksApi from '@/api/tasks'
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
import type { Task } from '@/types/api'

export function TaskPage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingTask, setEditingTask] = useState<Task | null>(null)
  const [formData, setFormData] = useState<Task>({})
  const queryClient = useQueryClient()

  const { data, isLoading } = useQuery({
    queryKey: ['tasks', page, pageSize],
    queryFn: () => tasksApi.list(page, pageSize),
  })

  const createMutation = useMutation({
    mutationFn: (task: Task) => tasksApi.create(task),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
      setDialogOpen(false)
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, task }: { id: number; task: Task }) =>
      tasksApi.update(id, task),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
      setDialogOpen(false)
    },
  })

  const deleteMutation = useMutation({
    mutationFn: (id: number) => tasksApi.remove(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const startupMutation = useMutation({
    mutationFn: (id: number) => tasksApi.startup(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const shutdownMutation = useMutation({
    mutationFn: (id: number) => tasksApi.shutdown(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const pauseMutation = useMutation({
    mutationFn: (id: number) => tasksApi.pause(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const resumeMutation = useMutation({
    mutationFn: (id: number) => tasksApi.resume(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const openCreateDialog = () => {
    setEditingTask(null)
    setFormData({})
    setDialogOpen(true)
  }

  const openEditDialog = (task: Task) => {
    setEditingTask(task)
    setFormData(task)
    setDialogOpen(true)
  }

  const handleSubmit = () => {
    if (editingTask?.id) {
      updateMutation.mutate({ id: editingTask.id, task: formData })
    } else {
      createMutation.mutate(formData)
    }
  }

  const handleDelete = (task: Task) => {
    if (task.id && confirm('Are you sure you want to delete this task?')) {
      deleteMutation.mutate(task.id)
    }
  }

  const getStateText = (state?: number) => {
    switch (state) {
      case 0: return 'Running'
      case 1: return 'Stopped'
      case 2: return 'Paused'
      default: return 'Unknown'
    }
  }

  const getStateColor = (state?: number) => {
    switch (state) {
      case 0: return 'text-green-500'
      case 1: return 'text-slate-500'
      case 2: return 'text-yellow-500'
      default: return 'text-slate-500'
    }
  }

  const columns: Column<Task>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'name', header: 'Name' },
    { key: 'cronExpression', header: 'Cron' },
    { key: 'targetObject', header: 'Target Object' },
    {
      key: 'state',
      header: 'State',
      render: (row) => (
        <span className={getStateColor(row.state)}>
          {getStateText(row.state)}
        </span>
      ),
    },
    {
      key: 'actions',
      header: 'Actions',
      render: (row) => (
        <div className="flex gap-1">
          <Button variant="ghost" size="icon" onClick={() => openEditDialog(row)} title="Edit">
            <Pencil className="h-4 w-4" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            onClick={() => row.id && startupMutation.mutate(row.id)}
            title="Start"
          >
            <Play className="h-4 w-4 text-green-500" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            onClick={() => row.id && shutdownMutation.mutate(row.id)}
            title="Stop"
          >
            <Square className="h-4 w-4 text-red-500" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            onClick={() => row.id && pauseMutation.mutate(row.id)}
            title="Pause"
          >
            <Pause className="h-4 w-4 text-yellow-500" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            onClick={() => row.id && resumeMutation.mutate(row.id)}
            title="Resume"
          >
            <RotateCcw className="h-4 w-4 text-blue-500" />
          </Button>
          <Button variant="ghost" size="icon" onClick={() => handleDelete(row)} title="Delete">
            <Trash2 className="h-4 w-4 text-red-500" />
          </Button>
        </div>
      ),
    },
  ]

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-slate-800">Task Management</h1>
        <Button onClick={openCreateDialog} className="gap-2">
          <Plus className="h-4 w-4" />
          Add Task
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
            <DialogTitle>{editingTask ? 'Edit Task' : 'Add Task'}</DialogTitle>
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
              <Label htmlFor="cronExpression">Cron Expression</Label>
              <Input
                id="cronExpression"
                value={formData.cronExpression || ''}
                onChange={(e) => setFormData({ ...formData, cronExpression: e.target.value })}
                placeholder="0 0 * * * ?"
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="targetObject">Target Object</Label>
              <Input
                id="targetObject"
                value={formData.targetObject || ''}
                onChange={(e) => setFormData({ ...formData, targetObject: e.target.value })}
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
              {editingTask ? 'Update' : 'Create'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  )
}
