package toy.lsd.library.loan.domain.repository

import org.springframework.data.repository.query.Param
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import java.time.LocalDate
import java.util.Optional

interface LoanRepository {
    fun save(loan: Loan): Loan
    fun findAll(): List<Loan>
    fun findById(id: LoanId): Optional<Loan>
    fun findByMemberId(memberId: String): List<Loan>
    fun findOverdueLoans(@Param("now") now: LocalDate): List<Loan>
    fun deleteById(id: LoanId)
}
