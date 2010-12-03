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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.harmony.archive.internal.nls.Messages;
import org.apache.harmony.luni.util.InputStreamHelper;
import org.apache.harmony.luni.util.ThreadLocalCache;

/**
 * The {@code Manifest} class is used to obtain attribute information for a
 * {@code JarFile} and its entries.
 */
public class Manifest implements Cloneable {
    static final int LINE_LENGTH_LIMIT = 72;

    private static final byte[] LINE_SEPARATOR = new byte[] { '\r', '\n' };

    private static final byte[] VALUE_SEPARATOR = new byte[] { ':', ' ' };

    private static final Attributes.Name NAME_ATTRIBUTE = new Attributes.Name(
            "Name"); //$NON-NLS-1$

    private Attributes mainAttributes = new Attributes();

    private HashMap<String, Attributes> entries = new HashMap<String, Attributes>();

    static class Chunk {
        int start;
        int end;

        Chunk(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private HashMap<String, Chunk> chunks;

    /**
     * Manifest bytes are used for delayed entry parsing.
     */
    private InitManifest im;

    /**
     * The end of the main attributes section in the manifest is needed in
     * verification.
     */
    private int mainEnd;

    /**
     * Creates a new {@code Manifest} instance.
     */
    public Manifest() {
        super();
    }

    /**
     * Creates a new {@code Manifest} instance using the attributes obtained
     * from the input stream.
     *
     * @param is
     *            {@code InputStream} to parse for attributes.
     * @throws IOException
     *             if an IO error occurs while creating this {@code Manifest}
     */
    public Manifest(InputStream is) throws IOException {
        super();
        read(is);
    }

    /**
     * Creates a new {@code Manifest} instance. The new instance will have the
     * same attributes as those found in the parameter {@code Manifest}.
     *
     * @param man
     *            {@code Manifest} instance to obtain attributes from.
     */
    @SuppressWarnings("unchecked")
    public Manifest(Manifest man) {
        mainAttributes = (Attributes) man.mainAttributes.clone();
        entries = (HashMap<String, Attributes>) ((HashMap<String, Attributes>) man
                .getEntries()).clone();
    }

    Manifest(InputStream is, boolean readChunks) throws IOException {
        if (readChunks) {
            chunks = new HashMap<String, Chunk>();
        }
        read(is);
    }

    /**
     * Resets the both the main attributes as well as the entry attributes
     * associated with this {@code Manifest}.
     */
    public void clear() {
        im = null;
        entries.clear();
        mainAttributes.clear();
    }

    /**
     * Returns the {@code Attributes} associated with the parameter entry
     * {@code name}.
     *
     * @param name
     *            the name of the entry to obtain {@code Attributes} from.
     * @return the Attributes for the entry or {@code null} if the entry does
     *         not exist.
     */
    public Attributes getAttributes(String name) {
        return getEntries().get(name);
    }

    /**
     * Returns a map containing the {@code Attributes} for each entry in the
     * {@code Manifest}.
     *
     * @return the map of entry attributes.
     */
    public Map<String, Attributes> getEntries() {
        initEntries();
        return entries;
    }

    private void initEntries() {
        if (im == null) {
            return;
        }
        // try {
        // im.initEntries(entries, chunks);
        // } catch (IOException ioe) {
        // throw new RuntimeException(ioe);
        // }
        // im = null;
    }

    /**
     * Returns the main {@code Attributes} of the {@code JarFile}.
     *
     * @return main {@code Attributes} associated with the source {@code
     *         JarFile}.
     */
    public Attributes getMainAttributes() {
        return mainAttributes;
    }

    /**
     * Creates a copy of this {@code Manifest}. The returned {@code Manifest}
     * will equal the {@code Manifest} from which it was cloned.
     *
     * @return a copy of this instance.
     */
    @Override
    public Object clone() {
        return new Manifest(this);
    }

    /**
     * Writes out the attribute information of the receiver to the specified
     * {@code OutputStream}.
     *
     * @param os
     *            The {@code OutputStream} to write to.
     * @throws IOException
     *             If an error occurs writing the {@code Manifest}.
     */
    public void write(OutputStream os) throws IOException {
        write(this, os);
    }

    /**
     * Constructs a new {@code Manifest} instance obtaining attribute
     * information from the specified input stream.
     *
     * @param is
     *            The {@code InputStream} to read from.
     * @throws IOException
     *             If an error occurs reading the {@code Manifest}.
     */
    public void read(InputStream is) throws IOException {
        byte[] buf;
        // Try to read get a reference to the bytes directly
        try {
            buf = InputStreamHelper.expose(is);
        } catch (UnsupportedOperationException uoe) {
            buf = readFully(is);
        }

        if (buf.length == 0) {
            return;
        }

        // a workaround for HARMONY-5662
        // replace EOF and NUL with another new line
        // which does not trigger an error
        byte b = buf[buf.length - 1];
        if (0 == b || 26 == b) {
            buf[buf.length - 1] = '\n';
        }

        // Attributes.Name.MANIFEST_VERSION is not used for
        // the second parameter for RI compatibility
        im = new InitManifest(buf, mainAttributes, null);
        mainEnd = im.getPos();
        // FIXME
        im.initEntries(entries, chunks);
        im = null;
    }

    /*
     * Helper to read the entire contents of the manifest from the
     * given input stream.  Usually we can do this in a single read
     * but we need to account for 'infinite' streams, by ensuring we
     * have a line feed within a reasonable number of characters.
     */
    private byte[] readFully(InputStream is) throws IOException {
        // Initial read
        byte[] buffer = new byte[4096];
        int count = is.read(buffer);
        int nextByte = is.read();

        // Did we get it all in one read?
        if (nextByte == -1) {
            byte[] dest = new byte[count];
            System.arraycopy(buffer, 0, dest, 0, count);
            return dest;
        }

        // Does it look like a manifest?
        if (!containsLine(buffer, count)) {
            // archive.2E=Manifest is too long
            throw new IOException(Messages.getString("archive.2E")); //$NON-NLS-1$
        }

        // Requires additional reads
        ByteArrayOutputStream baos = new ByteArrayOutputStream(count * 2);
        baos.write(buffer, 0, count);
        baos.write(nextByte);
        while (true) {
            count = is.read(buffer);
            if (count == -1) {
                return baos.toByteArray();
            }
            baos.write(buffer, 0, count);
        }
    }

    /*
     * Check to see if the buffer contains a newline or carriage
     * return character within the first 'length' bytes.  Used to
     * check the validity of the manifest input stream.
     */
    private boolean containsLine(byte[] buffer, int length) {
        for (int i = 0; i < length; i++) {
            if (buffer[i] == 0x0A || buffer[i] == 0x0D) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the hash code for this instance.
     *
     * @return this {@code Manifest}'s hashCode.
     */
    @Override
    public int hashCode() {
        return mainAttributes.hashCode() ^ getEntries().hashCode();
    }

    /**
     * Determines if the receiver is equal to the parameter object. Two {@code
     * Manifest}s are equal if they have identical main attributes as well as
     * identical entry attributes.
     *
     * @param o
     *            the object to compare against.
     * @return {@code true} if the manifests are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        if (!mainAttributes.equals(((Manifest) o).mainAttributes)) {
            return false;
        }
        return getEntries().equals(((Manifest) o).getEntries());
    }

    Chunk getChunk(String name) {
        return chunks.get(name);
    }

    void removeChunks() {
        chunks = null;
    }

    int getMainAttributesEnd() {
        return mainEnd;
    }

    /**
     * Writes out the attribute information of the specified manifest to the
     * specified {@code OutputStream}
     *
     * @param manifest
     *            the manifest to write out.
     * @param out
     *            The {@code OutputStream} to write to.
     * @throws IOException
     *             If an error occurs writing the {@code Manifest}.
     */
    static void write(Manifest manifest, OutputStream out) throws IOException {
        CharsetEncoder encoder = ThreadLocalCache.utf8Encoder.get();
        ByteBuffer buffer = ThreadLocalCache.byteBuffer.get();

        String version = manifest.mainAttributes
                .getValue(Attributes.Name.MANIFEST_VERSION);
        if (version != null) {
            writeEntry(out, Attributes.Name.MANIFEST_VERSION, version, encoder,
                    buffer);
            Iterator<?> entries = manifest.mainAttributes.keySet().iterator();
            while (entries.hasNext()) {
                Attributes.Name name = (Attributes.Name) entries.next();
                if (!name.equals(Attributes.Name.MANIFEST_VERSION)) {
                    writeEntry(out, name, manifest.mainAttributes
                            .getValue(name), encoder, buffer);
                }
            }
        }
        out.write(LINE_SEPARATOR);
        Iterator<String> i = manifest.getEntries().keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            writeEntry(out, NAME_ATTRIBUTE, key, encoder, buffer);
            Attributes attrib = manifest.entries.get(key);
            Iterator<?> entries = attrib.keySet().iterator();
            while (entries.hasNext()) {
                Attributes.Name name = (Attributes.Name) entries.next();
                writeEntry(out, name, attrib.getValue(name), encoder, buffer);
            }
            out.write(LINE_SEPARATOR);
        }
    }

    private static void writeEntry(OutputStream os, Attributes.Name name,
            String value, CharsetEncoder encoder, ByteBuffer bBuf)
            throws IOException {
        byte[] out = name.getBytes();
        if (out.length > LINE_LENGTH_LIMIT) {
            throw new IOException(Messages.getString(
                    "archive.33", name, Integer.valueOf(LINE_LENGTH_LIMIT))); //$NON-NLS-1$
        }

        os.write(out);
        os.write(VALUE_SEPARATOR);

        encoder.reset();
        bBuf.clear().limit(LINE_LENGTH_LIMIT - out.length - 2);

        CharBuffer cBuf = CharBuffer.wrap(value);
        CoderResult r;

        while (true) {
            r = encoder.encode(cBuf, bBuf, true);
            if (CoderResult.UNDERFLOW == r) {
                r = encoder.flush(bBuf);
            }
            os.write(bBuf.array(), bBuf.arrayOffset(), bBuf.position());
            os.write(LINE_SEPARATOR);
            if (CoderResult.UNDERFLOW == r) {
                break;
            }
            os.write(' ');
            bBuf.clear().limit(LINE_LENGTH_LIMIT - 1);
        }
    }
}
