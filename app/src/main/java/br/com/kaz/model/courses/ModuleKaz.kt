package br.com.kaz.model.courses

data class ModuleKaz(
    var completed: Boolean?,
    val description: String,
    val steps: List<Step>,
    val title: String
)