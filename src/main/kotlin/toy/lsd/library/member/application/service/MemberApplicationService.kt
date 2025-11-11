package toy.lsd.library.member.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import toy.lsd.library.member.application.usecase.FindMemberUseCase
import toy.lsd.library.member.application.usecase.ManageMemberUseCase
import toy.lsd.library.member.application.usecase.RegisterMemberUseCase
import toy.lsd.library.member.application.usecase.dto.MemberResponseDto
import toy.lsd.library.member.application.usecase.dto.RegisterMemberCommand
import toy.lsd.library.member.domain.Email
import toy.lsd.library.member.domain.Member
import toy.lsd.library.member.domain.port.MemberRepository
import toy.lsd.library.member.domain.port.NotificationPort
import toy.lsd.library.shared.domain.model.MemberId

@Service
@Transactional
class MemberApplicationService(
    private val memberRepository: MemberRepository,    // Outbound Port
    private val notificationPort: NotificationPort     // Outbound Port
) : RegisterMemberUseCase, FindMemberUseCase, ManageMemberUseCase {

    override fun register(command: RegisterMemberCommand): MemberResponseDto {
        // 비즈니스 규칙 검증
        val email = Email(command.email)
        if (memberRepository.existsByEmail(email)) {
            throw IllegalArgumentException("Email already exists: ${command.email}")
        }

        // 도메인 객체 생성
        val member = Member.register(command.name, command.email)

        // 저장
        val savedMember = memberRepository.save(member)

        // 외부 시스템 호출 (이메일 발송)
        notificationPort.sendWelcomeEmail(savedMember.email.value, savedMember.name)

        return MemberResponseDto.from(savedMember)
    }

    @Transactional(readOnly = true)
    override fun findById(id: MemberId): MemberResponseDto? {
        return memberRepository.findById(id)?.let { MemberResponseDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun findByEmail(email: String): MemberResponseDto? {
        return memberRepository.findByEmail(Email(email))?.let { MemberResponseDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<MemberResponseDto> {
        return memberRepository.findAll().map { MemberResponseDto.from(it) }
    }

    override fun suspend(id: MemberId): MemberResponseDto {
        val member = memberRepository.findById(id)
            ?: throw IllegalArgumentException("Member not found: ${id.value}")

        val suspendedMember = member.suspend()
        val savedMember = memberRepository.save(suspendedMember)

        notificationPort.sendSuspensionNotice(savedMember.email.value, savedMember.name)

        return MemberResponseDto.from(savedMember)
    }

    override fun activate(id: MemberId): MemberResponseDto {
        val member = memberRepository.findById(id)
            ?: throw IllegalArgumentException("Member not found: ${id.value}")

        val activatedMember = member.activate()
        val savedMember = memberRepository.save(activatedMember)

        notificationPort.sendActivationNotice(savedMember.email.value, savedMember.name)

        return MemberResponseDto.from(savedMember)
    }

    override fun withdraw(id: MemberId): MemberResponseDto {
        val member = memberRepository.findById(id)
            ?: throw IllegalArgumentException("Member not found: ${id.value}")

        val withdrawnMember = member.withdraw()
        val savedMember = memberRepository.save(withdrawnMember)

        return MemberResponseDto.from(savedMember)
    }
}
