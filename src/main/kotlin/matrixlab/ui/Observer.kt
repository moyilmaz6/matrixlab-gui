package matrixlab.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import matrixlab.engine.Brain
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.activeFileName
import java.nio.file.Files
import java.nio.file.Paths

class Observer {
    val userFiles: SnapshotStateList<String> = mutableStateListOf() // savedFilesList
    val objectList: SnapshotStateList<String> = mutableStateListOf() // objList
    var activeFile by mutableStateOf("") // activeFileName
    fun refreshFiles() {
        FileHandler.syncSavedFiles()
        userFiles.clear()
        userFiles.addAll(FileHandler.savedFilesList.lines())
    }
    fun refreshObjects() {
        objectList.clear()
        objectList.addAll(Brain.getObjList().lines())
    }
    fun refreshActiveFile() {
        activeFile = activeFileName
    }

    // Settings
    var debugMode by mutableStateOf(false)
    var logMode by mutableStateOf(false)
    var autoSave by mutableStateOf(false)
    var currentTheme by mutableStateOf(AppTheme.LIGHT)
    fun whatDebugMode() = if (debugMode) "on" else "off"
    fun whatLogMode() = if (logMode) "on" else "off"
    fun whatAutoSave() = if (autoSave) "on" else "off"
    fun whatAppTheme() = currentTheme.name.lowercase()

    fun toggleDebugMode() {
        debugMode = !debugMode
        writeSettings()
    }
    fun toggleLogMode() {
        logMode = !logMode
        writeSettings()
    }
    fun toggleAutoSave() {
        autoSave = !autoSave
        writeSettings()
    }
    fun toggleAppTheme() {
        currentTheme = when (currentTheme) {
            AppTheme.LIGHT -> AppTheme.DARK
            AppTheme.DARK -> AppTheme.DASHA
            AppTheme.DASHA -> AppTheme.DEFAULT
            else -> AppTheme.LIGHT
        }
        writeSettings()
    }
    fun readSettings() {
        try {
            val settingsPath = Paths.get("settings.txt")
            if (Files.exists(settingsPath)) {
                val content = Files.readString(settingsPath)
                if (!content.trim().isEmpty())
                    content.lines().forEach { line ->
                        if (line.isNotEmpty() && line.contains("=")) {
                            val parts = line.split("=", limit = 2)
                            val key = parts[0].trim()
                            val value = parts[1].trim()

                            when (key) {
                                "DEBUG_MODE" -> debugMode = value.equals("on", ignoreCase = true)
                                "LOG_MODE" -> logMode = value.equals("on", ignoreCase = true)
                                "AUTO_SAVE" -> autoSave = value.equals("on", ignoreCase = true)
                                "THEME" -> {
                                    currentTheme = when (value.uppercase()) {
                                        "LIGHT" -> AppTheme.LIGHT
                                        "DARK" -> AppTheme.DARK
                                        "DASHA" -> AppTheme.DASHA
                                        "DEFAULT" -> AppTheme.DEFAULT
                                        else -> AppTheme.LIGHT
                                    }
                                }
                            }
                        }
                }
                else {
                    writeSettings()
                }
            }
        } catch (e: Exception) {
            println("Error reading settings: ${e.message}")
        }
    }

    fun writeSettings() {
        try {
            val settingsPath = Paths.get("settings.txt")
            val content = """
                DEBUG_MODE=${whatDebugMode()}
                LOG_MODE=${whatLogMode()}
                AUTO_SAVE=${whatAutoSave()}
                THEME=${whatAppTheme()}
            """.trimIndent()

            Files.writeString(settingsPath, content)
        } catch (e: Exception) {
            println("Error writing settings: ${e.message}")
        }
    }

    }
