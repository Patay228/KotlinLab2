package com.example.laba2.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = YpBlue,
    onPrimary = YpWhite,
    background = DarkBackground,
    onBackground = YpWhite,
    surface = DarkSurface,
    onSurface = YpWhite,
    surfaceVariant = DarkSearchBg,
    onSurfaceVariant = YpWhite
)

private val LightColorScheme = lightColorScheme(
    primary = YpBlue,
    onPrimary = YpWhite,
    background = YpWhite,
    onBackground = YpBlack,
    surface = YpWhite,
    onSurface = YpBlack,
    surfaceVariant = YpLightGray,
    onSurfaceVariant = YpBlack
)

@Composable
fun PlaylistMakerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
