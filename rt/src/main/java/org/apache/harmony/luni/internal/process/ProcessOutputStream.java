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

package org.apache.harmony.luni.internal.process;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;

class ProcessOutputStream extends OutputStream {

    private long handle;

    private FileDescriptor fd;

    // Fill in the JNI id caches
    private static native void oneTimeInitialization();

    static {
        oneTimeInitialization();
    }

    /**
     * Native to close the stream.
     */
    private native void closeImpl() throws IOException;

    /**
     * Native to set the FileDescriptor handle.
     */
    private native void setFDImpl(FileDescriptor fd, long handle);

    /**
     * Native to write the buffer to the stream.
     */
    private native void writeImpl(byte[] buf, int offset, int nbytes, long hndl)
            throws IOException;

    /**
     * Open an OutputStream based on the handle.
     */
    protected ProcessOutputStream(long handle) {
        this.fd = new FileDescriptor();
        setFDImpl(fd, handle);
        this.handle = handle;
    }

    /**
     * There is no way, at the library/VM level, to know when the stream will be
     * available for closing. If the user doesn't close it in its code, the
     * finalize() will run (eventually ?) and close the dangling OS
     * fileDescriptor.
     */
    @Override
    protected void finalize() throws Throwable {
        close();
    }

    @Override
    public void close() throws IOException {
        synchronized (this) {
            if (handle == -1) {
                return;
            }
            closeImpl();
            handle = -1;
        }
    }

    @Override
    public void write(byte[] buf) throws IOException {
        synchronized (this) {
            writeImpl(buf, 0, buf.length, handle);
        }
    }

    @Override
    public void write(byte[] buf, int offset, int nbytes) throws IOException {
        synchronized (this) {
            if (handle == -1) {
                return;
            }
            writeImpl(buf, offset, nbytes, handle);
        }
    }

    @Override
    public void write(int oneByte) throws IOException {
        byte buf[] = new byte[1];
        buf[0] = (byte) oneByte;
        synchronized (this) {
            writeImpl(buf, 0, 1, handle);
        }
    }
}
