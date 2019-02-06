package com.mdc.cpfit.screen.tab2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.util.ActivityUnit


class TrophyAdapter(var activity: ActivityUnit) : RecyclerView.Adapter<TrophyAdapter.ViewHolder>() {

    var array = ArrayList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_leaderboard, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = array!!.get(position)

    }

    fun updateArray(array: ArrayList<Any>) {
        this.array = array
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//        var tvRanking = itemView?.findViewById<TextView>(R.id.tvRanking)

    }

}