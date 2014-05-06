/*
 * Copyright (C) 2013 Trillian Mobile AB
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

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robovm.compiler.config.Config.Home;
import org.robovm.compiler.config.Config.Lib;
import org.robovm.compiler.config.Config.TargetType;
import org.zeroturnaround.zip.ZipUtil;

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
        
        Constructor<Home> c = Home.class.getDeclaredConstructor(File.class, boolean.class);
        c.setAccessible(true);
        fakeHome = c.newInstance(new File(""), false);
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
        assertEquals(Arch.x86, config.getArch());
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
        assertEquals(Arch.x86, config.getArch());
    }
    
    @Test
    public void testWriteConsole() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.addClasspathEntry(new File("foo1.jar"));
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
        builder.arch(Arch.x86);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.console.xml")), out.toString());
    }

    @Test
    public void testReadIOS() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.read(new InputStreamReader(getClass().getResourceAsStream("ConfigTest.ios.xml"), "utf-8"), wd);
        Config config = builder.config;
        assertEquals("6.1", config.getIosSdkVersion());
        assertEquals(new File(wd, "Info.plist"), config.getIosInfoPList());
        assertEquals(new File(wd, "entitlements.plist"), config.getIosEntitlementsPList());
        assertEquals(new File(tmp, "resourcerules.plist"), config.getIosResourceRulesPList());
    }
    
    @Test
    public void testWriteIOS() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.iosSdkVersion("6.1");
        builder.iosInfoPList(new File("Info.plist"));
        builder.iosEntitlementsPList(new File("entitlements.plist"));
        builder.iosResourceRulesPList(new File(tmp, "resourcerules.plist"));
        builder.targetType(TargetType.ios);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.ios.xml")), out.toString());
    }
    
    private File createMergeConfig(File tmpDir, String dir, String id, OS os, Arch arch, boolean jar) throws Exception {
        File p = new File(tmpDir, dir);
        File root1 = new File(p, "META-INF/robovm/ios/thumbv7");
        File root2 = new File(p, "META-INF/robovm/ios/x86");
        File root3 = new File(p, "META-INF/robovm/macosx/x86");
        File root4 = new File(p, "META-INF/robovm/linux/x86");
        root1.mkdirs();
        root2.mkdirs();
        root3.mkdirs();
        root4.mkdirs();

        new Config.Builder().write(new File(root1, "robovm.xml"));
        new Config.Builder().write(new File(root2, "robovm.xml"));
        new Config.Builder().write(new File(root3, "robovm.xml"));
        new Config.Builder().write(new File(root4, "robovm.xml"));

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
        File p3 = createMergeConfig(tmpDir, "p3", "Baaz", OS.macosx, Arch.x86, true);
        
        Config.Builder builder = new Config.Builder();
        builder.cacheDir(cacheDir);
        builder.os(OS.macosx);
        builder.arch(Arch.x86);
        builder.targetType(TargetType.console);
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

        File p1Root = new File(p1, "META-INF/robovm/macosx/x86");
        File p3Cache = config.getCacheDir(config.getClazzes().getClasspathPaths().get(2));
        File p3Root = new File(p3Cache.getParentFile(), p3Cache.getName() + ".extracted/META-INF/robovm/macosx/x86");

        assertEquals(Arrays.asList("FOO*", "BAAZ*", "YADA*"), config.getExportedSymbols());
        assertEquals(Arrays.asList("com.foo.**", "com.baaz.**", "org.yada.**"), config.getForceLinkClasses());
        assertEquals(Arrays.asList(
                new File(p1Root, "foo/bar"), 
                new File(p3Root, "baaz/bar"), 
                new File(p1, "yada")), 
                config.getFrameworkPaths());
        assertEquals(Arrays.asList("Foo", "Baaz", "Yada"), config.getFrameworks());
        assertEquals(Arrays.asList(
                new Lib("foo", true), 
                new Lib(new File(p1Root, "libfoo.a").getAbsolutePath(), true), 
                new Lib("baaz", true), 
                new Lib(new File(p3Root, "libbaaz.a").getAbsolutePath(), true), 
                new Lib("yada", true)), 
                config.getLibs());
        assertEquals(Arrays.asList(
                new Resource(new File(p1Root, "resources")), 
                new Resource(new File(p3Root, "resources")), 
                new Resource(new File(p1, "resources"))), 
                config.getResources());
        assertEquals(Arrays.asList("WeakFoo", "WeakBaaz", "WeakYada"), config.getWeakFrameworks());
    }

}
