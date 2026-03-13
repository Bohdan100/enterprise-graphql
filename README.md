# Enterprise Application
The Enterprise is a Spring Boot application with a GraphQL schema and the GraphiQL graphical tool, providing a convenient API for managing employee and project data within the enterprise. It includes interconnected tables in a MySQL database that describe departments, projects, workers, and worker details. GraphQL's query and mutation capabilities empower clients to efficiently retrieve and modify data within these tables, requesting precisely the information they need.

## Features

*   **Comprehensive Data Management:** Manage departments, projects, workers, and worker details through a unified API.
*   **GraphQL Schema for Flexible Data Retrieval:**  Leverage a GraphQL schema to retrieve precisely the data you need, in any combination, minimizing over-fetching and maximizing efficiency.
*   **Interactive Querying with GraphiQL:**  Utilize the GraphiQL graphical tool for convenient and intuitive interaction with the GraphQL API, simplifying query creation and testing.
*   **Modern Development with Kotlin:** Leverage Kotlin's concise syntax, null safety, and data classes to enhance code readability, reduce boilerplate, and improve maintainability.
*   **Containerized Deployment with Docker:** Seamlessly build and run the entire ecosystem—including the Spring Boot application and MySQL database—using Docker and Docker Compose for consistent, "one-command" environment setup.
* **Department Management:** Add, update, and delete departments, including their names and duties.
*   **Project Management:** Add, update, and delete projects, specifying their names, descriptions, importance, and associated departments.
*   **Worker Management:** Add, update, and delete workers, including their first names, last names, positions, addresses, and sex, and assign them to departments.
*   **Interconnected Data Relationships:** Maintain and query the relationships between departments, projects, and workers through interconnected tables.
*   **MySQL Database Persistence:** Store and retrieve data reliably using a MySQL database.
*   **Flyway Database Migrations:** Manage database schema changes efficiently with Flyway.
*   **Simplified Build Process with Gradle:** Streamline the build and deployment process using Gradle.

## Requirements
The following configurations are required to launch the project:
- **Java Platform (JDK)**: 25
- **Gradle**: 9.2.0
- **Spring Boot**: 4.0.2
- **Docker**: 29.1.3
- **Kotlin**: 2.3.0

## Getting Started

1. Build and Run the Application Using Docker in Terminal:
   ```bash
   docker-compose up -d --build app

2. Interact with the GraphQL API using:
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