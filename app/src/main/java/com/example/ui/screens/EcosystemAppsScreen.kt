package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.EcosystemAppInfo
import com.example.model.EcosystemRegistry
import com.experience.design.components.ExperienceAppTile
import com.experience.design.components.ExperienceChip
import com.experience.design.components.ExperienceFilledButton
import com.experience.design.components.ExperienceGlassCard
import com.experience.design.components.ExperienceOutlinedButton
import com.experience.design.components.ExperienceSearchBar
import com.experience.design.theme.ExperienceShapes

@Composable
fun EcosystemAppsScreen(
    onNavigateToDemo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedAppForDetails by remember { mutableStateOf<EcosystemAppInfo?>(null) }

    val categories = listOf("All", "System Core", "Communications", "Media", "Productivity", "Utilities", "Customization")

    val filteredApps = remember(searchQuery, selectedCategory) {
        EcosystemRegistry.apps.filter { app ->
            val matchesCategory = selectedCategory == "All" || app.category.equals(selectedCategory, ignoreCase = true)
            val matchesSearch = searchQuery.isBlank() ||
                    app.name.contains(searchQuery, ignoreCase = true) ||
                    app.packageName.contains(searchQuery, ignoreCase = true) ||
                    app.description.contains(searchQuery, ignoreCase = true) ||
                    app.role.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Search bar
        ExperienceSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            placeholder = "Search 15 ecosystem applications..."
        )

        // Category Filter Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                ExperienceChip(
                    label = category,
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
            }
        }

        // Summary Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${filteredApps.size} Applications",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Min Android 9 · API 28",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        // Apps List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredApps, key = { it.packageName }) { app ->
                val isInstalled = remember(app.packageName) {
                    isAppInstalled(context, app.packageName)
                }

                ExperienceAppTile(
                    appName = app.name,
                    packageName = app.packageName,
                    description = app.description,
                    icon = app.icon,
                    statusText = if (isInstalled) "Installed" else "Standalone APK",
                    isInstalled = isInstalled,
                    onClick = { selectedAppForDetails = app }
                )
            }
        }
    }

    // App Detail Dialog
    selectedAppForDetails?.let { app ->
        AlertDialog(
            onDismissRequest = { selectedAppForDetails = null },
            confirmButton = {
                ExperienceFilledButton(
                    onClick = {
                        val launched = launchAppOrFallback(context, app.packageName)
                        if (!launched) {
                            onNavigateToDemo(app.id)
                        }
                        selectedAppForDetails = null
                    }
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Try Experience")
                }
            },
            dismissButton = {
                ExperienceOutlinedButton(
                    onClick = { selectedAppForDetails = null }
                ) {
                    Text("Close")
                }
            },
            shape = ExperienceShapes.dialogExpressive,
            containerColor = MaterialTheme.colorScheme.surface,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = app.icon,
                                contentDescription = app.name,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = app.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = app.packageName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = app.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "System Role / API Integration:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = app.role,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Target Artifact Path:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = app.artifactFile,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = "Key Architecture Features:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    app.keyFeatures.forEach { feature ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = feature,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        )
    }
}

private fun isAppInstalled(context: Context, packageName: String): Boolean {
    return try {
        context.packageManager.getPackageInfo(packageName, 0)
        true
    } catch (_: Exception) {
        false
    }
}

private fun launchAppOrFallback(context: Context, packageName: String): Boolean {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    return if (launchIntent != null) {
        try {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(launchIntent)
            true
        } catch (e: Exception) {
            Toast.makeText(context, "Cannot launch: ${e.message}", Toast.LENGTH_SHORT).show()
            false
        }
    } else {
        false
    }
}
