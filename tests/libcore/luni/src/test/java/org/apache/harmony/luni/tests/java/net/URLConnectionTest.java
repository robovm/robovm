/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.luni.tests.java.net;

import dalvik.annotation.BrokenTest;
import junit.framework.TestCase;
import tests.support.Support_Configuration;
import tests.support.Support_PortManager;
import tests.support.Support_TestWebData;
import tests.support.Support_TestWebServer;
import tests.support.resource.Support_Resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilePermission;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.SocketPermission;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.UnknownServiceException;
import java.security.Permission;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class URLConnectionTest extends TestCase {

    private static final String testString = "Hello World";

    private URLConnection fileURLCon;

    private URL fileURL;

    private JarURLConnection jarURLCon;

    private URLConnection gifURLCon;

    /**
     * {@link java.net.URLConnection#addRequestProperty(String, String)}
     */
    public void test_addRequestProperty() throws MalformedURLException,
            IOException {


        MockURLConnection u = new MockURLConnection(new URL(
                "http://www.apache.org"));

        try {
            // Regression for HARMONY-604
            u.addRequestProperty(null, "someValue");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        u.connect();
        try {
            // state of connection is checked first
            // so no NPE in case of null 'field' param
            u.addRequestProperty(null, "someValue");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

    }

    /**
     * {@link java.net.URLConnection#setRequestProperty(String, String)}
     */
    public void test_setRequestProperty() throws MalformedURLException,
            IOException {

        MockURLConnection u = new MockURLConnection(new URL(
                "http://www.apache.org"));
        try {
            u.setRequestProperty(null, "someValue");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            u.setRequestProperty("user-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.0; de-DE; rv:1.7.5) Gecko/20041122 Firefox/1.0");
        } catch (NullPointerException e) {
            fail("Unexpected Exception");
        }

        u.connect();

        try {
            // state of connection is checked first
            // so no NPE in case of null 'field' param
            u.setRequestProperty(null, "someValue");
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    /**
     * {@link java.net.URLConnection#setUseCaches(boolean)}
     */
    public void test_setUseCachesZ() throws MalformedURLException, IOException {

        // Regression for HARMONY-71
        MockURLConnection u = new MockURLConnection(new URL(
                "http://www.apache.org"));
        u.connect();
        try {
            u.setUseCaches(true);
            fail("Assert 0: expected an IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    /**
     * {@link java.net.URLConnection#setAllowUserInteraction(boolean)}
     */
    public void test_setAllowUserInteractionZ() throws MalformedURLException,
            IOException {

        // Regression for HARMONY-72
        MockURLConnection u = new MockURLConnection(new URL(
                "http://www.apache.org"));
        u.connect();
        try {
            u.setAllowUserInteraction(false);
            fail("Assert 0: expected an IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    static class MockURLConnection extends URLConnection {

        public MockURLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() {
            connected = true;
        }
    }

    static class NewHandler extends URLStreamHandler {
        protected URLConnection openConnection(URL u) throws IOException {
            return new HttpURLConnection(u) {
                @Override
                public void connect() throws IOException {
                    connected = true;
                }

                @Override
                public void disconnect() {
                    // do nothing
                }

                @Override
                public boolean usingProxy() {
                    return false;
                }
            };
        }
    }

    private static int port;

    URL url;

    URL url2;

    URLConnection uc;

    URLConnection uc2;

    Support_TestWebServer server;

    @Override
    public void setUp() throws Exception {
        super.setUp();

//        ftpURL = new URL(Support_Configuration.testFTPURL);

        port = Support_PortManager.getNextPort();
        server = new Support_TestWebServer();
        server.initServer(port, false);
        url = new URL("http://localhost:" + port + "/test1");
        uc = url.openConnection();
        url2 =  new URL("http://localhost:" + port + "/test2");
        uc2 = url2.openConnection();

        fileURL = createTempHelloWorldFile();
        fileURLCon = fileURL.openConnection();

        jarURLCon = openJarURLConnection();
        gifURLCon = openGifURLConnection();
    }

    @Override
    public void tearDown()throws Exception {
        super.tearDown();
        server.close();
        ((HttpURLConnection) uc).disconnect();
        ((HttpURLConnection) uc2).disconnect();
//        if (((FtpURLConnection) ftpURLCon).getInputStream() !=  null) {
//        ((FtpURLConnection) ftpURLCon).getInputStream().close();
//        }
    }

    /**
     * @throws URISyntaxException
     * @throws ClassNotFoundException
     * {@link java.net.URLConnection#addRequestProperty(java.lang.String,java.lang.String)}
     */
    public void test_addRequestPropertyLjava_lang_StringLjava_lang_String()
            throws IOException, ClassNotFoundException, URISyntaxException {
        uc.setRequestProperty("prop", "yo");
        uc.setRequestProperty("prop", "yo2");
        assertEquals("yo2", uc.getRequestProperty("prop"));
        Map<String, List<String>> map = uc.getRequestProperties();
        List<String> props = uc.getRequestProperties().get("prop");
        assertEquals(1, props.size());

        try {
            // the map should be unmodifiable
            map.put("hi", Arrays.asList(new String[] { "bye" }));
            fail("could modify map");
        } catch (UnsupportedOperationException e) {
            // Expected
        }
        try {
            // the list should be unmodifiable
            props.add("hi");
            fail("could modify list");
        } catch (UnsupportedOperationException e) {
            // Expected
        }

        JarURLConnection con1 = openJarURLConnection();
        map = con1.getRequestProperties();
        assertNotNull(map);
        assertEquals(0, map.size());
        try {
            // the map should be unmodifiable
            map.put("hi", Arrays.asList(new String[] { "bye" }));
            fail();
        } catch (UnsupportedOperationException e) {
            // Expected
        }
    }

    public void testHttpPostHeaders() throws IOException {
        String path = "/" + Math.random();
        HttpURLConnection connection = (HttpURLConnection)
                new URL("http://localhost:" + port + path).openConnection();

        // post a request
        connection.setDoOutput(true);
        OutputStreamWriter writer
                = new OutputStreamWriter(connection.getOutputStream());
        writer.write("hello");
        writer.flush();
        assertEquals(200, connection.getResponseCode());

        // validate the request by asking the server what was received
        Map<String, String> headers = server.pathToRequest().get(path).getHeaders();
        assertNull(headers.get("Accept"));
        assertEquals("application/x-www-form-urlencoded", headers.get("Content-Type"));
        assertEquals("5", headers.get("Content-Length"));
        assertEquals("localhost:" + port, headers.get("Host"));
        // TODO: test User-Agent?
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getAllowUserInteraction()}
     */
    public void test_getAllowUserInteraction() throws IOException {
        uc.setAllowUserInteraction(false);
        assertFalse("getAllowUserInteraction should have returned false", uc
                .getAllowUserInteraction());

        uc.setAllowUserInteraction(true);
        assertTrue("getAllowUserInteraction should have returned true", uc
                .getAllowUserInteraction());

        uc.connect();

        try {
            uc.setAllowUserInteraction(false);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            //ok
        }

        // test if setAllowUserInteraction works
        URL serverURL = new URL("http://onearth.jpl.nasa.gov/landsat.cgi");

        // connect to server
        URLConnection uc2 = serverURL.openConnection();
        HttpURLConnection conn = (HttpURLConnection) uc2;
        uc2.setAllowUserInteraction(true);

        uc2.setDoInput(true);
        uc2.setDoOutput(true);

        // get reference to stream to post to
        OutputStream os = uc2.getOutputStream();

        InputStream in = uc2.getInputStream();


        int contentLength = uc2.getContentLength();
        String contentType = uc2.getContentType();
        int numBytesRead = 0;
        int allBytesRead = 0;

        byte[] buffer = new byte[4096];

        do {

        numBytesRead = in.read(buffer);
        allBytesRead += allBytesRead + numBytesRead;

        } while (numBytesRead > 0);

        assertTrue(allBytesRead > 0);

        uc2.connect();

        numBytesRead = in.read(buffer);

        assertEquals(-1, numBytesRead);
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#connect()}
     */
    public void test_connect() throws IOException {

        uc.connect();
        ((HttpURLConnection) uc).disconnect();
        uc.connect();

        try {
            uc.setDoOutput(false);
        } catch (Exception e) {
            // ok
        }
    }

    /**
     * {@link java.net.URLConnection#getContent()}
     */
    public void test_getContent() throws IOException {
        byte[] ba = new byte[testString.getBytes().length];
        String buf = null;

        Object obj = fileURLCon.getContent();
        if (obj instanceof String) {
            buf = (String) obj;
            assertTrue("Incorrect content returned from fileURL: "+buf,
                    testString.equals(buf.trim()));
        } else if (obj instanceof InputStream) {
            InputStream i = (InputStream) obj;
            BufferedReader r = new BufferedReader(new InputStreamReader(i),testString.getBytes().length);
            buf = r.readLine();
            assertTrue("Incorrect content returned from fileURL: "+buf,
                    testString.equals(buf.trim()));
        } else {
            fail("Some unkown type is returned "+obj.toString());
        }

        //Exception test
        URL url = new URL("http://a/b/c/?y");
        URLConnection fakeCon = url.openConnection();
        try {
        fakeCon.getContent();
        } catch (IOException e) {
            //ok
        }

        ((HttpURLConnection) uc).disconnect();
        try {
            uc.getContent();
        } catch (IOException e) {
            //ok
        }

    }

    /**
     * {@link java.net.URLConnection#getContent(Class[])}
     */
    public void test_getContent_LjavalangClass() throws IOException {
        byte[] ba = new byte[600];

        fileURLCon.setDoInput(true);
        fileURLCon.connect();

        InputStream  helloWorld2 = (InputStream) fileURLCon.getContent(new Class[] {InputStream.class});
        assertNotNull(helloWorld2);
        BufferedReader r = new BufferedReader(new InputStreamReader(helloWorld2),testString.getBytes().length);
        assertTrue("Incorrect content returned from fileURL",
                testString.equals(r.readLine().trim()));

        String test = (String) fileURLCon.getContent(new Class[] {String.class} );
        assertNull(test);

        //Exception test
        ((HttpURLConnection) uc).disconnect();
        try {
            uc.getContent();
        } catch (IOException e) {
            //ok
        }

        try {
            ((InputStream) fileURLCon.getContent(null)).read(ba, 0, 600);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            ((InputStream) fileURLCon.getContent(new Class[] {})).read(ba, 0, 600);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            ((InputStream) fileURLCon.getContent(new Class[] { Class.class })).read(ba,
                    0, 600);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getContentEncoding()}
     */
    @BrokenTest("Fails in CTS, passes in CoreTestRunner")
    public void test_getContentEncoding() throws IOException {
        // faulty setup
        try {

        fileURLCon.getContentEncoding();
        fail("Exception expected");
        } catch (Throwable e) {
            //ok
        }

        // positive case

        URL url = new URL("http://www.amazon.com/");

        URLConnection con = url.openConnection();
        con.setRequestProperty("Accept-Encoding", "gzip");
        con.connect();

        assertEquals(con.getContentEncoding(), "gzip");


        uc2.setRequestProperty("Accept-Encoding", "bla");
        uc2.connect();

        assertNull(uc2.getContentEncoding());

    }

    /**
     * {@link java.net.URLConnection#getContentLength()}
     */
    public void test_getContentLength() {
        assertEquals(testString.getBytes().length,
                fileURLCon.getContentLength());
        assertEquals(Support_TestWebData.test1.length, uc.getContentLength());
        assertEquals(Support_TestWebData.test2.length, uc2.getContentLength());

        assertNotNull(jarURLCon.getContentLength());
        assertNotNull(gifURLCon.getContentLength());
    }

    /**
     * {@link java.net.URLConnection#getContentType()}
     */
    public void test_getContentType() throws IOException, MalformedURLException {

        assertTrue("getContentType failed: " + fileURLCon.getContentType(),
                fileURLCon.getContentType().contains("text/plain"));

        URLConnection htmlFileCon = openHTMLFile();
        String contentType = htmlFileCon.getContentType();
        if (contentType != null) {
            assertTrue(contentType.equalsIgnoreCase("text/html"));
        }


        /*
        contentType = uc.getContentType();
        if (contentType != null) {
        assertTrue(contentType.equalsIgnoreCase("text/html"));
        }

        contentType = gifURLCon.getContentType();
        if (contentType != null) {
        assertTrue(contentType.equalsIgnoreCase("image/gif"));
        }
        */

    }

    /**
     * {@link java.net.URLConnection#getDate()}
     */
    public void test_getDate() {
        // should be greater than 930000000000L which represents the past
        assertTrue("getDate gave wrong date: " + uc.getDate(),
                uc.getDate() > 930000000000L);
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getDefaultAllowUserInteraction()}
     */
    public void test_getDefaultAllowUserInteraction() throws IOException {
        boolean oldSetting = URLConnection.getDefaultAllowUserInteraction();

        URLConnection.setDefaultAllowUserInteraction(false);
        assertFalse(
                "getDefaultAllowUserInteraction should have returned false",
                URLConnection.getDefaultAllowUserInteraction());

        URLConnection.setDefaultAllowUserInteraction(true);
        assertTrue("getDefaultAllowUserInteraction should have returned true",
                URLConnection.getDefaultAllowUserInteraction());

        URLConnection.setDefaultAllowUserInteraction(oldSetting);
    }

    /**
     * {@link java.net.URLConnection#getDefaultRequestProperty(String)}
     */
    @SuppressWarnings("deprecation")
    public void test_getDefaultRequestPropertyLjava_lang_String() {
        URLConnection.setDefaultRequestProperty("Shmoo", "Blah");
        assertNull("setDefaultRequestProperty should have returned: null",
                URLConnection.getDefaultRequestProperty("Shmoo"));

        URLConnection.setDefaultRequestProperty("Shmoo", "Boom");
        assertNull("setDefaultRequestProperty should have returned: null",
                URLConnection.getDefaultRequestProperty("Shmoo"));

        assertNull("setDefaultRequestProperty should have returned: null",
                URLConnection.getDefaultRequestProperty("Kapow"));

        URLConnection.setDefaultRequestProperty("Shmoo", null);
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getDoInput()}
     */
    public void test_getDoInput() throws IOException {
        assertTrue("Should be set to true by default", uc.getDoInput());

        fileURLCon.setDoInput(true);
        assertTrue("Should have been set to true", fileURLCon.getDoInput());

        uc2.setDoInput(false);
        assertFalse("Should have been set to false", uc2.getDoInput());

        fileURLCon.connect();
        fileURLCon.getInputStream();

        uc2.connect();
        try {
            uc2.getInputStream();
        } catch (Throwable e) {
            // ok
        }

    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getDoOutput()}
     */
    public void test_getDoOutput() throws IOException {
        assertFalse("Should be set to false by default", uc.getDoOutput());

        uc.setDoOutput(true);
        assertTrue("Should have been set to true", uc.getDoOutput());

        uc.connect();
        uc.getOutputStream();

        uc2.setDoOutput(false);
        assertFalse("Should have been set to false", uc2.getDoOutput());
        uc2.connect();

        try{
            uc2.getOutputStream();
        } catch (Throwable e) {
            //ok
        }
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getExpiration()}
     */
    public void test_getExpiration() throws IOException {

        uc.connect();
        // should be unknown
        assertEquals("getExpiration returned wrong expiration", 0, uc
                .getExpiration());

        uc2.connect();
        assertTrue("getExpiration returned wrong expiration: " + uc2
                .getExpiration(), uc2.getExpiration() > 0);
    }

    /**
     * {@link java.net.URLConnection#getFileNameMap()}
     */
    public void test_getFileNameMap() {
        // Tests for the standard MIME types -- users may override these
        // in their JRE

        FileNameMap mapOld = URLConnection.getFileNameMap();

        try {
        // These types are defaulted
        assertEquals("text/html", mapOld.getContentTypeFor(".htm"));
        assertEquals("text/html", mapOld.getContentTypeFor(".html"));
        assertEquals("text/plain", mapOld.getContentTypeFor(".text"));
        assertEquals("text/plain", mapOld.getContentTypeFor(".txt"));

        // These types come from the properties file :
        // not black-box testing. Special tests moved to setContentType
        /*
        assertEquals("application/pdf", map.getContentTypeFor(".pdf"));
        assertEquals("application/zip", map.getContentTypeFor(".zip"));
        assertEquals("image/gif", map.getContentTypeFor("gif"));
        */

        URLConnection.setFileNameMap(new FileNameMap() {
            public String getContentTypeFor(String fileName) {
                return "Spam!";
            }
        });

            assertEquals("Incorrect FileNameMap returned", "Spam!",
                    URLConnection.getFileNameMap().getContentTypeFor(null));
        } finally {
            // unset the map so other tests don't fail
            URLConnection.setFileNameMap(mapOld);
        }
        // RI fails since it does not support fileName that does not begin with
        // '.'

    }

    /**
     * {@link java.net.URLConnection#getHeaderFieldDate(java.lang.String, long)}
     */
    public void test_getHeaderFieldDateLjava_lang_StringJ() {
        Support_TestWebData params = Support_TestWebData.testParams[0];

        long hf;
        hf = uc.getHeaderFieldDate("Content-Encoding", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'Content-Encoding':",
                Long.MIN_VALUE, hf);
        hf = uc.getHeaderFieldDate("Content-Length", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'Content-Length': ",
                Long.MIN_VALUE, hf);
        hf = uc.getHeaderFieldDate("Content-Type", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'Content-Type': ",
                Long.MIN_VALUE, hf);
        hf = uc.getHeaderFieldDate("content-type", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'content-type': ",
                Long.MIN_VALUE, hf);
        hf = uc.getHeaderFieldDate("Date", Long.MIN_VALUE);
        assertTrue("Wrong value returned for header field 'Date': " + hf,
                new Date().getTime() - hf < 5000);
        hf = uc.getHeaderFieldDate("SERVER", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'SERVER': ",
                Long.MIN_VALUE, hf);
        hf = uc.getHeaderFieldDate("Last-Modified", Long.MIN_VALUE);
        assertEquals("Long value returned for header field 'Last-Modified': ",
                Long.MIN_VALUE, hf);
    }

    /**
     * {@link java.net.URLConnection#getHeaderField(int)}
     */
    public void DISABLED_test_getHeaderFieldI() {
        int i = 0;
        String hf;
        boolean foundResponse = false;
        while ((hf = uc.getHeaderField(i++)) != null) {
            if (hf.equals(Support_Configuration.HomeAddressSoftware)) {
                foundResponse = true;
            }
        }
        assertTrue("Could not find header field containing \""
                + Support_Configuration.HomeAddressSoftware + "\"",
                foundResponse);

        i = 0;
        foundResponse = false;
        while ((hf = uc.getHeaderField(i++)) != null) {
            if (hf.equals(Support_Configuration.HomeAddressResponse)) {
                foundResponse = true;
            }
        }
        assertTrue("Could not find header field containing \""
                + Support_Configuration.HomeAddressResponse + "\"",
                foundResponse);
    }

    /**
     * {@link java.net.URLConnection#getHeaderFieldKey(int)}
     */
    public void DISABLED_test_getHeaderFieldKeyI() {
        String hf;
        boolean foundResponse = false;
        for (int i = 0; i < 100; i++) {
            hf = uc.getHeaderFieldKey(i);
            if (hf != null && hf.toLowerCase().equals("content-type")) {
                foundResponse = true;
                break;
            }
        }
        assertTrue(
                "Could not find header field key containing \"content-type\"",
                foundResponse);
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getHeaderFieldInt(String, int)}
     */
    public void test_getHeaderFieldInt() throws IOException, ParseException {
        Support_TestWebData params = Support_TestWebData.testParams[1];

        int hf = 0;
        hf = uc2.getHeaderFieldInt("Content-Encoding", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("Content-Length", Integer.MIN_VALUE);
        assertEquals(params.testLength, hf);
        hf = uc2.getHeaderFieldInt("Content-Type", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("Date", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("Expires", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("SERVER", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("Last-Modified", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("accept-ranges", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);
        hf = uc2.getHeaderFieldInt("DoesNotExist", Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, hf);

    }

    /**
     * {@link java.net.URLConnection#getHeaderField(java.lang.String)}
     */
    public void test_getHeaderFieldLjava_lang_String() {
        Support_TestWebData params = Support_TestWebData.testParams[0];

        String hf;
        hf = uc.getHeaderField("Content-Encoding");
        assertNull("String value returned for header field 'Content-Encoding':",
                hf);
        hf = uc.getHeaderField("Content-Length");
        assertEquals("Wrong value returned for header field 'Content-Length': ",
                String.valueOf(params.testLength), hf);
        hf = uc.getHeaderField("Content-Type");
        assertEquals("Wrong value returned for header field 'Content-Type': ",
                params.testType, hf);
        hf = uc.getHeaderField("content-type");
        assertEquals("Wrong value returned for header field 'content-type': ",
                params.testType, hf);
        hf = uc.getHeaderField("Date");
        assertTrue("Wrong string value returned for header field 'Date': "
                + hf, hf.length() > 20);
        hf = uc.getHeaderField("SERVER");
        assertEquals("Wrong value returned for header field 'SERVER': ",
                "TestWebServer" + port, hf);
        hf = uc.getHeaderField("Last-Modified");
        assertNull("Wrong string value returned for 'Last-Modified': "
                + hf, hf);
    }

    /**
     * @throws URISyntaxException
     * @throws ClassNotFoundException
     * {@link java.net.URLConnection#getHeaderFields()}
     */
    public void test_getHeaderFields() throws IOException, ClassNotFoundException, URISyntaxException {
        Support_TestWebData params = Support_TestWebData.testParams[1];

        try {
            uc2.getInputStream();
        } catch (IOException e) {
            fail("Error in test setup: "+e.getMessage());
        }

        Map<String, List<String>> headers = uc2.getHeaderFields();
        assertNotNull(headers);

        List<String> list = headers.get("content-type");
        if (list == null) {
            list = headers.get("Content-Type");
        }
        if (list == null) {
            list = headers.get("Content-type");
        }

        assertNotNull(list);
        String contentType = (String) list.get(0);
        assertEquals(params.testType, contentType);

        // there should be at least 2 headers
        assertTrue("Not more than one header in URL connection",
                headers.size() > 1);

        headers = jarURLCon.getHeaderFields();
        assertNotNull(headers);
        assertEquals(0, headers.size());

        try {
            // the map should be unmodifiable
            headers.put("hi", Arrays.asList(new String[] { "bye" }));
            fail("The map should be unmodifiable");
        } catch (UnsupportedOperationException e) {
            // Expected
        }
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getLastModified()}
     */
    public void test_getLastModified() throws IOException {

        URL url4 = new URL(Support_Configuration.hTTPURLwLastModified);
        URLConnection uc4 = url4.openConnection();

        uc4.connect();

        if (uc4.getLastModified() == 0) {
            System.out
                    .println("WARNING: Server does not support 'Last-Modified', test_getLastModified() not run");
            return;
        }

        long millis = uc4.getHeaderFieldDate("Last-Modified", 0);

        assertEquals(
                "Returned wrong getLastModified value.  Wanted: "
                        + " got: " + uc4.getLastModified(),
                millis, uc4.getLastModified());


        ((HttpURLConnection) uc).disconnect();
    }

    public void test_getOutputStream() throws IOException {
        String posted = "this is a test";
        URLConnection uc3 = new URL(Support_Configuration.hTTPURLgoogle)
                .openConnection();
        uc3.setDoOutput(true);
        uc3.connect();

        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(uc3
                .getOutputStream()), posted.getBytes().length);

        w.write(posted);
        w.flush();
        w.close();

        int code = ((HttpURLConnection) uc3).getResponseCode();


        // writing to url not allowed
        assertEquals("Got different responseCode ", 405, code);


        // try exception testing
        try {
            fileURLCon.setDoOutput(true);
            fileURLCon.connect();
            fileURLCon.getOutputStream();
        } catch (UnknownServiceException e) {
            // ok cannot write to fileURL
        }

        ((HttpURLConnection) uc2).disconnect();

        try {
            uc2.getOutputStream();
            fail("Exception expected");
        } catch (IOException e) {
            // ok
        }
    }

    /**
     * {@link java.net.URLConnection#getRequestProperties()}
     */
    public void test_getRequestProperties() {
        uc.setRequestProperty("whatever", "you like");
        Map headers = uc.getRequestProperties();

        // content-length should always appear
        List header = (List) headers.get("whatever");
        assertNotNull(header);

        assertEquals("you like", header.get(0));

        assertTrue(headers.size() >= 1);

        try {
            // the map should be unmodifiable
            headers.put("hi", "bye");
            fail();
        } catch (UnsupportedOperationException e) {
        }
        try {
            // the list should be unmodifiable
            header.add("hi");
            fail();
        } catch (UnsupportedOperationException e) {
        }

    }

    /**
     * {@link java.net.URLConnection#getRequestProperties()}
     */
    public void test_getRequestProperties_Exception() throws IOException {
        URL url = new URL("http", "test", 80, "index.html", new NewHandler());
        URLConnection urlCon = url.openConnection();
        urlCon.connect();

        try {
            urlCon.getRequestProperties();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

    }

    /**
     * {@link java.net.URLConnection#getRequestProperty(java.lang.String)}
     */
    public void test_getRequestProperty_LString_Exception() throws IOException {
        URL url = new URL("http", "test", 80, "index.html", new NewHandler());
        URLConnection urlCon = url.openConnection();
        urlCon.setRequestProperty("test", "testProperty");
        assertNull(urlCon.getRequestProperty("test"));

        urlCon.connect();
        try {
            urlCon.getRequestProperty("test");
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    /**
     * {@link java.net.URLConnection#getRequestProperty(java.lang.String)}
     */
    public void test_getRequestPropertyLjava_lang_String() {
        uc.setRequestProperty("Yo", "yo");
        assertTrue("Wrong property returned: " + uc.getRequestProperty("Yo"),
                uc.getRequestProperty("Yo").equals("yo"));
        assertNull("Wrong property returned: " + uc.getRequestProperty("No"),
                uc.getRequestProperty("No"));
    }

    /**
     * {@link java.net.URLConnection#getURL()}
     */
    public void test_getURL() {
        assertTrue("Incorrect URL returned", uc.getURL().equals(url));
    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#getUseCaches()}
     */
    public void test_getUseCaches() throws IOException {
        uc2.setUseCaches(false);
        assertTrue("getUseCaches should have returned false", !uc2
                .getUseCaches());
        uc2.setUseCaches(true);
        assertTrue("getUseCaches should have returned true", uc2.getUseCaches());

        uc2.connect();


        try {
        uc2.setUseCaches(false);
        fail("Exception expected");
        } catch (IllegalStateException e) {
            //ok
        }

        ((HttpURLConnection) uc2).disconnect();

    }

    /**
     * {@link java.net.URLConnection#guessContentTypeFromName(String)}
     */
    public void test_guessContentTypeFromName()
            throws IOException {

        URLConnection htmlFileCon = openHTMLFile();
        String[] expected = new String[] {"text/html",
                "text/plain" };
        String[] resources = new String[] {
                htmlFileCon.getURL().toString(),
                fileURL.toString()
                };
        for (int i = 0; i < resources.length; i++) {
                String mime = URLConnection.guessContentTypeFromName( resources[i]);
                assertEquals("checking " + resources[i] + " expected " + expected[i]+"got " + expected[i],
                expected[i], mime);
        }

        // Try simple case
        try {
            URLConnection.guessContentTypeFromStream(null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * {@link java.net.URLConnection#guessContentTypeFromStream(java.io.InputStream)}
     */
    public void test_guessContentTypeFromStreamLjava_io_InputStream()
            throws IOException {
        assertContentTypeEquals("ASCII", "text/html", "<html>");
        assertContentTypeEquals("ASCII", "text/html", "<head>");
        assertContentTypeEquals("ASCII", "text/html", "<head ");
        assertContentTypeEquals("ASCII", "text/html", "<body");
        assertContentTypeEquals("ASCII", "text/html", "<BODY ");
        assertContentTypeEquals("ASCII", "application/xml", "<?xml ");

        assertContentTypeEquals("UTF-8", "text/html", "<html>");
        assertContentTypeEquals("UTF-8", "text/html", "<head>");
        assertContentTypeEquals("UTF-8", "text/html", "<head ");
        assertContentTypeEquals("UTF-8", "text/html", "<body");
        assertContentTypeEquals("UTF-8", "text/html", "<BODY ");
        assertContentTypeEquals("UTF-8", "application/xml", "<?xml ");

        //"UTF-16BE", "UTF-16LE", "UTF-32BE" and
        //"UTF-32LE" are not supported

        // Try simple case
        try {
            URLConnection.guessContentTypeFromStream(null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        /* not supported
        // Test magic bytes
        byte[][] bytes = new byte[][] { { 'P', 'K' }, { 'G', 'I' } };
        expected = new String[] { "application/zip", "image/gif" };

        for (int i = 0; i < bytes.length; i++) {
            InputStream is = new ByteArrayInputStream(bytes[i]);
            assertEquals(expected[i], URLConnection
                    .guessContentTypeFromStream(is));
        }
        */
    }

    void assertContentTypeEquals(String encoding, String expected,
            String header) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String encodedString = new String(header.getBytes(), encoding);
        InputStream is = new ByteArrayInputStream(encodedString.getBytes());
        String mime = URLConnection.guessContentTypeFromStream(is);
        assertEquals("checking '" + header + "' with " + encoding,
                expected, mime);
    }

    /**
     * {@link java.net.URLConnection#setConnectTimeout(int)}
     */
    public void test_setConnectTimeoutI() throws Exception {
        URLConnection uc = new URL("http://localhost").openConnection();
        assertEquals(0, uc.getConnectTimeout());
        uc.setConnectTimeout(0);
        assertEquals(0, uc.getConnectTimeout());
        try {
            uc.setConnectTimeout(-100);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        assertEquals(0, uc.getConnectTimeout());
        uc.setConnectTimeout(100);
        assertEquals(100, uc.getConnectTimeout());
        try {
            uc.setConnectTimeout(-1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        assertEquals(100, uc.getConnectTimeout());

        uc2.setConnectTimeout(2);

        try {
        uc2.connect();
        } catch (SocketTimeoutException e) {
            //ok
        }

    }

    /**
     * @throws IOException
     * {@link java.net.URLConnection#setFileNameMap(java.net.FileNameMap)}
     */
    public void test_setFileNameMapLjava_net_FileNameMap() throws IOException {
        FileNameMap mapOld = URLConnection.getFileNameMap();
        // nothing happens if set null
        URLConnection.setFileNameMap(null);
        // take no effect
        assertNotNull(URLConnection.getFileNameMap());

        try {
        URLConnection.setFileNameMap(new java.net.FileNameMap(){
                        public String getContentTypeFor(String fileName) {
                           if (fileName==null || fileName.length()<1)
                      return null;
                    String name=fileName.toLowerCase();
                    String type=null;
                    if (name.endsWith(".xml"))
                      type="text/xml";
                    else if (name.endsWith(".dtd"))
                      type="text/dtd";
                    else if (name.endsWith(".pdf"))
                        type = "application/pdf";
                    else if (name.endsWith(".zip"))
                        type = "application/zip";
                    else if (name.endsWith(".gif"))
                        type = "image/gif";
                    else
                      type="application/unknown";
                    return type;
                         }
              });
        FileNameMap mapNew = URLConnection.getFileNameMap();
        assertEquals("application/pdf", mapNew.getContentTypeFor(".pdf"));
        assertEquals("application/zip", mapNew.getContentTypeFor(".zip"));
        assertEquals("image/gif", mapNew.getContentTypeFor(".gif"));
        } finally {

        URLConnection.setFileNameMap(mapOld);
        }
    }

    /**
     * {@link java.net.URLConnection#setIfModifiedSince(long)}
     */
    public void test_setIfModifiedSinceJ() throws IOException {
        URL url = new URL("http://localhost:8080/");
        URLConnection connection = url.openConnection();
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        cal.clear();
        cal.set(2000, Calendar.MARCH, 5);

        long sinceTime = cal.getTime().getTime();
        connection.setIfModifiedSince(sinceTime);
        assertEquals("Wrong date set", sinceTime, connection
                .getIfModifiedSince());

        // content should be returned

        uc2.setIfModifiedSince(sinceTime);
        uc2.connect();


        assertEquals(200,((HttpURLConnection) uc2).getResponseCode());

        try {
            uc2.setIfModifiedSince(2);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            //ok
        }

        ((HttpURLConnection) uc2).disconnect();


    }

    public void test_getIfModifiedSinceJ() throws IOException {

        uc2.setIfModifiedSince(Calendar.getInstance().getTimeInMillis());
        uc2.connect();

        assertEquals(200,((HttpURLConnection) uc2).getResponseCode());

    }


    /**
     * {@link java.net.URLConnection#setReadTimeout(int)}
     */
    public void test_setReadTimeoutI() throws Exception {
        assertEquals(0, uc.getReadTimeout());
        uc.setReadTimeout(0);
        assertEquals(0, uc.getReadTimeout());
        try {
            uc.setReadTimeout(-100);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        assertEquals(0, uc.getReadTimeout());
        uc.setReadTimeout(100);
        assertEquals(100, uc.getReadTimeout());
        try {
            uc.setReadTimeout(-1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        assertEquals(100, uc.getReadTimeout());

        byte[] ba = new byte[600];

        uc2.setReadTimeout(5);
        uc2.setDoInput(true);
        uc2.connect();

        try {
        ((InputStream) uc2.getInputStream()).read(ba, 0, 600);
        } catch (SocketTimeoutException e) {
            //ok
        } catch ( UnknownServiceException e) {
            fail(""+e.getMessage());
        }
    }

    /**
     * {@link java.net.URLConnection#toString()}
     */
    public void test_toString() {

        assertTrue("Wrong toString: " + uc.toString(), uc.toString().indexOf(
                "URLConnection") > 0);
        assertTrue("Wrong toString: " + uc.toString(), uc.toString().indexOf(
                uc.getURL().toString()) > 0);
    }

    public void test_URLConnection() {
        String url = uc2.getURL().toString();
        assertEquals(url2.toString(), url);
    }

    public void testGetInputStream() throws IOException {
        fileURLCon.setDoInput(true);
        fileURLCon.connect();

        BufferedReader buf = new BufferedReader(new InputStreamReader(
                fileURLCon.getInputStream()), testString.getBytes().length);

        String nextline;
        while ((nextline = buf.readLine()) != null) {
            assertEquals(testString, nextline);
        }

        buf.close();

        assertNotNull(uc.getInputStream());

        ((HttpURLConnection) uc2).disconnect();

        assertNotNull(uc2.getInputStream());

    }

    private URLConnection openGifURLConnection() throws IOException {
        String cts = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(cts);
        Support_Resources.copyFile(tmpDir, null, "Harmony.GIF");
        URL fUrl1 = new URL("file:/" + tmpDir.getPath()
                + "/Harmony.GIF");
        URLConnection con1 = fUrl1.openConnection();
        return con1;
    }

    private JarURLConnection openJarURLConnection()
            throws MalformedURLException, IOException {
        String cts = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(cts);
        Support_Resources.copyFile(tmpDir, null, "hyts_att.jar");
        URL fUrl1 = new URL("jar:file:" + tmpDir.getPath()
                + "/hyts_att.jar!/");
        JarURLConnection con1 = (JarURLConnection) fUrl1.openConnection();
        return con1;
    }

    private URLConnection openHTMLFile() throws IOException {
        String cts = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(cts);
        Support_Resources.copyFile(tmpDir, null, "hyts_htmltest.html");
        URL fUrl1 = new URL("file:/" + tmpDir.getPath()
                + "/hyts_htmltest.html");
        URLConnection con1 = fUrl1.openConnection();
        return con1;
    }

    private URL createTempHelloWorldFile() throws MalformedURLException {
        // create content to read
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File sampleFile = null;
        try {
            if (tmpDir.isDirectory()) {
                sampleFile = File.createTempFile("openStreamTest", ".txt",
                        tmpDir);
                sampleFile.deleteOnExit();
            } else {
                fail("Error in test setup tmpDir does not exist");
            }

            FileWriter fstream = new FileWriter(sampleFile);
            BufferedWriter out = new BufferedWriter(fstream, testString.getBytes().length);
            out.write(testString);
            // Close the output stream
            out.close();
        } catch (Exception e) {// Catch exception if any
            fail("Error: in test setup" + e.getMessage());
        }

        // read content from file
        return sampleFile.toURL();
    }
}
