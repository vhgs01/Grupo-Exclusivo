package com.antoniocitty.grupoexclusivo.view.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.antoniocitty.grupoexclusivo.R
import com.antoniocitty.grupoexclusivo.model.courses.ModuleKaz
import com.antoniocitty.grupoexclusivo.model.courses.CourseKaz
import com.antoniocitty.grupoexclusivo.view.activity.StepsActivity
import kotlinx.android.synthetic.main.base_list_item.view.*

class CourseKazAdapter(private val course: CourseKaz, private val context: Context) :
    RecyclerView.Adapter<CourseKazAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCard: CardView? = itemView.cardView
        var moduleTitle: TextView? = itemView.itemTitle
        var moduleItemDescription: TextView? = itemView.itemDescription
        var moduleItemWithOpacity: View? = itemView.itemWithOpacity
        var moduleItemImage: ImageView? = itemView.itemImage
        var moduleItemStatusText: TextView? = itemView.itemStatusText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.base_list_item, parent, false)
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
        it.moduleTitle?.text = moduleKaz.title
        it.moduleItemDescription?.text = moduleKaz.description

        when (moduleKaz.completed) {
            false -> {
                it.moduleItemWithOpacity?.visibility = View.VISIBLE
                it.moduleItemImage?.visibility = View.VISIBLE
                it.moduleItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_locked)
            }
            true -> {
                it.moduleItemWithOpacity?.visibility = View.GONE
                it.moduleItemImage?.visibility = View.VISIBLE
                it.moduleItemImage?.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorPrimaryDark))
                it.moduleItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_completed)
                it.moduleItemStatusText?.text = context.getString(R.string.modulesCompletedText)
            }
            else -> {
                it.moduleItemWithOpacity?.visibility = View.GONE
                it.moduleItemImage?.visibility = View.VISIBLE
                it.moduleItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_doing)
                it.moduleItemStatusText?.text = context.getString(R.string.modulesInProgressText)
            }
        }
    }

    private fun setClickListener(it: ViewHolder, position: Int, moduleKaz: ModuleKaz) {
        it.moduleCard?.setOnClickListener {
            if (moduleKaz.completed == false) {
                Toast.makeText(
                    context,
                    context.getString(R.string.moduleNotAbleYet), Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(context, StepsActivity::class.java)
                intent.putExtra("modulePosition", position)
                context.startActivity(intent)
            }
        }

    }

}