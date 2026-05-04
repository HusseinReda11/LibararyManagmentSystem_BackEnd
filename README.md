# LibararyManagmentSystem_BackEnd
# 📚 Library Management System API

**A RESTful API for managing a library system — built with Spring Boot and secured with Basic Authentication. Supports book management, user management, and borrowing operations.
✅ Spring Security ✅ JWT Authentication ✅ Role-Based Access Control (RBAC) ✅ RESTful API Design ✅ CRUD Operations✅ OOP COncepts ✅ MysqlServer**
---
src/main/java/com/yourpackage/
│
├── Controller/
│   ├── AuthController.java        # Handles authentication (login/register)
│   ├── BookController.java        # Manages books (CRUD)
│   ├── BorrowController.java      # Handles borrowing/return operations
│   └── UserController.java        # Manages users
│
├── Models/
│   ├── Book.java                  # Book entity
│   ├── Borrow.java                # Borrow entity
│   ├── User.java                  # User entity
│   └── UserType.java              # Enum (ADMIN / USER)
│
├── Repository/
│   ├── BookRepo.java              # Database operations for books
│   ├── BorrowRepo.java            # Database operations for borrow
│   └── UserRepo.java              # Database operations for users
│
├── Security/
│   ├── SecurityConfiguration.java # Spring Security configuration
│   └── UserDetailsServicee.java   # Load user details for authentication
│
├── Services/
│   ├── BookService.java           # Business logic for books
│   ├── BorrowService.java         # Business logic for borrowing
│   └── UserService.java           # Business logic for users
│
└── LibararyManagmentSystemApplication.java
    # نقطة تشغيل التطبيق (Main Class)
## 🚀 Base URL

```
http://localhost:8081
```

---

## 🔐 Authentication

The API uses **HTTP Basic Authentication**.

> Default credentials: `username: admin` / `password: 123456`

Some endpoints also support JWT-based login via `/auth/login`.

---

## 📋 API Endpoints

### 🔐 Auth

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/auth/register` | Register a new user |
| `POST` | `/auth/login` | Login and get token |

<details>
<summary>Register - Request Body</summary>

```json
{
  "username": "admin",
  "password": "123456",
  "role": "ADMIN"
}
```

</details>

<details>
<summary>Login - Request Body</summary>

```json
{
  "username": "admin",
  "password": "123456"
}
```

</details>

---

### 📚 Books

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| `GET` | `/books` | Get all books | Any |
| `GET` | `/books/{id}` | Get book by ID | Any |
| `GET` | `/books/search/title?title=` | Search by title | Any |
| `GET` | `/books/search/author?author=` | Search by author | Any |
| `GET` | `/books/available/{id}` | Check book availability | Any |
| `POST` | `/books/add` | Add a new book | `ADMIN` |
| `PUT` | `/books/update/{id}` | Update a book | `ADMIN` |
| `DELETE` | `/books/delete/{id}` | Delete a book | `ADMIN` |

<details>
<summary>Add / Update Book - Request Body</summary>

```json
{
  "title": "Clean Code",
  "author": "Robert Martin",
  "available": true
}
```

</details>

---

### 🔄 Borrow

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| `GET` | `/borrow` | Get all borrow records | Any |
| `GET` | `/borrow/{id}` | Get borrow record by ID | Any |
| `GET` | `/borrow/user/{userId}` | Get borrows by user | Any |
| `GET` | `/borrow/active` | Get all active borrows | Any |
| `GET` | `/borrow/overdue` | Get overdue borrows | `ADMIN` |
| `POST` | `/borrow/add?userId=&bookId=` | Borrow a book | Any |
| `PUT` | `/borrow/return/{id}` | Return a book | Any |

---

### 👤 Users

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| `GET` | `/users/` | Get all users | `ADMIN` |
| `GET` | `/users/{id}` | Get user by ID | `ADMIN` |
| `GET` | `/users/username/{username}` | Get user by username | `ADMIN` |
| `PUT` | `/users/update/{id}` | Update user info | `ADMIN` |
| `PUT` | `/users/role/{id}` | Change user role | `ADMIN` |
| `DELETE` | `/users/delete/{id}` | Delete a user | `ADMIN` |

<details>
<summary>Update User - Request Body</summary>

```json
{
  "username": "admin_updated",
  "password": "newpass123",
  "role": "ADMIN"
}
```

</details>

<details>
<summary>Change Role - Request Body</summary>

```json
"ADMIN"
```

</details>

---

## 🛠️ Tech Stack

- **Java** + **Spring Boot**
- **Spring Security** (Basic Auth)
- **REST API**

---

## ▶️ Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/library-management-system.git
   cd library-management-system
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the API**
   ```
   http://localhost:8081
   ```

4. **Register an admin user**
   ```bash
   curl -X POST http://localhost:8081/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username":"admin","password":"123456","role":"ADMIN"}'
   ```

---

## 🧪 Testing with Postman

A Postman collection is included in the repository.

1. Import `LibraryManagementSystem_postman_collection.json` into Postman
2. All requests are pre-configured with Basic Auth
3. Run the **Auth → Register** request first to create your admin account

---

## 👥 User Roles

| Role | Permissions |
|------|-------------|
| `ADMIN` | Full access — manage books, users, and borrow records |
| `USER` | Read books, borrow and return books |

---

## 📄 License

This project is open source. Feel free to use, modify, and distribute.
