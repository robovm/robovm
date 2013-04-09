/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Boris V. Kuznetsov
 * @version $Revision$
 */

package org.apache.harmony.security.tests.java.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.security.Provider;
import java.security.Security;
import java.security.SecurityPermission;
import java.security.Provider.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;

/**
 * Tests for <code>Provider</code> constructor and methods
 *
 */
public class ProviderTest extends TestCase {
    /*
     * Implementation note: The algorithm name ASH-1 might seem a bit strange,
     * but since the algorithms cannot be uninstalled anymore we need to make
     * sure there are not side-effects on other tests. Simply inserting SHA-1
     * destroys the existing provider infrastructure.
     */

    Provider[] storedProviders;

    Provider p;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        storedProviders = Security.getProviders();

        p = new MyProvider();
    }

    @Override
    protected void tearDown() throws Exception {
        p.remove("MessageDigest.ASH-1");
        p.remove("MessageDigest.abc");
        p.remove("Alg.Alias.MessageDigest.ASH1");

        for (Provider p: Security.getProviders()) {
            Security.removeProvider(p.getName());
        }

        for (Provider p: storedProviders) {
            Security.addProvider(p);
        }

        super.tearDown();
    }

    /*
     * Class under test for void Provider()
     */
    public final void testProvider() {
        if (!p.getProperty("Provider.id name").equals(
                String.valueOf(p.getName()))) {
            fail("Incorrect \"Provider.id name\" value");
        }
        if (!p.getProperty("Provider.id version").equals(
                String.valueOf(p.getVersion()))) {
            fail("Incorrect \"Provider.id version\" value");
        }
        if (!p.getProperty("Provider.id info").equals(
                String.valueOf(p.getInfo()))) {
            fail("Incorrect \"Provider.id info\" value");
        }
        if (!p.getProperty("Provider.id className").equals(
                p.getClass().getName())) {
            fail("Incorrect \"Provider.id className\" value");
        }
    }

    public final void testClear() {
        p.clear();
        assertNull(p.getProperty("MessageDigest.SHA-1"));
    }

    /*
     * Class under test for void Provider(String, double, String)
     */
    public final void testProviderStringdoubleString() {
        Provider p = new MyProvider("Provider name", 123.456, "Provider info");
        assertEquals("Provider name", p.getName());
        assertEquals(123.456, p.getVersion(), 0L);
        assertEquals("Provider info", p.getInfo());
    }

    public final void testGetName() {
        assertEquals("MyProvider", p.getName());
    }

    public final void testGetVersion() {
        assertEquals(1.0, p.getVersion(), 0L);
    }

    public final void testGetInfo() {
        assertEquals("Provider for testing", p.getInfo());
    }

    /*
     * Class under test for void putAll(Map)
     */
    public final void testPutAllMap() {
        HashMap hm = new HashMap();
        hm.put("MessageDigest.SHA-1", "aaa.bbb.ccc.ddd");
        hm.put("Property 1", "value 1");
        hm.put("serviceName.algName attrName", "attrValue");
        hm.put("Alg.Alias.engineClassName.aliasName", "standardName");
        p.putAll(hm);
        if (!"value 1".equals(p.getProperty("Property 1").trim()) ||
                !"attrValue".equals(p.getProperty("serviceName.algName attrName").trim()) ||
                !"standardName".equals(p.getProperty("Alg.Alias.engineClassName.aliasName").trim()) ||
                !"aaa.bbb.ccc.ddd".equals(p.getProperty("MessageDigest.SHA-1").trim()) ) {
            fail("Incorrect property value");
        }
    }

    /*
     * Class under test for Set entrySet()
     */
    public final void testEntrySet() {
        p.put("MessageDigest.SHA-256", "aaa.bbb.ccc.ddd");

        Set s = p.entrySet();
        try {
            s.clear();
            fail("Must return unmodifiable set");
        } catch (UnsupportedOperationException e) {
        }

        assertEquals("Incorrect set size", 8, s.size());

        for (Iterator it = s.iterator(); it.hasNext();) {
            Entry e = (Entry)it.next();
            String key = (String)e.getKey();
            String val = (String)e.getValue();
            if (key.equals("MessageDigest.SHA-1") && val.equals("SomeClassName")) {
                continue;
            }
            if (key.equals("Alg.Alias.MessageDigest.SHA1") && val.equals("SHA-1")) {
                continue;
            }
            if (key.equals("MessageDigest.abc") && val.equals("SomeClassName")) {
                continue;
            }
            if (key.equals("Provider.id className") && val.equals(p.getClass().getName())) {
                continue;
            }
            if (key.equals("Provider.id name") && val.equals("MyProvider")) {
                continue;
            }
            if (key.equals("MessageDigest.SHA-256") && val.equals("aaa.bbb.ccc.ddd")) {
                continue;
            }
            if (key.equals("Provider.id version") && val.equals("1.0")) {
                continue;
            }
            if (key.equals("Provider.id info") && val.equals("Provider for testing")) {
                continue;
            }
            fail("Incorrect set");
        }
    }

    /*
     * Class under test for Set keySet()
     */
    public final void testKeySet() {
        p.put("MessageDigest.SHA-256", "aaa.bbb.ccc.ddd");

        Set<Object> s = p.keySet();
        try {
            s.clear();
        } catch (UnsupportedOperationException e) {
        }
        Set s1 = p.keySet();

        assertNotSame(s, s1);
        assertFalse(s1.isEmpty());
        assertEquals(8, s1.size());

        assertTrue(s1.contains("MessageDigest.SHA-256"));
        assertTrue(s1.contains("MessageDigest.SHA-1"));
        assertTrue(s1.contains("Alg.Alias.MessageDigest.SHA1"));
        assertTrue(s1.contains("MessageDigest.abc"));
        assertTrue(s1.contains("Provider.id info"));
        assertTrue(s1.contains("Provider.id className"));
        assertTrue(s1.contains("Provider.id version"));
        assertTrue(s1.contains("Provider.id name"));
    }

    /*
     * Class under test for Collection values()
     */
    public final void testValues() {
        p.put("MessageDigest.ASH-256", "aaa.bbb.ccc.ddd");

        Collection<Object> c = p.values();
        try {
            c.clear();
        } catch (UnsupportedOperationException e) {
        }
        Collection c1 = p.values();

        assertNotSame(c, c1);
        assertFalse(c1.isEmpty());
        assertEquals(8, c1.size());

        assertTrue(c1.contains("MyProvider"));
        assertTrue(c1.contains("aaa.bbb.ccc.ddd"));
        assertTrue(c1.contains("Provider for testing"));
        assertTrue(c1.contains("1.0"));
        assertTrue(c1.contains("SomeClassName"));
        assertTrue(c1.contains("SHA-1"));
        assertTrue(c1.contains(p.getClass().getName()));
    }

    /*
     * Class under test for Object put(Object, Object)
     */
    public final void testPutObjectObject() {
        p.put("MessageDigest.SHA-1", "aaa.bbb.ccc.ddd");
        p.put("Type.Algorithm", "className");
        assertEquals("aaa.bbb.ccc.ddd", p.getProperty("MessageDigest.SHA-1")
                .trim());

        Set services = p.getServices();
        assertEquals(3, services.size());

        for (Iterator it = services.iterator(); it.hasNext();) {
            Provider.Service s = (Provider.Service)it.next();
            if ("Type".equals(s.getType()) &&
                    "Algorithm".equals(s.getAlgorithm()) &&
                    "className".equals(s.getClassName())) {
                continue;
            }
            if ("MessageDigest".equals(s.getType()) &&
                    "SHA-1".equals(s.getAlgorithm()) &&
                    "aaa.bbb.ccc.ddd".equals(s.getClassName())) {
                continue;
            }
            if ("MessageDigest".equals(s.getType()) &&
                    "abc".equals(s.getAlgorithm()) &&
                    "SomeClassName".equals(s.getClassName())) {
                continue;
            }
            fail("Incorrect service");
        }
    }

    /*
     * Class under test for Object remove(Object)
     */
    public final void testRemoveObject() {
        Object o = p.remove("MessageDigest.SHA-1");

        assertEquals("SomeClassName", o);
        assertNull(p.getProperty("MessageDigest.SHA-1"));
        assertEquals(1, p.getServices().size());
    }

    public final void testService1() {
        p.put("MessageDigest.SHA-1", "AnotherClassName");
        Provider.Service s = p.getService("MessageDigest", "SHA-1");
        assertEquals("AnotherClassName", s.getClassName());
    }

    public final void testGetServiceCaseSensitivity() {
        p.put("i.I", "foo");

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(new Locale("tr", "TR"));
        try {
            assertEquals("foo", p.getService("i", "i").getClassName());
            assertEquals("foo", p.getService("i", "I").getClassName());
            assertNull(p.getService("\u0130", "\u0130")); // Turkish dotless i and dotted I
            assertNull(p.getService("\u0131", "\u0131"));
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    // Regression for HARMONY-2760.
    public void testConstructor() {
        MyProvider myProvider = new MyProvider(null, 1, null);
        assertNull(myProvider.getName());
        assertNull(myProvider.getInfo());
        assertEquals("null", myProvider.getProperty("Provider.id name"));
        assertEquals("null", myProvider.getProperty("Provider.id info"));
    }

    class MyProvider extends Provider {
        MyProvider() {
            super("MyProvider", 1.0, "Provider for testing");
            put("MessageDigest.SHA-1", "SomeClassName");
            put("MessageDigest.abc", "SomeClassName");
            put("Alg.Alias.MessageDigest.SHA1", "SHA-1");
        }

        MyProvider(String name, double version, String info) {
            super(name, version, info);
        }

        // BEGIN android-added
        public void putService(Provider.Service s) {
            super.putService(s);
        }
        // END android-added

        // BEGIN android-added
        public void removeService(Provider.Service s) {
            super.removeService(s);
        }
        // END android-added

        // BEGIN android-added
        public int getNumServices() {
            return getServices().size();
        }
        // END android-added
    }

    // BEGIN android-added
    public final void testService2() {
        Provider[] pp = Security.getProviders("MessageDigest.ASH-1");
        if (pp == null) {
            return;
        }
        Provider p2 = pp[0];
        String old = p2.getProperty("MessageDigest.ASH-1");
        p2.put("MessageDigest.ASH-1", "AnotherClassName");
        Provider.Service s = p2.getService("MessageDigest", "ASH-1");
        if (!"AnotherClassName".equals(s.getClassName())) {
            fail("Incorrect class name " + s.getClassName());
        }
        try {
            s.newInstance(null);
            fail("No expected NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        }
    }
    // END android-added

    // BEGIN android-added
    public final void testGetServices() {
        MyProvider myProvider = new MyProvider(null, 1, null);
        Set<Provider.Service> services = myProvider.getServices();
        assertEquals(0, services.size());

        Provider.Service s[] = new Provider.Service[3];

        s[0] = new Provider.Service(p, "type1", "algorithm1", "className1",
                null, null);
        s[1] = new Provider.Service(p, "type2", "algorithm2", "className2",
                null, null);
        s[2] = new Provider.Service(p, "type3", "algorithm3", "className3",
                null, null);
        myProvider.putService(s[0]);
        myProvider.putService(s[1]);
        assertEquals(2, myProvider.getNumServices());
        Set<Service> actual = myProvider.getServices();

        assertTrue(actual.contains(s[0]));
        assertTrue(actual.contains(s[1]));
        assertTrue(!actual.contains(s[2]));

        myProvider.removeService(s[1]);
        actual = myProvider.getServices();
        assertEquals(1, myProvider.getNumServices());

        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(!actual.contains(s[2]));

        myProvider.putService(s[2]);
        actual = myProvider.getServices();
        assertEquals(2, myProvider.getNumServices());
        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));
    }
    // END android-added

    // BEGIN android-added
    public final void testPutService() {
        MyProvider myProvider = new MyProvider(null, 1, null);
        Provider.Service s[] = new Provider.Service[3];

        s[0] = new Provider.Service(p, "type1", "algorithm1", "className1",
                null, null);
        s[1] = new Provider.Service(p, "type2", "algorithm2", "className2",
                null, null);
        s[2] = new Provider.Service(p, "type3", "algorithm3", "className3",
                null, null);
        myProvider.putService(s[0]);
        myProvider.putService(s[1]);
        assertEquals(2, myProvider.getNumServices());
        Set<Service> actual = myProvider.getServices();

        assertTrue(actual.contains(s[0]));
        assertTrue(actual.contains(s[1]));
        assertTrue(!actual.contains(s[2]));

        myProvider.removeService(s[1]);
        assertEquals(1, myProvider.getNumServices());
        actual = myProvider.getServices();

        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(!actual.contains(s[2]));

        myProvider.putService(s[2]);
        actual = myProvider.getServices();
        assertEquals(2, myProvider.getNumServices());
        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));

        myProvider.putService(s[2]);
        actual = myProvider.getServices();
        assertEquals(2, myProvider.getNumServices());
        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));

        try {
            myProvider.putService(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }
    // END android-added

    // BEGIN android-added
    public final void testRemoveService() {
        MyProvider myProvider = new MyProvider(null, 1, null);
        try {
            myProvider.removeService(null);
            fail("NullPoiterException expected");
        } catch (NullPointerException e) {
            // expected
        }

        Provider.Service s[] = new Provider.Service[3];

        s[0] = new Provider.Service(p, "type0", "algorithm0", "className0",
                null, null);
        s[1] = new Provider.Service(p, "type1", "algorithm1", "className1",
                null, null);
        s[2] = new Provider.Service(p, "type2", "algorithm2", "className2",
                null, null);

        try {
            myProvider.removeService(s[0]);
        } catch (NullPointerException e) {
            fail("Unexpected exception");
        }

        myProvider.putService(s[0]);
        myProvider.putService(s[1]);
        myProvider.putService(s[2]);
        assertEquals(3, myProvider.getNumServices());
        Set<Service> actual = myProvider.getServices();

        assertTrue(actual.contains(s[0]));
        assertTrue(actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));

        myProvider.removeService(s[1]);
        assertEquals(2, myProvider.getNumServices());
        actual = myProvider.getServices();

        assertTrue(actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));

        myProvider.removeService(s[0]);
        assertEquals(1, myProvider.getNumServices());
        actual = myProvider.getServices();

        assertTrue(!actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(actual.contains(s[2]));

        myProvider.removeService(s[2]);
        assertEquals(0, myProvider.getNumServices());
        actual = myProvider.getServices();

        assertTrue(!actual.contains(s[0]));
        assertTrue(!actual.contains(s[1]));
        assertTrue(!actual.contains(s[2]));

        try {
            myProvider.removeService(null);
            fail("NullPoiterException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }
    // END android-added

    // BEGIN android-added
    public final void testLoad() throws IOException {
        InputStream is = new ByteArrayInputStream(writeProperties());
        MyProvider myProvider = new MyProvider("name", 1, "info");
        myProvider.load(is);
        assertEquals("tests.security", myProvider.get("test.pkg"));
        assertEquals("Unit Tests", myProvider.get("test.proj"));
        assertNull(myProvider.get("#commented.entry"));

        assertEquals("info", myProvider.get("Provider.id info"));
        String className = myProvider.getClass().toString();
        assertEquals(
                className.substring("class ".length(), className.length()),
                myProvider.get("Provider.id className"));
        assertEquals("1.0", myProvider.get("Provider.id version"));

        try {
            myProvider.load((InputStream) null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }
    // END android-added

    // BEGIN android-added
    public final void testLoad2() {
        class TestInputStream extends InputStream {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        }

        MyProvider p = new MyProvider();
        try {
            p.load(new TestInputStream());
            fail("expected IOException");
        } catch (IOException e) {
            // expected
        }
    }
    // END android-added

    // BEGIN android-added
    protected byte[] writeProperties() {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bout);
        ps.println("#commented.entry=Bogus");
        ps.println("test.pkg=tests.security");
        ps.println("test.proj=Unit Tests");
        ps.close();
        return bout.toByteArray();
    }
    // END android-added

    // BEGIN android-added
    static class TestSecurityManager extends SecurityManager {
        boolean called = false;
        private final String permissionName;

        public TestSecurityManager(String permissionName) {
            this.permissionName = permissionName;
        }

        @Override
        public void checkPermission(Permission permission) {
            if (permission instanceof SecurityPermission) {
                if (permissionName.equals(permission.getName())) {
                    called = true;
                }
            }
        }
    }
    // END android-added
}
