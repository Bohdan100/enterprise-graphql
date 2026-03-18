package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.SchemaMapping
import corp.enterprise.repository.WorkerRepository
import corp.enterprise.repository.ProjectRepository
import corp.enterprise.model.Department

@Controller
class DepartmentAggregationController(
    private val workerRepository: WorkerRepository,
    private val projectRepository: ProjectRepository
) {
    @SchemaMapping(typeName = "Department", field = "workerCount")
    fun workerCount(department: Department): Long {
        return workerRepository.countWorkersByDeptId(department.id!!)
    }

    @SchemaMapping(typeName = "Department", field = "projectCount")
    fun projectCount(department: Department): Long {
        return projectRepository.countProjectsByDepartmentId(department.id!!)
    }

    @SchemaMapping(typeName = "Department", field = "averageImportance")
    fun averageImportance(department: Department): Double {
        return projectRepository.getAverageImportanceByDeptId(department.id!!) ?: 0.0
    }
}