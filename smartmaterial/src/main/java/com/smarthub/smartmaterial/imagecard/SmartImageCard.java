package com.smarthub.smartmaterial.imagecard;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartImageCard extends FrameLayout {
    private final ImageView image;
    private final TextView title;
    public SmartImageCard(Context c){
        super(c);
        setClipToOutline(true);
        setBackground(background());
        image=new ImageView(c);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(image,new FrameLayout.LayoutParams(-1,-1));
        title=new TextView(c);
        title.setTextSize(16);
        title.setTextColor(0xFFFFFFFF);
        SmartTheme.medium(title);
        title.setGravity(Gravity.BOTTOM);
        title.setPadding(dp(16),dp(40),dp(16),dp(14));
        addView(title,new FrameLayout.LayoutParams(-1,dp(80),Gravity.BOTTOM));
    }
    public SmartImageCard setImageResource(int resId){image.setImageResource(resId);return this;}
    public SmartImageCard setTitle(CharSequence text){title.setText(text);return this;}
    public ImageView getImageView(){return image;}
    private GradientDrawable background(){GradientDrawable d=new GradientDrawable();d.setColor(SmartTheme.surface(getContext()));d.setCornerRadius(dp(16));return d;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}