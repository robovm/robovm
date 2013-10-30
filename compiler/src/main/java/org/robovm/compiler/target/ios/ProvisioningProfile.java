/*
 * Copyright (C) 2013 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.target.ios;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.cms.CMSSignedData;

import com.dd.plist.NSArray;
import com.dd.plist.NSData;
import com.dd.plist.NSDate;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Represents a provisioning profile.
 */
public class ProvisioningProfile implements Comparable<ProvisioningProfile> {
    private static final String HEX_DIGITS = "0123456789ABCDEF";

    private final File file;
    private final NSDictionary dict;
    private final String uuid;
    private final String name;
    private final String appIdName;
    private final String appIdPrefix;
    private final String appId;
    private final Date creationDate;
    private final Date expirationDate;
    private final NSDictionary entitlements;
    private final List<String> certFingerprints = new ArrayList<String>();
    
    ProvisioningProfile(File file, NSDictionary dict) {
        this.file = file;
        this.dict = dict;
        this.uuid = dict.objectForKey("UUID").toString();
        this.name = dict.objectForKey("Name").toString();
        this.appIdName = dict.objectForKey("AppIDName") != null 
                ? dict.objectForKey("AppIDName").toString() : null;
        this.appIdPrefix = ((NSArray) dict.objectForKey("ApplicationIdentifierPrefix"))
                .objectAtIndex(0).toString();
        this.creationDate = ((NSDate) dict.objectForKey("CreationDate")).getDate();
        this.expirationDate = ((NSDate) dict.objectForKey("ExpirationDate")).getDate();
        this.entitlements = (NSDictionary) dict.objectForKey("Entitlements");
        this.appId = this.entitlements.objectForKey("application-identifier").toString();
        
        for (NSObject o : ((NSArray) dict.objectForKey("DeveloperCertificates")).getArray()) {
            NSData data = (NSData) o;
            certFingerprints.add(getCertFingerprint(data.bytes()));
        }
    }
    
    @Override
    public int compareTo(ProvisioningProfile o) {
        return this.name.compareToIgnoreCase(o.name);
    }
    
    private static String getCertFingerprint(byte[] certData) {
        try {
            CertificateFactory x509CertFact = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) x509CertFact.generateCertificate(new ByteArrayInputStream(certData));
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return toHexString(md.digest(cert.getEncoded()));
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String toHexString(byte bytes[]) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            buf.append(HEX_DIGITS.charAt((bytes[i] & 0xf0) >> 4));
            buf.append(HEX_DIGITS.charAt(bytes[i] & 0x0f));
        }
        return buf.toString();
    }
    
    public File getFile() {
        return file;
    }

    public NSDictionary getDict() {
        return dict;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getAppIdName() {
        return appIdName;
    }

    public String getAppIdPrefix() {
        return appIdPrefix;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public NSDictionary getEntitlements() {
        return entitlements;
    }

    private static ProvisioningProfile create(File file) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            CMSSignedData data = new CMSSignedData(in);
            byte[] content = (byte[]) data.getSignedContent().getContent();
            NSDictionary dict = (NSDictionary) PropertyListParser.parse(content);
            return new ProvisioningProfile(file, dict);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    public static List<ProvisioningProfile> list() {
        File dir = new File(new File(System.getProperty("user.home")), "Library/MobileDevice/Provisioning Profiles");
        if (!dir.exists() || !dir.isDirectory()) {
            return Collections.emptyList();
        }
        List<ProvisioningProfile> result = new ArrayList<ProvisioningProfile>();
        for (File f : dir.listFiles()) {
            if (f.getName().endsWith(".mobileprovision")) {
                ProvisioningProfile p = create(f);
                if (p.expirationDate.after(new Date())) {
                    result.add(p);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public static ProvisioningProfile find(List<ProvisioningProfile> profiles, String search) {
        for (ProvisioningProfile p : profiles) {
            if (p.uuid.equals(search) || p.appIdPrefix.equals(search) || p.appIdName != null && p.appIdName.equals(search)) {
                return p;
            }
        }        
        throw new IllegalArgumentException("No provisioning profile found matching '" + search + "'");
    }
    
    public static ProvisioningProfile find(List<ProvisioningProfile> profiles, SigningIdentity signingIdentity, String bundleId) {
        return find(profiles, signingIdentity, bundleId, bundleId);
    }
    
    private static ProvisioningProfile find(List<ProvisioningProfile> profiles, SigningIdentity signingIdentity, String bundleId, String origBundleId) {
        // Try a direct match first
        for (ProvisioningProfile p : profiles) {
            if (p.appId.equals(p.appIdPrefix + "." + bundleId)) {
                for (String fp : p.certFingerprints) {
                    if (fp.equals(signingIdentity.getFingerprint())) {
                        return p;
                    }
                }
            }
        }
        if (!bundleId.equals("*")) {
            // Try with the last component replaced with a wildcard
            if (bundleId.endsWith(".*")) {
                bundleId = bundleId.substring(0, bundleId.length() - 2);
            }
            int lastDot = bundleId.lastIndexOf('.');
            if (lastDot != -1) {
                bundleId = bundleId.substring(0, lastDot) + ".*";
            } else {
                bundleId = "*";
            }
            return find(profiles, signingIdentity, bundleId, origBundleId);
        }
        throw new IllegalArgumentException("No provisioning profile found " 
                + "matching signing identity '" + signingIdentity.getName() 
                + "' and app bundle ID '" + origBundleId + "'");
    }
    
    @Override
    public String toString() {
        return "ProvisioningProfile [file=" + file + ", uuid=" + uuid
                + ", name=" + name + ", appIdName=" + appIdName
                + ", appIdPrefix=" + appIdPrefix + ", appId=" + appId
                + ", creationDate=" + creationDate + ", expirationDate="
                + expirationDate + ", certFingerprints=" + certFingerprints
                + "]";
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(list());
        } else if (args.length == 1) {
            System.out.println(find(list(), args[0]));
        } else {
            System.out.println(find(list(), SigningIdentity.find(SigningIdentity.list(), args[0]), args[1]));
        }
    }
}
