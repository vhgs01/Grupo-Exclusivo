package com.antoniocitty.grupoexclusivo.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.ChecklistContract
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseKaz
import com.antoniocitty.grupoexclusivo.view.adapter.ChecklistAdapter
import com.google.android.youtube.player.YouTubeBaseActivity
import kotlinx.android.synthetic.main.activity_checklist.*

class ChecklistActivity : YouTubeBaseActivity(), ChecklistContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        val stepPosition = intent.getIntExtra("stepPosition", 99)

        setListeners()
        configureAdapter(modulePosition, stepPosition)
    }

    override fun configureAdapter(modulePosition: Int, stepPosition: Int) {
        val recyclerView = checklistList
        val course = getCourseKaz(applicationContext)

        recyclerView.adapter = course?.let {
                    ChecklistAdapter(
                        it,
                        this,
                        modulePosition,
                        stepPosition,
                        checklistYouTubePlayerView
                    )
                }
        recyclerView.layoutManager = LinearLayoutManager(this)

        checklistJourney.text = course?.let {
            it.moduleKaz[modulePosition].steps[stepPosition].title
        }
    }

    override fun setListeners() {
        checklistBack.setOnClickListener {
            finish()
        }
    }
}