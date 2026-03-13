package corp.enterprise.service

import corp.enterprise.model.Project
import corp.enterprise.input.ProjectCreateInput
import corp.enterprise.input.ProjectUpdateInput

interface ProjectService {
    fun getAllProjects(): List<Project>
    fun getProjectById(id: Long): Project
    fun searchByPartialName(partialName: String): List<Project>
    fun searchByPartialDescription(partialDescription: String): List<Project>
    fun searchByImportance(importance: Int): List<Project>
    fun createProject(input: ProjectCreateInput): Project
    fun updateProject(input: ProjectUpdateInput): Project
    fun deleteProject(id: Long)
}