package com.i2softwares.force5g.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Aboutscreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val appVersion = "1.0.0" // Replace with your actual version
    val scrollState = rememberScrollState()
 val primary=MaterialTheme.colorScheme.primary.copy(alpha = 0.03f)
    val secondry=MaterialTheme.colorScheme.secondary.copy(alpha = 0.03f)
    // Animation states
    val infiniteTransition = rememberInfiniteTransition()
    val floatingAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {
        // Decorative circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = primary,
                radius = size.width * 0.8f,
                center = Offset(size.width * 0.2f, size.height * 0.1f)
            )
            drawCircle(
                color =secondry,
                radius = size.width * 0.6f,
                center = Offset(size.width * 0.8f, size.height * 0.8f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
        ) {
            // Animated Header with App Icon


            // About Description Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "About",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = "A 5G-only mode app would allow users to restrict their device connectivity exclusively to 5G networks, automatically disabling fallback to slower 4G/LTE or 3G networks when 5G signal weakens.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Perfect for 4K streaming, cloud gaming, and large file downloads while potentially improving battery life by avoiding constant network switching.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 24.sp
                    )
                }
            }

            // Features Section
            Text(
                text = "FEATURES",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            // Feature Chips
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "🚀 5G Only Mode",
                    "⚡ No Network Switching",
                    "🔋 Battery Saver",
                    "🎮 Gaming Optimized",
                    "📺 4K Streaming",
                ).forEach { feature ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { /* Optional: Show tooltip */ }
                    ) {
                        Text(
                            text = feature,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Quick Actions Section

            // Settings Items (Full width)
            Text(
                text = "QUICK OPTIONS",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )

            val settingsItems = listOf(
                SettingsItem(
                    label = "Privacy Policy",
                    icon = Icons.Outlined.Lock,
                    action = {
                        val url = "https://privacypolicyforappstore.vercel.app/"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)), null)
                    }
                ),
                SettingsItem(
                    label = "Send Feedback",
                    icon = Icons.Outlined.Email,
                    action = {
                        val email = "i234678i234@gmail.com"
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                        }
                        context.startActivity(intent, null)
                    }
                ),
                SettingsItem(
                    label = "Share App",
                    icon = Icons.Outlined.Share,
                    action = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Check out this awesome Permanent 5g app: " +
                                        "https://play.google.com/store/apps/details?id=${context.packageName}"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share App"), null)
                    }
                ),
                SettingsItem(
                    label = "Rate Us",
                    icon = Icons.Outlined.Star,
                    action = {
                        val url = "https://play.google.com/store/apps/details?id=${context.packageName}"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)), null)
                    }
                ),
                SettingsItem(
                    label = "Contact Support",
                    icon = Icons.Outlined.Call,
                    action = {
                        val email = "i234678i234@gmail.com"
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                            putExtra(Intent.EXTRA_SUBJECT, "Support Request - 5G Pro App")
                        }
                        context.startActivity(intent, null)
                    }
                )
            )

            settingsItems.forEach { item ->
                SettingsItemCard(
                    item = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer with gradient
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                                Color.Transparent
                            )
                        )
                    )
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Version with badge
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Version $appVersion",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = buildAnnotatedString {
                        append("Made with ")
                        pushStyle(SpanStyle(color = Color.Red))
                        append("❤️")
                        pop()
                        append(" for 5G users")
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "© 2024 5G Pro. All rights reserved.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun QuickActionCard(
    item: QuickActionItem,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release -> isPressed = false
                is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    Surface(
        modifier = modifier
            .aspectRatio(1f)
            .scale(if (isPressed) 0.95f else 1f),
        shape = RoundedCornerShape(16.dp),
        color = item.color.copy(alpha = 0.1f),
        border = BorderStroke(
            width = 1.dp,
            color = item.color.copy(alpha = 0.3f)
        ),
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    item.action()
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = item.color,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = item.color,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun SettingsItemCard(
    item: SettingsItem,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release -> isPressed = false
                is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .scale(if (isPressed) 0.98f else 1f),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        ),
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    item.action()
                }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon with gradient background
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = item.label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

data class SettingsItem(
    val label: String,
    val icon: ImageVector,
    val action: () -> Unit
)

data class QuickActionItem(
    val icon: ImageVector,
    val label: String,
    val color: Color,
    val action: () -> Unit
)

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val horizontalSpacing = horizontalArrangement.spacing
        val verticalSpacing = verticalArrangement.spacing

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))
        }

        val rows = mutableListOf<MutableList<Placeable>>()
        var currentRow = mutableListOf<Placeable>()
        var currentRowWidth = 0

        placeables.forEach { placeable ->
            val itemWidth = placeable.width
            if (currentRowWidth + itemWidth + (if (currentRow.isNotEmpty()) horizontalSpacing.roundToPx() else 0) <= constraints.maxWidth) {
                // Add to current row
                if (currentRow.isNotEmpty()) {
                    currentRowWidth += horizontalSpacing.roundToPx()
                }
                currentRow.add(placeable)
                currentRowWidth += itemWidth
            } else {
                // Start new row
                if (currentRow.isNotEmpty()) {
                    rows.add(currentRow)
                }
                currentRow = mutableListOf(placeable)
                currentRowWidth = itemWidth
            }
        }

        if (currentRow.isNotEmpty()) {
            rows.add(currentRow)
        }

        // Calculate total height
        val totalHeight = rows.sumOf { row ->
            row.maxOf { it.height } + verticalSpacing.roundToPx()
        } - verticalSpacing.roundToPx()

        layout(constraints.maxWidth, totalHeight.coerceAtLeast(0)) {
            var y = 0
            rows.forEach { row ->
                val rowHeight = row.maxOf { it.height }
                var x = 0

                // Apply horizontal arrangement
                when (horizontalArrangement) {
                    Arrangement.Center -> {
                        val totalWidth = row.sumOf { it.width } + (horizontalSpacing.roundToPx() * (row.size - 1))
                        x = (constraints.maxWidth - totalWidth) / 2
                    }
                    Arrangement.End -> {
                        val totalWidth = row.sumOf { it.width } + (horizontalSpacing.roundToPx() * (row.size - 1))
                        x = constraints.maxWidth - totalWidth
                    }
                    Arrangement.SpaceEvenly -> {
                        val totalWidth = row.sumOf { it.width }
                        val spacing = (constraints.maxWidth - totalWidth) / (row.size + 1)
                        x = spacing
                        row.forEachIndexed { index, placeable ->
                            placeable.placeRelative(x, y)
                            x += placeable.width + spacing
                        }
                        y += rowHeight + verticalSpacing.roundToPx()
                        return@forEach
                    }
                    else -> {
                        // Start (default)
                    }
                }

                row.forEachIndexed { index, placeable ->
                    placeable.placeRelative(x, y)
                    x += placeable.width + horizontalSpacing.roundToPx()
                }

                y += rowHeight + verticalSpacing.roundToPx()
            }
        }
    }
}

