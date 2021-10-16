package com.antoniocitty.grupoexclusivo.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.SplashContract
import com.antoniocitty.grupoexclusivo.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val presenter: SplashPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimation()
    }

    override fun startAnimation() {
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_animation)

        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                splashKaz.visibility = View.VISIBLE
                splashFrom.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {
                //do nothing
            }

            override fun onAnimationEnd(animation: Animation?) {
                presenter.verifyUserIsLogged()
            }
        })

        ivLogoSplash.clearAnimation()
        ivLogoSplash.startAnimation(animation)
    }

    override fun redirectToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    override fun redirectToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }

}