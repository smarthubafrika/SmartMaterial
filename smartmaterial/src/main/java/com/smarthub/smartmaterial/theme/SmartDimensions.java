package com.smarthub.smartmaterial.theme;

import android.content.Context;

public final class SmartDimensions {
    private SmartDimensions() {}

    public static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }

    public static final float BUTTON_HEIGHT = 48f;
    public static final float CORNER_MEDIUM = 12f;
    public static final float CORNER_LARGE = 16f;
    public static final float CARD_ELEVATION = 1f;
}