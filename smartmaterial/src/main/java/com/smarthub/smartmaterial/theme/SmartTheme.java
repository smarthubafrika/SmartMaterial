package com.smarthub.smartmaterial.theme;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public final class SmartTheme {
    private SmartTheme() {}

    public static void applyText(TextView view, float sp) {
        view.setTextSize(sp);
        view.setTextColor(SmartColors.ON_SURFACE);
        view.setTypeface(Typeface.create("sans", Typeface.NORMAL));
    }

    public static int dp(Context context, float value) {
        return SmartDimensions.dp(context, value);
    }

    public static void makeClickable(View view) {
        view.setClickable(true);
        view.setFocusable(true);
    }
}