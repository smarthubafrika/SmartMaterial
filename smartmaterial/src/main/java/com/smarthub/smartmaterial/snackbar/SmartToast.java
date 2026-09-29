package com.smarthub.smartmaterial.snackbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public final class SmartToast {
    private SmartToast() {}

    public static void show(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence message, int duration) {
        if (context == null) return;
        TextView view = new TextView(context);
        view.setText(message);
        view.setTextColor(Color.WHITE);
        view.setTextSize(14);
        view.setGravity(Gravity.CENTER);
        view.setPadding(dp(context, 18), dp(context, 12), dp(context, 18), dp(context, 12));

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(SmartColors.ON_SURFACE);
        bg.setCornerRadius(dp(context, 12));
        view.setBackground(bg);

        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, dp(context, 64));
        toast.show();
    }

    private static int dp(Context context, float value) {
        return SmartDimensions.dp(context, value);
    }
}
