package matrixlab.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileHandler {

    private static final Path saveDirectory = Paths.get("savedFiles");
    private static final Path logDirectory = Paths.get("logFiles");
    public static String activeFileName = "";
    public static String savedFilesList = null;
    private static Path activeFilePath = null;

    // getters and setters
    public static Path getActiveFilePath() {
        return activeFilePath;
    }

    public static void setActiveFile(Path path) {
        activeFilePath = path;
        activeFileName = path.getFileName().toString();
    }

    public static void nullActiveFile() {
        activeFilePath = null;
        activeFileName = "";
    }

    // file operations start
    public static void createFile(String name) throws IOException {
        Path path = saveDirectory.resolve(name);
        Files.createFile(path);
    }

    public static void createLog(String name) throws IOException {
        Path path = logDirectory.resolve(name + "_log");
        Files.createFile(path);
    }

    public static void removeFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        removeLog(path.getFileName().toString());
    }

    public static void removeLog(String fileName) throws IOException {
        Path logPath = getLogFilePath(fileName);
        Files.deleteIfExists(logPath);
    }

    public static void saveFile(Path path, String text) throws IOException {
        if (activeFilePath == null) {
            initNewFile();
            path = activeFilePath;
        }
        Files.writeString(path, text);
    }

    public static void updateLog(String filename, String text) throws IOException {
        String logFileName = filename + "_log";
        if (!Files.exists(getLogFilePath(filename))) {
            createLog(filename);
        }
        Path logPath = logDirectory.resolve(logFileName);
        Files.writeString(logPath, text, StandardOpenOption.APPEND);
    }

    public static String readFile(Path path) throws IOException {
        setActiveFile(path);
        return Files.readString(path);
    }

    public static void renameFile(Path oldPath, String newName) throws IOException {
        Path newPath = saveDirectory.resolve(newName);
        Files.move(oldPath, newPath);
        // logging
        Path logPath = getLogFilePath(oldPath.getFileName().toString());
        if (Files.exists(logPath)) {
            Files.move(logPath, getLogFilePath(newName));
        }
    }

    public static Path getSaveFilePath(String fileName) {
        return saveDirectory.resolve(fileName);
    }

    public static Path getLogFilePath(String fileName) {
        return logDirectory.resolve(fileName + "_log");
    }

    public static String fileNameGenerator() {
        int counter = 1;
        String baseName = "Untitled";

        if (!Files.exists(getSaveFilePath(baseName))) {
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
        setActiveFile(getSaveFilePath(fileName));
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
