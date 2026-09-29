package com.smarthub.smartmaterial.navigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartNavigationRail extends LinearLayout {
    private int selectedIndex = 0;
    private int activeColor = SmartColors.PRIMARY;
    private int inactiveColor = SmartColors.ON_SURFACE_VARIANT;
    private final LinearLayout container;

    public SmartNavigationRail(Context context) { super(context); container = new LinearLayout(context); init(); }
    public SmartNavigationRail(Context context, android.util.AttributeSet attrs) { super(context, attrs); container = new LinearLayout(context); init(); }
    public SmartNavigationRail(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); container = new LinearLayout(context); init(); }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setBackgroundColor(SmartColors.SURFACE);
        container.setOrientation(VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(container, new LinearLayout.LayoutParams(dp(80), -1));
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    public SmartNavigationRail addItem(CharSequence label, int iconResId, View.OnClickListener listener) {
        final int index = container.getChildCount();
        LinearLayout item = new LinearLayout(getContext());
        item.setOrientation(LinearLayout.VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(dp(8), dp(8), dp(8), dp(8));

        TextView icon = new TextView(getContext());
        icon.setText("●");
        icon.setTextSize(18);
        icon.setGravity(Gravity.CENTER);
        icon.setTextColor(index == selectedIndex ? activeColor : inactiveColor);

        TextView text = new TextView(getContext());
        text.setText(label);
        text.setTextSize(12);
        text.setGravity(Gravity.CENTER);
        text.setTextColor(index == selectedIndex ? activeColor : inactiveColor);

        item.addView(icon, new LinearLayout.LayoutParams(-1, dp(32)));
        item.addView(text, new LinearLayout.LayoutParams(-1, dp(24)));
        item.setOnClickListener(v -> {
            setSelectedIndex(index);
            if (listener != null) listener.onClick(v);
        });

        container.addView(item, new LinearLayout.LayoutParams(-1, dp(72)));
        return this;
    }

    public SmartNavigationRail setSelectedIndex(int index) {
        if (index < 0 || index >= container.getChildCount()) return this;
        selectedIndex = index;
        for (int i = 0; i < container.getChildCount(); i++) {
            LinearLayout item = (LinearLayout) container.getChildAt(i);
            int color = i == selectedIndex ? activeColor : inactiveColor;
            ((TextView) item.getChildAt(0)).setTextColor(color);
            ((TextView) item.getChildAt(1)).setTextColor(color);
        }
        return this;
    }

    public int getSelectedIndex() { return selectedIndex; }
    public SmartNavigationRail setActiveColor(int color) { activeColor = color; setSelectedIndex(selectedIndex); return this; }
    public SmartNavigationRail setInactiveColor(int color) { inactiveColor = color; setSelectedIndex(selectedIndex); return this; }
}
