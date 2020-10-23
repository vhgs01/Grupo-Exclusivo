package br.com.kaz.presenter

import br.com.kaz.contract.SplashContract
import br.com.kaz.firebase.FirebaseIntegration

class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {

    override fun verifyUserIsLogged() {
        FirebaseIntegration.initializeFirebase()
        val user = FirebaseIntegration.getCurrentlyUserSignedIn()

        if (user == null) {
            view.redirectToRegisterActivity()
        } else {
            view.redirectToModulesActivity()
        }
    }
}