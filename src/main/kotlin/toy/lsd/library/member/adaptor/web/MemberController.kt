package toy.lsd.library.member.adaptor.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.lsd.library.member.adaptor.web.dto.RegisterMemberRequest
import toy.lsd.library.member.adaptor.web.dto.MemberResponseDto
import toy.lsd.library.member.application.usecase.FindMemberUsecase
import toy.lsd.library.member.application.usecase.ManageMemberUsecase
import toy.lsd.library.member.application.usecase.RegisterMemberCommand
import toy.lsd.library.member.application.usecase.RegisterMemberUsecase
import toy.lsd.library.shared.domain.model.MemberId

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val registerMemberUsecase: RegisterMemberUsecase,
    private val findMemberUsecase: FindMemberUsecase,
    private val manageMemberUsecase: ManageMemberUsecase
) {

    @PostMapping
    fun register(@RequestBody request: RegisterMemberRequest): ResponseEntity<MemberResponseDto> {
        val command = RegisterMemberCommand(request.name, request.email)
        val response = registerMemberUsecase.register(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<MemberResponseDto> {
        val response = findMemberUsecase.findById(MemberId.of(id))
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<MemberResponseDto>> {
        val responses = findMemberUsecase.findAll()
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/email/{email}")
    fun findByEmail(@PathVariable email: String): ResponseEntity<MemberResponseDto> {
        val response = findMemberUsecase.findByEmail(email)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/suspend")
    fun suspend(@PathVariable id: String): ResponseEntity<MemberResponseDto> {
        val response = manageMemberUsecase.suspend(MemberId.of(id))
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/activate")
    fun activate(@PathVariable id: String): ResponseEntity<MemberResponseDto> {
        val response = manageMemberUsecase.activate(MemberId.of(id))
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/withdraw")
    fun withdraw(@PathVariable id: String): ResponseEntity<MemberResponseDto> {
        val response = manageMemberUsecase.withdraw(MemberId.of(id))
        return ResponseEntity.ok(response)
    }
}
