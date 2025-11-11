package toy.lsd.library.catalog.adapter.gateway

import org.springframework.stereotype.Component
import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.catalog.infrastructure.persistence.entity.BookEntity
import toy.lsd.library.catalog.infrastructure.persistence.repository.BookJpaRepository
import toy.lsd.library.catalog.usecase.port.BookRepository
import toy.lsd.library.shared.domain.model.ISBN

@Component
class BookRepositoryGateway(
    private val jpaRepository: BookJpaRepository
): BookRepository {
    override fun save(book: Book) {
        val entity = BookEntity.from(book)
        jpaRepository.save(entity)
    }
    
    override fun findByIsbn(isbn: ISBN): Book? {
        return jpaRepository.findById(isbn.value)
            .map { it.toDomain() }
            .orElse(null)
    }
    
    override fun findAll(): List<Book> {
        return jpaRepository.findAll()
            .map { it.toDomain() }
    }
    
    override fun existsByIsbn(isbn: ISBN): Boolean {
        return jpaRepository.existsById(isbn.value)
    }
}
