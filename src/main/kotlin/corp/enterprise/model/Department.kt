package corp.enterprise.model

import jakarta.persistence.*

@Entity
@Table(name = "department")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 200)
    var name: String,

    @Column(nullable = false, length = 150)
    var duties: String,

    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL])
    var projects: List<Project> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "department_worker",
        joinColumns = [JoinColumn(name = "department_id")],
        inverseJoinColumns = [JoinColumn(name = "worker_id")]
    )
    var workers: List<Worker> = mutableListOf()
) {
    constructor() : this(null, "", "", mutableListOf(), mutableListOf())
}
