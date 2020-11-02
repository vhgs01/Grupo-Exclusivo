package br.com.kaz.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.kaz.R
import br.com.kaz.model.courses.Course
import br.com.kaz.model.courses.CourseKaz
import kotlinx.android.synthetic.main.module_item.view.*

class CourseKazAdapter(private val course: CourseKaz, private val context: Context) :
    RecyclerView.Adapter<CourseKazAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleTitle = itemView.moduleItemTitle!!
        var moduleItemDescription = itemView.moduleItemDescription!!
        var moduleItemWithOpacity = itemView.moduleItemWithOpacity!!
        var moduleItemImage = itemView.moduleItemImage!!
        var moduleItemStatusText = itemView.moduleItemStatusText!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.module_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return course.course.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = course.course[position]

        configureModuleView(holder, module)
    }

    private fun configureModuleView(it: ViewHolder, module: Course) {
        it.moduleTitle.text = module.title
        it.moduleItemDescription.text = module.description

        when (module.completed) {
            false -> {
                it.moduleItemWithOpacity.visibility = View.VISIBLE
                it.moduleItemImage.visibility = View.VISIBLE
                it.moduleItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_locked)
            }
            true -> {
                it.moduleItemWithOpacity.visibility = View.GONE
                it.moduleItemImage.visibility = View.VISIBLE
                it.moduleItemImage.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorGreenStatusButton))
                it.moduleItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_completed)
                it.moduleItemStatusText.text = context.getString(R.string.modulesCompletedText)
            }
            else -> {
                it.moduleItemWithOpacity.visibility = View.GONE
                it.moduleItemImage.visibility = View.VISIBLE
                it.moduleItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_doing)
                it.moduleItemStatusText.text = context.getString(R.string.modulesInProgressText)
            }
        }
    }

}