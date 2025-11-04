package toy.lsd.library.catalog.domain.repository

import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.shared.domain.model.ISBN
import java.util.Optional

interface BookRepository {
    fun findById(id: ISBN): Optional<Book>
}
