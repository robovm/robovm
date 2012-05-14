/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "jni.h"
#include <sys/socket.h>

// Convert from sockaddr_storage to InetAddress and an optional int port.
jobject sockaddrToInetAddress(JNIEnv* env, const sockaddr_storage* ss, int* port);

// Convert from InetAddress to sockaddr_storage. An Inet4Address will be converted to
// an IPv4-mapped AF_INET6 sockaddr_in6. This is what you want if you're about to perform an
// operation on a socket, since all our sockets are AF_INET6.
bool inetAddressToSockaddr(JNIEnv* env, jobject inetAddress, int port, sockaddr_storage* ss);

// Convert from InetAddress to sockaddr_storage. An Inet6Address will be converted to
// a sockaddr_in6 while an Inet4Address will be converted to a sockaddr_in. This is
// probably only useful for getnameinfo(2), where we'll be presenting the result to
// the user and the user may actually care whether the original address was pure IPv4
// or an IPv4-mapped IPv6 address, and for the MCAST_JOIN_GROUP socket option.
bool inetAddressToSockaddrVerbatim(JNIEnv* env, jobject inetAddress, int port, sockaddr_storage* ss);



// Changes 'fd' to be blocking/non-blocking. Returns false and sets errno on failure.
// @Deprecated - use IoUtils.setBlocking
bool setBlocking(int fd, bool blocking);
