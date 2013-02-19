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

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import libcore.io.Streams;

/**
 * {@code JarFile} is used to read jar entries and their associated data from
 * jar files.
 *
 * @see JarInputStream
 * @see JarEntry
 */
public class JarFile extends ZipFile {

    /**
     * The MANIFEST file name.
     */
    public static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";

    // The directory containing the manifest.
    static final String META_DIR = "META-INF/";

    // The manifest after it has been read from the JAR.
    private Manifest manifest;

    // The entry for the MANIFEST.MF file before it is read.
    private ZipEntry manifestEntry;

    JarVerifier verifier;

    private boolean closed = false;

    static final class JarFileInputStream extends FilterInputStream {
        private long count;

        private ZipEntry zipEntry;

        private JarVerifier.VerifierEntry entry;

        private boolean done = false;

        JarFileInputStream(InputStream is, ZipEntry ze,
                JarVerifier.VerifierEntry e) {
            super(is);
            zipEntry = ze;
            count = zipEntry.getSize();
            entry = e;
        }

        @Override
        public int read() throws IOException {
            if (done) {
                return -1;
            }
            if (count > 0) {
                int r = super.read();
                if (r != -1) {
                    entry.write(r);
                    count--;
                } else {
                    count = 0;
                }
                if (count == 0) {
                    done = true;
                    entry.verify();
                }
                return r;
            } else {
                done = true;
                entry.verify();
                return -1;
            }
        }

        @Override
        public int read(byte[] buf, int off, int nbytes) throws IOException {
            if (done) {
                return -1;
            }
            if (count > 0) {
                int r = super.read(buf, off, nbytes);
                if (r != -1) {
                    int size = r;
                    if (count < size) {
                        size = (int) count;
                    }
                    entry.write(buf, off, size);
                    count -= size;
                } else {
                    count = 0;
                }
                if (count == 0) {
                    done = true;
                    entry.verify();
                }
                return r;
            } else {
                done = true;
                entry.verify();
                return -1;
            }
        }

        @Override
        public int available() throws IOException {
            if (done) {
                return 0;
            }
            return super.available();
        }

        @Override
        public long skip(long byteCount) throws IOException {
            return Streams.skipByReading(this, byteCount);
        }
    }

    /**
     * Create a new {@code JarFile} using the contents of the specified file.
     *
     * @param file
     *            the JAR file as {@link File}.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file) throws IOException {
        this(file, true);
    }

    /**
     * Create a new {@code JarFile} using the contents of the specified file.
     *
     * @param file
     *            the JAR file as {@link File}.
     * @param verify
     *            if this JAR file is signed whether it must be verified.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file, boolean verify) throws IOException {
        super(file);
        if (verify) {
            verifier = new JarVerifier(file.getPath());
        }
        readMetaEntries();
    }

    /**
     * Create a new {@code JarFile} using the contents of file.
     *
     * @param file
     *            the JAR file as {@link File}.
     * @param verify
     *            if this JAR filed is signed whether it must be verified.
     * @param mode
     *            the mode to use, either {@link ZipFile#OPEN_READ OPEN_READ} or
     *            {@link ZipFile#OPEN_DELETE OPEN_DELETE}.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file, boolean verify, int mode) throws IOException {
        super(file, mode);
        if (verify) {
            verifier = new JarVerifier(file.getPath());
        }
        readMetaEntries();
    }

    /**
     * Create a new {@code JarFile} from the contents of the file specified by
     * filename.
     *
     * @param filename
     *            the file name referring to the JAR file.
     * @throws IOException
     *             if file name cannot be opened for reading.
     */
    public JarFile(String filename) throws IOException {
        this(filename, true);
    }

    /**
     * Create a new {@code JarFile} from the contents of the file specified by
     * {@code filename}.
     *
     * @param filename
     *            the file name referring to the JAR file.
     * @param verify
     *            if this JAR filed is signed whether it must be verified.
     * @throws IOException
     *             If file cannot be opened or read.
     */
    public JarFile(String filename, boolean verify) throws IOException {
        super(filename);
        if (verify) {
            verifier = new JarVerifier(filename);
        }
        readMetaEntries();
    }

    /**
     * Return an enumeration containing the {@code JarEntrys} contained in this
     * {@code JarFile}.
     *
     * @return the {@code Enumeration} containing the JAR entries.
     * @throws IllegalStateException
     *             if this {@code JarFile} is closed.
     */
    @Override
    public Enumeration<JarEntry> entries() {
        class JarFileEnumerator implements Enumeration<JarEntry> {
            Enumeration<? extends ZipEntry> ze;

            JarFile jf;

            JarFileEnumerator(Enumeration<? extends ZipEntry> zenum, JarFile jf) {
                ze = zenum;
                this.jf = jf;
            }

            public boolean hasMoreElements() {
                return ze.hasMoreElements();
            }

            public JarEntry nextElement() {
                JarEntry je = new JarEntry(ze.nextElement());
                je.parentJar = jf;
                return je;
            }
        }
        return new JarFileEnumerator(super.entries(), this);
    }

    /**
     * Return the {@code JarEntry} specified by its name or {@code null} if no
     * such entry exists.
     *
     * @param name
     *            the name of the entry in the JAR file.
     * @return the JAR entry defined by the name.
     */
    public JarEntry getJarEntry(String name) {
        return (JarEntry) getEntry(name);
    }

    /**
     * Returns the {@code Manifest} object associated with this {@code JarFile}
     * or {@code null} if no MANIFEST entry exists.
     *
     * @return the MANIFEST.
     * @throws IOException
     *             if an error occurs reading the MANIFEST file.
     * @throws IllegalStateException
     *             if the jar file is closed.
     * @see Manifest
     */
    public Manifest getManifest() throws IOException {
        if (closed) {
            throw new IllegalStateException("JarFile has been closed");
        }
        if (manifest != null) {
            return manifest;
        }
        try {
            InputStream is = super.getInputStream(manifestEntry);
            if (verifier != null) {
                verifier.addMetaEntry(manifestEntry.getName(), Streams.readFully(is));
                is = super.getInputStream(manifestEntry);
            }
            try {
                manifest = new Manifest(is, verifier != null);
            } finally {
                is.close();
            }
            manifestEntry = null;  // Can discard the entry now.
        } catch (NullPointerException e) {
            manifestEntry = null;
        }
        return manifest;
    }

    /**
     * Called by the JarFile constructors, this method reads the contents of the
     * file's META-INF/ directory and picks out the MANIFEST.MF file and
     * verifier signature files if they exist. Any signature files found are
     * registered with the verifier.
     *
     * @throws IOException
     *             if there is a problem reading the jar file entries.
     */
    private void readMetaEntries() throws IOException {
        // Get all meta directory entries
        ZipEntry[] metaEntries = getMetaEntriesImpl();
        if (metaEntries == null) {
            verifier = null;
            return;
        }

        boolean signed = false;

        for (ZipEntry entry : metaEntries) {
            String entryName = entry.getName();
            // Is this the entry for META-INF/MANIFEST.MF ?
            if (manifestEntry == null && entryName.equalsIgnoreCase(MANIFEST_NAME)) {
                manifestEntry = entry;
                // If there is no verifier then we don't need to look any further.
                if (verifier == null) {
                    break;
                }
            } else {
                // Is this an entry that the verifier needs?
                if (verifier != null
                        && (endsWithIgnoreCase(entryName, ".SF")
                                || endsWithIgnoreCase(entryName, ".DSA")
                                || endsWithIgnoreCase(entryName, ".RSA"))) {
                    signed = true;
                    InputStream is = super.getInputStream(entry);
                    verifier.addMetaEntry(entryName, Streams.readFully(is));
                }
            }
        }

        // If there were no signature files, then no verifier work to do.
        if (!signed) {
            verifier = null;
        }
    }

    private static boolean endsWithIgnoreCase(String s, String suffix) {
        return s.regionMatches(true, s.length() - suffix.length(), suffix, 0, suffix.length());
    }

    /**
     * Return an {@code InputStream} for reading the decompressed contents of
     * ZIP entry.
     *
     * @param ze
     *            the ZIP entry to be read.
     * @return the input stream to read from.
     * @throws IOException
     *             if an error occurred while creating the input stream.
     */
    @Override
    public InputStream getInputStream(ZipEntry ze) throws IOException {
        if (manifestEntry != null) {
            getManifest();
        }
        if (verifier != null) {
            verifier.setManifest(getManifest());
            if (manifest != null) {
                verifier.mainAttributesEnd = manifest.getMainAttributesEnd();
            }
            if (verifier.readCertificates()) {
                verifier.removeMetaEntries();
                if (manifest != null) {
                    manifest.removeChunks();
                }
                if (!verifier.isSignedJar()) {
                    verifier = null;
                }
            }
        }
        InputStream in = super.getInputStream(ze);
        if (in == null) {
            return null;
        }
        if (verifier == null || ze.getSize() == -1) {
            return in;
        }
        JarVerifier.VerifierEntry entry = verifier.initEntry(ze.getName());
        if (entry == null) {
            return in;
        }
        return new JarFileInputStream(in, ze, entry);
    }

    /**
     * Return the {@code JarEntry} specified by name or {@code null} if no such
     * entry exists.
     *
     * @param name
     *            the name of the entry in the JAR file.
     * @return the ZIP entry extracted.
     */
    @Override
    public ZipEntry getEntry(String name) {
        ZipEntry ze = super.getEntry(name);
        if (ze == null) {
            return ze;
        }
        JarEntry je = new JarEntry(ze);
        je.parentJar = this;
        return je;
    }

    /**
     * Returns all the ZipEntry's that relate to files in the
     * JAR's META-INF directory.
     *
     * @return the list of ZipEntry's or {@code null} if there are none.
     */
    private ZipEntry[] getMetaEntriesImpl() {
        List<ZipEntry> list = new ArrayList<ZipEntry>(8);
        Enumeration<? extends ZipEntry> allEntries = entries();
        while (allEntries.hasMoreElements()) {
            ZipEntry ze = allEntries.nextElement();
            if (ze.getName().startsWith(META_DIR)
                    && ze.getName().length() > META_DIR.length()) {
                list.add(ze);
            }
        }
        if (list.size() == 0) {
            return null;
        }
        ZipEntry[] result = new ZipEntry[list.size()];
        list.toArray(result);
        return result;
    }

    /**
     * Closes this {@code JarFile}.
     *
     * @throws IOException
     *             if an error occurs.
     */
    @Override
    public void close() throws IOException {
        super.close();
        closed = true;
    }
}
