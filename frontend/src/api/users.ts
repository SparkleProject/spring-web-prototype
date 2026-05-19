import client from '@/api/client'
import type { DataGrid, Messager, User } from '@/types/api'

export async function list(page: number, rows: number): Promise<DataGrid<User>> {
  const response = await client.get<DataGrid<User>>('/admin/users', {
    params: { page, rows },
  })
  return response.data
}

export async function getById(id: number): Promise<User> {
  const response = await client.get<User>(`/admin/users/${id}`)
  return response.data
}

export async function create(user: User): Promise<Messager> {
  const response = await client.post<Messager>('/admin/users', user)
  return response.data
}

export async function update(id: number, user: User): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/users/${id}`, user)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/users/${id}`)
  return response.data
}

export async function changePassword(id: number, password: string): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/users/${id}/password`, { password_new: password })
  return response.data
}

export async function toggleEnabled(id: number, enabled: number): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/users/${id}/enabled`, { enabled })
  return response.data
}
