/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.targets.security.cert;

import dalvik.annotation.AndroidOnly;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;

public class CertificateTest extends TestCase {

    /*
     * Following certificate chain was taken from https://www.verisign.com and
     * uses MD2withRSA for the root certificate. This chain stops validating
     * in Nov 2016.
     */

    /**
     * A selfsigned certificate using MD2withRSA
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 1 (0x0)
     *         Serial Number:
     *             70:ba:e4:1d:10:d9:29:34:b6:38:ca:7b:03:cc:ba:bf
     *         Signature Algorithm: md2WithRSAEncryption
     *         Issuer: C=US, O=VeriSign, Inc., OU=Class 3 Public Primary Certification Authority
     *         Validity
     *             Not Before: Jan 29 00:00:00 1996 GMT
     *             Not After : Aug  1 23:59:59 2028 GMT
     *         Subject: C=US, O=VeriSign, Inc., OU=Class 3 Public Primary Certification Authority
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (1024 bit)
     *                 Modulus (1024 bit):
     *                     00:c9:5c:59:9e:f2:1b:8a:01:14:b4:10:df:04:40:
     *                     db:e3:57:af:6a:45:40:8f:84:0c:0b:d1:33:d9:d9:
     *                     11:cf:ee:02:58:1f:25:f7:2a:a8:44:05:aa:ec:03:
     *                     1f:78:7f:9e:93:b9:9a:00:aa:23:7d:d6:ac:85:a2:
     *                     63:45:c7:72:27:cc:f4:4c:c6:75:71:d2:39:ef:4f:
     *                     42:f0:75:df:0a:90:c6:8e:20:6f:98:0f:f8:ac:23:
     *                     5f:70:29:36:a4:c9:86:e7:b1:9a:20:cb:53:a5:85:
     *                     e7:3d:be:7d:9a:fe:24:45:33:dc:76:15:ed:0f:a2:
     *                     71:64:4c:65:2e:81:68:45:a7
     *                 Exponent: 65537 (0x10001)
     *     Signature Algorithm: md2WithRSAEncryption
     *         bb:4c:12:2b:cf:2c:26:00:4f:14:13:dd:a6:fb:fc:0a:11:84:
     *         8c:f3:28:1c:67:92:2f:7c:b6:c5:fa:df:f0:e8:95:bc:1d:8f:
     *         6c:2c:a8:51:cc:73:d8:a4:c0:53:f0:4e:d6:26:c0:76:01:57:
     *         81:92:5e:21:f1:d1:b1:ff:e7:d0:21:58:cd:69:17:e3:44:1c:
     *         9c:19:44:39:89:5c:dc:9c:00:0f:56:8d:02:99:ed:a2:90:45:
     *         4c:e4:bb:10:a4:3d:f0:32:03:0e:f1:ce:f8:e8:c9:51:8c:e6:
     *         62:9f:e6:9f:c0:7d:b7:72:9c:c9:36:3a:6b:9f:4e:a8:ff:64:
     *         0d:64
     * </pre>
     */
    private static final String selfSignedCertMD2 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIICPDCCAaUCEHC65B0Q2Sk0tjjKewPMur8wDQYJKoZIhvcNAQECBQAwXzELMAkG\n"
        + "A1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMTcwNQYDVQQLEy5DbGFz\n"
        + "cyAzIFB1YmxpYyBQcmltYXJ5IENlcnRpZmljYXRpb24gQXV0aG9yaXR5MB4XDTk2\n"
        + "MDEyOTAwMDAwMFoXDTI4MDgwMTIzNTk1OVowXzELMAkGA1UEBhMCVVMxFzAVBgNV\n"
        + "BAoTDlZlcmlTaWduLCBJbmMuMTcwNQYDVQQLEy5DbGFzcyAzIFB1YmxpYyBQcmlt\n"
        + "YXJ5IENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIGfMA0GCSqGSIb3DQEBAQUAA4GN\n"
        + "ADCBiQKBgQDJXFme8huKARS0EN8EQNvjV69qRUCPhAwL0TPZ2RHP7gJYHyX3KqhE\n"
        + "BarsAx94f56TuZoAqiN91qyFomNFx3InzPRMxnVx0jnvT0Lwdd8KkMaOIG+YD/is\n"
        + "I19wKTakyYbnsZogy1Olhec9vn2a/iRFM9x2Fe0PonFkTGUugWhFpwIDAQABMA0G\n"
        + "CSqGSIb3DQEBAgUAA4GBALtMEivPLCYATxQT3ab7/AoRhIzzKBxnki98tsX63/Do\n"
        + "lbwdj2wsqFHMc9ikwFPwTtYmwHYBV4GSXiHx0bH/59AhWM1pF+NEHJwZRDmJXNyc\n"
        + "AA9WjQKZ7aKQRUzkuxCkPfAyAw7xzvjoyVGM5mKf5p/AfbdynMk2OmufTqj/ZA1k\n"
        + "-----END CERTIFICATE-----\n";

    /**
     * A certificate signed by selfSignedCertMD2
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 3 (0x2)
     *         Serial Number:
     *             57:bf:fb:03:fb:2c:46:d4:e1:9e:ce:e0:d7:43:7f:13
     *         Signature Algorithm: sha1WithRSAEncryption
     *         Issuer: C=US, O=VeriSign, Inc., OU=Class 3 Public Primary Certification Authority
     *         Validity
     *             Not Before: Nov  8 00:00:00 2006 GMT
     *             Not After : Nov  7 23:59:59 2021 GMT
     *         Subject: C=US, O=VeriSign, Inc., OU=VeriSign Trust Network, OU=(c) 2006 VeriSign, Inc. - For authorized use only, CN=VeriSign Class 3 Public Primary Certification Authority - G5
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (2048 bit)
     *                 Modulus (2048 bit):
     *                     00:af:24:08:08:29:7a:35:9e:60:0c:aa:e7:4b:3b:
     *                     4e:dc:7c:bc:3c:45:1c:bb:2b:e0:fe:29:02:f9:57:
     *                     08:a3:64:85:15:27:f5:f1:ad:c8:31:89:5d:22:e8:
     *                     2a:aa:a6:42:b3:8f:f8:b9:55:b7:b1:b7:4b:b3:fe:
     *                     8f:7e:07:57:ec:ef:43:db:66:62:15:61:cf:60:0d:
     *                     a4:d8:de:f8:e0:c3:62:08:3d:54:13:eb:49:ca:59:
     *                     54:85:26:e5:2b:8f:1b:9f:eb:f5:a1:91:c2:33:49:
     *                     d8:43:63:6a:52:4b:d2:8f:e8:70:51:4d:d1:89:69:
     *                     7b:c7:70:f6:b3:dc:12:74:db:7b:5d:4b:56:d3:96:
     *                     bf:15:77:a1:b0:f4:a2:25:f2:af:1c:92:67:18:e5:
     *                     f4:06:04:ef:90:b9:e4:00:e4:dd:3a:b5:19:ff:02:
     *                     ba:f4:3c:ee:e0:8b:eb:37:8b:ec:f4:d7:ac:f2:f6:
     *                     f0:3d:af:dd:75:91:33:19:1d:1c:40:cb:74:24:19:
     *                     21:93:d9:14:fe:ac:2a:52:c7:8f:d5:04:49:e4:8d:
     *                     63:47:88:3c:69:83:cb:fe:47:bd:2b:7e:4f:c5:95:
     *                     ae:0e:9d:d4:d1:43:c0:67:73:e3:14:08:7e:e5:3f:
     *                     9f:73:b8:33:0a:cf:5d:3f:34:87:96:8a:ee:53:e8:
     *                     25:15
     *                 Exponent: 65537 (0x10001)
     *         X509v3 extensions:
     *             X509v3 Basic Constraints: critical
     *                 CA:TRUE
     *             X509v3 CRL Distribution Points:
     *                 URI:http://crl.verisign.com/pca3.crl
     *             X509v3 Key Usage: critical
     *                 Certificate Sign, CRL Sign
     *             1.3.6.1.5.5.7.1.12:
     *                 0_.].[0Y0W0U..image/gif0!0.0...+..............k...j.H.,{..0%.#http://logo.verisign.com/vslogo.gif
     *             X509v3 Certificate Policies:
     *                 Policy: X509v3 Any Policy
     *                   CPS: https://www.verisign.com/cps
     *             X509v3 Subject Key Identifier:
     *                 7F:D3:65:A7:C2:DD:EC:BB:F0:30:09:F3:43:39:FA:02:AF:33:31:33
     *             X509v3 Extended Key Usage:
     *                 Netscape Server Gated Crypto, 2.16.840.1.113733.1.8.1, TLS Web Server Authentication, TLS Web Client Authentication
     *             X509v3 Authority Key Identifier:
     *                 DirName:/C=US/O=VeriSign, Inc./OU=Class 3 Public Primary Certification Authority
     *                 serial:70:BA:E4:1D:10:D9:29:34:B6:38:CA:7B:03:CC:BA:BF
     *     Signature Algorithm: sha1WithRSAEncryption
     *         a9:7b:66:29:30:f7:d5:b4:a6:96:12:d0:ee:72:f0:58:11:69:
     *         15:55:5f:41:ff:d2:12:84:13:a4:d9:03:66:ff:a9:e0:4c:c9:
     *         ed:8c:72:8b:b4:d7:55:3b:29:15:60:c8:3c:21:ef:44:2e:93:
     *         3d:c6:0b:0c:8d:24:3f:1e:fb:01:5a:7a:dd:83:66:14:d1:c7:
     *         fd:30:53:48:51:85:85:13:a8:54:e1:ee:76:a2:89:18:d3:97:
     *         89:7a:c6:fd:b3:bd:94:61:5a:3a:08:cf:14:93:bd:93:fd:09:
     *         a9:7b:56:c8:00:b8:44:58:e9:de:5b:77:bd:07:1c:6c:0b:30:
     *         30:c7
     * </pre>
     */
    private static final String signedCert1Chain1 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIFEzCCBHygAwIBAgIQV7/7A/ssRtThns7g10N/EzANBgkqhkiG9w0BAQUFADBf\n"
        + "MQswCQYDVQQGEwJVUzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xNzA1BgNVBAsT\n"
        + "LkNsYXNzIDMgUHVibGljIFByaW1hcnkgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkw\n"
        + "HhcNMDYxMTA4MDAwMDAwWhcNMjExMTA3MjM1OTU5WjCByjELMAkGA1UEBhMCVVMx\n"
        + "FzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQLExZWZXJpU2lnbiBUcnVz\n"
        + "dCBOZXR3b3JrMTowOAYDVQQLEzEoYykgMjAwNiBWZXJpU2lnbiwgSW5jLiAtIEZv\n"
        + "ciBhdXRob3JpemVkIHVzZSBvbmx5MUUwQwYDVQQDEzxWZXJpU2lnbiBDbGFzcyAz\n"
        + "IFB1YmxpYyBQcmltYXJ5IENlcnRpZmljYXRpb24gQXV0aG9yaXR5IC0gRzUwggEi\n"
        + "MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCvJAgIKXo1nmAMqudLO07cfLw8\n"
        + "RRy7K+D+KQL5VwijZIUVJ/XxrcgxiV0i6CqqpkKzj/i5Vbext0uz/o9+B1fs70Pb\n"
        + "ZmIVYc9gDaTY3vjgw2IIPVQT60nKWVSFJuUrjxuf6/WhkcIzSdhDY2pSS9KP6HBR\n"
        + "TdGJaXvHcPaz3BJ023tdS1bTlr8Vd6Gw9KIl8q8ckmcY5fQGBO+QueQA5N06tRn/\n"
        + "Arr0PO7gi+s3i+z016zy9vA9r911kTMZHRxAy3QkGSGT2RT+rCpSx4/VBEnkjWNH\n"
        + "iDxpg8v+R70rfk/Fla4OndTRQ8Bnc+MUCH7lP59zuDMKz10/NIeWiu5T6CUVAgMB\n"
        + "AAGjggHeMIIB2jAPBgNVHRMBAf8EBTADAQH/MDEGA1UdHwQqMCgwJqAkoCKGIGh0\n"
        + "dHA6Ly9jcmwudmVyaXNpZ24uY29tL3BjYTMuY3JsMA4GA1UdDwEB/wQEAwIBBjBt\n"
        + "BggrBgEFBQcBDARhMF+hXaBbMFkwVzBVFglpbWFnZS9naWYwITAfMAcGBSsOAwIa\n"
        + "BBSP5dMahqyNjmvDz4Bq1EgYLHsZLjAlFiNodHRwOi8vbG9nby52ZXJpc2lnbi5j\n"
        + "b20vdnNsb2dvLmdpZjA9BgNVHSAENjA0MDIGBFUdIAAwKjAoBggrBgEFBQcCARYc\n"
        + "aHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL2NwczAdBgNVHQ4EFgQUf9Nlp8Ld7Lvw\n"
        + "MAnzQzn6Aq8zMTMwNAYDVR0lBC0wKwYJYIZIAYb4QgQBBgpghkgBhvhFAQgBBggr\n"
        + "BgEFBQcDAQYIKwYBBQUHAwIwgYAGA1UdIwR5MHehY6RhMF8xCzAJBgNVBAYTAlVT\n"
        + "MRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjE3MDUGA1UECxMuQ2xhc3MgMyBQdWJs\n"
        + "aWMgUHJpbWFyeSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eYIQcLrkHRDZKTS2OMp7\n"
        + "A8y6vzANBgkqhkiG9w0BAQUFAAOBgQCpe2YpMPfVtKaWEtDucvBYEWkVVV9B/9IS\n"
        + "hBOk2QNm/6ngTMntjHKLtNdVOykVYMg8Ie9ELpM9xgsMjSQ/HvsBWnrdg2YU0cf9\n"
        + "MFNIUYWFE6hU4e52ookY05eJesb9s72UYVo6CM8Uk72T/Qmpe1bIALhEWOneW3e9\n"
        + "BxxsCzAwxw==\n"
        + "-----END CERTIFICATE-----";

    /**
     * A certificate signed by signedCert1Chain1
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 3 (0x2)
     *         Serial Number:
     *             11:2a:00:6d:37:e5:10:6f:d6:ca:7c:c3:ef:ba:cc:18
     *         Signature Algorithm: sha1WithRSAEncryption
     *         Issuer: C=US, O=VeriSign, Inc., OU=VeriSign Trust Network, OU=(c) 2006 VeriSign, Inc. - For authorized use only, CN=VeriSign Class 3 Public Primary Certification Authority - G5
     *         Validity
     *             Not Before: Nov  8 00:00:00 2006 GMT
     *             Not After : Nov  7 23:59:59 2016 GMT
     *         Subject: C=US, O=VeriSign, Inc., OU=VeriSign Trust Network, OU=Terms of use at https://www.verisign.com/rpa (c)06, CN=VeriSign Class 3 Extended Validation SSL SGC CA
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (2048 bit)
     *                 Modulus (2048 bit):
     *                     00:bd:56:88:ba:88:34:64:64:cf:cd:ca:b0:ee:e7:
     *                     19:73:c5:72:d9:bb:45:bc:b5:a8:ff:83:be:1c:03:
     *                     db:ed:89:b7:2e:10:1a:25:bc:55:ca:41:a1:9f:0b:
     *                     cf:19:5e:70:b9:5e:39:4b:9e:31:1c:5f:87:ae:2a:
     *                     aa:a8:2b:a2:1b:3b:10:23:5f:13:b1:dd:08:8c:4e:
     *                     14:da:83:81:e3:b5:8c:e3:68:ed:24:67:ce:56:b6:
     *                     ac:9b:73:96:44:db:8a:8c:b3:d6:f0:71:93:8e:db:
     *                     71:54:4a:eb:73:59:6a:8f:70:51:2c:03:9f:97:d1:
     *                     cc:11:7a:bc:62:0d:95:2a:c9:1c:75:57:e9:f5:c7:
     *                     ea:ba:84:35:cb:c7:85:5a:7e:e4:4d:e1:11:97:7d:
     *                     0e:20:34:45:db:f1:a2:09:eb:eb:3d:9e:b8:96:43:
     *                     5e:34:4b:08:25:1e:43:1a:a2:d9:b7:8a:01:34:3d:
     *                     c3:f8:e5:af:4f:8c:ff:cd:65:f0:23:4e:c5:97:b3:
     *                     5c:da:90:1c:82:85:0d:06:0d:c1:22:b6:7b:28:a4:
     *                     03:c3:4c:53:d1:58:bc:72:bc:08:39:fc:a0:76:a8:
     *                     a8:e9:4b:6e:88:3d:e3:b3:31:25:8c:73:29:48:0e:
     *                     32:79:06:ed:3d:43:f4:f6:e4:e9:fc:7d:be:8e:08:
     *                     d5:1f
     *                 Exponent: 65537 (0x10001)
     *         X509v3 extensions:
     *             X509v3 Subject Key Identifier:
     *                 4E:43:C8:1D:76:EF:37:53:7A:4F:F2:58:6F:94:F3:38:E2:D5:BD:DF
     *             X509v3 Basic Constraints: critical
     *                 CA:TRUE, pathlen:0
     *             X509v3 Certificate Policies:
     *                 Policy: X509v3 Any Policy
     *                   CPS: https://www.verisign.com/cps
     *             X509v3 CRL Distribution Points:
     *                 URI:http://EVSecure-crl.verisign.com/pca3-g5.crl
     *             X509v3 Extended Key Usage:
     *                 Netscape Server Gated Crypto, 2.16.840.1.113733.1.8.1
     *             X509v3 Key Usage: critical
     *                 Certificate Sign, CRL Sign
     *             Netscape Cert Type:
     *                 SSL CA, S/MIME CA
     *             1.3.6.1.5.5.7.1.12:
     *                 0_.].[0Y0W0U..image/gif0!0.0...+..............k...j.H.,{..0%.#http://logo.verisign.com/vslogo.gif
     *             X509v3 Subject Alternative Name:
     *                 DirName:/CN=Class3CA2048-1-48
     *             Authority Information Access:
     *                 OCSP - URI:http://EVSecure-ocsp.verisign.com
     *             X509v3 Authority Key Identifier:
     *                 keyid:7F:D3:65:A7:C2:DD:EC:BB:F0:30:09:F3:43:39:FA:02:AF:33:31:33
     *     Signature Algorithm: sha1WithRSAEncryption
     *         5a:a2:b1:bf:eb:8d:d4:38:a8:80:72:c2:dc:38:2e:ac:a7:71:
     *         f9:2b:a3:bb:47:bb:6d:69:6f:10:36:98:8c:c7:56:2e:bb:bc:
     *         ab:4a:9b:7a:d6:f2:82:93:e0:14:fe:8a:ce:83:b7:83:db:93:
     *         87:ab:ac:65:79:49:fd:57:a9:b1:ce:09:1f:ba:10:15:c4:09:
     *         0e:62:e3:f9:0a:25:d5:64:98:f0:f2:a8:0f:76:32:7e:91:e6:
     *         18:ee:bc:e7:da:d0:4e:8d:78:bb:e2:9d:c0:59:2b:c0:ce:95:
     *         0d:24:0c:72:ca:34:5e:70:22:89:2b:4a:b0:f1:68:87:f3:ee:
     *         44:8d:28:40:77:39:6e:48:72:45:31:5d:6b:39:0e:86:02:ea:
     *         66:99:93:31:0f:df:67:de:a6:9f:8c:9d:4c:ce:71:6f:3a:21:
     *         f6:b9:34:3f:f9:6e:d8:9a:f7:3e:da:f3:81:5f:7a:5c:6d:8f:
     *         7c:f6:99:74:b7:ff:e4:17:5d:ed:61:5e:ab:48:bb:96:8d:66:
     *         45:39:b4:12:0a:f6:70:e9:9c:76:22:4b:60:e9:2a:1b:34:49:
     *         f7:a2:d4:67:c0:b1:26:ad:13:ba:d9:84:01:c1:ab:e1:8e:6d:
     *         70:16:3b:77:ac:91:9a:bb:1a:1f:da:58:a7:e4:4f:c1:61:ae:
     *         bc:a2:fe:4b
     * </pre>
     */
    private static final String signedCert2Chain1 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIGCjCCBPKgAwIBAgIQESoAbTflEG/WynzD77rMGDANBgkqhkiG9w0BAQUFADCB\n"
        + "yjELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQL\n"
        + "ExZWZXJpU2lnbiBUcnVzdCBOZXR3b3JrMTowOAYDVQQLEzEoYykgMjAwNiBWZXJp\n"
        + "U2lnbiwgSW5jLiAtIEZvciBhdXRob3JpemVkIHVzZSBvbmx5MUUwQwYDVQQDEzxW\n"
        + "ZXJpU2lnbiBDbGFzcyAzIFB1YmxpYyBQcmltYXJ5IENlcnRpZmljYXRpb24gQXV0\n"
        + "aG9yaXR5IC0gRzUwHhcNMDYxMTA4MDAwMDAwWhcNMTYxMTA3MjM1OTU5WjCBvjEL\n"
        + "MAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQLExZW\n"
        + "ZXJpU2lnbiBUcnVzdCBOZXR3b3JrMTswOQYDVQQLEzJUZXJtcyBvZiB1c2UgYXQg\n"
        + "aHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL3JwYSAoYykwNjE4MDYGA1UEAxMvVmVy\n"
        + "aVNpZ24gQ2xhc3MgMyBFeHRlbmRlZCBWYWxpZGF0aW9uIFNTTCBTR0MgQ0EwggEi\n"
        + "MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC9Voi6iDRkZM/NyrDu5xlzxXLZ\n"
        + "u0W8taj/g74cA9vtibcuEBolvFXKQaGfC88ZXnC5XjlLnjEcX4euKqqoK6IbOxAj\n"
        + "XxOx3QiMThTag4HjtYzjaO0kZ85Wtqybc5ZE24qMs9bwcZOO23FUSutzWWqPcFEs\n"
        + "A5+X0cwRerxiDZUqyRx1V+n1x+q6hDXLx4VafuRN4RGXfQ4gNEXb8aIJ6+s9nriW\n"
        + "Q140SwglHkMaotm3igE0PcP45a9PjP/NZfAjTsWXs1zakByChQ0GDcEitnsopAPD\n"
        + "TFPRWLxyvAg5/KB2qKjpS26IPeOzMSWMcylIDjJ5Bu09Q/T25On8fb6OCNUfAgMB\n"
        + "AAGjggH0MIIB8DAdBgNVHQ4EFgQUTkPIHXbvN1N6T/JYb5TzOOLVvd8wEgYDVR0T\n"
        + "AQH/BAgwBgEB/wIBADA9BgNVHSAENjA0MDIGBFUdIAAwKjAoBggrBgEFBQcCARYc\n"
        + "aHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL2NwczA9BgNVHR8ENjA0MDKgMKAuhixo\n"
        + "dHRwOi8vRVZTZWN1cmUtY3JsLnZlcmlzaWduLmNvbS9wY2EzLWc1LmNybDAgBgNV\n"
        + "HSUEGTAXBglghkgBhvhCBAEGCmCGSAGG+EUBCAEwDgYDVR0PAQH/BAQDAgEGMBEG\n"
        + "CWCGSAGG+EIBAQQEAwIBBjBtBggrBgEFBQcBDARhMF+hXaBbMFkwVzBVFglpbWFn\n"
        + "ZS9naWYwITAfMAcGBSsOAwIaBBSP5dMahqyNjmvDz4Bq1EgYLHsZLjAlFiNodHRw\n"
        + "Oi8vbG9nby52ZXJpc2lnbi5jb20vdnNsb2dvLmdpZjApBgNVHREEIjAgpB4wHDEa\n"
        + "MBgGA1UEAxMRQ2xhc3MzQ0EyMDQ4LTEtNDgwPQYIKwYBBQUHAQEEMTAvMC0GCCsG\n"
        + "AQUFBzABhiFodHRwOi8vRVZTZWN1cmUtb2NzcC52ZXJpc2lnbi5jb20wHwYDVR0j\n"
        + "BBgwFoAUf9Nlp8Ld7LvwMAnzQzn6Aq8zMTMwDQYJKoZIhvcNAQEFBQADggEBAFqi\n"
        + "sb/rjdQ4qIBywtw4Lqyncfkro7tHu21pbxA2mIzHVi67vKtKm3rW8oKT4BT+is6D\n"
        + "t4Pbk4errGV5Sf1XqbHOCR+6EBXECQ5i4/kKJdVkmPDyqA92Mn6R5hjuvOfa0E6N\n"
        + "eLvincBZK8DOlQ0kDHLKNF5wIokrSrDxaIfz7kSNKEB3OW5IckUxXWs5DoYC6maZ\n"
        + "kzEP32fepp+MnUzOcW86Ifa5ND/5btia9z7a84Ffelxtj3z2mXS3/+QXXe1hXqtI\n"
        + "u5aNZkU5tBIK9nDpnHYiS2DpKhs0Sfei1GfAsSatE7rZhAHBq+GObXAWO3eskZq7\n"
        + "Gh/aWKfkT8Fhrryi/ks=\n"
        + "-----END CERTIFICATE-----";

    /*
     * Following certificate chain was taken from https://www.thawte.com and
     * uses MD5withRSA for the root certificate. This chain stops validating
     * in Nov 2016.
     */

    /**
     * A selfsigned certificate using MD5withRSA
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 3 (0x2)
     *         Serial Number: 1 (0x1)
     *         Signature Algorithm: md5WithRSAEncryption
     *         Issuer: C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Premium Server CA/emailAddress=premium-server@thawte.com
     *         Validity
     *             Not Before: Aug  1 00:00:00 1996 GMT
     *             Not After : Dec 31 23:59:59 2020 GMT
     *         Subject: C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Premium Server CA/emailAddress=premium-server@thawte.com
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (1024 bit)
     *                 Modulus (1024 bit):
     *                     00:d2:36:36:6a:8b:d7:c2:5b:9e:da:81:41:62:8f:
     *                     38:ee:49:04:55:d6:d0:ef:1c:1b:95:16:47:ef:18:
     *                     48:35:3a:52:f4:2b:6a:06:8f:3b:2f:ea:56:e3:af:
     *                     86:8d:9e:17:f7:9e:b4:65:75:02:4d:ef:cb:09:a2:
     *                     21:51:d8:9b:d0:67:d0:ba:0d:92:06:14:73:d4:93:
     *                     cb:97:2a:00:9c:5c:4e:0c:bc:fa:15:52:fc:f2:44:
     *                     6e:da:11:4a:6e:08:9f:2f:2d:e3:f9:aa:3a:86:73:
     *                     b6:46:53:58:c8:89:05:bd:83:11:b8:73:3f:aa:07:
     *                     8d:f4:42:4d:e7:40:9d:1c:37
     *                 Exponent: 65537 (0x10001)
     *         X509v3 extensions:
     *             X509v3 Basic Constraints: critical
     *                 CA:TRUE
     *     Signature Algorithm: md5WithRSAEncryption
     *         26:48:2c:16:c2:58:fa:e8:16:74:0c:aa:aa:5f:54:3f:f2:d7:
     *         c9:78:60:5e:5e:6e:37:63:22:77:36:7e:b2:17:c4:34:b9:f5:
     *         08:85:fc:c9:01:38:ff:4d:be:f2:16:42:43:e7:bb:5a:46:fb:
     *         c1:c6:11:1f:f1:4a:b0:28:46:c9:c3:c4:42:7d:bc:fa:ab:59:
     *         6e:d5:b7:51:88:11:e3:a4:85:19:6b:82:4c:a4:0c:12:ad:e9:
     *         a4:ae:3f:f1:c3:49:65:9a:8c:c5:c8:3e:25:b7:94:99:bb:92:
     *         32:71:07:f0:86:5e:ed:50:27:a6:0d:a6:23:f9:bb:cb:a6:07:
     *         14:42
     * </pre>
     */
    private static final String selfSignedCertMD5 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIDJzCCApCgAwIBAgIBATANBgkqhkiG9w0BAQQFADCBzjELMAkGA1UEBhMCWkEx\n"
        + "FTATBgNVBAgTDFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJQ2FwZSBUb3duMR0wGwYD\n"
        + "VQQKExRUaGF3dGUgQ29uc3VsdGluZyBjYzEoMCYGA1UECxMfQ2VydGlmaWNhdGlv\n"
        + "biBTZXJ2aWNlcyBEaXZpc2lvbjEhMB8GA1UEAxMYVGhhd3RlIFByZW1pdW0gU2Vy\n"
        + "dmVyIENBMSgwJgYJKoZIhvcNAQkBFhlwcmVtaXVtLXNlcnZlckB0aGF3dGUuY29t\n"
        + "MB4XDTk2MDgwMTAwMDAwMFoXDTIwMTIzMTIzNTk1OVowgc4xCzAJBgNVBAYTAlpB\n"
        + "MRUwEwYDVQQIEwxXZXN0ZXJuIENhcGUxEjAQBgNVBAcTCUNhcGUgVG93bjEdMBsG\n"
        + "A1UEChMUVGhhd3RlIENvbnN1bHRpbmcgY2MxKDAmBgNVBAsTH0NlcnRpZmljYXRp\n"
        + "b24gU2VydmljZXMgRGl2aXNpb24xITAfBgNVBAMTGFRoYXd0ZSBQcmVtaXVtIFNl\n"
        + "cnZlciBDQTEoMCYGCSqGSIb3DQEJARYZcHJlbWl1bS1zZXJ2ZXJAdGhhd3RlLmNv\n"
        + "bTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA0jY2aovXwlue2oFBYo847kkE\n"
        + "VdbQ7xwblRZH7xhINTpS9CtqBo87L+pW46+GjZ4X9560ZXUCTe/LCaIhUdib0GfQ\n"
        + "ug2SBhRz1JPLlyoAnFxODLz6FVL88kRu2hFKbgifLy3j+ao6hnO2RlNYyIkFvYMR\n"
        + "uHM/qgeN9EJN50CdHDcCAwEAAaMTMBEwDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG\n"
        + "9w0BAQQFAAOBgQAmSCwWwlj66BZ0DKqqX1Q/8tfJeGBeXm43YyJ3Nn6yF8Q0ufUI\n"
        + "hfzJATj/Tb7yFkJD57taRvvBxhEf8UqwKEbJw8RCfbz6q1lu1bdRiBHjpIUZa4JM\n"
        + "pAwSremkrj/xw0llmozFyD4lt5SZu5IycQfwhl7tUCemDaYj+bvLpgcUQg==\n"
        + "-----END CERTIFICATE-----";

    /**
     * A certificate signed by selfSignedCertMD5
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 3 (0x2)
     *         Serial Number:
     *             5f:a6:be:80:b6:86:c6:2f:01:ed:0c:ab:b1:96:a1:05
     *         Signature Algorithm: sha1WithRSAEncryption
     *         Issuer: C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Premium Server CA/emailAddress=premium-server@thawte.com
     *         Validity
     *             Not Before: Nov 17 00:00:00 2006 GMT
     *             Not After : Dec 30 23:59:59 2020 GMT
     *         Subject: C=US, O=thawte, Inc., OU=Certification Services Division, OU=(c) 2006 thawte, Inc. - For authorized use only, CN=thawte Primary Root CA
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (2048 bit)
     *                 Modulus (2048 bit):
     *                     00:ac:a0:f0:fb:80:59:d4:9c:c7:a4:cf:9d:a1:59:
     *                     73:09:10:45:0c:0d:2c:6e:68:f1:6c:5b:48:68:49:
     *                     59:37:fc:0b:33:19:c2:77:7f:cc:10:2d:95:34:1c:
     *                     e6:eb:4d:09:a7:1c:d2:b8:c9:97:36:02:b7:89:d4:
     *                     24:5f:06:c0:cc:44:94:94:8d:02:62:6f:eb:5a:dd:
     *                     11:8d:28:9a:5c:84:90:10:7a:0d:bd:74:66:2f:6a:
     *                     38:a0:e2:d5:54:44:eb:1d:07:9f:07:ba:6f:ee:e9:
     *                     fd:4e:0b:29:f5:3e:84:a0:01:f1:9c:ab:f8:1c:7e:
     *                     89:a4:e8:a1:d8:71:65:0d:a3:51:7b:ee:bc:d2:22:
     *                     60:0d:b9:5b:9d:df:ba:fc:51:5b:0b:af:98:b2:e9:
     *                     2e:e9:04:e8:62:87:de:2b:c8:d7:4e:c1:4c:64:1e:
     *                     dd:cf:87:58:ba:4a:4f:ca:68:07:1d:1c:9d:4a:c6:
     *                     d5:2f:91:cc:7c:71:72:1c:c5:c0:67:eb:32:fd:c9:
     *                     92:5c:94:da:85:c0:9b:bf:53:7d:2b:09:f4:8c:9d:
     *                     91:1f:97:6a:52:cb:de:09:36:a4:77:d8:7b:87:50:
     *                     44:d5:3e:6e:29:69:fb:39:49:26:1e:09:a5:80:7b:
     *                     40:2d:eb:e8:27:85:c9:fe:61:fd:7e:e6:7c:97:1d:
     *                     d5:9d
     *                 Exponent: 65537 (0x10001)
     *         X509v3 extensions:
     *             X509v3 Basic Constraints: critical
     *                 CA:TRUE
     *             X509v3 Certificate Policies:
     *                 Policy: X509v3 Any Policy
     *                   CPS: https://www.thawte.com/cps
     *             X509v3 Key Usage: critical
     *                 Certificate Sign, CRL Sign
     *             X509v3 Subject Key Identifier:
     *                 7B:5B:45:CF:AF:CE:CB:7A:FD:31:92:1A:6A:B6:F3:46:EB:57:48:50
     *             X509v3 CRL Distribution Points:
     *                 URI:http://crl.thawte.com/ThawtePremiumServerCA.crl
     *             X509v3 Extended Key Usage:
     *                 Netscape Server Gated Crypto, 2.16.840.1.113733.1.8.1
     *             X509v3 Authority Key Identifier:
     *                 DirName:/C=ZA/ST=Western Cape/L=Cape Town/O=Thawte Consulting cc/OU=Certification Services Division/CN=Thawte Premium Server CA/emailAddress=premium-server@thawte.com
     *                 serial:01
     *     Signature Algorithm: sha1WithRSAEncryption
     *         2b:ca:12:c9:dd:d7:cc:63:1c:9b:31:35:4a:dd:e4:b7:f6:9d:
     *         d1:a4:fb:1e:f8:47:f9:ae:07:8e:0d:58:12:fb:da:ed:b5:cc:
     *         33:e5:97:68:47:61:42:d5:66:a9:6e:1e:47:bf:85:db:7d:58:
     *         d1:77:5a:cc:90:61:98:9a:29:f5:9d:b1:cf:b8:dc:f3:7b:80:
     *         47:48:d1:7d:f4:68:8c:c4:41:cb:b4:e9:fd:f0:23:e0:b1:9b:
     *         76:2a:6d:28:56:a3:8c:cd:e9:ec:21:00:71:f0:5f:dd:50:a5:
     *         69:42:1b:83:11:5d:84:28:d3:27:ae:ec:2a:ab:2f:60:42:c5:
     *         c4:78
     * </pre>
     */
    private static final String signedCert1Chain2 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIFUTCCBLqgAwIBAgIQX6a+gLaGxi8B7QyrsZahBTANBgkqhkiG9w0BAQUFADCB\n"
        + "zjELMAkGA1UEBhMCWkExFTATBgNVBAgTDFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJ\n"
        + "Q2FwZSBUb3duMR0wGwYDVQQKExRUaGF3dGUgQ29uc3VsdGluZyBjYzEoMCYGA1UE\n"
        + "CxMfQ2VydGlmaWNhdGlvbiBTZXJ2aWNlcyBEaXZpc2lvbjEhMB8GA1UEAxMYVGhh\n"
        + "d3RlIFByZW1pdW0gU2VydmVyIENBMSgwJgYJKoZIhvcNAQkBFhlwcmVtaXVtLXNl\n"
        + "cnZlckB0aGF3dGUuY29tMB4XDTA2MTExNzAwMDAwMFoXDTIwMTIzMDIzNTk1OVow\n"
        + "gakxCzAJBgNVBAYTAlVTMRUwEwYDVQQKEwx0aGF3dGUsIEluYy4xKDAmBgNVBAsT\n"
        + "H0NlcnRpZmljYXRpb24gU2VydmljZXMgRGl2aXNpb24xODA2BgNVBAsTLyhjKSAy\n"
        + "MDA2IHRoYXd0ZSwgSW5jLiAtIEZvciBhdXRob3JpemVkIHVzZSBvbmx5MR8wHQYD\n"
        + "VQQDExZ0aGF3dGUgUHJpbWFyeSBSb290IENBMIIBIjANBgkqhkiG9w0BAQEFAAOC\n"
        + "AQ8AMIIBCgKCAQEArKDw+4BZ1JzHpM+doVlzCRBFDA0sbmjxbFtIaElZN/wLMxnC\n"
        + "d3/MEC2VNBzm600JpxzSuMmXNgK3idQkXwbAzESUlI0CYm/rWt0RjSiaXISQEHoN\n"
        + "vXRmL2o4oOLVVETrHQefB7pv7un9Tgsp9T6EoAHxnKv4HH6JpOih2HFlDaNRe+68\n"
        + "0iJgDblbnd+6/FFbC6+Ysuku6QToYofeK8jXTsFMZB7dz4dYukpPymgHHRydSsbV\n"
        + "L5HMfHFyHMXAZ+sy/cmSXJTahcCbv1N9Kwn0jJ2RH5dqUsveCTakd9h7h1BE1T5u\n"
        + "KWn7OUkmHgmlgHtALevoJ4XJ/mH9fuZ8lx3VnQIDAQABo4IBzTCCAckwDwYDVR0T\n"
        + "AQH/BAUwAwEB/zA7BgNVHSAENDAyMDAGBFUdIAAwKDAmBggrBgEFBQcCARYaaHR0\n"
        + "cHM6Ly93d3cudGhhd3RlLmNvbS9jcHMwDgYDVR0PAQH/BAQDAgEGMB0GA1UdDgQW\n"
        + "BBR7W0XPr87Lev0xkhpqtvNG61dIUDBABgNVHR8EOTA3MDWgM6Axhi9odHRwOi8v\n"
        + "Y3JsLnRoYXd0ZS5jb20vVGhhd3RlUHJlbWl1bVNlcnZlckNBLmNybDAgBgNVHSUE\n"
        + "GTAXBglghkgBhvhCBAEGCmCGSAGG+EUBCAEwgeUGA1UdIwSB3TCB2qGB1KSB0TCB\n"
        + "zjELMAkGA1UEBhMCWkExFTATBgNVBAgTDFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJ\n"
        + "Q2FwZSBUb3duMR0wGwYDVQQKExRUaGF3dGUgQ29uc3VsdGluZyBjYzEoMCYGA1UE\n"
        + "CxMfQ2VydGlmaWNhdGlvbiBTZXJ2aWNlcyBEaXZpc2lvbjEhMB8GA1UEAxMYVGhh\n"
        + "d3RlIFByZW1pdW0gU2VydmVyIENBMSgwJgYJKoZIhvcNAQkBFhlwcmVtaXVtLXNl\n"
        + "cnZlckB0aGF3dGUuY29tggEBMA0GCSqGSIb3DQEBBQUAA4GBACvKEsnd18xjHJsx\n"
        + "NUrd5Lf2ndGk+x74R/muB44NWBL72u21zDPll2hHYULVZqluHke/hdt9WNF3WsyQ\n"
        + "YZiaKfWdsc+43PN7gEdI0X30aIzEQcu06f3wI+Cxm3YqbShWo4zN6ewhAHHwX91Q\n"
        + "pWlCG4MRXYQo0yeu7CqrL2BCxcR4\n"
        + "-----END CERTIFICATE-----";

    /**
     * A certificate signed by signedCert1Chain2
     *
     * <pre>
     * Certificate:
     *     Data:
     *         Version: 3 (0x2)
     *         Serial Number:
     *             7b:11:55:eb:78:9a:90:85:b5:8c:92:ff:42:b7:fe:56
     *         Signature Algorithm: sha1WithRSAEncryption
     *         Issuer: C=US, O=thawte, Inc., OU=Certification Services Division, OU=(c) 2006 thawte, Inc. - For authorized use only, CN=thawte Primary Root CA
     *         Validity
     *             Not Before: Nov 17 00:00:00 2006 GMT
     *             Not After : Nov 16 23:59:59 2016 GMT
     *         Subject: C=US, O=thawte, Inc., OU=Terms of use at https://www.thawte.com/cps (c)06, CN=thawte Extended Validation SSL CA
     *         Subject Public Key Info:
     *             Public Key Algorithm: rsaEncryption
     *             RSA Public Key: (2048 bit)
     *                 Modulus (2048 bit):
     *                     00:b5:8d:47:f7:b0:48:76:9b:bd:fb:a9:cb:bf:04:
     *                     31:a2:3d:9a:7e:30:29:d3:28:b8:fe:68:ce:cf:e9:
     *                     30:6a:53:95:0e:50:65:80:26:c9:98:bf:f2:14:ff:
     *                     06:7c:6a:7b:dc:50:07:e2:98:fa:df:cf:30:5d:ca:
     *                     a8:b9:8a:9b:2d:2d:7e:59:8b:1a:f7:b3:c9:c3:69:
     *                     80:0f:89:19:08:77:b2:52:55:ad:78:83:9d:6b:b9:
     *                     87:e4:53:24:37:2c:fc:19:0e:8b:79:14:4d:be:80:
     *                     9e:b4:9b:73:74:31:f2:38:ec:8a:af:2a:36:8e:64:
     *                     ce:31:26:14:03:54:53:8e:fb:84:08:c1:7e:47:32:
     *                     3d:71:e0:ba:ba:8c:82:58:96:4d:68:43:56:1a:f3:
     *                     46:5a:32:99:95:b0:60:6f:e9:41:8a:48:cc:16:0d:
     *                     44:68:b1:8a:dd:dd:17:3d:a4:9b:78:7f:2e:29:06:
     *                     f0:dc:d5:d2:13:3f:c0:36:05:fd:c7:b5:b9:80:1b:
     *                     8a:46:74:2f:f1:ab:79:9e:97:6e:f8:a5:13:5a:f3:
     *                     fc:b5:d7:c8:96:19:37:ee:06:bc:c6:27:14:81:05:
     *                     14:33:38:16:9f:4b:e2:0f:db:38:bb:f3:01:ef:35:
     *                     2e:de:af:f1:e4:6f:6f:f7:96:00:56:5e:8f:60:94:
     *                     1d:2f
     *                 Exponent: 65537 (0x10001)
     *         X509v3 extensions:
     *             Authority Information Access:
     *                 OCSP - URI:http://EVSecure-ocsp.thawte.com
     *             X509v3 Basic Constraints: critical
     *                 CA:TRUE, pathlen:0
     *             X509v3 Certificate Policies:
     *                 Policy: X509v3 Any Policy
     *                   CPS: https://www.thawte.com/cps
     *             X509v3 CRL Distribution Points:
     *                 URI:http://crl.thawte.com/ThawtePCA.crl
     *             X509v3 Key Usage: critical
     *                 Certificate Sign, CRL Sign
     *             X509v3 Subject Alternative Name:
     *                 DirName:/CN=PrivateLabel3-2048-234
     *             X509v3 Subject Key Identifier:
     *                 CD:32:E2:F2:5D:25:47:02:AA:8F:79:4B:32:EE:03:99:FD:30:49:D1
     *             X509v3 Authority Key Identifier:
     *                 keyid:7B:5B:45:CF:AF:CE:CB:7A:FD:31:92:1A:6A:B6:F3:46:EB:57:48:50
     *     Signature Algorithm: sha1WithRSAEncryption
     *         0b:b4:96:ce:03:0c:d1:9d:af:cb:e3:39:56:0d:c6:22:a0:c9:
     *         71:7d:ea:65:95:31:f1:dc:b6:1e:f2:8d:31:5d:61:b3:54:84:
     *         13:cc:2b:3f:02:5c:c7:1f:15:01:82:90:1e:31:25:06:e3:32:
     *         0c:87:f0:c3:be:9a:c4:00:41:f6:c6:91:e5:6c:3e:92:5d:a3:
     *         e4:3d:1f:32:2d:31:1e:50:c1:02:21:b4:23:e3:07:75:9a:52:
     *         45:51:fa:d3:1d:fd:01:6f:60:6d:25:d9:bf:43:b1:a7:43:6c:
     *         ad:8c:bb:bc:f7:99:41:eb:d6:95:cf:20:5c:7e:6f:c4:2a:da:
     *         4b:4d:1b:5b:c2:9f:b0:94:d4:bf:47:97:fd:9d:49:79:60:8e:
     *         ae:96:19:a1:b0:eb:e8:df:42:c7:22:74:61:0c:25:a3:7f:8f:
     *         45:d2:7e:e7:4a:6e:1d:4f:48:bb:c2:da:1a:7e:4a:59:81:fa:
     *         1c:e3:fb:14:73:41:03:a1:77:fa:9b:06:fc:7c:33:bd:46:3d:
     *         0c:06:17:85:7b:2a:7b:e3:36:e8:83:df:fa:aa:cb:32:0c:79:
     *         aa:86:74:6c:44:54:f6:d8:07:9e:cd:98:f4:23:05:09:2f:a2:
     *         53:b5:db:0a:81:cc:5f:23:cb:79:11:c5:11:5b:85:6b:27:01:
     *         89:f3:0e:bb
     * </pre>
     */
    private static final String signedCert2Chain2 =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIFCjCCA/KgAwIBAgIQexFV63iakIW1jJL/Qrf+VjANBgkqhkiG9w0BAQUFADCB\n"
        + "qTELMAkGA1UEBhMCVVMxFTATBgNVBAoTDHRoYXd0ZSwgSW5jLjEoMCYGA1UECxMf\n"
        + "Q2VydGlmaWNhdGlvbiBTZXJ2aWNlcyBEaXZpc2lvbjE4MDYGA1UECxMvKGMpIDIw\n"
        + "MDYgdGhhd3RlLCBJbmMuIC0gRm9yIGF1dGhvcml6ZWQgdXNlIG9ubHkxHzAdBgNV\n"
        + "BAMTFnRoYXd0ZSBQcmltYXJ5IFJvb3QgQ0EwHhcNMDYxMTE3MDAwMDAwWhcNMTYx\n"
        + "MTE2MjM1OTU5WjCBizELMAkGA1UEBhMCVVMxFTATBgNVBAoTDHRoYXd0ZSwgSW5j\n"
        + "LjE5MDcGA1UECxMwVGVybXMgb2YgdXNlIGF0IGh0dHBzOi8vd3d3LnRoYXd0ZS5j\n"
        + "b20vY3BzIChjKTA2MSowKAYDVQQDEyF0aGF3dGUgRXh0ZW5kZWQgVmFsaWRhdGlv\n"
        + "biBTU0wgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC1jUf3sEh2\n"
        + "m737qcu/BDGiPZp+MCnTKLj+aM7P6TBqU5UOUGWAJsmYv/IU/wZ8anvcUAfimPrf\n"
        + "zzBdyqi5ipstLX5Zixr3s8nDaYAPiRkId7JSVa14g51ruYfkUyQ3LPwZDot5FE2+\n"
        + "gJ60m3N0MfI47IqvKjaOZM4xJhQDVFOO+4QIwX5HMj1x4Lq6jIJYlk1oQ1Ya80Za\n"
        + "MpmVsGBv6UGKSMwWDURosYrd3Rc9pJt4fy4pBvDc1dITP8A2Bf3HtbmAG4pGdC/x\n"
        + "q3mel274pRNa8/y118iWGTfuBrzGJxSBBRQzOBafS+IP2zi78wHvNS7er/Hkb2/3\n"
        + "lgBWXo9glB0vAgMBAAGjggFIMIIBRDA7BggrBgEFBQcBAQQvMC0wKwYIKwYBBQUH\n"
        + "MAGGH2h0dHA6Ly9FVlNlY3VyZS1vY3NwLnRoYXd0ZS5jb20wEgYDVR0TAQH/BAgw\n"
        + "BgEB/wIBADA7BgNVHSAENDAyMDAGBFUdIAAwKDAmBggrBgEFBQcCARYaaHR0cHM6\n"
        + "Ly93d3cudGhhd3RlLmNvbS9jcHMwNAYDVR0fBC0wKzApoCegJYYjaHR0cDovL2Ny\n"
        + "bC50aGF3dGUuY29tL1RoYXd0ZVBDQS5jcmwwDgYDVR0PAQH/BAQDAgEGMC4GA1Ud\n"
        + "EQQnMCWkIzAhMR8wHQYDVQQDExZQcml2YXRlTGFiZWwzLTIwNDgtMjM0MB0GA1Ud\n"
        + "DgQWBBTNMuLyXSVHAqqPeUsy7gOZ/TBJ0TAfBgNVHSMEGDAWgBR7W0XPr87Lev0x\n"
        + "khpqtvNG61dIUDANBgkqhkiG9w0BAQUFAAOCAQEAC7SWzgMM0Z2vy+M5Vg3GIqDJ\n"
        + "cX3qZZUx8dy2HvKNMV1hs1SEE8wrPwJcxx8VAYKQHjElBuMyDIfww76axABB9saR\n"
        + "5Ww+kl2j5D0fMi0xHlDBAiG0I+MHdZpSRVH60x39AW9gbSXZv0Oxp0NsrYy7vPeZ\n"
        + "QevWlc8gXH5vxCraS00bW8KfsJTUv0eX/Z1JeWCOrpYZobDr6N9CxyJ0YQwlo3+P\n"
        + "RdJ+50puHU9Iu8LaGn5KWYH6HOP7FHNBA6F3+psG/HwzvUY9DAYXhXsqe+M26IPf\n"
        + "+qrLMgx5qoZ0bERU9tgHns2Y9CMFCS+iU7XbCoHMXyPLeRHFEVuFaycBifMOuw==\n"
        + "-----END CERTIFICATE-----";

    public void testVerifyMD5() throws Exception {
        Provider[] providers = Security.getProviders("CertificateFactory.X509");
        for (Provider provider : providers) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509", provider);

            Certificate certificate = certificateFactory
                    .generateCertificate(new ByteArrayInputStream(selfSignedCertMD5
                            .getBytes()));

            certificate.verify(certificate.getPublicKey());
        }
    }

    @AndroidOnly("MD2 is not supported by Android")
    public void testVerifyMD2() throws Exception {
        Provider[] providers = Security.getProviders("CertificateFactory.X509");
        for (Provider provider : providers) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509", provider);

            Certificate certificate = certificateFactory
                    .generateCertificate(new ByteArrayInputStream(selfSignedCertMD2
                            .getBytes()));
            try {
                certificate.verify(certificate.getPublicKey());
                fail("MD2 should not be allowed");
            } catch (NoSuchAlgorithmException e) {
                // expected
            } catch (Throwable e) {
                throw new AssertionError(provider.getName(), e);
            }
        }
    }

    public void testVerifyMD2_chain() throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");

        // First check with the trust anchor not included in the chain
        CertPath path = certificateFactory.generateCertPath(getCertList(true, false));

        CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
        PKIXParameters params = createPKIXParams();

        CertPathValidatorResult res = certPathValidator.validate(path, params);
        assertTrue("wrong result type",
                   res instanceof PKIXCertPathValidatorResult);

        PKIXCertPathValidatorResult r = (PKIXCertPathValidatorResult) res;
        assertTrue("Wrong trust anchor returned",
                   params.getTrustAnchors().contains(r.getTrustAnchor()));

        // Now check with the trust anchor included in the chain
        path = certificateFactory.generateCertPath(getCertList(true, true));

        certPathValidator = CertPathValidator.getInstance("PKIX");
        params = createPKIXParams();

        if (StandardNames.IS_RI) {
            res = certPathValidator.validate(path, params);
            assertTrue("wrong result type", res instanceof PKIXCertPathValidatorResult);

            r = (PKIXCertPathValidatorResult) res;
            assertTrue("Wrong trust anchor returned",
                       params.getTrustAnchors().contains(r.getTrustAnchor()));
        } else {
            try {
                certPathValidator.validate(path, params);
                fail();
            } catch (CertPathValidatorException expected) {
            }
        }
    }

    public void testVerifyMD5_chain() throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");

        // First check with the trust anchor not included in the chain
        CertPath path = certificateFactory.generateCertPath(getCertList(false, false));

        CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
        PKIXParameters params = createPKIXParams();

        CertPathValidatorResult res = certPathValidator.validate(path, params);
        assertTrue("wrong result type",
                   res instanceof PKIXCertPathValidatorResult);

        PKIXCertPathValidatorResult r = (PKIXCertPathValidatorResult) res;
        assertTrue("Wrong trust anchor returned",
                   params.getTrustAnchors().contains(r.getTrustAnchor()));

        // Now check with the trust anchor included in the chain
        path = certificateFactory.generateCertPath(getCertList(false, true));

        certPathValidator = CertPathValidator.getInstance("PKIX");
        params = createPKIXParams();

        res = certPathValidator.validate(path, params);
        assertTrue("wrong result type",
                res instanceof PKIXCertPathValidatorResult);

        r = (PKIXCertPathValidatorResult) res;
        assertTrue("Wrong trust anchor returned",
                   params.getTrustAnchors().contains(r.getTrustAnchor()));
    }

    private X509Certificate[] certs= new X509Certificate[3];

    private List<Certificate> getCertList(boolean useMD2root,
            boolean includeRootInChain) throws Exception {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");

        if (useMD2root) {
            certs[0] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            selfSignedCertMD2.getBytes()));
            certs[1] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            signedCert1Chain1.getBytes()));
            certs[2] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            signedCert2Chain1.getBytes()));
        } else {
            certs[0] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            selfSignedCertMD5.getBytes()));
            certs[1] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            signedCert1Chain2.getBytes()));
            certs[2] = (X509Certificate) certificateFactory
                    .generateCertificate(new ByteArrayInputStream(
                            signedCert2Chain2.getBytes()));
        }

        ArrayList<Certificate> result = new ArrayList<Certificate>();
        result.add(certs[2]);
        result.add(certs[1]);
        if (includeRootInChain) {
            result.add(certs[0]);
        }
        return result;
    }

    private PKIXParameters createPKIXParams() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("selfSignedCert", certs[0]);

        PKIXParameters params;
        params = new PKIXParameters(keyStore);
        params.setRevocationEnabled(false);
        return params;
    }
}
