package toy.lsd.library.catalog.usecase.port

interface ISBNService {
    fun validate(isbn: String): Boolean
}
