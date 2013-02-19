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
import java.io.OutputStream;
import java.nio.charset.Charsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import libcore.io.Base64;
import org.apache.harmony.security.utils.JarUtils;

/**
 * Non-public class used by {@link JarFile} and {@link JarInputStream} to manage
 * the verification of signed JARs. {@code JarFile} and {@code JarInputStream}
 * objects are expected to have a {@code JarVerifier} instance member which
 * can be used to carry out the tasks associated with verifying a signed JAR.
 * These tasks would typically include:
 * <ul>
 * <li>verification of all signed signature files
 * <li>confirmation that all signed data was signed only by the party or parties
 * specified in the signature block data
 * <li>verification that the contents of all signature files (i.e. {@code .SF}
 * files) agree with the JAR entries information found in the JAR manifest.
 * </ul>
 */
class JarVerifier {

    private final String jarName;

    private Manifest man;

    private HashMap<String, byte[]> metaEntries = new HashMap<String, byte[]>(5);

    private final Hashtable<String, HashMap<String, Attributes>> signatures = new Hashtable<String, HashMap<String, Attributes>>(
            5);

    private final Hashtable<String, Certificate[]> certificates = new Hashtable<String, Certificate[]>(
            5);

    private final Hashtable<String, Certificate[]> verifiedEntries = new Hashtable<String, Certificate[]>();

    int mainAttributesEnd;

    /**
     * Stores and a hash and a message digest and verifies that massage digest
     * matches the hash.
     */
    class VerifierEntry extends OutputStream {

        private String name;

        private MessageDigest digest;

        private byte[] hash;

        private Certificate[] certificates;

        VerifierEntry(String name, MessageDigest digest, byte[] hash,
                Certificate[] certificates) {
            this.name = name;
            this.digest = digest;
            this.hash = hash;
            this.certificates = certificates;
        }

        /**
         * Updates a digest with one byte.
         */
        @Override
        public void write(int value) {
            digest.update((byte) value);
        }

        /**
         * Updates a digest with byte array.
         */
        @Override
        public void write(byte[] buf, int off, int nbytes) {
            digest.update(buf, off, nbytes);
        }

        /**
         * Verifies that the digests stored in the manifest match the decrypted
         * digests from the .SF file. This indicates the validity of the
         * signing, not the integrity of the file, as it's digest must be
         * calculated and verified when its contents are read.
         *
         * @throws SecurityException
         *             if the digest value stored in the manifest does <i>not</i>
         *             agree with the decrypted digest as recovered from the
         *             <code>.SF</code> file.
         */
        void verify() {
            byte[] d = digest.digest();
            if (!MessageDigest.isEqual(d, Base64.decode(hash))) {
                throw invalidDigest(JarFile.MANIFEST_NAME, name, jarName);
            }
            verifiedEntries.put(name, certificates);
        }

    }

    private SecurityException invalidDigest(String signatureFile, String name, String jarName) {
        throw new SecurityException(signatureFile + " has invalid digest for " + name +
                " in " + jarName);
    }

    private SecurityException failedVerification(String jarName, String signatureFile) {
        throw new SecurityException(jarName + " failed verification of " + signatureFile);
    }

    /**
     * Constructs and returns a new instance of {@code JarVerifier}.
     *
     * @param name
     *            the name of the JAR file being verified.
     */
    JarVerifier(String name) {
        jarName = name;
    }

    /**
     * Invoked for each new JAR entry read operation from the input
     * stream. This method constructs and returns a new {@link VerifierEntry}
     * which contains the certificates used to sign the entry and its hash value
     * as specified in the JAR MANIFEST format.
     *
     * @param name
     *            the name of an entry in a JAR file which is <b>not</b> in the
     *            {@code META-INF} directory.
     * @return a new instance of {@link VerifierEntry} which can be used by
     *         callers as an {@link OutputStream}.
     */
    VerifierEntry initEntry(String name) {
        // If no manifest is present by the time an entry is found,
        // verification cannot occur. If no signature files have
        // been found, do not verify.
        if (man == null || signatures.size() == 0) {
            return null;
        }

        Attributes attributes = man.getAttributes(name);
        // entry has no digest
        if (attributes == null) {
            return null;
        }

        ArrayList<Certificate> certs = new ArrayList<Certificate>();
        Iterator<Map.Entry<String, HashMap<String, Attributes>>> it = signatures.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, Attributes>> entry = it.next();
            HashMap<String, Attributes> hm = entry.getValue();
            if (hm.get(name) != null) {
                // Found an entry for entry name in .SF file
                String signatureFile = entry.getKey();
                certs.addAll(getSignerCertificates(signatureFile, certificates));
            }
        }

        // entry is not signed
        if (certs.isEmpty()) {
            return null;
        }
        Certificate[] certificatesArray = certs.toArray(new Certificate[certs.size()]);

        String algorithms = attributes.getValue("Digest-Algorithms");
        if (algorithms == null) {
            algorithms = "SHA SHA1";
        }
        StringTokenizer tokens = new StringTokenizer(algorithms);
        while (tokens.hasMoreTokens()) {
            String algorithm = tokens.nextToken();
            String hash = attributes.getValue(algorithm + "-Digest");
            if (hash == null) {
                continue;
            }
            byte[] hashBytes = hash.getBytes(Charsets.ISO_8859_1);

            try {
                return new VerifierEntry(name, MessageDigest
                        .getInstance(algorithm), hashBytes, certificatesArray);
            } catch (NoSuchAlgorithmException e) {
                // ignored
            }
        }
        return null;
    }

    /**
     * Add a new meta entry to the internal collection of data held on each JAR
     * entry in the {@code META-INF} directory including the manifest
     * file itself. Files associated with the signing of a JAR would also be
     * added to this collection.
     *
     * @param name
     *            the name of the file located in the {@code META-INF}
     *            directory.
     * @param buf
     *            the file bytes for the file called {@code name}.
     * @see #removeMetaEntries()
     */
    void addMetaEntry(String name, byte[] buf) {
        metaEntries.put(name.toUpperCase(Locale.US), buf);
    }

    /**
     * If the associated JAR file is signed, check on the validity of all of the
     * known signatures.
     *
     * @return {@code true} if the associated JAR is signed and an internal
     *         check verifies the validity of the signature(s). {@code false} if
     *         the associated JAR file has no entries at all in its {@code
     *         META-INF} directory. This situation is indicative of an invalid
     *         JAR file.
     *         <p>
     *         Will also return {@code true} if the JAR file is <i>not</i>
     *         signed.
     * @throws SecurityException
     *             if the JAR file is signed and it is determined that a
     *             signature block file contains an invalid signature for the
     *             corresponding signature file.
     */
    synchronized boolean readCertificates() {
        if (metaEntries == null) {
            return false;
        }
        Iterator<String> it = metaEntries.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (key.endsWith(".DSA") || key.endsWith(".RSA")) {
                verifyCertificate(key);
                // Check for recursive class load
                if (metaEntries == null) {
                    return false;
                }
                it.remove();
            }
        }
        return true;
    }

    /**
     * @param certFile
     */
    private void verifyCertificate(String certFile) {
        // Found Digital Sig, .SF should already have been read
        String signatureFile = certFile.substring(0, certFile.lastIndexOf('.'))
                + ".SF";
        byte[] sfBytes = metaEntries.get(signatureFile);
        if (sfBytes == null) {
            return;
        }

        byte[] manifest = metaEntries.get(JarFile.MANIFEST_NAME);
        // Manifest entry is required for any verifications.
        if (manifest == null) {
            return;
        }

        byte[] sBlockBytes = metaEntries.get(certFile);
        try {
            Certificate[] signerCertChain = JarUtils.verifySignature(
                    new ByteArrayInputStream(sfBytes),
                    new ByteArrayInputStream(sBlockBytes));
            /*
             * Recursive call in loading security provider related class which
             * is in a signed JAR.
             */
            if (metaEntries == null) {
                return;
            }
            if (signerCertChain != null) {
                certificates.put(signatureFile, signerCertChain);
            }
        } catch (IOException e) {
            return;
        } catch (GeneralSecurityException e) {
            throw failedVerification(jarName, signatureFile);
        }

        // Verify manifest hash in .sf file
        Attributes attributes = new Attributes();
        HashMap<String, Attributes> entries = new HashMap<String, Attributes>();
        try {
            InitManifest im = new InitManifest(sfBytes, attributes, Attributes.Name.SIGNATURE_VERSION);
            im.initEntries(entries, null);
        } catch (IOException e) {
            return;
        }

        boolean createdBySigntool = false;
        String createdBy = attributes.getValue("Created-By");
        if (createdBy != null) {
            createdBySigntool = createdBy.indexOf("signtool") != -1;
        }

        // Use .SF to verify the mainAttributes of the manifest
        // If there is no -Digest-Manifest-Main-Attributes entry in .SF
        // file, such as those created before java 1.5, then we ignore
        // such verification.
        if (mainAttributesEnd > 0 && !createdBySigntool) {
            String digestAttribute = "-Digest-Manifest-Main-Attributes";
            if (!verify(attributes, digestAttribute, manifest, 0, mainAttributesEnd, false, true)) {
                throw failedVerification(jarName, signatureFile);
            }
        }

        // Use .SF to verify the whole manifest.
        String digestAttribute = createdBySigntool ? "-Digest"
                : "-Digest-Manifest";
        if (!verify(attributes, digestAttribute, manifest, 0, manifest.length,
                false, false)) {
            Iterator<Map.Entry<String, Attributes>> it = entries.entrySet()
                    .iterator();
            while (it.hasNext()) {
                Map.Entry<String, Attributes> entry = it.next();
                Manifest.Chunk chunk = man.getChunk(entry.getKey());
                if (chunk == null) {
                    return;
                }
                if (!verify(entry.getValue(), "-Digest", manifest,
                        chunk.start, chunk.end, createdBySigntool, false)) {
                    throw invalidDigest(signatureFile, entry.getKey(), jarName);
                }
            }
        }
        metaEntries.put(signatureFile, null);
        signatures.put(signatureFile, entries);
    }

    /**
     * Associate this verifier with the specified {@link Manifest} object.
     *
     * @param mf
     *            a {@code java.util.jar.Manifest} object.
     */
    void setManifest(Manifest mf) {
        man = mf;
    }

    /**
     * Returns a <code>boolean</code> indication of whether or not the
     * associated jar file is signed.
     *
     * @return {@code true} if the JAR is signed, {@code false}
     *         otherwise.
     */
    boolean isSignedJar() {
        return certificates.size() > 0;
    }

    private boolean verify(Attributes attributes, String entry, byte[] data,
            int start, int end, boolean ignoreSecondEndline, boolean ignorable) {
        String algorithms = attributes.getValue("Digest-Algorithms");
        if (algorithms == null) {
            algorithms = "SHA SHA1";
        }
        StringTokenizer tokens = new StringTokenizer(algorithms);
        while (tokens.hasMoreTokens()) {
            String algorithm = tokens.nextToken();
            String hash = attributes.getValue(algorithm + entry);
            if (hash == null) {
                continue;
            }

            MessageDigest md;
            try {
                md = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                continue;
            }
            if (ignoreSecondEndline && data[end - 1] == '\n'
                    && data[end - 2] == '\n') {
                md.update(data, start, end - 1 - start);
            } else {
                md.update(data, start, end - start);
            }
            byte[] b = md.digest();
            byte[] hashBytes = hash.getBytes(Charsets.ISO_8859_1);
            return MessageDigest.isEqual(b, Base64.decode(hashBytes));
        }
        return ignorable;
    }

    /**
     * Returns all of the {@link java.security.cert.Certificate} instances that
     * were used to verify the signature on the JAR entry called
     * {@code name}.
     *
     * @param name
     *            the name of a JAR entry.
     * @return an array of {@link java.security.cert.Certificate}.
     */
    Certificate[] getCertificates(String name) {
        Certificate[] verifiedCerts = verifiedEntries.get(name);
        if (verifiedCerts == null) {
            return null;
        }
        return verifiedCerts.clone();
    }

    /**
     * Remove all entries from the internal collection of data held about each
     * JAR entry in the {@code META-INF} directory.
     *
     * @see #addMetaEntry(String, byte[])
     */
    void removeMetaEntries() {
        metaEntries = null;
    }

    /**
     * Returns a {@code Vector} of all of the
     * {@link java.security.cert.Certificate}s that are associated with the
     * signing of the named signature file.
     *
     * @param signatureFileName
     *            the name of a signature file.
     * @param certificates
     *            a {@code Map} of all of the certificate chains discovered so
     *            far while attempting to verify the JAR that contains the
     *            signature file {@code signatureFileName}. This object is
     *            previously set in the course of one or more calls to
     *            {@link #verifyJarSignatureFile(String, String, String, Map, Map)}
     *            where it was passed as the last argument.
     * @return all of the {@code Certificate} entries for the signer of the JAR
     *         whose actions led to the creation of the named signature file.
     */
    public static Vector<Certificate> getSignerCertificates(
            String signatureFileName, Map<String, Certificate[]> certificates) {
        Vector<Certificate> result = new Vector<Certificate>();
        Certificate[] certChain = certificates.get(signatureFileName);
        if (certChain != null) {
            for (Certificate element : certChain) {
                result.add(element);
            }
        }
        return result;
    }
}
