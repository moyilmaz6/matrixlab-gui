package matrixlab.ui

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val DarkBackground = Color(0xFF1E1E1E)
private val DarkSurface = Color(0xFF2D2D2D)
private val AccentColor = Color(0xFFBB86FC)

enum class AppTheme(val displayName: String) {
    LIGHT("Light"),
    DARK("Dark"),
    DASHA("Dasha")
}

val LightThemeColors = lightColors(
    primary = AccentColor,
    primaryVariant = AccentColor,
    onPrimary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)

val DarkThemeColors = darkColors(
    primary = AccentColor,
    primaryVariant = AccentColor,
    onPrimary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)

val DashaThemeColors = lightColors(
    primary = AccentColor,
    primaryVariant = AccentColor,
    onPrimary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)