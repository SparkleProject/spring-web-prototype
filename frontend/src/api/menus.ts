import client from '@/api/client'
import type { Messager, Menu, TreeNode } from '@/types/api'

export async function list(): Promise<TreeNode[]> {
  const response = await client.get<TreeNode[]>('/admin/menus')
  return response.data
}

function toBackend(menu: Menu) {
  const { text, ...rest } = menu
  return { ...rest, name: text }
}

export async function create(menu: Menu): Promise<Messager> {
  const response = await client.post<Messager>('/admin/menus', toBackend(menu))
  return response.data
}

export async function update(id: number, menu: Menu): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/menus/${id}`, toBackend(menu))
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/menus/${id}`)
  return response.data
}
