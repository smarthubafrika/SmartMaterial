package com.smarthub.smartmaterial.topappbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartTopAppBar extends LinearLayout {
    private final ImageButton navigationButton;
    private final TextView titleView;

    public SmartTopAppBar(Context context) {
        super(context);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setMinimumHeight(dp(64));
        setPadding(dp(4), 0, dp(8), 0);
        setBackground(makeBackground());

        navigationButton = new ImageButton(context);
        navigationButton.setBackground(makeButtonBackground());
        navigationButton.setColorFilter(SmartTheme.onSurface(context));
        navigationButton.setContentDescription("Navigation");
        navigationButton.setImageResource(android.R.drawable.ic_menu_revert);

        LayoutParams navigationParams = new LayoutParams(dp(48), dp(48));
        navigationParams.gravity = Gravity.CENTER_VERTICAL;
        addView(navigationButton, navigationParams);

        titleView = new TextView(context);
        titleView.setTextSize(20);
        titleView.setTextColor(SmartTheme.onSurface(context));
        titleView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setSingleLine(true);

        LayoutParams titleParams = new LayoutParams(
                0, LayoutParams.WRAP_CONTENT, 1f);
        titleParams.leftMargin = dp(8);
        addView(titleView, titleParams);
    }

    public SmartTopAppBar setTitle(CharSequence title) {
        titleView.setText(title);
        return this;
    }

    public SmartTopAppBar setNavigationIcon(int resId) {
        navigationButton.setImageResource(resId);
        return this;
    }

    public SmartTopAppBar setNavigationDescription(CharSequence description) {
        navigationButton.setContentDescription(description);
        return this;
    }

    public SmartTopAppBar setOnNavigationClickListener(OnClickListener listener) {
        navigationButton.setOnClickListener(listener);
        return this;
    }

    public SmartTopAppBar setBarColor(int color) {
        setBackground(makeBackground(color));
        return this;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public ImageButton getNavigationButton() {
        return navigationButton;
    }

    private GradientDrawable makeBackground() {
        return makeBackground(SmartTheme.surface(getContext()));
    }

    private GradientDrawable makeBackground(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(0);
        return drawable;
    }

    private GradientDrawable makeButtonBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.TRANSPARENT);
        drawable.setCornerRadius(dp(24));
        return drawable;
    }

    private int dp(float value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }
}
