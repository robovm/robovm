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

#include "hysock.h"
#include "AddressUtil.h"

/*
 * Class:     org_apache_harmony_nio_AddressUtil
 * Method:    getFDAddress
 * Signature: (Ljava/io/FileDescriptor;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_AddressUtil_getFDAddress
  (JNIEnv * env, jclass clz, jobject fd){
	jclass descriptorCLS;
	jfieldID descriptorFID;
	hysocket_t hysocketP;

	//TODO add to cache
	descriptorCLS = (*env)->FindClass (env, "java/io/FileDescriptor");
	if (NULL == descriptorCLS){
		return 0;
	}

	descriptorFID = (*env)->GetFieldID (env, descriptorCLS, "descriptor", "J");
	if (NULL == descriptorFID){
		return 0;
	}

	hysocketP = (hysocket_t) ((IDATA)((*env)->GetLongField (env, fd, descriptorFID)));
    if (NULL == hysocketP) {
        return 0;
    }

#if defined(WIN32) || defined(WIN64)
    if (hysocketP->flags & SOCKET_IPV4_OPEN_MASK) {
        return (jlong)(hysocketP->ipv4);
    } else if (hysocketP->flags & SOCKET_IPV6_OPEN_MASK) {
        return (jlong)(hysocketP->ipv6);
    } else {
        return 0;
    }
#else 
    return (jlong)(hysocketP->sock);
#endif
}



