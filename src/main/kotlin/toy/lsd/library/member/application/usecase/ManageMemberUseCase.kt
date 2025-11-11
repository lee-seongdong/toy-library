package toy.lsd.library.member.application.usecase

import toy.lsd.library.member.application.usecase.dto.MemberResponseDto
import toy.lsd.library.shared.domain.model.MemberId

interface ManageMemberUseCase {
    fun suspend(id: MemberId): MemberResponseDto
    fun activate(id: MemberId): MemberResponseDto
    fun withdraw(id: MemberId): MemberResponseDto
}
