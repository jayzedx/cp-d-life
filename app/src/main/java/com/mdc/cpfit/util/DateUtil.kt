package com.mdc.cpfit.util

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {


    companion object {

        val formatServer = "dd/MM/yyyy"
        val formatShow = "dd MMMM yyyy"


        fun convertDateFormatServer(date: String, pattern: String = formatServer): String {
            val dateTitle = SimpleDateFormat(formatShow, Locale.getDefault())
            var formatter = SimpleDateFormat(pattern)
            var date = formatter.parse(date)
            val c = Calendar.getInstance()
            c.time = date
            var locale = Locale.getDefault()
            if (locale == Locale("TH")) {
                c.add(Calendar.YEAR, 543)
            }
            date = c.time
            return dateTitle.format(date)
        }




//
//        fun convertDateFormat(date: String): String {
//            val dateTitle = SimpleDateFormat("dd MMMM yyyy")
//            var formatter = SimpleDateFormat("yyyy-MM-dd")
//            val date = formatter.parse(date)
//            return dateTitle.format(date)
//        }
//        fun convertDateFormat(date: String,format: String): String {
//            val dateTitle = SimpleDateFormat(format)
//            var formatter = SimpleDateFormat("yyyy-MM-dd")
//            val date = formatter.parse(date)
//            return dateTitle.format(date)
//        }
//        fun convertDateFormat(date: String, addMounth: Int, addYear: Int): String {
////            val dateTitle = SimpleDateFormat("dd MMMM yyyy")
//
//            var formatter = SimpleDateFormat("dd-MM-yyyy")
//            val date = formatter.parse(date)
//            val cal = Calendar.getInstance()
//            cal.time = date
//
//            if (addMounth > 0) {
//                cal.add(Calendar.MONTH, addMounth)
//            }
//            if (addYear > 0) {
//                cal.add(Calendar.YEAR, addYear)
//            }
//            return formatter.format(cal.getTime())
//        }
//
//
//        fun convertDateFormatformServer(date: String, pattern: String = formatServer): String {
//            val dateTitle = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
//            var formatter = SimpleDateFormat(pattern)
//            var date = formatter.parse(date)
//            val c = Calendar.getInstance()
//            c.time = date
//
//            var locale = Locale.getDefault()
//            if (locale == Locale("TH")) {
//                c.add(Calendar.YEAR, 543)
//            }
//
//            date = c.time
//            return dateTitle.format(date)
//        }
//
//
//        fun convertDateToTimeStamp(date: String): Long {
//            var formatter = SimpleDateFormat("yyyy-MM-dd")
//            val date = formatter.parse(date)
//            return date.time
//        }
//
//        fun splitTime(time: String): String {
//            var timeTemp = time.split(":")
//            var hour = timeTemp[0]
//            var min = timeTemp[1]
//
//            return hour + ":" + min
//        }
//
//
//        fun convertToDate(createDateAsString: String?, format: String): String {
//            var createDateAsString: String? = null
//            createDateAsString = createDateAsString
//            //if (NumberUtil.IsNumber(_createDateString)) {
//
//            //20160821 18:00:00 format
//            val format = SimpleDateFormat(format)
//            var date: Date? = null
//            var timestamp: Long? = 0
//            try {
//                date = format.parse(createDateAsString)
//                timestamp = date?.time
//
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            } catch (e: NullPointerException) {
//                e.printStackTrace()
//            }
//
//            val currentTimeStampLong = System.currentTimeMillis()
//
//            if (currentTimeStampLong > timestamp!! && currentTimeStampLong - timestamp < 86400000) {
//                createDateAsString = DateUtils.getRelativeTimeSpanString(timestamp, currentTimeStampLong, 0).toString()
//            } else {
//                val dateCreateDate = Date(timestamp)
//                val dateFormat = SimpleDateFormat("dd MMM h:mm a")
//                createDateAsString = dateFormat.format(dateCreateDate)
//            }
//            //}
//            return createDateAsString ?: ""
//        }
//
//        fun currentDate(): CharSequence? {
//            var cal = Calendar.getInstance()
//            val dateTitle = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
//            return dateTitle.format(cal.time)
//        }
//
//        fun currentDate(format: String): CharSequence? {
//            var cal = Calendar.getInstance()
//            val dateTitle = SimpleDateFormat(format, Locale.getDefault())
//            return dateTitle.format(cal.time)
//        }
//
//        fun currentDateAddDate(mounth: Int, year: Int): String {
////            val dateTitle = SimpleDateFormat("dd MMMM yyyy")
//
//            var formatter = SimpleDateFormat("dd-MM-yyyy")
//            val cal = Calendar.getInstance()
//            val date = cal.time
//
//            if (mounth > 0) {
//                cal.time = date
//                cal.add(Calendar.MONTH, mounth)
//            }
//            if (year > 0) {
//                cal.time = date
//                cal.add(Calendar.YEAR, year)
//            }
//            return formatter.format(cal.getTime())
//        }
//
//
    }


}