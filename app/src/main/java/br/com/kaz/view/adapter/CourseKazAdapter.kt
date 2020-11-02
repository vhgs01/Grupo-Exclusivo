package br.com.kaz.view.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.kaz.R
import br.com.kaz.model.courses.ModuleKaz
import br.com.kaz.model.courses.CourseKaz
import br.com.kaz.view.activity.StepsActivity
import kotlinx.android.synthetic.main.module_item.view.*

class CourseKazAdapter(private val course: CourseKaz, private val context: Context) :
    RecyclerView.Adapter<CourseKazAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCard = itemView.moduleCard!!
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
        return course.moduleKaz.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = course.moduleKaz[position]

        configureModuleView(holder, module)
        setClickListener(holder, position, module)
    }

    private fun configureModuleView(it: ViewHolder, moduleKaz: ModuleKaz) {
        it.moduleTitle.text = moduleKaz.title
        it.moduleItemDescription.text = moduleKaz.description

        when (moduleKaz.completed) {
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

    private fun setClickListener(it: ViewHolder, position: Int, moduleKaz: ModuleKaz) {
        it.moduleCard.setOnClickListener {
            if (moduleKaz.completed == false) {
                Toast.makeText(context, "Módulo não liberado", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(context, StepsActivity::class.java)
                intent.putExtra("modulePosition", position)
                context.startActivity(intent)
            }
        }

    }

}