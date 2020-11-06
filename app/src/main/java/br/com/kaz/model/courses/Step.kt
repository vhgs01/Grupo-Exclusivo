package br.com.kaz.model.courses

data class Step(
    val checklist: List<Checklist>,
    var completed: Boolean?,
    val description: String,
    val youtubeVideoId: String,
    val title: String
)