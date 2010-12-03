/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.jar;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Map;

import org.apache.harmony.archive.internal.nls.Messages;
import org.apache.harmony.luni.util.ThreadLocalCache;

class InitManifest {

    private byte[] buf;

    private int pos;

    Attributes.Name name;

    String value;

    CharsetDecoder decoder = ThreadLocalCache.utf8Decoder.get();
    CharBuffer cBuf = ThreadLocalCache.charBuffer.get();

    InitManifest(byte[] buf, Attributes main, Attributes.Name ver)
            throws IOException {

        this.buf = buf;

        // check a version attribute
        if (!readHeader() || (ver != null && !name.equals(ver))) {
            throw new IOException(Messages.getString(
                    "archive.2D", ver)); //$NON-NLS-1$
        }

        main.put(name, value);
        while (readHeader()) {
            main.put(name, value);
        }
    }

    void initEntries(Map<String, Attributes> entries,
            Map<String, Manifest.Chunk> chunks) throws IOException {

        int mark = pos;
        while (readHeader()) {
            if (!Attributes.Name.NAME.equals(name)) {
                throw new IOException(Messages.getString("archive.23")); //$NON-NLS-1$
            }
            String entryNameValue = value;

            Attributes entry = entries.get(entryNameValue);
            if (entry == null) {
                entry = new Attributes(12);
            }

            while (readHeader()) {
                entry.put(name, value);
            }

            if (chunks != null) {
                if (chunks.get(entryNameValue) != null) {
                    // TODO A bug: there might be several verification chunks for
                    // the same name. I believe they should be used to update
                    // signature in order of appearance; there are two ways to fix
                    // this: either use a list of chunks, or decide on used
                    // signature algorithm in advance and reread the chunks while
                    // updating the signature; for now a defensive error is thrown
                    throw new IOException(Messages.getString("archive.34")); //$NON-NLS-1$
                }
                chunks.put(entryNameValue, new Manifest.Chunk(mark, pos));
                mark = pos;
            }

            entries.put(entryNameValue, entry);
        }
    }

    int getPos() {
        return pos;
    }

    /**
     * Number of subsequent line breaks.
     */
    int linebreak = 0;

    /**
     * Read a single line from the manifest buffer.
     */
    private boolean readHeader() throws IOException {
        if (linebreak > 1) {
            // break a section on an empty line
            linebreak = 0;
            return false;
        }
        readName();
        linebreak = 0;
        readValue();
        // if the last line break is missed, the line
        // is ignored by the reference implementation
        return linebreak > 0;
    }

    private byte[] wrap(int mark, int pos) {
        byte[] buffer = new byte[pos - mark];
        System.arraycopy(buf, mark, buffer, 0, pos - mark);
        return buffer;
    }

    private void readName() throws IOException {
        int i = 0;
        int mark = pos;

        while (pos < buf.length) {
            byte b = buf[pos++];

            if (b == ':') {
                byte[] nameBuffer = wrap(mark, pos - 1);

                if (buf[pos++] != ' ') {
                    throw new IOException(Messages.getString(
                            "archive.30", nameBuffer)); //$NON-NLS-1$
                }

                name = new Attributes.Name(nameBuffer);
                return;
            }

            if (!((b >= 'a' && b <= 'z') || (b >= 'A' && b <= 'Z') || b == '_'
                    || b == '-' || (b >= '0' && b <= '9'))) {
                throw new IOException(Messages.getString("archive.30", b)); //$NON-NLS-1$
            }
        }
        if (i > 0) {
            throw new IOException(Messages.getString(
                    "archive.30", wrap(mark, buf.length))); //$NON-NLS-1$
        }
    }

    private void readValue() throws IOException {
        byte next;
        boolean lastCr = false;
        int mark = pos;
        int last = pos;

        decoder.reset();
        cBuf.clear();

        while (pos < buf.length) {
            next = buf[pos++];

            switch (next) {
            case 0:
                throw new IOException(Messages.getString("archive.2F")); //$NON-NLS-1$
            case '\n':
                if (lastCr) {
                    lastCr = false;
                } else {
                    linebreak++;
                }
                continue;
            case '\r':
                lastCr = true;
                linebreak++;
                continue;
            case ' ':
                if (linebreak == 1) {
                    decode(mark, last, false);
                    mark = pos;
                    linebreak = 0;
                    continue;
                }
            }

            if (linebreak >= 1) {
                pos--;
                break;
            }
            last = pos;
        }

        decode(mark, last, true);
        while (CoderResult.OVERFLOW == decoder.flush(cBuf)) {
            enlargeBuffer();
        }
        value = new String(cBuf.array(), cBuf.arrayOffset(), cBuf.position());
    }

    private void decode(int mark, int pos, boolean endOfInput)
            throws IOException {
        ByteBuffer bBuf = ByteBuffer.wrap(buf, mark, pos - mark);
        while (CoderResult.OVERFLOW == decoder.decode(bBuf, cBuf, endOfInput)) {
            enlargeBuffer();
        }
    }

    private void enlargeBuffer() {
        CharBuffer newBuf = CharBuffer.allocate(cBuf.capacity() * 2);
        newBuf.put(cBuf.array(), cBuf.arrayOffset(), cBuf.position());
        cBuf = newBuf;
        ThreadLocalCache.charBuffer.set(cBuf);
    }
}
