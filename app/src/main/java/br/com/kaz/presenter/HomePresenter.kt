package br.com.kaz.presenter

import br.com.kaz.contract.HomeContract
import br.com.kaz.firebase.FirebaseAuthIntegration

class HomePresenter(
    private val view: HomeContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : HomeContract.Presenter {

    override fun singOutUser() {
        try {
            firebaseAuth.singOutUser()
            view.redirectToLoginScreen()
        } catch (e: NullPointerException) {
            view.showSingOutUserError()
            view.handleAnimation(false)
        }
    }

}