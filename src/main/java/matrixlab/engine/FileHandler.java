package matrixlab.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileHandler {

    // On macOS/Linux this will be: /Users/<username>/.dashinkalab/
    // On Windows this will be: C:\Users\<username>\.dashinkalab\
    private static final String USER_HOME = System.getProperty("user.home");
    private static final Path APP_ROOT = Paths.get(USER_HOME, ".matrixlab");

    private static final Path saveDirectory = APP_ROOT.resolve("savedFiles");
    private static final Path logDirectory = APP_ROOT.resolve("logFiles");

    public static String activeFileName = "";
    public static String savedFilesList = null;
    private static Path activeFilePath = null;

    static {
        try {
            if (!Files.exists(saveDirectory)) Files.createDirectories(saveDirectory);
            if (!Files.exists(logDirectory)) Files.createDirectories(logDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void createFile(String name) throws IOException {
        ensureDirectoriesExist(); // Double check before writing
        Path path = saveDirectory.resolve(name);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public static void createLog(String name) throws IOException {
        ensureDirectoriesExist();
        Path path = logDirectory.resolve(name + "_log");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
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
        ensureDirectoriesExist();
        if (activeFilePath == null) {
            initNewFile();
            path = activeFilePath;
        }
        Files.writeString(path, text);
    }

    public static void updateLog(String filename, String text) throws IOException {
        ensureDirectoriesExist();
        String logFileName = filename + "_log";
        Path logPath = logDirectory.resolve(logFileName);

        if (!Files.exists(logPath)) {
            Files.createFile(logPath);
        }

        Files.writeString(logPath, text, StandardOpenOption.APPEND);
    }

    public static String readFile(Path path) throws IOException {
        setActiveFile(path);
        return Files.readString(path);
    }

    public static void renameFile(Path oldPath, String newName) throws IOException {
        ensureDirectoriesExist();
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
        try { ensureDirectoriesExist(); } catch(IOException e) { e.printStackTrace(); }

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
        ensureDirectoriesExist();
        String fileName = fileNameGenerator();
        createFile(fileName);
        setActiveFile(getSaveFilePath(fileName));
        syncSavedFiles();
    }

    public static void syncSavedFiles() throws IOException {
        ensureDirectoriesExist();

        try (Stream<Path> savedFiles = Files.list(saveDirectory)) {
            StringBuilder sb = new StringBuilder();
            savedFiles.sorted().forEach(path ->
                    sb.append(path.getFileName().toString()).append("\n")
            );
            savedFilesList = sb.toString().strip();
        }
    }

    private static void ensureDirectoriesExist() throws IOException {
        if (!Files.exists(saveDirectory)) {
            Files.createDirectories(saveDirectory);
        }
        if (!Files.exists(logDirectory)) {
            Files.createDirectories(logDirectory);
        }
    }
}