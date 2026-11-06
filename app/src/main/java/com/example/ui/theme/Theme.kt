package com.example.ui.theme

import androidx.compose.runtime.Composable
import com.experience.design.theme.ExperienceTheme
import com.experience.design.theme.ExperienceThemeState

@Composable
fun MyApplicationTheme(
    state: ExperienceThemeState = ExperienceThemeState(),
    content: @Composable () -> Unit
) {
    ExperienceTheme(state = state, content = content)
}
