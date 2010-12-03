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

package org.apache.harmony.luni.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class implements a Stream whose position can be queried for.
 */
public class PositionedInputStream extends FilterInputStream {

    private int currentPosition; // Current position on the underlying stream

    /**
     * Constructs a new instance of the receiver.
     * 
     * @param in The actual input stream where to read the bytes from.
     */
    public PositionedInputStream(InputStream in) {
        super(in);
    }

    /**
     * Return the current position in the receiver
     * 
     * @return int The current position in the receiver
     */
    public int currentPosition() {
        return currentPosition;
    }

    @Override
    public int read() throws IOException {
        int read = in.read();
        if (read >= 0) {
            currentPosition++;
        }
        return read;

    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int read = in.read(b, off, len);
        if (read >= 0) {
            currentPosition += read;
        }
        return read;
    }

    /**
     * Makes the current position on the underlying stream be assigned relative
     * position zero.
     */
    public void resetCurrentPosition() {
        currentPosition = 0;
    }

    @Override
    public long skip(long n) throws IOException {
        long skip = in.skip(n);
        currentPosition += skip; // Maybe currentPosition should be long ?
        return skip;
    }
}
