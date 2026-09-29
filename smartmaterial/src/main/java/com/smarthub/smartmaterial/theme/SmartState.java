package com.smarthub.smartmaterial.theme;

import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;

public final class SmartState {
    private SmartState() {}

    public static void applyRipple(View view, int baseColor, int rippleColor) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(baseColor);
        background.setCornerRadius(SmartDimensions.dp(view.getContext(), 12));
        view.setBackground(new RippleDrawable(ColorStateList.valueOf(rippleColor), background, null));
        view.setClickable(true);
        view.setFocusable(true);
    }

    public static void accessible(View view, CharSequence description) {
        if (description != null) view.setContentDescription(description);
        view.setFocusable(true);
    }
}