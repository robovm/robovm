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

package org.apache.harmony.xnet.provider.jsse;

import java.nio.ByteBuffer;
import javax.net.ssl.SSLException;

/**
 * This class is used to retrieve the application data
 * arrived for the SSLEngine.
 */
public class SSLEngineAppData implements org.apache.harmony.xnet.provider.jsse.Appendable {

    /**
     * Buffer containing received application data.
     */
    byte[] buffer;

    /**
     * Constructor
     */
    protected SSLEngineAppData() {}

    /**
     * Stores received data. The source data is not cloned,
     * just the array reference is remembered into the buffer field.
     */
    public void append(byte[] src) {
        if (buffer != null) {
            throw new AlertException(
                AlertProtocol.INTERNAL_ERROR,
                new SSLException("Attempt to override the data"));
        }
        buffer = src;
    }

    /**
     * Places the data from the buffer into the array of destination
     * ByteBuffer objects.
     */
    protected int placeTo(ByteBuffer[] dsts, int offset, int length) {
        if (buffer == null) {
            return 0;
        }
        int pos = 0;
        int len = buffer.length;
        int rem;
        // write data to the buffers
        for (int i=offset; i<offset+length; i++) {
            rem = dsts[i].remaining();
            // TODO: optimization work - use hasArray, array(), arraycopy
            if (len - pos < rem) {
                // can fully write remaining data into buffer
                dsts[i].put(buffer, pos, len - pos);
                pos = len;
                // data was written, exit
                break;
            }
            // write chunk of data
            dsts[i].put(buffer, pos, rem);
            pos += rem;
        }
        if (pos != len) {
            // The data did not feet into the buffers,
            // it should not happen, because the destination buffers
            // had been checked for the space before record unwrapping.
            // But if it so, we should allert about internal error.
            throw new AlertException(
                AlertProtocol.INTERNAL_ERROR,
                new SSLException(
                    "The received application data could not be fully written"
                    + "into the destination buffers"));
        }
        buffer = null;
        return len;
    }
}

