package toy.lsd.library.loan.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import toy.lsd.library.loan.domain.model.LoanStatus
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "loan")
data class LoanEntity(
    @Id
    val id: String,

    @Column(name = "member_id", nullable = false)
    val memberId: String,

    @Column(name = "book_isbn", nullable = false)
    val bookIsbn: String,

    @Column(name = "start_date", nullable = false)
    val startDate: LocalDate,

    @Column(name = "due_date", nullable = false)
    val dueDate: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: LoanStatus,

    @Column(name = "borrowed_at")
    val borrowedAt: LocalDateTime? = null,

    @Column(name = "returned_at")
    val returnedAt: LocalDateTime? = null
)