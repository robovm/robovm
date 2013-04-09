package tests.api.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import junit.framework.TestCase;

/**
 * Tests for <code>X509KeyManager</code> class constructors and methods.
 */
public class X509KeyManagerTest extends TestCase {

    private X509KeyManager manager;
    private KeyManagerFactory factory;

    private static final String CLIENT = "CLIENT";
    private static final String SERVER = "SERVER";
    private static final String TYPE_RSA = "RSA";
    private static final char[] PASSWORD = "1234".toCharArray();

    private String keyType;
    private KeyStore keyTest;
    private X509Certificate[] cert;
    private PrivateKey[] keys;


    /*
       Certificate:
           Data:
               Version: 3 (0x2)
               Serial Number: 0 (0x0)
               Signature Algorithm: sha1WithRSAEncryption
               Issuer: C=AN, ST=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Validity
                   Not Before: Mar 20 17:00:06 2009 GMT
                   Not After : Mar 19 17:00:06 2012 GMT
               Subject: C=AN, ST=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Subject Public Key Info:
                   Public Key Algorithm: rsaEncryption
                   RSA Public Key: (1024 bit)
                       Modulus (1024 bit):
                           00:aa:42:40:ed:92:21:17:99:5f:0e:e4:42:b8:cb:
                           66:3d:63:2a:16:34:3c:7b:d3:3e:1f:a8:3f:bd:9a:
                           eb:b3:24:6b:8c:e4:da:2f:31:bc:61:07:27:2e:28:
                           71:77:58:ae:b4:89:7c:eb:b0:06:24:07:57:3c:54:
                           71:db:71:41:05:ab:3d:9f:05:d2:ca:cb:1c:bf:9d:
                           8a:21:96:8f:13:61:25:69:12:3b:77:bd:f7:34:b2:
                           09:a9:e0:52:94:44:31:ce:db:3d:eb:64:f1:d6:ca:
                           c5:7d:2f:d6:6f:8d:e4:29:8b:06:98:8a:95:3d:7a:
                           97:41:9a:f1:66:c5:09:82:0d
                       Exponent: 65537 (0x10001)
               X509v3 extensions:
                   X509v3 Subject Key Identifier:
                       E7:9B:7D:90:29:EA:90:0B:7F:08:41:76:4E:41:23:E8:43:2C:A9:03
                   X509v3 Authority Key Identifier:
                       keyid:E7:9B:7D:90:29:EA:90:0B:7F:08:41:76:4E:41:23:E8:43:2C:A9:03
                       DirName:/C=AN/ST=Android/O=Android/OU=Android/CN=Android/emailAddress=android@android.com
                       serial:00

                   X509v3 Basic Constraints:
                       CA:TRUE
           Signature Algorithm: sha1WithRSAEncryption
               14:98:30:29:42:ef:ab:e6:b8:25:4b:55:85:04:a5:c4:dd:1d:
               8b:6a:c1:6f:6c:1c:1d:c3:61:34:30:07:34:4d:6a:8b:55:6f:
               75:55:6e:15:58:c5:f8:af:e0:be:73:ba:d9:a5:85:d7:b5:1a:
               85:44:2b:88:fd:cc:cb:d1:ed:46:69:43:ff:59:ae:9b:5c:17:
               26:da:ee:c8:bf:67:55:01:a0:0e:10:b9:85:49:54:d9:79:1e:
               7b:2e:6f:65:4f:d9:10:2e:9d:b8:92:63:67:74:8b:22:0d:6d:
               d3:5d:9e:29:63:f9:36:93:1b:a7:80:e2:b1:f1:bf:29:19:81:
               3d:07
     */
    String certificate = "-----BEGIN CERTIFICATE-----\n"
            + "MIIDPzCCAqigAwIBAgIBADANBgkqhkiG9w0BAQUFADB5MQswCQYDVQQGEwJBTjEQ\n"
            + "MA4GA1UECBMHQW5kcm9pZDEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5k\n"
            + "cm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBh\n"
            + "bmRyb2lkLmNvbTAeFw0wOTAzMjAxNzAwMDZaFw0xMjAzMTkxNzAwMDZaMHkxCzAJ\n"
            + "BgNVBAYTAkFOMRAwDgYDVQQIEwdBbmRyb2lkMRAwDgYDVQQKEwdBbmRyb2lkMRAw\n"
            + "DgYDVQQLEwdBbmRyb2lkMRAwDgYDVQQDEwdBbmRyb2lkMSIwIAYJKoZIhvcNAQkB\n"
            + "FhNhbmRyb2lkQGFuZHJvaWQuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKB\n"
            + "gQCqQkDtkiEXmV8O5EK4y2Y9YyoWNDx70z4fqD+9muuzJGuM5NovMbxhBycuKHF3\n"
            + "WK60iXzrsAYkB1c8VHHbcUEFqz2fBdLKyxy/nYohlo8TYSVpEjt3vfc0sgmp4FKU\n"
            + "RDHO2z3rZPHWysV9L9ZvjeQpiwaYipU9epdBmvFmxQmCDQIDAQABo4HWMIHTMB0G\n"
            + "A1UdDgQWBBTnm32QKeqQC38IQXZOQSPoQyypAzCBowYDVR0jBIGbMIGYgBTnm32Q\n"
            + "KeqQC38IQXZOQSPoQyypA6F9pHsweTELMAkGA1UEBhMCQU4xEDAOBgNVBAgTB0Fu\n"
            + "ZHJvaWQxEDAOBgNVBAoTB0FuZHJvaWQxEDAOBgNVBAsTB0FuZHJvaWQxEDAOBgNV\n"
            + "BAMTB0FuZHJvaWQxIjAgBgkqhkiG9w0BCQEWE2FuZHJvaWRAYW5kcm9pZC5jb22C\n"
            + "AQAwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOBgQAUmDApQu+r5rglS1WF\n"
            + "BKXE3R2LasFvbBwdw2E0MAc0TWqLVW91VW4VWMX4r+C+c7rZpYXXtRqFRCuI/czL\n"
            + "0e1GaUP/Wa6bXBcm2u7Iv2dVAaAOELmFSVTZeR57Lm9lT9kQLp24kmNndIsiDW3T\n"
            + "XZ4pY/k2kxungOKx8b8pGYE9Bw==\n"
            + "-----END CERTIFICATE-----";

    ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
            .getBytes());

    /*
     * The key in DER format.
     * Below is the same key in PEM format as reference
     */
    byte[] keyBytes = new byte[] {
            (byte)0x30, (byte)0x82, (byte)0x02, (byte)0x77, (byte)0x02, (byte)0x01, (byte)0x00,
            (byte)0x30, (byte)0x0d, (byte)0x06, (byte)0x09, (byte)0x2a, (byte)0x86, (byte)0x48,
            (byte)0x86, (byte)0xf7, (byte)0x0d, (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x05,
            (byte)0x00, (byte)0x04, (byte)0x82, (byte)0x02, (byte)0x61, (byte)0x30, (byte)0x82,
            (byte)0x02, (byte)0x5d, (byte)0x02, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x81,
            (byte)0x81, (byte)0x00, (byte)0xaa, (byte)0x42, (byte)0x40, (byte)0xed, (byte)0x92,
            (byte)0x21, (byte)0x17, (byte)0x99, (byte)0x5f, (byte)0x0e, (byte)0xe4, (byte)0x42,
            (byte)0xb8, (byte)0xcb, (byte)0x66, (byte)0x3d, (byte)0x63, (byte)0x2a, (byte)0x16,
            (byte)0x34, (byte)0x3c, (byte)0x7b, (byte)0xd3, (byte)0x3e, (byte)0x1f, (byte)0xa8,
            (byte)0x3f, (byte)0xbd, (byte)0x9a, (byte)0xeb, (byte)0xb3, (byte)0x24, (byte)0x6b,
            (byte)0x8c, (byte)0xe4, (byte)0xda, (byte)0x2f, (byte)0x31, (byte)0xbc, (byte)0x61,
            (byte)0x07, (byte)0x27, (byte)0x2e, (byte)0x28, (byte)0x71, (byte)0x77, (byte)0x58,
            (byte)0xae, (byte)0xb4, (byte)0x89, (byte)0x7c, (byte)0xeb, (byte)0xb0, (byte)0x06,
            (byte)0x24, (byte)0x07, (byte)0x57, (byte)0x3c, (byte)0x54, (byte)0x71, (byte)0xdb,
            (byte)0x71, (byte)0x41, (byte)0x05, (byte)0xab, (byte)0x3d, (byte)0x9f, (byte)0x05,
            (byte)0xd2, (byte)0xca, (byte)0xcb, (byte)0x1c, (byte)0xbf, (byte)0x9d, (byte)0x8a,
            (byte)0x21, (byte)0x96, (byte)0x8f, (byte)0x13, (byte)0x61, (byte)0x25, (byte)0x69,
            (byte)0x12, (byte)0x3b, (byte)0x77, (byte)0xbd, (byte)0xf7, (byte)0x34, (byte)0xb2,
            (byte)0x09, (byte)0xa9, (byte)0xe0, (byte)0x52, (byte)0x94, (byte)0x44, (byte)0x31,
            (byte)0xce, (byte)0xdb, (byte)0x3d, (byte)0xeb, (byte)0x64, (byte)0xf1, (byte)0xd6,
            (byte)0xca, (byte)0xc5, (byte)0x7d, (byte)0x2f, (byte)0xd6, (byte)0x6f, (byte)0x8d,
            (byte)0xe4, (byte)0x29, (byte)0x8b, (byte)0x06, (byte)0x98, (byte)0x8a, (byte)0x95,
            (byte)0x3d, (byte)0x7a, (byte)0x97, (byte)0x41, (byte)0x9a, (byte)0xf1, (byte)0x66,
            (byte)0xc5, (byte)0x09, (byte)0x82, (byte)0x0d, (byte)0x02, (byte)0x03, (byte)0x01,
            (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x81, (byte)0x80, (byte)0x34, (byte)0x91,
            (byte)0x8e, (byte)0x50, (byte)0x8b, (byte)0xfc, (byte)0xf1, (byte)0xb7, (byte)0x66,
            (byte)0x35, (byte)0x47, (byte)0xdf, (byte)0x1e, (byte)0x05, (byte)0x97, (byte)0x44,
            (byte)0xbe, (byte)0xf8, (byte)0x80, (byte)0xb0, (byte)0x92, (byte)0x38, (byte)0x3d,
            (byte)0x4a, (byte)0x02, (byte)0x26, (byte)0x45, (byte)0xbf, (byte)0xfa, (byte)0x34,
            (byte)0x6a, (byte)0x34, (byte)0x85, (byte)0x8c, (byte)0x94, (byte)0x20, (byte)0x95,
            (byte)0xcf, (byte)0xca, (byte)0x75, (byte)0x3e, (byte)0xeb, (byte)0x27, (byte)0x02,
            (byte)0x4f, (byte)0xbe, (byte)0x64, (byte)0xc0, (byte)0x54, (byte)0x77, (byte)0xda,
            (byte)0xfd, (byte)0x3e, (byte)0x75, (byte)0x36, (byte)0xec, (byte)0x99, (byte)0x4f,
            (byte)0xc4, (byte)0x56, (byte)0xff, (byte)0x45, (byte)0x61, (byte)0xa8, (byte)0xa8,
            (byte)0x41, (byte)0xe4, (byte)0x42, (byte)0x71, (byte)0x7a, (byte)0x8c, (byte)0x84,
            (byte)0xc2, (byte)0x02, (byte)0x40, (byte)0x0b, (byte)0x3d, (byte)0x42, (byte)0xe0,
            (byte)0x8b, (byte)0x22, (byte)0xf7, (byte)0x4c, (byte)0xa3, (byte)0xbb, (byte)0xd8,
            (byte)0x8f, (byte)0x45, (byte)0xa2, (byte)0x55, (byte)0xc7, (byte)0xd0, (byte)0x6a,
            (byte)0x25, (byte)0xbf, (byte)0xda, (byte)0x54, (byte)0x57, (byte)0x14, (byte)0x91,
            (byte)0x0c, (byte)0x09, (byte)0x0b, (byte)0x9a, (byte)0x50, (byte)0xca, (byte)0xe6,
            (byte)0x9e, (byte)0x28, (byte)0xc3, (byte)0x78, (byte)0x39, (byte)0x10, (byte)0x06,
            (byte)0x02, (byte)0x96, (byte)0x10, (byte)0x1a, (byte)0xd2, (byte)0x4b, (byte)0x7b,
            (byte)0x6c, (byte)0x72, (byte)0x9e, (byte)0x1e, (byte)0xac, (byte)0xd2, (byte)0xc1,
            (byte)0x02, (byte)0x41, (byte)0x00, (byte)0xde, (byte)0x27, (byte)0xbd, (byte)0x43,
            (byte)0xa4, (byte)0xbd, (byte)0x95, (byte)0x14, (byte)0x2e, (byte)0x1c, (byte)0xa0,
            (byte)0x74, (byte)0xa5, (byte)0x3e, (byte)0xfa, (byte)0xf9, (byte)0x15, (byte)0xb2,
            (byte)0x29, (byte)0x6a, (byte)0x2a, (byte)0x42, (byte)0x94, (byte)0x5a, (byte)0xf2,
            (byte)0x81, (byte)0xf3, (byte)0xe1, (byte)0x76, (byte)0x49, (byte)0x11, (byte)0x9d,
            (byte)0x18, (byte)0xc5, (byte)0xeb, (byte)0xb6, (byte)0xbc, (byte)0x81, (byte)0x3a,
            (byte)0x14, (byte)0x9c, (byte)0x41, (byte)0x01, (byte)0x58, (byte)0x56, (byte)0xa9,
            (byte)0x9b, (byte)0x73, (byte)0x2f, (byte)0xd9, (byte)0xa8, (byte)0x8e, (byte)0xc4,
            (byte)0x48, (byte)0x69, (byte)0x35, (byte)0xe6, (byte)0xf4, (byte)0x73, (byte)0x2f,
            (byte)0xf9, (byte)0x12, (byte)0x12, (byte)0x71, (byte)0x02, (byte)0x41, (byte)0x00,
            (byte)0xc4, (byte)0x32, (byte)0x81, (byte)0x5d, (byte)0x19, (byte)0x54, (byte)0x2c,
            (byte)0x29, (byte)0x5a, (byte)0x9f, (byte)0x36, (byte)0x4c, (byte)0x6f, (byte)0x2d,
            (byte)0xfd, (byte)0x62, (byte)0x0e, (byte)0xe6, (byte)0x37, (byte)0xc2, (byte)0xf6,
            (byte)0x69, (byte)0x64, (byte)0xf9, (byte)0x3a, (byte)0xcc, (byte)0xb2, (byte)0x63,
            (byte)0x2f, (byte)0xa9, (byte)0xfe, (byte)0x7e, (byte)0x8b, (byte)0x2d, (byte)0x69,
            (byte)0x13, (byte)0xe5, (byte)0x61, (byte)0x58, (byte)0xb7, (byte)0xfa, (byte)0x55,
            (byte)0x74, (byte)0x2c, (byte)0xe8, (byte)0xa1, (byte)0xac, (byte)0xc3, (byte)0xdd,
            (byte)0x5b, (byte)0x62, (byte)0xae, (byte)0x0a, (byte)0x27, (byte)0xce, (byte)0xb0,
            (byte)0xf2, (byte)0x81, (byte)0x5f, (byte)0x9a, (byte)0x6f, (byte)0x5f, (byte)0x3f,
            (byte)0x5d, (byte)0x02, (byte)0x41, (byte)0x00, (byte)0x92, (byte)0x42, (byte)0xff,
            (byte)0xac, (byte)0xe5, (byte)0x6d, (byte)0x9c, (byte)0x15, (byte)0x29, (byte)0x36,
            (byte)0xd7, (byte)0xbd, (byte)0x74, (byte)0x7e, (byte)0x3e, (byte)0xa6, (byte)0x77,
            (byte)0xce, (byte)0x50, (byte)0xce, (byte)0x00, (byte)0xfc, (byte)0xcc, (byte)0xc8,
            (byte)0x04, (byte)0x19, (byte)0xe3, (byte)0x03, (byte)0x71, (byte)0xe9, (byte)0x31,
            (byte)0x9b, (byte)0x88, (byte)0x8f, (byte)0xe6, (byte)0x5c, (byte)0xed, (byte)0x46,
            (byte)0xf7, (byte)0x82, (byte)0x52, (byte)0x4d, (byte)0xca, (byte)0x20, (byte)0xeb,
            (byte)0x0d, (byte)0xc7, (byte)0xb6, (byte)0xd2, (byte)0xae, (byte)0x2e, (byte)0xf7,
            (byte)0xaf, (byte)0xeb, (byte)0x2c, (byte)0xb9, (byte)0xbc, (byte)0x50, (byte)0xfc,
            (byte)0xf5, (byte)0x7c, (byte)0xba, (byte)0x95, (byte)0x41, (byte)0x02, (byte)0x40,
            (byte)0x54, (byte)0xf8, (byte)0x46, (byte)0x9c, (byte)0x6a, (byte)0x5e, (byte)0xd0,
            (byte)0xed, (byte)0x6c, (byte)0x08, (byte)0xed, (byte)0xfc, (byte)0x36, (byte)0x5e,
            (byte)0x65, (byte)0x91, (byte)0x75, (byte)0x40, (byte)0x71, (byte)0x3f, (byte)0xe7,
            (byte)0x76, (byte)0x07, (byte)0xbc, (byte)0x04, (byte)0xa2, (byte)0x28, (byte)0x53,
            (byte)0xda, (byte)0x8d, (byte)0xb5, (byte)0xe1, (byte)0x5a, (byte)0x27, (byte)0x65,
            (byte)0x8d, (byte)0xaf, (byte)0x56, (byte)0xf4, (byte)0x94, (byte)0x61, (byte)0x3f,
            (byte)0x67, (byte)0x1c, (byte)0x17, (byte)0xf8, (byte)0x05, (byte)0x19, (byte)0xa2,
            (byte)0xa1, (byte)0x74, (byte)0x60, (byte)0x49, (byte)0x97, (byte)0xa9, (byte)0xe5,
            (byte)0x6a, (byte)0x71, (byte)0x6b, (byte)0x55, (byte)0x38, (byte)0x0c, (byte)0xb9,
            (byte)0x25, (byte)0x02, (byte)0x41, (byte)0x00, (byte)0xae, (byte)0xf2, (byte)0xa8,
            (byte)0x6d, (byte)0x1d, (byte)0x35, (byte)0x38, (byte)0x73, (byte)0x98, (byte)0x15,
            (byte)0xc7, (byte)0x15, (byte)0x02, (byte)0x2f, (byte)0x29, (byte)0x5d, (byte)0x18,
            (byte)0x4b, (byte)0x7d, (byte)0xb2, (byte)0x59, (byte)0xbe, (byte)0x5a, (byte)0xc7,
            (byte)0x72, (byte)0xd0, (byte)0x80, (byte)0xd8, (byte)0x77, (byte)0xa1, (byte)0x7f,
            (byte)0xb2, (byte)0x35, (byte)0x0d, (byte)0x78, (byte)0x92, (byte)0x91, (byte)0x35,
            (byte)0x47, (byte)0xeb, (byte)0x4b, (byte)0x00, (byte)0x59, (byte)0xb4, (byte)0xc4,
            (byte)0x2c, (byte)0x29, (byte)0xe7, (byte)0x39, (byte)0x9d, (byte)0x48, (byte)0x8b,
            (byte)0x4f, (byte)0x46, (byte)0xe6, (byte)0xce, (byte)0xd3, (byte)0x6c, (byte)0x84,
            (byte)0x9b, (byte)0xd2, (byte)0x10, (byte)0xb0, (byte)0xe1
    };

    /*
     * The same key in PEM format.
     * The DER version of this key was created using
     *
     * openssl pkcs8 -topk8 -nocrypt -in key1.pem
     *         -inform PEM -out key1.der -outform DER
     *
     * -----BEGIN RSA PRIVATE KEY-----
     * Proc-Type: 4,ENCRYPTED
     * DEK-Info: DES-EDE3-CBC,69E26FCC3A7F136E
     *
     * YKiLXOwf2teog4IoOvbbROy9vqp0EMt1KF9eNKeKFCWGCS4RFATaAGjKrdA26bOV
     * MBdyB4V7qaxLC8/UwLlzFLpprouIfGqrEoR/NT0eKQ+4Pl25GlMvlPaR0pATBLZ2
     * OEaB3zcNygOQ02Jdrmw2+CS9qVtGGXjn6Qp6TVFm6edNCoOVZODLP9kkzPLn8Mkm
     * /isgsprwMELuth8Y5BC0brI5XYdMqZFI5dLz4wzVH81wBYbRmJqR7yOE1pzAJS9I
     * gJ5YvcP7pSmoA2SHVN4v4qolM+GAM9YIp2bwEyWFRjbriNlF1yM+HflGMEZ1HNpZ
     * FSFFA3G8EIH9ogbZ3j+7EujrndJC7GIibwiu5rd3eIHtcwrWprp+wEoPc/vM8OpR
     * so9ms7iQYV6faYCWK4yeCfErYw7t+AhGqfLiqHO6bO2XAYJcD28RYV9gXmugZOhT
     * 9471MOw94HWF5tBVjgIkyNBcbRyMF9iyQKafbkHYpmxaB4s2EqQr1SNZl3SLEwhX
     * MEGy3/tyveuMLAvdTlSDZbt6memWoXXEX4Ep/q6r0ErCTY31awdP/XaJcJBGb9ni
     * Iai8DICaG1v4bUuBVgaiacZlgw1O4Hhj8D2DWfVZsgpx5y8tBRM2lGWvyzEi5n2F
     * PiR2UlT0DjCD1ObjCpWJ5insX/w8dXSHGZLLb9ccGRUrw/+5Bptn+AoEfdP+8S3j
     * UdMdxl6qt2gneCYu1Lr3cQ+qKPqikQty2UQ6Yp8dJkheLJ2Tr+rnaytOCp2dAT9K
     * KXTimIcXV+ftvUMbDPXYu4LJBldr2VokD+k3QbHDgFnfHIiNkwiPzA==
     * -----END RSA PRIVATE KEY-----
     */

    /*
       Certificate:
           Data:
               Version: 3 (0x2)
               Serial Number: 1 (0x1)
               Signature Algorithm: sha1WithRSAEncryption
               Issuer: C=AN, ST=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Validity
                   Not Before: Mar 20 17:00:40 2009 GMT
                   Not After : Mar 20 17:00:40 2010 GMT
               Subject: C=AN, ST=Android, L=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Subject Public Key Info:
                   Public Key Algorithm: rsaEncryption
                   RSA Public Key: (1024 bit)
                       Modulus (1024 bit):
                           00:d0:44:5a:c4:76:ef:ae:ff:99:5b:c3:37:c1:09:
                           33:c1:97:e5:64:7a:a9:7e:98:4b:3a:a3:33:d0:5c:
                           c7:56:ac:d8:42:e8:4a:ac:9c:d9:8f:89:84:c8:46:
                           95:ce:22:f7:6a:09:de:91:47:9c:38:23:a5:4a:fc:
                           08:af:5a:b4:6e:39:8e:e9:f5:0e:46:00:69:e1:e5:
                           cc:4c:81:b6:82:7b:56:fb:f4:dc:04:ff:61:e2:7e:
                           5f:e2:f9:97:53:93:d4:69:9b:ba:79:20:cd:1e:3e:
                           d5:9a:44:95:7c:cf:c1:51:f2:22:fc:ec:cc:66:18:
                           74:60:2a:a2:be:06:c2:9e:8d
                       Exponent: 65537 (0x10001)
               X509v3 extensions:
                   X509v3 Basic Constraints:
                       CA:FALSE
                   Netscape Comment:
                       OpenSSL Generated Certificate
                   X509v3 Subject Key Identifier:
                       95:3E:C3:46:69:52:78:08:05:46:B9:00:69:E5:E7:A7:99:E3:C4:67
                   X509v3 Authority Key Identifier:
                       keyid:E7:9B:7D:90:29:EA:90:0B:7F:08:41:76:4E:41:23:E8:43:2C:A9:03

           Signature Algorithm: sha1WithRSAEncryption
               a3:5b:30:f5:28:3f:87:f6:1b:36:6a:22:6d:66:48:fa:cb:ee:
               4c:04:cf:11:14:e2:1f:b5:68:0c:e7:61:0e:bc:d3:69:19:02:
               8b:d5:d3:05:4a:c8:29:e8:e3:d0:e9:32:ad:6c:7d:9c:c4:46:
               6c:f9:66:e6:64:60:47:6b:ef:8e:c8:1c:67:5a:5a:cf:73:a3:
               7e:9d:6e:89:0c:67:99:17:3d:b2:b8:8e:41:95:9c:84:95:bf:
               57:95:24:22:8f:19:12:c1:fd:23:45:75:7f:4f:61:06:e3:9f:
               05:dc:e7:29:9a:6b:17:e1:e1:37:d5:8b:ba:b4:d0:8a:3c:dd:
               3f:6a
     */
    String certificate2 = "-----BEGIN CERTIFICATE-----\n"
            + "MIIC9jCCAl+gAwIBAgIBATANBgkqhkiG9w0BAQUFADB5MQswCQYDVQQGEwJBTjEQ\n"
            + "MA4GA1UECBMHQW5kcm9pZDEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5k\n"
            + "cm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBh\n"
            + "bmRyb2lkLmNvbTAeFw0wOTAzMjAxNzAwNDBaFw0xMDAzMjAxNzAwNDBaMIGLMQsw\n"
            + "CQYDVQQGEwJBTjEQMA4GA1UECBMHQW5kcm9pZDEQMA4GA1UEBxMHQW5kcm9pZDEQ\n"
            + "MA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5k\n"
            + "cm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCBnzANBgkq\n"
            + "hkiG9w0BAQEFAAOBjQAwgYkCgYEA0ERaxHbvrv+ZW8M3wQkzwZflZHqpfphLOqMz\n"
            + "0FzHVqzYQuhKrJzZj4mEyEaVziL3agnekUecOCOlSvwIr1q0bjmO6fUORgBp4eXM\n"
            + "TIG2gntW+/TcBP9h4n5f4vmXU5PUaZu6eSDNHj7VmkSVfM/BUfIi/OzMZhh0YCqi\n"
            + "vgbCno0CAwEAAaN7MHkwCQYDVR0TBAIwADAsBglghkgBhvhCAQ0EHxYdT3BlblNT\n"
            + "TCBHZW5lcmF0ZWQgQ2VydGlmaWNhdGUwHQYDVR0OBBYEFJU+w0ZpUngIBUa5AGnl\n"
            + "56eZ48RnMB8GA1UdIwQYMBaAFOebfZAp6pALfwhBdk5BI+hDLKkDMA0GCSqGSIb3\n"
            + "DQEBBQUAA4GBAKNbMPUoP4f2GzZqIm1mSPrL7kwEzxEU4h+1aAznYQ6802kZAovV\n"
            + "0wVKyCno49DpMq1sfZzERmz5ZuZkYEdr747IHGdaWs9zo36dbokMZ5kXPbK4jkGV\n"
            + "nISVv1eVJCKPGRLB/SNFdX9PYQbjnwXc5ymaaxfh4TfVi7q00Io83T9q\n\n"
            + "-----END CERTIFICATE-----";

    ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
            .getBytes());

       /*
        * The key in DER format.
        * Below is the same key in PEM format as reference
        */
       byte[] key2Bytes = new byte[] {
            (byte)0x30, (byte)0x82, (byte)0x02, (byte)0x75, (byte)0x02, (byte)0x01, (byte)0x00,
            (byte)0x30, (byte)0x0d, (byte)0x06, (byte)0x09, (byte)0x2a, (byte)0x86, (byte)0x48,
            (byte)0x86, (byte)0xf7, (byte)0x0d, (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x05,
            (byte)0x00, (byte)0x04, (byte)0x82, (byte)0x02, (byte)0x5f, (byte)0x30, (byte)0x82,
            (byte)0x02, (byte)0x5b, (byte)0x02, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x81,
            (byte)0x81, (byte)0x00, (byte)0xd0, (byte)0x44, (byte)0x5a, (byte)0xc4, (byte)0x76,
            (byte)0xef, (byte)0xae, (byte)0xff, (byte)0x99, (byte)0x5b, (byte)0xc3, (byte)0x37,
            (byte)0xc1, (byte)0x09, (byte)0x33, (byte)0xc1, (byte)0x97, (byte)0xe5, (byte)0x64,
            (byte)0x7a, (byte)0xa9, (byte)0x7e, (byte)0x98, (byte)0x4b, (byte)0x3a, (byte)0xa3,
            (byte)0x33, (byte)0xd0, (byte)0x5c, (byte)0xc7, (byte)0x56, (byte)0xac, (byte)0xd8,
            (byte)0x42, (byte)0xe8, (byte)0x4a, (byte)0xac, (byte)0x9c, (byte)0xd9, (byte)0x8f,
            (byte)0x89, (byte)0x84, (byte)0xc8, (byte)0x46, (byte)0x95, (byte)0xce, (byte)0x22,
            (byte)0xf7, (byte)0x6a, (byte)0x09, (byte)0xde, (byte)0x91, (byte)0x47, (byte)0x9c,
            (byte)0x38, (byte)0x23, (byte)0xa5, (byte)0x4a, (byte)0xfc, (byte)0x08, (byte)0xaf,
            (byte)0x5a, (byte)0xb4, (byte)0x6e, (byte)0x39, (byte)0x8e, (byte)0xe9, (byte)0xf5,
            (byte)0x0e, (byte)0x46, (byte)0x00, (byte)0x69, (byte)0xe1, (byte)0xe5, (byte)0xcc,
            (byte)0x4c, (byte)0x81, (byte)0xb6, (byte)0x82, (byte)0x7b, (byte)0x56, (byte)0xfb,
            (byte)0xf4, (byte)0xdc, (byte)0x04, (byte)0xff, (byte)0x61, (byte)0xe2, (byte)0x7e,
            (byte)0x5f, (byte)0xe2, (byte)0xf9, (byte)0x97, (byte)0x53, (byte)0x93, (byte)0xd4,
            (byte)0x69, (byte)0x9b, (byte)0xba, (byte)0x79, (byte)0x20, (byte)0xcd, (byte)0x1e,
            (byte)0x3e, (byte)0xd5, (byte)0x9a, (byte)0x44, (byte)0x95, (byte)0x7c, (byte)0xcf,
            (byte)0xc1, (byte)0x51, (byte)0xf2, (byte)0x22, (byte)0xfc, (byte)0xec, (byte)0xcc,
            (byte)0x66, (byte)0x18, (byte)0x74, (byte)0x60, (byte)0x2a, (byte)0xa2, (byte)0xbe,
            (byte)0x06, (byte)0xc2, (byte)0x9e, (byte)0x8d, (byte)0x02, (byte)0x03, (byte)0x01,
            (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x81, (byte)0x80, (byte)0x06, (byte)0x41,
            (byte)0xd7, (byte)0x7c, (byte)0x49, (byte)0x9a, (byte)0x7f, (byte)0xe6, (byte)0x7c,
            (byte)0x04, (byte)0x0e, (byte)0xc4, (byte)0x71, (byte)0x0f, (byte)0x46, (byte)0xb7,
            (byte)0xcd, (byte)0x49, (byte)0x7e, (byte)0x10, (byte)0x55, (byte)0x61, (byte)0x51,
            (byte)0x50, (byte)0x09, (byte)0x4d, (byte)0xf7, (byte)0xf3, (byte)0x8d, (byte)0xa6,
            (byte)0x0b, (byte)0x8b, (byte)0x9b, (byte)0xdf, (byte)0xbe, (byte)0xbc, (byte)0xe7,
            (byte)0x9c, (byte)0xba, (byte)0xc8, (byte)0x9e, (byte)0x38, (byte)0x18, (byte)0x10,
            (byte)0x4e, (byte)0xd5, (byte)0xe7, (byte)0xa5, (byte)0x09, (byte)0x51, (byte)0x8c,
            (byte)0x97, (byte)0x4e, (byte)0xd0, (byte)0x79, (byte)0xbb, (byte)0x50, (byte)0x6f,
            (byte)0x05, (byte)0x4d, (byte)0x79, (byte)0x7f, (byte)0x3f, (byte)0x26, (byte)0x76,
            (byte)0xc1, (byte)0xcc, (byte)0x40, (byte)0x0f, (byte)0xde, (byte)0x42, (byte)0x5d,
            (byte)0xc1, (byte)0x5f, (byte)0x70, (byte)0x46, (byte)0x70, (byte)0x8d, (byte)0xff,
            (byte)0x26, (byte)0x35, (byte)0x75, (byte)0x9a, (byte)0x97, (byte)0xd2, (byte)0x74,
            (byte)0x53, (byte)0x11, (byte)0x2b, (byte)0xc1, (byte)0x76, (byte)0x9c, (byte)0x9f,
            (byte)0x93, (byte)0xaa, (byte)0xa8, (byte)0x41, (byte)0x23, (byte)0x9a, (byte)0x04,
            (byte)0x11, (byte)0x6e, (byte)0x56, (byte)0xea, (byte)0xf5, (byte)0xd6, (byte)0x1d,
            (byte)0x49, (byte)0x2a, (byte)0x83, (byte)0x49, (byte)0x7d, (byte)0xb7, (byte)0xd1,
            (byte)0xe6, (byte)0x8d, (byte)0x93, (byte)0x1a, (byte)0x81, (byte)0x8e, (byte)0xc2,
            (byte)0xb9, (byte)0xbf, (byte)0xfd, (byte)0x00, (byte)0xe2, (byte)0xb5, (byte)0x01,
            (byte)0x02, (byte)0x41, (byte)0x00, (byte)0xea, (byte)0xce, (byte)0xc6, (byte)0x11,
            (byte)0x1e, (byte)0xf6, (byte)0xcf, (byte)0x3a, (byte)0x8c, (byte)0xe7, (byte)0x80,
            (byte)0x16, (byte)0x8f, (byte)0x1d, (byte)0xeb, (byte)0xa2, (byte)0xd2, (byte)0x23,
            (byte)0x9e, (byte)0xf9, (byte)0xf1, (byte)0x14, (byte)0x16, (byte)0xc8, (byte)0x87,
            (byte)0xf2, (byte)0x17, (byte)0xdf, (byte)0xc6, (byte)0xe4, (byte)0x1c, (byte)0x74,
            (byte)0x74, (byte)0xb0, (byte)0xbb, (byte)0x40, (byte)0xeb, (byte)0xa6, (byte)0xb2,
            (byte)0x5b, (byte)0x6d, (byte)0xf5, (byte)0x9a, (byte)0x85, (byte)0xf1, (byte)0x73,
            (byte)0x84, (byte)0xec, (byte)0xdb, (byte)0x9b, (byte)0xf9, (byte)0xf8, (byte)0x3d,
            (byte)0xba, (byte)0xeb, (byte)0xd7, (byte)0x6c, (byte)0x45, (byte)0x7b, (byte)0xca,
            (byte)0x12, (byte)0x67, (byte)0x5f, (byte)0xcd, (byte)0x02, (byte)0x41, (byte)0x00,
            (byte)0xe3, (byte)0x10, (byte)0x5b, (byte)0xd0, (byte)0xad, (byte)0x59, (byte)0x90,
            (byte)0x18, (byte)0x17, (byte)0xdc, (byte)0x68, (byte)0xd4, (byte)0x75, (byte)0x55,
            (byte)0xab, (byte)0x7d, (byte)0xd1, (byte)0xb5, (byte)0x5a, (byte)0xc4, (byte)0xb0,
            (byte)0x2d, (byte)0xa9, (byte)0xd1, (byte)0x6f, (byte)0xe9, (byte)0x21, (byte)0x4a,
            (byte)0x27, (byte)0xc4, (byte)0x98, (byte)0x89, (byte)0xfa, (byte)0x65, (byte)0xb6,
            (byte)0x10, (byte)0x5d, (byte)0x66, (byte)0xdd, (byte)0x17, (byte)0xb3, (byte)0xf3,
            (byte)0xd3, (byte)0xe3, (byte)0xa0, (byte)0x1a, (byte)0x93, (byte)0xe4, (byte)0xfb,
            (byte)0x88, (byte)0xa7, (byte)0x3b, (byte)0x97, (byte)0x1b, (byte)0xf1, (byte)0x08,
            (byte)0x0c, (byte)0x66, (byte)0xd0, (byte)0x86, (byte)0x5e, (byte)0x39, (byte)0xf9,
            (byte)0xc1, (byte)0x02, (byte)0x40, (byte)0x24, (byte)0x7c, (byte)0xcd, (byte)0x3a,
            (byte)0x8b, (byte)0xdd, (byte)0x3e, (byte)0x86, (byte)0x92, (byte)0xae, (byte)0xc6,
            (byte)0xb0, (byte)0xba, (byte)0xbc, (byte)0xa3, (byte)0x89, (byte)0x41, (byte)0xae,
            (byte)0x57, (byte)0x5d, (byte)0xef, (byte)0xa0, (byte)0x77, (byte)0x89, (byte)0xe1,
            (byte)0xd6, (byte)0x34, (byte)0xef, (byte)0x89, (byte)0x30, (byte)0x99, (byte)0x5b,
            (byte)0x5f, (byte)0x66, (byte)0xb7, (byte)0x32, (byte)0x77, (byte)0x6c, (byte)0x07,
            (byte)0xfb, (byte)0x3d, (byte)0x33, (byte)0x15, (byte)0x38, (byte)0x0b, (byte)0x35,
            (byte)0x30, (byte)0x4a, (byte)0xbe, (byte)0x35, (byte)0x96, (byte)0xba, (byte)0x84,
            (byte)0x9d, (byte)0x2f, (byte)0x58, (byte)0xe2, (byte)0x72, (byte)0x49, (byte)0xb2,
            (byte)0x34, (byte)0xf9, (byte)0xeb, (byte)0x61, (byte)0x02, (byte)0x40, (byte)0x2a,
            (byte)0xd4, (byte)0x89, (byte)0x1d, (byte)0x21, (byte)0xb5, (byte)0xc5, (byte)0x32,
            (byte)0x66, (byte)0x3d, (byte)0xd3, (byte)0x20, (byte)0x50, (byte)0x49, (byte)0xaa,
            (byte)0xa1, (byte)0x7f, (byte)0x0f, (byte)0x20, (byte)0x61, (byte)0xfd, (byte)0x81,
            (byte)0x7f, (byte)0x88, (byte)0xdb, (byte)0xfd, (byte)0x33, (byte)0xa4, (byte)0x53,
            (byte)0x40, (byte)0x08, (byte)0x2d, (byte)0xee, (byte)0xa7, (byte)0x84, (byte)0xe2,
            (byte)0x2d, (byte)0x5c, (byte)0x1b, (byte)0xd4, (byte)0x3e, (byte)0xc3, (byte)0x7d,
            (byte)0x72, (byte)0x70, (byte)0x5e, (byte)0xd3, (byte)0x0a, (byte)0xdc, (byte)0x4f,
            (byte)0x78, (byte)0x8c, (byte)0x0b, (byte)0x02, (byte)0xe0, (byte)0x42, (byte)0x4e,
            (byte)0x64, (byte)0x8e, (byte)0x6c, (byte)0xea, (byte)0x15, (byte)0x31, (byte)0x81,
            (byte)0x02, (byte)0x40, (byte)0x57, (byte)0x72, (byte)0xb9, (byte)0x78, (byte)0xc0,
            (byte)0x1f, (byte)0x5b, (byte)0x1d, (byte)0xb2, (byte)0xcf, (byte)0x94, (byte)0x42,
            (byte)0xed, (byte)0xbd, (byte)0xe7, (byte)0xaa, (byte)0x14, (byte)0x56, (byte)0xd0,
            (byte)0x94, (byte)0x25, (byte)0x30, (byte)0x87, (byte)0x35, (byte)0x82, (byte)0xa0,
            (byte)0x42, (byte)0xb5, (byte)0x7f, (byte)0x66, (byte)0x77, (byte)0xb0, (byte)0x13,
            (byte)0xbe, (byte)0x57, (byte)0x06, (byte)0x7e, (byte)0x50, (byte)0x67, (byte)0x13,
            (byte)0xa7, (byte)0x09, (byte)0xac, (byte)0xd6, (byte)0xbf, (byte)0x22, (byte)0x74,
            (byte)0x6b, (byte)0x37, (byte)0x92, (byte)0x2b, (byte)0x91, (byte)0xbd, (byte)0x0a,
            (byte)0xd8, (byte)0x0f, (byte)0x8d, (byte)0x86, (byte)0x4b, (byte)0x20, (byte)0x5e,
            (byte)0x50, (byte)0x60, (byte)0x80
    };

    /*
     * The same key in PEM format.
     * The DER version of this key was created using
     *
     * openssl pkcs8 -topk8 -nocrypt -in key1.pem
     *         -inform PEM -out key1.der -outform DER
     *
     * -----BEGIN RSA PRIVATE KEY-----
     * Proc-Type: 4,ENCRYPTED
     * DEK-Info: DES-EDE3-CBC,370723FFDC1B1CFA
     *
     * KJ20ODBEQujoOpnzNfHNoo5DF/qENhw9IaApChGMj+WhqYuFfKfPQKuRli8sJSEk
     * uoPmEqjJndHz5M5bI7wVxiafv/Up4+SaNKhn/vu6xjx/senJMX8HMUchqfvn0eCd
     * 31NHQeNbQ67O73xGIdltLzwTRsavTu/hwhnnJxiXzXnYtI5HTZUaRbVJQNpdlkNW
     * H91u70lwlT8W2MATBhl3R3wIbRHQG1I0RQX12O04gMfK1PBl9d/tnFOi4ESfth1W
     * e06XV0U12g06V5/UUuicJANvgyf0Pix0xxPr2tqibWeGpFwCvJpNHl4L3tUocydF
     * HYoUKx/r3VSmesnZ1zUMsuO2zXOuLLcwCSFN+73GBLWocCxBvag6HFvCemy5Tuhs
     * 9MhfF+5lKER/9Ama/e7C61usaoUhR1OvpGWMfjewrFLCsyWlInscoZ1ad5YtcWGx
     * MM7+BsTnK00fcXZuPHTPsiwQ0fMVeNM2a/e65aIivfzzHmb6gqUigNpfNYcqQsJJ
     * Wwoc5hXVO92vugdHOHOiAUpfZZgNDZwgCTluMuI+KJ0QCb0dhF5w/TDA8z+vRwmW
     * sz5WrA4F+T3LfwwLQfxJyHTnbAu38VlMMZP98iIobOX3AAkBw4+kTOCEedvmKt0f
     * s7iSKrnnV6AyzRPEJUWknMF8xNFH7HDqkZf4Mv8cMM6e45K4kBGd17d3tcEFi2An
     * 5l6S9hHtoyMhHjnAcyuHJbD9rGRgyOlbhSYTcbX/gKiECZj0kf8xHi20qntO3c+p
     * jdpp97fIMnQTl5IDNxOy5h9MDLs/SYAR7iyF19RkIGc=
     * -----END RSA PRIVATE KEY-----
     */

    /*
       Certificate:
           Data:
               Version: 3 (0x2)
               Serial Number: 2 (0x2)
               Signature Algorithm: sha1WithRSAEncryption
               Issuer: C=AN, ST=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Validity
                   Not Before: Mar 20 17:02:32 2009 GMT
                   Not After : Mar 20 17:02:32 2010 GMT
               Subject: C=AN, ST=Android, L=Android, O=Android, OU=Android, CN=Android/emailAddress=android@android.com
               Subject Public Key Info:
                   Public Key Algorithm: rsaEncryption
                   RSA Public Key: (1024 bit)
                       Modulus (1024 bit):
                           00:b4:c5:ed:df:30:42:6d:8b:af:4b:e4:9c:13:5e:
                           83:23:cd:2f:ce:34:e2:43:d7:6c:72:bb:03:b3:b9:
                           24:02:e0:cc:b5:8d:d6:92:41:04:2b:5c:94:b2:c3:
                           9c:9d:56:f0:99:bc:0f:81:af:eb:54:ed:80:a6:a0:
                           c7:c2:43:05:04:7c:9c:7e:07:03:10:b9:bd:c5:16:
                           cf:19:dd:e3:4f:73:83:72:c5:66:e4:5b:14:c4:96:
                           d1:e3:24:0b:b6:d4:f7:84:2e:b1:e7:93:02:9d:f5:
                           da:aa:c1:d9:cc:5e:36:e9:8f:bf:8b:da:a7:45:82:
                           f2:b0:f5:a7:e4:e1:80:a3:17
                       Exponent: 65537 (0x10001)
               X509v3 extensions:
                   X509v3 Basic Constraints:
                       CA:FALSE
                   Netscape Comment:
                       OpenSSL Generated Certificate
                   X509v3 Subject Key Identifier:
                       3B:5B:3D:DB:45:F5:8F:58:70:0B:FC:70:3E:31:2B:43:63:A9:FE:2B
                   X509v3 Authority Key Identifier:
                       keyid:E7:9B:7D:90:29:EA:90:0B:7F:08:41:76:4E:41:23:E8:43:2C:A9:03

           Signature Algorithm: sha1WithRSAEncryption
               1c:7f:93:1c:59:21:88:15:45:4b:e0:9c:78:3a:88:3e:55:19:
               86:31:e8:53:3d:74:e2:4a:34:9f:92:17:4e:13:46:92:54:f8:
               43:eb:5e:03:4f:14:51:61:d2:04:b8:04:5a:31:eb:14:6a:18:
               b0:20:03:92:0c:7f:07:c4:1b:f9:9e:7f:5f:ec:03:7a:c8:e3:
               df:d3:94:6e:68:8a:3a:3d:e4:61:f3:e0:87:5d:40:d8:cb:99:
               4d:9a:7b:bc:95:7c:d2:9d:b7:04:9a:9a:63:89:cd:39:ec:32:
               60:0a:97:da:e9:50:a5:73:4a:a2:aa:9c:9b:a8:7f:5a:20:d6:
               48:bd
     */
    String certificate3 = "-----BEGIN CERTIFICATE-----\n"
            + "MIIC9jCCAl+gAwIBAgIBAjANBgkqhkiG9w0BAQUFADB5MQswCQYDVQQGEwJBTjEQ\n"
            + "MA4GA1UECBMHQW5kcm9pZDEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5k\n"
            + "cm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBh\n"
            + "bmRyb2lkLmNvbTAeFw0wOTAzMjAxNzAyMzJaFw0xMDAzMjAxNzAyMzJaMIGLMQsw\n"
            + "CQYDVQQGEwJBTjEQMA4GA1UECBMHQW5kcm9pZDEQMA4GA1UEBxMHQW5kcm9pZDEQ\n"
            + "MA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5k\n"
            + "cm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCBnzANBgkq\n"
            + "hkiG9w0BAQEFAAOBjQAwgYkCgYEAtMXt3zBCbYuvS+ScE16DI80vzjTiQ9dscrsD\n"
            + "s7kkAuDMtY3WkkEEK1yUssOcnVbwmbwPga/rVO2ApqDHwkMFBHycfgcDELm9xRbP\n"
            + "Gd3jT3ODcsVm5FsUxJbR4yQLttT3hC6x55MCnfXaqsHZzF426Y+/i9qnRYLysPWn\n"
            + "5OGAoxcCAwEAAaN7MHkwCQYDVR0TBAIwADAsBglghkgBhvhCAQ0EHxYdT3BlblNT\n"
            + "TCBHZW5lcmF0ZWQgQ2VydGlmaWNhdGUwHQYDVR0OBBYEFDtbPdtF9Y9YcAv8cD4x\n"
            + "K0Njqf4rMB8GA1UdIwQYMBaAFOebfZAp6pALfwhBdk5BI+hDLKkDMA0GCSqGSIb3\n"
            + "DQEBBQUAA4GBABx/kxxZIYgVRUvgnHg6iD5VGYYx6FM9dOJKNJ+SF04TRpJU+EPr\n"
            + "XgNPFFFh0gS4BFox6xRqGLAgA5IMfwfEG/mef1/sA3rI49/TlG5oijo95GHz4Idd\n"
            + "QNjLmU2ae7yVfNKdtwSammOJzTnsMmAKl9rpUKVzSqKqnJuof1og1ki9\n"
            + "-----END CERTIFICATE-----";

    ByteArrayInputStream certArray3 = new ByteArrayInputStream(certificate3
            .getBytes());

    /*
     * The key in DER format.
     * Below is the same key in PEM format as reference
     */
    byte[] key3Bytes = new byte[] {
            (byte)0x30, (byte)0x82, (byte)0x02, (byte)0x76, (byte)0x02, (byte)0x01, (byte)0x00,
            (byte)0x30, (byte)0x0d, (byte)0x06, (byte)0x09, (byte)0x2a, (byte)0x86, (byte)0x48,
            (byte)0x86, (byte)0xf7, (byte)0x0d, (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x05,
            (byte)0x00, (byte)0x04, (byte)0x82, (byte)0x02, (byte)0x60, (byte)0x30, (byte)0x82,
            (byte)0x02, (byte)0x5c, (byte)0x02, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x81,
            (byte)0x81, (byte)0x00, (byte)0xb4, (byte)0xc5, (byte)0xed, (byte)0xdf, (byte)0x30,
            (byte)0x42, (byte)0x6d, (byte)0x8b, (byte)0xaf, (byte)0x4b, (byte)0xe4, (byte)0x9c,
            (byte)0x13, (byte)0x5e, (byte)0x83, (byte)0x23, (byte)0xcd, (byte)0x2f, (byte)0xce,
            (byte)0x34, (byte)0xe2, (byte)0x43, (byte)0xd7, (byte)0x6c, (byte)0x72, (byte)0xbb,
            (byte)0x03, (byte)0xb3, (byte)0xb9, (byte)0x24, (byte)0x02, (byte)0xe0, (byte)0xcc,
            (byte)0xb5, (byte)0x8d, (byte)0xd6, (byte)0x92, (byte)0x41, (byte)0x04, (byte)0x2b,
            (byte)0x5c, (byte)0x94, (byte)0xb2, (byte)0xc3, (byte)0x9c, (byte)0x9d, (byte)0x56,
            (byte)0xf0, (byte)0x99, (byte)0xbc, (byte)0x0f, (byte)0x81, (byte)0xaf, (byte)0xeb,
            (byte)0x54, (byte)0xed, (byte)0x80, (byte)0xa6, (byte)0xa0, (byte)0xc7, (byte)0xc2,
            (byte)0x43, (byte)0x05, (byte)0x04, (byte)0x7c, (byte)0x9c, (byte)0x7e, (byte)0x07,
            (byte)0x03, (byte)0x10, (byte)0xb9, (byte)0xbd, (byte)0xc5, (byte)0x16, (byte)0xcf,
            (byte)0x19, (byte)0xdd, (byte)0xe3, (byte)0x4f, (byte)0x73, (byte)0x83, (byte)0x72,
            (byte)0xc5, (byte)0x66, (byte)0xe4, (byte)0x5b, (byte)0x14, (byte)0xc4, (byte)0x96,
            (byte)0xd1, (byte)0xe3, (byte)0x24, (byte)0x0b, (byte)0xb6, (byte)0xd4, (byte)0xf7,
            (byte)0x84, (byte)0x2e, (byte)0xb1, (byte)0xe7, (byte)0x93, (byte)0x02, (byte)0x9d,
            (byte)0xf5, (byte)0xda, (byte)0xaa, (byte)0xc1, (byte)0xd9, (byte)0xcc, (byte)0x5e,
            (byte)0x36, (byte)0xe9, (byte)0x8f, (byte)0xbf, (byte)0x8b, (byte)0xda, (byte)0xa7,
            (byte)0x45, (byte)0x82, (byte)0xf2, (byte)0xb0, (byte)0xf5, (byte)0xa7, (byte)0xe4,
            (byte)0xe1, (byte)0x80, (byte)0xa3, (byte)0x17, (byte)0x02, (byte)0x03, (byte)0x01,
            (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x81, (byte)0x80, (byte)0x53, (byte)0xbc,
            (byte)0x1f, (byte)0x1c, (byte)0x34, (byte)0x09, (byte)0x81, (byte)0x1e, (byte)0xa3,
            (byte)0xfb, (byte)0x5e, (byte)0x90, (byte)0xa1, (byte)0x34, (byte)0x35, (byte)0x40,
            (byte)0x9f, (byte)0x29, (byte)0xd6, (byte)0xb5, (byte)0x8e, (byte)0x5d, (byte)0x68,
            (byte)0x6a, (byte)0xf6, (byte)0x96, (byte)0x03, (byte)0xf7, (byte)0xfa, (byte)0xf9,
            (byte)0x60, (byte)0x4f, (byte)0xea, (byte)0xe2, (byte)0xea, (byte)0x29, (byte)0x8b,
            (byte)0x23, (byte)0x8c, (byte)0x9f, (byte)0xdd, (byte)0x49, (byte)0x8f, (byte)0xa8,
            (byte)0xa6, (byte)0x62, (byte)0x07, (byte)0x44, (byte)0x79, (byte)0xa1, (byte)0xaf,
            (byte)0xf9, (byte)0x1d, (byte)0x98, (byte)0xbf, (byte)0x85, (byte)0x28, (byte)0x03,
            (byte)0x87, (byte)0x14, (byte)0x20, (byte)0xba, (byte)0xd4, (byte)0x96, (byte)0x61,
            (byte)0x2a, (byte)0xd0, (byte)0xaa, (byte)0x30, (byte)0x19, (byte)0x4b, (byte)0x40,
            (byte)0x35, (byte)0xb0, (byte)0x79, (byte)0x0b, (byte)0x7f, (byte)0xd7, (byte)0xcd,
            (byte)0x64, (byte)0xd9, (byte)0x93, (byte)0x38, (byte)0xe2, (byte)0x59, (byte)0xe0,
            (byte)0x9e, (byte)0x3a, (byte)0x25, (byte)0x27, (byte)0xa2, (byte)0xd9, (byte)0x20,
            (byte)0xb0, (byte)0x45, (byte)0x5f, (byte)0x6c, (byte)0x15, (byte)0x6f, (byte)0x10,
            (byte)0x55, (byte)0xa7, (byte)0xf9, (byte)0x3d, (byte)0x92, (byte)0x3c, (byte)0x7c,
            (byte)0x23, (byte)0x1b, (byte)0xc0, (byte)0xb5, (byte)0x17, (byte)0x41, (byte)0x5e,
            (byte)0x8c, (byte)0xdc, (byte)0x25, (byte)0x1d, (byte)0x35, (byte)0x2b, (byte)0xd3,
            (byte)0x97, (byte)0x1a, (byte)0x6f, (byte)0xae, (byte)0xeb, (byte)0xf5, (byte)0xf9,
            (byte)0x02, (byte)0x41, (byte)0x00, (byte)0xd7, (byte)0x3e, (byte)0xed, (byte)0x70,
            (byte)0xfe, (byte)0xee, (byte)0x0e, (byte)0x30, (byte)0x29, (byte)0xfa, (byte)0xd7,
            (byte)0x38, (byte)0xcf, (byte)0x8e, (byte)0xc1, (byte)0x9c, (byte)0x78, (byte)0x06,
            (byte)0x2d, (byte)0xda, (byte)0x33, (byte)0x58, (byte)0xa1, (byte)0x7b, (byte)0xbf,
            (byte)0x00, (byte)0xb9, (byte)0xdf, (byte)0xea, (byte)0x65, (byte)0x86, (byte)0xbb,
            (byte)0xcc, (byte)0x83, (byte)0xce, (byte)0xde, (byte)0xc3, (byte)0xf8, (byte)0x89,
            (byte)0xf5, (byte)0x9f, (byte)0xa6, (byte)0x1d, (byte)0xc9, (byte)0xfb, (byte)0x98,
            (byte)0xa1, (byte)0x2e, (byte)0xe0, (byte)0x57, (byte)0x6e, (byte)0xbd, (byte)0x57,
            (byte)0x20, (byte)0xf9, (byte)0x6b, (byte)0x13, (byte)0x42, (byte)0x9d, (byte)0x8d,
            (byte)0x66, (byte)0x4d, (byte)0x7a, (byte)0x2d, (byte)0x02, (byte)0x41, (byte)0x00,
            (byte)0xd7, (byte)0x00, (byte)0x18, (byte)0x54, (byte)0xe8, (byte)0x37, (byte)0xdb,
            (byte)0xf8, (byte)0x98, (byte)0x7b, (byte)0x18, (byte)0x33, (byte)0xf6, (byte)0x28,
            (byte)0xa8, (byte)0x8c, (byte)0xd9, (byte)0xfd, (byte)0x4c, (byte)0x4e, (byte)0x41,
            (byte)0x73, (byte)0x2e, (byte)0x79, (byte)0x31, (byte)0xcc, (byte)0x7d, (byte)0x42,
            (byte)0xb7, (byte)0xa1, (byte)0xd2, (byte)0xbc, (byte)0x1f, (byte)0x62, (byte)0xcf,
            (byte)0x15, (byte)0x7c, (byte)0x62, (byte)0x97, (byte)0x70, (byte)0xf1, (byte)0x15,
            (byte)0xf1, (byte)0x33, (byte)0xa1, (byte)0x9d, (byte)0xbb, (byte)0x5f, (byte)0xd7,
            (byte)0x5a, (byte)0xf9, (byte)0x24, (byte)0x58, (byte)0xac, (byte)0x86, (byte)0x6a,
            (byte)0xed, (byte)0xd4, (byte)0x84, (byte)0xe4, (byte)0x3f, (byte)0xfe, (byte)0xb0,
            (byte)0xd3, (byte)0x02, (byte)0x41, (byte)0x00, (byte)0xd4, (byte)0xb7, (byte)0x84,
            (byte)0xb2, (byte)0x39, (byte)0xce, (byte)0x0b, (byte)0x49, (byte)0x80, (byte)0x03,
            (byte)0x3c, (byte)0xb5, (byte)0x11, (byte)0x32, (byte)0x34, (byte)0x96, (byte)0xac,
            (byte)0x6a, (byte)0xf6, (byte)0xdf, (byte)0x80, (byte)0x04, (byte)0xe4, (byte)0x39,
            (byte)0xc6, (byte)0x0e, (byte)0x32, (byte)0xa3, (byte)0x5e, (byte)0x23, (byte)0x0d,
            (byte)0x9f, (byte)0x04, (byte)0xc3, (byte)0x72, (byte)0x2a, (byte)0xe6, (byte)0xa2,
            (byte)0xf5, (byte)0xbc, (byte)0x3f, (byte)0x15, (byte)0x4c, (byte)0xb5, (byte)0x33,
            (byte)0x26, (byte)0xa8, (byte)0x8c, (byte)0x09, (byte)0xfb, (byte)0x7e, (byte)0x1e,
            (byte)0x32, (byte)0x40, (byte)0x0d, (byte)0x1d, (byte)0xcb, (byte)0x7f, (byte)0xf6,
            (byte)0xf2, (byte)0x29, (byte)0x9b, (byte)0x01, (byte)0xd5, (byte)0x02, (byte)0x40,
            (byte)0x24, (byte)0x26, (byte)0x1c, (byte)0xf1, (byte)0x31, (byte)0xb6, (byte)0x2a,
            (byte)0xa3, (byte)0x0a, (byte)0xa8, (byte)0x2f, (byte)0xb2, (byte)0x94, (byte)0xe1,
            (byte)0xd3, (byte)0x2d, (byte)0x13, (byte)0x7d, (byte)0xd6, (byte)0x35, (byte)0x96,
            (byte)0x25, (byte)0x92, (byte)0x9b, (byte)0xc7, (byte)0xf6, (byte)0xb4, (byte)0xdc,
            (byte)0xe1, (byte)0xd9, (byte)0x30, (byte)0x80, (byte)0x76, (byte)0xda, (byte)0x7b,
            (byte)0x2d, (byte)0x06, (byte)0xa3, (byte)0xe1, (byte)0x08, (byte)0x99, (byte)0x50,
            (byte)0x72, (byte)0x24, (byte)0x97, (byte)0x38, (byte)0xd9, (byte)0x07, (byte)0x4d,
            (byte)0x43, (byte)0x3b, (byte)0x7e, (byte)0x93, (byte)0xf6, (byte)0x36, (byte)0x07,
            (byte)0x86, (byte)0x83, (byte)0x63, (byte)0xf0, (byte)0xa8, (byte)0x9d, (byte)0xdf,
            (byte)0x07, (byte)0x02, (byte)0x40, (byte)0x3e, (byte)0x58, (byte)0x03, (byte)0xbf,
            (byte)0xea, (byte)0x3e, (byte)0x34, (byte)0x2c, (byte)0xb7, (byte)0xc3, (byte)0x09,
            (byte)0xe9, (byte)0xf4, (byte)0x43, (byte)0x41, (byte)0xc4, (byte)0x7c, (byte)0x6e,
            (byte)0x75, (byte)0x72, (byte)0x5d, (byte)0xfc, (byte)0xa3, (byte)0x75, (byte)0x1d,
            (byte)0xa0, (byte)0xee, (byte)0xc2, (byte)0x1f, (byte)0x71, (byte)0xb0, (byte)0xf3,
            (byte)0x1d, (byte)0xec, (byte)0x81, (byte)0xdb, (byte)0x45, (byte)0xe5, (byte)0x6a,
            (byte)0xe8, (byte)0xe0, (byte)0x64, (byte)0x90, (byte)0xff, (byte)0xb9, (byte)0xf8,
            (byte)0x12, (byte)0xed, (byte)0x55, (byte)0x5c, (byte)0x9b, (byte)0x81, (byte)0xcd,
            (byte)0xbb, (byte)0x06, (byte)0x91, (byte)0xfe, (byte)0x27, (byte)0x2c, (byte)0x3a,
            (byte)0xed, (byte)0x96, (byte)0x3b, (byte)0xfe
    };

    /*
     * The same key in PEM format.
     * The DER version of this key was created using
     *
     * openssl pkcs8 -topk8 -nocrypt -in key1.pem
     *         -inform PEM -out key1.der -outform DER
     *
     * -----BEGIN RSA PRIVATE KEY-----
     * Proc-Type: 4,ENCRYPTED
     * DEK-Info: DES-EDE3-CBC,0EE6B33EC2D92297
     *
     * r7lbWwtlmubgMG020XiOStqgrvPkP1hTrbOV7Gh2IVNTyXWyA8UriQlPyqBQNzy2
     * 5+Z+JUqzYoLCGY0fQ95ck+ya/wHJQX4OSKFOZwQKpU7pEY9wN1YPa7U9ZnyCPGtB
     * +ejvHuIMJhE5wq9Y1iEDIlON++onWTf4T36Sz3OQ8gEJbnx3x+UjcCINooj7kOeM
     * giCi5yJEOJaf4fkRioUh6S7cm/msTH3ID33rrvTjk7cD8mGzzTy4hWyKaK4K9GbC
     * dOvSORM9mVwTWMUdu1wJ5uyadwBhpSIhC/qpP8Je60nFy8YJlzB2FaMUpAuIOM7B
     * EVN2uAMDNOpGzcOJPbLig8smk2lA4+y1T3gFd9paskSjD9B8+/3KuagWEEQQL7T4
     * YK3xtjzXwEp6OdG2QjD4ZcK5D0MKuYPF3PszwzlCnBG/On6wIvIiTPWBn/G2u59D
     * gJPV7V3Jipn0iYYN+i7T5TNoT7Vko8s3BRpVSrlFUFFhtQPad6NcxGNNH5L1g3fF
     * +dp4TnG64PCQZtuu6I6gfuMXztOwQtEpxxHo9WktlCpwL0tT/tpx+zOVbLvgusjB
     * QKYCIplbSI7VtpOfcJ3kTTAWSOGZli4FayB/Dplf/FXN6ZwwASw09ioVQc/CFdLk
     * Xw05elxV8/AFvm+/VkUHK5JJSp32WMgAJA+XrUsOb5lw1Tl3Hlj9KHALp+Pt/i7N
     * +LPnxrpuTry31APt8aRup/pWOLa+f97Hz+arp4wJa5LK+GtTTtoI4+QZp5qzR/jy
     * oM+DoKtK+1WsCU7teJwEWXV/ayo1TEFEhcY0F7IAPCzDlG3XOFmulQ==
     * -----END RSA PRIVATE KEY-----
     */

    @Override
    protected void setUp() {
        String defAlg = KeyManagerFactory.getDefaultAlgorithm();
        try {
            factory = KeyManagerFactory.getInstance(defAlg);
        } catch (NoSuchAlgorithmException e) {
            fail("could not get default KeyManagerFactory");
        }
    }

    void init(String name) {
      keyType = name;
      try {
          CertificateFactory cf = CertificateFactory.getInstance("X.509");
          KeyFactory kf = KeyFactory.getInstance("RSA");
          keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
          keyTest.load(null, "1234".toCharArray());
          if (keyType.equals(CLIENT)) {
              keys = new PrivateKey[3];
              keys[0] = kf.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
              keys[1] = kf.generatePrivate(new PKCS8EncodedKeySpec(key2Bytes));
              keys[2] = kf.generatePrivate(new PKCS8EncodedKeySpec(key3Bytes));
              cert = new X509Certificate[3];
              cert[0] = (X509Certificate) cf.generateCertificate(certArray);
              cert[1] = (X509Certificate) cf.generateCertificate(certArray2);
              cert[2] = (X509Certificate) cf.generateCertificate(certArray3);
              keyTest.setKeyEntry("clientKey_01", keys[0], PASSWORD, new X509Certificate[] {cert[0]});
              keyTest.setKeyEntry("clientKey_02", keys[1], PASSWORD, new X509Certificate[] {cert[0], cert[1]});
              keyTest.setKeyEntry("clientKey_03", keys[2], PASSWORD, new X509Certificate[] {cert[0], cert[2]});
              keyTest.setCertificateEntry("clientAlias_01", cert[0]);
              keyTest.setCertificateEntry("clientAlias_02", cert[0]);
              keyTest.setCertificateEntry("clientAlias_03", cert[1]);
          } else if (keyType.equals(SERVER)) {
              keys = new PrivateKey[1];
              keys[0] = kf.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
              cert = new X509Certificate[1];
              cert[0] = (X509Certificate) cf.generateCertificate(certArray3);
              keyTest.setKeyEntry("serverKey_00", keys[0], PASSWORD, new X509Certificate[] {cert[0]});
              keyTest.setCertificateEntry("serverAlias_00", cert[0]);
          }
      } catch (Exception ex) {
          ex.printStackTrace();
          throw new IllegalArgumentException(ex.getMessage());
      }
      try {
        factory.init(keyTest, "1234".toCharArray());
      } catch (Exception e) {
        fail("Could't init the KeyManagerFactory");
      }
      manager = (X509KeyManager) factory.getKeyManagers()[0];
    }

    /**
     * X509KeyManager#getClientAliases(String keyType, Principal[] issuers)
     */
    public void test_getClientAliases() {
        init(CLIENT);
        assertNull(manager.getClientAliases(null, null));
        assertNull(manager.getClientAliases("", null));
        String[] resArray = manager.getClientAliases(TYPE_RSA, null);
        assertNotNull(resArray);
        assertEquals(3, resArray.length);
        assertKnownAliases(resArray);
    }

    /**
     * X509KeyManager#chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket)
     */
    public void test_chooseClientAlias() {
        init(CLIENT);
        assertNull(manager.chooseClientAlias(null, null, new Socket()));
        assertNull(manager.chooseClientAlias(new String[0], null, new Socket()));
        assertNull(manager.chooseClientAlias(new String[]{"BOGUS"}, null, new Socket()));
        String res = manager.chooseClientAlias(new String[]{TYPE_RSA}, null, null);
        assertNotNull(res);
        assertKnownAlias(res);
    }

    /**
     * X509KeyManager#getServerAliases(String keyType, Principal[] issuers)
     */
    public void test_getServerAliases() {
        init(SERVER);
        assertNull(manager.getServerAliases(null, null));
        assertNull(manager.getServerAliases("", null));
        String[] resArray = manager.getServerAliases(TYPE_RSA, null);
        assertNotNull(resArray);
        assertEquals("Incorrect length", 1, resArray.length);
        assertEquals("Incorrect aliase", "serverkey_00", resArray[0].toLowerCase());
    }

    /**
     * X509KeyManager#chooseServerAlias(String keyType, Principal[] issuers, Socket socket)
     */
    public void test_chooseServerAlias() {
        init(SERVER);
        assertNull(manager.chooseServerAlias(null, null, new Socket()));
        assertNull(manager.chooseServerAlias("", null, new Socket()));
        String res = manager.chooseServerAlias(TYPE_RSA, null, null);
        assertNotNull(res);
        assertEquals("serverkey_00", res.toLowerCase());
        res = manager.chooseServerAlias(TYPE_RSA, null, new Socket());
        assertNotNull(res);
        assertEquals("serverkey_00", res.toLowerCase());
    }

    /**
     * X509KeyManager#getCertificateChain(String alias)
     */
    public void test_getCertificateChain() {
        init(SERVER);
        assertNull("Not NULL for NULL parameter", manager.getCertificateChain(null));
        assertNull("Not NULL for empty parameter",manager.getCertificateChain(""));
        assertNull("Not NULL for clientAlias_01 parameter", manager.getCertificateChain("clientAlias_01"));
        assertNull("Not NULL for serverAlias_00 parameter", manager.getCertificateChain("serverAlias_00"));
    }

    /**
     * X509KeyManager#getPrivateKey(String alias)
     */
    public void test_getPrivateKey() {
        init(CLIENT);
        assertNull("Not NULL for NULL parameter", manager.getPrivateKey(null));
        assertNull("Not NULL for serverAlias_00 parameter", manager.getPrivateKey("serverAlias_00"));
        assertNull("Not NULL for clientAlias_02 parameter", manager.getPrivateKey("clientAlias_02"));
    }


    private void assertKnownAliases(String[] aliases) {
        for (String alias : aliases) {
            assertKnownAlias(alias);
        }
    }

    private void assertKnownAlias(String alias) {
        String a = alias.toLowerCase();
        boolean okay = a.equals("clientkey_01") || a.equals("clientkey_02") || a.equals("clientkey_03");
        assertTrue("Expected one of clientkey_01, clientkey_02, clientkey_03. Received: " + alias, okay);
    }
}

