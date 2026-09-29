package com.smarthub.smartmaterial.sheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartBottomSheet {
    private final Context context;
    private final Dialog dialog;
    private final FrameLayout container;

    public SmartBottomSheet(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        container = new FrameLayout(context);
        container.setPadding(dp(24), dp(16), dp(24), dp(24));
        container.setBackground(background(SmartColors.SURFACE, 28));
        dialog.setContentView(container);
        dialog.setCanceledOnTouchOutside(true);
    }

    private int dp(float v) { return SmartDimensions.dp(context, v); }

    private GradientDrawable background(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadii(new float[]{dp(radius),dp(radius),dp(radius),dp(radius),0,0,0,0});
        return d;
    }

    public SmartBottomSheet setView(View view) {
        container.removeAllViews();
        container.addView(view, new FrameLayout.LayoutParams(-1, -2));
        return this;
    }

    public SmartBottomSheet setCancelable(boolean value) {
        dialog.setCancelable(value);
        return this;
    }

    public void show() {
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(-1, -2);
        }
    }

    public void dismiss() { dialog.dismiss(); }
    public Dialog getDialog() { return dialog; }
}
