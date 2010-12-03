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

#include <stdlib.h>
#include "nethelp.h"
#include "hysock.h"
#include "harmonyglob.h"

/**
 * Answer the aliases for the nominated host name.  Useful for multihomed hosts.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 * @param	aName		the host name string object
 *
 * @return	an array of InetAddress's
 */

JNIEXPORT jobjectArray JNICALL
Java_java_net_InetAddress_getAliasesByNameImpl (JNIEnv * env, jclass clazz,
                                                jstring aName)
{
  PORT_ACCESS_FROM_ENV (env);
  jobjectArray aliases;
  hyaddrinfo_struct addrinfo;
  hyaddrinfo_t hints;
  U_32 result;

  const char *str = (*env)->GetStringUTFChars (env, aName, 0);
  if (NULL == str)
    {
      return NULL;
    }

  if (!preferIPv4Stack (env))
    {
      hysock_getaddrinfo_create_hints (&hints, (I_16) HYADDR_FAMILY_UNSPEC, 0,
                                       HYPROTOCOL_FAMILY_UNSPEC, 0);
    }
  else
    {
      hysock_getaddrinfo_create_hints (&hints, (I_16) HYADDR_FAMILY_AFINET4,
                                       0, HYPROTOCOL_FAMILY_UNSPEC, 0);
    }
  result = hysock_getaddrinfo ((char *) str, hints, &addrinfo);
  if (result != 0)
    {
      (*env)->ReleaseStringUTFChars (env, aName, str);
      throwJavaNetUnknownHostException (env, result);
      return NULL;
    }

  aliases = createAliasArrayFromAddrinfo (env, &addrinfo, str);
  hysock_freeaddrinfo (&addrinfo);
  (*env)->ReleaseStringUTFChars (env, aName, str);
  return aliases;
}

/**
 * Answer the InetAddress for the nominated host, performing a name lookup
 * to find the name the host is known by in the network.
 * Note, unlike the unix getHostByAddr call, the addr argument in the function parameter list 
 * is a 32-bit address value rather than a dotted IP string.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 * @param	addr			the host address (integer, in host order)
 *
 * @return	the InetAddress representing the host
 */
JNIEXPORT jobject JNICALL
Java_java_net_InetAddress_getHostByAddrImpl (JNIEnv * env, jclass clazz,
                                             jbyteArray addr)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result = 0;
  hysockaddr_struct in_addr;
  U_32 length;
  char hostName[OSNIMAXHOST];
  jbyte ipaddr[HYSOCK_INADDR6_LEN];
  int address_family = HYADDR_FAMILY_AFINET4;
  length = (*env)->GetArrayLength (env, addr);

  /* If it's a valid length for an IP address then do the work */
  if (length == HYSOCK_INADDR6_LEN || length == HYSOCK_INADDR_LEN)
    {

      if (length == HYSOCK_INADDR6_LEN)
        {
          address_family = HYADDR_FAMILY_AFINET6;
        }

      (*env)->GetByteArrayRegion (env, addr, 0, length, ipaddr);
      hysock_sockaddr_init6 (&in_addr, (U_8 *) ipaddr, length,
                             (I_16) address_family, 0, 0, 0, NULL);
      result =
        hysock_getnameinfo (&in_addr, sizeof (in_addr.addr), hostName,
                            OSNIMAXHOST, 0);
      if (0 == result)
        {
          return newJavaNetInetAddressGenericBS (env, ipaddr, length,
                                                 hostName, 0);
        }
    }

  throwJavaNetUnknownHostException (env, result);
  return NULL;
}

/**
 * Answer the InetAddress for the nominated host, performing a name lookup
 * to find the network address.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 * @param	aName		the host name
 * @param preferIPv6Addresses on V4/V6 nodes, a preference as to which address to return for the node
 * @return	the InetAddress representing the host
 */

JNIEXPORT jobject JNICALL
Java_java_net_InetAddress_getHostByNameImpl (JNIEnv * env, jclass clazz,
                                             jstring aName,
                                             jboolean preferIPv6Addresses)
{

  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jbyte nipAddr[HYSOCK_INADDR6_LEN];
  jobject inetA;
  const char *str = (*env)->GetStringUTFChars (env, aName, 0);
  hyaddrinfo_struct addrinfo;
  hyaddrinfo_t hints;
  U_32 length = 0;
  I_32 family;
  U_32 scope_id = 0;
  I_32 i = 0;
  I_32 addrLength = 0;
  BOOLEAN preferIPv4StackValue = preferIPv4Stack (env);

  if (NULL == str)
    {
      return NULL;
    }
  if (!preferIPv4StackValue)
    {
      hysock_getaddrinfo_create_hints (&hints, (I_16) HYADDR_FAMILY_UNSPEC, 0,
                                       HYPROTOCOL_FAMILY_UNSPEC, 0);
    }
  else
    {
      hysock_getaddrinfo_create_hints (&hints, (I_16) HYADDR_FAMILY_AFINET4,
                                       0, HYPROTOCOL_FAMILY_UNSPEC, 0);
    }
  result = hysock_getaddrinfo ((char *) str, hints, &addrinfo);

  if (result != 0)
    {
      (*env)->ReleaseStringUTFChars (env, aName, str);
      throwJavaNetUnknownHostException (env, result);
      return NULL;
    }

  /* now loop through the addresses returned and return the first one that matches the preference indicated */
  hysock_getaddrinfo_length (&addrinfo, &addrLength);
  for (i = 0; i < addrLength; i++)
    {
      hysock_getaddrinfo_family (&addrinfo, &family, i);
      if (((family == HYADDR_FAMILY_AFINET4)
           && ((preferIPv6Addresses == FALSE) || preferIPv4StackValue))
          || ((family == HYADDR_FAMILY_AFINET6)
              && (preferIPv6Addresses == TRUE) && (!preferIPv4StackValue)))
        {
          /* ok found one that matches the preference so get the required info */
          hysock_getaddrinfo_address (&addrinfo, (U_8 *) nipAddr, i,
                                      &scope_id);
          break;
        }
    }

  /**
   * check if there was no match for the preferIPv6Addresses.  
   * If not just return the first one 
   */
  if (i == addrLength)
    {
      hysock_getaddrinfo_family (&addrinfo, &family, 0);
      hysock_getaddrinfo_address (&addrinfo, (U_8 *) nipAddr, 0, &scope_id);
    }

  hysock_freeaddrinfo (&addrinfo);

  length =
    (family ==
     HYPROTOCOL_FAMILY_INET4) ? HYSOCK_INADDR_LEN : HYSOCK_INADDR6_LEN;

  inetA =
    newJavaNetInetAddressGenericBS (env, nipAddr, length, (char *) str,
                                    scope_id);

  (*env)->ReleaseStringUTFChars (env, aName, str);
  return inetA;
}

/**
 * Answer the string name for the local host.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 *
 * @return	the local host name
 */

JNIEXPORT jstring JNICALL
Java_java_net_InetAddress_getHostNameImpl (JNIEnv * env, jclass clazz)
{

  PORT_ACCESS_FROM_ENV (env);
  char buf[64];
  I_32 result = hysock_gethostname (buf, sizeof (buf));

  if (0 != result)
    {
      throwJavaNetUnknownHostException (env, result);
      return NULL;
    }
  else
    {
      return (*env)->NewStringUTF (env, buf);
    }
}

/**
 * Answer the network address for the nominated dotted ip string.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 * @param	host		the dotted ip string to be converted
 *
 * @return	the network address
 */

JNIEXPORT jint JNICALL
Java_java_net_InetAddress_inetAddrImpl (JNIEnv * env, jclass clazz,
                                        jstring host)
{

  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_32 nipAddr;
  const char *strHost = (*env)->GetStringUTFChars (env, host, 0);

  if (NULL == strHost)
    {
      return (jint) 0;
    }
  result = hysock_inetaddr ((char *) strHost, &nipAddr);        /* Resolve the dotted ip string to an address */
  (*env)->ReleaseStringUTFChars (env, host, strHost);
  if (0 != result)
    {
      throwJavaNetUnknownHostException (env, result);
      return (jint) 0;
    }
  return (jint) hysock_ntohl (nipAddr);
}

/**
 * Answer the dotted ip string for the nominated network address.
 *
 * @param	env			pointer to the JNI library
 * @param	clazz		the class of the object invoking the JNI function
 * @param	hostAddr	the network address to be converted
 *
 * @return	the dotted ip string
 */

JNIEXPORT jstring JNICALL
Java_java_net_InetAddress_inetNtoaImpl (JNIEnv * env, jclass clazz,
                                        jint hipAddr)
{

  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  U_32 nipAddr;
  char *strHost;

  nipAddr = hysock_htonl ((I_32) hipAddr);
  /* if this native method is not synchronized in Java, the underlying implementation of hysock_inetntoa must be threadsafe */
  result = hysock_inetntoa (&strHost, nipAddr);
  if (0 != result)
    {
      throwJavaLangIllegalArgumentException (env, result);
      return NULL;
    }
  else
    {
      return (*env)->NewStringUTF (env, strHost);
    }
}

JNIEXPORT void JNICALL
Java_java_net_InetAddress_oneTimeInitialization (JNIEnv * env, jclass clazz,
                                                 jboolean ipv6_support)
{
  netInitializeIDCaches (env, ipv6_support);
}
