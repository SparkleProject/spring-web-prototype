import { useEffect, useState } from 'react'
import { Users, Shield, FolderTree, ClipboardList } from 'lucide-react'
import { useAuth } from '@/hooks/useAuth'
import * as staffApi from '@/api/staff'
import * as rolesApi from '@/api/roles'
import * as departmentsApi from '@/api/departments'
import * as tasksApi from '@/api/tasks'

interface StatCardProps {
  title: string
  value: string
  icon: React.ComponentType<{ className?: string }>
  color: string
}

function StatCard({ title, value, icon: Icon, color }: StatCardProps) {
  return (
    <div className="bg-white rounded-lg shadow p-6">
      <div className="flex items-center gap-4">
        <div className={`p-3 rounded-lg ${color}`}>
          <Icon className="h-6 w-6 text-white" />
        </div>
        <div>
          <p className="text-sm text-slate-500">{title}</p>
          <p className="text-2xl font-semibold text-slate-800">{value}</p>
        </div>
      </div>
    </div>
  )
}

export function DashboardPage() {
  const { staff } = useAuth()
  const [stats, setStats] = useState({ staff: '--', roles: '--', departments: '--', tasks: '--' })

  useEffect(() => {
    async function fetchStats() {
      const [staffRes, rolesRes, deptRes, tasksRes] = await Promise.allSettled([
        staffApi.list(1, 1),
        rolesApi.list(1, 1),
        departmentsApi.list(),
        tasksApi.list(1, 1),
      ])
      setStats({
        staff: staffRes.status === 'fulfilled' ? String(staffRes.value.total) : '--',
        roles: rolesRes.status === 'fulfilled' ? String(rolesRes.value.total) : '--',
        departments: deptRes.status === 'fulfilled' ? String(countTreeNodes(deptRes.value)) : '--',
        tasks: tasksRes.status === 'fulfilled' ? String(tasksRes.value.total) : '--',
      })
    }
    fetchStats()
  }, [])

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold text-slate-800">Dashboard</h1>
        <p className="text-slate-500 mt-1">
          Welcome back, {staff?.name || staff?.loginName || 'User'}!
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <StatCard
          title="Total Staff"
          value={stats.staff}
          icon={Users}
          color="bg-blue-500"
        />
        <StatCard
          title="Roles"
          value={stats.roles}
          icon={Shield}
          color="bg-green-500"
        />
        <StatCard
          title="Departments"
          value={stats.departments}
          icon={FolderTree}
          color="bg-purple-500"
        />
        <StatCard
          title="Tasks"
          value={stats.tasks}
          icon={ClipboardList}
          color="bg-orange-500"
        />
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-lg font-semibold text-slate-800 mb-4">Recent Activity</h2>
          <div className="text-slate-500 text-sm">No recent activity</div>
        </div>

        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-lg font-semibold text-slate-800 mb-4">Quick Actions</h2>
          <div className="space-y-2 text-sm">
            <p className="text-slate-500">Access common functions from the sidebar menu.</p>
          </div>
        </div>
      </div>
    </div>
  )
}

function countTreeNodes(nodes: { children?: unknown[] }[]): number {
  let count = 0
  for (const node of nodes) {
    if ((node as { id?: number }).id !== 0) count++
    if (node.children && Array.isArray(node.children)) {
      count += countTreeNodes(node.children as { children?: unknown[] }[])
    }
  }
  return count
}
