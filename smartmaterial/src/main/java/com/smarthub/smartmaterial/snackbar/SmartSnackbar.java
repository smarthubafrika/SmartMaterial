package com.smarthub.smartmaterial.snackbar;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class SmartSnackbar {
    private SmartSnackbar() {}

    public static void show(View anchor, CharSequence message) {
        show(anchor, message, 3000L);
    }

    public static void show(View anchor, CharSequence message, long durationMs) {
        if (anchor == null || anchor.getContext() == null) return;
        View root = anchor.getRootView();
        if (!(root instanceof ViewGroup)) return;

        ViewGroup parent = (ViewGroup) root;
        TextView bar = new TextView(anchor.getContext());
        int horizontal = dp(anchor, 16);

        bar.setText(message);
        bar.setTextColor(Color.WHITE);
        bar.setTextSize(14);
        bar.setGravity(Gravity.CENTER_VERTICAL);
        bar.setPadding(horizontal, 0, horizontal, 0);
        bar.setBackground(round(Color.rgb(45, 47, 52), dp(anchor, 12)));
        bar.setElevation(dp(anchor, 6));

        int width = parent.getWidth() > 0
                ? Math.min(dp(anchor, 520), Math.max(dp(anchor, 200), parent.getWidth() - dp(anchor, 32)))
                : ViewGroup.LayoutParams.MATCH_PARENT;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, dp(anchor, 52));
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = dp(anchor, 24);

        parent.addView(bar, params);
        bar.setAlpha(0f);
        bar.animate().alpha(1f).setDuration(180).start();

        bar.postDelayed(() -> {
            bar.animate().alpha(0f).setDuration(180)
                    .withEndAction(() -> {
                        if (bar.getParent() != null) parent.removeView(bar);
                    }).start();
        }, Math.max(500L, durationMs));
    }

    private static GradientDrawable round(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private static int dp(View v, float value) {
        return Math.round(value * v.getResources().getDisplayMetrics().density);
    }
}
