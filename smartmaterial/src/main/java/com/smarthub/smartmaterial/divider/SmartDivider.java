package com.smarthub.smartmaterial.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartDivider extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dividerColor;
    private float thickness = 1f;
    private boolean vertical;

    public SmartDivider(Context context) {
        super(context);
        init();
    }

    public SmartDivider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartDivider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dividerColor = 0x33777780;
        setWillNotDraw(false);
        setMinimumHeight(dp(thickness));
    }

    public SmartDivider setDividerColor(int color) {
        dividerColor = color;
        invalidate();
        return this;
    }

    public SmartDivider setThickness(float value) {
        thickness = Math.max(1f, value);
        requestLayout();
        invalidate();
        return this;
    }

    public SmartDivider setVertical(boolean value) {
        vertical = value;
        requestLayout();
        invalidate();
        return this;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int thicknessPx = dp(thickness);

        if (vertical) {
            setMeasuredDimension(
                    resolveSize(thicknessPx, widthMeasureSpec),
                    resolveSize(dp(48), heightMeasureSpec)
            );
        } else {
            setMeasuredDimension(
                    resolveSize(dp(48), widthMeasureSpec),
                    resolveSize(thicknessPx, heightMeasureSpec)
            );
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(SmartTheme.isDark(getContext())
                ? 0x55777780
                : dividerColor);

        if (vertical) {
            float x = getWidth() / 2f;
            float half = dp(thickness) / 2f;
            canvas.drawRect(
                    x - half,
                    0,
                    x + half,
                    getHeight(),
                    paint
            );
        } else {
            float y = getHeight() / 2f;
            float half = dp(thickness) / 2f;
            canvas.drawRect(
                    0,
                    y - half,
                    getWidth(),
                    y + half,
                    paint
            );
        }
    }

    private int dp(float value) {
        return Math.max(1, Math.round(
                value * getResources().getDisplayMetrics().density
        ));
    }
}
