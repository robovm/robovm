/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/** 
 * @author Intel, Gregory Shimansky
 */  

#ifndef _JNI_TYPES_H_
#define _JNI_TYPES_H_

/*
 * @file JNI types used for OPEN interface
 */

#if defined (_WIN32) || defined (__WIN32__) || defined (WIN32)

#define JNIEXPORT __declspec(dllexport)
#define JNIIMPORT __declspec(dllimport)
#define JNICALL __stdcall

typedef signed __int64 jlong;

#else

#define JNIEXPORT 
#define JNIIMPORT
#define JNICALL

typedef signed long long jlong; 

#endif

/*
 * Primitive types
 */
typedef unsigned char jboolean;
typedef signed char jbyte;
typedef unsigned short jchar;
typedef signed short jshort;
typedef signed int jint;
typedef float jfloat;
typedef double jdouble;
typedef jint jsize;

/*
 * Java types
 */
struct _jobject;
typedef struct _jobject*  jobject;
typedef jobject           jclass;
typedef jobject           jstring;
typedef jobject           jarray;
    typedef jarray        jobjectArray;
    typedef jarray        jbooleanArray;
    typedef jarray        jbyteArray;
    typedef jarray        jcharArray;
    typedef jarray        jshortArray;
    typedef jarray        jintArray;
    typedef jarray        jlongArray;
    typedef jarray        jfloatArray;
    typedef jarray        jdoubleArray;
typedef jobject           jthrowable;
typedef jobject           jweak;

typedef union jvalue {
    jboolean z;
    jbyte    b;
    jchar    c;
    jshort   s;
    jint     i;
    jlong    j;
    jfloat   f;
    jdouble  d;
    jobject  l;
} jvalue;

struct _jfieldID;
typedef struct _jfieldID* jfieldID;
struct _jmethodID;
typedef struct _jmethodID* jmethodID;

/*
 * Constants
 */

/*
 * Boolean constants
 */
#define JNI_FALSE  0
#define JNI_TRUE   1

/*
 * Return values
 */
#define JNI_OK     0
#define JNI_ERR    (-1)
#define JNI_EDETACHED (-2)
#define JNI_EVERSION  (-3)
#define JNI_ENOMEM    (-4)
#define JNI_EEXIST    (-5)
#define JNI_EINVAL    (-6)

/*
 * Release modes for working with arrays.
 */
#define JNI_COMMIT 1
#define JNI_ABORT  2

/*
 * Used as a generic pointer to a function.
 */ 
typedef struct {
    char *name;
    char *signature;
    void *fnPtr;
} JNINativeMethod;

/*
 * JNI Native Method Interface
 */
struct JNINativeInterface_;
struct JNIEnv_External;

#ifdef __cplusplus
typedef JNIEnv_External JNIEnv;
#else
typedef const struct JNINativeInterface_ *JNIEnv;
#endif

/*
 * JNI Invocation Interface
 */
struct JNIInvokeInterface_;
struct JavaVM_External;

#ifdef __cplusplus
typedef JavaVM_External JavaVM;
#else
typedef const struct JNIInvokeInterface_ *JavaVM;
#endif

#endif /* _JNI_TYPES_H_ */
