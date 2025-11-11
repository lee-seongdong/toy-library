package toy.lsd.library.catalog.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import toy.lsd.library.catalog.domain.model.Book
import toy.lsd.library.catalog.domain.model.BookTitle
import toy.lsd.library.catalog.usecase.port.BookRepository
import toy.lsd.library.catalog.usecase.port.ISBNService
import toy.lsd.library.shared.domain.model.ISBN
import java.time.LocalDateTime

@Service
@Transactional
class RegisterBookInteractor(
    private val bookRepository: BookRepository,
    private val isbnService: ISBNService
): RegisterBookUseCase {
    override fun execute(request: RegisterBookUseCase.Request): RegisterBookUseCase.Response {
        // 1. ISBN 검증
        isbnService.validate(request.isbn)
        
        // 2. 도메인 모델 생성
        val book = Book(
            isbn = ISBN(request.isbn),
            title = BookTitle(request.title)
        )
        
        // 3. 저장
        bookRepository.save(book)
        
        // 4. 응답 생성
        return RegisterBookUseCase.Response(
            isbn = book.isbn.value,
            title = book.title.value,
            registeredAt = LocalDateTime.now().toString()
        )
    }
}
