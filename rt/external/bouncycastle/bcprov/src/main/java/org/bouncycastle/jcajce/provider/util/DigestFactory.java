package org.bouncycastle.jcajce.provider.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
// BEGIN android-removed
// import org.bouncycastle.crypto.digests.MD5Digest;
// import org.bouncycastle.crypto.digests.SHA1Digest;
// import org.bouncycastle.crypto.digests.SHA224Digest;
// import org.bouncycastle.crypto.digests.SHA256Digest;
// import org.bouncycastle.crypto.digests.SHA384Digest;
// import org.bouncycastle.crypto.digests.SHA512Digest;
// END android-removed
// BEGIN android-added
import org.bouncycastle.crypto.digests.AndroidDigestFactory;
// END android-added
import org.bouncycastle.util.Strings;

public class DigestFactory
{
    private static Set md5 = new HashSet();
    private static Set sha1 = new HashSet();
    // BEGIN android-removed
    // private static Set sha224 = new HashSet();
    // END android-removed
    private static Set sha256 = new HashSet();
    private static Set sha384 = new HashSet();
    private static Set sha512 = new HashSet();
    
    private static Map oids = new HashMap();
    
    static
    {
        md5.add("MD5");
        md5.add(PKCSObjectIdentifiers.md5.getId());
        
        sha1.add("SHA1");
        sha1.add("SHA-1");
        sha1.add(OIWObjectIdentifiers.idSHA1.getId());
        
        // BEGIN android-removed
        // sha224.add("SHA224");
        // sha224.add("SHA-224");
        // sha224.add(NISTObjectIdentifiers.id_sha224.getId());
        // END android-removed
        
        sha256.add("SHA256");
        sha256.add("SHA-256");
        sha256.add(NISTObjectIdentifiers.id_sha256.getId());
        
        sha384.add("SHA384");
        sha384.add("SHA-384");
        sha384.add(NISTObjectIdentifiers.id_sha384.getId());
        
        sha512.add("SHA512");
        sha512.add("SHA-512");
        sha512.add(NISTObjectIdentifiers.id_sha512.getId()); 

        oids.put("MD5", PKCSObjectIdentifiers.md5);
        oids.put(PKCSObjectIdentifiers.md5.getId(), PKCSObjectIdentifiers.md5);
        
        oids.put("SHA1", OIWObjectIdentifiers.idSHA1);
        oids.put("SHA-1", OIWObjectIdentifiers.idSHA1);
        oids.put(OIWObjectIdentifiers.idSHA1.getId(), OIWObjectIdentifiers.idSHA1);
        
        // BEGIN android-removed
        // oids.put("SHA224", NISTObjectIdentifiers.id_sha224);
        // oids.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        // oids.put(NISTObjectIdentifiers.id_sha224.getId(), NISTObjectIdentifiers.id_sha224);
        // END android-removed
        
        oids.put("SHA256", NISTObjectIdentifiers.id_sha256);
        oids.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        oids.put(NISTObjectIdentifiers.id_sha256.getId(), NISTObjectIdentifiers.id_sha256);
        
        oids.put("SHA384", NISTObjectIdentifiers.id_sha384);
        oids.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        oids.put(NISTObjectIdentifiers.id_sha384.getId(), NISTObjectIdentifiers.id_sha384);
        
        oids.put("SHA512", NISTObjectIdentifiers.id_sha512);
        oids.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        oids.put(NISTObjectIdentifiers.id_sha512.getId(), NISTObjectIdentifiers.id_sha512); 
    }
    
    public static Digest getDigest(
        String digestName) 
    {
        digestName = Strings.toUpperCase(digestName);
        
        if (sha1.contains(digestName))
        {
            // BEGIN android-changed
            return AndroidDigestFactory.getSHA1();
            // END android-changed
        }
        if (md5.contains(digestName))
        {
            // BEGIN android-changed
            return AndroidDigestFactory.getMD5();
            // END android-changed
        }
        // BEGIN android-removed
        // if (sha224.contains(digestName))
        // {
        //     return new SHA224Digest();
        // }
        // END android-removed
        if (sha256.contains(digestName))
        {
            // BEGIN android-changed
            return AndroidDigestFactory.getSHA256();
            // END android-changed
        }
        if (sha384.contains(digestName))
        {
            // BEGIN android-changed
            return AndroidDigestFactory.getSHA384();
            // END android-changed
        }
        if (sha512.contains(digestName))
        {
            // BEGIN android-changed
            return AndroidDigestFactory.getSHA512();
            // END android-changed
        }
        
        return null;
    }
    
    public static boolean isSameDigest(
        String digest1,
        String digest2)
    {
        return (sha1.contains(digest1) && sha1.contains(digest2))
            // BEGIN android-removed
            // || (sha224.contains(digest1) && sha224.contains(digest2))
            // END android-removed
            || (sha256.contains(digest1) && sha256.contains(digest2))
            || (sha384.contains(digest1) && sha384.contains(digest2))
            || (sha512.contains(digest1) && sha512.contains(digest2))
            || (md5.contains(digest1) && md5.contains(digest2));
    }
    
    public static ASN1ObjectIdentifier getOID(
        String digestName)
    {
        return (ASN1ObjectIdentifier)oids.get(digestName);
    }
}
