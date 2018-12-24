package com.mdc.cpfit.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.mdc.cpfit.listener.OnPermissionsResult;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * Created by suphadate_noy on 6/23/17.
 */

public class Permission implements ActivityCompat.OnRequestPermissionsResultCallback {


    public static OnPermissionsResult onPermissionsResult = null;
    public static final int REQUEST_PERMISSION_ALL = 999;
    private static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public interface CallBackPermission {
        void allowPermission();

        void deninePermissiob();
    }

    boolean per = false;
    boolean perDine = false;

    public void requestPermission(ActivityUnit actvity, final CallBackPermission callBack, String... permission) {
        RxPermissions rxPermissions = new RxPermissions(actvity); // where this is an Activity instance
        // Must be done during an initialization phase like onCreate
        rxPermissions
                .requestEach(permission)
                .subscribe(permission1 -> {

                    if (permission1.granted) {
                        // `permission.name` is granted !
                        if (!per) {
                            per = true;
                            callBack.allowPermission();
                        }

                    } else if (permission1.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        if (!perDine) {
                            perDine = true;
                            callBack.deninePermissiob();
                        }
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        if (!perDine) {
                            perDine = true;
                            callBack.deninePermissiob();
                        }
                    }
                }, throwable -> Log.e("TAG", "onError," + throwable.getMessage()));
    }

    public static boolean Camera(Activity context) {


        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions(context, PERMISSIONS, REQUEST_PERMISSION_ALL);
            return false;
        } else {
            return true;
        }
    }

    public void permission(Activity activity, String[] permission) {
        Context context;
        if (activity == null) {
            context = Contextor.Companion.getInstance().getContextApp();
            activity = ActivityUnit.getActivity();
        } else {
            context = activity.getApplicationContext();
        }
        if (!hasPermissions(context, permission)) {
            ActivityCompat.requestPermissions(activity, permission, REQUEST_PERMISSION_ALL);
        }
    }

    public void statusCheckGPS(Activity activity) {

        if (activity == null) {
            activity = ActivityUnit.getActivity();
        }

        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps(activity);
        }
    }

    private void buildAlertMessageNoGps(Activity activity) {
        if (activity == null) {
            activity = ActivityUnit.getActivity();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final Activity finalActivity = activity;
        builder.setTitle("Need LOCATION Permission")
                .setMessage("This app needs ACCESS_FINE_LOCATION permission.")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        finalActivity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSION_ALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permissions granted.
//                    doPermissionGrantedStuffs();
                } else {
                    // no permissions granted.
                    if (onPermissionsResult != null) {
                        onPermissionsResult.onPermissionsResult(requestCode, permissions, grantResults);
                    }
                }
                return;
            }
        }
    }


    public void StrictModePolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.allowThreadDiskReads();
        StrictMode.allowThreadDiskWrites();
    }


}

