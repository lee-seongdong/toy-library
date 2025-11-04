package toy.lsd.library.loan.domain.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class LoanPeriod(
    val startDate: LocalDate,
    val dueDate: LocalDate
) {
    companion object {
        fun standardPeriod(): LoanPeriod {
            val now = LocalDate.now()
            return LoanPeriod(now, now.plusDays(7))
        }
    }

    fun extend(days: Int): LoanPeriod {
        return LoanPeriod(startDate, dueDate.plusDays(days.toLong()))
    }

    fun isOverdue(): Boolean {
        return LocalDate.now().isAfter(dueDate)
    }

    fun getOverdueDays(): Int {
        if (!isOverdue()) return 0
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now()).toInt()
    }
}