package matrixlab.engine;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files.*;
import static matrixlab.engine.Brain.think;

public class FileHandler {

    private static Path activeFile = null;
    private static final Path saveDirectory = Paths.get("savedFiles");
    public static String getActiveFile() {
        return activeFile.getFileName().toString();
    }
    // file operations start
    public static void createFile(String name) throws IOException {
        Path path = saveDirectory.resolve(name);
    }
    public static void removeFile(Path path) throws IOException {
        Files.deleteIfExists(path);
    }
    public static void saveFile(Path path, String text) throws IOException {
        Files.writeString(path, text);
    }
    public static String loadFile(Path path) throws IOException {
        activeFile = path;
        return Files.readString(path);
    }
    public static void renameFile(Path oldPath, String newName) throws IOException {
        Path newPath = saveDirectory.resolve(newName);
        Files.move(oldPath, newPath);
    }




}
