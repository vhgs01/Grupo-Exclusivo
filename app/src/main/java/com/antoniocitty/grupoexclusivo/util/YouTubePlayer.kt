package com.antoniocitty.grupoexclusivo.util

import android.content.Context
import android.widget.Toast
import com.antoniocitty.grupoexclusivo.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

object YouTubePlayer {

    fun initializeYouTubePlayer(view: YouTubePlayerView, context: Context, videoId: String) {
        val first = context.getString(R.string.ytFirstStep)
        val second = context.getString(R.string.ytSecondStep)
        val third = context.getString(R.string.ytThirdStep)
        val ytJoinedStep = context.getString(R.string.ytJoinedStep, first, second, third)

        view.initialize(
            ytJoinedStep,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(videoId)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(context, "Vídeo indisponível", Toast.LENGTH_LONG).show()
                }
            })
    }

}