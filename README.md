# 🧪 API Automation Testing – REST Assured Project

This project demonstrates automated API testing using **REST Assured** with **JUnit 5** and **Allure Reports**. It covers full **CRUD operations** (Create, Read, Update, Delete) against the `https://jsonplaceholder.typicode.com` mock API.

---

## 🚀 Technologies Used

- **Java 21**
- **REST Assured** – API testing framework
- **JUnit 5** – Test framework
- **Allure** – Report generation
- **Maven** – Build and dependency management
- **Git & GitHub** – Version control

---

## 🧩 Project Structure

```
APIAutomation/
├── src
│   └── test
│       └── java
│           └── com.example
│               ├── base         # Base test config
│               ├── endpoints    # Encapsulated CRUD logic
│               └── tests        # All test cases
│       └── resources
│           └── schemas          # JSON schema files (optional)
├── pom.xml                      # Maven dependencies
└── README.md
```

---

## ✅ Test Coverage

### ➕ Positive Test Cases
- `GET /posts/{id}` – Retrieve a post by ID
- `POST /posts` – Create a new post
- `PUT /posts/{id}` – Update a post
- `DELETE /posts/{id}` – Delete a post

### ➖ Negative Test Cases
- Retrieve a post that doesn't exist
- Update a non-existing post
- Delete a non-existing post

> ⚠️ Note: JSONPlaceholder is a fake API. Even invalid inputs return successful responses (e.g., 200/201), which would differ in real-world APIs.

---

## 📄 How to Run Tests

Make sure you have **Java 21** and **Maven** installed.

```bash
# Run tests
mvn clean test
```

---

## 📊 How to Generate Allure Report

```bash
# Generate & serve Allure report locally
allure serve target/allure-results
```

> If you don’t have Allure installed: [Install Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline)

---

## 🔗 Useful Links

- [JSONPlaceholder API Docs](https://jsonplaceholder.typicode.com/)
- [REST Assured GitHub](https://github.com/rest-assured/rest-assured)
- [Allure Documentation](https://docs.qameta.io/allure/)

---

## 👤 Author

**Nana Quaci**
> Week 7 Lab Activity – Advanced Testing  
> Graduate Trainee Program

---

## 🏁 License

This project is for educational purposes only.
