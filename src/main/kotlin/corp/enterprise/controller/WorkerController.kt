package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import jakarta.validation.Valid

import corp.enterprise.model.Worker
import corp.enterprise.input.WorkerCreateInput
import corp.enterprise.input.WorkerUpdateInput
import corp.enterprise.service.WorkerService

@Controller
class WorkerController(private val workerService: WorkerService) {

    @QueryMapping
    fun workers(): List<Worker> = workerService.getAllWorkers()

    @QueryMapping
    fun worker(
        @Argument id: Long?,
        @Argument partialFirstName: String?,
        @Argument partialLastName: String?,
        @Argument sex: String?,
        @Argument position: String?,
        @Argument departmentIds: List<Long>?
    ): List<Worker> = when {
        id != null -> listOf(workerService.getWorkerById(id))
        position != null -> workerService.searchByPosition(position.trim())
        partialFirstName != null -> workerService.searchByPartialFirstName(partialFirstName.trim())
        partialLastName != null -> workerService.searchByPartialLastName(partialLastName.trim())
        sex != null -> workerService.searchBySex(sex)
        !departmentIds.isNullOrEmpty() -> workerService.searchByDepartments(departmentIds)
        else -> throw IllegalArgumentException("At least one search parameter is required.")
    }

    @MutationMapping
    fun addWorker(@Argument("worker") @Valid workerCreateInput: WorkerCreateInput): Worker =
        workerService.createWorker(workerCreateInput)

    @MutationMapping
    fun updateWorker(@Argument("worker") @Valid workerUpdateInput: WorkerUpdateInput): Worker =
        workerService.updateWorker(workerUpdateInput)

    @MutationMapping
    fun deleteWorker(@Argument id: Long): String {
        workerService.deleteWorker(id)
        return "Worker deleted successfully!"
    }
}