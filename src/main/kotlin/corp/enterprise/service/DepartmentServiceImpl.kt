package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import corp.enterprise.repository.DepartmentRepository
import corp.enterprise.repository.WorkerRepository
import corp.enterprise.input.DepartmentCreateInput
import corp.enterprise.input.DepartmentUpdateInput
import corp.enterprise.model.Department

@Service
@Transactional
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository,
    private val workerRepository: WorkerRepository
) : DepartmentService {

    override fun getAllDepartments(): List<Department> = departmentRepository.findAll()

    override fun getDepartmentById(id: Long): Department =
        departmentRepository.findById(id)
            .orElseThrow { RuntimeException("Department not found") }

    override fun searchByPartialName(partialName: String): List<Department> =
        departmentRepository.findByNameContainingIgnoreCase(partialName)

    override fun searchByPartialDuties(partialDuties: String): List<Department> =
        departmentRepository.findByDutiesContainingIgnoreCase(partialDuties)

    override fun createDepartment(input: DepartmentCreateInput): Department {
        val department = Department(
            name = input.name,
            duties = input.duties
        )
        return departmentRepository.save(department)
    }

    override fun updateDepartment(input: DepartmentUpdateInput): Department {
        val department = departmentRepository.findById(input.id.toLong())
            .orElseThrow { RuntimeException("Department not found") }

        input.name?.let { department.name = it }
        input.duties?.let { department.duties = it }

        return departmentRepository.save(department)
    }

    override fun deleteDepartment(id: Long) {
        if (!departmentRepository.existsById(id)) throw RuntimeException("Department not found")
        departmentRepository.deleteById(id)
    }

    fun getWorkerCount(departmentId: Long): Int =
        workerRepository.findByDepartments_IdIn(listOf(departmentId)).size
}