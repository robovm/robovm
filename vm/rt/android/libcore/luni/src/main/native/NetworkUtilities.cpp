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

#define LOG_TAG "NetworkUtilities"

#include "NetworkUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedLocalRef.h"

#include <arpa/inet.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>

jobject sockaddrToInetAddress(JNIEnv* env, const sockaddr_storage* ss, jint* port) {
    // Convert IPv4-mapped IPv6 addresses to IPv4 addresses.
    // The RI states "Java will never return an IPv4-mapped address".
    sockaddr_storage tmp;
    memset(&tmp, 0, sizeof(tmp));
    const sockaddr_in6* sin6 = reinterpret_cast<const sockaddr_in6*>(ss);
    if (ss->ss_family == AF_INET6 && IN6_IS_ADDR_V4MAPPED(&sin6->sin6_addr)) {
        // Copy the IPv6 address into the temporary sockaddr_storage.
        memcpy(&tmp, ss, sizeof(tmp));
        // Unmap it into an IPv4 address.
        sockaddr_in* sin = reinterpret_cast<sockaddr_in*>(&tmp);
        sin->sin_family = AF_INET;
        sin->sin_port = sin6->sin6_port;
        memcpy(&sin->sin_addr.s_addr, &sin6->sin6_addr.s6_addr[12], 4);
        // Fall through into the regular conversion using the unmapped address.
        ss = &tmp;
    }

    const void* rawAddress;
    size_t addressLength;
    int sin_port;
    int scope_id = 0;
    if (ss->ss_family == AF_INET) {
        const sockaddr_in* sin = reinterpret_cast<const sockaddr_in*>(ss);
        rawAddress = &sin->sin_addr.s_addr;
        addressLength = 4;
        sin_port = ntohs(sin->sin_port);
    } else if (ss->ss_family == AF_INET6) {
        const sockaddr_in6* sin6 = reinterpret_cast<const sockaddr_in6*>(ss);
        rawAddress = &sin6->sin6_addr.s6_addr;
        addressLength = 16;
        sin_port = ntohs(sin6->sin6_port);
        scope_id = sin6->sin6_scope_id;
    } else {
        // We can't throw SocketException. We aren't meant to see bad addresses, so seeing one
        // really does imply an internal error.
        jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException",
                "sockaddrToInetAddress bad ss_family: %i", ss->ss_family);
        return NULL;
    }
    if (port != NULL) {
        *port = sin_port;
    }

    ScopedLocalRef<jbyteArray> byteArray(env, env->NewByteArray(addressLength));
    if (byteArray.get() == NULL) {
        return NULL;
    }
    env->SetByteArrayRegion(byteArray.get(), 0, addressLength,
            reinterpret_cast<const jbyte*>(rawAddress));

    static jmethodID getByAddressMethod = env->GetStaticMethodID(JniConstants::inetAddressClass,
            "getByAddress", "(Ljava/lang/String;[BI)Ljava/net/InetAddress;");
    if (getByAddressMethod == NULL) {
        return NULL;
    }
    return env->CallStaticObjectMethod(JniConstants::inetAddressClass, getByAddressMethod,
            NULL, byteArray.get(), scope_id);
}

static bool inetAddressToSockaddr(JNIEnv* env, jobject inetAddress, int port, sockaddr_storage* ss, bool map) {
    memset(ss, 0, sizeof(*ss));

    if (inetAddress == NULL) {
        jniThrowNullPointerException(env, NULL);
        return false;
    }

    // Get the address family.
    static jfieldID familyFid = env->GetFieldID(JniConstants::inetAddressClass, "family", "I");
    ss->ss_family = env->GetIntField(inetAddress, familyFid);
    if (ss->ss_family == AF_UNSPEC) {
        return true; // Job done!
    }

    // Check this is an address family we support.
    if (ss->ss_family != AF_INET && ss->ss_family != AF_INET6) {
        jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException",
                "inetAddressToSockaddr bad family: %i", ss->ss_family);
        return false;
    }

    // Get the byte array that stores the IP address bytes in the InetAddress.
    static jfieldID bytesFid = env->GetFieldID(JniConstants::inetAddressClass, "ipaddress", "[B");
    ScopedLocalRef<jbyteArray> addressBytes(env, reinterpret_cast<jbyteArray>(env->GetObjectField(inetAddress, bytesFid)));
    if (addressBytes.get() == NULL) {
        jniThrowNullPointerException(env, NULL);
        return false;
    }

    // We use AF_INET6 sockets, so we want an IPv6 address (which may be a IPv4-mapped address).
    sockaddr_in6* sin6 = reinterpret_cast<sockaddr_in6*>(ss);
    sin6->sin6_port = htons(port);
    if (ss->ss_family == AF_INET6) {
        // IPv6 address. Copy the bytes...
        jbyte* dst = reinterpret_cast<jbyte*>(&sin6->sin6_addr.s6_addr);
        env->GetByteArrayRegion(addressBytes.get(), 0, 16, dst);
        // ...and set the scope id...
        static jfieldID scopeFid = env->GetFieldID(JniConstants::inet6AddressClass, "scope_id", "I");
        sin6->sin6_scope_id = env->GetIntField(inetAddress, scopeFid);
        return true;
    }

    // Deal with Inet4Address instances.
    if (map) {
        // We should represent this Inet4Address as an IPv4-mapped IPv6 sockaddr_in6.
        // Change the family...
        sin6->sin6_family = AF_INET6;
        // Copy the bytes...
        jbyte* dst = reinterpret_cast<jbyte*>(&sin6->sin6_addr.s6_addr[12]);
        env->GetByteArrayRegion(addressBytes.get(), 0, 4, dst);
        // INADDR_ANY and in6addr_any are both all-zeros...
        if (!IN6_IS_ADDR_UNSPECIFIED(&sin6->sin6_addr)) {
            // ...but all other IPv4-mapped addresses are ::ffff:a.b.c.d, so insert the ffff...
            memset(&(sin6->sin6_addr.s6_addr[10]), 0xff, 2);
        }
    } else {
        // We should represent this Inet4Address as an IPv4 sockaddr_in.
        sockaddr_in* sin = reinterpret_cast<sockaddr_in*>(ss);
        sin->sin_port = htons(port);
        jbyte* dst = reinterpret_cast<jbyte*>(&sin->sin_addr.s_addr);
        env->GetByteArrayRegion(addressBytes.get(), 0, 4, dst);
    }
    return true;
}

bool inetAddressToSockaddrVerbatim(JNIEnv* env, jobject inetAddress, int port, sockaddr_storage* ss) {
    return inetAddressToSockaddr(env, inetAddress, port, ss, false);
}

bool inetAddressToSockaddr(JNIEnv* env, jobject inetAddress, int port, sockaddr_storage* ss) {
    return inetAddressToSockaddr(env, inetAddress, port, ss, true);
}

bool setBlocking(int fd, bool blocking) {
    int flags = fcntl(fd, F_GETFL);
    if (flags == -1) {
        return false;
    }

    if (!blocking) {
        flags |= O_NONBLOCK;
    } else {
        flags &= ~O_NONBLOCK;
    }

    int rc = fcntl(fd, F_SETFL, flags);
    return (rc != -1);
}
