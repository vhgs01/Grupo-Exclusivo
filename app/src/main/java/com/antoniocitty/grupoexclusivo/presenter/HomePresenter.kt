package com.antoniocitty.grupoexclusivo.presenter

import com.antoniocitty.grupoexclusivo.contract.HomeContract
import com.antoniocitty.grupoexclusivo.firebase.FirebaseAuthIntegration

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