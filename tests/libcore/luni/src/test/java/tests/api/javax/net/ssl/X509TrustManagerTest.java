package tests.api.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import junit.framework.TestCase;
import org.apache.harmony.security.tests.support.cert.TestUtils;
import org.apache.harmony.xnet.tests.support.X509TrustManagerImpl;

public class X509TrustManagerTest extends TestCase {

    private X509Certificate[] setX509Certificate() throws Exception {
        CertificateFactory certFact = CertificateFactory.getInstance("X.509");
        X509Certificate pemCert = (X509Certificate) certFact.generateCertificate(
                new ByteArrayInputStream(TestUtils.getX509Certificate_v3()));
        X509Certificate[] xcert = { pemCert };
        return xcert;
    }

    private X509Certificate[] setInvalid() throws Exception {
        CertificateFactory certFact = CertificateFactory.getInstance("X.509");
        X509Certificate pemCert = (X509Certificate) certFact.generateCertificate(
                new ByteArrayInputStream(TestUtils.getX509Certificate_v1()));
        X509Certificate[] xcert = { pemCert };
        return xcert;
    }

    public void test_checkClientTrusted_01() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = null;

        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        xcert = new X509Certificate[0];
        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        xcert = setX509Certificate();
        try {
            xtm.checkClientTrusted(xcert, null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        try {
            xtm.checkClientTrusted(xcert, "");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkClientTrusted_02() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setInvalid();

        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("CertificateException wasn't thrown");
        } catch (CertificateException expected) {
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkClientTrusted_03() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setX509Certificate();
        xtm.checkClientTrusted(xcert, "SSL");
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_01() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = null;

        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        xcert = new X509Certificate[0];
        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        xcert = setX509Certificate();
        try {
            xtm.checkServerTrusted(xcert, null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }

        try {
            xtm.checkServerTrusted(xcert, "");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_02() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setInvalid();

        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("CertificateException wasn't thrown");
        } catch (CertificateException expected) {
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_03() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setX509Certificate();
        xtm.checkServerTrusted(xcert, "SSL");
    }

    /**
     * javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public void test_getAcceptedIssuers() throws Exception {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        assertNotNull(xtm.getAcceptedIssuers());
    }

}
