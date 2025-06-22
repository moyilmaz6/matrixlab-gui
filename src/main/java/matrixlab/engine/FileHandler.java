package matrixlab.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileHandler {

    private static Path activeFilePath = null;
    public static String activeFileName = "";
    public static String savedFilesList = null;
    private static final Path saveDirectory = Paths.get("savedFiles");
    // getters and setters
    public static Path getActiveFilePath() {return activeFilePath;}
    public static void setActiveFile(Path path) {
        activeFilePath = path;
        activeFileName = path.getFileName().toString();
    }
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
    public static String readFile(Path path) throws IOException {
        activeFilePath = path;
        activeFileName = path.getFileName().toString();
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
            Path filePath = saveDirectory.resolve(baseName + "-" + counter);
            if (!Files.exists(filePath)) {
                return filePath.getFileName().toString();
            }
            counter++;
        }
    }
    public static void initNewFile() throws IOException {
        String fileName = fileNameGenerator();
        createFile(fileName);
        setActiveFile(getFilePath(fileName));
        syncSavedFiles();
    }
    public static void syncSavedFiles() throws IOException {
        Stream<Path> savedFiles = Files.list(saveDirectory).sorted();
        savedFilesList = "";
        savedFiles.forEach(path ->
                {
                    // assert savedFilesList != null;
                    savedFilesList = savedFilesList + (path.getFileName().toString()) + "\n";
                }
        );
        savedFilesList = savedFilesList.strip();
    }
}
