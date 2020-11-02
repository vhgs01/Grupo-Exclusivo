package br.com.kaz.contract

interface SplashContract {
    interface View {
        fun startAnimation()
        fun redirectToLoginActivity()
        fun redirectToModulesActivity()
    }

    interface Presenter {
        fun verifyUserIsLogged()
    }
}