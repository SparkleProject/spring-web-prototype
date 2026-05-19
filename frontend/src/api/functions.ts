import client from '@/api/client'
import type { DataGrid, Messager, FunctionEntity } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<FunctionEntity>> {
  const response = await client.get<DataGrid<FunctionEntity>>('/admin/functions', {
    params: { page, rows },
  })
  return response.data
}

export async function all(): Promise<FunctionEntity[]> {
  const response = await client.get<FunctionEntity[]>('/admin/functions/all')
  return response.data
}

export async function create(fn: FunctionEntity): Promise<Messager> {
  const response = await client.post<Messager>('/admin/functions', fn)
  return response.data
}

export async function update(id: number, fn: FunctionEntity): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/functions/${id}`, fn)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/functions/${id}`)
  return response.data
}
