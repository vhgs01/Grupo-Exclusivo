package br.com.kaz.presenter

import br.com.kaz.contract.RegisterContract
import br.com.kaz.firebase.FirebaseIntegration.createUser
import br.com.kaz.util.FieldsValidations.isValidEmail
import br.com.kaz.util.FieldsValidations.isValidPassword

class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun handleRegisterUser(email: String, pass: String) {
        if (isValidEmail(email) && isValidPassword(pass)) {
            registerUser(email, pass)
        } else {
            view.showInvalidFieldsToast()
        }
    }

    override fun registerUser(email: String, pass: String) {
        createUser(
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