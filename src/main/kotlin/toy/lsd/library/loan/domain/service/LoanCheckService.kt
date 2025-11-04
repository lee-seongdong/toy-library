package toy.lsd.library.loan.domain.service

import org.springframework.stereotype.Component
import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.loan.domain.model.Loan

@Component
class LoanCheckService {
    fun checkBorrowable(loan: Loan, book: Book): Boolean {
        // ... 체크 로직
        return true
    }
}
