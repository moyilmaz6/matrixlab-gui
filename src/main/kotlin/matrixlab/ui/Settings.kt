package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import matrixlab.engine.Brain
import java.awt.Window

private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun SettingsWindow(onClose: () -> Unit, observer: Observer, parentWindow: Window) {
    val appIcon = painterResource("icons/icon.png")
    val windowState = rememberWindowState(width = 250.dp, height = 350.dp)
    Window(onCloseRequest = onClose, title = "Settings Window", icon = appIcon, alwaysOnTop = true,
        state = windowState) {
        val window = this.window
        LaunchedEffect(Unit) {
            val parentBounds = parentWindow.bounds
            val x = parentBounds.x + (parentBounds.width - window.width) / 2
            val y = parentBounds.y + (parentBounds.height - window.height) / 2
            window.setLocation(x, y)
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Box (modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = TopCenter) {
                Column (
                ) {
                    Button(onClick = {
                        observer.toggleAppTheme()
                    }) { Text("Theme: ${observer.whatAppTheme()}") }
                    Button(onClick = {
                        observer.toggleDebugMode()
                    }) { Text("Debug Mode: ${observer.whatDebugMode()}") } // Currently resets if the settings window is reopened
                    Button(onClick = {
                        observer.toggleLogMode()
                    }) { Text("Logging: ${observer.whatLogMode()}")}
                    Button(onClick = {
                        observer.toggleAutoSave()
                    }) { Text("Autosave: ${observer.whatAutoSave()}")}
                }
            }
        }
    }
}
