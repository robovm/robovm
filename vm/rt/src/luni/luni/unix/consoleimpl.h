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
#include "jni.h"
 
/*
 * Class:     java_io_Console
 * Method:    hasStdInImpl
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_java_io_Console_hasStdInImpl
  (JNIEnv *, jclass);

/*
 * Class:     java_io_Console
 * Method:    hasStdOutImpl
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_java_io_Console_hasStdOutImpl
  (JNIEnv *, jclass);
    
/*
 * Class:     java_io_Console
 * Method:    setEchoOffImpl
 * Signature: ()V
 */
  JNIEXPORT void JNICALL Java_java_io_Console_setEchoOffImpl
    (JNIEnv *, jclass);
    
/*
 * Class:     java_io_Console
 * Method:    setEchoOnImpl
 * Signature: ()V
 */
  JNIEXPORT void JNICALL Java_java_io_Console_setEchoOnImpl
    (JNIEnv *, jclass);
    
