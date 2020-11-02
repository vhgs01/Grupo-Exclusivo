package br.com.kaz.model.courses

data class Step(
    val checklist: List<Checklist>,
    val completed: Boolean,
    val description: String,
    val title: String
)