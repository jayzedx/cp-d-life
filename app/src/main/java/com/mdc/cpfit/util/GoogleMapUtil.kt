package com.mdc.cpfit.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import android.support.v4.os.ConfigurationCompat.getLocales
import android.os.Build
import java.util.*


class GoogleMapUtil {
    companion object {

        fun getLocationName(location: LatLng, mMap: GoogleMap?): Address {

            var address = Address(getCurrentLocale(Contextor.instance.ContextApp!!))
            val geocoder = Geocoder(Contextor.instance.ContextApp)
            val TAG = GoogleMapUtil::class.simpleName

            try {
                val addressList = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addressList.size != 0) {
                    address = addressList[0]
                    Logging.d(TAG, "setMarkerList: " + addressList.toString())
                    Logging.d(TAG, "address: " + address.countryCode)
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            //        return location;
            return address
        }

        fun getCurrentLocale(context: Context): Locale {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.getResources().getConfiguration().getLocales().get(0)
            } else {

                context.getResources().getConfiguration().locale
            }
        }

        fun getDistance2Postion(start: Location?, stop: Location?): Float? {
            var location = start?.distanceTo(stop)?.div(1000)

            return location?.let { NumberUtil.roundFloat(it, 2) }
        }
    }

}