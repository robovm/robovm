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
import java.nio.charset.Charsets;
import java.util.Map;

/**
 * Reads a JAR file manifest. The specification is here:
 * http://java.sun.com/javase/6/docs/technotes/guides/jar/jar.html
 */
class InitManifest {
    private final byte[] buf;

    private int pos;

    private Attributes.Name name;

    private String value;

    private final UnsafeByteSequence valueBuffer = new UnsafeByteSequence(80);
    private int consecutiveLineBreaks = 0;

    InitManifest(byte[] buf, Attributes main, Attributes.Name ver) throws IOException {
        this.buf = buf;

        // check a version attribute
        if (!readHeader() || (ver != null && !name.equals(ver))) {
            throw new IOException("Missing version attribute: " + ver);
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
                throw new IOException("Entry is not named");
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
                    throw new IOException("A jar verifier does not support more than one entry with the same name");
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
     * Read a single line from the manifest buffer.
     */
    private boolean readHeader() throws IOException {
        if (consecutiveLineBreaks > 1) {
            // break a section on an empty line
            consecutiveLineBreaks = 0;
            return false;
        }
        readName();
        consecutiveLineBreaks = 0;
        readValue();
        // if the last line break is missed, the line
        // is ignored by the reference implementation
        return consecutiveLineBreaks > 0;
    }

    private void readName() throws IOException {
        int mark = pos;

        while (pos < buf.length) {
            if (buf[pos++] != ':') {
                continue;
            }

            String name = new String(buf, mark, pos - mark - 1, Charsets.US_ASCII);

            if (buf[pos++] != ' ') {
                throw new IOException(String.format("Invalid value for attribute '%s'", name));
            }

            try {
                this.name = new Attributes.Name(name);
            } catch (IllegalArgumentException e) {
                // new Attributes.Name() throws IllegalArgumentException but we declare IOException
                throw new IOException(e.getMessage());
            }
            return;
        }
    }

    private void readValue() throws IOException {
        boolean lastCr = false;
        int mark = pos;
        int last = pos;
        valueBuffer.rewind();
        while (pos < buf.length) {
            byte next = buf[pos++];
            switch (next) {
            case 0:
                throw new IOException("NUL character in a manifest");
            case '\n':
                if (lastCr) {
                    lastCr = false;
                } else {
                    consecutiveLineBreaks++;
                }
                continue;
            case '\r':
                lastCr = true;
                consecutiveLineBreaks++;
                continue;
            case ' ':
                if (consecutiveLineBreaks == 1) {
                    valueBuffer.write(buf, mark, last - mark);
                    mark = pos;
                    consecutiveLineBreaks = 0;
                    continue;
                }
            }

            if (consecutiveLineBreaks >= 1) {
                pos--;
                break;
            }
            last = pos;
        }

        valueBuffer.write(buf, mark, last - mark);
        value = valueBuffer.toString(Charsets.UTF_8);
    }
}
