package toy.lsd.library.loan.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import toy.lsd.library.loan.domain.model.LoanPeriod
import toy.lsd.library.loan.domain.model.LoanStatus
import toy.lsd.library.shared.domain.model.ISBN
import toy.lsd.library.shared.domain.model.MemberId
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
) {
    fun toDomain(): Loan {
        return Loan(
            id = LoanId.of(id),
            memberId = MemberId(memberId),
            bookIsbn = ISBN(bookIsbn),
            period = LoanPeriod(startDate, dueDate),
            status = status,
            borrowedAt = borrowedAt,
            returnedAt = returnedAt
        )
    }

    companion object {
        fun from(domain: Loan): LoanEntity {
            return LoanEntity(
                id = domain.id.value,
                memberId = domain.memberId.value,
                bookIsbn = domain.bookIsbn.value,
                startDate = domain.period.startDate,
                dueDate = domain.period.dueDate,
                status = domain.status,
                borrowedAt = domain.borrowedAt,
                returnedAt = domain.returnedAt
            )
        }
    }
}
