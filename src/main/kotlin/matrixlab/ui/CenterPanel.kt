package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import matrixlab.engine.Brain
import matrixlab.engine.FileHandler

// Consistent dark theme colors
private val DarkBackground = Color(0xFF1E1E1E)
private val StrongBorder = Color(0xFFAAAAAA)
private val AccentColor = Color(0xFFBB86FC)

@Composable
fun CenterPanel(observer: Observer) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        val messages = remember { mutableStateListOf<String>() }
        val scrollState = rememberScrollState()
        var input by remember { mutableStateOf("") }

        LaunchedEffect(messages.size) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Message list
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                messages.forEach { line ->
                    Text(line, color = MaterialTheme.colors.onSurface)
                }
            }

            // Input bar
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onPreviewKeyEvent {
                        if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                            if (input.isNotBlank()) {
                                if (input == "clear") {
                                    messages.clear()
                                    input = ""
                                }else if (input == "debug") {
                                    observer.toggleDebugMode()
                                    messages += "> $input"
                                    messages += "Debug mode: ${observer.whatDebugMode()}"
                                    if (observer.debugMode) messages += "/commands for available commands"
                                    input = ""
                                }
                                else {
                                    if (!observer.debugMode && input.startsWith("/")) {
                                        messages += "> $input"
                                        messages += "Debug console is not activated"
                                        input = ""
                                    }
                                    else {
                                        val output = Brain.think(input.trim())
                                        messages += "> $input"
                                        messages += output
                                        input = ""
                                        observer.refreshActiveFile()
                                        observer.refreshObjects()
                                        observer.refreshFiles()
                                    }
                                }
                            }
                            true
                        } else false
                    },
                label = { Text("Type here...") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = DarkBackground,
                    textColor = Color.White,
                    cursorColor = AccentColor,
                    focusedIndicatorColor = AccentColor,
                    unfocusedIndicatorColor = StrongBorder,
                    focusedLabelColor = AccentColor,
                    unfocusedLabelColor = Color.Gray
                )
            )
        }
    }
}
