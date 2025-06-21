package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Consistent dark theme colors
private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun RightPanel() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .border(2.dp, StrongBorder, RoundedCornerShape(6.dp)),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("Right Box", color = MaterialTheme.colors.onSurface)
        }
    }
}
