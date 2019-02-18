package com.mdc.cpfit.screen.tab1.adapter

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.model.WalkingHistoryReportBody
import com.mdc.cpfit.screen.WalkingHistoryScreen
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.ScreenUnit
import java.text.DateFormatSymbols
import java.util.*


class WalkingHistoryReportAdapter(var activity: ActivityUnit, var screen: ScreenUnit? = null) : RecyclerView.Adapter<WalkingHistoryReportAdapter.ViewHolder>() {
    var array = ArrayList<WalkingHistoryReportBody>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_walking_history_report, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = array!!.get(position)


        holder.tvMonth?.setTypeface(null, Typeface.BOLD)
        holder.tvStatusBmi?.setTypeface(null, Typeface.BOLD)


        if(position %2 == 1) {
            holder?.itemView?.setBackgroundColor(ContextCompat.getColor(activity, R.color.bg_list))
        } else {
            holder?.itemView?.setBackgroundColor(ContextCompat.getColor(activity, R.color.White))
        }


        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, model?.month)
//        var month = DateFormatSymbols().getShortMonths()[model?.month]
        var month = DateFormatSymbols().months[model?.month]

        holder.tvMonth?.text = month
        holder.tvDetail?.text = "Weight : ${model?.weight} kg | BMI : ${model?.bmi}"
        holder.tvStatusBmi?.text = model?.bmi.toString()


        Glide.with(activity).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(holder.imvWalkingHistory!!)


        holder.viewWalkingHistoryReport?.setOnClickListener {
//            var mp: HashMap<String, Bundle>? = HashMap()
//            var bunble = Bundle()
//            bunble.putSerializable(MsgProperties.SCEEN_INTENT, MsgProperties.DISTANCE_HISTORY)
//            bunble.putSerializable(MsgProperties.MODEL, null)
//            mp?.put(MsgProperties.ARGUMENT, bunble)
//            activity?.startActivityUnit(TabOneContainActivity::class.java, mp)
            screen?.IntentFragment(WalkingHistoryScreen.newInstance())
        }
    }


    fun updateArray(array: ArrayList<WalkingHistoryReportBody>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imvWalkingHistory = itemView?.findViewById<ImageView>(R.id.imvWalkingHistory)
        var tvMonth = itemView?.findViewById<TextView>(R.id.tvMonth)
        var tvDetail = itemView?.findViewById<TextView>(R.id.tvDetail)
        var tvStatusBmi = itemView?.findViewById<TextView>(R.id.tvStatusBmi)
        var viewWalkingHistoryReport = itemView?.findViewById<View>(R.id.viewWalkingHistoryReport)

    }

}

