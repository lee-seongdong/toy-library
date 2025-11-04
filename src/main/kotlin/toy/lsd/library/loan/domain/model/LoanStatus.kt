package toy.lsd.library.loan.domain.model

enum class LoanStatus {
    AVAILABLE,      // 대여 가능
    ON_LOAN,        // 대여 중
    PROCESSING,     // 정리중 (반납되어 관리자가 정리중)
    LOAN_WAITING,   // 대여 대기 (예약 순서가 와서 3일간 우선권)
    OVERDUE,        // 연체됨
    DAMAGED         // 파손됨
}