package toy.lsd.library.catalog.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.catalog.domain.model.BookTitle
import toy.lsd.library.shared.domain.model.ISBN


@Entity
@Table(name = "books")
class BookEntity(
    @Id
    @Column(name = "isbn", length = 13)
    val isbn: String,
    
    @Column(name = "title", nullable = false)
    val title: String
) {

    companion object {
        fun from(book: Book): BookEntity {
            return BookEntity(
                isbn = book.isbn.value,
                title = book.title.value
            )
        }
    }
    
    fun toDomain(): Book {
        return Book(
            isbn = ISBN(this.isbn),
            title = BookTitle(this.title)
        )
    }
}
