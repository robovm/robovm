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

package java.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OptionalDataException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.SocketPermission;
import java.net.URL;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * {@code CodeSource} encapsulates the location from where code is loaded and
 * the certificates that were used to verify that code. This information is used
 * by {@code SecureClassLoader} to define protection domains for loaded classes.
 *
 * @see SecureClassLoader
 * @see ProtectionDomain
 */
public class CodeSource implements Serializable {

    private static final long serialVersionUID = 4977541819976013951L;

    // Location of this CodeSource object
    private URL location;

    // Array of certificates assigned to this CodeSource object
    private transient java.security.cert.Certificate[] certs;

    // Array of CodeSigners
    private transient CodeSigner[] signers;

    // SocketPermission() in implies() method takes to many time.
    // Need to cache it for better performance.
    private transient SocketPermission sp;

    // Cached factory used to build CertPath-s in <code>getCodeSigners()</code>.  
    private transient CertificateFactory factory;

    /**
     * Constructs a new instance of {@code CodeSource} with the specified
     * {@code URL} and the {@code Certificate}s.
     *
     * @param location
     *            the {@code URL} representing the location from where code is
     *            loaded, maybe {@code null}.
     * @param certs
     *            the {@code Certificate} used to verify the code, loaded from
     *            the specified {@code location}, maybe {@code null}.
     */
    public CodeSource(URL location, Certificate[] certs) {
        this.location = location;
        if (certs != null) {
            this.certs = new Certificate[certs.length];
            System.arraycopy(certs, 0, this.certs, 0, certs.length);
        }
    }

    /**
     * Constructs a new instance of {@code CodeSource} with the specified
     * {@code URL} and the {@code CodeSigner}s.
     *
     * @param location
     *            the {@code URL} representing the location from where code is
     *            loaded, maybe {@code null}.
     * @param signers
     *            the {@code CodeSigner}s of the code, loaded from the specified
     *            {@code location}. Maybe {@code null}.
     */
    public CodeSource(URL location, CodeSigner[] signers) {
        this.location = location;
        if (signers != null) {
            this.signers = new CodeSigner[signers.length];
            System.arraycopy(signers, 0, this.signers, 0, signers.length);
        }
    }

    /**
     * Compares the specified object with this {@code CodeSource} for equality.
     * Returns {@code true} if the specified object is also an instance of
     * {@code CodeSource}, points to the same {@code URL} location and the two
     * code sources encapsulate the same {@code Certificate}s. The order of the
     * {@code Certificate}s is ignored by this method.
     *
     * @param obj
     *            object to be compared for equality with this {@code
     *            CodeSource}.
     * @return {@code true} if the specified object is equal to this {@code
     *         CodeSource}, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof CodeSource)) {
            return false;
        }

        CodeSource that = (CodeSource) obj;

        if (this.location != null) {
            if (that.location == null) {
                return false;
            }
            if (!this.location.equals(that.location)) {
                return false;
            }
        } else if (that.location != null) {
            return false;
        }

        // do not use this.certs, as we also need to take care about 
        // CodeSigners' certificates
        Certificate[] thizCerts = getCertificatesNoClone();
        Certificate[] thatCerts = that.getCertificatesNoClone();
        if (!PolicyUtils.matchSubset(thizCerts, thatCerts)) {
            return false;
        }
        if (!PolicyUtils.matchSubset(thatCerts, thizCerts)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the certificates of this {@code CodeSource}. If the
     * {@link #CodeSource(URL, CodeSigner[])} constructor was used to create
     * this instance, the certificates are obtained from the supplied signers.
     * <p>
     * External modifications of the returned {@code Certificate[]} has no
     * impact on this {@code CodeSource}.
     *
     * @return the certificates of this {@code CodeSource} or {@code null} if
     *         there is none.
     */
    public final Certificate[] getCertificates() {
        getCertificatesNoClone();
        if (certs == null) {
            return null;
        }
        Certificate[] tmp = new Certificate[certs.length];
        System.arraycopy(certs, 0, tmp, 0, certs.length);
        return tmp;
    }

    // Acts exactly as {@link #getCertificates()} does, but does not clone the 
    // array before returning (and returns reference to <code>this.certs</code>
    // if this array is not null).<br>
    // @return a reference to the certificates array, or null if there are no 
    // certificates associated.  
    private Certificate[] getCertificatesNoClone() {
        if (certs != null) {
            return certs;
        }

        if (signers == null) {
            return null;
        }
        // Extract Certificates from the CodeSigner-s
        ArrayList<Certificate> v = new ArrayList<Certificate>();
        for (int i = 0; i < signers.length; i++) {
            v.addAll(signers[i].getSignerCertPath().getCertificates());
        }

        certs = v.toArray(new Certificate[v.size()]);
        return certs;
    }

    /**
     * Returns the {@code CodeSigner}s of this {@code CodeSource}. If the
     * {@link #CodeSource(URL, Certificate[])} constructor was used to create
     * this instance, the signers are obtained from the supplied certificates.
     * Only X.509 certificates are analyzed.
     *
     * @return the signers of this {@code CodeSource}, or {@code null} if there
     *         is none.
     */
    public final CodeSigner[] getCodeSigners() {
        if (signers != null) {
            CodeSigner[] tmp = new CodeSigner[signers.length];
            System.arraycopy(signers, 0, tmp, 0, tmp.length);
            return tmp;
        }
        if(certs == null || factory != null){
            // factory != null means we've done this exercise already.
            return null;
        }

        X500Principal prevIssuer = null;
        ArrayList<Certificate> list = new ArrayList<Certificate>(certs.length);
        ArrayList<CodeSigner> asigners = new ArrayList<CodeSigner>();

        // The presumption is that the chains of certificates are placed
        // according to the CertPath agreement:
        //
        // the lowest certs first; the CAs are at the last
        //
        // So the following loop scans trough the certs and checks
        // that every next certificate is an Issuer of the previous one. 
        // Any certificate that is not an Issuer of the previous one starts a 
        // new chain (== a new CertPath) 

        for (int i = 0; i < certs.length; i++) {
            if (!(certs[i] instanceof X509Certificate)) {
                // Only X509Certificate-s are taken into account - see API spec.
                continue;
            }
            X509Certificate x509 = (X509Certificate) certs[i];
            if (prevIssuer == null) {
                // start a very first chain
                prevIssuer = x509.getIssuerX500Principal();
                list.add(x509);
            } else {
                X500Principal subj = x509.getSubjectX500Principal();
                if (!prevIssuer.equals(subj)) {
                    // Ok, this ends the previous chain, 
                    // so transform this one into CertPath ...
                    CertPath cpath = makeCertPath(list);
                    if (cpath != null) {
                        asigners.add(new CodeSigner(cpath, null));
                    }
                    // ... and start a new one
                    list.clear();
                }// else { it's still the same chain }
                prevIssuer = x509.getSubjectX500Principal();
                list.add(x509);
            }
        }
        if (!list.isEmpty()) {
            CertPath cpath = makeCertPath(list);
            if (cpath != null) {
                asigners.add(new CodeSigner(cpath, null));
            }
        }
        if (asigners.isEmpty()) {
            // 'signers' is 'null' already
            return null;
        }
        signers = new CodeSigner[asigners.size()];
        asigners.toArray(signers);
        CodeSigner[] tmp = new CodeSigner[asigners.size()];
        System.arraycopy(signers, 0, tmp, 0, tmp.length);
        return tmp;
    }

    // Makes an CertPath from a given List of X509Certificate-s. 
    // @param list
    // @return CertPath, or null if CertPath cannot be made  
    private CertPath makeCertPath(List<? extends Certificate> list) {
        if (factory == null) {
            try {
                factory = CertificateFactory.getInstance("X.509"); //$NON-NLS-1$
            } catch (CertificateException ex) {
                //? throw new Error("X.509 is a 'must be'", ex);
                return null;
            }
        }
        try {
            return factory.generateCertPath(list);
        } catch (CertificateException ex) {
            // ignore(ex)
        }
        return null;
    }

    /**
     * Returns the location of this {@code CodeSource}.
     *
     * @return the location of this {@code CodeSource}, maybe {@code null}.
     */
    public final URL getLocation() {
        return location;
    }

    /**
     * Returns the hash code value for this {@code CodeSource}.
     * Returns the same hash code for {@code CodeSource}s that are
     * equal to each other as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this {@code CodeSource}.
     * @see Object#equals(Object)
     * @see CodeSource#equals(Object)
     */
    @Override
    public int hashCode() {
        //
        // hashCode() is undocumented there. Should we also use certs[i] to
        // compute the hash ?
        // for now, I don't take certs[] into account
        return location == null ? 0 : location.hashCode();
    }

    /**
     * Indicates whether the specified code source is implied by this {@code
     * CodeSource}. Returns {@code true} if all of the following conditions are
     * {@code true}, otherwise {@code false}:
     * <p>
     * <ul>
     * <li>{@code cs} is not {@code null}
     * <li>if this {@code CodeSource} has associated certificates, all
     * certificates are present in {@code cs}. The certificates are extracted
     * from the signers if signers are present.
     * <li>if this {@code CodeSource}'s location is not {@code null}, the
     * following conditions are checked
     * <ul>
     * <li>this {@code CodeSource}'s location is not {@code null}
     * <li>this {@code CodeSource}'s location protocol is equal to {@code cs}'s
     * location protocol
     * <li>if this {@code CodeSource}'s location host is not {@code null}, the
     * following conditions are checked
     * <ul>
     * <li>{@code cs}'s host is not {@code null}
     * <li>the {@link SocketPermission} of this {@code CodeSource}'s location
     * host implies the {@code SocketPermission} of {@code cs}'s location host
     * </ul>
     * <li>if this {@code CodeSource}'s location port != -1 the port of {@code
     * cs}'s location is equal to this {@code CodeSource}'s location port
     * <li>this {@code CodeSource}'s location file matches {@code cs}'s file
     * whereas special wildcard matching applies as described below
     * <li>this {@code CodeSource}'s location reference is equal to to {@code
     * cs}'s location reference
     * </ul>
     * </ul>
     * <p>
     * Note: If this {@code CodeSource} has a {@code null} location and not any
     * certificates, this method returns {@code true}.
     * <p>
     * Matching rules for the {@code CodeSource}'s location file:
     * <ul>
     * <li>if this {@code CodeSource}'s location file ends with {@code "/-"},
     * then {@code cs}'s file must start with {@code CodeSource}'s location file
     * (exclusive the trailing '-')
     * <li>if this {@code CodeSource}'s location file ends with {@code "/*"},
     * then {@code cs}'s file must start with {@code CodeSource}'s location file
     * (exclusive the trailing '*') and must not have any further '/'
     * <li>if this {@code CodeSource}'s location file ends with {@code "/"},
     * then {@code cs}'s file must start with {@code CodeSource}'s location file
     * <li>if this {@code CodeSource}'s location file does not end with {@code
     * "/"}, then {@code cs}'s file must start with {@code CodeSource}'s
     * location file with the '/' appended to it.
     * </ul>
     * Examples for locations that imply the location
     * "http://harmony.apache.org/milestones/M9/apache-harmony.jar":
     *
     * <pre>
     * http:
     * http://&#42;/milestones/M9/*
     * http://*.apache.org/milestones/M9/*
     * http://harmony.apache.org/milestones/-
     * http://harmony.apache.org/milestones/M9/apache-harmony.jar
     * </pre>
     *
     * @param cs
     *            the code source to check.
     * @return {@code true} if the argument code source is implied by this
     *         {@code CodeSource}, otherwise {@code false}.
     */
    public boolean implies(CodeSource cs) {
        //
        // Here, javadoc:N refers to the appropriate item in the API spec for 
        // the CodeSource.implies()
        // The info was taken from the 1.5 final API spec

        // javadoc:1
        if (cs == null) {
            return false;
        }

        // javadoc:2
        // with a comment: the javadoc says only about certificates and does 
        // not explicitly mention CodeSigners' certs.
        // It seems more convenient to use getCerts() to get the real 
        // certificates - with a certificates got form the signers
        Certificate[] thizCerts = getCertificatesNoClone();
        if (thizCerts != null) {
            Certificate[] thatCerts = cs.getCertificatesNoClone();
            if (thatCerts == null
                    || !PolicyUtils.matchSubset(thizCerts, thatCerts)) {
                return false;
            }
        }

        // javadoc:3
        if (this.location != null) {
            //javadoc:3.1
            if (cs.location == null) {
                return false;
            }
            //javadoc:3.2
            if (this.location.equals(cs.location)) {
                return true;
            }
            //javadoc:3.3
            if (!this.location.getProtocol().equals(cs.location.getProtocol())) {
                return false;
            }
            //javadoc:3.4
            String thisHost = this.location.getHost();
            if (thisHost != null) {
                String thatHost = cs.location.getHost();
                if (thatHost == null) {
                    return false;
                }

                // 1. According to the spec, an empty string will be considered 
                // as "localhost" in the SocketPermission
                // 2. 'file://' URLs will have an empty getHost()
                // so, let's make a special processing of localhost-s, I do 
                // believe this'll improve performance of file:// code sources 

                //
                // Don't have to evaluate both the boolean-s each time.
                // It's better to evaluate them directly under if() statement.
                // 
                // boolean thisIsLocalHost = thisHost.length() == 0 || "localhost".equals(thisHost);
                // boolean thatIsLocalHost = thatHost.length() == 0 || "localhost".equals(thatHost);
                // 
                // if( !(thisIsLocalHost && thatIsLocalHost) &&
                // !thisHost.equals(thatHost)) {

                if (!((thisHost.length() == 0 || "localhost".equals(thisHost)) && (thatHost //$NON-NLS-1$
                        .length() == 0 || "localhost".equals(thatHost))) //$NON-NLS-1$
                        && !thisHost.equals(thatHost)) {

                    // Obvious, but very slow way....
                    // 
                    // SocketPermission thisPerm = new SocketPermission(
                    //          this.location.getHost(), "resolve");
                    // SocketPermission thatPerm = new SocketPermission(
                    //          cs.location.getHost(), "resolve");
                    // if (!thisPerm.implies(thatPerm)) { 
                    //      return false;
                    // }
                    //
                    // let's cache it: 

                    if (this.sp == null) {
                        this.sp = new SocketPermission(thisHost, "resolve"); //$NON-NLS-1$
                    }

                    if (cs.sp == null) {
                        cs.sp = new SocketPermission(thatHost, "resolve"); //$NON-NLS-1$
                    }

                    if (!this.sp.implies(cs.sp)) {
                        return false;
                    }
                } // if( ! this.location.getHost().equals(cs.location.getHost())
            } // if (this.location.getHost() != null)

            //javadoc:3.5
            if (this.location.getPort() != -1) {
                if (this.location.getPort() != cs.location.getPort()) {
                    return false;
                }
            }

            //javadoc:3.6
            String thisFile = this.location.getFile();
            String thatFile = cs.location.getFile();

            if (thisFile.endsWith("/-")) { //javadoc:3.6."/-" //$NON-NLS-1$
                if (!thatFile.startsWith(thisFile.substring(0, thisFile
                        .length() - 2))) {
                    return false;
                }
            } else if (thisFile.endsWith("/*")) { //javadoc:3.6."/*" //$NON-NLS-1$
                if (!thatFile.startsWith(thisFile.substring(0, thisFile
                        .length() - 2))) {
                    return false;
                }
                // no further separators(s) allowed
                if (thatFile.indexOf("/", thisFile.length() - 1) != -1) { //$NON-NLS-1$
                    return false;
                }
            } else {
                // javadoc:3.6."/"
                if (!thisFile.equals(thatFile)) {
                    if (!thisFile.endsWith("/")) { //$NON-NLS-1$
                        if (!thatFile.equals(thisFile + "/")) { //$NON-NLS-1$
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            //javadoc:3.7
            if (this.location.getRef() != null) {
                if (!this.location.getRef().equals(cs.location.getRef())) {
                    return false;
                }
            }
            // ok, every check was made, and they all were successful. 
            // it's ok to return true.
        } // if this.location != null

        // javadoc: a note about CodeSource with null location and null Certs 
        // is applicable here 
        return true;
    }

    /**
     * Returns a string containing a concise, human-readable description of the
     * this {@code CodeSource} including its location, its certificates and its
     * signers.
     *
     * @return a printable representation for this {@code CodeSource}.
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("CodeSource, url="); //$NON-NLS-1$
        buf.append(location == null ? "<null>" : location.toString()); //$NON-NLS-1$

        if (certs == null) {
            buf.append(", <no certificates>"); //$NON-NLS-1$
        } else {
            buf.append("\nCertificates [\n"); //$NON-NLS-1$
            for (int i = 0; i < certs.length; i++) {
                buf.append(i + 1).append(") ").append(certs[i]).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            buf.append("]\n"); //$NON-NLS-1$
        }
        if (signers != null) {
            buf.append("\nCodeSigners [\n"); //$NON-NLS-1$
            for (int i = 0; i < signers.length; i++) {
                buf.append(i + 1).append(") ").append(signers[i]).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            buf.append("]\n"); //$NON-NLS-1$
        }
        return buf.toString();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {

        oos.defaultWriteObject();

        if (certs == null || certs.length == 0) {
            oos.writeInt(0);
        } else {
            oos.writeInt(certs.length);
            for (int i = 0; i < certs.length; i++) {
                try {
                    oos.writeUTF(certs[i].getType());
                    byte[] data = certs[i].getEncoded();
                    // hope there are no certificates with 'data==null'
                    oos.writeInt(data.length);
                    oos.write(data);
                } catch (CertificateEncodingException ex) {
                    throw (IOException) new IOException(
                            Messages.getString("security.18")).initCause(ex); //$NON-NLS-1$
                }
            }
        }
        if (signers != null && signers.length != 0) {
            oos.writeObject(signers);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        
        ois.defaultReadObject();
        
        int certsCount = ois.readInt();
        certs = null;
        if (certsCount != 0) {
            certs = new Certificate[certsCount];
            for (int i = 0; i < certsCount; i++) {
                String type = ois.readUTF();
                CertificateFactory factory;
                try {
                    factory = CertificateFactory.getInstance(type);
                } catch (CertificateException ex) {
                    throw new ClassNotFoundException(
                            Messages.getString("security.19", type), //$NON-NLS-1$
                            ex);
                }
                int dataLen = ois.readInt();
                byte[] data = new byte[dataLen];
                ois.readFully(data);
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                try {
                    certs[i] = factory.generateCertificate(bais);
                } catch (CertificateException ex) {
                    throw (IOException) new IOException(
                            Messages.getString("security.1A")).initCause(ex); //$NON-NLS-1$
                }
            }
        }
        try {
            signers = (CodeSigner[]) ois.readObject();
        } catch (OptionalDataException ex) {
            if (!ex.eof) {
                throw ex;
            }
            // no signers (ex.eof==true <= no data left) is allowed
        }
    }
}
