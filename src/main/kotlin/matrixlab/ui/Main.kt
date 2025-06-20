package matrixlab.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import matrixlab.engine.Brain // 👈 Make sure this is recognized!

// Consistent dark theme colors
private val DarkBackground = Color(0xFF1E1E1E)
private val DarkSurface = Color(0xFF2D2D2D)
private val StrongBorder = Color(0xFFAAAAAA)
private val AccentColor = Color(0xFFBB86FC)

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = darkColors(
            primary = AccentColor,
            primaryVariant = AccentColor,
            onPrimary = Color.White,
            background = DarkBackground,
            surface = DarkSurface,
            onSurface = Color.White,
            onBackground = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {

            // --- Top Toolbar ---
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, StrongBorder, RoundedCornerShape(6.dp)),
                color = MaterialTheme.colors.surface,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { println("Button 1 clicked") }) { Text("Button 1") }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { println("Button 2 clicked") }) { Text("Button 2") }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { println("Button 3 clicked") }) { Text("Button 3") }
                }
            }

            // --- Main Content Area ---
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // --- Left Box ---
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .border(2.dp, StrongBorder, RoundedCornerShape(6.dp)),
                    color = MaterialTheme.colors.surface,
                    elevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Left Box", color = MaterialTheme.colors.onSurface)
                    }
                }

                // --- Middle Box ---
                Surface(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .border(2.dp, StrongBorder, RoundedCornerShape(6.dp)),
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
                                            val output = Brain.think(input.trim())
                                            messages += "> $input"
                                            messages += output
                                            input = ""
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

                // --- Right Box ---
                Surface(
                    modifier = Modifier
                        .weight(1f)
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
        }
    }
}

fun main() = application {
    System.loadLibrary("mathengine") // Load native library if needed
    Window(onCloseRequest = ::exitApplication, title = "MatrixLab-GUI") {
        App()
    }
}
