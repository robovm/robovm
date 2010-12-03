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

package org.apache.harmony.luni.platform;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;
import java.nio.channels.Channel;

/*
 * The interface for network methods.
 */
public interface INetworkSystem {

    /*
     * Socket connect Step start
     */
    public final int SOCKET_CONNECT_STEP_START = 0;

    /*
     * Socket connect Step check
     */
    public final int SOCKET_CONNECT_STEP_CHECK = 1;

    /*
     * socket accept
     */
    public void accept(FileDescriptor fdServer, SocketImpl newSocket,
            FileDescriptor fdnewSocket, int timeout) throws IOException;

    public void bind(FileDescriptor aFD, InetAddress inetAddress, int port)
            throws SocketException;

    public int read(FileDescriptor aFD, byte[] data, int offset, int count,
            int timeout) throws IOException;
    
    public int readDirect(FileDescriptor aFD, long address, int count,
            int timeout) throws IOException;

    public int write(FileDescriptor fd, byte[] data, int offset, int count)
            throws IOException;
    
    public int writeDirect(FileDescriptor fd, long address, int count)
            throws IOException;

    public long writev(FileDescriptor fd, Object[] buffers, int[] offsets,
            int[] lengths, int length) throws IOException;

    public void setNonBlocking(FileDescriptor aFD, boolean block)
            throws IOException;

    public int connect(FileDescriptor aFD, int trafficClass,
            InetAddress inetAddress, int port) throws IOException;

    public int connectWithTimeout(FileDescriptor aFD, int timeout,
            int trafficClass, InetAddress hostname, int port, int step,
            Long context) throws IOException;

    public int sendDatagram(FileDescriptor fd, byte[] data, int offset,
            int length, int port, boolean bindToDevice, int trafficClass,
            InetAddress inetAddress) throws IOException;
    
    public int sendDatagramDirect(FileDescriptor fd, long address, int offset,
            int length, int port, boolean bindToDevice, int trafficClass,
            InetAddress inetAddress) throws IOException;

    public int receiveDatagram(FileDescriptor aFD, DatagramPacket packet,
            byte[] data, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;
    
    public int receiveDatagramDirect(FileDescriptor aFD, DatagramPacket packet,
            long address, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;

    public int recvConnectedDatagram(FileDescriptor aFD, DatagramPacket packet,
            byte[] data, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;
    
    public int recvConnectedDatagramDirect(FileDescriptor aFD,
            DatagramPacket packet, long address, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;
    
    public int peekDatagram(FileDescriptor aFD, InetAddress sender,
            int receiveTimeout) throws IOException;

    public int sendConnectedDatagram(FileDescriptor fd, byte[] data,
            int offset, int length, boolean bindToDevice) throws IOException;
    
    public int sendConnectedDatagramDirect(FileDescriptor fd, long address,
            int offset, int length, boolean bindToDevice) throws IOException;

    public void disconnectDatagram(FileDescriptor aFD) throws SocketException;

    public void createDatagramSocket(FileDescriptor aFD, boolean preferIPv4Stack)
            throws SocketException;

    public void connectDatagram(FileDescriptor aFD, int port, int trafficClass,
            InetAddress inetAddress) throws SocketException;

    /**
     * @deprecated Use {@link #read(FileDescriptor, byte[], int, int, int)}
     */
    @Deprecated
    public int receiveStream(FileDescriptor aFD, byte[] data, int offset,
            int count, int timeout) throws IOException;

    public void shutdownInput(FileDescriptor descriptor) throws IOException;

    public void shutdownOutput(FileDescriptor descriptor) throws IOException;

    public boolean supportsUrgentData(FileDescriptor fd);

    public void sendUrgentData(FileDescriptor fd, byte value);

    public int availableStream(FileDescriptor aFD) throws SocketException;

    public void acceptStreamSocket(FileDescriptor fdServer,
            SocketImpl newSocket, FileDescriptor fdnewSocket, int timeout)
            throws IOException;

    public void createServerStreamSocket(FileDescriptor aFD, boolean preferIPv4Stack)
            throws SocketException;

    public void createStreamSocket(FileDescriptor aFD, boolean preferIPv4Stack)
            throws SocketException;
    
    public void listenStreamSocket(FileDescriptor aFD, int backlog)
            throws SocketException;

    public void connectStreamWithTimeoutSocket(FileDescriptor aFD, int aport,
            int timeout, int trafficClass, InetAddress inetAddress)
            throws IOException;

    public int sendDatagram2(FileDescriptor fd, byte[] data, int offset,
            int length, int port, InetAddress inetAddress) throws IOException;

    public InetAddress getSocketLocalAddress(FileDescriptor aFD,
            boolean preferIPv6Addresses);

    /**
     * Select the given file descriptors for read and write operations.
     *
     * <p>The first {@code numReadable} file descriptors of {@code readFDs} will
     * be selected for read-ready operations. The first {@code numWritable} file
     * descriptors in {@code writeFDs} will be selected for write-ready
     * operations. A file descriptor can appear in either or both and must not
     * be null. If the file descriptor is closed during the select the behavior
     * depends upon the underlying OS.
     *
     * @param readFDs
     *            all sockets interested in read and accept
     * @param writeFDs
     *            all sockets interested in write and connect
     * @param numReadable
     *            the size of the subset of readFDs to read or accept.
     * @param numWritable
     *            the size of the subset of writeFDs to write or connect
     * @param timeout
     *            timeout in milliseconds
     * @param flags
     *            for output. Length must be at least {@code numReadable
     *            + numWritable}. Upon returning, each element describes the
     *            state of the descriptor in the corresponding read or write
     *            array. See {@code SelectorImpl.READABLE} and {@code
     *            SelectorImpl.WRITEABLE}
     * @return true
     *            unless selection timed out or was interrupted
     * @throws SocketException
     */
    public boolean select(FileDescriptor[] readFDs, FileDescriptor[] writeFDs,
            int numReadable, int numWritable, long timeout, int[] flags)
            throws SocketException;

    /*
     * Query the IP stack for the local port to which this socket is bound.
     * 
     * @param aFD the socket descriptor @param preferIPv6Addresses address
     * preference for nodes that support both IPv4 and IPv6 @return int the
     * local port to which the socket is bound
     */
    public int getSocketLocalPort(FileDescriptor aFD,
            boolean preferIPv6Addresses);

    /*
     * Query the IP stack for the nominated socket option.
     * 
     * @param aFD the socket descriptor @param opt the socket option type
     * @return the nominated socket option value
     * 
     * @throws SocketException if the option is invalid
     */
    public Object getSocketOption(FileDescriptor aFD, int opt)
            throws SocketException;

    /*
     * Set the nominated socket option in the IP stack.
     * 
     * @param aFD the socket descriptor @param opt the option selector @param
     * optVal the nominated option value
     * 
     * @throws SocketException if the option is invalid or cannot be set
     */
    public void setSocketOption(FileDescriptor aFD, int opt, Object optVal)
            throws SocketException;

    public int getSocketFlags();

    /*
     * Close the socket in the IP stack.
     * 
     * @param aFD the socket descriptor
     */
    public void socketClose(FileDescriptor aFD) throws IOException;

    public InetAddress getHostByAddr(byte[] addr) throws UnknownHostException;

    public InetAddress getHostByName(String addr, boolean preferIPv6Addresses)
            throws UnknownHostException;

    public void setInetAddress(InetAddress sender, byte[] address);
    
    public boolean isReachableByICMP(InetAddress dest,InetAddress source,int ttl,int timeout);
    
    public Channel inheritedChannel();
    
    public void oneTimeInitialization(boolean jcl_supports_ipv6);
}
