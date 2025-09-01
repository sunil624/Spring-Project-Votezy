# 🗳️ Votezy

**Votezy** is an online voting platform built with **Spring Boot** that allows voters to register, cast their votes, and view election results in real-time.  
It provides a set of REST-full APIs to manage voters, candidates, votes, and election results.

---

## 🚀 Features

- 👤 **Voter Management** – Register, update, view, and delete voters.
- 🏛️ **Candidate Management** – Register, update, view, and delete candidates.
- 🗳️ **Voting System** – Ensures one voter can cast only one vote.
- 📊 **Election Results** – Calculates results and declares winners automatically.
- 🛠️ **Validation** – Input validation (e.g., email format, required fields).
- 🌐 **REST APIs** – Ready for integration with frontend apps (React, Angular, etc.).

---

## ⚙️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Spring Validation**
- **MySQL** (Relational Database)
- **Lombok**
- **Maven** (Build tool)

---

## 📂 Project Structure

```
Votezy/
 ├── src/main/java/com/votezy/
 │    ├── entity/           # JPA Entities (Voter, Candidate, Vote, ElectionResult)
 │    ├── controller/       # REST Controllers
 │    ├── service/          # Business Logic
 │    ├── dto/              # Data Transfer Objects
 │    └── VotezyApplication.java
 ├── src/main/resources/
 │    ├── application.properties
 │    └── schema.sql / data.sql (optional for DB initialization)
 ├── pom.xml                # Maven dependencies
 └── README.md
```

---

## 🛠️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/votezy.git
cd votezy
```

### 2️⃣ Configure Database
Create a MySQL database:
```sql
CREATE DATABASE vote_db;
```

Update your **`application.properties`** file:
```properties
spring.application.name=Votezy
spring.datasource.url=jdbc:mysql://localhost:3306/vote_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

### 3️⃣ Build & Run the Project
```bash
mvn clean install
mvn spring-boot:run
```

The app will start at:  
👉 `http://localhost:8080`

---

## 📡 API Endpoints

### Voter APIs
- `POST /api/voters/register` → Register a new voter
- `GET /api/voters/{id}` → Get voter by ID
- `GET /api/voters` → Get all voters
- `PUT /api/voters/update/{id}` → Update voter
- `DELETE /api/voters/{id}` → Delete voter

### Candidate APIs
- `POST /api/candidate/register` → Register a new candidate
- `GET /api/candidate/{id}` → Get candidate by ID
- `GET /api/candidate` → Get all candidates
- `PUT /api/candidate/update/{id}` → Update candidate
- `DELETE /api/candidate/{id}` → Delete candidate

### Voting APIs
- `POST /api/votes/cast` → Cast a vote
- `GET /api/votes` → Get all votes

### Election Result APIs
- `POST /api/election-result/declare` → Declare election result
- `GET /api/election-result` → Get all election results

---

## 🔒 Rules & Constraints

- Each **voter can only vote once**.
- Vote count is **automatically updated** for candidates.
- Election results include **total voters & winner details**.

---

## ✅ Example API Usage

### Cast a Vote
**Request**  
`POST /api/votes/cast`
```json
{
  "voterId": 1,
  "candidateId": 2
}
```

**Response**
```json
{
  "message": "Vote casted successfully.",
  "success": true,
  "candidateId": 2,
  "voterId": 1
}
```

---

## 📌 Future Improvements

- 🔑 Add **Authentication & JWT Security**.
- 📱 Build a **frontend** (React/Angular) for user interaction.
- 🗂️ Integrate **Swagger UI** for API documentation.
- 📊 Add **real-time results** with WebSockets.

---

## 🤝 Contributing

1. Fork the repository.
2. Create a new branch for your feature.
3. Commit your changes.
4. Push the branch and create a Pull Request.

---

## 📝 License

This project is licensed under the **Sunil Shah**.  
