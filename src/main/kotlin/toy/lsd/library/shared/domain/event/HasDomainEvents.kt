package toy.lsd.library.shared.domain.event

interface HasDomainEvents {
    val domainEvents: List<DomainEvent>
}
