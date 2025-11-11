package toy.lsd.library.catalog.infrastructure.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import toy.lsd.library.catalog.infrastructure.persistence.entity.BookEntity

interface BookJpaRepository: JpaRepository<BookEntity, String> {
}
