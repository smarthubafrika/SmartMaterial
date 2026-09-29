# SmartMaterial

A small, dependency-free Material 3 inspired Android UI library written in Java.

The first release deliberately contains only a few stable components. The goal is reliability, simple APIs and easy use from Android Studio and Sketchware-style Java code.

## Requirements

- Java
- minSdk 23
- No Google Material Components dependency
- Android framework APIs only

## Components

- SmartButton
- SmartCard
- SmartTextField
- SmartProgress
- SmartSnackbar
- SmartColors and SmartTheme utilities

## Quick example

    SmartButton button = new SmartButton(this).setButtonText("Continue");
    button.setOnClickListener(v -> SmartSnackbar.show(v, "Button clicked"));

    SmartTextField name = new SmartTextField(this)
            .setLabel("Name")
            .setHint("Enter your name");

    String value = name.getText();

## JitPack

    repositories {
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        implementation("com.github.smarthubafrika:SmartMaterial:1.0.0")
    }

## Design goal

SmartMaterial is Material 3 inspired, not a copy of Google's Material Components. It uses Android framework APIs and keeps the dependency surface small.

## License

Apache License 2.0.
