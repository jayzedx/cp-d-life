package com.mdc.cpfit.listener;

import android.support.annotation.NonNull;

/**
 * Created by Suphadate_Noy on 11/11/2016.
 */

public interface OnPermissionsResult {
    public void onPermissionsResult(int requestCode, @NonNull String[] permissions,
                                    @NonNull int[] grantResults);
}