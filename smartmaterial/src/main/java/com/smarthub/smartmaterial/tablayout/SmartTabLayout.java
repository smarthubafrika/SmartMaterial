package com.smarthub.smartmaterial.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartTabLayout extends HorizontalScrollView {
    private final LinearLayout tabContainer;
    private int selectedIndex = -1;
    private int selectedColor;
    private int unselectedColor;

    public SmartTabLayout(Context context) {
        super(context);

        setHorizontalScrollBarEnabled(false);
        setFillViewport(false);

        selectedColor = SmartTheme.primary(context);
        unselectedColor = SmartTheme.onSurface(context);

        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setGravity(Gravity.CENTER_VERTICAL);
        tabContainer.setPadding(dp(8), 0, dp(8), 0);

        addView(tabContainer, new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        ));
    }

    public SmartTabLayout addTab(CharSequence title) {
        return addTab(title, null);
    }

    public SmartTabLayout addTab(
            CharSequence title,
            OnClickListener listener) {

        final int index = tabContainer.getChildCount();

        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setTextSize(14);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine(true);
        tab.setPadding(dp(16), 0, dp(16), 0);
        tab.setTextColor(unselectedColor);
        tab.setBackground(makeTabBackground(false));
        tab.setClickable(true);
        tab.setFocusable(true);

        tab.setOnClickListener(v -> {
            selectTab(index);
            if (listener != null) {
                listener.onClick(v);
            }
        });

        tabContainer.addView(tab, new LinearLayout.LayoutParams(
                dp(96),
                LayoutParams.MATCH_PARENT
        ));

        if (selectedIndex == -1) {
            selectTab(0);
        }

        return this;
    }

    public SmartTabLayout clearTabs() {
        tabContainer.removeAllViews();
        selectedIndex = -1;
        return this;
    }

    public SmartTabLayout selectTab(int index) {
        if (index < 0 || index >= tabContainer.getChildCount()) {
            return this;
        }

        selectedIndex = index;

        for (int i = 0; i < tabContainer.getChildCount(); i++) {
            View child = tabContainer.getChildAt(i);
            if (child instanceof TextView) {
                TextView tab = (TextView) child;
                boolean selected = i == index;
                tab.setTextColor(selected ? selectedColor : unselectedColor);
                tab.setBackground(makeTabBackground(selected));
            }
        }

        View selected = tabContainer.getChildAt(index);
        if (selected != null) {
            selected.post(() -> selected.requestRectangleOnScreen(
                    new android.graphics.Rect(
                            selected.getLeft(),
                            selected.getTop(),
                            selected.getRight(),
                            selected.getBottom()
                    ), false
            ));
        }

        return this;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public SmartTabLayout setSelectedColor(int color) {
        selectedColor = color;
        if (selectedIndex >= 0) {
            selectTab(selectedIndex);
        }
        return this;
    }

    public SmartTabLayout setUnselectedColor(int color) {
        unselectedColor = color;
        if (selectedIndex >= 0) {
            selectTab(selectedIndex);
        }
        return this;
    }

    public TextView getTabAt(int index) {
        if (index < 0 || index >= tabContainer.getChildCount()) {
            return null;
        }
        View view = tabContainer.getChildAt(index);
        return view instanceof TextView ? (TextView) view : null;
    }

    private GradientDrawable makeTabBackground(boolean selected) {
        GradientDrawable drawable = new GradientDrawable();

        drawable.setColor(selected
                ? Color.argb(30, 65, 95, 145)
                : Color.TRANSPARENT);

        drawable.setCornerRadius(dp(20));
        return drawable;
    }

    private int dp(float value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }
}
