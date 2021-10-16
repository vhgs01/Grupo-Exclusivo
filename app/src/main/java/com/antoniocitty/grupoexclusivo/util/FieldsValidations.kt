package com.antoniocitty.grupoexclusivo.util

object FieldsValidations {

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        return emailPattern.toRegex().matches(email)
    }

    fun isValidPassword(pass: String, passConfirmation: String?): Boolean {
        if (passConfirmation != null) {
            if (pass != passConfirmation) {
                return false
            }
        }

        return pass.isNotBlank() && pass.isNotEmpty() && pass.length >= 6
    }

}