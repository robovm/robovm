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

package java.security.cert;

import java.io.IOException;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.utils.Array;
import org.apache.harmony.security.x509.NameConstraints;



/**
 * This class represents a trust anchor for validation of X.509 certification
 * path.
 * <p>
 * It is a <i>trusted</i> certificate authority (CA) and includes the public key
 * of the CA, the CA's name and the constraints for the validation of
 * certification paths. The constructor also allows to specify a binary
 * representation of a so called "Name Constraints" extension as a byte array.
 */
public class TrustAnchor {
    // Most trusted CA as a X500Principal
    private final X500Principal caPrincipal;
    // Most trusted CA name
    private final String caName;
    // Most trusted CA public key
    private final PublicKey caPublicKey;
    // Most trusted CA certificate
    private final X509Certificate trustedCert;
    // Name constraints extension
    private final byte[] nameConstraints;

    /**
     * Creates a new {@code TrustAnchor} with the specified certificate and name
     * constraints.
     * <p>
     * The name constraints will be used as additional constraints during the
     * validation of certification paths.
     *
     * @param trustedCert
     *            the trusted certificate
     * @param nameConstraints
     *            the ASN.1 DER encoded form of the name constraints or {@code
     *            null} if none.
     * @throws IllegalArgumentException
     *             if the decoding of the name constraints fail.
     */
    public TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) {
        if (trustedCert == null) {
            throw new NullPointerException("trustedCert == null");
        }
        this.trustedCert = trustedCert;
        // copy nameConstraints if not null
        if (nameConstraints != null) {
            this.nameConstraints = new byte[nameConstraints.length];
            System.arraycopy(nameConstraints, 0,
                    this.nameConstraints, 0, this.nameConstraints.length);
            processNameConstraints();
        } else {
            this.nameConstraints = null;
        }
        this.caName = null;
        this.caPrincipal = null;
        this.caPublicKey = null;
    }

    /**
     * Creates a new {@code TrustAnchor} with the specified certificate
     * authority name, its public key and the specified name constraints.
     * <p>
     * The name constraints will be used as additional constraints during the
     * validation of certification paths.
     *
     * @param caName
     *            the X.500 name of the certificate authority in RFC 2253
     *            {@code String} format.
     * @param caPublicKey
     *            the public key of the certificate authority
     * @param nameConstraints
     *            the ASN.1 DER encoded form of the name constraints or {@code
     *            null} if none.
     * @throws IllegalArgumentException
     *             if the {@code caName} is empty or if decoding of the name
     *             constraints fail.
     */
    public TrustAnchor(String caName, PublicKey caPublicKey,
            byte[] nameConstraints) {
        if (caName == null) {
            throw new NullPointerException("caName == null");
        }
        this.caName = caName;
        if (caPublicKey == null) {
            throw new NullPointerException("caPublicKey == null");
        }
        this.caPublicKey = caPublicKey;
        // copy nameConstraints if not null
        if (nameConstraints != null) {
            this.nameConstraints = new byte[nameConstraints.length];
            System.arraycopy(nameConstraints, 0,
                    this.nameConstraints, 0, this.nameConstraints.length);
            processNameConstraints();
        } else {
            this.nameConstraints = null;
        }

        this.trustedCert = null;

        // X500Principal checks caName validity
        if (caName.isEmpty()) {
            throw new IllegalArgumentException("caName.isEmpty()");
        }
        this.caPrincipal = new X500Principal(this.caName);
    }

    /**
     * Creates a new {@code TrustAnchor} with the specified certificate
     * authority name as principal, its public key and the specified name
     * constraints.
     * <p>
     * The name constraints will be used as additional constraints during the
     * validation of certification paths.
     *
     * @param caPrincipal
     *            the name of the certificate authority as X500 principal.
     * @param caPublicKey
     *            the public key of the certificate authority.
     * @param nameConstraints
     *            the ASN.1 DER encoded form of the name constraints or {@code
     *            null} if none.
     * @throws IllegalArgumentException
     *             if decoding of the name constraints fail.
     */
    public TrustAnchor(X500Principal caPrincipal,
            PublicKey caPublicKey, byte[] nameConstraints) {
        if (caPrincipal == null) {
            throw new NullPointerException("caPrincipal == null");
        }
        this.caPrincipal = caPrincipal;
        if (caPublicKey == null) {
            throw new NullPointerException("caPublicKey == null");
        }
        this.caPublicKey = caPublicKey;
        // copy nameConstraints if not null
        if (nameConstraints != null) {
            this.nameConstraints = new byte[nameConstraints.length];
            System.arraycopy(nameConstraints, 0,
                    this.nameConstraints, 0, this.nameConstraints.length);
            processNameConstraints();
        } else {
            this.nameConstraints = null;
        }

        this.trustedCert = null;
        this.caName = caPrincipal.getName();
    }

    /**
     * Returns a copy of the name constraints in ASN.1 DER encoded form.
     *
     * @return a copy of the name constraints in ASN.1 DER encoded form.
     */
    public final byte[] getNameConstraints() {
        if (nameConstraints == null) {
            return null;
        }
        byte[] ret = new byte[nameConstraints.length];
            System.arraycopy(nameConstraints, 0,
                    ret, 0, nameConstraints.length);
        return ret;
    }

    /**
     * Returns the certificate of this <i>trusted</i> certificate authority.
     *
     * @return the certificate of this CA or {@code null}, if the trust anchor
     *         of this instance was not created with a certificate.
     */
    public final X509Certificate getTrustedCert() {
        return trustedCert;
    }

    /**
     * Returns the name of the certificate authority as {@code X500Principal}.
     *
     * @return the name of the certificate authority or {@code null} if the
     *         trust anchor of this instance was not created with a {@code
     *         X500Principal}.
     */
    public final X500Principal getCA() {
        return caPrincipal;
    }

    /**
     * Returns the name of the certificate authority as {@code String} in RFC
     * 2253 format.
     *
     * @return the name of the certificate authority as {@code String} in RFC
     *         2253 format or {@code null} if the trust anchor of this instance
     *         was not created with a CA name.
     */
    public final String getCAName() {
        return caName;
    }

    /**
     * Returns the public key of the certificate authority.
     *
     * @return the public key of the certificate authority or {@code null} if
     *         the trust anchor if this instance was not created with a public
     *         key.
     */
    public final PublicKey getCAPublicKey() {
        return caPublicKey;
    }

    /**
     * Returns a string representation of this {@code TrustAnchor} instance.
     *
     * @return a string representation of this {@code TrustAnchor} instance.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("TrustAnchor: [\n");
        if (trustedCert != null) {
            sb.append("Trusted CA certificate: ");
            sb.append(trustedCert);
            sb.append("\n");
        }
        if (caPrincipal != null) {
            sb.append("Trusted CA Name: ");
            sb.append(caPrincipal);
            sb.append("\n");
        }
        if (caPublicKey != null) {
            sb.append("Trusted CA Public Key: ");
            sb.append(caPublicKey);
            sb.append("\n");
        }
        // FIXME if needed:
        if (nameConstraints != null) {
            sb.append("Name Constraints:\n");
            sb.append(Array.toString(nameConstraints, "    "));
        }
        sb.append("\n]");
        return sb.toString();
    }

    //
    // Private stuff
    //

    // Decodes and checks NameConstraints structure.
    // Throws IllegalArgumentException if NameConstraints
    // encoding is invalid.
    private void processNameConstraints() {
        try {
            // decode and check nameConstraints
            NameConstraints.ASN1.decode(nameConstraints);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
