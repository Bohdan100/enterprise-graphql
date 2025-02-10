package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import corp.enterprise.repository.ProjectRepository
import corp.enterprise.repository.DepartmentRepository
import corp.enterprise.model.Project
import corp.enterprise.input.ProjectCreateInput
import corp.enterprise.input.ProjectUpdateInput

@Service
@Transactional
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val departmentRepository: DepartmentRepository
) {
    fun getAllProjects(): List<Project> = projectRepository.findAll()

    fun getProjectById(id: Long): Project =
        projectRepository.findById(id).orElseThrow { RuntimeException("Project not found") }

    fun searchByPartialName(partialName: String): List<Project> =
        projectRepository.findByNameContainingIgnoreCase(partialName.trim())

    fun searchByPartialDescription(partialDescription: String): List<Project> =
        projectRepository.findByDescriptionContainingIgnoreCase(partialDescription.trim())

    fun searchByImportance(importance: Int): List<Project> =
        projectRepository.findByImportance(importance)

    fun createProject(input: ProjectCreateInput): Project {
        val department = departmentRepository.findById(input.departmentId.toLong())
            .orElseThrow { RuntimeException("Department not found") }

        val project = Project(
            name = input.name,
            description = input.description,
            importance = input.importance,
            department = department
        )
        return projectRepository.save(project)
    }

    fun updateProject(input: ProjectUpdateInput): Project {
        val project = projectRepository.findById(input.id.toLong())
            .orElseThrow { RuntimeException("Project not found") }

        input.name?.let { project.name = it }
        input.description?.let { project.description = it }
        input.importance?.let { project.importance = it }
        input.departmentId?.let {
            val department = departmentRepository.findById(it.toLong())
                .orElseThrow { RuntimeException("Department not found") }
            project.department = department
        }

        return projectRepository.save(project)
    }

    fun deleteProject(id: Long) {
        if (!projectRepository.existsById(id)) throw RuntimeException("Project not found")
        projectRepository.deleteById(id)
    }
}