package com.mdc.cpfit.screen.tab2.adapter

import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.model.LeaderBoardBody
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.util.ActivityUnit


class LeaderBoardAdapter(var activity: ActivityUnit, var page: String) : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {

    var array = ArrayList<LeaderBoardBody>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_leaderboard, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = array!!.get(position)
        when(page) {
            MsgProperties.LEADERBOARD_COMPANY -> {
                holder?.tvCompany?.visibility = View.GONE
                holder?.viewTrophy?.visibility = View.VISIBLE
            }
            MsgProperties.LEADERBOARD_ALL_COMPANY -> {
                holder?.tvCompany?.visibility = View.GONE
                holder?.viewTrophy?.visibility = View.GONE
            }
            MsgProperties.LEADERBOARD_ALL_MEMBER -> {
                holder?.viewTrophy?.visibility = View.GONE
            }
        }

        holder?.tvRanking?.text =  model?.rank.toString()
        holder?.tvName?.text =  model?.name
        holder?.tvDistance?.text = model?.distanceKm.toString() + " " + activity.getString(R.string.leaderboard_km_unit)

    }

    fun updateArray(array: ArrayList<LeaderBoardBody>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvRanking = itemView?.findViewById<TextView>(R.id.tvRanking)
        var imvProfile = itemView?.findViewById<ImageView>(R.id.imvProfile)
        var tvName = itemView?.findViewById<TextView>(R.id.tvName)
        var tvDistance = itemView?.findViewById<TextView>(R.id.tvDistance)
        var tvCompany = itemView?.findViewById<TextView>(R.id.tvCompany)

        var viewTrophy = itemView?.findViewById<RelativeLayout>(R.id.viewTrophy)
        var imvWeekTrophy = itemView?.findViewById<ImageView>(R.id.imvWeekTrophy)
        var imvMonthTrophy = itemView?.findViewById<ImageView>(R.id.imvMonthTrophy)
        var imvSpecialTrophy = itemView?.findViewById<ImageView>(R.id.imvSpecialTrophy)
        var imvSpecialTrophy2 = itemView?.findViewById<ImageView>(R.id.imvSpecialTrophy2)
    }

}