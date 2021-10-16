package com.antoniocitty.grupoexclusivo.presenter

import com.antoniocitty.grupoexclusivo.contract.LoginContract
import com.antoniocitty.grupoexclusivo.domain.EntityErrorResult
import com.antoniocitty.grupoexclusivo.domain.EntityResult
import com.antoniocitty.grupoexclusivo.firebase.FirebaseAuthIntegration
import com.antoniocitty.grupoexclusivo.util.FieldsValidations.isValidEmail
import com.antoniocitty.grupoexclusivo.util.FieldsValidations.isValidPassword

class LoginPresenter(
    private val view: LoginContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : LoginContract.Presenter {

    override fun handleLoginUser(email: String, pass: String) {
        if (isValidEmail(email) && isValidPassword(pass, null)) {
            loginWithUser(email, pass)
        } else {
            view.handleAnimation(false)
            view.showInvalidFieldsToast()
        }
    }

    override fun loginWithUser(email: String, pass: String) {
        firebaseAuth.loginWithUser(email, pass, ::onLoginWithUser)
    }

    private fun onLoginWithUser(result: EntityResult<Unit>) {
        when (result) {
            is EntityResult.Success -> view.redirectToHomeActivity()
            is EntityResult.Error -> {
                view.handleAnimation(false)

                when (result.error) {
                    EntityErrorResult.InvalidUser -> view.showInvalidUserError()
                    EntityErrorResult.WeakPassword -> view.showWeakPasswordError()
                    EntityErrorResult.InvalidCredentials -> view.showInvalidCredentialsError()
                    EntityErrorResult.UserCollision -> view.showUserCollisionError()
                    EntityErrorResult.OtherException -> view.showOtherExceptionError()
                }
            }
        }
    }
}