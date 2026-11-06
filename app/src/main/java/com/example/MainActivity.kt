package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.ui.screens.EcosystemAppsScreen
import com.example.ui.screens.InteractiveDemosScreen
import com.example.ui.screens.StudioScreen
import com.example.ui.screens.SystemArchitectureScreen
import com.experience.design.components.ExperienceBottomNavigation
import com.experience.design.components.ExperienceNavItem
import com.experience.design.components.ExperienceScaffold
import com.experience.design.theme.ExperienceTheme
import com.experience.design.theme.ExperienceThemeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeState = remember { ExperienceThemeState() }
            ExperienceTheme(state = themeState) {
                ExperienceUiMainApp()
            }
        }
    }
}

@Composable
fun ExperienceUiMainApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var demoAppInitialTarget by remember { mutableStateOf<String?>(null) }

    val navItems = listOf(
        ExperienceNavItem("Design Studio", Icons.Default.Palette),
        ExperienceNavItem("15 Apps", Icons.Default.Apps),
        ExperienceNavItem("Live Demos", Icons.Default.PlayCircle),
        ExperienceNavItem("Architecture", Icons.Default.Devices)
    )

    // Handle back button to return to first tab if on secondary tab
    BackHandler(enabled = selectedTab != 0) {
        selectedTab = 0
    }

    val (screenTitle, screenSubtitle) = when (selectedTab) {
        0 -> "Experience Studio" to "Material 3 Expressive Design System"
        1 -> "Ecosystem Directory" to "15 Independent Native Applications"
        2 -> "Live App Demos" to "Functional Experience Previews"
        3 -> "Architecture & Target" to "Huawei Y9 2019 · Android 9 Baseline"
        else -> "Experience UI" to "Material 3 Expressive Ecosystem"
    }

    ExperienceScaffold(
        title = screenTitle,
        subtitle = screenSubtitle,
        bottomBar = {
            ExperienceBottomNavigation(
                items = navItems,
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it },
                modifier = Modifier.testTag("main_bottom_nav")
            )
        }
    ) {
        when (selectedTab) {
            0 -> StudioScreen()
            1 -> EcosystemAppsScreen(
                onNavigateToDemo = { appId ->
                    demoAppInitialTarget = appId
                    selectedTab = 2
                }
            )
            2 -> InteractiveDemosScreen(
                initialTab = demoAppInitialTarget
            )
            3 -> SystemArchitectureScreen()
        }
    }
}
