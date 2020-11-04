package br.com.kaz.presenter

import br.com.kaz.contract.ModuleContract
import br.com.kaz.firebase.FirebaseAuthIntegration

class ModulePresenter(
    private val view: ModuleContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : ModuleContract.Presenter {

    override fun singOutUser() {
        try {
            firebaseAuth.singOutUser()
            view.redirectToLoginScreen()
        } catch (e: NullPointerException) {
            view.showSingOutUserError()
        }
    }
}