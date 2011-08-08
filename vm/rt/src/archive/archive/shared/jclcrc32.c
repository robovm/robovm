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

#include "vmi.h"
#include "zconf.h"
#include "exceptions.h"

uLong crc32 PROTOTYPE ((uLong crc, const Bytef * buf, uInt size));

JNIEXPORT jlong JNICALL
Java_java_util_zip_CRC32_updateImpl (JNIEnv * env, jobject recv,
                                     jbyteArray buf, int off, int len,
                                     jlong crc)
{
  jbyte *b;
  jlong result;

  b = ((*env)->GetPrimitiveArrayCritical (env, buf, 0));
  if (b == NULL) {
    throwNewOutOfMemoryError(env, "");
    return -1;
  }
  result = crc32 ((uLong) crc, (Bytef *) (b + off), (uInt) len);
  ((*env)->ReleasePrimitiveArrayCritical (env, buf, b, JNI_ABORT));
  return result;
}

JNIEXPORT jlong JNICALL
Java_java_util_zip_CRC32_updateByteImpl (JNIEnv * env, jobject recv,
                                         jbyte val, jlong crc)
{
  return crc32 ((uLong) crc, (Bytef *) (&val), 1);
}
