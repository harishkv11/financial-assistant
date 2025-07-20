# 💰 AI-Powered Personal Finance Assistant

An intelligent backend system that extracts, summarizes, and analyzes financial documents using OCR and LLMs. Built with Spring Boot, the service offers secure authentication, smart document insights, and seamless integration with AI APIs like Cohere.

## 🚀 Features

- 🔐 JWT-based User Authentication (PostgreSQL)
- 📄 Upload & extract text from images/PDFs using Tesseract OCR
- 🤖 Summarize financial data via Cohere API
- 📊 Generate keyword-based insights using Mongo Aggregation
- 🗃️ Dual Database Support: PostgreSQL + MongoDB
- 🐳 Containerized with Docker

## 🧠 Tech Stack

`Spring Boot`, `PostgreSQL`, `MongoDB`, `JWT`, `Tesseract OCR`, `Cohere API`, `Docker`, `Maven`

## 📂 Module Overview

| Module             | Description                                                      |
|--------------------|------------------------------------------------------------------|
| `Auth`             | User signup/login with JWT security (PostgreSQL)                |
| `Upload`           | Upload financial documents and extract text using OCR           |
| `Summarization`    | Send extracted text to Cohere for summary and categorization    |
| `Insights`         | Generate real-time user-specific insights with Mongo Aggregation|
| `Persistence`      | Store user info in PostgreSQL and documents in MongoDB          |

## 🔧 Setup Instructions

```bash
# Clone the repo
git clone https://github.com/harishkv11/personal-finance-assistant.git
cd personal-finance-assistant

# Configure DB credentials in application.properties
# PostgreSQL for users, MongoDB for uploads

# Add your Cohere API key
export COHERE_API_KEY=your_key_here

# Run the app
./mvnw spring-boot:run
```

## 📬 API Endpoints

| Method | Endpoint                     | Description                        |
|--------|------------------------------|------------------------------------|
| POST   | `/api/auth/register`         | Register a new user                |
| POST   | `/api/auth/login`            | Login and receive JWT              |
| POST   | `/api/upload`                | Upload PDF/Image document          |
| GET    | `/api/summarize/{docId}`     | Get AI-generated summary           |
| GET    | `/api/insights/{userId}`     | Get financial insights             |
