package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import corp.enterprise.model.Project

@Repository
interface ProjectRepository: JpaRepository<Project, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Project>
    fun findByDescriptionContainingIgnoreCase(description: String): List<Project>
    fun findByImportance(importance: Int): List<Project>

    @Query("SELECT COUNT(p) FROM Project p")
    fun countAllProjects(): Long

    @Query("SELECT AVG(p.importance) FROM Project p")
    fun getAverageImportance(): Double

     @Query("SELECT COUNT(p) FROM Project p WHERE p.department.id = :deptId")
     fun countProjectsByDepartmentId(deptId: Long): Long

    @Query("SELECT AVG(p.importance) FROM Project p WHERE p.department.id = :deptId")
    fun getAverageImportanceByDeptId(deptId: Long): Double?
}