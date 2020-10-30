package br.com.kaz.model.methodKaz

data class Module(
    val title: String,
    val description: String,
    var completed: Boolean,
    var steps: List<Step>
)