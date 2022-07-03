/*
 * Copyright (C) 2018-2022 crDroid Android Project
 * Copyright (C) 2022 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.evolution.oplus.OPlusExtras.slider;

import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;

public final class RefreshController extends SliderControllerBase {

    public static final int ID = 7;

    private static final String TAG = "RefreshController";

    private static final int REFRESH_AUTO = 66;
    private static final int REFRESH_MIN = 67;
    private static final int REFRESH_MAX = 68;

    public RefreshController(Context context) {
        super(context);
    }

    @Override
    protected int processAction(int action) {
        Log.d(TAG, "slider action: " + action);
        switch (action) {
            case REFRESH_AUTO:
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 120f);
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 60f);
                 return SliderConstants.MODE_REFRESH_AUTO;
            case REFRESH_MIN:
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 60f);
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 60f);
                 return SliderConstants.MODE_REFRESH_MIN;
            case REFRESH_MAX:
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 120f);
                 Settings.System.putFloat(mContext.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 120f);
                 return SliderConstants.MODE_REFRESH_MAX;
            default:
                return 0;
       }
    }

    @Override
    public void reset() {
         Settings.System.putFloat(mContext.getContentResolver(), Settings.System.PEAK_REFRESH_RATE, 120f);
         Settings.System.putFloat(mContext.getContentResolver(), Settings.System.MIN_REFRESH_RATE, 60f);
    }

    private boolean writeSettings(String key, int value) {
        return Settings.System.putIntForUser(mContext.getContentResolver(),
                    key, value, UserHandle.USER_CURRENT);
    }
}
