package com.smarthub.smartmaterial.textfield;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartTextField extends FrameLayout {
    private final EditText editText;
    private final TextView labelView;
    private int normalStrokeColor = SmartColors.OUTLINE;
    private int focusedStrokeColor = SmartColors.PRIMARY;
    private int fillColor = SmartColors.SURFACE;
    private int labelColor = SmartColors.ON_SURFACE_VARIANT;
    private float cornerRadius = SmartDimensions.CORNER_MEDIUM;

    public SmartTextField(Context context) { super(context); editText = new EditText(context); labelView = new TextView(context); init(); }
    public SmartTextField(Context context, AttributeSet attrs) { super(context, attrs); editText = new EditText(context); labelView = new TextView(context); init(); }
    public SmartTextField(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); editText = new EditText(context); labelView = new TextView(context); init(); }

    private void init() {
        setClipChildren(false);
        setClipToPadding(false);
        setMinimumHeight(SmartDimensions.dp(getContext(),64));

        editText.setBackground(createBackground(normalStrokeColor));
        editText.setTextColor(SmartColors.ON_SURFACE);
        editText.setHintTextColor(SmartColors.ON_SURFACE_VARIANT);
        editText.setTextSize(16);
        editText.setSingleLine(true);
        editText.setGravity(Gravity.CENTER_VERTICAL);
        editText.setPadding(SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),8), SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),8));
        LayoutParams ep = new LayoutParams(-1, SmartDimensions.dp(getContext(),56));
        ep.gravity = Gravity.BOTTOM;
        addView(editText, ep);

        labelView.setTextSize(12);
        labelView.setTextColor(labelColor);
        labelView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        labelView.setBackgroundColor(fillColor);
        labelView.setPadding(SmartDimensions.dp(getContext(),4),0,SmartDimensions.dp(getContext(),4),0);
        LayoutParams lp = new LayoutParams(-2, SmartDimensions.dp(getContext(),20));
        lp.leftMargin = SmartDimensions.dp(getContext(),12);
        lp.topMargin = SmartDimensions.dp(getContext(),2);
        addView(labelView, lp);

        editText.setOnFocusChangeListener((v, focused) -> {
            editText.setBackground(createBackground(focused ? focusedStrokeColor : normalStrokeColor));
            labelView.setTextColor(focused ? focusedStrokeColor : labelColor);
        });
    }

    private GradientDrawable createBackground(int strokeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fillColor);
        d.setCornerRadius(SmartDimensions.dp(getContext(), cornerRadius));
        d.setStroke(SmartDimensions.dp(getContext(),1), strokeColor);
        return d;
    }

    public SmartTextField setLabel(CharSequence label) { labelView.setText(label); return this; }
    public SmartTextField setHint(CharSequence hint) { editText.setHint(hint); return this; }
    public SmartTextField setText(CharSequence text) { editText.setText(text); return this; }
    public String getText() { return editText.getText().toString(); }
    public EditText getEditText() { return editText; }
    public SmartTextField setInputType(int type) { editText.setInputType(type); return this; }
    public SmartTextField setPasswordMode(boolean enabled) {
        editText.setInputType(enabled ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
        return this;
    }
    public SmartTextField setFillColor(int color) {
        fillColor = color;
        labelView.setBackgroundColor(color);
        editText.setBackground(createBackground(editText.hasFocus() ? focusedStrokeColor : normalStrokeColor));
        return this;
    }
    public SmartTextField setStrokeColor(int normal, int focused) {
        normalStrokeColor = normal; focusedStrokeColor = focused;
        editText.setBackground(createBackground(editText.hasFocus() ? focusedStrokeColor : normalStrokeColor));
        return this;
    }
    public SmartTextField setCornerRadius(float dp) {
        cornerRadius = dp;
        editText.setBackground(createBackground(editText.hasFocus() ? focusedStrokeColor : normalStrokeColor));
        return this;
    }
}