package br.com.kaz.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.kaz.R
import br.com.kaz.contract.RegisterContract
import br.com.kaz.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private var presenter: RegisterPresenter = RegisterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.initPresenter(this, this)

        setRegisterButtonListener()
    }

    override fun redirectToModulesActivity() {
        startActivity(Intent(this, ModulesActivity::class.java))
        finish()
    }

    override fun setRegisterButtonListener() {
        registerButton!!.setOnClickListener {
            val email = registerEmailAddress.text.toString()
            val pass = registerPassword.text.toString()

            if (presenter.isValidEmail(email) && presenter.isValidPassword(pass)) {
                presenter.registerUser(email, pass)
            }
        }
    }

}