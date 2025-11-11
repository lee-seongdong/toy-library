package toy.lsd.library.catalog.adapter.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import toy.lsd.library.catalog.usecase.RegisterBookUseCase

@RestController
class BookController(
    private val registerBookUseCase: RegisterBookUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerBook(@RequestBody request: BookRequestDto): BookResponseDto {
        // DTO → UseCase Request 변환
        val useCaseRequest = RegisterBookUseCase.Request(
            isbn = request.isbn,
            title = request.title,
            author = request.author
        )
        
        // UseCase 실행
        val response = registerBookUseCase.execute(useCaseRequest)
        
        // UseCase Response → DTO 변환
        return BookResponseDto(
            isbn = response.isbn,
            title = response.title,
            registeredAt = response.registeredAt
        )
    }
}

data class BookRequestDto(
    val isbn: String,
    val title: String,
    val author: String
)

data class BookResponseDto(
    val isbn: String,
    val title: String,
    val registeredAt: String
)
