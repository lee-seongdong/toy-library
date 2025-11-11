package toy.lsd.library.catalog.usecase.port

import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.shared.domain.model.ISBN

interface BookRepository {
    fun save(book: Book)
    fun findByIsbn(isbn: ISBN): Book?
    fun findAll(): List<Book>
    fun existsByIsbn(isbn: ISBN): Boolean
}
