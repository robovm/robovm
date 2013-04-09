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

package libcore.java.net;

import dalvik.annotation.SideEffect;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Manifest;
import org.apache.harmony.security.tests.support.TestCertUtils;
import tests.support.Support_Configuration;
import tests.support.Support_PortManager;
import tests.support.Support_TestWebData;
import tests.support.Support_TestWebServer;
import tests.support.resource.Support_Resources;

public class OldURLClassLoaderTest extends junit.framework.TestCase {

    URLClassLoader ucl;
    SecurityManager sm = new SecurityManager() {

        public void checkPermission(Permission perm) {
        }

        public void checkCreateClassLoader() {
            throw new SecurityException();
        }
    };

    /**
     * java.net.URLClassLoader#URLClassLoader(java.net.URL[])
     */
    public void test_Constructor$Ljava_net_URL() throws MalformedURLException {
        URL[] u = new URL[0];
        ucl = new URLClassLoader(u);
        assertTrue("Failed to set parent", ucl != null
                && ucl.getParent() == URLClassLoader.getSystemClassLoader());


        URL [] urls = {new URL("http://foo.com/foo"),
                       new URL("jar:file://foo.jar!/foo.c"),
                       new URL("ftp://foo1/foo2/foo.c")};

        URLClassLoader ucl1 = new URLClassLoader(urls);
        assertTrue(urls.length == ucl1.getURLs().length);

        try {
            Class.forName("test", false, ucl);
            fail("Should throw ClassNotFoundException");
        } catch (ClassNotFoundException e) {
            // expected
        }

        try {
            new URLClassLoader(new URL[] { null });
        } catch(Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    /**
     * java.net.URLClassLoader#findResources(java.lang.String)
     */
    public void test_findResourcesLjava_lang_String() throws Exception {
        Enumeration<URL> res = null;
        String[] resValues = { "This is a test resource file.",
                "This is a resource from a subdir"};

        String tmp = System.getProperty("java.io.tmpdir") + "/";

        File tmpDir = new File(tmp);
        File test1 = new File(tmp + "test0");
        test1.deleteOnExit();
        FileOutputStream out = new FileOutputStream(test1);
        out.write(resValues[0].getBytes());
        out.flush();
        out.close();

        File subDir = new File(tmp + "subdir/");
        subDir.mkdir();
        File test2 = new File(tmp + "subdir/test0");
        test2.deleteOnExit();
        out = new FileOutputStream(test2);
        out.write(resValues[1].getBytes());
        out.flush();
        out.close();

        URL[] urls = new URL[2];
        urls[0] = new URL("file://" + tmpDir.getAbsolutePath() + "/");
        urls[1] = new URL("file://" + subDir.getAbsolutePath() + "/");

        ucl = new URLClassLoader(urls);
        res = ucl.findResources("test0");
        assertNotNull("Failed to locate resources", res);

        int i = 0;
        while (res.hasMoreElements()) {
            StringBuffer sb = getResContent(res.nextElement());
            assertEquals("Returned incorrect resource/or in wrong order",
                    resValues[i++], sb.toString());
        }
        assertEquals("Incorrect number of resources returned", 2, i);
    }

    public void test_addURLLjava_net_URL() throws MalformedURLException {
        URL[] u = new URL[0];

        URL [] urls = {new URL("http://foo.com/foo"),
                       new URL("jar:file://foo.jar!/foo.c"),
                       new URL("ftp://foo1/foo2/foo.c"), null};

        TestURLClassLoader tucl = new TestURLClassLoader(u);

        for(int i = 0; i < urls.length;) {
            tucl.addURL(urls[i]);
            i++;
            URL [] result = tucl.getURLs();
            assertEquals("Result array length is incorrect: " + i,
                                                            i, result.length);
            for(int j = 0; j < result.length; j++) {
                assertEquals("Result array item is incorrect: " + j,
                                                            urls[j], result[j]);
            }
        }
    }

    public void test_definePackage() throws MalformedURLException {
        Manifest manifest = new Manifest();
        URL[] u = new URL[0];
        TestURLClassLoader tucl = new TestURLClassLoader(u);

        URL [] urls = {new URL("http://foo.com/foo"),
                new URL("jar:file://foo.jar!/foo.c"),
                new URL("ftp://foo1/foo2/foo.c"),
                new URL("file://new/package/name/"),
                null};

        String packageName = "new.package.name";

        for(int i = 0; i < urls.length; i++) {
            Package pack = tucl.definePackage(packageName + i, manifest, urls[i]);
            assertEquals(packageName + i, pack.getName());
            assertNull("Implementation Title is not null",
                    pack.getImplementationTitle());
            assertNull("Implementation Vendor is not null",
                    pack.getImplementationVendor());
            assertNull("Implementation Version is not null.",
                    pack.getImplementationVersion());
        }

        try {
            tucl.definePackage(packageName + "0", manifest, null);
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    class TestURLClassLoader extends URLClassLoader {
        public TestURLClassLoader(URL[] urls) {
            super(urls);
        }

        public void addURL(URL url) {
            super.addURL(url);
        }

        public Package definePackage(String name,
                                     Manifest man,
                                     URL url)
                                     throws IllegalArgumentException {
            return super.definePackage(name, man, url);
        }

        public Class<?> findClass(String name)
                                        throws ClassNotFoundException {
            return super.findClass(name);
        }

        protected PermissionCollection getPermissions(CodeSource codesource) {
            return super.getPermissions(codesource);
        }
    }

    @SideEffect("Support_TestWebServer requires isolation.")
    public void test_findResourceLjava_lang_String() throws Exception {
        int port = Support_PortManager.getNextPort();
        File tmp = File.createTempFile("test", ".txt");

        Support_TestWebServer server = new Support_TestWebServer();
        try {

            server.initServer(port, tmp.getAbsolutePath(), "text/html");

            URL[] urls = { new URL("http://localhost:" + port + "/") };
            ucl = new URLClassLoader(urls);
            URL res = ucl.findResource("test1");
            assertNotNull("Failed to locate resource", res);

            StringBuffer sb = getResContent(res);
            assertEquals("Returned incorrect resource", new String(Support_TestWebData.test1),
                    sb.toString());
        } finally {
            server.close();
        }
    }

    /**
     * Regression for Harmony-2237
     */
    @SideEffect("Support_TestWebServer requires isolation.")
    public void test_findResource_String() throws Exception {
        File tempFile1 = File.createTempFile("textFile", ".txt");
        tempFile1.createNewFile();
        tempFile1.deleteOnExit();
        File tempFile2 = File.createTempFile("jarFile", ".jar");
        tempFile2.delete();
        tempFile2.deleteOnExit();

        Support_TestWebServer server = new Support_TestWebServer();
        int port = Support_PortManager.getNextPort();
        try {
            server.initServer(port, false);

            String tempPath1 = tempFile1.getParentFile().getAbsolutePath() + "/";
            InputStream is = getClass().getResourceAsStream(
                    "/tests/resources/hyts_patch.jar");
            Support_Resources.copyLocalFileto(tempFile2, is);
            String tempPath2 = tempFile2.getAbsolutePath();
            String tempPath3 = "http://localhost:" + port + "/";
            URLClassLoader urlLoader = getURLClassLoader(tempPath1, tempPath2);
            assertNull("Found inexistant resource",
                    urlLoader.findResource("XXX"));
            assertNotNull("Couldn't find resource from directory",
                    urlLoader.findResource(tempFile1.getName()));
            assertNotNull("Couldn't find resource from jar",
                    urlLoader.findResource("Blah.txt"));
            urlLoader = getURLClassLoader(tempPath1, tempPath2, tempPath3);
            assertNotNull("Couldn't find resource from web",
                    urlLoader.findResource("test1"));
            assertNull("Found inexistant resource from web",
                    urlLoader.findResource("test3"));
        } finally {
            server.close();
        }
    }

    private static URLClassLoader getURLClassLoader(String... classPath)
            throws MalformedURLException {
        List<URL> urlList = new ArrayList<URL>();
        for (String path : classPath) {
            String url;
            File f = new File(path);
            if (f.isDirectory()) {
                url = "file:" + path;
            } else if (path.startsWith("http")) {
                url = path;
            } else {
                url = "jar:file:" + path + "!/";
            }
            urlList.add(new URL(url));
        }
        return new URLClassLoader(urlList.toArray(new URL[urlList.size()]));
    }

    private StringBuffer getResContent(URL res) throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStream is = res.openStream();

        int c;
        while ((c = is.read()) != -1) {
            sb.append((char) c);
        }
        is.close();
        return sb;
    }
}
