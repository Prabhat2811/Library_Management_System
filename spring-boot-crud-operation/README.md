# 📚 Library Management System

## 📌 Introduction

The **Library Management System** is a robust web-based application developed to automate and simplify daily library operations such as managing books, users, issue/return records, and search functionality. This project is built using **Spring Boot**, leveraging **Spring Data JPA** for database operations and **RESTful APIs** for scalable communication between frontend and backend.

The application runs using the **embedded/inbuilt Apache Tomcat Server** provided by Spring Boot, eliminating the need for external server installation. This project does not include JSP/HTML view pages or UI screens; all operations are performed by sending HTTP requests through Postman or similar API clients.

---

## 🚀 Features

* 🧾 Backend-only REST API project (no frontend/view pages).
* 📮 Requests are tested and sent using Postman.
* 📖 Add, Update, Delete Book Records.
* 🔍 Search Books by ID, Author, Genre, Availabilty, Author and Title,etc.
* 📊 Book Inventory Tracking.
* 🌐 RESTful APIs for CRUD Operations.
* ⚡ Fast Database Access using Spring Data JPA.
* 🖥️ Runs on Embedded Apache Tomcat Server.

---

## 🛠️ Tech Stack

### Backend

* Java 17+
* Spring Boot
* Spring Data JPA
* REST API Architecture
* Maven

### Database

* MySQL / PostgreSQL

### Server

* Embedded Apache Tomcat Server

### IDE

* Eclipse IDE for Enterprise Java Developers

---

## 📂 Project Structure

```bash
LibraryManagementSystem/
│── src/main/java/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│── src/main/resources/
│   ├── application.properties
│── pom.xml
```

---

## ⚙️ Setup Instructions in Eclipse IDE

### Step 1: Install Requirements

* Install Java JDK 17 or above
* Install Eclipse IDE
* Install MySQL / PostgreSQL

### Step 2: Import Project into Eclipse

1. Open Eclipse IDE
2. Click **File > Import**
3. Select **Existing Maven Projects**
4. Click **Next**
5. Browse your project folder
6. Click **Finish**

### Step 3: Configure Database

Open `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### Step 4: Update Maven Dependencies

* Right Click Project
* Select **Maven > Update Project**
* Click **Finish**

### Step 5: Run Project

1. Open main class containing `@SpringBootApplication`
2. Right Click File
3. Select **Run As > Java Application**

Spring Boot will automatically start the **inbuilt Tomcat Server**.

---

## 🌐 Access Application

After running the application, use Postman to send requests to:

```bash
http://localhost:8080/
```

---

## 🔗 CRUD REST API Endpoints

### Get All Books

```http
GET /api/book
```

### Get Book By ID

```http
GET /api/book/{id}
```

### Add New Book

```http
POST /api/book
```

### Add Multiple Books

```http
POST /api/book/all
```

### Update Book

```http
PUT /api/book/{id}
```

### Delete Book

```http
DELETE /api/book/{id}
```

---

## 📸 Testing Tool

Use **Postman** to test all CRUD endpoints such as GET, POST, PUT, and DELETE requests.

---

## 🤝 Contribution

Feel free to fork this repository and contribute enhancements.

---

## 📄 License

This project is open-source and free to use.

---

## 👨‍💻 Author

Prabhat Ranjan
GitHub: [https://github.com/Prabhat2811](https://github.com/Prabhat2811)

---

## ⭐ Support

If you found this project useful, give it a ⭐ on GitHub.
