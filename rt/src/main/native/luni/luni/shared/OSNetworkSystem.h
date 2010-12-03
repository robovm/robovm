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
/* Header for class org_apache_harmony_luni_platform_OSNetworkSystem */
#define SOCKET_CONNECT_STEP_START 0
#define SOCKET_CONNECT_STEP_CHECK 1
#define SOCKET_OP_NONE 0
#define SOCKET_OP_READ 1
#define SOCKET_OP_WRITE 2
#define SOCKET_READ_WRITE 3

// const of ICMP value
#define ICMP_ECHO_REPLY 0  
#define ICMP_DEST_UNREACH 3
#define ICMP_TTL_EXPIRE 11
#define ICMP_ECHO_REQUEST 8
#define ICMP_SIZE 8
#define PACKET_SIZE 1024

// IP header
struct IPHeader {
    unsigned char h_len:4;           // default 4
    unsigned char version:4;         // that is IP v4
    unsigned char tos;               // Type of service
    unsigned short total_len;       // Length of the packet in dwords
    unsigned short ident;           // unique identifier
    unsigned short flags;           
    unsigned char ttl;               // Time to live
    unsigned char proto;             // Protocol number (TCP, UDP etc)
    unsigned short checksum;        // IP checksum
    unsigned long source_ip;
    unsigned long dest_ip;
};

// ICMP header
struct ICMPHeader {
    unsigned char type;          // ICMP packet type
    unsigned char code;          // Type sub code
    unsigned short checksum;
    unsigned short id;
    unsigned short seq;
};

#ifndef _Included_org_apache_harmony_luni_platform_OSNetworkSystem
#define _Included_org_apache_harmony_luni_platform_OSNetworkSystem
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    accept
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_accept
  (JNIEnv *, jobject, jobject, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    acceptStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_acceptStreamSocket
  (JNIEnv *, jobject, jobject, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    availableStream
 * Signature: (Ljava/io/FileDescriptor;)I
 * Throws:    java.net.SocketException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_availableStream
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    bind
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_bind
  (JNIEnv *, jobject, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    connect
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;I)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connect
  (JNIEnv *, jobject, jobject, jint, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    connectDatagram
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectDatagram
  (JNIEnv *, jobject, jobject, jint, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    connectStreamWithTimeoutSocket
 * Signature: (Ljava/io/FileDescriptor;IIILjava/net/InetAddress;)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectStreamWithTimeoutSocket
  (JNIEnv *, jobject, jobject, jint, jint, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    connectWithTimeout
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;IILjava/lang/Long;)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectWithTimeout
  (JNIEnv *, jobject, jobject, jint, jint, jobject, jint, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    createDatagramSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createDatagramSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    createServerStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createServerStreamSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    createStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createStreamSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    disconnectDatagram
 * Signature: (Ljava/io/FileDescriptor;)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_disconnectDatagram
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getHostByAddr
 * Signature: ([B)Ljava/net/InetAddress;
 * Throws:    java.net.UnknownHostException
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByAddr
  (JNIEnv *, jobject, jbyteArray);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getHostByName
 * Signature: (Ljava/lang/String;Z)Ljava/net/InetAddress;
 * Throws:    java.net.UnknownHostException
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByName
  (JNIEnv *, jobject, jstring, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getSocketFlags
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketFlags
  (JNIEnv *, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getSocketLocalAddress
 * Signature: (Ljava/io/FileDescriptor;Z)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalAddress
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getSocketLocalPort
 * Signature: (Ljava/io/FileDescriptor;Z)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalPort
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    getSocketOption
 * Signature: (Ljava/io/FileDescriptor;I)Ljava/lang/Object;
 * Throws:    java.net.SocketException
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketOption
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    inheritedChannel
 * Signature: ()Ljava/nio/channels/Channel;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_inheritedChannel
  (JNIEnv *, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    isReachableByICMPImpl
 * Signature: (Ljava/net/InetAddress;Ljava/net/InetAddress;II)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_isReachableByICMPImpl
  (JNIEnv *, jobject, jobject, jobject, jint, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    listenStreamSocket
 * Signature: (Ljava/io/FileDescriptor;I)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_listenStreamSocket
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    oneTimeInitializationImpl
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_oneTimeInitializationImpl
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    peekDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_peekDatagram
  (JNIEnv *, jobject, jobject, jobject, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    read
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_read
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    readDirect
 * Signature: (Ljava/io/FileDescriptor;JII)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_readDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    receiveDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagram
  (JNIEnv *, jobject, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    receiveDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramDirect
  (JNIEnv *, jobject, jobject, jobject, jlong, jint, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    receiveStream
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveStream
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    recvConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagram
  (JNIEnv *, jobject, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    recvConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramDirect
  (JNIEnv *, jobject, jobject, jobject, jlong, jint, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_selectImpl
  (JNIEnv *, jobject, jobjectArray, jobjectArray, jint, jint, jintArray, jlong);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagram
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIZ)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIIZILjava/net/InetAddress;)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendDatagram2
 * Signature: (Ljava/io/FileDescriptor;[BIIILjava/net/InetAddress;)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram2
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIIZILjava/net/InetAddress;)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    sendUrgentData
 * Signature: (Ljava/io/FileDescriptor;B)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendUrgentData
  (JNIEnv *, jobject, jobject, jbyte);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    setInetAddress
 * Signature: (Ljava/net/InetAddress;[B)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setInetAddress
  (JNIEnv *, jobject, jobject, jbyteArray);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    setNonBlocking
 * Signature: (Ljava/io/FileDescriptor;Z)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setNonBlocking
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    setSocketOption
 * Signature: (Ljava/io/FileDescriptor;ILjava/lang/Object;)V
 * Throws:    java.net.SocketException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setSocketOption
  (JNIEnv *, jobject, jobject, jint, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    shutdownInput
 * Signature: (Ljava/io/FileDescriptor;)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownInput
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    shutdownOutput
 * Signature: (Ljava/io/FileDescriptor;)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownOutput
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    socketClose
 * Signature: (Ljava/io/FileDescriptor;)V
 * Throws:    java.io.IOException
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketClose
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    supportsUrgentData
 * Signature: (Ljava/io/FileDescriptor;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_supportsUrgentData
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    write
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_write
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint);

/*
 * Class:     org.apache.harmony.luni.platform.OSNetworkSystem
 * Method:    writeDirect
 * Signature: (Ljava/io/FileDescriptor;JI)I
 * Throws:    java.io.IOException
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeDirect
  (JNIEnv *, jobject, jobject, jlong, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    writev
 * Signature: (Ljava/io/FileDescriptor;[Ljava/lang/Object;[I[II)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_writev
  (JNIEnv *, jobject, jobject, jobjectArray, jintArray, jintArray, jint);

#ifdef __cplusplus
}
#endif
#endif
