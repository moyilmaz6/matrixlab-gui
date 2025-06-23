package matrixlab.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import java.awt.SystemColor.window

@Composable
fun HelpWindow(onClose: () -> Unit) {
    Window(onCloseRequest = onClose, title = "Help Window") {
        Text("This is the help window!")
    }
}