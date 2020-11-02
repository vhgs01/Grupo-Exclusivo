package br.com.kaz.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.kaz.R

class ChecklistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val modulePosition = intent.getIntExtra("modulePosition", 99)
        val stepPosition = intent.getIntExtra("stepPosition", 99)

    }
}