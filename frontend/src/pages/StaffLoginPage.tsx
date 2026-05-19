import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import * as staffLoginsApi from '@/api/staff-logins'
import { DataTable, type Column } from '@/components/features/DataTable'
import type { StaffLogin } from '@/types/api'

export function StaffLoginPage() {
  const [page, setPage] = useState(1)
  const [pageSize] = useState(20)

  const { data, isLoading } = useQuery({
    queryKey: ['staff-logins', page, pageSize],
    queryFn: () => staffLoginsApi.list(page, pageSize),
  })

  const columns: Column<StaffLogin>[] = [
    { key: 'id', header: 'ID', width: '80px' },
    { key: 'staffName', header: 'Staff Name' },
    { key: 'loginDate', header: 'Login Time' },
    { key: 'loginIp', header: 'IP Address' },
  ]

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-slate-800">Staff Login History</h1>
      </div>

      <DataTable
        columns={columns}
        data={data?.rows || []}
        total={data?.total || 0}
        page={page}
        pageSize={pageSize}
        onPageChange={setPage}
        isLoading={isLoading}
      />
    </div>
  )
}
