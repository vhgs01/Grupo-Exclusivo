package com.antoniocitty.grupoexclusivo.contract

interface SplashContract {
    interface View {
        fun startAnimation()
        fun redirectToLoginActivity()
        fun redirectToHomeActivity()
    }

    interface Presenter {
        fun verifyUserIsLogged()
    }
}