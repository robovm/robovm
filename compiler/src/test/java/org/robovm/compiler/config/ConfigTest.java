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
package org.robovm.compiler.config;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robovm.compiler.target.ConsoleTarget;
import org.robovm.compiler.target.ios.IOSTarget;

/**
 * Tests {@link Config}.
 */
public class ConfigTest {
    String savedUserDir;
    File tmp;
    File wd;
    
    @Before
    public void setUp() {
        savedUserDir = System.getProperty("user.dir");
        tmp = new File("/tmp");
        wd = new File(tmp, "wd");
        System.setProperty("user.dir", wd.getAbsolutePath());
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
        assertEquals(Arrays.asList("dl", "/tmp/wd/libs/libmy.a", "/tmp/wd/libs/foo.o", "/usr/lib/libbar.a"), config.getLibs());
        assertEquals(Arrays.asList(new File("/tmp/wd/resources"), new File("/usr/share/resources")), config.getResources());
        assertEquals(Arrays.asList("javax.**.*"), config.getRoots());
        assertTrue(config.getTarget() instanceof ConsoleTarget);
        ConsoleTarget target = (ConsoleTarget) config.getTarget();
        assertEquals(OS.macosx, target.getOS());
        assertEquals(Arch.x86, target.getArch());
    }
    
    @Test
    public void testWriteConsole() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.addClasspathEntry(new File("foo1.jar"));
        builder.addClasspathEntry(new File(tmp, "foo2.jar"));
        builder.addFramework("Foundation");
        builder.addFramework("AppKit");
        builder.addLib("dl");
        builder.addLib("libs/libmy.a");
        builder.addLib("libs/foo.o");
        builder.addLib("/usr/lib/libbar.a");
        builder.addResource(new File("resources"));
        builder.addResource(new File("/usr/share/resources"));
        builder.addRoot("javax.**.*");
        ConsoleTarget target = new ConsoleTarget();
        target.setOS(OS.macosx);
        target.setArch(Arch.x86);
        builder.target(target);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.console.xml")), out.toString());
    }

    @Test
    public void testReadIOS() throws Exception {
        Config.Builder builder = new Config.Builder();
        builder.read(new InputStreamReader(getClass().getResourceAsStream("ConfigTest.ios.xml"), "utf-8"), wd);
        Config config = builder.config;
        assertTrue(config.getTarget() instanceof IOSTarget);
        IOSTarget target = (IOSTarget) config.getTarget();
        assertEquals("6.1", target.getSdkVersion());
        assertEquals("FooBar", target.getSignIdentity());
        assertEquals(new File(wd, "Info.plist"), target.getInfoPList());
        assertEquals(new File(wd, "entitlements.plist"), target.getEntitlementsPList());
        assertEquals(new File(tmp, "resourcerules.plist"), target.getResourceRulesPList());
    }
    
    @Test
    public void testWriteIOS() throws Exception {
        Config.Builder builder = new Config.Builder();
        IOSTarget target = new IOSTarget();
        target.setSdkVersion("6.1");
        target.setInfoPList(new File("Info.plist"));
        target.setEntitlementsPList(new File("entitlements.plist"));
        target.setResourceRulesPList(new File(tmp, "resourcerules.plist"));
        target.setSignIdentity("FooBar");
        builder.target(target);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.ios.xml")), out.toString());
    }
}
