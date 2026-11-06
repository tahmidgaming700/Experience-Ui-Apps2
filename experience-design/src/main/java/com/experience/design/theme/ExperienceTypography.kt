package com.experience.design.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Material 3 Expressive Typography for Experience UI.
 * Features bold expressive display scales, legible body text, and accessible sizing.
 */
object ExperienceTypography {

    val typography = Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 54.sp,
            lineHeight = 62.sp,
            letterSpacing = (-0.5).sp
        ),
        displayMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            lineHeight = 50.sp,
            letterSpacing = (-0.25).sp
        ),
        displaySmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 34.sp,
            lineHeight = 42.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            lineHeight = 38.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 26.sp,
            lineHeight = 34.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 30.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 26.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        titleSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 22.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp
        )
    )
}
