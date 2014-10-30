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
import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.plugin.objc.ObjCBlockPlugin.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.Types;
import org.robovm.compiler.util.generic.SootClassType;
import org.robovm.compiler.util.generic.SootMethodType;
import org.robovm.compiler.util.generic.SootTypeType;
import org.robovm.compiler.util.generic.Type;

import soot.BooleanType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.options.Options;
import soot.tagkit.SignatureTag;

/**
 * Tests {@link ObjCBlockPlugin}.
 */
public class ObjCBlockPluginTest {

    private Type BOOLEAN, VOID;
    
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
    public void setup() {
        BOOLEAN = new SootTypeType(BooleanType.v());
        VOID = new SootTypeType(VoidType.v());
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

    private Type signatureToType(String desc) {
        String rawDesc = desc.replaceAll("<.*>", "");
        String internalName = rawDesc.replaceAll("^\\[*", "");
        int dims = rawDesc.length() - internalName.length();
        internalName = Types.getInternalNameFromDescriptor(internalName);
        soot.Type sootType = SootResolver.v().makeClassRef(internalName.replace('/', '.')).getType();
        for (int i = 0; i < dims; i++) {
            sootType = sootType.makeArrayType();
        }
        SootMethod  m = new SootMethod("foo", Arrays.asList(sootType), VoidType.v());
        m.addTag(new SignatureTag("(" + desc + ")V"));
        SootMethodType mType = new SootMethodType(m);
        return mType.getGenericParameterTypes()[0];
    }
    
    private Type classNameToType(String name) {
        return new SootClassType(SootResolver.v().makeClassRef(name));
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
            Type expectedReturnType, Type ... expectedParamTypes) {
        
        SootMethod m = toSootClass(Runners.class).getMethodByName(runnerMethodName);
        SootMethodType mType = new SootMethodType(m);
        SootMethod target = ObjCBlockPlugin.getBlockTargetMethod(m, 0);
        Type[] types = ObjCBlockPlugin.resolveTargetMethodSignature(m, target, mType.getGenericParameterTypes()[0]);
        assertEquals(target.getParameterCount() + 1, types.length);
        assertEquals(expectedReturnType, types[0]);
        for (int i = 0; i < types.length - 1; i++) {
            assertEquals(expectedParamTypes[i], types[i + 1]);
        }
    }
    
    @Test
    public void testResolveTargetMethodSignatureDirectGeneric() throws Exception {
        testResolveTargetMethodSignature("runner1", classNameToType("java.lang.String"), 
                classNameToType("java.lang.Integer"), classNameToType("java.lang.Integer"), 
                BOOLEAN);
    }
    
    @Test
    public void testResolveTargetMethodSignatureIndirectGeneric() throws Exception {
        testResolveTargetMethodSignature("runner2", classNameToType("java.math.BigDecimal"), 
                classNameToType("java.lang.Double"), classNameToType("java.lang.Double"),
                BOOLEAN);
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithWildcards() throws Exception {
        testResolveTargetMethodSignature("runner3", classNameToType("java.lang.Object"), 
                classNameToType("java.lang.Number"), classNameToType("java.lang.Double"),
                BOOLEAN);
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithDirectParameterizedType() throws Exception {
        testResolveTargetMethodSignature("runner4", signatureToType("Ljava/lang/Comparable<Ljava/lang/String;>;"), 
                classNameToType("java.lang.Number"), classNameToType("java.lang.Number"),
                BOOLEAN);
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithIndirectParameterizedType() throws Exception {
        testResolveTargetMethodSignature("runner5", signatureToType("Ljava/lang/Comparable<Ljava/lang/String;>;"), 
                classNameToType("java.lang.Integer"), classNameToType("java.lang.Integer"),
                BOOLEAN);
    }

    @Test
    public void testResolveTargetMethodSignatureGenericWithGenericArrayType() throws Exception {
        testResolveTargetMethodSignature("runner6", signatureToType("[[Ljava/lang/Comparable<Ljava/lang/String;>;"), 
                classNameToType("java.lang.Number"), classNameToType("java.lang.Number"),
                BOOLEAN);
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
        testResolveTargetMethodSignature("runner9", VOID);
    }

    @Test
    public void testResolveTargetMethodSignatureRawType() throws Exception {
        testResolveTargetMethodSignature("runner10", classNameToType("java.lang.Object"), 
                classNameToType("java.lang.Number"), classNameToType("java.lang.Number"),
                BOOLEAN);
    }
    
    @Test
    public void testParseTargetMethodAnnotations() throws Exception {
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner1");
        
        assertArrayEquals(new String[][] {{}}, ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, ""));
        assertArrayEquals(new String[][] {{}}, ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "  "));
        assertArrayEquals(new String[][] {{}}, ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "()"));
        assertArrayEquals(new String[][] {{BY_VAL, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "@Pointer@ByVal"));
        assertArrayEquals(new String[][] {{BY_VAL, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "@Pointer  @ByVal ()"));

        assertArrayEquals(new String[][] {{BY_VAL, POINTER}, {BY_REF}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 1, "@Pointer  @ByVal (@ByRef)"));
        assertArrayEquals(new String[][] {{BY_VAL, POINTER}, {BY_REF, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 1, "@Pointer  @ByVal (  @ByRef  @Pointer  )"));
        assertArrayEquals(new String[][] {{BY_VAL, POINTER}, {BY_REF, POINTER}, {MACHINE_SIZED_S_INT}, {}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 3, "@Pointer  @ByVal (  @ByRef  @Pointer , @MachineSizedSInt , )"));
        assertArrayEquals(new String[][] {{BY_VAL, POINTER}, {}, {BY_REF, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 2, "@Pointer @ByVal(,@ByRef @Pointer)"));
        assertArrayEquals(new String[][] {{}, {BY_REF, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 1, "(@ByRef @Pointer)  "));

        assertArrayEquals(new String[][] {{}, {BLOCK, BY_REF, BY_VAL, MACHINE_SIZED_FLOAT, 
                MACHINE_SIZED_S_INT, MACHINE_SIZED_U_INT, POINTER}}, 
                ObjCBlockPlugin.parseTargetMethodAnnotations(m, 1, 
                        "(@ByRef @ByVal @Pointer @MachineSizedFloat @MachineSizedSInt " 
                                + "@MachineSizedUInt @Block)  "));
    }
    
    @Test(expected = CompilerException.class)
    public void testParseTargetMethodAnnotationsInvalid1() throws Exception {
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner1");
        ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "(");
    }

    @Test(expected = CompilerException.class)
    public void testParseTargetMethodAnnotationsInvalid2() throws Exception {
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner1");
        ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "@Yada");
    }

    @Test(expected = CompilerException.class)
    public void testParseTargetMethodAnnotationsInvalid3() throws Exception {
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner1");
        ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "garbage");
    }

    @Test(expected = CompilerException.class)
    public void testParseTargetMethodAnnotationsInvalid4() throws Exception {
        SootMethod m = toSootClass(Runners.class).getMethodByName("runner1");
        ObjCBlockPlugin.parseTargetMethodAnnotations(m, 0, "@ByVal(");
    }
}
