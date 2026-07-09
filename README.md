# Interactive Headless Portfolio API & Terminal

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Overview

> An API-First personal portfolio engineered to demonstrate backend capabilities. It features a decoupled architecture where a lightweight frontend consumes a robust Java Spring Boot REST API, alongside a real-time WebSocket connection that streams server logs directly to an interactive terminal UI.

## About the Developer
I am a Junior Java Backend Developer with 6 months of freelance experience focused on building robust, scalable APIs and managing relational databases. This project serves as a live demonstration of my system design, security, and architectural skills.

## Tech Stack & Priorities
1. **Core Backend:** Java 21, Spring Boot (Web, Data JPA, Security, WebSockets).
2. **Database:** PostgreSQL.
3. **Frontend (Consumer):** Vanilla JavaScript, HTML5, SCSS (kept minimal to prioritize backend logic).
4. **Infrastructure:** Docker & Docker Compose for isolated, one-command deployment.

## Architecture Design
Explain the "Split-Screen" concept here. 
* **Left Panel (GUI):** Asynchronously fetches and renders data from standard REST endpoints.
* **Right Panel (Terminal):** Maintains an open WebSocket connection, receiving and displaying real-time server logs, HTTP request processing, and internal application events.

## Getting Started (Local Development)

### Prerequisites
* Java 21+
* Maven 3.8+
* Docker & Docker Compose

### Installation & Run
1. Clone the repository:

   ```bash
   git clone https://github.com/Michaelec19/mespinal-backend-portfolio.git
   ```

2. Navigate to the project directory:
    ```bash
   cd mespinal-backend-portfolio
   ```

3. Start the infrastructure (Database) and Application using Docker:
    ```bash
   docker-compose up --build
   ```

4. Access the application:

    - Frontend UI: http://localhost:8080
    - Swagger API Documentation: http://localhost:8080/swagger-ui.html

## Core API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/profile` | Retrieves core developer information. | No |
| `GET` | `/api/v1/projects` | Fetches a list of portfolio projects. | No |
| `POST` | `/api/v1/contact` | Sends an email/message to the developer. | No (Rate Limited) |
| `POST` | `/api/v1/auth/login` | Authenticates admin and returns JWT. | No |
| `POST` | `/api/v1/projects` | Adds a new project to the database. | Yes (JWT) |

Let's Connect 🤝

- LinkedIn: https://www.linkedin.com/in/michael-espinal-java/
- Email: michaelec519@gmail.com