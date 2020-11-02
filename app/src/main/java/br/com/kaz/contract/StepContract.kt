package br.com.kaz.contract

interface StepContract {
    interface View {
        fun configureAdapter(modulePosition: Int)
        fun setListeners()
    }
}