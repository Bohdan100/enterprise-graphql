package corp.enterprise.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "audit_logs")
data class AuditLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val operation: String = "",
    val details: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)