package com.antoniocitty.grupoexclusivo.contract

interface ChecklistContract {
    interface View {
        fun configureAdapter(modulePosition: Int, stepPosition: Int)
        fun setListeners()
    }

    interface Presenter {

    }
}