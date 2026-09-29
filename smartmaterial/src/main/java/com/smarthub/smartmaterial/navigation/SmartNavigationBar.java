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

public class SmartNavigationBar extends LinearLayout {
    private int selectedIndex = 0;
    private int activeColor = SmartColors.PRIMARY;
    private int inactiveColor = SmartColors.ON_SURFACE_VARIANT;
    private final LinearLayout container;

    public SmartNavigationBar(Context context) { super(context); container = new LinearLayout(context); init(); }
    public SmartNavigationBar(Context context, android.util.AttributeSet attrs) { super(context, attrs); container = new LinearLayout(context); init(); }
    public SmartNavigationBar(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); container = new LinearLayout(context); init(); }

    private void init() {
        setOrientation(VERTICAL);
        setBackgroundColor(SmartColors.SURFACE);
        container.setOrientation(HORIZONTAL);
        container.setGravity(Gravity.CENTER);
        addView(container, new LinearLayout.LayoutParams(-1, dp(80)));
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    public SmartNavigationBar addItem(CharSequence label, int iconResId, OnClickListener listener) {
        final int index = container.getChildCount();
        LinearLayout item = new LinearLayout(getContext());
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(dp(8), dp(6), dp(8), dp(4));

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

        item.addView(icon, new LinearLayout.LayoutParams(-1, dp(28)));
        item.addView(text, new LinearLayout.LayoutParams(-1, dp(24)));

        item.setOnClickListener(v -> {
            setSelectedIndex(index);
            if (listener != null) listener.onClick(v);
        });

        container.addView(item, new LinearLayout.LayoutParams(0, dp(72), 1f));
        return this;
    }

    public SmartNavigationBar setSelectedIndex(int index) {
        if (index < 0 || index >= container.getChildCount()) return this;
        selectedIndex = index;
        for (int i = 0; i < container.getChildCount(); i++) {
            View item = container.getChildAt(i);
            LinearLayout layout = (LinearLayout) item;
            TextView icon = (TextView) layout.getChildAt(0);
            TextView text = (TextView) layout.getChildAt(1);
            int color = i == selectedIndex ? activeColor : inactiveColor;
            icon.setTextColor(color);
            text.setTextColor(color);
        }
        return this;
    }

    public int getSelectedIndex() { return selectedIndex; }
    public SmartNavigationBar setActiveColor(int color) { activeColor = color; setSelectedIndex(selectedIndex); return this; }
    public SmartNavigationBar setInactiveColor(int color) { inactiveColor = color; setSelectedIndex(selectedIndex); return this; }
}
