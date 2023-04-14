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
import com.antoniocitty.grupoexclusivo.databinding.BaseListItemBinding
import com.antoniocitty.grupoexclusivo.model.courses.ModuleGE
import com.antoniocitty.grupoexclusivo.model.courses.CourseGE
import com.antoniocitty.grupoexclusivo.view.activity.StepsActivity
import java.lang.Exception

class CourseGEAdapter(private val course: CourseGE, private val context: Context) :
    RecyclerView.Adapter<CourseGEAdapter.ViewHolder>() {

    class ViewHolder(binding: BaseListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var moduleCard: CardView? = binding.cardView
        var moduleTitle: TextView? = binding.itemTitle
        var moduleItemDescription: TextView? = binding.itemDescription
        var moduleItemWithOpacity: View? = binding.itemWithOpacity
        var moduleItemImage: ImageView? = binding.itemImage
        var moduleItemStatusText: TextView? = binding.itemStatusText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BaseListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return try {
            course.moduleGE.size
        } catch (e: Exception) {
            0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = course.moduleGE[position]

        configureModuleView(holder, module)
        setClickListener(holder, position, module)
    }

    private fun configureModuleView(it: ViewHolder, moduleGE: ModuleGE) {
        it.moduleTitle?.text = moduleGE.title
        it.moduleItemDescription?.text = moduleGE.description

        when (moduleGE.completed) {
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

    private fun setClickListener(it: ViewHolder, position: Int, moduleGE: ModuleGE) {
        it.moduleCard?.setOnClickListener {
            if (moduleGE.completed == false) {
                Toast.makeText(
                    context, context.getString(R.string.moduleNotAbleYet), Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(context, StepsActivity::class.java)
                intent.let {
                    it.putExtra("modulePosition", position)
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        }

    }

}