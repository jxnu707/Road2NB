#include "mJni_JniClient.h"
#include <stdlib.h>
#include <stdio.h>

#ifdef __cplusplus
extern "C"
{
#endif
/*
 * Class:     mJni_JniClient
 * Method:    addStr
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_mJni_JniClient_addStr
  (JNIEnv *env , jclass arg, jstring inStringA, jstring inStringB)
{
    jstring str = (*env)->NewStringUTF(env, "HelloWorld from JNI !");
    return str;
}

/*
 * Class:     mJni_JniClient
 * Method:    addInt
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_mJni_JniClient_addInt
  (JNIEnv *env, jclass arg, jint a, jint b)
{
    return a + b;
}

#ifdef __cplusplus
}
#endif
