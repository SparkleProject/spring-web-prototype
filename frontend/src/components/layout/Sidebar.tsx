import { useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { ChevronRight, ChevronDown, Menu, LayoutDashboard, Users, UserCog, Shield, FolderTree, Building2, Code2, ListTree, Zap, ClipboardList, Newspaper, History, CalendarClock } from 'lucide-react'
import { cn } from '@/lib/utils'
import { useAuth } from '@/hooks/useAuth'
import { Button } from '@/components/ui/button'
import { ScrollArea } from '@/components/ui/scroll-area'
import type { TreeNode } from '@/types/api'

const iconMap: Record<string, React.ComponentType<{ className?: string }>> = {
  'icon-dashboard': LayoutDashboard,
  'icon-users': Users,
  'icon-user': UserCog,
  'icon-shield': Shield,
  'icon-folder': FolderTree,
  'icon-building': Building2,
  'icon-code': Code2,
  'icon-list': ListTree,
  'icon-zap': Zap,
  'icon-clipboard': ClipboardList,
  'icon-news': Newspaper,
  'icon-history': History,
  'icon-calendar': CalendarClock,
}

function getIcon(iconName?: string) {
  if (!iconName) return FolderTree
  return iconMap[iconName] || FolderTree
}

function stripAdminPrefix(url?: string): string {
  if (!url) return ''
  return url.replace(/^\/admin/, '')
}

interface SidebarItemProps {
  node: TreeNode
  collapsed: boolean
  level?: number
}

function SidebarItem({ node, collapsed, level = 0 }: SidebarItemProps) {
  const [expanded, setExpanded] = useState(node.state === 'open')
  const location = useLocation()
  const hasChildren = node.children && node.children.length > 0
  const url = node.attributes?.url as string | undefined
  const path = stripAdminPrefix(url)
  const isActive = path && location.pathname === path
  const Icon = getIcon(node.attributes?.icon as string | undefined)

  const handleClick = () => {
    if (hasChildren) {
      setExpanded(!expanded)
    }
  }

  const content = (
    <div
      className={cn(
        'flex items-center gap-2 px-3 py-2 rounded-md cursor-pointer transition-colors',
        isActive ? 'bg-blue-100 text-blue-700' : 'text-slate-600 hover:bg-slate-100',
        collapsed && level === 0 && 'justify-center'
      )}
      style={{ paddingLeft: collapsed ? undefined : `${12 + level * 16}px` }}
      onClick={handleClick}
    >
      <Icon className="h-4 w-4 shrink-0" />
      {!collapsed && (
        <>
          <span className="flex-1 truncate text-sm">{node.text}</span>
          {hasChildren && (
            expanded ? <ChevronDown className="h-4 w-4" /> : <ChevronRight className="h-4 w-4" />
          )}
        </>
      )}
    </div>
  )

  return (
    <div>
      {path && !hasChildren ? (
        <Link to={path}>{content}</Link>
      ) : (
        content
      )}
      {!collapsed && hasChildren && expanded && (
        <div>
          {node.children?.map((child) => (
            <SidebarItem key={child.id} node={child} collapsed={collapsed} level={level + 1} />
          ))}
        </div>
      )}
    </div>
  )
}

interface SidebarProps {
  collapsed: boolean
  onToggle: () => void
}

export function Sidebar({ collapsed, onToggle }: SidebarProps) {
  const { menu } = useAuth()

  return (
    <aside
      className={cn(
        'h-full border-r bg-white transition-all duration-300',
        collapsed ? 'w-16' : 'w-64'
      )}
    >
      <div className="h-14 border-b flex items-center justify-between px-4">
        {!collapsed && <span className="font-semibold text-slate-800">Menu</span>}
        <Button variant="ghost" size="icon" onClick={onToggle}>
          <Menu className="h-5 w-5" />
        </Button>
      </div>

      <ScrollArea className="h-[calc(100vh-3.5rem)]">
        <nav className="p-2 space-y-1">
          <Link to="/dashboard">
            <div
              className={cn(
                'flex items-center gap-2 px-3 py-2 rounded-md cursor-pointer transition-colors text-slate-600 hover:bg-slate-100',
                collapsed && 'justify-center'
              )}
            >
              <LayoutDashboard className="h-4 w-4 shrink-0" />
              {!collapsed && <span className="text-sm">Dashboard</span>}
            </div>
          </Link>
          {menu.map((node) => (
            <SidebarItem key={node.id} node={node} collapsed={collapsed} />
          ))}
        </nav>
      </ScrollArea>
    </aside>
  )
}
