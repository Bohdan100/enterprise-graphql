package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Min
import org.jetbrains.annotations.NotNull

data class ProjectCreateInput(
    @field:NotBlank(message = "Name is required!")
    @field:Size(min = 2, message = "Name must have at least two characters.")
    val name: String,

    @field:NotBlank(message = "Description is required!")
    @field:Size(min = 2, message = "Description must have at least two characters.")
    val description: String,

    @field:NotNull
    @field:Min(value = 1, message = "Importance must be a positive number greater than 0")
    val importance: Int,

    @field:NotBlank(message = "DepartmentId is required!")
    val departmentId: String
)