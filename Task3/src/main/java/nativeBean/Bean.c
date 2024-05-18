#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "nativeBean_Bean.h"


void method1();
void method2();
void method3();


JNIEXPORT void JNICALL Java_nativeBean_Bean_dropMemory
  (JNIEnv *env, jobject obj) {
         while (1) {
                     // Попытка выделить большой блок памяти
                     size_t blockSize = 1024 * 1024 * 512; // 0.5 гб
                     void* memory = malloc(blockSize);
                     if (memory != NULL) {
                         // Заполняем выделенную память, чтобы гарантировать, что ОС действительно ее выделила
                         memset(memory, 0, blockSize);
                     } else {
                         // Если выделить память не удалось, прекращаем цикл
                         printf("Something goes wrong!");
                         break;
                     }
         }
}

JNIEXPORT void JNICALL Java_nativeBean_Bean_allocateMemory
  (JNIEnv *env, jobject obj) {
        void* memory = malloc(1024);
        if (memory != NULL) {
            memset(memory, 0, 1024);
        }
  }

JNIEXPORT void JNICALL Java_nativeBean_Bean_chainMethods
  (JNIEnv *env, jobject obj){
    method1();
}

JNIEXPORT jint JNICALL Java_nativeBean_Bean_stringLength
  (JNIEnv *env, jobject obj, jstring inputString){
        // Получаем указатель на строку в нативном формате
        const char *nativeString = (*env)->GetStringUTFChars(env, inputString, NULL);

        // Вычисляем длину строки
        int length = strlen(nativeString);

        // Освобождаем память, выделенную для строки
        (*env)->ReleaseStringUTFChars(env, inputString, nativeString);

        // Возвращаем длину строки
        return length;
  }

JNIEXPORT void JNICALL Java_nativeBean_Bean_callObjectMethod
  (JNIEnv *env, jobject obj, jobject inputObject){
        // Получаем класс объекта
        jclass cls = (*env)->GetObjectClass(env, inputObject);

        // Получаем метод объекта
        jmethodID mid = (*env)->GetMethodID(env, cls, "myMethod", "()V");

        // Вызываем метод объекта
        (*env)->CallVoidMethod(env, inputObject, mid);
  }


JNIEXPORT void JNICALL Java_nativeBean_Bean_changeFieldValue
  (JNIEnv *env, jobject obj, jobject inputObject){
        // Получаем класс объекта
        jclass cls = (*env)->GetObjectClass(env, inputObject);

        // Получаем поле объекта
        jfieldID fid = (*env)->GetFieldID(env, cls, "intValue", "I");
        if (fid == NULL) {
            return; // Не удалось найти поле
        }

        // Получаем текущее значение поля
        jint value = (*env)->GetIntField(env, inputObject, fid);

        // Увеличиваем значение поля на 1
        value++;

        // Устанавливаем новое значение поля
        (*env)->SetIntField(env, inputObject, fid, value);
  }

typedef struct IntStruct {
    int value;
} IntStruct;

JNIEXPORT jlong JNICALL Java_nativeBean_Bean_allocateStructure
  (JNIEnv * env, jobject obj){
    IntStruct* intStruct = (IntStruct*) malloc(sizeof(IntStruct));
    intStruct->value = 42;
    printf("1)The structure was allocated. Value of the structure is 42.\n");
    return (jlong)intStruct;
  }

JNIEXPORT jlong JNICALL Java_nativeBean_Bean_getStructureValue
  (JNIEnv * env, jobject obj, jlong structValue){
    IntStruct* intStruct = (IntStruct*) structValue;
    if (intStruct == NULL) return 0;
    return intStruct->value;
  }

JNIEXPORT void JNICALL Java_nativeBean_Bean_freeStructure
  (JNIEnv * env, jobject obj, jlong structValue){
    IntStruct* intStruct = (IntStruct*) structValue;
    free(intStruct);
    printf("2)The structure was cleared.\n");
  }


// Метод 1
void method1() {
    printf("Called method 1\n");
    method2();
}

// Метод 2
void method2() {
    printf("Called method 2\n");
    method3();
}

// Метод 3 (вызывает критическую ошибку)
void method3() {
    printf("Called method 3\n");
    // Вызов несуществующей функции для генерации ошибки сегментации
    int* ptr = NULL;
    *ptr = 10; // Это приведет к критической ошибке
}

/*
void throwJavaException(JNIEnv *env, const char *msg)
{
    // You can put your own exception here
    jclass c = env->FindClass("company/com/YourException");

    if (NULL == c)
    {
        //B plan: null pointer ...
        c = env->FindClass("java/lang/NullPointerException");
    }

    env->ThrowNew(c, msg);
}*/
