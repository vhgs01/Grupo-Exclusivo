package com.antoniocitty.grupoexclusivo.contract

interface ModuleContract {
    interface View {
        fun configureAdapter()
        fun setListeners()
    }

    interface  Presenter {
        fun singOutUser()
    }
}