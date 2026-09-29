package com.smarthub.smartmaterial.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.smarthub.smartmaterial.R;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartCard extends FrameLayout {
    private int cardColor = SmartColors.SURFACE;
    private int strokeColor = SmartColors.OUTLINE;
    private float cornerRadius = SmartDimensions.CORNER_LARGE;
    private float strokeWidth = 1f;

    public SmartCard(Context context) { super(context); init(null); }
    public SmartCard(Context context, AttributeSet attrs) { super(context, attrs); init(attrs); }
    public SmartCard(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(attrs); }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SmartCard);
            cardColor = a.getColor(R.styleable.SmartCard_smartCardColor, cardColor);
            strokeColor = a.getColor(R.styleable.SmartCard_smartStrokeColor, strokeColor);
            strokeWidth = a.getDimension(R.styleable.SmartCard_smartStrokeWidth, dp(strokeWidth)) / getResources().getDisplayMetrics().density;
            setElevation(a.getDimension(R.styleable.SmartCard_smartElevation, dp(SmartDimensions.CARD_ELEVATION)));
            a.recycle();
        } else {
            setElevation(dp(SmartDimensions.CARD_ELEVATION));
        }
        setPadding(dp(16), dp(16), dp(16), dp(16));
        updateBackground();
    }

    private int dp(float v) { return SmartDimensions.dp(getContext(), v); }

    private void updateBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(cardColor);
        drawable.setCornerRadius(dp(cornerRadius));
        drawable.setStroke(dp(strokeWidth), strokeColor);
        setBackground(drawable);
    }

    public SmartCard setCardColor(int color) { cardColor = color; updateBackground(); return this; }
    public SmartCard setStrokeColor(int color) { strokeColor = color; updateBackground(); return this; }
    public SmartCard setStrokeWidth(float dp) { strokeWidth = dp; updateBackground(); return this; }
    public SmartCard setCornerRadius(float dp) { cornerRadius = dp; updateBackground(); return this; }
    public SmartCard setCardElevation(float dp) { setElevation(dp(dp)); return this; }
}