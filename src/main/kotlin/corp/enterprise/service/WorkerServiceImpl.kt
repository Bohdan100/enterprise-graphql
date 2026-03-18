package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Lookup

import corp.enterprise.repository.DepartmentRepository
import corp.enterprise.repository.WorkerRepository
import corp.enterprise.repository.WorkerDetailsRepository
import corp.enterprise.input.WorkerCreateInput
import corp.enterprise.input.WorkerUpdateInput
import corp.enterprise.model.Sex
import corp.enterprise.model.Worker
import corp.enterprise.model.WorkerDetails
import corp.enterprise.util.ExecutionTracker
import corp.enterprise.util.WorkerValidator

@Service
@Transactional
open class WorkerServiceImpl(
    private val workerRepository: WorkerRepository,
    private val workerDetailsRepository: WorkerDetailsRepository,
    private val departmentRepository: DepartmentRepository
) : WorkerService {

    @Lookup
    open fun getTracker(): ExecutionTracker = throw UnsupportedOperationException()

    @Lookup
    open fun getValidator(): WorkerValidator = throw UnsupportedOperationException()

    @Transactional(readOnly = true)
    override fun getAllWorkers(): List<Worker> {
        val tracker = getTracker()
        tracker.logStep("Fetching all workers from DB")

        val result = workerRepository.findAll()

        tracker.finish("getAllWorkers")
        return result
    }

    @Transactional(readOnly = true)
    override fun getWorkerById(id: Long): Worker =
        workerRepository.findById(id)
            .orElseThrow { RuntimeException("Worker not found") }

    @Transactional(readOnly = true)
    override fun searchByPosition(position: String): List<Worker> {
        val tracker = getTracker()
        tracker.logStep("Searching for position: $position")

        val result = workerRepository.findByPositionContainingIgnoreCase(position)

        tracker.finish("searchByPosition")
        return result
    }

    @Transactional(readOnly = true)
    override fun searchByDepartments(departmentIds: List<Long>): List<Worker> =
        workerRepository.findByDepartments_IdIn(departmentIds)

    @Transactional(readOnly = true)
    override fun searchByPartialFirstName(partialFirstName: String): List<Worker> =
        workerRepository.findByFirstNameContainingIgnoreCase(partialFirstName)

    @Transactional(readOnly = true)
    override fun searchByPartialLastName(partialLastName: String): List<Worker> =
        workerRepository.findByLastNameContainingIgnoreCase(partialLastName)

    @Transactional(readOnly = true)
    override fun searchBySex(sex: String): List<Worker> {
        val sexEnum = try {
            Sex.fromString(sex)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Invalid sex value: $sex")
        }

        return workerRepository.findByDetails_Sex(sexEnum)
    }

    override fun createWorker(input: WorkerCreateInput): Worker {
        val tracker = getTracker()
        tracker.logStep("Starting worker creation process")

        val validator = getValidator()
        if (!validator.validate(input).isValid()) {
            tracker.logStep("Validation failed for ${input.firstName}")
            throw IllegalArgumentException("Validation failed: ${validator.getErrors()}")
        }

        val departments = departmentRepository.findAllById(input.departmentIds.map { it })

        val worker = Worker(
            firstName = input.firstName,
            lastName = input.lastName,
            position = input.position,
            departments = departments
        )
        val savedWorker = workerRepository.save(worker)

        val details = WorkerDetails(
            worker = savedWorker,
            workerId = savedWorker.id,
            address = input.address,
            sex = Sex.fromString(input.sex)
        )
        workerDetailsRepository.save(details)

        tracker.finish("createWorker")
        return workerRepository.save(savedWorker)
    }

    override fun updateWorker(input: WorkerUpdateInput): Worker {
        val worker = workerRepository.findById(input.id.toLong())
            .orElseThrow { RuntimeException("Worker not found") }

        input.firstName?.let { worker.firstName = it }
        input.lastName?.let { worker.lastName = it }
        input.position?.let { worker.position = it }

        val details = workerDetailsRepository.findById(worker.id!!)
            .orElseGet { WorkerDetails(workerId = worker.id, worker = worker) }

        input.address?.let { details.address = it }
        input.sex?.let { details.sex = Sex.fromString(it) }

        workerDetailsRepository.save(details)

        input.departmentIds?.let { ids ->
            val newDepartments = departmentRepository.findAllById(ids.map { it })
            worker.departments = (worker.departments + newDepartments).distinct()
        }

        return workerRepository.save(worker)
    }

    override fun deleteWorker(id: Long) {
        if (!workerRepository.existsById(id)) throw RuntimeException("Worker not found")
        workerRepository.deleteById(id)
    }
}