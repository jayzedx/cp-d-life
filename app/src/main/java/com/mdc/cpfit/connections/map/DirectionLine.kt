package com.mdc.cpfit.connections.map

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.animation.LinearInterpolator
import com.mdc.cpfit.connections.APIService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.mdc.cpfit.R
import com.mdc.cpfit.connections.RespondListener
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap


class DirectionLine(var context: Context) {


    private var animator: ValueAnimator? = null
    private val listLatLng = ArrayList<LatLng>()
    private var blackPolyLine: Polyline? = null
    private var greyPolyLine: Polyline? = null

//
//    public fun getDirection(currentLatlng: LatLng, destLatlng: LatLng?, mMap: GoogleMap?) {
//        APIService.instance.getDirection(currentLatlng, destLatlng, object : RespondListener {
//            override fun onSuccess(resquest: Any) {
//                val jObject = resquest as JSONObject
//
//                var routes: List<List<HashMap<String, String>>>? = null
//
//                try {
//                    val parser = DirectionsJSONParser()
//
//                    // Starts parsing data
//                    routes = parser.parse(jObject)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//                drawPolyline(routes, mMap)
//
//            }
//
//            override fun onFailure(resquest: Any) {
//                if (resquest != null) {
//
//                }
//            }
//        })
//    }


    private fun drawPolyline(result: List<List<HashMap<String, String>>>?, mMap: GoogleMap?) {
        ClearPolyline()
        var points: ArrayList<LatLng>? = null
        val lineOptions = PolylineOptions()

        // Traversing through all the routes
        for (i in result!!.indices) {
            points = ArrayList()


            // Fetching i-th route
            val path = result!![i]

            // Fetching all the points in i-th route
            for (j in path.indices) {
                val point = path[j]

                val lat = java.lang.Double.parseDouble(point["lat"])
                val lng = java.lang.Double.parseDouble(point["lng"])
                val position = LatLng(lat, lng)

                points.add(position)
            }
            this.listLatLng.clear()
            this.listLatLng.addAll(points)
        }

        lineOptions.width(7f)
        lineOptions.color(ContextCompat.getColor(context, R.color.Purple_Monster))
        lineOptions.startCap(SquareCap())
        lineOptions.endCap(SquareCap())
        lineOptions.jointType(JointType.ROUND)
        blackPolyLine = mMap?.addPolyline(lineOptions)


        val greyOptions = PolylineOptions()
        greyOptions.width(7f)
        greyOptions.color(ContextCompat.getColor(context, R.color.Purple_Mimosa))
        greyOptions.startCap(SquareCap())
        greyOptions.endCap(SquareCap())
        greyOptions.jointType(JointType.ROUND)
        greyPolyLine = mMap?.addPolyline(greyOptions)
        animatePolyLine()
    }

    private fun ClearPolyline() {
        if (animator != null)
            animator?.cancel()
        this.listLatLng.clear()

        if (greyPolyLine != null) {
            greyPolyLine?.remove()
            greyPolyLine = null
        }

        if (blackPolyLine != null) {
            blackPolyLine?.remove()
            blackPolyLine = null
        }
    }

    private fun animatePolyLine() {
        animator = ValueAnimator.ofInt(0, 100)
        animator?.setDuration(300)
        animator?.setInterpolator(LinearInterpolator())
        animator?.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animator ->
            if (greyPolyLine != null && blackPolyLine != null) {

                val latLngList = blackPolyLine?.getPoints()
                val initialPointSize = latLngList!!.size
                val animatedValue = animator.animatedValue as Int
                val newPoints = animatedValue * listLatLng.size / 100

                if (initialPointSize < newPoints) {
                    latLngList?.addAll(listLatLng.subList(initialPointSize, newPoints))
                    blackPolyLine?.setPoints(latLngList)
                }
            }
        })

        animator?.addListener(polyLineAnimationListener)
        animator?.start()

    }

    private var polyLineAnimationListener: Animator.AnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {

            //            addMarker(listLatLng.get(listLatLng.size()-1));
        }

        override fun onAnimationEnd(animator: Animator) {
            if (greyPolyLine != null && blackPolyLine != null) {
                var blackLatLng = blackPolyLine?.getPoints()
                var greyLatLng = greyPolyLine?.getPoints()

                greyLatLng?.clear()
                greyLatLng?.addAll(blackLatLng as List<LatLng>)
                blackLatLng?.clear()

                blackPolyLine?.setPoints(blackLatLng)
                greyPolyLine?.setPoints(greyLatLng)

                blackPolyLine?.setZIndex(2f)

                animator.start()
                animatePolyLine()
            }
        }

        override fun onAnimationCancel(animator: Animator) {

        }

        override fun onAnimationRepeat(animator: Animator) {


        }
    }
}