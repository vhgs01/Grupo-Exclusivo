package br.com.kaz.contract

import android.content.Context

interface RegisterContract {
    interface View {
        fun setListeners()
        fun redirectToModulesActivity()
        fun getViewContext(): Context
        fun showInvalidFieldsToast()
        fun showInvalidUserError()
        fun showWeakPasswordError()
        fun showInvalidCredentialsError()
        fun showUserCollisionError()
        fun showOtherExceptionError()
    }

    interface Presenter {
        fun handleRegisterUser(email: String, pass: String)
        fun registerUser(email: String, pass: String)
    }
}