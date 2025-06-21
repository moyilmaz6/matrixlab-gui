package matrixlab.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import matrixlab.engine.FileHandler.activeFileName

class Observer {

    val userFiles: SnapshotStateList<String> = mutableStateListOf() // savedFilesList
    val objectList: SnapshotStateList<String> = mutableStateListOf() // objList
    var activeFile by mutableStateOf("") // activeFileName

    fun refreshFiles(text: String) {
        userFiles.clear()
        userFiles.addAll(text.lines())
    }
    fun refreshObjects(text: String) {
        objectList.clear()
        objectList.addAll(text.lines())
    }
    fun refreshActiveFile() {
        activeFile = activeFileName
    }


}