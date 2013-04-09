package org.apache.harmony.xnet.tests.support;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.security.Principal;
import java.security.PrivateKey;
import java.net.Socket;

import javax.net.ssl.X509KeyManager;

public class X509KeyManagerImpl implements X509KeyManager {

    private String keyType;
    private String client = "CLIENT";
    private String server = "SERVER";
    private KeyStore keyTest;
    private X509Certificate[] cert = null;

    // creating a certificate
    String certificate = "-----BEGIN CERTIFICATE-----\n"
            + "MIICZTCCAdICBQL3AAC2MA0GCSqGSIb3DQEBAgUAMF8xCzAJBgNVBAYTAlVTMSAw\n"
            + "HgYDVQQKExdSU0EgRGF0YSBTZWN1cml0eSwgSW5jLjEuMCwGA1UECxMlU2VjdXJl\n"
            + "IFNlcnZlciBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAeFw05NzAyMjAwMDAwMDBa\n"
            + "Fw05ODAyMjAyMzU5NTlaMIGWMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZv\n"
            + "cm5pYTESMBAGA1UEBxMJUGFsbyBBbHRvMR8wHQYDVQQKExZTdW4gTWljcm9zeXN0\n"
            + "ZW1zLCBJbmMuMSEwHwYDVQQLExhUZXN0IGFuZCBFdmFsdWF0aW9uIE9ubHkxGjAY\n"
            + "BgNVBAMTEWFyZ29uLmVuZy5zdW4uY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCB\n"
            + "iQKBgQCofmdY+PiUWN01FOzEewf+GaG+lFf132UpzATmYJkA4AEA/juW7jSi+LJk\n"
            + "wJKi5GO4RyZoyimAL/5yIWDV6l1KlvxyKslr0REhMBaD/3Z3EsLTTEf5gVrQS6sT\n"
            + "WMoSZAyzB39kFfsB6oUXNtV8+UKKxSxKbxvhQn267PeCz5VX2QIDAQABMA0GCSqG\n"
            + "SIb3DQEBAgUAA34AXl3at6luiV/7I9MN5CXYoPJYI8Bcdc1hBagJvTMcmlqL2uOZ\n"
            + "H9T5hNMEL9Tk6aI7yZPXcw/xI2K6pOR/FrMp0UwJmdxX7ljV6ZtUZf7pY492UqwC\n"
            + "1777XQ9UEZyrKJvF5ntleeO0ayBqLGVKCWzWZX9YsXCpv47FNLZbupE=\n"
            + "-----END CERTIFICATE-----\n";

    ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
            .getBytes());

    String certificate2 = "-----BEGIN CERTIFICATE-----\n"
            + "MIICZzCCAdCgAwIBAgIBGzANBgkqhkiG9w0BAQUFADBhMQswCQYDVQQGEwJVUzEY\n"
            + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
            + "A1BLSTEcMBoGA1UEAxMTRG9EIFBLSSBNZWQgUm9vdCBDQTAeFw05ODA4MDMyMjAy\n"
            + "MjlaFw0wODA4MDQyMjAyMjlaMGExCzAJBgNVBAYTAlVTMRgwFgYDVQQKEw9VLlMu\n"
            + "IEdvdmVybm1lbnQxDDAKBgNVBAsTA0RvRDEMMAoGA1UECxMDUEtJMRwwGgYDVQQD\n"
            + "ExNEb0QgUEtJIE1lZCBSb290IENBMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKB\n"
            + "gQDbrM/J9FrJSX+zxFUbsI9Vw5QbguVBIa95rwW/0M8+sM0r5gd+DY6iubm6wnXk\n"
            + "CSvbfQlFEDSKr4WYeeGp+d9WlDnQdtDFLdA45tCi5SHjnW+hGAmZnld0rz6wQekF\n"
            + "5xQaa5A6wjhMlLOjbh27zyscrorMJ1O5FBOWnEHcRv6xqQIDAQABoy8wLTAdBgNV\n"
            + "HQ4EFgQUVrmYR6m9701cHQ3r5kXyG7zsCN0wDAYDVR0TBAUwAwEB/zANBgkqhkiG\n"
            + "9w0BAQUFAAOBgQDVX1Y0YqC7vekeZjVxtyuC8Mnxbrz6D109AX07LEIRzNYzwZ0w\n"
            + "MTImSp9sEzWW+3FueBIU7AxGys2O7X0qmN3zgszPfSiocBuQuXIYQctJhKjF5KVc\n"
            + "VGQRYYlt+myhl2vy6yPzEVCjiKwMEb1Spu0irCf+lFW2hsdjvmSQMtZvOw==\n"
            + "-----END CERTIFICATE-----\n";

    ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
            .getBytes());

    String certificate3 = "-----BEGIN CERTIFICATE-----\n"
            + "MIIDXDCCAsWgAwIBAgIBSjANBgkqhkiG9w0BAQUFADBWMQswCQYDVQQGEwJVUzEY\n"
            + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
            + "A1BLSTERMA8GA1UEAxMITWVkIENBLTEwHhcNOTgwODAyMTgwMjQwWhcNMDEwODAy\n"
            + "MTgwMjQwWjB0MQswCQYDVQQGEwJVUzEYMBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50\n"
            + "MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsTA1BLSTENMAsGA1UECxMEVVNBRjEgMB4G\n"
            + "A1UEAxMXR3VtYnkuSm9zZXBoLjAwMDAwMDUwNDQwgZ8wDQYJKoZIhvcNAQEBBQAD\n"
            + "gY0AMIGJAoGBALT/R7bPqs1c1YqXAg5HNpZLgW2HuAc7RCaP06cE4R44GBLw/fQc\n"
            + "VRNLn5pgbTXsDnjiZVd8qEgYqjKFQka4/tNhaF7No2tBZB+oYL/eP0IWtP+h/W6D\n"
            + "KR5+UvIIdgmx7k3t9jp2Q51JpHhhKEb9WN54trCO9Yu7PYU+LI85jEIBAgMBAAGj\n"
            + "ggEaMIIBFjAWBgNVHSAEDzANMAsGCWCGSAFlAgELAzAfBgNVHSMEGDAWgBQzOhTo\n"
            + "CWdhiGUkIOx5cELXppMe9jAdBgNVHQ4EFgQUkLBJl+ayKgzOp/wwBX9M1lSkCg4w\n"
            + "DgYDVR0PAQH/BAQDAgbAMAwGA1UdEwEB/wQCMAAwgZ0GA1UdHwSBlTCBkjCBj6CB\n"
            + "jKCBiYaBhmxkYXA6Ly9kcy0xLmNoYW1iLmRpc2EubWlsL2NuJTNkTWVkJTIwQ0El\n"
            + "MmQxJTJjb3UlM2RQS0klMmNvdSUzZERvRCUyY28lM2RVLlMuJTIwR292ZXJubWVu\n"
            + "dCUyY2MlM2RVUz9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0JTNiYmluYXJ5MA0G\n"
            + "CSqGSIb3DQEBBQUAA4GBAFjapuDHMvIdUeYRyEYdShBR1JZC20tJ3MQnyBQveddz\n"
            + "LGFDGpIkRAQU7T/5/ne8lMexyxViC21xOlK9LdbJCbVyywvb9uEm/1je9wieQQtr\n"
            + "kjykuB+WB6qTCIslAO/eUmgzfzIENvnH8O+fH7QTr2PdkFkiPIqBJYHvw7F3XDqy\n"
            + "-----END CERTIFICATE-----\n";

    ByteArrayInputStream certArray3 = new ByteArrayInputStream(certificate3
            .getBytes());


    public X509KeyManagerImpl(String name) {
        keyType = name;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
            keyTest.load(null, null);
            if (keyType.equals(client)) {
                cert = new X509Certificate[2];
                cert[0] = (X509Certificate) cf.generateCertificate(certArray);
                cert[1] = (X509Certificate) cf.generateCertificate(certArray2);
                //keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
                //keyTest.load(null, null);
                keyTest.setCertificateEntry("clientAlias_01", cert[0]);
                keyTest.setCertificateEntry("clientAlias_02", cert[0]);
                keyTest.setCertificateEntry("clientAlias_03", cert[1]);
            } else if (keyType.equals(server)) {
                //CertificateFactory cf = CertificateFactory.getInstance("X.509");
                cert = new X509Certificate[1];
                cert[0] = (X509Certificate) cf.generateCertificate(certArray3);
                //keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
                //keyTest.load(null, null);
                keyTest.setCertificateEntry("serverAlias_00", cert[0]);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public String[] getClientAliases(String s, Principal[] aprincipal) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            if (s.equals(client)) {
                Enumeration<String> aliase = keyTest.aliases();
                Vector vec = new Vector();
                int i = 0;
                while (aliase.hasMoreElements()) {
                    vec.addElement(aliase.nextElement());
                    i++;
                }
                String[] res = new String[vec.size()];
                for (i = 0; i < vec.size(); i++) {
                    res[i] = vec.elementAt(i).toString();
                }
                return res;
            } else return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public String chooseClientAlias(String[] as, Principal[] aprincipal, Socket socket) {
        String alias = null;
        if (as == null || as.length == 0) {
            return null;
        }
        try {
            if (as.length == 1 && as[0].equals(client)) {
                if (socket == null) {
                    alias = keyTest.getCertificateAlias(cert[0]);
                } else {
                    alias = keyTest.getCertificateAlias(cert[1]);
                }
                return alias;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return null;
    }

    public String[] getServerAliases(String s, Principal aprincipal[]) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            if (s.equals(server)) {
                Enumeration<String> aliase = keyTest.aliases();
                Vector vec = new Vector();
                int i = 0;
                while (aliase.hasMoreElements()) {
                    vec.addElement(aliase.nextElement());
                    i++;
                }
                String[] res = new String[vec.size()];
                for (i = 0; i < vec.size(); i++) {
                    res[i] = vec.elementAt(i).toString();
                }
                return res;
            } else return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public String chooseServerAlias(String as, Principal[] aprincipal, Socket socket) {
        String alias = null;
        if (as == null || as.equals("")) {
            return null;
        }
        try {
            if (as.equals(server) && socket != null) {
                return alias = keyTest.getCertificateAlias(cert[0]);
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public X509Certificate[] getCertificateChain(String s) {
        /*try {
            if (s != null && !s.equals("")) {
                X509Certificate[] cert = (X509Certificate[]) keyTest.getCertificateChain(s);
                return cert;
            } else return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }*/
        return null;
    }

    public PrivateKey getPrivateKey(String s) {
        /*try {
            if (s != null && !s.equals("")) {
                Certificate[] cert = keyTest.getCertificateChain(s);
                PrivateKey pk = (PrivateKey) keyTest.getKey(s, null);
                return pk;
            } else return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }*/
        return null;
    }

}
