/*
 * Copyright (C) 2013 RoboVM AB
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
package org.robovm.compiler.config;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jdom2.output.Format;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robovm.compiler.config.Config.Builder;
import org.robovm.compiler.config.Config.Home;
import org.robovm.compiler.config.Config.Lib;
import org.robovm.compiler.target.ConsoleTarget;
import org.robovm.compiler.target.ios.IOSTarget;
import org.zeroturnaround.zip.ZipUtil;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import java.util.Comparator;
import java.util.List;

/**
 * Tests {@link Config}.
 */
public class ConfigTest {
    String savedUserDir;
    File tmp;
    File wd;
    Home fakeHome;
    
    @Before
    public void setUp() throws Exception {
        savedUserDir = System.getProperty("user.dir");
        tmp = new File("/tmp");
        wd = new File(tmp, "wd");
        System.setProperty("user.dir", wd.getAbsolutePath());
        
        fakeHome = new FakeHome();
    }
    
    @After
    public void tearDown() {
        System.setProperty("user.dir", savedUserDir);
    }
    
    @Test
    public void testReadConsole() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.read(new InputStreamReader(getClass().getResourceAsStream("ConfigTest.console.xml"), "utf-8"), wd);
        Config config = builder.config;
        assertEquals(Arrays.asList(new File(wd, "foo1.jar"), new File(tmp, "foo2.jar")), config.getClasspath());
        assertEquals(Arrays.asList("Foundation", "AppKit"), config.getFrameworks());
        assertEquals(Arrays.asList(
                new Config.Lib("dl", true),
                new Config.Lib("/tmp/wd/libs/libmy.a", true),
                new Config.Lib("/tmp/wd/libs/foo.o", true),
                new Config.Lib("/usr/lib/libbar.a", false)
                ), config.getLibs());
        assertEquals(Arrays.asList(
                new Resource(new File(wd, "resources")), 
                new Resource(new File("/usr/share/resources")),
                new Resource(null, null).include("data/**/*"),
                new Resource(null, null).include("videos/**/*.avi"),
                new Resource(new File(wd, "resources"), "data")
                    .include("**/*.png")
                    .exclude("**/foo.png")
                    .flatten(true)
                ), config.getResources());
        assertEquals(Arrays.asList("javax.**.*"), config.getForceLinkClasses());
        assertEquals(OS.macosx, config.getOs());
        assertEquals(2, config.getArchs().size());
        assertEquals(Arch.x86, config.getArchs().get(0));
        assertEquals(Arch.x86_64, config.getArchs().get(1));
    }
    
    @Test
    public void testReadOldConsole() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.read(new InputStreamReader(getClass().getResourceAsStream("ConfigTest.old.console.xml"), "utf-8"), wd);
        Config config = builder.config;
        assertEquals(Arrays.asList(new File(wd, "foo1.jar"), new File(tmp, "foo2.jar")), config.getClasspath());
        assertEquals(Arrays.asList("Foundation", "AppKit"), config.getFrameworks());
        assertEquals(Arrays.asList(
                new Config.Lib("dl", true),
                new Config.Lib("/tmp/wd/libs/libmy.a", true),
                new Config.Lib("/tmp/wd/libs/foo.o", true),
                new Config.Lib("/usr/lib/libbar.a", false)
                ), config.getLibs());
        assertEquals(Arrays.asList(new Resource(new File("/tmp/wd/resources")), 
                new Resource(new File("/usr/share/resources"))),
                config.getResources());
        assertEquals(Arrays.asList("javax.**.*"), config.getForceLinkClasses());
        assertEquals(OS.macosx, config.getOs());
        assertEquals(1, config.getArchs().size());
        assertEquals(Arch.x86, config.getArchs().get(0));
    }

    @Test
    public void testWriteConsole() throws Exception {
        // Calculate the relative path
        File targetFile = new File("libs/libmy.a");
        File targetFile2 = new File("libs/foo.o");
        File targetFile3 = new File("foo1.jar");
        // build actual XML
        Config.Builder builder = new Config.Builder();
        builder.addClasspathEntry(targetFile3);
        builder.addClasspathEntry(new File(tmp, "foo2.jar"));
        builder.addFramework("Foundation");
        builder.addFramework("AppKit");
        builder.addLib(new Config.Lib("dl", true));
        builder.addLib(new Config.Lib("libs/libmy.a", true));
        builder.addLib(new Config.Lib("libs/foo.o", true));
        builder.addLib(new Config.Lib("/usr/lib/libbar.a", false));
        builder.addResource(new Resource(new File("/tmp/wd/resources")));
        builder.addResource(new Resource(new File("/usr/share/resources")));
        builder.addResource(new Resource(new File("/tmp/wd"), null).include("data/**/*"));
        builder.addResource(new Resource(null, null).include("videos/**/*.avi"));
        builder.addResource(
                new Resource(new File("/tmp/wd/resources"), "data")
                        .include("**/*.png")
                        .exclude("**/foo.png")
                        .flatten(true));
        builder.addForceLinkClass("javax.**.*");
        builder.os(OS.macosx);
        builder.archs(Arch.x86, Arch.x86_64);

        StringWriter out = new StringWriter();
        builder.write(out, wd);
        // Load the expected XML content from a resource file
        String expectedXml = IOUtils.toString(getClass().getResourceAsStream("ConfigTest.console.xml"));
        String actualXml = out.toString();
        // Modify the XML content in the expectedXml with the calculated relative path
        expectedXml = expectedXml.replace("<lib>libs/libmy.a</lib>", "<lib>" + targetFile.getAbsolutePath() + "</lib>");
        expectedXml = expectedXml.replace("<lib>libs/foo.o</lib>", "<lib>" + targetFile2.getAbsolutePath() + "</lib>");
        expectedXml = expectedXml.replace("<classpathentry>foo1.jar</classpathentry>", "<classpathentry>" + targetFile3.getAbsolutePath() + "</classpathentry>");
        // sort XMLs using JDOM
        String sortedActualXml = getSortedXml(actualXml);
        String sortedExpectedXml = getSortedXml(expectedXml);
        // test
        assertEquals(sortedExpectedXml, sortedActualXml);
    }

    private String getSortedXml(String xml) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new StringReader(xml));

        // Get the parent element and its children
        Element root = document.getRootElement();
        sortElementRecursively(root);
        // Serialize the sorted XML back to a string
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        String sortedXml = xmlOutputter.outputString(document);
        return sortedXml;
    }
    private void sortElementRecursively(Element element) {
        List<Element> children = element.getChildren();
        children.sort(Comparator.comparing(Element::getName));
        for (Element child : children) {
            sortElementRecursively(child); // Recursively sort child elements
        }
    }

    @Test
    public void testReadIOS() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.read(new InputStreamReader(getClass().getResourceAsStream("ConfigTest.ios.xml"), "utf-8"), wd);
        Config config = builder.config;
        assertEquals("6.1", config.getIosSdkVersion());
        assertEquals(new File(wd, "Info.plist"), config.getIosInfoPList().getFile());
        assertEquals(new File(wd, "entitlements.plist"), config.getIosEntitlementsPList());        
    }
    
    @Test
    public void testWriteIOS() throws Exception {
        File infoFile = new File("Info.plist");
        File entitlementsFile = new File("entitlements.plist");
        Config.Builder builder = new Config.Builder();
        builder.iosSdkVersion("6.1");
        builder.iosInfoPList(infoFile);
        builder.iosEntitlementsPList(entitlementsFile);
        builder.targetType(IOSTarget.TYPE);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        // Load the expected XML content from a resource file
        String expectedXml = IOUtils.toString(getClass().getResourceAsStream("ConfigTest.ios.xml"));
        String actualXml = out.toString();
        // Calculate the relative path
        expectedXml = expectedXml.replace("<iosInfoPList>Info.plist</iosInfoPList>", "<iosInfoPList>" + infoFile.getAbsolutePath() + "</iosInfoPList>");
        expectedXml = expectedXml.replace("<iosEntitlementsPList>entitlements.plist</iosEntitlementsPList>", "<iosEntitlementsPList>" + entitlementsFile.getAbsolutePath() + "</iosEntitlementsPList>");
        // sort XMLs using JDOM
        String sortedActualXml = getSortedXml(actualXml);
        String sortedExpectedXml = getSortedXml(expectedXml);
        // test
        assertEquals(sortedExpectedXml, sortedActualXml);
    }
    
    private File createMergeConfig(File tmpDir, String dir, String id, OS os, Arch arch, boolean jar) throws Exception {
        File p = new File(tmpDir, dir);
        for (OS os2 : OS.values()) {
            for (Arch arch2 : Arch.values()) {
                File root = new File(p, "META-INF/robovm/" + os2 + "/" + arch2);
                root.mkdirs();
                if (!new File(root, "robovm.xml").exists()) {
                    new Config.Builder().write(new File(root, "robovm.xml"));
                }
            }
        }

        File root = new File(p, "META-INF/robovm/" + os + "/" + arch);
        new Config.Builder()
            .addExportedSymbol(id.toUpperCase() + "*")
            .addForceLinkClass("com." + id.toLowerCase() + ".**")
            .addFrameworkPath(new File(root, id.toLowerCase() + "/bar"))
            .addFramework(id)
            .addLib(new Lib(id.toLowerCase(), true))
            .addLib(new Lib(new File(root, "lib" + id.toLowerCase() + ".a").getAbsolutePath(), true))
            .addResource(new Resource(new File(root, "resources")))
            .addWeakFramework("Weak" + id)
            .write(new File(root, "robovm.xml"));

        if (jar) {
            File jarFile = new File(tmpDir, p.getName() + ".jar");
            ZipUtil.pack(p, jarFile);
            FileUtils.deleteDirectory(p);
            return jarFile;
        } else {
            return p;
        }
    }
    
    private File createTempDir() throws IOException {
        final File tmp = File.createTempFile(getClass().getName(), ".tmp");
        tmp.delete();
        FileUtils.deleteDirectory(tmp);
        tmp.mkdirs();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    FileUtils.deleteDirectory(tmp);
                } catch (IOException e) {
                }
            }
        });
        return tmp;
    }
    
    @Test
    public void testMergeConfigsFromClasspath() throws Exception {
        File tmpDir = createTempDir();
        File cacheDir = new File(tmpDir, "cache");
        File p1 = createMergeConfig(tmpDir, "p1", "Foo", OS.macosx, Arch.x86, false);
        File p2 = createMergeConfig(tmpDir, "p2", "Wooz", OS.linux, Arch.x86, false);
        // Create a jar file with both x86 and x86_64 by first creating a folder for x86 in p3/ 
        // and then passing p3/ again but this time compress it to a jar.
                  createMergeConfig(tmpDir, "p3", "Baaz", OS.macosx, Arch.x86, false);
        File p3 = createMergeConfig(tmpDir, "p3", "Raaz", OS.macosx, Arch.x86_64, true);
        
        Config.Builder builder = new Config.Builder();
        builder.cacheDir(cacheDir);
        builder.os(OS.macosx);
        builder.arch(Arch.x86);
        builder.targetType(ConsoleTarget.TYPE);
        builder.mainClass("Main");
        builder.addClasspathEntry(p1);
        builder.addClasspathEntry(p2);
        builder.addClasspathEntry(p3);
        builder.addExportedSymbol("YADA*");
        builder.addFrameworkPath(new File(p1, "yada"));
        builder.addFramework("Yada");
        builder.addForceLinkClass("org.yada.**");
        builder.addLib(new Lib("yada", true));
        builder.addResource(new Resource(new File(p1, "resources")));
        builder.addWeakFramework("WeakYada");
        builder.home(fakeHome);
        Config config = builder.build();

        File p1X86Root = new File(p1, "META-INF/robovm/macosx/x86");
        File p3X86Cache = config.getCacheDir(config.getClazzes().getClasspathPaths().get(2));
        File p3X86Root = new File(p3X86Cache.getParentFile(), p3X86Cache.getName() + ".extracted/META-INF/robovm/macosx/x86");

        assertEquals(Arrays.asList("FOO*", "BAAZ*", "YADA*"), config.getExportedSymbols());
        assertEquals(Arrays.asList("com.foo.**", "com.baaz.**", "org.yada.**"), config.getForceLinkClasses());
        assertEquals(Arrays.asList(
                new File(p1X86Root, "foo/bar"), 
                new File(p3X86Root, "baaz/bar"), 
                new File(p1, "yada")), 
                config.getFrameworkPaths());
        assertEquals(Arrays.asList("Foo", "Baaz", "Yada"), config.getFrameworks());
        assertEquals(Arrays.asList(
                new Lib("foo", true), 
                new Lib(new File(p1X86Root, "libfoo.a").getAbsolutePath(), true), 
                new Lib("baaz", true), 
                new Lib(new File(p3X86Root, "libbaaz.a").getAbsolutePath(), true), 
                new Lib("yada", true)), 
                config.getLibs());
        assertEquals(Arrays.asList(
                new Resource(new File(p1X86Root, "resources")), 
                new Resource(new File(p3X86Root, "resources")), 
                new Resource(new File(p1, "resources"))), 
                config.getResources());
        assertEquals(Arrays.asList("WeakFoo", "WeakBaaz", "WeakYada"), config.getWeakFrameworks());
        
        // Make sure builder() returns a config which merges in x86_64 configs instead
        config = config.builder().arch(Arch.x86_64).build();
        
        File p3X86_64Cache = config.getCacheDir(config.getClazzes().getClasspathPaths().get(2));
        File p3X86_64Root = new File(p3X86_64Cache.getParentFile(), p3X86_64Cache.getName() + ".extracted/META-INF/robovm/macosx/x86_64");
        
        assertEquals(Arrays.asList("RAAZ*", "YADA*"), config.getExportedSymbols());
        assertEquals(Arrays.asList("com.raaz.**", "org.yada.**"), config.getForceLinkClasses());
        assertEquals(Arrays.asList(
                new File(p3X86_64Root, "raaz/bar"), 
                new File(p1, "yada")), 
                config.getFrameworkPaths());
        assertEquals(Arrays.asList("Raaz", "Yada"), config.getFrameworks());
        assertEquals(Arrays.asList(
                new Lib("raaz", true), 
                new Lib(new File(p3X86_64Root, "libraaz.a").getAbsolutePath(), true), 
                new Lib("yada", true)), 
                config.getLibs());
        assertEquals(Arrays.asList(
                new Resource(new File(p3X86_64Root, "resources")), 
                new Resource(new File(p1, "resources"))), 
                config.getResources());
        assertEquals(Arrays.asList("WeakRaaz", "WeakYada"), config.getWeakFrameworks());
    }

    @Test
    public void testCreateBuilderFromConfig() throws Exception {
        File tmpDir = createTempDir();
        File cacheDir = new File(tmpDir, "cache");
        
        Config.Builder builder = new Config.Builder();
        builder.tmpDir(tmpDir);
        builder.cacheDir(cacheDir);
        builder.os(OS.macosx);
        builder.arch(Arch.x86);
        builder.targetType(ConsoleTarget.TYPE);
        builder.mainClass("Main");
        builder.addBootClasspathEntry(new File(tmpDir, "bcp1"));
        builder.addBootClasspathEntry(new File(tmpDir, "bcp2"));
        builder.addBootClasspathEntry(new File(tmpDir, "bcp3"));
        builder.addClasspathEntry(new File(tmpDir, "cp1"));
        builder.addClasspathEntry(new File(tmpDir, "cp2"));
        builder.addClasspathEntry(new File(tmpDir, "cp3"));
        builder.addExportedSymbol("YADA*");
        builder.addFrameworkPath(new File(tmpDir, "yada"));
        builder.addFramework("Yada");
        builder.addForceLinkClass("org.yada.**");
        builder.addLib(new Lib("yada", true));
        builder.addResource(new Resource(new File(tmpDir, "resources")));
        builder.addWeakFramework("WeakYada");
        builder.addPluginArgument("foo:bar=yada");
        builder.home(fakeHome);

        Config config = builder.build();
        
        Builder builder2 = config.builder();
        builder2.arch(Arch.arm64);
        Config config2 = builder2.build();
        
        assertNotSame(config, config2);
        assertEquals(config.getTmpDir(), config2.getTmpDir());
        assertEquals(config.getCacheDir().getParentFile().getParentFile(), 
                config2.getCacheDir().getParentFile().getParentFile());
        assertEquals(config.getOs(), config2.getOs());
        assertEquals(config.getMainClass(), config2.getMainClass());
        assertEquals(config.getBootclasspath(), config2.getBootclasspath());
        assertNotSame(config.getBootclasspath(), config2.getBootclasspath());
        assertEquals(config.getClasspath(), config2.getClasspath());
        assertNotSame(config.getClasspath(), config2.getClasspath());
        assertEquals(config.getExportedSymbols(), config2.getExportedSymbols());
        assertNotSame(config.getExportedSymbols(), config2.getExportedSymbols());
        assertEquals(config.getFrameworkPaths(), config2.getFrameworkPaths());
        assertNotSame(config.getFrameworkPaths(), config2.getFrameworkPaths());
        assertEquals(config.getFrameworks(), config2.getFrameworks());
        assertNotSame(config.getFrameworks(), config2.getFrameworks());
        assertEquals(config.getForceLinkClasses(), config2.getForceLinkClasses());
        assertNotSame(config.getForceLinkClasses(), config2.getForceLinkClasses());
        assertEquals(config.getLibs(), config2.getLibs());
        assertNotSame(config.getLibs(), config2.getLibs());
        assertEquals(config.getResources(), config2.getResources());
        assertNotSame(config.getResources(), config2.getResources());
        assertEquals(config.getPluginArguments(), config2.getPluginArguments());
        assertNotSame(config.getPluginArguments(), config2.getPluginArguments());
        
        assertEquals(Arch.arm64, config2.getArch());
        
        assertFalse(config.getPlugins().equals(config2.getPlugins()));
        assertNotSame(config.getTarget(), config2.getTarget());
        assertNotSame(config.getClazzes(), config2.getClazzes());
    }

    @Test
    public void testGetFileName() throws Exception {
        assertEquals("201a6b3053cc1422d2c3670b62616221d2290929.class.o", Config.getFileName("Foo", "class.o", 0));
        assertEquals("201a6b3053cc1422d2c3670b62616221d2290929.class.o", Config.getFileName("Foo", "class.o", 1));
        assertEquals("201a6b3053cc1422d2c3670b62616221d2290929.class.o", Config.getFileName("Foo", "class.o", 10));
        assertEquals("Foo.class.o", Config.getFileName("Foo", "class.o", 11));

        assertEquals("com/example/201a6b3053cc1422d2c3670b62616221d2290929.class.o",
                Config.getFileName("com/example/Foo", "class.o", 0));
        assertEquals("com/example/201a6b3053cc1422d2c3670b62616221d2290929.class.o",
                Config.getFileName("com/example/Foo", "class.o", 1));
        assertEquals("com/example/201a6b3053cc1422d2c3670b62616221d2290929.class.o",
                Config.getFileName("com/example/Foo", "class.o", 10));
        assertEquals("com/example/Foo.class.o", Config.getFileName("com/example/Foo", "class.o", 11));

        assertEquals("com/example/AB9ca44297c0e0d22df654119dce73ee52d3d51c71.class.o",
                Config.getFileName("com/example/ABCDEFGIHJABCDEFGIHJABCDEFGIHJABCDEFGIHJABCDEFGIHJ", "class.o", 50));
    }
}
