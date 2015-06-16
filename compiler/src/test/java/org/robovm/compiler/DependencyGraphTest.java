/*
 * Copyright (C) 2015 RoboVM AB
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
import java.io.IOException;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.FakeHome;
import org.robovm.compiler.config.Config.TreeShakerMode;
import org.robovm.rt.annotation.StronglyLinked;
import org.robovm.rt.annotation.WeaklyLinked;

import soot.Scene;
import soot.options.Options;

/**
 * Tests {@link DependencyGraph}.
 */
public class DependencyGraphTest {

    Config config;
    Clazz Root;
    Clazz A;
    Clazz B;
    Clazz C;

    @BeforeClass
    public static void initializeSoot() throws IOException {
        soot.G.reset();
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_include_all(true);
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(System.getProperty("sun.boot.class.path") +
                ":" + System.getProperty("java.class.path"));
        Scene.v().loadNecessaryClasses();
    }

    @Before
    public void setup() throws Exception {
        Config.Builder builder = new Config.Builder()
                .home(new FakeHome())
                .skipRuntimeLib(true)
                .skipLinking(true);
        for (String path : System.getProperty("sun.boot.class.path").split(File.pathSeparator)) {
            builder.addBootClasspathEntry(new File(path));
        }
        for (String path : System.getProperty("java.class.path").split(File.pathSeparator)) {
            builder.addClasspathEntry(new File(path));
        }
        config = builder.build();

        Root = loadClazz(Root.class);
        A = loadClazz(A.class);
        B = loadClazz(B.class);
        C = loadClazz(C.class);

        Root.getClazzInfo().addClassDependency(A.getInternalName(), false);
        Root.getClazzInfo().addClassDependency(B.getInternalName(), false);
        Root.getClazzInfo().addClassDependency(C.getInternalName(), false);
    }

    private Clazz loadClazz(Class<?> cls) {
        Clazz clazz = config.getClazzes().load(cls.getName().replace('.', '/'));
        if (clazz.getClazzInfo() == null) {
            clazz.resetClazzInfo().initClassInfo();
        }
        return clazz;
    }

    public static class Root {}

    public static class A {
        public A(byte a) {}

        @StronglyLinked
        public A(short a) {}

        @WeaklyLinked
        public A(int a) {}

        public void a() {}

        @StronglyLinked
        public void b() {}

        @WeaklyLinked
        public void c() {}
    }

    @WeaklyLinked
    public static class B {
        public B(byte a) {}

        @StronglyLinked
        public B(short a) {}

        @WeaklyLinked
        public B(int a) {}

        public void a() {}

        @StronglyLinked
        public void b() {}

        @WeaklyLinked
        public void c() {}
    }

    @StronglyLinked
    public static class C {
        public C(byte a) {}

        @StronglyLinked
        public C(short a) {}

        @WeaklyLinked
        public C(int a) {}

        public void a() {}

        @StronglyLinked
        public void b() {}

        @WeaklyLinked
        public void c() {}
    }

    @Test
    public void testWeaklyStronglyLinkedMethodInfos() throws Exception {
        assertFalse(A.getClazzInfo().getMethod("<init>", "(B)V").isStronglyLinked());
        assertFalse(A.getClazzInfo().getMethod("<init>", "(B)V").isWeaklyLinked());
        assertTrue(A.getClazzInfo().getMethod("<init>", "(S)V").isStronglyLinked());
        assertFalse(A.getClazzInfo().getMethod("<init>", "(S)V").isWeaklyLinked());
        assertFalse(A.getClazzInfo().getMethod("<init>", "(I)V").isStronglyLinked());
        assertTrue(A.getClazzInfo().getMethod("<init>", "(I)V").isWeaklyLinked());

        assertFalse(A.getClazzInfo().getMethod("a", "()V").isStronglyLinked());
        assertFalse(A.getClazzInfo().getMethod("a", "()V").isWeaklyLinked());
        assertTrue(A.getClazzInfo().getMethod("b", "()V").isStronglyLinked());
        assertFalse(A.getClazzInfo().getMethod("b", "()V").isWeaklyLinked());
        assertFalse(A.getClazzInfo().getMethod("c", "()V").isStronglyLinked());
        assertTrue(A.getClazzInfo().getMethod("c", "()V").isWeaklyLinked());

        assertFalse(B.getClazzInfo().getMethod("<init>", "(B)V").isStronglyLinked());
        assertTrue(B.getClazzInfo().getMethod("<init>", "(B)V").isWeaklyLinked());
        assertTrue(B.getClazzInfo().getMethod("<init>", "(S)V").isStronglyLinked());
        assertFalse(B.getClazzInfo().getMethod("<init>", "(S)V").isWeaklyLinked());
        assertFalse(B.getClazzInfo().getMethod("<init>", "(I)V").isStronglyLinked());
        assertTrue(B.getClazzInfo().getMethod("<init>", "(I)V").isWeaklyLinked());

        assertFalse(B.getClazzInfo().getMethod("a", "()V").isStronglyLinked());
        assertTrue(B.getClazzInfo().getMethod("a", "()V").isWeaklyLinked());
        assertTrue(B.getClazzInfo().getMethod("b", "()V").isStronglyLinked());
        assertFalse(B.getClazzInfo().getMethod("b", "()V").isWeaklyLinked());
        assertFalse(B.getClazzInfo().getMethod("c", "()V").isStronglyLinked());
        assertTrue(B.getClazzInfo().getMethod("c", "()V").isWeaklyLinked());

        assertTrue(C.getClazzInfo().getMethod("<init>", "(B)V").isStronglyLinked());
        assertFalse(C.getClazzInfo().getMethod("<init>", "(B)V").isWeaklyLinked());
        assertTrue(C.getClazzInfo().getMethod("<init>", "(S)V").isStronglyLinked());
        assertFalse(C.getClazzInfo().getMethod("<init>", "(S)V").isWeaklyLinked());
        assertFalse(C.getClazzInfo().getMethod("<init>", "(I)V").isStronglyLinked());
        assertTrue(C.getClazzInfo().getMethod("<init>", "(I)V").isWeaklyLinked());

        assertTrue(C.getClazzInfo().getMethod("a", "()V").isStronglyLinked());
        assertFalse(C.getClazzInfo().getMethod("a", "()V").isWeaklyLinked());
        assertTrue(C.getClazzInfo().getMethod("b", "()V").isStronglyLinked());
        assertFalse(C.getClazzInfo().getMethod("b", "()V").isWeaklyLinked());
        assertFalse(C.getClazzInfo().getMethod("c", "()V").isStronglyLinked());
        assertTrue(C.getClazzInfo().getMethod("c", "()V").isWeaklyLinked());
    }

    @Test
    public void testFindReachableClassesNoTreeShaking() throws Exception {
        DependencyGraph graph = new DependencyGraph(TreeShakerMode.none);
        graph.add(Root, true);
        graph.add(A, false);
        graph.add(B, false);
        graph.add(C, false);

        assertTrue(graph.findReachableClasses().contains(Root.getInternalName()));
        assertTrue(graph.findReachableClasses().contains(A.getInternalName()));
        assertTrue(graph.findReachableClasses().contains(B.getInternalName()));
        assertTrue(graph.findReachableClasses().contains(C.getInternalName()));
    }

    @Test
    public void testFindReachableMethodsNoTreeShaking() throws Exception {
        DependencyGraph graph = new DependencyGraph(TreeShakerMode.none);
        graph.add(Root, true);
        graph.add(A, false);
        graph.add(B, false);
        graph.add(C, false);

        assertEquals(19, graph.findReachableMethods().size());

        assertTrue(graph.findReachableMethods()
                .contains(new ImmutableTriple<>(Root.getInternalName(), "<init>", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(I)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "b", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "c", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "<init>", "(I)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "b", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "c", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(I)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "b", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "c", "()V")));
    }

    @Test
    public void testFindReachableMethodsConservativeTreeShaking() throws Exception {
        DependencyGraph graph = new DependencyGraph(TreeShakerMode.conservative);
        graph.add(Root, true);
        graph.add(A, false);
        graph.add(B, false);
        graph.add(C, false);

        assertEquals(11, graph.findReachableMethods().size());

        assertTrue(graph.findReachableMethods()
                .contains(new ImmutableTriple<>(Root.getInternalName(), "<init>", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "b", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "b", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "b", "()V")));
    }

    @Test
    public void testFindReachableMethodsAggressiveTreeShaking() throws Exception {
        DependencyGraph graph = new DependencyGraph(TreeShakerMode.aggressive);
        graph.add(Root, true);
        graph.add(A, false);
        graph.add(B, false);
        graph.add(C, false);

        assertEquals(10, graph.findReachableMethods().size());

        assertTrue(graph.findReachableMethods()
                .contains(new ImmutableTriple<>(Root.getInternalName(), "<init>", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(A.getInternalName(), "b", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(B.getInternalName(), "b", "()V")));

        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(B)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "<init>", "(S)V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "a", "()V")));
        assertTrue(graph.findReachableMethods().contains(new ImmutableTriple<>(C.getInternalName(), "b", "()V")));
    }
}
