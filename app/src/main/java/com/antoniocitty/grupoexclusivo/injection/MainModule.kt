package com.antoniocitty.grupoexclusivo.injection

import com.antoniocitty.grupoexclusivo.contract.HomeContract
import com.antoniocitty.grupoexclusivo.firebase.FirebaseAuthIntegration
import com.antoniocitty.grupoexclusivo.contract.LoginContract
import com.antoniocitty.grupoexclusivo.contract.RegisterContract
import com.antoniocitty.grupoexclusivo.contract.SplashContract
import com.antoniocitty.grupoexclusivo.presenter.HomePresenter
import com.antoniocitty.grupoexclusivo.presenter.LoginPresenter
import com.antoniocitty.grupoexclusivo.presenter.RegisterPresenter
import com.antoniocitty.grupoexclusivo.presenter.SplashPresenter
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