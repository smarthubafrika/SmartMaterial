package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;
import com.smarthub.smartmaterial.R;
import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.*;

public class SmartButton extends TextView {
    private int backgroundColor,textColor,rippleColor; private float cornerRadius=SmartDimensions.CORNER_MEDIUM;
    public SmartButton(Context c){super(c);init(null);} public SmartButton(Context c,AttributeSet a){super(c,a);init(a);} public SmartButton(Context c,AttributeSet a,int s){super(c,a,s);init(a);}
    private void init(AttributeSet a){backgroundColor=SmartTheme.primary(getContext());textColor=SmartColors.ON_PRIMARY;rippleColor=SmartColors.PRIMARY_CONTAINER;if(a!=null){TypedArray x=getContext().obtainStyledAttributes(a,R.styleable.SmartButton);backgroundColor=x.getColor(R.styleable.SmartButton_smartButtonColor,backgroundColor);textColor=x.getColor(R.styleable.SmartButton_smartButtonTextColor,textColor);cornerRadius=x.getDimension(R.styleable.SmartButton_smartCornerRadius,dp(cornerRadius))/getResources().getDisplayMetrics().density;rippleColor=x.getColor(R.styleable.SmartButton_smartRippleColor,rippleColor);String cd=x.getString(R.styleable.SmartButton_smartContentDescription);if(cd!=null)setContentDescription(cd);x.recycle();}setGravity(Gravity.CENTER);setTextColor(textColor);setTextSize(14);setTypeface(android.graphics.Typeface.DEFAULT,android.graphics.Typeface.BOLD);setMinHeight(dp(SmartDimensions.BUTTON_HEIGHT));setPadding(dp(24),0,dp(24),0);setAllCaps(false);updateBackground();SmartState.accessible(this,getContentDescription());}
    private int dp(float v){return SmartDimensions.dp(getContext(),v);} private void updateBackground(){GradientDrawable d=new GradientDrawable();d.setColor(backgroundColor);d.setCornerRadius(dp(cornerRadius));SmartState.applyRipple(this,d,rippleColor);}
    public SmartButton setButtonText(CharSequence t){setText(t);if(getContentDescription()==null)setContentDescription(t);return this;} public SmartButton setButtonColor(int c){backgroundColor=c;updateBackground();return this;} public SmartButton setButtonTextColor(int c){textColor=c;setTextColor(c);return this;} public SmartButton setRippleColor(int c){rippleColor=c;updateBackground();return this;} public SmartButton setCornerRadius(float d){cornerRadius=d;updateBackground();return this;}
    @Override public boolean onTouchEvent(MotionEvent e){if(e.getActionMasked()==MotionEvent.ACTION_DOWN)SmartAnimations.press(this);if(e.getActionMasked()==MotionEvent.ACTION_UP||e.getActionMasked()==MotionEvent.ACTION_CANCEL)SmartAnimations.release(this);return super.onTouchEvent(e);}
}