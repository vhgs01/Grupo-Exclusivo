package br.com.kaz.presenter

import br.com.kaz.contract.ModuleContract
import br.com.kaz.firebase.FirebaseIntegration

class ModulePresenter(
    private val view: ModuleContract.View,
    private val firebase: FirebaseIntegration
) : ModuleContract.Presenter {

    override fun singOutUser() {
        try {
            firebase.singOutUser()
            view.redirectToLoginScreen()
        } catch (e: NullPointerException) {
            view.showSingOutUserError()
        }
    }
}