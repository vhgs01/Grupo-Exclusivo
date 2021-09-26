package com.antoniocitty.grupoexclusivo.util

import android.content.Context

object SharedPreferences {

    const val SHARED_PREFERENCES_NAME = "COURSE_SHARED_PREF"
    const val FIELD_COURSE_COMPLETED = "COURSE_COMPLETED"

    fun saveCompletedStateOnSharedPrefs(context: Context, completedCourse: Boolean) {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editorSharedPref = sharedPref.edit()

        editorSharedPref.putBoolean(FIELD_COURSE_COMPLETED, completedCourse)
        editorSharedPref.apply()
    }

}