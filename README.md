# SmartMaterial

A lightweight, dependency-free Material 3 inspired Android UI library built in Java.

## Highlights
- Java and Android framework APIs only
- No Google Material Components dependency
- AndroidX compatible
- minSdk 23
- Material-inspired color, typography, shape, elevation and motion utilities
- Core controls, progress indicators, feedback, search and navigation components
- Light/dark color tokens
- XML styleable attributes
- Ripple and accessibility helpers

## Installation

JitPack:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.smarthubafrika:SmartMaterial:1.0.0")
}
```

## Java

```java
SmartButton button = new SmartButton(this)
        .setButtonText("Continue");

SmartTextField name = new SmartTextField(this)
        .setLabel("Name")
        .setHint("Enter your name");

SmartCard card = new SmartCard(this);
```

## Theme helpers

```java
int surface = SmartTheme.surface(context);
int primary = SmartTheme.primary(context);
boolean dark = SmartTheme.isDark(context);

SmartState.applyRipple(view, SmartColors.PRIMARY, SmartColors.PRIMARY_CONTAINER);
SmartState.accessible(view, "Open settings");
```


## Documentation

For the complete installation instructions, component reference, public API examples, theming, accessibility, XML usage, troubleshooting and usage patterns, see the [SmartMaterial User Guide](docs/USER_GUIDE.md).

## Components

Buttons, icon buttons, cards, text fields, chips, switches, checkboxes, radio buttons, sliders, dropdowns, search bars, badges, progress indicators, dialogs, snackbar, toast, bottom sheets, app bars, navigation bars, navigation rails and FABs are included.

## Design philosophy

SmartMaterial is Material 3 inspired, not a copy of Google's Material Components. The library uses Android framework APIs and keeps the dependency surface small.

## License

Apache License 2.0.
