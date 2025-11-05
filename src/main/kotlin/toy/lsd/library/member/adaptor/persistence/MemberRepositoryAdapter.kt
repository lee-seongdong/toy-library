package toy.lsd.library.member.adaptor.persistence

import org.springframework.stereotype.Component
import toy.lsd.library.member.domain.Email
import toy.lsd.library.member.domain.Member
import toy.lsd.library.member.adaptor.persistence.entity.MemberEntity
import toy.lsd.library.member.adaptor.persistence.repository.MemberJpaRepository
import toy.lsd.library.member.domain.port.MemberRepository
import toy.lsd.library.shared.domain.model.MemberId

@Component
class MemberRepositoryAdapter(
    private val jpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member): Member {
        val entity = toEntity(member)
        val savedEntity = jpaRepository.save(entity)
        return toDomain(savedEntity)
    }

    override fun findById(id: MemberId): Member? {
        return jpaRepository.findById(id.value)
            .map { toDomain(it) }
            .orElse(null)
    }

    override fun findByEmail(email: Email): Member? {
        return jpaRepository.findByEmail(email.value)?.let { toDomain(it) }
    }

    override fun findAll(): List<Member> {
        return jpaRepository.findAll().map { toDomain(it) }
    }

    override fun delete(id: MemberId) {
        jpaRepository.deleteById(id.value)
    }

    override fun existsByEmail(email: Email): Boolean {
        return jpaRepository.existsByEmail(email.value)
    }

    private fun toDomain(entity: MemberEntity): Member {
        return Member(
            id = MemberId.of(entity.id),
            name = entity.name,
            email = Email(entity.email),
            status = entity.status,
            registeredAt = entity.registeredAt,
            maxLoanCount = entity.maxLoanCount
        )
    }

    private fun toEntity(member: Member): MemberEntity {
        return MemberEntity(
            id = member.id.value,
            name = member.name,
            email = member.email.value,
            status = member.status,
            registeredAt = member.registeredAt,
            maxLoanCount = member.maxLoanCount
        )
    }
}