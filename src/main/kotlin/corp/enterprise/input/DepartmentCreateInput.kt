package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class DepartmentCreateInput(
    @field:NotBlank(message = "Name is required!")
    @field:Size(min = 2, message = "Name must have at least two characters.")
    val name: String,

    @field:NotBlank(message = "Duties is required!")
    @field:Size(min = 2, message = "Duties must have at least two characters.")
    val duties: String
)

