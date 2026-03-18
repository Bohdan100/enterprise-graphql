package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import jakarta.validation.Valid

import corp.enterprise.service.DepartmentService
import corp.enterprise.model.Department
import corp.enterprise.input.DepartmentCreateInput
import corp.enterprise.input.DepartmentUpdateInput

@Controller
class DepartmentController(
    private val departmentService: DepartmentService
) {

    @QueryMapping
    fun departments(): List<Department> = departmentService.getAllDepartments()

    @QueryMapping
    fun department(
        @Argument id: Long?,
        @Argument partialName: String?,
        @Argument partialDuties: String?
    ): List<Department> = when {
        id != null -> listOf(departmentService.getDepartmentById(id))
        partialName != null -> departmentService.searchByPartialName(partialName.trim())
        partialDuties != null -> departmentService.searchByPartialDuties(partialDuties.trim())
        else -> throw IllegalArgumentException("At least one search parameter is required.")
    }

    @MutationMapping
    fun addDepartment(@Argument("department") @Valid departmentInput: DepartmentCreateInput): Department =
        departmentService.createDepartment(departmentInput)

    @MutationMapping
    fun updateDepartment(@Argument("department") @Valid departmentInput: DepartmentUpdateInput): Department =
        departmentService.updateDepartment(departmentInput)

    @MutationMapping
    fun deleteDepartment(@Argument id: Long): String {
        departmentService.deleteDepartment(id)
        return "Department deleted successfully!"
    }
}
