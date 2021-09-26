package br.com.kaz.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.kaz.R
import br.com.kaz.WebViewActivity
import br.com.kaz.contract.HomeContract
import br.com.kaz.presenter.HomePresenter
import br.com.kaz.util.LottieAnimation
import br.com.kaz.util.SharedPreferences.FIELD_COURSE_COMPLETED
import br.com.kaz.util.SharedPreferences.SHARED_PREFERENCES_NAME
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract.View {

    companion object {
        private const val WEBVIEW_TO_OPEN = "webview_to_open"
        private const val HANDOUT = "handout"
        private const val PURCHASE = "purchase"
    }

    private val presenter: HomePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val courseIsCompleted = prefs.getBoolean(FIELD_COURSE_COMPLETED, false)

        if (courseIsCompleted) cardPurchase.visibility = View.VISIBLE else cardPurchase.visibility = View.GONE
    }

    override fun setListeners() {
        setCardViewModulesListener()
        setLogoutButtonListener()
        setHandoutButtonListener()
        setPurchaseButtonListener()
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

    private fun setHandoutButtonListener() {
        cardViewHandout.setOnClickListener {
            val intent = Intent(applicationContext, WebViewActivity::class.java)

            intent.putExtra(WEBVIEW_TO_OPEN, HANDOUT)
            startActivity(intent)
        }
    }

    private fun setPurchaseButtonListener() {
        cardPurchase.setOnClickListener {
            val intent = Intent(applicationContext, WebViewActivity::class.java)

            intent.putExtra(WEBVIEW_TO_OPEN, PURCHASE)
            startActivity(intent)
        }
    }
}