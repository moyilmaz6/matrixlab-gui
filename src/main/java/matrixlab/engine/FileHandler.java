package matrixlab.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    private static Path activeFile = null;
    private static final Path saveDirectory = Paths.get("savedFiles");
    public static Path getActiveFilePath() {return activeFile;}
    public static String getActiveFileName() {
        return activeFile.getFileName().toString();
    }
    public static void setActiveFilePath(Path path) {activeFile = path;}
    // file operations start
    public static void createFile(String name) throws IOException {
        Path path = saveDirectory.resolve(name);
        Files.createFile(path);
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
    public static Path getFilePath(String fileName) {
        return saveDirectory.resolve(fileName);
    }
    public static String fileNameGenerator() {
        int counter = 1;
        String baseName = "Untitled";

        if (!Files.exists(getFilePath(baseName))) {
            return baseName;
        }

        while (true) {
            Path filePath = saveDirectory.resolve(baseName + " " + counter);
            if (!Files.exists(filePath)) {
                return filePath.getFileName().toString();
            }
        }
    }
    public static void initNewFile() throws IOException {
        String fileName = fileNameGenerator();
        createFile(fileName);
        setActiveFilePath(getFilePath(fileName));
    }
}
