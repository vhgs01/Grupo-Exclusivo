package com.antoniocitty.grupoexclusivo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.contract.ModuleContract
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.getCourseKaz
import com.antoniocitty.grupoexclusivo.view.adapter.CourseKazAdapter
import kotlinx.android.synthetic.main.activity_modules.*

class ModulesActivity : AppCompatActivity(), ModuleContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modules)

        setListeners()
    }

    override fun onResume() {
        super.onResume()

        configureAdapter()
    }

    override fun setListeners() {
        setModulesBackListener()
    }

    override fun configureAdapter() {
        val recyclerView = modulesList
        recyclerView.adapter =
            getCourseKaz(applicationContext)?.let { CourseKazAdapter(it, this) }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setModulesBackListener() {
        modulesBack.setOnClickListener {
            finish()
        }
    }

}