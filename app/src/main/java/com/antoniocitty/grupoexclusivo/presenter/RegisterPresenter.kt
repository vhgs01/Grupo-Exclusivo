package com.antoniocitty.grupoexclusivo.presenter

import com.antoniocitty.grupoexclusivo.contract.RegisterContract
import com.antoniocitty.grupoexclusivo.domain.EntityErrorResult
import com.antoniocitty.grupoexclusivo.domain.EntityResult
import com.antoniocitty.grupoexclusivo.firebase.FirebaseAuthIntegration
import com.antoniocitty.grupoexclusivo.util.FieldsValidations.isValidEmail
import com.antoniocitty.grupoexclusivo.util.FieldsValidations.isValidPassword

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : RegisterContract.Presenter {

    override fun handleRegisterUser(email: String, pass: String, passConfirmation: String) {
        if (isValidEmail(email) && isValidPassword(pass, passConfirmation)) {
            registerUser(email, pass)
        } else {
            view.handleAnimation(false)
            view.showInvalidFieldsToast()
        }
    }

    override fun registerUser(email: String, pass: String) {
        firebaseAuth.createUser(email, pass, ::onCreateUser)
    }

    private fun onCreateUser(result: EntityResult<Unit>) {
        when (result) {
            is EntityResult.Success -> view.redirectToModulesActivity()
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