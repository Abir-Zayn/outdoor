# Authorization Guide

This document explains how to sign in, register users, and change password in this project.

## Base URL

- Local: `http://localhost:8010`

## Important Notes

- Login uses `email` in the `username` field.
- Signup endpoint is protected.
- Change-password endpoint is protected.
- You must sign in first and pass token auth for protected endpoints.

## 1. Signin (Login)

Endpoint:

- `POST /api/account/login`

Request body:

```json
{
  "username": "admin@example.com",
  "password": "Admin12345#"
}
```

cURL:

```bash
curl -X POST "http://localhost:8010/api/account/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin@example.com",
    "password": "Admin12345#"
  }'
```

Success response:

```json
{
  "token": "your_auth_token_here"
}
```

## 2. Signup (Register User)

Endpoint:

- `POST /api/account/register`

Required header:

- `Authorization: Token <your_auth_token_here>`

Request body:

```json
{
  "email": "newuser@example.com",
  "first_name": "New",
  "last_name": "User",
  "password": "StrongPass123!",
  "password2": "StrongPass123!"
}
```

cURL:

```bash
curl -X POST "http://localhost:8010/api/account/register" \
  -H "Content-Type: application/json" \
  -H "Authorization: Token your_auth_token_here" \
  -d '{
    "email": "newuser@example.com",
    "first_name": "New",
    "last_name": "User",
    "password": "StrongPass123!",
    "password2": "StrongPass123!"
  }'
```

Success response:

```json
{
  "response": "Successfully registered a new user.",
  "email": "newuser@example.com",
  "first_name": "New",
  "last_name": "User",
  "token": "token_for_new_user"
}
```

## 3. Change Password

Endpoint:

- `PUT /api/account/change-password`

Required header:

- `Authorization: Token <your_auth_token_here>`

Request body:

```json
{
  "old_password": "Admin12345#",
  "new_password": "Abirzayn561#"
}
```

cURL:

```bash
curl -X PUT "http://localhost:8010/api/account/change-password" \
  -H "Content-Type: application/json" \
  -H "Authorization: Token your_auth_token_here" \
  -d '{
    "old_password": "Admin12345#",
    "new_password": "Abirzayn561#"
  }'
```

Success response:

```text
Password updated successfully
```

Wrong old password response:

```text
Your provided old password is not correct.
```

## Common Errors

### 400 Bad Request

Cause:

- Invalid JSON shape (for example sending nested objects for `username`/`password`).
- Password mismatch (`password` and `password2` different).
- Invalid old password for change-password.

### 401 Unauthorized

Cause:

- Missing `Authorization` header on protected endpoints.
- Invalid or expired token.

### 500 / ProgrammingError: relation does not exist

Cause:

- Database migrations not applied.

Fix:

```bash
docker compose exec app python /app/src/manage.py migrate
```

## Swagger Tips

- Use `POST /api/account/login` first.
- Copy returned token.
- Click `Authorize` in Swagger UI and enter:

```text
Token your_auth_token_here
```

- Then call protected endpoints:
  - `POST /api/account/register`
  - `PUT /api/account/change-password`

