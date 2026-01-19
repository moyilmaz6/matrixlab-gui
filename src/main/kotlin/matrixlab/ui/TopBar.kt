package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import matrixlab.engine.Brain
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.activeFileName
import matrixlab.engine.FileHandler.getSaveFilePath
import java.awt.Window

private val StrongBorder = Color(0xFFAAAAAA)

@Composable
fun saveWindow(onClose: () -> Unit, observer: Observer, parentWindow: Window) {
    val appIcon = painterResource("icons/icon.png")
    val windowState = rememberWindowState(width = 320.dp, height = 155.dp)
    Window(
        onCloseRequest = onClose, icon = appIcon, state = windowState,
        title = "Save Window", alwaysOnTop = true
    ) {
        val window = this.window
        // Center the window once it's ready
        LaunchedEffect(Unit) {
            val parentBounds = parentWindow.bounds
            val x = parentBounds.x + (parentBounds.width - window.width) / 2
            val y = parentBounds.y + (parentBounds.height - window.height) / 2
            window.setLocation(x, y)
        }
        var newName by remember { mutableStateOf("") }
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = TopCenter) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Spacer(Modifier.weight(0.05f))
                    TextField(
                        value = newName,
                        onValueChange = { newName = it },
                        modifier = Modifier
                            .weight(0.6f)
                            .width(240.dp),

                        //label = { Text("Type here...") },
                        singleLine = true,
                        shape = RoundedCornerShape(6.dp),
                        textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center)
                    )
                    Spacer(Modifier.weight(0.05f))
                    Row(
                        Modifier.weight(0.4f)
                    ) {
                        Spacer(Modifier.weight(0.1f))

                        Button(onClick = {
                            onClose()
                        }, Modifier.weight(0.2f)) { Text("Cancel", fontSize = 10.sp) } // Close the window
                        Spacer(Modifier.weight(0.2f))
                        Button(onClick = {
                            FileHandler.renameFile(getSaveFilePath(observer.activeFile), "$newName")
                            FileHandler.setActiveFile(getSaveFilePath("$newName"))
                            observer.refreshActiveFile()
                            observer.refreshFiles()
                            observer.refreshObjects()
                            onClose()
                        }, Modifier.weight(0.2f)) { Text("Save", fontSize = 10.sp) } // Change the filename
                        Spacer(Modifier.weight(0.1f))
                    }
                    Spacer(Modifier.weight(0.05f))
                }
            }
        }
    }
}

@Composable
fun TopBar(observer: Observer, parentWindow: Window) {
    var openHelpWindow by remember { mutableStateOf(false) }
    var openSettingsWindow by remember { mutableStateOf(false) }
    var openSaveWindow by remember { mutableStateOf(false) }

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
            Spacer(Modifier.width(8.dp))
            if (activeFileName.isNotBlank()) {
                Button(
                    onClick = { openSaveWindow = true },
                    modifier = Modifier
                        .width(80.dp)
                        .height(32.dp)
                ) { Text("Rename", fontSize = 10.sp) }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                FileHandler.saveFile(
                    getSaveFilePath(activeFileName),
                    Brain.getTable()
                )
                observer.refreshActiveFile()
                observer.refreshFiles()
            })
            { Text("Save") }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { openHelpWindow = true }) { Text("Help") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { openSettingsWindow = true }) { Text("Settings") }
        }

    }
    if (openHelpWindow) HelpWindow(onClose = { openHelpWindow = false }, parentWindow = parentWindow)
    if (openSettingsWindow) SettingsWindow(
        onClose = { openSettingsWindow = false },
        observer = observer,
        parentWindow = parentWindow
    )
    if (openSaveWindow) saveWindow(
        onClose = { openSaveWindow = false },
        observer = observer,
        parentWindow = parentWindow
    )
}