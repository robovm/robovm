/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/io/SocketInputBuffer.java $
 * $Revision: 560358 $
 * $Date: 2007-07-27 12:30:42 -0700 (Fri, 27 Jul 2007) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import java.net.SocketTimeoutException;
import org.apache.http.params.HttpParams;


/**
 * {@link Socket} bound session input buffer.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 560358 $
 *
 * @since 4.0
 */
public class SocketInputBuffer extends AbstractSessionInputBuffer {

    private final Socket socket;

    public SocketInputBuffer(
            final Socket socket,
            int buffersize,
            final HttpParams params) throws IOException {
        super();
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        this.socket = socket;
        // BEGIN android-changed
        // Workaround for http://b/3514259. We take 'buffersize' as a hint in
        // the weakest sense, and always use an 8KiB heap buffer and leave the
        // kernel buffer size alone, trusting the system to have set a
        // network-appropriate default.
        init(socket.getInputStream(), 8192, params);
        // END android-changed
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        boolean result = hasBufferedData();
        if (!result) {
            int oldtimeout = this.socket.getSoTimeout();
            try {
                this.socket.setSoTimeout(timeout);
                fillBuffer();
                result = hasBufferedData();
            } catch (InterruptedIOException e) {
                if (!(e instanceof SocketTimeoutException)) {
                    throw e;
                }
            } finally {
                socket.setSoTimeout(oldtimeout);
            }
        }
        return result;
    }    
        
    // BEGIN android-added
    /**
     * Returns true if the connection is probably functional. It's insufficient
     * to rely on isDataAvailable() returning normally; that approach cannot
     * distinguish between an exhausted stream and a stream with zero bytes
     * buffered.
     *
     * @hide
     */
    public boolean isStale() throws IOException {
        if (hasBufferedData()) {
            return false;
        }
        int oldTimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(1);
            return fillBuffer() == -1;
        } catch (SocketTimeoutException e) {
            return false; // the connection is not stale; hooray
        } catch (IOException e) {
            return true; // the connection is stale, the read or soTimeout failed.
        } finally {
            this.socket.setSoTimeout(oldTimeout);
        }
    }
    // END android-added
}
