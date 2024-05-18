#include "nativeLib_CMatrix.h"
#include <stdlib.h>
#include <time.h>

JNIEXPORT void JNICALL Java_nativeLib_CMatrix_multiplyMatrix128(JNIEnv *env,
                                                                jobject obj) {

  clock_t start, end;
  double cpu_time_used;

  // Генерация матрицы 128x128
  jint matrixSize = 128;
  jint **matrix1 = (jint **)malloc(matrixSize * sizeof(jint *));
  jint **matrix2 = (jint **)malloc(matrixSize * sizeof(jint *));
  jint **resultMatrix = (jint **)malloc(matrixSize * sizeof(jint *));

  // Проверка успешности выделения памяти
  if (matrix1 == NULL || matrix2 == NULL || resultMatrix == NULL) {
    // Обработка ошибки
    return;
  }

  for (int i = 0; i < matrixSize; i++) {
    matrix1[i] = (jint *)malloc(matrixSize * sizeof(jint));
    matrix2[i] = (jint *)malloc(matrixSize * sizeof(jint));
    resultMatrix[i] = (jint *)malloc(matrixSize * sizeof(jint));

    if (matrix1[i] == NULL || matrix2[i] == NULL || resultMatrix[i] == NULL) {
      // Обработка ошибки
      return;
    }
  }

  // Заполнение матриц рандомными числами
  srand(time(NULL));
  for (int i = 0; i < matrixSize; i++) {
    for (int j = 0; j < matrixSize; j++) {
      matrix1[i][j] = rand() % 100000;
      matrix2[i][j] = rand() % 100000;
    }
  }

  start = clock(); // Замер времени начала выполнения

  // Умножение матриц
  for (int i = 0; i < matrixSize; i++) {
    for (int j = 0; j < matrixSize; j++) {
      resultMatrix[i][j] = 0;
      for (int k = 0; k < matrixSize; k++) {
        resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
      }
    }
  }

  end = clock(); // Замер времени окончания выполнения
  cpu_time_used =
      ((double)(end - start)) / CLOCKS_PER_SEC; // Вычисление времени выполнения
  printf("C 128x128 matrices:\t\t%f seconds\n", cpu_time_used);

  // Освобождение памяти
  for (int i = 0; i < matrixSize; i++) {
    free(matrix1[i]);
    free(matrix2[i]);
    free(resultMatrix[i]);
  }
  free(matrix1);
  free(matrix2);
  free(resultMatrix);
}


JNIEXPORT void JNICALL Java_nativeLib_CMatrix_multiplyMatrix1024(JNIEnv *env,
                                                                 jobject obj) {

  clock_t start, end;
  double cpu_time_used;

  // Генерация матрицы 1024x1024
  jint matrixSize = 1024;
  jint **matrix1 = (jint **)malloc(matrixSize * sizeof(jint *));
  jint **matrix2 = (jint **)malloc(matrixSize * sizeof(jint *));
  jint **resultMatrix = (jint **)malloc(matrixSize * sizeof(jint *));

  // Проверка успешности выделения памяти
  if (matrix1 == NULL || matrix2 == NULL || resultMatrix == NULL) {
    // Обработка ошибки
    return;
  }

  for (int i = 0; i < matrixSize; i++) {
    matrix1[i] = (jint *)malloc(matrixSize * sizeof(jint));
    matrix2[i] = (jint *)malloc(matrixSize * sizeof(jint));
    resultMatrix[i] = (jint *)malloc(matrixSize * sizeof(jint));

    if (matrix1[i] == NULL || matrix2[i] == NULL || resultMatrix[i] == NULL) {
      // Обработка ошибки
      return;
    }
  }

  // Заполнение матриц рандомными числами
  srand(time(NULL));
  for (int i = 0; i < matrixSize; i++) {
    for (int j = 0; j < matrixSize; j++) {
      matrix1[i][j] = rand() % 100000;
      matrix2[i][j] = rand() % 100000;
    }
  }

  start = clock(); // Замер времени начала выполнения

  // Умножение матриц
  for (int i = 0; i < matrixSize; i++) {
    for (int j = 0; j < matrixSize; j++) {
      resultMatrix[i][j] = 0;
      for (int k = 0; k < matrixSize; k++) {
        resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
      }
    }
  }

  end = clock(); // Замер времени окончания выполнения
  cpu_time_used =
      ((double)(end - start)) / CLOCKS_PER_SEC; // Вычисление времени выполнения
  printf("C 1024x1024 matrices:\t\t%f seconds\n", cpu_time_used);

  // Освобождение памяти
  for (int i = 0; i < matrixSize; i++) {
    free(matrix1[i]);
    free(matrix2[i]);
    free(resultMatrix[i]);
  }
  free(matrix1);
  free(matrix2);
  free(resultMatrix);
}
