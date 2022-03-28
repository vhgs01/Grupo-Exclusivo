package com.antoniocitty.grupoexclusivo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.model.courses.Checklist
import com.antoniocitty.grupoexclusivo.model.courses.CourseKaz
import com.antoniocitty.grupoexclusivo.util.HandleAdapters.handleLockUnlockCourseKaz
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.convertCourseKazToJson
import com.antoniocitty.grupoexclusivo.util.JsonManipulation.overrideCourseKazJson
import com.antoniocitty.grupoexclusivo.util.SharedPreferences.saveCompletedStateOnSharedPrefs
import com.antoniocitty.grupoexclusivo.util.YouTubePlayer.initializeYouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.checklist_item.view.*

class ChecklistAdapter(
    private val course: CourseKaz,
    private val context: Context,
    private val modulePosition: Int,
    private val stepPosition: Int,
    private val checklistYouTubePlayerView: YouTubePlayerView
) :
    RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checklistItemTitle: TextView? = itemView.checklistItemTitle
        var checklistBox: CheckBox? = itemView.checklistBox
    }

    override fun getItemCount(): Int {
        return course.moduleKaz[modulePosition].steps[stepPosition].checklist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.checklist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checklist = course.moduleKaz[modulePosition].steps[stepPosition].checklist[position]
        val moduleVideoUrl = course.moduleKaz[modulePosition].steps[stepPosition].youtubeVideoId

        setClickListener(holder, position)
        configureModuleView(holder, moduleVideoUrl, checklist)
    }

    private fun setClickListener(it: ViewHolder, position: Int) {
        it.checklistBox?.setOnClickListener {
            course.moduleKaz[modulePosition].steps[stepPosition].checklist[position].completed =
                (it as CompoundButton).isChecked

            val course = handleLockUnlockCourseKaz(course)
            overrideCourseKazJson(context, convertCourseKazToJson(course))

            if (course.moduleKaz.last().completed == true) {
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