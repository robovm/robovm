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

#include "nethelp.h"
#include "helpers.h"
#include "harmonyglob.h"
#include "hysock.h"

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

/**
 * A helper method, to create a socket of stream or datagram type and update the
 * descriptor of the java socketImpl argument.
 *
 * @param	env				pointer to the JNI library
 * @param	thisObj			pointer to java socket object to update the descriptor of
 * @param	sockType		constant, indicating to create either a stream or datagram socket
 * @param preferIPv4Stack if application preference is to use only IPv4 sockets (default is false)
 * @exception	SocketException	if an error occurs during the call
 */

void
createSocket (JNIEnv * env, jobject thisObjFD, int sockType,
              jboolean preferIPv4Stack)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  hysocket_t sockdesc;
  int family = HYADDR_FAMILY_AFINET4;
  int supportsIPv6 = HARMONY_CACHE_GET (env, harmony_supports_ipv6);

  if (supportsIPv6 && !(preferIPv4Stack))
    {
      /* We are creating a server socket on the any address */
      family = HYADDR_FAMILY_UNSPEC;
    }

  result = hysock_socket (&sockdesc, family, sockType, HYSOCK_DEFPROTOCOL);
  if (0 != result)
    {
      /* ok now if we tried to create an IPv6 socket and it failed it could be that the
         platform does not have IPv6 enabled.  In this case we should revert back to 
         creating an IPv4 socket */
      if (HYADDR_FAMILY_UNSPEC == family)
        {
          /* now try to create an IPv4 socket */
          family = HYADDR_FAMILY_AFINET4;
          result =
            hysock_socket (&sockdesc, family, sockType, HYSOCK_DEFPROTOCOL);
        }

      if (0 != result)
        {
          throwJavaNetSocketException (env, result);
        }
    }

  if (0 == result)
    {
      setJavaIoFileDescriptorContents (env, thisObjFD, sockdesc);
    }
}

/**
 * Answer the linger value of the socket argument.  A value of -1 indicates linger is disabled.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 *
 * @return	the Integer value of the socket linger, in milliSeconds
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getLingerOption (JNIEnv * env, hysocket_t hysocketP)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  hylinger_struct lingerOpt;
  U_16 linger;
  BOOLEAN enabled;
  jobject option;

  result = hysock_linger_init (&lingerOpt, 0, 0);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  result =
    hysock_getopt_linger (hysocketP, HY_SOL_SOCKET, HY_SO_LINGER, &lingerOpt);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  hysock_linger_enabled (&lingerOpt, &enabled);
  if (enabled)
    {
      hysock_linger_linger (&lingerOpt, &linger);
      option = newJavaLangInteger (env, linger);
    }
  else
    {
      option = newJavaLangInteger (env, -1);
    }
  return option;
}

/**
 * Answer the multicast interface address of the socket argument.  Implemented by getting
 * the multicast interface option at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 *
 * @return	the InetAddress for the socket multicast interface
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getMcastInterface (JNIEnv * env, hysocket_t hysocketP)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_8 byte_array[HYSOCK_INADDR6_LEN];
  hysockaddr_struct sockaddrP;
  U_32 length;
  U_32 scope_id = 0;

  memset (byte_array, 0, HYSOCK_INADDR6_LEN);
  hysock_sockaddr_init6 (&sockaddrP, byte_array, HYSOCK_INADDR_LEN,
                         HYADDR_FAMILY_AFINET4, 0, 0, 0, hysocketP);

  result =
    hysock_getopt_sockaddr (hysocketP, HY_IPPROTO_IP, HY_MCAST_INTERFACE,
                            &sockaddrP);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  hysock_sockaddr_address6 (&sockaddrP, byte_array, &length, &scope_id);
  return newJavaNetInetAddressGenericB (env, (jbyte *)byte_array, length, scope_id);

}

/**
 * Answer the receive buffer size for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 *
 * @return	the receive buffer size, as an Integer value
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getReceiveBufferSize (JNIEnv * env, hysocket_t hysocketP)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  I_32 optval = 0;

  result =
    hysock_getopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_RCVBUF, &optval);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  return newJavaLangInteger (env, optval);
}

/**
 * Answer the send buffer size for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 *
 * @return	the send buffer size, as an Integer value
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getSendBufferSize (JNIEnv * env, hysocket_t hysocketP)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  I_32 optval = 0;

  result =
    hysock_getopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_SNDBUF, &optval);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  return newJavaLangInteger (env, optval);
}

/**
 * Join the nominated multicast group on the specified socket .  Implemented by setting
 * the multicast 'add membership' option at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to join on
 * @param	optVal			pointer to the InetAddress, the multicast group to join
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
mcastAddMembership (JNIEnv * env, hysocket_t hysocketP, jobject optVal,
                    BOOLEAN ignoreIF)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_8 nipmcast[HYSOCK_INADDR6_LEN], nipInterface[HYSOCK_INADDR6_LEN];
  U_32 length, lengthIF;
  hyipmreq_struct ipmreqP;
  hysockaddr_struct sockaddrP;
  hyipv6_mreq_struct ipv6Mreq;
  U_32 interfaceIndex;
  U_32 scope_id = 0;

  /* JNI objects needed to access the information in the optVal object passed in */
  /* the object passed in is a GenericIPMreq object */
  jclass cls;
  jfieldID multiaddrID;
  jfieldID interfaceAddrID;
  jfieldID isIPV6AddressID;
  jfieldID interfaceIdxID;
  jobject multiaddr;
  jobject interfaceAddr;
  jboolean isIPV6Address;
  jint interfaceIdx;
  jclass inetAddressClass;

  memset (nipInterface, 0, HYSOCK_INADDR6_LEN);

  /* check whether we are getting an InetAddress or an Generic IPMreq, for now we support both so that we will not
     break the tests */
  inetAddressClass = (*env)->FindClass (env, "java/net/InetAddress");
  if ((*env)->IsInstanceOf (env, optVal, inetAddressClass))
    {

      netGetJavaNetInetAddressValue (env, optVal, nipmcast, &length);

      if (!ignoreIF)
        {
          /* nipInterface is initialized to zero and we are supposed to initialize this with zero */
          hysock_sockaddr_init6 (&sockaddrP, nipInterface, HYSOCK_INADDR_LEN,
                                 HYADDR_FAMILY_AFINET4, 0, 0, 0, hysocketP);
          result =
            hysock_getopt_sockaddr (hysocketP, HY_IPPROTO_IP,
                                    HY_MCAST_INTERFACE, &sockaddrP);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
          hysock_sockaddr_address6 (&sockaddrP, nipInterface, &lengthIF,
                                    &scope_id);
        }

      result =
        hysock_ipmreq_init (&ipmreqP, *((U_32 *) nipmcast),
                            *((U_32 *) nipInterface));
      if (0 != result)
        {
          throwJavaNetSocketException (env, result);
          return;
        }
      result =
        hysock_setopt_ipmreq (hysocketP, HY_IPPROTO_IP,
                              HY_MCAST_ADD_MEMBERSHIP, &ipmreqP);
      if (0 != result)
        {
          throwJavaNetSocketException (env, result);
        }
    }
  else
    {
      /* we need the multicast address and isIPV6Address regardless of the type of address */
      cls = (*env)->GetObjectClass (env, optVal);
      multiaddrID =
        (*env)->GetFieldID (env, cls, "multiaddr", "Ljava/net/InetAddress;");
      isIPV6AddressID = (*env)->GetFieldID (env, cls, "isIPV6Address", "Z");
      isIPV6Address = (*env)->GetBooleanField (env, optVal, isIPV6AddressID);
      multiaddr = (*env)->GetObjectField (env, optVal, multiaddrID);
      netGetJavaNetInetAddressValue (env, multiaddr, nipmcast, &length);

      /* now get either the address or the index depending on wether it is an IPV4 or IPV6 address 
         and add the mcast address */
      if (isIPV6Address)
        {
          /* ok we need to use an IPV6_MREQ.  */

          /* get the interface index */
          interfaceIdxID = (*env)->GetFieldID (env, cls, "interfaceIdx", "I");
          interfaceIdx = (*env)->GetIntField (env, optVal, interfaceIdxID);
          interfaceIndex = interfaceIdx;
          if ((!ignoreIF) && (0 == interfaceIndex))
            {
              result =
                hysock_getopt_int (hysocketP, HY_IPPROTO_IPV6,
                                   HY_MCAST_INTERFACE_2, (I_32 *)&interfaceIndex);
              if (0 != result)
                {
                  throwJavaNetSocketException (env, result);
                  return;
                }
            }

          /* get the multicast group address , multiaddr allready has the multicast group address */
          interfaceIdxID = (*env)->GetFieldID (env, cls, "interfaceIdx", "I");
          interfaceIdx = (*env)->GetIntField (env, optVal, interfaceIdxID);

          /* initiaze the ip_mreq with the multicast address passed in and the appropriate interface number */
          result =
            hysock_ipv6_mreq_init (&ipv6Mreq, ((U_8 *) multiaddr),
                                   interfaceIdx);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }

          result =
            hysock_setopt_ipv6_mreq (hysocketP, HY_IPPROTO_IPV6,
                                     HY_IPV6_ADD_MEMBERSHIP, &ipv6Mreq);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
          return;
        }
      else
        {
          /* we need to use an IP_MREQ as it is an IPV4 address */
          interfaceAddrID =
            (*env)->GetFieldID (env, cls, "interfaceAddr",
                                "Ljava/net/InetAddress;");
          interfaceAddr =
            (*env)->GetObjectField (env, optVal, interfaceAddrID);

          /* if an interfaceAddr was passed then use that value, otherwise set the interface to all 0 to 
             indicate the system should select the interface used */

          if (NULL != interfaceAddr)
            {
              netGetJavaNetInetAddressValue (env, interfaceAddr, nipInterface,
                                             &length);
            }
          else
            {
              memset (nipInterface, 0, HYSOCK_INADDR6_LEN);
            }

          /* if we did not pass in an interface and we are not ignoring the interface that was set on the socket then 
             get address for the interface set and included that in the ip_mreq */
          if ((!ignoreIF) && (NULL == interfaceAddr))
            {
              /* nipInterface is initialized to zero and we are supposed to initialize this with zero */
              hysock_sockaddr_init6 (&sockaddrP, nipInterface,
                                     HYSOCK_INADDR_LEN, HYADDR_FAMILY_AFINET4,
                                     0, 0, 0, hysocketP);
              result =
                hysock_getopt_sockaddr (hysocketP, HY_IPPROTO_IP,
                                        HY_MCAST_INTERFACE, &sockaddrP);
              if (0 != result)
                {
                  throwJavaNetSocketException (env, result);
                  return;
                }
              hysock_sockaddr_address6 (&sockaddrP, nipInterface, &lengthIF,
                                        &scope_id);
            }

          /* initiaze the ip_mreq with the multicast address passed in and the appropriate interface address */
          result =
            hysock_ipmreq_init (&ipmreqP, *((U_32 *) nipmcast),
                                *((U_32 *) nipInterface));
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }

          /* add the multicast address */
          result =
            hysock_setopt_ipmreq (hysocketP, HY_IPPROTO_IP,
                                  HY_MCAST_ADD_MEMBERSHIP, &ipmreqP);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
        }
      return;
    }
}

/**
 * Leave the nominated multicast group on the specified socket .  Implemented by setting
 * the multicast 'drop membership' option at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to leave on
 * @param	optVal			pointer to the InetAddress, the multicast group to drop
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
mcastDropMembership (JNIEnv * env, hysocket_t hysocketP, jobject optVal,
                     BOOLEAN ignoreIF)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  hyipmreq_struct ipmreqP;
  hysockaddr_struct sockaddrP;
  U_32 length, lengthIF;
  U_8 nipmcast[HYSOCK_INADDR6_LEN];
  U_8 nipInterface[HYSOCK_INADDR6_LEN];
  hyipv6_mreq_struct ipv6Mreq;
  U_32 interfaceIndex;
  U_32 scope_id = 0;

/* JNI objects needed to access the information in the optVal object passed in */
  /* the object passed in is a GenericIPMreq object */
  jclass cls;
  jfieldID multiaddrID;
  jfieldID interfaceAddrID;
  jfieldID isIPV6AddressID;
  jfieldID interfaceIdxID;
  jobject multiaddr;
  jobject interfaceAddr;
  jboolean isIPV6Address;
  jint interfaceIdx;
  jclass inetAddressClass;

  memset (nipInterface, 0, HYSOCK_INADDR6_LEN);

  /* check whether we are getting an InetAddress or an Generic IPMreq, for now we support both so that we will not
     break the tests */
  inetAddressClass = (*env)->FindClass (env, "java/net/InetAddress");
  if ((*env)->IsInstanceOf (env, optVal, inetAddressClass))
    {
      netGetJavaNetInetAddressValue (env, optVal, nipmcast, &length);
      if (!ignoreIF)
        {
          hysock_sockaddr_init6 (&sockaddrP, nipInterface, length,
                                 HYADDR_FAMILY_AFINET4, 0, 0, 0, hysocketP);
          result =
            hysock_getopt_sockaddr (hysocketP, HY_IPPROTO_IP,
                                    HY_MCAST_INTERFACE, &sockaddrP);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
          hysock_sockaddr_address6 (&sockaddrP, nipInterface, &lengthIF,
                                    &scope_id);
        }

      result =
        hysock_ipmreq_init (&ipmreqP, *((int *) nipmcast),
                            *((int *) nipInterface));
      if (0 != result)
        {
          throwJavaNetSocketException (env, result);
          return;
        }

      result =
        hysock_setopt_ipmreq (hysocketP, HY_IPPROTO_IP,
                              HY_MCAST_DROP_MEMBERSHIP, &ipmreqP);
      if (0 != result)
        {
          throwJavaNetSocketException (env, result);
        }
    }
  else
    {
      /* we need the multicast address and isIPV6Address regardless of the type of address */
      cls = (*env)->GetObjectClass (env, optVal);
      multiaddrID =
        (*env)->GetFieldID (env, cls, "multiaddr", "Ljava/net/InetAddress;");
      isIPV6AddressID = (*env)->GetFieldID (env, cls, "isIPV6Address", "Z");
      isIPV6Address = (*env)->GetBooleanField (env, optVal, isIPV6AddressID);
      multiaddr = (*env)->GetObjectField (env, optVal, multiaddrID);
      netGetJavaNetInetAddressValue (env, multiaddr, nipmcast, &length);

      /* now get either the address or the index depending on wether it is an IPV4 or IPV6 address 
         and add the mcast address */
      if (isIPV6Address)
        {
          /* ok we need to use an IPV6_MREQ.  */

          /* get the interface index */
          interfaceIdxID = (*env)->GetFieldID (env, cls, "interfaceIdx", "I");
          interfaceIdx = (*env)->GetIntField (env, optVal, interfaceIdxID);
          interfaceIndex = interfaceIdx;
          if ((!ignoreIF) && (0 == interfaceIndex))
            {
              result =
                hysock_getopt_int (hysocketP, HY_IPPROTO_IPV6,
                                   HY_MCAST_INTERFACE_2, (I_32 *)&interfaceIndex);
              if (0 != result)
                {
                  throwJavaNetSocketException (env, result);
                  return;
                }
            }

          /* get the multicast group address , multiaddr allready has the multicast group address */
          interfaceIdxID = (*env)->GetFieldID (env, cls, "interfaceIdx", "I");
          interfaceIdx = (*env)->GetIntField (env, optVal, interfaceIdxID);

          /* initiaze the ip_mreq with the multicast address passed in and the appropriate interface number */
          result =
            hysock_ipv6_mreq_init (&ipv6Mreq, ((U_8 *) multiaddr),
                                   interfaceIdx);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }

          result =
            hysock_setopt_ipv6_mreq (hysocketP, HY_IPPROTO_IPV6,
                                     HY_IPV6_DROP_MEMBERSHIP, &ipv6Mreq);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
          return;
        }
      else
        {
          /* we need to use an IP_MREQ as it is an IPV4 address */
          interfaceAddrID =
            (*env)->GetFieldID (env, cls, "interfaceAddr",
                                "Ljava/net/InetAddress;");
          interfaceAddr =
            (*env)->GetObjectField (env, optVal, interfaceAddrID);

          /* if an interfaceAddr was passed then use that value, otherwise set the interface to all 0 to 
             indicate the system should select the interface used */
          if (NULL != interfaceAddr)
            {
              netGetJavaNetInetAddressValue (env, interfaceAddr, nipInterface,
                                             &length);
            }
          else
            {
              memset (nipInterface, 0, HYSOCK_INADDR6_LEN);
            }

          if ((!ignoreIF) && (NULL == interfaceAddr))
            {
              /* nipInterface is initialized to zero and we are supposed to initialize this with zero */
              hysock_sockaddr_init6 (&sockaddrP, nipInterface,
                                     HYSOCK_INADDR_LEN, HYADDR_FAMILY_AFINET4,
                                     0, 0, 0, hysocketP);
              result =
                hysock_getopt_sockaddr (hysocketP, HY_IPPROTO_IP,
                                        HY_MCAST_INTERFACE, &sockaddrP);
              if (0 != result)
                {
                  throwJavaNetSocketException (env, result);
                  return;
                }
              hysock_sockaddr_address6 (&sockaddrP, nipInterface, &lengthIF,
                                        &scope_id);
            }

          /* initiaze the ip_mreq with the multicast address passed in and the appropriate interface address */
          result =
            hysock_ipmreq_init (&ipmreqP, *((U_32 *) nipmcast),
                                *((U_32 *) nipInterface));
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }

          /* drop the multicast address */
          result =
            hysock_setopt_ipmreq (hysocketP, HY_IPPROTO_IP,
                                  HY_MCAST_DROP_MEMBERSHIP, &ipmreqP);
          if (0 != result)
            {
              throwJavaNetSocketException (env, result);
              return;
            }
        }
      return;
    }
}

/**
 * Set the linger value for the socket argument.  A value of -1 indicates linger is disabled.
 * Note, the optVal from Java is in milliseconds.
 * The caller is expected to limit the value of optVal to 65535000 (mSec),
 * which is the max portable linger value.
 * Since the IP stack deals in seconds, any attempt to set a linger value 0 < linger < 1 sec, will
 * set a value of 1 second, so that truncation does not yield the unexpected result of linger indefinitely.
 *
 * @param	env			pointer to the JNI library
 * @param	socketP	pointer to the hysocket to set linger on
 * @param	optVal		the linger value to set, in milliSeconds
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setLingerOption (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  hylinger_struct lingerOpt;
  I_32 enabled = 0;
  I_32 result = 0;

  I_32 value = intValue (env, optVal);
  enabled = value >= 0;
  result = hysock_linger_init (&lingerOpt, enabled, (U_16) value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return;
    }
  result =
    hysock_setopt_linger (hysocketP, HY_SOL_SOCKET, HY_SO_LINGER, &lingerOpt);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}

/**
 * Set the multicast interface address of the socket argument.  Implemented by setting
 * the multicast interface option at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the interface on
 * @param	optVal			pointer to the InetAddress representing the interface address
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setMcastInterface (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  int length;
  U_8 address[HYSOCK_INADDR6_LEN];
  hysockaddr_struct sockaddrP;

  netGetJavaNetInetAddressValue (env, optVal, address, (U_32 *)&length);
  hysock_sockaddr_init6 (&sockaddrP, address, length, HYADDR_FAMILY_AFINET4,
                         0, 0, 0, hysocketP);
  result =
    hysock_setopt_sockaddr (hysocketP, HY_IPPROTO_IP, HY_MCAST_INTERFACE,
                            &sockaddrP);
  if (0 != result)
    throwJavaNetSocketException (env, result);
}

/**
 * Set the receive buffer size for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set receive buffer size on
 * @param	optVal			the buffer size to set
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setReceiveBufferSize (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  I_32 value;

  value = intValue (env, optVal);
  result = hysock_setopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_RCVBUF, &value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}

/**
 * Set the send buffer size for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set send buffer size on
 * @param	optVal			the buffer size to set
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setSendBufferSize (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  I_32 value;

  value = intValue (env, optVal);
  result = hysock_setopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_SNDBUF, &value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}


/**
 * Set a boolean socket option.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the broadcast status of
 * @param	level			the socket option level
 * @param	option			the socket option
 * @param	optVal			the broadcast value to set
 *
 * @exception	SocketException	if an error occurs during the call
 */
void
setBoolSocketOption (JNIEnv * env, hysocket_t hysocketP, int level,
                     int option, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  BOOLEAN value;

  value = booleanValue (env, optVal);
  result = hysock_setopt_bool (hysocketP, level, option, &value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}

/**
 * Answer the status of the specified boolean option for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 * @param	level			the socket option level
 * @param	option			the socket option
 *
 * @return	the Boolean value
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getBooleanValue (JNIEnv * env, hysocket_t hysocketP, int level, int option)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  BOOLEAN optval;

  result = hysock_getopt_bool (hysocketP, level, option, &optval);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  return newJavaLangBoolean (env, optval);
}


/**
 * Answer the byte value of the specified option for the socket argument,
 * at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 * @param	option			the socket option
 *
 * @return	a Byte value
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getByteSocketOption (JNIEnv * env, hysocket_t hysocketP, int option)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_8 optval;

  result = hysock_getopt_byte (hysocketP, HY_IPPROTO_IP, option, &optval);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }
  return newJavaLangByte (env, optval);
}

/**
 * Set the byte value of the specified option for the socket argument,
 * at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the TTL on
 * @param	option			the socket option
 * @param	optVal			the value to set
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setByteSocketOption (JNIEnv * env, hysocket_t hysocketP, int option,
                     jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_8 value;

  value = byteValue (env, optVal);
  result = hysock_setopt_byte (hysocketP, HY_IPPROTO_IP, option, &value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}

/**
 * Answer the IPV6 multicast interface index of the socket argument.  Implemented by getting
 * the multicast interface index at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env			pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 *
 * @return	the index for the interface bound to the multicast socket
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getIPV6McastInterface (JNIEnv * env, hysocket_t hysocketP)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jint valueJNI;
  I_32 value;

  /* get the value */
  result =
    hysock_getopt_int (hysocketP, HY_IPPROTO_IPV6, HY_MCAST_INTERFACE_2,
                       &value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }

  /* turn it into an integer and return it */
  valueJNI = value;
  return newJavaLangInteger (env, value);
}

/**
 * Set the multicast interface index of the socket argument.  Implemented by setting
 * the multicast interface index at the HY_IPPROTO_IP level on the socket.
 *
 * @param	env			pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the interface on
 * @param	optVal		pointer to the InetAddress representing the interface address
 *
 * @exception	SocketException	if an error occurs during the call
 */

void
setIPV6McastInterface (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;

  /* get the integer value that should have been passed in */
  jint index = intValue (env, optVal);

  /* set the option */
  result =
    hysock_setopt_int (hysocketP, HY_IPPROTO_IPV6, HY_MCAST_INTERFACE_2,
                       &index);
  if (0 != result)
    throwJavaNetSocketException (env, result);
}

/**
 * Set an integer socket option.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the broadcast status of
 * @param	level			the socket option level
 * @param	option			the socket option
 * @param	optVal			the broadcast value to set
 *
 * @exception	SocketException	if an error occurs during the call
 */
void
setIntegerSocketOption (JNIEnv * env, hysocket_t hysocketP, int level,
                        int option, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_32 value;

  value = intValue (env, optVal);
  result = hysock_setopt_int (hysocketP, level, option, (I_32 *)&value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}

/**
 * Answer the status of the specified integer option for the socket argument.
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to query
 * @param	level			the socket option level
 * @param	option			the socket option
 *
 * @return	the Integer value
 * @exception	SocketException	if an error occurs during the call
 */

jobject
getIntegerValue (JNIEnv * env, hysocket_t hysocketP, int level, int option)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  BOOLEAN optval;

  result = hysock_getopt_int (hysocketP, level, option, (I_32 *)&optval);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
      return NULL;
    }

  return newJavaLangInteger (env, optval);
}

/**
 * Set both REUSEADDR and REUSEPORT if appropriate.  Datagram sockets require that when REUSEADDR is set REUSEPORT is also set
 * on platforms which support this option.  Other types of sockets should not have this behavior so we cannot do it lower down in the port
 * library
 *
 * @param	env				pointer to the JNI library
 * @param	socketP		pointer to the hysocket to set the broadcast status of
 * @param	level			the socket option level
 * @param	option			the socket option
 * @param	optVal			the broadcast value to set
 *
 * @exception	SocketException	if an error occurs during the call
 */
void
setReuseAddrAndReusePort (JNIEnv * env, hysocket_t hysocketP, jobject optVal)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_32 value;

  value = booleanValue (env, optVal);

  /* first set REUSEPORT.  Ignore the error as not all platforms will support this */
  result =
    hysock_setopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_REUSEPORT, (I_32 *)&value);

  /* now set REUSEADDR.  We expect this to work */
  result =
    hysock_setopt_int (hysocketP, HY_SOL_SOCKET, HY_SO_REUSEADDR, (I_32 *)&value);
  if (0 != result)
    {
      throwJavaNetSocketException (env, result);
    }
}
