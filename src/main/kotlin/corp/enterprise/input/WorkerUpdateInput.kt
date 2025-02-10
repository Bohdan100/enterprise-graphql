package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class WorkerUpdateInput(
    @field:NotBlank(message = "Id is required!")
    val id: String,

    @field:Size(min = 2, message = "First name must have at least two characters.")
    val firstName: String? = null,

    @field:Size(min = 2, message = "Last name must have at least two characters.")
    val lastName: String? = null,

    @field:Size(min = 2, message = "Position must have at least two characters.")
    val position: String? = null,

    @field:Size(min = 2, message = "Address must have at least two characters.")
    val address: String? = null,

    val sex: String? = null,
    val departmentIds: List<Long>? = null
)