package toy.lsd.library.catalog.domain.model

@JvmInline
value class BookTitle(val value: String) {
    companion object {
        fun of(value: String) = BookTitle(value)
    }
}
