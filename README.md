# Todo List Backend

REST API for personal task management, built with Spring Boot and JWT authentication.

## Tech Stack

- **Java 21** + **Spring Boot 4**
- **Spring Security** + **JWT** (JJWT)
- **PostgreSQL** (via Docker)
- **Spring Data JPA** + **Hibernate**
- **MapStruct** + **Lombok**
- **Bean Validation**

## Prerequisites

- Java 21
- Maven
- Docker and Docker Compose

## Getting Started

1. Start the database with Docker:

```bash
docker-compose up -d
```

2. Run the application:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Endpoints

### Authentication — `/auth`

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| POST | `/auth/register` | Register a new user | No |
| POST | `/auth/login` | Log in and get a JWT token | No |

**Register example:**
```json
POST /auth/register
{
  "username": "angel",
  "email": "angel@email.com",
  "password": "my_password"
}
```

**Login response:**
```json
{
  "token": "******"
}
```

Use the token in subsequent requests via the header:
```
Authorization: ******
```

---

### User — `/users`

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| GET | `/users/me` | Get the authenticated user's profile | Yes |

---

### Tasks — `/tasks`

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| POST | `/tasks` | Create a new task | Yes |
| GET | `/tasks` | List my tasks | Yes |
| PUT | `/tasks/{id}` | Update a task | Yes |
| DELETE | `/tasks/{id}` | Delete a task | Yes |
| GET | `/tasks/completed/{status}` | Filter tasks by completion status (`true`/`false`) | Yes |
| GET | `/tasks/search?title=` | Search tasks by title | Yes |

**Create task example:**
```json
POST /tasks
{
  "title": "Study Spring Security",
  "description": "Review filters and JWT",
  "completed": false
}
```

## Security

- All `/tasks` and `/users` endpoints require a valid JWT.
- Each user can only view and modify their own tasks.
- Missing or invalid tokens return `401 Unauthorized`.
- Valid tokens without sufficient permissions return `403 Forbidden`.

## Project Structure

```
src/main/java/org/angelo/todolist/
├── auth/        # Registration, login, JWT filter and service
├── config/      # Security and CORS configuration
├── exceptions/  # Global error handling
├── tasks/       # Task CRUD
└── users/       # User management
```
