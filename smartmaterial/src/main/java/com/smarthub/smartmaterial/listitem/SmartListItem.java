package com.smarthub.smartmaterial.listitem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartListItem extends LinearLayout {
    private final TextView titleView;
    private final TextView subtitleView;

    public SmartListItem(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(16),dp(10),dp(16),dp(10));
        setMinimumHeight(dp(64));
        setClickable(true);
        setFocusable(true);
        setBackground(makeBackground());

        titleView = new TextView(context);
        titleView.setTextSize(16);
        titleView.setTextColor(SmartTheme.onSurface(context));
        SmartTheme.medium(titleView);
        titleView.setSingleLine(true);

        subtitleView = new TextView(context);
        subtitleView.setTextSize(14);
        subtitleView.setTextColor(Color.rgb(105,106,112));
        subtitleView.setSingleLine(true);
        SmartTheme.light(subtitleView);
        subtitleView.setVisibility(GONE);

        addView(titleView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        LayoutParams subtitleParams=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        subtitleParams.topMargin=dp(3);
        addView(subtitleView,subtitleParams);
    }

    public SmartListItem setTitle(CharSequence title) { titleView.setText(title); return this; }

    public SmartListItem setSubtitle(CharSequence subtitle) {
        subtitleView.setText(subtitle);
        subtitleView.setVisibility(subtitle==null || subtitle.length()==0 ? GONE : VISIBLE);
        return this;
    }

    public SmartListItem setOnItemClickListener(OnClickListener listener) { setOnClickListener(listener); return this; }
    public TextView getTitleView() { return titleView; }
    public TextView getSubtitleView() { return subtitleView; }
    public SmartListItem setItemColor(int color) { setBackground(makeBackground(color)); return this; }

    private GradientDrawable makeBackground() {
        return makeBackground(SmartTheme.isDark(getContext()) ? Color.rgb(30,31,36) : SmartTheme.surface(getContext()));
    }

    private GradientDrawable makeBackground(int color) {
        GradientDrawable drawable=new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(12));
        return drawable;
    }

    private int dp(float value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}