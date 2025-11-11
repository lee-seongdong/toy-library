package toy.lsd.library.member.application.usecase

import toy.lsd.library.member.application.usecase.dto.MemberResponseDto
import toy.lsd.library.shared.domain.model.MemberId

interface FindMemberUseCase {
    fun findById(id: MemberId): MemberResponseDto?
    fun findByEmail(email: String): MemberResponseDto?
    fun findAll(): List<MemberResponseDto>
}
