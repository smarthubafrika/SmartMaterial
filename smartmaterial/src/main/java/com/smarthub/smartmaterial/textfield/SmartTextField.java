package com.smarthub.smartmaterial.textfield;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartTextField extends LinearLayout {
    private final TextView labelView;
    private final EditText editText;
    private float radius = 12f;

    public SmartTextField(Context context) { this(context, null); }

    public SmartTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        labelView = new TextView(context);
        labelView.setTextSize(12);
        labelView.setTextColor(SmartTheme.onSurface(context));
        labelView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        labelView.setVisibility(GONE);

        editText = new EditText(context);
        editText.setTextSize(16);
        editText.setSingleLine(true);
        editText.setTextColor(SmartTheme.onSurface(context));
        editText.setHintTextColor(Color.rgb(120, 120, 125));
        editText.setPadding(dp(16), 0, dp(16), 0);
        editText.setBackground(makeBackground(SmartColors.OUTLINE));

        LayoutParams lpLabel = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lpLabel.bottomMargin = dp(6);

        LayoutParams lpEdit = new LayoutParams(
                LayoutParams.MATCH_PARENT, dp(56));

        addView(labelView, lpLabel);
        addView(editText, lpEdit);

        editText.setOnFocusChangeListener((v, focused) ->
                editText.setBackground(makeBackground(
                        focused ? SmartTheme.primary(context) : SmartColors.OUTLINE)));
    }

    public SmartTextField setLabel(CharSequence label) {
        labelView.setText(label);
        labelView.setVisibility(label == null || label.length() == 0 ? GONE : VISIBLE);
        return this;
    }

    public SmartTextField setHint(CharSequence hint) {
        editText.setHint(hint);
        return this;
    }

    public SmartTextField setText(CharSequence text) {
        editText.setText(text);
        return this;
    }

    public SmartTextField setInputType(int type) {
        editText.setInputType(type);
        return this;
    }

    public SmartTextField setPassword() {
        editText.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        return this;
    }

    public String getText() { return editText.getText().toString(); }

    public EditText getEditText() { return editText; }

    public SmartTextField setCornerRadius(float value) {
        radius = value;
        editText.setBackground(makeBackground(SmartColors.OUTLINE));
        return this;
    }

    private GradientDrawable makeBackground(int strokeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(SmartTheme.isDark(getContext())
                ? Color.rgb(30, 31, 36) : Color.rgb(247, 248, 252));
        d.setCornerRadius(dp(radius));
        d.setStroke(dp(1), strokeColor);
        return d;
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
