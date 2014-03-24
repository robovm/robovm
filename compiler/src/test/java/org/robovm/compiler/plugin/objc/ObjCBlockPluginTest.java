/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.plugin.objc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.util.generic.SootMethodType;

import soot.BooleanType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.options.Options;

/**
 * Tests {@link ObjCBlockPlugin}.
 */
public class ObjCBlockPluginTest {

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
    
    public static interface F<R, A extends Number, B extends Number> {
        R run(A a, B b, boolean c);
    }

    public static interface G extends F<BigDecimal, Double, Double> {
    }

    public static interface H extends F<String, Integer, Integer> {
        void foo();
    }

    public static interface I {
    }

    public static interface J<T> extends F<Comparable<T>, Integer, Integer> {
    }

    public static class Runners<T> {
        public native void runner1(F<String, Integer, Integer> f);
        public native void runner2(G g);
        public native void runner3(F<?, ?, ? extends Double> f);
        public native void runner4(F<Comparable<String>, ?, ?> f);
        public native void runner5(J<String> f);
        public native void runner6(F<Comparable<String>[][], ?, ?> f);
        public native void runner7(F<T, Integer, Integer> f);
        public native <U extends Number> void runner8(F<Object, Integer, U> f);
        public native void runner9(Runnable r);
        public native void runner10(@SuppressWarnings("rawtypes") F f);
        public native void runner11(H h);
        public native void runner12(I i);
    }
    
    private SootClass toSootClass(Class<?> cls) {
        return SootResolver.v().resolveClass(cls.getName(), SootClass.SIGNATURES);
    }

    private soot.Type toSootType(String name) {
        if (name.endsWith("[]")) {
            return toSootType(name.substring(0, name.length() - 2)).makeArrayType();
        }
        return SootResolver.v().makeClassRef(name).getType();
    }

    @Test
    public void testGetTargetBlockMethodDirect1() throws Exception {
        assertEquals(toSootClass(F.class).getMethodByName("run"), 
                ObjCBlockPlugin.getBlockTargetMethod(
                        toSootClass(Runners.class).getMethodByName("runner1"), 0));
    }

    @Test
    public void testGetTargetBlockMethodDirect2() throws Exception {
        assertEquals(toSootClass(Runnable.class).getMethodByName("run"), 
                ObjCBlockPlugin.getBlockTargetMethod(
                        toSootClass(Runners.class).getMethodByName("runner9"), 0));
    }

    @Test
    public void testGetTargetBlockMethodInSuperInterface() throws Exception {
        assertEquals(toSootClass(F.class).getMethodByName("run"), 
                ObjCBlockPlugin.getBlockTargetMethod(
                        toSootClass(Runners.class).getMethodByName("runner2"), 0));
    }

    @Test(expected = CompilerException.class)
    public void testGetTargetBlockMethodTooManyMethods() throws Exception {
        ObjCBlockPlugin.getBlockTargetMethod(toSootClass(Runners.class).getMethodByName("runner11"), 0);
    }

    @Test(expected = CompilerException.class)
    public void testGetTargetBlockMethodNoMethods() throws Exception {
        ObjCBlockPlugin.getBlockTargetMethod(toSootClass(Runners.class).getMethodByName("runner12"), 0);
    }

    private void testResolveTargetMethodSignature(String runnerMethodName, 
            soot.Type expectedReturnType, soot.Type ... expectedParamTypes) {
        
        SootMethod m = toSootClass(Runners.class).getMethodByName(runnerMethodName);
        SootMethodType mType = new SootMethodType(m);
        SootMethod target = ObjCBlockPlugin.getBlockTargetMethod(m, 0);
        soot.Type[] types = ObjCBlockPlugin.resolveTargetMethodSignature(m, target, mType.getGenericParameterTypes()[0]);
        assertEquals(target.getParameterCount() + 1, types.length);
        assertEquals(expectedReturnType, types[0]);
        for (int i = 0; i < types.length - 1; i++) {
            assertEquals(expectedParamTypes[i], types[i + 1]);
        }
    }
    
    @Test
    public void testResolveTargetMethodSignatureDirectGeneric() throws Exception {
        testResolveTargetMethodSignature("runner1", toSootType("java.lang.String"), 
                toSootType("java.lang.Integer"), toSootType("java.lang.Integer"), 
                BooleanType.v());
    }
    
    @Test
    public void testResolveTargetMethodSignatureIndirectGeneric() throws Exception {
        testResolveTargetMethodSignature("runner2", toSootType("java.math.BigDecimal"), 
                toSootType("java.lang.Double"), toSootType("java.lang.Double"),
                BooleanType.v());
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithWildcards() throws Exception {
        testResolveTargetMethodSignature("runner3", toSootType("java.lang.Object"), 
                toSootType("java.lang.Number"), toSootType("java.lang.Double"),
                BooleanType.v());
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithDirectParameterizedType() throws Exception {
        testResolveTargetMethodSignature("runner4", toSootType("java.lang.Comparable"), 
                toSootType("java.lang.Number"), toSootType("java.lang.Number"),
                BooleanType.v());
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithIndirectParameterizedType() throws Exception {
        testResolveTargetMethodSignature("runner5", toSootType("java.lang.Comparable"), 
                toSootType("java.lang.Integer"), toSootType("java.lang.Integer"),
                BooleanType.v());
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithGenericArrayType() throws Exception {
        testResolveTargetMethodSignature("runner6", toSootType("java.lang.Comparable[][]"), 
                toSootType("java.lang.Number"), toSootType("java.lang.Number"),
                BooleanType.v());
    }

    @Test(expected = CompilerException.class)
    public void testResolveTargetMethodSignatureGenericWithUnresolvedIndirectTypeVariable() throws Exception {
        SootMethod target = toSootClass(F.class).getMethodByName("run");
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner7");
        SootMethodType mType = new SootMethodType(m);
        ObjCBlockPlugin.resolveTargetMethodSignature(m, target, mType.getGenericParameterTypes()[0]);
    }

    @Test(expected = CompilerException.class)
    public void testResolveTargetMethodSignatureGenericWithUnresolvedDirectTypeVariable() throws Exception {
        SootMethod target = toSootClass(F.class).getMethodByName("run");
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner8");
        SootMethodType mType = new SootMethodType(m);
        ObjCBlockPlugin.resolveTargetMethodSignature(m, target, mType.getGenericParameterTypes()[0]);
    }

    @Test
    public void testResolveTargetMethodSignatureNonGeneric() throws Exception {
        testResolveTargetMethodSignature("runner9", VoidType.v());
    }

    @Test
    public void testResolveTargetMethodSignatureRawType() throws Exception {
        testResolveTargetMethodSignature("runner10", toSootType("java.lang.Object"), 
                toSootType("java.lang.Number"), toSootType("java.lang.Number"),
                BooleanType.v());
    }
}
