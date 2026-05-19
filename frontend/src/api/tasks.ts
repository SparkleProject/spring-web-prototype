import client from '@/api/client'
import type { DataGrid, Messager, Task } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<Task>> {
  const response = await client.get<DataGrid<Task>>('/admin/tasks', {
    params: { page, rows },
  })
  return response.data
}

export async function create(task: Task): Promise<Messager> {
  const response = await client.post<Messager>('/admin/tasks', task)
  return response.data
}

export async function update(id: number, task: Task): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/tasks/${id}`, task)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/tasks/${id}`)
  return response.data
}

export async function startup(id: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/tasks/${id}/startup`)
  return response.data
}

export async function shutdown(id: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/tasks/${id}/shutdown`)
  return response.data
}

export async function pause(id: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/tasks/${id}/pause`)
  return response.data
}

export async function resume(id: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/tasks/${id}/resume`)
  return response.data
}
