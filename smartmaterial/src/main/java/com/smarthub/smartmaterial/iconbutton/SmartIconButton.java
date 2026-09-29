package com.smarthub.smartmaterial.iconbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartIconButton extends FrameLayout {
    private final ImageView iconView;
    private float radius = 20f;
    private int backgroundColor = 0xFFE7E8EC;

    public SmartIconButton(Context context) {
        super(context);
        iconView = createIcon(context);
        init();
    }

    public SmartIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        iconView = createIcon(context);
        init();
    }

    public SmartIconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iconView = createIcon(context);
        init();
    }

    private ImageView createIcon(Context context) {
        return new ImageView(context);
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
        setGravity(Gravity.CENTER);
        setMinimumWidth(dp(48));
        setMinimumHeight(dp(48));
        setContentDescription("Icon button");

        LayoutParams params = new LayoutParams(dp(24), dp(24), Gravity.CENTER);
        addView(iconView, params);
        setBackground(makeBackground());
    }

    public SmartIconButton setIconResource(int resId) {
        iconView.setImageResource(resId);
        return this;
    }

    public SmartIconButton setIconTint(int color) {
        iconView.setImageTintList(ColorStateList.valueOf(color));
        return this;
    }

    public SmartIconButton setIconSize(float sizeDp) {
        LayoutParams params = (LayoutParams) iconView.getLayoutParams();
        params.width = dp(sizeDp);
        params.height = dp(sizeDp);
        iconView.setLayoutParams(params);
        return this;
    }

    public SmartIconButton setButtonColor(int color) {
        backgroundColor = color;
        setBackground(makeBackground());
        return this;
    }

    public SmartIconButton setCornerRadius(float value) {
        radius = value;
        setBackground(makeBackground());
        return this;
    }

    public SmartIconButton setDescription(CharSequence description) {
        setContentDescription(description);
        return this;
    }

    public SmartIconButton setOnIconClickListener(OnClickListener listener) {
        setOnClickListener(listener);
        return this;
    }

    public ImageView getIconView() {
        return iconView;
    }

    private RippleDrawable makeBackground() {
        GradientDrawable base = new GradientDrawable();
        base.setColor(
                SmartTheme.isDark(getContext())
                        ? Color.rgb(48, 49, 55)
                        : backgroundColor
        );
        base.setCornerRadius(dp(radius));

        return new RippleDrawable(
                ColorStateList.valueOf(0x33777780),
                base,
                null
        );
    }

    private int dp(float value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }
}
