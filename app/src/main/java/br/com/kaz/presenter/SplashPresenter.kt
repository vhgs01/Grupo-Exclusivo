package br.com.kaz.presenter

import android.content.Context
import br.com.kaz.contract.SplashContract
import br.com.kaz.firebase.FirebaseIntegration
import br.com.kaz.view.SplashActivity

class SplashPresenter : SplashContract.Presenter {

    private var context: Context? = null
    private var splashActivity: SplashActivity? = null

    override fun initPresenter(splashActivity: SplashActivity, context: Context) {
        this.context = context
        this.splashActivity = splashActivity
    }

    override fun verifyUserIsLogged() {
        FirebaseIntegration.initializeFirebase()
        val user = FirebaseIntegration.getCurrentlyUserSignedIn()

        if (user == null) {
            redirectToRegisterActivity()
        } else {
            redirectToModulesActivity()
        }
    }

    override fun redirectToRegisterActivity() {
        splashActivity!!.redirectToRegisterActivity()
    }

    override fun redirectToModulesActivity() {
        splashActivity!!.redirectToModulesActivity()
    }
}