package toy.lsd.library.member.domain

import toy.lsd.library.shared.domain.model.MemberId
import java.time.LocalDateTime

data class Member(
    val id: MemberId,
    val name: String,
    val email: Email,
    val status: MemberStatus,
    val registeredAt: LocalDateTime,
    val maxLoanCount: Int = 3
) {
    companion object {
        fun register(name: String, email: String): Member {
            require(name.isNotBlank()) { "Name cannot be blank" }

            return Member(
                id = MemberId.generate(),
                name = name,
                email = Email(email),
                status = MemberStatus.ACTIVE,
                registeredAt = LocalDateTime.now(),
                maxLoanCount = 3
            )
        }
    }

    fun suspend(): Member {
        require(status == MemberStatus.ACTIVE) { "Only active members can be suspended" }
        return copy(status = MemberStatus.SUSPENDED)
    }

    fun activate(): Member {
        require(status == MemberStatus.SUSPENDED) { "Only suspended members can be activated" }
        return copy(status = MemberStatus.ACTIVE)
    }

    fun withdraw(): Member {
        require(status != MemberStatus.WITHDRAWN) { "Member already withdrawn" }
        return copy(status = MemberStatus.WITHDRAWN)
    }

    fun canBorrow(): Boolean {
        return status == MemberStatus.ACTIVE
    }

    fun hasReachedLoanLimit(currentLoanCount: Int): Boolean {
        return currentLoanCount >= maxLoanCount
    }
}