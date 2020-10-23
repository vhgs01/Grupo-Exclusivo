package br.com.kaz.contract

import android.content.Context

interface RegisterContract {
    interface View {
        fun setListeners()
        fun redirectToModulesActivity()
        fun getViewContext(): Context
    }

    interface Presenter {
        fun isValidEmail(email: String): Boolean
        fun isValidPassword(pass: String): Boolean
        fun registerUser(email: String, pass: String)
        fun redirectToModuleActivity()
    }
}