package toy.lsd.library.shared.infrastructure.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import toy.lsd.library.shared.domain.event.DomainEventPublisher
import toy.lsd.library.shared.domain.event.HasDomainEvents

@Aspect
@Component
class DomainEventPublishingAspect(
    private val domainEventPublisher: DomainEventPublisher
) {
    @AfterReturning("@annotation(toy.lsd.library.shared.domain.event.PublishDomainEvents)")
    fun publishDomainEvents(joinPoint: JoinPoint) {
        joinPoint.args.forEach { arg ->
            if (arg is HasDomainEvents) {
                arg.domainEvents.forEach { event -> domainEventPublisher.publish(event) }
            }
        }
    }
}
