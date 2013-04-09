/*
 * Copyright (C) 2008 The Android Open Source Project
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

package libcore.java.util.jar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import junit.framework.TestCase;
import libcore.io.Streams;
import static tests.support.Support_Exec.execAndGetOutput;
import tests.support.resource.Support_Resources;


public class DalvikExecTest extends TestCase {

    String execDalvik1(String classpath, String mainClass, String arg1)
            throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();

        File dalvikvm = new File("/system/bin/dalvikvm");
        if (dalvikvm.exists()) {
            builder.command().add(dalvikvm.getPath());
        } else {
            builder.command().add("dalvikvm"); // for host mode, assume dalvikvm is on the path
        }

        builder.command().add("-Duser.language=en");
        builder.command().add("-Duser.region=US");
        builder.command().add("-Xbootclasspath:" + System.getProperty("java.boot.class.path"));
        builder.command().add("-classpath");
        builder.command().add(classpath);
        builder.command().add(mainClass);

        if (arg1 != null) {
            builder.command().add(arg1);
        }

        // Create a writable dalvik-cache under ANDROID_DATA.
        // The default dalvik-cache is only writable by the system user (and root).
        String tmp = System.getProperty("java.io.tmpdir");
        builder.environment().put("ANDROID_DATA", tmp);
        new File(tmp, "dalvik-cache").mkdir();

        return execAndGetOutput(builder);
    }

    String execDalvik (String classpath, String mainClass)
            throws IOException, InterruptedException {
        return execDalvik1(classpath, mainClass, null);
    }

    // Execute an existing JAR on dalvikvm using -classpath option.",
    public void test_execExistingJar () throws IOException, InterruptedException {
        String res;
        File jarFile;
        if (System.getProperty("java.vendor").contains("Android")) {
            //
            // Test against Android:
            //
            File tempDir = Support_Resources.createTempFolder();
            jarFile = Support_Resources.copyFile(
                    tempDir, null, "cts_dalvikExecTest.jar" );
            res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.HelloWorld");
            assertEquals("Hello Android World!", "Hello Android World!\n", res);

            res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.ResourceDumper");
            assertTrue("Android Resource Dumper started",
                    res.contains("Android Resource Dumper started"));
            assertTrue("This Resource contains some text.",
                    res.contains("This Resource contains some text."));
        } else {
            //
            // Test against RI:
            //
            // Do nothing!
        }
    }

    // Create a temp file, fill it with contents according to Dalvik JAR format, and execute it on dalvikvm using -classpath option.",
    public void test_execCreatedJar () throws IOException, InterruptedException {
        File jarFile = File.createTempFile("cts_dalvikExecTest_", ".jar");
        jarFile.deleteOnExit();

        // Create a JAR output stream on the temp file:
        JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(jarFile));

        // Define the entry for the classes.dex:
        jarOut.putNextEntry(new JarEntry("classes.dex"));

        // Fill in the classes.dex contents, i.e. the Dalvik executable code:
        // (See below for the detailed source code contents.)
        Streams.copy(Support_Resources.getResourceStream("cts_dalvikExecTest_classes.dex"), jarOut);

        // Now add a resource file:
        //
        jarOut.putNextEntry(new JarEntry("dalvikExecTest/myResource"));
        jarOut.write("This Resource contains some text.".getBytes());

        // Close the stream to the completed JAR file.
        jarOut.close();

        // The resulting JAR file contains the classes listed at the end of this text,
        // like the 'cts_dalvikExecTest.jar' as part of the resources, too.

        String res;

        res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.HelloWorld");
        assertEquals("Hello Android World!", "Hello Android World!\n", res);

        res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.ResourceDumper");
        assertTrue("Android Resource Dumper started",
                res.contains("Android Resource Dumper started"));
        assertTrue("This Resource contains some text.",
                res.contains("This Resource contains some text."));
    }


    /**
     * This test does quite the same as test_execCreatedJar, but includes a manifest.
     * Note however that the Dalvik JAR format does not require this manifest.
     * We just test whether the manifest is placed correctly within the JAR by
     * dumping its contents read as a simple text resource.
     * No! We can't do that so easily either, as there are other (parent) JARs
     * with a manifest inside, taken with precedence.
     * So we will reopen the JAR as a JarFile and check the manifest
     *  with a top level end-to-end approach.
     */
    public void test_execCreatedJarWithManifest () throws IOException, InterruptedException {
        File jarFile = File.createTempFile("cts_dalvikExecTest_", ".jar");
        jarFile.deleteOnExit();

        // Create the manifest:
        Manifest manifest = new Manifest();
        Attributes attrs = manifest.getMainAttributes();
        attrs.put(Attributes.Name.MANIFEST_VERSION, "3.1415962");
        attrs.put(Attributes.Name.MAIN_CLASS, "dalvikExecTest.HelloWorld");
        attrs.put(Attributes.Name.CLASS_PATH, jarFile.getName());

        // Create a JAR output stream on the temp file using the manifest:
        JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(jarFile), manifest);

        // Define the entry for the classes.dex:
        jarOut.putNextEntry(new JarEntry("classes.dex"));

        // Fill in the classes.dex contents, i.e. the Dalvik executable code:
        // (See below for the detailed source code contents.)
        Streams.copy(Support_Resources.getResourceStream("cts_dalvikExecTest_classes.dex"), jarOut);

        // Now add a resource file:
        //
        jarOut.putNextEntry(new JarEntry("dalvikExecTest/myResource"));
        jarOut.write("This Resource contains some text.".getBytes());

        // Close the stream to the completed JAR file.
        jarOut.close();

        // The resulting JAR file contains the classes listed at the end of this text,
        // like the 'cts_dalvikExecTest.jar' as part of the resources, too.

        String res;

        res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.HelloWorld");
        assertEquals("Hello Android World!", "Hello Android World!\n", res);

        res = execDalvik(jarFile.getAbsolutePath(), "dalvikExecTest.ResourceDumper");
        assertTrue("Android Resource Dumper started",
                res.contains("Android Resource Dumper started"));
        assertTrue("This Resource contains some text.",
                res.contains("This Resource contains some text."));

        // And now reread the manifest:
        //
        JarFile jarIn = new JarFile(jarFile);
        manifest = jarIn.getManifest();
        attrs = manifest.getMainAttributes();
        assertEquals("MANIFEST_VERSION must match!", "3.1415962",
                attrs.get(Attributes.Name.MANIFEST_VERSION));
        assertEquals("MAIN_CLASS must match!", "dalvikExecTest.HelloWorld",
                attrs.get(Attributes.Name.MAIN_CLASS));
        assertEquals("CLASS_PATH must match!", jarFile.getName(),
                attrs.get(Attributes.Name.CLASS_PATH));
    }


    /*
     * The following two classes are added, here, only for completeness.
     * They form the contents of the dalvikExecTest package contained
     * in the 'cts_dalvikExecTest_classes.dex' resource file.
     */
    /**
     * @hide
     */
    public static class HelloWorld {

        public static void main(String[] args) {
            System.out.println("Hello Android World!");
        }

    }

    public static class ResourceDumper {

        static ByteArrayOutputStream outputFrom (InputStream input) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int total = 0;
            int count;
            count = input.read(buffer);
            while (count != -1) {
                out.write(buffer, 0, count);
                total = total + count;
                count = input.read(buffer);
            }
            return out;
        }

        public static void main(String[] args) throws IOException {
            System.out.print("Android Resource Dumper started ");
            String fileName;
            if (args.length >= 1) {
                fileName = args[0];
                System.out.format("for argument '%s'.\n", fileName);
            } else {
                System.out.print("standard ");
                fileName = "myResource";
                System.out.println("for standard 'myResource'.");
            }
            InputStream is = ResourceDumper.class.getResourceAsStream(fileName);
            if (is != null) {
                System.out.println("Resource obtained and being dumped:");
                System.out.println(outputFrom(is).toString());
            }
        }

    }

}
