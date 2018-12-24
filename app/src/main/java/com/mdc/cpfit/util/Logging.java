package com.mdc.cpfit.util;

import android.util.Log;

/**
 * Created by mdc_macbook on 4/10/2017 AD.
 */

public class Logging {
    public static boolean InnitLog = false;

    public static void StartLogging() {
        InnitLog = true;
    }
    public static void StopLogging() {
        InnitLog = false;
    }

    public static void d(String tag, String meg) {
        if (InnitLog) {
            Log.d(tag, meg);
        }
    }

    public static void d(String tag, String meg, Throwable throwable) {
        if (InnitLog) {
            Log.d(tag, meg, throwable);
        }
    }

    public static void e(String tag, String meg) {
        if (InnitLog) {
            Log.e(tag, meg);
        }
    }

    public static void e(String tag, String meg, Throwable throwable) {
        if (InnitLog) {
            Log.e(tag, meg, throwable);
        }
    }

    public static void v(String tag, String meg) {
        if (InnitLog) {
            Log.v(tag, meg);
        }
    }

    public static void v(String tag, String meg, Throwable throwable) {
        if (InnitLog) {
            Log.v(tag, meg, throwable);
        }
    }

    public static void i(String tag, String meg) {
        if (InnitLog) {
            Log.i(tag, meg);
        }
    }

    public static void i(String tag, String meg, Throwable throwable) {
        if (InnitLog) {
            Log.i(tag, meg, throwable);
        }
    }

    public static void w(String tag, String meg) {
        if (InnitLog) {
            Log.w(tag, meg);
        }
    }

    public static void w(String tag, String meg, Throwable throwable) {
        if (InnitLog) {
            Log.w(tag, meg, throwable);
        }
    }

}
