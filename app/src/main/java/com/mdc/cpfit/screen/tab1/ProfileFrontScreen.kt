package com.mdc.cpfit.screen.tab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.partial_profile.*


class ProfileFrontScreen : ScreenUnit() {

    val TAG = ProfileFrontScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase


    companion object {
        fun newInstance(): ProfileFrontScreen {
            val fragment = ProfileFrontScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.partial_profile, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(ProfileFrontScreen::class.simpleName.toString(), rootView)
        setValue()


    }



    private fun setValue() {
        val args = arguments
//        type = args?.getString(MsgProperties.PERSON_TYPE, "")!!

        dialog = DialogBase(context)
        setComponents()

    }

    private fun setComponents() {
        Glide.with(this.context!!).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(imvProfile)


    }
}