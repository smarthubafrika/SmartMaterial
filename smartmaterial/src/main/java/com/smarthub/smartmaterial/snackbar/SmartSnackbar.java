package com.smarthub.smartmaterial.snackbar;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public final class SmartSnackbar {
    private SmartSnackbar() {}

    public static void show(View anchor, CharSequence message) {
        show(anchor, message, 3000);
    }

    public static void show(View anchor, CharSequence message, long duration) {
        if (anchor == null || anchor.getContext() == null) return;

        ViewGroup parent = findParent(anchor);
        if (parent == null) {
            Toast.makeText(anchor.getContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }

        TextView snackbar = new TextView(anchor.getContext());
        snackbar.setText(message);
        snackbar.setTextSize(14);
        snackbar.setTextColor(Color.WHITE);
        snackbar.setGravity(Gravity.CENTER_VERTICAL);
        snackbar.setPadding(SmartDimensions.dp(anchor.getContext(),16),
                0, SmartDimensions.dp(anchor.getContext(),16), 0);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(SmartColors.ON_SURFACE);
        bg.setCornerRadius(SmartDimensions.dp(anchor.getContext(),12));
        snackbar.setBackground(bg);
        snackbar.setElevation(SmartDimensions.dp(anchor.getContext(),6));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                SmartDimensions.dp(anchor.getContext(),48)
        );

        parent.addView(snackbar, params);
        snackbar.setAlpha(0f);
        snackbar.animate().alpha(1f).setDuration(180).start();

        snackbar.postDelayed(() -> {
            snackbar.animate().alpha(0f).setDuration(180).withEndAction(() -> {
                if (snackbar.getParent() != null) parent.removeView(snackbar);
            }).start();
        }, Math.max(500, duration));
    }

    private static ViewGroup findParent(View view) {
        View current = view;
        while (current.getParent() instanceof View) {
            current = (View) current.getParent();
        }
        return current instanceof ViewGroup ? (ViewGroup) current : null;
    }
}
