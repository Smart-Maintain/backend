#  SmartMaintain - Predictive Maintenance System

##  Description
**SmartMaintain** is an industrial predictive maintenance platform. This repository contains the **Identity Service**, which manages authentication, user registration, and security using a Microservices architecture.

---

##  Features
* **JWT Authentication**: Secure login using HS256 algorithm.
* **Role-Based Access Control (RBAC)**: Different permissions for `ADMIN`, `MANAGER`, `INGENIEUR`, `OPERATEUR`, and `CLIENT`.
* **Password Security**: All passwords are encrypted using `BCrypt`.
* **User Management**: Handled via JPA Inheritance (`SINGLE_TABLE` strategy).
* **CORS Configuration**: Fully configured for frontend integration.

---

##  Tech Stack
* **Backend**: Java 21, Spring Boot 3.x, Spring Security 6.
* **Security**: OAuth2 Resource Server, JWT (Nimbus Jose-JWT).
* **Database**: PostgreSQL / MySQL (Running on Docker).
* **Tools**: Lombok, Maven, IntelliJ IDEA.

---

##  Security Workflow
1.  **Registration**: User data is saved with an encoded password.
2.  **Login**: `AuthenticationManager` verifies credentials via `UserDetailsService`.
3.  **Token Generation**: Upon success, a JWT signed with a Secret Key (HS256) is returned.
4.  **Authorization**: Subsequent requests use the Bearer Token to access protected resources.

---

##  API Endpoints

###  Authentication & Accounts
| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/account/login` | Public | Authenticate and get JWT |
| `POST` | `/api/account/admin` | Public | Create an Admin user |
| `POST` | `/api/account/client` | Public | Create a Client user |
| `POST` | `/api/account/manager` | Public | Create a Manager user |

###  Example Login Request (Postman)
**URL**: `http://localhost:8085/api/account/login`  
**Body (JSON)**:
```json
{
  "email": "karima.admin@smartmaintain.ma",
  "password": "admin123"
}
