# Finance API

A RESTful API for managing personal finances — accounts, transactions, and budgets — built with Spring Boot 3 and secured with JWT authentication.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2 |
| Build | Maven 3.9 |
| Database | PostgreSQL (prod), H2 (dev/test) |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT (jjwt 0.12) |
| API Docs | SpringDoc OpenAPI 3 (Swagger UI) |
| Testing | JUnit 5, Mockito, Spring Boot Test |
| Utilities | Lombok |

## Project Structure

```
src/
├── main/java/com/finance/api/
│   ├── FinanceApiApplication.java   # Entry point
│   ├── config/
│   │   ├── SecurityConfig.java      # JWT + stateless security filter chain
│   │   └── OpenApiConfig.java       # Swagger UI + bearer auth scheme
│   ├── controller/                  # REST controllers (feature-driven)
│   ├── service/                     # Business logic interfaces + implementations
│   ├── repository/                  # Spring Data JPA repositories
│   ├── model/                       # JPA entities
│   ├── dto/
│   │   ├── request/                 # Validated request bodies
│   │   └── response/
│   │       └── ApiResponse.java     # Generic response envelope
│   ├── exception/
│   │   └── GlobalExceptionHandler.java  # @RestControllerAdvice
│   └── security/
│       ├── JwtService.java          # Token generation + validation
│       ├── JwtAuthenticationFilter.java
│       ├── JwtProperties.java       # Bound from application.yml
│       └── AppUserDetailsService.java   # UserDetailsService (wire to User repo)
├── main/resources/
│   ├── application.yml              # Production config (PostgreSQL)
│   └── application-dev.yml         # Dev config (H2 in-memory)
└── test/java/com/finance/api/
    ├── controller/                  # MockMvc integration tests
    ├── service/                     # Mockito unit tests
    └── repository/                  # @DataJpaTest repository tests
```

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL 14+ (for production profile)

### Clone

```bash
git clone https://github.com/<your-username>/springboot-finance-api.git
cd springboot-finance-api
```

### Run in Dev Mode (H2 in-memory database)

No database setup required — H2 spins up automatically.

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:finance_dev`
- Username: `sa` / Password: *(empty)*

### Run with PostgreSQL

1. Create the database:
   ```sql
   CREATE DATABASE finance_db;
   ```

2. Set environment variables (or update `application.yml`):
   ```bash
   export DB_USERNAME=postgres
   export DB_PASSWORD=your_password
   export JWT_SECRET=your-256-bit-secret-key-here
   ```

3. Run:
   ```bash
   mvn spring-boot:run
   ```

### Environment Variables

| Variable | Default | Description |
|---|---|---|
| `DB_USERNAME` | `postgres` | PostgreSQL username |
| `DB_PASSWORD` | `postgres` | PostgreSQL password |
| `JWT_SECRET` | *(insecure default)* | 256-bit JWT signing secret — **must be changed in production** |

## API Documentation

Once running, Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON spec:

```
http://localhost:8080/api-docs
```

## Authentication

All endpoints (except `/api/auth/**` and `/actuator/health`) require a valid JWT.

1. Obtain a token via `POST /api/auth/login`
2. Include it in subsequent requests:
   ```
   Authorization: Bearer <token>
   ```

Tokens expire after **1 hour**. Refresh tokens are valid for **7 days**.

## API Response Format

All endpoints return a consistent envelope:

```json
{
  "data": { ... },
  "message": "Optional message",
  "status": 200
}
```

Error responses follow the same structure with no `data` field:

```json
{
  "message": "Account not found",
  "status": 404
}
```

## Running Tests

```bash
# Unit + integration tests
mvn test

# Full build including tests
mvn verify
```

Tests use an H2 in-memory database automatically — no PostgreSQL required.

## Claude AI Agents

This project includes pre-configured Claude Code agents and slash commands under `.claude/`:

| Agent | Purpose |
|---|---|
| `backend-developer` | Implements controllers, services, repositories, entities, DTOs |
| `test-writer` | Writes JUnit 5 unit and MockMvc integration tests |
| `security-reviewer` | Audits code for OWASP issues and financial data exposure |
| `api-documenter` | Adds SpringDoc OpenAPI/Swagger annotations |

| Command | Usage |
|---|---|
| `/scaffold` | Generate full project structure |
| `/add-feature <name>` | End-to-end feature generation (entity → tests → docs) |
| `/review [path]` | Security and quality audit with severity report |

## Contributing

1. Branch: `feature/<name>`, `fix/<name>`, `chore/<name>`
2. Commits follow [Conventional Commits](https://www.conventionalcommits.org/) (`feat:`, `fix:`, `refactor:`, `test:`, `docs:`)
3. All new features require service unit tests and controller integration tests
4. Run `mvn verify` before opening a PR

## License

MIT
