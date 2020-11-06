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
import br.com.kaz.model.courses.CourseKaz
import br.com.kaz.model.courses.Step
import br.com.kaz.view.activity.ChecklistActivity
import kotlinx.android.synthetic.main.base_list_item.view.*

class StepsAdapter(
    private val course: CourseKaz,
    private val context: Context,
    private val modulePosition: Int
) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stepCard = itemView.cardView!!
        var stepItemTitle = itemView.itemTitle!!
        var stepItemDescription = itemView.itemDescription!!
        var stepItemWithOpacity = itemView.itemWithOpacity!!
        var stepItemImage = itemView.itemImage!!
        var stepItemStatusText = itemView.itemStatusText!!
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
        it.stepItemTitle.text = step.title
        it.stepItemDescription.text = step.description

        when (step.completed) {
            false -> {
                it.stepItemWithOpacity.visibility = View.VISIBLE
                it.stepItemImage.visibility = View.VISIBLE
                it.stepItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_locked)
            }
            true -> {
                it.stepItemWithOpacity.visibility = View.GONE
                it.stepItemImage.visibility = View.VISIBLE
                it.stepItemImage.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorGreenStatusButton))
                it.stepItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_completed)
                it.stepItemStatusText.text = context.getString(R.string.stepCompletedText)
            }
            else -> {
                it.stepItemWithOpacity.visibility = View.GONE
                it.stepItemImage.visibility = View.VISIBLE
                it.stepItemImage.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_item_doing)
                it.stepItemStatusText.text = context.getString(R.string.stepInProgressText)
            }
        }
    }

    private fun setClickListener(it: ViewHolder, position: Int, step: Step) {
        it.stepCard.setOnClickListener {
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