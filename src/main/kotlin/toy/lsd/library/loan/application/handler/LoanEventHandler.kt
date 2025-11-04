package toy.lsd.library.loan.application.handler

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener
import toy.lsd.library.loan.domain.event.BookBorrowed
import toy.lsd.library.loan.domain.event.LoanCreated

@Component
class LoanEventHandler {
    @TransactionalEventListener
    fun handleBookBorrowed(event: BookBorrowed) {
        println("알림: 회원 ${event.memberId}님이 도서 ${event.bookIsbn}를 대출했습니다.")
    }

    @TransactionalEventListener
    fun handleLoanCreated(event: LoanCreated) {
        println("도서 대출 발생. memberId: ${event.memberId}, isbn: ${event.isbn}")
    }
}
