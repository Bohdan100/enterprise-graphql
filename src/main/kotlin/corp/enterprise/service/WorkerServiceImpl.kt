package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import corp.enterprise.repository.DepartmentRepository
import corp.enterprise.repository.WorkerRepository
import corp.enterprise.repository.WorkerDetailsRepository
import corp.enterprise.input.WorkerCreateInput
import corp.enterprise.input.WorkerUpdateInput
import corp.enterprise.model.Sex
import corp.enterprise.model.Worker
import corp.enterprise.model.WorkerDetails

@Service
@Transactional
class WorkerServiceImpl(
    private val workerRepository: WorkerRepository,
    private val workerDetailsRepository: WorkerDetailsRepository,
    private val departmentRepository: DepartmentRepository
) : WorkerService {

    override fun getAllWorkers(): List<Worker> = workerRepository.findAll()

    override fun getWorkerById(id: Long): Worker =
        workerRepository.findById(id)
            .orElseThrow { RuntimeException("Worker not found") }

    override fun searchByPosition(position: String): List<Worker> =
        workerRepository.findByPositionContainingIgnoreCase(position)

    override fun searchByDepartments(departmentIds: List<Long>): List<Worker> =
        workerRepository.findByDepartments_IdIn(departmentIds)

    override fun searchByPartialFirstName(partialFirstName: String): List<Worker> =
        workerRepository.findByFirstNameContainingIgnoreCase(partialFirstName)

    override fun searchByPartialLastName(partialLastName: String): List<Worker> =
        workerRepository.findByLastNameContainingIgnoreCase(partialLastName)

    override fun searchBySex(sex: String): List<Worker> {
        if (sex == null) return workerRepository.findAll()

        val sexEnum = try {
            Sex.fromString(sex)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Invalid sex value: $sex")
        }

        return workerRepository.findAll().filter {
            it.details?.sex == sexEnum
        }
    }

    override fun createWorker(input: WorkerCreateInput): Worker {
        val departments = departmentRepository.findAllById(input.departmentIds.map { it.toLong() })

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
            val newDepartments = departmentRepository.findAllById(ids.map { it.toLong() })
            worker.departments = (worker.departments + newDepartments).distinct()
        }

        return workerRepository.save(worker)
    }

    override fun deleteWorker(id: Long) {
        if (!workerRepository.existsById(id)) throw RuntimeException("Worker not found")
        workerRepository.deleteById(id)
    }
}