package br.com.kaz.contract

import android.content.Context

interface LoginContract {
    interface View {
        fun setListeners()
        fun setLoginButtonListener()
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
        fun handleLoginUser(email: String, pass: String)
        fun loginWithUser(email: String, pass: String)
    }
}