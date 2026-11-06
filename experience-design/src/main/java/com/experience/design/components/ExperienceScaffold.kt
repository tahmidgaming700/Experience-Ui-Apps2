package com.experience.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.experience.design.theme.LocalExperienceGlassTokens

/**
 * Standard ecosystem top app bar for Experience UI.
 */
@Composable
fun ExperienceTopBar(
    title: String,
    subtitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        actions()
    }
}

/**
 * Material 3 Expressive Scaffold wrapper with full edge-to-edge support,
 * safe insets handling, and consistent background gradient atmosphere.
 */
@Composable
fun ExperienceScaffold(
    title: String? = null,
    subtitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    topBarActions: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val tokens = LocalExperienceGlassTokens.current
    val backgroundBrush = if (tokens.isDark) {
        Brush.verticalGradient(
            listOf(
                Color(0xFF09111C),
                Color(0xFF0E1A2B),
                Color(0xFF0A1320)
            )
        )
    } else {
        Brush.verticalGradient(
            listOf(
                Color(0xFFF6FAFF),
                Color(0xFFEDF4FD),
                Color(0xFFF7F4FD)
            )
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                if (title != null) {
                    ExperienceTopBar(
                        title = title,
                        subtitle = subtitle,
                        onBackClick = onBackClick,
                        actions = topBarActions
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    content = content
                )
            }
        }
    }
}
