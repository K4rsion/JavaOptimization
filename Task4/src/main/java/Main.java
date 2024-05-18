import nativeLib.CMatrix;
import nativeLib.JavaMatrix;

public class Main {
    public static void main(String[] args) {
        JavaMatrix javaMatrix = new JavaMatrix();
        javaMatrix.multiplyMatrix128();
        javaMatrix.multiplyMatrix1024();

        CMatrix cMatrix = new CMatrix();
        cMatrix.multiplyMatrix128();
        cMatrix.multiplyMatrix1024();
    }
}
