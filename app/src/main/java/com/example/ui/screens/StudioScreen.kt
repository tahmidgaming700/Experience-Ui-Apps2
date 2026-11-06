package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.experience.design.components.ExperienceChip
import com.experience.design.components.ExperienceFilledButton
import com.experience.design.components.ExperienceGlassCard
import com.experience.design.components.ExperienceIconButton
import com.experience.design.components.ExperienceLoadingIndicator
import com.experience.design.components.ExperienceMetricCard
import com.experience.design.components.ExperienceOutlinedButton
import com.experience.design.components.ExperiencePaletteSelector
import com.experience.design.components.ExperienceSearchBar
import com.experience.design.components.ExperienceSlider
import com.experience.design.components.ExperienceStatusBadge
import com.experience.design.components.ExperienceSwitch
import com.experience.design.components.ExperienceTonalButton

@Composable
fun StudioScreen(
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(true) }
    var sliderValue by remember { mutableFloatStateOf(0.72f) }
    var selectedChipIndex by remember { mutableIntStateOf(0) }
    var buttonClickCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card
        ExperienceGlassCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Experience Design System",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Material 3 Expressive shared styling module with Android 9 (API 28) backwards compatibility.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                ExperienceStatusBadge(status = "Design V1", isActive = true)
            }
        }

        // Interactive Palette & Theme Selector
        ExperiencePaletteSelector()

        // Metrics Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ExperienceMetricCard(
                title = "Corner Radius",
                value = "28 dp",
                subtitle = "Expressive Squircle",
                icon = Icons.Default.Layers,
                modifier = Modifier.weight(1f)
            )
            ExperienceMetricCard(
                title = "Touch Target",
                value = "48 dp+",
                subtitle = "A11y Compliant",
                icon = Icons.Default.TouchApp,
                modifier = Modifier.weight(1f)
            )
        }

        // Search Bar Demo
        ExperienceSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            placeholder = "Test Expressive search component..."
        )

        // Buttons Showcase
        ExperienceGlassCard {
            Text(
                text = "Expressive Buttons & Motion",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Spring press physics, minimum 48dp touch targets, and tactile feedback. Taps: $buttonClickCount",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ExperienceFilledButton(
                    onClick = { buttonClickCount++ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Filled Pill")
                }
                ExperienceTonalButton(
                    onClick = { buttonClickCount++ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tonal Pill")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ExperienceOutlinedButton(
                    onClick = { buttonClickCount++ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Outlined")
                }

                ExperienceIconButton(
                    icon = Icons.Default.Favorite,
                    contentDescription = "Favorite action",
                    onClick = { buttonClickCount++ }
                )

                ExperienceIconButton(
                    icon = Icons.Default.AutoAwesome,
                    contentDescription = "Expressive sparkle",
                    onClick = { buttonClickCount++ }
                )
            }
        }

        // Controls Showcase
        ExperienceGlassCard {
            Text(
                text = "Controls & Feedback",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Tactile Switch with Thumb Icon",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (switchState) "State: Active" else "State: Standby",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                ExperienceSwitch(
                    checked = switchState,
                    onCheckedChange = { switchState = it }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Fluid Slider
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Fluid Slider",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${(sliderValue * 100).toInt()}%",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                ExperienceSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Chips
            Text(
                text = "Expressive Choice Chips",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Expressive", "Tactile", "Fluid", "Adaptive").forEachIndexed { index, label ->
                    ExperienceChip(
                        label = label,
                        selected = selectedChipIndex == index,
                        onClick = { selectedChipIndex = index }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Loading & Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Motion Loader & Badges",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        ExperienceStatusBadge(status = "API 28 OK", isActive = true)
                        ExperienceStatusBadge(status = "ARM64", isActive = true)
                    }
                }
                ExperienceLoadingIndicator()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
