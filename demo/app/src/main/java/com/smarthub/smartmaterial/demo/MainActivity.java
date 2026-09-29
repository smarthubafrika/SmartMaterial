package com.smarthub.smartmaterial.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthub.smartmaterial.button.SmartButton;
import com.smarthub.smartmaterial.card.SmartCard;
import com.smarthub.smartmaterial.progress.SmartCircularProgress;
import com.smarthub.smartmaterial.progress.SmartLinearProgress;
import com.smarthub.smartmaterial.progress.SmartLoadingDots;
import com.smarthub.smartmaterial.textfield.SmartTextField;
import com.smarthub.smartmaterial.theme.SmartColors;

public class MainActivity extends Activity {
    private int dp(float v) { return Math.round(v * getResources().getDisplayMetrics().density); }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(24));
        root.setBackgroundColor(SmartColors.SURFACE);

        TextView title = new TextView(this);
        title.setText("SmartMaterial");
        title.setTextSize(28);
        title.setTextColor(SmartColors.ON_SURFACE);
        title.setTypeface(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD);
        root.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView subtitle = new TextView(this);
        subtitle.setText("Material 3 inspired • Java • dependency-free");
        subtitle.setTextSize(14);
        subtitle.setTextColor(SmartColors.ON_SURFACE_VARIANT);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = dp(6);
        root.addView(subtitle, sp);

        SmartCard card = new SmartCard(this);
        SmartTextField field = new SmartTextField(this).setLabel("Name").setHint("Enter your name");
        card.addView(field, new FrameLayout.LayoutParams(-1, dp(72)));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, dp(100));
        cp.topMargin = dp(24);
        root.addView(card, cp);

        SmartButton button = new SmartButton(this).setButtonText("Continue");
        button.setContentDescription("Continue");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, dp(48));
        bp.topMargin = dp(16);
        root.addView(button, bp);

        SmartLinearProgress linear = new SmartLinearProgress();
        linear.setIndeterminate(false);
        linear.setProgress(0.65f);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(6));
        lp.topMargin = dp(24);
        root.addView(linear, lp);

        SmartCircularProgress circular = new SmartCircularProgress(this);
        circular.setIndeterminate(false);
        circular.setProgress(0.72f);
        circular.setContentDescription("72 percent complete");
        LinearLayout.LayoutParams ctp = new LinearLayout.LayoutParams(dp(64), dp(64));
        ctp.gravity = Gravity.CENTER_HORIZONTAL;
        ctp.topMargin = dp(24);
        root.addView(circular, ctp);

        SmartLoadingDots dots = new SmartLoadingDots(this);
        dots.setContentDescription("Loading");
        LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(-1, dp(24));
        dlp.topMargin = dp(16);
        root.addView(dots, dlp);

        setContentView(root);
    }
}
