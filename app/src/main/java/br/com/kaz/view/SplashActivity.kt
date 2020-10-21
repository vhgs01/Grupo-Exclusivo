package br.com.kaz.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.kaz.R
import br.com.kaz.firebase.FirebaseIntegration.getCurrentlyUserSignedIn
import br.com.kaz.firebase.FirebaseIntegration.initializeFirebase
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimation()
    }

    private fun startAnimation() {
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
                verifyUserIsLogged()
            }
        })

        ivLogoSplash.clearAnimation()
        ivLogoSplash.startAnimation(animation)
    }

    private fun verifyUserIsLogged() {
        initializeFirebase()
        val user = getCurrentlyUserSignedIn()

        if (user == null) {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }

}