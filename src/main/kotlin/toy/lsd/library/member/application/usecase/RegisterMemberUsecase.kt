package toy.lsd.library.member.application.usecase

import toy.lsd.library.member.adaptor.web.dto.MemberResponseDto

interface RegisterMemberUsecase {
    fun register(command: RegisterMemberCommand): MemberResponseDto
}

data class RegisterMemberCommand(
    val name: String,
    val email: String
)