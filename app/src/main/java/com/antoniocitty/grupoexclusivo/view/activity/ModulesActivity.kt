package com.antoniocitty.grupoexclusivo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.contract.ModuleContract
import com.antoniocitty.grupoexclusivo.databinding.ActivityModulesBinding
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseGE
import com.antoniocitty.grupoexclusivo.view.adapter.CourseGEAdapter

class ModulesActivity : AppCompatActivity(), ModuleContract.View {

    private lateinit var binding: ActivityModulesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    override fun onResume() {
        super.onResume()

        configureAdapter()
    }

    override fun setListeners() {
        setModulesBackListener()
    }

    override fun configureAdapter() = with(binding) {
        val recyclerView = modulesList
        recyclerView.adapter = getCourseGE(applicationContext)?.let {
            CourseGEAdapter(it, applicationContext)
        }
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun setModulesBackListener() = with(binding) {
        modulesBack.setOnClickListener { finish() }
    }

}