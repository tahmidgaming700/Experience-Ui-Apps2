package com.experience.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

@Stable
class ExperienceThemeState(
    initialPalette: ExperiencePalette = ExperiencePalette.SAPPHIRE_OCEAN,
    initialThemeMode: ThemeMode = ThemeMode.SYSTEM,
    initialUseDynamicColor: Boolean = false
) {
    var palette by mutableStateOf(initialPalette)
    var themeMode by mutableStateOf(initialThemeMode)
    var useDynamicColor by mutableStateOf(initialUseDynamicColor)

    val isDynamicColorSupported: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}

val LocalExperienceThemeState = compositionLocalOf {
    ExperienceThemeState()
}

val LocalExperienceGlassTokens = compositionLocalOf {
    ExperienceColorSchemes.getGlassTokens(ExperiencePalette.SAPPHIRE_OCEAN, false)
}

/**
 * Main theme provider for the Experience UI ecosystem.
 * Supports Android 9 (API 28) with manual expressive color palette selection,
 * and Android 12+ (API 31+) with wallpaper-based dynamic color tokens.
 */
@Composable
fun ExperienceTheme(
    state: ExperienceThemeState = remember { ExperienceThemeState() },
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val systemInDark = isSystemInDarkTheme()
    val isDark = when (state.themeMode) {
        ThemeMode.SYSTEM -> systemInDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme: ColorScheme = when {
        state.useDynamicColor && state.isDynamicColorSupported -> {
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDark -> ExperienceColorSchemes.getDarkColorScheme(state.palette)
        else -> ExperienceColorSchemes.getLightColorScheme(state.palette)
    }

    val glassTokens = remember(state.palette, isDark) {
        ExperienceColorSchemes.getGlassTokens(state.palette, isDark)
    }

    CompositionLocalProvider(
        LocalExperienceThemeState provides state,
        LocalExperienceGlassTokens provides glassTokens
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = ExperienceShapes.materialShapes,
            typography = ExperienceTypography.typography,
            content = content
        )
    }
}
