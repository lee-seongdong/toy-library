package toy.lsd.library.shared.domain.model

@JvmInline
value class MemberId(val value: String) {
    companion object {
        fun of(value: String): MemberId = MemberId(value)
    }
}
