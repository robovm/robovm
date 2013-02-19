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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.Charsets;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import libcore.io.Streams;

/**
 * The {@code Manifest} class is used to obtain attribute information for a
 * {@code JarFile} and its entries.
 */
public class Manifest implements Cloneable {
    static final int LINE_LENGTH_LIMIT = 72;

    private static final byte[] LINE_SEPARATOR = new byte[] { '\r', '\n' };

    private static final byte[] VALUE_SEPARATOR = new byte[] { ':', ' ' };

    private static final Attributes.Name NAME_ATTRIBUTE = new Attributes.Name("Name");

    private static final Field BAIS_BUF = getByteArrayInputStreamField("buf");
    private static final Field BAIS_POS = getByteArrayInputStreamField("pos");

    private static Field getByteArrayInputStreamField(String name) {
        try {
            Field f = ByteArrayInputStream.class.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

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
     * The end of the main attributes section in the manifest is needed in
     * verification.
     */
    private int mainEnd;

    /**
     * Creates a new {@code Manifest} instance.
     */
    public Manifest() {
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
        return entries;
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
     * Merges name/attribute pairs read from the input stream {@code is} into this manifest.
     *
     * @param is
     *            The {@code InputStream} to read from.
     * @throws IOException
     *             If an error occurs reading the manifest.
     */
    public void read(InputStream is) throws IOException {
        byte[] buf;
        if (is instanceof ByteArrayInputStream) {
            buf = exposeByteArrayInputStreamBytes((ByteArrayInputStream) is);
        } else {
            buf = Streams.readFullyNoClose(is);
        }

        if (buf.length == 0) {
            return;
        }

        // a workaround for HARMONY-5662
        // replace EOF and NUL with another new line
        // which does not trigger an error
        byte b = buf[buf.length - 1];
        if (b == 0 || b == 26) {
            buf[buf.length - 1] = '\n';
        }

        // Attributes.Name.MANIFEST_VERSION is not used for
        // the second parameter for RI compatibility
        InitManifest im = new InitManifest(buf, mainAttributes, null);
        mainEnd = im.getPos();
        im.initEntries(entries, chunks);
    }

    /**
     * Returns a byte[] containing all the bytes from a ByteArrayInputStream.
     * Where possible, this returns the actual array rather than a copy.
     */
    private static byte[] exposeByteArrayInputStreamBytes(ByteArrayInputStream bais) {
        byte[] buffer;
        synchronized (bais) {
            byte[] buf;
            int pos;
            try {
                buf = (byte[]) BAIS_BUF.get(bais);
                pos = BAIS_POS.getInt(bais);
            } catch (IllegalAccessException iae) {
                throw new AssertionError(iae);
            }
            int available = bais.available();
            if (pos == 0 && buf.length == available) {
                buffer = buf;
            } else {
                buffer = new byte[available];
                System.arraycopy(buf, pos, buffer, 0, available);
            }
            bais.skip(available);
        }
        return buffer;
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
        CharsetEncoder encoder = Charsets.UTF_8.newEncoder();
        ByteBuffer buffer = ByteBuffer.allocate(LINE_LENGTH_LIMIT);

        String version = manifest.mainAttributes.getValue(Attributes.Name.MANIFEST_VERSION);
        if (version != null) {
            writeEntry(out, Attributes.Name.MANIFEST_VERSION, version, encoder, buffer);
            Iterator<?> entries = manifest.mainAttributes.keySet().iterator();
            while (entries.hasNext()) {
                Attributes.Name name = (Attributes.Name) entries.next();
                if (!name.equals(Attributes.Name.MANIFEST_VERSION)) {
                    writeEntry(out, name, manifest.mainAttributes.getValue(name), encoder, buffer);
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
            String value, CharsetEncoder encoder, ByteBuffer bBuf) throws IOException {
        String nameString = name.getName();
        os.write(nameString.getBytes(Charsets.US_ASCII));
        os.write(VALUE_SEPARATOR);

        encoder.reset();
        bBuf.clear().limit(LINE_LENGTH_LIMIT - nameString.length() - 2);

        CharBuffer cBuf = CharBuffer.wrap(value);

        while (true) {
            CoderResult r = encoder.encode(cBuf, bBuf, true);
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
