package br.com.kaz.util

import android.content.Context
import br.com.kaz.R
import br.com.kaz.model.courses.CourseKaz
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.*
import java.lang.Exception

object JsonManipulation {

    fun getCourseKaz(context: Context): CourseKaz? {
        val file = File(context.filesDir, "method_kaz.json")
        return try {
            val jsonInText = readCourseKazJsonFromFiles(file)
            Gson().fromJson(jsonInText, CourseKaz::class.java)
        } catch (e: Exception) {
            readCourseKazJsonFromRaw(context)
            val jsonInText = readCourseKazJsonFromFiles(file)
            Gson().fromJson(jsonInText, CourseKaz::class.java)
        }
    }

    fun convertCourseKazToJson(courseKaz: CourseKaz): String {
        return GsonBuilder().serializeNulls().create().toJson(courseKaz)
    }

    fun overrideCourseKazJson(context: Context, str: String) {
        val file = File(context.filesDir, "method_kaz.json")
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.write(str)
        bufferedWriter.close()
    }

    private fun readCourseKazJsonFromFiles(file: File): String {
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()

        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = bufferedReader.readLine()
        }

        val jsonInText = stringBuilder.toString()
        bufferedReader.close()
        return jsonInText
    }

    private fun readCourseKazJsonFromRaw(context: Context) {
        val rawResource = context.resources.openRawResource(R.raw.method_kaz)
        val jsonInText = readTextFile(rawResource)

        jsonInText?.let { overrideCourseKazJson(context, it) }
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