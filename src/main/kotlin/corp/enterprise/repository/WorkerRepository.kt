package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import corp.enterprise.model.Worker
import corp.enterprise.model.Sex

@Repository
interface WorkerRepository: JpaRepository<Worker, Long> {
    fun findByFirstNameContainingIgnoreCase(firstName: String): List<Worker>
    fun findByLastNameContainingIgnoreCase(lastName: String): List<Worker>
    fun findByPositionContainingIgnoreCase(position: String): List<Worker>
    fun findByDepartments_IdIn(departmentIds: List<Long>): List<Worker>
    fun findByDetails_Sex(sex: Sex): List<Worker>

    @Query("SELECT COUNT(w) FROM Worker w")
    fun countAllWorkers(): Long

    @Query("SELECT COUNT(w) FROM Worker w JOIN w.departments d WHERE d.id = :deptId")
    fun countWorkersByDeptId(deptId: Long): Long
}