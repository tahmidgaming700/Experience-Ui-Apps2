package com.example.ui.screens

import android.os.SystemClock
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.experience.design.components.ExperienceChip
import com.experience.design.components.ExperienceFilledButton
import com.experience.design.components.ExperienceGlassCard
import com.experience.design.components.ExperienceIconButton
import com.experience.design.components.ExperienceMetricCard
import com.experience.design.components.ExperienceOutlinedButton
import com.experience.design.components.ExperienceSearchBar
import com.experience.design.components.ExperienceStatusBadge
import com.experience.design.components.ExperienceTonalButton
import com.experience.design.theme.ExperienceShapes
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

enum class DemoAppTab(val title: String, val icon: ImageVector) {
    CALCULATOR("Calculator", Icons.Default.Calculate),
    NOTES("Notes", Icons.Default.Notes),
    CLOCK("Clock & Timer", Icons.Default.Schedule),
    WEATHER("Weather", Icons.Default.WbSunny),
    WALLPAPER("Wallpaper", Icons.Default.Wallpaper)
}

@Composable
fun InteractiveDemosScreen(
    initialTab: String? = null,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember {
        mutableStateOf(
            when (initialTab) {
                "calculator" -> DemoAppTab.CALCULATOR
                "notes" -> DemoAppTab.NOTES
                "clock" -> DemoAppTab.CLOCK
                "weather" -> DemoAppTab.WEATHER
                "wallpaper" -> DemoAppTab.WALLPAPER
                else -> DemoAppTab.CALCULATOR
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // App Switcher Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(DemoAppTab.values()) { tab ->
                ExperienceChip(
                    label = tab.title,
                    selected = selectedTab == tab,
                    leadingIcon = tab.icon,
                    onClick = { selectedTab = tab }
                )
            }
        }

        // Selected Live App Container
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                DemoAppTab.CALCULATOR -> CalculatorDemo()
                DemoAppTab.NOTES -> NotesDemo()
                DemoAppTab.CLOCK -> ClockStopwatchDemo()
                DemoAppTab.WEATHER -> WeatherDemo()
                DemoAppTab.WALLPAPER -> WallpaperDemo()
            }
        }
    }
}

// -------------------------------------------------------------
// 1. CALCULATOR DEMO
// -------------------------------------------------------------
@Composable
private fun CalculatorDemo() {
    var expression by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("0") }
    var isScientific by remember { mutableStateOf(false) }
    val history = remember { mutableStateListOf<Pair<String, String>>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Display Screen
        ExperienceGlassCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isScientific) "Scientific Mode" else "Standard Mode",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                ExperienceChip(
                    label = if (isScientific) "Std" else "Sci",
                    selected = isScientific,
                    onClick = { isScientific = !isScientific }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (expression.isEmpty()) "0" else expression,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = resultText,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        // Keypad Grid
        ExperienceGlassCard {
            if (isScientific) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CalcKey("sin", Modifier.weight(1f)) { expression += "sin(" }
                    CalcKey("cos", Modifier.weight(1f)) { expression += "cos(" }
                    CalcKey("tan", Modifier.weight(1f)) { expression += "tan(" }
                    CalcKey("√", Modifier.weight(1f)) { expression += "sqrt(" }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CalcKey("(", Modifier.weight(1f)) { expression += "(" }
                    CalcKey(")", Modifier.weight(1f)) { expression += ")" }
                    CalcKey("^", Modifier.weight(1f)) { expression += "^" }
                    CalcKey("%", Modifier.weight(1f)) { expression += "%" }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcKey("C", Modifier.weight(1f), isAction = true) {
                    expression = ""
                    resultText = "0"
                }
                CalcKey("⌫", Modifier.weight(1f), isAction = true) {
                    if (expression.isNotEmpty()) expression = expression.dropLast(1)
                }
                CalcKey("÷", Modifier.weight(1f), isAction = true) { expression += " ÷ " }
                CalcKey("×", Modifier.weight(1f), isAction = true) { expression += " × " }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcKey("7", Modifier.weight(1f)) { expression += "7" }
                CalcKey("8", Modifier.weight(1f)) { expression += "8" }
                CalcKey("9", Modifier.weight(1f)) { expression += "9" }
                CalcKey("-", Modifier.weight(1f), isAction = true) { expression += " - " }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcKey("4", Modifier.weight(1f)) { expression += "4" }
                CalcKey("5", Modifier.weight(1f)) { expression += "5" }
                CalcKey("6", Modifier.weight(1f)) { expression += "6" }
                CalcKey("+", Modifier.weight(1f), isAction = true) { expression += " + " }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcKey("1", Modifier.weight(1f)) { expression += "1" }
                CalcKey("2", Modifier.weight(1f)) { expression += "2" }
                CalcKey("3", Modifier.weight(1f)) { expression += "3" }
                CalcKey("=", Modifier.weight(1f), isPrimary = true) {
                    val evaluated = evaluateExpression(expression)
                    resultText = evaluated
                    if (expression.isNotBlank() && evaluated != "Error") {
                        history.add(0, expression to evaluated)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcKey("0", Modifier.weight(2f)) { expression += "0" }
                CalcKey(".", Modifier.weight(1f)) { expression += "." }
                CalcKey("00", Modifier.weight(1f)) { expression += "00" }
            }
        }

        // Calculation History
        if (history.isNotEmpty()) {
            ExperienceGlassCard {
                Text(
                    text = "Calculation History",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                history.take(4).forEach { (exp, res) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expression = exp
                                resultText = res
                            }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = exp,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "= $res",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalcKey(
    label: String,
    modifier: Modifier = Modifier,
    isAction: Boolean = false,
    isPrimary: Boolean = false,
    onClick: () -> Unit
) {
    val bgColor = when {
        isPrimary -> MaterialTheme.colorScheme.primary
        isAction -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    }
    val textColor = when {
        isPrimary -> MaterialTheme.colorScheme.onPrimary
        isAction -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

private fun evaluateExpression(expr: String): String {
    return try {
        val sanitized = expr.replace("×", "*").replace("÷", "/")
        val tokens = sanitized.split(" ").filter { it.isNotBlank() }
        if (tokens.size == 3) {
            val left = tokens[0].toDouble()
            val op = tokens[1]
            val right = tokens[2].toDouble()
            val result = when (op) {
                "+" -> left + right
                "-" -> left - right
                "*" -> left * right
                "/" -> if (right != 0.0) left / right else return "Cannot div by 0"
                "%" -> left % right
                "^" -> Math.pow(left, right)
                else -> return "Error"
            }
            if (result % 1.0 == 0.0) result.toLong().toString() else "%.4f".format(result).trimEnd('0').trimEnd('.')
        } else if (sanitized.startsWith("sqrt(") && sanitized.endsWith(")")) {
            val v = sanitized.removePrefix("sqrt(").removeSuffix(")").toDouble()
            "%.4f".format(sqrt(v)).trimEnd('0').trimEnd('.')
        } else if (sanitized.startsWith("sin(") && sanitized.endsWith(")")) {
            val v = sanitized.removePrefix("sin(").removeSuffix(")").toDouble()
            "%.4f".format(sin(Math.toRadians(v))).trimEnd('0').trimEnd('.')
        } else if (sanitized.startsWith("cos(") && sanitized.endsWith(")")) {
            val v = sanitized.removePrefix("cos(").removeSuffix(")").toDouble()
            "%.4f".format(cos(Math.toRadians(v))).trimEnd('0').trimEnd('.')
        } else if (sanitized.startsWith("tan(") && sanitized.endsWith(")")) {
            val v = sanitized.removePrefix("tan(").removeSuffix(")").toDouble()
            "%.4f".format(tan(Math.toRadians(v))).trimEnd('0').trimEnd('.')
        } else {
            val v = sanitized.toDoubleOrNull()
            if (v != null) v.toString() else "Enter full expression"
        }
    } catch (_: Exception) {
        "Error"
    }
}

// -------------------------------------------------------------
// 2. NOTES DEMO
// -------------------------------------------------------------
data class ExperienceNote(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val isPinned: Boolean,
    val date: String
)

@Composable
private fun NotesDemo() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf("All") }
    var isCreatingNote by remember { mutableStateOf(false) }

    var newTitle by remember { mutableStateOf("") }
    var newContent by remember { mutableStateOf("") }
    var newCategory by remember { mutableStateOf("Work") }

    val notesList = remember {
        mutableStateListOf(
            ExperienceNote(
                id = 1,
                title = "Huawei Y9 2019 Deployment",
                content = "Verify baseline compatibility with EMUI 9.1 / Android 9 (API 28). ARM64 architecture testing passes.",
                category = "System",
                isPinned = true,
                date = "Nov 6, 2026"
            ),
            ExperienceNote(
                id = 2,
                title = "Material 3 Expressive Tokens",
                content = "Using 28dp card radii, sapphire ocean gradient tokens, and spring press feedback across all 15 APKs.",
                category = "Design",
                isPinned = true,
                date = "Nov 5, 2026"
            ),
            ExperienceNote(
                id = 3,
                title = "GSI & Boot Animation Storyboard",
                content = "Eight-stage storyboard: abstract mark phases, Harmony wordmark, Experience UI wordmark, powered by Android.",
                category = "Ideas",
                isPinned = false,
                date = "Nov 4, 2026"
            )
        )
    }

    val tags = listOf("All", "Work", "Design", "System", "Ideas")

    val filteredNotes = notesList.filter { note ->
        val matchesTag = selectedTag == "All" || note.category.equals(selectedTag, ignoreCase = true)
        val matchesSearch = searchQuery.isBlank() ||
                note.title.contains(searchQuery, ignoreCase = true) ||
                note.content.contains(searchQuery, ignoreCase = true)
        matchesTag && matchesSearch
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Search & Add
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ExperienceSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    placeholder = "Search notes..."
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            ExperienceIconButton(
                icon = Icons.Default.Add,
                contentDescription = "New note",
                onClick = { isCreatingNote = !isCreatingNote }
            )
        }

        // Tag Filters
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tags) { tag ->
                ExperienceChip(
                    label = tag,
                    selected = selectedTag == tag,
                    onClick = { selectedTag = tag }
                )
            }
        }

        // Note Creation Sheet
        AnimatedVisibility(visible = isCreatingNote) {
            ExperienceGlassCard {
                Text(
                    text = "New Experience Note",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = newTitle,
                    onValueChange = { newTitle = it },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newContent,
                    onValueChange = { newContent = it },
                    label = { Text("Note content") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listOf("Work", "Design", "System", "Ideas").forEach { cat ->
                        ExperienceChip(
                            label = cat,
                            selected = newCategory == cat,
                            onClick = { newCategory = cat }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ExperienceOutlinedButton(onClick = { isCreatingNote = false }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    ExperienceFilledButton(
                        onClick = {
                            if (newTitle.isNotBlank()) {
                                notesList.add(
                                    0,
                                    ExperienceNote(
                                        id = System.currentTimeMillis(),
                                        title = newTitle,
                                        content = newContent,
                                        category = newCategory,
                                        isPinned = false,
                                        date = "Today"
                                    )
                                )
                                newTitle = ""
                                newContent = ""
                                isCreatingNote = false
                            }
                        }
                    ) {
                        Text("Save Note")
                    }
                }
            }
        }

        // Notes List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredNotes, key = { it.id }) { note ->
                ExperienceGlassCard {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (note.isPinned) {
                                Icon(
                                    imageVector = Icons.Default.PushPin,
                                    contentDescription = "Pinned",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = note.category,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = note.content,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = note.date,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                        IconButton(
                            onClick = { notesList.remove(note) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete note",
                                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 3. CLOCK & STOPWATCH DEMO
// -------------------------------------------------------------
@Composable
private fun ClockStopwatchDemo() {
    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var isRunning by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableLongStateOf(0L) }
    var startTime by remember { mutableLongStateOf(0L) }
    val laps = remember { mutableStateListOf<Long>() }

    // Live clock ticker
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = System.currentTimeMillis()
            delay(1000)
        }
    }

    // Stopwatch ticker
    LaunchedEffect(isRunning) {
        if (isRunning) {
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            while (isRunning) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                delay(30)
            }
        }
    }

    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Digital World Clock
        ExperienceGlassCard {
            Text(
                text = "Local System Clock",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = timeFormat.format(Date(currentTime)),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = dateFormat.format(Date(currentTime)),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Millisecond Stopwatch
        ExperienceGlassCard {
            Text(
                text = "Precision Stopwatch",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            val minutes = (elapsedTime / 60000) % 60
            val seconds = (elapsedTime / 1000) % 60
            val millis = (elapsedTime % 1000) / 10

            Text(
                text = "%02d:%02d.%02d".format(minutes, seconds, millis),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ExperienceFilledButton(
                    onClick = { isRunning = !isRunning },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (isRunning) "Pause" else "Start")
                }

                ExperienceTonalButton(
                    onClick = {
                        if (isRunning) {
                            laps.add(0, elapsedTime)
                        } else {
                            elapsedTime = 0L
                            laps.clear()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Default.Flag else Icons.Default.Refresh,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (isRunning) "Lap" else "Reset")
                }
            }

            if (laps.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Laps Recorded",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
                laps.take(5).forEachIndexed { idx, lapTime ->
                    val lapMin = (lapTime / 60000) % 60
                    val lapSec = (lapTime / 1000) % 60
                    val lapMs = (lapTime % 1000) / 10
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Lap ${laps.size - idx}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "%02d:%02d.%02d".format(lapMin, lapSec, lapMs),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 4. WEATHER DEMO
// -------------------------------------------------------------
@Composable
private fun WeatherDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Weather Card
        ExperienceGlassCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Current City",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Partly Cloudy · High 26° / Low 18°",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "24°",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Feels like 25°",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }

        // Hourly Forecast
        ExperienceGlassCard {
            Text(
                text = "Hourly Forecast",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                val hours = listOf(
                    Triple("Now", "24°", Icons.Default.Cloud),
                    Triple("11:00", "25°", Icons.Default.WbSunny),
                    Triple("12:00", "26°", Icons.Default.WbSunny),
                    Triple("13:00", "26°", Icons.Default.WbSunny),
                    Triple("14:00", "25°", Icons.Default.Cloud),
                    Triple("15:00", "23°", Icons.Default.WaterDrop)
                )
                items(hours) { (time, temp, icon) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = time,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = temp,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Metrics Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ExperienceMetricCard(
                title = "Humidity",
                value = "64%",
                subtitle = "Dew point 16°",
                icon = Icons.Default.WaterDrop,
                modifier = Modifier.weight(1f)
            )
            ExperienceMetricCard(
                title = "Wind",
                value = "14 km/h",
                subtitle = "Northwest",
                icon = Icons.Default.Air,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// -------------------------------------------------------------
// 5. WALLPAPER DEMO
// -------------------------------------------------------------
@Composable
private fun WallpaperDemo() {
    val wallpapers = listOf(
        Triple("Cosmic Sapphire", Color(0xFF0F2027), Color(0xFF2C5364)),
        Triple("Electric Violet", Color(0xFF2E0854), Color(0xFF8A2BE2)),
        Triple("Emerald Forest", Color(0xFF0A2E1D), Color(0xFF2ECC71)),
        Triple("Sunset Ember", Color(0xFF4A1204), Color(0xFFFF5722)),
        Triple("Titanium Minimal", Color(0xFF1F2937), Color(0xFF4B5563))
    )
    var selectedWallpaper by remember { mutableStateOf(wallpapers[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Wallpaper Preview Canvas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(ExperienceShapes.cardExpressive)
                .background(
                    Brush.verticalGradient(
                        listOf(selectedWallpaper.second, selectedWallpaper.third)
                    )
                )
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    ExperienceShapes.cardExpressive
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "10:42",
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Friday, November 6",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )
                Spacer(modifier = Modifier.height(20.dp))
                ExperienceStatusBadge(
                    status = selectedWallpaper.first,
                    isActive = true
                )
            }
        }

        ExperienceGlassCard {
            Text(
                text = "Experience UI Wallpapers",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Curated ambient gradients extracted by the palette engine for dynamic color coordination.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(wallpapers) { wp ->
                    val isSelected = selectedWallpaper == wp
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { selectedWallpaper = wp }
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Brush.linearGradient(listOf(wp.second, wp.third)))
                                .border(
                                    if (isSelected) 3.dp else 1.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    RoundedCornerShape(14.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = wp.first.substringBefore(" "),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            ExperienceFilledButton(
                onClick = { /* Simulated wallpaper apply */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Apply Wallpaper to Ecosystem")
            }
        }
    }
}
