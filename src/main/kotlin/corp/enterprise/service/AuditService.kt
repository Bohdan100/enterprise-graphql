package corp.enterprise.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.annotation.Propagation
import org.springframework.data.domain.Sort
import corp.enterprise.model.AuditLog
import corp.enterprise.repository.AuditRepository

@Service
class AuditService(private val auditRepository: AuditRepository) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun log(operation: String, details: String) {
        auditRepository.save(AuditLog(operation = operation, details = details))
    }

    @Transactional(readOnly = true)
    fun getAllLogs(): List<AuditLog> {
        return auditRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"))
    }
}