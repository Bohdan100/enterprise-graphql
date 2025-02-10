package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import corp.enterprise.repository.DepartmentRepository
import corp.enterprise.model.Department
import corp.enterprise.input.DepartmentCreateInput
import corp.enterprise.input.DepartmentUpdateInput

@Service
@Transactional
class DepartmentService(private val departmentRepository: DepartmentRepository) {

    fun getAllDepartments(): List<Department> = departmentRepository.findAll()

    fun getDepartmentById(id: Long): Department =
        departmentRepository.findById(id).orElseThrow { RuntimeException("Department not found") }

    fun searchByPartialName(partialName: String): List<Department> =
        departmentRepository.findByNameContainingIgnoreCase(partialName)

    fun searchByPartialDuties(partialDuties: String): List<Department> =
        departmentRepository.findByDutiesContainingIgnoreCase(partialDuties)

    fun createDepartment(input: DepartmentCreateInput): Department {
        val department = Department(
            name = input.name,
            duties = input.duties
        )
        return departmentRepository.save(department)
    }

    fun updateDepartment(input: DepartmentUpdateInput): Department {
        val department = departmentRepository.findById(input.id.toLong())
            .orElseThrow { RuntimeException("Department not found") }

        input.name?.let { department.name = it }
        input.duties?.let { department.duties = it }

        return departmentRepository.save(department)
    }

    fun deleteDepartment(id: Long) {
        if (!departmentRepository.existsById(id)) throw RuntimeException ("Department not found")
        departmentRepository.deleteById(id)
    }
}
