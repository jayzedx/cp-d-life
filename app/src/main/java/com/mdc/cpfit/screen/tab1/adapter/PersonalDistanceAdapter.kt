package com.mdc.cpfit.screen.tab1.adapter

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.model.PersonalDistanceAdapterModel
import com.mdc.cpfit.util.ActivityUnit


class PersonalDistanceAdapter(var activity: ActivityUnit) : RecyclerView.Adapter<PersonalDistanceAdapter.ViewHolder>() {

    var array = ArrayList<PersonalDistanceAdapterModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_distance_record, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = array!!.get(position)
        holder.tvTitle?.setTypeface(null, Typeface.BOLD_ITALIC)
        holder.tvStep?.setTypeface(null, Typeface.BOLD)
        holder.tvKm?.setTypeface(null, Typeface.BOLD)

        holder.tvTitle?.text = model.title
        holder.tvStep?.text = model.step.toString()
        holder.tvKm?.text = model.km.toString()

        model.stepGoal?.let {
            holder.tvStepGoal?.text  = it.toString()
        } ?: holder.tvStepGoal?.setVisibility(View.INVISIBLE)

        model.kmGoal?.let {
            holder.tvKmGoal?.text  = it.toString()
        } ?: holder.tvKmGoal?.setVisibility(View.INVISIBLE)

        holder.viewRecord?.background = when(position) {
            0 -> ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_company_record)
            1 -> ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_all_company_record)
            2 -> ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_total_record)
            else -> ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_total_record)
        }

    }

    fun updateArray(array: ArrayList<PersonalDistanceAdapterModel>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView?.findViewById<TextView>(R.id.tvTitle)
        var tvStep = itemView?.findViewById<TextView>(R.id.tvStep)
        var tvKm = itemView?.findViewById<TextView>(R.id.tvKm)
        var tvStepGoal = itemView?.findViewById<TextView>(R.id.tvStepGoal)
        var tvKmGoal = itemView?.findViewById<TextView>(R.id.tvKmGoal)
        var viewRecord = itemView?.findViewById<RelativeLayout>(R.id.viewRecord)
    }




}