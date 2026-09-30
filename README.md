# SmartMaterial

A small, dependency-free, Material 3 inspired Android UI library written in Java.

SmartMaterial provides reusable Android UI components with a clean API, rounded surfaces, ripple feedback, typography support, light/dark theme utilities, and modern Material-inspired styling — without requiring Google Material Components.

## Current version

**SmartMaterial v1.0.8**

## Features

- Java-based Android UI library
- minSdk 23
- Android framework APIs only
- No Google Material Components dependency
- No external runtime UI dependencies
- Light and dark theme support
- Built-in Google Sans Light, Medium, and Bold typography
- Ripple and touch feedback
- Programmatic UI components
- Suitable for Android Studio and Sketchware Pro
- Published through JitPack

## Requirements

- Android project using Java
- minSdk 23 or higher
- Android SDK / Android Gradle Plugin compatible with the library
- JitPack repository for Gradle dependency resolution

## Components

### Buttons and basic controls

- SmartButton
- SmartIconButton
- SmartFAB
- SmartSwitch
- SmartCheckbox
- SmartRadioButton
- SmartChip
- SmartBadge
- SmartSegmentedControl
- SmartFilterChipGroup

### Text and input

- SmartTextField
- SmartSearchBar
- SmartDropdown
- SmartDatePicker
- SmartRatingBar

### Cards and content

- SmartCard
- SmartImageCard
- SmartListItem
- SmartDivider
- SmartAvatar

### Feedback and dialogs

- SmartSnackbar
- SmartDialog
- SmartConfirmDialog
- SmartAlert
- SmartTooltip

### Progress and state

- SmartProgress
- SmartCircularProgress
- SmartLoadingState
- SmartEmptyState
- SmartErrorState

### Navigation

- SmartTopAppBar
- SmartBottomNavigation
- SmartNavigationDrawer
- SmartTabLayout

### Workflow and data navigation

- SmartTimeline
- SmartStepper
- SmartPagination

### Theme utilities

- SmartColors
- SmartTheme
- SmartMaterial

## Quick example

```java
SmartButton button = new SmartButton(this)
        .setButtonText("Continue");

button.setOnClickListener(v ->
        SmartSnackbar.show(v, "Button clicked"));

SmartTextField name = new SmartTextField(this)
        .setLabel("Name")
        .setHint("Enter your name");

SmartSearchBar search = new SmartSearchBar(this);

SmartBadge badge = new SmartBadge(this);

SmartAvatar avatar = new SmartAvatar(this);

SmartCircularProgress progress = new SmartCircularProgress(this);
progress.setIndeterminate(true);
```

## Google Sans typography

SmartMaterial includes three Google Sans font assets:

- `google_sans_light.ttf`
- `google_sans_medium.ttf`
- `google_sans_bold.ttf`

Typography can be applied through `SmartTheme`:

```java
SmartTheme.light(textView);
SmartTheme.medium(textView);
SmartTheme.bold(textView);
```

You can also obtain the typefaces directly:

```java
Typeface light = SmartTheme.getLightTypeface(this);
Typeface medium = SmartTheme.getMediumTypeface(this);
Typeface bold = SmartTheme.getBoldTypeface(this);
```

## Theme

SmartMaterial provides automatic light/dark theme helpers:

```java
int surface = SmartTheme.surface(this);
int text = SmartTheme.onSurface(this);
int primary = SmartTheme.primary(this);

boolean dark = SmartTheme.isDark(this);
```

Components use the theme utilities where appropriate, so applications can use SmartMaterial without manually styling every component.

## SmartButton

SmartButton includes rounded styling, ripple feedback, press animation, disabled-state handling, and JitPack-inspired red and green button presets.

```java
SmartButton red = new SmartButton(this)
        .setButtonText("Delete")
        .setRedButton();

SmartButton green = new SmartButton(this)
        .setButtonText("Continue")
        .setGreenButton();

SmartButton custom = new SmartButton(this)
        .setButtonText("Custom")
        .setButtonColor(0xFF3F51B5)
        .setCornerRadius(8);
```

## SmartTextField

SmartTextField provides a compact Material-inspired text input with labels, hints, password input, customizable colors, and rounded borders.

```java
SmartTextField email = new SmartTextField(this)
        .setLabel("Email")
        .setHint("Enter your email");

SmartTextField password = new SmartTextField(this)
        .setLabel("Password")
        .setHint("Enter your password")
        .setPassword();

String value = email.getText();
```

## Dialog example

```java
SmartDialog.show(
        this,
        "Welcome",
        "This is a SmartMaterial dialog."
);
```

For custom dialog content:

```java
LinearLayout content = new LinearLayout(this);
content.setOrientation(LinearLayout.VERTICAL);

SmartTextField username = new SmartTextField(this)
        .setLabel("Username")
        .setHint("Enter username");

content.addView(username);

SmartDialog.showView(
        this,
        "Login",
        content,
        "Login",
        (dialog, which) -> {
            String user = username.getText();
        }
);
```

## Snackbar example

```java
SmartSnackbar.show(this.getWindow().getDecorView(), "Saved successfully");
```

Or attach it to a view:

```java
SmartSnackbar.show(button, "Saved successfully");
```

## JitPack

Add JitPack to your root `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

For **SmartMaterial v1.0.8**:

```gradle
dependencies {
    implementation 'com.github.smarthubafrika:SmartMaterial:v1.0.8'
}
```

The repository-level coordinate above is the recommended dependency for applications using SmartMaterial.

## Sketchware Pro

SmartMaterial is designed to work with programmatic Android views, making it suitable for Sketchware Pro.

After adding the JitPack dependency, import the components you use. For example:

```java
import com.smarthub.smartmaterial.button.SmartButton;
import com.smarthub.smartmaterial.textfield.SmartTextField;
import com.smarthub.smartmaterial.snackbar.SmartSnackbar;
import com.smarthub.smartmaterial.theme.SmartTheme;
```

Most components can then be created directly inside an Activity event or More Block.

## What's new in 1.0.8

- Added SmartSearchBar
- Added SmartBadge
- Added SmartAvatar
- Added SmartCircularProgress
- Added SmartRatingBar
- Added SmartFAB
- Added SmartAlert
- Added SmartTooltip
- Added SmartConfirmDialog
- Added SmartEmptyState
- Added SmartErrorState
- Added SmartLoadingState
- Added SmartBottomNavigation
- Added SmartNavigationDrawer
- Added SmartSegmentedControl
- Added SmartFilterChipGroup
- Added SmartTimeline
- Added SmartStepper
- Added SmartPagination
- Added Google Sans typography assets and automatic theme typography support
- Included compilation fixes and component stability updates

## Design goal

SmartMaterial is Material 3 inspired, not a copy of Google's Material Components.

The library focuses on:

- Simple Java APIs
- Small dependency surface
- Reusable programmatic components
- Consistent styling
- Modern Android UI patterns
- Easy integration with Android Studio and Sketchware Pro

## License

Apache License 2.0.
