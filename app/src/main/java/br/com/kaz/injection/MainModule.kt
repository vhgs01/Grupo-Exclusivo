package br.com.kaz.injection

import br.com.kaz.contract.LoginContract
import br.com.kaz.contract.RegisterContract
import br.com.kaz.contract.SplashContract
import br.com.kaz.firebase.FirebaseIntegration
import br.com.kaz.presenter.LoginPresenter
import br.com.kaz.presenter.RegisterPresenter
import br.com.kaz.presenter.SplashPresenter
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val mainModule = module {
    single { (view : SplashContract.View) -> SplashPresenter(view, get()) }
    single { (view : RegisterContract.View) -> RegisterPresenter(view, get()) }
    single { (view : LoginContract.View) -> LoginPresenter(view, get()) }
}

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseIntegration(get()) }
}