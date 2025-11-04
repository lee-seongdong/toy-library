package toy.lsd.library.loan.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import toy.lsd.library.loan.infrastructure.persistence.mapper.LoanMapper
import toy.lsd.library.loan.infrastructure.persistence.LoanRepository

@Service
@Transactional
open class LoanService(
    private val loanRepository: LoanRepository,
    private val loanMapper: LoanMapper
) {
    fun createLoan(memberId: String, bookIsbn: String): Loan {
        val loan = Loan.create(memberId, bookIsbn)
        val entity = loanMapper.toEntity(loan)
        val savedEntity = loanRepository.save(entity)
        return loanMapper.toDomain(savedEntity)
    }

    fun borrowBook(loanId: LoanId): Loan {
        val entity = loanRepository.findById(loanId.value)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }

        val loan = loanMapper.toDomain(entity)
        val borrowedLoan = loan.borrow()

        val updatedEntity = loanMapper.toEntity(borrowedLoan)
        val savedEntity = loanRepository.save(updatedEntity)
        return loanMapper.toDomain(savedEntity)
    }

    fun returnBook(loanId: LoanId): Loan {
        val entity = loanRepository.findById(loanId.value)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }

        val loan = loanMapper.toDomain(entity)
        val returnedLoan = loan.returnBook()

        val updatedEntity = loanMapper.toEntity(returnedLoan)
        val savedEntity = loanRepository.save(updatedEntity)
        return loanMapper.toDomain(savedEntity)
    }

    fun extendLoan(loanId: LoanId, days: Int = 3): Loan {
        val entity = loanRepository.findById(loanId.value)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }

        val loan = loanMapper.toDomain(entity)
        val extendedLoan = loan.extend(days)

        val updatedEntity = loanMapper.toEntity(extendedLoan)
        val savedEntity = loanRepository.save(updatedEntity)
        return loanMapper.toDomain(savedEntity)
    }

    @Transactional(readOnly = true)
    fun findById(loanId: LoanId): Loan? {
        return loanRepository.findById(loanId.value)
            .map { loanMapper.toDomain(it) }
            .orElse(null)
    }

    @Transactional(readOnly = true)
    open fun findByMemberId(memberId: String): List<Loan> {
        val entities = loanRepository.findByMemberId(memberId)
        return loanMapper.toDomainList(entities)
    }

    @Transactional(readOnly = true)
    open fun findAll(): List<Loan> {
        val entities = loanRepository.findAll()
        return loanMapper.toDomainList(entities)
    }

    fun deleteLoan(loanId: LoanId) {
        loanRepository.deleteById(loanId.value)
    }
}