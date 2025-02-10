# Enterprise Application
The Enterprise is a Spring Boot application with a GraphQL schema and the GraphiQL graphical tool, providing a convenient API for managing employee and project data within the enterprise. It includes interconnected tables in a MySQL database that describe departments, projects, workers, and worker details. GraphQL's query and mutation capabilities empower clients to efficiently retrieve and modify data within these tables, requesting precisely the information they need.

## Features

*   **Comprehensive Data Management:** Manage departments, projects, workers, and worker details through a unified API.
*   **GraphQL Schema for Flexible Data Retrieval:**  Leverage a GraphQL schema to retrieve precisely the data you need, in any combination, minimizing over-fetching and maximizing efficiency.
*   **Interactive Querying with GraphiQL:**  Utilize the GraphiQL graphical tool for convenient and intuitive interaction with the GraphQL API, simplifying query creation and testing.
*   **Department Management:** Add, update, and delete departments, including their names and duties.
*   **Project Management:** Add, update, and delete projects, specifying their names, descriptions, importance, and associated departments.
*   **Worker Management:** Add, update, and delete workers, including their first names, last names, positions, addresses, and sex, and assign them to departments.
*   **Interconnected Data Relationships:** Maintain and query the relationships between departments, projects, and workers through interconnected tables.
*   **MySQL Database Persistence:** Store and retrieve data reliably using a MySQL database.
*   **Flyway Database Migrations:** Manage database schema changes efficiently with Flyway.
*   **Simplified Build Process with Gradle:** Streamline the build and deployment process using Gradle.
*   **Code Simplification with Lombok:** Reduce boilerplate code and improve readability with Lombok.

## Requirements
The application is built using the following technologies:
- **Spring Boot**: 3.3.1
- **Kotlin**: 1.9.24
- **Spring Boot Starter GraphQL**: 3.3.5
- **Java Platform (JDK)**: 21
- **MySQL**: 8.0.40
- **Flyway**: 11.0.1
- **Lombok**: 1.18.36
- **Gradle**: 8.8
  
## Database Setup
Before running the application, follow these steps to set up the database:

1. **Create a MySQL 8.0.40 Database**  
   Set up a MySQL database to store the application’s data.

2. **Configure Database and User**  
   Perform the following steps in your MySQL instance to create a user and database for the application:

    1. Create a new user with a password:
       ```sql
       CREATE USER IF NOT EXISTS 'adminDir'@'%' IDENTIFIED BY 'secret1234';
       ```

    2. Create a new database:
       ```sql
       CREATE DATABASE IF NOT EXISTS enterprise;
       DROP DATABASE IF EXISTS enterprise;
       ```

    3. Assign ownership of the database to the new user:
       ```sql
       GRANT ALL PRIVILEGES ON enterprise.* TO 'adminDir'@'%';
       GRANT SUPER ON *.* TO 'adminDir'@'%';
       ```
       
3. **Connect to the Database**  
   To connect to the `enterprise` database as the `adminDir` user, use the following command in the terminal:
   ```bash
   mysql -u adminDir -p -D enterprise

## Getting Started

1. Build and Run the Application Using Gradle in Terminal:
   ```bash
   .\gradlew bootRun     (for Windows)
   ./gradlew bootRun     (for Linux)

2. Build and Run the Application Using a JAR File:
   ```bash
   .\gradlew bootJar     (for Windows)
   ./gradlew bootJar     (for Linux)
   
   java -jar enterprise-graphql.jar
    ```

3. Interact with the GraphQL API using:
    * GraphiQL: `http://localhost:8080/graphiql` (GUI interface in Browser)
    * Postman: `http://localhost:8080/graphql` (HTTPS requests)

## Queries and Mutation examples:

**Create Department:**
```graphql
mutation {
  addDepartment(department: { name: "Marketing", duties: "Promotion and advertising"}) {
    id
    name
    duties
    projects {
      id
      name
    }
    workers {
      id
      firstName
      lastName
    }
  }
}
```
**Update Project:**
```graphql
mutation {
  updateProject(project: { id: "3", name: "Updated Project Name", description: "Updated Description", importance: 4 }) { # Replace with actual values, id is required
    id
    name
    description
    importance
    department {
      id
      name
    }
  }
}
```
**Delete Project:**
```graphql
mutation {
  deleteProject(id: "9") # Replace "9" with the actual ID
}
```
**Get All Workers:**
```graphql
query {
  workers {
    id
    firstName
    lastName
    position
    details {
      address
      sex
    }
    departments {
      id
      name
    }
  }
}
```
**Get Department by ID:**
```graphql
query {
  department(id: "1") { # Replace "1" with the actual ID
    id
    name
    duties
    projects {
      id
      name
    }
    workers {
      id
      firstName
      lastName
    }
  }
}
```
**Get Worker by FirstName:**
```graphql
query {
  worker(partialFirstName: "Emily") {
    id
    firstName
    lastName
    position
    details {
      address
      sex
    }
    departments {
      id
      name
    }
  }
}
```
**Get Worker by Position:**
```graphql
query {
  worker(position: "Developer") {
    id
    firstName
    lastName
    position
    details {
      address
      sex
    }
    departments {
      id
      name
    }
  }
}
```