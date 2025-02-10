package corp.enterprise.input

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class WorkerCreateInput(
    @field:NotBlank(message = "First Name is required!")
    val firstName: String,

    @field:NotBlank(message = "Last Name is required!")
    val lastName: String,

    @field:NotBlank(message = "Position is required!")
    val position: String,

    @field:NotBlank(message = "Address is required!")
    val address: String,

    @field:NotBlank(message = "Sex is required!")
    val sex: String,

    @field:NotEmpty(message = "Department IDs are required!")
    val departmentIds: List<Long>
)