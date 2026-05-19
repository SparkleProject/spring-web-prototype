import client from '@/api/client'
import type { DataGrid, Messager, Code, Combobox } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<Code>> {
  const response = await client.get<DataGrid<Code>>('/admin/codes', {
    params: { page, rows },
  })
  return response.data
}

export async function all(): Promise<Code[]> {
  const response = await client.get<Code[]>('/admin/codes/all')
  return response.data
}

export async function create(code: Code): Promise<Messager> {
  const response = await client.post<Messager>('/admin/codes', code)
  return response.data
}

export async function update(id: number, code: Code): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/codes/${id}`, code)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/codes/${id}`)
  return response.data
}

export async function combobox(type: string, select?: string): Promise<Combobox[]> {
  const params: Record<string, string> = {}
  if (select) params.select = select
  const response = await client.get<Combobox[]>(`/admin/codes/combobox/${encodeURIComponent(type)}`, {
    params,
  })
  return response.data
}
