# Frontend - React Admin Panel

A modern admin panel SPA built with React, TypeScript, and shadcn/ui.

## Tech Stack

- React 18
- TypeScript 5
- Vite 5 (build tool)
- shadcn/ui + TailwindCSS (UI components)
- React Router 6 (routing)
- TanStack React Query (data fetching)
- Axios (HTTP client)
- Lucide React (icons)

## Prerequisites

- Node.js 20+
- npm 10+

## Getting Started

```bash
npm install
npm run dev
```

The dev server starts at http://localhost:5173. API requests to `/api/*` are proxied to `http://localhost:8080` (the backend).

## Scripts

| Script | Description |
|--------|-------------|
| `npm run dev` | Start Vite dev server with HMR |
| `npm run build` | Type-check and build for production |
| `npm run preview` | Preview the production build locally |

## Project Structure

```
src/
├── api/               API client layer (typed axios calls)
│   ├── client.ts      Axios instance with auth interceptor
│   ├── auth.ts        Login/logout/session
│   ├── staff.ts       Staff CRUD
│   ├── roles.ts       Role CRUD + authorization
│   ├── menus.ts       Menu tree CRUD
│   ├── departments.ts Department tree CRUD
│   ├── codes.ts       Code lookup CRUD
│   └── ...
├── components/
│   ├── ui/            shadcn/ui base components
│   ├── layout/        App shell (MainLayout, Sidebar, Header)
│   └── features/      Reusable feature components (DataTable, TreeView)
├── hooks/
│   └── useAuth.tsx    Authentication context and hook
├── pages/             Route pages
│   ├── LoginPage.tsx
│   ├── DashboardPage.tsx
│   ├── StaffPage.tsx
│   ├── RolePage.tsx
│   └── ...
├── types/
│   └── api.ts         TypeScript interfaces matching backend DTOs
├── lib/
│   └── utils.ts       Utility functions
├── App.tsx            Router and provider setup
└── main.tsx           Entry point
```

## Pages

| Route | Page | Description |
|-------|------|-------------|
| `/login` | LoginPage | Authentication |
| `/dashboard` | DashboardPage | Welcome page with stats |
| `/staff` | StaffPage | Staff CRUD management |
| `/users` | UserPage | User CRUD management |
| `/roles` | RolePage | Role management with menu/function authorization |
| `/menus` | MenuPage | Menu tree management |
| `/departments` | DepartmentPage | Department tree management |
| `/codes` | CodePage | Code/lookup management |
| `/code-types` | CodeTypePage | Code type management |
| `/functions` | FunctionPage | Permission/function management |
| `/staff-logins` | StaffLoginPage | Login history (read-only) |
| `/tasks` | TaskPage | Scheduled task management |
| `/news` | NewsPage | News article management |
| `/profile` | ProfilePage | Password change |

## Building for Production

```bash
npm run build
```

Output goes to `dist/`. In production (Docker), nginx serves the static files and proxies `/api/*` to the backend.
