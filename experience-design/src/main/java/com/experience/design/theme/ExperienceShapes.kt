package com.experience.design.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Material 3 Expressive shapes for Experience UI.
 * Expressive design emphasizes generous radii, organic curves, and tactile squircles.
 */
object ExperienceShapes {
    val extraSmall: CornerBasedShape = RoundedCornerShape(8.dp)
    val small: CornerBasedShape = RoundedCornerShape(14.dp)
    val medium: CornerBasedShape = RoundedCornerShape(20.dp)
    val large: CornerBasedShape = RoundedCornerShape(28.dp)
    val extraLarge: CornerBasedShape = RoundedCornerShape(36.dp)

    // Specialized shapes
    val pill: CornerBasedShape = RoundedCornerShape(percent = 50)
    val cardExpressive: CornerBasedShape = RoundedCornerShape(26.dp)
    val dialogExpressive: CornerBasedShape = RoundedCornerShape(32.dp)
    val sheetExpressive: CornerBasedShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    val chipExpressive: CornerBasedShape = RoundedCornerShape(12.dp)
    val searchBarExpressive: CornerBasedShape = RoundedCornerShape(24.dp)

    val materialShapes = Shapes(
        extraSmall = extraSmall,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge
    )
}
