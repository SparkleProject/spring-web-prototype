import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { AuthProvider } from '@/hooks/useAuth'
import { MainLayout } from '@/components/layout/MainLayout'
import { LoginPage } from '@/pages/LoginPage'
import { DashboardPage } from '@/pages/DashboardPage'
import { StaffPage } from '@/pages/StaffPage'
import { UserPage } from '@/pages/UserPage'
import { RolePage } from '@/pages/RolePage'
import { MenuPage } from '@/pages/MenuPage'
import { DepartmentPage } from '@/pages/DepartmentPage'
import { CodePage } from '@/pages/CodePage'
import { CodeTypePage } from '@/pages/CodeTypePage'
import { FunctionPage } from '@/pages/FunctionPage'
import { StaffLoginPage } from '@/pages/StaffLoginPage'
import { TaskPage } from '@/pages/TaskPage'
import { NewsPage } from '@/pages/NewsPage'
import { ProfilePage } from '@/pages/ProfilePage'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      refetchOnWindowFocus: false,
    },
  },
})

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/" element={<MainLayout />}>
              <Route index element={<Navigate to="/dashboard" replace />} />
              <Route path="dashboard" element={<DashboardPage />} />
              <Route path="staff" element={<StaffPage />} />
              <Route path="user" element={<UserPage />} />
              <Route path="role" element={<RolePage />} />
              <Route path="menu" element={<MenuPage />} />
              <Route path="department" element={<DepartmentPage />} />
              <Route path="code" element={<CodePage />} />
              <Route path="code-types" element={<CodeTypePage />} />
              <Route path="function" element={<FunctionPage />} />
              <Route path="staff-login" element={<StaffLoginPage />} />
              <Route path="task" element={<TaskPage />} />
              <Route path="news" element={<NewsPage />} />
              <Route path="profile" element={<ProfilePage />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </QueryClientProvider>
  )
}

export default App
