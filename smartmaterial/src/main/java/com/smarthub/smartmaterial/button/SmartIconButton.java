package com.smarthub.smartmaterial.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import com.smarthub.smartmaterial.R;
import com.smarthub.smartmaterial.animation.SmartAnimations;
import com.smarthub.smartmaterial.theme.*;

public class SmartIconButton extends ImageButton {
    private int backgroundColor,iconTint,rippleColor; private float cornerRadius=20f;
    public SmartIconButton(Context c){super(c);init(null);} public SmartIconButton(Context c,AttributeSet a){super(c,a);init(a);} public SmartIconButton(Context c,AttributeSet a,int s){super(c,a,s);init(a);}
    private void init(AttributeSet a){
        backgroundColor=SmartTheme.surface(getContext()); iconTint=SmartTheme.onSurface(getContext()); rippleColor=SmartTheme.primary(getContext());
        if(a!=null){TypedArray x=getContext().obtainStyledAttributes(a,R.styleable.SmartIconButton);backgroundColor=x.getColor(R.styleable.SmartIconButton_smartButtonColor,backgroundColor);iconTint=x.getColor(R.styleable.SmartIconButton_smartIconTint,iconTint);cornerRadius=x.getDimension(R.styleable.SmartIconButton_smartCornerRadius,dp(cornerRadius))/getResources().getDisplayMetrics().density;rippleColor=x.getColor(R.styleable.SmartIconButton_smartRippleColor,rippleColor);String cd=x.getString(R.styleable.SmartIconButton_smartContentDescription);if(cd!=null)setContentDescription(cd);x.recycle();}
        setScaleType(ScaleType.CENTER);setColorFilter(iconTint);setPadding(dp(12),dp(12),dp(12),dp(12));setMinimumWidth(dp(48));setMinimumHeight(dp(48));updateBackground();SmartState.accessible(this,getContentDescription());
    }
    private int dp(float v){return SmartDimensions.dp(getContext(),v);}
    private void updateBackground(){GradientDrawable d=new GradientDrawable();d.setColor(backgroundColor);d.setCornerRadius(dp(cornerRadius));SmartState.applyRipple(this,d,rippleColor);}
    public SmartIconButton setIcon(int r){setImageResource(r);return this;} public SmartIconButton setIconTint(int c){iconTint=c;setColorFilter(c);return this;} public SmartIconButton setButtonColor(int c){backgroundColor=c;updateBackground();return this;} public SmartIconButton setRippleColor(int c){rippleColor=c;updateBackground();return this;} public SmartIconButton setCornerRadius(float d){cornerRadius=d;updateBackground();return this;} public SmartIconButton setIconSize(float d){int p=dp(d);setPadding(p,p,p,p);return this;}
    @Override public boolean onTouchEvent(MotionEvent e){if(e.getActionMasked()==MotionEvent.ACTION_DOWN)SmartAnimations.press(this);if(e.getActionMasked()==MotionEvent.ACTION_UP||e.getActionMasked()==MotionEvent.ACTION_CANCEL)SmartAnimations.release(this);return super.onTouchEvent(e);}
}