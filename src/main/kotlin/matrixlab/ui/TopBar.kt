package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import matrixlab.engine.Brain
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.activeFileName
import matrixlab.engine.FileHandler.getFilePath

// Consistent dark theme colors
private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun TopBar(observer: Observer) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Active File: ${observer.activeFile}") // TODO: Implement function
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { FileHandler.saveFile(getFilePath(FileHandler.activeFileName), Brain.getTable())
                                observer.refreshActiveFile()})
            { Text("Save") }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { println("Button 2 clicked") }) { Text("Help") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { println("Button 3 clicked") }) { Text("Settings") }
        }

    }
}
