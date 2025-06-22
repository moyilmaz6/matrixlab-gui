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
import androidx.compose.foundation.lazy.items
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
import matrixlab.engine.Brain

// Consistent dark theme colors
private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun RightPanel(observer: Observer) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Button(onClick = {Brain.clearTable()
                    observer.refreshObjects()}) { Text("Clear Table") }
                LazyColumn (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxSize()
                ) { items(observer.objectList.filter { it.isNotBlank() })  { obj ->
                    Box (
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, StrongBorder, RoundedCornerShape(4.dp))
                            .padding(top = 4.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ){
                            Spacer(Modifier.weight(.1f))
                            Text(obj, color = MaterialTheme.colors.onSurface, modifier = Modifier.weight(.5f))
                            Spacer(Modifier.weight(.1f))
                            Button(onClick = {
                                Brain.removeObj(obj)
                                observer.refreshObjects()
                            }, Modifier.weight(.3f)) { Text("X") }
                            Spacer(Modifier.weight(.1f))
                        }
                    }
                }
                }
            }
        }
    }
}
