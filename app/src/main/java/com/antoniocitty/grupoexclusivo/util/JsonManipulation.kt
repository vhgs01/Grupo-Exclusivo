package com.antoniocitty.grupoexclusivo.util

import android.content.Context
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.model.courses.CourseGE
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.*
import java.lang.Exception

object JsonManipulation {

    fun getCourseGE(context: Context): CourseGE? {
        val file = File(context.filesDir, "method_grupo_exclusivo.json")
        return try {
            val jsonInText = readCourseGEJsonFromFiles(file)
            Gson().fromJson(jsonInText, CourseGE::class.java)
        } catch (e: Exception) {
            readCoursGEJsonFromRaw(context)
            val jsonInText = readCourseGEJsonFromFiles(file)
            Gson().fromJson(jsonInText, CourseGE::class.java)
        }
    }

    fun convertCourseGEToJson(courseGE: CourseGE): String {
        return GsonBuilder().serializeNulls().create().toJson(courseGE)
    }

    fun overrideCourseGEJson(context: Context, str: String) {
        val file = File(context.filesDir, "method_grupo_exclusivo.json")
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.write(str)
        bufferedWriter.close()
    }

    private fun readCourseGEJsonFromFiles(file: File): String {
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

    private fun readCoursGEJsonFromRaw(context: Context) {
        val rawResource = context.resources.openRawResource(R.raw.method_grupo_exclusivo)
        val jsonInText = readTextFile(rawResource)

        jsonInText?.let { overrideCourseGEJson(context, it) }
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