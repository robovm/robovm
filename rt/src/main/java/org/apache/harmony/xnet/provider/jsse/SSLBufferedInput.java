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

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * This is a wrapper input stream for ByteBuffer data source.
 * Among with the read functionality it provides info
 * about number of cunsumed bytes from the source ByteBuffer.
 * The source ByteBuffer object can be reseted.
 * So one instance of this wrapper can be reused for several
 * ByteBuffer data sources.
 */
public class SSLBufferedInput extends SSLInputStream {

    private ByteBuffer in;
    private int bytik;
    private int consumed = 0;

    /**
     * Constructor
     */
    protected SSLBufferedInput() {}

    /**
     * Sets the buffer as a data source
     */
    protected void setSourceBuffer(ByteBuffer in) {
        consumed = 0;
        this.in = in;
    }

    @Override
    public int available() throws IOException {
        // in assumption that the buffer has been set
        return in.remaining();
    }

    /**
     * Returns the number of consumed bytes.
     */
    protected int consumed() {
        return consumed;
    }

    /**
     * Reads the following byte value. If there are no bytes in the source
     * buffer, method throws java.nio.BufferUnderflowException.
     */
    @Override
    public int read() throws IOException {
        // TODO: implement optimized read(int)
        // and read(byte[], int, int) methods
        bytik = in.get() & 0x00FF;
        consumed ++;
        return bytik;
    }
}
