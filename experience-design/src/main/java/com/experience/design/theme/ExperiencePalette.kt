package com.experience.design.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Color palettes available for the Experience UI ecosystem.
 * On Android 12+, wallpaper-based dynamic color is supported.
 * On Android 9 (API 28) and above, users can select any of these expressive palettes.
 */
enum class ExperiencePalette(
    val displayName: String,
    val previewColor: Color,
    val accentColor: Color
) {
    SAPPHIRE_OCEAN(
        displayName = "Sapphire Ocean",
        previewColor = Color(0xFF1B72E8),
        accentColor = Color(0xFF00D2FF)
    ),
    CYBER_VIOLET(
        displayName = "Cyber Violet",
        previewColor = Color(0xFF8A4FFF),
        accentColor = Color(0xFFE056FD)
    ),
    EMERALD_MINT(
        displayName = "Emerald Mint",
        previewColor = Color(0xFF00A86B),
        accentColor = Color(0xFF2BD9A5)
    ),
    SUNSET_CORAL(
        displayName = "Sunset Coral",
        previewColor = Color(0xFFFF5E3A),
        accentColor = Color(0xFFFF9500)
    ),
    SAKURA_ROSE(
        displayName = "Sakura Rose",
        previewColor = Color(0xFFE84393),
        accentColor = Color(0xFFFD79A8)
    ),
    TITANIUM_GRAY(
        displayName = "Titanium Slate",
        previewColor = Color(0xFF5A6B82),
        accentColor = Color(0xFF90A4AE)
    )
}

/**
 * Custom luminous styling tokens for Experience UI glassmorphic surfaces.
 */
@Immutable
data class ExperienceGlassTokens(
    val surfaceFill: Color,
    val surfaceBorder: Color,
    val gradientBrush: Brush,
    val glowColor: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val isDark: Boolean
)

object ExperienceColorSchemes {

    fun getLightColorScheme(palette: ExperiencePalette): ColorScheme {
        return when (palette) {
            ExperiencePalette.SAPPHIRE_OCEAN -> lightColorScheme(
                primary = Color(0xFF1B72E8),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFD6E4FF),
                onPrimaryContainer = Color(0xFF001B3F),
                secondary = Color(0xFF436084),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFD8E4F8),
                onSecondaryContainer = Color(0xFF001D36),
                tertiary = Color(0xFF00687A),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFABEDFF),
                onTertiaryContainer = Color(0xFF001F26),
                background = Color(0xFFF6F9FE),
                onBackground = Color(0xFF111C28),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF111C28),
                surfaceVariant = Color(0xFFE2EBF5),
                onSurfaceVariant = Color(0xFF414E5D),
                outline = Color(0xFFBDC9D7),
                outlineVariant = Color(0xFFDDE5EE)
            )
            ExperiencePalette.CYBER_VIOLET -> lightColorScheme(
                primary = Color(0xFF7E3FF2),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFEEDBFF),
                onPrimaryContainer = Color(0xFF2C005F),
                secondary = Color(0xFF655675),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFECDCFF),
                onSecondaryContainer = Color(0xFF201430),
                tertiary = Color(0xFF86438A),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFFFD6FC),
                onTertiaryContainer = Color(0xFF37003E),
                background = Color(0xFFFAF6FE),
                onBackground = Color(0xFF1E1724),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF1E1724),
                surfaceVariant = Color(0xFFEAE1F3),
                onSurfaceVariant = Color(0xFF4C4355),
                outline = Color(0xFFCCC0D8),
                outlineVariant = Color(0xFFE8DEF0)
            )
            ExperiencePalette.EMERALD_MINT -> lightColorScheme(
                primary = Color(0xFF00875A),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFB3F5D1),
                onPrimaryContainer = Color(0xFF002113),
                secondary = Color(0xFF486355),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFCAE9D7),
                onSecondaryContainer = Color(0xFF052015),
                tertiary = Color(0xFF006B5D),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFA1F2E2),
                onTertiaryContainer = Color(0xFF00201B),
                background = Color(0xFFF3FAF6),
                onBackground = Color(0xFF121E18),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF121E18),
                surfaceVariant = Color(0xFFDAE9E0),
                onSurfaceVariant = Color(0xFF3F4E46),
                outline = Color(0xFFB7CCC0),
                outlineVariant = Color(0xFFDDEAE2)
            )
            ExperiencePalette.SUNSET_CORAL -> lightColorScheme(
                primary = Color(0xFFDE3700),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFFDBD1),
                onPrimaryContainer = Color(0xFF3B0900),
                secondary = Color(0xFF77574E),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFFDBD1),
                onSecondaryContainer = Color(0xFF2C150F),
                tertiary = Color(0xFF725C0C),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFFFE08B),
                onTertiaryContainer = Color(0xFF241A00),
                background = Color(0xFFFFF7F5),
                onBackground = Color(0xFF231917),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF231917),
                surfaceVariant = Color(0xFFF5DED8),
                onSurfaceVariant = Color(0xFF53433F),
                outline = Color(0xFFD8C2BC),
                outlineVariant = Color(0xFFF0DDD8)
            )
            ExperiencePalette.SAKURA_ROSE -> lightColorScheme(
                primary = Color(0xFFB81D6D),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFFD8E8),
                onPrimaryContainer = Color(0xFF3D0020),
                secondary = Color(0xFF745663),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFFD8E7),
                onSecondaryContainer = Color(0xFF2B1520),
                tertiary = Color(0xFF7F5538),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFFFDBC8),
                onTertiaryContainer = Color(0xFF321401),
                background = Color(0xFFFEF6F9),
                onBackground = Color(0xFF20171B),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF20171B),
                surfaceVariant = Color(0xFFF2DDE6),
                onSurfaceVariant = Color(0xFF50424A),
                outline = Color(0xFFD4C1CA),
                outlineVariant = Color(0xFFEEDBE4)
            )
            ExperiencePalette.TITANIUM_GRAY -> lightColorScheme(
                primary = Color(0xFF485D75),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFCCE2FF),
                onPrimaryContainer = Color(0xFF001D33),
                secondary = Color(0xFF57606D),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFDBE4F3),
                onSecondaryContainer = Color(0xFF141D28),
                tertiary = Color(0xFF6B5868),
                onTertiary = Color.White,
                tertiaryContainer = Color(0xFFF4DAEE),
                onTertiaryContainer = Color(0xFF251624),
                background = Color(0xFFF7F9FC),
                onBackground = Color(0xFF181C22),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF181C22),
                surfaceVariant = Color(0xFFE0E5ED),
                onSurfaceVariant = Color(0xFF434850),
                outline = Color(0xFFC0C6CF),
                outlineVariant = Color(0xFFDEE3EC)
            )
        }
    }

    fun getDarkColorScheme(palette: ExperiencePalette): ColorScheme {
        return when (palette) {
            ExperiencePalette.SAPPHIRE_OCEAN -> darkColorScheme(
                primary = Color(0xFFA8C8FF),
                onPrimary = Color(0xFF003062),
                primaryContainer = Color(0xFF00468B),
                onPrimaryContainer = Color(0xFFD6E4FF),
                secondary = Color(0xFFB5C9E7),
                onSecondary = Color(0xFF1E324F),
                secondaryContainer = Color(0xFF354866),
                onSecondaryContainer = Color(0xFFD8E4F8),
                tertiary = Color(0xFF55D6F4),
                onTertiary = Color(0xFF003640),
                tertiaryContainer = Color(0xFF004E5C),
                onTertiaryContainer = Color(0xFFABEDFF),
                background = Color(0xFF0A121E),
                onBackground = Color(0xFFE2EAF4),
                surface = Color(0xFF101B2B),
                onSurface = Color(0xFFE2EAF4),
                surfaceVariant = Color(0xFF1A2A3F),
                onSurfaceVariant = Color(0xFFB9CADC),
                outline = Color(0xFF3C536F),
                outlineVariant = Color(0xFF22344B)
            )
            ExperiencePalette.CYBER_VIOLET -> darkColorScheme(
                primary = Color(0xFFD9B9FF),
                onPrimary = Color(0xFF450085),
                primaryContainer = Color(0xFF611CD5),
                onPrimaryContainer = Color(0xFFEEDBFF),
                secondary = Color(0xFFD0C0E4),
                onSecondary = Color(0xFF362847),
                secondaryContainer = Color(0xFF4D3F5F),
                onSecondaryContainer = Color(0xFFECDCFF),
                tertiary = Color(0xFFF8ADF6),
                onTertiary = Color(0xFF511357),
                tertiaryContainer = Color(0xFF6B2B70),
                onTertiaryContainer = Color(0xFFFFD6FC),
                background = Color(0xFF120C1C),
                onBackground = Color(0xFFEFE6F7),
                surface = Color(0xFF1B1428),
                onSurface = Color(0xFFEFE6F7),
                surfaceVariant = Color(0xFF281E3B),
                onSurfaceVariant = Color(0xFFC7B8D6),
                outline = Color(0xFF574474),
                outlineVariant = Color(0xFF332549)
            )
            ExperiencePalette.EMERALD_MINT -> darkColorScheme(
                primary = Color(0xFF75DC9E),
                onPrimary = Color(0xFF003822),
                primaryContainer = Color(0xFF005234),
                onPrimaryContainer = Color(0xFFB3F5D1),
                secondary = Color(0xFFAECDBC),
                onSecondary = Color(0xFF193528),
                secondaryContainer = Color(0xFF304B3E),
                onSecondaryContainer = Color(0xFFCAE9D7),
                tertiary = Color(0xFF85D6C6),
                onTertiary = Color(0xFF003730),
                tertiaryContainer = Color(0xFF005046),
                onTertiaryContainer = Color(0xFFA1F2E2),
                background = Color(0xFF0A1611),
                onBackground = Color(0xFFE2EFE7),
                surface = Color(0xFF12221B),
                onSurface = Color(0xFFE2EFE7),
                surfaceVariant = Color(0xFF1C3429),
                onSurfaceVariant = Color(0xFFB3C8BD),
                outline = Color(0xFF395E4D),
                outlineVariant = Color(0xFF213D30)
            )
            ExperiencePalette.SUNSET_CORAL -> darkColorScheme(
                primary = Color(0xFFFFB5A0),
                onPrimary = Color(0xFF5E1600),
                primaryContainer = Color(0xFF862200),
                onPrimaryContainer = Color(0xFFFFDBD1),
                secondary = Color(0xFFE7BDB2),
                onSecondary = Color(0xFF442A23),
                secondaryContainer = Color(0xFF5D4038),
                onSecondaryContainer = Color(0xFFFFDBD1),
                tertiary = Color(0xFFE2C46C),
                onTertiary = Color(0xFF3C2F00),
                tertiaryContainer = Color(0xFF564500),
                onTertiaryContainer = Color(0xFFFFE08B),
                background = Color(0xFF1A110E),
                onBackground = Color(0xFFF6ECE8),
                surface = Color(0xFF251A16),
                onSurface = Color(0xFFF6ECE8),
                surfaceVariant = Color(0xFF3B2A24),
                onSurfaceVariant = Color(0xFFD6C0BA),
                outline = Color(0xFF6B514A),
                outlineVariant = Color(0xFF45302A)
            )
            ExperiencePalette.SAKURA_ROSE -> darkColorScheme(
                primary = Color(0xFFFFB0D0),
                onPrimary = Color(0xFF5C0032),
                primaryContainer = Color(0xFF830048),
                onPrimaryContainer = Color(0xFFFFD8E8),
                secondary = Color(0xFFE3BDCE),
                onSecondary = Color(0xFF422935),
                secondaryContainer = Color(0xFF5A3F4C),
                onSecondaryContainer = Color(0xFFFFD8E7),
                tertiary = Color(0xFFF3B995),
                onTertiary = Color(0xFF492810),
                tertiaryContainer = Color(0xFF633E23),
                onTertiaryContainer = Color(0xFFFFDBC8),
                background = Color(0xFF170E14),
                onBackground = Color(0xFFF4EAEF),
                surface = Color(0xFF23161F),
                onSurface = Color(0xFFF4EAEF),
                surfaceVariant = Color(0xFF382331),
                onSurfaceVariant = Color(0xFFD4BFCB),
                outline = Color(0xFF67485A),
                outlineVariant = Color(0xFF432B39)
            )
            ExperiencePalette.TITANIUM_GRAY -> darkColorScheme(
                primary = Color(0xFFB1C8E8),
                onPrimary = Color(0xFF183049),
                primaryContainer = Color(0xFF304661),
                onPrimaryContainer = Color(0xFFCCE2FF),
                secondary = Color(0xFFBFC7D5),
                onSecondary = Color(0xFF29323D),
                secondaryContainer = Color(0xFF3F4854),
                onSecondaryContainer = Color(0xFFDBE4F3),
                tertiary = Color(0xFFD7BED3),
                onTertiary = Color(0xFF3A2B3A),
                tertiaryContainer = Color(0xFF524151),
                onTertiaryContainer = Color(0xFFF4DAEE),
                background = Color(0xFF0F141B),
                onBackground = Color(0xFFE1E6EE),
                surface = Color(0xFF171E27),
                onSurface = Color(0xFFE1E6EE),
                surfaceVariant = Color(0xFF242E3B),
                onSurfaceVariant = Color(0xFFBDC5CF),
                outline = Color(0xFF4E5C6E),
                outlineVariant = Color(0xFF2E3A49)
            )
        }
    }

    fun getGlassTokens(palette: ExperiencePalette, isDark: Boolean): ExperienceGlassTokens {
        val baseColor = palette.previewColor
        val accentColor = palette.accentColor

        return if (isDark) {
            ExperienceGlassTokens(
                surfaceFill = Color(0xCC111D2E),
                surfaceBorder = baseColor.copy(alpha = 0.35f),
                gradientBrush = Brush.linearGradient(
                    listOf(
                        Color(0xDD152338),
                        Color(0xCC0E1826),
                        Color(0xBB18263D)
                    )
                ),
                glowColor = accentColor.copy(alpha = 0.25f),
                textPrimary = Color(0xFFF0F6FF),
                textSecondary = Color(0xFFB8CCE5),
                isDark = true
            )
        } else {
            ExperienceGlassTokens(
                surfaceFill = Color(0xE8FFFFFF),
                surfaceBorder = baseColor.copy(alpha = 0.22f),
                gradientBrush = Brush.linearGradient(
                    listOf(
                        Color(0xF5FFFFFF),
                        Color(0xEEF2F7FD),
                        Color(0xE6F8F6FF)
                    )
                ),
                glowColor = baseColor.copy(alpha = 0.15f),
                textPrimary = Color(0xFF101B2B),
                textSecondary = Color(0xFF4B5E78),
                isDark = false
            )
        }
    }
}
