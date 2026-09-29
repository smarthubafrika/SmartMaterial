package com.smarthub.smartmaterial.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;
import com.smarthub.smartmaterial.R;
import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.*;

public class SmartChip extends TextView {
    private int chipColor=SmartColors.SURFACE_VARIANT,textColor=SmartColors.ON_SURFACE_VARIANT,rippleColor=SmartColors.PRIMARY; private float cornerRadius=20f;
    public SmartChip(Context c){super(c);init(null);} public SmartChip(Context c,AttributeSet a){super(c,a);init(a);} public SmartChip(Context c,AttributeSet a,int s){super(c,a,s);init(a);}
    private void init(AttributeSet a){if(a!=null){TypedArray x=getContext().obtainStyledAttributes(a,R.styleable.SmartChip);chipColor=x.getColor(R.styleable.SmartChip_smartChipColor,chipColor);textColor=x.getColor(R.styleable.SmartChip_smartTextColor,textColor);cornerRadius=x.getDimension(R.styleable.SmartChip_smartCornerRadius,dp(cornerRadius))/getResources().getDisplayMetrics().density;rippleColor=x.getColor(R.styleable.SmartChip_smartRippleColor,rippleColor);x.recycle();}setGravity(Gravity.CENTER);setTextSize(14);setTextColor(textColor);setTypeface(Typeface.DEFAULT,Typeface.NORMAL);setPadding(dp(16),dp(8),dp(16),dp(8));setMinHeight(dp(40));updateBackground();}
    private int dp(float v){return SmartDimensions.dp(getContext(),v);} private void updateBackground(){GradientDrawable d=new GradientDrawable();d.setColor(chipColor);d.setCornerRadius(dp(cornerRadius));SmartState.applyRipple(this,d,rippleColor);}
    public SmartChip setChipText(CharSequence t){setText(t);return this;} public SmartChip setChipColor(int c){chipColor=c;updateBackground();return this;} public SmartChip setTextColorValue(int c){textColor=c;setTextColor(c);return this;} public SmartChip setRippleColor(int c){rippleColor=c;updateBackground();return this;} public SmartChip setCornerRadius(float d){cornerRadius=d;updateBackground();return this;}
    @Override public boolean onTouchEvent(MotionEvent e){if(e.getActionMasked()==MotionEvent.ACTION_DOWN)SmartAnimations.press(this);if(e.getActionMasked()==MotionEvent.ACTION_UP||e.getActionMasked()==MotionEvent.ACTION_CANCEL)SmartAnimations.release(this);return super.onTouchEvent(e);}
}