package com.smarthub.smartmaterial.theme;

import android.widget.TextView;

public final class SmartTypography {
    private SmartTypography() {}

    public static void displayLarge(TextView v) { style(v, 57f); }
    public static void headlineLarge(TextView v) { style(v, 32f); }
    public static void headlineMedium(TextView v) { style(v, 28f); }
    public static void titleLarge(TextView v) { style(v, 22f); }
    public static void titleMedium(TextView v) { style(v, 16f); }
    public static void bodyLarge(TextView v) { style(v, 16f); }
    public static void bodyMedium(TextView v) { style(v, 14f); }
    public static void bodySmall(TextView v) { style(v, 12f); }
    public static void labelLarge(TextView v) { style(v, 14f); }
    public static void labelMedium(TextView v) { style(v, 12f); }
    public static void labelSmall(TextView v) { style(v, 11f); }

    private static void style(TextView v, float sp) {
        SmartTheme.applyText(v, sp);
    }
}
