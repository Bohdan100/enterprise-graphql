package corp.enterprise.model

import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
@Table(name = "project")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    var name: String,

    @Column(nullable = false, length = 100)
    var description: String,

    @Column(nullable = false)
    @field:Min(1)
    var importance: Int,

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department
) {
    constructor() : this(null, "", "", 1, Department())
}