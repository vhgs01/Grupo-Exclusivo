package com.antoniocitty.grupoexclusivo.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.HomeContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityHomeBinding
import com.antoniocitty.grupoexclusivo.presenter.HomePresenter
import com.antoniocitty.grupoexclusivo.util.LottieAnimation
import com.antoniocitty.grupoexclusivo.util.SharedPreferences.FIELD_COURSE_COMPLETED
import com.antoniocitty.grupoexclusivo.util.SharedPreferences.SHARED_PREFERENCES_NAME
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.net.URLEncoder
import java.util.*


class HomeActivity : AppCompatActivity(), HomeContract.View {

    companion object {
        private const val WEBVIEW_TO_OPEN = "webview_to_open"
        private const val HANDOUT = "handout"
        private const val ONE_MINUTE = 60000L
        private const val PURCHASE = "purchase"
        private const val LOGGED_AREA = "logged_area"
        private const val WHATS_PHONE = "5528999511880"
        private const val WHATS_MESSAGE =
            "Olá Antônio! Estava usando o aplicativo e fiquei com uma dúvida, poderia me ajudar?"
    }

    private lateinit var binding: ActivityHomeBinding

    private val presenter: HomePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    override fun onResume() = with(binding) {
        super.onResume()
        val prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val courseIsCompleted = prefs.getBoolean(FIELD_COURSE_COMPLETED, false)

        if (courseIsCompleted) {
            cardPurchase.visibility = View.VISIBLE
            // In study if will be removed permanently
            // cardLoggedArea.visibility = View.VISIBLE
        } else {
            cardPurchase.visibility = View.GONE
            // In study if will be removed permanently
            // cardLoggedArea.visibility = View.GONE
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { favWhats.visibility = View.VISIBLE }
            }

        }, ONE_MINUTE)
    }

    override fun setListeners() {
        setCardViewModulesListener()
        setLogoutButtonListener()
        setHandoutButtonListener()
        setPurchaseButtonListener()
        setLoggedAreaButtonListener()
        setFabListener()
    }

    override fun handleAnimation(startAnimation: Boolean) = with(binding) {
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

    private fun setLogoutButtonListener() = with(binding) {
        homeLogout.let { btn ->
            btn.setOnClickListener {
                handleAnimation(true)
                presenter.singOutUser()
            }
        }
    }

    private fun setCardViewModulesListener() = with(binding) {
        cardViewModules.setOnClickListener {
            startActivity(Intent(applicationContext, ModulesActivity::class.java))
        }
    }

    private fun setHandoutButtonListener() = with(binding) {
        cardViewHandout.setOnClickListener {
            val intent = Intent(applicationContext, WebViewActivity::class.java)

            intent.putExtra(WEBVIEW_TO_OPEN, HANDOUT)
            startActivity(intent)
        }
    }

    private fun setPurchaseButtonListener() = with(binding) {
        cardPurchase.setOnClickListener {
            val intent = Intent(applicationContext, WebViewActivity::class.java)

            intent.putExtra(WEBVIEW_TO_OPEN, PURCHASE)
            startActivity(intent)
        }
    }

    private fun setLoggedAreaButtonListener() = with(binding) {
        cardLoggedArea.setOnClickListener {
            val intent = Intent(applicationContext, WebViewActivity::class.java)

            intent.putExtra(WEBVIEW_TO_OPEN, LOGGED_AREA)
            startActivity(intent)
        }
    }

    private fun setFabListener() = with(binding) {
        favWhats.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)

            try {
                val url =
                    "https://api.whatsapp.com/send?phone=$WHATS_PHONE&text=" + URLEncoder.encode(
                        WHATS_MESSAGE,
                        "UTF-8"
                    )

                intent.setPackage("com.whatsapp")
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}