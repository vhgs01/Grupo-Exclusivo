package br.com.kaz.presenter

import br.com.kaz.contract.SplashContract
import br.com.kaz.firebase.FirebaseAuthIntegration

class SplashPresenter(
    private val view: SplashContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : SplashContract.Presenter {

    override fun verifyUserIsLogged() {
        val user = firebaseAuth.getCurrentlyUserSignedIn()

        if (user == null) {
            view.redirectToLoginActivity()
        } else {
            view.redirectToModulesActivity()
        }
    }
}