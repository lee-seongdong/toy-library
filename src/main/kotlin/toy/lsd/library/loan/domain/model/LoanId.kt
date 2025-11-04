package toy.lsd.library.loan.domain.model

import java.util.UUID


@JvmInline
value class LoanId(val value: String) {
    companion object {
        fun generate(): LoanId = LoanId(UUID.randomUUID().toString())
        fun of(value: String): LoanId = LoanId(value)
    }
}