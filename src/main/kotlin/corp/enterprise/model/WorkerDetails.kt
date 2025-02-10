package corp.enterprise.model

import jakarta.persistence.*

@Entity
@Table(name = "worker_details")
data class WorkerDetails(
    @Id
    val workerId: Long? = null,

    @Column(nullable = false, length = 100)
    var address: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var sex: Sex = Sex.MALE,

    @OneToOne
    @MapsId
    val worker: Worker
) {
    constructor() : this(null, "", Sex.MALE, Worker())
}