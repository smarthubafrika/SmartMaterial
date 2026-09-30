package com.smarthub.smartmaterial.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public final class SmartDialog {
    private SmartDialog() {}

    public static AlertDialog show(Context context, CharSequence title, CharSequence message) {
        return show(context, title, message, null, null);
    }

    public static AlertDialog show(
            Context context,
            CharSequence title,
            CharSequence message,
            CharSequence positiveText,
            DialogInterface.OnClickListener positiveListener) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        positiveText == null ? "OK" : positiveText,
                        positiveListener)
                .create();

        dialog.setOnShowListener(d -> {
            TextView messageView = dialog.findViewById(android.R.id.message);
            if (messageView != null) {
                messageView.setTextColor(SmartTheme.onSurface(context));
                messageView.setTextSize(16);
                SmartTheme.light(messageView);
            }

            int positiveId = dialog.getContext().getResources()
                    .getIdentifier("button1", "id", "android");
            View positive = dialog.findViewById(positiveId);
            if (positive instanceof TextView) {
                ((TextView) positive).setTextColor(SmartTheme.primary(context));
                SmartTheme.medium((TextView) positive);
                ((TextView) positive).setAllCaps(false);
            }
        });

        dialog.show();
        return dialog;
    }

    public static AlertDialog showView(
            Context context,
            CharSequence title,
            View content,
            CharSequence positiveText,
            DialogInterface.OnClickListener positiveListener) {

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(dp(context, 24), dp(context, 8),
                dp(context, 24), dp(context, 8));
        container.setBackground(round(
                SmartTheme.surface(context),
                dp(context, 28)));

        if (title != null) {
            TextView titleView = new TextView(context);
            titleView.setText(title);
            titleView.setTextColor(SmartTheme.onSurface(context));
            SmartTheme.bold(titleView);
            titleView.setTextSize(20);
            titleView.setGravity(Gravity.CENTER_VERTICAL);
            titleView.setPadding(0, dp(context, 8), 0, dp(context, 8));
            container.addView(titleView, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        if (content != null) {
            container.addView(content, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(container)
                .setPositiveButton(
                        positiveText == null ? "OK" : positiveText,
                        positiveListener)
                .create();

        dialog.setOnShowListener(d -> {
            int positiveId = dialog.getContext().getResources()
                    .getIdentifier("button1", "id", "android");
            View positive = dialog.findViewById(positiveId);
            if (positive instanceof TextView) {
                ((TextView) positive).setTextColor(SmartTheme.primary(context));
                ((TextView) positive).setAllCaps(false);
            }
        });

        dialog.show();
        return dialog;
    }

    private static GradientDrawable round(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
