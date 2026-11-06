package com.experience.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.experience.design.theme.ExperiencePalette
import com.experience.design.theme.LocalExperienceThemeState
import com.experience.design.theme.ThemeMode

/**
 * Interactive Palette & Theming Studio component for Android 9+ compatibility.
 * Allows users to switch manual color themes, toggle dark/light mode,
 * and test dynamic wallpaper-based theming.
 */
@Composable
fun ExperiencePaletteSelector(
    modifier: Modifier = Modifier
) {
    val themeState = LocalExperienceThemeState.current

    ExperienceGlassCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Material 3 Expressive Palettes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Android 9 (API 28) compatible palette engine",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Color Swatches
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(ExperiencePalette.values()) { palette ->
                val isSelected = themeState.palette == palette && !themeState.useDynamicColor
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            themeState.palette = palette
                            themeState.useDynamicColor = false
                        }
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(palette.previewColor, palette.accentColor)
                                )
                            )
                            .border(
                                width = if (isSelected) 3.dp else 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = palette.displayName.substringBefore(" "),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Theme Mode Selector (System, Light, Dark)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val modes = listOf(
                Triple(ThemeMode.SYSTEM, "System", Icons.Default.SettingsBrightness),
                Triple(ThemeMode.LIGHT, "Light", Icons.Default.LightMode),
                Triple(ThemeMode.DARK, "Dark", Icons.Default.DarkMode)
            )

            modes.forEach { (mode, label, icon) ->
                val isSelected = themeState.themeMode == mode
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                        .clickable { themeState.themeMode = mode }
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (themeState.isDynamicColorSupported) {
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                    .clickable { themeState.useDynamicColor = !themeState.useDynamicColor }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Wallpaper Dynamic Color (Android 12+)",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExperienceSwitch(
                    checked = themeState.useDynamicColor,
                    onCheckedChange = { themeState.useDynamicColor = it }
                )
            }
        }
    }
}
