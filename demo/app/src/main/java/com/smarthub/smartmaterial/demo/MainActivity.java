package com.smarthub.smartmaterial.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import com.smarthub.smartmaterial.button.*;
import com.smarthub.smartmaterial.card.SmartCard;
import com.smarthub.smartmaterial.chip.SmartChip;
import com.smarthub.smartmaterial.control.*;
import com.smarthub.smartmaterial.dropdown.SmartDropdown;
import com.smarthub.smartmaterial.navigation.*;
import com.smarthub.smartmaterial.progress.*;
import com.smarthub.smartmaterial.search.SmartSearchBar;
import com.smarthub.smartmaterial.slider.SmartSlider;
import com.smarthub.smartmaterial.switcher.SmartSwitch;
import com.smarthub.smartmaterial.textfield.SmartTextField;
import com.smarthub.smartmaterial.theme.SmartColors;
import com.smarthub.smartmaterial.badge.SmartBadge;

public class MainActivity extends Activity {
    private int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
    private TextView heading(String s){TextView v=new TextView(this);v.setText(s);v.setTextSize(20);v.setTextColor(SmartColors.ON_SURFACE);v.setTypeface(null,1);v.setPadding(0,dp(18),0,dp(8));return v;}
    @Override protected void onCreate(Bundle b){super.onCreate(b);
        ScrollView scroll=new ScrollView(this); LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(dp(20),dp(20),dp(20),dp(24));root.setBackgroundColor(SmartColors.SURFACE);scroll.addView(root);
        TextView title=new TextView(this);title.setText("SmartMaterial");title.setTextSize(30);title.setTextColor(SmartColors.ON_SURFACE);title.setTypeface(null,1);root.addView(title);
        TextView sub=new TextView(this);sub.setText("Material 3 inspired • Java • dependency-free");sub.setTextSize(14);sub.setTextColor(SmartColors.ON_SURFACE_VARIANT);root.addView(sub);
        root.addView(heading("Buttons"));SmartButton btn=new SmartButton(this).setButtonText("Primary button");root.addView(btn,new LinearLayout.LayoutParams(-1,dp(48)));SmartIconButton icon=new SmartIconButton(this).setIcon(android.R.drawable.ic_menu_search);icon.setContentDescription("Search");LinearLayout.LayoutParams ip=new LinearLayout.LayoutParams(dp(48),dp(48));ip.topMargin=dp(8);root.addView(icon,ip);
        root.addView(heading("Text & selection"));SmartTextField field=new SmartTextField(this).setLabel("Name").setHint("Enter your name");root.addView(field,new LinearLayout.LayoutParams(-1,dp(72)));SmartSearchBar search=new SmartSearchBar(this);search.setContentDescription("Search");LinearLayout.LayoutParams qp=new LinearLayout.LayoutParams(-1,dp(56));qp.topMargin=dp(8);root.addView(search,qp);SmartDropdown drop=new SmartDropdown(this).setItems("General","Business","Personal");LinearLayout.LayoutParams dd=new LinearLayout.LayoutParams(-1,dp(56));dd.topMargin=dp(8);root.addView(drop,dd);
        LinearLayout choices=new LinearLayout(this);choices.setGravity(Gravity.CENTER_VERTICAL);SmartSwitch sw=new SmartSwitch(this);SmartCheckbox cb=new SmartCheckbox(this);SmartRadioButton rb=new SmartRadioButton(this);choices.addView(sw);choices.addView(cb);choices.addView(rb);root.addView(choices,new LinearLayout.LayoutParams(-1,dp(56)));
        root.addView(heading("Chips & badges"));LinearLayout chips=new LinearLayout(this);chips.setGravity(Gravity.CENTER_VERTICAL);chips.addView(new SmartChip(this).setChipText("Featured"));chips.addView(new SmartBadge(this).setBadgeText("3"));root.addView(chips);
        root.addView(heading("Progress"));SmartLinearProgress lp=new SmartLinearProgress(this);lp.setProgress(.65f);root.addView(lp,new LinearLayout.LayoutParams(-1,dp(6)));SmartCircularProgress cp=new SmartCircularProgress(this);cp.setProgress(.72f);cp.setContentDescription("72 percent complete");LinearLayout.LayoutParams cpp=new LinearLayout.LayoutParams(dp(64),dp(64));cpp.gravity=Gravity.CENTER_HORIZONTAL;cpp.topMargin=dp(16);root.addView(cp,cpp);SmartLoadingDots dots=new SmartLoadingDots(this);dots.setContentDescription("Loading");root.addView(dots,new LinearLayout.LayoutParams(-1,dp(28)));
        root.addView(heading("Slider"));SmartSlider slider=new SmartSlider(this).setValue(.5f);root.addView(slider,new LinearLayout.LayoutParams(-1,dp(48)));
        root.addView(heading("Card"));SmartCard card=new SmartCard(this);TextView ct=new TextView(this);ct.setText("SmartCard\nMaterial-style shape, stroke and elevation.");ct.setTextSize(15);ct.setTextColor(SmartColors.ON_SURFACE);card.addView(ct,new FrameLayout.LayoutParams(-1,-2));root.addView(card,new LinearLayout.LayoutParams(-1,dp(100)));
        root.addView(heading("Navigation"));SmartTopAppBar bar=new SmartTopAppBar(this).setTitle("Top App Bar");root.addView(bar,new LinearLayout.LayoutParams(-1,dp(56)));SmartNavigationBar nav=new SmartNavigationBar(this);nav.addItem("Home",0,null).addItem("Search",0,null).addItem("Profile",0,null);root.addView(nav,new LinearLayout.LayoutParams(-1,dp(80)));
        SmartFloatingActionButton fab=new SmartFloatingActionButton(this).setIcon(android.R.drawable.ic_input_add);LinearLayout.LayoutParams fp=new LinearLayout.LayoutParams(dp(56),dp(56));fp.gravity=Gravity.CENTER_HORIZONTAL;fp.topMargin=dp(16);root.addView(fab,fp);
        setContentView(scroll);
    }
}