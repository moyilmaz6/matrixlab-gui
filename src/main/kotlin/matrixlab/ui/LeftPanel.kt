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
import matrixlab.engine.Brain.loadTable
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.getFilePath
import matrixlab.engine.FileHandler.syncSavedFiles

// Consistent dark theme colors
private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun LeftPanel(observer: Observer) {
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
                Button(onClick = { println("Button 1 clicked") }) { Text("New File +") }

                LazyColumn { items(observer.userFiles) { fileName ->
                    Box (
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, StrongBorder, RoundedCornerShape(4.dp))
                            .padding(top = 4.dp, bottom = 4.dp)
                            .weight(0.9f),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(fileName, color = MaterialTheme.colors.onSurface)
                            Row {
                                Spacer(Modifier.weight(.1f))
                                Button(onClick = {
                                    loadTable(FileHandler.loadFile(getFilePath(fileName)))
                                    observer.refreshActiveFile()
                                                 }, Modifier.weight(0.3f)) { Text("Load") }
                                Spacer(Modifier.weight(.2f))
                                Button(onClick = {
                                    FileHandler.removeFile(getFilePath(fileName))
                                    observer.refreshActiveFile()
                                    FileHandler.syncSavedFiles()
                                    observer.refreshFiles(FileHandler.savedFilesList)
                                                 }, Modifier.weight(0.3f)) { Text("Delete") }
                                Spacer(Modifier.weight(.1f))
                            }
                        }
                    }
                }
                }
            }
        }
    }
}
