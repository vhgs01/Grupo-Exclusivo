package br.com.kaz.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kaz.R
import br.com.kaz.model.courses.CourseKaz
import br.com.kaz.model.methodKaz.MethodKaz
import br.com.kaz.view.adapter.CourseKazAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_modules.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class ModulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modules)

        val recyclerView = modulesList
        recyclerView.adapter = readJson()?.let { CourseKazAdapter(it, this) }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun readJson(): CourseKaz? {
        val rawResource = resources.openRawResource(R.raw.method_kaz)
        val jsonInText = readTextFile(rawResource)

        return Gson().fromJson(jsonInText, CourseKaz::class.java)
    }

    private fun readTextFile(inputStream: InputStream): String? {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
        }
        return outputStream.toString()
    }


}