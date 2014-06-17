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

package org.conscrypt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import junit.framework.TestCase;

public class ChainStrengthAnalyzerTest extends TestCase {

    //openssl req -x509 -nodes -days 365 -subj '/C=US/ST=Testsota/L=Testville/CN=test.com' \
    //-newkey rsa:2048 -sha256 -keyout k.pem -out good.pem
    private static final String GOOD_PEM = "" +
                            "-----BEGIN CERTIFICATE-----\n" +
                            "MIIDYTCCAkmgAwIBAgIJAPFX8KGuEZcgMA0GCSqGSIb3DQEBCwUAMEcxCzAJBgNV\n" +
                            "BAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVzdHZpbGxlMREw\n" +
                            "DwYDVQQDDAh0ZXN0LmNvbTAeFw0xMjEwMTUyMTQ0MTBaFw0xMzEwMTUyMTQ0MTBa\n" +
                            "MEcxCzAJBgNVBAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVz\n" +
                            "dHZpbGxlMREwDwYDVQQDDAh0ZXN0LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEP\n" +
                            "ADCCAQoCggEBAM44hz3eTINuAIS9OYmg6DkUIj3MItn5dgbcMEdbXrhNpeWY93ho\n" +
                            "WQFfsqcSSx28NzqKJmnX+cyinzIUfVde/qciP9P7fxRDokRsf34DJ6gXQplz6P2t\n" +
                            "s4CWjYM+WXJrvEUgLUQ3CBV0CCrtYvG1B9wYsBdAdWkVaMxTvEt7aVxcvJYzp+KU\n" +
                            "ME7HDg0PVxptvUExIskcqKVmW7i748AgBLhd0r1nFWLuH20d42Aowja0Wi19fWl2\n" +
                            "SEMErDRjG8jIPUdSoOLPVLGTktEpex51xnAaZ+I7hy6zs55dq8ua/hE/v2cXIkiQ\n" +
                            "ZXpWyvI/MaKEfeydLnNpa7J3GpH3KW93HQcCAwEAAaNQME4wHQYDVR0OBBYEFA0M\n" +
                            "RI+3hIPCSpVVArisr3Y3/sheMB8GA1UdIwQYMBaAFA0MRI+3hIPCSpVVArisr3Y3\n" +
                            "/sheMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQELBQADggEBAFgUNyuy2qaJvgDO\n" +
                            "plYudTrJR38O3id1B5oKOzgTEgRrfmHHfyloY4fL5gjAGNp7vdlDKSHC2Ebo23/X\n" +
                            "Wg535MJ2296R855jaTMdkSE0+4ASpdmon1D007H0FhLyojlKVta3pqMAF1zsp0YF\n" +
                            "Mf3V/rVMDxCOnbSnqAX0+1nW8Qm4Jgrr3AAMafZk6ypq0xuNQn+sUWuIWw3Xv5Jl\n" +
                            "KehjnuKtMgVYkn2ItRNnUdhm2dQK+Phdb5Yg8WHXN/r9sZQdORg8FQS9TfQJmimB\n" +
                            "CVYuqA9Dt0JJZPuO/Pd1yAxWP4NpxX1xr3lNQ5jrTO702QA3gOrscluULLzrYR50\n" +
                            "FoAjeos=\n" +
                            "-----END CERTIFICATE-----";

    //openssl req -x509 -nodes -days 365 -subj '/C=US/ST=Testsota/L=Testville/CN=test.com' \
    //-newkey rsa:2048 -md5 -keyout k.pem -out md5.pem
    private static final String MD5_PEM = "" +
                            "-----BEGIN CERTIFICATE-----\n" +
                            "MIIDYTCCAkmgAwIBAgIJAJsffMf2cyx0MA0GCSqGSIb3DQEBBAUAMEcxCzAJBgNV\n" +
                            "BAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVzdHZpbGxlMREw\n" +
                            "DwYDVQQDDAh0ZXN0LmNvbTAeFw0xMjEwMTUyMTQzMzZaFw0xMzEwMTUyMTQzMzZa\n" +
                            "MEcxCzAJBgNVBAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVz\n" +
                            "dHZpbGxlMREwDwYDVQQDDAh0ZXN0LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEP\n" +
                            "ADCCAQoCggEBAOJyiUwgf/VsdbTTdx6dsb742adeBFBY1FpSWCeQW/JVtdMephbK\n" +
                            "AA00nu8Xq3dNx9bp8AqvzeyHi/RBsZOtb2eAsOXE3RbFy28ehDTHdG34fRQNT6kp\n" +
                            "RUHw8wrUGovMVqS8j+iW8HfAy3sjArje0ygz2NIETlNQbEOifAJtY+AEfZwZE0/0\n" +
                            "IMVP4hwTmIgyReJBDmAx31clwsWZSPar9x+WQfeJ3rfy5LBCtf3RUbdgnvynBHFk\n" +
                            "FjucwoqgOOXviCWxIa0F+ZAmZJBj5+pLN/V92RXOu0c2fR3Mf68J67OJ+K4ueo1N\n" +
                            "nBhRsulWMmGqIVjYOZQxiNzWYcOVXj3DTRMCAwEAAaNQME4wHQYDVR0OBBYEFJbY\n" +
                            "TU06RuJaiMBs2vzx5y0MbaQOMB8GA1UdIwQYMBaAFJbYTU06RuJaiMBs2vzx5y0M\n" +
                            "baQOMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEEBQADggEBAFEky0jLTmKefDVX\n" +
                            "8O84KoupmQ2qQQBaQF3F5GEuhi0qJRwnmsWkCmsxPP55S67WDFp3JH+LX14UxL4T\n" +
                            "fbG2CXHt/BF1yU3Z8JBwx3bDmfUnUOAFkO3nmByb11FyZTHMzq4jp03DexWREv4q\n" +
                            "Ai5+5Xb56VECgCH/hnGqhQeFGhlZUcSXobVhAU+39L6azWELXxk1K4bpVxYFGn1N\n" +
                            "uZ+dWmb6snPKDzG6J5IIX8QIs6G8H6ptj+QNoU/qTcZEnuzMJxpqMsyq10AA+bY/\n" +
                            "VAYyXeZm3XZrtqYosDeiUdmcL0jjmyQtyOcAoVUQWj1EJuRjXg4BvI6xxRAIPWYT\n" +
                            "EDeWHJE=\n" +
                            "-----END CERTIFICATE-----";

    //openssl req -x509 -nodes -days 365 -subj '/C=US/ST=Testsota/L=Testville/CN=test.com' \
    //-newkey rsa:512 -sha256 -keyout k.pem -out short.pem
    private static final String SHORT_PEM = "" +
                            "-----BEGIN CERTIFICATE-----\n" +
                            "MIIB1zCCAYGgAwIBAgIJAOxaz9TreDNIMA0GCSqGSIb3DQEBCwUAMEcxCzAJBgNV\n" +
                            "BAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVzdHZpbGxlMREw\n" +
                            "DwYDVQQDDAh0ZXN0LmNvbTAeFw0xMjEwMTUyMTQzMjNaFw0xMzEwMTUyMTQzMjNa\n" +
                            "MEcxCzAJBgNVBAYTAlVTMREwDwYDVQQIDAhUZXN0c290YTESMBAGA1UEBwwJVGVz\n" +
                            "dHZpbGxlMREwDwYDVQQDDAh0ZXN0LmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgC\n" +
                            "QQCoMgxK9HG0L+hXEht1mKq6ApN3+3lmIEVUcWQKL7EMmn9+L6rVSJyOAGwpTVG7\n" +
                            "eZ5uulC0Lkm5/bzKFSrCf1jlAgMBAAGjUDBOMB0GA1UdDgQWBBTda66RZsgUvR4e\n" +
                            "2RSsq65K1xcz0jAfBgNVHSMEGDAWgBTda66RZsgUvR4e2RSsq65K1xcz0jAMBgNV\n" +
                            "HRMEBTADAQH/MA0GCSqGSIb3DQEBCwUAA0EAZWYgoNDn6yEzcmWgsYnG3w2BT6fL\n" +
                            "Npi0+APKWkwxnEJk1kgpdeSTMgaHAphQ8qksHnSgeBAJSs2ZCQMinVPgOg==\n" +
                            "-----END CERTIFICATE-----";

    public void testMD5() throws Exception {
        assertBad(MD5_PEM, "Weak hash check did not fail as expected");
    }

    public void test512() throws Exception {
        assertBad(SHORT_PEM, "Short modulus check did not fail as expected");
    }

    public void testGoodChain() throws Exception {
        assertGood(GOOD_PEM);
    }

    private static void assertBad(String pem, String msg) throws Exception {
        try {
            check(createCert(pem));
            fail(msg);
        } catch (CertificateException expected) {
        }
    }

    private static void assertGood(String pem) throws Exception {
        check(createCert(pem));
    }

    private static void check(X509Certificate cert) throws Exception {
        X509Certificate[] chain = {cert};
        ChainStrengthAnalyzer.check(chain);
    }

    private static X509Certificate createCert(String pem) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        InputStream pemInput = new ByteArrayInputStream(pem.getBytes());
        return (X509Certificate) cf.generateCertificate(pemInput);
    }
}
