/*
 * Copyright (C) 2013 The Android Open Source Project
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

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.conscrypt.OpenSSLX509CertificateFactory.ParsingException;

public class OpenSSLX509CertPath extends CertPath {
    private static final byte[] PKCS7_MARKER = "-----BEGIN PKCS7".getBytes();

    private static final int PUSHBACK_SIZE = 64;

    /**
     * Supported encoding types for CerthPath. Used by the various APIs that
     * encode this into bytes such as {@link #getEncoded()}.
     */
    private enum Encoding {
        PKI_PATH("PkiPath"),
        PKCS7("PKCS7");

        private final String apiName;

        Encoding(String apiName) {
            this.apiName = apiName;
        }

        static Encoding findByApiName(String apiName) throws CertificateEncodingException {
            for (Encoding element : values()) {
                if (element.apiName.equals(apiName)) {
                    return element;
                }
            }

            return null;
        }
    }

    /** Unmodifiable list of encodings for the API. */
    private static final List<String> ALL_ENCODINGS = Collections.unmodifiableList(Arrays
            .asList(new String[] {
                    Encoding.PKI_PATH.apiName,
                    Encoding.PKCS7.apiName,
            }));

    private static final Encoding DEFAULT_ENCODING = Encoding.PKI_PATH;

    private final List<? extends X509Certificate> mCertificates;

    static Iterator<String> getEncodingsIterator() {
        return ALL_ENCODINGS.iterator();
    }

    protected OpenSSLX509CertPath(List<? extends X509Certificate> certificates) {
        super("X.509");

        mCertificates = certificates;
    }

    @Override
    public List<? extends Certificate> getCertificates() {
        return Collections.unmodifiableList(mCertificates);
    }

    private byte[] getEncoded(Encoding encoding) throws CertificateEncodingException {
        final OpenSSLX509Certificate[] certs = new OpenSSLX509Certificate[mCertificates.size()];
        final long[] certRefs = new long[certs.length];

        for (int i = 0, j = certs.length - 1; j >= 0; i++, j--) {
            final X509Certificate cert = mCertificates.get(i);

            if (cert instanceof OpenSSLX509Certificate) {
                certs[j] = (OpenSSLX509Certificate) cert;
            } else {
                certs[j] = OpenSSLX509Certificate.fromX509Der(cert.getEncoded());
            }

            certRefs[j] = certs[j].getContext();
        }

        switch (encoding) {
            case PKI_PATH:
                return NativeCrypto.ASN1_seq_pack_X509(certRefs);
            case PKCS7:
                return NativeCrypto.i2d_PKCS7(certRefs);
            default:
                throw new CertificateEncodingException("Unknown encoding");
        }
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return getEncoded(DEFAULT_ENCODING);
    }

    @Override
    public byte[] getEncoded(String encoding) throws CertificateEncodingException {
        Encoding enc = Encoding.findByApiName(encoding);
        if (enc == null) {
            throw new CertificateEncodingException("Invalid encoding: " + encoding);
        }

        return getEncoded(enc);
    }

    @Override
    public Iterator<String> getEncodings() {
        return getEncodingsIterator();
    }

    private static CertPath fromPkiPathEncoding(InputStream inStream) throws CertificateException {
        OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(inStream);

        final boolean markable = inStream.markSupported();
        if (markable) {
            inStream.mark(PUSHBACK_SIZE);
        }

        final long[] certRefs;
        try {
            certRefs = NativeCrypto.ASN1_seq_unpack_X509_bio(bis.getBioContext());
        } catch (Exception e) {
            if (markable) {
                try {
                    inStream.reset();
                } catch (IOException ignored) {
                }
            }
            throw new CertificateException(e);
        } finally {
            NativeCrypto.BIO_free(bis.getBioContext());
        }

        if (certRefs == null) {
            return new OpenSSLX509CertPath(Collections.<X509Certificate> emptyList());
        }

        final List<OpenSSLX509Certificate> certs =
                new ArrayList<OpenSSLX509Certificate>(certRefs.length);
        for (int i = certRefs.length - 1; i >= 0; i--) {
            if (certRefs[i] == 0) {
                continue;
            }
            certs.add(new OpenSSLX509Certificate(certRefs[i]));
        }

        return new OpenSSLX509CertPath(certs);
    }

    private static CertPath fromPkcs7Encoding(InputStream inStream) throws CertificateException {
        try {
            if (inStream == null || inStream.available() == 0) {
                return new OpenSSLX509CertPath(Collections.<X509Certificate> emptyList());
            }
        } catch (IOException e) {
            throw new CertificateException("Problem reading input stream", e);
        }

        final boolean markable = inStream.markSupported();
        if (markable) {
            inStream.mark(PUSHBACK_SIZE);
        }

        /* Attempt to see if this is a PKCS#7 bag. */
        final PushbackInputStream pbis = new PushbackInputStream(inStream, PUSHBACK_SIZE);
        try {
            final byte[] buffer = new byte[PKCS7_MARKER.length];

            final int len = pbis.read(buffer);
            if (len < 0) {
                /* No need to reset here. The stream was empty or EOF. */
                throw new ParsingException("inStream is empty");
            }
            pbis.unread(buffer, 0, len);

            if (len == PKCS7_MARKER.length && Arrays.equals(PKCS7_MARKER, buffer)) {
                return new OpenSSLX509CertPath(OpenSSLX509Certificate.fromPkcs7PemInputStream(pbis));
            }

            return new OpenSSLX509CertPath(OpenSSLX509Certificate.fromPkcs7DerInputStream(pbis));
        } catch (Exception e) {
            if (markable) {
                try {
                    inStream.reset();
                } catch (IOException ignored) {
                }
            }
            throw new CertificateException(e);
        }
    }

    private static CertPath fromEncoding(InputStream inStream, Encoding encoding)
            throws CertificateException {
        switch (encoding) {
            case PKI_PATH:
                return fromPkiPathEncoding(inStream);
            case PKCS7:
                return fromPkcs7Encoding(inStream);
            default:
                throw new CertificateEncodingException("Unknown encoding");
        }
    }

    public static CertPath fromEncoding(InputStream inStream, String encoding)
            throws CertificateException {
        if (inStream == null) {
            throw new CertificateException("inStream == null");
        }

        Encoding enc = Encoding.findByApiName(encoding);
        if (enc == null) {
            throw new CertificateException("Invalid encoding: " + encoding);
        }

        return fromEncoding(inStream, enc);
    }

    public static CertPath fromEncoding(InputStream inStream) throws CertificateException {
        return fromEncoding(inStream, DEFAULT_ENCODING);
    }
}
