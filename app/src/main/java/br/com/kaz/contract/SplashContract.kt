package br.com.kaz.contract

import android.content.Context
import br.com.kaz.view.SplashActivity

interface SplashContract {
    interface View {
        fun startAnimation()
        fun redirectToRegisterActivity()
        fun redirectToModulesActivity()
    }

    interface Presenter {
        fun initPresenter(splashActivity: SplashActivity, context: Context)
        fun verifyUserIsLogged()
        fun redirectToRegisterActivity()
        fun redirectToModulesActivity()
    }
}