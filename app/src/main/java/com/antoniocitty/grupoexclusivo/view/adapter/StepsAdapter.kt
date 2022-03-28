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
import com.antoniocitty.grupoexclusivo.model.courses.CourseKaz
import com.antoniocitty.grupoexclusivo.model.courses.Step
import com.antoniocitty.grupoexclusivo.view.activity.ChecklistActivity
import kotlinx.android.synthetic.main.base_list_item.view.*

class StepsAdapter(
    private val course: CourseKaz,
    private val context: Context,
    private val modulePosition: Int
) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stepCard: CardView? = itemView.cardView
        var stepItemTitle: TextView? = itemView.itemTitle
        var stepItemDescription: TextView? = itemView.itemDescription
        var stepItemWithOpacity: View? = itemView.itemWithOpacity
        var stepItemImage: ImageView? = itemView.itemImage
        var stepItemStatusText: TextView? = itemView.itemStatusText
    }

    override fun getItemCount(): Int {
        return course.moduleKaz[modulePosition].steps.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.base_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = course.moduleKaz[modulePosition].steps[position]

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
                    context,
                    context.getString(R.string.stepNotAbleYet),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(context, ChecklistActivity::class.java)
                intent.putExtra("modulePosition", modulePosition)
                intent.putExtra("stepPosition", position)
                context.startActivity(intent)
            }
        }

    }
}