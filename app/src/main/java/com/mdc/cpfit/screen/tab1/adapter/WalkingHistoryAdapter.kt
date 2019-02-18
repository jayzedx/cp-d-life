package com.mdc.cpfit.screen.tab1.adapter


import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.model.WalkingHistoryBody
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.ImageUtil
import java.util.*


class WalkingHistoryAdapter(var activity: ActivityUnit) : RecyclerView.Adapter<WalkingHistoryAdapter.ViewHolder>() {
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
//        calendar.set(Calendar.MONTH, model?.month)
//        var month = DateFormatSymbols().getShortMonths()[model?.month]
//        var month = DateFormatSymbols().months[model?.month]

        holder.tvDetail?.text = "${model?.step} step | ${model?.distance} km"
        holder.tvDate?.text = "${model?.date}"


        Glide.with(activity).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(holder.imvWalkingHistory!!)


        if(position %2 == 1) {
            holder?.itemView?.setBackgroundColor(ContextCompat.getColor(activity, R.color.bg_list))
        } else {
            holder?.itemView?.setBackgroundColor(ContextCompat.getColor(activity, R.color.White))
        }

    }


    fun updateArray(array: ArrayList<WalkingHistoryBody>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imvWalkingHistory = itemView?.findViewById<ImageView>(R.id.imvWalkingHistory)
        var tvDetail = itemView?.findViewById<TextView>(R.id.tvDetail)
        var tvDate = itemView?.findViewById<TextView>(R.id.tvDate)
        var viewWalkingHistory = itemView?.findViewById<View>(R.id.viewWalkingHistory)

    }

}

