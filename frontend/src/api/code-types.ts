import client from '@/api/client'
import type { DataGrid, Messager, CodeType } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<CodeType>> {
  const response = await client.get<DataGrid<CodeType>>('/admin/code-types', {
    params: { page, rows },
  })
  return response.data
}

export async function all(): Promise<CodeType[]> {
  const response = await client.get<CodeType[]>('/admin/code-types/all')
  return response.data
}

export async function create(ct: CodeType): Promise<Messager> {
  const response = await client.post<Messager>('/admin/code-types', ct)
  return response.data
}

export async function update(id: number, ct: CodeType): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/code-types/${id}`, ct)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/code-types/${id}`)
  return response.data
}
