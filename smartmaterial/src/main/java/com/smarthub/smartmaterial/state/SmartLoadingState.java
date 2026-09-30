package com.smarthub.smartmaterial.state;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.progress.SmartCircularProgress;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartLoadingState extends LinearLayout {
    private final SmartCircularProgress progress;
    private final TextView message;

    public SmartLoadingState(Context c) {
        super(c);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPadding(dp(c, 24), dp(c, 32), dp(c, 24), dp(c, 32));

        progress = new SmartCircularProgress(c);
        progress.setIndeterminate(true);
        addView(progress, new LayoutParams(dp(c, 64), dp(c, 64)));

        message = new TextView(c);
        message.setText("Loading...");
        message.setTextSize(14);
        message.setTextColor(SmartTheme.onSurface(c));
        SmartTheme.light(message);
        message.setGravity(Gravity.CENTER);
        message.setPadding(0, dp(c, 12), 0, 0);
        addView(message, new LayoutParams(-1, -2));
    }

    public SmartLoadingState setMessage(CharSequence v) {
        message.setText(v);
        return this;
    }

    public SmartLoadingState setProgressColor(int color) {
        progress.setProgressColor(color);
        return this;
    }

    private int dp(Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
