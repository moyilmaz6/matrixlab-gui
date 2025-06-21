package matrixlab.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

// Consistent dark theme colors
private val DarkBackground = Color(0xFF1E1E1E)
private val DarkSurface = Color(0xFF2D2D2D)
private val AccentColor = Color(0xFFBB86FC)

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = darkColors(
            primary = AccentColor,
            primaryVariant = AccentColor,
            onPrimary = Color.White,
            background = DarkBackground,
            surface = DarkSurface,
            onSurface = Color.White,
            onBackground = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            // Top Toolbar
            TopBar()

            // Main Content Area
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Left Panel
                Box(modifier = Modifier.weight(1f)) {
                    LeftPanel()
                }

                // Center Panel
                Box(modifier = Modifier.weight(2f)) {
                    CenterPanel()
                }

                // Right Panel
                Box(modifier = Modifier.weight(1f)) {
                    RightPanel()
                }
            }
        }
    }
}

fun main() = application {
    System.loadLibrary("mathengine") // Load native library
    Window(onCloseRequest = ::exitApplication, title = "MatrixLab-GUI") {
        App()
    }
}
