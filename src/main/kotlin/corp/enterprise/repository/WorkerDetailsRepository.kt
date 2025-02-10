package corp.enterprise.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import corp.enterprise.model.WorkerDetails

@Repository
interface WorkerDetailsRepository: JpaRepository<WorkerDetails, Long>