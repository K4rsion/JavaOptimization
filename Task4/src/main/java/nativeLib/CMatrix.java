package nativeLib;

public class CMatrix {
    static {
        System.loadLibrary("matrix");
    }
    public native void multiplyMatrix128();
    public native void multiplyMatrix1024();
}
