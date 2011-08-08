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

#if !defined(nethelp_h)
#define nethelp_h
#include "iohelp.h"
#include "exceptions.h"
#include "hysocket.h"
#define JAVASOCKOPT_TCP_NODELAY 1
#define JAVASOCKOPT_SO_REUSEADDR 4
#define JAVASOCKOPT_MCAST_ADD_MEMBERSHIP 19
#define JAVASOCKOPT_MCAST_DROP_MEMBERSHIP 20
#define JAVASOCKOPT_MCAST_TTL 17
#define JAVASOCKOPT_SO_KEEPALIVE 8
#define JAVASOCKOPT_MCAST_TIME_TO_LIVE 10       /* Currently unused */
#define JAVASOCKOPT_SO_BROADCAST 32
#define JAVASOCKOPT_SO_BINDADDR 15
#define JAVASOCKOPT_MCAST_INTERFACE 16
#define JAVASOCKOPT_SO_LINGER 128
#define JAVASOCKOPT_SO_REUSEPORT 512
#define JAVASOCKOPT_SO_SNDBUF 4097
#define JAVASOCKOPT_SO_RCVBUF 4098
#define JAVASOCKOPT_SO_RCVTIMEOUT  4102
#define JAVASOCKOPT_IP_TOS 3
#define JAVASOCKOPT_IP_MULTICAST_LOOP 18
#define JAVASOCKOPT_IP_MULTICAST_IF2 31
#define JAVASOCKOPT_SO_OOBINLINE  4099
#define JAVASOCKOPT_REUSEADDR_AND_REUSEPORT  10001


void *
getJavaIoFileDescriptorContentsAsAPointer (JNIEnv * env, jobject fd);
void throwJavaNetBindException (JNIEnv * env, I_32 errorNumber);
jobject newJavaNetInetAddressGenericBS (JNIEnv * env, jbyte * address,
          U_32 length, const char *hostName,
          U_32 scope_id);
void throwJavaNetUnknownHostException (JNIEnv * env, I_32 errorNumber);
jobject newJavaNetInetAddressGenericB (JNIEnv * env, jbyte * address,
               U_32 length, U_32 scope_id);
jobject newJavaLangByte (JNIEnv * env, U_8 aByte);
U_8 byteValue (JNIEnv * env, jobject aByte);
I_32 intValue (JNIEnv * env, jobject anInteger);
void throwJavaNetPortUnreachableException (JNIEnv * env, I_32 errorNumber);
jobject newJavaByteArray (JNIEnv * env, jbyte * bytes, jint length);
jobjectArray createAliasArrayFromAddrinfo (JNIEnv * env,
             hyaddrinfo_t addresses,
             const char *hName);
BOOLEAN booleanValue (JNIEnv * env, jobject aBoolean);
BOOLEAN harmony_supports_ipv6 (JNIEnv * env);
jobject newJavaLangInteger (JNIEnv * env, I_32 anInt);
BOOLEAN preferIPv4Stack (JNIEnv * env);
char *netLookupErrorString (JNIEnv * env, I_32 anErrorNum);
void netInitializeIDCaches (JNIEnv * env, jboolean ipv6_support);
jobject newJavaLangBoolean (JNIEnv * env, BOOLEAN aBool);
void throwJavaLangIllegalArgumentException (JNIEnv * env, I_32 errorNumber);
void netGetJavaNetInetAddressValue (JNIEnv * env, jobject anInetAddress,
            U_8 * buffer, U_32 * length);
void throwJavaIoInterruptedIOException (JNIEnv * env, I_32 errorNumber);
void throwJavaNetSocketTimeoutException (JNIEnv * env, I_32 errorNumber);
void callThreadYield (JNIEnv * env);
void throwJavaNetConnectException (JNIEnv * env, I_32 errorNumber);
void netGetJavaNetInetAddressScopeId (JNIEnv * env, jobject anInetAddress,
              U_32 * scope_id);
BOOLEAN preferIPv6Addresses (JNIEnv * env);
jobjectArray createAliasArray (JNIEnv * env, jbyte ** addresses,
             I_32 * family, U_32 count, const char *hName,
             U_32 * scope_id_array);
void throwJavaNetSocketException (JNIEnv * env, I_32 errorNumber);
I_32 netGetSockAddr (JNIEnv * env, jobject fileDescriptor,
         hysockaddr_t sockaddrP, jboolean preferIPv6Addresses);
         
void
setJavaIoFileDescriptorContents (JNIEnv * env, jobject fd,
                                          void *value);

void setSocketAddressContent(JNIEnv * env, jclass channel_class, jobject channel_object,jbyte * address);

void setFDContent(JNIEnv * env, jclass channel_class, jobject channel_object, void * sock);

void setJavaNioChannelsLocalPort(JNIEnv * env,jclass channel_class,jobject channel_object,int port);

jobject getJavaNioChannelsSocketChannelImplObj(JNIEnv * env, jclass channel_class);

jobject getJavaNioChannelsDatagramChannelImplObj(JNIEnv * env);

void setServerSocketLocalAddressContent(JNIEnv * env, jclass socketImpl_class, jobject socketImpl_object, jbyte * localAddr);

void setSocketLocalAddressContent(JNIEnv * env, jclass channel_class, jobject channel_object,jbyte * address);

jint ioh_readbytesImpl (JNIEnv * env, jobject recv, jbyteArray buffer,
                        jint offset, jint count, IDATA descriptor);
void ioh_writebytesImpl (JNIEnv * env, jobject recv, jbyteArray buffer,
                         jint offset, jint count, IDATA descriptor);
void new_ioh_close (JNIEnv * env, jobject recv, jfieldID fdFID);
char *ioLookupErrorString (JNIEnv * env, I_32 anErrorNum);

#endif /* nethelp_h */
