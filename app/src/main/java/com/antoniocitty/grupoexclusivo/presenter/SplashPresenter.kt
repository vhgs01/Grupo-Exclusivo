package com.antoniocitty.grupoexclusivo.presenter

import com.antoniocitty.grupoexclusivo.contract.SplashContract
import com.antoniocitty.grupoexclusivo.firebase.FirebaseAuthIntegration

class SplashPresenter(
    private val view: SplashContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : SplashContract.Presenter {

    override fun verifyUserIsLogged() {
        val user = firebaseAuth.getCurrentlyUserSignedIn()

        if (user == null) {
            view.redirectToLoginActivity()
        } else {
            view.redirectToHomeActivity()
        }
    }
}