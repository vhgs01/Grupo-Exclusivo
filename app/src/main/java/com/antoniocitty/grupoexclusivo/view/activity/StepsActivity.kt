package com.antoniocitty.grupoexclusivo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.contract.StepContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityStepsBinding
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseGE
import com.antoniocitty.grupoexclusivo.view.adapter.StepsAdapter

class StepsActivity : AppCompatActivity(), StepContract.View {

    private lateinit var binding: ActivityStepsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    override fun onResume() {
        super.onResume()

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        configureAdapter(modulePosition)
    }

    override fun setListeners() = with(binding) {
        stepBack.setOnClickListener { finish() }
    }

    override fun configureAdapter(modulePosition: Int) = with(binding) {
        val recyclerView = stepsList
        val course = getCourseGE(applicationContext)

        recyclerView.adapter = course?.let { StepsAdapter(it, applicationContext, modulePosition) }

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        stepsJourney.text = course?.let {
            it.moduleGE[modulePosition].title.split("- ")[1]
        }
    }


}