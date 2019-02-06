package com.mdc.cpfit.screen.tab2

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.util.ScreenUnit
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.sc_trophy.*
import org.w3c.dom.Text


class TrophyScreen : ScreenUnit() {

    val TAG = TrophyScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var adapter: SectionedRecyclerViewAdapter


    companion object {
        fun newInstance(): TrophyScreen {
            val fragment = TrophyScreen()
            val args = Bundle()
            //args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_trophy, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(TrophyScreen::class.simpleName.toString(), rootView)
        setValue()
        setComponent()

    }

    private fun setValue() {
        val args = arguments
        //type = args?.getString(MsgProperties.PERSON_TYPE, "")!!
    }
    private fun setComponent() {


        val maxSpan = 4
        var gridManager = GridLayoutManager(context, maxSpan)

        adapter = SectionedRecyclerViewAdapter()
        //Add header and array list
        adapter.addSection(TrophySection(getString(R.string.trophy_event_header), getEventTrophy()))
        adapter.addSection(TrophySection(getString(R.string.trophy_distance_header), getDistanceTrophy()))

        gridManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (adapter.getSectionItemViewType(position)) {
                    SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER -> return 4
                    else -> return 1
                }
            }

        }
        recTrophy.layoutManager = gridManager
        recTrophy.adapter = adapter
    }





    /**
     *  View Holder RecyclerView
     */

    private inner class HeaderViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        //set header
        val tvTitle: TextView

        init {
            tvTitle = view.findViewById<TextView>(R.id.tvTitle) as TextView
        }
    }

    private inner class ItemViewHolder internal constructor(val rootView: View) : RecyclerView.ViewHolder(rootView) {

        //set item
        val imvTrophy: ImageView

        init {
            imvTrophy = rootView.findViewById<ImageView>(R.id.imvTrophy)
        }
    }



    /**
     *  Adapter
     */
    private inner class TrophySection internal constructor(var title: String, var list: ArrayList<Any>) : StatelessSection(SectionParameters.builder()
            .itemResourceId(R.layout.list_trophy)
            .headerResourceId(R.layout.header_trophy)
            .build()) {

        override fun getContentItemsTotal(): Int {
            return list.size
        }

        override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
            return ItemViewHolder(view)
        }

        override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val itemHolder = holder as ItemViewHolder
            //var data = list[position]

//            itemHolder.imvTrophy.setImageResource(R.drawable.border_top_rank)
            itemHolder.rootView.setOnClickListener {

            }
        }

        override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
            return HeaderViewHolder(view)
        }

        override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
            val headerHolder = holder as HeaderViewHolder
            headerHolder.tvTitle.setTypeface(null, Typeface.BOLD)
            headerHolder.tvTitle.text = title
        }
    }

    private fun getEventTrophy(): ArrayList<Any> { return arrayListOf(1,2,3,4,5,6,7,8) }
    private fun getDistanceTrophy(): ArrayList<Any> { return arrayListOf(1,2,3,4,5)}




}

