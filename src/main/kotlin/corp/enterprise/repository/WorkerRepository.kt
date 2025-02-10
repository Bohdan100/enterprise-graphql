package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import corp.enterprise.model.Worker

@Repository
interface WorkerRepository: JpaRepository<Worker, Long> {
    fun findByFirstNameContainingIgnoreCase(firstName: String): List<Worker>
    fun findByLastNameContainingIgnoreCase(lastName: String): List<Worker>
    fun findByPositionContainingIgnoreCase(position: String): List<Worker>
    fun findByDepartments_IdIn(departmentIds: List<Long>): List<Worker>
}