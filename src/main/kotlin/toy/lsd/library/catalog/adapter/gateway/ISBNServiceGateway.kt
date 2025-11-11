package toy.lsd.library.catalog.adapter.gateway

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import toy.lsd.library.catalog.infrastructure.external.ISBNApiClient
import toy.lsd.library.catalog.usecase.port.ISBNService

@Component
class ISBNServiceGateway(
    private val isbnApiClient: ISBNApiClient
): ISBNService {
    override fun validate(isbn: String): Boolean {
        TODO("Not yet implemented")
    }
}
