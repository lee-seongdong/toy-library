package toy.lsd.library.member.adaptor.web.dto

import toy.lsd.library.member.domain.Member
import toy.lsd.library.member.domain.MemberStatus
import java.time.LocalDateTime

data class MemberResponseDto(
    val id: String,
    val name: String,
    val email: String,
    val status: MemberStatus,
    val registeredAt: LocalDateTime,
    val maxLoanCount: Int,
    val canBorrow: Boolean
) {
    companion object {
        fun from(member: Member): MemberResponseDto {
            return MemberResponseDto(
                id = member.id.value,
                name = member.name,
                email = member.email.value,
                status = member.status,
                registeredAt = member.registeredAt,
                maxLoanCount = member.maxLoanCount,
                canBorrow = member.canBorrow()
            )
        }
    }
}