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
    DASHA("Dasha"),
    DEFAULT("Default")
}
val LightThemeColors = lightColors(
    primary = Color(0xFF9C27B0),            // Lavender/Purple (similar to your buttons)
    primaryVariant = Color(0xFF7B1FA2),     // Slightly darker purple
    onPrimary = Color.White,                // Text/icon on purple

    background = Color(0xFFF5F5F5),         // Very light gray (softer than pure white)
    onBackground = Color.Black,             // Black text/icons on light background

    surface = Color(0xFFFFFFFF),            // Widget surfaces
    onSurface = Color.Black                 // Text/icons on widgets
)


val DashaThemeColors = lightColors(
    primary = Color(0xFFCDDC39),            // Lime 500 – vibrant accent
    primaryVariant = Color(0xFFAEEA00),     // Lime A700 – punchy highlight
    onPrimary = Color.Black,                // Black text on lime buttons

    background = Color(0xFFDFFFD2),         // 🍈 Soft lime peel (main background)
    onBackground = Color(0xFF1A1A1A),       // Almost black – crisp on green

    surface = Color(0xFFCCF5B1),            // 🍋 Lime pulp – surface panels/cards
    onSurface = Color(0xFF202020)           // Dark gray for legible content
)

val DarkThemeColors = lightColors(
    primary = AccentColor,
    primaryVariant = AccentColor,
    onPrimary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)

val DefaultThemeColors = darkColors(
    primary = AccentColor,
    primaryVariant = AccentColor,
    onPrimary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)