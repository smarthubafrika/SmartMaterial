package com.smarthub.smartmaterial;

import android.content.Context;

public final class SmartMaterial {
    private SmartMaterial() {}

    public static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
