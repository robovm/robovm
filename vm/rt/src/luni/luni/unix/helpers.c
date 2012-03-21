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

#include <errno.h>
#include <sys/socket.h>
#include <netdb.h>
#include <sys/ioctl.h>
#include <net/if.h>
#include <netinet/in.h>

#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/statvfs.h>

#include <utime.h>

#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>

#include <langinfo.h>

#include <dirent.h>

#include "helpers.h"
#include "nethelp.h"
#include "harmonyglob.h"

#if defined(LINUX)
#define HAS_RTNETLINK 1
#endif

#if defined(HAS_RTNETLINK)
#include <asm/types.h>
#include <linux/netlink.h>
#include <linux/rtnetlink.h>

#define NETLINK_DATA_BUFFER_SIZE 2048
#define NETLINK_READTIMEOUT_SECS 20

typedef struct linkReq_struct
{
  struct nlmsghdr netlinkHeader;
  struct ifinfomsg msg;
} linkReq_struct;

typedef struct addrReq_struct
{
  struct nlmsghdr netlinkHeader;
  struct ifaddrmsg msg;
} addrReq_struct;

typedef struct netlinkContext_struct
{
  int netlinkSocketHandle;
  char *buffer;
  int bufferSize;
  struct nlmsghdr *netlinkHeader;
  U_32 remainingLength;
  U_32 done;
} netlinkContext_struct;
#endif

int portCmp (const void **a, const void **b);

/**
 * It is the responsibility of #getPlatformRoots to return a char array
 * with volume names separated by null with a trailing extra null, so for
 * Unix it should be '\<null><null>' .
 */
I_32
getPlatformRoots (char *rootStrings)
{
  rootStrings[0] = (char) '/';
  rootStrings[1] = (char) 0;
  rootStrings[2] = (char) 0;
  return 1;
}

/**
 * Answer 1 if the path is hidden, 0 otherwise even in fail cases.
 */
I_32
getPlatformIsHidden (JNIEnv * env, char *path)
{
  PORT_ACCESS_FROM_ENV (env);

  /* Answer true if the file exists and starts with a period */
  I_32 length = strlen (path), index, existsResult;
  existsResult = hyfile_attr (path);
  if (existsResult < 0)
    return 0;

  if (length == 0)
    return 0;
  for (index = length; index >= 0; index--)
    {
      if (path[index] == '.' && (index > 0 && path[index - 1] == '/'))
        return 1;
    }

  return 0;
}

/**
 * Answer 1 if the file time was updated, 0 otherwise even in fail cases.
 */
I_32
setPlatformLastModified (JNIEnv * env, char *path, I_64 time)
{

  struct stat statbuf;
  struct utimbuf timebuf;
  if (stat (path, &statbuf))
    return FALSE;
  timebuf.actime = statbuf.st_atime;
  timebuf.modtime = (time_t) (time / 1000);
  return utime (path, &timebuf) == 0;

}

/**
 * Answer 1 if the path is now readOnly, 0 otherwise even in fail cases.
 */
I_32
setPlatformReadOnly (JNIEnv * env, char *path)
{
  struct stat buffer;
  mode_t mode;
  if (stat (path, &buffer))
    {
      return 0;
    }
  mode = buffer.st_mode;
  mode = mode & 07555;
  return chmod (path, mode) == 0;

}

/**
 * Answer 1 if the path is now readable, 0 otherwise even in fail cases.
 */
I_32
setPlatformReadable (JNIEnv * env, char *path, jboolean readable, jboolean ownerOnly)
{
  struct stat buffer;
  mode_t mode;
  if (stat (path, &buffer))
    {
      return 0;
    }
  mode = buffer.st_mode;
  if (readable && ownerOnly)
	  mode |= S_IRUSR;
  else if (readable) 
	  mode |= (S_IRUSR | S_IRGRP | S_IROTH);
  else if (ownerOnly)
  	  mode &= (~S_IRUSR);
  else
      mode &= ~(S_IRUSR | S_IRGRP | S_IROTH);
  return chmod (path, mode) == 0;
}

/**
 * Answer 1 if the path is now writable, 0 otherwise even in fail cases.
 */
I_32
setPlatformWritable (JNIEnv * env, char *path, jboolean writable, jboolean ownerOnly)
{
	
  struct stat buffer;
  mode_t mode;
  if (stat (path, &buffer))
    {
      return 0;
    }
  mode = buffer.st_mode;
  if (writable && ownerOnly)
	  mode |= S_IWUSR;
  else if (writable) 
	  mode |= (S_IWUSR | S_IWGRP | S_IWOTH);
  else if (ownerOnly)
  	  mode &= (~S_IWUSR);
  else
      mode &= ~(S_IWUSR | S_IWGRP | S_IWOTH);
  return chmod (path, mode) == 0;
}
/**
 * Answer 1 if the file length was set, 0 otherwise even in fail cases.
 */
I_32
setPlatformFileLength (JNIEnv * env, IDATA descriptor, jlong newLength)
{

  return (ftruncate ((int) descriptor, newLength) == 0);

}

void
setPlatformBindOptions (JNIEnv * env, hysocket_t socketP)
{
  PORT_ACCESS_FROM_ENV (env);
  BOOLEAN value = TRUE;

  hysock_setopt_bool (socketP, HY_SOL_SOCKET, HY_SO_REUSEADDR, &value);
}

/**
 * Answer 1 if the path is read-only, 0 otherwise even in fail cases.
 */
I_32
getPlatformIsReadOnly (JNIEnv * env, char *path)
{
	return access(path, W_OK) !=0;
}

/**
 * Answer 1 if the path is write-only, 0 otherwise even in fail cases.
 */
I_32
getPlatformIsWriteOnly (JNIEnv * env, char *path)
{
  return access(path, R_OK) !=0;
}

jlong getPlatformTotal (JNIEnv * env, char *path) {
	struct statvfs fs_buf;
	jlong total_size;
	int ret;
	if((ret = statvfs(path, &fs_buf) < 0))
	{
		return 0l;
	}
	total_size = fs_buf.f_blocks*(fs_buf.f_bsize/1024.0);
	total_size *= 1024;
	return total_size;
}

jlong getPlatformUsableTotal (JNIEnv * env, char *path) {
    struct statvfs fs_buf;
	jlong total_size;
	int ret;
	if((ret = statvfs(path, &fs_buf) < 0))
	{
		return 0l;
	}
	total_size = fs_buf.f_bavail*(fs_buf.f_bsize/1024.0);
	total_size *= 1024;
	return total_size;
}

jlong getPlatformFreeTotal (JNIEnv * env, char *path) {
	struct statvfs fs_buf;
	jlong total_size;
	int ret;
	if((ret = statvfs(path, &fs_buf) < 0))
	{
		return 0l;
	}
	total_size = fs_buf.f_bfree*(fs_buf.f_bsize/1024.0);
	total_size *= 1024;
	return total_size;
}

/* Resolve link if it is a symbolic link and put the result in link. */
int
platformReadLink (char *link)
{

  int size = readlink (link, link, HyMaxPath-1);
  if (size <= 0)
    return FALSE;
  link[size] = 0;
  return TRUE;

}

jstring
getCustomTimeZoneInfo (JNIEnv * env, jintArray tzinfo,
                       jbooleanArray isCustomTimeZone)
{
    time_t curTime;
    struct tm *tmStruct;
    char tzInfo[9];
    int h, m;
    jboolean fls;

    time(&curTime);
    //curTime += 15552000l;
    tmStruct = localtime(&curTime);
    // timezone is now set to time zone offset
    // tmStruct->tm_isdst is set to 1 if DST is in effect
    strcpy(tzInfo, "GMT");
    tzInfo[3] = timezone > 0 ? '-' : '+';
#if defined (FREEBSD) || defined(DARWIN)
    h = labs(tmStruct->tm_gmtoff) / 3600;
#else /* !FREEBSD */
    h = labs(timezone) / 3600;
#endif /* FREEBSD */
    if (tmStruct->tm_isdst) {
        if (timezone > 0) {
            h--;
        } else {
            h++;
        }
    }
#if defined (FREEBSD) || defined(DARWIN)
    m = (labs(tmStruct->tm_isdst) % 3600) / 60;
#else /* !FREEBSD */
    m = (labs(timezone) % 3600) / 60;
#endif /* FREEBSD */
   tzInfo[4] = h / 10 + '0';
    tzInfo[5] = h % 10 + '0';
    tzInfo[6] = m / 10 + '0';
    tzInfo[7] = m % 10 + '0';
    tzInfo[8] = 0;

    fls = JNI_FALSE;

    (*env)->SetBooleanArrayRegion(env, isCustomTimeZone, 0, 1, &fls);
    return (*env)->NewStringUTF(env, tzInfo);
}

void
setDefaultServerSocketOptions (JNIEnv * env, hysocket_t socketP)
{
  PORT_ACCESS_FROM_ENV (env);
  BOOLEAN value = TRUE;

  hysock_setopt_bool (socketP, HY_SOL_SOCKET, HY_SO_REUSEADDR, &value);
}

jboolean 
getPlatformNetworkInterfaceAttribute(JNIEnv * env, jstring ifname, u_long iiFlag)
{    
	struct ifconf ifc;
	char buff[2048];
	int fd, i, n, result;
	struct ifreq *ifr = NULL;	
       char* interfaceName = 0;

       /* required call if we are going to call port library methods */
	PORT_ACCESS_FROM_ENV (env);

	if ((fd = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
                return 0;
	}

	memset(buff, 0, sizeof(buff));
	ifc.ifc_len = sizeof(buff);
	ifc.ifc_buf = buff;
	if (ioctl(fd, SIOCGIFCONF, (char *)&ifc) != 0) {
		close(fd);	
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);	
              return 0;	
	}

	ifr = ifc.ifc_req;
	n = ifc.ifc_len / sizeof(struct ifreq);

      interfaceName = convertInterfaceName(env, ifname);         

	/* Loop through interfaces, looking for given IP address */
	for (i=0; i<n; i++) {		
		if((ioctl(fd, SIOCGIFADDR, &ifr[i]) == 0) 
			&& (strcmp(interfaceName, ifr[i].ifr_name) == 0)) {	       
				if ( (result = ioctl(fd, SIOCGIFFLAGS, &ifr[i])) != 0) {
					close(fd);
					throwJavaNetSocketException (env, result); 
                                   hymem_free_memory(interfaceName);
      	   	                     return 0;	
				}
				close(fd);
                           hymem_free_memory(interfaceName);
			      return (ifr[i].ifr_flags & iiFlag) == iiFlag;	        
			}		
	}	
	close(fd);
       hymem_free_memory(interfaceName);
	return 0;
}


jboolean
getPlatformIsUp(JNIEnv * env, jstring ifname, jint jindex)
{
   return getPlatformNetworkInterfaceAttribute(env, ifname, IFF_UP);
}

jboolean
getPlatformIsLoopback(JNIEnv * env, jstring ifname, jint jindex)
{
    return getPlatformNetworkInterfaceAttribute(env, ifname, IFF_LOOPBACK);	
}

jboolean
getPlatformIsPoint2Point(JNIEnv * env, jstring ifname, jint jindex)
{
    return getPlatformNetworkInterfaceAttribute(env, ifname, IFF_POINTOPOINT);	
}

jboolean
getPlatformSupportMulticast(JNIEnv * env, jstring ifname, jint jindex)
{
    return getPlatformNetworkInterfaceAttribute(env, ifname, IFF_MULTICAST);	
}

jint
getPlatformMTU(JNIEnv * env, jstring ifname, jint index)
{
   int mtu = 0;
#ifdef SIOCGIFMTU
   struct ifconf ifc;
   char buff[2048];
   int fd, i, n, result;
   struct ifreq *ifr = NULL;
   char * interfaceName = 0;	

	/* required call if we are going to call port library methods */
	PORT_ACCESS_FROM_ENV (env);

	if ((fd = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return 0;
	}
	
	memset(buff, 0, sizeof(buff));
	ifc.ifc_len = sizeof(buff);
	ifc.ifc_buf = buff;
	if (ioctl(fd, SIOCGIFCONF, (char *)&ifc) != 0) {
		close(fd);	
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return 0; 
	}

	ifr = ifc.ifc_req;
	n = ifc.ifc_len / sizeof(struct ifreq);
	        
	interfaceName = convertInterfaceName (env, ifname);
	/* Loop through interfaces, looking for given interface name */
	for (i=0; i<n; i++) {
		if((ioctl(fd, SIOCGIFADDR, &ifr[i]) == 0) 
			&& (strcmp(interfaceName, ifr[i].ifr_name) == 0)) {	       
			if ( (result = ioctl(fd, SIOCGIFMTU, &ifr[i])) != 0) {
				close(fd);
                            hymem_free_memory (interfaceName);	
				throwJavaNetSocketException (env, result);  
				return 0;     	   	
			}
			mtu = ifr[i].ifr_mtu;
                     break;
		}		
	}	
	close(fd);
	hymem_free_memory (interfaceName);	
#endif
	return mtu;
}

jbyteArray 
getPlatformHardwareAddress(JNIEnv * env, jstring ifname, jint index)
{
    jbyteArray bytearray = NULL;
#if defined(SIOCGIFHWADDR)
    const int MAC_ADDR_SIZE = 6;
	struct ifconf ifc;
	char buff[2048];
	int fd, i, j, n, result;
	struct ifreq *ifr = NULL;
	char * interfaceName = 0;
	char array[MAC_ADDR_SIZE];
	jboolean isEmpty = JNI_TRUE; 

	/* required call if we are going to call port library methods */
	PORT_ACCESS_FROM_ENV (env);

	if ((fd = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL; 
	}

	memset(buff, 0, sizeof(buff));
	ifc.ifc_len = sizeof(buff);
	ifc.ifc_buf = buff;
	if (ioctl(fd, SIOCGIFCONF, (char *)&ifc) != 0) {
		close(fd);	
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);		
		return NULL; 
	}

	ifr = ifc.ifc_req;
	n = ifc.ifc_len / sizeof(struct ifreq);

	// Transfer ifname to type char*
       interfaceName = convertInterfaceName (env, ifname);

	/* Loop through interfaces, looking for given IP address */
	for (i=0; i<n; i++) {
	   if((ioctl(fd, SIOCGIFADDR, &ifr[i]) == 0) 
		&& (strcmp(interfaceName, ifr[i].ifr_name) == 0)) {	       
		if ( (result = ioctl(fd, SIOCGIFHWADDR, &ifr[i])) != 0) {
			close(fd);
                     hymem_free_memory (interfaceName);	
	       	throwJavaNetSocketException (env, result);
			return NULL;        	   	
		}

		// Transfer char to jbyteArray		    
		for(j = 0; j < MAC_ADDR_SIZE; j++) {
			array[j] = (unsigned char)ifr[i].ifr_hwaddr.sa_data[j]; 
			if (array[j] != 0) {
	       		isEmpty = JNI_FALSE;
			}
		}
		// if all bytes are zero, return null
		if(!isEmpty) {
                  bytearray = (*env)->NewByteArray (env, MAC_ADDR_SIZE);
                  (*env)->SetByteArrayRegion (env, bytearray, 0, MAC_ADDR_SIZE, (jbyte *)array);				
		}
		break;
	    }
	}	
	close(fd);
	hymem_free_memory (interfaceName);	
#endif
	return bytearray;
}

#if defined(HAS_RTNETLINK)
static void 
initNetlinkContext (I_32 netlinkSocketHandle,
                    struct netlinkContext_struct * netlinkContext)
{
  netlinkContext->netlinkSocketHandle = netlinkSocketHandle;
  netlinkContext->netlinkHeader = NULL;
  netlinkContext->remainingLength = 0;
  netlinkContext->done = 0;
}


static void cleanupNetlinkContext(JNIEnv * env, struct netlinkContext_struct *nlc) 
{
    if (nlc && nlc->buffer) {
        PORT_ACCESS_FROM_ENV (env);
        hymem_free_memory(nlc->buffer);
        nlc->buffer = NULL;
        nlc->bufferSize = 0;
    }
}

static jint getNextNetlinkMsg (JNIEnv * env, 
                   struct netlinkContext_struct * netlinkContext,
                   struct nlmsghdr ** nextMessage)
{
  U_32 receiveLength;

  PORT_ACCESS_FROM_ENV (env);

  for (;;)
    {
      /* check if we are done */
      if (netlinkContext->done == 1)
        {
          *nextMessage = NULL;
          return 0;
        }

      if (netlinkContext->remainingLength > 0)
        {
          /* there is data left from the last read, get the next header from this existing data */
          netlinkContext->netlinkHeader =
            NLMSG_NEXT (netlinkContext->netlinkHeader,
                        netlinkContext->remainingLength);
        }

    /* 
     * if the remainingLength is 0 then there was no messages available in the existing data 
     * so read another datagram containing messages we first use a select to make sure we 
     * don't block forever if for some reason there is no netlink message to read 
     */
     
      if (netlinkContext->remainingLength == 0)
        {
          fd_set waitSockets;
          struct timeval waitTime;
          waitTime.tv_sec = NETLINK_READTIMEOUT_SECS;
          waitTime.tv_usec = 0;
          FD_ZERO (&waitSockets);
          FD_SET (netlinkContext->netlinkSocketHandle, &waitSockets);
                     
          if (select
              (netlinkContext->netlinkSocketHandle + 1, &waitSockets, NULL,
               NULL, &waitTime) > 0)
            {
                
                struct sockaddr_nl nladdr;
                struct msghdr msg;
                struct iovec iov;
                iov.iov_base = netlinkContext->buffer;
                iov.iov_len = netlinkContext->bufferSize;
                msg.msg_name = (void *)&(nladdr);
                msg.msg_namelen = sizeof(nladdr);
                msg.msg_iov = &iov;
                msg.msg_iovlen = 1;
                
                int reallocLoop = 1;

                while (reallocLoop) {
                    /*
                     *  if the peek shows that we would truncate, realloc to 2x the buffer size
                     */
                    if (msg.msg_flags & MSG_TRUNC) {
                        
                        /*
                         * safety - only go to 64k
                         */
                         
                        if (netlinkContext->bufferSize * 2 > 65536) {
                            reallocLoop = 0;
                            break;
                        }                        
                        
                        netlinkContext->buffer = hymem_reallocate_memory(netlinkContext->buffer, 
                                   netlinkContext->bufferSize * 2);
                        #if defined(VALIDATE_ALLOCATIONS)
                            if (NULL == netlinkContext->buffer){
                               return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
                                 }
                        #endif
                        netlinkContext->bufferSize *= 2;
                                
                        iov.iov_base = netlinkContext->buffer;
                        iov.iov_len = netlinkContext->bufferSize;
                    }
                    else { 
                        reallocLoop = 0;
                    }
                }
                
                receiveLength = recvmsg(netlinkContext->netlinkSocketHandle, &msg, 0);
                
                if (receiveLength < 0) {
                  /* we failed to get the response message */
                  return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
                }
                
              netlinkContext->remainingLength = receiveLength;
              netlinkContext->netlinkHeader =
                (struct nlmsghdr *) netlinkContext->buffer;
            }
          else
            {
              /* timeout waiting from netlink response messages */
              return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
            }
        }

      /* now return message if everything was ok */
      if (NLMSG_OK
          (netlinkContext->netlinkHeader, netlinkContext->remainingLength))
        {
          if (NLMSG_ERROR == netlinkContext->netlinkHeader->nlmsg_type)
            {
              /* we got an unexpected error message */
              return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
            }
          /* we are done if the NLM_F_MULTI flag is not set in this header */
          *nextMessage = netlinkContext->netlinkHeader;
          if (netlinkContext->netlinkHeader->nlmsg_flags & (NLM_F_MULTI !=
              NLM_F_MULTI))
            {
              netlinkContext->done = 1;
            }

          /* if this is the message indicating that we are at the end of a multi-part message just each the message and
             return NULL to indicate we are done */
          if (netlinkContext->netlinkHeader->nlmsg_type == NLMSG_DONE)
            {
              /* end of multilink message so just return null as we don't need this message */
              *nextMessage = NULL;
            }
        }
      else
        {
          /* this is an error as we have to read all of the data in one shot */
          /* It is assumed that the kernel uses a reasonable max size datagram and will break up response into multi-link messages
             if a message larger than this size needs to be sent.  The max size seen so far is about  552 bytes */
  
          return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
        }

      /*  If this message is not a NOOP return, otherwise just eat the message and go look for the next one  */
      if (netlinkContext->netlinkHeader->nlmsg_type != NLMSG_NOOP)
        {
          return 0;
        }
    }
}
#endif

I_32 
getPlatformInterfaceAddresses(JNIEnv * env, 
                                 jstring ifname, 
                                 jint index,                               
                                 interfaceAddressArray_struct* interfaceAddressArray)
{
       interfaceAddress_struct *interfaces = NULL;
       U_32 numAddresses = 0;

#if defined(HAS_RTNETLINK)
       int counter = 0;
     	U_8 addressFamily = 0;
       int netlinkSocketHandle = 0;	
       netlinkContext_struct netlinkContext;	
       struct addrReq_struct addrRequest;	
       U_32 sendLength = 0;
       U_32 result = 0;

       struct nlmsghdr *currentNlHeader = NULL;
       struct ifaddrmsg *returnedAddrHeader = NULL;
	
	PORT_ACCESS_FROM_ENV (env);
	/*
	*  initialize the buffer as it doesn't have one yet
	*/
	netlinkContext.buffer = hymem_allocate_memory(NETLINK_DATA_BUFFER_SIZE);
	netlinkContext.bufferSize = NETLINK_DATA_BUFFER_SIZE;

       /* set the address family based on the preferIPv4stack flag */
	if (preferIPv4Stack(env)) {
		addressFamily = AF_INET;
	}else {
		addressFamily = AF_UNSPEC;
	}
       
       /* we need socket to do the netlink calls so create one */
       netlinkSocketHandle = socket (PF_NETLINK, SOCK_DGRAM, NETLINK_ROUTE);       
       if (netlinkSocketHandle <= 0) {
           cleanupNetlinkContext(env, &netlinkContext); 
           return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
        }

       // inital address request message 
      memset (&addrRequest, 0, sizeof (struct addrReq_struct));
      addrRequest.netlinkHeader.nlmsg_len =
        NLMSG_LENGTH (sizeof (struct addrReq_struct));
      addrRequest.netlinkHeader.nlmsg_flags = NLM_F_REQUEST | NLM_F_MATCH;
      addrRequest.netlinkHeader.nlmsg_type = RTM_GETADDR;
      addrRequest.msg.ifa_index = index;
      addrRequest.msg.ifa_family = addressFamily;
      addrRequest.netlinkHeader.nlmsg_len = NLMSG_ALIGN (addrRequest.netlinkHeader.nlmsg_len);
       // send the message out
      sendLength =
        send (netlinkSocketHandle, &addrRequest,
              addrRequest.netlinkHeader.nlmsg_len, 0);
      if (sendLength != addrRequest.netlinkHeader.nlmsg_len)
        {
          close (netlinkSocketHandle);          
          cleanupNetlinkContext(env, &netlinkContext);        
          return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
        }

       initNetlinkContext (netlinkSocketHandle, &netlinkContext);
        // calculate the interface addresses number
       do
        {
          if ((result =
               getNextNetlinkMsg (env, &netlinkContext,
                                  &currentNlHeader)) != 0)
            {
              close (netlinkSocketHandle);              
              cleanupNetlinkContext(env, &netlinkContext);
              return result;
            }

           if (currentNlHeader != NULL)
             {
               struct rtattr *rta;
               U_32 attLength = IFA_PAYLOAD (currentNlHeader);
               returnedAddrHeader = NLMSG_DATA (currentNlHeader);
               if (((returnedAddrHeader->ifa_family == AF_INET) ||
                    (returnedAddrHeader->ifa_family == AF_INET6)) &&
                  (returnedAddrHeader->ifa_index == index))
                 {
                  rta = IFA_RTA (returnedAddrHeader);
                  while (RTA_OK (rta, attLength))
                    {
                      if (IFA_ADDRESS == rta->rta_type)
                        {
                          numAddresses++;
                        }
                      rta = RTA_NEXT (rta, attLength);
                    }
                }
             }
          
        } while (currentNlHeader != NULL); 

        if (numAddresses > 0)
         {
           // allocate new spaces
           interfaces = hymem_allocate_memory (sizeof(interfaceAddress_struct) * (numAddresses));
           for (counter = 0; counter < numAddresses; counter++)
             {
		interfaces[counter].address = NULL;
		interfaces[counter].prefixLength = 0;
             }
           counter = 0;
           // fetch the addresses and prefix length
           sendLength =
                send (netlinkSocketHandle, &addrRequest,
                      addrRequest.netlinkHeader.nlmsg_len, 0);
           if (sendLength != addrRequest.netlinkHeader.nlmsg_len)
             {
                close (netlinkSocketHandle);          
                cleanupNetlinkContext(env, &netlinkContext);        
                return hyerror_set_last_error(errno, HYPORT_ERROR_SOCKET_NORECOVERY);
             }

           initNetlinkContext (netlinkSocketHandle, &netlinkContext);

           do
             {
                if ((result =
                    getNextNetlinkMsg (env, &netlinkContext,
                                  &currentNlHeader)) != 0)
                   {
                    close (netlinkSocketHandle);              
                    cleanupNetlinkContext(env, &netlinkContext);
                    return result;
                   }

                if (currentNlHeader != NULL)
                   {
                    struct rtattr *rta;
                    U_32 attLength = IFA_PAYLOAD (currentNlHeader);
                    returnedAddrHeader = NLMSG_DATA (currentNlHeader);
                    /* make sure that it is for the requested interface, and one of the types we are expecting */
                    if (((returnedAddrHeader->ifa_family == AF_INET) ||
                        (returnedAddrHeader->ifa_family == AF_INET6)) &&
                        (returnedAddrHeader->ifa_index == index))
                        {
                         rta = IFA_RTA (returnedAddrHeader);
                         while (RTA_OK (rta, attLength))
                             {
                            if (IFA_ADDRESS == rta->rta_type)
                                 {
                              // copy addresses
                              interfaces[counter].address = 
                                     hymem_allocate_memory (sizeof(hyipAddress_struct));
                              if (returnedAddrHeader->ifa_family == AF_INET)
                                    {
                                   memcpy (interfaces[counter].address->addr.bytes, 
                                           RTA_DATA (rta),
                                           sizeof (struct in_addr));
                                   interfaces[counter].address->length = sizeof (struct in_addr);
                                   interfaces[counter].address->scope = 0;
                                } else if (returnedAddrHeader->ifa_family ==  AF_INET6) {
                                   memcpy (interfaces[counter].address->addr.bytes, 
                                           RTA_DATA (rta),
                                           sizeof (struct in6_addr));
                                   interfaces[counter].address->length = sizeof (struct in6_addr);
                                   if ((returnedAddrHeader->ifa_scope == RT_SCOPE_LINK) || 
                                       (returnedAddrHeader->ifa_scope == RT_SCOPE_SITE))
                                        {
                                       interfaces[counter].address->scope = returnedAddrHeader->ifa_index;
                                  } else {
                                       interfaces[counter].address->scope = 0;
                                        }
                                     
                                    }
                              // copy prefix length
                              // RI has bug about prefix length of loopback interface.
                              interfaces[counter].prefixLength = returnedAddrHeader->ifa_prefixlen;
                              counter++;                              
                               }                          
                          rta = RTA_NEXT (rta, attLength);
                           }
                       }
                   }
             } while (currentNlHeader != NULL);
         }

      
       /* do any final clean up before returning */
       close (netlinkSocketHandle);
       cleanupNetlinkContext(env, &netlinkContext);

#endif
       interfaceAddressArray -> length = numAddresses;
       interfaceAddressArray -> elements = interfaces;

       return 0;	
}

I_32
getPlatformIsExecutable (JNIEnv * env, char *path)
{
  I_32 result;
  struct stat buffer;
  PORT_ACCESS_FROM_ENV (env);

  result = stat (path, &buffer);
  if (result == -1)
    return 0;

  //if the current user is 'root', then the file is executable when 
  //either of user/group/others has the permission to execute.
  // and the directory is always executable.
  if (geteuid() == 0) {
     if(HyIsDir == hyfile_attr (path)) {
         return 1;
     } else {
         return (buffer.st_mode & (S_IXUSR | S_IXGRP | S_IXOTH)) != 0;
     }
  }

  if (buffer.st_uid == geteuid ())
    return (buffer.st_mode & S_IXUSR) != 0;
  else if (buffer.st_gid == getegid ())
    return (buffer.st_mode & S_IXGRP) != 0;

  result = hasPrivilegeInOtherGroups(env, &buffer, S_IXGRP);
  return -1 == result ? (buffer.st_mode & S_IXOTH) != 0 : result;
}

I_32 
hasPrivilegeInOtherGroups(JNIEnv * env, struct stat * buffer, mode_t attr) 
{
      // if the user belongs other groups
      gid_t *group;
      long ngroups_max;
      int result;
      
      PORT_ACCESS_FROM_ENV (env);
      ngroups_max = sysconf(_SC_NGROUPS_MAX) + 1;
      group = (gid_t *) hymem_allocate_memory(ngroups_max * sizeof(gid_t));
      result = getgroups(ngroups_max, group);
      
      if(-1 != result) {
            int i = 0;
            for (i = 0; i < result; i++) {
                if (buffer->st_gid == group[i]) {
                  hymem_free_memory(group);
                  return (buffer->st_mode & attr) != 0;
                }
           } 
      }
      hymem_free_memory(group);
      return result;    	
}

I_32
setPlatformExecutable (JNIEnv * env, char *path, jboolean executable, jboolean ownerOnly)
{
  struct stat buffer;
  mode_t mode;
  if (stat (path, &buffer))
    {
      return 0;
    }
  mode = buffer.st_mode;
  if (executable && ownerOnly)
	  mode |= S_IXUSR;
  else if (executable) 
	  mode |= (S_IXUSR | S_IXGRP | S_IXOTH);
  else if (ownerOnly)
  	  mode &= (~S_IXUSR);
  else
      mode &= ~(S_IXUSR | S_IXGRP | S_IXOTH);
  return chmod (path, mode) == 0;
}

/* Get charset from the OS */
void getOSCharset(char *locale, const size_t size) {
  char * codec = NULL;
  size_t cur = 0;
  short flag = 0;
  setlocale(LC_CTYPE, "");
  codec = setlocale(LC_CTYPE, NULL);
  // get codeset from language[_territory][.codeset][@modifier]
  while (*codec) {
    if (!flag) {
      if (*codec != '.') {
        codec++;
        continue;
      } else {
        flag = 1;
        codec++;
      }
    } else {
      if (*codec == '@') {
        break;
      } else {
        locale[cur++] = (*codec);
        codec++;
        if (cur >= size) {
          // Not enough size
          cur = 0;
          break;
        }
      }
    }
  }
  locale[cur] = '\0';
  if (!strlen(locale)) {
    strcpy(locale, "8859_1");
  }
  return;
}
