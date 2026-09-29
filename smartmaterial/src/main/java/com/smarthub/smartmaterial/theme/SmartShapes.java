package com.smarthub.smartmaterial.theme;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

public final class SmartShapes {
    private SmartShapes() {}

    public static GradientDrawable rounded(Context context, int color, float radiusDp) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(SmartDimensions.dp(context, radiusDp));
        return d;
    }

    public static GradientDrawable outlined(Context context, int color, int strokeColor, float radiusDp, float strokeDp) {
        GradientDrawable d = rounded(context, color, radiusDp);
        d.setStroke(SmartDimensions.dp(context, strokeDp), strokeColor);
        return d;
    }

    public static GradientDrawable pill(Context context, int color) {
        return rounded(context, color, 999f);
    }

    public static GradientDrawable circle(Context context, int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setShape(GradientDrawable.OVAL);
        return d;
    }
}