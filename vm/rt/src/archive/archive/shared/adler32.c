/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "jni.h"
#include "vmi.h"
#include "zconf.h"
#include "zlib.h"
#include "exceptions.h"

JNIEXPORT jlong JNICALL
Java_java_util_zip_Adler32_updateImpl (JNIEnv * env, jobject recv,
                                       jbyteArray buf, int off, int len,
                                       jlong crc)
{
  jbyte *b;
  jboolean isCopy;
  jlong result;

  b = (*env)->GetPrimitiveArrayCritical (env, buf, &isCopy);
  if (b == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }
  result = (jlong) adler32 ((uLong) crc, (Bytef *) (b + off), (uInt) len);
  (*env)->ReleasePrimitiveArrayCritical (env, buf, b, JNI_ABORT);

  return result;
}

JNIEXPORT jlong JNICALL
Java_java_util_zip_Adler32_updateByteImpl (JNIEnv * env, jobject recv,
                                           jint val, jlong crc)
{
  Bytef bytefVal = val;
  return adler32 ((uLong) crc, (Bytef *) (&bytefVal), 1);
}
