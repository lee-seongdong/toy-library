package toy.lsd.library.shared.domain.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DomainEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publish(event: DomainEvent) {
        applicationEventPublisher.publishEvent(event)
    }
}
