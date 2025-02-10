package corp.enterprise.model

import jakarta.persistence.*

@Entity
@Table(name = "worker")
data class Worker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "first_name", nullable = false, length = 100)
    var firstName: String,

    @Column(name = "last_name", nullable = false, length = 100)
    var lastName: String,

    @Column(nullable = false, length = 100)
    var position: String,

    @OneToOne(mappedBy = "worker", cascade = [CascadeType.ALL], orphanRemoval = true)
    var details: WorkerDetails? = null,

    @ManyToMany(mappedBy = "workers")
    var departments: List<Department> = mutableListOf()
) {
    constructor() : this(null, "", "", "", null, mutableListOf())
}