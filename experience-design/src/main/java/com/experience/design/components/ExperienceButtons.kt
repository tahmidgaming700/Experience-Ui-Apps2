package com.experience.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.experience.design.theme.ExperienceShapes

/**
 * Material 3 Expressive Filled Button with tactile spring feedback.
 */
@Composable
fun ExperienceFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 450f),
        label = "btn_press_scale"
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        shape = ExperienceShapes.pill,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * Material 3 Expressive Tonal Button for secondary emphasis.
 */
@Composable
fun ExperienceTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 450f),
        label = "tonal_press_scale"
    )

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        shape = ExperienceShapes.pill,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * Material 3 Expressive Outlined Button with subtle luminous stroke.
 */
@Composable
fun ExperienceOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 450f),
        label = "outlined_press_scale"
    )

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        shape = ExperienceShapes.pill,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * Accessible minimum 48dp touch target icon button.
 */
@Composable
fun ExperienceIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 500f),
        label = "icon_btn_scale"
    )

    IconButton(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .size(48.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor,
            contentColor = tint
        ),
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}
