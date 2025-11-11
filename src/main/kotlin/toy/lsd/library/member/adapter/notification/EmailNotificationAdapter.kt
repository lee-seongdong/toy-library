package toy.lsd.library.member.adapter.notification

import org.springframework.stereotype.Component
import toy.lsd.library.member.domain.port.NotificationPort

@Component
class EmailNotificationAdapter : NotificationPort {

    override fun sendWelcomeEmail(email: String, name: String) {
        println("📧 Welcome email sent to $email for $name")
    }

    override fun sendSuspensionNotice(email: String, name: String) {
        println("📧 Suspension notice sent to $email for $name")
    }

    override fun sendActivationNotice(email: String, name: String) {
        println("📧 Activation notice sent to $email for $name")
    }
}
