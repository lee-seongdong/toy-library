package toy.lsd.library.loan.domain.event

import toy.lsd.library.shared.domain.event.DomainEvent

data class BookBorrowed(
    val loanId: String,
    val memberId: String,
    val bookIsbn: String,
) : DomainEvent
