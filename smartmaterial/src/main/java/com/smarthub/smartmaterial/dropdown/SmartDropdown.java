package com.smarthub.smartmaterial.dropdown;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartDropdown extends TextView {
    private final List<String> items = new ArrayList<>();
    private int selectedIndex = -1;
    private int fillColor = SmartColors.SURFACE;
    private int strokeColor = SmartColors.OUTLINE;
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(SmartDropdown view, int position, String item);
    }

    public SmartDropdown(Context context) { super(context); init(); }
    public SmartDropdown(Context context, android.util.AttributeSet attrs) { super(context, attrs); init(); }
    public SmartDropdown(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setTextSize(16);
        setTextColor(SmartColors.ON_SURFACE);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(16), 0, dp(16), 0);
        setMinHeight(dp(56));
        setClickable(true);
        setFocusable(true);
        updateBackground();
        setOnClickListener(v -> showMenu());
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    private void updateBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fillColor);
        d.setCornerRadius(dp(12));
        d.setStroke(dp(1), strokeColor);
        setBackground(d);
    }

    private void showMenu() {
        if (items.isEmpty()) return;

        LinearLayout list = new LinearLayout(getContext());
        list.setOrientation(LinearLayout.VERTICAL);
        list.setPadding(0, dp(8), 0, dp(8));
        list.setBackground(roundBackground(SmartColors.SURFACE, 12));
        list.setElevation(dp(6));

        final PopupWindow[] holder = new PopupWindow[1];
        for (int i = 0; i < items.size(); i++) {
            final int index = i;
            TextView row = new TextView(getContext());
            row.setText(items.get(i));
            row.setTextSize(16);
            row.setTextColor(SmartColors.ON_SURFACE);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(dp(16), 0, dp(16), 0);
            list.addView(row, new LinearLayout.LayoutParams(-1, dp(48)));
            row.setOnClickListener(v -> {
                selectedIndex = index;
                setText(items.get(index));
                if (listener != null) listener.onItemSelected(this, index, items.get(index));
                if (holder[0] != null) holder[0].dismiss();
            });
        }

        PopupWindow popup = new PopupWindow(list, getWidth() > 0 ? getWidth() : dp(240),
                Math.min(dp(320), dp(48) * items.size() + dp(16)), true);
        holder[0] = popup;
        popup.setBackgroundDrawable(roundBackground(SmartColors.SURFACE, 12));
        popup.setOutsideTouchable(true);
        popup.setElevation(dp(8));
        popup.showAsDropDown(this, 0, dp(4));
    }

    private GradientDrawable roundBackground(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    public SmartDropdown setItems(String... values) {
        items.clear();
        if (values != null) items.addAll(Arrays.asList(values));
        selectedIndex = -1;
        setText("");
        return this;
    }

    public SmartDropdown setItems(List<String> values) {
        items.clear();
        if (values != null) items.addAll(values);
        selectedIndex = -1;
        setText("");
        return this;
    }

    public SmartDropdown setSelectedIndex(int index) {
        if (index >= 0 && index < items.size()) {
            selectedIndex = index;
            setText(items.get(index));
        }
        return this;
    }

    public int getSelectedIndex() { return selectedIndex; }

    public String getSelectedItem() {
        return selectedIndex >= 0 && selectedIndex < items.size() ? items.get(selectedIndex) : "";
    }

    public SmartDropdown setFillColor(int color) { fillColor = color; updateBackground(); return this; }
    public SmartDropdown setStrokeColor(int color) { strokeColor = color; updateBackground(); return this; }

    public SmartDropdown setOnItemSelectedListener(OnItemSelectedListener l) {
        listener = l;
        return this;
    }
}
