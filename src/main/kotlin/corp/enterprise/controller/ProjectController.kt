package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import jakarta.validation.Valid

import corp.enterprise.service.ProjectService
import corp.enterprise.model.Project
import corp.enterprise.input.ProjectCreateInput
import corp.enterprise.input.ProjectUpdateInput

@Controller
class ProjectController(private val projectService: ProjectService) {
    @QueryMapping
    fun projects(): List<Project> = projectService.getAllProjects()

    @QueryMapping
    fun project(
        @Argument id: Long?,
        @Argument partialName: String?,
        @Argument partialDescription: String?,
        @Argument importance: Int?
    ): List<Project> = when {
        id != null -> listOf(projectService.getProjectById(id))
        partialName != null -> projectService.searchByPartialName(partialName.trim())
        partialDescription != null -> projectService.searchByPartialDescription(partialDescription.trim())
        importance != null -> projectService.searchByImportance(importance)
        else -> throw IllegalArgumentException("At least one search parameter is required.")
    }

    @MutationMapping
    fun addProject(@Argument("project") @Valid projectCreateInput: ProjectCreateInput): Project =
        projectService.createProject(projectCreateInput)

    @MutationMapping
    fun updateProject(@Argument("project") @Valid projectUpdateInput: ProjectUpdateInput): Project =
        projectService.updateProject(projectUpdateInput)


    @MutationMapping
    fun deleteProject(@Argument id: Long): String {
        projectService.deleteProject(id)
        return "Project deleted successfully!"
    }
}