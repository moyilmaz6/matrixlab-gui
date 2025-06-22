package matrixlab.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import matrixlab.engine.Brain
import matrixlab.engine.FileHandler
import matrixlab.engine.FileHandler.activeFileName

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


}