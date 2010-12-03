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

#if !defined(inflater_h)
#define inflater_h

#include "zlib.h"
typedef struct JCLZipStream
{
  U_8 *inaddr;
  U_8 *dict;
  z_stream *stream;
} JCLZipStream;

void zfree PROTOTYPE ((void *opaque, void *address));
void *zalloc PROTOTYPE ((void *opaque, U_32 items, U_32 size));
JNIEXPORT void JNICALL Java_java_util_zip_Inflater_endImpl (JNIEnv * env, jobject recv,
                                                  jlong handle);
JNIEXPORT void JNICALL Java_java_util_zip_Inflater_setInputImpl (JNIEnv * env,
                                                       jobject recv,
                                                       jbyteArray buf,
                                                       jint off, jint len,
                                                       jlong handle);
JNIEXPORT jint JNICALL Java_java_util_zip_Inflater_inflateImpl (JNIEnv * env,
                                                      jobject recv,
                                                      jbyteArray buf, int off,
                                                      int len, jlong handle);
JNIEXPORT void JNICALL Java_java_util_zip_Inflater_setDictionaryImpl (JNIEnv * env,
                                                            jobject recv,
                                                            jbyteArray dict,
                                                            int off, int len,
                                                            jlong handle);
JNIEXPORT void JNICALL Java_java_util_zip_Inflater_oneTimeInitialization (JNIEnv * env,
                                                                jclass clazz);
JNIEXPORT void JNICALL Java_java_util_zip_Inflater_resetImpl (JNIEnv * env,
                                                    jobject recv,
                                                    jlong handle);
JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_getTotalOutImpl (JNIEnv * env,
                                                         jobject recv,
                                                         jlong handle);
JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_createStream (JNIEnv * env,
                                                        jobject recv,
                                                        jboolean noHeader);
JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_getTotalInImpl (JNIEnv * env,
                                                        jobject recv,
                                                        jlong handle);
JNIEXPORT jint JNICALL Java_java_util_zip_Inflater_getAdlerImpl (JNIEnv * env,
                                                       jobject recv,
                                                       jlong handle);

#endif /* inflater_h */
