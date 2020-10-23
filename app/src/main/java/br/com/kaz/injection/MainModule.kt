package br.com.kaz.injection

import br.com.kaz.contract.LoginContract
import br.com.kaz.contract.RegisterContract
import br.com.kaz.contract.SplashContract
import br.com.kaz.presenter.LoginPresenter
import br.com.kaz.presenter.RegisterPresenter
import br.com.kaz.presenter.SplashPresenter
import org.koin.dsl.module

val mainModule = module {
    single { (view : SplashContract.View) -> SplashPresenter(view) }
    single { (view : RegisterContract.View) -> RegisterPresenter(view) }
    single { (view : LoginContract.View) -> LoginPresenter(view) }
}