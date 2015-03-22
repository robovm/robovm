/*
 * Copyright (C) 2015 Trillian Mobile AB
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
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.robovm.compiler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Config;

/**
 *
 * @author Jaroslav Tulach
 */
public class AppCompilerTest {
    @Test
    public void testMetainfServiceImplIsAdded() throws Exception {
        final Path impl1 = new MockPath("META-INF/services/java.lang.Number", "java.lang.Integer");
        Clazzes clazzes = createClazzes(impl1);
        Clazz interfaceClazz = clazzes.load("java/lang/Number");

        Set<Clazz> compiled = new HashSet<>();
        Set<Clazz> queue = new LinkedHashSet<>();
        AppCompiler.addMetaInfImplementations(clazzes, interfaceClazz, compiled, queue);
     
        assertEquals("One item added to queue: " + queue, 1, queue.size());
        assertTrue("Integer in queue" + queue, queue.contains(clazzes.load("java/lang/Integer")));
    }

    @Test
    public void testMultipleMetainfServiceImplsAdded() throws Exception {
        final Path impl1 = new MockPath("META-INF/services/java.lang.Number", "java.lang.Integer");
        final Path impl2 = new MockPath("META-INF/services/java.lang.Number", "java.lang.Long");
        Clazzes clazzes = createClazzes(impl1, impl2);
        Clazz interfaceClazz = clazzes.load("java/lang/Number");

        Set<Clazz> compiled = new HashSet<>();
        Set<Clazz> queue = new LinkedHashSet<>();
        AppCompiler.addMetaInfImplementations(clazzes, interfaceClazz, compiled, queue);
     
        assertEquals("Two items added to queue: " + queue, 2, queue.size());
        
        assertTrue("Integer in queue" + queue, queue.contains(clazzes.load("java/lang/Integer")));
        assertTrue("Long in queue" + queue, queue.contains(clazzes.load("java/lang/Long")));
    }

    @Test
    public void testMultilineFile() throws Exception {
        final Path impl1 = new MockPath("META-INF/services/java.lang.Number", 
            "# first register Integer\n" +
            "java.lang.Integer\n" +
            "# then add Long\n" +
            "java.lang.Long\n"
                    + "\n\n\n\n"
        );
        Clazzes clazzes = createClazzes(impl1);
        Clazz interfaceClazz = clazzes.load("java/lang/Number");

        Set<Clazz> compiled = new HashSet<>();
        Set<Clazz> queue = new LinkedHashSet<>();
        AppCompiler.addMetaInfImplementations(clazzes, interfaceClazz, compiled, queue);
     
        assertEquals("Two items added to queue: " + queue, 2, queue.size());
        
        assertTrue("Integer in queue" + queue, queue.contains(clazzes.load("java/lang/Integer")));
        assertTrue("Long in queue" + queue, queue.contains(clazzes.load("java/lang/Long")));
    }

    @Test
    public void testMissingImplIsIgnore() throws Exception {
        final Path impl1 = new MockPath("META-INF/services/java.lang.Number", "java.lang.Integer");
        final Path impl2 = new MockPath("META-INF/services/java.lang.Number", "nobody.knows.such.Class");
        Clazzes clazzes = createClazzes(impl1, impl2);
        Clazz interfaceClazz = clazzes.load("java/lang/Number");

        Set<Clazz> compiled = new HashSet<>();
        Set<Clazz> queue = new LinkedHashSet<>();
        AppCompiler.addMetaInfImplementations(clazzes, interfaceClazz, compiled, queue);
     
        assertEquals("Just one item added to queue: " + queue, 1, queue.size());
        
        assertTrue("Integer in queue" + queue, queue.contains(clazzes.load("java/lang/Integer")));
    }
    
    private static Clazzes createClazzes(final Path... paths) throws Exception {
        File home = new File(System.getProperty("java.home"));
        Config cfg = new Config() {
        };
        Clazzes clazzes = new Clazzes(
                cfg,
                Collections.nCopies(1, new File(new File(home, "lib"), "rt.jar")),
                Collections.<File>emptyList()
        ) {
            @Override
            public List<Path> getPaths() {
                return Arrays.asList(paths);
            }
        };
        return clazzes;
    }
    
    private static final class MockPath implements Path {
        private final String file;
        private final String content;

        public MockPath(String file, String content) {
            this.file = file;
            this.content = content;
        }
        
        @Override
        public int getIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public File getFile() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Clazz> listClasses() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Clazz loadGeneratedClass(String internalName) {
            throw new UnsupportedOperationException();
        }

        @Override
        public File getGeneratedClassFile(String internalName) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasChangedSince(long timestamp) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isInBootClasspath() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean contains(String file) {
            return this.file.equals(file);
        }

        @Override
        public InputStream open(String file) throws IOException {
            if (this.file.equals(file)) {
                return new ByteArrayInputStream(this.content.getBytes("UTF-8"));
            }
            throw new IOException();
        }
        
    }
}
