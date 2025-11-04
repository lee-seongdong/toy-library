package toy.lsd.library.loan.infrastructure.persistence.mapper

import org.springframework.stereotype.Component
import toy.lsd.library.loan.domain.model.Loan
import toy.lsd.library.loan.domain.model.LoanId
import toy.lsd.library.loan.domain.model.LoanPeriod
import toy.lsd.library.loan.infrastructure.persistence.entity.LoanEntity

@Component
class LoanMapper {
    fun toDomain(entity: LoanEntity): Loan {
        return Loan(
            id = LoanId.Companion.of(entity.id),
            memberId = entity.memberId,
            bookIsbn = entity.bookIsbn,
            period = LoanPeriod(entity.startDate, entity.dueDate),
            status = entity.status,
            borrowedAt = entity.borrowedAt,
            returnedAt = entity.returnedAt
        )
    }

    fun toEntity(domain: Loan): LoanEntity {
        return LoanEntity(
            id = domain.id.value,
            memberId = domain.memberId,
            bookIsbn = domain.bookIsbn,
            startDate = domain.period.startDate,
            dueDate = domain.period.dueDate,
            status = domain.status,
            borrowedAt = domain.borrowedAt,
            returnedAt = domain.returnedAt
        )
    }

    fun toDomainList(entities: List<LoanEntity>): List<Loan> {
        return entities.map { toDomain(it) }
    }
}