package com.mahmoudalim.calorytrackingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.mahmoudalim.core_ui.BrightGreen
import com.mahmoudalim.core_ui.DarkGray
import com.mahmoudalim.core_ui.DarkGreen
import com.mahmoudalim.core_ui.Dimensions
import com.mahmoudalim.core_ui.LightGray
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.core_ui.MediumGray
import com.mahmoudalim.core_ui.Orange
import com.mahmoudalim.core_ui.Purple500
import com.mahmoudalim.core_ui.Purple700
import com.mahmoudalim.core_ui.TextWhite

private val DarkColorPalette = darkColors(
    primary = Purple700,
    primaryVariant = Purple500,
    secondary = Orange,
    background = MediumGray,
    onBackground = TextWhite,
    surface = LightGray,
    onSurface = TextWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Purple700,
    primaryVariant = Purple500,
    secondary = Orange,
    background = Color.White,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun CaloryTrackingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}