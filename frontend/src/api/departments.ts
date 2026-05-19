import client from '@/api/client'
import type { Messager, Department, TreeNode } from '@/types/api'

export async function list(): Promise<TreeNode[]> {
  const response = await client.get<TreeNode[]>('/admin/departments')
  return response.data
}

export async function create(dept: Department): Promise<Messager> {
  const response = await client.post<Messager>('/admin/departments', dept)
  return response.data
}

export async function update(id: number, dept: Department): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/departments/${id}`, dept)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/departments/${id}`)
  return response.data
}
