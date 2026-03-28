Generate the full Spring Boot project scaffold for the Finance API.

Create the Maven project structure with:
1. `pom.xml` — Spring Boot 3.x, Spring Web, Spring Data JPA, Spring Security, PostgreSQL driver, H2 (test), Lombok, SpringDoc OpenAPI, jjwt, Spring Boot Actuator, Spring Boot Validation
2. `src/main/java/com/finance/api/FinanceApiApplication.java` — main class
3. `src/main/resources/application.yml` — datasource, JPA, JWT secret placeholder, server port 8080
4. `src/main/resources/application-dev.yml` — H2 console enabled, DDL auto create-drop
5. `src/test/java/com/finance/api/FinanceApiApplicationTests.java` — context loads test
6. `src/main/java/com/finance/api/config/SecurityConfig.java` — basic JWT security config skeleton
7. `src/main/java/com/finance/api/exception/GlobalExceptionHandler.java` — @RestControllerAdvice with handlers for MethodArgumentNotValidException, EntityNotFoundException, generic Exception
8. `src/main/java/com/finance/api/dto/ApiResponse.java` — generic response wrapper with data, message, status fields

Use Java 17, Spring Boot 3.2.x, and Maven.
