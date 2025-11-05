package toy.lsd.library.member.domain

@JvmInline
value class Email(val value: String) {
    init {
        require(isValid(value)) { "Invalid email format: $value" }
    }

    private fun isValid(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}