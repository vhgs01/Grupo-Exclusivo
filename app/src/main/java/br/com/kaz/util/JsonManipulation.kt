package br.com.kaz.util

import android.content.res.Resources
import br.com.kaz.R
import br.com.kaz.model.courses.CourseKaz
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

object JsonManipulation {

    fun readCourseKazJson(resources: Resources): CourseKaz? {
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
            return ""
        }
        return outputStream.toString()
    }

}