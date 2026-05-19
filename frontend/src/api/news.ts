import client from '@/api/client'
import type { DataGrid, Messager, News } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<News>> {
  const response = await client.get<DataGrid<News>>('/admin/news', {
    params: { page, rows },
  })
  return response.data
}

export async function getById(id: number): Promise<News> {
  const response = await client.get<News>(`/admin/news/${id}`)
  return response.data
}

export async function create(news: News): Promise<Messager> {
  const response = await client.post<Messager>('/admin/news', news)
  return response.data
}

export async function update(id: number, news: News): Promise<Messager> {
  const response = await client.put<Messager>(`/admin/news/${id}`, news)
  return response.data
}

export async function remove(id: number): Promise<Messager> {
  const response = await client.delete<Messager>(`/admin/news/${id}`)
  return response.data
}
