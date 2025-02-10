package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import corp.enterprise.model.Project

@Repository
interface ProjectRepository: JpaRepository<Project, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Project>
    fun findByDescriptionContainingIgnoreCase(description: String): List<Project>
    fun findByImportance(importance: Int): List<Project>
}