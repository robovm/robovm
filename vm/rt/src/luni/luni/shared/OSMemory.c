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

/*
 * Common natives supporting the memory system interface.
 */

#include <string.h>
#include "vmi.h"
#include "OSMemory.h"
#include "IMemorySystem.h"
#include "exceptions.h"

JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSMemory_malloc
  (JNIEnv * env, jobject thiz, jlong size)
{
  PORT_ACCESS_FROM_ENV (env);

  void *address = hymem_allocate_memory ((UDATA) size);

  if (address == NULL)
    {
      throwNewOutOfMemoryError(env, "Insufficient memory available.");
    }

  return (jlong) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_free
  (JNIEnv * env, jobject thiz, jlong address)
{
  PORT_ACCESS_FROM_ENV (env);

  hymem_free_memory ((void *) ((IDATA) address));
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_memmove
  (JNIEnv * env, jobject thiz, jlong destAddress, jlong srcAddress,
   jlong length)
{
  memmove ((void *) ((IDATA) destAddress),
	   (const void *) ((IDATA) srcAddress), (size_t) length);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_memset
  (JNIEnv * env, jobject thiz, jlong address, jbyte value, jlong length)
{
  memset ((void *) ((IDATA) address), (int) value, (size_t) length);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getByteArray
  (JNIEnv * env, jobject thiz, jlong address, jbyteArray byteArray,
   jint offset, jint length)
{
  (*env)->SetByteArrayRegion(env, byteArray, offset, length, (jbyte *) ((IDATA) address));
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setByteArray
  (JNIEnv * env, jobject thiz, jlong address, jbyteArray byteArray,
   jint offset, jint length)
{
  jboolean isCopy;
  jbyte *bytes = (*env)->GetPrimitiveArrayCritical(env, byteArray, &isCopy);
  memcpy ((void *) ((IDATA) address),
	  (const jbyte *) ((IDATA) bytes + offset), (size_t) length);
  (*env)->ReleasePrimitiveArrayCritical(env, byteArray, bytes, JNI_ABORT);
}

JNIEXPORT jbyte JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getByte
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jbyte *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setByte
  (JNIEnv * env, jobject thiz, jlong address, jbyte value)
{
  *(jbyte *) ((IDATA) address) = value;
}

JNIEXPORT jshort JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getShort
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jshort *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setShort
  (JNIEnv * env, jobject thiz, jlong address, jshort value)
{
  *(jshort *) ((IDATA) address) = value;
}

JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getInt
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jint *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setInt
  (JNIEnv * env, jobject thiz, jlong address, jint value)
{
  *(jint *) ((IDATA) address) = value;
}

JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getLong
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jlong *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setLong
  (JNIEnv * env, jobject thiz, jlong address, jlong value)
{
  *(jlong *) ((IDATA) address) = value;
}

JNIEXPORT jfloat JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getFloat
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jfloat *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setFloat
  (JNIEnv * env, jobject thiz, jlong address, jfloat value)
{
  *(jfloat *) ((IDATA) address) = value;
}

JNIEXPORT jdouble JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getDouble
  (JNIEnv * env, jobject thiz, jlong address)
{
  return *(jdouble *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setDouble
  (JNIEnv * env, jobject thiz, jlong address, jdouble value)
{
  *(jdouble *) ((IDATA) address) = value;
}
