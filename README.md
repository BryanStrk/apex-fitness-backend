# 🏋️ MAS POWER - Backend

REST API for gym class management built with Spring Boot.

## Tech Stack
- Java 25
- Spring Boot 4.0.5
- MySQL 8
- JPA / Hibernate
- Lombok

## Entities
- User
- Professor
- Activity

## Run locally
1. Create database: `CREATE DATABASE maspower_db;`
2. Configure `application.properties` with your MySQL credentials
3. Run `MaspowerBackendApplication.java`
4. API available at `http://localhost:8080/api`

## Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/users | Get all users |
| POST | /api/users | Create user |
| GET | /api/professors | Get all professors |
| POST | /api/professors | Create professor |
