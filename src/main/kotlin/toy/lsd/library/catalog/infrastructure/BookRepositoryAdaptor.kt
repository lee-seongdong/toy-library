package toy.lsd.library.catalog.infrastructure

import org.springframework.stereotype.Component
import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.catalog.domain.repository.BookRepository
import toy.lsd.library.shared.domain.model.ISBN
import java.util.Optional

@Component
class BookRepositoryAdaptor: BookRepository {
    override fun findById(id: ISBN): Optional<Book> {
        TODO("Not yet implemented")
    }
}
