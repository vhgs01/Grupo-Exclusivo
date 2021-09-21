package br.com.kaz.contract

interface HomeContract {
    interface View {
        fun setListeners()
        fun showSingOutUserError()
        fun redirectToLoginScreen()
        fun handleAnimation(startAnimation: Boolean)
    }

    interface Presenter {
        fun singOutUser()
    }
}