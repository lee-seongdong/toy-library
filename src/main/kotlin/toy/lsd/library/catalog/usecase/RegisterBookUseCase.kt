package toy.lsd.library.catalog.usecase

interface RegisterBookUseCase {
    fun execute(request: Request): Response
    
    data class Request(
        val isbn: String,
        val title: String,
        val author: String
    )
    
    data class Response(
        val isbn: String,
        val title: String,
        val registeredAt: String
    )
}
