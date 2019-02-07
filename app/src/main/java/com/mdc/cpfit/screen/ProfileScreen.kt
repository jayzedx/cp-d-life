package com.mdc.cpfit.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.dialog.MainMenuBottomSheetDialog
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.profile_menu.view.*
import kotlinx.android.synthetic.main.toolbar_menu_back.*


class ProfileScreen : ScreenUnit() {

    val TAG = ProfileScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase


    companion object {
        fun newInstance(): ProfileScreen {
            val fragment = ProfileScreen()
            val args = Bundle()
            //args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(ProfileScreen::class.simpleName.toString(), rootView)
        setValue()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_profile, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
        //type = args?.getString(MsgProperties.PERSON_TYPE, "")!!
        dialog = DialogBase(context)
        setToolbar()
        setComponents()
    }

    private fun setToolbar() {
        toolbarMenu?.setOnClickListener {
            showBottomSheet()
        }
        backMenu?.setOnClickListener {
            goback(false)
        }
    }

    private fun setComponents() {

    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.profile_menu, null)
        var bottomSheet = MainMenuBottomSheetDialog()

        bottomSheet?.setContentView(bottomSheetView)
        bottomSheet?.show(childFragmentManager, TAG)

        bottomSheetView?.changePasswordMenu?.setOnClickListener {
            IntentFragment(ChangePasswordScreen.newInstance())
            bottomSheet?.dismiss()
        }
        bottomSheetView?.changePictureMenu?.setOnClickListener {
            bottomSheet?.dismiss()
        }
        bottomSheetView?.cancelMenu?.setOnClickListener {
            bottomSheet?.dismiss()
        }
    }



}