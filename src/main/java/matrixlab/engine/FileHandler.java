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

import static matrixlab.engine.Brain.think;

public class FileHandler {

    private static String activeFile = null;
    private static Path saveDirectory = Paths.get("savedFiles");


}
