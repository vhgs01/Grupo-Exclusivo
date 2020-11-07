package br.com.kaz.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kaz.R
import br.com.kaz.contract.RegisterContract
import br.com.kaz.presenter.RegisterPresenter
import br.com.kaz.util.LottieAnimation.startAnimation
import br.com.kaz.util.LottieAnimation.cancelAnimation
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val presenter: RegisterPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setListeners()
    }

    override fun getViewContext(): Context {
        return applicationContext
    }

    override fun redirectToModulesActivity() {
        startActivity(Intent(this, ModulesActivity::class.java))
        finishAffinity()
    }

    override fun setListeners() {
        setRegisterButtonListener()
        setLoginButtonListener()
    }

    override fun showInvalidFieldsToast() {
        Toast.makeText(
            applicationContext,
            R.string.registerInvalidfields,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun handleAnimation(startAnimation: Boolean) {
        if (startAnimation) {
            registerButton.text = ""

            startAnimation(registerLottieAnimation)
        } else {
            registerButton.text = getString(R.string.registerButton)

            cancelAnimation(registerLottieAnimation)
        }
    }

    override fun showInvalidUserError() {
        Toast.makeText(this, R.string.FirebaseAuthInvalidUserException, Toast.LENGTH_LONG)
            .show()
    }

    override fun showWeakPasswordError() {
        Toast.makeText(this, R.string.FirebaseAuthWeakPasswordException, Toast.LENGTH_LONG)
            .show()
    }

    override fun showInvalidCredentialsError() {
        Toast.makeText(this, R.string.FirebaseAuthInvalidCredentialsException, Toast.LENGTH_LONG)
            .show()
    }

    override fun showUserCollisionError() {
        Toast.makeText(this, R.string.FirebaseAuthUserCollisionException, Toast.LENGTH_LONG)
            .show()
    }

    override fun showOtherExceptionError() {
        Toast.makeText(this, R.string.FirebaseAuthOtherException, Toast.LENGTH_LONG)
            .show()
    }

    private fun setRegisterButtonListener() {
        registerButton!!.setOnClickListener {
            val email = registerEmailAddress.text.toString()
            val pass = registerPassword.text.toString()
            val passConfirmation = registerPasswordConfirmation.text.toString()

            handleAnimation(true)
            presenter.handleRegisterUser(email, pass, passConfirmation)
        }
    }

    private fun setLoginButtonListener() {
        registerButtonDoLogin!!.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finishAffinity()
        }
    }

}