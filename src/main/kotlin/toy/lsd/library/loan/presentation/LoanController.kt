package toy.lsd.library.loan.presentation

import org.springframework.web.bind.annotation.*
import toy.lsd.library.loan.application.dto.CreateLoanRequest
import toy.lsd.library.loan.application.service.LoanService
import toy.lsd.library.loan.domain.model.LoanId

@RestController
@RequestMapping("/api/loans")
class LoanController(
    private val loanService: LoanService
) {
    @PostMapping
    fun createLoan(@RequestBody createLoanRequest: CreateLoanRequest): String {
        val createdLoan = loanService.createLoan(createLoanRequest.memberId, createLoanRequest.bookIsbn)
        return createdLoan.id.value
    }

    @GetMapping("/{loanId}")
    fun loans(@PathVariable loanId: LoanId): String {
        val loan = loanService.findById(loanId) ?: return "Not found"
        return loan.id.value
    }
}