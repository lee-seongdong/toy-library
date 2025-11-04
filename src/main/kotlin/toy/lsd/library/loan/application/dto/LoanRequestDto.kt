package toy.lsd.library.loan.application.dto

import toy.lsd.library.loan.domain.model.LoanStatus
import java.time.LocalDate

data class CreateLoanRequest(
    val memberId: String,
    val bookIsbn: String
)

data class UpdateLoanRequest(
    val memberId: String? = null,
    val bookIsbn: String? = null,
    val startDate: LocalDate? = null,
    val dueDate: LocalDate? = null,
    val status: LoanStatus? = null
)

data class ExtendLoanRequest(
    val days: Int = 3
)