package toy.lsd.library.shared.domain.model

@JvmInline
value class ISBN(val value: String) {
    companion object {
        fun of(value: String): ISBN = ISBN(value)
    }
}
