package com.antoniocitty.grupoexclusivo.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.LoginContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityLoginBinding
import com.antoniocitty.grupoexclusivo.presenter.LoginPresenter
import com.antoniocitty.grupoexclusivo.util.LottieAnimation.cancelAnimation
import com.antoniocitty.grupoexclusivo.util.LottieAnimation.startAnimation
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding

    private val presenter: LoginPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    override fun setListeners() {
        setRegisterButtonListener()
        setLoginButtonListener()
    }

    override fun getViewContext(): Context {
        return applicationContext
    }

    override fun setLoginButtonListener() = with(binding) {
        loginButton.let { btn ->
            btn.setOnClickListener {
                val email = loginEmailAddress?.text.toString()
                val pass = loginPassword?.text.toString()

                handleAnimation(true)
                presenter.handleLoginUser(email, pass)
            }
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

    override fun redirectToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }

    override fun handleAnimation(startAnimation: Boolean) = with(binding) {
        if (startAnimation) {
            loginButton.text = ""

            startAnimation(loginLottieAnimation)
        } else {
            loginButton.text = getString(R.string.loginButton)

            cancelAnimation(loginLottieAnimation)
        }
    }

    override fun setRegisterButtonListener() = with(binding) {
        loginButtonRegister.let { btn ->
            btn.setOnClickListener {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
                finishAffinity()
            }
        }
    }
}