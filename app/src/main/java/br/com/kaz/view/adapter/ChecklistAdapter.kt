package br.com.kaz.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import br.com.kaz.R
import br.com.kaz.model.courses.Checklist
import br.com.kaz.model.courses.CourseKaz
import br.com.kaz.util.JsonManipulation.convertCourseKazToJson
import br.com.kaz.util.JsonManipulation.overrideCourseKazJson
import br.com.kaz.util.YouTubePlayer.initializeYouTubePlayer
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
        var checklistItemTitle = itemView.checklistItemTitle!!
        var checklistItemDescription = itemView.checklistItemDescription!!
        var checklistBox = itemView.checklistBox!!
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
        it.checklistBox.setOnClickListener {
            course.moduleKaz[modulePosition].steps[stepPosition].checklist[position].completed =
                (it as CompoundButton).isChecked

            val courseKazJsonConverted = convertCourseKazToJson(course)
            overrideCourseKazJson(context, courseKazJsonConverted)
        }
    }

    private fun configureModuleView(it: ViewHolder, moduleVideoUrl: String, checklist: Checklist) {
        initializeYouTubePlayer(checklistYouTubePlayerView, context, moduleVideoUrl)

        it.checklistItemTitle.text = checklist.title
        it.checklistItemDescription.text = checklist.description
        it.checklistBox.isChecked = checklist.completed!!
    }

}