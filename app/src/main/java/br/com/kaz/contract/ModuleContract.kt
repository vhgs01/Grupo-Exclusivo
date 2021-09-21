package br.com.kaz.contract

interface ModuleContract {
    interface View {
        fun configureAdapter()
        fun setListeners()
    }

    interface  Presenter {
        fun singOutUser()
    }
}