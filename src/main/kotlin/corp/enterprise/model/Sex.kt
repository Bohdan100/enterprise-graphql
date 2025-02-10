package corp.enterprise.model

enum class Sex(val value: String) {
    MALE("Male"),
    FEMALE("Female");

    companion object {
        fun fromString(value: String?): Sex {
            return entries.find { it.value.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unexpected value: $value")
        }
    }
}