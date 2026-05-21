export interface DataGrid<T> {
  total: number
  rows: T[]
}

export interface Messager {
  success: boolean
  msg?: string
}

export interface TreeNode {
  id: number | string
  text: string
  state?: 'open' | 'closed'
  checked?: boolean
  pid?: number | string
  seq?: number
  attributes?: Record<string, unknown>
  children?: TreeNode[]
}

export interface Combobox {
  value: string | number
  text: string
}

export interface Staff {
  id?: number
  name?: string
  loginName?: string
  password?: string
  yearEntry?: string
  yearSeparation?: string
  companyId?: number
  deptId?: string
  positionId?: string
  sex?: boolean
  birthday?: string
  mobile?: string
  education?: string
  nation?: string
  marital?: boolean
  household?: string
  profession?: string
  address?: string
  email?: string
  idCard?: string
  status?: boolean
  createTime?: string
  creator?: number
  modifyTime?: string
  modificator?: number
  resume?: string
  evaluation?: string
  enabled?: number
  locked?: number
  follow?: string
}

export interface Role {
  id?: number
  code?: string
  name?: string
  description?: string
}

export interface Menu {
  id?: number
  pid?: number
  text?: string
  url?: string
  icon?: string
  seq?: number
  state?: string
  enabled?: boolean
  createTime?: string
  creator?: number
  modifyTime?: string
  modificator?: number
}

export interface Department {
  id?: number
  pid?: number
  name?: string
  seq?: number
  description?: string
  createTime?: string
  creator?: number
  modifyTime?: string
  modificator?: number
}

export interface Code {
  id?: number
  type?: string
  code?: string
  name?: string
  value?: string
  seq?: number
  enable?: boolean
  description?: string
}

export interface CodeType {
  id?: number
  code?: string
  name?: string
  seq?: number
  description?: string
}

export interface FunctionEntity {
  id?: number
  code?: string
  name?: string
  description?: string
}

export interface Task {
  id?: number
  name?: string
  period?: number
  targetObject?: string
  beginDate?: string
  endDate?: string
  cronExpression?: string
  description?: string
  state?: number
  createDate?: string
  creator?: string
  modifyDate?: string
  modificator?: string
}

export interface StaffLogin {
  id?: number
  staffId?: number
  staffName?: string
  loginDate?: string
  loginIp?: string
}

export interface News {
  id?: number
  title?: string
  content?: string
  creator?: number
  createDate?: string
  modificator?: number
  modifyDate?: string
}

export interface User {
  id?: number
  name?: string
  loginName?: string
  password?: string
  sex?: boolean
  birthday?: string
  mobile?: string
  address?: string
  email?: string
  idCard?: string
  createTime?: string
  creator?: number
  modifyTime?: string
  modificator?: number
  enabled?: number
  locked?: number
}

export interface AuthResponse {
  success: boolean
  msg?: string
  staff?: Staff
  menu?: TreeNode[]
}
