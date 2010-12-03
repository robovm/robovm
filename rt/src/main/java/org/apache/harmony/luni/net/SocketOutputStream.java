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

package org.apache.harmony.luni.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketImpl;

import org.apache.harmony.luni.internal.nls.Messages;

class SocketOutputStream extends OutputStream {

    private PlainSocketImpl socket;

    /**
     * Constructs a SocketOutputStream for the <code>socket</code>. Write
     * operations are forwarded to the <code>socket</code>.
     * 
     * @param socket the socket to be written
     * @see Socket
     */
    public SocketOutputStream(SocketImpl socket) {
        super();
        this.socket = (PlainSocketImpl) socket;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public void write(byte[] buffer) throws IOException {
        socket.write(buffer, 0, buffer.length);
    }

    @Override
    public void write(byte[] buffer, int offset, int count) throws IOException {
        // avoid int overflow
        if (buffer != null) {
            if (0 <= offset && offset <= buffer.length && 0 <= count
                    && count <= buffer.length - offset) {
                socket.write(buffer, offset, count);
            } else {
                throw new ArrayIndexOutOfBoundsException(Messages.getString("luni.13"));//$NON-NLS-1$
            }
        } else {
            throw new NullPointerException(Messages.getString("luni.11"));//$NON-NLS-1$
        }
    }

    @Override
    public void write(int oneByte) throws IOException {
        byte[] buffer = new byte[1];
        buffer[0] = (byte) (oneByte & 0xFF);

        socket.write(buffer, 0, 1);
    }
}
