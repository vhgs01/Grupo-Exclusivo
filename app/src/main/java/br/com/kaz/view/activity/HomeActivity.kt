package br.com.kaz.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.kaz.R
import br.com.kaz.contract.HomeContract
import br.com.kaz.presenter.HomePresenter
import br.com.kaz.util.LottieAnimation
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private val presenter: HomePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setListeners()
    }

    override fun setListeners() {
        setCardViewModulesListener()
        setLogoutButtonListener()
    }

    override fun handleAnimation(startAnimation: Boolean) {
        if (startAnimation) {
            homeLogout.text = ""

            LottieAnimation.startAnimation(homeLottieAnimation)
        } else {
            homeLogout.text = getString(R.string.modulesLogout)

            LottieAnimation.cancelAnimation(homeLottieAnimation)
        }
    }

    override fun showSingOutUserError() {
        Toast.makeText(this, getString(R.string.modulesSingOutUserErrorText), Toast.LENGTH_LONG)
            .show()
    }

    override fun redirectToLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun setLogoutButtonListener() {
        homeLogout!!.setOnClickListener {
            handleAnimation(true)
            presenter.singOutUser()
        }
    }

    private fun setCardViewModulesListener() {
        cardViewModules.setOnClickListener {
            startActivity(Intent(applicationContext, ModulesActivity::class.java))
        }
    }
}