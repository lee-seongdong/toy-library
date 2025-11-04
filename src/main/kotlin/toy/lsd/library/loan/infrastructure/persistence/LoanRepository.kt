package toy.lsd.library.loan.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import toy.lsd.library.loan.infrastructure.persistence.entity.LoanEntity
import java.time.LocalDate

@Repository
interface LoanRepository: JpaRepository<LoanEntity, String> {
    fun findByMemberId(memberId: String): List<LoanEntity>

    @Query("""
       SELECT l 
         FROM LoanEntity l 
        WHERE l.dueDate < :now 
          AND l.status = 'ON_LOAN' 
    """)
    fun findOverdueLoans(@Param("now") now: LocalDate): List<LoanEntity>
}