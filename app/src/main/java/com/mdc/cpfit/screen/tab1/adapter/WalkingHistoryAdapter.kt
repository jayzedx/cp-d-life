package com.mdc.cpfit.screen.tab1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.model.WalkingHistoryBody
import com.mdc.cpfit.util.ImageUtil
import java.text.DateFormatSymbols
import java.util.*


class WalkingHistoryAdapter(var context: Context) : RecyclerView.Adapter<WalkingHistoryAdapter.ViewHolder>() {
    var array = ArrayList<WalkingHistoryBody>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_walking_history, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = array!!.get(position)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, model?.month)
//        var month = DateFormatSymbols().getShortMonths()[model?.month]
        var month = DateFormatSymbols().months[model?.month]

        holder.tvMonth?.text = month
        holder.tvDetail?.text = "Weight : ${model?.weight} kg | BMI : ${model?.bmi}"
        holder.tvStatusBmi?.text = model?.bmi.toString()

        Glide.with(context).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(holder.imvWalkingHistory!!)
    }

    fun updateArray(array: ArrayList<WalkingHistoryBody>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imvWalkingHistory = itemView?.findViewById<ImageView>(R.id.imvWalkingHistory)
        var tvMonth = itemView?.findViewById<TextView>(R.id.tvMonth)
        var tvDetail = itemView?.findViewById<TextView>(R.id.tvDetail)
        var tvStatusBmi = itemView?.findViewById<TextView>(R.id.tvStatusBmi)

    }

}

