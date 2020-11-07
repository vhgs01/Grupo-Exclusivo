package br.com.kaz.presenter

import br.com.kaz.contract.RegisterContract
import br.com.kaz.domain.EntityErrorResult
import br.com.kaz.domain.EntityResult
import br.com.kaz.firebase.FirebaseAuthIntegration
import br.com.kaz.util.FieldsValidations.isValidEmail
import br.com.kaz.util.FieldsValidations.isValidPassword

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val firebaseAuth: FirebaseAuthIntegration
) : RegisterContract.Presenter {

    override fun handleRegisterUser(email: String, pass: String, passConfirmation: String) {
        if (isValidEmail(email) && isValidPassword(pass, passConfirmation)) {
            registerUser(email, pass)
        } else {
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