package com.smarthub.smartmaterial.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartBottomSheet {
    private SmartBottomSheet() {}

    public static Dialog show(Context context, View content) {
        return show(context, content, true);
    }

    public static Dialog show(Context context, View content, boolean cancelable) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }

        Dialog dialog = new Dialog(context);

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(
                dp(context, 24),
                dp(context, 12),
                dp(context, 24),
                dp(context, 24)
        );
        container.setBackground(makeBackground(context));

        View handle = new View(context);
        GradientDrawable handleBackground = new GradientDrawable();
        handleBackground.setColor(
                SmartTheme.isDark(context)
                        ? Color.rgb(130, 130, 136)
                        : Color.rgb(115, 116, 122)
        );
        handleBackground.setCornerRadius(dp(context, 3));
        handle.setBackground(handleBackground);

        LinearLayout.LayoutParams handleParams = new LinearLayout.LayoutParams(
                dp(context, 36),
                dp(context, 5)
        );
        handleParams.gravity = Gravity.CENTER_HORIZONTAL;
        handleParams.bottomMargin = dp(context, 16);
        container.addView(handle, handleParams);

        if (content != null) {
            container.addView(content, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
        }

        dialog.setContentView(container);
        dialog.setCancelable(cancelable);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        dialog.setOnShowListener(d -> {
            Window w = dialog.getWindow();
            if (w != null) {
                w.setBackgroundDrawableResource(android.R.color.transparent);
                w.setGravity(Gravity.BOTTOM);
                w.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        });

        dialog.show();
        return dialog;
    }

    public static Dialog show(Context context, View content, int backgroundColor) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }

        Dialog dialog = new Dialog(context);

        FrameLayout container = new FrameLayout(context);
        container.setPadding(
                dp(context, 24),
                dp(context, 12),
                dp(context, 24),
                dp(context, 24)
        );

        GradientDrawable background = new GradientDrawable();
        background.setColor(backgroundColor);
        background.setCornerRadius(
                dp(context, 28)
        );
        container.setBackground(background);

        if (content != null) {
            container.addView(content, new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
        }

        dialog.setContentView(container);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.BOTTOM);
        }

        dialog.setOnShowListener(d -> {
            Window w = dialog.getWindow();
            if (w != null) {
                w.setBackgroundDrawableResource(android.R.color.transparent);
                w.setGravity(Gravity.BOTTOM);
                w.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        });

        dialog.show();
        return dialog;
    }

    private static GradientDrawable makeBackground(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(SmartTheme.surface(context));
        drawable.setCornerRadii(new float[] {
                dp(context, 28), dp(context, 28),
                dp(context, 28), dp(context, 28),
                0, 0,
                0, 0
        });
        return drawable;
    }

    private static int dp(Context context, float value) {
        return Math.round(
                value * context.getResources().getDisplayMetrics().density
        );
    }
}
