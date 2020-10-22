package br.com.kaz.presenter

import android.widget.Toast
import br.com.kaz.R
import br.com.kaz.contract.RegisterContract
import br.com.kaz.firebase.FirebaseIntegration.createUser

class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    private val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    override fun isValidEmail(email: String): Boolean {
        return if (emailPattern.toRegex().matches(email)) {
            true
        } else {
            Toast.makeText(
                view.getViewContext(),
                R.string.registerInvalidEmail,
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

    override fun isValidPassword(pass: String): Boolean {
        return if (pass.isNotBlank() && pass.isNotEmpty() && pass.length >= 6) {
            true
        } else {
            Toast.makeText(
                view.getViewContext(),
                R.string.registerInvalidPassword,
                Toast.LENGTH_LONG
            ).show()
            false
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