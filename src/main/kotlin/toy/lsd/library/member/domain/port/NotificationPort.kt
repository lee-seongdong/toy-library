package toy.lsd.library.member.domain.port

interface NotificationPort {
    fun sendWelcomeEmail(email: String, name: String)
    fun sendSuspensionNotice(email: String, name: String)
    fun sendActivationNotice(email: String, name: String)
}