package com.smarthub.smartmaterial.search;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartSearchBar extends LinearLayout {
    public interface OnSearchListener { void onSearch(String query); }
    private final EditText editText;
    private OnSearchListener listener;

    public SmartSearchBar(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(12), 0, dp(8), 0);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(SmartTheme.isDark(context) ? Color.rgb(30,31,36) : Color.rgb(245,246,248));
        bg.setCornerRadius(dp(24));
        setBackground(bg);

        ImageView icon = new ImageView(context);
        icon.setImageResource(android.R.drawable.ic_menu_search);
        icon.setColorFilter(SmartTheme.onSurface(context));
        addView(icon, new LayoutParams(dp(24), dp(24)));

        editText = new EditText(context);
        editText.setSingleLine(true);
        editText.setTextSize(14);
        editText.setHintTextColor(Color.rgb(120,120,125));
        editText.setTextColor(SmartTheme.onSurface(context));
        editText.setHint("Search");
        editText.setBackground(null);
        SmartTheme.light(editText);
        LayoutParams ep = new LayoutParams(0, dp(48), 1);
        ep.leftMargin = dp(8);
        addView(editText, ep);

        ImageView clear = new ImageView(context);
        clear.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        clear.setColorFilter(SmartTheme.onSurface(context));
        clear.setVisibility(GONE);
        clear.setContentDescription("Clear search");
        clear.setOnClickListener(v -> editText.setText(""));
        addView(clear, new LayoutParams(dp(40), dp(40)));

        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s,int a,int b,int c) {}
            public void onTextChanged(CharSequence s,int a,int b,int c) {
                clear.setVisibility(s.length() == 0 ? GONE : VISIBLE);
                if (listener != null) listener.onSearch(s.toString());
            }
            public void afterTextChanged(Editable e) {}
        });
    }

    public SmartSearchBar setHint(CharSequence hint) { editText.setHint(hint); return this; }
    public SmartSearchBar setText(CharSequence text) { editText.setText(text); return this; }
    public String getText() { return editText.getText().toString(); }
    public EditText getEditText() { return editText; }
    public SmartSearchBar setOnSearchListener(OnSearchListener l) { listener = l; return this; }
    private int dp(float v) { return Math.round(v * getResources().getDisplayMetrics().density); }
}