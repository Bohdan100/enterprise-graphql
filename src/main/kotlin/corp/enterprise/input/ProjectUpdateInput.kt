package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Min

data class ProjectUpdateInput(
    @field:NotBlank(message = "Id is required!")
    val id: String,

    @field:Size(min = 2, message = "Name must have at least two characters.")
    val name: String? = null,

    @field:Size(min = 2, message = "Description must have at least two characters.")
    val description: String? = null,

    @field:Min(value = 1, message = "Importance must be a positive number greater than 0")
    val importance: Int? = null,

    @field:Size(min = 1, message = "DepartmentId must have at least one character.")
    val departmentId: String? = null
)