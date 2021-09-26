package com.antoniocitty.grupoexclusivo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.StepContract
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseKaz
import com.antoniocitty.grupoexclusivo.view.adapter.StepsAdapter
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity(), StepContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        setListeners()
    }

    override fun onResume() {
        super.onResume()

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        configureAdapter(modulePosition)
    }

    override fun setListeners() {
        stepBack.setOnClickListener {
            finish()
        }
    }

    override fun configureAdapter(modulePosition: Int) {
        val recyclerView = stepsList
        val course = getCourseKaz(applicationContext)

        recyclerView.adapter = course?.let { StepsAdapter(it, this, modulePosition) }

        recyclerView.layoutManager = LinearLayoutManager(this)

        stepsJourney.text = course?.let {
            it.moduleKaz[modulePosition].title.split("- ")[1]
        }
    }


}