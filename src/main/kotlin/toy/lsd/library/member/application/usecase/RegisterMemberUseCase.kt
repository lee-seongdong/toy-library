package toy.lsd.library.member.application.usecase

import toy.lsd.library.member.application.usecase.dto.MemberResponseDto
import toy.lsd.library.member.application.usecase.dto.RegisterMemberCommand

interface RegisterMemberUseCase {
    fun register(command: RegisterMemberCommand): MemberResponseDto
}
