# Spring Boot Finance API — Claude Guidelines

## Project Overview
A RESTful Finance API built with Spring Boot. Handles accounts, transactions, budgets, and financial reporting.

## Tech Stack
- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Build**: Maven
- **Database**: PostgreSQL (prod), H2 (test)
- **ORM**: Spring Data JPA / Hibernate
- **Security**: Spring Security + JWT
- **Docs**: SpringDoc OpenAPI (Swagger UI)
- **Testing**: JUnit 5, Mockito, Spring Boot Test

## Project Structure
```
src/
├── main/java/com/finance/api/
│   ├── FinanceApiApplication.java
│   ├── config/          # Security, CORS, Swagger config
│   ├── controller/      # REST controllers
│   ├── service/         # Business logic
│   ├── repository/      # JPA repositories
│   ├── model/           # JPA entities
│   ├── dto/             # Request/Response DTOs
│   ├── exception/       # Global exception handling
│   └── security/        # JWT filter, UserDetails
├── main/resources/
│   ├── application.yml
│   └── application-test.yml
└── test/java/com/finance/api/
    ├── controller/
    ├── service/
    └── repository/
```

## Coding Standards
- Use constructor injection (not field injection with `@Autowired`)
- DTOs for all API input/output — never expose entities directly
- Use `@Valid` on controller method parameters for input validation
- All monetary values use `BigDecimal`, never `double` or `float`
- Global exception handler via `@RestControllerAdvice`
- Return consistent API response envelope: `{ data, message, status }`
- Write unit tests for all service methods; integration tests for controllers

## Security Rules
- Never log sensitive financial data (account numbers, balances in plain text)
- Validate and sanitize all user input
- Use parameterized queries only — no string concatenation in JPQL
- JWT tokens expire in 1 hour; refresh tokens in 7 days
- All endpoints require authentication except `/api/auth/**` and `/actuator/health`

## Git Conventions
- Branch: `feature/`, `fix/`, `chore/`
- Commit: conventional commits (`feat:`, `fix:`, `refactor:`, `test:`, `docs:`)

## Running Locally
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Running Tests
```bash
mvn test
mvn verify   # includes integration tests
```
