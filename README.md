# Smart Study & Exam Prep Engine

A full-stack exam preparation and analytics backend built with **Java 17**, **Spring Boot 3**, and **MySQL**, designed around competitive exam patterns (**SSC CGL**, **RRB NTPC**, and **GATE**).

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.3 (Spring Web, Spring Data JPA)
- **Database:** MySQL (`smart_study_db`)
- **Build Tool:** Maven

## REST API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/status` | Health check for backend service |
| `POST` | `/api/v1/exams/questions` | Add a new MCQ to the question bank |
| `GET` | `/api/v1/exams/{examCategory}` | Fetch questions by exam (`SSC_CGL`, `RRB_NTPC`, `GATE`) |
| `POST` | `/api/v1/exams/submit` | Evaluate mock answers with negative marking & weak-subject detection |

## Local Setup
1. Ensure MySQL is running locally on port `3306` and update `spring.datasource.password` in `src/main/resources/application.properties`.
2. Start the Spring Boot server:
   ```bash
   mvn spring-boot:run
