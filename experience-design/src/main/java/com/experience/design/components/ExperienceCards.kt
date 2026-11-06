package com.experience.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.experience.design.theme.ExperienceShapes
import com.experience.design.theme.LocalExperienceGlassTokens

/**
 * Luminous Glassmorphic Card adhering to Experience UI design guidelines.
 * Features translucent gradient surface, radiant hairline border, and spring press feedback.
 */
@Composable
fun ExperienceGlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = ExperienceShapes.cardExpressive,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val tokens = LocalExperienceGlassTokens.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(dampingRatio = 0.75f, stiffness = 400f),
        label = "glass_press_scale"
    )

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .scale(scale)
            .clip(shape)
            .background(tokens.gradientBrush)
            .border(BorderStroke(1.dp, tokens.surfaceBorder), shape)
            .then(clickModifier)
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            content = content
        )
    }
}

/**
 * Standard Elevated Expressive Card for content grouping.
 */
@Composable
fun ExperienceElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = ExperienceShapes.cardExpressive,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick ?: {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            content = content
        )
    }
}

/**
 * Metric Card for system status, statistics, or hardware diagnostics.
 */
@Composable
fun ExperienceMetricCard(
    title: String,
    value: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    accentColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    ExperienceGlassCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            if (icon != null) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = accentColor.copy(alpha = 0.15f),
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = title,
                            tint = accentColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Application card representing one of the ecosystem applications.
 */
@Composable
fun ExperienceAppTile(
    appName: String,
    packageName: String,
    description: String,
    icon: ImageVector,
    statusText: String = "Native APK",
    isInstalled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tokens = LocalExperienceGlassTokens.current
    ExperienceGlassCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = appName,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = appName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (isInstalled) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                else MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = statusText,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isInstalled) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = packageName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
            }
        }
    }
}
