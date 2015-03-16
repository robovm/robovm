/*
 * Copyright (C) 2014 RoboVM AB
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

import static org.robovm.compiler.Annotations.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.robovm.compiler.Annotations.Visibility;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.options.Options;
import soot.tagkit.AnnotationTag;

/**
 * Tests {@link Annotations}
 */
public class AnnotationsTest {

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
    
    private SootClass toSootClass(Class<?> cls) {
        return SootResolver.v().resolveClass(cls.getName(), SootClass.SIGNATURES);
    }
    
    private String join(List<AnnotationTag> annotations) {
        StringBuilder sb = new StringBuilder();
        List<String> l = new ArrayList<>();
        for (AnnotationTag tag : annotations) {
            String type = tag.getType();
            type = type.substring(type.lastIndexOf('$') + 1, type.length() - 1);
            l.add(type);
        }
        Collections.sort(l);
        for (String type : l) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append("@" + type);
        }
        return sb.toString();
    }
    
    private String toString(SootMethod method) {
        StringBuilder sb = new StringBuilder();
        String s = join(getAnnotations(method, Visibility.Any));
        sb.append(s);
        sb.append(s.length() > 0 ? " " : "");
        sb.append(method.getReturnType());
        sb.append(' ');
        sb.append(method.getName());
        sb.append('(');
        
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            s = join(Annotations.getParameterAnnotations(method, i, Visibility.Any));
            sb.append(s);
            sb.append(s.length() > 0 ? " " : "");
            sb.append(method.getParameterType(i));
        }
        sb.append(')');
        return sb.toString();
    }
    
    @interface A {}
    @interface B {}
    @interface C {}
    @interface D {}
    @Retention(RetentionPolicy.RUNTIME) @interface E {}

    public static native void src1();
    public static native void dest1();
    public static native void src2(int a, int b);
    public static native void dest2(int a, int b);
    public static native void src3(@A int a, @B int b);
    public static native void dest3(int a, int b);
    public static native void src4(@A int a, @B int b);
    public static native void dest4(int a, int b);
    public static native void src5(@A int a, @B int b);
    public static native void dest5(int a, int b);
    public static native void src6(@A @B @C int a, @B @C @D int b);
    public static native void dest6(int a, int b);
    public static native void src7(@A @B @C int a, @B @C @D int b, @C @D @E int c);
    public static native void dest7(int foo, int a, int b, int c);
    public static native void src8(@A @B @C int a, @B @C @D int b, @C @D @E int c);
    public static native void dest8(int a, int b, int foo, int c);
    public static native void src9(@A int a, @B @E int b);
    public static native void dest9(int a, int b);
    
    @Test
    public void testCopyParameterAnnotationsNoParams() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src1");
        SootMethod dest = sc.getMethodByName("dest1");
        copyParameterAnnotations(src, dest, 0, 0, 0, Visibility.Any);
        assertEquals("void dest1()", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsNoAnnotations() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src2");
        SootMethod dest = sc.getMethodByName("dest2");
        copyParameterAnnotations(src, dest, 0, 2, 0, Visibility.Any);
        assertEquals("void dest2(int, int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsSingleAllNoShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src3");
        SootMethod dest = sc.getMethodByName("dest3");
        copyParameterAnnotations(src, dest, 0, 2, 0, Visibility.Any);
        assertEquals("void dest3(@A int, @B int)", toString(dest));
    }
    
    @Test
    public void testCopyParameterAnnotationsSingleFirstNoShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src4");
        SootMethod dest = sc.getMethodByName("dest4");
        copyParameterAnnotations(src, dest, 0, 1, 0, Visibility.Any);
        assertEquals("void dest4(@A int, int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsSingleLastNoShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src5");
        SootMethod dest = sc.getMethodByName("dest5");
        copyParameterAnnotations(src, dest, 1, 2, 0, Visibility.Any);
        assertEquals("void dest5(int, @B int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsMultipleAllNoShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src6");
        SootMethod dest = sc.getMethodByName("dest6");
        copyParameterAnnotations(src, dest, 0, 2, 0, Visibility.Any);
        assertEquals("void dest6(@A @B @C int, @B @C @D int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsMultipleSubsetWithShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src7");
        SootMethod dest = sc.getMethodByName("dest7");
        copyParameterAnnotations(src, dest, 0, 3, 1, Visibility.Any);
        assertEquals("void dest7(int, @A @B @C int, @B @C @D int, @C @D @E int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsTwiceMultipleSubsetWithShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src8");
        SootMethod dest = sc.getMethodByName("dest8");
        copyParameterAnnotations(src, dest, 0, 2, 0, Visibility.Any);
        copyParameterAnnotations(src, dest, 2, 3, 1, Visibility.Any);
        assertEquals("void dest8(@A @B @C int, @B @C @D int, int, @C @D @E int)", toString(dest));
    }

    @Test
    public void testCopyParameterAnnotationsOnlyInvisibleNoShift() {
        SootClass sc = toSootClass(getClass());
        SootMethod src = sc.getMethodByName("src9");
        SootMethod dest = sc.getMethodByName("dest9");
        copyParameterAnnotations(src, dest, 0, 2, 0, Visibility.RuntimeVisible);
        assertEquals("void dest9(int, @E int)", toString(dest));
    }
}
