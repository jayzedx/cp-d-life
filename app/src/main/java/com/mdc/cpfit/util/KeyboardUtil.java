package com.mdc.cpfit.util;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * Created by suphadate_noy on 9/13/17.
 */

public class KeyboardUtil  {
    private static final String TAG = KeyboardUtil.class.getSimpleName();

    public static void hideSoftKeyboard(Activity activity) {
        try {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            Logging.e(TAG + "NullPointerException", e.toString());
        }

    }

    public static void hideKeyboardOnTuchScreen(View view, final Activity activity) {
        if (view != null) {
            // Set up touch listener for non-text box views to hide keyboard.
            if (!(view instanceof EditText)) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        hideSoftKeyboard(activity);
                        return false;
                    }
                });
            }

            //If a layout container, iterate over children and seed recursion.
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View innerView = ((ViewGroup) view).getChildAt(i);
                    hideKeyboardOnTuchScreen(innerView,activity);
                }
            }
        }
    }
}
