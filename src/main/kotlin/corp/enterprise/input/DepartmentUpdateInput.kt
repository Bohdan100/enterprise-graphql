package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class DepartmentUpdateInput (
    @field:NotBlank(message = "Id is required!")
    val id: String,

    @field:Size(min = 2, message = "Name must have at least two characters.")
    val name: String? = null,

    @field:Size(min = 2, message = "Duties must have at least two characters.")
    val duties: String? = null
)