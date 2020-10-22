package br.com.kaz.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kaz.R
import br.com.kaz.firebase.FirebaseIntegration
import br.com.kaz.firebase.FirebaseIntegration.createUser
import br.com.kaz.firebase.FirebaseIntegration.initializeFirebase
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setRegisterButtonListener()
    }

    private fun setRegisterButtonListener() {
        registerButton.setOnClickListener {
            if (isValidEmail() && isValidPassword()) {
                registerUser()
            }
        }
    }

    private fun isValidEmail(): Boolean {
        return if (emailPattern.toRegex().matches(registerEmailAddress.text.toString())) {
            true
        } else {
            Toast.makeText(
                this,
                getString(R.string.registerInvalidEmail),
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

    private fun isValidPassword(): Boolean {
        val pass = registerPassword.text.toString()

        return if (pass.isNotBlank() && pass.isNotEmpty() && pass.length >= 6) {
            true
        } else {
            Toast.makeText(
                this,
                getString(R.string.registerInvalidPassword),
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

    private fun registerUser() {
        createUser(
            this,
            registerEmailAddress.text.toString(),
            registerPassword.text.toString(),
            ::redirectToModulesActivity
        )
    }

    private fun redirectToModulesActivity() {
        startActivity(Intent(this, ModulesActivity::class.java))
    }

}