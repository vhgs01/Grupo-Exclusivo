package com.antoniocitty.grupoexclusivo.contract

interface StepContract {
    interface View {
        fun configureAdapter(modulePosition: Int)
        fun setListeners()
    }
}