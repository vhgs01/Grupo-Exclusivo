package br.com.kaz.presenter

import br.com.kaz.contract.SplashContract
import br.com.kaz.firebase.FirebaseIntegration

class SplashPresenter(
    private val view: SplashContract.View,
    private val firebase: FirebaseIntegration
) : SplashContract.Presenter {

    override fun verifyUserIsLogged() {
        val user = firebase.getCurrentlyUserSignedIn()

        if (user == null) {
            view.redirectToLoginActivity()
        } else {
            view.redirectToModulesActivity()
        }
    }
}