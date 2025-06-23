package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window

private val StrongBorder = Color(0xFFAAAAAA)
@Composable
fun SettingsWindow(onClose: () -> Unit) {
    val appIcon = painterResource("icons/bear96.png")
    Window(onCloseRequest = onClose, title = "Settings Window", icon = appIcon, alwaysOnTop = true) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Box (modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Text("This is the settings window!")
            }
        }
    }
}