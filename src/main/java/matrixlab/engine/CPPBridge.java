package matrixlab.engine;

import org.scijava.nativelib.NativeLoader;

import java.io.IOException;

public class CPPBridge {
    static {
        try {
            NativeLoader.loadLibrary("mathengine"); // loads C++ math engine
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static native String math(String input);
}
