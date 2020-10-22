package br.com.kaz.presenter

import android.content.Context
import android.widget.Toast
import br.com.kaz.R
import br.com.kaz.contract.RegisterContract
import br.com.kaz.firebase.FirebaseIntegration.createUser
import br.com.kaz.view.RegisterActivity

class RegisterPresenter : RegisterContract.Presenter {

    private val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    private var context: Context? = null
    private var registerActivity: RegisterActivity? = null

    override fun initPresenter(registerActivity: RegisterActivity, context: Context) {
        this.registerActivity = registerActivity
        this.context = context
    }

    override fun isValidEmail(email: String): Boolean {
        return if (emailPattern.toRegex().matches(email)) {
            true
        } else {
            Toast.makeText(
                context,
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
                this.context,
                R.string.registerInvalidPassword,
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

    override fun registerUser(email: String, pass: String) {
        createUser(
            this.context!!,
            email,
            pass,
            ::redirectToModuleActivity
        )
    }

    override fun redirectToModuleActivity() {
        registerActivity!!.redirectToModulesActivity()
    }
}