package com.mdc.cpfit.screen.tab1

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.DateUtil
import com.mdc.cpfit.util.ScreenUnit
import java.util.*
import kotlinx.android.synthetic.main.partial_personal_add_distance.*
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.mdc.cpfit.dialog.ImagePickerDialog
import com.mdc.cpfit.dialog.PopupDialog
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.Permission
import com.mdc.cpfit.util.Permission.*
import org.parceler.Parcels
import java.io.File
import java.io.Serializable
import java.text.SimpleDateFormat


class PersonalUpdateDistanceScreen: ScreenUnit() {

    val TAG = PersonalUpdateDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var showOrHiddenCallBack:(()-> Unit)? = null

    var stepUnitSelected = true


    lateinit var dialog: DialogBase
    var datePicker = ""



    val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    val KEY_SELECTED_UNIT = "KEY_SELECTED_UNIT"



    var imageBitmap: Bitmap? = null
    var imgstring = ""
    var uriSavedImage: Uri? = null
    val REQ_IMAGE = 300

    companion object {
        fun newInstance(): PersonalUpdateDistanceScreen {
            val fragment = PersonalUpdateDistanceScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.partial_personal_add_distance, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalUpdateDistanceScreen::class.simpleName.toString(), rootView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            stepUnitSelected = savedInstanceState.getBoolean(KEY_SELECTED_UNIT)
//            imageBitmap = Parcels.unwrap(savedInstanceState.getParcelable(KEY_IMAGE_URI)) as Bitmap
            uriSavedImage = Uri.parse(savedInstanceState.getString(KEY_IMAGE_URI))
            setRestoreState()
        } else {
            setInitialState()
        }
        setValue()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_SELECTED_UNIT, stepUnitSelected)
//        outState.putParcelable(KEY_IMAGE_URI, Parcels.wrap(imageBitmap))
        outState.putString(KEY_IMAGE_URI, uriSavedImage.toString())
        super.onSaveInstanceState(outState)
    }


    private fun setValue() {
        setComponent()
    }

    private fun setInitialState() {
        UpdateUI {
            imageBitmap = null
            uriSavedImage = null
            imgstring = ""
            datePicker = ""
            stepUnitSelected = true
            edtDistance.setText("")
            edtSelectDate.setText("")
            imvPreview.setImageResource(R.drawable.ic_gallery)
            onChangeUnit(initial = true)
        }
    }

    private fun setRestoreState() {
        onChangeUnit(initial = true)
        if (uriSavedImage != null) {
            Glide.with(context!!).load(uriSavedImage).into(imvPreview)
        }
    }

    private fun setComponent() {
        edtSelectDate.setOnClickListener { onClickDatePicker() }
        tvStepSelectUnit.setOnClickListener { onChangeUnit()}
        tvKmSelectUnit.setOnClickListener { onChangeUnit()}
        imvAdd.setOnClickListener { onClickPickImage() }

        imvPreview.setOnClickListener {
            imageBitmap?.run {
                var dialogFragment: ImagePickerDialog = ImagePickerDialog.newInstance(this)
                dialogFragment.show(activity?.supportFragmentManager, ImagePickerDialog::class.java.simpleName)
            }
        }
        imvBtnCancel.setOnClickListener { showOrHiddenCallBack?.invoke() }
        imvBtnOk.setOnClickListener {
            var dialogFragment: PopupDialog = PopupDialog.newInstance()
            dialogFragment.setTextHead("Yo Yo")
            dialogFragment.setTextTitle("this is title")
            dialogFragment.setTextDetail("this is detail dasdsa das dsad asdas dasd asd asd asd asdas dasd asdasdasdasdsa")
            dialogFragment.setImageHead(R.drawable.ic_trophy)
            dialogFragment.show(activity?.supportFragmentManager, PopupDialog::class.java.simpleName)
        }
    }

    private fun onChangeUnit(initial:Boolean = false) {
        val smallPx = context?.resources?.getDimension(R.dimen._12sdp)!!
        val largePx = context?.resources?.getDimension(R.dimen._14sdp)!!
        val smallSize = smallPx / getResources().displayMetrics.density
        val largeSize = largePx / getResources().displayMetrics.density

        if (initial) stepUnitSelected = !stepUnitSelected

        stepUnitSelected = if (stepUnitSelected) {
            tvKmSelectUnit.text = getString(R.string.personal_km_selected)
            tvStepSelectUnit.text = getString(R.string.personal_step_selected) + "/"
            tvStepSelectUnit.setTypeface(null, Typeface.NORMAL)
            tvKmSelectUnit.setTypeface(null, Typeface.BOLD)
            tvKmSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, largeSize)
            tvStepSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, smallSize)
            false
        } else {
            tvStepSelectUnit.text = getString(R.string.personal_step_selected)
            tvKmSelectUnit.text =  "/" + getString(R.string.personal_km_selected)
            tvStepSelectUnit.setTypeface(null, Typeface.BOLD)
            tvKmSelectUnit.setTypeface(null, Typeface.NORMAL)
            tvStepSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, largeSize)
            tvKmSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, smallSize)
            true
        }
    }

    private fun onClickDatePicker() {
        val c = Calendar.getInstance()
        val currentDate = c.get(Calendar.DAY_OF_MONTH).toString() + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR)
        var dateArray : List<String>
        var yearPick = 0
        var monthPick = 0
        var dayPick = 0

        dateArray = if (datePicker.equals("")) {
            currentDate?.split("/")
        } else {
            datePicker.split("/")
        }
        dayPick = dateArray[0].toInt()
        monthPick = dateArray[1].toInt().let {month->
            if (datePicker.equals("")) month
            else month-1
        }
        yearPick = dateArray!![2].toInt()

        var cal = Calendar.getInstance()
        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//            val myFormat = "dd MMMM yyyy" // mention the format you need
//            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
//            c.set(year, monthOfYear, dayOfMonth)
//            val dateString = sdf.format(c.time)

            var month = monthOfYear + 1
            datePicker = "$dayOfMonth/$month/$year"
            edtSelectDate.setText(DateUtil.convertDateFormatServer(datePicker))

        }, yearPick, monthPick, dayPick)
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }









    private fun onClickPickImage() {
        val chooseImageIntent = onChooseImageIntent()
        startActivityForResult(chooseImageIntent, REQ_IMAGE)
    }


    fun getTakePhotoIntent(): Intent? {
        val permis = Camera(activityMain)
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
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

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
                        imvPreview.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
                        Glide.with(context!!).load(imageBitmap)
                                //.apply(ImageUtil.getImageCirclePartientProfile())
                                .into(imvPreview)
                    }
                    //imageEncode = ImageUtil.BitMapToString(imageBitmap)!!
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)

        }
    }

}