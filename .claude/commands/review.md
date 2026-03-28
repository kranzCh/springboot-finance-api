Perform a full security and quality review of the codebase or a specific file/directory.

Target: $ARGUMENTS (if empty, review the entire src/ directory)

Using the security-reviewer agent:
1. Scan all controllers for missing `@Valid`, improper authorization, or exposed entities
2. Check all JPA queries for injection risks
3. Verify JWT configuration is sound
4. Check for sensitive data in logs or responses
5. Review `BigDecimal` usage for all monetary fields

Also check general code quality:
- No business logic in controllers
- Proper `@Transactional` usage
- No N+1 query issues (missing `@EntityGraph` or fetch joins)

Output a prioritized findings report with CRITICAL/HIGH/MEDIUM/LOW/INFO severities.
