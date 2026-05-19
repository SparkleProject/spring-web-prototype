import client from '@/api/client'
import type { AuthResponse, Messager } from '@/types/api'

export async function login(username: string, password: string): Promise<AuthResponse> {
  const response = await client.post<AuthResponse>('/admin/auth/login', { username, password })
  return response.data
}

export async function logout(): Promise<Messager> {
  const response = await client.post<Messager>('/admin/auth/logout')
  return response.data
}

export async function getMe(): Promise<AuthResponse> {
  const response = await client.get<AuthResponse>('/admin/auth/me')
  return response.data
}
