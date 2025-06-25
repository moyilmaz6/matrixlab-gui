package matrixlab.engine;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Brain {
    public static List<String> objList = new ArrayList<>();
//    private static Boolean debugMode = false;
    public static String forwardToEngine(String input) {
        return CPPBridge.math(input);
    } // wrapper for engine com

    public static String think(String input) throws IOException {
        if (Objects.equals(input, "help")) {
            return "Available commands: help, print, remove\n" +
                    "/debug - activates debug mode";
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
                String text = FileHandler.readFile(filePath);
                loadTable(text);
                return "File content: " + text;
            }
            if (input.startsWith("/renameFile")) {
                String[] args = input.substring(12).split(" ");
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
                    FileHandler.setActiveFile(filePath);
                }
                FileHandler.saveFile(FileHandler.getActiveFilePath(), getTable());
                return "File: " + FileHandler.activeFileName + " saved";
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
            if (input.startsWith("/active")) {
                return "Active file: " + FileHandler.activeFileName;
            }
            return "Unknown command";
        }
        String engineResponse = forwardToEngine(input);
        syncObjList();
        return engineResponse;
    }

    public static void removeObj(String objName) {
        forwardToEngine("remove " + objName);
    }

    public static String getObjList() {
        return forwardToEngine("/list").strip();
    }

    public static String getTable() {
        return forwardToEngine("/getTable");
    }

    public static void loadTable(String fileName) throws IOException {
        forwardToEngine("/loadTable\n" + FileHandler.readFile(FileHandler.getFilePath(fileName)));
    }

    public static void clearTable() {
        forwardToEngine("/clearTable");
    }

    public static String dumpTable() {
        return forwardToEngine("/dumpTable");
    }

    public static void syncObjList() {
        objList = Arrays.asList(getObjList().split("\n"));
    }

    public static void printHelp() {

    }
}