# G-Scores

A web application to look up 2024 National High School Exam (THPT) scores by registration number, view score statistics by subject, and see the top 10 students in Group A (Math, Physics, Chemistry).

## Demo

- **Frontend:** https://g-scores-nine.vercel.app/
- **Backend API:** https://g-scores-production.up.railway.app
- **Swagger UI:** https://g-scores-production.up.railway.app/swagger-ui/index.html

## Tech Stack

**Backend:** Java 17, Spring Boot 3.5, Spring Data JPA, MySQL 8, Flyway, Docker

**Frontend:** React 19, Vite, Tailwind CSS 4, Axios, Recharts

## Getting Started

### Prerequisites

- Node.js ≥ 20
- Docker & Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/thPhonG-oss/g-scores.git
cd g-scores
```

### 2. Run the backend

```bash
cd g-scores-backend
cp .env.example .env        # fill in DB credentials
docker compose up --build
```

Backend runs at `http://localhost:8080`.
On first startup, the app automatically seeds ~1 million rows from the CSV dataset into MySQL (takes about 2–3 minutes).

### 3. Run the frontend

```bash
cd g-score-frontend
npm install
cp .env.example .env        # set VITE_API_BASE_URL=http://localhost:8080
npm run dev
```

Frontend runs at `http://localhost:5173`.

## Environment Variables

**Backend (`.env`)**

| Variable              | Description               |
| --------------------- | ------------------------- |
| `DATABASE_URL`        | MySQL JDBC connection URL |
| `DB_USERNAME`         | MySQL username            |
| `DB_PASSWORD`         | MySQL password            |
| `MYSQL_DATABASE`      | Database name             |
| `CORS_ALLOWED_ORIGIN` | Allowed frontend origin   |

**Frontend (`.env`)**

| Variable            | Description      |
| ------------------- | ---------------- |
| `VITE_API_BASE_URL` | Backend base URL |

## API Endpoints

| Method | Endpoint                        | Description                              |
| ------ | ------------------------------- | ---------------------------------------- |
| `GET`  | `/api/v1/students/{sbd}/scores` | Get scores by registration number        |
| `GET`  | `/api/v1/scores/statistics`     | Score distribution statistics by subject |
| `GET`  | `/api/v1/scores/top-group-a`    | Top 10 students in Group A               |
