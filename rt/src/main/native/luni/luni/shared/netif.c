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

/**
 * Answer an array of NetworkInterface objects.  One for each network interface within the system
 *
 * @param	env	pointer to the JNI library
 * @param	clazz	the class of the object invoking the JNI function
 *
 * @return			an array of NetworkInterface objects of length 0 or more
 */

JNIEXPORT jobjectArray JNICALL
Java_java_net_NetworkInterface_getNetworkInterfacesImpl (JNIEnv * env,
                                                         jclass clazz)
{
  /* variables to store network interface data returned by call to port library */
  hyNetworkInterfaceArray_struct networkInterfaceArray;
  I_32 result = 0;

  /* variables for class and method objects needed to create bridge to java */
  jclass networkInterfaceClass = NULL;
  jclass inetAddressClass = NULL;
  jclass utilClass = NULL;
  jmethodID methodID = NULL;
  jmethodID utilMid = NULL;

  /* JNI objects used to return values from native call */
  jstring name = NULL;
  jstring displayName = NULL;
  jobjectArray addresses = NULL;
  jobjectArray networkInterfaces = NULL;
  jbyteArray bytearray = NULL;

  /* jobjects used to build the object arrays returned */
  jobject currentInterface = NULL;
  jobject element = NULL;

  /* misc variables needed for looping and determining inetAddress info */
  U_32 i = 0;
  U_32 j = 0;
  U_32 nameLength = 0;

  /* required call if we are going to call port library methods */
  PORT_ACCESS_FROM_ENV (env);

  /* get the classes and methods that we need for later calls */
  networkInterfaceClass =
    (*env)->FindClass (env, "java/net/NetworkInterface");
  if (networkInterfaceClass == NULL)
    {
      throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
      return NULL;
    }

  inetAddressClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (inetAddressClass == NULL)
    {
      throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
      return NULL;
    }

  methodID =
    (*env)->GetMethodID (env, networkInterfaceClass, "<init>",
                         "(Ljava/lang/String;Ljava/lang/String;[Ljava/net/InetAddress;I)V");
  if (methodID == NULL)
    {
      throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
      return NULL;
    }

  utilClass = (*env)->FindClass (env, "org/apache/harmony/luni/util/Util");
  if (!utilClass)
    {
      return NULL;
    }

  utilMid =
    ((*env)->
     GetStaticMethodID (env, utilClass, "toString",
                        "([BII)Ljava/lang/String;"));
  if (!utilMid)
    return NULL;

  result =
    hysock_get_network_interfaces (&networkInterfaceArray,
                                   preferIPv4Stack (env));

  if (result < 0)
    {
      /* this means an error occured.  The value returned is the socket error that should be returned */
      throwJavaNetSocketException (env, result);
      return NULL;
    }

  /* now loop through the interfaces and extract the information to be returned */
  for (j = 0; j < networkInterfaceArray.length; j++)
    {
      /* set the name and display name and reset the addresses object array */
      addresses = NULL;
      name = NULL;
      displayName = NULL;

      if (networkInterfaceArray.elements[j].name != NULL)
        {
          nameLength = strlen (networkInterfaceArray.elements[j].name);
          bytearray = (*env)->NewByteArray (env, nameLength);
          if (bytearray == NULL)
            {
              /* NewByteArray should have thrown an exception */
              return NULL;
            }
          (*env)->SetByteArrayRegion (env, bytearray, (jint) 0, nameLength,
                                      (jbyte *)networkInterfaceArray.elements[j].name);
          name =
            (*env)->CallStaticObjectMethod (env, utilClass, utilMid,
                                            bytearray, (jint) 0, nameLength);
          if ((*env)->ExceptionCheck (env))
            {
              return NULL;
            }
          (*env)->DeleteLocalRef(env, bytearray);
        }

      if (networkInterfaceArray.elements[j].displayName != NULL)
        {
          nameLength = strlen (networkInterfaceArray.elements[j].displayName);
          bytearray = (*env)->NewByteArray (env, nameLength);
          if (bytearray == NULL)
            {
              /* NewByteArray should have thrown an exception */
              return NULL;
            }
          (*env)->SetByteArrayRegion (env, bytearray, (jint) 0, nameLength,
                                      (jbyte *)networkInterfaceArray.elements[j].
                                      displayName);
          displayName =
            (*env)->CallStaticObjectMethod (env, utilClass, utilMid,
                                            bytearray, (jint) 0, nameLength);
          if ((*env)->ExceptionCheck (env))
            {
              return NULL;
            }
          (*env)->DeleteLocalRef(env, bytearray);
        }

      /* generate the object with the inet addresses for the interface       */
      for (i = 0; i < networkInterfaceArray.elements[j].numberAddresses; i++)
        {
          element = newJavaNetInetAddressGenericB (env,
                                                   (jbyte *)networkInterfaceArray.
                                                   elements[j].addresses[i].
                                                   addr.bytes,
                                                   networkInterfaceArray.
                                                   elements[j].addresses[i].
                                                   length,
                                                   networkInterfaceArray.
                                                   elements[j].addresses[i].
                                                   scope);
          if (i == 0)
            {
              addresses =
                (*env)->NewObjectArray (env,
                                        networkInterfaceArray.elements[j].
                                        numberAddresses, inetAddressClass,
                                        element);
            }
          else
            {
              (*env)->SetObjectArrayElement (env, addresses, i, element);
            }
        }

      /* now create the NetworkInterface object for this interface and
         then add it to the array that will be returned */
      currentInterface =
        (*env)->NewObject (env, networkInterfaceClass, methodID, name,
                           displayName, addresses,
                           networkInterfaceArray.elements[j].index);

      (*env)->DeleteLocalRef(env, name);
      (*env)->DeleteLocalRef(env, addresses);

      if (j == 0)
        {
          networkInterfaces =
            (*env)->NewObjectArray (env, networkInterfaceArray.length,
                                    networkInterfaceClass, currentInterface);
        }
      else
        {
          (*env)->SetObjectArrayElement (env, networkInterfaces, j,
                                         currentInterface);
        }
          
      (*env)->DeleteLocalRef(env, currentInterface);

    }

  /* free the memory for the interfaces struct and return the new NetworkInterface List */
  hysock_free_network_interface_struct (&networkInterfaceArray);
  return networkInterfaces;
}

/**
* Answer whether a specific NetworkInterface is up.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is up
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isUpImpl
(JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformIsUp(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface is Loopback.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is a loopback one
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isLoopbackImpl (JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformIsLoopback(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface is up.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is a point to point one
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isPoint2PointImpl
(JNIEnv * env, jobject obj, jstring ifname, jint jindex)

{
	return getPlatformIsPoint2Point(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface supports multicast.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface supports multicast
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_supportMulticastImpl (JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformSupportMulticast(env, ifname, jindex);
}

JNIEXPORT jint JNICALL
Java_java_net_NetworkInterface_getMTUImpl (JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	return getPlatformMTU(env, ifname, index);
}

char* 
convertInterfaceName(JNIEnv * env, jstring ifname)
{
	char * interfaceName = 0;
	jsize nameLen = 0;
	const jchar * p;
	int l = 0;

	PORT_ACCESS_FROM_ENV (env);

	nameLen = (*env)->GetStringLength (env, ifname);
	interfaceName= hymem_allocate_memory (sizeof(char) * (nameLen + 1));
#if defined(VALIDATE_ALLOCATIONS)
	if (NULL == interfaceName){
		return NULL;
	}
#endif
	p = (*env)->GetStringChars(env, ifname, NULL);
	for(; l < nameLen; l++) {
		interfaceName[l] = (char)p[l]; 
	}
	interfaceName[nameLen] = '\0';
	return interfaceName;
}

I_32
freeInterfaceAddressArray(JNIEnv * env, struct interfaceAddressArray_struct * array)
{
	U_32 i = 0;

	PORT_ACCESS_FROM_ENV (env);

	if ((array != NULL) && (array->elements != NULL))
	{
		/* free the allocated memory in each of the structures */
		for (i = 0; i < array->length; i++)
		{
			if (array->elements[i].address != NULL)
			{
				hymem_free_memory(array->elements[i].address);
			}
		}

		/* now free the array itself */
		hymem_free_memory(array->elements);
	}
	return 0;
}

JNIEXPORT jobjectArray JNICALL
Java_java_net_NetworkInterface_getInterfaceAddressesImpl (JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	U_32 i = 0;
	I_32 result = 0;

	interfaceAddressArray_struct interfaceAddressArray;

	jobjectArray interfaceAddresses = NULL;
	jobject currentInterfaceAddress = NULL;
	jclass interfaceAddressClass = NULL;
	jmethodID methodID = NULL;

	interfaceAddressClass =
		(*env)->FindClass (env, "java/net/InterfaceAddress");
	if (interfaceAddressClass == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	methodID =
		(*env)->GetMethodID (env, interfaceAddressClass, "<init>",
		"(Ljava/net/InetAddress;S)V");
	if (methodID == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	// initialize interfaceAddressArray struct
	interfaceAddressArray.elements = NULL;
	interfaceAddressArray.length = 0;
	
	// get the interface address info according to different system calls.
    
	result = getPlatformInterfaceAddresses(env, ifname, index, &interfaceAddressArray);

	if (result != 0) {
		/* this means an error occured.  The value returned is the socket error that should be returned */
		throwJavaNetSocketException (env, result);
		return NULL;
	}

    // use JNI to encapsulate interfaceAddress_struct to jobject InterfaceAddress
	for (i = 0; i < interfaceAddressArray.length; i++)
	{
		jobject inetAddress = NULL;
		jshort prefix = 0;

		inetAddress = newJavaNetInetAddressGenericB (env,
			(jbyte *)interfaceAddressArray.elements[i].address->addr.bytes,
            interfaceAddressArray.elements[i].address->length,
			interfaceAddressArray.elements[i].address->scope);

		prefix = (jshort) interfaceAddressArray.elements[i].prefixLength;

		currentInterfaceAddress = (*env)->NewObject (env,
			interfaceAddressClass, methodID, 
			inetAddress, prefix);

        if (i == 0)
		{
			interfaceAddresses =
				(*env)->NewObjectArray (env, interfaceAddressArray.length,
				interfaceAddressClass, currentInterfaceAddress);
		}
		else
		{
			(*env)->SetObjectArrayElement (env, interfaceAddresses, i,
				currentInterfaceAddress);
		}
	}
      
	freeInterfaceAddressArray(env, &interfaceAddressArray);
	
	return interfaceAddresses;
}

jbyteArray getPlatformHardwareAddress(JNIEnv * env, jstring ifname, jint index);

JNIEXPORT jbyteArray JNICALL
Java_java_net_NetworkInterface_getHardwareAddressImpl(JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	return getPlatformHardwareAddress(env, ifname, index);
}
