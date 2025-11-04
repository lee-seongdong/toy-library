package toy.lsd.library.loan.infrastructure

import org.springframework.stereotype.Component
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import toy.lsd.library.loan.domain.repository.LoanRepository
import toy.lsd.library.loan.infrastructure.persistence.entity.LoanEntity
import toy.lsd.library.loan.infrastructure.persistence.repository.LoanJpaRepository
import toy.lsd.library.shared.domain.event.PublishDomainEvents
import java.time.LocalDate
import java.util.*

@Component
class LoanRepositoryAdaptor(
    private val loanJpaRepository: LoanJpaRepository,
) : LoanRepository {

    @PublishDomainEvents
    override fun save(loan: Loan): Loan {
        val savedLoan = loanJpaRepository.save(LoanEntity.from(loan)).toDomain()
        throw RuntimeException("")
        return savedLoan
    }

    override fun findAll(): List<Loan> {
        return loanJpaRepository.findAll().map { it.toDomain() }
    }

    override fun findById(id: LoanId): Optional<Loan> {
        return loanJpaRepository.findById(id.value).map { it.toDomain() }
    }


    override fun findByMemberId(memberId: String): List<Loan> {
        return loanJpaRepository.findByMemberId(memberId).map { it.toDomain() }
    }

    override fun findOverdueLoans(now: LocalDate): List<Loan> {
        return loanJpaRepository.findOverdueLoans(now).map { it.toDomain() }
    }

    override fun deleteById(id: LoanId) {
        loanJpaRepository.deleteById(id.value)
    }

}
