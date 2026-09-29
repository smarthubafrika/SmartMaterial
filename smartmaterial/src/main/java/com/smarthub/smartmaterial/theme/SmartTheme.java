package com.smarthub.smartmaterial.theme;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public final class SmartTheme {
    private SmartTheme() {}

    public static boolean isDark(Context context) {
        return (context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    public static int surface(Context context) {
        return isDark(context) ? Color.rgb(18, 19, 23) : SmartColors.SURFACE;
    }

    public static int onSurface(Context context) {
        return isDark(context) ? Color.rgb(227, 225, 229) : SmartColors.ON_SURFACE;
    }

    public static int primary(Context context) {
        return isDark(context) ? Color.rgb(174, 198, 255) : SmartColors.PRIMARY;
    }

    public static void text(TextView view, float sp) {
        view.setTextSize(sp);
        view.setTextColor(onSurface(view.getContext()));
    }

    public static void clickable(View view, CharSequence description) {
        view.setClickable(true);
        view.setFocusable(true);
        if (description != null) view.setContentDescription(description);
    }
}
