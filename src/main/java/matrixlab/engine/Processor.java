package matrixlab.engine;

import java.io.*;

public class Processor {
    public static String process(String input) {
        try {
            // Load the executable from resources
            File exeFile = new File("src/main/resources/engine.exe"); // or "build/resources/main/test.exe" if built
            if (!exeFile.exists()) {
                return "Executable not found: " + exeFile.getAbsolutePath();
            }

            ProcessBuilder pb = new ProcessBuilder(exeFile.getAbsolutePath());
            Process process = pb.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.newLine();
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            reader.close();

            return result.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "C++ call failed: " + e.getMessage();
        }
    }
}
