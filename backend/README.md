# Backend - Spring Boot REST API

A Spring Boot REST API serving the admin panel, with Spring Security, JPA/Hibernate, and Liquibase.

## Tech Stack

- Java 26
- Spring Boot 3.5.14
- Spring Security (session-based authentication)
- Spring Data JPA / Hibernate
- PostgreSQL 17
- Liquibase (database migrations)
- Druid connection pool
- Gradle

## Prerequisites

- JDK 26+
- PostgreSQL 17 (or use Docker)

## Running

### With Docker (database only)

```bash
# From the project root
docker compose up postgres

# Then start the backend
./gradlew bootRun
```

The API will be available at http://localhost:8080/api/

### Configuration

| Property | Default | Description |
|----------|---------|-------------|
| `server.port` | 8080 | HTTP port |
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/demo` | Database URL |
| `spring.datasource.username` | root | Database user |
| `spring.datasource.password` | 123456 | Database password |
| `app.pagesize` | 20 | Default page size for paginated queries |
| `app.cors.allowed-origins` | `http://localhost:5173,http://localhost:3000` | CORS origins |

For Docker deployment, the `docker` profile overrides the datasource URL to `jdbc:postgresql://postgres:5432/demo`.

## API Endpoints

### Authentication

| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/admin/auth/login` | Login (JSON: `{username, password}`) |
| GET | `/api/admin/auth/me` | Get current user and menu tree |
| POST | `/api/admin/auth/logout` | Logout |

### Staff

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/admin/staff?page=1&rows=20` | List staff (paginated) |
| GET | `/api/admin/staff/{id}` | Get staff by ID |
| POST | `/api/admin/staff` | Create staff |
| PUT | `/api/admin/staff/{id}` | Update staff |
| DELETE | `/api/admin/staff/{id}` | Delete staff |
| PUT | `/api/admin/staff/{id}/password` | Change password |
| PUT | `/api/admin/staff/{id}/enabled` | Toggle enabled |

### Roles

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/admin/roles?page=1` | List roles |
| POST | `/api/admin/roles` | Create role |
| PUT | `/api/admin/roles/{id}` | Update role |
| DELETE | `/api/admin/roles/{id}` | Delete role |
| GET | `/api/admin/roles/{roleId}/menus` | Get role menu tree |
| PUT | `/api/admin/roles/{roleId}/menus` | Assign menus to role |
| GET | `/api/admin/roles/{roleId}/functions` | Get role functions |
| PUT | `/api/admin/roles/{roleId}/functions` | Assign functions to role |

### Menus, Departments, Codes, Functions, etc.

All follow the same REST pattern: `GET` (list), `POST` (create), `PUT /{id}` (update), `DELETE /{id}` (delete).

| Resource | Base Path |
|----------|-----------|
| Menus | `/api/admin/menus` |
| Departments | `/api/admin/departments` |
| Codes | `/api/admin/codes` |
| Code Types | `/api/admin/code-types` |
| Functions | `/api/admin/functions` |
| Users | `/api/admin/users` |
| Tasks | `/api/admin/tasks` |
| News | `/api/admin/news` |
| Staff Logins | `/api/admin/staff-logins` |
| Profile | `/api/admin/profile` |

### Response Formats

**Paginated list (DataGrid):**
```json
{ "total": 100, "rows": [...] }
```

**Operation result (Messager):**
```json
{ "success": true, "msg": null, "code": 0 }
```

**Tree data (TreeNode):**
```json
[{ "id": 1, "text": "Node", "pid": 0, "children": [...], "attributes": {...} }]
```

## Database

The database schema is managed by Liquibase. Migrations are in `src/main/resources/db/changelog/`. The initial schema and seed data (including the `admin` user) are applied automatically on first startup.

## Project Structure

```
src/main/java/com/lintech/
├── config/          Security and CORS configuration
├── controller/api/  REST API controllers
├── core/easyui/     Response DTOs (DataGrid, Messager, TreeNode)
├── dao/             Spring Data JPA repositories
├── entity/          JPA entity classes
├── security/        Spring Security components
└── service/         Business logic services
```
