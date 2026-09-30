package com.smarthub.smartmaterial.theme;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public final class SmartTheme {
    private static final String FONT_PATH = "fonts/";

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
        light(view);
    }

    public static void light(TextView view) {
        if (view != null) {
            view.setTypeface(getTypeface(view.getContext(), "google_sans_light.ttf"));
        }
    }

    public static void medium(TextView view) {
        if (view != null) {
            view.setTypeface(getTypeface(view.getContext(), "google_sans_medium.ttf"));
        }
    }

    public static void bold(TextView view) {
        if (view != null) {
            view.setTypeface(getTypeface(view.getContext(), "google_sans_bold.ttf"));
        }
    }

    public static Typeface getLightTypeface(Context context) {
        return getTypeface(context, "google_sans_light.ttf");
    }

    public static Typeface getMediumTypeface(Context context) {
        return getTypeface(context, "google_sans_medium.ttf");
    }

    public static Typeface getBoldTypeface(Context context) {
        return getTypeface(context, "google_sans_bold.ttf");
    }

    private static Typeface getTypeface(Context context, String fileName) {
        try {
            return Typeface.createFromAsset(context.getAssets(), FONT_PATH + fileName);
        } catch (Exception ignored) {
            return Typeface.DEFAULT;
        }
    }

    public static void clickable(View view, CharSequence description) {
        view.setClickable(true);
        view.setFocusable(true);
        if (description != null) view.setContentDescription(description);
    }
}
