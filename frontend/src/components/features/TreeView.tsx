import { useState } from 'react'
import { ChevronRight, ChevronDown, FolderOpen, Folder } from 'lucide-react'
import { cn } from '@/lib/utils'
import { Checkbox } from '@/components/ui/checkbox'
import type { TreeNode } from '@/types/api'

interface TreeViewItemProps {
  node: TreeNode
  level: number
  selectedId?: number | string
  checkedIds?: Set<number | string>
  onSelect?: (node: TreeNode) => void
  onCheck?: (node: TreeNode, checked: boolean) => void
  showCheckbox?: boolean
}

function TreeViewItem({
  node,
  level,
  selectedId,
  checkedIds,
  onSelect,
  onCheck,
  showCheckbox,
}: TreeViewItemProps) {
  const [expanded, setExpanded] = useState(node.state === 'open')
  const hasChildren = node.children && node.children.length > 0
  const isSelected = selectedId === node.id
  const isChecked = checkedIds?.has(node.id) || false

  const handleToggle = (e: React.MouseEvent) => {
    e.stopPropagation()
    setExpanded(!expanded)
  }

  const handleSelect = () => {
    onSelect?.(node)
  }

  const handleCheck = (checked: boolean) => {
    onCheck?.(node, checked)
  }

  return (
    <div>
      <div
        className={cn(
          'flex items-center gap-2 py-1.5 px-2 rounded cursor-pointer hover:bg-slate-100',
          isSelected && 'bg-blue-50 text-blue-700'
        )}
        style={{ paddingLeft: `${8 + level * 20}px` }}
        onClick={handleSelect}
      >
        <span className="w-4 h-4 flex items-center justify-center" onClick={handleToggle}>
          {hasChildren ? (
            expanded ? (
              <ChevronDown className="h-4 w-4 text-slate-400" />
            ) : (
              <ChevronRight className="h-4 w-4 text-slate-400" />
            )
          ) : null}
        </span>

        {showCheckbox && (
          <Checkbox
            checked={isChecked}
            onCheckedChange={(checked) => handleCheck(checked === true)}
            onClick={(e) => e.stopPropagation()}
          />
        )}

        {hasChildren ? (
          expanded ? (
            <FolderOpen className="h-4 w-4 text-yellow-500" />
          ) : (
            <Folder className="h-4 w-4 text-yellow-500" />
          )
        ) : (
          <div className="w-4" />
        )}

        <span className="text-sm truncate">{node.text}</span>
      </div>

      {hasChildren && expanded && (
        <div>
          {node.children?.map((child) => (
            <TreeViewItem
              key={child.id}
              node={child}
              level={level + 1}
              selectedId={selectedId}
              checkedIds={checkedIds}
              onSelect={onSelect}
              onCheck={onCheck}
              showCheckbox={showCheckbox}
            />
          ))}
        </div>
      )}
    </div>
  )
}

interface TreeViewProps {
  data: TreeNode[]
  selectedId?: number | string
  checkedIds?: Set<number | string>
  onSelect?: (node: TreeNode) => void
  onCheck?: (node: TreeNode, checked: boolean) => void
  showCheckbox?: boolean
}

export function TreeView({
  data,
  selectedId,
  checkedIds,
  onSelect,
  onCheck,
  showCheckbox,
}: TreeViewProps) {
  if (data.length === 0) {
    return <div className="text-sm text-slate-500 p-4">No data</div>
  }

  return (
    <div className="py-2">
      {data.map((node) => (
        <TreeViewItem
          key={node.id}
          node={node}
          level={0}
          selectedId={selectedId}
          checkedIds={checkedIds}
          onSelect={onSelect}
          onCheck={onCheck}
          showCheckbox={showCheckbox}
        />
      ))}
    </div>
  )
}
