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

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.dd.plist.NSArray;
import com.dd.plist.NSDate;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;

/**
 * Tests {@link ProvisioningProfile}.
 */
public class ProvisioningProfileTest {

    @Test
    public void testFindWithWildcardMatchesLongest() throws Exception {
        ProvisioningProfile p1 = createProfile("Profile 1", "App 1", "DF6YH89WE3", "DF6YH89WE3.*", "0123456789");
        ProvisioningProfile p2 = createProfile("Profile 2", "App 2", "HSKDO63H63", "HSKDO63H63.com.*", "0123456789");
        ProvisioningProfile p3 = createProfile("Profile 3", "App 3", "GS73MK54DW", "GS73MK54DW.com.foo.*", "0123456789");
        SigningIdentity si = new SigningIdentity("foo", "0123456789");
        ProvisioningProfile result = ProvisioningProfile.find(Arrays.asList(p1, p2, p3), si, "com.foo.bar");
        assertSame(p3, result);
    }

    @Test
    public void testFindDirectMatch() throws Exception {
        ProvisioningProfile p1 = createProfile("Profile 1", "App 1", "DF6YH89WE3", "DF6YH89WE3.*", "0123456789");
        ProvisioningProfile p2 = createProfile("Profile 2", "App 2", "HSKDO63H63", "HSKDO63H63.com.*", "0123456789");
        ProvisioningProfile p3 = createProfile("Profile 3", "App 3", "GS73MK54DW", "GS73MK54DW.com.foo.bar", "0123456789");
        SigningIdentity si = new SigningIdentity("foo", "0123456789");
        ProvisioningProfile result = ProvisioningProfile.find(Arrays.asList(p1, p2, p3), si, "com.foo.bar");
        assertSame(p3, result);
    }

    @Test
    public void testFindNoFingerPrintMatch() throws Exception {
        ProvisioningProfile p1 = createProfile("Profile 1", "App 1", "DF6YH89WE3", "DF6YH89WE3.*", "1123456789");
        ProvisioningProfile p2 = createProfile("Profile 2", "App 2", "HSKDO63H63", "HSKDO63H63.com.*", "2123456789");
        ProvisioningProfile p3 = createProfile("Profile 3", "App 3", "GS73MK54DW", "GS73MK54DW.com.foo.bar", "3123456789");
        SigningIdentity si = new SigningIdentity("foo", "0123456789");
        try {
            ProvisioningProfile.find(Arrays.asList(p1, p2, p3), si, "com.foo.bar");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testFindOnlyWildcardFingerPrintMatch() throws Exception {
        ProvisioningProfile p1 = createProfile("Profile 1", "App 1", "DF6YH89WE3", "DF6YH89WE3.*", "0123456789");
        ProvisioningProfile p2 = createProfile("Profile 2", "App 2", "HSKDO63H63", "HSKDO63H63.com.*", "2123456789");
        ProvisioningProfile p3 = createProfile("Profile 3", "App 3", "GS73MK54DW", "GS73MK54DW.com.foo.bar", "3123456789");
        SigningIdentity si = new SigningIdentity("foo", "0123456789");
        ProvisioningProfile result = ProvisioningProfile.find(Arrays.asList(p1, p2, p3), si, "com.foo.bar");
        assertSame(p1, result);
    }
    
    @SuppressWarnings("unchecked")
    private ProvisioningProfile createProfile(String name, String appIdName, String appIdPrefix, String appId,
            String ... fingerprints) throws Exception {
        
        NSDictionary entitlements = new NSDictionary();
        entitlements.put("application-identifier", appId);
        
        NSDictionary dict = new NSDictionary();
        dict.put("UUID", UUID.randomUUID().toString());
        dict.put("Name", name);
        dict.put("AppIDName", appIdName);
        dict.put("ApplicationIdentifierPrefix", new NSArray(new NSString(appIdPrefix)));
        dict.put("CreationDate", new NSDate(new Date()));
        dict.put("ExpirationDate", new NSDate(new Date()));
        dict.put("Entitlements", entitlements);
        dict.put("DeveloperCertificates", new NSArray());
        ProvisioningProfile profile = new ProvisioningProfile(new File(""), dict);
        Field f = ProvisioningProfile.class.getDeclaredField("certFingerprints");
        f.setAccessible(true);
        List<String> certFingerprints = (List<String>) f.get(profile);
        certFingerprints.addAll(Arrays.asList(fingerprints));
        return profile;
    }
}
