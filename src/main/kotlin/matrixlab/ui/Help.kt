package matrixlab.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import java.awt.SystemColor.window

private val StrongBorder = Color(0xFFAAAAAA)

var helpMessage =
    """
                    MatrixLab is simply a matrix/vector calculator

                    Type commands in the input field at the bottom of the center panel and press Enter to execute.

                    - `help` - Display basic help information
                    - `print <object>` - Print the value of an object
                    - `remove <object>` - Remove an object from the workspace
                    - `clear` - Clear the message history in the center panel

                    You can create and manipulate matrices using the following syntax:

                    - Create a matrix: m <name> = 1 2 3; 4 5 6; 7 8 9;
                    - Create a vector: v <name> = 1 2 3;
                    - Create a variable: var <name> = 123;

                    ## Debug Mode

                    Enter `/debug` to activate debug mode, which provides additional commands:

                    - `/commands` - List all available debug commands
                    - `/removeFile <fileName>` - Remove a file
                    - `/loadFile <fileName>` - Load a file
                    - `/renameFile <oldName> <newName>` - Rename a file
                    - `/saveFile` - Save the current table
                    - `/createFile <fileName>` - Create a new file
                    - `/list` - List objects in the workspace
                    - `/dump` - Dump the current table
                    - `/clear` - Clear the current table
                    - `/active` - Show the active file
                    - `/exit` - Exit debug mode

                    ## Tips

                    - Do NOT forget the save the file
                    - Use the Settings button to customize the application
                    - The active file name is displayed in the top bar
                    - Command history is preserved in the center panel until you use the `clear` command or quit the app
                    """.trimIndent()

@Composable
fun HelpWindow(onClose: () -> Unit) {
    val appIcon = painterResource("icons/bear96.png")
    Window(onCloseRequest = onClose, title = "Help Window", icon = appIcon, alwaysOnTop = true,
        state = rememberWindowState(width = 600.dp, height = 800.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .border(2.dp, StrongBorder, RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            val scrollState = rememberScrollState()

            Box (modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = helpMessage,
                    modifier = Modifier.verticalScroll(scrollState)
                )
            }
        }
    }
}
