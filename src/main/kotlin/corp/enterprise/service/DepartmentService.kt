package corp.enterprise.service

import corp.enterprise.model.Department
import corp.enterprise.input.DepartmentCreateInput
import corp.enterprise.input.DepartmentUpdateInput

interface DepartmentService {
    fun getAllDepartments(): List<Department>
    fun getDepartmentById(id: Long): Department
    fun searchByPartialName(partialName: String): List<Department>
    fun searchByPartialDuties(partialDuties: String): List<Department>
    fun createDepartment(input: DepartmentCreateInput): Department
    fun updateDepartment(input: DepartmentUpdateInput): Department
    fun deleteDepartment(id: Long)
}