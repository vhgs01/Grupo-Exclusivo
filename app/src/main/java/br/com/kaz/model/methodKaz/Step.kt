package br.com.kaz.model.methodKaz

data class Step(
    val title: String,
    val description: String,
    var completed: Boolean,
    var checklist: List<Checklist>
)