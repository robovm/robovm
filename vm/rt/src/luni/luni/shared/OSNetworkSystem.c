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

#include "OSNetworkSystem.h"
#include "vmi.h"
#include "nethelp.h"
#include "helpers.h"
#include "hysock.h"
#include "socket.h"
#include "harmonyglob.h"

#if defined(WIN32) || defined(WIN64)
#if !defined(ECONNABORTED)
#define ECONNABORTED WSAECONNABORTED
#endif
#endif

void setSocketImplPort(JNIEnv * env, jobject socketImpl, U_16 hPort);
void setSocketImplAddress(JNIEnv * env, jobject socketImpl,
                          jobject anInetAddress);
void updateSocket(JNIEnv * env, hysockaddr_t sockaddrP, hysocket_t socketNew,
                  jobject socketImpl, jobject fileDescriptorSocketImpl);
void *getConnectContext(JNIEnv * env, jobject longclass);
void setConnectContext(JNIEnv * env, jobject longclass, U_8 * context);

void setDatagramPacketAddress(JNIEnv * env, jobject datagramPacket,
                              jobject anInetAddress);
void setDatagramPacketPort(JNIEnv * env, jobject datagramPacket, U_16 hPort);
void updateAddress(JNIEnv * env, hysockaddr_t sockaddrP,
                   jobject senderAddress);
void updatePacket(JNIEnv * env, hysockaddr_t sockaddrP,
                  jobject datagramPacket, I_32 bytesRead);
void setDatagramPacketLength(JNIEnv * env, jobject datagramPacket,
                             I_32 length);
int
selectRead(JNIEnv * env, hysocket_t hysocketP, I_32 uSecTime, BOOLEAN accept);

/**
 * A helper method, to set the remote address into the DatagramPacket.
 *
 * @param env             pointer to the JNI library
 * @param datagramPacket  pointer to the java DatagramPacket object to update
 * @param anInetAddress   pointer to the java InetAddress to update the packet with
 *
 */
void
setDatagramPacketAddress(JNIEnv * env, jobject datagramPacket,
                         jobject anInetAddress)
{
  jfieldID fid = HARMONY_CACHE_GET(env, FID_java_net_DatagramPacket_address);
  (*env)->SetObjectField(env, datagramPacket, fid, anInetAddress);
}

/**
 * A helper method, to set the remote port into the java DatagramPacket.
 *
 * @param env             pointer to the JNI library
 * @param datagramPacket  pointer to the java DatagramPacket object to update
 * @param hPort          the port value to update the packet with, in host order
 */
void
setDatagramPacketPort(JNIEnv * env, jobject datagramPacket, U_16 hPort)
{
  jfieldID fid = HARMONY_CACHE_GET(env, FID_java_net_DatagramPacket_port);
  (*env)->SetIntField(env, datagramPacket, fid, hPort);
}

/**
 * A helper method, to set the data length into a java DatagramPacket.
 *
 * @param env             pointer to the JNI library
 * @param datagramPacket  pointer to the java DatagramPacket object to update
 * @param length          the length value to update the packet with
 */
void
setDatagramPacketLength(JNIEnv * env, jobject datagramPacket, I_32 length)
{
  jfieldID fid = HARMONY_CACHE_GET(env, FID_java_net_DatagramPacket_length);
  (*env)->SetIntField(env, datagramPacket, fid, length);
}

/**
 * A helper method, to update the java DatagramPacket argument. Used after receiving a datagram packet, 
 * to update the DatagramPacket with the network address and port of the sending machine.
 *
 * @param env            pointer to the JNI library
 * @param sockaddrP      pointer to the hysockaddr struct with the sending host address & port
 * @param datagramPacket pointer to the java DatagramPacket object to update
 * @param bytesRead      the bytes read value to update the packet with
 */
void
updatePacket(JNIEnv * env, hysockaddr_t sockaddrP, jobject datagramPacket,
             I_32 bytesRead)
{
  PORT_ACCESS_FROM_ENV(env);
  jobject anInetAddress;
  U_16 nPort;
  U_32 length;
  U_32 scope_id = 0;
  jbyte byte_array[HYSOCK_INADDR6_LEN];
  hysock_sockaddr_address6(sockaddrP, (U_8 *) byte_array, &length, &scope_id);

  nPort = hysock_sockaddr_port(sockaddrP);
  anInetAddress =
    newJavaNetInetAddressGenericB(env, byte_array, length, scope_id);

  setDatagramPacketAddress(env, datagramPacket, anInetAddress);
  setDatagramPacketPort(env, datagramPacket, hysock_ntohs(nPort));
  setDatagramPacketLength(env, datagramPacket, bytesRead);
}

/**
 * A helper method, to set address of the java InetAddress argument.
 *
 * @param env           pointer to the JNI library
 * @param sockaddrP     pointer to the hysockaddr struct containing the network address
 * @param senderAddress pointer to the java InetAddress object to update
 */
void
updateAddress(JNIEnv * env, hysockaddr_t sockaddrP, jobject senderAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte ipv4Addr[16];
  U_32 length;
  U_32 scope_id = 0;
  hysock_sockaddr_address6(sockaddrP, (U_8 *) ipv4Addr, &length, &scope_id);
  (*env)->SetObjectField(env, senderAddress,
                         HARMONY_CACHE_GET(env,
                                           FID_java_net_InetAddress_address),
                         newJavaByteArray(env, ipv4Addr, length));
  if (harmony_supports_ipv6(env) && (scope_id != 0)) {
    jfieldID fid = NULL;
    jclass tempClass = HARMONY_CACHE_GET(env, CLS_java_net_InetAddress);

    fid = (*env)->GetFieldID(env, tempClass, "scope_id", "I");
    if ((*env)->ExceptionCheck(env)) {
      (*env)->ExceptionClear(env);
    } else {
      (*env)->SetIntField(env, senderAddress, fid, scope_id);
    }
  }
}

/**
 * A helper method, to set the connect context to a Long object.
 *
 * @param env       pointer to the JNI library
 * @param longclass Java Long Object
 */
void
setConnectContext(JNIEnv * env, jobject longclass, U_8 * context)
{
  jclass descriptorCLS;
  jfieldID descriptorFID;
  descriptorCLS = (*env)->FindClass(env, "java/lang/Long");
  descriptorFID = (*env)->GetFieldID(env, descriptorCLS, "value", "J");
  (*env)->SetLongField(env, longclass, descriptorFID, (jlong) (IDATA) context);
}

/**
 * A helper method, to get the connect context.
 *
 * @param env       pointer to the JNI library
 * @param longclass Java Long Object
 */
void *
getConnectContext(JNIEnv * env, jobject longclass)
{
  jclass descriptorCLS;
  jfieldID descriptorFID;
  descriptorCLS = (*env)->FindClass(env, "java/lang/Long");
  descriptorFID = (*env)->GetFieldID(env, descriptorCLS, "value", "J");
  return (void *) (IDATA) ((*env)->GetLongField(env, longclass, descriptorFID));
}

/**
 * A helper method, to set the remote address into the socketImpl.
 *
 * @param env           pointer to the JNI library
 * @param socketImpl    pointer to the java SocketImpl object to update
 * @param anInetAddress pointer to the java InetAddress to update the socket with
 */
void
setSocketImplAddress(JNIEnv * env, jobject socketImpl, jobject anInetAddress)
{
  jfieldID fid = HARMONY_CACHE_GET(env, FID_java_net_SocketImpl_address);
  (*env)->SetObjectField(env, socketImpl, fid, anInetAddress);
}

/**
 * A helper method, to set the remote port into the socketImpl.
 *
 * @param env       pointer to the JNI library
 * @param socketImpl pointer to the java SocketImpl object to update 
 * @param hPort       the port number, in host order, to update the socket with
 */
void
setSocketImplPort(JNIEnv * env, jobject socketImpl, U_16 hPort)
{
  jfieldID fid = HARMONY_CACHE_GET(env, FID_java_net_SocketImpl_port);
  (*env)->SetIntField(env, socketImpl, fid, hPort);
}

/**
 * A helper method, to update the java SocketImpl argument.  Used after connecting, to 'link' the 
 * system socket with the java socketImpl and update the address/port fields with the values
 * corresponding to the remote machine. 
 *
 * @param env          pointer to the JNI library
 * @param sockaddrP    pointer to the hysockaddr struct with the remote host address & port
 * @param socketNew    pointer to the new hysocket
 * @param socketImpl   pointer to the new java (connected) socket
 * @param fileDescriptorSocketImpl pointer to the java file descriptor of the socketImpl
 */
void
updateSocket(JNIEnv * env,
             hysockaddr_t sockaddrP, hysocket_t socketNew,
             jobject socketImpl, jobject fileDescriptorSocketImpl)
{
  PORT_ACCESS_FROM_ENV(env);
  U_8 nipAddress[HYSOCK_INADDR6_LEN];
  U_32 length;
  jobject anInetAddress;
  U_16 nPort;
  U_32 scope_id = 0;

  hysock_sockaddr_address6(sockaddrP, nipAddress, &length, &scope_id);
  nPort = hysock_sockaddr_port(sockaddrP);
  anInetAddress =
    newJavaNetInetAddressGenericB(env, (jbyte *) nipAddress, length,
                                  scope_id);

  setJavaIoFileDescriptorContents(env, fileDescriptorSocketImpl, socketNew);
  setSocketImplAddress(env, socketImpl, anInetAddress);
  setSocketImplPort(env, socketImpl, hysock_ntohs(nPort));
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    oneTimeInitializationImpl
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_oneTimeInitializationImpl
  (JNIEnv * env, jclass clazz, jboolean harmony_supports_ipv6)
{
  jfieldID fid;
  jclass lookupClass;
  netInitializeIDCaches(env, harmony_supports_ipv6);

  lookupClass = (*env)->FindClass(env, "java/net/SocketImpl");
  if (!lookupClass)
    return;

  fid =
    (*env)->GetFieldID(env, lookupClass, "address", "Ljava/net/InetAddress;");
  if (!fid)
    return;
  HARMONY_CACHE_SET(env, FID_java_net_SocketImpl_address, fid);

  fid = (*env)->GetFieldID(env, lookupClass, "port", "I");
  if (!fid)
    return;
  HARMONY_CACHE_SET(env, FID_java_net_SocketImpl_port, fid);

  lookupClass = (*env)->FindClass(env, "java/net/DatagramPacket");
  if (!lookupClass)
    return;

  fid =
    (*env)->GetFieldID(env, lookupClass, "address", "Ljava/net/InetAddress;");
  if (!fid)
    return;
  HARMONY_CACHE_SET(env, FID_java_net_DatagramPacket_address, fid);

  fid = (*env)->GetFieldID(env, lookupClass, "length", "I");
  if (!fid)
    return;
  HARMONY_CACHE_SET(env, FID_java_net_DatagramPacket_length, fid);

  fid = (*env)->GetFieldID(env, lookupClass, "port", "I");
  if (!fid)
    return;
  HARMONY_CACHE_SET(env, FID_java_net_DatagramPacket_port, fid);
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_createStreamSocket
  (JNIEnv * env, jobject thiz, jobject thisObjFD, jboolean preferIPv4Stack)
{
  createSocket(env, thisObjFD, HYSOCK_STREAM, preferIPv4Stack);
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createDatagramSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_createDatagramSocket
  (JNIEnv * env, jobject thiz, jobject fd, jboolean preferIPv4Stack)
{
  createSocket(env, fd, HYSOCK_DGRAM, preferIPv4Stack);
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    read
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_read
  (JNIEnv * env, jobject thiz, jobject fd, jbyteArray data, jint offset,
   jint count, jint timeout)
{
  jbyte *message;
  jboolean isCopy = JNI_FALSE;
  jint result;

  /* Get a pointer to the start of the bytearray */
  message = (*env)->GetByteArrayElements (env, data, &isCopy);

  /* Read directly into the byte array */
  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_readDirect
    (env, thiz, fd, (jlong) (IDATA)(message + offset), count, timeout);

  /* If the pointer was to a copy it needs to be released */
  if (isCopy == JNI_TRUE) {
      /* Only copy back if we modified the bytearray data */
      if (0 < result) {
        (*env)->ReleaseByteArrayElements (env, data, message, 0);
      } else {
        (*env)->ReleaseByteArrayElements (env, data, message, JNI_ABORT);
      }
  }

  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    readDirect
 * Signature: (Ljava/io/FileDescriptor;JII)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_readDirect
  (JNIEnv * env, jobject thiz, jobject fd, jlong address,
   jint count, jint timeout)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;
  U_8 *message = (U_8 *)(IDATA)address;
  I_32 result, localCount;

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fd);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  /* A non-zero timeout will first check, and potentially wait, to see if any
   * bytes are available
   */
  if (timeout != 0) {
    result = selectRead(env, hysocketP, timeout * 1000, FALSE);
    if (0 > result) {
      if (result == HYPORT_ERROR_SOCKET_TIMEOUT) {
        return (jint) 0;  // return zero bytes to indicate timeout
      }
      throwJavaNetSocketException(env, result);
      return (jint) 0;  // Unused, exception takes precedence
    }
  }

  /* Limit size of read to 64k bytes */
  localCount = (count < 65536) ? count : 65536;
  result = hysock_read(hysocketP, message, localCount, HYSOCK_NOFLAGS);
  if (0 > result) {
    if (HYPORT_ERROR_SOCKET_WOULDBLOCK == result) {
      /* We were asked to read on a nonblocking socket and there is no data available */
      return (jint) 0;
    }
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }

  /* If no bytes are read, return -1 to signal 'endOfFile' to the Java input stream */
  return (0 == result) ? (jint) -1 : (jint) result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    write
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_write
  (JNIEnv * env, jobject thiz, jobject fd, jbyteArray data,
   jint offset, jint count)
{
  jbyte *message;
  jboolean isCopy = JNI_FALSE;
  jint result;

  /* Get a pointer to the start of the bytearray */
  message = (*env)->GetByteArrayElements (env, data, &isCopy);

  /* Write directly from the byte array */
  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeDirect
    (env, thiz, fd, (jlong) (IDATA)(message + offset), count);


  /* If the pointer was to a copy it needs to be released */
  if (isCopy == JNI_TRUE) {
    (*env)->ReleaseByteArrayElements (env, data, message, JNI_ABORT);
  }

  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    writeDirect
 * Signature: (Ljava/io/FileDescriptor;JII)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeDirect
  (JNIEnv * env, jobject thiz, jobject fd, jlong address, jint count)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message = (jbyte *) (IDATA)address;
  I_32 result;

  hysocket_t socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fd);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  result = hysock_write(socketP, (U_8 *) message, (I_32) count, HYSOCK_NOFLAGS);
  if (0 > result) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;  // Ignored, exception takes precedence
  }

  return (jint) result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setNonBlocking
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_setNonBlocking
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jboolean nonblocking)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t socketP;
  int result;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    // return silently, leave validation in real I/O operation
    return;
  }
  result = hysock_set_nonblocking(socketP, nonblocking);
  if (0 != result) {
    throwJavaNetSocketException(env, result);
  }
  return;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connect
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_connect
  (JNIEnv * env, jobject thiz, jobject fd, jint trafficClass,
   jobject inetAddress, jint remotePort)
{
  PORT_ACCESS_FROM_ENV(env);
  U_8 nAddrBytes[HYSOCK_INADDR6_LEN];
  U_32 length = 0;
  U_16 nPort;
  I_32 result;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  U_32 scope_id = 0;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fd);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return -1;
  }

  netGetJavaNetInetAddressValue(env, inetAddress, nAddrBytes, &length);
  nPort = hysock_htons((U_16) remotePort);

  if (length == HYSOCK_INADDR_LEN) {
    // IPv4
    hysock_sockaddr_init6(&sockaddrP, nAddrBytes, length,
                          HYADDR_FAMILY_AFINET4, nPort, 0, 0, socketP);
  } else {
    // IPv6
    netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
    hysock_sockaddr_init6(&sockaddrP, nAddrBytes, length,
                          HYADDR_FAMILY_AFINET6, nPort,
                          (trafficClass & 0xFF) << 20, scope_id, socketP);
  }

  result = hysock_connect(socketP, &sockaddrP);
  if (0 != result) {
    throwJavaNetConnectException(env, result);
    return result;
  }

  return result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectWithTimeout
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;IILjava/lang/Long;)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectWithTimeout
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jint timeout,
   jint trafficClass, jobject inetAddr, jint port, jint step,
   jobject passContext)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte nAddrBytes[HYSOCK_INADDR6_LEN];
  hysocket_t socketP;
  /*former:jbyte * nAddrBytes;  */
  int length;
  U_16 nPort;
  I_32 result = 0;
  hysockaddr_struct sockaddrP;
  U_32 scope_id = 0;
  U_8 *context = NULL;
  context = getConnectContext(env, passContext);

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);

  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return -1;
  } else {
      /*----------------------- former -----------------------------
      //jbyteArray byte_array = getInetAddrIP(env,inteAddr);  
      //length = (*env)->GetArrayLength (env, byte_array);
      //(*env)->GetByteArrayRegion (env, byte_array, 0, length, nAddrBytes);
      */
    netGetJavaNetInetAddressValue(env, inetAddr, (U_8 *) nAddrBytes,
                                  (U_32 *) & length);
    nPort = hysock_htons((U_16) port);
    hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, length,
                          HYADDR_FAMILY_AFINET4, nPort, 0, scope_id, socketP);

    switch (step) {
    case SOCKET_CONNECT_STEP_START:
      result =
        hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                    HY_PORT_SOCKET_STEP_START, &context);
      break;
    case SOCKET_CONNECT_STEP_CHECK:
      if (timeout == -1) {
            /* Blocking connect, so loop for a reasonable amount of time, then
             * throw a ConnectException if we still fail to connect. This matches
             * the behaviour of the RI with a similar timeout.
             */
            int i = 0;
            for (; i<1000; i++) {
                result = hysock_connect_with_timeout(socketP, &sockaddrP, 100,
                                    HY_PORT_SOCKET_STEP_CHECK, &context);
                if (0 == result) {
                    break;
                }
            }
            if (0 != result) {
                throwJavaNetConnectException(env, result);
                return result;
            }
        } else {
            result = hysock_connect_with_timeout(socketP, &sockaddrP, timeout,
                                    HY_PORT_SOCKET_STEP_CHECK, &context);
        }
      break;
    }

    /* set connext for next call */
    setConnectContext(env, passContext, context);

    if (0 == result) {
      /* connected , so stop here */
      hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                  HY_PORT_SOCKET_STEP_DONE, &context);
    } else if (result != HYPORT_ERROR_SOCKET_NOTCONNECTED) {
      /* can not connect... */
      if ((HYPORT_ERROR_SOCKET_CONNRESET == result) ||
          (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result) ||
          (HYPORT_ERROR_SOCKET_ADDRNOTAVAIL == result) ||
          (HYPORT_ERROR_SOCKET_ADDRINUSE == result) ||
          (HYPORT_ERROR_SOCKET_ENETUNREACH == result) ||
          (HYPORT_ERROR_SOCKET_EACCES == result)) {
        // This wasn't commented out on win.IA32 but I don't
        // see why it shouldn't be the same on both platforms
        //hysock_connect_with_timeout (socketP, &sockaddrP,       
        //0,HY_PORT_SOCKET_STEP_DONE,
        //&context);
        throwJavaNetConnectException(env, result);
      } else {
        hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                    HY_PORT_SOCKET_STEP_DONE, &context);
        throwJavaNetSocketException(env, result);
      }
    }
  }

  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    bind
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_bind
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject inetAddress, jint localPort)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte nlocalAddrBytes[HYSOCK_INADDR6_LEN];
  int length;
  U_16 nPort;
  I_32 result;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  U_32 scope_id = 0;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  } else {
    netGetJavaNetInetAddressValue(env, inetAddress, (U_8 *) nlocalAddrBytes,
                                  (U_32 *) & length);

    nPort = hysock_htons((U_16) localPort);
    if (length == HYSOCK_INADDR6_LEN) {
      netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nlocalAddrBytes, length,
                            HYADDR_FAMILY_AFINET6, nPort, 0, scope_id,
                            socketP);
    } else {
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nlocalAddrBytes, length,
                            HYADDR_FAMILY_AFINET4, nPort, 0, scope_id,
                            socketP);
    }
    result = hysock_bind(socketP, &sockaddrP);
    if (0 != result) {
      throwJavaNetBindException(env, result);
      return;
    }
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    listenStreamSocket
 * Signature: (Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_listenStreamSocket
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jint backlog)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t socketP;
  I_32 result;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }

  result = hysock_listen(socketP, (I_32) backlog);
  if (result < 0) {
    throwJavaNetSocketException(env, result);
    return;
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    supportsUrgentData
 * Signature: (Ljava/io/FileDescriptor;)Z
 */
JNIEXPORT jboolean JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_supportsUrgentData
  (JNIEnv * env, jobject thiz, jobject fileDescriptor)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t socketP;
  I_32 flags = 0;
  I_32 result;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    return FALSE;
  }
  result = hysock_setflag(HYSOCK_MSG_OOB, &flags);
  return !result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendUrgentData
 * Signature: (Ljava/io/FileDescriptor;B)Z
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendUrgentData
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jbyte data)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t socketP;
  I_32 flags = 0;
  I_32 result = 0;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }
  result = hysock_setflag(HYSOCK_MSG_OOB, &flags);
  if (!result) {
    result = hysock_write(socketP, (U_8 *) & data, 1, flags);
  }

  /* Always throw an exception if all the data cannot be sent because Java methods
   * assume all the data will be sent or an error occurs.
   */
  if (result < 0) {
    throwJavaNetSocketException(env, result);
    return;
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectDatagram
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jint remotePort,
   jint trafficClass, jobject inetAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte nAddrBytes[HYSOCK_INADDR6_LEN];
  int length;
  U_16 nPort;
  I_32 result;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  U_32 scope_id = 0;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }

  netGetJavaNetInetAddressValue(env, inetAddress, (U_8 *) nAddrBytes,
                                (U_32 *) & length);

  nPort = hysock_htons((U_16) remotePort);
  if (length == HYSOCK_INADDR_LEN) {
    hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, length,
                          HYADDR_FAMILY_AFINET4, nPort, 0, 0, socketP);
  } else {
    netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
    hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, length,
                          HYADDR_FAMILY_AFINET6, nPort,
                          (trafficClass & 0xFF) << 20, scope_id, socketP);
  }

  result = hysock_connect(socketP, &sockaddrP);
  if (0 != result) {
    throwJavaNetConnectException(env, result);
    return;
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    disconnectDatagram
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_disconnectDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte nAddrBytes[HYSOCK_INADDR6_LEN];
  U_16 nPort = 0;
  I_32 result;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }

  /* the address itself should not matter as the protocol family is AF_UNSPEC.
   * This tells connect to disconnect the Datagram
   */
  memset(nAddrBytes, 0, HYSOCK_INADDR6_LEN);
  hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, HYSOCK_INADDR_LEN,
                        HYADDR_FAMILY_UNSPEC, nPort, 0, 0, socketP);

  /* there is the possiblity of an exception here */
  result = hysock_connect(socketP, &sockaddrP);

  /* will likely need to eat the correct exception here.  Leave as is until we
   * figure out what that exception will be
   */
  if (0 != result) {
    throwJavaNetSocketException(env, result);
    return;
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    peekDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_peekDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject senderAddress, jint timeout)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;
  hysockaddr_struct sockaddrP;
  char msg[1] = { 0 };
  I_32 msgLen = 1;
  I_32 result;
  I_32 flags = 0;
  jint hport;
  jbyte nlocalAddrBytes[HYSOCK_INADDR6_LEN];

  result = pollSelectRead(env, fileDescriptor, timeout, TRUE);
  if (0 > result) {
    return (jint) 0;
  }

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  hysock_sockaddr_init6(&sockaddrP, (U_8 *) nlocalAddrBytes,
                        HYSOCK_INADDR_LEN, HYADDR_FAMILY_AFINET4, 0, 0, 0,
                        hysocketP);

  result = hysock_setflag(HYSOCK_MSG_PEEK, &flags);
  if (0 > result) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }
  result = hysock_readfrom(hysocketP, (U_8 *) msg, msgLen, flags, &sockaddrP);

  /* Note, the msgsize error is acceptable as the read buffer was set to a nominal length.
   * Updating sockaddrP is the purpose of this call.
   */
  if (result < 0 && result != HYPORT_ERROR_SOCKET_MSGSIZE) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }

  updateAddress(env, &sockaddrP, senderAddress);
  hport = (jint) hysock_ntohs(hysock_sockaddr_port(&sockaddrP));
  return hport;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject datagramPacket, jbyteArray data, jint offset, jint msgLength,
   jint timeout, jboolean peek)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message;
  jint result;
  I_32 localCount;

  localCount = (msgLength < 65536) ? msgLength : 65536;
  message = hymem_allocate_memory(localCount);
  if (message == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }

  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramDirect
    (env, thiz, fileDescriptor, datagramPacket, (jlong)(IDATA)message, offset,
     localCount, timeout, peek);

  if (result > 0) {
    (*env)->SetByteArrayRegion(env, data, offset, result, message);
  }
  hymem_free_memory(message);
  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramDirect
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject datagramPacket, jlong address, jint offset, jint msgLength,
   jint timeout, jboolean peek)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message = (jbyte *) (IDATA)address;
  hysocket_t hysocketP;
  hysockaddr_struct sockaddrP;
  I_32 result, localCount;
  I_32 flags = HYSOCK_NOFLAGS;
  jbyte nlocalAddrBytes[HYSOCK_INADDR6_LEN];

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  result = pollSelectRead(env, fileDescriptor, timeout, TRUE);
  //result = selectRead (env,hysocketP, timeout, FALSE);
  if (0 > result) {
    return (jint) 0;
  }

  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  hysock_sockaddr_init6(&sockaddrP, (U_8 *) nlocalAddrBytes,
                        HYSOCK_INADDR_LEN, HYADDR_FAMILY_AFINET4, 0, 0, 0,
                        hysocketP);

  localCount = (msgLength < 65536) ? msgLength : 65536;

  if (peek) {
    result = hysock_setflag(HYSOCK_MSG_PEEK, &flags);
    if (result) {
      throwJavaNetSocketException(env, result);
      return (jint) 0;
    }
  }
  result =
    hysock_readfrom(hysocketP, (U_8 *) message, localCount, flags,
                    &sockaddrP);

  if (result < 0) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }

  if (datagramPacket != NULL) {
    updatePacket(env, &sockaddrP, datagramPacket, result);
  }
  return (jint) result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject datagramPacket, jbyteArray data, jint offset, jint msgLength,
   jint timeout, jboolean peek)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message;
  jint result;
  I_32 localCount;

  /* allocate the buffer into which data will be read */
  localCount = (msgLength < 65536) ? msgLength : 65536;
  message = hymem_allocate_memory(localCount);
  if (message == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }

  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramDirect
    (env, thiz, fileDescriptor, datagramPacket, (jlong)(IDATA)message, offset,
     localCount, timeout, peek);

  if (result > 0) {
    (*env)->SetByteArrayRegion(env, data, offset, result, message);

  }
  hymem_free_memory(message);
  return result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramDirect
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jobject datagramPacket, jlong address, jint offset, jint msgLength,
   jint timeout, jboolean peek)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;
  jbyte *message = (jbyte *) (IDATA)address;
  I_32 result;
  I_32 localCount;
  I_32 flags = HYSOCK_NOFLAGS;

  localCount = (msgLength < 65536) ? msgLength : 65536;
  /* check if there is any data to be read before we go ahead and do the read */
  result = pollSelectRead(env, fileDescriptor, timeout, TRUE);
  if (0 > result) {
    return (jint) 0;
  }

  /* get the handle to the socket */
  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  /* check for peek option, if so set the appropriate flag */
  if (peek) {
    result = hysock_setflag(HYSOCK_MSG_PEEK, &flags);
    if (result) {
      throwJavaNetSocketException(env, result);
      return (jint) 0;
    }
  }

  /* read the data and copy it to the return array, then free the buffer as we
     no longer need it */
  result = hysock_read(hysocketP, (U_8 *) message, localCount, flags);

  if (result < 0) {
    if ((HYPORT_ERROR_SOCKET_CONNRESET == result)
        || (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result)) {
      throwJavaNetPortUnreachableException(env, result);
      return (jint) 0;
    }
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }

  /* Update the packet with the length of data received.  Since we are connected
   * we did not get back an address.  This address is cached within the 
   * PlainDatagramSocket java object and is filled in at the java level.
   */
  if (datagramPacket != NULL) {
    setDatagramPacketLength(env, datagramPacket, result);
  }

  return (jint) result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jbyteArray data,
   jint offset, jint msgLength, jint targetPort, jboolean bindToDevice,
   jint trafficClass, jobject inetAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message;
  jint result = 0;

  message = hymem_allocate_memory(msgLength);
  if (message == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }
  (*env)->GetByteArrayRegion(env, data, offset, msgLength, message);
  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramDirect
    (env, thiz, fileDescriptor, (jlong) (IDATA)message, offset, msgLength,
     targetPort, bindToDevice, trafficClass, inetAddress);

  hymem_free_memory(message);
  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramDirect
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jlong address,
   jint offset, jint msgLength, jint targetPort, jboolean bindToDevice,
   jint trafficClass, jobject inetAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message = (jbyte *) (IDATA)address;
  jbyte nhostAddrBytes[HYSOCK_INADDR6_LEN];
  int length;

  U_16 nPort;
  I_32 result = 0, sent = 0;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  int flags;
  U_32 scope_id = 0;

  netGetJavaNetInetAddressValue(env, inetAddress, (U_8 *) nhostAddrBytes,
                                (U_32 *) & length);
  nPort = hysock_htons((U_16) targetPort);

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (length == HYSOCK_INADDR6_LEN) {
    netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
    hysock_sockaddr_init6(&sockaddrP, (U_8 *) nhostAddrBytes, length,
                          HYADDR_FAMILY_AFINET6, nPort,
                          (trafficClass & 0xFF) << 20, scope_id, socketP);
  } else {
    hysock_sockaddr_init6(&sockaddrP, (U_8 *) nhostAddrBytes, length,
                          HYADDR_FAMILY_AFINET4, nPort, 0, scope_id, socketP);
  }

  flags = HYSOCK_NOFLAGS;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env,
                                sent ==
                                0 ? HYPORT_ERROR_SOCKET_BADSOCKET :
                                HYPORT_ERROR_SOCKET_INTERRUPTED);
    return (jint) 0;
  }

  do {
    result =
      hysock_writeto(socketP, (U_8 *) message + sent, (I_32) msgLength - sent,
                     flags, &sockaddrP);
    if (result < 0)
      break;
    sent += result;
  } while (sent < msgLength);

  if (result < 0) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  } else {
    return (jint) result;
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagram
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jbyteArray data,
   jint offset, jint msgLength, jboolean bindToDevice)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message;
  jint result;
  /* allocate a local buffer into which we will copy the data to be sent and which we will use  
     for the write call   */
  message = hymem_allocate_memory(msgLength);
  if (message == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }
  (*env)->GetByteArrayRegion(env, data, offset, msgLength, message);
  result =
    Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramDirect
    (env, thiz, fileDescriptor, (jlong) (IDATA)message, offset, msgLength,
     bindToDevice);
  /* ok free the buffer and return the length sent */
  hymem_free_memory(message);
  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIZ)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramDirect
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jlong address,
   jint offset, jint msgLength, jboolean bindToDevice)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message = (jbyte *) (IDATA)address;
  I_32 result = 0;
  I_32 sent = 0;
  hysocket_t socketP;
  int flags = HYSOCK_NOFLAGS;

  do {
    /* make sure the socket is still valid */
    socketP =
      (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
    if (!hysock_socketIsValid(socketP)) {
      throwJavaNetSocketException(env,
                                  sent == 0 ?
                                    HYPORT_ERROR_SOCKET_BADSOCKET :
                                    HYPORT_ERROR_SOCKET_INTERRUPTED);
      return (jint) 0;
    }

    /* try to send the next block of data */
    result =
      hysock_write(socketP, (U_8 *) message + sent, (I_32) msgLength - sent,
                   flags);
    if (result < 0) {
      break;
    }
    sent += result;
  } while (sent < msgLength);

#if defined(LINUX)
  if (result < 0) {
    if ((HYPORT_ERROR_SOCKET_CONNRESET == result)
        || (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result)) {
      //throwJavaNetPortUnreachableException (env, result);
      return (jint) 0;
    } else {
      throwJavaNetSocketException(env, result);
      return (jint) 0;
    }
  } else {
    return (jint) result;
  }
#endif

  if (result < 0) {
    if ((HYPORT_ERROR_SOCKET_CONNRESET == result)
        || (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result)) {
      throwJavaNetPortUnreachableException(env, result);
      return (jint) 0;
    } else {
      throwJavaNetSocketException(env, result);
      return (jint) 0;
    }
  } else {
    return (jint) result;
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectStreamWithTimeoutSocket
 * Signature: (Ljava/io/FileDescriptor;IIILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectStreamWithTimeoutSocket
  (JNIEnv * env, jobject thisClz, jobject fileDescriptor, jint remotePort,
   jint timeout, jint trafficClass, jobject inetAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte nAddrBytes[HYSOCK_INADDR6_LEN];
  int length;
  U_16 nPort;
  I_32 result;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  U_8 *context = NULL;
  I_32 remainingTimeout = timeout;
  I_32 passedTimeout = 0;
  UDATA finishTime = 0;
  BOOLEAN hasTimeout = timeout > 0;
  U_32 scope_id = 0;

  /* if a timeout was specified calculate the finish time value */
  if (hasTimeout) {
    finishTime = hytime_msec_clock() + (UDATA) timeout;
  }

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  } else {
    netGetJavaNetInetAddressValue(env, inetAddress, (U_8 *) nAddrBytes,
                                  (U_32 *) & length);
    nPort = hysock_htons((U_16) remotePort);
    if (length == HYSOCK_INADDR_LEN) {
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, length,
                            HYADDR_FAMILY_AFINET4, nPort, 0, scope_id,
                            socketP);
    } else {
      netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nAddrBytes, length,
                            HYADDR_FAMILY_AFINET6, nPort,
                            (trafficClass & 0xFF) << 20, scope_id, socketP);
    }

    result =
      hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                  HY_PORT_SOCKET_STEP_START, &context);
    if (0 == result) {
      /* ok we connected right away so we are done */
      hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                  HY_PORT_SOCKET_STEP_DONE, &context);
      return;
    } else if (result != HYPORT_ERROR_SOCKET_NOTCONNECTED) {
      /* we got an error other than NOTCONNECTED so we cannot continue */
      if ((HYPORT_ERROR_SOCKET_CONNRESET == result) ||
          (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result) ||
          (HYPORT_ERROR_SOCKET_ADDRNOTAVAIL == result) ||
          (HYPORT_ERROR_SOCKET_ADDRINUSE == result) ||
          (HYPORT_ERROR_SOCKET_ENETUNREACH == result) ||
          (HYPORT_ERROR_SOCKET_EACCES == result)) {
        hysock_connect_with_timeout(socketP, &sockaddrP,
                                    remainingTimeout,
                                    HY_PORT_SOCKET_STEP_DONE, &context);
        throwJavaNetConnectException(env, result);
        return;
      } else {
        hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                    HY_PORT_SOCKET_STEP_DONE, &context);
        throwJavaNetSocketException(env, result);
        return;
      }
    }

    while (HYPORT_ERROR_SOCKET_NOTCONNECTED == result) {
      passedTimeout = remainingTimeout;

      /* ok now try and connect.  Depending on the platform this may sleep for up to passedTimeout milliseconds */
      result =
        hysock_connect_with_timeout(socketP, &sockaddrP, passedTimeout,
                                    HY_PORT_SOCKET_STEP_CHECK, &context);

      /* now check if the socket is still connected.  Do it here as some platforms seem to think they 
       * are connected if the socket is closed on them. */
      socketP =
        getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
      if (!hysock_socketIsValid(socketP)) {
        hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                    HY_PORT_SOCKET_STEP_DONE, &context);
        throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
        return;
      }

      /* check if we are now connected, if so we can finish the process and return */
      if (0 == result) {
        hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                    HY_PORT_SOCKET_STEP_DONE, &context);
        return;
      }

      /* if the error is HYPORT_ERROR_SOCKET_NOTCONNECTED then we have not yet connected and we may not be
         done yet */
      if (HYPORT_ERROR_SOCKET_NOTCONNECTED == result) {
        /* check if the timeout has expired */
        if (hasTimeout) {
          remainingTimeout = finishTime - hytime_msec_clock();
          if (remainingTimeout <= 0) {
            hysock_connect_with_timeout(socketP, &sockaddrP, 0,
                                        HY_PORT_SOCKET_STEP_DONE, &context);
            throwJavaNetSocketTimeoutException(env, result);
            return;
          }
        } else {
          remainingTimeout = 100;
        }
      } else {
        if ((HYPORT_ERROR_SOCKET_CONNRESET == result) ||
            (HYPORT_ERROR_SOCKET_CONNECTION_REFUSED == result) ||
            (HYPORT_ERROR_SOCKET_ADDRNOTAVAIL == result) ||
            (HYPORT_ERROR_SOCKET_ADDRINUSE == result) ||
            (HYPORT_ERROR_SOCKET_ENETUNREACH == result) ||
            (HYPORT_ERROR_SOCKET_EACCES == result)) {
          hysock_connect_with_timeout(socketP, &sockaddrP,
                                      remainingTimeout,
                                      HY_PORT_SOCKET_STEP_DONE, &context);
          throwJavaNetConnectException(env, result);
          return;
        } else {
          hysock_connect_with_timeout(socketP, &sockaddrP,
                                      remainingTimeout,
                                      HY_PORT_SOCKET_STEP_DONE, &context);
          throwJavaNetSocketException(env, result);
          return;
        }
      }
    }
  }
}


JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram2
  (JNIEnv * env, jclass thiz, jobject fileDescriptor, jbyteArray data,
   jint offset, jint msgLength, jint targetPort, jobject inetAddress)
{
  PORT_ACCESS_FROM_ENV(env);
  jbyte *message;
  jbyte nhostAddrBytes[HYSOCK_INADDR6_LEN];
  U_16 nPort;
  I_32 result = 0, sent = 0;
  hysocket_t socketP;
  hysockaddr_struct sockaddrP;
  int length;
  U_32 scope_id = 0;

  if (inetAddress != NULL) {
    netGetJavaNetInetAddressValue(env, inetAddress, (U_8 *) nhostAddrBytes,
                                  (U_32 *) & length);

    socketP =
      (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
    nPort = hysock_htons((U_16) targetPort);
    if (length == HYSOCK_INADDR_LEN) {
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nhostAddrBytes, length,
                            HYPROTOCOL_FAMILY_INET4, nPort, 0, scope_id,
                            socketP);
    } else {
      netGetJavaNetInetAddressScopeId(env, inetAddress, &scope_id);
      hysock_sockaddr_init6(&sockaddrP, (U_8 *) nhostAddrBytes, length,
                            HYPROTOCOL_FAMILY_INET6, nPort, 0, scope_id,
                            socketP);
    }
  }

  message = hymem_allocate_memory(msgLength);
  if (message == NULL) {
    throwNewOutOfMemoryError(env, "");
    return 0;
  }
  (*env)->GetByteArrayRegion(env, data, offset, msgLength, message);
  while (sent < msgLength) {
    socketP =
      (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env,
                                                             fileDescriptor);
    if (!hysock_socketIsValid(socketP)) {
      hymem_free_memory(message);
      throwJavaNetSocketException(env,
                                  sent ==
                                  0 ? HYPORT_ERROR_SOCKET_BADSOCKET :
                                  HYPORT_ERROR_SOCKET_INTERRUPTED);
      return (jint) 0;
    }
    result =
      hysock_writeto(socketP, (U_8 *) message + sent,
                     (I_32) msgLength - sent, HYSOCK_NOFLAGS, &sockaddrP);
    if (result < 0)
      break;
    sent += result;
  }
  hymem_free_memory(message);
  /**
   * We should always throw an exception if all the data cannot be sent because Java methods
   * assume all the data will be sent or an error occurs.
   */
  if (result < 0) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  } else {
    return (jint) sent;
  }
}

/*
 * Deprecated.  Use Java_org_apache_harmony_luni_platform_OSNetworkSystem_read
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveStream
  (JNIEnv * env, jobject thiz, jobject fileDescriptor, jbyteArray data,
   jint offset, jint count, jint timeout)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;
  jbyte *message;
  I_32 result, localCount;

/* TODO: ARRAY PINNING */
#define INTERNAL_RECEIVE_BUFFER_MAX 2048
  U_8 internalBuffer[INTERNAL_RECEIVE_BUFFER_MAX];

  result = pollSelectRead(env, fileDescriptor, timeout, TRUE);
  if (0 > result) {
    return (jint) 0;
  }

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  localCount = (count < 65536) ? count : 65536;

  if (localCount > INTERNAL_RECEIVE_BUFFER_MAX) {
    message = hymem_allocate_memory(localCount);
    if (message == NULL) {
      throwNewOutOfMemoryError(env, "");
      return 0;
    }
  } else {
    message = (jbyte *) internalBuffer;
  }

  result =
    hysock_read(hysocketP, (U_8 *) message, localCount, HYSOCK_NOFLAGS);
  if (result > 0)
    (*env)->SetByteArrayRegion(env, data, offset, result, message);

  if (((U_8 *) message) != internalBuffer) {
    hymem_free_memory(message);
  }
#undef INTERNAL_RECEIVE_BUFFER_MAX

  /* If no bytes are read, return -1 to signal 'endOfFile' to the Java input stream */
  if (0 < result) {
    return (jint) result;
  } else if (0 == result) {
    return (jint) -1;
  } else {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownInput
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownInput
  (JNIEnv * env, jobject thiz, jobject fd)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  hysocket_t socketP;

  socketP = (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env, fd);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }
  result = hysock_shutdown_input(socketP);
  if (0 != result) {
    throwJavaNetSocketException(env, result);
    return;
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownOutput
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownOutput
  (JNIEnv * env, jobject thiz, jobject fd)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  hysocket_t socketP;

  socketP = (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env, fd);
  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }
  result = hysock_shutdown_output(socketP);
  if (0 != result) {
    throwJavaNetSocketException(env, result);
    return;
  }
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    acceptStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_acceptStreamSocket
  (JNIEnv * env, jobject thiz, jobject fdServer, jobject socketImpl,
   jobject fdSocketImpl, jint timeout)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  hysocket_t socketS, socketNew;
  hysockaddr_struct sockaddrP;
  jbyte nlocalAddrBytes[HYSOCK_INADDR6_LEN];

  select_accept:
  result = pollSelectRead(env, fdServer, timeout, TRUE);
  if (0 > result) {
    return;
  }

  socketS = getJavaIoFileDescriptorContentsAsAPointer(env, fdServer);
  if (!hysock_socketIsValid(socketS)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }

  hysock_sockaddr_init6(&sockaddrP, (U_8 *) nlocalAddrBytes,
                        HYSOCK_INADDR_LEN, HYADDR_FAMILY_AFINET4, 0, 0, 0,
                        socketS);

  result = hysock_accept(socketS, &sockaddrP, &socketNew);
  if (0 != result) {
    // repeat accept if the server was reset
    if (errno == ECONNABORTED) {
      goto select_accept;
    } else {
      throwJavaNetSocketException(env, result);
      return;
    }
  }

  updateSocket(env, &sockaddrP, socketNew, socketImpl, fdSocketImpl);
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalAddress
 * Signature: (Ljava/io/FileDescriptor;Z)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalAddress
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jboolean preferIPv6Addresses)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  hysockaddr_struct sockaddrP;
  jbyte byte_array[HYSOCK_INADDR6_LEN];
  U_32 length;
  U_32 scope_id = 0;
  memset(byte_array, 0, HYSOCK_INADDR6_LEN);

  result =
    netGetSockAddr(env, fileDescriptor, &sockaddrP, preferIPv6Addresses);
  if (0 != result) {
    return newJavaNetInetAddressGenericB(env, byte_array,
                                         HYSOCK_INADDR_LEN, 0);
    /*The Java spec allows no exception on this call */
  } else {
    hysock_sockaddr_address6(&sockaddrP, (U_8 *) byte_array, &length,
                             &scope_id);
    /* Cannot call gethostbyaddr since it is not reentrant on some OS's */
    return newJavaNetInetAddressGenericB(env, byte_array, length, scope_id);
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalPort
 * Signature: (Ljava/io/FileDescriptor;Z)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalPort
  (JNIEnv * env, jobject thiz, jobject fileDescriptor,
   jboolean preferIPv6Addresses)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  hysockaddr_struct sockaddrP;
  U_16 nPort, hPort;

  result =
    netGetSockAddr(env, fileDescriptor, &sockaddrP, preferIPv6Addresses);
  if (0 != result) {
    /* The java spec does not indicate any exceptions on this call */
    return (jint) 0;
  }

  nPort = hysock_sockaddr_port(&sockaddrP);
  hPort = hysock_ntohs(nPort);
  return (jint) hPort;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketOption
 * Signature: (Ljava/io/FileDescriptor;I)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketOption
  (JNIEnv * env, jobject thiz, jobject aFileDescriptor, jint anOption)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, aFileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return NULL;
  }
  switch ((I_32) anOption & 0xffff) {
  case JAVASOCKOPT_SO_LINGER:
    return getLingerOption(env, hysocketP);
  case JAVASOCKOPT_TCP_NODELAY:
    if ((anOption >> 16) & BROKEN_TCP_NODELAY)
      return NULL;
    return getBooleanValue(env, hysocketP, HY_IPPROTO_TCP, HY_TCP_NODELAY);
  case JAVASOCKOPT_MCAST_TTL:
    if ((anOption >> 16) & BROKEN_MULTICAST_TTL)
      return newJavaLangByte(env, 0);
    return getByteSocketOption(env, hysocketP, HY_MCAST_TTL);
  case JAVASOCKOPT_MCAST_INTERFACE:
    if ((anOption >> 16) & BROKEN_MULTICAST_IF)
      return NULL;
    return getMcastInterface(env, hysocketP);
  case JAVASOCKOPT_IP_MULTICAST_IF2:
    return getIPV6McastInterface(env, hysocketP);
  case JAVASOCKOPT_SO_SNDBUF:
    return getSendBufferSize(env, hysocketP);
  case JAVASOCKOPT_SO_RCVBUF:
    return getReceiveBufferSize(env, hysocketP);
  case JAVASOCKOPT_SO_BROADCAST:
    return getBooleanValue(env, hysocketP, HY_SOL_SOCKET, HY_SO_BROADCAST);
  case JAVASOCKOPT_SO_REUSEADDR:
    return getBooleanValue(env, hysocketP, HY_SOL_SOCKET, HY_SO_REUSEADDR);
  case JAVASOCKOPT_SO_REUSEPORT:
    return getBooleanValue(env, hysocketP, HY_SOL_SOCKET, HY_SO_REUSEPORT);
  case JAVASOCKOPT_SO_KEEPALIVE:
    return getBooleanValue(env, hysocketP, HY_SOL_SOCKET, HY_SO_KEEPALIVE);
  case JAVASOCKOPT_SO_OOBINLINE:
    return getBooleanValue(env, hysocketP, HY_SOL_SOCKET, HY_SO_OOBINLINE);
  case JAVASOCKOPT_IP_MULTICAST_LOOP:
    return getBooleanValue(env, hysocketP, HY_IPPROTO_IP,
                           HY_IP_MULTICAST_LOOP);
  case JAVASOCKOPT_IP_TOS:
    return getIntegerValue(env, hysocketP, HY_IPPROTO_IP, HY_IP_TOS);

  default:
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_OPTUNSUPP);
    return NULL;
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setSocketOption
 * Signature: (Ljava/io/FileDescriptor;ILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_setSocketOption
  (JNIEnv * env, jclass thisClz, jobject aFileDescriptor, jint anOption,
   jobject aValue)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, aFileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return;
  }
  switch ((I_32) anOption & 0xffff) {
  case JAVASOCKOPT_SO_LINGER:
    setLingerOption(env, hysocketP, aValue);
    break;
  case JAVASOCKOPT_TCP_NODELAY:
    if ((anOption >> 16) & BROKEN_TCP_NODELAY)
      return;
    setBoolSocketOption(env, hysocketP, HY_IPPROTO_TCP, HY_TCP_NODELAY,
                        aValue);
    break;
  case JAVASOCKOPT_MCAST_TTL:
    if ((anOption >> 16) & BROKEN_MULTICAST_TTL)
      return;
    setByteSocketOption(env, hysocketP, HY_MCAST_TTL, aValue);
    break;
  case JAVASOCKOPT_MCAST_ADD_MEMBERSHIP:
    mcastAddMembership(env, hysocketP, aValue,
                       (anOption >> 16) & BROKEN_MULTICAST_IF);
    break;
  case JAVASOCKOPT_MCAST_DROP_MEMBERSHIP:
    mcastDropMembership(env, hysocketP, aValue,
                        (anOption >> 16) & BROKEN_MULTICAST_IF);
    break;
  case JAVASOCKOPT_MCAST_INTERFACE:
    if ((anOption >> 16) & BROKEN_MULTICAST_IF)
      return;
    setMcastInterface(env, hysocketP, aValue);
    break;
  case JAVASOCKOPT_IP_MULTICAST_IF2:
    setIPV6McastInterface(env, hysocketP, aValue);
    break;
  case JAVASOCKOPT_SO_SNDBUF:
    setSendBufferSize(env, hysocketP, aValue);
    break;
  case JAVASOCKOPT_SO_RCVBUF:
    setReceiveBufferSize(env, hysocketP, aValue);
    break;
  case JAVASOCKOPT_SO_BROADCAST:
    setBoolSocketOption(env, hysocketP, HY_SOL_SOCKET, HY_SO_BROADCAST,
                        aValue);
    break;
  case JAVASOCKOPT_SO_REUSEADDR:
    setBoolSocketOption(env, hysocketP, HY_SOL_SOCKET, HY_SO_REUSEADDR,
                        aValue);
    break;
  case JAVASOCKOPT_SO_REUSEPORT:
    setBoolSocketOption(env, hysocketP, HY_SOL_SOCKET, HY_SO_REUSEPORT,
                        aValue);
    break;
  case JAVASOCKOPT_SO_KEEPALIVE:
    setBoolSocketOption(env, hysocketP, HY_SOL_SOCKET, HY_SO_KEEPALIVE,
                        aValue);
    break;
  case JAVASOCKOPT_SO_OOBINLINE:
    setBoolSocketOption(env, hysocketP, HY_SOL_SOCKET, HY_SO_OOBINLINE,
                        aValue);
    break;
  case JAVASOCKOPT_IP_MULTICAST_LOOP:
    setBoolSocketOption(env, hysocketP, HY_IPPROTO_IP,
                        HY_IP_MULTICAST_LOOP, aValue);
    break;
  case JAVASOCKOPT_IP_TOS:
    setIntegerSocketOption(env, hysocketP, HY_IPPROTO_IP, HY_IP_TOS, aValue);
    break;
  case JAVASOCKOPT_REUSEADDR_AND_REUSEPORT:
    setReuseAddrAndReusePort(env, hysocketP, aValue);
    break;

  default:
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_OPTUNSUPP);
  }
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketFlags
 * Signature: ()I
 */
JNIEXPORT jint JNICALL
  Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketFlags
  (JNIEnv * env, jobject thiz)
{
  /* Return the flags indicating the socket state to save in the class library. */
  /* 1 - Multicast interface */
  /* 2 - Multicast TTL */
  /* 4 - Socket TCP_NODELAY */
  /* 8 - Calling shutdown output before close when SO_LINGER is set */

  return 0;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    socketClose
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketClose
  (JNIEnv * env, jobject thiz, jobject fileDescriptor)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t socketP;
  I_32 result = 0;

  socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (hysock_socketIsValid(socketP)) {
    /* Set the file descriptor before closing so the select polling loop will terminate. */
    /* Some platforms wait in the socket close. */
    setJavaIoFileDescriptorContents(env, fileDescriptor, (void *) -1);
    result = hysock_close(&socketP);
  }
}

JNIEXPORT jobject JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByAddr
  (JNIEnv * env, jobject thiz, jbyteArray addr)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result = 0;
  hysockaddr_struct in_addr;
  U_32 length;
  char hostName[OSNIMAXHOST];
  jbyte ipaddr[HYSOCK_INADDR6_LEN];
  int address_family = HYADDR_FAMILY_AFINET4;
  length = (*env)->GetArrayLength(env, addr);

  /* If it's a valid length for an IP address then do the work */
  if (length == HYSOCK_INADDR6_LEN || length == HYSOCK_INADDR_LEN) {
    if (length == HYSOCK_INADDR6_LEN) {
      address_family = HYADDR_FAMILY_AFINET6;
    }

    (*env)->GetByteArrayRegion(env, addr, 0, length, ipaddr);
    hysock_sockaddr_init6(&in_addr, (U_8 *) ipaddr, length,
                          (I_16) address_family, 0, 0, 0, NULL);
    result =
      hysock_getnameinfo(&in_addr, sizeof(in_addr.addr), hostName,
                         OSNIMAXHOST, 0);
    if (0 == result) {
      return newJavaNetInetAddressGenericBS(env, ipaddr, length, hostName, 0);
    }
  }

  throwJavaNetUnknownHostException(env, result);
  return NULL;
}

JNIEXPORT jobject JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByName
  (JNIEnv * env, jobject thiz, jstring aName, jboolean preferIPv6Addresses)
{
  PORT_ACCESS_FROM_ENV(env);
  I_32 result;
  jbyte nipAddr[HYSOCK_INADDR6_LEN];
  jobject inetA;
  const char *str = (*env)->GetStringUTFChars(env, aName, 0);
  hyaddrinfo_struct addrinfo;
  hyaddrinfo_t hints;
  U_32 length = 0;
  I_32 family;
  U_32 scope_id = 0;
  I_32 i = 0;
  I_32 addrLength = 0;
  BOOLEAN preferIPv4StackValue = preferIPv4Stack(env);

  if (NULL == str) {
    return NULL;
  }
  if (!preferIPv4StackValue) {
    hysock_getaddrinfo_create_hints(&hints, (I_16) HYADDR_FAMILY_UNSPEC, 0,
                                    HYPROTOCOL_FAMILY_UNSPEC, 0);
  } else {
    hysock_getaddrinfo_create_hints(&hints, (I_16) HYADDR_FAMILY_AFINET4,
                                    0, HYPROTOCOL_FAMILY_UNSPEC, 0);
  }
  result = hysock_getaddrinfo((char *) str, hints, &addrinfo);

  if (result != 0) {
    (*env)->ReleaseStringUTFChars(env, aName, str);
    throwJavaNetUnknownHostException(env, result);
    return NULL;
  }

  /* now loop through the addresses returned and return the first one that matches the preference indicated */
  hysock_getaddrinfo_length(&addrinfo, &addrLength);
  for (i = 0; i < addrLength; i++) {
    hysock_getaddrinfo_family(&addrinfo, &family, i);
    if (((family == HYADDR_FAMILY_AFINET4)
         && ((preferIPv6Addresses == FALSE) || preferIPv4StackValue))
        || ((family == HYADDR_FAMILY_AFINET6)
            && (preferIPv6Addresses == TRUE) && (!preferIPv4StackValue))) {
      /* ok found one that matches the preference so get the required info */
      hysock_getaddrinfo_address(&addrinfo, (U_8 *) nipAddr, i, &scope_id);
      break;
    }
  }

  /* check if there was no match for the preferIPv6Addresses.  If not just return the first one */
  if (i == addrLength) {
    hysock_getaddrinfo_family(&addrinfo, &family, 0);
    hysock_getaddrinfo_address(&addrinfo, (U_8 *) nipAddr, 0, &scope_id);
  }

  hysock_freeaddrinfo(&addrinfo);

  length =
    (family ==
     HYPROTOCOL_FAMILY_INET4) ? HYSOCK_INADDR_LEN : HYSOCK_INADDR6_LEN;

  inetA =
    newJavaNetInetAddressGenericBS(env, nipAddr, length, (char *) str,
                                   scope_id);

  (*env)->ReleaseStringUTFChars(env, aName, str);
  return inetA;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setInetAddress
 * Signature: (Ljava/net/InetAddress;[B)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_setInetAddress
  (JNIEnv * env, jobject thiz, jobject sender, jbyteArray address)
{
  I_8 *passAddr = NULL;
  jbyteArray addr_array =
    (jbyteArray) ((*env)->GetObjectField(env,
                                         sender,
                                         HARMONY_CACHE_GET(env, FID_java_net_InetAddress_address)));
  I_32 length = (*env)->GetArrayLength(env, address);
  addr_array = (*env)->NewByteArray(env, (jsize) length);
  (*env)->GetByteArrayRegion(env, address, 0, length, (jbyte *) passAddr);
  (*env)->SetByteArrayRegion(env, addr_array, 0, length, (jbyte *) passAddr);
}
