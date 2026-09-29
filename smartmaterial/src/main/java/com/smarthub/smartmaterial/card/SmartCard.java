package com.smarthub.smartmaterial.card;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.theme.SmartDimensions;

public class SmartCard extends FrameLayout {
    private int cardColor = SmartColors.SURFACE;
    private int strokeColor = SmartColors.OUTLINE;
    private float cornerRadius = SmartDimensions.CORNER_LARGE;
    private float strokeWidth = 1f;

    public SmartCard(Context context) { super(context); init(); }
    public SmartCard(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public SmartCard(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        setPadding(SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),16), SmartDimensions.dp(getContext(),16));
        setElevation(SmartDimensions.dp(getContext(), SmartDimensions.CARD_ELEVATION));
        updateBackground();
    }

    private void updateBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(cardColor);
        drawable.setCornerRadius(SmartDimensions.dp(getContext(), cornerRadius));
        drawable.setStroke(SmartDimensions.dp(getContext(), strokeWidth), strokeColor);
        setBackground(drawable);
    }

    public SmartCard setCardColor(int color) { cardColor = color; updateBackground(); return this; }
    public SmartCard setStrokeColor(int color) { strokeColor = color; updateBackground(); return this; }
    public SmartCard setStrokeWidth(float dp) { strokeWidth = dp; updateBackground(); return this; }
    public SmartCard setCornerRadius(float dp) { cornerRadius = dp; updateBackground(); return this; }
    public SmartCard setCardElevation(float dp) { setElevation(SmartDimensions.dp(getContext(), dp)); return this; }
}