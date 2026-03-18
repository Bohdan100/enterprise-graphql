# Enterprise Application
The Enterprise is a Spring Boot application with a GraphQL schema and the GraphiQL graphical tool, providing a convenient API for managing employee and project data within the enterprise. It includes interconnected tables in a MySQL database that describe departments, projects, workers, and worker details. GraphQL's query and mutation capabilities empower clients to efficiently retrieve and modify data within these tables, requesting precisely the information they need. The entire ecosystem is fully containerized using Docker, ensuring seamless deployment and environment consistency. With Docker Compose, the application and its MySQL database can be orchestrated and launched with a single command, eliminating "it works on my machine" issues and streamlining the development workflow.
## Features
*   **Comprehensive Data Management:** Manage departments, projects, workers, and worker details through a unified API.
*   **Flexible Data Retrieval with GraphQL & GraphiQL:**  Leverage a robust GraphQL schema to retrieve precise data combinations while minimizing over-fetching, and utilize the integrated GraphiQL tool for intuitive, real-time query testing and API interaction.
*   **High-Performance Aggregations:** Implemented complex aggregate functions (COUNT, AVG) at the database level to minimize application memory overhead.
*   **Database Indexing:** Optimized search performance using indexes in Flyway migrations and JPA entities (search by names, positions, and departments).
*   **Advanced Bean Lifecycle Management:** Implemented Method Injection using @Lookup to handle stateful prototype-scoped components (ExecutionTracker, WorkerValidator) within singleton services.
*   **Advanced Transaction Management (AOP):**  Leveraged Spring AOP through tuned @Transactional configurations (used readOnly = true and implemented Propagation.REQUIRES_NEW for audit logging).
*   **Modern Development with Kotlin:** Leverage Kotlin's concise syntax, null safety, and data classes to enhance code readability, reduce boilerplate, and improve maintainability.
*   **Containerized Deployment with Docker:** Seamlessly build and run the entire ecosystem—including the Spring Boot application and MySQL database—using Docker and Docker Compose for consistent, "one-command" environment setup.
*   **Full-Cycle Entity Management:** Perform complete CRUD operations (Create, Read, Update, Delete) for departments, projects, and workers, including the management of detailed attributes such as duties, importance levels, personal details, and cross-department assignments.
*   **MySQL Database Persistence:** Maintain and reliably query complex relationships between departments, projects, and workers using a MySQL database with interconnected tables for persistent storage.
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
📊 Enterprise Analytics & Intelligence  
**1. Global System Statistics (retrieve high-level enterprise metrics):**
```graphql
query GetGlobalStats {
  systemStats {
    totalWorkers              # Total headcount across the enterprise
    totalProjects             # Total number of active projects
    averageProjectImportance  # Global average priority score
  }
}
```
**2. Department Intelligence (Per-Department Analytics):**
```graphql
query GetDepartmentAnalytics {
  departments {
    name
    workerCount       # Real-time worker count per department
    projectCount      # Number of projects assigned to this department
    averageImportance # Average importance of projects within this department
  }
}
```

🛠 EntityManagement  
**3. Create New Department:**
```graphql
mutation CreateDepartment {
  addDepartment(department: { 
    name: "Marketing", 
    duties: "Promotion and strategic advertising" 
  }) {
    id
    name
    duties
  }
}
```
**4. Update Existing Project:**
```graphql
mutation UpdateProjectDetails {
  updateProject(project: { 
    id: "3", 
    name: "Enterprise Link V2", 
    importance: 9 
  }) {
    id
    name
    importance
    department {
      name
    }
  }
}
```
**5. Data Deletion (Project/Department):**
```graphql
mutation DeleteRecord {
  deleteProject(id: "4") # Replace "4" with the actual ID
}
```

🔍 Workforce Search & Details  
**6. Advanced Worker Search (Filter by Name, Position, or Sex):**
```graphql
query SearchWorkforce {
  # Example: Search by position or partial name
  worker(position: "Developer", partialFirstName: "Emily") {
    id
    firstName
    lastName
    position
    details {
      address
      sex
    }
    departments {
      name
      workerCount
    }
  }
}
```
**7. Retrieve All Personnel Records:**
```graphql
query GetAllWorkers {
  workers {
    id
    lastName
    position
    details {
      address
    }
    departments {
      name
    }
  }
}
```
**8. Get Department Deep-Dive by ID:**
```graphql
query GetDepartmentDetails {
  department(id: "1") {
    name
    duties
    projects {
      name
      importance
    }
    workers {
      lastName
      position
    }
  }
}
```

🛡 Monitoring & Error Handling (Audit & Validation)  
**9. System Audit Trail:**
```graphql
query GetAuditTrail {
  auditLogs {
    id
    operation    # e.g., CREATE_PROJECT_ATTEMPT, DELETE_PROJECT_FAILURE
    details      # Description or error stack trace
    timestamp    # Event occurrence time
  }
}
```
**10. Test Entity Not Found (NOT_FOUND, triggers a RuntimeException handled by the global error resolver):**
```graphql
query TestNotFound {
  department(id: "999") { # ID that does not exist
    name
  }
}
```
**11. Test Business Validation (BAD_REQUEST, triggers a validation error (e.g., violating @Min(1) on importance field)):**
```graphql
mutation TestValidation {
  addProject(project: {
    name: "Invalid Project",
    description: "Testing constraints",
    importance: 0,        # Triggers @Min(1) violation
    departmentId: "1"
  }) {
    id
    importance
  }
}
```