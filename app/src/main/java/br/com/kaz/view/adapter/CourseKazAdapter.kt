package br.com.kaz.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.kaz.R
import br.com.kaz.model.courses.CourseKaz
import kotlinx.android.synthetic.main.module_item.view.*

class CourseKazAdapter(private val course: CourseKaz, private val context: Context) :
    RecyclerView.Adapter<CourseKazAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleTitle = itemView.moduleItemTitle!!
        var moduleItemDescription = itemView.moduleItemDescription!!
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

        holder.let {
            it.moduleTitle.text = module.title
            it.moduleItemDescription.text = module.description
        }
    }

}