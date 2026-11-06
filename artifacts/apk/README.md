# Experience UI — APK Artifacts & Distribution Directory

Welcome to the **Experience UI Android Ecosystem** APK distribution directory.

All applications in this ecosystem are **genuinely independent, native Android applications**, built using Kotlin, Jetpack Compose, and Google's **Material 3 Expressive** design system.

---

## Target Devices & Compatibility

- **Primary Baseline Device:** Huawei Y9 2019 (Model: `JKM-LX2`)
- **Android Version Baseline:** Android 9.0 Pie (API Level 28)
- **Firmware:** EMUI 9.1
- **CPU Architecture:** ARM64 (`aarch64` / Kirin 710)
- **Forward Compatibility:** Android 10 (Q), Android 11 (R), Android 12 (S, with wallpaper dynamic colors), Android 13/14/15/16.

---

## Ecosystem Application Roster

| Application Name | Output File | Package Name | Min SDK | Target SDK | System Role / API |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Experience Launcher** | `experience-launcher.apk` | `com.experience.launcher` | API 28 (Android 9) | API 35/36 | `android.app.role.HOME` |
| **Experience Settings** | `experience-settings.apk` | `com.experience.settings` | API 28 (Android 9) | API 35/36 | Unified Preference Hub |
| **Experience Phone** | `experience-phone.apk` | `com.experience.phone` | API 28 (Android 9) | API 35/36 | `android.app.role.DIALER` |
| **Experience Contacts** | `experience-contacts.apk` | `com.experience.contacts` | API 28 (Android 9) | API 35/36 | `ContactsContract` |
| **Experience Messages** | `experience-messages.apk` | `com.experience.messages` | API 28 (Android 9) | API 35/36 | `android.app.role.SMS` |
| **Experience Gallery** | `experience-gallery.apk` | `com.experience.gallery` | API 28 (Android 9) | API 35/36 | `MediaStore` & SAF |
| **Experience Camera** | `experience-camera.apk` | `com.experience.camera` | API 28 (Android 9) | API 35/36 | CameraX API |
| **Experience Files** | `experience-files.apk` | `com.experience.files` | API 28 (Android 9) | API 35/36 | Storage Access Framework |
| **Experience Clock** | `experience-clock.apk` | `com.experience.clock` | API 28 (Android 9) | API 35/36 | `AlarmClock` & Precision Timer |
| **Experience Calendar** | `experience-calendar.apk` | `com.experience.calendar` | API 28 (Android 9) | API 35/36 | `CalendarContract` |
| **Experience Calculator** | `experience-calculator.apk` | `com.experience.calculator` | API 28 (Android 9) | API 35/36 | Scientific Math Engine |
| **Experience Notes** | `experience-notes.apk` | `com.experience.notes` | API 28 (Android 9) | API 35/36 | Local Room Database |
| **Experience Weather** | `experience-weather.apk` | `com.experience.weather` | API 28 (Android 9) | API 35/36 | Atmospheric Weather Engine |
| **Experience Wallpaper** | `experience-wallpaper.apk` | `com.experience.wallpaper` | API 28 (Android 9) | API 35/36 | `WallpaperManager` & Palette Extraction |
| **Experience Setup** | `experience-setup.apk` | `com.experience.setup` | API 28 (Android 9) | API 35/36 | Ecosystem Onboarding Wizard |

---

## Installation Instructions

### Via Android Debug Bridge (ADB)
Connect your device (or start an emulator with API 28+) with USB Debugging enabled:

```bash
# Install individual application:
adb install -r artifacts/apk/experience-launcher.apk

# Install the entire Experience UI suite:
for apk in artifacts/apk/*.apk; do
    adb install -r "$apk"
done
```

### On Huawei Y9 2019 / EMUI 9.1:
1. Transfer the `.apk` files to internal storage or SD card via USB file transfer or Huawei Share.
2. Open the built-in **Files** app.
3. Tap on the desired APK file.
4. When prompted by EMUI, select **Allow installation from this source**.
5. Follow the on-screen installer prompts.

---

## Setting Default Roles

On Android 9 and newer, applications request role assignment via Android's `RoleManager`:

- **Default Home Launcher:** Navigate to `Settings > Apps > Default Apps > Launcher` and select **Experience Launcher**.
- **Default Dialer:** Navigate to `Settings > Apps > Default Apps > Phone` and select **Experience Phone**.
- **Default SMS App:** Navigate to `Settings > Apps > Default Apps > SMS` and select **Experience Messages**.

---

## Build Automation & CI

The GitHub Actions workflow `.github/workflows/build-apks.yml` compiles every application module independently, validates APK signatures and zip integrity, populates this directory, and uploads the artifacts for distribution.

To build manually from source:
```bash
gradle assembleDebug
```
