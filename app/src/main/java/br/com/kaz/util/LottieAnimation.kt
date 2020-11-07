package br.com.kaz.util

import android.view.View
import com.airbnb.lottie.LottieAnimationView

object LottieAnimation {

    fun startAnimation(view: LottieAnimationView) {
        view.visibility = View.VISIBLE
        view.playAnimation()
    }

    fun cancelAnimation(view: LottieAnimationView) {
        view.visibility = View.GONE
        view.cancelAnimation()
    }

}