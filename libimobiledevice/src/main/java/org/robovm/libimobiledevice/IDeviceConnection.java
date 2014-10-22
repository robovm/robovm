/*
 * Copyright (C) 2013 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.libimobiledevice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.robovm.libimobiledevice.binding.IDeviceConnectionRef;
import org.robovm.libimobiledevice.binding.IntOut;
import org.robovm.libimobiledevice.binding.LibIMobileDevice;

/**
 * Represents a device connection.
 */
public class IDeviceConnection implements AutoCloseable {
    protected IDeviceConnectionRef ref;
    private DeviceInputStream deviceInputStream;
    private DeviceOutputStream deviceOutputStream;

    IDeviceConnection(IDeviceConnectionRef ref) {
        this.ref = ref;
    }
    
    protected IDeviceConnectionRef getRef() {
        checkDisposed();
        return ref;
    }

    private void createStreams() {
        if (deviceInputStream == null) {
            deviceInputStream = new DeviceInputStream();
            deviceOutputStream = new DeviceOutputStream();
        }
    }

    /**
     * Returns an {@link InputStream} for reading from this
     * {@link IDeviceConnection}.
     */
    public InputStream getInputStream() {
        createStreams();
        return deviceInputStream;
    }

    /**
     * Returns and {@link OutputStream} for writing to this
     * {@link IDeviceConnection}.
     */
    public OutputStream getOutputStream() {
        createStreams();
        return deviceOutputStream;
    }

    /**
     * Receives data from the device. Waits indefinitely for data on the
     * connection.
     *
     * @param buffer the byte array in which to store the received data.
     * @param offset the initial position in {@code buffer} to store the 
     *               received bytes.
     * @param count the maximum number of bytes to store in {@code buffer}.
     * @return the number of bytes received.
     */
    public int receive(byte[] buffer, int offset, int count) {
        return receive(buffer, offset, count, Integer.MAX_VALUE);
    }
    
    /**
     * Receives data from the device. Returns after the given timeout even if no 
     * data has been received.
     *
     * @param buffer the byte array in which to store the received data.
     * @param offset the initial position in {@code buffer} to store the 
     *               received bytes.
     * @param count the maximum number of bytes to store in {@code buffer}.
     * @param timeout timeout in milliseconds after which this method will
     *                return even if no data has been received.
     * @return the number of bytes received.
     */
    public int receive(byte[] buffer, int offset, int count, int timeout) {
        checkArrayBounds(buffer, offset, count);
        
        if (count == 0) {
            return 0;
        }
        
        byte[] data = buffer;
        if (offset > 0) {
            data = new byte[count];
        }
        
        IntOut bytesReceivedOut = new IntOut();
        try {
            IDevice.checkResult(LibIMobileDevice.idevice_connection_receive_timeout(getRef(), 
                    data, count, bytesReceivedOut, timeout));
            int bytesRead = bytesReceivedOut.getValue();
            if (bytesRead > 0 && data != buffer) {
                System.arraycopy(data, 0, buffer, offset, bytesRead);
            }
            return bytesRead;
        } finally {
            bytesReceivedOut.delete();
        }
    }

    private void checkArrayBounds(byte[] buffer, int offset, int count) {
        if ((offset | count) < 0 || offset > buffer.length || buffer.length - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length 
                    + "; regionStart=" + offset + "; regionLength=" + count);
        }
    }
    
    /**
     * Sends data to the device on this connection.
     *
     * @param buffer the buffer to be sent.
     * @param offset the start position in {@code buffer} from where to get bytes.
     * @param count the number of bytes from {@code buffer} to send.
     * @return the number of bytes actually sent.
     */
    public int send(byte[] buffer, int offset, int count) {
        checkArrayBounds(buffer, offset, count);
        
        if (count == 0) {
            return 0;
        }
        
        byte[] data = buffer;
        if (offset > 0) {
            data = new byte[count];
            System.arraycopy(buffer, offset, data, 0, count);
        }
        
        IntOut bytesSentOut = new IntOut();
        try {
            IDevice.checkResult(LibIMobileDevice.idevice_connection_send(getRef(), data, count, bytesSentOut));
            return bytesSentOut.getValue();
        } finally {
            bytesSentOut.delete();
        }
    }
    
    /**
     * Disconnects from the device. This is the same as calling {@link #dispose()}
     * or {@link #close()}.
     */
    public void disconnect() {
        dispose();
    }
    
    protected final void checkDisposed() {
        if (ref == null) {
            throw new LibIMobileDeviceException("Already disposed");
        }
    }
    
    public synchronized void dispose() {
        checkDisposed();
        LibIMobileDevice.idevice_disconnect(ref);
        ref = null;
    }
    
    @Override
    public void close() {
        dispose();
    }

    private class DeviceInputStream extends InputStream {

        @Override
        public int read() throws IOException {
            byte[] b = new byte[1];
            int n = read(b);
            return n <= 0 ? -1 : b[0];
        }

        @Override
        public int read(byte[] b) throws IOException {
            return read(b, 0, b.length);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            try {
                return receive(b, off, len);
            } catch (LibIMobileDeviceException e) {
                throw new IOException(e);
            }
        }
    }

    private class DeviceOutputStream extends OutputStream {

        @Override
        public void write(int b) throws IOException {
            byte[] buffer = new byte[1];
            buffer[0] = (byte) b;
            write(buffer);
        }

        @Override
        public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            checkArrayBounds(b, off, len);
            try {
                send(b, off, len);
            } catch (LibIMobileDeviceException e) {
                throw new IOException(e);
            }
        }
    }
}
