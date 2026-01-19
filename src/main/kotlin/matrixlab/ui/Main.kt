package matrixlab.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.runBlocking
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.initNewFile
import matrixlab.ui.AppTheme
import matrixlab.ui.LightThemeColors
import matrixlab.ui.DarkThemeColors
import matrixlab.ui.DashaThemeColors
import org.scijava.nativelib.NativeLoader
import java.awt.Window

// Consistent dark theme colors
private val DarkBackground = Color(0xFF1E1E1E)
private val DarkSurface = Color(0xFF2D2D2D)
private val AccentColor = Color(0xFFBB86FC)

@Composable
@Preview
fun App(parentWindow: Window) {
    val observer = remember { Observer() }

    observer.readSettings()
    observer.refreshActiveFile()
    MaterialTheme(
        colors = when (observer.currentTheme) {
            AppTheme.LIGHT -> LightThemeColors
            AppTheme.DARK -> DarkThemeColors
            AppTheme.DASHA -> DashaThemeColors
            AppTheme.DEFAULT -> DefaultThemeColors
            else -> LightThemeColors
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            // Top Toolbar
            TopBar(observer = observer, parentWindow = parentWindow)

            // Main Content Area
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Left Panel
                Box(modifier = Modifier.weight(1f)) {
                    observer.refreshFiles()
                    LeftPanel(observer = observer)
                }

                // Center Panel
                Box(modifier = Modifier.weight(2f)) {
                    CenterPanel(observer = observer)
                }

                // Right Panel
                Box(modifier = Modifier.weight(1f)) {
                    RightPanel(observer = observer)
                }
            }
        }
    }
}

fun main() = application {
    val appIcon = painterResource("icons/icon.png")
    NativeLoader.loadLibrary("mathengine") // Load native library
    FileHandler.syncSavedFiles()
    if (FileHandler.savedFilesList.isEmpty()) {
        initNewFile()
    }
    Window(onCloseRequest =::exitApplication,
        title = "DashinkaLab",
        icon = appIcon,
        state = rememberWindowState(width = 1200.dp, height = 800.dp)) {
        val mainWindow = this.window
        App(mainWindow)
    }
}
