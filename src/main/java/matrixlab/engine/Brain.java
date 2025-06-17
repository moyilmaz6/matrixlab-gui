package matrixlab.engine;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import matrixlab.engine.CPPBridge;
import org.jetbrains.annotations.NotNull;

public class Brain {
    public static String think(String input) {
        return CPPBridge.math(input);
    }
    public static void save(String filename) throws IOException {
        Path saveDir = Paths.get("savedFiles");
        Path filePath = saveDir.resolve(filename);
        String input = think("/save");
        Files.writeString(filePath, input);
    }
    // writes to a file given with its name
    public static void load(String filename) throws IOException {
        String output = Files.readString(Paths.get(filename));
        String command = "/load\n";
        think(command + output);
    }
    // loads the file given with its name
    public static void delete(String filename) throws IOException{
        Files.delete(Paths.get(filename));
    }
    // deletes the file given with its name
    public static List<String> updateTable(String input) {
        String output = think("/getTable");
        return Arrays.asList(output.split("\n"));
    }
    // returns a list of objects from the user table from C++
    public static void rename(String oldName, String newName) throws IOException {
        Path source = Paths.get(oldName);
        Path target = Paths.get(newName);
        Files.move(source, target);
    }
    // renames the file
}