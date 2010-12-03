/* Licensed to the Apache Software Foundation (ASF) under one or more
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

#include <jni.h>
#include "hycomp.h"

JNIEXPORT jobject JNICALL NewDirectByteBuffer(JNIEnv * , void*, jlong );
JNIEXPORT void* JNICALL GetDirectBufferAddress(JNIEnv * , jobject );
JNIEXPORT jlong JNICALL GetDirectBufferCapacity(JNIEnv * , jobject );

/*
 * Reference implementation of NewDirectByteBuffer() functionality (JNI 1.4 NIO support).
 * To be included to JNI function table either directly or wrapped.
 */
JNIEXPORT jobject JNICALL NewDirectByteBuffer
  (JNIEnv * env, void* address, jlong capacity){
	  jmethodID newBufferMethod;
	  jclass directBufferClass;
	  jclass platformaddressClass;
	  jobject platformaddress;
	  jmethodID onMethod;
          directBufferClass = (*env)->FindClass (env, "java/nio/ReadWriteDirectByteBuffer");
	  if (!directBufferClass){
	      	  return NULL;
	  }
	  newBufferMethod = (*env)->GetMethodID (env, directBufferClass, "<init>",
             "(Lorg/apache/harmony/luni/platform/PlatformAddress;II)V");
	  if (!newBufferMethod){
	      	  return NULL;
	  }
	  platformaddressClass = (*env)->FindClass (env, "org/apache/harmony/luni/platform/PlatformAddressFactory");
	  if (!platformaddressClass){
	      	  return NULL;
	  }
	  onMethod = (*env)->GetStaticMethodID (env, platformaddressClass, "on",
             "(J)Lorg/apache/harmony/luni/platform/PlatformAddress;");
	  if (!onMethod){
	      	  return NULL;
	  }
	  platformaddress = (*env)->CallStaticObjectMethod(env, platformaddressClass, onMethod, (jlong)(IDATA)address);
	  return (*env)->NewObject(env, directBufferClass, newBufferMethod, 
                                platformaddress, (jint)capacity, (jint)0);
  }

/*
 * Reference implementation of GetDirectBufferAddress() functionality 
 * (JNI 1.4 NIO support).
 * To be included to JNI function table either directly or wrapped.
 */
JNIEXPORT void* JNICALL GetDirectBufferAddress
  (JNIEnv * env, jobject buf){
	  jmethodID tempMethod;
	  jclass tempClass;
	  jobject platformAddr;
	  jclass platformAddrClass;
	  jmethodID toLongMethod;
	  
          tempClass = (*env)->FindClass (env, "org/apache/harmony/nio/internal/DirectBuffer");
	  if (!tempClass){
	      	  return 0;
	  }
	  if (JNI_FALSE == (*env)->IsInstanceOf(env, buf, tempClass)){
		  return 0;
	  }	  
	  tempMethod = (*env)->GetMethodID (env, tempClass, "getBaseAddress",
             "()Lorg/apache/harmony/luni/platform/PlatformAddress;");	  	  
	  if (!tempMethod){
	      	  return 0;
	  }	  
	  platformAddr = (*env)->CallObjectMethod(env, buf, tempMethod);
	  platformAddrClass = (*env)->FindClass (env, "org/apache/harmony/luni/platform/PlatformAddress");
	  if (!platformAddrClass){
	      	  return 0;
	  }
	  toLongMethod = (*env)->GetMethodID (env, platformAddrClass, "toLong",
             "()J");
	  if (!toLongMethod){
	      	  return 0;
	  }
	  return (void*)(IDATA)(*env)->CallLongMethod(env, platformAddr, toLongMethod);	  
  }

/*
 * Reference implementation of GetDirectBufferCapacity() functionality 
 * (JNI 1.4 NIO support).
 * To be included to JNI function table either directly or wrapped.
 */
JNIEXPORT jlong JNICALL GetDirectBufferCapacity
  (JNIEnv * env, jobject buf){
	  jmethodID methodCapacity;
	  jclass directBufferClass;
          directBufferClass = (*env)->FindClass (env, "org/apache/harmony/nio/internal/DirectBuffer");
	  if (!directBufferClass){
	      	  return -1;
	  }
	  if (JNI_FALSE == (*env)->IsInstanceOf(env, buf, directBufferClass)){
		  return -1;
	  }
	  methodCapacity = (*env)->GetMethodID (env, directBufferClass, "getByteCapacity",
             "()I");
	  if (!methodCapacity){
	      	  return -1;
	  }
	  return (jlong)(*env)->CallIntMethod(env, buf, methodCapacity);
  }
