package br.com.kaz.presenter

import br.com.kaz.contract.LoginContract
import br.com.kaz.domain.EntityErrorResult
import br.com.kaz.domain.EntityResult
import br.com.kaz.firebase.FirebaseAuthIntegration
import br.com.kaz.util.FieldsValidations.isValidEmail
import br.com.kaz.util.FieldsValidations.isValidPassword

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