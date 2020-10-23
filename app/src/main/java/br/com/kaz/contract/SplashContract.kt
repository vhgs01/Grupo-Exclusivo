package br.com.kaz.contract

interface SplashContract {
    interface View {
        fun startAnimation()
        fun redirectToRegisterActivity()
        fun redirectToModulesActivity()
    }

    interface Presenter {
        fun verifyUserIsLogged()
    }
}