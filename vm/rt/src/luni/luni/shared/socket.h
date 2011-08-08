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

#if !defined(socket_h)
#define socket_h
#define BROKEN_MULTICAST_IF 1
#define BROKEN_MULTICAST_TTL 2
#define BROKEN_TCP_NODELAY 4
/* signals that when SO_LINGER is enabled and shutdown() is called, a subsequent call to closesocket() will unnecessarily hang */
#define BROKEN_SO_LINGER_SHUTDOWN 8

jobject getByteSocketOption (JNIEnv * env, hysocket_t hysocketP, int option);
void setIPV6McastInterface (JNIEnv * env, hysocket_t hysocketP,
          jobject optVal);
void setByteSocketOption (JNIEnv * env, hysocket_t hysocketP, int option,
        jobject optVal);
jobject getIPV6McastInterface (JNIEnv * env, hysocket_t hysocketP);
void setSendBufferSize (JNIEnv * env, hysocket_t hysocketP, jobject optVal);
jobject getReceiveBufferSize (JNIEnv * env, hysocket_t hysocketP);
jobject getMcastInterface (JNIEnv * env, hysocket_t hysocketP);
void mcastAddMembership (JNIEnv * env, hysocket_t hysocketP, jobject optVal,
       BOOLEAN ignoreIF);
void setIntegerSocketOption (JNIEnv * env, hysocket_t hysocketP, int level,
           int option, jobject optVal);
jobject getSendBufferSize (JNIEnv * env, hysocket_t hysocketP);
void mcastDropMembership (JNIEnv * env, hysocket_t hysocketP, jobject optVal,
        BOOLEAN ignoreIF);
void setReuseAddrAndReusePort (JNIEnv * env, hysocket_t hysocketP,
             jobject optVal);
void setBoolSocketOption (JNIEnv * env, hysocket_t hysocketP, int level,
        int option, jobject optVal);
void setMcastInterface (JNIEnv * env, hysocket_t hysocketP, jobject optVal);
jobject getIntegerValue (JNIEnv * env, hysocket_t hysocketP, int level,
       int option);
void setLingerOption (JNIEnv * env, hysocket_t hysocketP, jobject optVal);
jobject getBooleanValue (JNIEnv * env, hysocket_t hysocketP, int level,
       int option);
jobject getLingerOption (JNIEnv * env, hysocket_t hysocketP);
void setReceiveBufferSize (JNIEnv * env, hysocket_t hysocketP,
         jobject optVal);
void createSocket (JNIEnv* env, jobject thisObjFD, int sockType,
                   jboolean preferIPv4Stack);
I_32 pollSelectRead (JNIEnv * env, jobject fileDescriptor, jint timeout,
                     BOOLEAN poll);

#endif /* socket_h */

