package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) { item {
                Box (
                    modifier = Modifier.fillMaxWidth()
                        .border(2.dp, StrongBorder, RoundedCornerShape(6.dp))
                        .padding(top = 8.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ){
                        Spacer(Modifier.weight(.1f))
                        Text("Matrix A", color = MaterialTheme.colors.onSurface, modifier = Modifier.weight(.5f))
                        Spacer(Modifier.weight(.1f))
                        Button(onClick = { println("Button 2 clicked") }, Modifier.weight(.3f)) { Text("X") }
                        Spacer(Modifier.weight(.1f))
                    }
                }
            }
            }
        }
    }
}
