package toy.lsd.library.loan.domain.event

import toy.lsd.library.shared.domain.event.DomainEvent

data class LoanCreated(
    val memberId: String,
    val isbn: String,
) : DomainEvent
