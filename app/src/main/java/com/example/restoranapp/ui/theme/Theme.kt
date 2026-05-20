package com.example.restoranapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Warna Modern Natural
val GreenPrimary = Color(0xFF2D6A4F)
val GreenSecondary = Color(0xFF52B788)
val GreenTertiary = Color(0xFF95D5B2)
val CreamBackground = Color(0xFFF8F4E3)
val CreamSurface = Color(0xFFFFFBF0)
val BrownText = Color(0xFF2D1B00)
val GreenContainer = Color(0xFFD8F3DC)

// Dark mode
val GreenDark = Color(0xFF1B4332)
val GreenDarkSecondary = Color(0xFF40916C)
val DarkSurface = Color(0xFF1A1A2E)
val DarkBackground = Color(0xFF16213E)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    primaryContainer = GreenContainer,
    onPrimaryContainer = GreenPrimary,
    secondary = GreenSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFB7E4C7),
    onSecondaryContainer = GreenDark,
    tertiary = GreenTertiary,
    onTertiary = GreenDark,
    background = CreamBackground,
    onBackground = BrownText,
    surface = CreamSurface,
    onSurface = BrownText,
    surfaceVariant = Color(0xFFEEF4E8),
    onSurfaceVariant = Color(0xFF4A4A4A),
    outline = Color(0xFFB0C4B1)
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenSecondary,
    onPrimary = GreenDark,
    primaryContainer = GreenDark,
    onPrimaryContainer = GreenTertiary,
    secondary = GreenTertiary,
    onSecondary = GreenDark,
    secondaryContainer = Color(0xFF1B4332),
    onSecondaryContainer = GreenTertiary,
    tertiary = Color(0xFFB7E4C7),
    onTertiary = GreenDark,
    background = DarkBackground,
    onBackground = Color(0xFFF0F0F0),
    surface = DarkSurface,
    onSurface = Color(0xFFF0F0F0),
    surfaceVariant = Color(0xFF1E2D2A),
    onSurfaceVariant = Color(0xFFB0C4B1),
    outline = Color(0xFF4A6741)
)

@Composable
fun RestoranAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}