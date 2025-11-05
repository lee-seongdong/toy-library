package toy.lsd.library.shared.domain.model

import java.util.UUID

@JvmInline
value class MemberId(val value: String) {
    companion object {
        fun generate(): MemberId = MemberId(UUID.randomUUID().toString())
        fun of(value: String): MemberId = MemberId(value)
    }
}
