package corp.enterprise.util

import org.springframework.stereotype.Component
import org.springframework.context.annotation.Scope
import corp.enterprise.input.WorkerCreateInput

@Component
@Scope("prototype")
class WorkerValidator {
    private val errors = mutableListOf<String>()

    fun validate(input: WorkerCreateInput): WorkerValidator {
        if (input.position.contains("Lead") && input.firstName.length < 2) {
            errors.add("Lead position requires a valid full first name.")
        }

        if (input.position.contains("Junior", ignoreCase = true) && input.position.length > 50) {
            errors.add("Junior position description is too long.")
        }

        return this
    }

    fun isValid(): Boolean = errors.isEmpty()

    fun getErrors(): String = errors.joinToString(", ")
}