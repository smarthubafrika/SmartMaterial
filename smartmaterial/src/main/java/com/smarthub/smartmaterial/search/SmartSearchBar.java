package com.smarthub.smartmaterial.search;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartSearchBar extends LinearLayout {
    private final ImageButton searchIcon;
    private final EditText editText;
    private final ImageButton clearButton;
    private OnQueryChangeListener listener;

    public interface OnQueryChangeListener {
        void onQueryChanged(SmartSearchBar view, String query);
    }

    public SmartSearchBar(Context context) { super(context); searchIcon = new ImageButton(context); editText = new EditText(context); clearButton = new ImageButton(context); init(); }
    public SmartSearchBar(Context context, android.util.AttributeSet attrs) { super(context, attrs); searchIcon = new ImageButton(context); editText = new EditText(context); clearButton = new ImageButton(context); init(); }
    public SmartSearchBar(Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); searchIcon = new ImageButton(context); editText = new EditText(context); clearButton = new ImageButton(context); init(); }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(8), 0, dp(8), 0);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(SmartColors.SURFACE_VARIANT);
        bg.setCornerRadius(dp(28));
        setBackground(bg);

        searchIcon.setImageResource(android.R.drawable.ic_menu_search);
        searchIcon.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        searchIcon.setColorFilter(SmartColors.ON_SURFACE_VARIANT);

        editText.setSingleLine(true);
        editText.setTextSize(16);
        editText.setTextColor(SmartColors.ON_SURFACE);
        editText.setHintTextColor(SmartColors.ON_SURFACE_VARIANT);
        editText.setHint("Search");
        editText.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        editText.setPadding(dp(8), 0, dp(8), 0);

        clearButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        clearButton.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        clearButton.setColorFilter(SmartColors.ON_SURFACE_VARIANT);
        clearButton.setVisibility(GONE);
        clearButton.setOnClickListener(v -> editText.setText(""));

        addView(searchIcon, new LinearLayout.LayoutParams(dp(48), dp(56)));
        addView(editText, new LinearLayout.LayoutParams(0, dp(56), 1f));
        addView(clearButton, new LinearLayout.LayoutParams(dp(48), dp(56)));

        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearButton.setVisibility(s.length() > 0 ? VISIBLE : GONE);
                if (listener != null) listener.onQueryChanged(SmartSearchBar.this, s.toString());
            }
            public void afterTextChanged(Editable s) {}
        });
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    public SmartSearchBar setHint(CharSequence hint) { editText.setHint(hint); return this; }
    public SmartSearchBar setQuery(CharSequence query) { editText.setText(query); editText.setSelection(editText.length()); return this; }
    public String getQuery() { return editText.getText().toString(); }
    public EditText getEditText() { return editText; }
    public SmartSearchBar setOnQueryChangeListener(OnQueryChangeListener l) { listener = l; return this; }
}
