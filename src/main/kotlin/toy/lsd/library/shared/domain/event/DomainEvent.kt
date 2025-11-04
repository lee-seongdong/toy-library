package toy.lsd.library.shared.domain.event

import java.time.LocalDateTime

interface DomainEvent {
    val occurredOn: LocalDateTime
        get() = LocalDateTime.now()

}
