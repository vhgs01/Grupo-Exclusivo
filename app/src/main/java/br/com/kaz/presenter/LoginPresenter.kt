package br.com.kaz.presenter

import br.com.kaz.contract.LoginContract
import br.com.kaz.firebase.FirebaseIntegration.loginWithUser
import br.com.kaz.util.FieldsValidations.isValidEmail
import br.com.kaz.util.FieldsValidations.isValidPassword

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun handleLoginUser(email: String, pass: String) {
        if (isValidEmail(email) && isValidPassword(pass)) {
            loginWithUser(email, pass)
        } else {
            view.showInvalidFieldsToast()
        }
    }

    override fun loginWithUser(email: String, pass: String) {
        loginWithUser(
            view.getViewContext(),
            email,
            pass,
            ::redirectToModuleActivity
        )
    }

    override fun redirectToModuleActivity() {
        view.redirectToModulesActivity()
    }
}