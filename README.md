# **MVC Code Generation**

## **Overview of the Project**

This project demonstrates how to dynamically generate the Model-View-Controller (MVC) structure in a Spring Boot application using Java Reflection and database schema (DDL). The goal is to automate the creation of entity classes, repositories, services, and controllers, reducing repetitive coding and improving efficiency in projects with multiple database entities.

---

## **Pre-requirements**

Before running the project, ensure you have the following installed and configured:

1. **Java Development Kit (JDK)**
    - Version: 17 or above
    - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Gradle**
    - Ensure Gradle is installed for building the project.
    - [Download Gradle](https://gradle.org/install/)

3. **MySQL Database**
    - Install MySQL and ensure the service is running.
    - Create a database named `mvc`.
    - Update the `spring.datasource.username` and `spring.datasource.password` in the `application.properties` file to match your MySQL credentials.

4. **IDE/Text Editor**
    - Use any preferred IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code.

5. **Git**
    - Ensure Git is installed for cloning the repository.
    - [Download Git](https://git-scm.com/)

---

## **Steps to Run the Project Locally**

Follow these steps to set up and run the project on your local machine:

1. **Clone the Repository**  
   Open a terminal and run:
   ```bash
   git clone https://github.com/akshay-7span/mvc-code-generation.git
   cd mvc-code-generation
   
2. **Switch to the Appropriate Branch**

    Check out the branch containing the code for this implementation:

    ```bash
    git checkout mcg-1
   
3. **Configure the Application**
   Open the `src/main/resources/application.properties` file and verify or update the following configurations:
    ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/mvc
    spring.datasource.username=<your_username>
    spring.datasource.password=<your_password>
   
4. **Build the Project**

    Use Gradle to build the project:
    ```bash
    gradle build
5. **Run the Code Generation Tool
   To generate MVC components:**

    Start the Spring Boot application:
    ```bash
    gradle run --main-class com.mvc_code_generation.codegen.CodeGenerator

---

## **Using the Code Generation Tool** 
### **DDL Input**

1. Launch the Code Generation Tool
2. Enter your SQL DDL in the `String[] ddls` variable in the `CodeGenerator class`.
3. Example DDL Input:
    ```bash
    CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isActive BOOLEAN DEFAULT FALSE
   );

4. Save the file.
5. Run the application to generate code.

### **Generated Components**
For each table, the tool will generate:

- Entity class with JPA annotations
- Repository interface
- Service class
- RESTful Controller