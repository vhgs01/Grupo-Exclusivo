package br.com.kaz.util

object FieldsValidations {

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        return emailPattern.toRegex().matches(email)
    }

    fun isValidPassword(pass: String): Boolean {
        return pass.isNotBlank() && pass.isNotEmpty() && pass.length >= 6
    }

}