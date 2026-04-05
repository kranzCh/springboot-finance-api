# Personal Finance Tracker API — Project Development Plan

> **Spring Boot 3 · Java 21 · PostgreSQL · JWT · Docker**

| | | |
|:---:|:---:|:---:|
| ⏱ 10 Days | 👤 Solo | 🎯 Just Starting |

---

## 1. Project Overview

A RESTful backend API for tracking personal income, expenses, and account balances. The project serves a dual purpose: delivering a functional fintech-adjacent product while acting as a structured learning path for transitioning from C#/.NET to the Java/Spring Boot ecosystem.

> **Author:** C#/.NET Backend Engineer (4+ years experience)
> **Goal:** Build a production-quality Spring Boot REST API from scratch in 10 evenings
> **Context:** Familiar with layered architecture, JWT, ORM, REST — new to Java/Spring specifically

### 1.1 Project Goals

- Deliver a working RESTful API for auth, account management, and transaction tracking
- Demonstrate clean layered architecture: Controller → Service → Repository
- Apply Java/Spring Boot patterns equivalent to known .NET patterns
- Produce a GitHub-ready project with docs, tests, and Docker support

### 1.2 Scope

- **In Scope:** Auth module, Account CRUD, Transaction module, Swagger docs, unit tests, Docker
- **Out of Scope:** Frontend UI, email notifications, multi-user permissions, cloud deployment

---

## 2. Technology Stack

| Category | Technology | Notes for .NET Devs |
|---|---|---|
| Language | Java 21 | Latest LTS release; familiar OOP paradigm from C# |
| Framework | Spring Boot 3 | Auto-config, convention-over-config, analogous to ASP.NET Core |
| Security | Spring Security + JWT | Filter chain for auth; analogous to ASP.NET Core middleware |
| ORM / DB | Spring Data JPA + PostgreSQL | Hibernate under the hood; similar to EF Core |
| Build Tool | Maven or Gradle | Maven = familiar XML config; Gradle = more flexible |
| API Docs | Swagger / OpenAPI 3 | Auto-generates interactive docs from annotations |
| Testing | JUnit 5 + Mockito | Analogous to xUnit + Moq in .NET |
| DevOps | Docker + docker-compose | Containerise app + PostgreSQL together |

---

## 3. Architecture & Modules

The application follows a strict three-layer architecture to ensure separation of concerns and testability — identical in principle to the approach used in ASP.NET Core projects.

### 3.1 Layer Architecture

```
HTTP Request
     ↓
Controller Layer  (REST endpoints, request/response mapping, validation annotations)
     ↓
Service Layer     (business logic, transaction management, exception throwing)
     ↓
Repository Layer  (Spring Data JPA interfaces, DB queries via Hibernate)
     ↓
PostgreSQL Database
```

### 3.2 Module Breakdown

#### Auth Module
- `POST /api/auth/register` — create new user, hash password (BCrypt)
- `POST /api/auth/login` — validate credentials, return JWT
- JWT filter — validates token on every protected request
- _.NET equivalent: ASP.NET Core Identity + middleware pipeline_

#### Account Module
- `GET/POST /api/accounts` — list all accounts, create new account
- `GET/PUT/DELETE /api/accounts/{id}` — get, update, delete a specific account
- Account types: `CHECKING`, `SAVINGS` — mapped as a JPA enum
- Balance is read-only via API — auto-calculated from transactions

#### Transaction Module
- `POST /api/transactions` — create income or expense, auto-updates account balance
- `GET /api/transactions` — list with filters: `?accountId=`, `?startDate=`, `?endDate=`, `?category=`
- Transaction types: `INCOME`, `EXPENSE` — enforced at service layer
- Balance update logic lives in `TransactionService`, wrapped in `@Transactional`

---

## 4. Timeline — 10-Day Gantt Chart

Each block represents one evening session (~2–3 hours).

| Task / Phase | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8 | D9 | D10 |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 🔵 Project Setup & JWT Auth | ▪ | ▪ | | | | | | | | |
| 🔷 Account Module (CRUD) | | | ▪ | ▪ | | | | | | |
| 🟣 Transaction Module | | | | | ▪ | ▪ | | | | |
| 🟠 Exception Handling & Swagger | | | | | | | ▪ | ▪ | | |
| 🟢 Tests, Docker & README | | | | | | | | | ▪ | ▪ |

**Legend:** 🔵 Phase 1: Auth · 🔷 Phase 2: Account · 🟣 Phase 3: Transaction · 🟠 Phase 4: Docs · 🟢 Phase 5: Tests

---

## 5. Milestones & Definition of Done

| # | Target Day | Milestone | Definition of Done |
|:---:|---|---|---|
| M1 | End of Day 2 | Auth Working | JWT register/login, token validation live |
| M2 | End of Day 4 | Account API Done | Full CRUD for bank accounts, JPA entities configured |
| M3 | End of Day 6 | Transaction API Done | Income/expense logic, balance auto-update, filters working |
| M4 | End of Day 8 | Docs & Error Handling | Swagger UI live, GlobalExceptionHandler active |
| M5 | End of Day 10 | Production Ready | All tests pass, Docker running, README complete, pushed to GitHub |

---

## 6. Day-by-Day Task Breakdown

### 🔵 Phase 1: Authentication Foundation (Days 1–2)

#### Day 1 — Project Bootstrap & Security Config
- [x] Initialize Spring Boot 3 project via Spring Initializr (Web, Security, JPA, PostgreSQL driver, Lombok)
- [x] Configure `application.yml`: datasource, JPA `ddl-auto=create`, server port
- [x] Create PostgreSQL database locally (or via Docker); verify connection
- [x] Add JWT library dependency (`jjwt` or `spring-security-jwt`) to `pom.xml` / `build.gradle`
- [x] Scaffold package structure: `controller`, `service`, `repository`, `model`, `dto`, `security`, `exception`
- [x] Create `User` entity with `@Entity`, `@Id`, `@GeneratedValue`, username, passwordHash, createdAt
- [x] Create `UserRepository` extending `JpaRepository<User, Long>`

#### Day 2 — JWT Auth Endpoints
- [x] Implement `JwtTokenProvider`: `generateToken()`, `validateToken()`, `extractUsername()`
- [x] Implement `JwtAuthenticationFilter` extends `OncePerRequestFilter`
- [x] Configure `SecurityFilterChain`: permit `/api/auth/**`, require auth on all others
- [x] Implement `AuthService`: `register()` with `BCryptPasswordEncoder`, `login()` returning JWT
- [x] Create `AuthController`: `POST /api/auth/register` and `POST /api/auth/login`
- [x] Define `RegisterRequest` and `LoginResponse` DTOs
- [x] Manual test: register a user, login, inspect JWT payload at jwt.io

---

### 🔷 Phase 2: Account Module (Days 3–4)

#### Day 3 — Account Entity & Repository
- [ ] Create `Account` entity: id, name, type (`AccountType` enum: `CHECKING`/`SAVINGS`), balance, user (`ManyToOne`), createdAt
- [ ] Create `AccountType` enum
- [ ] Create `AccountRepository` extending `JpaRepository<Account, Long>`
- [ ] Create `AccountDto` and `CreateAccountRequest` DTOs
- [ ] Write `AccountService` with `findAllByUser()`, `findById()`, `create()`, `update()`, `delete()`
- [ ] Add ownership check: throw 403 if `account.getUser() != currentUser`

#### Day 4 — Account REST Endpoints
- [ ] Create `AccountController` with `@RestController`, `@RequestMapping("/api/accounts")`
- [ ] Implement `GET /api/accounts` — returns list of current user's accounts
- [ ] Implement `POST /api/accounts` — create new account
- [ ] Implement `GET /api/accounts/{id}` — get single account (with ownership check)
- [ ] Implement `PUT /api/accounts/{id}` — update name/type (not balance directly)
- [ ] Implement `DELETE /api/accounts/{id}` — soft-delete or hard-delete
- [ ] Manual test all endpoints with Postman or curl using JWT from Day 2

---

### 🟣 Phase 3: Transaction Module (Days 5–6)

#### Day 5 — Transaction Entity & Business Logic
- [ ] Create `Transaction` entity: id, account (`ManyToOne`), type (`INCOME`/`EXPENSE`), amount, category, description, transactionDate
- [ ] Create `TransactionType` enum
- [ ] Create `TransactionRepository` with custom queries: `findByAccountAndDateBetween()`, `findByCategory()`
- [ ] Implement `TransactionService.create()`: persist transaction AND update `account.balance` atomically
- [ ] Annotate `create()` with `@Transactional` — ensures both writes succeed or both roll back
- [ ] Add validation: amount must be > 0; account must exist and belong to current user

#### Day 6 — Transaction Endpoints & Filtering
- [ ] Create `TransactionController`: `POST /api/transactions`, `GET /api/transactions`
- [ ] Implement query filtering via `@RequestParam`: `accountId`, `startDate`, `endDate`, `category` (all optional)
- [ ] Create `CreateTransactionRequest` DTO and `TransactionDto` response DTO
- [ ] Verify account balance updates correctly after each transaction via `GET /api/accounts/{id}`
- [ ] Write a manual test scenario: create account → add income → add expense → check balance
- [ ] Edge case test: transaction on a non-owned account should return `403 Forbidden`

---

### 🟠 Phase 4: Exception Handling & API Docs (Days 7–8)

#### Day 7 — Global Exception Handling & Validation
- [ ] Create `GlobalExceptionHandler` with `@ControllerAdvice`
- [ ] Handle `ResourceNotFoundException` (404), `AccessDeniedException` (403), `ValidationException` (400)
- [ ] Create `ErrorResponse` DTO: `{ timestamp, status, error, message, path }`
- [ ] Add `@Valid` on controller method parameters; add JSR-303 annotations to DTOs (`@NotBlank`, `@Positive`, etc.)
- [ ] Test that invalid requests return structured error JSON (not Spring's default HTML error page)
- [ ] Add custom `ResourceNotFoundException extends RuntimeException`

#### Day 8 — Swagger / OpenAPI Documentation
- [ ] Add `springdoc-openapi-starter-webmvc-ui` dependency
- [ ] Annotate controllers with `@Tag(name = "Auth")`, `@Operation(summary = "...")`, `@ApiResponse`
- [ ] Configure Swagger to support JWT: add `@SecurityScheme` for Bearer token in `OpenAPIConfig`
- [ ] Verify Swagger UI at `http://localhost:8080/swagger-ui.html`
- [ ] Test: authenticate via Swagger UI, then call a protected endpoint from within the UI
- [ ] Review and clean up all endpoint summaries and response descriptions

---

### 🟢 Phase 5: Tests, Docker & GitHub (Days 9–10)

#### Day 9 — Unit Tests (JUnit 5 + Mockito)
- [ ] Write `AuthServiceTest`: test `register()` with duplicate username throws exception, test `login()` with wrong password
- [ ] Write `AccountServiceTest`: test `create()`, `findById()` with valid/invalid ownership
- [ ] Write `TransactionServiceTest`: test balance update after INCOME, after EXPENSE, rollback on invalid account
- [ ] Use `@Mock` for repositories, `@InjectMocks` for services — analogous to Moq in .NET
- [ ] Run `mvn test` (or `./gradlew test`) — all tests must pass before Day 10
- [ ] Aim for coverage on all service-layer business logic branches

#### Day 10 — Docker, README & GitHub Push
- [ ] Write `Dockerfile`: `FROM eclipse-temurin:21-jre`, COPY jar, EXPOSE 8080, ENTRYPOINT
- [ ] Write `docker-compose.yml`: two services — app (build from Dockerfile) + db (`postgres:15-alpine`)
- [ ] Set environment variables in docker-compose: `SPRING_DATASOURCE_URL`, `POSTGRES_USER`, `POSTGRES_PASSWORD`
- [ ] Test: `docker-compose up` — app starts, connects to DB, Swagger UI loads
- [ ] Write GitHub README: project description, architecture diagram, tech stack badges, run instructions
- [ ] Final review: all tests pass, Docker working, Swagger complete, README written — push to GitHub

---

## 7. Risks & Mitigations

| Risk | Likelihood | Mitigation |
|---|:---:|---|
| Spring Security config complexity | High | Study official Spring Security docs filter chain diagram first; keep config minimal |
| JPA relationship mapping errors | Medium | Start with unidirectional `@ManyToOne` only; add `@OneToMany` lazily if needed |
| `@Transactional` not rolling back | Medium | Ensure service is not calling itself internally; use integration test to verify |
| JWT filter order issues | Low | Add filter before `UsernamePasswordAuthenticationFilter` explicitly in `SecurityConfig` |
| Docker networking (app can't reach DB) | Low | Use service name as hostname in docker-compose; use healthcheck or wait-for-it |

---

## 8. Success Criteria

The project is considered complete when **all** of the following are true:

- ✅ All 3 modules (Auth, Account, Transaction) are fully functional via Swagger UI
- ✅ Unit tests cover all service-layer business logic; `mvn test` passes with 0 failures
- ✅ `docker-compose up` starts the full stack (app + DB) without manual intervention
- ✅ Swagger UI is accessible and supports JWT-authenticated requests
- ✅ GitHub repository has a complete README with architecture notes and run instructions
- ✅ No hardcoded secrets — all config via environment variables or `application.yml`

---

*Personal Finance Tracker — Project Development Plan · Java 21 · Version 1.0*
