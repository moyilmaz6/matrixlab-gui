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

private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun SettingsWindow(onClose: () -> Unit) {
    val appIcon = painterResource("icons/bear96.png")
    val debugMode = remember { mutableStateOf("off") }
    val darkMode = remember { mutableStateOf("off") }
    val logMode = remember { mutableStateOf("off") }
    Window(onCloseRequest = onClose, title = "Settings Window", icon = appIcon, alwaysOnTop = true,
        state = rememberWindowState(width = 250.dp, height = 350.dp)) {
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
                        if (darkMode.value == "on") { darkMode.value = "off" }
                        else { darkMode.value = "on" }
                    }) { Text("Dark Mode: ${darkMode.value}") }
                    Button(onClick = {
                        if (debugMode.value == "on") { debugMode.value = "off" }
                        else { debugMode.value = "on" }
                    }) { Text("Debug Mode: ${debugMode.value}") }
                    Button(onClick = {
                        if (logMode.value == "on") { logMode.value = "off" }
                        else { logMode.value = "on" }
                    }) { Text("Logging: ${logMode.value}")}
                }
            }
        }
    }
}
