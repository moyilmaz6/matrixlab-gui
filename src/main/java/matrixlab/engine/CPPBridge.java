package matrixlab.engine;

public class CPPBridge {
    static {
        System.loadLibrary("mathengine"); // loads C++ math engine
    }
    public static native String math(String input);
}
