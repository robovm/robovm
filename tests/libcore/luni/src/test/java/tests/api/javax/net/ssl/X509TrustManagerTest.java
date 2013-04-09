package tests.api.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.cert.TestUtils;
import org.apache.harmony.xnet.tests.support.X509TrustManagerImpl;

/**
 * Tests for <code>X509TrustManager</code> class constructors and methods.
 */
public class X509TrustManagerTest extends TestCase {

    private X509Certificate[] setX509Certificate() {
        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            X509Certificate pemCert = (X509Certificate) certFact
                .generateCertificate(new ByteArrayInputStream(TestUtils
                        .getX509Certificate_v3()));
            X509Certificate[] xcert = {pemCert};
            return xcert;
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
        return null;
    }

    private X509Certificate[] setInvalid() {
        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            X509Certificate pemCert = (X509Certificate) certFact
                .generateCertificate(new ByteArrayInputStream(TestUtils
                        .getX509Certificate_v1()));
            X509Certificate[] xcert = {pemCert};
            return xcert;
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
        return null;
    }

    /**
     * javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkClientTrusted_01() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = null;

        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        xcert = new X509Certificate[0];
        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        xcert = setX509Certificate();
        try {
            xtm.checkClientTrusted(xcert, null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        try {
            xtm.checkClientTrusted(xcert, "");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkClientTrusted_02() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setInvalid();

        try {
            xtm.checkClientTrusted(xcert, "SSL");
            fail("CertificateException wasn't thrown");
        } catch (CertificateException ce) {
            //expected
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkClientTrusted_03() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setX509Certificate();

        try {
            xtm.checkClientTrusted(xcert, "SSL");
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_01() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = null;

        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        xcert = new X509Certificate[0];
        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        xcert = setX509Certificate();
        try {
            xtm.checkServerTrusted(xcert, null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        try {
            xtm.checkServerTrusted(xcert, "");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException iae) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_02() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setInvalid();

        try {
            xtm.checkServerTrusted(xcert, "SSL");
            fail("CertificateException wasn't thrown");
        } catch (CertificateException ce) {
            //expected
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[] chain, String authType)
     */
    public void test_checkServerTrusted_03() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();
        X509Certificate[] xcert = setX509Certificate();

        try {
            xtm.checkServerTrusted(xcert, "SSL");
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
    }

    /**
     * javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public void test_getAcceptedIssuers() {
        X509TrustManagerImpl xtm = new X509TrustManagerImpl();

        try {
            assertNotNull(xtm.getAcceptedIssuers());
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
    }

}
