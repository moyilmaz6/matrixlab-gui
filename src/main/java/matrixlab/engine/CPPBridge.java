package matrixlab.engine;

public class CPPBridge {
    static {
        System.loadLibrary("mathengine"); // loads libmatrixengine.so / matrixengine.dll
    }
    public static native String math(String input);
}
