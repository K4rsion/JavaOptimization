package nativeLib;

import java.util.Random;

public class JavaMatrix {
    public void multiplyMatrix128() {
        int[][] matrix1 = generateRandomMatrix(128, 128);
        int[][] matrix2 = generateRandomMatrix(128, 128);
        double startTime = System.currentTimeMillis();
        int[][] result = multiplyMatrices(matrix1, matrix2);
        double endTime = System.currentTimeMillis();
//        printMatrix(result);
        System.out.println("Java 128x128 matrices: \t\t" + (endTime - startTime) / 1000.0 + " seconds");
    }

    public void multiplyMatrix1024() {
        int[][] matrix1 = generateRandomMatrix(1024, 1024);
        int[][] matrix2 = generateRandomMatrix(1024, 1024);
        double startTime = System.currentTimeMillis();
        int[][] result = multiplyMatrices(matrix1, matrix2);
        double endTime = System.currentTimeMillis();
//        printMatrix(result);
        System.out.println("Java 1024x1024 matrices: \t" + (endTime - startTime) / 1000.0 + " seconds");
    }

    private int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(100000); // Генерация случайного числа от 0 до 99999
            }
        }
        return matrix;
    }

    private int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix");
        }

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
