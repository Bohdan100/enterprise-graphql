package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping

import corp.enterprise.service.AuditService
import corp.enterprise.model.AuditLog

@Controller
class AuditController(private val auditService: AuditService) {
    @QueryMapping
    fun auditLogs(): List<AuditLog> {
        return auditService.getAllLogs()
    }
}