package com.experience.design.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.experience.design.theme.ExperienceShapes
import com.experience.design.theme.LocalExperienceGlassTokens

data class ExperienceNavItem(
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
)

/**
 * Expressive Bottom Navigation bar with active pill indicator.
 * Explicitly applies .windowInsetsPadding(WindowInsets.navigationBars) to prevent cutoffs.
 */
@Composable
fun ExperienceBottomNavigation(
    items: List<ExperienceNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tokens = LocalExperienceGlassTokens.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = tokens.surfaceFill,
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex
                val pillColor by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                    else androidx.compose.ui.graphics.Color.Transparent,
                    animationSpec = spring(stiffness = 500f),
                    label = "nav_pill_color"
                )
                val iconTint by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    animationSpec = spring(stiffness = 500f),
                    label = "nav_icon_color"
                )

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onItemSelected(index) }
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(ExperienceShapes.pill)
                            .background(pillColor)
                            .padding(horizontal = 18.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                            contentDescription = item.title,
                            tint = iconTint,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }
    }
}
