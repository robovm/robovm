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

#if defined(FREEBSD) || defined(AIX) || defined(DARWIN) || defined(ZOS)
#include <sys/types.h>
#include <netinet/in.h>

#if defined(FREEBSD)
/*
 * Function definition is in <sys/uio.h> under FreeBSD.
 */
#include <sys/uio.h>
#endif /* FREEBSD */
#endif

#if !defined(ZOS)
#include <sys/poll.h>
#else
/* poll.h in a different location on zOS */
#include <poll.h>
#endif

#include <sys/ioctl.h>

/* We do not get these header files "for free" on zOS, so we will use the
  definitions for these structures defined in OSNetworkSystem.h */
#if !defined(ZOS)
#include <netinet/in_systm.h>
#include <netinet/ip.h>
#if !defined(IOS)
#include <netinet/ip_icmp.h>
#endif
#endif /* !ZOS */

#include <string.h>

#include "nethelp.h"
#include "harmonyglob.h"
#include "helpers.h"
#include "hysock.h"
#include "socket.h"
#include "hyport.h"
#include "jni.h"
#include "OSNetworkSystem.h"
#define NOPRIVILEGE -1
#define UNREACHABLE -2
#define REACHABLE 0
#define INVALID_SOCKET -1
#define SOCKET_ERROR -1

unsigned short ip_checksum(unsigned short * buffer, int size);
#if !defined(ZOS) && !defined(IOS)
void set_icmp_packet(struct icmp * icmp_hdr, int packet_size);
#else
void set_icmp_packet(struct ICMPHeader * icmp_hdr, int packet_size);
#endif

// Alternative Select function
int
selectRead
(JNIEnv * env, hysocket_t hysocketP, I_32 uSecTime, BOOLEAN accept)
{
  I_32 result = 0;
  I_32 timeout;
  struct pollfd my_pollfd;

  timeout = uSecTime >= 0 ? TO_MILLIS(0, uSecTime) : -1;
  my_pollfd.fd = hysocketP->sock;
  my_pollfd.events = POLLIN | POLLPRI;
  my_pollfd.revents = 0;
  do {
    result = poll (&my_pollfd, 1, timeout);
  } while (result == -1 && errno == EINTR);
  
  if (result == 0)
    return HYPORT_ERROR_SOCKET_TIMEOUT;

  if (result == -1)
    return HYPORT_ERROR_SOCKET_OPFAILED;

  return result;
}


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createServerStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_createServerStreamSocket
  (JNIEnv * env, jobject thiz, jobject thisObjFD, jboolean preferIPv4Stack)
{
  PORT_ACCESS_FROM_ENV (env);
  hysocket_t socketP;
  BOOLEAN value = TRUE;

  createSocket(env, thisObjFD, HYSOCK_STREAM, preferIPv4Stack);

  /* Check if any exception occurred creating the socket */
  if ((*env)->ExceptionCheck(env)) {
    return;
  }

  /* Also sets HY_SO_REUSEADDR = TRUE on Linux only */
  socketP =
    (hysocket_t) getJavaIoFileDescriptorContentsAsAPointer(env, thisObjFD);
  hysock_setopt_bool (socketP, HY_SOL_SOCKET, HY_SO_REUSEADDR, &value);
}


JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_isReachableByICMPImpl
  (JNIEnv * env, jobject thiz, jobject address, jobject localaddr, jint ttl, jint timeout)
{
  PORT_ACCESS_FROM_ENV (env);
  struct sockaddr_in dest,source,local;
#if !defined(ZOS) && !defined(IOS)
  struct icmp * send_buf = 0;
  struct ip * recv_buf = 0;
  struct icmp* icmphdr = 0;
#else /* !ZOS */
  struct ICMPHeader* send_buf = 0;
  struct IPHeader* recv_buf = 0;
  struct ICMPHeader* icmphdr = 0;
#endif /* !ZOS */
  int result,ret=UNREACHABLE;
  struct pollfd my_pollfd;
  int sockadd_size = sizeof (source);
  jbyte host[HYSOCK_INADDR6_LEN];
  U_32 length;
  unsigned short header_len = 0;

  int sock = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
  if (INVALID_SOCKET == sock){
	return NOPRIVILEGE;
  }
  setuid(getuid());
  if (0 < ttl){
	  if (0 > setsockopt(sock, IPPROTO_ICMP, IP_TTL, (char*)&ttl,
	          sizeof(ttl))) {
	        return NOPRIVILEGE;
	  }
  }
  
  memset(&dest, 0, sizeof(dest));

  // set address
  netGetJavaNetInetAddressValue (env, address,(U_8 *)host, &length);
  memset(&dest, 0, sizeof(dest));	  
  memcpy (&dest.sin_addr.s_addr,(U_8 *)host, length);
  dest.sin_family = AF_INET;

  if(NULL != localaddr){
    memset(&local, 0, sizeof(local));
    netGetJavaNetInetAddressValue (env, localaddr,(U_8 *)host, &length);
    memcpy (&local.sin_addr.s_addr,(U_8 *)host, length);
    bind(sock, (struct sockaddr *)& local, sizeof(local));
  }

  send_buf = (struct icmp*)hymem_allocate_memory(sizeof(char)*ICMP_SIZE);
  if (NULL == send_buf) {
    ret = NOPRIVILEGE;
    goto cleanup;
  }
  recv_buf = (struct ip*)hymem_allocate_memory(sizeof(char)*PACKET_SIZE);
  if (NULL == recv_buf) {
    ret = NOPRIVILEGE;
    goto cleanup;
  }
  set_icmp_packet(send_buf, ICMP_SIZE);

  if(SOCKET_ERROR == sendto(sock, (char*)send_buf, ICMP_SIZE, 0,
            (struct sockaddr*)&dest, sizeof(dest))){
            goto cleanup;
  }

  //don't ask what is it - just kinda sleep
  my_pollfd.fd = 0;
  my_pollfd.events = 0;
  result = poll(&my_pollfd, 1, timeout);

  my_pollfd.fd = sock;
  my_pollfd.events = POLLIN | POLLPRI;
  result = poll(&my_pollfd, 1, timeout);

  if (SOCKET_ERROR == result || 0 == result){
  	goto cleanup;
  }  
  result = recvfrom(sock, (char*)recv_buf,
            PACKET_SIZE, 0,
            (struct sockaddr*)&source, (unsigned int *)&sockadd_size);

  if (SOCKET_ERROR == result){
  	goto cleanup;
  }  
			    
#if !defined(ZOS) && !defined(IOS)
  header_len = recv_buf->ip_hl << 2;
  icmphdr = (struct icmp*)((char*)recv_buf + header_len);
  if ((result < header_len + ICMP_SIZE)||
	(icmphdr->icmp_type != ICMP_ECHO_REPLY)||
	(icmphdr->icmp_id != getpid())) {	
	if (!(icmphdr->icmp_type == ICMP_ECHO_REQUEST && icmphdr->icmp_seq == 0))
		goto cleanup;
  }
#else
  header_len = recv_buf->h_len << 2;
  icmphdr = (struct ICMPHeader*)((char*)recv_buf + header_len);
  if ((result < header_len + ICMP_SIZE)||
	(icmphdr->type != ICMP_ECHO_REPLY)||
	(icmphdr->id != getpid())) {	
	if (!(icmphdr->type == ICMP_ECHO_REQUEST && icmphdr->seq == 0))
		goto cleanup;
  }
#endif
  ret = REACHABLE;
cleanup:

  if (send_buf != NULL) {
      hymem_free_memory(send_buf);
  }

  if (recv_buf != NULL) {
      hymem_free_memory(recv_buf);
  }

  return ret;
}

// typical ip checksum
unsigned short ip_checksum(unsigned short* buffer, int size)
{
	register unsigned short * buf = buffer;
    register int bufleft = size;
    register unsigned long sum = 0;
    
    while (bufleft > 1) {
        sum = sum + (*buf++);
        bufleft = bufleft - sizeof(unsigned short );
    }
    if (bufleft) {
        sum = sum + (*(unsigned char*)buf);
    }
    sum = (sum >> 16) + (sum & 0xffff);
    sum += (sum >> 16);
   
    return (unsigned short )(~sum);
}

#if !defined(ZOS) && !defined(IOS)
void set_icmp_packet(struct icmp* icmp_hdr, int packet_size)
{

    icmp_hdr->icmp_type = ICMP_ECHO_REQUEST;
    icmp_hdr->icmp_code = 0;
    icmp_hdr->icmp_cksum = 0;
    icmp_hdr->icmp_id = getpid();
    icmp_hdr->icmp_seq = 0;

    // Calculate a checksum on the result
    icmp_hdr->icmp_cksum = ip_checksum((unsigned short*)icmp_hdr, packet_size);
}
#else
void set_icmp_packet(struct ICMPHeader* icmp_hdr, int packet_size)
{

    icmp_hdr->type = ICMP_ECHO_REQUEST;
    icmp_hdr->code = 0;
    icmp_hdr->checksum = 0;
    icmp_hdr->id = getpid();
    icmp_hdr->seq = 0;

    // Calculate a checksum on the result
    icmp_hdr->checksum = ip_checksum((unsigned short*)icmp_hdr, packet_size);
}
#endif


/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 * Assumption: outFlags is zeroed
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_selectImpl	
  (JNIEnv * env, jobject thiz, jobjectArray readFDArray, jobjectArray writeFDArray,
   jint	countReadC, jint countWriteC, jintArray	outFlags, jlong	timeout)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result =	0;		
  hysocket_t hysocketP;		
  jboolean isCopy ;
  jint *flagArray;
  int val;
  struct pollfd * my_pollfds;
  int n_pollfds;
  jobject gotFD;

  n_pollfds = countReadC + countWriteC;

  my_pollfds = hymem_allocate_memory(sizeof(struct pollfd) * n_pollfds);
  if (my_pollfds == NULL) {
      return HYPORT_ERROR_SYSTEMFULL;
  }

  for (val=0; val<countReadC; val++) {
	  gotFD	= (*env)->GetObjectArrayElement(env, readFDArray, val);
	  hysocketP = getJavaIoFileDescriptorContentsAsAPointer	(env, gotFD);
      (*env)->DeleteLocalRef(env, gotFD);

      /* hysocketP is -1 if the socket is closed */
      my_pollfds[val].fd = (IDATA)hysocketP == -1 ? -1 : hysocketP->sock;
      my_pollfds[val].events = POLLIN | POLLPRI;
      my_pollfds[val].revents = 0;
  }

  for (val=0; val<countWriteC; val++) {
	  gotFD	= (*env)->GetObjectArrayElement(env, writeFDArray, val);
	  hysocketP = getJavaIoFileDescriptorContentsAsAPointer	(env, gotFD);
      (*env)->DeleteLocalRef(env, gotFD);

      /* hysocketP is -1 if the socket is closed */
      my_pollfds[countReadC + val].fd = (IDATA)hysocketP == -1 ? -1 : hysocketP->sock;
      my_pollfds[countReadC + val].events = POLLOUT;
      my_pollfds[countReadC + val].revents = 0;
  }

  result = poll(my_pollfds, n_pollfds, timeout);

  if (result > 0) {
          int changed = 0; /* Record if we actually change the IntArray */
	  /* output result to int array */
	  flagArray = (*env)->GetIntArrayElements(env, outFlags, &isCopy);
	  for (val=0; val<countReadC; val++) {
          if (my_pollfds[val].revents & (POLLIN | POLLPRI)) {
              flagArray[val] = SOCKET_OP_READ;
              changed=1;
          }
      }

	  for (val=0; val<countWriteC; val++) {
          if (my_pollfds[val+countReadC].revents & POLLOUT) {
              flagArray[val+countReadC] = SOCKET_OP_WRITE;
              changed=1;
          }
      }
          (*env)->ReleaseIntArrayElements(env, outFlags, flagArray, changed ? 0 : JNI_ABORT);
  } else if (result == 0) {

    result = HYPORT_ERROR_SOCKET_TIMEOUT;

  } else {

    if (errno == EINTR) {
      result = HYPORT_ERROR_SOCKET_INTERRUPTED;
    } else {
      result = HYPORT_ERROR_SOCKET_OPFAILED;
    }

  }
      
  hymem_free_memory(my_pollfds);
  
  /* return both correct and error result, let java code handle	exceptions */
  return result;
}


JNIEXPORT jobject JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_inheritedChannel
  (JNIEnv * env , jobject thiz)
{
    PORT_ACCESS_FROM_ENV (env);
    int socket = 0;
    int opt;
    int length = sizeof(opt);
    int socket_type;
    struct sockaddr_in local_addr;
    struct sockaddr_in remote_addr;
    jclass channel_class, socketaddr_class,serverSocket_class,socketImpl_class;
    jobject channel_object = NULL, socketaddr_object, serverSocket_object,socketImpl_object;
	jfieldID port_field, socketaddr_field, bound_field;
	jfieldID serverSocket_field,socketImpl_field;
	hysocket_t sock;
	jbyte * address;
	jbyte * localAddr;
	jboolean jtrue = TRUE;

	if(0 != getsockopt(socket,SOL_SOCKET,SO_TYPE,&opt,(unsigned int *)&length)){
		return NULL;
	}
	if(SOCK_STREAM !=opt && SOCK_DGRAM !=opt){
		return NULL;
	}
	socket_type = opt;

	length  = sizeof(struct sockaddr);
	if(0 != getsockname(socket,(struct sockaddr *)&local_addr,(unsigned int *)&length)){
		return NULL;
	} else {
		if(AF_INET != local_addr.sin_family || length != sizeof(struct sockaddr)){
			return NULL;
		}
		localAddr = hymem_allocate_memory(sizeof(jbyte)*4);
		if (NULL == localAddr){
			return NULL;
		}
		memcpy (localAddr, &(local_addr.sin_addr.s_addr), 4); 
	}
	if(0 != getpeername(socket,(struct sockaddr *)&remote_addr,(unsigned int *)&length))	{
		remote_addr.sin_port = 0;
                remote_addr.sin_addr.s_addr = 0;
		address = hymem_allocate_memory(sizeof(jbyte)*4);
                if (NULL == address) {
                  goto clean;
                }
		bzero(address,sizeof(jbyte)*4);
	} else {
		if(AF_INET != remote_addr.sin_family || length != sizeof(struct sockaddr))	{
			return NULL;
		}
		address = hymem_allocate_memory(sizeof(jbyte)*4);
                if (NULL == address) {
                  goto clean;
                }
		memcpy (address, &(remote_addr.sin_addr.s_addr), 4);
	}
	sock = hymem_allocate_memory(sizeof(hysocket_struct));
        /* TODO: where is sock free'd? */
        if (NULL == sock) {
          goto clean;
        }
	sock->sock = socket;
	sock->family = AF_INET;

	// analysis end, begin pack to java
        if(SOCK_STREAM == opt)
        {	  
	  if(remote_addr.sin_port!=0){
		//socket
		channel_class = (*env)->FindClass(env,"org/apache/harmony/nio/internal/SocketChannelImpl");
        	if(NULL == channel_class) {
                    hymem_free_memory(sock);
        	    goto clean;
	        }
		channel_object = getJavaNioChannelsSocketChannelImplObj(env,channel_class);
          	if(NULL == channel_object) {
                    hymem_free_memory(sock);
	            goto clean;
        	}
		// new and set FileDescript
		setFDContent(env, channel_class, channel_object,&sock);
		// local port
		setJavaNioChannelsLocalPort(env,channel_class,channel_object,ntohs(local_addr.sin_port));
	  	// new and set remote addr
		setSocketAddressContent(env, channel_class,channel_object,address);
		// localAddr
		socketaddr_class = (*env)->FindClass(env,"java/net/InetSocketAddress");
		socketaddr_field = (*env)->GetFieldID(env,channel_class,"connectAddress","Ljava/net/InetSocketAddress;");
		socketaddr_object = (*env)->GetObjectField(env,channel_object,socketaddr_field);
		setSocketLocalAddressContent(env,channel_class,channel_object,localAddr);
		// set port
		port_field = (*env)->GetFieldID(env,socketaddr_class,"port","I");
		(*env)->SetIntField(env,socketaddr_object, port_field, ntohs(remote_addr.sin_port));
		// set bound
		if (0 != local_addr.sin_port){
			bound_field = (*env)->GetFieldID(env,channel_class,"isBound","Z");
			(*env)->SetBooleanField(env,channel_object, bound_field,jtrue);
		}
	  } else {
                // sock isn't used on this code path so we should free it
                hymem_free_memory(sock);
		//serverSocket	
		channel_class = (*env)->FindClass(env,"org/apache/harmony/nio/internal/ServerSocketChannelImpl");
        	if(NULL == channel_class) {
        	    goto clean;
	        }
		channel_object = getJavaNioChannelsSocketChannelImplObj(env,channel_class);
          	if(NULL == channel_object) {
	            goto clean;
        	}
		serverSocket_field = (*env)->GetFieldID(env,channel_class,"socket","Ljava/net/ServerSocket;");	
		serverSocket_class = (*env)->FindClass(env,"Ljava/net/ServerSocket;");
		serverSocket_object = (*env)->GetObjectField(env,channel_object,serverSocket_field);	
		// set bound
		if (0 != local_addr.sin_port){		
			bound_field = (*env)->GetFieldID(env,channel_class,"isBound","Z");
			(*env)->SetBooleanField(env,channel_object, bound_field,jtrue);			
			bound_field = (*env)->GetFieldID(env,serverSocket_class,"isBound","Z");
			(*env)->SetBooleanField(env,serverSocket_object, bound_field,jtrue);
		}
		// localAddr
		socketImpl_class = (*env)->FindClass(env,"java/net/SocketImpl");
		socketImpl_field = (*env)->GetFieldID(env,channel_class,"impl","Ljava/net/SocketImpl;");
		socketImpl_object =  (*env)->GetObjectField(env,channel_object,socketImpl_field);
		if(NULL == socketImpl_object) {
	             goto clean;
        	}
		setServerSocketLocalAddressContent(env, socketImpl_class, socketImpl_object, localAddr);
		// set port
		port_field = (*env)->GetFieldID(env,socketImpl_class,"localport","I");
		(*env)->SetIntField(env,socketImpl_object, port_field, ntohs(local_addr.sin_port));
	  }	
        }
        else
        {
          //Datagram Socket
	  // new DatagramChannel
	  channel_class = (*env)->FindClass(env,"org/apache/harmony/nio/internal/DatagramChannelImpl");
          if(NULL == channel_class) {
              hymem_free_memory(sock);
              goto clean;
          }
          channel_object = getJavaNioChannelsSocketChannelImplObj(env,channel_class);
          if(NULL == channel_object) {
              hymem_free_memory(sock);
              goto clean;
          }
	  // new and set FileDescript
	  setFDContent(env,channel_class,channel_object,&sock);
	  setJavaNioChannelsLocalPort(env,channel_class,channel_object,ntohs(local_addr.sin_port));
	  // new and set remote addr
	  setSocketAddressContent(env, channel_class,channel_object,address);
	  // set bound
	  if (0 != local_addr.sin_port){		
		bound_field = (*env)->GetFieldID(env,channel_class,"isBound","Z");
		(*env)->SetBooleanField(env,channel_object, bound_field,jtrue);			
	  }
        }	
 clean:
	hymem_free_memory(address);
	hymem_free_memory(localAddr);
	return channel_object;
}

/*
 * Utilizes the ioctl call to get the available bytes pending on a socket
 * which is similar to, but different to the call on other platforms.
 *
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    availableStream
 * Signature: (Ljava/io/FileDescriptor;)I
 */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_availableStream
  (JNIEnv * env, jobject thiz, jobject fileDescriptor)
{
  PORT_ACCESS_FROM_ENV(env);
  hysocket_t hysocketP;
  U_32 nbytes = 0;
  I_32 result;

  hysocketP = getJavaIoFileDescriptorContentsAsAPointer(env, fileDescriptor);
  if (!hysock_socketIsValid(hysocketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jint) 0;
  }

  result = ioctl(hysocketP->sock, FIONREAD, &nbytes);
  if (result != 0) {
    throwJavaNetSocketException(env, result);
    return (jint) 0;
  }

  return (jint) nbytes;
}

/**
 * A helper method, call selectRead with a small timeout until read is ready or an error occurs.
 *
 * @param	env						pointer to the JNI library
 * @param	hysocketP				socket pointer
 * @param	timeout				timeout value
 */

I_32
pollSelectRead (JNIEnv * env, jobject fileDescriptor, jint timeout,
                BOOLEAN poll)
{

  I_32 result;
  hysocket_t hysocketP;

    PORT_ACCESS_FROM_ENV (env);
    
    if (!poll) {
        UDATA finishTime;
            
        /* 
         * A zero timeout means wait forever. If not polling, return success
         * and call receive() or accept() to block. 
         */
      
        if (!timeout) {
            return 0;
        }

        finishTime = hytime_msec_clock() + (UDATA) timeout;
    
        SELECT_NOPOLL:
              
        hysocketP = getJavaIoFileDescriptorContentsAsAPointer (env, fileDescriptor);
      
        if (!hysock_socketIsValid (hysocketP)) {
            throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_BADSOCKET);
            return (jint) - 1;
        }
        
        result = hysock_select_read (hysocketP, timeout / 1000,
                            (timeout % 1000) * 1000, FALSE);
    
        /*
         *  if we time out, then throw the InterruptedIO exception
         *  which gets converted by a caller into the appropriate thing
         * 
         *  if we are interrupted, recalculate our timeout and if we 
         *  have time left or 0, try again.  If no time lest, throw InterruptedIO
         *  Exception
         * 
         *  If some other error, just throw exceptionand bail
         */
        if (HYPORT_ERROR_SOCKET_TIMEOUT == result) {
            throwJavaIoInterruptedIOException (env, result);
        }
        else if (HYPORT_ERROR_SOCKET_INTERRUPTED == result) {

            timeout = finishTime - hytime_msec_clock();
            
            if (timeout < 0) {
                throwJavaIoInterruptedIOException (env, result);
            }
            else { 
                goto SELECT_NOPOLL;
            }
        }
        else if (0 > result) {
            throwJavaNetSocketException (env, result);
        }
    }
  else  /* we are polling */
    {
      I_32 pollTimeoutUSec = 100000, pollMsec = 100;
      UDATA finishTime = 0;
      IDATA timeLeft = timeout;
      BOOLEAN hasTimeout = timeout > 0;
      
      if (hasTimeout) {
        finishTime = hytime_msec_clock () + (UDATA) timeout;
      }
      
    select:
      
      /* 
       * Fetch the handle every time in case the socket is closed.
       */
       
      hysocketP =
        getJavaIoFileDescriptorContentsAsAPointer (env, fileDescriptor);
      
      if (!hysock_socketIsValid (hysocketP))
        {
          throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_INTERRUPTED);
          return (jint) - 1;
        }
      
      if (hasTimeout)
        {
          if (timeLeft - 10 < pollMsec) {
            pollTimeoutUSec = timeLeft <= 0 ? 0 : (timeLeft * 1000);
          }
          
          result = hysock_select_read (hysocketP, 0, pollTimeoutUSec, FALSE);

          /*
           *  because we are polling at a time smaller than timeout (presumably)
           *  lets treat an interrupt and timeout the same - go see if we're done
           *  timewise, and then just try again if not
           */         
          if (HYPORT_ERROR_SOCKET_TIMEOUT == result ||
                HYPORT_ERROR_SOCKET_INTERRUPTED == result)
            {
              timeLeft = finishTime - hytime_msec_clock ();
              
              if (timeLeft <= 0) {
                throwJavaIoInterruptedIOException (env, result);
              }                
              else
                {
                  goto select;
                }
            }   
          else if (0 > result) {
            throwJavaNetSocketException (env, result);
          }
        }
      else  /* polling with no timeout (why would you do this?)*/
        {
          result = hysock_select_read (hysocketP, 0, pollTimeoutUSec, FALSE);

          /* 
           *  if interrupted (or a timeout) just retry
           */
          if (HYPORT_ERROR_SOCKET_TIMEOUT == result ||
                HYPORT_ERROR_SOCKET_INTERRUPTED == result)
            {
              goto select;
            }
          else if (0 > result)
            throwJavaNetSocketException (env, result);
        }
    }

  return result;
}

JNIEXPORT jlong JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_writev
(JNIEnv *env, jobject thiz, jobject fd, jobjectArray buffers, jintArray offset, jintArray counts, jint length) {

  PORT_ACCESS_FROM_ENV(env);

  jobject buffer;
  jobject* toBeReleasedBuffers;
  jint *noffset = NULL;
  jboolean isDirectBuffer = JNI_FALSE;
  ssize_t result = 0;
  jclass byteBufferClass;
  struct iovec* vect;
  int i;

  hysocket_t socketP = getJavaIoFileDescriptorContentsAsAPointer(env, fd);

  if (!hysock_socketIsValid(socketP)) {
    throwJavaNetSocketException(env, HYPORT_ERROR_SOCKET_BADSOCKET);
    return (jlong)0;
  }

  vect = (struct iovec*) hymem_allocate_memory(sizeof(struct iovec) * length);
  if (vect == NULL) {
    throwNewOutOfMemoryError(env, "");
    return (jlong)0;
  }

  toBeReleasedBuffers =
    (jobject*) hymem_allocate_memory(sizeof(jobject) * length);
  if (toBeReleasedBuffers == NULL) {
    throwNewOutOfMemoryError(env, "");
    goto free_resources;
  }
  memset(toBeReleasedBuffers, 0, sizeof(jobject)*length);

  byteBufferClass = HARMONY_CACHE_GET (env, CLS_java_nio_DirectByteBuffer);
  noffset = (*env)->GetIntArrayElements(env, offset, NULL);
  if (noffset == NULL) {
    throwNewOutOfMemoryError(env, "");
    goto free_resources;
  }

  for (i = 0; i < length; ++i) {
    jint *cts;
    U_8* base;
    buffer = (*env)->GetObjectArrayElement(env, buffers, i);
    isDirectBuffer = (*env)->IsInstanceOf(env, buffer, byteBufferClass);
    if (isDirectBuffer) {
      base =
        (U_8 *)(jbyte *)(IDATA) (*env)->GetDirectBufferAddress(env, buffer);
      if (base == NULL) {
        throwNewOutOfMemoryError(env, "");
        goto free_resources;
      }
      toBeReleasedBuffers[i] = NULL;
    } else {
      base =
        (U_8 *)(jbyte *)(IDATA) (*env)->GetByteArrayElements(env, buffer, NULL);
      if (base == NULL) {
        throwNewOutOfMemoryError(env, "");
        goto free_resources;
      }
      toBeReleasedBuffers[i] = buffer;
    }
    vect[i].iov_base = base + noffset[i];

    cts = (*env)->GetPrimitiveArrayCritical(env, counts, NULL);
    vect[i].iov_len = cts[i];
    (*env)->ReleasePrimitiveArrayCritical(env, counts, cts, JNI_ABORT);
  }

  result = writev(SOCKET_CAST (socketP), vect, length);

  if (0 > result) {
    if (errno != EAGAIN && errno != EWOULDBLOCK) {
      throwJavaNetSocketException(env, result);
    }
    result = 0;
  }

 free_resources:
  
  if (toBeReleasedBuffers != NULL) {
    for (i = 0; i < length; ++i) {
      if (toBeReleasedBuffers[i] != NULL) {
        (*env)->ReleaseByteArrayElements(env, toBeReleasedBuffers[i],
                                         vect[i].iov_base - noffset[i],
                                         JNI_ABORT);
      }
    }
  }

  if (noffset != NULL) {
    (*env)->ReleaseIntArrayElements(env, offset, noffset, JNI_ABORT);
  }

  hymem_free_memory(toBeReleasedBuffers);
  hymem_free_memory(vect);

  return (jlong)result;
}
