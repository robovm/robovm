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


#ifndef _Included_org_apache_harmony_luni_platform_Environment
#define _Included_org_apache_harmony_luni_platform_Environment
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_apache_harmony_luni_platform_Environment
 * Method:    getEnvBytes
 * Signature: ()[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvBytes
  (JNIEnv *, jclass);

/*
 * Class:     org_apache_harmony_luni_platform_Environment
 * Method:    getEnvByName
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvByName
  (JNIEnv *, jclass, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
