package br.com.kaz.contract

interface ModuleContract {
    interface View {
        fun configureAdapter()
        fun setListeners()
        fun showSingOutUserError()
        fun redirectToLoginScreen()
        fun handleAnimation(startAnimation: Boolean)
    }

    interface  Presenter {
        fun singOutUser()
    }
}