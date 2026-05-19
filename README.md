# Spring Web Prototype

A full-stack admin panel application with a Spring Boot REST API backend and React + TypeScript frontend.

## Architecture

```
spring-web-prototype/
├── backend/          Spring Boot 3.5 REST API (Java 26)
├── frontend/         React + TypeScript SPA (Vite, shadcn/ui, TailwindCSS)
└── docker-compose.yml
```

| Service    | Technology                           | Port |
|------------|--------------------------------------|------|
| PostgreSQL | PostgreSQL 17                        | 5432 |
| Backend    | Spring Boot 3.5, JPA, Spring Security| 8080 |
| Frontend   | React 18, Vite, shadcn/ui, Tailwind  | 3000 |

## Quick Start (Docker Compose)

```bash
docker compose up --build
```

Once all services are healthy:

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api/admin/

Default login: `admin` / `123456`

## Development

See individual project READMEs for development setup:

- [Backend README](./backend/README.md)
- [Frontend README](./frontend/README.md)

### Running locally

1. Start PostgreSQL:
   ```bash
   docker compose up postgres
   ```

2. Start the backend (in a separate terminal):
   ```bash
   cd backend
   ./gradlew bootRun
   ```

3. Start the frontend (in a separate terminal):
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

4. Open http://localhost:5173 in your browser.

## Features

- Staff management (CRUD, password reset, enable/disable)
- User management
- Role-based access control (RBAC) with menu and function authorization
- Menu management (hierarchical tree)
- Department management (hierarchical tree)
- Code/lookup table management
- Scheduled task management
- Staff login history
- News management

## Project History

This project was originally a Spring Boot + JSP + jQuery EasyUI monolith, then restructured into separate frontend and backend sub-projects with a modern React UI.
