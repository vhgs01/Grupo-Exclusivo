package br.com.kaz.injection

import br.com.kaz.contract.*
import br.com.kaz.firebase.FirebaseAuthIntegration
import br.com.kaz.presenter.*
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val mainModule = module {
    single { (view : SplashContract.View) -> SplashPresenter(view, get()) }
    single { (view : RegisterContract.View) -> RegisterPresenter(view, get()) }
    single { (view : LoginContract.View) -> LoginPresenter(view, get()) }
    single { (view : HomeContract.View) -> HomePresenter(view, get()) }
}

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseAuthIntegration(get()) }
}