package toy.lsd.library.loan.domain.model

import toy.lsd.library.loan.domain.event.BookBorrowed
import toy.lsd.library.loan.domain.event.LoanCreated
import toy.lsd.library.shared.domain.event.DomainEvent
import toy.lsd.library.shared.domain.event.HasDomainEvents
import toy.lsd.library.shared.domain.model.ISBN
import toy.lsd.library.shared.domain.model.MemberId
import java.time.LocalDateTime

data class Loan(
    val id: LoanId,
    val memberId: MemberId,  // 외부 애그리거트 참조 (ID만)
    val bookIsbn: ISBN,  // 외부 애그리거트 참조 (ID만)
    val period: LoanPeriod,
    val status: LoanStatus,
    val borrowedAt: LocalDateTime? = null,
    val returnedAt: LocalDateTime? = null,
    private val _domainEvents: List<DomainEvent> = emptyList()
): HasDomainEvents {
    override val domainEvents get() = _domainEvents

    companion object {
        fun create(memberId: MemberId, bookIsbn: ISBN): Loan {
            val loanCreated = LoanCreated(
                memberId = memberId.value,
                isbn = bookIsbn.value,
            )

            return Loan(
                id = LoanId.generate(),
                memberId = memberId,
                bookIsbn = bookIsbn,
                period = LoanPeriod.standardPeriod(),
                status = LoanStatus.AVAILABLE,
                borrowedAt = null,
                returnedAt = null,
                _domainEvents = listOf(loanCreated)
            )
        }
    }

    fun borrow(): Loan {
        if (this.status != LoanStatus.AVAILABLE) {
            throw IllegalStateException("Book is not available for loan")
        }

        val bookBorrowed = BookBorrowed(
            loanId = id.value,
            memberId = memberId.value,
            bookIsbn = bookIsbn.value,
        )

        return this.copy(
            status = LoanStatus.ON_LOAN,
            borrowedAt = LocalDateTime.now(),
            _domainEvents = domainEvents + bookBorrowed
        )
    }

    fun returnBook(): Loan {
        if (this.status != LoanStatus.ON_LOAN && this.status != LoanStatus.OVERDUE) {
            throw IllegalStateException("Cannot return book in current status")
        }
        return this.copy(
            status = LoanStatus.PROCESSING,
            returnedAt = LocalDateTime.now()
        )
    }

    fun extend(days: Int = 3): Loan {
        if (this.status != LoanStatus.ON_LOAN) {
            throw IllegalStateException("Cannot extend non-active loan")
        }
        if (this.period.isOverdue()) {
            throw IllegalStateException("Cannot extend overdue loan")
        }
        return this.copy(period = this.period.extend(days))
    }

    fun markAsOverdue(): Loan {
        if (this.status != LoanStatus.ON_LOAN) {
            throw IllegalStateException("Only active loans can become overdue")
        }
        return this.copy(status = LoanStatus.OVERDUE)
    }

    fun eventCleared(): Loan = this.copy(_domainEvents = emptyList())
}
