package com.antoniocitty.grupoexclusivo.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.contract.ChecklistContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityChecklistBinding
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseGE
import com.antoniocitty.grupoexclusivo.view.adapter.ChecklistAdapter
import com.google.android.youtube.player.YouTubeBaseActivity

class ChecklistActivity : YouTubeBaseActivity(), ChecklistContract.View {

    private lateinit var binding: ActivityChecklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        val stepPosition = intent.getIntExtra("stepPosition", 99)

        setListeners()
        configureAdapter(modulePosition, stepPosition)
    }

    override fun configureAdapter(modulePosition: Int, stepPosition: Int) = with(binding) {
        val recyclerView = checklistList
        val course = getCourseGE(applicationContext)

        recyclerView.adapter = course?.let {
            ChecklistAdapter(
                it,
                applicationContext,
                modulePosition,
                stepPosition,
                checklistYouTubePlayerView
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        checklistJourney.text = course?.let {
            it.moduleGE[modulePosition].steps[stepPosition].title
        }
    }

    override fun setListeners() = with(binding) {
        checklistBack.setOnClickListener {
            finish()
        }
    }
}