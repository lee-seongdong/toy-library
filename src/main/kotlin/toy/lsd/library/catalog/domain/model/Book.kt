package toy.lsd.library.catalog.domain.model

import toy.lsd.library.shared.domain.model.ISBN

data class Book(
    val isbn: ISBN,
    val title: BookTitle,
) {
}
