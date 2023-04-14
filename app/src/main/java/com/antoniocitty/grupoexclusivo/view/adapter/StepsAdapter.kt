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
import com.antoniocitty.grupoexclusivo.model.courses.CourseGE
import com.antoniocitty.grupoexclusivo.model.courses.Step
import com.antoniocitty.grupoexclusivo.view.activity.ChecklistActivity
import java.lang.Exception

class StepsAdapter(
    private val course: CourseGE, private val context: Context, private val modulePosition: Int
) : RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    class ViewHolder(binding: BaseListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var stepCard: CardView? = binding.cardView
        var stepItemTitle: TextView? = binding.itemTitle
        var stepItemDescription: TextView? = binding.itemDescription
        var stepItemWithOpacity: View? = binding.itemWithOpacity
        var stepItemImage: ImageView? = binding.itemImage
        var stepItemStatusText: TextView? = binding.itemStatusText
    }

    override fun getItemCount(): Int {
        return try {
            course.moduleGE[modulePosition].steps.size
        } catch (e: Exception) {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BaseListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = course.moduleGE[modulePosition].steps[position]

        configureModuleView(holder, step)
        setClickListener(holder, position, step)
    }

    private fun configureModuleView(it: ViewHolder, step: Step) {
        it.stepItemTitle?.text = step.title
        it.stepItemDescription?.text = step.description

        when (step.completed) {
            false -> {
                it.stepItemWithOpacity?.visibility = View.VISIBLE
                it.stepItemImage?.visibility = View.VISIBLE
                it.stepItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_locked)
            }

            true -> {
                it.stepItemWithOpacity?.visibility = View.GONE
                it.stepItemImage?.visibility = View.VISIBLE
                it.stepItemImage?.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorPrimaryDark))
                it.stepItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_completed)
                it.stepItemStatusText?.text = context.getString(R.string.stepCompletedText)
            }

            else -> {
                it.stepItemWithOpacity?.visibility = View.GONE
                it.stepItemImage?.visibility = View.VISIBLE
                it.stepItemImage?.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_doing)
                it.stepItemStatusText?.text = context.getString(R.string.stepInProgressText)
            }
        }
    }

    private fun setClickListener(it: ViewHolder, position: Int, step: Step) {
        it.stepCard?.setOnClickListener {
            if (step.completed == false) {
                Toast.makeText(
                    context, context.getString(R.string.stepNotAbleYet), Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(context, ChecklistActivity::class.java)
                intent.let {
                    it.putExtra("modulePosition", modulePosition)
                    it.putExtra("stepPosition", position)
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        }

    }
}