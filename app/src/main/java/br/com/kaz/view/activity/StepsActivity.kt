package br.com.kaz.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kaz.R
import br.com.kaz.contract.StepContract
import br.com.kaz.util.JsonManipulation.getCourseKaz
import br.com.kaz.view.adapter.StepsAdapter
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
        recyclerView.adapter =
            getCourseKaz(applicationContext)?.let { StepsAdapter(it, this, modulePosition) }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}