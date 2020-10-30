package br.com.kaz.model.courses

data class Course(
    val completed: Boolean,
    val description: String,
    val steps: List<Step>,
    val title: String
)