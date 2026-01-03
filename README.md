# Hospital-Appointment-Booking-System
A **microservices-based healthcare application** built using **Spring Boot & Spring Cloud** to manage hospital operations such as **doctor management, patient management, appointment booking, and authentication**.

---

## 📌 Project Structure
```text
Hospital-Appointment-Booking-System
│
├── api-gateway
├── appointment-UI
├── eureka-server
├── login-logout-service
├── micro-appointment
├── micro-doctor
├── micro-patient
└── README.md
```
---

## 🧩 Microservices Overview

### 1️⃣ API Gateway (`api-gateway`)
- Acts as the **single entry point** for all client requests.
- Routes requests to appropriate microservices.
- Handles cross-cutting concerns:
  - Authentication
  - Logging
  - Rate limiting
- Built using **Spring Cloud Gateway**.

---

### 2️⃣ Appointment UI (`appointment-UI`)
- Frontend-facing microservice (Presentation Layer).
- Serves **HTML / Thymeleaf templates**.
- Handles user interactions:
  - Book appointments
  - View appointments
  - Cancel appointments
- Communicates with backend microservices using **OpenFeign / REST APIs**.

---

### 3️⃣ Eureka Server (`eureka-server`)
- Service registry for all microservices.
- Enables **dynamic service discovery**.
- Eliminates the need to hardcode service URLs.
- Implemented using **Netflix Eureka**.

---

### 4️⃣ Login & Logout Service (`login-logout-service`)
- Handles **user authentication and authorization**.
- Manages:
  - Login
  - Logout
  - User registration
- Uses **JWT-based authentication** (token-based security).

---

### 5️⃣ Appointment Service (`micro-appointment`)
- Core business logic for appointment management.
- Features:
  - Book appointments
  - Update appointments
  - Cancel appointments
  - View appointment history
- Connected to an **Appointment Database** (MySQL / PostgreSQL).

---

### 6️⃣ Doctor Service (`micro-doctor`)
- Manages doctor-related data:
  - Name
  - Specialization
  - Qualification
  - Availability
- Used by patients while searching and selecting doctors.
- Connected to a **Doctor Database**.

---

### 7️⃣ Patient Service (`micro-patient`)
- Manages patient information:
  - Profile details
  - Contact information
  - Medical history
- Provides patient data required during appointment booking.
- Connected to a **Patient Database**.

---

## ⚙️ Technologies Used

- Java
- Spring Boot
- Spring Cloud (Eureka, Gateway, OpenFeign)
- Spring Data JPA
- REST APIs
- JWT Authentication
- MySQL / PostgreSQL
- Thymeleaf
- Maven

---

## 🚀 How to Run the Project

1. Start **Eureka Server**
2. Start **API Gateway**
3. Start backend microservices:
   - login-logout-service
   - micro-doctor
   - micro-patient
   - micro-appointment
4. Start **appointment-UI**
5. Access the application via **API Gateway**

---

## 🎯 Key Highlights

- Microservices architecture
- Service discovery with Eureka
- Centralized routing via API Gateway
- Clean separation of concerns
- Scalable & production-ready design

---
