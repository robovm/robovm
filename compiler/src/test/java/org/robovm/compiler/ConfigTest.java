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
package org.robovm.compiler;

import static org.junit.Assert.*;

import java.io.File;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robovm.compiler.AbstractIOSTarget.SDK;

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
    public void testWrite() throws Exception {
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
        builder.target(new ConsoleTarget());
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        System.out.println(out.toString());
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.write.xml")), out.toString());
    }

    @Test
    public void testWriteIOSTarget() throws Exception {
        Config.Builder builder = new Config.Builder();
        IOSDeviceTarget target = new IOSDeviceTarget();
        target.setSDK(new AbstractIOSTarget.SDK("", "", "", "6.1", new File(tmp, "sdk"), null));
        builder.target(target);
        
        StringWriter out = new StringWriter();
        builder.write(out, wd);
        System.out.println(out.toString());
        assertEquals(IOUtils.toString(getClass().getResourceAsStream("ConfigTest.write.xml")), out.toString());
    }
}
