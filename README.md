# SecureAuthSystem

**Java Login & Registration System with Validation & Unit Tests**

---

## Project Description
SecureAuthSystem is a Java-based login and registration system designed to demonstrate secure authentication practices. It validates usernames, strong passwords, and South African cellphone numbers. The project includes automated unit tests using JUnit 4.13.2 to ensure input validation and authentication logic work correctly.

---

## Features
- **User Registration:** Validate and register users with first name, last name, username, password, and South African cellphone number.
- **Username Validation:** Must start with a letter and be 3-20 alphanumeric characters.
- **Password Complexity:** Requires minimum 8 characters, at least one uppercase, one lowercase, one digit, and one special character.
- **Cellphone Validation:** Accepts valid South African cellphone numbers (10 digits starting with 06, 07, or 08).
- **Login:** Authenticate registered users with username and password.
- **Unit Testing:** Includes JUnit tests covering validation and registration/login workflows.
- **In-memory & fallback storage:** Uses static fields to simulate persistent storage for demonstration purposes.

---

## Technologies Used
- Java (JDK 17+ recommended)
- NetBeans IDE
- JUnit 4.13.2
- Git & GitHub

---

## How to Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/SecureAuthSystem.git

Open the project in NetBeans.

Run Main.java to start the command-line demo:

Type r to register a new user.

Type l to login with an existing user.

Type q to quit the program.

Run JUnit tests:

Right-click the project → Test to execute all unit tests.




Run JUnit tests:

Right-click the project → Test to execute all unit tests.
