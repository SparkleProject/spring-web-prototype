import client from '@/api/client'
import type { DataGrid, Messager, Staff } from '@/types/api'

export async function list(page: number, rows: number): Promise<DataGrid<Staff>> {
  const response = await client.get<DataGrid<Staff>>('/admin/staff', {
    params: { page, rows },
  })
  return response.data
}

export async function getById(id: number): Promise<Staff> {
  const response = await client.get<Staff>(`/admin/staff/${id}`)
  return response.data
}

export async function create(staff: Staff): Promise<Messager> {
  const response = await client.post<Messager>('/admin/staff', staff)
  return response.data
}

export async function update(id: number, staff: Staff): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/staff/${id}`, staff)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/staff/${id}`)
  return response.data
}

export async function changePassword(id: number, password: string): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/staff/${id}/password`, { password_new: password })
  return response.data
}

export async function toggleEnabled(id: number, enabled: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/staff/${id}/enabled`, { enabled })
  return response.data
}
