package com.smarthub.smartmaterial.textfield;

import android.content.Context;
import android.graphics.Color;
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

    private static final int RED = 0xFFE53935;
    private static final int GREEN = 0xFF43A047;

    private final TextView labelView;
    private final EditText editText;

    private float radius = 6f;
    private int fieldColor = SmartColors.OUTLINE;

    public SmartTextField(Context context) {
        this(context, null);
    }

    public SmartTextField(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        labelView = new TextView(context);
        labelView.setTextSize(12);
        labelView.setTextColor(SmartTheme.onSurface(context));
        SmartTheme.medium(labelView);
        labelView.setVisibility(GONE);
        SmartTheme.light(editText);

        editText = new EditText(context);
        editText.setTextSize(14);
        editText.setSingleLine(true);
        editText.setTextColor(SmartTheme.onSurface(context));
        editText.setHintTextColor(Color.rgb(120, 120, 125));
        editText.setGravity(Gravity.CENTER_VERTICAL);
        editText.setPadding(dp(16), 0, dp(16), 0);
        editText.setBackground(makeBackground(fieldColor));

        LayoutParams lpLabel = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        lpLabel.bottomMargin = dp(6);

        LayoutParams lpEdit = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                dp(40)
        );

        addView(labelView, lpLabel);
        addView(editText, lpEdit);

        editText.setOnFocusChangeListener((v, focused) ->
                editText.setBackground(
                        makeBackground(focused ? fieldColor : SmartColors.OUTLINE)
                )
        );
    }

    public SmartTextField setLabel(CharSequence label) {
        labelView.setText(label);
        labelView.setVisibility(
                label == null || label.length() == 0 ? GONE : VISIBLE
        );
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
        editText.setInputType(
                InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        return this;
    }

    public String getText() {
        return editText.getText().toString();
    }

    public EditText getEditText() {
        return editText;
    }

    public SmartTextField setCornerRadius(float value) {
        radius = value;
        editText.setBackground(makeBackground(fieldColor));
        return this;
    }

    public SmartTextField setFieldColor(int color) {
        fieldColor = color;
        editText.setBackground(makeBackground(fieldColor));
        return this;
    }

    public SmartTextField setRedField() {
        return setFieldColor(RED);
    }

    public SmartTextField setGreenField() {
        return setFieldColor(GREEN);
    }

    private GradientDrawable makeBackground(int strokeColor) {
        GradientDrawable d = new GradientDrawable();

        d.setColor(
                SmartTheme.isDark(getContext())
                        ? Color.rgb(30, 31, 36)
                        : Color.WHITE
        );

        d.setCornerRadius(dp(radius));
        d.setStroke(dp(1), strokeColor);

        return d;
    }

    private int dp(float value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }
}
