package com.antoniocitty.grupoexclusivo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antoniocitty.grupoexclusivo.databinding.ChecklistItemBinding
import com.antoniocitty.grupoexclusivo.model.courses.Checklist
import com.antoniocitty.grupoexclusivo.model.courses.CourseGE
import com.antoniocitty.grupoexclusivo.util.HandleAdapters.handleLockUnlockCourseGE
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.convertCourseGEToJson
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.overrideCourseGEJson
import com.antoniocitty.grupoexclusivo.util.SharedPreferences.saveCompletedStateOnSharedPrefs
import com.antoniocitty.grupoexclusivo.util.YouTubePlayer.initializeYouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import java.lang.Exception

class ChecklistAdapter(
    private val course: CourseGE,
    private val context: Context,
    private val modulePosition: Int,
    private val stepPosition: Int,
    private val checklistYouTubePlayerView: YouTubePlayerView
) : RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {

    class ViewHolder(binding: ChecklistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var checklistItemTitle: TextView? = binding.checklistItemTitle
        var checklistBox: CheckBox? = binding.checklistBox
    }

    override fun getItemCount(): Int {
        return try {
            course.moduleGE[modulePosition].steps[stepPosition].checklist.size
        } catch (e: Exception) {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChecklistItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checklist = course.moduleGE[modulePosition].steps[stepPosition].checklist[position]
        val moduleVideoUrl = course.moduleGE[modulePosition].steps[stepPosition].youtubeVideoId

        setClickListener(holder, position)
        configureModuleView(holder, moduleVideoUrl, checklist)
    }

    private fun setClickListener(it: ViewHolder, position: Int) {
        it.checklistBox?.setOnClickListener {
            course.moduleGE[modulePosition].steps[stepPosition].checklist[position].completed =
                (it as CompoundButton).isChecked

            val course = handleLockUnlockCourseGE(course)
            overrideCourseGEJson(context, convertCourseGEToJson(course))

            if (course.moduleGE.last().completed == true) {
                saveCompletedStateOnSharedPrefs(context, true)
            } else {
                saveCompletedStateOnSharedPrefs(context, false)
            }
        }
    }

    private fun configureModuleView(it: ViewHolder, moduleVideoUrl: String, checklist: Checklist) {
        initializeYouTubePlayer(checklistYouTubePlayerView, context, moduleVideoUrl)

        it.checklistItemTitle?.text = checklist.title
        checklist.completed?.let { completed ->
            it.checklistBox?.isChecked = completed
        }
    }

}