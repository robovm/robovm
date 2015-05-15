/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2013 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.conscrypt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.security.auth.x500.X500Principal;

import libcore.io.IoUtils;

/**
 * A source for trusted root certificate authority (CA) certificates
 * supporting an immutable system CA directory along with mutable
 * directories allowing the user addition of custom CAs and user
 * removal of system CAs. This store supports the {@code
 * TrustedCertificateKeyStoreSpi} wrapper to allow a traditional
 * KeyStore interface for use with {@link
 * javax.net.ssl.TrustManagerFactory.init}.
 *
 * <p>The CAs are accessed via {@code KeyStore} style aliases. Aliases
 * are made up of a prefix identifying the source ("system:" vs
 * "user:") and a suffix based on the OpenSSL X509_NAME_hash_old
 * function of the CA's subject name. For example, the system CA for
 * "C=US, O=VeriSign, Inc., OU=Class 3 Public Primary Certification
 * Authority" could be represented as "system:7651b327.0". By using
 * the subject hash, operations such as {@link #getCertificateAlias
 * getCertificateAlias} can be implemented efficiently without
 * scanning the entire store.
 *
 * <p>In addition to supporting the {@code
 * TrustedCertificateKeyStoreSpi} implementation, {@code
 * TrustedCertificateStore} also provides the additional public
 * methods {@link #isTrustAnchor} and {@link #findIssuer} to allow
 * efficient lookup operations for CAs again based on the file naming
 * convention.
 *
 * <p>The KeyChainService users the {@link installCertificate} and
 * {@link #deleteCertificateEntry} to install user CAs as well as
 * delete those user CAs as well as system CAs. The deletion of system
 * CAs is performed by placing an exact copy of that CA in the deleted
 * directory. Such deletions are intended to persist across upgrades
 * but not intended to mask a CA with a matching name or public key
 * but is otherwise reissued in a system update. Reinstalling a
 * deleted system certificate simply removes the copy from the deleted
 * directory, reenabling the original in the system directory.
 *
 * <p>Note that the default mutable directory is created by init via
 * configuration in the system/core/rootdir/init.rc file. The
 * directive "mkdir /data/misc/keychain 0775 system system"
 * ensures that its owner and group are the system uid and system
 * gid and that it is world readable but only writable by the system
 * user.
 */
public final class TrustedCertificateStore {

    /*
     * RoboVM note: This class has been changed to use URIs instead of Files for the certs dirs.
     * If the ANDROID_ROOT and ANDROID_DATA environment variables have been set it will behave
     * as the old version (reading system certs from $ANDROID_ROOT/etc/security/cacerts). If
     * these have not been set system certs will be read from the /cacerts/ folder in the classpath.
     * User certs will be read from ${user.home}/keychain/cacerts-added and removed certs will be
     * stored in ${user.home}/keychain/cacerts-removed.
     */
    
    private static final String PREFIX_SYSTEM = "system:";
    private static final String PREFIX_USER = "user:";

    public static final boolean isSystem(String alias) {
        return alias.startsWith(PREFIX_SYSTEM);
    }
    public static final boolean isUser(String alias) {
        return alias.startsWith(PREFIX_USER);
    }

    private static final URI CA_CERTS_DIR_SYSTEM;
    private static final URI CA_CERTS_DIR_ADDED;
    private static final URI CA_CERTS_DIR_DELETED;
    private static final CertificateFactory CERT_FACTORY;
    static {
        String ANDROID_ROOT = System.getenv("ANDROID_ROOT");
        String ANDROID_DATA = System.getenv("ANDROID_DATA");
        if (ANDROID_ROOT != null && ANDROID_DATA != null) {
            CA_CERTS_DIR_SYSTEM = new File(ANDROID_ROOT + "/etc/security/cacerts").toURI();
            CA_CERTS_DIR_ADDED = new File(ANDROID_DATA + "/misc/keychain/cacerts-added").toURI();
            CA_CERTS_DIR_DELETED = new File(ANDROID_DATA + "/misc/keychain/cacerts-removed").toURI();
        } else {
            try {
                CA_CERTS_DIR_SYSTEM = TrustedCertificateStore.class.getResource("/cacerts/").toURILenient();
            } catch (URISyntaxException e) {
                throw new AssertionError(e);
            }
            String userHome = System.getProperty("user.home");
            CA_CERTS_DIR_ADDED = new File(userHome + "/keychain/cacerts-added").toURI();
            CA_CERTS_DIR_DELETED = new File(userHome + "/keychain/cacerts-removed").toURI();
        }

        try {
            CERT_FACTORY = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            throw new AssertionError(e);
        }
    }

    private final URI systemDir;
    private final URI addedDir;
    private final URI deletedDir;

    public TrustedCertificateStore() {
        this(CA_CERTS_DIR_SYSTEM, CA_CERTS_DIR_ADDED, CA_CERTS_DIR_DELETED);
    }

    public TrustedCertificateStore(File systemDir, File addedDir, File deletedDir) {
        this.systemDir = systemDir.toURI();
        this.addedDir = addedDir.toURI();
        this.deletedDir = deletedDir.toURI();
    }
    
    public TrustedCertificateStore(URI systemDir, URI addedDir, URI deletedDir) {
        this.systemDir = systemDir;
        this.addedDir = addedDir;
        this.deletedDir = deletedDir;
    }

    public Certificate getCertificate(String alias) {
        return getCertificate(alias, false);
    }

    public Certificate getCertificate(String alias, boolean includeDeletedSystem) {

        URI file = fileForAlias(alias);
        if (file == null || (isUser(alias) && isTombstone(file))) {
            return null;
        }
        X509Certificate cert = readCertificate(file);
        if (cert == null || (isSystem(alias)
                             && !includeDeletedSystem
                             && isDeletedSystemCertificate(cert))) {
            // skip malformed certs as well as deleted system ones
            return null;
        }
        return cert;
    }

    private URI fileForAlias(String alias) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        }
        URI file;
        if (isSystem(alias)) {
            file = makeURI(systemDir, alias.substring(PREFIX_SYSTEM.length()));
        } else if (isUser(alias)) {
            file = makeURI(addedDir, alias.substring(PREFIX_USER.length()));
        } else {
            return null;
        }
        if (!exists(file) || isTombstone(file)) {
            // silently elide tombstones
            return null;
        }
        return file;
    }

    private boolean isTombstone(URI file) {
        if ("file".equals(file.getScheme())) {
            return new File(file).length() == 0;
        }
        InputStream in = null;
        try {
            in = file.toURL().openStream();
            return in.read() == -1;
        } catch (IOException e) {
        } finally {
            IoUtils.closeQuietly(in);
        }
        return false;
    }

    private boolean isFile(URI file) {
        if ("file".equals(file.getScheme())) {
            return new File(file).isFile();
        }
        if ("jar".equals(file.getScheme())) {
            try {
                JarURLConnection conn = (JarURLConnection) file.toURL().openConnection();
                return conn.getJarEntry() != null && !conn.getJarEntry().getName().endsWith("/");
            } catch (IOException e) {}
        }
        return false;
    }
    
    private boolean exists(URI file) {
        if ("file".equals(file.getScheme())) {
            return new File(file).exists();
        }
        if ("jar".equals(file.getScheme())) {
            try {
                JarURLConnection conn = (JarURLConnection) file.toURL().openConnection();
                return conn.getJarEntry() != null;
            } catch (IOException e) {}
        }
        return false;
    }
    
    private String getName(URI file) {
        if ("jar".equals(file.getScheme())) {
            // For jar: URIs getPath() returns null
            String uriStr = file.getSchemeSpecificPart();
            return uriStr.substring(uriStr.lastIndexOf('/') + 1);
        }
        return file.getPath().substring(file.getPath().lastIndexOf('/') + 1);
    }
    
    private String[] list(URI file) {
        if ("file".equals(file.getScheme())) {
            return new File(file).list();
        }
        if ("jar".equals(file.getScheme())) {
            try {
                JarURLConnection conn = (JarURLConnection) file.toURL().openConnection();
                JarFile jarFile = conn.getJarFile();
                String uriStr = file.toString();
                if (!uriStr.endsWith("/")) {
                    uriStr += "/";
                }
                String path = uriStr.substring(uriStr.lastIndexOf('!') + 1);
                if (path.startsWith("/")) {
                    path = path.substring(1);
                }
                Enumeration<JarEntry> en = jarFile.entries();
                List<String> result = new ArrayList<String>();
                while (en.hasMoreElements()) {
                    JarEntry entry = en.nextElement();
                    String name = entry.getName();
                    if (name.startsWith(path) && !name.endsWith("/")) {
                        int lastSlash = name.lastIndexOf('/');
                        if (lastSlash == path.length() - 1) {
                            result.add(name.substring(lastSlash + 1));
                        }
                    }
                }
                return result.toArray(new String[result.size()]);
            } catch (IOException e) {
            }
        }
        return null;
    }
    
    private long lastModified(URI file) {
        if ("file".equals(file.getScheme())) {
            return new File(file).lastModified();
        }
        if ("jar".equals(file.getScheme())) {
            try {
                JarURLConnection conn = (JarURLConnection) file.toURL().openConnection();
                return conn.getJarEntry().getTime();
            } catch (IOException e) {
            }
        }
        return 0L;
    }
    
    private X509Certificate readCertificate(URI file) {
        if (!isFile(file)) {
            return null;
        }
        InputStream is = null;
        try {
            if ("file".equals(file.getScheme())) {
                is = new BufferedInputStream(new FileInputStream(new File(file)));
            } else {
                is = file.toURL().openStream();
            }
            return (X509Certificate) CERT_FACTORY.generateCertificate(is);
        } catch (IOException e) {
            return null;
        } catch (CertificateException e) {
            // reading a cert while its being installed can lead to this.
            // just pretend like its not available yet.
            return null;
        } finally {
            IoUtils.closeQuietly(is);
        }
    }

    private void writeCertificate(URI uri, X509Certificate cert)
            throws IOException, CertificateException {
        File file = new File(uri);
        File dir = file.getParentFile();
        dir.mkdirs();
        dir.setReadable(true, false);
        dir.setExecutable(true, false);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(cert.getEncoded());
        } finally {
            IoUtils.closeQuietly(os);
        }
        file.setReadable(true, false);
    }

    private boolean isDeletedSystemCertificate(X509Certificate x) {
        return exists(getCertificateFile(deletedDir, x));
    }

    public Date getCreationDate(String alias) {
        // containsAlias check ensures the later fileForAlias result
        // was not a deleted system cert.
        if (!containsAlias(alias)) {
            return null;
        }
        URI file = fileForAlias(alias);
        if (file == null) {
            return null;
        }
        long time = lastModified(file);
        if (time == 0) {
            return null;
        }
        return new Date(time);
    }

    public Set<String> aliases() {
        Set<String> result = new HashSet<String>();
        addAliases(result, PREFIX_USER, addedDir);
        addAliases(result, PREFIX_SYSTEM, systemDir);
        return result;
    }

    public Set<String> userAliases() {
        Set<String> result = new HashSet<String>();
        addAliases(result, PREFIX_USER, addedDir);
        return result;
    }

    private void addAliases(Set<String> result, String prefix, URI dir) {
        String[] files = list(dir);
        if (files == null) {
            return;
        }
        for (String filename : files) {
            String alias = prefix + filename;
            if (containsAlias(alias)) {
                result.add(alias);
            }
        }
    }

    public Set<String> allSystemAliases() {
        Set<String> result = new HashSet<String>();
        String[] files = list(systemDir);
        if (files == null) {
            return result;
        }
        for (String filename : files) {
            String alias = PREFIX_SYSTEM + filename;
            if (containsAlias(alias, true)) {
                result.add(alias);
            }
        }
        return result;
    }

    public boolean containsAlias(String alias) {
        return containsAlias(alias, false);
    }

    private boolean containsAlias(String alias, boolean includeDeletedSystem) {
        return getCertificate(alias, includeDeletedSystem) != null;
    }

    public String getCertificateAlias(Certificate c) {
        if (c == null || !(c instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x = (X509Certificate) c;
        URI user = getCertificateFile(addedDir, x);
        if (exists(user)) {
            return PREFIX_USER + getName(user);
        }
        if (isDeletedSystemCertificate(x)) {
            return null;
        }
        URI system = getCertificateFile(systemDir, x);
        if (exists(system)) {
            return PREFIX_SYSTEM + getName(system);
        }
        return null;
    }

    /**
     * Returns true to indicate that the certificate was added by the
     * user, false otherwise.
     */
    public boolean isUserAddedCertificate(X509Certificate cert) {
        return exists(getCertificateFile(addedDir, cert));
    }

    /**
     * Returns a File for where the certificate is found if it exists
     * or where it should be installed if it does not exist. The
     * caller can disambiguate these cases by calling {@code
     * File.exists()} on the result.
     */
    private URI getCertificateFile(URI dir, final X509Certificate x) {
        // compare X509Certificate.getEncoded values
        CertSelector selector = new CertSelector() {
            @Override public boolean match(X509Certificate cert) {
                return cert.equals(x);
            }
        };
        return findCert(dir, x.getSubjectX500Principal(), selector, URI.class);
    }

    /**
     * This non-{@code KeyStoreSpi} public interface is used by {@code
     * TrustManagerImpl} to locate a CA certificate with the same name
     * and public key as the provided {@code X509Certificate}. We
     * match on the name and public key and not the entire certificate
     * since a CA may be reissued with the same name and PublicKey but
     * with other differences (for example when switching signature
     * from md2WithRSAEncryption to SHA1withRSA)
     */
    public boolean isTrustAnchor(final X509Certificate c) {
        // compare X509Certificate.getPublicKey values
        CertSelector selector = new CertSelector() {
            @Override public boolean match(X509Certificate ca) {
                return ca.getPublicKey().equals(c.getPublicKey());
            }
        };
        boolean user = findCert(addedDir,
                                c.getSubjectX500Principal(),
                                selector,
                                Boolean.class);
        if (user) {
            return true;
        }
        X509Certificate system = findCert(systemDir,
                                          c.getSubjectX500Principal(),
                                          selector,
                                          X509Certificate.class);
        return system != null && !isDeletedSystemCertificate(system);
    }

    /**
     * This non-{@code KeyStoreSpi} public interface is used by {@code
     * TrustManagerImpl} to locate the CA certificate that signed the
     * provided {@code X509Certificate}.
     */
    public X509Certificate findIssuer(final X509Certificate c) {
        // match on verified issuer of Certificate
        CertSelector selector = new CertSelector() {
            @Override public boolean match(X509Certificate ca) {
                try {
                    c.verify(ca.getPublicKey());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
        X500Principal issuer = c.getIssuerX500Principal();
        X509Certificate user = findCert(addedDir, issuer, selector, X509Certificate.class);
        if (user != null) {
            return user;
        }
        X509Certificate system = findCert(systemDir, issuer, selector, X509Certificate.class);
        if (system != null && !isDeletedSystemCertificate(system)) {
            return system;
        }
        return null;
    }

    private static boolean isSelfIssuedCertificate(OpenSSLX509Certificate cert) {
        final long ctx = cert.getContext();
        return NativeCrypto.X509_check_issued(ctx, ctx) == 0;
    }

    /**
     * Converts the {@code cert} to the internal OpenSSL X.509 format so we can
     * run {@link NativeCrypto} methods on it.
     */
    private static OpenSSLX509Certificate convertToOpenSSLIfNeeded(X509Certificate cert)
            throws CertificateException {
        if (cert == null) {
            return null;
        }

        if (cert instanceof OpenSSLX509Certificate) {
            return (OpenSSLX509Certificate) cert;
        }

        try {
            return OpenSSLX509Certificate.fromX509Der(cert.getEncoded());
        } catch (Exception e) {
            throw new CertificateException(e);
        }
    }

    /**
     * Attempt to build a certificate chain from the supplied {@code leaf}
     * argument through the chain of issuers as high up as known. If the chain
     * can't be completed, the most complete chain available will be returned.
     * This means that a list with only the {@code leaf} certificate is returned
     * if no issuer certificates could be found.
     *
     * @throws CertificateException if there was a problem parsing the
     *             certificates
     */
    public List<X509Certificate> getCertificateChain(X509Certificate leaf)
            throws CertificateException {
        final List<OpenSSLX509Certificate> chain = new ArrayList<OpenSSLX509Certificate>();
        chain.add(convertToOpenSSLIfNeeded(leaf));

        for (int i = 0; true; i++) {
            OpenSSLX509Certificate cert = chain.get(i);
            if (isSelfIssuedCertificate(cert)) {
                break;
            }
            OpenSSLX509Certificate issuer = convertToOpenSSLIfNeeded(findIssuer(cert));
            if (issuer == null) {
                break;
            }
            chain.add(issuer);
        }

        return new ArrayList<X509Certificate>(chain);
    }

    // like java.security.cert.CertSelector but with X509Certificate and without cloning
    private static interface CertSelector {
        public boolean match(X509Certificate cert);
    }

    private <T> T findCert(
            URI dir, X500Principal subject, CertSelector selector, Class<T> desiredReturnType) {

        String hash = hash(subject);
        for (int index = 0; true; index++) {
            URI file = file(dir, hash, index);
            if (!isFile(file)) {
                // could not find a match, no file exists, bail
                if (desiredReturnType == Boolean.class) {
                    return (T) Boolean.FALSE;
                }
                if (desiredReturnType == URI.class) {
                    // we return file so that caller that wants to
                    // write knows what the next available has
                    // location is
                    return (T) file;
                }
                return null;
            }
            if (isTombstone(file)) {
                continue;
            }
            X509Certificate cert = readCertificate(file);
            if (cert == null) {
                // skip problem certificates
                continue;
            }
            if (selector.match(cert)) {
                if (desiredReturnType == X509Certificate.class) {
                    return (T) cert;
                }
                if (desiredReturnType == Boolean.class) {
                    return (T) Boolean.TRUE;
                }
                if (desiredReturnType == URI.class) {
                    return (T) file;
                }
                throw new AssertionError();
            }
        }
    }

    private String hash(X500Principal name) {
        int hash = NativeCrypto.X509_NAME_hash_old(name);
        return IntegralToString.intToHexString(hash, false, 8);
    }

    private URI makeURI(URI base, String file) {
        StringBuilder sb = new StringBuilder(base.toString());
        if (sb.charAt(sb.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(file);
        try {
            return new URI(sb.toString());
        } catch (URISyntaxException e) {
            throw new AssertionError(e);
        }
    }
    
    private URI file(URI dir, String hash, int index) {
        return makeURI(dir, hash + "." + index);
    }

    /**
     * This non-{@code KeyStoreSpi} public interface is used by the
     * {@code KeyChainService} to install new CA certificates. It
     * silently ignores the certificate if it already exists in the
     * store.
     */
    public void installCertificate(X509Certificate cert) throws IOException, CertificateException {
        if (cert == null) {
            throw new NullPointerException("cert == null");
        }
        URI system = getCertificateFile(systemDir, cert);
        if (exists(system)) {
            URI deleted = getCertificateFile(deletedDir, cert);
            if (exists(deleted)) {
                // we have a system cert that was marked deleted.
                // remove the deleted marker to expose the original
                if (!"file".equals(deleted.getScheme()) || !new File(deleted).delete()) {
                    throw new IOException("Could not remove " + deleted);
                }
                return;
            }
            // otherwise we just have a dup of an existing system cert.
            // return taking no further action.
            return;
        }
        URI user = getCertificateFile(addedDir, cert);
        if (exists(user)) {
            // we have an already installed user cert, bail.
            return;
        }
        // install the user cert
        writeCertificate(user, cert);
    }

    /**
     * This could be considered the implementation of {@code
     * TrustedCertificateKeyStoreSpi.engineDeleteEntry} but we
     * consider {@code TrustedCertificateKeyStoreSpi} to be read
     * only. Instead, this is used by the {@code KeyChainService} to
     * delete CA certificates.
     */
    public void deleteCertificateEntry(String alias) throws IOException, CertificateException {
        if (alias == null) {
            return;
        }
        URI file = fileForAlias(alias);
        if (file == null) {
            return;
        }
        if (isSystem(alias)) {
            X509Certificate cert = readCertificate(file);
            if (cert == null) {
                // skip problem certificates
                return;
            }
            URI deleted = getCertificateFile(deletedDir, cert);
            if (exists(deleted)) {
                // already deleted system certificate
                return;
            }
            // write copy of system cert to marked as deleted
            writeCertificate(deleted, cert);
            return;
        }
        if (isUser(alias)) {
            // truncate the file to make a tombstone by opening and closing.
            // we need ensure that we don't leave a gap before a valid cert.
            new FileOutputStream(new File(file)).close();
            removeUnnecessaryTombstones(alias);
            return;
        }
        // non-existant user cert, nothing to delete
    }

    private void removeUnnecessaryTombstones(String alias) throws IOException {
        if (!isUser(alias)) {
            throw new AssertionError(alias);
        }
        int dotIndex = alias.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new AssertionError(alias);
        }

        String hash = alias.substring(PREFIX_USER.length(), dotIndex);
        int lastTombstoneIndex = Integer.parseInt(alias.substring(dotIndex + 1));

        if (exists(file(addedDir, hash, lastTombstoneIndex + 1))) {
            return;
        }
        while (lastTombstoneIndex >= 0) {
            URI file = file(addedDir, hash, lastTombstoneIndex);
            if (!isTombstone(file)) {
                break;
            }
            if (!"file".equals(file.getScheme()) || !new File(file).delete()) {
                throw new IOException("Could not remove " + file);
            }
            lastTombstoneIndex--;
        }
    }
}
