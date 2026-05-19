import client from '@/api/client'
import type { DataGrid, Messager, Role, TreeNode } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<Role>> {
  const response = await client.get<DataGrid<Role>>('/admin/roles', {
    params: { page, rows },
  })
  return response.data
}

export async function create(role: Role): Promise<Messager> {
  const response = await client.post<Messager>('/admin/roles', role)
  return response.data
}

export async function update(id: number, role: Role): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/roles/${id}`, role)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/roles/${id}`)
  return response.data
}

export async function loadMenus(roleId: number): Promise<TreeNode[]> {
  const response = await client.get<TreeNode[]>(`/admin/roles/${roleId}/menus`)
  return response.data
}

export async function saveMenus(roleId: number, menuIds: number[]): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/roles/${roleId}/menus`, { menuIds })
  return response.data
}

export async function loadFunctions(roleId: number): Promise<TreeNode[]> {
  const response = await client.get<TreeNode[]>(`/admin/roles/${roleId}/functions`)
  return response.data
}

export async function saveFunctions(roleId: number, funcIds: number[]): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/roles/${roleId}/functions`, { funcIds })
  return response.data
}
