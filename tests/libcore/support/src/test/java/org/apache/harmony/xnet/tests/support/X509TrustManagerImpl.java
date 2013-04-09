package org.apache.harmony.xnet.tests.support;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;

public class X509TrustManagerImpl implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] ax509certificate, String s)
                                   throws CertificateException {

        if(ax509certificate == null || ax509certificate.length == 0)
            throw new IllegalArgumentException("null or zero-length certificate chain");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("null or zero-length authentication type");

        for (int i = 0; i < ax509certificate.length; i++) {
            if (ax509certificate[i].getVersion() != 3) {
                throw new CertificateException();
            }
        }
    }

    public void checkServerTrusted(X509Certificate[] ax509certificate, String s)
                                   throws CertificateException {

        if(ax509certificate == null || ax509certificate.length == 0)
            throw new IllegalArgumentException("null or zero-length certificate chain");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("null or zero-length authentication type");

        for (int i = 0; i < ax509certificate.length; i++) {
            if (ax509certificate[i].getVersion() != 3) {
                throw new CertificateException();
            }
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] cert = new X509Certificate[0];
        return cert;
    }
}
