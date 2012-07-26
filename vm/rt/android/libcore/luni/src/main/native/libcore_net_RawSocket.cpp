/*
 * Copyright 2010, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "AsynchronousSocketCloseMonitor.h"
#include "JNIHelp.h"
#include "JniException.h"
#include "JniConstants.h"
#include "NetFd.h"
#include "NetworkUtilities.h"
#include "ScopedUtfChars.h"
#include "ScopedPrimitiveArray.h"

#include "jni.h"

#include <sys/types.h>
#include <sys/socket.h>
#include <linux/rtnetlink.h>
#include <net/if.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h>
#include <arpa/inet.h>
#include <errno.h>
#include <fcntl.h>
#include <poll.h>
#include <netinet/ip.h>
#include <linux/udp.h>

union GCC_HIDDEN sockunion {
    sockaddr sa;
    sockaddr_ll sll;
} su;

/*
 * Creates a socket suitable for raw socket operations.  The socket is
 * bound to the interface specified by the supplied name.  The socket
 * value is placed into the supplied FileDescriptor instance.
 *
 * TODO(chesnutt): consider breaking this into pieces: create a
 * variety of constructors for different socket types, then a generic
 * setBlocking() method followed by polymorphic bind().
 */
extern "C" void Java_libcore_net_RawSocket_create(JNIEnv* env, jclass, jobject fileDescriptor,
    jshort protocolType, jstring interfaceName) {

  ScopedUtfChars ifname(env, interfaceName);
  if (ifname.c_str() == NULL) {
    return;
  }

  memset(&su, 0, sizeof(su));
  su.sll.sll_family = PF_PACKET;
  su.sll.sll_protocol = htons(protocolType);
  su.sll.sll_ifindex = if_nametoindex(ifname.c_str());
  int sock = socket(PF_PACKET, SOCK_DGRAM, htons(protocolType));

  if (sock == -1) {
    ALOGE("Can't create socket %s", strerror(errno));
    jniThrowSocketException(env, errno);
    return;
  }

  jniSetFileDescriptorOfFD(env, fileDescriptor, sock);
  if (!setBlocking(sock, false)) {
    ALOGE("Can't set non-blocking mode on socket %s", strerror(errno));
    jniThrowSocketException(env, errno);
    return;
  }

  int err = bind(sock, &su.sa, sizeof(su));
  if (err != 0) {
    ALOGE("Socket bind error %s", strerror(errno));
    jniThrowSocketException(env, errno);
    return;
  }
}

/*
 * Writes the L3 (IP) packet to the raw socket supplied in the
 * FileDescriptor instance.
 *
 * Assumes that the caller has validated the offset & byteCount values.
 */
extern "C" int Java_libcore_net_RawSocket_sendPacket(JNIEnv* env, jclass, jobject fileDescriptor,
    jstring interfaceName, jshort protocolType, jbyteArray destMac,
    jbyteArray packet, jint offset, jint byteCount)
{
  NetFd fd(env, fileDescriptor);

  if (fd.isClosed()) {
    return 0;
  }

  ScopedUtfChars ifname(env, interfaceName);
  if (ifname.c_str() == NULL) {
    return 0;
  }

  ScopedByteArrayRO byteArray(env, packet);
  if (byteArray.get() == NULL) {
    return 0;
  }

  ScopedByteArrayRO mac(env, destMac);
  if (mac.get() == NULL) {
    return 0;
  }

  memset(&su, 0, sizeof(su));
  su.sll.sll_hatype = htons(1); // ARPHRD_ETHER
  su.sll.sll_halen = mac.size();
  memcpy(&su.sll.sll_addr, mac.get(), mac.size());
  su.sll.sll_family = AF_PACKET;
  su.sll.sll_protocol = htons(protocolType);
  su.sll.sll_ifindex = if_nametoindex(ifname.c_str());

  int err;
  {
    int intFd = fd.get();
    AsynchronousSocketCloseMonitor monitor(intFd);
    err = NET_FAILURE_RETRY(fd, sendto(intFd, byteArray.get() + offset,
        byteCount, 0, &su.sa, sizeof(su)));
  }

  return err;
}

/*
 * Reads a network packet into the user-supplied buffer.  Return the
 * length of the packet, or a 0 if there was a timeout or an
 * unacceptable packet was acquired.
 *
 * Assumes that the caller has validated the offset & byteCount values.
 */
extern "C" jint Java_libcore_net_RawSocket_recvPacket(JNIEnv* env, jclass, jobject fileDescriptor,
    jbyteArray packet, jint offset, jint byteCount, jint port,
    jint timeout_millis)
{
  NetFd fd(env, fileDescriptor);
  if (fd.isClosed()) {
    return 0;
  }

  ScopedByteArrayRW body(env, packet);
  jbyte* packetData = body.get();
  if (packetData == NULL) {
    return 0;
  }

  packetData += offset;

  pollfd fds[1];
  fds[0].fd = fd.get();
  fds[0].events = POLLIN;
  int retval = poll(fds, 1, timeout_millis);
  if (retval <= 0) {
    return 0;
  }

  unsigned int size = 0;
  {
    int packetSize = byteCount;
    int intFd = fd.get();
    AsynchronousSocketCloseMonitor monitor(intFd);
    size = NET_FAILURE_RETRY(fd, read(intFd, packetData, packetSize));
  }

  if (env->ExceptionOccurred()) {
    return 0;
  }

  if (port != -1) {
    // quick check for UDP type & UDP port
    // the packet is an IP header, UDP header, and UDP payload
    if ((size < (sizeof(struct iphdr) + sizeof(struct udphdr)))) {
      return 0;  // runt packet
    }

    u_int8_t ip_proto = ((iphdr *) packetData)->protocol;
    if (ip_proto != IPPROTO_UDP) {
      return 0;  // something other than UDP
    }

    __be16 destPort = htons((reinterpret_cast<udphdr*>(packetData + sizeof(iphdr)))->dest);
    if (destPort != port) {
      return 0; // something other than requested port
    }
  }

  return size;
}

