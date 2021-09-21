package br.com.kaz.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kaz.R
import br.com.kaz.contract.ModuleContract
import br.com.kaz.util.JsonManipulation.getCourseKaz
import br.com.kaz.view.adapter.CourseKazAdapter
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