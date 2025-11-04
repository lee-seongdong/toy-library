package toy.lsd.library.loan.application.dto

import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class LoanResponse(
    val id: String,
    val memberId: String,
    val bookIsbn: String,
    val startDate: LocalDate,
    val dueDate: LocalDate,
    val status: LoanStatus,
    val borrowedAt: LocalDateTime?,
    val returnedAt: LocalDateTime?,
    val isOverdue: Boolean,
    val overdueDays: Int
) {
    companion object {
        fun from(loan: Loan): LoanResponse {
            return LoanResponse(
                id = loan.id.value,
                memberId = loan.memberId,
                bookIsbn = loan.bookIsbn,
                startDate = loan.period.startDate,
                dueDate = loan.period.dueDate,
                status = loan.status,
                borrowedAt = loan.borrowedAt,
                returnedAt = loan.returnedAt,
                isOverdue = loan.period.isOverdue(),
                overdueDays = loan.period.getOverdueDays()
            )
        }
    }
}

data class LoanListResponse(
    val loans: List<LoanResponse>,
    val totalCount: Int
)