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

#include <jni.h>
/* Header for class org_apache_harmony_luni_platform_OSFileSystem */

#ifndef _Included_org_apache_harmony_luni_platform_OSFileSystem
#define _Included_org_apache_harmony_luni_platform_OSFileSystem
#ifdef __cplusplus
extern "C"
{
#endif
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    lockImpl
 * Signature: (JJJIZ)I
 */
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_lockImpl
    (JNIEnv *, jobject, jlong, jlong, jlong, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    getPageSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_getPageSize
  (JNIEnv *, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    unlockImpl
 * Signature: (JJJ)I
 */
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_unlockImpl
    (JNIEnv *, jobject, jlong, jlong, jlong);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    fflushImpl
 * Signature: (JZ)I
 */
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_fflushImpl
    (JNIEnv *, jobject, jlong, jboolean);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    seekImpl
 * Signature: (JJI)J
 */
  JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl
    (JNIEnv *, jobject, jlong, jlong, jint);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readDirectImpl
 * Signature: (JJII)J
 */
  JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readDirectImpl
    (JNIEnv *, jobject, jlong, jlong, jint, jint);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writeDirectImpl
 * Signature: (JJII)J
 */
  JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_writeDirectImpl
    (JNIEnv *, jobject, jlong, jlong, jint, jint);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readImpl
 * Signature: (J[BII)J
 */
  JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readImpl
    (JNIEnv *, jobject, jlong, jbyteArray, jint, jint);
/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writeImpl
 * Signature: (J[BII)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_writeImpl
  (JNIEnv *, jobject, jlong, jbyteArray, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readvImpl
 * Signature: (J[J[I[II)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readvImpl
  (JNIEnv *, jobject, jlong, jlongArray, jintArray, jintArray, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writev
 * Signature: (J[Ljava/lang/Object;[I[II)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_writev
  (JNIEnv *, jobject, jlong, jlongArray, jintArray, jintArray, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    closeImpl
 * Signature: (J)I
 */
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_closeImpl
    (JNIEnv *, jobject, jlong);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    truncateImpl
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_truncateImpl
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    openImpl
 * Signature: ([BI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_openImpl
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    transferImpl
 * Signature: (JLjava/io/FileDescriptor;JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_transferImpl
  (JNIEnv *, jobject, jlong, jobject, jlong, jlong);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    ttyAvailableImpl
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_ttyAvailableImpl
  (JNIEnv *, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    ttyReadImpl
 * Signature: ([BII)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_ttyReadImpl
  (JNIEnv *, jobject, jbyteArray, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
