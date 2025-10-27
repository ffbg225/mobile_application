package com.example.myapplication.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Custom Light Theme Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,            // Main interactive elements (buttons, FAB)
    secondary = MediumGreen,        // Less prominent elements
    tertiary = DeepBrown,           // Accents
    background = SandyBeige,        // App background
    surface = SandyBeige,           // Surface of cards, top app bar
    onPrimary = Color.White,        // Text on primary color (e.g., text on a green button)
    onSecondary = Color.White,      // Text on secondary color
    onTertiary = Color.White,       // Text on tertiary color
    onBackground = DarkBrown,       // Main text color
    onSurface = DarkBrown           // Text on surfaces like the app bar
)

// Custom Dark Theme Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = MediumGreen,          // Main interactive elements
    secondary = LightGreen,         // Less prominent elements
    tertiary = SandyBeige,          // Accents
    background = DarkBrown,         // App background
    surface = Color(0xFF3A2D1F),    // Surface of cards, top app bar
    onPrimary = Color.White,        // Text on primary color
    onSecondary = DarkBrown,        // Text on secondary color
    onTertiary = DarkBrown,         // Text on tertiary color
    onBackground = SandyBeige,      // Main text color
    onSurface = SandyBeige          // Text on surfaces like the app bar
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
