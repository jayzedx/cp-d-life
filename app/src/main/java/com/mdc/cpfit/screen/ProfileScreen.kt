package com.mdc.cpfit.screen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.dialog.ImagePickerDialog
import com.mdc.cpfit.dialog.MainMenuBottomSheetDialog
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.Permission
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.profile_menu.view.*
import kotlinx.android.synthetic.main.sc_profile.*
import kotlinx.android.synthetic.main.toolbar_menu_back.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ProfileScreen : ScreenUnit() {

    val TAG = ProfileScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase

    val REQ_IMAGE = 300
    var imageBitmap: Bitmap? = null
    var imgstring = ""
    var uriSavedImage: Uri? = null

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

//        viewProfile.setOnClickListener {
//            val dialogBuilder = iOSDialogBuilder(context)
//            dialogBuilder.setTitle("ยืนการทำการการ")
//            dialogBuilder.setSubtitle("Enter ยืนยันการขออนุมัติการทำงานล่วงหน้า Below")
//            dialogBuilder.setBoldPositiveLabel(true)
//            dialogBuilder.setCancelable(false)
//            dialogBuilder.setPositiveListener("ยืนยัน") { dialog ->
//                //do something with edt.getText().toString();
//                dialog.dismiss()
//
//            }
//            dialogBuilder.setNegativeListener("ยกเลิก", { dialog ->
//
//                dialog.dismiss()
//                //pass
//            })
//
//            dialogBuilder.build().show()
//        }

        viewProfile.setOnClickListener {
            imageBitmap?.run {
                var dialogFragment: ImagePickerDialog = ImagePickerDialog.newInstance(this)
                dialogFragment.show(activity?.supportFragmentManager, ImagePickerDialog::class.java.simpleName)
            } ?: run {
                onClickPickImage()
            }
        }

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
            onClickPickImage()
            bottomSheet?.dismiss()
        }
        bottomSheetView?.cancelMenu?.setOnClickListener {
            bottomSheet?.dismiss()
        }
    }




    private fun onClickPickImage() {
        val chooseImageIntent = onChooseImageIntent()
        startActivityForResult(chooseImageIntent, REQ_IMAGE)
    }

    fun getTakePhotoIntent(): Intent? {
        val permis = Permission.Camera(activityMain)
        val timeStamp = SimpleDateFormat("yyyyHHmmss").format(Date())
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(context?.packageManager) != null) {
            val root = Environment.getExternalStorageDirectory().absolutePath + "/"
            val photo = File(root + "cp_d_life" + File.separator)
            if (!photo.exists()) {
                photo.mkdir()
            } else {
                for (file in photo.listFiles())
                    if (!file.isDirectory)
                        file.delete()
            }
            val image = File(photo.path + File.separator, "$timeStamp.jpg")
            imgstring = image.toString()

            if (permis) {
                uriSavedImage = FileProvider.getUriForFile(activity!!, "com.mdc.cpfit.provider", image)
                return intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage)
            }

        }

        return null
    }

    private fun onChooseImageIntent(): Intent? {
        var chooserIntent: Intent? = null
        var intentList: MutableList<Intent> = ArrayList()
        /** GALLERY **/
        val pickIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        /** CAMERA **/
        val intentTakePhoto = getTakePhotoIntent()

        if (intentTakePhoto != null) {
            intentList = addIntentsToList(intentList, intentTakePhoto, isCamera = true)
        }
        intentList = addIntentsToList(intentList, pickIntent)

        if (intentList.size > 0) {
            chooserIntent = Intent.createChooser(intentList.removeAt(intentList.size - 1),
                    context?.getString(R.string.personal_select_picture))
            chooserIntent!!.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toTypedArray<Parcelable>())
        }
        return chooserIntent
    }


    private fun addIntentsToList(list: MutableList<Intent>, intent: Intent, isCamera: Boolean = false): MutableList<Intent> {
        val resInfo = context?.getPackageManager()?.queryIntentActivities(intent, 0)
        if (resInfo != null) {
            for (resolveInfo in resInfo) {
                val packageName = resolveInfo.activityInfo.packageName
                if ((packageName.contains(".android") && isCamera) || !isCamera) { //use only official camera
                    val targetedIntent = Intent(intent)
                    targetedIntent.setPackage(packageName)
                    list.add(targetedIntent)
                }
            }
        }
        return list
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_IMAGE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val time = SimpleDateFormat("yyyyHHmmss").format(Date())
                    val displaymetrics = getResources().getDisplayMetrics()
                    val maxHeight = displaymetrics.heightPixels
                    if (data == null || data.action == MediaStore.ACTION_IMAGE_CAPTURE) {
                        /** CAMERA **/
//                        imageBitmap = ImageUtil.onCameraResult(uriSavedImage, context)
                        imageBitmap = ImageUtil.onCameraResult(uriSavedImage, context, maxHeight)
                    } else {
                        /** ALBUM **/
                        val pair = ImageUtil.onGalleryResult(time, data, activityMain, maxHeight)
                        imageBitmap = pair.first
                        uriSavedImage = pair.second
                    }

                    UpdateUI {
                        var reqOptions = ImageUtil.getImageCirclePersonnalProfile()
                        Glide.with(context!!).load(imageBitmap)
                                .apply(reqOptions)
                                .into(imvProfile)
                    }
                    //imageEncode = ImageUtil.BitMapToString(imageBitmap)!!
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)

        }
    }



}