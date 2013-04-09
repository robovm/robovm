/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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
package org.apache.harmony.archive.tests.java.util.jar;


import junit.framework.TestCase;

import tests.support.Support_PlatformFile;
import tests.support.resource.Support_Resources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Permission;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


public class JarFileTest extends TestCase {

    // BEGIN android-added
    public byte[] getAllBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        byte[] buf = new byte[666];
        int iRead;
        int off;
        while (is.available() > 0) {
            iRead = is.read(buf, 0, buf.length);
            if (iRead > 0) bs.write(buf, 0, iRead);
        }
        return bs.toByteArray();
    }

    // END android-added

    private final String jarName = "hyts_patch.jar"; // a 'normal' jar file

    private final String jarName2 = "hyts_patch2.jar";

    private final String jarName3 = "hyts_manifest1.jar";

    private final String jarName4 = "hyts_signed.jar";

    private final String jarName5 = "hyts_signed_inc.jar";

    private final String entryName = "foo/bar/A.class";

    private final String entryName3 = "coucou/FileAccess.class";

    private final String integrateJar = "Integrate.jar";

    private final String integrateJarEntry = "Test.class";

    private final String emptyEntryJar = "EmptyEntries_signed.jar";

    private final String emptyEntry1 = "subfolder/internalSubset01.js";

    private final String emptyEntry2 = "svgtest.js";

    private final String emptyEntry3 = "svgunit.js";

    private File resources;

    // custom security manager
    SecurityManager sm = new SecurityManager() {
        final String forbidenPermissionName = "user.dir";

        public void checkPermission(Permission perm) {
            if (perm.getName().equals(forbidenPermissionName)) {
                throw new SecurityException();
            }
        }
    };

    @Override
    protected void setUp() {
        resources = Support_Resources.createTempFolder();
    }

    /**
     * java.util.jar.JarFile#JarFile(java.io.File)
     */
    public void test_ConstructorLjava_io_File() {
        try {
            JarFile jarFile = new JarFile(new File("Wrong.file"));
            fail("Should throw IOException");
        } catch (IOException e) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName));
        } catch (IOException e) {
            fail("Should not throw IOException");
        }
    }

    /**
     * java.util.jar.JarFile#JarFile(java.lang.String)
     */
    public void test_ConstructorLjava_lang_String() {
        try {
            JarFile jarFile = new JarFile("Wrong.file");
            fail("Should throw IOException");
        } catch (IOException e) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            String fileName = (new File(resources, jarName)).getCanonicalPath();
            JarFile jarFile = new JarFile(fileName);
        } catch (IOException e) {
            fail("Should not throw IOException");
        }
    }

    /**
     * java.util.jar.JarFile#JarFile(java.lang.String, boolean)
     */
    public void test_ConstructorLjava_lang_StringZ() {
        try {
            JarFile jarFile = new JarFile("Wrong.file", false);
            fail("Should throw IOException");
        } catch (IOException e) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            String fileName = (new File(resources, jarName)).getCanonicalPath();
            JarFile jarFile = new JarFile(fileName, true);
        } catch (IOException e) {
            fail("Should not throw IOException");
        }
    }

    /**
     * java.util.jar.JarFile#JarFile(java.io.File, boolean)
     */
    public void test_ConstructorLjava_io_FileZ() {
        try {
            JarFile jarFile = new JarFile(new File("Wrong.file"), true);
            fail("Should throw IOException");
        } catch (IOException e) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName), false);
        } catch (IOException e) {
            fail("Should not throw IOException");
        }
    }

    /**
     * java.util.jar.JarFile#JarFile(java.io.File, boolean, int)
     */
    public void test_ConstructorLjava_io_FileZI() {
        try {
            JarFile jarFile = new JarFile(new File("Wrong.file"), true,
                    ZipFile.OPEN_READ);
            fail("Should throw IOException");
        } catch (IOException e) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName), false,
                    ZipFile.OPEN_READ);
        } catch (IOException e) {
            fail("Should not throw IOException");
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName), false,
                    ZipFile.OPEN_READ | ZipFile.OPEN_DELETE + 33);
            fail("Should throw IllegalArgumentException");
        } catch (IOException e) {
            fail("Should not throw IOException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * Constructs JarFile object.
     *
     * java.util.jar.JarFile#JarFile(java.io.File)
     * java.util.jar.JarFile#JarFile(java.lang.String)
     */
    public void testConstructor_file() throws IOException {
        File f = new File(resources, jarName);
        Support_Resources.copyFile(resources, null, jarName);
        assertTrue(new JarFile(f).getEntry(entryName).getName().equals(
                entryName));
        assertTrue(new JarFile(f.getPath()).getEntry(entryName).getName()
                .equals(entryName));
    }

    /**
     * java.util.jar.JarFile#entries()
     */
    public void test_entries() throws Exception {
        /*
         * Note only (and all of) the following should be contained in the file
         * META-INF/ META-INF/MANIFEST.MF foo/ foo/bar/ foo/bar/A.class Blah.txt
         */
        Support_Resources.copyFile(resources, null, jarName);
        JarFile jarFile = new JarFile(new File(resources, jarName));
        Enumeration<JarEntry> e = jarFile.entries();
        int i;
        for (i = 0; e.hasMoreElements(); i++) {
            e.nextElement();
        }
        assertEquals(jarFile.size(), i);
        jarFile.close();
        assertEquals(6, i);
    }

    public void test_entries2() throws Exception {
        Support_Resources.copyFile(resources, null, jarName);
        JarFile jarFile = new JarFile(new File(resources, jarName));
        Enumeration<JarEntry> enumeration = jarFile.entries();
        jarFile.close();
        try {
            enumeration.hasMoreElements();
            fail("hasMoreElements() did not detect a closed jar file");
        } catch (IllegalStateException e) {
        }
        Support_Resources.copyFile(resources, null, jarName);
        jarFile = new JarFile(new File(resources, jarName));
        enumeration = jarFile.entries();
        jarFile.close();
        try {
            enumeration.nextElement();
            fail("nextElement() did not detect closed jar file");
        } catch (IllegalStateException e) {
        }
    }

    /**
     * @throws IOException
     * java.util.jar.JarFile#getJarEntry(java.lang.String)
     */
    public void test_getEntryLjava_lang_String() throws IOException {
        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName));
            assertEquals("Error in returned entry", 311, jarFile.getEntry(
                    entryName).getSize());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during test: " + e.toString());
        }

        Support_Resources.copyFile(resources, null, jarName);
        JarFile jarFile = new JarFile(new File(resources, jarName));
        Enumeration<JarEntry> enumeration = jarFile.entries();
        assertTrue(enumeration.hasMoreElements());
        while (enumeration.hasMoreElements()) {
            JarEntry je = enumeration.nextElement();
            jarFile.getEntry(je.getName());
        }

        enumeration = jarFile.entries();
        assertTrue(enumeration.hasMoreElements());
        JarEntry je = enumeration.nextElement();
        try {
            jarFile.close();
            jarFile.getEntry(je.getName());
            // fail("IllegalStateException expected.");
        } catch (IllegalStateException ee) { // Per documentation exception
            // may be thrown.
            // expected
        }
    }

    /**
     * @throws IOException
     * java.util.jar.JarFile#getJarEntry(java.lang.String)
     */
    public void test_getJarEntryLjava_lang_String() throws IOException {
        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName));
            assertEquals("Error in returned entry", 311, jarFile.getJarEntry(
                    entryName).getSize());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during test: " + e.toString());
        }

        Support_Resources.copyFile(resources, null, jarName);
        JarFile jarFile = new JarFile(new File(resources, jarName));
        Enumeration<JarEntry> enumeration = jarFile.entries();
        assertTrue(enumeration.hasMoreElements());
        while (enumeration.hasMoreElements()) {
            JarEntry je = enumeration.nextElement();
            jarFile.getJarEntry(je.getName());
        }

        enumeration = jarFile.entries();
        assertTrue(enumeration.hasMoreElements());
        JarEntry je = enumeration.nextElement();
        try {
            jarFile.close();
            jarFile.getJarEntry(je.getName());
            // fail("IllegalStateException expected.");
        } catch (IllegalStateException ee) { // Per documentation exception
            // may be thrown.
            // expected
        }
    }


    /**
     * java.util.jar.JarFile#getJarEntry(java.lang.String)
     */
    public void testGetJarEntry() throws Exception {
        Support_Resources.copyFile(resources, null, jarName);
        JarFile jarFile = new JarFile(new File(resources, jarName));
        assertEquals("Error in returned entry", 311, jarFile.getEntry(
                entryName).getSize());
        jarFile.close();

        // tests for signed jars
        // test all signed jars in the /Testres/Internal/SignedJars directory
        String jarDirUrl = Support_Resources
                .getResourceURL("/../internalres/signedjars");
        Vector<String> signedJars = new Vector<String>();
        try {
            InputStream is = new URL(jarDirUrl + "/jarlist.txt").openStream();
            while (is.available() > 0) {
                StringBuilder linebuff = new StringBuilder(80); // Typical line
                // length
                done: while (true) {
                    int nextByte = is.read();
                    switch (nextByte) {
                        case -1:
                            break done;
                        case (byte) '\r':
                            if (linebuff.length() == 0) {
                                // ignore
                            }
                            break done;
                        case (byte) '\n':
                            if (linebuff.length() == 0) {
                                // ignore
                            }
                            break done;
                        default:
                            linebuff.append((char) nextByte);
                    }
                }
                if (linebuff.length() == 0) {
                    break;
                }
                String line = linebuff.toString();
                signedJars.add(line);
            }
            is.close();
        } catch (IOException e) {
            // no list of jars found
        }

        for (int i = 0; i < signedJars.size(); i++) {
            String jarName = signedJars.get(i);
            try {
                File file = Support_Resources.getExternalLocalFile(jarDirUrl
                        + "/" + jarName);
                jarFile = new JarFile(file, true);
                boolean foundCerts = false;
                Enumeration<JarEntry> e = jarFile.entries();
                while (e.hasMoreElements()) {
                    JarEntry entry = e.nextElement();
                    InputStream is = jarFile.getInputStream(entry);
                    is.skip(100000);
                    is.close();
                    Certificate[] certs = entry.getCertificates();
                    if (certs != null && certs.length > 0) {
                        foundCerts = true;
                        break;
                    }
                }
                assertTrue(
                        "No certificates found during signed jar test for jar \""
                                + jarName + "\"", foundCerts);
            } catch (IOException e) {
                fail("Exception during signed jar test for jar \"" + jarName
                        + "\": " + e.toString());
            }
        }
    }

    /**
     * java.util.jar.JarFile#getManifest()
     */
    public void test_getManifest() {
        // Test for method java.util.jar.Manifest
        // java.util.jar.JarFile.getManifest()
        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName));
            assertNotNull("Error--Manifest not returned", jarFile.getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 1st test: " + e.toString());
        }
        try {
            Support_Resources.copyFile(resources, null, jarName2);
            JarFile jarFile = new JarFile(new File(resources, jarName2));
            assertNull("Error--should have returned null", jarFile
                    .getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 2nd test: " + e.toString());
        }

        try {
            // jarName3 was created using the following test
            Support_Resources.copyFile(resources, null, jarName3);
            JarFile jarFile = new JarFile(new File(resources, jarName3));
            assertNotNull("Should find manifest without verifying", jarFile
                    .getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 3rd test: " + e.toString());
        }

        try {
            // this is used to create jarName3 used in the previous test
            Manifest manifest = new Manifest();
            Attributes attributes = manifest.getMainAttributes();
            attributes.put(new Attributes.Name("Manifest-Version"), "1.0");
            ByteArrayOutputStream manOut = new ByteArrayOutputStream();
            manifest.write(manOut);
            byte[] manBytes = manOut.toByteArray();
            File file = File.createTempFile(
                    Support_PlatformFile.getNewPlatformFile("hyts_manifest1",
                            ""), ".jar");
            JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(
                    file.getAbsolutePath()));
            ZipEntry entry = new ZipEntry("META-INF/");
            entry.setSize(0);
            jarOut.putNextEntry(entry);
            entry = new ZipEntry(JarFile.MANIFEST_NAME);
            entry.setSize(manBytes.length);
            jarOut.putNextEntry(entry);
            jarOut.write(manBytes);
            entry = new ZipEntry("myfile");
            entry.setSize(1);
            jarOut.putNextEntry(entry);
            jarOut.write(65);
            jarOut.close();
            JarFile jar = new JarFile(file.getAbsolutePath(), false);
            assertNotNull("Should find manifest without verifying", jar
                    .getManifest());
            jar.close();
            file.delete();
        } catch (IOException e) {
            fail("IOException 3");
        }
        try {
            Support_Resources.copyFile(resources, null, jarName2);
            JarFile jF = new JarFile(new File(resources, jarName2));
            jF.close();
            jF.getManifest();
            fail("FAILED: expected IllegalStateException");
        } catch (IllegalStateException ise) {
            // expected;
        } catch (Exception e) {
            fail("Exception during 4th test: " + e.toString());
        }

        Support_Resources.copyFile(resources, null, "Broken_manifest.jar");
        JarFile jf;
        try {
            jf = new JarFile(new File(resources, "Broken_manifest.jar"));
            jf.getManifest();
            fail("IOException expected.");
        } catch (IOException e) {
            // expected.
        }
    }

    /**
     * java.util.jar.JarFile#getInputStream(java.util.zip.ZipEntry)
     */
    // This test doesn't pass on RI. If entry size is set up incorrectly,
    // SecurityException is thrown. But SecurityException is thrown on RI only
    // if jar file is signed incorrectly.
    public void test_getInputStreamLjava_util_jar_JarEntry_subtest0() {
        File signedFile = null;
        try {
            Support_Resources.copyFile(resources, null, jarName4);
            signedFile = new File(resources, jarName4);
        } catch (Exception e) {
            fail("Failed to create local file 2: " + e);
        }

        try {
            JarFile jar = new JarFile(signedFile);
            JarEntry entry = new JarEntry(entryName3);
            InputStream in = jar.getInputStream(entry);
            in.read();
        } catch (Exception e) {
            fail("Exception during test 3: " + e);
        }

        try {
            JarFile jar = new JarFile(signedFile);
            JarEntry entry = new JarEntry(entryName3);
            InputStream in = jar.getInputStream(entry);
            // BEGIN android-added
            byte[] dummy = getAllBytesFromStream(in);
            // END android-added
            assertNull("found certificates", entry.getCertificates());
        } catch (Exception e) {
            fail("Exception during test 4: " + e);
        }

        try {
            JarFile jar = new JarFile(signedFile);
            JarEntry entry = new JarEntry(entryName3);
            entry.setSize(1076);
            InputStream in = jar.getInputStream(entry);
            // BEGIN android-added
            byte[] dummy = getAllBytesFromStream(in);
            // END android-added
            fail("SecurityException should be thrown.");
        } catch (SecurityException e) {
            // expected
        } catch (Exception e) {
            fail("Exception during test 5: " + e);
        }

        try {
            Support_Resources.copyFile(resources, null, jarName5);
            signedFile = new File(resources, jarName5);
        } catch (Exception e) {
            fail("Failed to create local file 5: " + e);
        }

        try {
            JarFile jar = new JarFile(signedFile);
            JarEntry entry = new JarEntry(entryName3);
            InputStream in = jar.getInputStream(entry);
            fail("SecurityException should be thrown.");
        } catch (SecurityException e) {
            // expected
        } catch (Exception e) {
            fail("Exception during test 5: " + e);
        }
    }

    /*
     * The jar created by 1.4 which does not provide a
     * algorithm-Digest-Manifest-Main-Attributes entry in .SF file.
     */
    public void test_Jar_created_before_java_5() throws IOException {
        String modifiedJarName = "Created_by_1_4.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            jarFile.getInputStream(zipEntry);
        }
    }

    /* The jar is intact, then everything is all right. */
    public void test_JarFile_Integrate_Jar() throws IOException {
        String modifiedJarName = "Integrate.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            jarFile.getInputStream(zipEntry).skip(Long.MAX_VALUE);
        }
    }

    /**
     * The jar is intact, but the entry object is modified.
     */
    public void testJarVerificationModifiedEntry() throws IOException {
        Support_Resources.copyFile(resources, null, integrateJar);
        File f = new File(resources, integrateJar);

        JarFile jarFile = new JarFile(f);
        ZipEntry zipEntry = jarFile.getJarEntry(integrateJarEntry);
        zipEntry.setSize(zipEntry.getSize() + 1);
        jarFile.getInputStream(zipEntry).skip(Long.MAX_VALUE);

        jarFile = new JarFile(f);
        zipEntry = jarFile.getJarEntry(integrateJarEntry);
        zipEntry.setSize(zipEntry.getSize() - 1);
        try {
            //jarFile.getInputStream(zipEntry).skip(Long.MAX_VALUE);
            jarFile.getInputStream(zipEntry).read(new byte[5000], 0, 5000);
            fail("SecurityException expected");
        } catch (SecurityException e) {
            // desired
        }
    }

    /*
     * If another entry is inserted into Manifest, no security exception will be
     * thrown out.
     */
    public void test_JarFile_InsertEntry_in_Manifest_Jar() throws IOException {
        String modifiedJarName = "Inserted_Entry_Manifest.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        int count = 0;
        while (entries.hasMoreElements()) {

            ZipEntry zipEntry = entries.nextElement();
            jarFile.getInputStream(zipEntry);
            count++;
        }
        assertEquals(5, count);
    }

    /*
     * If another entry is inserted into Manifest, no security exception will be
     * thrown out.
     */
    public void test_Inserted_Entry_Manifest_with_DigestCode()
            throws IOException {
        String modifiedJarName = "Inserted_Entry_Manifest_with_DigestCode.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        int count = 0;
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            jarFile.getInputStream(zipEntry);
            count++;
        }
        assertEquals(5, count);
    }

    /*
     * The content of Test.class is modified, jarFile.getInputStream will not
     * throw security Exception, but it will anytime before the inputStream got
     * from getInputStream method has been read to end.
     */
    public void test_JarFile_Modified_Class() throws IOException {
        String modifiedJarName = "Modified_Class.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            jarFile.getInputStream(zipEntry);
        }
        /* The content of Test.class has been tampered. */
        ZipEntry zipEntry = jarFile.getEntry("Test.class");
        InputStream in = jarFile.getInputStream(zipEntry);
        byte[] buffer = new byte[1024];
        try {
            while (in.available() > 0) {
                in.read(buffer);
            }
            fail("SecurityException expected");
        } catch (SecurityException e) {
            // desired
        }
    }

    /*
     * In the Modified.jar, the main attributes of META-INF/MANIFEST.MF is
     * tampered manually. Hence the RI 5.0 JarFile.getInputStream of any
     * JarEntry will throw security exception.
     */
    public void test_JarFile_Modified_Manifest_MainAttributes()
            throws IOException {
        String modifiedJarName = "Modified_Manifest_MainAttributes.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            try {
                jarFile.getInputStream(zipEntry);
                fail("SecurityException expected");
            } catch (SecurityException e) {
                // desired
            }
        }
    }

    /*
     * It is all right in our original JarFile. If the Entry Attributes, for
     * example Test.class in our jar, the jarFile.getInputStream will throw
     * Security Exception.
     */
    public void test_JarFile_Modified_Manifest_EntryAttributes()
            throws IOException {
        String modifiedJarName = "Modified_Manifest_EntryAttributes.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            try {
                jarFile.getInputStream(zipEntry);
                fail("should throw Security Exception");
            } catch (SecurityException e) {
                // desired
            }
        }
    }

    /*
     * If the content of the .SA file is modified, no matter what it resides,
     * JarFile.getInputStream of any JarEntry will throw Security Exception.
     */
    public void test_JarFile_Modified_SF_EntryAttributes() throws IOException {
        String modifiedJarName = "Modified_SF_EntryAttributes.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            try {
                jarFile.getInputStream(zipEntry);
                fail("should throw Security Exception");
            } catch (SecurityException e) {
                // desired
            }
        }
    }

    public void test_close() throws IOException {
        String modifiedJarName = "Modified_SF_EntryAttributes.jar";
        Support_Resources.copyFile(resources, null, modifiedJarName);
        JarFile jarFile = new JarFile(new File(resources, modifiedJarName),
                true);
        Enumeration<JarEntry> entries = jarFile.entries();

        jarFile.close();
        jarFile.close();

        // Can not check IOException
    }

    /**
     * @throws IOException
     * java.util.jar.JarFile#getInputStream(java.util.zip.ZipEntry)
     */
    public void test_getInputStreamLjava_util_jar_JarEntry() throws IOException {
        File localFile = null;
        try {
            Support_Resources.copyFile(resources, null, jarName);
            localFile = new File(resources, jarName);
        } catch (Exception e) {
            fail("Failed to create local file: " + e);
        }

        byte[] b = new byte[1024];
        try {
            JarFile jf = new JarFile(localFile);
            java.io.InputStream is = jf.getInputStream(jf.getEntry(entryName));
            // BEGIN android-removed
            // jf.close();
            // END android-removed
            assertTrue("Returned invalid stream", is.available() > 0);
            int r = is.read(b, 0, 1024);
            is.close();
            StringBuffer sb = new StringBuffer(r);
            for (int i = 0; i < r; i++) {
                sb.append((char) (b[i] & 0xff));
            }
            String contents = sb.toString();
            assertTrue("Incorrect stream read", contents.indexOf("bar") > 0);
            // BEGIN android-added
            jf.close();
            // END android-added
        } catch (Exception e) {
            fail("Exception during test: " + e.toString());
        }

        try {
            JarFile jf = new JarFile(localFile);
            InputStream in = jf.getInputStream(new JarEntry("invalid"));
            assertNull("Got stream for non-existent entry", in);
        } catch (Exception e) {
            fail("Exception during test 2: " + e);
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            File signedFile = new File(resources, jarName);
            JarFile jf = new JarFile(signedFile);
            JarEntry jre = new JarEntry("foo/bar/A.class");
            jf.getInputStream(jre);
            // InputStream returned in any way, exception can be thrown in case
            // of reading from this stream only.
            // fail("Should throw ZipException");
        } catch (ZipException ee) {
            // expected
        }

        try {
            Support_Resources.copyFile(resources, null, jarName);
            File signedFile = new File(resources, jarName);
            JarFile jf = new JarFile(signedFile);
            JarEntry jre = new JarEntry("foo/bar/A.class");
            jf.close();
            jf.getInputStream(jre);
            // InputStream returned in any way, exception can be thrown in case
            // of reading from this stream only.
            // The same for IOException
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException ee) {
            // expected
        }
    }

    /**
     * The jar is intact, but the entry object is modified.
     */
    // Regression test for issue introduced by HARMONY-4569: signed archives containing files with size 0 could not get verified.
    public void testJarVerificationEmptyEntry() throws IOException {
        Support_Resources.copyFile(resources, null, emptyEntryJar);
        File f = new File(resources, emptyEntryJar);

        JarFile jarFile = new JarFile(f);

        ZipEntry zipEntry = jarFile.getJarEntry(emptyEntry1);
        int res = jarFile.getInputStream(zipEntry).read(new byte[100], 0, 100);
        assertEquals("Wrong length of empty jar entry", -1, res);

        zipEntry = jarFile.getJarEntry(emptyEntry2);
        res = jarFile.getInputStream(zipEntry).read(new byte[100], 0, 100);
        assertEquals("Wrong length of empty jar entry", -1, res);

        zipEntry = jarFile.getJarEntry(emptyEntry3);
        res = jarFile.getInputStream(zipEntry).read();
        assertEquals("Wrong length of empty jar entry", -1, res);
    }
}
