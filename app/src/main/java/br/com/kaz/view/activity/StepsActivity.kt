package br.com.kaz.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.kaz.R

class StepsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        val modulePosition = intent.getIntExtra("modulePosition", 99)

        if (modulePosition != 99) {
            print("yes")
        }
    }
}