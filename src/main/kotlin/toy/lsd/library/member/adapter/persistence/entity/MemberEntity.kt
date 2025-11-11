package toy.lsd.library.member.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import toy.lsd.library.member.domain.MemberStatus
import java.time.LocalDateTime

@Entity
@Table(name = "members")
data class MemberEntity(
    @Id
    val id: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: MemberStatus,

    @Column(name = "registered_at", nullable = false)
    val registeredAt: LocalDateTime,

    @Column(name = "max_loan_count", nullable = false)
    val maxLoanCount: Int
)
