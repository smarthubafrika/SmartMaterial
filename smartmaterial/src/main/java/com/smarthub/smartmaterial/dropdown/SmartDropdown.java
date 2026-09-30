package com.smarthub.smartmaterial.dropdown;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.smarthub.smartmaterial.theme.SmartTheme;

public class SmartDropdown extends LinearLayout {
    private final TextView valueView;
    private final List<String> values=new ArrayList<>();
    private PopupWindow popup;
    private int selected=-1;

    public SmartDropdown(Context c){
        super(c);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(16),0,dp(16),0);
        setMinimumHeight(dp(56));
        setClickable(true);
        valueView=new TextView(c);
        valueView.setTextSize(16);
        valueView.setTextColor(SmartTheme.onSurface(c));
        SmartTheme.light(valueView);
        addView(valueView,new LayoutParams(0,-2,1));
        setBackground(background());
        setOnClickListener(v->showMenu());
    }

    public SmartDropdown setHint(CharSequence hint){valueView.setHint(hint);return this;}
    public SmartDropdown setItems(List<String> items){values.clear();if(items!=null)values.addAll(items);return this;}
    public SmartDropdown setItems(String... items){values.clear();if(items!=null)for(String s:items)values.add(s);return this;}
    public int getSelectedIndex(){return selected;}
    public String getSelectedItem(){return selected>=0&&selected<values.size()?values.get(selected):null;}

    public SmartDropdown showMenu(){
        if(values.isEmpty())return this;
        LinearLayout box=new LinearLayout(getContext());
        box.setOrientation(VERTICAL);
        box.setPadding(dp(4),dp(4),dp(4),dp(4));
        box.setBackground(background());
        for(int i=0;i<values.size();i++){
            final int index=i;
            TextView item=new TextView(getContext());
            item.setText(values.get(i));
            item.setTextSize(16);
            item.setTextColor(SmartTheme.onSurface(getContext()));
            SmartTheme.light(item);
            item.setGravity(Gravity.CENTER_VERTICAL);
            item.setPadding(dp(16),0,dp(16),0);
            item.setMinHeight(dp(48));
            item.setOnClickListener(v->{selected=index;valueView.setText(values.get(index));if(popup!=null)popup.dismiss();});
            box.addView(item,new LayoutParams(-1,dp(48)));
        }
        popup=new PopupWindow(box,Math.max(getWidth(),dp(180)),WindowLayoutParamsWrap(),true);
        popup.setBackgroundDrawable(background());
        popup.setOutsideTouchable(true);
        popup.setElevation(dp(8));
        popup.showAsDropDown(this,0,dp(4));
        return this;
    }

    private int WindowLayoutParamsWrap(){return -2;}
    private GradientDrawable background(){GradientDrawable d=new GradientDrawable();d.setColor(SmartTheme.surface(getContext()));d.setCornerRadius(dp(12));d.setStroke(dp(1),0xFFE0E1E5);return d;}
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
}