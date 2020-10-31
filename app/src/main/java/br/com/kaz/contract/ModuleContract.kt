package br.com.kaz.contract

interface ModuleContract {
    interface View {
        fun configureAdapter()
        fun setListeners()
        fun showSingOutUserError()
        fun redirectToLoginScreen()
    }

    interface  Presenter {
        fun singOutUser()
    }
}