package br.com.kaz.contract

import android.content.Context
import br.com.kaz.view.RegisterActivity

interface RegisterContract {
    interface View {
        fun redirectToModulesActivity()
        fun setRegisterButtonListener()
    }

    interface Presenter {
        fun initPresenter(
            registerActivity: RegisterActivity,
            context: Context
        )

        fun isValidEmail(email: String): Boolean
        fun isValidPassword(pass: String): Boolean
        fun registerUser(email: String, pass: String)
        fun redirectToModuleActivity()
    }
}