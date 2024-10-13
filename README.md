# JWT User Management System with Spring Boot
This project is a JWT-based user management system built with Spring Boot. It includes core functionalities such as user registration and login with JSON Web Tokens (JWT) for secure authentication.

Key Features:
User Registration: Allows users to create an account by providing a username, password, and email.
User Login: Users can log in using their credentials, and the system generates a JWT token for secure access to protected resources.
JWT Authentication: After login, the JWT token is used to authenticate users and grant access to secured endpoints.
Stateless Session Management: Utilizes stateless session policy via JWT to ensure scalable and secure authentication without maintaining server-side sessions.
Spring Security Integration: Implements Spring Security for role-based access and authentication.
Technologies Used:
Spring Boot for REST API development
Spring Security for authentication and authorization
JWT (JSON Web Token) for secure token-based authentication
BCrypt Password Encoder for password encryption
MySQL Database for persistent data storage
H2 Database for in-memory testing (optional)
This project is ideal for learning and applying JWT authentication in Spring Boot applications and can be extended with further user management functionalities like password reset, account activation, etc.
