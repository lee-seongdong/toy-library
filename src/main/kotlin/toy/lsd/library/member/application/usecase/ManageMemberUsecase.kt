package toy.lsd.library.member.application.usecase

import toy.lsd.library.member.adaptor.web.dto.MemberResponseDto
import toy.lsd.library.shared.domain.model.MemberId

interface ManageMemberUsecase {
    fun suspend(id: MemberId): MemberResponseDto
    fun activate(id: MemberId): MemberResponseDto
    fun withdraw(id: MemberId): MemberResponseDto
}