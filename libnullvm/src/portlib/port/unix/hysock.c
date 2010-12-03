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

#define CDEV_CURRENT_FUNCTION _comment_
/**
 * @file
 * @ingroup Port
 * @brief Sockets
 */

#undef CDEV_CURRENT_FUNCTION

#include "hysock.h"
#include "hymutex.h"
#include "portpriv.h"
#include "hyportptb.h"
#include <fcntl.h>
#include <string.h>

#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>         /* for struct in_addr */
#include <arpa/inet.h>          /* for inet_addr */
#include <sys/ioctl.h>
#include <net/if.h>             /* for struct ifconf */

#if !defined(ZOS)
#include <sys/poll.h>
#else
/* On zOS this header file has a different location */
#include <poll.h>
#endif

#if defined(LINUX)
#define IPV6_FLOWINFO_SEND      33
#if defined(HARDHAT)
#else
#define HAS_RTNETLINK 1
#endif
#endif

#if defined(HAS_RTNETLINK)
#include <asm/types.h>
#include <linux/netlink.h>
#include <linux/rtnetlink.h>
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
#define NETLINK_DATA_BUFFER_SIZE 2048
#define NETLINK_READTIMEOUT_SECS 20
typedef struct netlinkContext_struct
{
  int netlinkSocketHandle;
  char *buffer;
  int bufferSize;
  struct nlmsghdr *netlinkHeader;
  U_32 remainingLength;
  U_32 done;
} netlinkContext_struct;
#else
/* need something so that functions still compile */
typedef struct netlinkContext_struct
{
  int netlinkSocketHandle;
} netlinkContext_struct;
typedef struct nlmsghdr
{
  int length;
} nlmsghdr;
#endif

#define INVALID_SOCKET (hysocket_t) -1

/* these platforms include a colon in aliases so additional tests are required */

/* The platforms that do not define the standard type socklen_t seem to all want an int in the socket calls */

/*the number of times to retry a gethostby* call if the return code is TRYAGAIN*/
#define MAX_RETRIES 50

#if NO_R
/*use a mutex if the gethostbyaddr, gethostbyname calls are not threadsafe*/
MUTEX hostentLock = PTHREAD_MUTEX_INITIALIZER;
#endif /*NO_R */

#define VALIDATE_ALLOCATIONS 1

#define CDEV_CURRENT_FUNCTION _prototypes_private

I_32 platformSocketOption (I_32 portableSocketOption);

I_32 initNetlinkContext (struct HyPortLibrary *portLibrary,
                         I_32 netlinkSocketHandle,
                         struct netlinkContext_struct *netlinkContext);

I_32 getNextNetlinkMsg (struct HyPortLibrary *portLibrary,
                        struct netlinkContext_struct *netlinkContext,
                        struct nlmsghdr **nextMessage);

I_32 platformSocketLevel (I_32 portableSocketLevel);

static I_32 findError (I_32 errorCode);

I_32 map_protocol_family_Hy_to_OS (I_32 addr_family);

I_32 map_addr_family_Hy_to_OS (I_32 addr_family);

I_32 map_sockettype_Hy_to_OS (I_32 socket_type);

static I_32 findHostError (int herr);

static socklen_t getAddrLength(hysockaddr_t addr);

#undef CDEV_CURRENT_FUNCTION

#if NO_R
static I_32 copy_hostent (struct HyPortLibrary *portLibrary,
                          OSHOSTENT * source, PortlibPTBuffers_t * ptBuffers);

#define CDEV_CURRENT_FUNCTION copy_hostent

static I_32
copy_hostent (struct HyPortLibrary *portLibrary, OSHOSTENT * source,
              PortlibPTBuffers_t * ptBuffers)
{
  int h_len = strlen (source->h_name);
  int total = 0;
  int i = 0;
  char *buffer;
  OSHOSTENT *dest = &(*ptBuffers)->hostent;
  /* add trailing NULL, and round off to nearest pointer size */
  h_len = (h_len + 1 + sizeof (void *)) & ~(sizeof (void *) - 1);
  while (source->h_addr_list[i])
    i++;
  total = h_len + ((i + 2) * sizeof (void *)) + (i * sizeof (U_32));
  if (!(*ptBuffers)->gethostBuffer || (*ptBuffers)->gethostBufferSize < total)
    {
      (*ptBuffers)->gethostBuffer =
        portLibrary->mem_allocate_memory (portLibrary, total);
      if (!(*ptBuffers)->gethostBuffer)
        {
          return HYPORT_ERROR_SOCKET_SYSTEMFULL;
        }
      (*ptBuffers)->gethostBufferSize = total;
    }
  buffer = (*ptBuffers)->gethostBuffer;
  dest->h_name = buffer;
  strcpy (buffer, source->h_name);
  buffer += h_len;
  dest->h_aliases = (void *) buffer;
  *((void **) buffer) = NULL;
  buffer += sizeof (void *);
  dest->h_addrtype = source->h_addrtype;
  dest->h_length = source->h_length;
  dest->h_addr_list = (void *) buffer;
  buffer += (i + 1) * sizeof (void *);
  i = 0;
  while (source->h_addr_list[i])
    {
      dest->h_addr_list[i] = (void *) buffer;
      *((U_32 *) buffer) = *((U_32 *) source->h_addr_list[i]);
      buffer += sizeof (U_32);
      i++;
    }
  dest->h_addr_list[i] = NULL;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION
#endif

#define CDEV_CURRENT_FUNCTION findError

/**
 * @internal
 * Determines the proper portable error code to return given a native error code
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError (I_32 errorCode)
{
  switch (errorCode)
    {
    case HYPORT_ERROR_SOCKET_UNIX_EBADF:
      return HYPORT_ERROR_SOCKET_BADDESC;
    case HYPORT_ERROR_SOCKET_UNIX_ENOBUFS:
      return HYPORT_ERROR_SOCKET_NOBUFFERS;
    case HYPORT_ERROR_SOCKET_UNIX_EOPNOTSUP:
      return HYPORT_ERROR_SOCKET_OPNOTSUPP;
    case HYPORT_ERROR_SOCKET_UNIX_ENOPROTOOPT:
      return HYPORT_ERROR_SOCKET_OPTUNSUPP;
    case HYPORT_ERROR_SOCKET_UNIX_EINVAL:
      return HYPORT_ERROR_SOCKET_SOCKLEVELINVALID;
    case HYPORT_ERROR_SOCKET_UNIX_ENOTSOCK:
      return HYPORT_ERROR_SOCKET_NOTSOCK;
    case HYPORT_ERROR_SOCKET_UNIX_EINTR:
      return HYPORT_ERROR_SOCKET_INTERRUPTED;
    case HYPORT_ERROR_SOCKET_UNIX_ENOTCONN:
      return HYPORT_ERROR_SOCKET_NOTCONNECTED;
    case HYPORT_ERROR_SOCKET_UNIX_EAFNOSUPPORT:
      return HYPORT_ERROR_SOCKET_BADAF;
      /* note: HYPORT_ERROR_SOCKET_UNIX_ECONNRESET not included because it has the same
       * value as HYPORT_ERROR_SOCKET_UNIX_CONNRESET and they both map to HYPORT_ERROR_SOCKET_CONNRESET */
    case HYPORT_ERROR_SOCKET_UNIX_CONNRESET:
      return HYPORT_ERROR_SOCKET_CONNRESET;
    case HYPORT_ERROR_SOCKET_UNIX_EAGAIN:
      return HYPORT_ERROR_SOCKET_WOULDBLOCK;
    case HYPORT_ERROR_SOCKET_UNIX_EPROTONOSUPPORT:
      return HYPORT_ERROR_SOCKET_BADPROTO;
    case HYPORT_ERROR_SOCKET_UNIX_EFAULT:
      return HYPORT_ERROR_SOCKET_ARGSINVALID;
    case HYPORT_ERROR_SOCKET_UNIX_ETIMEDOUT:
      return HYPORT_ERROR_SOCKET_TIMEOUT;
    case HYPORT_ERROR_SOCKET_UNIX_CONNREFUSED:
      return HYPORT_ERROR_SOCKET_CONNECTION_REFUSED;
    case HYPORT_ERROR_SOCKET_UNIX_ENETUNREACH:
      return HYPORT_ERROR_SOCKET_ENETUNREACH;
    case HYPORT_ERROR_SOCKET_UNIX_EACCES:
      return HYPORT_ERROR_SOCKET_EACCES;
    default:
      return HYPORT_ERROR_SOCKET_OPFAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findHostError

/**
 * @internal Determines the proper portable error code to return given a native host error code
 *
 * @return	the (negative) error code
 */
static I_32
findHostError (int herr)
{
  switch (herr)
    {
    case HYPORT_ERROR_SOCKET_UNIX_NORECOVERY:
      return HYPORT_ERROR_SOCKET_NORECOVERY;
    case HYPORT_ERROR_SOCKET_UNIX_HOSTNOTFOUND:
      return HYPORT_ERROR_SOCKET_HOSTNOTFOUND;
    case HYPORT_ERROR_SOCKET_UNIX_NODATA:
      return HYPORT_ERROR_SOCKET_NODATA;
    case HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN:
      return HYPORT_ERROR_SOCKET_INTERRUPTED;
    default:
      return HYPORT_ERROR_SOCKET_BADSOCKET;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION map_addr_family_Hy_to_OS

/**
 * @internal Map the portable address family to the platform address family. 
 *
 * @param	addr_family	the portable address family to convert
 *
 * @return	the platform family, or OS_AF_UNSPEC if none exists
 */
I_32
map_addr_family_Hy_to_OS (I_32 addr_family)
{
  switch (addr_family)
    {
    case HYADDR_FAMILY_AFINET4:
      return OS_AF_INET4;
    case HYADDR_FAMILY_AFINET6:
      return OS_AF_INET6;
    }
  return OS_AF_UNSPEC;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION map_protocol_family_Hy_to_OS

/**
 * @internal Map the portable address protocol to the platform protocol
 *
 * @param	addr_protocol  the portable address protocol to convert
 *
 * @return	the platform family, or OS_PF_UNSPEC if none exists
 */
I_32
map_protocol_family_Hy_to_OS (I_32 addr_family)
{
  switch (addr_family)
    {
    case HYPROTOCOL_FAMILY_INET4:
      return OS_PF_INET4;
    case HYPROTOCOL_FAMILY_INET6:
      return OS_PF_INET6;
    }
  return OS_PF_UNSPEC;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION map_sockettype_Hy_to_OS

/**
 * @internal Map the portable socket type to the platform type. 
 *
 * @param	addr_family	the portable socket type to convert
 *
 * @return	the platform family, or OSSOCK_ANY if none exists
 */
I_32
map_sockettype_Hy_to_OS (I_32 socket_type)
{
  switch (socket_type)
    {
    case HYSOCKET_STREAM:
      return OSSOCK_STREAM;
    case HYSOCKET_DGRAM:
      return OSSOCK_DGRAM;
    case HYSOCKET_RAW:
      return OSSOCK_RAW;
    case HYSOCKET_RDM:
      return OSSOCK_RDM;
    case HYSOCKET_SEQPACKET:
      return OSSOCK_SEQPACKET;
    }
  return OSSOCK_ANY;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION platformSocketLevel

/**
 * @internal
 * Map the portable to the platform socket level.  Used to resolve the arguments of socket option functions.
 * Levels currently in use are:
 * \arg SOL_SOCKET, for most options
 * \arg IPPROTO_TCP, for the TCP noDelay option
 * \arg IPPROTO_IP, for the option operations associated with multicast (join, drop, interface)
 *
 * @param[in] portableSocketLevel The portable socket level to convert.
 *
 * @return	the platform socket level or a (negative) error code if no equivalent level exists.
 */
/**
 * @internal Map the portable to the platform socket level.  Used to resolve the arguments of socket option functions.
 * Levels currently in use are:
 *		SOL_SOCKET, for most options
 *		IPPROTO_TCP, for the TCP noDelay option
 *		IPPROTO_IP, for the option operations associated with multicast (join, drop, interface)
 *
 * @param	portlib		pointer to the VM port library
 * @param	portableSocketLevel	the portable socket level to convert
 *
 * @return	the platform socket level or an error if no equivalent level exists
 */
I_32
platformSocketLevel (I_32 portableSocketLevel)
{
  switch (portableSocketLevel)
    {
    case HY_SOL_SOCKET:
      return OS_SOL_SOCKET;
    case HY_IPPROTO_TCP:
      return OS_IPPROTO_TCP;
    case HY_IPPROTO_IP:
      return OS_IPPROTO_IP;
#if defined(IPv6_FUNCTION_SUPPORT)
    case HY_IPPROTO_IPV6:
      return OS_IPPROTO_IPV6;
#endif

    }
  return HYPORT_ERROR_SOCKET_SOCKLEVELINVALID;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION platformSocketOption

/**
 * @internal
 * Map the portable to the platform socket options.  Used to resolve the arguments of socket option functions.
 * Options currently in supported are:
 * \arg SOL_LINGER, the linger timeout
 * \arg TCP_NODELAY, the buffering scheme implementing Nagle's algorithm
 * \arg IP_MULTICAST_TTL, the packet Time-To-Live
 * \arg IP_ADD_MEMBERSHIP, to join a multicast group
 * \arg  IP_DROP_MEMBERSHIP, to leave a multicast group
 * \arg IP_MULTICAST_IF, the multicast interface
 *
 * @param[in] portableSocketOption The portable socket option to convert.
 *
 * @return	the platform socket option or a (negative) error code if no equivalent option exists.
 */
/**
 * @internal Map the portable to the platform socket options.  Used to resolve the arguments of socket option functions.
 * Options currently in supported are:
 *		SOL_LINGER, the linger timeout
 *		TCP_NODELAY, the buffering scheme implementing Nagle's algorithm
 *		IP_MULTICAST_TTL, the packet Time-To-Live
 *		IP_ADD_MEMBERSHIP, to join a multicast group
 *		IP_DROP_MEMBERSHIP, to leave a multicast group
 *		IP_MULTICAST_IF, the multicast interface
 *
 * @param	portlib		pointer to the VM port library
 * @param	portableSocketOption	the portable socket option to convert
 *
 * @return	the platform socket option or an error if no equivalent option exists
 */
/* Support datagram broadcasts */
I_32
platformSocketOption (I_32 portableSocketOption)
{
  switch (portableSocketOption)
    {
    case HY_SO_LINGER:
      return OS_SO_LINGER;
    case HY_SO_KEEPALIVE:
      return OS_SO_KEEPALIVE;
    case HY_TCP_NODELAY:
      return OS_TCP_NODELAY;
    case HY_MCAST_TTL:
      return OS_MCAST_TTL;
    case HY_MCAST_ADD_MEMBERSHIP:
      return OS_MCAST_ADD_MEMBERSHIP;
    case HY_MCAST_DROP_MEMBERSHIP:
      return OS_MCAST_DROP_MEMBERSHIP;
    case HY_MCAST_INTERFACE:
      return OS_MCAST_INTERFACE;
    case HY_SO_REUSEADDR:
      return OS_SO_REUSEADDR;
    case HY_SO_SNDBUF:
      return OS_SO_SNDBUF;
    case HY_SO_RCVBUF:
      return OS_SO_RCVBUF;
    case HY_SO_BROADCAST:
      return OS_SO_BROADCAST;

    case HY_SO_OOBINLINE:
      return OS_SO_OOBINLINE;
    case HY_IP_MULTICAST_LOOP:
      return OS_MCAST_LOOP;
    case HY_IP_TOS:
      return OS_IP_TOS;
#if defined(IPv6_FUNCTION_SUPPORT)
    case HY_MCAST_INTERFACE_2:
      return OS_MCAST_INTERFACE_2;
    case HY_IPV6_ADD_MEMBERSHIP:
      return OS_IPV6_ADD_MEMBERSHIP;
    case HY_IPV6_DROP_MEMBERSHIP:
      return OS_IPV6_DROP_MEMBERSHIP;
#endif

    }
  return HYPORT_ERROR_SOCKET_OPTUNSUPP;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_accept

/**
 * The accept function extracts the first connection on the queue of pending connections 
 * on socket sock. It then creates a new socket and returns a handle to the new socket. 
 * The newly created socket is the socket that will handle the actual the connection and 
 * has the same properties as socket sock.  
 *
 * The accept function can block the caller until a connection is present if no pending 
 * connections are present on the queue.
 *
 * @param[in] portLibrary The port library.
 * @param[in] serverSock  A hysocket_t  from which data will be read.
 * @param[in] addrHandle An optional pointer to a buffer that receives the address of the connecting 
 * entity, as known to the communications layer. The exact format of the addr parameter 
 * is determined by the address family established when the socket was created. 
 * @param[in] sockHandle A pointer to a hysocket_t  which will point to the newly created 
 * socket once accept returns successfully
 *
 * @return 
 * \arg 0 on success
 * \arg HYPORT_ERROR_SOCKET_BADSOCKET, on generic error
 * \arg HYPORT_ERROR_SOCKET_NOTINITIALIZED, if socket library uninitialized
 * \arg HYPORT_ERROR_SOCKET_INTERRUPTED, the call was cancelled
 * \arg HYPORT_ERROR_SOCKET_ADDRNOTAVAIL, the addr parameter is not valid
 * \arg HYPORT_ERROR_SOCKET_SYSTEMBUSY, if system busy handling other requests
 * \arg HYPORT_ERROR_SOCKET_SYSTEMFULL, is too many sockets are active
 * \arg HYPORT_ERROR_SOCKET_WOULDBLOCK, the socket is marked as nonblocking and no connections are present to be accepted., 
 */
I_32 VMCALL
hysock_accept (struct HyPortLibrary * portLibrary, hysocket_t serverSock,
               hysockaddr_t addrHandle, hysocket_t * sockHandle)
{
#if defined(LINUX)
#define ACCEPTCAST (socklen_t *)
#else
#define ACCEPTCAST
#endif

  I_32 rc = 0;
  int sc;
  socklen_t fromlen = sizeof (addrHandle->addr);

  *sockHandle = INVALID_SOCKET;

  sc =
    accept (SOCKET_CAST (serverSock), (struct sockaddr *) &addrHandle->addr,
            ACCEPTCAST & fromlen);
  if (sc < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<accept failed, err=%d>\n", rc);
      rc =
        portLibrary->error_set_last_error (portLibrary, rc,
                                           HYPORT_ERROR_SOCKET_ADDRNOTAVAIL);
    }

  if (rc == 0)
    {
      *sockHandle =
        portLibrary->mem_allocate_memory (portLibrary,
                                          sizeof (struct hysocket_struct));
#if (defined(VALIDATE_ALLOCATIONS))
      if (*sockHandle == NULL)
        {
          close (sc);
          *sockHandle = INVALID_SOCKET;
          return HYPORT_ERROR_SOCKET_NOBUFFERS;
        }
#endif

      SOCKET_CAST (*sockHandle) = sc;
      (*sockHandle)->family = serverSock->family;
    }
  return rc;
}

#undef ACCEPTCAST

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_bind

/**
 * The bind function is used on an unconnected socket before subsequent 
 * calls to the connect or listen functions. When a socket is created with a 
 * call to the socket function, it exists in a name space (address family), but 
 * it has no name assigned to it. Use bind to establish the local association 
 * of the socket by assigning a local name to an unnamed socket.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock hysocket_t which will be associated with the specified name.
 * @param[in] addr Address to bind to socket.
 *
 * @return
 * \arg 0, on success
 * \arg HYPORT_ERROR_SOCKET_BADSOCKET, on generic error
 * \arg HYPORT_ERROR_SOCKET_NOTINITIALIZED, if socket library uninitialized
 * \arg HYPORT_ERROR_SOCKET_ADDRINUSE  A process on the machine is already bound to the same fully-qualified address 
 * and the socket has not been marked to allow address re-use with SO_REUSEADDR. 
 * \arg HYPORT_ERROR_SOCKET_ADDRNOTAVAIL The specified address is not a valid address for this machine 
 * \arg HYPORT_ERROR_SOCKET_SYSTEMBUSY, if system busy handling other requests
 * \arg HYPORT_ERROR_SOCKET_SYSTEMFULL, is too many sockets are active
 * \arg HYPORT_ERROR_SOCKET_BADADDR, the addr parameter is not a valid part of the user address space, 
 */
I_32 VMCALL
hysock_bind (struct HyPortLibrary * portLibrary, hysocket_t sock,
             hysockaddr_t addr)
{
  I_32 rc = 0;
  I_32 length = getAddrLength(addr);

  if (bind
      (SOCKET_CAST (sock), (struct sockaddr *) &addr->addr, length) < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<bind failed, err=%d>\n", rc);
      rc =
        portLibrary->error_set_last_error (portLibrary, rc,
                                           HYPORT_ERROR_SOCKET_ADDRNOTAVAIL);
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_close

/**
 * The close function closes a socket. Use it to release the socket descriptor socket so 
 * further references to socket will fail.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock hysocket_t  which will be closed.
 *
 * @return
 * \arg 0, on success
 * \arg HYPORT_ERROR_SOCKET_BADSOCKET, on generic error
 * \arg HYPORT_ERROR_SOCKET_SYSTEMBUSY, if system busy handling other requests
 * \arg HYPORT_ERROR_SOCKET_WOULDBLOCK,  the socket is marked as nonblocking and SO_LINGER is set to a nonzero time-out value.
 */
I_32 VMCALL
hysock_close (struct HyPortLibrary * portLibrary, hysocket_t * sock)
{
  I_32 rc = 0;

  if (*sock == INVALID_SOCKET) {
    HYSOCKDEBUGPRINT ("<closesocket failed, invalid socket>\n");
    return portLibrary->error_set_last_error (portLibrary,
                                              HYPORT_ERROR_SOCKET_UNIX_EBADF,
                                              HYPORT_ERROR_SOCKET_BADSOCKET);
  }

  if (close (SOCKET_CAST (*sock)) < 0) {
    rc = errno;
    HYSOCKDEBUG ("<closesocket failed, err=%d>\n", rc);
    rc =
      portLibrary->error_set_last_error (portLibrary, rc,
                                         HYPORT_ERROR_SOCKET_BADSOCKET);
  }

  portLibrary->mem_free_memory (portLibrary, *sock);
  *sock = INVALID_SOCKET;
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_connect

/**
 * Establish a connection to a peer.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock pointer to the unconnected local socket.
 * @param[in] addr	pointer to the sockaddr, specifying remote host/port.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_connect (struct HyPortLibrary * portLibrary, hysocket_t sock,
                hysockaddr_t addr)
{
  I_32 rc = 0;
  I_32 length = getAddrLength(addr);

  if (connect
      (SOCKET_CAST (sock), (struct sockaddr *) &addr->addr, length) < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<connect failed, err=%d>\n", rc);
      rc =
        portLibrary->error_set_last_error (portLibrary, rc,
                                           HYPORT_ERROR_SOCKET_OPFAILED);
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_error_message

/**
 * Return an error message describing the last OS error that occurred.  The last
 * error returned is not thread safe, it may not be related to the operation that
 * failed for this thread.
 *
 * @param[in] portLibrary The port library
 *
 * @return	error message describing the last OS error, may return NULL.
 *
 * @internal
 * @note  This function gets the last error code from the OS and then returns
 * the corresponding string.  It is here as a helper function for JCL.  Once hyerror
 * is integrated into the port library this function should probably disappear.
 */
const char *VMCALL
hysock_error_message (struct HyPortLibrary *portLibrary)
{
  return portLibrary->error_last_error_message (portLibrary);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_fdset_init

/**
 * Create a file descriptor (FD) set of one element.  The call may not be generally useful,
 * as it currently only supports a single FD and is assumed to be used in conjunction with the 
 * hysock_select function.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP pointer to the socket to be added to the FD set.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_fdset_init (struct HyPortLibrary * portLibrary, hysocket_t socketP)
{
  PortlibPTBuffers_t ptBuffers;
  hyfdset_t fdset;

  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }

  if (NULL == ptBuffers->fdset)
    {
      ptBuffers->fdset =
        portLibrary->mem_allocate_memory (portLibrary,
                                          sizeof (struct hyfdset_struct));
      if (NULL == ptBuffers->fdset)
        {
          return HYPORT_ERROR_SOCKET_SYSTEMFULL;
        }
    }
  fdset = ptBuffers->fdset;
  memset (fdset, 0, sizeof (struct hyfdset_struct));

  FD_ZERO (&fdset->handle);
  FD_SET (SOCKET_CAST (socketP), &fdset->handle);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_fdset_size

/**
 * Answer the maximum size of the fdset currently declared for the platform.
 * This value is a parameter of the @ref hysock_select call.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle
 *
 * @return	the maximum size of the fdset, otherwise the (negative) error code.
 *
 * @note On Unix, the value was the maximum file descriptor plus one, although
 * on many flavors, the value is ignored in the select function.
 * It is essential on Neutrino 2.0.
 * On Windows, the value is ignored by the select function.
 * On OS/2, the value is the number of file descriptors to be checked.
 */
I_32 VMCALL
hysock_fdset_size (struct HyPortLibrary * portLibrary, hysocket_t handle)
{
  return SOCKET_CAST (handle) + 1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_fdset_zero
void VMCALL
hysock_fdset_zero(struct HyPortLibrary *portLibrary, hyfdset_t fdset) {
	FD_ZERO(&fdset->handle);
	return;
}
#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_fdset_set
void VMCALL
hysock_fdset_set(struct HyPortLibrary *portLibrary, hysocket_t socket, hyfdset_t fdset) {
    FD_SET(SOCKET_CAST(socket), &fdset->handle);
	return;
}
#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_freeaddrinfo

/**
 * Frees the memory created by the call to @ref hysock_getaddrinfo().
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Hints on what results are returned and how the response if formed .
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_freeaddrinfo (struct HyPortLibrary * portLibrary, hyaddrinfo_t handle)
{

#if defined(IPv6_FUNCTION_SUPPORT)
  freeaddrinfo ((OSADDRINFO *) handle->addr_info);
#endif

  handle->addr_info = NULL;
  handle->length = 0;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo

/**
 * Answers a list of addresses as an opaque pointer in "result".
 * 
 * Use the following functions to extract the details:
 * \arg \ref hysock_getaddrinfo_length
 * \arg \ref hysock_getaddrinfo_name
 * \arg \ref hysock_getaddrinfo_address
 * \arg \ref hysock_getaddrinfo_family
 *
 * If the machine type supports IPv6 you can specify how you want the results returned with the following function:
 * \arg \ref hysock_create_getaddrinfo_hints.
 * Passing the structure into a machine with only IPv4 support will have no effect.
 *
 * @param[in] portLibrary The port library.
 * @param[in] name The name of the host in either host name format or in IPv4 or IPv6 accepted notations.
 * @param[in] hints Hints on what results are returned and how the response if formed (can be NULL for default action).
 * @param[out] result An opaque pointer to a list of results (hyaddrinfo_struct must be preallocated).
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note you must free the "result" structure with @ref hysock_freeaddrinfo to free up memory.  This must be done
 * before you make a subsequent call in the same thread to this function. 
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo (struct HyPortLibrary * portLibrary, char *name,
                    hyaddrinfo_t hints, hyaddrinfo_t result)
{
  /* If we have the IPv6 functions available we will call them, otherwise we'll call the IPv4 function */
#if defined(IPv6_FUNCTION_SUPPORT)
  OSADDRINFO *ipv6_result;
  OSADDRINFO *addr_info_hints = NULL;
  int count = 0;
  if (hints != NULL)
    {
      addr_info_hints = (OSADDRINFO *) hints->addr_info;
    }
  if (0 != getaddrinfo (name, NULL, addr_info_hints, &ipv6_result))
    {
      I_32 errorCode = errno;
      HYSOCKDEBUG ("<getaddrinfo failed, err=%d>\n", errorCode);
      return portLibrary->error_set_last_error (portLibrary, errorCode,
                                                findError (errorCode));
    }
  memset (result, 0, sizeof (struct hyaddrinfo_struct));
  result->addr_info = (void *) ipv6_result;
  while (ipv6_result->ai_next != NULL)
    {
      count++;
      ipv6_result = ipv6_result->ai_next;
    }
  result->length = ++count;     /* Have to add an extra, because we didn't count the first entry */

  return 0;
#else /* IPv6_FUNCTION_SUPPORT */
  I_32 rc = 0;
  U_32 addr = 0;
  U_32 count = 0;
  struct hyhostent_struct hyhostent;
  if (0 != portLibrary->sock_inetaddr (portLibrary, name, &addr))
    {
      if (0 !=
          (rc =
           portLibrary->sock_gethostbyname (portLibrary, name, &hyhostent)))
        {
          return rc;
        }
    }
  else
    {
      if (0 !=
          (rc =
           portLibrary->sock_gethostbyaddr (portLibrary, (char *) &addr,
                                            sizeof (addr), HYSOCK_AFINET,
                                            &hyhostent)))
        {
          return rc;
        }
    }
  memset (result, 0, sizeof (struct hyaddrinfo_struct));
  result->addr_info = (void *) hyhostent.entity;
  /* count the host names and the addresses */
  while (hyhostent.entity->h_addr_list[count] != 0)
    {
      count++;
    }
  result->length = count;
  return 0;
#endif /* IPv6_FUNCTION_SUPPORT */

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo_address

/**
 * Answers a U_8 array representing the address at "index" in the structure returned from @ref hysock_getaddrinfo, indexed starting at 0.
 * The address is in network byte order. 
 *
 * The address will either be 4 or 16 bytes depending on whether it is an OS_AF_INET  address or an OS_AF_INET6  address.  You can check
 * this will a call to @ref hysock_getaddrinfo_family.  Therefore you should either check the family type before preallocating the "address"
 * or define it as 16 bytes.
 *
 * @param[in]   portLibrary The port library.
 * @param[in]   handle The result structure returned by @ref hysock_getaddrinfo.
 * @param[out] address The address at "index" in a preallocated buffer.
 * @param[in]   index The address index into the structure returned by @ref hysock_getaddrinfo.
 * @param[out] scope_id  The scope id associated with the address if applicable
 *
 * @return	
 * \arg 0, if no errors occurred, otherwise the (negative) error code
 * \arg HYPORT_ERROR_SOCKET_VALUE_NULL when we have the old IPv4 gethostbyname call and the address indexed is out
 * of range.  This is because the address list and the host alias list are not the same length.  Just skip this entry.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo_address (struct HyPortLibrary * portLibrary,
                            hyaddrinfo_t handle, U_8 * address, int index,
                            U_32 * scope_id)
{
  I_32 rc = 0;
  OSADDRINFO *addr;
  void *sock_addr;
#if !defined(IPv6_FUNCTION_SUPPORT)
  char **addr_list;
#endif
  int i;

  /* If we have the IPv6 functions available we cast to an OSADDRINFO structure otherwise a OSHOSTENET structure */
#if defined(IPv6_FUNCTION_SUPPORT)
  addr = (OSADDRINFO *) handle->addr_info;
  for (i = 0; i < index; i++)
    {
      addr = addr->ai_next;
    }
  if (addr->ai_family == OS_AF_INET6)
    {
      sock_addr = ((OSSOCKADDR_IN6 *) addr->ai_addr)->sin6_addr.s6_addr;
      memcpy (address, sock_addr, 16);
      *scope_id = ((OSSOCKADDR_IN6 *) addr->ai_addr)->sin6_scope_id;
    }
  else
    {
      sock_addr = &((OSSOCKADDR *) addr->ai_addr)->sin_addr.s_addr;
      memcpy (address, sock_addr, 4);
    }
#else
  addr_list = ((OSHOSTENT *) handle->addr_info)->h_addr_list;
  for (i = 0; i < index; i++)
    {
      if (addr_list[i] == NULL)
        {
          return HYPORT_ERROR_SOCKET_VALUE_NULL;
        }
    }
  memcpy (address, addr_list[index], 4);
#endif

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo_create_hints

/**
 * Answers a hints structure as an opaque pointer in "result".
 * 
 * This hints structure is used to modify the results returned by a call to @ref hysock_getaddrinfo.  There is one of
 * these structures per thread, so subsequent calls to this function will overwrite the same structure in memory.
 * The structure is cached in ptBuffers and is cleared when a call to @ref hyport_free_ptBuffer is made.
 *
 * This function is only works on IPv6 supported OS's.  If it is called on an OS that does not support IPv6 then
 * it essentially returns a NULL pointer, meaning it will have no effect on the call to @ref hysock_getaddrinfo.
 *
 * See man pages, or MSDN doc on getaddrinfo for information on how the hints structure works.
 *
 * @param[in] portLibrary The port library.
 * @param[out] result The filled in (per thread) hints structure
 * @param[in] family A address family type
 * @param[in] socktype A socket type
 * @param[in] protocol A protocol family
 * @param[in] flags Flags for modifying the result
 *
 * @return
 * \arg 0, if no errors occurred, otherwise the (negative) error code
 * \arg HYPORT_ERROR_SOCKET_SYSTEMFULL -- when we can't allocate memory for the ptBuffers, or the hints structure
 *
 * @note current supported family types are:
 * \arg HYADDR_FAMILY_UNSPEC
 * \arg HYADDR_FAMILY_AFINET4
 * \arg HYADDR_FAMILY_AFINET6
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo_create_hints (struct HyPortLibrary * portLibrary,
                                 hyaddrinfo_t * result, I_16 family,
                                 I_32 socktype, I_32 protocol, I_32 flags)
{
  I_32 rc = 0;
#if defined(IPv6_FUNCTION_SUPPORT)
  OSADDRINFO *addrinfo;
  PortlibPTBuffers_t ptBuffers;
#endif /* IPv6_FUNCTION_SUPPORT */

  *result = NULL;

  /* If we have the IPv6 functions available we fill in the structure, otherwise it is null */
#if defined(IPv6_FUNCTION_SUPPORT)
#define addrinfohints (ptBuffers->addr_info_hints).addr_info
  /* Initialized the pt buffers if necessary */
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }
  if (!addrinfohints)
    {
      addrinfohints =
        portLibrary->mem_allocate_memory (portLibrary, sizeof (OSADDRINFO));
      if (!addrinfohints)
        {
          return HYPORT_ERROR_SOCKET_SYSTEMFULL;
        }
    }
  memset (addrinfohints, 0, sizeof (OSADDRINFO));
  addrinfo = (OSADDRINFO *) addrinfohints;
  addrinfo->ai_flags = flags;
  addrinfo->ai_family = map_addr_family_Hy_to_OS (family);
  addrinfo->ai_socktype = map_sockettype_Hy_to_OS (socktype);
  addrinfo->ai_protocol = map_protocol_family_Hy_to_OS (protocol);
  *result = &ptBuffers->addr_info_hints;
#undef addrinfohints
#endif /*IPv6_FUNCTION_SUPPORT */

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo_family

/**
 * Answers the family type of the address at "index" in the structure returned from @ref hysock_getaddrinfo, indexed starting at 0.
 *
 * Currently the family types we support are HYSOCK_AFINET and HYSOCK_AFINET6.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle The result structure returned by @ref hysock_getaddrinfo.
 * @param[out] family The family at "index".
 * @param[in] index The address index into the structure returned by @ref hysock_getaddrinfo.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo_family (struct HyPortLibrary * portLibrary,
                           hyaddrinfo_t handle, I_32 * family, int index)
{
  I_32 rc = 0;
  OSADDRINFO *addr;
  int i;

  /* If we have the IPv6 functions then we'll cast to a OSADDRINFO otherwise we have a hostent */
#if defined(IPv6_FUNCTION_SUPPORT)
  addr = (OSADDRINFO *) handle->addr_info;
  for (i = 0; i < index; i++)
    {
      addr = addr->ai_next;
    }
  if (addr->ai_family == OS_AF_INET4)
    {
      *family = HYADDR_FAMILY_AFINET4;
    }
  else
    {
      *family = HYADDR_FAMILY_AFINET6;
    }
#else
  *family = HYADDR_FAMILY_AFINET4;
#endif

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo_length

/**
 * Answers the number of results returned from @ref hysock_getaddrinfo.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle The result structure returned by @ref hysock_getaddrinfo.
 * @param[out] length The number of results.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo_length (struct HyPortLibrary * portLibrary,
                           hyaddrinfo_t handle, I_32 * length)
{
  *length = handle->length;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getaddrinfo_name

/**
 * Answers the cannon name of the address at "index" in the structure returned from @ref hysock_getaddrinfo, indexed starting at 0.
 * 
 * The preallocated buffer for "name" should be the size of the maximum host name: OSNIMAXHOST.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle The result structure returned by @ref hysock_getaddrinfo.
 * @param[out] name The name of the address at "index" in a preallocated buffer.
 * @param[in] index The address index into the structure returned by @ref hysock_getaddrinfo.
 *
 * @return
 * \arg 0, if no errors occurred, otherwise the (negative) error code.
 * \arg HYPORT_ERROR_SOCKET_VALUE_NULL when we have the old IPv4 gethostbyname call and the name indexed is out
 * of range.  This is because the address list and the host alias list are not the same length.  Just skip this entry.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_getaddrinfo_name (struct HyPortLibrary * portLibrary,
                         hyaddrinfo_t handle, char *name, int index)
{
  I_32 rc = 0;
#if !defined(IPv6_FUNCTION_SUPPORT)
  char **alias_list;
#endif
  int i;
  OSADDRINFO *addr;

  /* If we have the IPv6 functions available we cast to an OSADDRINFO structure otherwise a OSHOSTENET structure */
#if defined(IPv6_FUNCTION_SUPPORT)
  addr = (OSADDRINFO *) handle->addr_info;
  for (i = 0; i < index; i++)
    {
      addr = addr->ai_next;
    }
  if (addr->ai_canonname == NULL)
    {
      name[0] = 0;
    }
  else
    {
      strcpy (name, addr->ai_canonname);
    }
#else
  alias_list = ((OSHOSTENT *) handle->addr_info)->h_aliases;
  for (i = 0; i < index; i++)
    {
      if (alias_list[i] == NULL)
        {
          return HYPORT_ERROR_SOCKET_VALUE_NULL;
        }
    }
  if (alias_list[index] == NULL)
    {
      name[0] = 0;
    }
  else
    {
      strcpy (name, alias_list[index]);
    }
#endif

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_gethostbyaddr

/**
 * Answer information on the host referred to by the address.  The information includes name, aliases and
 * addresses for the nominated host (the latter being relevant on multi-homed hosts).
 * This call has only been tested for addresses of type AF_INET.
 *
 * @param[in] portLibrary The port library.
 * @param[in] addr Pointer to the binary-format (not null-terminated) address, in network byte order.
 * @param[in] length Length of the addr.
 * @param[in] type The type of the addr.
 * @param[out] handle Pointer to the hyhostent_struct, to be linked to the per thread platform hostent struct.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_gethostbyaddr (struct HyPortLibrary * portLibrary, char *addr,
                      I_32 length, I_32 type, hyhostent_t handle)
{
#if !HOSTENT_DATA_R
  OSHOSTENT *result;
#endif

#if GLIBC_R||OTHER_R
  BOOLEAN allocBuffer = FALSE;
#endif

  int herr = 0;
  int i = 0;

#if HOSTENT_DATA_R||GLIBC_R||OTHER_R||NO_R
  PortlibPTBuffers_t ptBuffers;
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }
#define hostentBuffer (&ptBuffers->hostent)
#endif /*HOSTENT_DATA_R || GLIBC_R || OTHER_R */

/* one of several threadsafe gethostbyaddr calls must be made depending on the current platform */
/* if there is a transient error (HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN), try making the call again */
  for (i = 0; i < MAX_RETRIES; i++)
    {
#if HOSTENT_DATA_R
#define dataBuffer (ptBuffers->hostent_data)
      if (NULL == dataBuffer)
        {
          dataBuffer =
            portLibrary->mem_allocate_memory (portLibrary,
                                              sizeof (OSHOSTENT_DATA));
          if (NULL == dataBuffer)
            {
              return HYPORT_ERROR_SOCKET_SYSTEMFULL;
            }
        }
      herr = gethostbyaddr_r (addr, length, type, hostentBuffer, dataBuffer);
#undef dataBuffer
#elif ORIGINAL_R || NO_R
#if NO_R
      MUTEX_ENTER (hostentLock);
#endif
      result = gethostbyaddr (addr, length, type);
#if NO_R
      if (result)
        {
          herr = copy_hostent (portLibrary, result, &ptBuffers);
        }
      MUTEX_EXIT (hostentLock);
      if (herr != 0)
        {
          return herr;
        }
#endif
      herr = h_errno;
#elif GLIBC_R || OTHER_R
#define buffer (ptBuffers->gethostBuffer)
#define bufferSize (ptBuffers->gethostBufferSize)
      if (NULL == buffer)
        {
          bufferSize = GET_HOST_BUFFER_SIZE;
        }
      while (TRUE)
        {
          if (allocBuffer == TRUE || (NULL == buffer))
            {
              /* The buffer is allocated bufferSize + EXTRA_SPACE, while gethostby*_r is only aware of bufferSize
               * because there seems to be a bug on Linux 386 where gethostbyname_r writes past the end of the
               * buffer.  This bug has not been observed on other platforms, but EXTRA_SPACE is added anyway as a precaution.*/
              buffer =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  bufferSize + EXTRA_SPACE);
              if (NULL == buffer)
                {
                  return HYPORT_ERROR_SOCKET_SYSTEMFULL;
                }
              allocBuffer = FALSE;
            }
#if GLIBC_R
          gethostbyaddr_r (addr, length, type, hostentBuffer, buffer,
                           bufferSize, &result, &herr);
#elif OTHER_R
          result =
            gethostbyaddr_r (addr, length, type, hostentBuffer, buffer,
                             bufferSize, &herr);
#endif /* GLIBC_R */
/* allocate more space if the buffer is too small */
          if (errno == ERANGE || herr == ERANGE)
            {
              portLibrary->mem_free_memory (portLibrary, buffer);
              bufferSize *= 2;
              allocBuffer = TRUE;
            }
          else
            {
              break;
            }
        }
#undef buffer
#undef bufferSize
#endif

      if (herr != HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN)
        {
          break;
        }
    }

#if HOSTENT_DATA_R
  if (herr != 0)
    {
      herr = h_errno;
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#else
  if (result == NULL)
    {
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#endif

  else
    {
      memset (handle, 0, sizeof (struct hyhostent_struct));
#if HOSTENT_DATA_R||NO_R
      handle->entity = hostentBuffer;
#else
      handle->entity = result;
#endif

    }
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R
#undef hostentBuffer
#endif /*HOSTENT_DATA_R || GLIBC_R || OTHER_R */

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_gethostbyname

/**
 * Answer information on the host, specified by name.  The information includes host name,
 * aliases and addresses.
 *
 * @param[in] portLibrary The port library.
 * @param[in] name The host name string.
 * @param[out] handle Pointer to the hyhostent_struct (to be linked to the per thread platform hostent struct).
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_gethostbyname (struct HyPortLibrary * portLibrary, const char *name,
                      hyhostent_t handle)
{
#if !HOSTENT_DATA_R
  OSHOSTENT *result;
#endif

#if GLIBC_R||OTHER_R
  BOOLEAN allocBuffer = FALSE;
#endif

  int herr = 0;
  int i = 0;

#if HOSTENT_DATA_R||GLIBC_R||OTHER_R||NO_R
  PortlibPTBuffers_t ptBuffers;
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }
#define hostentBuffer (&ptBuffers->hostent)
#endif /*HOSTENT_DATA_R || GLIBC_R || OTHER_R */

/* one of several threadsafe gethostbyname calls must be made depending on the current platform */
/* if there is a transient error (HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN), try making the call again */
  for (i = 0; i < MAX_RETRIES; i++)
    {
#if HOSTENT_DATA_R
#define dataBuffer (ptBuffers->hostent_data)
      if (!dataBuffer)
        {
          dataBuffer =
            portLibrary->mem_allocate_memory (portLibrary,
                                              sizeof (OSHOSTENT_DATA));
          if (!dataBuffer)
            {
              return HYPORT_ERROR_SOCKET_SYSTEMFULL;
            }
        }
      herr = gethostbyname_r (name, hostentBuffer, dataBuffer);
#undef dataBuffer
#elif ORIGINAL_R || NO_R
#if NO_R
      MUTEX_ENTER (hostentLock);
#endif
      result = gethostbyname (name);
#if NO_R
      if (result)
        {
          herr = copy_hostent (portLibrary, result, &ptBuffers);
        }
      MUTEX_EXIT (hostentLock);
      if (herr != 0)
        {
          return herr;
        }
#endif
      herr = h_errno;
#elif GLIBC_R || OTHER_R
#define buffer (ptBuffers->gethostBuffer)
#define bufferSize (ptBuffers->gethostBufferSize)
      if (!buffer)
        {
          bufferSize = GET_HOST_BUFFER_SIZE;
        }
      while (TRUE)
        {
          if (allocBuffer == TRUE || !buffer)
            {
              /* The buffer is allocated bufferSize + EXTRA_SPACE, while gethostby*_r is only aware of bufferSize
               * because there seems to be a bug on Linux 386 where gethostbyname_r writes past the end of the
               * buffer.  This bug has not been observed on other platforms, but EXTRA_SPACE is added anyway as a precaution.*/
              buffer =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  bufferSize + EXTRA_SPACE);
              if (!buffer)
                {
                  return HYPORT_ERROR_SOCKET_SYSTEMFULL;
                }
              allocBuffer = FALSE;
            }
#if GLIBC_R
          gethostbyname_r (name, hostentBuffer, buffer, bufferSize, &result,
                           &herr);
#elif OTHER_R
          result =
            gethostbyname_r (name, hostentBuffer, buffer, bufferSize, &herr);
#endif /* GLIBC_R */
/* allocate more space if the buffer is too small */
          if (errno == ERANGE || herr == ERANGE)
            {
              portLibrary->mem_free_memory (portLibrary, buffer);
              bufferSize *= 2;
              allocBuffer = TRUE;
            }
          else
            {
              break;
            }
        }
#undef buffer
#undef bufferSize
#endif

      if (herr != HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN)
        {
          break;
        }
    }

#if HOSTENT_DATA_R
  if (herr != 0)
    {
      herr = h_errno;
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#else
  if (result == NULL)
    {
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#endif

  else
    {
      memset (handle, 0, sizeof (struct hyhostent_struct));
#if HOSTENT_DATA_R||NO_R
      handle->entity = hostentBuffer;
#else
      handle->entity = result;
#endif

    }
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R
#undef hostentBuffer
#endif /*HOSTENT_DATA_R || GLIBC_R || OTHER_R */

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_gethostname

/**
 * Answer the name of the local host machine.
 *
 * @param[in] portLibrary The port library.
 * @param[in,out] buffer The string buffer to receive the name
 * @param[in] length The length of the buffer
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code
 */
I_32 VMCALL
hysock_gethostname (struct HyPortLibrary * portLibrary, char *buffer,
                    I_32 length)
{
  if (gethostname (buffer, length) != 0)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<gethostname failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getnameinfo

/**
 * Answers the host name of the "in_addr" in a preallocated buffer.
 *
 * The preallocated buffer for "name" should be the size of the maximum host name: OSNIMAXHOST.
 * Currently only AF_INET and AF_INET6 are supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] in_addr The address we want to do a name lookup on
 * @param[in] sockaddr_size The size of "in_addr"
 * @param[out] name The hostname of the passed address in a preallocated buffer.
 * @param[in] name_length The length of the buffer pointed to by name
 * @param[in] flags Flags on how to form the repsonse (see man pages or doc for getnameinfo)
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code
 *
 * @note Added for IPv6 support.
 * @note "flags" do not affect results on OS's that do not support the IPv6 calls.
 */
I_32 VMCALL
hysock_getnameinfo (struct HyPortLibrary * portLibrary, hysockaddr_t in_addr,
                    I_32 sockaddr_size, char *name, I_32 name_length,
                    int flags)
{

/* If we have the IPv6 functions available we will call them, otherwise we'll call the IPv4 function */
#if defined(IPv6_FUNCTION_SUPPORT)
  int rc = 0;
  rc =
    getnameinfo ((OSADDR *) & in_addr->addr, sizeof (in_addr->addr), name,
                 name_length, NULL, 0, flags);
  if (rc != 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<gethostbyaddr failed, err=%d>\n", rc);
      return portLibrary->error_set_last_error (portLibrary, rc,
                                                findError (rc));
    }
  return 0;
#else /* IPv6_FUNCTION_SUPPORT */
#if !HOSTENT_DATA_R
  OSHOSTENT *result;
#endif

#if GLIBC_R||OTHER_R
  BOOLEAN allocBuffer = FALSE;
#endif
  int herr = 0;
  int i = 0;
  int rc = 0;
  int length;
  OSSOCKADDR *addr;
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R||NO_R
  PortlibPTBuffers_t ptBuffers;
#endif /* HOSTENT_DATA_R || GLIBC_R || OTHER_R || NO_R */
  addr = (OSSOCKADDR *) & in_addr->addr;
  if (addr->sin_family == OS_AF_INET4)
    {
      length = 4;
    }
  else
    {
      length = 16;
    }
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R||NO_R
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }
#define hostentBuffer (&ptBuffers->hostent)
#endif /* HOSTENT_DATA_R || GLIBC_R || OTHER_R || NO_R */
/* one of several threadsafe gethostbyaddr calls must be made depending on the current platform */
/* if there is a transient error (HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN), try making the call again */
  for (i = 0; i < MAX_RETRIES; i++)
    {
#if HOSTENT_DATA_R
#define dataBuffer (ptBuffers->hostent_data)
      if (!dataBuffer)
        {
          dataBuffer =
            portLibrary->mem_allocate_memory (portLibrary,
                                              sizeof (OSHOSTENT_DATA));
          if (!dataBuffer)
            {
              return HYPORT_ERROR_SOCKET_SYSTEMFULL;
            }
        }
      herr =
        gethostbyaddr_r (&addr->sin_addr, length, addr->sin_family,
                         hostentBuffer, dataBuffer);
#undef dataBuffer
#elif ORIGINAL_R || NO_R
#if NO_R
      MUTEX_ENTER (hostentLock);
#endif
      result = gethostbyaddr (&addr->sin_addr, length, addr->sin_family);
#if NO_R
      if (result)
        {
          herr = copy_hostent (portLibrary, result, &ptBuffers);
        }
      MUTEX_EXIT (hostentLock);
      if (herr != 0)
        {
          return herr;
        }
#endif
      herr = h_errno;
#elif GLIBC_R || OTHER_R
#define buffer (ptBuffers->gethostBuffer)
#define bufferSize (ptBuffers->gethostBufferSize)
      if (!buffer)
        {
          bufferSize = GET_HOST_BUFFER_SIZE;
        }
      while (TRUE)
        {
          if (allocBuffer == TRUE || !buffer)
            {
              /* The buffer is allocated bufferSize + EXTRA_SPACE, while gethostby*_r is only aware of bufferSize
               * because there seems to be a bug on Linux 386 where gethostbyname_r writes past the end of the
               * buffer.  This bug has not been observed on other platforms, but EXTRA_SPACE is added anyway as a precaution.*/
              buffer =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  bufferSize + EXTRA_SPACE);
              if (!buffer)
                {
                  return HYPORT_ERROR_SOCKET_SYSTEMFULL;
                }
              allocBuffer = FALSE;
            }
#if GLIBC_R
          gethostbyaddr_r ((char *) &addr->sin_addr, length, addr->sin_family,
                           hostentBuffer, buffer, bufferSize, &result, &herr);
#elif OTHER_R
          result =
            gethostbyaddr_r ((char *) &addr->sin_addr, length,
                             addr->sin_family, hostentBuffer, buffer,
                             bufferSize, &herr);
#endif /* GLIBC_R */
/* allocate more space if the buffer is too small */
          if (errno == ERANGE || herr == ERANGE)
            {
              portLibrary->mem_free_memory (portLibrary, buffer);
              bufferSize *= 2;
              allocBuffer = TRUE;
            }
          else
            {
              break;
            }
        }
#undef buffer
#undef bufferSize
#endif
      if (herr != HYPORT_ERROR_SOCKET_UNIX_TRYAGAIN)
        {
          break;
        }
    }
#if HOSTENT_DATA_R
  if (herr != 0)
    {
      herr = h_errno;
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#else
  if (result == NULL)
    {
      HYSOCKDEBUGH ("<gethostbyaddr failed, err=%d>\n", herr);
      return portLibrary->error_set_last_error (portLibrary, herr,
                                                findHostError (herr));
    }
#endif
  else
    {
      memset (name, 0, sizeof (char) * name_length);
#if HOSTENT_DATA_R||NO_R
      strcpy (name, hostentBuffer->h_name);
#else
      strcpy (name, result->h_name);
#endif
    }
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R
#undef hostentBuffer
#endif /*HOSTENT_DATA_R || GLIBC_R || OTHER_R */
  return 0;
#endif /* IPv6_FUNCTION_SUPPORT */

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getopt_bool

/**
 * Answer the value of the nominated boolean socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to query for the option value.
 * @param[in] optlevel	 The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to retrieve.
 * @param[out] optval Pointer to the boolean to update with the option value.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getopt_bool (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                    I_32 optlevel, I_32 optname, BOOLEAN * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);
  U_8 uCharOptval = 0;

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (OS_MCAST_LOOP == platformOption)
    {
      /* most options are gotten using an 32 bit int which matches the definition of BOOLEAN.  Howerver, for unix
         platforms this option is gotten with a unsighed char.  Some platforms accept both but some such as AIX
         and false return an EINVAL if we try to set with an int instead of a unsigned char.  For windows platforms
         the spec indicates that it is get with a DWORD which seems to match the BOOLEAN.  Therefore since this
         is a platform specific case for a boolean option we handle it as a special case within this method */
      optlen = sizeof (uCharOptval);
      if (0 !=
          getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) &uCharOptval, &optlen))
        {
          I_32 err = errno;
          HYSOCKDEBUG ("<getsockopt (for bool) failed, err=%d>\n", err);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
      *optval = uCharOptval;
    }
  else
    {
      if (0 !=
          getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) optval, &optlen))
        {
          I_32 err = errno;
          HYSOCKDEBUG ("<getsockopt (for bool) failed, err=%d>\n", err);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
    }
  /* 0 indicates the option is disabled, while non-zero indicates that the option is enabled.  The caller
     of this method assumes that the value returned is 1 if the option is enabled.  For many platforms
     but on some platforms it is not the case for some boolean options (ex AIX for SO_OOBINLINE).
     Therefore, make sure we return 1 if the value is non zero */
  if (*optval != 0)
    {
      *optval = 1;
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getopt_byte

/**
 * Answer the value of the nominated byte socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to query for the option value.
 * @param[in] optlevel	 The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to retrieve.
 * @param[out] optval Pointer to the byte to update with the option value.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getopt_byte (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                    I_32 optlevel, I_32 optname, U_8 * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) optval, &optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getsockopt (for byte) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getopt_int

/**
 * Answer the value of the nominated integer socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to query for the option value.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to retrieve.
 * @param[out] optval Pointer to the integer to update with the option value.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getopt_int (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                   I_32 optlevel, I_32 optname, I_32 * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) optval, &optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getsockopt (for int) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getopt_linger

/**
 * Answer the value of the socket linger option.
 * See the @ref hysock_linger_init for details of the linger behavior.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to query for the option value
 * @param[in] optlevel The level within the IP stack at which the option is defined
 * @param[in] optname The name of the option to retrieve
 * @param[out] optval Pointer to the linger struct to update with the option value
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code
 */
I_32 VMCALL
hysock_getopt_linger (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                      I_32 optlevel, I_32 optname, hylinger_t optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (optval->linger);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (char *) (&optval->linger), &optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getsockopt (for linger) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getopt_sockaddr

/**
 * Answer the value of the socket option, an address struct.
 * Currently only used to retrieve the interface of multicast sockets, 
 * but the more general call style has been used.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to query for the option value.
 * @param[in] optlevel	 The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to retrieve.
 * @param[out] optval Pointer to the sockaddr struct to update with the option value.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getopt_sockaddr (struct HyPortLibrary * portLibrary,
                        hysocket_t socketP, I_32 optlevel, I_32 optname,
                        hysockaddr_t optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  U_32 val[3];
  socklen_t optlen = sizeof (val);
  OSSOCKADDR *sockaddr;

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      getsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (char *) val, &optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getsockopt (for sockaddr) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  sockaddr = (OSSOCKADDR *) & optval->addr;
  if (optlen == 4)
    {
      sockaddr->sin_addr.s_addr = val[0];       /* false */
    }
  else
    {                           /*Linux */
      sockaddr->sin_addr.s_addr = val[1];
      sockaddr->sin_port = val[2];
    }

  /* A temporary fix as Linux false (maybe others) returns 0 for the family.  Maybe there is a way to get it to return the
     right family.  However we now depend on the family to be set correctly to determine whether or not we have an 
     IPv6 address or not.  For now, we'll assume that this function will only be called for IPv4 multicast sockets */
  sockaddr->sin_family = OS_AF_INET4;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getpeername

/**
 * Answer the remote name for the socket.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the socket to get the address of.
 * @param[out] addrHandle Pointer to the sockaddr struct to update with the address.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getpeername (struct HyPortLibrary * portLibrary, hysocket_t handle,
                    hysockaddr_t addrHandle)
{
  socklen_t addrlen = getAddrLength(addrHandle);

  if (getpeername
      (SOCKET_CAST (handle), (struct sockaddr *) &addrHandle->addr,
       &addrlen) != 0)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getpeername failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_getsockname

/**
 * Answer the local name for the socket.  Note, the stack getsockname function
 * actually answers a sockaddr structure, not a string name as the function name
 * might imply.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the socket to get the address of.
 * @param[out] addrHandle Pointer to the sockaddr struct to update with the address.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_getsockname (struct HyPortLibrary * portLibrary, hysocket_t handle,
                    hysockaddr_t addrHandle)
{
  socklen_t addrlen = sizeof (addrHandle->addr);

  if (getsockname
      (SOCKET_CAST (handle), (struct sockaddr *) &addrHandle->addr,
       &addrlen) != 0)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<getsockname failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_hostent_addrlist

/**
 * Answer the nominated element of the address list within the argument hostent struct.
 * The address is in network order.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hostent struct, in which to access the addr_list.
 * @param[in] index The index of the element within the addr_list to retrieve.
 *
 * @return	the address, in network order.
 */
I_32 VMCALL
hysock_hostent_addrlist (struct HyPortLibrary * portLibrary,
                         hyhostent_t handle, U_32 index)
{
  return *((I_32 *) handle->entity->h_addr_list[index]);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_hostent_aliaslist

/**
 * Answer a reference to the list of alternative names for the host within the argument hostent struct.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hostent struct, in which to access the addr_list
 * @param[out] aliasList Pointer to the list of alternative names, to be updated 
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code
 */
I_32 VMCALL
hysock_hostent_aliaslist (struct HyPortLibrary * portLibrary,
                          hyhostent_t handle, char ***aliasList)
{
  *aliasList = handle->entity->h_addr_list;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_hostent_hostname

/**
 * Answer the host name (string) within the argument hostent struct.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hostent struct, in which to access the hostName.
 * @param[out] hostName Host name string.
 *
 * @return	0, the function does not validate the name access.
 */
I_32 VMCALL
hysock_hostent_hostname (struct HyPortLibrary * portLibrary,
                         hyhostent_t handle, char **hostName)
{
  *hostName = handle->entity->h_name;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_htonl

/**
 * Answer the 32 bit host ordered argument, in network byte order.
 *
 * @param[in] portLibrary The port library.
 * @param[in] val The 32 bit host ordered number.
 *
 * @return	the 32 bit network ordered number.
 */
I_32 VMCALL
hysock_htonl (struct HyPortLibrary * portLibrary, I_32 val)
{
  return htonl (val);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_htons

/**
 * Answer the 16 bit host ordered argument, in network byte order.
 *
 * @param[in] portLibrary The port library.
 * @param[in] val The 16 bit host ordered number.
 *
 * @return	the 16 bit network ordered number.
 */
U_16 VMCALL
hysock_htons (struct HyPortLibrary * portLibrary, U_16 val)
{
  return htons (val);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_inetaddr

/**
 * Answer the dotted IP string as an Internet address.
 *
 * @param[in] portLibrary The port library.
 * @param[in] addrStr The dotted IP string.
 * @param[out] addr Pointer to the Internet address.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_inetaddr (struct HyPortLibrary * portLibrary, const char *addrStr,
                 U_32 * addr)
{
  I_32 rc = 0;

#if !defined(ZOS)
  struct in_addr in;
  if (inet_aton(addrStr, &in) == 0)
    {
      HYSOCKDEBUGPRINT ("<inet_aton failed>\n");
      rc = HYPORT_ERROR_SOCKET_ADDRNOTAVAIL;
    }
  else
    {
      *addr = in.s_addr;
    }

#else
  /* on zOS we do not have inet_aton so revert to the previous inet_addr implementation */
  U_32 val;

  val = inet_addr (addrStr);
  if (val == -1)
    {
      HYSOCKDEBUGPRINT ("<inet_addr failed>\n");
      rc = HYPORT_ERROR_SOCKET_ADDRNOTAVAIL;
    }
  else
    {
      *addr = val;
  }
#endif

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_inetntoa

/**
 * Answer the Internet address as a dotted IP string.
 *
 * @param[in] portLibrary The port library.
 * @param[out] addrStr The dotted IP string.
 * @param[in] nipAddr The Internet address.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_inetntoa (struct HyPortLibrary * portLibrary, char **addrStr,
                 U_32 nipAddr)
{
  U_8 *addr = (U_8 *) & nipAddr;
  PortlibPTBuffers_t ptBuffers;

  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return HYPORT_ERROR_SOCKET_SYSTEMFULL;
    }
  portLibrary->str_printf (portLibrary, ptBuffers->ntoa, NTOA_SIZE,
                           "%d.%d.%d.%d", addr[0], addr[1], addr[2], addr[3]);
  *addrStr = ptBuffers->ntoa;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_ipmreq_init

/**
 * Initializes a new multicast membership structure.  The membership structure is used to join & leave
 * multicast groups @see hysock_setopt_ipmreq.  The group may be joined using 0 (HYSOCK_INADDR_ANY)
 * as the local interface, in which case the default local address will be used.
 *
 * @param[in] portLibrary The port library.
 * @param[out] handle Pointer to the multicast membership struct.
 * @param[in] nipmcast The address, in network order, of the multicast group to join.
 * @param[in] nipinterface The address, in network order, of the local machine interface to join on.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_ipmreq_init (struct HyPortLibrary * portLibrary, hyipmreq_t handle,
                    U_32 nipmcast, U_32 nipinterface)
{
  memset (handle, 0, sizeof (struct hyipmreq_struct));

  handle->addrpair.imr_multiaddr.s_addr = nipmcast;
  handle->addrpair.imr_interface.s_addr = nipinterface;
  return 0;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_ipv6_mreq_init

/**
 * Fills in a preallocated hyipv6_mreq_struct
 *
 * @param[in] portLibrary The port library.
 * @param[out] handle A pointer to the hyipv6_mreq_struct to populate.
 * @param[in] ipmcast_addr The ip mulitcast address.
 * @param[in] ipv6mr_interface The ip mulitcast inteface.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_ipv6_mreq_init (struct HyPortLibrary * portLibrary,
                       hyipv6_mreq_t handle, U_8 * ipmcast_addr,
                       U_32 ipv6mr_interface)
{
#if defined(IPv6_FUNCTION_SUPPORT)
  memset (handle, 0, sizeof (struct hyipmreq_struct));
  memcpy (handle->mreq.ipv6mr_multiaddr.s6_addr, ipmcast_addr, 16);
  handle->mreq.ipv6mr_interface = ipv6mr_interface;
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_linger_enabled

/**
 * Answer true if the linger is enabled in the argument linger struct.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the linger struct to be accessed.
 * @param[out] enabled Pointer to the boolean to be updated with the linger status.
 *
 * @return	0, the function does not validate the access.
 */
I_32 VMCALL
hysock_linger_enabled (struct HyPortLibrary * portLibrary, hylinger_t handle,
                       BOOLEAN * enabled)
{
  *enabled = (BOOLEAN) (handle->linger.l_onoff);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_linger_init

/**
 * Initializes a new linger structure, enabled or disabled, with the timeout as specified.
 * Linger defines the behavior when unsent messages exist for a socket that has been sent close.
 * If linger is disabled, the default, close returns immediately and the stack attempts to deliver unsent messages.
 * If linger is enabled:
 * \arg if the timeout is 0, the close will block indefinitely until the messages are sent
 * \arg if the timeout is set, the close will return after the messages are sent or the timeout period expired
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the linger struct to be accessed.
 * @param[in] enabled Aero to disable, a non-zero value to enable linger.
 * @param[in] timeout	 0 to linger indefinitely or a positive timeout value (in seconds).
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_linger_init (struct HyPortLibrary * portLibrary, hylinger_t handle,
                    I_32 enabled, U_16 timeout)
{
  memset (handle, 0, sizeof (struct hylinger_struct));
  handle->linger.l_onoff = enabled;
  handle->linger.l_linger = (I_32) timeout;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_linger_linger

/**
 * Answer the linger timeout value in the argument linger struct.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the linger struct to be accessed.
 * @param[out] linger Pointer to the integer, to be updated with the linger value (in seconds).
 *
 * @return	0, the function does not validate the access.
 */
I_32 VMCALL
hysock_linger_linger (struct HyPortLibrary * portLibrary, hylinger_t handle,
                      U_16 * linger)
{
  *linger = (U_16) (handle->linger.l_linger);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_listen

/**
 * Set the socket to listen for incoming connection requests.  This call is made prior to accepting requests,
 * via the @ref hysock_accept function.  The backlog specifies the maximum length of the queue of pending connections,
 * after which further requests are rejected.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Pointer to the socket to modify.
 * @param[in] backlog The maximum number of queued requests.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_listen (struct HyPortLibrary * portLibrary, hysocket_t sock,
               I_32 backlog)
{
  I_32 rc = 0;

  if (listen (SOCKET_CAST (sock), backlog) < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<listen failed, err=%d>\n", rc);
      rc =
        portLibrary->error_set_last_error (portLibrary, rc,
                                           HYPORT_ERROR_SOCKET_OPFAILED);
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_ntohl

/**
 * Answer the 32 bit network ordered argument, in host byte order.
 *
 * @param[in] portLibrary The port library.
 * @param[in] val The 32 bit network ordered number.
 *
 * @return	the 32 bit host ordered number.
 */
I_32 VMCALL
hysock_ntohl (struct HyPortLibrary * portLibrary, I_32 val)
{
  return ntohl (val);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_ntohs

/**
 * Answer the 16-bit network ordered argument, in host byte order.
 *
 * @param[in] portLibrary The port library.
 * @param[in] val The 16-bit network ordered number.
 *
 * @return	the 16-bit host ordered number.
 */
U_16 VMCALL
hysock_ntohs (struct HyPortLibrary * portLibrary, U_16 val)
{
  return ntohs (val);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_read

/**
 * The read function receives data from a connected socket.  Calling read will return as much 
 *	information as is currently available up to the size of the buffer supplied. If no incoming 
 * data is available at the socket, the read call blocks and waits for data to arrive.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Pointer to the socket to read on
 * @param[out] buf Pointer to the buffer where input bytes are written
 * @param[in] nbyte The length of buf
 * @param[in] flags The flags, to influence this read (in addition to the socket options)
 *
 * @return
 * \arg If no error occurs, return the number of bytes received.
 * \arg If the connection has been gracefully closed, return 0.
 * \arg Otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_read (struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
             I_32 nbyte, I_32 flags)
{
  I_32 bytesRec = 0;

  bytesRec = recv (SOCKET_CAST (sock), buf, nbyte, flags);
  if (-1 == bytesRec) {
    I_32 err = errno;
    HYSOCKDEBUG ("<recv failed, err=%d>\n", err);
    return portLibrary->error_set_last_error (portLibrary, err, findError(err));
  } else {
    return bytesRec;
  }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_readfrom

/**
 * The read function receives data from a possibly connected socket.  Calling read will return as much 
 * information as is currently available up to the size of the buffer supplied.  If the information is too large
 * for the buffer, the excess will be discarded.  If no incoming  data is available at the socket, the read call 
 * blocks and waits for data to arrive.  It the address argument is not null, the address will be updated with
 * address of the message sender.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Pointer to the socket to read on.
 * @param[out] buf Pointer to the buffer where input bytes are written.
 * @param[in] nbyte The length of buf.
 * @param[in] flags Tthe flags, to influence this read.
 * @param[out] addrHandle	if provided, the address to be updated with the sender information.
 *
 * @return
 * \arg If no error occurs, return the number of bytes received.
 * \arg If the connection has been gracefully closed, return 0.
 * \arg Otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_readfrom (struct HyPortLibrary * portLibrary, hysocket_t sock,
                 U_8 * buf, I_32 nbyte, I_32 flags, hysockaddr_t addrHandle)
{
  I_32 bytesRec = 0;
  socklen_t addrlen;

  if (NULL == addrHandle)
    {
      addrlen = sizeof (*addrHandle); /* TOFIX: This is not used? */
      bytesRec =
        recvfrom (SOCKET_CAST (sock), buf, nbyte, flags, NULL, &addrlen);
    }
  else
    {
      addrlen = sizeof (addrHandle->addr);
      bytesRec =
        recvfrom (SOCKET_CAST (sock), buf, nbyte, flags,
                  (struct sockaddr *) &addrHandle->addr, &addrlen);
      /* TOFIX: should check if addrlen > sizeof(addrlen) ? */
    }
  if (bytesRec == -1)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<recvfrom failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  else
    {
      return bytesRec;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_select

/**
 * The select function allows the state of sockets for read & write operations and exceptional conditions to be tested.
 * The function is used prior to a hysock_read/readfrom, to control the period the operation may block for.
 * Depending upon the timeout specified:
 * \arg 0, return immediately with the status of the descriptors
 * \arg timeout, return when one of the descriptors is ready or after the timeout period has expired
 * \arg null, block indefinitely for a ready descriptor
 *
 * @param[in] portLibrary The port library.
 * @param[in] nfds Maximum number of file descriptors to be tested.
 * @param[in] readfds Tthe set of descriptors to be checked if ready for read operations.
 * @param[in] writefds The set of descriptors to be checked if ready for write operations.
 * @param[in] exceptfds The set of descriptors to be checked for exceptional conditions.
 * @param[in] timeout Pointer to the timeout (a hytimeval struct).
 *
 * @return	0 if timeout, number of ready FDs, or  otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_select (struct HyPortLibrary * portLibrary, I_32 nfds,
               hyfdset_t readfds, hyfdset_t writefds, hyfdset_t exceptfds,
               hytimeval_t timeout)
{
    I_32 rc = 0;
    I_32 result = 0;

    if (nfds >= FD_SETSIZE) {
        rc = portLibrary->error_set_last_error(portLibrary, errno, 
                                               HYPORT_ERROR_SOCKET_UNIX_EINVAL);
        return -1;
    }
    result = select (nfds, 
                        readfds == NULL ? NULL : &readfds->handle,
                        writefds == NULL ? NULL : &writefds->handle,
                        exceptfds == NULL ? NULL : &exceptfds->handle,
                        timeout == NULL ? NULL : &timeout->time);

    if (result == -1) {
        HYSOCKDEBUG ("<select failed, err=%d>\n", errno);

        if (errno == EINTR) {
            rc = portLibrary->error_set_last_error(portLibrary, errno, 
                                            HYPORT_ERROR_SOCKET_INTERRUPTED);
        }
        else {
            rc = portLibrary->error_set_last_error (portLibrary, errno,
                                            HYPORT_ERROR_SOCKET_OPFAILED);
        }
    }
    else {
        if (result) {
            rc = result;
        } 
        else {
            rc = HYPORT_ERROR_SOCKET_TIMEOUT;
        }
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_select_read

/**
 * A helper method, to ensure a read operation can be performed without blocking.
 * The portable version of a read operation is a blocking call (will wait indefinitely for data).
 * This function should be called prior to a read operation, to provide a read timeout.
 * If the result is 1, the caller is guaranteed to be able to complete a read on the socket without blocking.
 * The timeout is specified in seconds and microseconds.
 * If the timeout is 0, skip this function (and thus the caller of a subsequent read operation may block).
 *
 * @param[in] portLibrary The port library.
 * @param[in] hysocketP Pointer to the hysocket to query for available read data.
 * @param[in] secTime The integer component of the timeout periond, in seconds.
 * @param[in] uSecTime The fractional component of the timeout period, in microSeconds.
 * @param[in] accept Set to true when called for an accept(), false when called for a read()
 *
 * @return
 * \arg 1, if there is data available to read at the socket
 * \arg HYPORT_ERROR_SOCKET_TIMEOUT if the call timed out
 * \arg otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_select_read (struct HyPortLibrary * portLibrary, hysocket_t hysocketP,
                    I_32 secTime, I_32 uSecTime, BOOLEAN accept)
{
  // Current implementation uses poll() system routine since select()
  // has issues if fd_num is greater than FD_SETSIZE. See HARMONY-4077.

  int poll_timeout;
  I_32 result = 0;
  I_32 rc = 0;
  struct pollfd my_pollfd;

  my_pollfd.fd = SOCKET_CAST(hysocketP);
  my_pollfd.events = POLLIN | POLLPRI;
  my_pollfd.revents = 0;
  poll_timeout = TO_MILLIS(secTime, uSecTime);

  result = poll(&my_pollfd, 1, poll_timeout);

  if (result == -1) {
      HYSOCKDEBUG ("<poll failed, err=%d>\n", errno);

      if (errno == EINTR) {
          rc = portLibrary->error_set_last_error(portLibrary, errno, 
                                                 HYPORT_ERROR_SOCKET_INTERRUPTED);
      } else {
          rc = portLibrary->error_set_last_error(portLibrary, errno,
                                                 HYPORT_ERROR_SOCKET_OPFAILED);
      }
  } else {
      if (result || poll_timeout == 0) {
      	  rc = result;
      } else {
          rc = HYPORT_ERROR_SOCKET_TIMEOUT;
      }
  }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_set_nonblocking

/**
 * Set the nonblocking state of the socket.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to read on
 * @param[in] nonblocking Set true for nonblocking, false for blocking
 *
 * @return	0 if no error occurs, otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_set_nonblocking (struct HyPortLibrary * portLibrary,
                        hysocket_t socketP, BOOLEAN nonblocking)
{

  I_32 rc;
  U_32 param = nonblocking;

  /* set the socket to non blocking or block as requested */
  rc = ioctl (SOCKET_CAST (socketP), FIONBIO, &param);

  if (rc < 0)
    {
      rc = errno;
      switch (rc)
        {
        case HYPORT_ERROR_SOCKET_UNIX_EINVAL:
          return HYPORT_ERROR_SOCKET_OPTARGSINVALID;
        default:
          return portLibrary->error_set_last_error (portLibrary, rc,
                                                    findError (rc));
        }
    }

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setflag

/**
 * Ensure the flag designated is set in the argument.  This is used to construct arguments for the 
 * hysock_read/readfrom/write/writeto calls with optional flags, such as HYSOCK_MSG_PEEK.
 *
 * @param[in] portLibrary The port library.
 * @param[in] flag The operation flag to set in the argument.
 * @param[in] arg Pointer to the argument to set the flag bit in.
 *
 * @return	0 if no error occurs, otherwise return the (negative) error code.
 */
I_32 VMCALL
hysock_setflag (struct HyPortLibrary * portLibrary, I_32 flag, I_32 * arg)
{
  I_32 rc = 0;

  if (flag == HYSOCK_MSG_PEEK)
    {
      *arg |= MSG_PEEK;
    }
  else if (flag == HYSOCK_MSG_OOB)
    {
      *arg |= MSG_OOB;
    }
  else
    {
      rc = HYPORT_ERROR_SOCKET_UNKNOWNFLAG;
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_bool

/**
 * Set the value of the nominated boolean socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the boolean to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_bool (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                    I_32 optlevel, I_32 optname, BOOLEAN * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);
  U_8 uCharOptval = *optval;

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (OS_MCAST_LOOP == platformOption)
    {
      /* most options are set using an 32 bit int which matches the definition of BOOLEAN.  Howerver, for unix
         platforms this option is set with a unsighed char.  Some platforms accept both but some such as AIX
         and false return an EINVAL if we try to set with an int instead of a unsigned char.  For windows platforms
         the spec indicates that it is set with a DWORD which seems to match the BOOLEAN.  Therefore since this
         is a platform specific case for a boolean option we handle it as a special case within this method */
      if (0 !=
          setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      &uCharOptval, sizeof (uCharOptval)))
        {
          I_32 err = errno;
          HYSOCKDEBUG ("<setsockopt (for bool) failed, err=%d>\n", err);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
    }
  else
    {
      if (0 !=
          setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) optval, optlen))
        {
          I_32 err = errno;
          HYSOCKDEBUG ("<setsockopt (for bool) failed, err=%d>\n", err);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
    }

#if defined(IPV6_FUNCTION_SUPPORT)
  /* there separate socket options for IPv4/IPv6 for ttl, the IPv6 one also needs to be set */
  if (platformOption == IP_MULTICAST_LOOP)
    {
      platformLevel = IPPROTO_IPV6;
      platformOption = IPV6_MULTICAST_LOOP;
      if (0 !=
          setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) optval, optlen))
        {
          HYSOCKDEBUG ("<setsockopt (for bool) failed, err=%d>\n");
          return portLibrary->error_set_last_error (portLibrary, errno,
                                                    findError (errno));
        }
    }
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_byte

/**
 * Set the value of the nominated byte socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the byte to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_byte (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                    I_32 optlevel, I_32 optname, U_8 * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) optval, optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<setsockopt (for byte) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }

#if defined(IPV6_FUNCTION_SUPPORT)
  /* there separate socket options for IPv4/IPv6 for ttl, the IPv6 one also needs to be set */
  if (platformOption == IP_MULTICAST_TTL)
    {
      platformLevel = IPPROTO_IPV6;
      platformOption = IPV6_MULTICAST_HOPS;
      if (0 !=
          setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) optval, optlen))
        {
          HYSOCKDEBUG ("<setsockopt (for byte) failed, err=%d>\n");
          return portLibrary->error_set_last_error (portLibrary, errno,
                                                    findError (errno));
        }
    }
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_int

/**
 * Set the value of the nominated integer socket option.
 * Refer to the private platformSocketLevel & platformSocketOption functions for details of the options
 * supported.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the integer to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_int (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                   I_32 optlevel, I_32 optname, I_32 * optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (*optval);
  I_32 optvalueUsed = *optval;

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  /* for LINUX in order to enable support sending the ipv6_flowinfo field we have to set the SEND_FLOWINFO option on the socket
     In java one value is set that is used for both the traffic class in the flowinfo field and the IP_TOS option.  Therefore  if the caller is setting traffic class 
     then this indicates that we should also be setting the flowinfo field so we need to 
     set this option.  Howerver it can only be set on IPv6 sockets */
#if defined(IPv6_FUNCTION_SUPPORT)
#if defined(LINUX)
  if ((OS_IPPROTO_IP == platformLevel) && (OS_IP_TOS == platformOption)
      && (socketP->family == HYADDR_FAMILY_AFINET6))
    {
      U_32 on = 1;
      U_32 result = 0;
      I_32 err = 0;
      result =
        setsockopt (SOCKET_CAST (socketP), SOL_IPV6, IPV6_FLOWINFO_SEND,
                    (void *) &on, sizeof (on));
      if (result != 0)
        {
          err = errno;
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
    }
#endif
#endif

  /* for some platforms if the lower bit of IP_TOS is set we get an error.  While this is correct because the
     value is used for several purposes in java the bit may not be 0 and we don't want th error.  So just
     mask the value so that we don't get the error */
  if ((OS_IPPROTO_IP == platformLevel) && (OS_IP_TOS == platformOption))
    {
      optvalueUsed = optvalueUsed & 0xFE;
    }

  /* If this option is IP_TOS then don't call it on an IPv6 socket as it is not supported */
#if defined(IPv6_FUNCTION_SUPPORT)
  if (!
      ((OS_IPPROTO_IP == platformLevel) && (OS_IP_TOS == platformOption)
       && (socketP->family == HYADDR_FAMILY_AFINET6)))
    {
#endif

      if (0 !=
          setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                      (void *) &optvalueUsed, optlen))
        {
          I_32 err = errno;
          HYSOCKDEBUG ("<setsockopt (for int) failed, err=%d>\n", err);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    findError (err));
        }
#if defined(IPv6_FUNCTION_SUPPORT)
    }
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_ipmreq

/**
 * Set the multicast request on this socket. 
 * Currently this is used to join or leave the nominated multicast group on the local interface.
 * 	It may be more generally useful, so a generic 'setop' function has been defined.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the ipmreq struct to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_ipmreq (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                      I_32 optlevel, I_32 optname, hyipmreq_t optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (optval->addrpair);
  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }
  if (0 !=
      setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) (&optval->addrpair), optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<setsockopt (for ipmreq) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_ipv6_mreq

/**
 * Set the multicast request on this socket for IPv6 sockets. 
 * Currently this is used to join or leave the nominated multicast group on the local interface.
 * 	It may be more generally useful, so a generic 'setop' function has been defined.t.
 *
 * Supported families are OS_AF_INET and OS_AF_INET6 
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the ipmreq struct to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_setopt_ipv6_mreq (struct HyPortLibrary * portLibrary,
                         hysocket_t socketP, I_32 optlevel, I_32 optname,
                         hyipv6_mreq_t optval)
{
  I_32 rc = 0;
#if defined(IPv6_FUNCTION_SUPPORT)
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  I_32 optlen = sizeof (optval->mreq);
  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }
  rc =
    setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                (char *) (&optval->mreq), optlen);
  if (rc != 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<setsockopt (for ipmreq) failed, err=%d>\n", rc);
      switch (rc)
        {
        case HYPORT_ERROR_SOCKET_UNIX_EINTR:
          return HYPORT_ERROR_SOCKET_OPTARGSINVALID;
        default:
          return portLibrary->error_set_last_error (portLibrary, rc,
                                                    findError (rc));
        }
    }
#endif

  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_linger

/**
 * Set the linger value on the socket. 
 * See the @ref hysock_linger_init for details of the linger behavior.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the linger struct to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_linger (struct HyPortLibrary * portLibrary, hysocket_t socketP,
                      I_32 optlevel, I_32 optname, hylinger_t optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen = sizeof (optval->linger);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) (&optval->linger), optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<setsockopt (for linger) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_setopt_sockaddr

/**
 * Set the sockaddr for the socket.
 * Currently used to set the interface of multicast sockets, but the more general call style is used,
 * in case it is more generally useful.
 *
 * @param[in] portLibrary The port library.
 * @param[in] socketP Pointer to the socket to set the option in.
 * @param[in] optlevel The level within the IP stack at which the option is defined.
 * @param[in] optname The name of the option to set.
 * @param[out] optval Pointer to the hysockaddr struct to update the socket option with.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_setopt_sockaddr (struct HyPortLibrary * portLibrary,
                        hysocket_t socketP, I_32 optlevel, I_32 optname,
                        hysockaddr_t optval)
{
  I_32 platformLevel = platformSocketLevel (optlevel);
  I_32 platformOption = platformSocketOption (optname);
  socklen_t optlen;
  OSSOCKADDR *sockaddr = (OSSOCKADDR *) & optval->addr;

  optlen = sizeof (sockaddr->sin_addr);

  if (0 > platformLevel)
    {
      return platformLevel;
    }
  if (0 > platformOption)
    {
      return platformOption;
    }

  if (0 !=
      setsockopt (SOCKET_CAST (socketP), platformLevel, platformOption,
                  (void *) (&sockaddr->sin_addr), optlen))
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<setsockopt (for sockaddr) failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_shutdown

/**
 * Terminates use of the socket library.  No sockets should be in use or the results
 * of this operation are unpredictable.  Frees any resources held by the socket library.
 *
 * @param[in] portLibrary The port library.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code
 */
I_32 VMCALL
hysock_shutdown (struct HyPortLibrary * portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_shutdown_input

/**
 * The shutdown_input function disables the input stream on a socket. Any subsequent reads from the socket
 * will fail.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Socket to close input stream on.
 *
 * @return
 * \arg  0, on success
 * \arg HYPORT_ERROR_SOCKET_OPFAILED, on generic error
 * \arg HYPORT_ERROR_SOCKET_NOTINITIALIZED, if the library is not initialized
*/
I_32 VMCALL
hysock_shutdown_input (struct HyPortLibrary * portLibrary, hysocket_t sock)
{
  I_32 rc = 0;

  if (shutdown (SOCKET_CAST (sock), 0) < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<closesocket failed, err=%d>\n", rc);
      return portLibrary->error_set_last_error (portLibrary, rc,
                                                findError (rc));
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_shutdown_output

/**
 * The shutdown_output function disables the output stream on a socket. Any subsequent writes to the socket
 * will fail.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Socket to close output stream on.
 *
 * @return
 * \arg  0, on success
 * \arg HYPORT_ERROR_SOCKET_OPFAILED, on generic error
 * \arg HYPORT_ERROR_SOCKET_NOTINITIALIZED, if the library is not initialized
 */
I_32 VMCALL
hysock_shutdown_output (struct HyPortLibrary * portLibrary, hysocket_t sock)
{
  I_32 rc = 0;

  if (shutdown (SOCKET_CAST (sock), 1) < 0)
    {
      rc = errno;
      HYSOCKDEBUG ("<closesocket failed, err=%d>\n", rc);
      return portLibrary->error_set_last_error (portLibrary, rc,
                                                findError (rc));
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr

/**
 * Creates a new hysockaddr, refering to the specified port and address.  The only address family currently supported
 * is AF_INET.
 *
 * @param[in] portLibrary The port library.
 * @param[out] handle Pointer to the hysockaddr struct, to be allocated.
 * @param[in] addrStr The target host, as either a name or dotted ip string.
 * @param[in] port The target port, in host order.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_sockaddr (struct HyPortLibrary * portLibrary, hysockaddr_t handle,
                 const char *addrStr, U_16 port)
{
  I_32 rc = 0;
  U_32 addr = 0;
  hyhostent_struct host_t;

  if (0 != portLibrary->sock_inetaddr (portLibrary, addrStr, &addr))
    {
      memset (&host_t, 0, sizeof (struct hyhostent_struct));
      if (0 !=
          (rc =
           portLibrary->sock_gethostbyname (portLibrary, addrStr, &host_t)))
        {
          return rc;
        }
      else
        {
          addr = portLibrary->sock_hostent_addrlist (portLibrary, &host_t, 0);
        }
    }
  rc =
    portLibrary->sock_sockaddr_init (portLibrary, handle, HYSOCK_AFINET, addr,
                                     port);
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_address

/**
 * Answer the address, in network order, of the hysockaddr argument.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hysockaddr struct to access.
 *
 * @return	the address (there is no validation on the access).
 */
I_32 VMCALL
hysock_sockaddr_address (struct HyPortLibrary * portLibrary,
                         hysockaddr_t handle)
{
  return ((OSSOCKADDR *) & handle->addr)->sin_addr.s_addr;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_address6

/**
 * Answers the IP address of a structure and its length, in a preallocated buffer.
 *
 * Preallocated buffer "address" should be 16 bytes.  "length" tells you how many bytes were used 4 or 16.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle A populated hysockaddr_t.
 * @param[out] address The IPv4 or IPv6 address in network byte order.
 * @param[out] length The number of bytes of the address (4 or 16).
 * @param[out] scope_id the scope id for the address if appropriate
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_sockaddr_address6 (struct HyPortLibrary * portLibrary,
                          hysockaddr_t handle, U_8 * address, U_32 * length,
                          U_32 * scope_id)
{
  OSSOCKADDR *ipv4;
#if defined(IPv6_FUNCTION_SUPPORT)
  OSSOCKADDR_IN6 *ipv6;
#endif

  ipv4 = (OSSOCKADDR *) & handle->addr;
  if (ipv4->sin_family == OS_AF_INET4)
    {
      memcpy (address, &ipv4->sin_addr, 4);
      *length = 4;
    }
#if defined(IPv6_FUNCTION_SUPPORT)
  else
    {
      ipv6 = (OSSOCKADDR_IN6 *) & handle->addr;
      memcpy (address, &ipv6->sin6_addr, 16);
      *length = 16;
      *scope_id = ipv6->sin6_scope_id;
    }
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_family

/**
 * Answers the family name of a hysockaddr_struct.
 *
 * Supported families are OS_AF_INET and OS_AF_INET6 
 *
 * @param[in] portLibrary The port library.
 * @param[out] family The family name of the address.
 * @param[in] handle A populated hysockaddr_t.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_sockaddr_family (struct HyPortLibrary * portLibrary, I_16 * family,
                        hysockaddr_t handle)
{
  OSSOCKADDR *ipv4;

  ipv4 = (OSSOCKADDR *) & handle->addr;
  if (ipv4->sin_family == OS_AF_INET4)
    {
      *family = HYADDR_FAMILY_AFINET4;
    }
  else
    {
      *family = HYADDR_FAMILY_AFINET6;
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_init

/**
 * Creates a new hysockaddr, refering to the specified port and address.  The only address family currently supported
 * is AF_INET.
 *
 * @param[in] portLibrary The port library.
 * @param[out] handle Pointer pointer to the hysockaddr struct, to be allocated.
 * @param[in] family The address family.
 * @param[in] nipAddr The target host address, in network order.
 * @param[in] nPort The target port, in host order.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_sockaddr_init (struct HyPortLibrary * portLibrary, hysockaddr_t handle,
                      I_16 family, U_32 nipAddr, U_16 nPort)
{
  OSSOCKADDR *sockaddr = (OSSOCKADDR *) & handle->addr;
  memset (handle, 0, sizeof (struct hysockaddr_struct));
  sockaddr->sin_family = family;
  sockaddr->sin_addr.s_addr = nipAddr;
  sockaddr->sin_port = nPort;
#if defined(FREEBSD)
  sockaddr->sin_len = sizeof(OSSOCKADDR);
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_init6

/**
 * Answers an initialized hysockaddr_struct structure.
 *
 * Pass in a hysockaddr_struct with some initial parameters to initialize it appropriately.
 * Currently the only address families supported are OS_AF_INET6 and OS_AF_INET, which
 * will be determined by addrlength.  (4 bytes for IPv4 addresses and 16 bytes for IPv6 addresses).
 *
 * @param[in] portLibrary The port library.
 * @param[out] handle Pointer pointer to the hysockaddr struct, to be allocated.
 * @param[in] addr The IPv4 or IPv6 address in network byte order.
 * @param[in] addrlength The number of bytes of the address (4 or 16).
 * @param[in] family The address family.
 * @param[in] nPort The target port, in network order.
 * @param[in] flowinfo The flowinfo value for IPv6 addresses in HOST order.  Set to 0 for IPv4 addresses or if no flowinfo needs to be set for IPv6 address
 * @param[in] scope_id The scope id for an IPv6 address in HOST order.  Set to 0 for IPv4 addresses and for non-scoped IPv6 addresses
 * @param[in] sock The socket that this address will be used with.  
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 *
 * @note Added for IPv6 support.
 */
I_32 VMCALL
hysock_sockaddr_init6 (struct HyPortLibrary * portLibrary,
                       hysockaddr_t handle, U_8 * addr, I_32 addrlength,
                       I_16 family, U_16 nPort, U_32 flowinfo, U_32 scope_id,
                       hysocket_t sock)
{
  OSSOCKADDR *sockaddr;
#if defined(IPv6_FUNCTION_SUPPORT)
  OSSOCKADDR_IN6 *sockaddr_6;
#endif

  memset (handle, 0, sizeof (struct hysockaddr_struct));

  if (family == HYADDR_FAMILY_AFINET4)
    {
#if defined(IPv6_FUNCTION_SUPPORT)
      if (hysock_socketIsValid (portLibrary, sock) &&
          (((struct hysocket_struct *) sock)->family ==
           HYADDR_FAMILY_AFINET6))
        {
          /* to talk IPv4 on an IPv6 socket we need to map the IPv4 address to an IPv6 format.  If mapAddress is true then we do this */
          sockaddr_6 = (OSSOCKADDR_IN6 *) & handle->addr;
          memset (sockaddr_6->sin6_addr.s6_addr, 0, 16);
          memcpy (&(sockaddr_6->sin6_addr.s6_addr[12]), addr, addrlength);
          /* do a check if it is the any address.  we know the top 4 bytes of sockaddr_6->sin6_addr.s6_addr are 0's as we just cleared the,
             so we use them to do the check */
          if (memcmp (sockaddr_6->sin6_addr.s6_addr, addr, addrlength) != 0)
            {
              /* if it is the any address then use the IPv6 any address */
              sockaddr_6->sin6_addr.s6_addr[10] = 0xFF;
              sockaddr_6->sin6_addr.s6_addr[11] = 0xFF;
            }
          sockaddr_6->sin6_port = nPort;
          sockaddr_6->sin6_family = OS_AF_INET6;
          sockaddr_6->sin6_scope_id = scope_id;
          sockaddr_6->sin6_flowinfo = htonl (flowinfo);
#if defined(FREEBSD)
          sockaddr_6->sin6_len = sizeof(OSSOCKADDR_IN6);
#endif
        }
      else
        {
#endif

          /* just initialize the IPv4 address as is as it will be used with an IPv4 Socket */
          sockaddr = (OSSOCKADDR *) & handle->addr;
          memcpy (&sockaddr->sin_addr.s_addr, addr, addrlength);
          sockaddr->sin_port = nPort;
          sockaddr->sin_family = OS_AF_INET4;
#if defined(FREEBSD)
          sockaddr->sin_len = sizeof(OSSOCKADDR);
#endif

#if defined(IPv6_FUNCTION_SUPPORT)
        }
#endif

    }
#if defined(IPv6_FUNCTION_SUPPORT)
  else if (family == HYADDR_FAMILY_AFINET6)
    {
      sockaddr_6 = (OSSOCKADDR_IN6 *) & handle->addr;
      memcpy (&sockaddr_6->sin6_addr.s6_addr, addr, addrlength);
      sockaddr_6->sin6_port = nPort;
      sockaddr_6->sin6_family = OS_AF_INET6;
      sockaddr_6->sin6_scope_id = scope_id;
      sockaddr_6->sin6_flowinfo = htonl (flowinfo);
#if defined(SIN6_LEN)
      sockaddr_6->sin6_len = sizeof(OSSOCKADDR_IN6);
#endif
    }
#endif

  else
    {
      sockaddr = (OSSOCKADDR *) & handle->addr;
      memcpy (&sockaddr->sin_addr.s_addr, addr, HYSOCK_INADDR_LEN);
      sockaddr->sin_port = nPort;
      sockaddr->sin_family = map_addr_family_Hy_to_OS (family);
#if defined(FREEBSD)
      sockaddr->sin_len = sizeof(OSSOCKADDR);
#endif
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_sockaddr_port

/**
 * Answer the port, in network order, of the hysockaddr argument.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hysockaddr struct to access.
 *
 * @return	the port (there is no validation on the access).
 */
U_16 VMCALL
hysock_sockaddr_port (struct HyPortLibrary * portLibrary, hysockaddr_t handle)
{
  if (((OSSOCKADDR *) & handle->addr)->sin_family == OS_AF_INET4)
    {
      return ((OSSOCKADDR *) & handle->addr)->sin_port;
    }
#if defined(IPv6_FUNCTION_SUPPORT)
  else
    {
      return ((OSSOCKADDR_IN6 *) & handle->addr)->sin6_port;
    }
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_socket

/**
 * Creates a new socket descriptor and any related resources.
 *
 * @param[in] portLibrary The port library.
 * @param[out]	handle Pointer pointer to the hysocket struct, to be allocated
 * @param[in] family The address family (currently, only HYSOCK_AFINET is supported)
 * @param[in] socktype Secifies what type of socket is created
 * \arg HYSOCK_STREAM, for a stream socket
 * \arg HYSOCK_DGRAM, for a datagram socket
 * @param[in] protocol Type/family specific creation parameter (currently, only HYSOCK_DEFPROTOCOL supported).
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_socket (struct HyPortLibrary * portLibrary, hysocket_t * handle,
               I_32 family, I_32 socktype, I_32 protocol)
{
  I_32 rc = 0;
  int sock;

  /* initialize return hysocket_t to invalid socket */
  *handle = INVALID_SOCKET;

  if (protocol != HYSOCK_DEFPROTOCOL)
    {
      rc = HYPORT_ERROR_SOCKET_BADPROTO;
    }
  else if ((socktype != HYSOCK_STREAM) && (socktype != HYSOCK_DGRAM))
    {
      rc = HYPORT_ERROR_SOCKET_BADTYPE;
    }
  else if (family != HYADDR_FAMILY_AFINET6 && family != HYADDR_FAMILY_AFINET4
           && family != HYADDR_FAMILY_UNSPEC)
    {
      rc = HYPORT_ERROR_SOCKET_BADAF;
    }
  if (rc == 0)
    {

#if defined(IPv6_FUNCTION_SUPPORT)
      if (family != HYADDR_FAMILY_AFINET4)
        {
          family = HYADDR_FAMILY_AFINET6;
          sock =
            socket (AF_INET6,
                    ((socktype == HYSOCK_STREAM) ? SOCK_STREAM : SOCK_DGRAM),
                    0);
          if (sock < 0)
            {
              rc = errno;
              HYSOCKDEBUG ("<socket failed, err=%d>\n", rc);
              return portLibrary->error_set_last_error (portLibrary, rc,
                                                        findError (rc));
            }
        }
      else
        {
#endif

          sock =
            socket (AF_INET,
                    ((socktype == HYSOCK_STREAM) ? SOCK_STREAM : SOCK_DGRAM),
                    0);
          if (sock < 0)
            {
              rc = errno;
              HYSOCKDEBUG ("<socket failed, err=%d>\n", rc);
              return portLibrary->error_set_last_error (portLibrary, rc,
                                                        findError (rc));
            }
#if defined(IPv6_FUNCTION_SUPPORT)
        }
#endif

    }
  if (rc == 0)
    {
      /*Tag this descriptor as being non-inheritable */
      I_32 fdflags = fcntl (sock, F_GETFD, 0);
      fcntl (sock, F_SETFD, fdflags | FD_CLOEXEC);

      /* set up the socket structure */
      *handle =
        portLibrary->mem_allocate_memory (portLibrary,
                                          sizeof (struct hysocket_struct));
#if (defined(VALIDATE_ALLOCATIONS))
      if (*handle == NULL)
        {
          close (sock);
          *handle = INVALID_SOCKET;
          return HYPORT_ERROR_SOCKET_NOBUFFERS;
        }
#endif

      SOCKET_CAST (*handle) = sock;
      (*handle)->family = family;
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_socketIsValid

/**
 * Determines whether or not the socket is valid.
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to the hysocket struct, to be allocated.
 *
 * @return	0 if invalid, non-zero for valid.
 */
I_32 VMCALL
hysock_socketIsValid (struct HyPortLibrary * portLibrary, hysocket_t handle)
{
  if ((handle != NULL) && (handle != INVALID_SOCKET))
    {
      return TRUE;
    }
  else
    {
      return FALSE;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_startup

/**
 * Initiate the use of sockets by a process.  This function must be called before any other socket calls.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_SOCK
 * \arg HYPORT_ERROR_SOCKET_OPFAILED
 * \arg HYPORT_ERROR_SOCKET_NOTINITIALIZED
 */
I_32 VMCALL
hysock_startup (struct HyPortLibrary * portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_timeval_init

/**
 * Create a time structure, representing the timeout period defined in seconds & microSeconds.
 * Timeval's are used as timeout arguments in the @ref hysock_select function.
 *
 * @param[in] portLibrary The port library.
 * @param[in] secTime The integer component of the timeout value (in seconds).
 * @param[in] uSecTime The fractional component of the timeout value (in microseconds).
 * @param[out] timeP Pointer pointer to the hytimeval_struct to be allocated.
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_timeval_init (struct HyPortLibrary * portLibrary, U_32 secTime,
                     U_32 uSecTime, hytimeval_t timeP)
{
  memset (timeP, 0, sizeof (struct hytimeval_struct));
  timeP->time.tv_sec = secTime;
  timeP->time.tv_usec = uSecTime;

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_write

/**
 * The write function writes data to a connected socket.  The successful completion of a write 
 * does not indicate that the data was successfully delivered.  If no buffer space is available 
 * within the transport system to hold the data to be transmitted, send will block.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Pointer to the socket to send on
 * @param[in] buf The bytes to be sent
 * @param[in] nbyte The number of bytes to send
 * @param[in] flags The flags to modify the send behavior
 *
 * @return	If no error occur, return the total number of bytes sent, which can be less than the 
 * 'nbyte' for nonblocking sockets, otherwise the (negative) error code
 */
I_32 VMCALL
hysock_write (struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
              I_32 nbyte, I_32 flags)
{
  I_32 bytesSent = 0;

  bytesSent = send (SOCKET_CAST (sock), buf, nbyte, flags);

  if (-1 == bytesSent)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<send failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  else
    {
      return bytesSent;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_writeto

/**
 * The writeto function writes data to a datagram socket.  The successful completion of a writeto
 * does not indicate that the data was successfully delivered.  If no buffer space is available 
 * within the transport system to hold the data to be transmitted, writeto will block.
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock Pointer to the socket to send on
 * @param[in] buf The bytes to be sent
 * @param[in] nbyte The number of bytes to send
 * @param[in] flags The flags to modify the send behavior
 * @param [in] addrHandle The network address to send the datagram to
 *
 * @return	If no error occur, return the total number of bytes sent, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_writeto (struct HyPortLibrary * portLibrary, hysocket_t sock,
                U_8 * buf, I_32 nbyte, I_32 flags, hysockaddr_t addrHandle)
{
  I_32 bytesSent = 0;

  bytesSent =
    sendto (SOCKET_CAST (sock), buf, nbyte, flags,
            (struct sockaddr *) &(addrHandle->addr),
            getAddrLength(addrHandle));

  if (bytesSent == -1)
    {
      I_32 err = errno;
      HYSOCKDEBUG ("<sendto failed, err=%d>\n", err);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                findError (err));
    }
  else
    {
      return bytesSent;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_cleanupNetlinkContext

#if (defined(HAS_RTNETLINK))
/**
 *   Convenience function to release any allocated memory for a netlinkContext
 * 
 *   @param[in] portLib  The port library
 *   @param[in] nlc NetlinkContext that needs to have memory freed
 * 
 *   @return
 */
void hysock_cleanupNetlinkContext(HyPortLibrary *portLib, struct netlinkContext_struct *nlc) 
{
    if (nlc && portLib && nlc->buffer) {
        portLib->mem_free_memory(portLib, nlc->buffer);
        nlc->buffer = NULL;
        nlc->bufferSize = 0;
    }
    
    return;
}
#endif
#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_get_network_interfaces

/**
 * Queries and returns the information for the network interfaces that are currently active within the system. 
 * Applications are responsible for freeing the memory returned via the handle.
 *
 * @param[in] portLibrary The port library.
 * @param[in,out] array Pointer to structure with array of network interface entries
 * @param[in] boolean which indicates if we should prefer the IPv4 stack or not
 *
 * @return The number of elements in handle on success, negatvie portable error code on failure. 
				-WSANO_RECOVERY if system calls required to get the info fail, -WSAENOBUFS if memory allocation fails
 * @note A return value of 0 indicates no interfaces exist
*/
I_32 VMCALL
hysock_get_network_interfaces (struct HyPortLibrary * portLibrary,
                               struct hyNetworkInterfaceArray_struct * array,
                               BOOLEAN preferIPv4Stack)
{
  struct hyNetworkInterface_struct *interfaces = NULL;
  U_32 nameLength = 0;
  U_32 currentAdapterIndex = 0;
  U_32 counter = 0;
  U_32 result = 0;
  U_32 numAddresses = 0;
  U_32 currentIPAddressIndex = 0;
  I_32 numAdapters = 0;
  I_32 err = 0;

#if (defined(HAS_RTNETLINK))
  U_32 sendLength = 0;
  struct linkReq_struct linkRequest;
  struct addrReq_struct addrRequest;
  int netlinkSocketHandle = 0;
  U_8 addressFamily = 0;
  struct nlmsghdr *currentNlHeader = NULL;
  struct ifinfomsg *returnedInfoHeader = NULL;
  struct ifaddrmsg *returnedAddrHeader = NULL;
  netlinkContext_struct netlinkContext;
  
  /*
   *  initialize the buffer as it doesn't have one yet
   */
  netlinkContext.buffer = portLibrary->mem_allocate_memory(portLibrary,NETLINK_DATA_BUFFER_SIZE);
  netlinkContext.bufferSize = NETLINK_DATA_BUFFER_SIZE;
    
  /* set the address family based on the preferIPv4stack flag */
  if (preferIPv4Stack)
    {
      addressFamily = AF_INET;
    }
  else
    {
      addressFamily = AF_UNSPEC;
    }

  /* we need socket to do the netlink calls so create one */
  
  netlinkSocketHandle = socket (PF_NETLINK, SOCK_DGRAM, NETLINK_ROUTE);

  if (netlinkSocketHandle <= 0)
    {
        hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
        
        return portLibrary->error_set_last_error (portLibrary, errno, 
                                                 HYPORT_ERROR_SOCKET_NORECOVERY);
    }
  /* now create the message to get the network interface information */
  memset (&linkRequest, 0, sizeof (struct linkReq_struct));
  linkRequest.netlinkHeader.nlmsg_len =
    NLMSG_LENGTH (sizeof (struct linkReq_struct));
  linkRequest.netlinkHeader.nlmsg_flags = NLM_F_REQUEST | NLM_F_ROOT;
  linkRequest.netlinkHeader.nlmsg_type = RTM_GETLINK;
  linkRequest.msg.ifi_family = addressFamily;
  linkRequest.netlinkHeader.nlmsg_len =
    NLMSG_ALIGN (linkRequest.netlinkHeader.nlmsg_len);
  
  sendLength = send (netlinkSocketHandle, &linkRequest,
          linkRequest.netlinkHeader.nlmsg_len, 0);

  /* send the request  and count the number of interfaces */
  if (sendLength != linkRequest.netlinkHeader.nlmsg_len)
    {
      err = errno;
      close (netlinkSocketHandle);
      hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
      
      return portLibrary->error_set_last_error (portLibrary, err,
                                                HYPORT_ERROR_SOCKET_NORECOVERY);
    }

  initNetlinkContext (portLibrary, netlinkSocketHandle, &netlinkContext);

  do
    {
      if ((result =
           getNextNetlinkMsg (portLibrary, &netlinkContext,
                              &currentNlHeader)) != 0)
        {
          /* we failed to get the next message so return the error */
          close (netlinkSocketHandle);
          hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
          
          return result;
        }
      if (currentNlHeader != NULL)
        {
          /* only return the interfaces that are up so check the IFF_UP flag) */
          returnedInfoHeader = NLMSG_DATA (currentNlHeader);
          if ((returnedInfoHeader->ifi_flags & IFF_UP) == IFF_UP)
            {
              numAdapters++;
            }
        }
    }
  while (currentNlHeader != NULL);
  
  /* now allocate the space for the hyNetworkInterface structs and fill it in */
  interfaces =
    portLibrary->mem_allocate_memory (portLibrary,
                                      numAdapters *
                                      sizeof (hyNetworkInterface_struct));
#if (defined(VALIDATE_ALLOCATIONS))
  if (NULL == interfaces)
    {
      close (netlinkSocketHandle);
      hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
      return HYPORT_ERROR_SOCKET_NOBUFFERS;
    }
  /* initialize the structure so that we can free allocated if a failure occurs */
  for (counter = 0; counter < numAdapters; counter++)
    {
      interfaces[counter].name = NULL;
      interfaces[counter].displayName = NULL;
      interfaces[counter].addresses = NULL;
    }
#endif
/* set up the return structure */
  array->elements = interfaces;
  array->length = numAdapters;
  /* now get the adapter information */
  currentAdapterIndex = 0;
  memset (&linkRequest, 0, sizeof (struct linkReq_struct));
  linkRequest.netlinkHeader.nlmsg_len =
    NLMSG_LENGTH (sizeof (struct linkReq_struct));
  linkRequest.netlinkHeader.nlmsg_flags = NLM_F_REQUEST | NLM_F_ROOT;
  linkRequest.netlinkHeader.nlmsg_type = RTM_GETLINK;
  linkRequest.msg.ifi_family = addressFamily;
  linkRequest.netlinkHeader.nlmsg_len =
    NLMSG_ALIGN (linkRequest.netlinkHeader.nlmsg_len);

  sendLength = send (netlinkSocketHandle, &linkRequest,
          linkRequest.netlinkHeader.nlmsg_len, 0);
          
  if (sendLength != linkRequest.netlinkHeader.nlmsg_len)
    {
      err = errno;
      close (netlinkSocketHandle);
      hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
      
      hysock_free_network_interface_struct (portLibrary, array);
      return portLibrary->error_set_last_error (portLibrary, err,
                                                HYPORT_ERROR_SOCKET_NORECOVERY);
    }
    
  initNetlinkContext (portLibrary, netlinkSocketHandle, &netlinkContext);
  currentAdapterIndex = 0;
  do
    {
      if ((result =
           getNextNetlinkMsg (portLibrary, &netlinkContext,
                              &currentNlHeader)) != 0)
        {
          // we failed to get the next message so return the error
          close (netlinkSocketHandle);
          hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
          return result;
        }
      if ((currentNlHeader != NULL) && (currentAdapterIndex < numAdapters))
        {
          struct rtattr *rta;
          U_32 attLength = IFLA_PAYLOAD (currentNlHeader);
          returnedInfoHeader = NLMSG_DATA (currentNlHeader);

          /* only return adapters that are up */
          if ((returnedInfoHeader->ifi_flags & IFF_UP) == IFF_UP)
            {
              interfaces[currentAdapterIndex].index =
                returnedInfoHeader->ifi_index;
              /* now set the name and display name */
              rta = IFLA_RTA (returnedInfoHeader);
              while (RTA_OK (rta, attLength))
                {
                  if (IFLA_IFNAME == rta->rta_type)
                    {
                      /* there only seems to be one name so use it for both the name and the display name */
                      nameLength = rta->rta_len;
                      interfaces[currentAdapterIndex].name =
                        portLibrary->mem_allocate_memory (portLibrary,
                                                          nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
                      if (NULL == interfaces[currentAdapterIndex].name)
                        {
                          hysock_free_network_interface_struct (portLibrary,
                                                                array);
                          close (netlinkSocketHandle);
                          hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
                          return HYPORT_ERROR_SOCKET_NOBUFFERS;
                        }
#endif
                      strncpy (interfaces[currentAdapterIndex].name,
                               RTA_DATA (rta), nameLength);
                      interfaces[currentAdapterIndex].name[nameLength] = 0;
                      nameLength = rta->rta_len;
                      interfaces[currentAdapterIndex].displayName =
                        portLibrary->mem_allocate_memory (portLibrary,
                                                          nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
                      if (NULL == interfaces[currentAdapterIndex].displayName)
                        {
                          hysock_free_network_interface_struct (portLibrary,
                                                                array);
                          close (netlinkSocketHandle);
                          hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
                          return HYPORT_ERROR_SOCKET_NOBUFFERS;
                        }
#endif
                      strncpy (interfaces[currentAdapterIndex].displayName,
                               RTA_DATA (rta), nameLength);
                      interfaces[currentAdapterIndex].
                        displayName[nameLength] = 0;
                    }
                  rta = RTA_NEXT (rta, attLength);
                }
              /* now get and populate the addresses for the network interface */
              interfaces[currentAdapterIndex].numberAddresses = 0;
              currentAdapterIndex++;
            }
        }
    }
  while (currentNlHeader != NULL);
  /* now get the addresses for each of the adaptors */
  for (counter = 0; counter < numAdapters; counter++)
    {
      numAddresses = 0;
      /* send message to get address info */
      memset (&addrRequest, 0, sizeof (struct addrReq_struct));
      addrRequest.netlinkHeader.nlmsg_len =
        NLMSG_LENGTH (sizeof (struct addrReq_struct));
      addrRequest.netlinkHeader.nlmsg_flags = NLM_F_REQUEST | NLM_F_MATCH;
      addrRequest.netlinkHeader.nlmsg_type = RTM_GETADDR;
      addrRequest.msg.ifa_index = interfaces[counter].index;
      addrRequest.msg.ifa_family = addressFamily;
      addrRequest.netlinkHeader.nlmsg_len =
        NLMSG_ALIGN (addrRequest.netlinkHeader.nlmsg_len);
      sendLength =
        send (netlinkSocketHandle, &addrRequest,
              addrRequest.netlinkHeader.nlmsg_len, 0);
      if (sendLength != addrRequest.netlinkHeader.nlmsg_len)
        {
          err = errno;
          close (netlinkSocketHandle);
          hysock_free_network_interface_struct (portLibrary, array);
          hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    HYPORT_ERROR_SOCKET_NORECOVERY);
        }
      /* now process each of the response messages to count the number of addresses */
      initNetlinkContext (portLibrary, netlinkSocketHandle, &netlinkContext);
      do
        {
          if ((result =
               getNextNetlinkMsg (portLibrary, &netlinkContext,
                                  &currentNlHeader)) != 0)
            {
              /* we failed to get the next message so return the error */
              close (netlinkSocketHandle);
              hysock_free_network_interface_struct (portLibrary, array);
              hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
              return result;
            }

          if (currentNlHeader != NULL)
            {
              struct rtattr *rta;
              U_32 attLength = IFA_PAYLOAD (currentNlHeader);
              returnedAddrHeader = NLMSG_DATA (currentNlHeader);
              if ((
#if defined(IPv6_FUNCTION_SUPPORT)
                    (returnedAddrHeader->ifa_family == AF_INET) ||
#endif
                    (returnedAddrHeader->ifa_family == AF_INET6)) &&
                  (returnedAddrHeader->ifa_index ==
                   interfaces[counter].index))
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
        }
      while (currentNlHeader != NULL);
      /* now that we have counted the number of addresses for this interface, allocate the memory required to return 
         them and set the number of addresses that we will return */
      interfaces[counter].numberAddresses = numAddresses;
      interfaces[counter].addresses = NULL;
      if (numAddresses > 0)
        {
          interfaces[counter].addresses =
            portLibrary->mem_allocate_memory (portLibrary,
                                              numAddresses *
                                              sizeof (hyipAddress_struct));
#if (defined(VALIDATE_ALLOCATIONS))
          if (NULL == interfaces[counter].addresses)
            {
              close (netlinkSocketHandle);
              hysock_free_network_interface_struct (portLibrary, array);
              hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
              return HYPORT_ERROR_SOCKET_NOBUFFERS;
            }
#endif
/*  now get the actual values and fill them into where we have allocated space for them */
          /* send message to get address info */
          currentIPAddressIndex = 0;
          memset (&addrRequest, 0, sizeof (struct addrReq_struct));
          addrRequest.netlinkHeader.nlmsg_len =
            NLMSG_LENGTH (sizeof (struct addrReq_struct));
          addrRequest.netlinkHeader.nlmsg_flags = NLM_F_REQUEST | NLM_F_MATCH;
          addrRequest.netlinkHeader.nlmsg_type = RTM_GETADDR;
          addrRequest.msg.ifa_index = interfaces[counter].index;
          addrRequest.msg.ifa_family = addressFamily;
          addrRequest.netlinkHeader.nlmsg_len =
            NLMSG_ALIGN (addrRequest.netlinkHeader.nlmsg_len);
          sendLength =
            send (netlinkSocketHandle, &addrRequest,
                  addrRequest.netlinkHeader.nlmsg_len, 0);
          if (sendLength != addrRequest.netlinkHeader.nlmsg_len)
            {
              err = errno;
              close (netlinkSocketHandle);
              hysock_free_network_interface_struct (portLibrary, array);
              hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
              return portLibrary->error_set_last_error (portLibrary, err,
                                                        HYPORT_ERROR_SOCKET_NORECOVERY);
            }
          /* read the reply message for each address for the interface and populate as appropriate */
          initNetlinkContext (portLibrary, netlinkSocketHandle,
                              &netlinkContext);
          do
            {
              if ((result =
                   getNextNetlinkMsg (portLibrary, &netlinkContext,
                                      &currentNlHeader)) != 0)
                {
                  /* we failed to get the next message so return the error */
                  close (netlinkSocketHandle);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);
                  return result;
                }

              if (currentNlHeader != NULL)
                {
                  struct rtattr *rta;
                  U_32 attLength = IFA_PAYLOAD (currentNlHeader);
                  returnedAddrHeader = NLMSG_DATA (currentNlHeader);
                  /* make sure that it is for the requested interface, and one of the types we are expecting */
                  if ((
#if defined(IPv6_FUNCTION_SUPPORT)
                        (returnedAddrHeader->ifa_family == AF_INET) ||
#endif
                        (returnedAddrHeader->ifa_family == AF_INET6)) &&
                      (returnedAddrHeader->ifa_index ==
                       interfaces[counter].index))
                    {
                      rta = IFA_RTA (returnedAddrHeader);
                      while (RTA_OK (rta, attLength))
                        {
                          if (IFA_ADDRESS == rta->rta_type)
                            {
                              if (currentIPAddressIndex < numAddresses)
                                {
                                  if (returnedAddrHeader->ifa_family ==
                                      AF_INET)
                                    {
                                      memcpy (interfaces[counter].
                                              addresses
                                              [currentIPAddressIndex].addr.
                                              bytes, RTA_DATA (rta),
                                              sizeof (struct in_addr));
                                      interfaces[counter].
                                        addresses[currentIPAddressIndex].
                                        length = sizeof (struct in_addr);
                                      interfaces[counter].
                                        addresses[currentIPAddressIndex].
                                        scope = 0;
                                    }
#if defined(IPv6_FUNCTION_SUPPORT)
                                  else
                                    if (returnedAddrHeader->ifa_family ==
                                        AF_INET6)
                                    {
                                      memcpy (interfaces[counter].
                                              addresses
                                              [currentIPAddressIndex].addr.
                                              bytes, RTA_DATA (rta),
                                              sizeof (struct in6_addr));
                                      interfaces[counter].
                                        addresses[currentIPAddressIndex].
                                        length = sizeof (struct in6_addr);
                                      if ((returnedAddrHeader->ifa_scope ==
                                           RT_SCOPE_LINK)
                                          || (returnedAddrHeader->ifa_scope ==
                                              RT_SCOPE_SITE))
                                        {
                                          interfaces[counter].
                                            addresses[currentIPAddressIndex].
                                            scope =
                                            returnedAddrHeader->ifa_index;
                                        }
                                      else
                                        {
                                          interfaces[counter].
                                            addresses[currentIPAddressIndex].
                                            scope = 0;
                                        }
                                    }
#endif
                                }
                              currentIPAddressIndex++;
                            }
                          rta = RTA_NEXT (rta, attLength);
                        }
                    }
                }
            }
          while (currentNlHeader != NULL);
        }
    }

  /* do any final clean up before returning */
  close (netlinkSocketHandle);

  hysock_cleanupNetlinkContext(portLibrary, &netlinkContext);

#else
  struct ifconf ifc;
  int len = 32 * sizeof (struct ifreq);
  hysocket_t socketP = NULL;
  U_32 totalInterfaces = 0;
  struct ifreq reqCopy;
  U_32 counter2 = 0;
  char *lastName = NULL;

  int ifconfCommand = SIOCGIFCONF;

#if defined(VARIABLE_LENGTH_IFREQ)
  U_32 bytesLeft = 0;
  struct ifreq *currentIfReq = NULL;
  struct ifreq *nextIfReq = NULL;
  U_32 ifrLength = 0;
  /* Some platforms have IFREQ structures that may be longer than the constant length defined for them.  In this case we need to get the next IFREQ 
     differentely */
  /* we need  socket to do the ioctl so create one */
  result =
    hysock_socket (portLibrary, &socketP, HYSOCK_AFINET, HYSOCK_DGRAM,
                   HYSOCK_DEFPROTOCOL);
  if (result != 0)
    {
      return result;
    }
  for (;;)
    {
      char *data =
        (char *) portLibrary->mem_allocate_memory (portLibrary, len);
#if (defined(VALIDATE_ALLOCATIONS))
      if (data == NULL)
        {
          hysock_close (portLibrary, &socketP);
          return HYPORT_ERROR_SOCKET_NOBUFFERS;
        }
#endif
      ifc.ifc_len = len;
      ifc.ifc_buf = data;
      if (ioctl (SOCKET_CAST (socketP), ifconfCommand, &ifc) != 0)
        {
          portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
          hysock_close (portLibrary, &socketP);
          return HYPORT_ERROR_SOCKET_NORECOVERY;
        }
      if (ifc.ifc_len < len)
        break;
      /* the returned data was likely truncated, expand the buffer and try again */
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      len += 32 * sizeof (struct ifreq);
    }
  /* get the number of distinct interfaces, including aliases */
  if (ifc.ifc_len != 0)
    {
      bytesLeft = ifc.ifc_len;

      currentIfReq = ifc.ifc_req;
      while (bytesLeft > 0)
        {
          totalInterfaces++;
          ifrLength =
            sizeof (currentIfReq->ifr_name) + currentIfReq->ifr_addr.sa_len;
          bytesLeft = bytesLeft - ifrLength;
          currentIfReq =
            (struct ifreq *) &(((char *) currentIfReq)[ifrLength]);
        }
      totalInterfaces = ifc.ifc_len / sizeof (struct ifreq);
    }
  lastName = NULL;
  currentIfReq = ifc.ifc_req;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      if ((NULL == lastName)
          || (strncmp (lastName, currentIfReq->ifr_name, IFNAMSIZ) != 0))
        {
          /* make sure the interface is up */
          reqCopy = *currentIfReq;
          ioctl (SOCKET_CAST (socketP), SIOCGIFFLAGS, &reqCopy);
          if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
            {
              numAdapters++;
            }
        }
      lastName = currentIfReq->ifr_name;
      currentIfReq =
        (struct ifreq *)
        &(((char *) currentIfReq)[sizeof (currentIfReq->ifr_name) +
                                  currentIfReq->ifr_addr.sa_len]);
    }
  /* now allocate the space for the hyNetworkInterface structs and fill it in */
  interfaces =
    portLibrary->mem_allocate_memory (portLibrary,
                                      numAdapters *
                                      sizeof (hyNetworkInterface_struct));
#if (defined(VALIDATE_ALLOCATIONS))
  if (NULL == interfaces)
    {
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      hysock_close (portLibrary, &socketP);
      return HYPORT_ERROR_SOCKET_NOBUFFERS;
    }
  /* initialize the structure so that we can free allocated if a failure occurs */
  for (counter = 0; counter < numAdapters; counter++)
    {
      interfaces[counter].name = NULL;
      interfaces[counter].displayName = NULL;
      interfaces[counter].addresses = NULL;
    }
#endif
/* set up the return structure */
  array->elements = interfaces;
  array->length = numAdapters;
  lastName = NULL;
  currentIfReq = ifc.ifc_req;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      /* make sure the interface is still up */
      reqCopy = *currentIfReq;
      ioctl (SOCKET_CAST (socketP), SIOCGIFFLAGS, &reqCopy);
      if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
        {
          /* since this function can return multiple entries for the same name, only do it for the first one with any given name */
          if ((NULL == lastName)
              || (strncmp (lastName, currentIfReq->ifr_name, IFNAMSIZ) != 0))
            {
              /* get the index for the interface.  This is only truely necessary on platforms that support IPV6 */
#if defined(IPv6_FUNCTION_SUPPORT)
              interfaces[currentAdapterIndex].index =
                if_nametoindex (currentIfReq->ifr_name);
#else
              interfaces[currentAdapterIndex].index = 0;
#endif
/* get the name and display name for the adapter */
              /* there only seems to be one name so use it for both the name and the display name */
              nameLength = strlen (currentIfReq->ifr_name);
              interfaces[currentAdapterIndex].name =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].name)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].name,
                       currentIfReq->ifr_name, nameLength);
              interfaces[currentAdapterIndex].name[nameLength] = 0;
              nameLength = strlen (currentIfReq->ifr_name);
              interfaces[currentAdapterIndex].displayName =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].displayName)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].displayName,
                       currentIfReq->ifr_name, nameLength);
              interfaces[currentAdapterIndex].displayName[nameLength] = 0;

              /* check how many addresses/aliases this adapter has.  aliases show up as adaptors with the same name */
              numAddresses = 0;
              nextIfReq = currentIfReq;
              for (counter2 = counter; counter2 < totalInterfaces; counter2++)
                {
                  if (strncmp
                      (currentIfReq->ifr_name, nextIfReq->ifr_name,
                       IFNAMSIZ) == 0)
                    {
                      if (
#if defined(IPv6_FUNCTION_SUPPORT)
                           (nextIfReq->ifr_addr.sa_family == AF_INET6) ||
#endif
                           (nextIfReq->ifr_addr.sa_family == AF_INET))
                        {
                          numAddresses++;
                        }
                    }
                  else
                    {
                      break;
                    }
                  nextIfReq =
                    (struct ifreq *)
                    &(((char *) nextIfReq)[sizeof (nextIfReq->ifr_name) +
                                           nextIfReq->ifr_addr.sa_len]);
                }

              /* allocate space for the addresses */
              interfaces[currentAdapterIndex].numberAddresses = numAddresses;
              interfaces[currentAdapterIndex].addresses =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  numAddresses *
                                                  sizeof
                                                  (hyipAddress_struct));
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].addresses)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
/* now get the addresses */
              currentIPAddressIndex = 0;
              lastName = currentIfReq->ifr_name;
              nextIfReq = currentIfReq;
              for (;;)
                {
                  if (nextIfReq->ifr_addr.sa_family == AF_INET)
                    {
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].addr.inAddr.s_addr =
                        ((struct sockaddr_in *) (&currentIfReq->ifr_addr))->
                        sin_addr.s_addr;
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope = 0;
                      currentIPAddressIndex++;
                    }
#if defined(IPv6_FUNCTION_SUPPORT)
                  else if (nextIfReq->ifr_addr.sa_family == AF_INET6)
                    {
                      memcpy (interfaces[currentAdapterIndex].
                              addresses[currentIPAddressIndex].addr.bytes,
                              &(((struct sockaddr_in6 *) (&currentIfReq->
                                                          ifr_addr))->
                                sin6_addr), sizeof (struct in6_addr));
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in6_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope =
                        ((struct sockaddr_in6 *) (&currentIfReq->
                                                  ifr_addr))->sin6_scope_id;
                      currentIPAddressIndex++;
                    }
#endif
/* we mean to increment the outside counter here as we want to skip the next entry as it is for the same interface
					   as we are currently working on */
                  nextIfReq =
                    (struct ifreq *)
                    &(((char *) nextIfReq)[sizeof (nextIfReq->ifr_name) +
                                           nextIfReq->ifr_addr.sa_len]);
                  if ((counter + 1 < totalInterfaces)
                      && (strncmp (nextIfReq->ifr_name, lastName, IFNAMSIZ) ==
                          0))
                    {
                      counter++;
                      currentIfReq = nextIfReq;
                    }
                  else
                    {
                      break;
                    }

                }
              currentAdapterIndex++;
            }
        }
      currentIfReq =
        (struct ifreq *)
        &(((char *) currentIfReq)[sizeof (currentIfReq->ifr_name) +
                                  currentIfReq->ifr_addr.sa_len]);
    }                           /* for over all interfaces */
  /* now an interface might have been taken down since we first counted them */
  array->length = currentAdapterIndex;
  /* free the memory now that we are done with it */
  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
  hysock_close (portLibrary, &socketP);
#else
  /* this method is not guarranteed to return the IPV6 addresses.  Code is include so that if the platform returns IPV6 addresses 
     in reply to the SIOCGIFCONF they will be included.  Howerver, it is not guarranteed or even expected that many platforms will
     include the IPV6 addresses.  For this reason there are other specific implementations that will return the IPV6 addresses */
  /* first get the list of interfaces.  We do not know how long the buffer needs to be so we try with one that allows for
     32 interfaces.  If this turns out not to be big enough then we expand the buffer to be able to support another
     32 interfaces and try again.  We do this until the result indicates that the result fit into the buffer provided */
  /* we need  socket to do the ioctl so create one */
  result =
    hysock_socket (portLibrary, &socketP, HYSOCK_AFINET, HYSOCK_DGRAM,
                   HYSOCK_DEFPROTOCOL);
  if (result != 0)
    {
      return result;
    }
  for (;;)
    {
      char *data =
        (char *) portLibrary->mem_allocate_memory (portLibrary, len);
#if (defined(VALIDATE_ALLOCATIONS))
      if (data == NULL)
        {
          hysock_close (portLibrary, &socketP);
          return HYPORT_ERROR_SOCKET_NOBUFFERS;
        }
#endif
      ifc.ifc_len = len;
      ifc.ifc_buf = data;
      errno = 0;
      if (ioctl (SOCKET_CAST (socketP), ifconfCommand, &ifc) != 0)
        {
          err = errno;
          portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
          hysock_close (portLibrary, &socketP);
          return portLibrary->error_set_last_error (portLibrary, err,
                                                    HYPORT_ERROR_SOCKET_NORECOVERY);
        }
      if (ifc.ifc_len < len)
        break;
      /* the returned data was likely truncated, expand the buffer and try again */
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      len += 32 * sizeof (struct ifreq);
    }
  /* get the number of distinct interfaces */
  if (ifc.ifc_len != 0)
    {
      totalInterfaces = ifc.ifc_len / sizeof (struct ifreq);
    }
  lastName = NULL;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      if ((NULL == lastName)
          || (strncmp (lastName, ifc.ifc_req[counter].ifr_name, IFNAMSIZ) !=
              0))
        {
          /* make sure the interface is up */
          reqCopy = ifc.ifc_req[counter];
          ioctl (SOCKET_CAST (socketP), SIOCGIFFLAGS, &reqCopy);
          if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
            {
              numAdapters++;
            }
        }
      lastName = ifc.ifc_req[counter].ifr_name;
    }
  /* now allocate the space for the hyNetworkInterface structs and fill it in */
  interfaces =
    portLibrary->mem_allocate_memory (portLibrary,
                                      numAdapters *
                                      sizeof (hyNetworkInterface_struct));
#if (defined(VALIDATE_ALLOCATIONS))
  if (NULL == interfaces)
    {
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      hysock_close (portLibrary, &socketP);
      return HYPORT_ERROR_SOCKET_NOBUFFERS;
    }
  /* initialize the structure so that we can free allocated if a failure occurs */
  for (counter = 0; counter < numAdapters; counter++)
    {
      interfaces[counter].name = NULL;
      interfaces[counter].displayName = NULL;
      interfaces[counter].addresses = NULL;
    }
#endif
/* set up the return structure */
  array->elements = interfaces;
  array->length = numAdapters;
  lastName = NULL;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      /* make sure the interface is still up */
      reqCopy = ifc.ifc_req[counter];
      ioctl (SOCKET_CAST (socketP), SIOCGIFFLAGS, &reqCopy);
      if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
        {
          /* since this function can return multiple entries for the same name, only do it for the first one with any given name */
          if ((NULL == lastName)
              || (strncmp (lastName, ifc.ifc_req[counter].ifr_name, IFNAMSIZ)
                  != 0))
            {
              /* get the index for the interface.  This is only truely necessary on platforms that support IPV6 */
#if defined(IPv6_FUNCTION_SUPPORT)
              interfaces[currentAdapterIndex].index =
                if_nametoindex (ifc.ifc_req[counter].ifr_name);
#else
              interfaces[currentAdapterIndex].index = 0;
#endif
/* get the name and display name for the adapter */
              /* there only seems to be one name so use it for both the name and the display name */
              nameLength = strlen (ifc.ifc_req[counter].ifr_name);
              interfaces[currentAdapterIndex].name =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].name)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].name,
                       ifc.ifc_req[counter].ifr_name, nameLength);
              interfaces[currentAdapterIndex].name[nameLength] = 0;
              nameLength = strlen (ifc.ifc_req[counter].ifr_name);
              interfaces[currentAdapterIndex].displayName =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLength + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].displayName)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].displayName,
                       ifc.ifc_req[counter].ifr_name, nameLength);
              interfaces[currentAdapterIndex].displayName[nameLength] = 0;

              /* check how many addresses/aliases this adapter has.  aliases show up as adaptors with the same name */
              numAddresses = 0;
              for (counter2 = counter; counter2 < totalInterfaces; counter2++)
                {
                  if (strncmp
                      (ifc.ifc_req[counter].ifr_name,
                       ifc.ifc_req[counter2].ifr_name, IFNAMSIZ) == 0)
                    {
                      if (
#if defined(IPv6_FUNCTION_SUPPORT)
                           (ifc.ifc_req[counter2].ifr_addr.sa_family ==
                            AF_INET6) ||
#endif
                           (ifc.ifc_req[counter2].ifr_addr.sa_family ==
                            AF_INET))
                        {
                          numAddresses++;
                        }
                    }
                  else
                    {
                      break;
                    }
                }

              /* allocate space for the addresses */
              interfaces[currentAdapterIndex].numberAddresses = numAddresses;
              interfaces[currentAdapterIndex].addresses =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  numAddresses *
                                                  sizeof
                                                  (hyipAddress_struct));
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].addresses)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, array);
                  hysock_close (portLibrary, &socketP);
                  return HYPORT_ERROR_SOCKET_NOBUFFERS;
                }
#endif
/* now get the addresses */
              currentIPAddressIndex = 0;
              lastName = ifc.ifc_req[counter].ifr_name;

              for (;;)
                {
                  if (ifc.ifc_req[counter].ifr_addr.sa_family == AF_INET)
                    {
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].addr.inAddr.s_addr =
                        ((struct sockaddr_in *) (&ifc.ifc_req[counter].
                                                 ifr_addr))->sin_addr.s_addr;
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope = 0;
                      currentIPAddressIndex++;
                    }
#if defined(IPv6_FUNCTION_SUPPORT)
                  else if (ifc.ifc_req[counter].ifr_addr.sa_family ==
                           AF_INET6)
                    {
                      memcpy (interfaces[currentAdapterIndex].
                              addresses[currentIPAddressIndex].addr.bytes,
                              &(((struct sockaddr_in6 *) (&ifc.
                                                          ifc_req[counter].
                                                          ifr_addr))->
                                sin6_addr), sizeof (struct in6_addr));
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in6_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope =
                        ((struct sockaddr_in6 *) (&ifc.ifc_req[counter].
                                                  ifr_addr))->sin6_scope_id;
                      currentIPAddressIndex++;
                    }
#endif
/* we mean to increment the outside counter here as we want to skip the next entry as it is for the same interface
					   as we are currently working on */
                  if ((counter + 1 < totalInterfaces)
                      &&
                      (strncmp
                       (ifc.ifc_req[counter + 1].ifr_name, lastName,
                        IFNAMSIZ) == 0))
                    {
                      counter++;
                    }
                  else
                    {
                      break;
                    }

                }
              currentAdapterIndex++;
            }
        }
    }                           /* for over all interfaces */
  /* now an interface might have been taken down since we first counted them */
  array->length = currentAdapterIndex;
  /* free the memory now that we are done with it */
  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
  hysock_close (portLibrary, &socketP);
#endif
#endif /* VARIABLE LENGTH IFREQ */

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_free_network_interface_struct

/**
 * Frees the memory allocated for the hyNetworkInterface_struct array passed in
 *
 * @param[in] portLibrary The port library.
 * @param[in] handle Pointer to array of network interface structures to be freed
 *
 * @return 0 on success
*/
I_32 VMCALL
hysock_free_network_interface_struct (struct HyPortLibrary * portLibrary,
                                      struct hyNetworkInterfaceArray_struct *
                                      array)
{
  U_32 i = 0;

  if ((array != NULL) && (array->elements != NULL))
    {

      /* free the allocated memory in each of the structures */
      for (i = 0; i < array->length; i++)
        {

          /* free the name, displayName and addresses */
          if (array->elements[i].name != NULL)
            {
              portLibrary->mem_free_memory (portLibrary,
                                            array->elements[i].name);
            }

          if (array->elements[i].displayName != NULL)
            {
              portLibrary->mem_free_memory (portLibrary,
                                            array->elements[i].displayName);
            }

          if (array->elements[i].addresses != NULL)
            {
              portLibrary->mem_free_memory (portLibrary,
                                            array->elements[i].addresses);
            }
        }

      /* now free the array itself */
      portLibrary->mem_free_memory (portLibrary, array->elements);
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getNextNetlinkMsg

/**
 * @internal	get the next message in a response to a netlink request.  The response may consist of one or more datagrams
 *                        each of which may contain one or more messages.  In addition the response may include NOOP messages
 *                        which are to be ignored and messages which simply indicate that we have reached the end of a multi-part
 *                        message.  This function simplifies the reading process and simply returns the next available netlink message
 * 					header and assocaited payload, or NULL when we have reached the end of the available messages
 *
 * @param	portLibrary useful so that we can use port library functions when necessary
 * 
 * @param netlinkContext  state must be maintined between calls to this function.  The state is maintained within this structure which is
 *                                              opaque to the caller.  The structure should be initialized with a call to initNetlinkContext before it is used to start
 *                                              getting a response for a request that has been made
 * 
 * @param nextMessage   populated with a ointer to the next netlink header or NULL if no more are available
 *
 * @return	0 if success, a socket error if a failure occured
 */
I_32
getNextNetlinkMsg (struct HyPortLibrary * portLibrary,
                   struct netlinkContext_struct * netlinkContext,
                   struct nlmsghdr ** nextMessage)
{

#if (defined(HAS_RTNETLINK))
  U_32 receiveLength;
  struct pollfd my_pollfd;

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
     * so read another datagram containing messages we first use a poll to make sure we 
     * don't block forever if for some reason there is no netlink message to read 
     */
     
      if (netlinkContext->remainingLength == 0)
        {
          my_pollfd.fd = netlinkContext->netlinkSocketHandle;
          my_pollfd.events = POLLIN | POLLPRI;
          my_pollfd.revents = 0;

          if (poll(&my_pollfd, 1, NETLINK_READTIMEOUT_SECS * 1000) > 0) {
                struct sockaddr_nl nladdr;
                struct msghdr msg;
                struct iovec iov;
                int reallocLoop = 1;

                iov.iov_base = netlinkContext->buffer;
                iov.iov_len = netlinkContext->bufferSize;
                msg.msg_name = (void *)&(nladdr);
                msg.msg_namelen = sizeof(nladdr);
                msg.msg_iov = &iov;
                msg.msg_iovlen = 1;
                
                while (reallocLoop) {
                    (void)recvmsg(netlinkContext->netlinkSocketHandle, &msg, MSG_PEEK);

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
                        
                        netlinkContext->buffer = portLibrary->mem_reallocate_memory(portLibrary, 
                                                    netlinkContext->buffer, 
                                                    netlinkContext->bufferSize * 2);
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
                  return portLibrary->error_set_last_error (portLibrary,
                                                            errno,
                                                            HYPORT_ERROR_SOCKET_NORECOVERY);
                }
                
              netlinkContext->remainingLength = receiveLength;
              netlinkContext->netlinkHeader =
                (struct nlmsghdr *) netlinkContext->buffer;
            }
          else
            {
              /* timeout waiting from netlink response messages */
              return portLibrary->error_set_last_error (portLibrary, errno,
                                                        HYPORT_ERROR_SOCKET_NORECOVERY);
            }
        }

      /* now return message if everything was ok */
      if (NLMSG_OK
          (netlinkContext->netlinkHeader, netlinkContext->remainingLength))
        {
          if (NLMSG_ERROR == netlinkContext->netlinkHeader->nlmsg_type)
            {
              /* we got an unexpected error message */
              return portLibrary->error_set_last_error (portLibrary, errno,
                                                        HYPORT_ERROR_SOCKET_NORECOVERY);
            }
          /* we are done if the NLM_F_MULTI flag is not set in this header */
          *nextMessage = netlinkContext->netlinkHeader;
          if ((netlinkContext->netlinkHeader->nlmsg_flags & NLM_F_MULTI) !=
              NLM_F_MULTI)
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
  
          return portLibrary->error_set_last_error (portLibrary, errno,
                                                    HYPORT_ERROR_SOCKET_NORECOVERY);
        }

      /*  If this message is not a NOOP return, otherwise just eat the message and go look for the next one  */
      if (netlinkContext->netlinkHeader->nlmsg_type != NLMSG_NOOP)
        {
          return 0;
        }
    }
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION initNetlinkContext

/**
 * @internal	Initializes the netlinkContext used across calls to getNextNetlinkMsg
 *
 * @param	portLibrary useful so that we can use port library functions when necessary
 * 
 * @param netlinkSocketHandle the netlink socket to be used for the netlink read operations
 * 
 * @param nextContext   pointer to the context to be initialized
 *
 * @return	0 if success, a socket error if a failure occured
 */

I_32
initNetlinkContext (struct HyPortLibrary * portLibrary,
                    I_32 netlinkSocketHandle,
                    struct netlinkContext_struct * netlinkContext)
{

#if (defined(HAS_RTNETLINK))
  netlinkContext->netlinkSocketHandle = netlinkSocketHandle;
  netlinkContext->netlinkHeader = NULL;
  netlinkContext->remainingLength = 0;
  netlinkContext->done = 0;
#endif

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysock_connect_with_timeout

/**
 * Establish a connection to a peer with a timeout.  This function is called repeatedly in order to carry out the connect and to allow
 * other tasks to proceed on certain platforms.  The caller must first call with step = HY_SOCK_STEP_START, if the result is HY_ERROR_SOCKET_NOTCONNECTED
 * it will then call it with step = CHECK until either another error or 0 is returned to indicate the connect is complete.  Each time the function should sleep for no more than
 * timeout milliseconds.  If the connect succeeds or an error occurs, the caller must always end the process by calling the function with step = HY_SOCK_STEP_DONE
 *
 * @param[in] portLibrary The port library.
 * @param[in] sock pointer to the unconnected local socket.
 * @param[in] addr	pointer to the sockaddr, specifying remote host/port.
 * @param[in] timeout  timeout in milliseconds. If timeout is negative, perform a block operation. 
 * @param[in,out] pointer to context pointer.  Filled in on first call and then to be passed into each subsequent call
 *
 * @return	0, if no errors occurred, otherwise the (negative) error code.
 */
I_32 VMCALL
hysock_connect_with_timeout (struct HyPortLibrary * portLibrary,
                             hysocket_t sock, hysockaddr_t addr, U_32 timeout,
                             U_32 step, U_8 ** context)
{
  I_32 rc = 0;
  int errorVal;
  int errorValLen = sizeof (int);
  struct pollfd my_pollfd;

  if (HY_PORT_SOCKET_STEP_START == step)
    {
      /* set the socket to non-blocking */
      rc = hysock_set_nonblocking (portLibrary, sock, TRUE);
      if (0 != rc)
        {
          return rc;
        }

      rc = connect
          (SOCKET_CAST (sock), (struct sockaddr *) &addr->addr,
           getAddrLength(addr));
      if (rc < 0)
        {
          rc = errno;
          switch (rc) {
            case HYPORT_ERROR_SOCKET_UNIX_EINTR:
              return HYPORT_ERROR_SOCKET_ALREADYBOUND;
            case HYPORT_ERROR_SOCKET_UNIX_EAGAIN:
            case HYPORT_ERROR_SOCKET_UNIX_EINPROGRESS:
              return HYPORT_ERROR_SOCKET_NOTCONNECTED;
            default:
              return portLibrary->error_set_last_error (portLibrary, rc,
                                                        findError (rc));
            }
          return rc;
        }

        /* we connected right off the bat so just return */
        return rc;

      }
    else if (HY_PORT_SOCKET_STEP_CHECK == step)
      {
      /* now check if we have connected yet */

      /* set the timeout value to be used. Because on some unix platforms we don't get notified when a socket
         is closed we only sleep for 100ms at a time */
      timeout = timeout > 100 ? 100 : timeout;

      /* initialize the FD sets for the select */
      my_pollfd.fd = SOCKET_CAST(sock);
      my_pollfd.events = POLLIN | POLLPRI | POLLOUT;
      my_pollfd.revents = 0;

      rc = poll(&my_pollfd, 1, timeout);
      
      /* if there is at least one descriptor ready to be checked */
      if (0 < rc)
        {
          /* if the descriptor is in the write set then we have connected or failed */

          if (my_pollfd.revents & POLLOUT) {
              if (!(my_pollfd.revents & (POLLIN | POLLPRI)))
                {
                  /* ok we have connected ok */
                  return 0;
                }
              else
                {
                  /* ok we have more work to do to figure it out */
                  if (getsockopt (SOCKET_CAST(sock), SOL_SOCKET, SO_ERROR,
                                  (char *) &errorVal, (unsigned int *)&errorValLen) >= 0) {
                      return errorVal ? findError(errorVal):0; 
                  } else {
                      rc = errno;
                      return portLibrary->error_set_last_error (portLibrary,
                                                                rc,
                                                                findError(rc));
                  }
              }
          }

          /* if the descriptor is in the exception set then the connect failed */
          if (my_pollfd.revents & (POLLERR | POLLHUP | POLLNVAL)) {
              if (getsockopt(SOCKET_CAST(sock), SOL_SOCKET, SO_ERROR,
                             (char *) &errorVal, (unsigned int *)&errorValLen) >= 0) {
                  return errorVal ? findError(errorVal):0; 
              }
              rc = errno;
              return portLibrary->error_set_last_error (portLibrary, rc,
                                                        findError (rc));
          }

        }
      else if (rc < 0)
        {
          /* something went wrong with the select call */
          rc = errno;

          /* if it was WASEINTR then we can just try again so just return not connected */
          if (EINTR == rc)
            {
              return HYPORT_ERROR_SOCKET_NOTCONNECTED;
            }

          /* some other error occured so look it up and return */
          return portLibrary->error_set_last_error (portLibrary, rc,
                                                    findError (rc));
      }

      /* if we get here the timeout expired or the connect had not yet completed
         just indicate that the connect is not yet complete  */
      return HYPORT_ERROR_SOCKET_NOTCONNECTED;
    }
  else if (HY_PORT_SOCKET_STEP_DONE == step)
    {
        /* we are done the connect or an error occured so clean up  */
        if (sock != INVALID_SOCKET)
          {
            hysock_set_nonblocking (portLibrary, sock, FALSE);
          }
        return 0;
    }
  return HYPORT_ERROR_SOCKET_ARGSINVALID;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getAddrLength

static socklen_t getAddrLength(hysockaddr_t addr)
{
  return
#if defined(IPv6_FUNCTION_SUPPORT)
    ((OSSOCKADDR *) & addr->addr)->sin_family == OS_AF_INET6 ?
    sizeof(OSSOCKADDR_IN6) :
#endif
    sizeof(OSSOCKADDR);
}
#undef CDEV_CURRENT_FUNCTION
