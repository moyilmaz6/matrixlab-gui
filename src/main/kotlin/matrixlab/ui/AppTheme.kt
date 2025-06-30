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
    primary = Color(0xFF9C27B0),
    primaryVariant = Color(0xFF7B1FA2),
    onPrimary = Color.White,

    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,

    surface = Color(0xFFFFFFFF),
    onSurface = Color.Black
)


val DashaThemeColors = lightColors(
    primary = Color(0xFFCDDC39),
    primaryVariant = Color(0xFFAEEA00),
    onPrimary = Color.Black,

    background = Color(0xFFDFFFD2),
    onBackground = Color(0xFF1A1A1A),

    surface = Color(0xFFCCF5B1),
    onSurface = Color(0xFF202020)
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
    // Default
//    primary = AccentColor,
//    primaryVariant = AccentColor,
//    onPrimary = Color.White,
//    background = DarkBackground,
//    surface = DarkSurface,
//    onSurface = Color.White,
//    onBackground = Color.White
    // Monochrome
    primary = Color(0xFF424242),        // Gray
    primaryVariant = Color(0xFF212121),
    onPrimary = Color.White,

    background = Color(0xFFF5F5F5),     // Light gray
    onBackground = Color(0xFF212121),

    surface = Color(0xFFE0E0E0),
    onSurface = Color.Black

)