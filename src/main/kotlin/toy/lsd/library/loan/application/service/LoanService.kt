package toy.lsd.library.loan.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import toy.lsd.library.catalog.domain.repository.BookRepository
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import toy.lsd.library.loan.domain.repository.LoanRepository
import toy.lsd.library.loan.domain.service.LoanCheckService
import toy.lsd.library.shared.domain.model.ISBN
import toy.lsd.library.shared.domain.model.MemberId

@Service
class LoanService(
    private val loanRepository: LoanRepository,
    private val bookRepository: BookRepository,
    private val loanCheckService: LoanCheckService,
) {
    @Transactional
    fun createLoan(memberId: MemberId, bookIsbn: ISBN): Loan {
        val loan = Loan.create(memberId, bookIsbn)
        return loanRepository.save(loan)
    }

    fun borrowBook(loanId: LoanId, bookIsbn: ISBN): Loan {
        val loan = loanRepository.findById(loanId)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }
        val book = bookRepository.findById(bookIsbn)
            .orElseThrow { IllegalArgumentException("Book not found: ${bookIsbn.value}") }

        if (!loanCheckService.checkBorrowable(loan, book)) {
            throw IllegalStateException("Can not Borrow. Loan: $loan, Book: $book")
        }

        val borrowedLoan = loan.borrow()
        return loanRepository.save(borrowedLoan)
    }

    fun returnBook(loanId: LoanId): Loan {
        val loan = loanRepository.findById(loanId)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }

        val returnedLoan = loan.returnBook()

        return loanRepository.save(returnedLoan)
    }

    fun extendLoan(loanId: LoanId, days: Int = 3): Loan {
        val loan = loanRepository.findById(loanId)
            .orElseThrow { IllegalArgumentException("Loan not found: ${loanId.value}") }

        val extendedLoan = loan.extend(days)
        return loanRepository.save(extendedLoan)
    }

    @Transactional(readOnly = true)
    fun findById(loanId: LoanId): Loan? {
        return loanRepository.findById(loanId)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    open fun findByMemberId(memberId: String): List<Loan> {
        return loanRepository.findByMemberId(memberId)
    }

    @Transactional(readOnly = true)
    open fun findAll(): List<Loan> {
        return loanRepository.findAll()
    }

    fun deleteLoan(loanId: LoanId) {
        loanRepository.deleteById(loanId)
    }
}
