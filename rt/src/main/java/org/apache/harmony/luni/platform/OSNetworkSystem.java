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

/**
 * This wraps native code that implements the INetworkSystem interface.
 */
final class OSNetworkSystem implements INetworkSystem {

    private static final int ERRORCODE_SOCKET_TIMEOUT = -209;
    private static final int ERRORCODE_SOCKET_INTERRUPTED = -208;

    private static final int INETADDR_REACHABLE = 0;

    private static boolean isNetworkInited = false;

    private static OSNetworkSystem singleton = new OSNetworkSystem();

    /**
     * Answers the unique instance of the OSNetworkSystem.
     * 
     * @return the network system interface instance
     */
    public static OSNetworkSystem getOSNetworkSystem() {
        return singleton;
    }

    // Can not be instantiated.
    private OSNetworkSystem() {
        super();
    }

    public native void accept(FileDescriptor fdServer, SocketImpl newSocket,
            FileDescriptor fdnewSocket, int timeout) throws IOException;

    public native void acceptStreamSocket(FileDescriptor fdServer,
            SocketImpl newSocket, FileDescriptor fdnewSocket, int timeout)
            throws IOException;

    public native int availableStream(FileDescriptor fd) throws SocketException;

    /**
     * Associates a local address with a socket.
     * 
     * @param fd
     *            the socket descriptor
     * @param port
     *            the port number
     * @param inetAddress
     *            address to bind
     * @throws SocketException
     *             thrown if bind operation fails
     */
    public native void bind(FileDescriptor fd, InetAddress inetAddress, int port) throws SocketException;

    public native int connect(FileDescriptor fd, int trafficClass,
            InetAddress inetAddress, int port) throws IOException;

    public native void connectDatagram(FileDescriptor fd, int port,
            int trafficClass, InetAddress inetAddress) throws SocketException;

    public native void connectStreamWithTimeoutSocket(FileDescriptor aFD,
            int aport, int timeout, int trafficClass, InetAddress inetAddress)
            throws IOException;

    public native int connectWithTimeout(FileDescriptor fd, int timeout,
            int trafficClass, InetAddress inetAddress, int port, int step,
            Long context) throws IOException;

    public native void createDatagramSocket(FileDescriptor fd,
            boolean preferIPv4Stack) throws SocketException;

    public native void createServerStreamSocket(FileDescriptor fd,
            boolean preferIPv4Stack) throws SocketException;

    public native void createStreamSocket(FileDescriptor fd,
            boolean preferIPv4Stack) throws SocketException;

    /**
     * Disconnect the socket to a port and address
     * 
     * @param fd
     *            the FileDescriptor associated with the socket
     * 
     * @throws SocketException
     *             if the disconnect fails
     */
    public native void disconnectDatagram(FileDescriptor fd)
            throws SocketException;

    public native InetAddress getHostByAddr(byte[] addr)
            throws UnknownHostException;

    public native InetAddress getHostByName(String addr,
            boolean preferIPv6Addresses) throws UnknownHostException;

    public native int getSocketFlags();

    public native InetAddress getSocketLocalAddress(FileDescriptor fd,
            boolean preferIPv6Addresses);

    /**
     * Query the IP stack for the local port to which this socket is bound.
     * 
     * @param aFD
     *            the socket descriptor
     * @param preferIPv6Addresses
     *            address preference for nodes that support both IPv4 and IPv6
     * @return the local port to which the socket is bound
     */
    public native int getSocketLocalPort(FileDescriptor aFD,
            boolean preferIPv6Addresses);

    /**
     * Query the IP stack for the nominated socket option.
     * 
     * @param fd
     *            the socket descriptor
     * @param opt
     *            the socket option type
     * @return the nominated socket option value
     * @throws SocketException
     *             if the option is invalid
     */
    public native Object getSocketOption(FileDescriptor fd, int opt)
            throws SocketException;

    public native Channel inheritedChannel();

    public boolean isReachableByICMP(final InetAddress dest,
            InetAddress source, final int ttl, final int timeout) {
        return INETADDR_REACHABLE == isReachableByICMPImpl(dest, source, ttl,
                timeout);
    }

    private native int isReachableByICMPImpl(InetAddress addr,
            InetAddress local, int ttl, int timeout);

    public native void listenStreamSocket(FileDescriptor aFD, int backlog)
            throws SocketException;

    public void oneTimeInitialization(boolean jcl_supports_ipv6) {
        if (!isNetworkInited) {
            oneTimeInitializationImpl(jcl_supports_ipv6);
            isNetworkInited = true;
        }
    }

    private native void oneTimeInitializationImpl(boolean jcl_supports_ipv6);

    /**
     * Peek on the socket, update <code>sender</code> address and answer the
     * sender port.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param sender
     *            an InetAddress, to be updated with the sender's address
     * @param receiveTimeout
     *            the maximum length of time the socket should block, reading
     * @return the sender port
     * 
     * @throws IOException
     *             upon an read error or timeout
     */
    public native int peekDatagram(FileDescriptor fd, InetAddress sender,
            int receiveTimeout) throws IOException;

    /**
     * Read available bytes from the given file descriptor into a byte array.
     * 
     * The read has an optional timeout parameter, which if non-zero is the
     * length of time that the read will wait on a select call to see if any
     * bytes are available for reading. If the timeout expires the method
     * returns zero to indicate no bytes were read.
     * 
     * @param fd
     *            the socket file descriptor to read
     * @param data
     *            the byte array in which to store the results
     * @param offset
     *            the offset into the byte array in which to start reading the
     *            results
     * @param count
     *            the maximum number of bytes to read
     * @param timeout
     *            the length of time to wait for the bytes, in milliseconds; or
     *            zero to indicate no timeout applied. When there is no timeout
     *            applied the read may block based upon socket options.
     * @return number of bytes read, or zero if there were no bytes available
     *         before the timeout occurred, or -1 to indicate the socket is
     *         closed
     * @throws IOException
     *             if an underlying socket exception occurred
     */
    public native int read(FileDescriptor fd, byte[] data, int offset,
            int count, int timeout) throws IOException;

    /**
     * Read available bytes from the given file descriptor into OS memory at a
     * given address.
     * 
     * @param fd
     *            the socket file descriptor to read
     * @param address
     *            the address of the memory in which to store the results
     * @param count
     *            the maximum number of bytes to read
     * @param timeout
     *            the length of time to wait for the bytes, in milliseconds
     * @return number of bytes read, or zero if there were no bytes available
     *         before the timeout occurred, or -1 to indicate the socket is
     *         closed
     * @throws IOException
     *             if an underlying socket exception occurred
     */
    public native int readDirect(FileDescriptor fd, long address, int count,
            int timeout) throws IOException;

    /**
     * Receive data on the socket into the specified buffer. The packet fields
     * <code>data</code> & <code>length</code> are passed in addition to
     * <code>packet</code> to eliminate the JNI field access calls.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param packet
     *            the DatagramPacket to receive into
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param receiveTimeout
     *            the maximum length of time the socket should block, reading
     * @param peek
     *            indicates to peek at the data
     * @return number of data received
     * @throws IOException
     *             upon an read error or timeout
     */
    public native int receiveDatagram(FileDescriptor fd, DatagramPacket packet,
            byte[] data, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;

    public native int receiveDatagramDirect(FileDescriptor fd,
            DatagramPacket packet, long address, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;

    /**
     * Receive at most <code>count</code> bytes into the buffer
     * <code>data</code> at the <code>offset</code> on the socket.
     * 
     * @param aFD
     *            the socket FileDescriptor
     * @param data
     *            the receive buffer
     * @param offset
     *            the offset into the buffer
     * @param count
     *            the max number of bytes to receive
     * @param timeout
     *            the max time the read operation should block waiting for data
     * @return the actual number of bytes read
     * @throws IOException
     * @throws SocketException
     *             if an error occurs while reading
     * @deprecated use {@link #read(FileDescriptor, byte[], int, int, int)}
     */
    @Deprecated
    public native int receiveStream(FileDescriptor aFD, byte[] data,
            int offset, int count, int timeout) throws IOException;

    /**
     * Recieve data on the connected socket into the specified buffer. The
     * packet fields <code>data</code> and <code>length</code> are passed in
     * addition to <code>packet</code> to eliminate the JNI field access calls.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param packet
     *            the DatagramPacket to receive into
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param receiveTimeout
     *            the maximum length of time the socket should block, reading
     * @param peek
     *            indicates to peek at the data
     * @return number of data received
     * @throws IOException
     *             upon an read error or timeout
     */
    public native int recvConnectedDatagram(FileDescriptor fd,
            DatagramPacket packet, byte[] data, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;

    public native int recvConnectedDatagramDirect(FileDescriptor fd,
            DatagramPacket packet, long address, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;


    public boolean select(FileDescriptor[] readFDs, FileDescriptor[] writeFDs,
            int numReadable, int numWritable, long timeout, int[] flags)
            throws SocketException {
        if (numReadable < 0 || numWritable < 0) {
            throw new IllegalArgumentException();
        }

        int total = numReadable + numWritable;
        if (total == 0) {
            return true;
        }

        assert validateFDs(readFDs, writeFDs, numReadable, numWritable) : "Invalid file descriptor arrays"; //$NON-NLS-1$

        // handle timeout in native
        int result = selectImpl(readFDs, writeFDs, numReadable, numWritable, flags, timeout);
        if (result >= 0) {
            return true;
        }
        if (result == ERRORCODE_SOCKET_TIMEOUT ||
                result == ERRORCODE_SOCKET_INTERRUPTED) {
            return false;
        }
        throw new SocketException();
    }

    private native int selectImpl(FileDescriptor[] readfd,
            FileDescriptor[] writefd, int cread, int cwirte, int[] flags,
            long timeout);

    /**
     * Send the <code>data</code> to the address and port to which the was
     * connected and <code>port</code>.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param bindToDevice
     *            not used, current kept in case needed as was the case for
     *            sendDatagramImpl
     * @return number of data send
     * @throws IOException
     *             upon an read error or timeout
     */
    public native int sendConnectedDatagram(FileDescriptor fd, byte[] data,
            int offset, int length, boolean bindToDevice) throws IOException;

    public native int sendConnectedDatagramDirect(FileDescriptor fd,
            long address, int offset, int length, boolean bindToDevice)
            throws IOException;

    /**
     * Send the <code>data</code> to the nominated target <code>address</code>
     * and <code>port</code>. These values are derived from the DatagramPacket
     * to reduce the field calls within JNI.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param port
     *            the target host port
     * @param bindToDevice
     *            if bind to device
     * @param trafficClass
     *            the traffic class to be used when the datagram is sent
     * @param inetAddress
     *            address to connect to.
     * @return number of data send
     * 
     * @throws IOException
     *             upon an read error or timeout
     */
    public native int sendDatagram(FileDescriptor fd, byte[] data, int offset,
            int length, int port, boolean bindToDevice, int trafficClass,
            InetAddress inetAddress) throws IOException;

    public native int sendDatagram2(FileDescriptor fd, byte[] data, int offset,
            int length, int port, InetAddress inetAddress) throws IOException;

    public native int sendDatagramDirect(FileDescriptor fd, long address,
            int offset, int length, int port, boolean bindToDevice,
            int trafficClass, InetAddress inetAddress) throws IOException;

    public native void sendUrgentData(FileDescriptor fd, byte value);

    public native void setInetAddress(InetAddress sender, byte[] address);

    public native void setNonBlocking(FileDescriptor fd, boolean block)
            throws IOException;

    /**
     * Set the nominated socket option in the IP stack.
     * 
     * @param aFD
     *            the socket descriptor @param opt the option selector @param
     *            optVal the nominated option value
     * 
     * @throws SocketException
     *             if the option is invalid or cannot be set
     */
    public native void setSocketOption(FileDescriptor aFD, int opt,
            Object optVal) throws SocketException;

    public native void shutdownInput(FileDescriptor fd) throws IOException;

    public native void shutdownOutput(FileDescriptor fd) throws IOException;

    /**
     * Close the socket in the IP stack.
     * 
     * @param fd
     *            the socket descriptor
     */
    public native void socketClose(FileDescriptor fd) throws IOException;

    public native boolean supportsUrgentData(FileDescriptor fd);

    /*
     * Used to check if the file descriptor arrays are valid before passing them
     * into the select native call.
     */
    private boolean validateFDs(FileDescriptor[] readFDs,
            FileDescriptor[] writeFDs) {
        for (FileDescriptor fd : readFDs) {
            // Also checks fd not null
            if (!fd.valid()) {
                return false;
            }
        }
        for (FileDescriptor fd : writeFDs) {
            if (!fd.valid()) {
                return false;
            }
        }
        return true;
    }

    private boolean validateFDs(FileDescriptor[] readFDs,
            FileDescriptor[] writeFDs, int countRead, int countWrite) {
        for (int i = 0; i < countRead; ++i) {
            // Also checks fd not null
            if (!readFDs[i].valid()) {
                return false;
            }
        }
        for (int i = 0; i < countWrite; ++i) {
            if (!writeFDs[i].valid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Write bytes from a byte array to a socket.
     * 
     * @param fd
     *            the socket on which to write the bytes
     * @param data
     *            the array containing the bytes to be written
     * @param offset
     *            the offset in the byte array from which to take the bytes
     * @param count
     *            the maximum number of bytes to be written. Callers are trusted
     *            not to send values of length+count that are larger than
     *            data.length
     * @return the actual number of bytes written, which will be between zero
     *         and count
     * @throws IOException
     *             if there is an underlying socket problem
     */
    public native int write(FileDescriptor fd, byte[] data, int offset,
            int count) throws IOException;

    /**
     * Write bytes from the given address to a socket.
     * 
     * @param fd
     *            the socket on which to write the bytes
     * @param address
     *            the start address of the bytes to be written
     * @param count
     *            the maximum number of bytes to be written
     * @return the actual number of bytes written, which will be between zero
     *         and count
     * @throws IOException
     *             if there is an underlying socket problem
     */
    public native int writeDirect(FileDescriptor fd, long address, int count)
            throws IOException;

    /**
     * Write given buffers to a socket. The given buffers is a Object array, the
     * element of array must be direct buffer or a byte array to be written.
     *
     * @param fd
     *            the socket on which to write the bytes
     * @param buffers
     *            the element of array must be direct buffer or a byte array to
     *            be written
     * @param offsets
     *            the index of the first byte to be write
     * @param counts
     *            the maximum number of bytes to be written
     * @param length
     *            the size of buffer array
     * @return the actual number of bytes written
     * @throws IOException
     *             if there is an underlying socket problem
     */
    public native long writev(FileDescriptor fd, Object[] buffers,
            int[] offsets, int[] lengths, int length) throws IOException;
}
