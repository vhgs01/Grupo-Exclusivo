package br.com.kaz.contract

interface ChecklistContract {
    interface View {
        fun configureAdapter(modulePosition: Int, stepPosition: Int)
        fun setListeners()
    }

    interface Presenter {

    }
}