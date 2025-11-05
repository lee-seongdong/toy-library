package toy.lsd.library.member.domain.port

import toy.lsd.library.member.domain.Email
import toy.lsd.library.member.domain.Member
import toy.lsd.library.shared.domain.model.MemberId

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: MemberId): Member?
    fun findByEmail(email: Email): Member?
    fun findAll(): List<Member>
    fun delete(id: MemberId)
    fun existsByEmail(email: Email): Boolean
}