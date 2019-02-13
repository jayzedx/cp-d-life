package com.mdc.cpfit.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.*
import android.media.ExifInterface
import android.nfc.Tag
import com.mdc.cpfit.R


class ImageUtil {


    companion object {
        val TAG = ImageUtil::class.java.simpleName

        fun getImageCirclePersonnalProfile(): RequestOptions {
            return RequestOptions()
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_personal_profile)
                    .error(R.drawable.ic_personal_profile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)


        }

        fun getImageCirclePartientProfile(): RequestOptions {
            return RequestOptions()
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)


        }

        fun BitMapToString(bitmap: Bitmap?): String? {
            var base64: String? = ""
            val baos = ByteArrayOutputStream()
            bitmap?.let {
                it.compress(Bitmap.CompressFormat.JPEG, 30, baos)
                val b = baos.toByteArray()
                base64 = Base64.encodeToString(b, Base64.DEFAULT)
            }
            return base64
        }

        fun Base64formUrl(url: String?, context: Context?): String? {
            try {
                return BitMapToString(Glide.with(context!!).asBitmap().load(url).apply(getImageCirclePartientProfile()).submit().get())
            } catch (r: java.lang.Exception) {
                return BitMapToString(BitmapFactory.decodeResource(context?.getResources(), R.drawable.ic_launcher_background))

            }
        }

        fun BitmapformUrl(url: String?, context: Context?): Bitmap? {
            try {

                return Glide.with(context!!).asBitmap().load(url).apply(getImageCirclePartientProfile()).submit().get()
            } catch (r: java.lang.Exception) {
                return BitmapFactory.decodeResource(context?.getResources(), R.drawable.ic_launcher_background)

            }

        }
        fun BitmapformUrl(url: String?, context: Context?,picDefault: Int): Bitmap? {
            try {

                return Glide.with(context!!).asBitmap().load(url).apply(getImageCirclePartientProfile()).submit().get()
            } catch (r: java.lang.Exception) {
                return BitmapFactory.decodeResource(context?.getResources(),picDefault)

            }

        }

        /**
         * @param encodedString
         * @return bitmap (from given string)
         */
        fun StringToBitMap(encodedString: String): Bitmap? {
            try {
                val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
                return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            } catch (e: Exception) {
                e.message
                return null
            }

        }

        fun onCameraResult(uriSavedImage: Uri?, context: Context?, size: Int): Bitmap? {
            val selectedImage = uriSavedImage
            context?.getContentResolver()?.notifyChange(selectedImage, null)
            var resizedBitmap: Bitmap? = null

            try {
//                bitmap = android.provider.MediaStore.Images.Media
//                        .getBitmap(cr, selectedImage)
                var bitmap = BitmapFactory.decodeStream(context!!.getContentResolver().openInputStream(selectedImage))

                var outWidth: Int
                var outHeight: Int
                val inWidth = bitmap.width
                val inHeight = bitmap.height
                if (inWidth > inHeight) {
                    outWidth = size
                    outHeight = (inHeight * size) / inWidth
                } else {
                    outHeight = size
                    outWidth = (inWidth * size) / inHeight
                }

                resizedBitmap = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false)

                //                        viewHolder.imageView.setImageBitmap(bitmap);
                //            Toast.makeText(context, selectedImage.toString(),
                //                    Toast.LENGTH_LONG).show();
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to load", Toast.LENGTH_SHORT)
                        .show()
                Log.e("Camera", e.toString())
            }

            return resizedBitmap
        }

        fun onCameraResult(uriSavedImage: Uri?, context: Context?): Bitmap? {

            val selectedImage = uriSavedImage
            context?.getContentResolver()?.notifyChange(selectedImage, null)
            val cr = context?.getContentResolver()
            var bitmap: Bitmap? = null

            try {
                bitmap = android.provider.MediaStore.Images.Media
                        .getBitmap(cr, selectedImage)

                //                        viewHolder.imageView.setImageBitmap(bitmap);
                //            Toast.makeText(context, selectedImage.toString(),
                //                    Toast.LENGTH_LONG).show();
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to load", Toast.LENGTH_SHORT)
                        .show()
                Log.e("Camera", e.toString())
            }

            return bitmap
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fun onGalleryResult(timeStamp: String?, data: Intent?, context: Context): Bitmap? {
            var bitmap: Bitmap? = null

            if (data != null && context != null) {
                val path = data.getData()
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), path)
//                    saveBitmapToFile(bitmap, timeStamp)

                    //            }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return bitmap

        }


        fun onGalleryResult(timeStamp: String?, data: Intent?, context: Context, size: Int): Bitmap? {
            var bitmap: Bitmap? = null
            var resizedBitmap: Bitmap? = null

            if (data != null && context != null) {
                val path = data.getData()
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), path)
//                    saveBitmapToFile(bitmap, timeStamp)

                    var outWidth: Int
                    var outHeight: Int
                    val inWidth = bitmap.width
                    val inHeight = bitmap.height
                    if (inWidth > inHeight) {
                        outWidth = size
                        outHeight = (inHeight * size) / inWidth
                    } else {
                        outHeight = size
                        outWidth = (inWidth * size) / inHeight
                    }

                    resizedBitmap = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return resizedBitmap

        }



//
//        fun getBitmapCompress(bitmap: Bitmap) : Bitmap {
//            var byteArrayOutputStream =   ByteArrayOutputStream()
//            bitmap?.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
//            val bitmapData = byteArrayOutputStream.toByteArray()
//            return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
//        }


        ///////////////////////////////////////////////////////////////////////////
        fun saveBitmapToFile(bitmap: Bitmap?, timeStamp: String?): File {


            val root = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
            val files = File(root + "cp_d_life" + File.separator)
            if (!files.exists()) {
                files.mkdir()
            } else {
                for (file in files.listFiles())
                    if (!file.isDirectory)
                        file.delete()
            }
            val image = File(files, "$timeStamp.jpg")
//            var uriSavedImage = Uri.fromFile(image)
            var outputStream: OutputStream
            try {
                outputStream = FileOutputStream(image)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream!!.flush()
                outputStream!!.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            Log.i("", "save bitmap to temp image")
            return image
        }

        fun getFileMetaData(context: Context, uri: Uri?): FileMetaData? {
            val fileMetaData = FileMetaData()

            if ("file".equals(uri?.scheme, ignoreCase = true)) {
                val file = File(uri?.path)
                fileMetaData.displayName = file.name
                fileMetaData.size = file.length()
                fileMetaData.path = file.path

                return fileMetaData
            } else {
                val contentResolver = context.contentResolver
                val cursor = contentResolver.query(uri, null, null, null, null)
                fileMetaData.mimeType = contentResolver.getType(uri)

                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                        fileMetaData.displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))

                        if (!cursor.isNull(sizeIndex))
                            fileMetaData.size = cursor.getLong(sizeIndex)
                        else
                            fileMetaData.size = -1

                        try {
                            fileMetaData.path = cursor.getString(cursor.getColumnIndexOrThrow("_data"))
                        } catch (e: Exception) {
                            // DO NOTHING, _data does not exist
                        }

                        return fileMetaData
                    }
                } catch (e: Exception) {
                    Log.e("Exception", e.toString())
                } finally {
                    cursor?.close()
                }

                return null
            }
        }

        //region convertToBase64
        fun convertToBase64TypeFile(imagePath: String): String {

            var encodedImage = ""
            val file = File(imagePath)

            val b = ByteArray(file.length() as Int)
            try {
                val fileInputStream = FileInputStream(file)
                fileInputStream.read(b)
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT)
            } catch (e: FileNotFoundException) {
                println("File Not Found.")
                e.printStackTrace()
            } catch (e1: IOException) {
                println("Error Reading The File.")
                e1.printStackTrace()
            }

            Logging.e(TAG, "--------------convertToBase64TypeFile----------------")
            Logging.e(TAG, "convertToBase64 encodedImage $encodedImage")
            Logging.e(TAG, "----------------------------------------------------")
            return encodedImage


        }

        fun convertBitmapToBase64TypeFile(imagePath:String):String {
        var encodedImage = "";
        var file =  File(imagePath);
        if (file.exists()) {
//        byte[] b = new byte[(int) file.length()];
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(b);
            var matrix = Matrix()
            matrix.postRotate(ImageUtil.getImageOrientation(file.getAbsolutePath()))
            var myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
            var resized = Bitmap.createScaledBitmap(myBitmap,  (myBitmap.getWidth() * 0.3).toInt(),  (myBitmap.getHeight() * 0.3).toInt(), true)
            var bitmapRotate = Bitmap.createBitmap(resized, 0, 0, resized.getWidth(), resized.getHeight(), matrix, true)

            var byteArrayOutputStream =   ByteArrayOutputStream()
            bitmapRotate.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            var byteArray = byteArrayOutputStream.toByteArray()
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)

            Log.d(TAG, "------------convertBitmapToBase64TypeFile--------------")
            Log.d(TAG, "convertToBase64 encodedImage " + encodedImage)
            Log.d(TAG, "----------------------------------------------------")
        }
        return encodedImage


    }

     fun convertBitmapToBase64TypeFileProfile(imagePath:String):String {
        var encodedImage = "";
        var file =  File(imagePath);
        if (file.exists()) {
//        byte[] b = new byte[(int) file.length()];
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(b);
            var matrix =   Matrix()
            matrix.postRotate(ImageUtil.getImageOrientation(file.getAbsolutePath()))
            var myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
            var resized = Bitmap.createScaledBitmap(myBitmap, (myBitmap.getWidth() * 0.05).toInt(),  (myBitmap.getHeight() * 0.05).toInt(), true)
            var bitmapRotate = Bitmap.createBitmap(resized, 0, 0, resized.getWidth(), resized.getHeight(), matrix, true)

            var byteArrayOutputStream =  ByteArrayOutputStream()
            bitmapRotate.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            var byteArray = byteArrayOutputStream.toByteArray()
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)

            Log.d(TAG, "------------convertBitmapToBase64TypeFile--------------")
            Log.d(TAG, "convertToBase64 encodedImage " + encodedImage)
            Log.d(TAG, "----------------------------------------------------")
        }
        return encodedImage


    }

        fun rotateBitmap(bitmap:Bitmap?,imagestring:String):Bitmap? {
            var bitmapRotate:Bitmap?
            var exif:ExifInterface
            var rotation:Float
            rotation  =  getImageOrientation(imagestring)

            var matrix = Matrix()
            matrix.postRotate(rotation)
             var resized =     bitmap?.let {
                Bitmap.createScaledBitmap(bitmap, (it.getWidth().times(0.3)).toInt(),  (it.getHeight() * 0.3).toInt(), true)

            }
            bitmapRotate =resized?.let {
                Bitmap.createBitmap(resized, 0, 0, resized.getWidth(), it.getHeight(), matrix, true)
            }

            var byteArrayOutputStream =   ByteArrayOutputStream()
            bitmapRotate?.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)

            return bitmapRotate
        }

        fun getImageOrientation(imagePath: String): Float {
            var rotate = 0
            try {

                val imageFile = File(imagePath)
                val exif = ExifInterface(
                        imageFile.absolutePath)
                val orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL)

                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return rotate.toFloat()
        }


    }



}



