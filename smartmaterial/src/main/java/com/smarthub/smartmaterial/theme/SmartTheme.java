package com.smarthub.smartmaterial.theme;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public final class SmartTheme {
    private SmartTheme() {}

    public static void applyText(TextView view, float sp) {
        view.setTextSize(sp);
        view.setTextColor(isDark(view.getContext()) ? SmartDarkColors.ON_SURFACE : SmartColors.ON_SURFACE);
        view.setTypeface(Typeface.create("sans", Typeface.NORMAL));
    }

    public static int dp(Context context, float value) {
        return SmartDimensions.dp(context, value);
    }

    public static boolean isDark(Context context) {
        return (context.getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    public static int surface(Context context) {
        return isDark(context) ? SmartDarkColors.SURFACE : SmartColors.SURFACE;
    }

    public static int onSurface(Context context) {
        return isDark(context) ? SmartDarkColors.ON_SURFACE : SmartColors.ON_SURFACE;
    }

    public static int primary(Context context) {
        return isDark(context) ? SmartDarkColors.PRIMARY : SmartColors.PRIMARY;
    }

    public static int primaryContainer(Context context) {
        return isDark(context) ? SmartDarkColors.PRIMARY_CONTAINER : SmartColors.PRIMARY_CONTAINER;
    }

    public static void makeClickable(View view) {
        view.setClickable(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(false);
    }

    public static void applyContentDescription(View view, CharSequence description) {
        if (description != null) view.setContentDescription(description);
    }
}