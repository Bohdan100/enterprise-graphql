package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import corp.enterprise.model.Department

@Repository
interface DepartmentRepository: JpaRepository<Department, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Department>
    fun findByDutiesContainingIgnoreCase(duties: String): List<Department>
}
