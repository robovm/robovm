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
/* Header for class org_apache_harmony_nio_internal_EPollSelectorImpl */
#define SOCKET_NONE_OP 0
#define SOCKET_READ_OP 1
#define SOCKET_WRITE_OP 2


/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    resolveFD
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_resolveFD
  (JNIEnv *, jclass, jclass, jobject);

/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    prepare
 * Signature: ()I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_prepare
  (JNIEnv *, jclass);


/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    addFileDescriptor
 * Signature: (L)I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_addFileDescriptor
  (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    delFileDescriptor
 * Signature: (L)I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_delFileDescriptor
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    epoll
 * Signature: (L)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_epoll
  (JNIEnv *, jclass, jlong, jint, jlongArray, jintArray, jlong);
