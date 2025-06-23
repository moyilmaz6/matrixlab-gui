package matrixlab.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window

@Composable
fun SettingsWindow(onClose: () -> Unit) {
    Window(onCloseRequest = onClose, title = "Settings Window") {
        Text("This is the settings window!")
    }
}