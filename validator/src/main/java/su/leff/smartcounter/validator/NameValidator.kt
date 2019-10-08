package su.leff.smartcounter.validator

object NameValidator {

    fun isValid(name: String): NameValidationResult {
        return when {
            name.isEmpty() -> NameValidationResult.SUCCESS
            name.length > 20 -> NameValidationResult.NAME_TOO_LONG
            name.length < 2 -> NameValidationResult.NAME_TOO_SHORT
            else -> NameValidationResult.SUCCESS
        }
    }
}

enum class NameValidationResult {
    NAME_TOO_LONG,
    NAME_TOO_SHORT,
    SUCCESS
}