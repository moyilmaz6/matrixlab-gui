package matrixlab.engine;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.jetbrains.annotations.NotNull;

public class Brain {
    public static String think(String input) {
        return CPPBridge.math(input);
    }
}