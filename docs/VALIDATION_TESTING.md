# Input Validation Testing Guide

## What Was Added

### Validation Annotations
- `@NotBlank` - Field cannot be null or empty
- `@NotNull` - Field cannot be null
- `@Size` - Field length constraints

### Validation Rules

**IncidentRequest:**
- **title**: Required, 1-200 characters
- **serviceName**: Required, 1-100 characters
- **severity**: Required (LOW, MEDIUM, HIGH, CRITICAL)
- **errorType**: Required (NETWORK, APPLICATION, CONFIGURATION, RESOURCE)
- **description**: Optional, max 1000 characters
- **correlationId**: Optional, max 100 characters

## Testing Validation

### Test 1: Valid Request (Should Work)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Incident",
    "serviceName": "test-service",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 200 OK with created incident

---

### Test 2: Missing Title (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "serviceName": "test-service",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "title": "Title is required"
}
```

---

### Test 3: Empty Title (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "",
    "serviceName": "test-service",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "title": "Title is required"
}
```

---

### Test 4: Title Too Long (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "'"$(printf 'A%.0s' {1..201})"'",
    "serviceName": "test-service",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "title": "Title must be between 1 and 200 characters"
}
```

---

### Test 5: Missing Service Name (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "serviceName": "Service name is required"
}
```

---

### Test 6: Missing Severity (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "serviceName": "test-service",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "severity": "Severity is required"
}
```

---

### Test 7: Invalid Severity (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "serviceName": "test-service",
    "severity": "SUPER_CRITICAL",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request (JSON parsing error)

---

### Test 8: Missing Error Type (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "serviceName": "test-service",
    "severity": "LOW"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "errorType": "Error type is required"
}
```

---

### Test 9: Description Too Long (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "description": "'"$(printf 'A%.0s' {1..1001})"'",
    "serviceName": "test-service",
    "severity": "LOW",
    "errorType": "APPLICATION"
  }'
```

**Expected**: HTTP 400 Bad Request
```json
{
  "description": "Description must not exceed 1000 characters"
}
```

---

### Test 10: Multiple Validation Errors (Should Fail)
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "",
    "serviceName": ""
  }'
```

**Expected**: HTTP 400 Bad Request with multiple errors
```json
{
  "title": "Title is required",
  "serviceName": "Service name is required",
  "severity": "Severity is required",
  "errorType": "Error type is required"
}
```

---

## Why Validation Matters

**Without Validation:**
- Empty titles create useless incidents
- Missing service names make incidents untraceable
- Invalid data corrupts the system
- Engineers waste time with bad data

**With Validation:**
- ✅ Data quality guaranteed
- ✅ Clear error messages for users
- ✅ Prevents system corruption
- ✅ Saves debugging time

## Next Steps

The next commit will improve error handling to return better validation error messages.
