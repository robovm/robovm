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

/**
 * This class provides the DataStream functionality
 * implemented over the array of ByteBuffer instances.
 * Among with the data chunks read functionality
 * it provides the info about amount of consumed data.
 * The source ByteBuffer objects can be replaced by other.
 * So one instance of this wrapper can be reused for several
 * data sources.
 */
public class SSLEngineDataStream implements DataStream {

    private ByteBuffer[] srcs;
    private int offset;
    private int limit;

    private int available;
    private int consumed;

    protected SSLEngineDataStream() {}

    protected void setSourceBuffers(ByteBuffer[] srcs, int offset, int length) {
        this.srcs = srcs;
        this.offset = offset;
        this.limit = offset+length;
        this.consumed = 0;
        this.available = 0;
        for (int i=offset; i<limit; i++) {
            if (srcs[i] == null) {
                throw new IllegalStateException(
                        "Some of the input parameters are null");
            }
            available += srcs[i].remaining();
        }
    }

    public int available() {
        return available;
    }

    public boolean hasData() {
        return available > 0;
    }

    public byte[] getData(int length) {
        // TODO: optimization work:
        // use ByteBuffer.get(byte[],int,int)
        // and ByteBuffer.hasArray() methods
        int len = (length < available) ? length : available;
        available -= len;
        consumed += len;
        byte[] res = new byte[len];
        int pos = 0;
        loop:
        for (; offset<limit; offset++) {
            while (srcs[offset].hasRemaining()) {
                res[pos++] = srcs[offset].get();
                len --;
                if (len == 0) {
                    break loop;
                }
            }
        }
        return res;
    }

    protected int consumed() {
        return consumed;
    }
}

