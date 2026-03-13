package corp.enterprise.service

import corp.enterprise.model.Worker
import corp.enterprise.input.WorkerCreateInput
import corp.enterprise.input.WorkerUpdateInput

interface WorkerService {
    fun getAllWorkers(): List<Worker>
    fun getWorkerById(id: Long): Worker
    fun searchByPosition(position: String): List<Worker>
    fun searchByDepartments(departmentIds: List<Long>): List<Worker>
    fun searchByPartialFirstName(partialFirstName: String): List<Worker>
    fun searchByPartialLastName(partialLastName: String): List<Worker>
    fun searchBySex(sex: String): List<Worker>
    fun createWorker(input: WorkerCreateInput): Worker
    fun updateWorker(input: WorkerUpdateInput): Worker
    fun deleteWorker(id: Long)
}