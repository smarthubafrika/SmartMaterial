package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartTopAppBar extends LinearLayout {
    private final ImageButton navigationButton;
    private final TextView titleView;
    private final LinearLayout actions;

    public SmartTopAppBar(Context context) { super(context); navigationButton = new ImageButton(context); titleView = new TextView(context); actions = new LinearLayout(context); init(); }
    public SmartTopAppBar(Context context, android.util.AttributeSet attrs) { super(context, attrs); navigationButton = new ImageButton(context); titleView = new TextView(context); actions = new LinearLayout(context); init(); }
    public SmartTopAppBar(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); navigationButton = new ImageButton(context); titleView = new TextView(context); actions = new LinearLayout(context); init(); }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(4), 0, dp(4), 0);
        setBackgroundColor(SmartColors.SURFACE);

        navigationButton.setBackgroundColor(Color.TRANSPARENT);
        navigationButton.setImageResource(android.R.drawable.ic_media_previous);
        navigationButton.setColorFilter(SmartColors.ON_SURFACE);
        addView(navigationButton, new LinearLayout.LayoutParams(dp(48), dp(56)));

        titleView.setTextSize(20);
        titleView.setTextColor(SmartColors.ON_SURFACE);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setTypeface(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(0, dp(56), 1f);
        addView(titleView, tp);

        actions.setOrientation(HORIZONTAL);
        actions.setGravity(Gravity.CENTER_VERTICAL);
        addView(actions, new LinearLayout.LayoutParams(-2, dp(56)));
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    public SmartTopAppBar setTitle(CharSequence title) { titleView.setText(title); return this; }
    public SmartTopAppBar setNavigationIcon(int resId) { navigationButton.setImageResource(resId); return this; }
    public SmartTopAppBar setNavigationOnClickListener(OnClickListener l) { navigationButton.setOnClickListener(l); return this; }
    public SmartTopAppBar addAction(View view) {
        actions.addView(view, new LinearLayout.LayoutParams(dp(48), dp(56)));
        return this;
    }
    public SmartTopAppBar setBarColor(int color) { setBackgroundColor(color); return this; }
    public SmartTopAppBar setTitleColor(int color) { titleView.setTextColor(color); return this; }
    public ImageButton getNavigationButton() { return navigationButton; }
}
