Add a new finance API feature end-to-end.

Given the feature name: $ARGUMENTS

Using the backend-developer agent, create:
1. JPA Entity in `src/main/java/com/finance/api/model/`
2. Repository interface in `src/main/java/com/finance/api/repository/`
3. Request and Response DTOs in `src/main/java/com/finance/api/dto/`
4. Service interface + implementation in `src/main/java/com/finance/api/service/`
5. REST Controller in `src/main/java/com/finance/api/controller/`

Then using the test-writer agent, create:
6. Unit tests for the service in `src/test/java/com/finance/api/service/`
7. Integration tests for the controller in `src/test/java/com/finance/api/controller/`

Finally using the api-documenter agent:
8. Add OpenAPI annotations to the controller and DTOs

Run `mvn test` to verify everything passes.
