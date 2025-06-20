package matrixlab.engine;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.jetbrains.annotations.NotNull;

public class Brain {
    private static Boolean debugMode = false;
    public static String forwardToEngine(String input) {return CPPBridge.math(input);} // wrapper for engine com
    public static String think(String input) throws IOException {
        if (Objects.equals(input, "help")) {
            return "Available commands: help, print, remove\n" +
                    "/debug - activates debug mode";
        }
        if (Objects.equals(input, "/debug")) {
            debugMode = true;
            return "Debug mode is activated\n" +
                    "/exit to disable\n\n" +
                    "IMPORTANT: every command must start with: /";
        }
        if (Objects.equals(input, "/commands")) {
            return "Available commands: help, print, remove\n" +
                    "/removeFile <fileName>\n" +
                    "/loadFile <fileName>\n" +
                    "/renameFile <newName>\n" +
                    "/saveFile\n" +
                    "/createFile <fileName>\n" +
                    "/list, /dump, /clear";
        }
        if (Objects.equals(input, "/exit")) {
            debugMode = false;
            return "Debug mode is disabled";
        }
        //
        if (!debugMode && input.startsWith("/")) {
            return "Debug mode is not activated\n" +
                    "/ is reserved for debug console";
        }
        if (input.startsWith("/")) { // debug console to test GUI
            if (input.startsWith("/removeFile")) {
                String fileName = input.substring(12);
                Path filePath = FileHandler.getFilePath(fileName);
                FileHandler.removeFile(filePath);
                return "File: " + fileName + " removed";
            }
            if (input.startsWith("/loadFile")) {
                String fileName = input.substring(10);
                Path filePath = FileHandler.getFilePath(fileName);
                String text = FileHandler.loadFile(filePath);
                loadTable(text);
                return "File: " + fileName + " loaded";
            }
            if (input.startsWith("/renameFile")) {
                String[] args = input.substring(13).split(" ");
                String oldName = args[0];
                String newName = args[1];
                Path oldFilePath = FileHandler.getFilePath(oldName);
                FileHandler.renameFile(oldFilePath, newName);
                return "File: " + oldName + " renamed to " + newName;
            }
            if (input.startsWith("/saveFile")) {
                if (FileHandler.getActiveFilePath() == null) {
                    FileHandler.createFile("Untitled");
                    Path filePath = FileHandler.getFilePath("Untitled");
                    FileHandler.setActiveFilePath(filePath);
                }
                FileHandler.saveFile(FileHandler.getActiveFilePath(), getTable());
                return "File: " + FileHandler.getActiveFileName() + " saved";
            }
            if (input.startsWith("/createFile")) {
                String fileName = input.substring(12);
                FileHandler.createFile(fileName);
                return "File: " + fileName + " created";
            }
            if (input.startsWith("/list")) {
                return getObjList();
            }
            if (input.startsWith("/dump")) {
                return dumpTable();
            }
            if (input.startsWith("/clear")) {
                clearTable();
                return "Table cleared";
            }
            return "Unknown command";
        }
        return forwardToEngine(input);
    }
    public static void removeObj(String objName) {
        forwardToEngine("remove" + objName);
    }
    public static String getObjList() {return forwardToEngine("/list");}
    public static String getTable() {return forwardToEngine("/getTable");}
    public static void loadTable(String text) {
        forwardToEngine("/loadTable\n" + text);
    }
    // String normalized = text.replace("\r\n", "\n").strip(); AI suggestion
    public static void clearTable() {forwardToEngine("/clearTable");}
    public static String dumpTable() {return forwardToEngine("/dumpTable");}
}