import client from '@/api/client'
import type { Messager } from '@/types/api'

export async function changePassword(oldPassword: string, newPassword: string): Promise<Messager> {
  const response = await client.put<Messager>('/admin/profile/password', {
    password_old: oldPassword,
    password_new: newPassword,
  })
  return response.data
}
