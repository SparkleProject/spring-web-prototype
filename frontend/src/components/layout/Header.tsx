import { Link, useNavigate } from 'react-router-dom'
import { LogOut, Key, User } from 'lucide-react'
import { Button } from '@/components/ui/button'
import { useAuth } from '@/hooks/useAuth'

export function Header() {
  const { staff, logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = async () => {
    await logout()
    navigate('/login')
  }

  return (
    <header className="h-14 border-b bg-white px-6 flex items-center justify-between">
      <div className="flex items-center gap-2">
        <h1 className="text-lg font-semibold text-slate-800">Admin Panel</h1>
      </div>

      <div className="flex items-center gap-4">
        <div className="flex items-center gap-2 text-sm text-slate-600">
          <User className="h-4 w-4" />
          <span>{staff?.name || staff?.loginName || 'User'}</span>
        </div>

        <Link to="/profile">
          <Button variant="ghost" size="sm" className="gap-2">
            <Key className="h-4 w-4" />
            Change Password
          </Button>
        </Link>

        <Button variant="ghost" size="sm" onClick={handleLogout} className="gap-2">
          <LogOut className="h-4 w-4" />
          Logout
        </Button>
      </div>
    </header>
  )
}
