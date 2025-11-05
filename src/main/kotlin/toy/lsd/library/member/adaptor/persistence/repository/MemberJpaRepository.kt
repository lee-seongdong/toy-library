package toy.lsd.library.member.adaptor.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import toy.lsd.library.member.adaptor.persistence.entity.MemberEntity

interface MemberJpaRepository : JpaRepository<MemberEntity, String> {
    fun findByEmail(email: String): MemberEntity?
    fun existsByEmail(email: String): Boolean
}