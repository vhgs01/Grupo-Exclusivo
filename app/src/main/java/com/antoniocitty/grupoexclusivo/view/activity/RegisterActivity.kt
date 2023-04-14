package com.antoniocitty.grupoexclusivo.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.RegisterContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityRegisterBinding
import com.antoniocitty.grupoexclusivo.presenter.RegisterPresenter
import com.antoniocitty.grupoexclusivo.util.LottieAnimation.startAnimation
import com.antoniocitty.grupoexclusivo.util.LottieAnimation.cancelAnimation
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var binding: ActivityRegisterBinding

    private val presenter: RegisterPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    override fun getViewContext(): Context {
        return applicationContext
    }

    override fun redirectToModulesActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
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

    override fun handleAnimation(startAnimation: Boolean) = with(binding) {
        if (startAnimation) {
            registerButton.text = ""

            startAnimation(registerLottieAnimation)
        } else {
            registerButton.text = getString(R.string.registerButton)

            cancelAnimation(registerLottieAnimation)
        }
    }

    override fun showInvalidUserError() {
        Toast.makeText(this, R.string.FirebaseAuthInvalidUserException, Toast.LENGTH_LONG).show()
    }

    override fun showWeakPasswordError() {
        Toast.makeText(this, R.string.FirebaseAuthWeakPasswordException, Toast.LENGTH_LONG).show()
    }

    override fun showInvalidCredentialsError() {
        Toast.makeText(this, R.string.FirebaseAuthInvalidCredentialsException, Toast.LENGTH_LONG)
            .show()
    }

    override fun showUserCollisionError() {
        Toast.makeText(this, R.string.FirebaseAuthUserCollisionException, Toast.LENGTH_LONG).show()
    }

    override fun showOtherExceptionError() {
        Toast.makeText(this, R.string.FirebaseAuthOtherException, Toast.LENGTH_LONG).show()
    }

    private fun setRegisterButtonListener() = with(binding) {
        registerButton.let { btn ->
            btn.setOnClickListener {
                val email = registerEmailAddress.text.toString()
                val pass = registerPassword.text.toString()
                val passConfirmation = registerPasswordConfirmation.text.toString()

                handleAnimation(true)
                presenter.handleRegisterUser(email, pass, passConfirmation)
            }
        }
    }

    private fun setLoginButtonListener() = with(binding) {
        registerButtonDoLogin.let { btn ->
            btn.setOnClickListener {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }

}