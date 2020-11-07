package br.com.kaz.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kaz.R
import br.com.kaz.contract.LoginContract
import br.com.kaz.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter: LoginPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListeners()
    }

    override fun setListeners() {
        setRegisterButtonListener()
        setLoginButtonListener()
    }

    override fun getViewContext(): Context {
        return applicationContext
    }

    override fun setLoginButtonListener() {
        loginButton!!.setOnClickListener {
            val email = loginEmailAddress?.text.toString()
            val pass = loginPassword?.text.toString()

            handleAnimation(true)
            presenter.handleLoginUser(email, pass)
        }
    }

    override fun showInvalidFieldsToast() {
        Toast.makeText(
            applicationContext,
            R.string.registerInvalidfields,
            Toast.LENGTH_LONG
        ).show()
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

    override fun redirectToModulesActivity() {
        startActivity(Intent(this, ModulesActivity::class.java))
        finishAffinity()
    }

    override fun handleAnimation(startAnimation: Boolean) {
        if (startAnimation) {
            loginButton.text = ""

            startLottieAnimation()
        } else {
            loginButton.text = getString(R.string.loginButton)

            cancelLottieAnimation()
        }
    }

    override fun setRegisterButtonListener() {
        loginButtonRegister!!.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
            finishAffinity()
        }
    }

    private fun startLottieAnimation() {
        loginLottieAnimation.visibility = View.VISIBLE
        loginLottieAnimation.playAnimation()
    }

    private fun cancelLottieAnimation() {
        loginLottieAnimation.visibility = View.GONE
        loginLottieAnimation.cancelAnimation()
    }
}