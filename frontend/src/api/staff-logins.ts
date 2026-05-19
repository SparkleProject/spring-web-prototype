import client from '@/api/client'
import type { DataGrid, StaffLogin } from '@/types/api'

export async function list(page: number, rows: number = 20): Promise<DataGrid<StaffLogin>> {
  const response = await client.get<DataGrid<StaffLogin>>('/admin/staff-logins', {
    params: { page, rows },
  })
  return response.data
}
