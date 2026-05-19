import { createContext, useContext, useEffect, useState, type ReactNode } from 'react'
import * as authApi from '@/api/auth'
import type { Staff, TreeNode } from '@/types/api'

interface AuthContextType {
  staff: Staff | null
  menu: TreeNode[]
  isAuthenticated: boolean
  isLoading: boolean
  login: (username: string, password: string) => Promise<boolean>
  logout: () => Promise<void>
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [staff, setStaff] = useState<Staff | null>(null)
  const [menu, setMenu] = useState<TreeNode[]>([])
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const response = await authApi.getMe()
        if (response.success && response.staff) {
          setStaff(response.staff)
          setMenu(response.menu || [])
        }
      } catch {
        // Not authenticated
      } finally {
        setIsLoading(false)
      }
    }
    checkAuth()
  }, [])

  const login = async (username: string, password: string): Promise<boolean> => {
    try {
      const response = await authApi.login(username, password)
      if (response.success && response.staff) {
        setStaff(response.staff)
        setMenu(response.menu || [])
        return true
      }
      return false
    } catch {
      return false
    }
  }

  const logout = async () => {
    try {
      await authApi.logout()
    } finally {
      setStaff(null)
      setMenu([])
    }
  }

  return (
    <AuthContext.Provider
      value={{
        staff,
        menu,
        isAuthenticated: !!staff,
        isLoading,
        login,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}
