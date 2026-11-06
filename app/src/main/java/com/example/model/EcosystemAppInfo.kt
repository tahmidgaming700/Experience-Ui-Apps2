package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector

data class EcosystemAppInfo(
    val id: String,
    val name: String,
    val packageName: String,
    val category: String,
    val description: String,
    val icon: ImageVector,
    val role: String,
    val minSdk: String = "Android 9 (API 28)",
    val targetSdk: String = "Android 15 (API 35/36)",
    val artifactFile: String,
    val keyFeatures: List<String>
)

object EcosystemRegistry {
    val apps = listOf(
        EcosystemAppInfo(
            id = "launcher",
            name = "Experience Launcher",
            packageName = "com.experience.launcher",
            category = "System Core",
            description = "Default home application featuring customizable grid, app drawer, live widgets, and Material 3 Expressive motion.",
            icon = Icons.Default.Home,
            role = "android.app.role.HOME",
            artifactFile = "artifacts/apk/experience-launcher.apk",
            keyFeatures = listOf(
                "Default home role handler",
                "Customizable app drawer and icon grids",
                "Quick search and application filtering",
                "Date/time and weather card integration"
            )
        ),
        EcosystemAppInfo(
            id = "settings",
            name = "Experience Settings",
            packageName = "com.experience.settings",
            category = "System Core",
            description = "Unified Experience UI preferences studio clearly distinguishing app settings from protected system configurations.",
            icon = Icons.Default.Settings,
            role = "Settings Provider",
            artifactFile = "artifacts/apk/experience-settings.apk",
            keyFeatures = listOf(
                "Theme & palette customization",
                "Device information & hardware status",
                "Display, sound and gesture controls",
                "Safe system setting handoffs"
            )
        ),
        EcosystemAppInfo(
            id = "phone",
            name = "Experience Phone",
            packageName = "com.experience.phone",
            category = "Communications",
            description = "Full-featured dialer with call history, speed dial, DTMF audio synthesis, and Android default dialer role integration.",
            icon = Icons.Default.Call,
            role = "android.app.role.DIALER",
            artifactFile = "artifacts/apk/experience-phone.apk",
            keyFeatures = listOf(
                "Default dialer role integration",
                "Interactive dialpad with haptic feedback",
                "Call history and contact speed-dial",
                "Emergency call routing support"
            )
        ),
        EcosystemAppInfo(
            id = "contacts",
            name = "Experience Contacts",
            packageName = "com.experience.contacts",
            category = "Communications",
            description = "Read, create, edit, search and organize device contacts with robust permission management.",
            icon = Icons.Default.Contacts,
            role = "Contacts Provider API",
            artifactFile = "artifacts/apk/experience-contacts.apk",
            keyFeatures = listOf(
                "ContactsContract integration",
                "Favorites and group organization",
                "Quick call & SMS action triggers",
                "Import/export vCard compatibility"
            )
        ),
        EcosystemAppInfo(
            id = "messages",
            name = "Experience Messages",
            packageName = "com.experience.messages",
            category = "Communications",
            description = "Native SMS inbox and conversation interface with support for Android default SMS role handling.",
            icon = Icons.Default.Chat,
            role = "android.app.role.SMS",
            artifactFile = "artifacts/apk/experience-messages.apk",
            keyFeatures = listOf(
                "Default SMS role support",
                "Threaded conversation view",
                "Telephony SMS delivery & receipt",
                "Expressive message bubbles and search"
            )
        ),
        EcosystemAppInfo(
            id = "gallery",
            name = "Experience Gallery",
            packageName = "com.experience.gallery",
            category = "Media",
            description = "High-performance photo and video browser using Android Storage Access Framework and MediaStore APIs.",
            icon = Icons.Default.PhotoLibrary,
            role = "MediaStore API",
            artifactFile = "artifacts/apk/experience-gallery.apk",
            keyFeatures = listOf(
                "MediaStore photo/video scanning",
                "Full-bleed image viewer with pinch-zoom",
                "Album categorization and date grouping",
                "Android Photo Picker fallback"
            )
        ),
        EcosystemAppInfo(
            id = "camera",
            name = "Experience Camera",
            packageName = "com.experience.camera",
            category = "Media",
            description = "Native camera implementation built with CameraX, supporting preview, photo capture, video recording, and flash modes.",
            icon = Icons.Default.CameraAlt,
            role = "CameraX API",
            artifactFile = "artifacts/apk/experience-camera.apk",
            keyFeatures = listOf(
                "CameraX lifecycle-aware preview",
                "High-resolution image capture",
                "Torch, flash and aspect ratio controls",
                "Front/rear lens switching"
            )
        ),
        EcosystemAppInfo(
            id = "files",
            name = "Experience Files",
            packageName = "com.experience.files",
            category = "Utilities",
            description = "Clean file explorer with directory browsing, file details, categorization, and Storage Access Framework.",
            icon = Icons.Default.Folder,
            role = "Storage Access Framework",
            artifactFile = "artifacts/apk/experience-files.apk",
            keyFeatures = listOf(
                "Storage Access Framework (SAF)",
                "Document and download management",
                "File size and MIME-type categorization",
                "Secure intent-based file openers"
            )
        ),
        EcosystemAppInfo(
            id = "clock",
            name = "Experience Clock",
            packageName = "com.experience.clock",
            category = "Utilities",
            description = "Comprehensive time utility featuring world clock, alarms, millisecond-precision stopwatch, and countdown timer.",
            icon = Icons.Default.Schedule,
            role = "AlarmClock API",
            artifactFile = "artifacts/apk/experience-clock.apk",
            keyFeatures = listOf(
                "Multi-city World Clock with timezone offsets",
                "Interactive stopwatch with lap tracking",
                "Custom countdown timer with sound notifications",
                "System alarm provider integration"
            )
        ),
        EcosystemAppInfo(
            id = "calendar",
            name = "Experience Calendar",
            packageName = "com.experience.calendar",
            category = "Productivity",
            description = "Month, week and agenda calendar views with event creation, reminders, and calendar provider sync.",
            icon = Icons.Default.CalendarMonth,
            role = "CalendarContract API",
            artifactFile = "artifacts/apk/experience-calendar.apk",
            keyFeatures = listOf(
                "CalendarContract synchronization",
                "Interactive month grid and day schedules",
                "Event creation with alarm reminders",
                "Expressive category color chips"
            )
        ),
        EcosystemAppInfo(
            id = "calculator",
            name = "Experience Calculator",
            packageName = "com.experience.calculator",
            category = "Productivity",
            description = "Fully functional calculator supporting arithmetic, scientific functions (trig, log, powers, sqrt), memory, and calculation history.",
            icon = Icons.Default.Calculate,
            role = "Standard Utility",
            artifactFile = "artifacts/apk/experience-calculator.apk",
            keyFeatures = listOf(
                "Standard and Scientific function toggles",
                "Parentheses, powers, square root, sin/cos/tan",
                "Calculation history with replay capability",
                "Haptic key feedback and expression editing"
            )
        ),
        EcosystemAppInfo(
            id = "notes",
            name = "Experience Notes",
            packageName = "com.experience.notes",
            category = "Productivity",
            description = "Fast local note taking with categories, pin to top, search, timestamps, and Room database persistence.",
            icon = Icons.Default.Notes,
            role = "Local Room Persistence",
            artifactFile = "artifacts/apk/experience-notes.apk",
            keyFeatures = listOf(
                "Room database local storage",
                "Tag-based organization (Work, Ideas, System)",
                "Full-text search across titles and notes",
                "Pinning notes and export capabilities"
            )
        ),
        EcosystemAppInfo(
            id = "weather",
            name = "Experience Weather",
            packageName = "com.experience.weather",
            category = "Utilities",
            description = "Atmospheric weather forecast app with dynamic conditions, hourly outlook, air quality, humidity, and wind stats.",
            icon = Icons.Default.WbSunny,
            role = "Location & Weather Provider",
            artifactFile = "artifacts/apk/experience-weather.apk",
            keyFeatures = listOf(
                "Atmospheric dynamic condition animations",
                "Hourly forecast scrollable pills",
                "7-day weather outlook cards",
                "Humidity, wind speed, pressure and UV metrics"
            )
        ),
        EcosystemAppInfo(
            id = "wallpaper",
            name = "Experience Wallpaper",
            packageName = "com.experience.wallpaper",
            category = "Customization",
            description = "Curated abstract, glassmorphic and geometric wallpapers with palette color extraction and WallpaperManager integration.",
            icon = Icons.Default.Wallpaper,
            role = "WallpaperManager API",
            artifactFile = "artifacts/apk/experience-wallpaper.apk",
            keyFeatures = listOf(
                "Curated high-res Experience UI wallpapers",
                "WallpaperManager lock/home screen apply",
                "Palette color extraction for dynamic theming",
                "Preview with lockscreen clock overlay"
            )
        ),
        EcosystemAppInfo(
            id = "setup",
            name = "Experience Setup",
            packageName = "com.experience.setup",
            category = "System Core",
            description = "First-run onboarding wizard for language, Wi-Fi, appearance palette, navigation gestures, and account setup.",
            icon = Icons.Default.Smartphone,
            role = "Setup Wizard Initializer",
            artifactFile = "artifacts/apk/experience-setup.apk",
            keyFeatures = listOf(
                "Step-by-step onboarding walkthrough",
                "Initial theme palette selection",
                "System gesture navigation preference",
                "Experience UI ecosystem orientation"
            )
        )
    )
}
