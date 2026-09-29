package com.smarthub.smartmaterial.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartDialog {
    private final Context context;
    private final LinearLayout content;
    private final TextView titleView;
    private final TextView messageView;
    private final TextView positiveButton;
    private final TextView negativeButton;
    private final Dialog dialog;

    public SmartDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.getWindow();
        
        LinearLayout root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(24), dp(20), dp(24), dp(8));
        root.setBackground(background(SmartColors.SURFACE, 28));

        titleView = text(20, SmartColors.ON_SURFACE);
        titleView.setTypeface(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD);
        root.addView(titleView, new LinearLayout.LayoutParams(-1, -2));

        messageView = text(16, SmartColors.ON_SURFACE_VARIANT);
        LinearLayout.LayoutParams mp = new LinearLayout.LayoutParams(-1, -2);
        mp.topMargin = dp(12);
        root.addView(messageView, mp);

        content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.topMargin = dp(12);
        root.addView(content, cp);

        LinearLayout actions = new LinearLayout(context);
        actions.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        negativeButton = buttonText();
        positiveButton = buttonText();
        actions.addView(negativeButton, new LinearLayout.LayoutParams(-2, dp(48)));
        actions.addView(positiveButton, new LinearLayout.LayoutParams(-2, dp(48)));
        root.addView(actions);

        dialog.setContentView(root);
        dialog.setCanceledOnTouchOutside(true);
    }

    private TextView text(float size, int color) {
        TextView v = new TextView(context);
        v.setTextSize(size);
        v.setTextColor(color);
        return v;
    }

    private TextView buttonText() {
        TextView v = text(14, SmartColors.PRIMARY);
        v.setGravity(Gravity.CENTER);
        v.setTypeface(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD);
        v.setPadding(dp(12), 0, dp(12), 0);
        v.setVisibility(View.GONE);
        return v;
    }

    private GradientDrawable background(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private int dp(float value) { return SmartDimensions.dp(context, value); }

    public SmartDialog setTitle(CharSequence title) { titleView.setText(title); return this; }
    public SmartDialog setMessage(CharSequence message) { messageView.setText(message); return this; }

    public SmartDialog setView(View view) {
        content.removeAllViews();
        content.addView(view, new LinearLayout.LayoutParams(-1, -2));
        return this;
    }

    public SmartDialog setPositiveButton(CharSequence text, View.OnClickListener listener) {
        positiveButton.setText(text);
        positiveButton.setVisibility(View.VISIBLE);
        positiveButton.setOnClickListener(listener);
        return this;
    }

    public SmartDialog setNegativeButton(CharSequence text, View.OnClickListener listener) {
        negativeButton.setText(text);
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setOnClickListener(listener);
        return this;
    }

    public SmartDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public void show() {
        dialog.show();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(
                Math.min(dp(360), (int)(context.getResources().getDisplayMetrics().widthPixels * 0.90f)),
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }

    public void dismiss() { dialog.dismiss(); }
    public Dialog getDialog() { return dialog; }
}
