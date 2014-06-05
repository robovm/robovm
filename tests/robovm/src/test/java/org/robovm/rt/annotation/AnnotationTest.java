/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.rt.annotation;

import static org.junit.Assert.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import org.junit.Test;
import org.robovm.rt.bro.annotation.Bridge;

/**
 * Tests the annotation implementation classes the {@code AnnotationImplPlugin} 
 * compiler plugin generates.
 */
public class AnnotationTest {

    public @interface Anno1 {}

    @Retention(RetentionPolicy.SOURCE)
    public @interface Anno2 {}
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Anno3 {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Anno4 {
        boolean boolean1();
        boolean boolean2() default true;
        byte byte1();
        byte byte2() default 100;
        short short1();
        short short2() default 23000;
        char char1();
        char char2() default 46000;
        int int1();
        int int2() default 2_000_000_000;
        long long1();
        long long2() default 20_000_000_000L;
        float float1();
        float float2() default 1234567.654321f;
        double double1();
        double double2() default 7654321.234567;
        String string1();
        String string2() default "string2default";
        Class<?> class1();
        Class<?> class2() default byte.class;
        Bridge anno1();
        Bridge anno2() default @Bridge(symbol = "foo", dynamic = true);
        boolean[] booleans1();
        boolean[] booleans2() default {true, false, true};
        byte[] bytes1();
        byte[] bytes2() default {100, 101, 102};
        short[] shorts1();
        short[] shorts2() default {23000, 23001, 23002};
        char[] chars1();
        char[] chars2() default {46000, 46001, 46002};
        int[] ints1();
        int[] ints2() default {2_000_000_000, 2_000_000_001, 2_000_000_002};
        long[] longs1();
        long[] longs2() default {20_000_000_000L, 20_000_000_001L, 20_000_000_002L};
        float[] floats1();
        float[] floats2() default {1234567.654321f, 2234567.654321f, 3234567.654321f};
        double[] doubles1();
        double[] doubles2() default {7654321.234567, 8654321.234567, 9654321.234567};
        String[] strings1();
        String[] strings2() default {"a", "b", "c"};
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Anno5 {
        String v1() default "v1";
        String v2() default "v2";
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Anno6 {
        Anno1 anno1() default @Anno1;
        Anno2 anno2() default @Anno2;
    }
    
    @Anno3
    public static class AnnoHost1 {}
    @Anno3
    public static class AnnoHost2 {}
    
    @Anno4(
        boolean1 = false, 
        byte1 = 101, 
        short1 = 23001, 
        char1 = 46001, 
        int1 = 2_000_000_001, 
        long1 = 20_000_000_001L,
        float1 = 2234567.654321f, 
        double1 = 8654321.234567,
        string1 = "string1value",
        class1 = Integer.class,
        anno1 = @Bridge(symbol = "bar", dynamic = true),
        booleans1 = {false, true},
        bytes1 = {102, 101, 100}, 
        shorts1 = {23002, 23001, 23000}, 
        chars1 = {46002, 46001, 46000}, 
        ints1 = {2_000_000_002, 2_000_000_001, 2_000_000_000}, 
        longs1 = {20_000_000_002L, 20_000_000_001L, 20_000_000_000L},
        floats1 = {3234567.654321f, 2234567.654321f, 1234567.654321f}, 
        doubles1 = {9654321.234567, 8654321.234567, 7654321.234567},
        strings1 = {"c", "b", "a"})
    public static class AnnoHost3 {}
    
    @Anno4(
            boolean1 = false, 
            byte1 = 101, 
            short1 = 23001, 
            char1 = 46001, 
            int1 = 2_000_000_001, 
            long1 = 20_000_000_001L,
            float1 = 2234567.654321f, 
            double1 = 8654321.234567,
            string1 = "string1value",
            class1 = Integer.class,
            anno1 = @Bridge(symbol = "bar", dynamic = true),
            booleans1 = {false, true},
            bytes1 = {102, 101, 100}, 
            shorts1 = {23002, 23001, 23000}, 
            chars1 = {46002, 46001, 46000}, 
            ints1 = {2_000_000_002, 2_000_000_001, 2_000_000_000}, 
            longs1 = {20_000_000_002L, 20_000_000_001L, 20_000_000_000L},
            floats1 = {3234567.654321f, 2234567.654321f, 1234567.654321f}, 
            doubles1 = {9654321.234567, 8654321.234567, 7654321.234567},
            strings1 = {"c", "b", "a"})
    public static class AnnoHost4 {}
    
    @Anno4(
            boolean1 = true, // Only diff! 
            byte1 = 101, 
            short1 = 23001, 
            char1 = 46001, 
            int1 = 2_000_000_001, 
            long1 = 20_000_000_001L,
            float1 = 2234567.654321f, 
            double1 = 8654321.234567,
            string1 = "string1value",
            class1 = Integer.class,
            anno1 = @Bridge(symbol = "bar", dynamic = true),
            booleans1 = {false, true},
            bytes1 = {102, 101, 100}, 
            shorts1 = {23002, 23001, 23000}, 
            chars1 = {46002, 46001, 46000}, 
            ints1 = {2_000_000_002, 2_000_000_001, 2_000_000_000}, 
            longs1 = {20_000_000_002L, 20_000_000_001L, 20_000_000_000L},
            floats1 = {3234567.654321f, 2234567.654321f, 1234567.654321f}, 
            doubles1 = {9654321.234567, 8654321.234567, 7654321.234567},
            strings1 = {"c", "b", "a"})
    public static class AnnoHost5 {}

    @Anno5
    public static class AnnoHost6 {}

    @Anno5
    public static class AnnoHost7 {}

    @Anno5(v1 = "a1", v2 = "a2")
    public static class AnnoHost8 {}

    @Anno5(v1 = "a1", v2 = "a2")
    public static class AnnoHost9 {}

    @Anno6
    public static class AnnoHost10 {}

    @Test
    public void testIsFastImpl() {
        Anno3 anno = AnnoHost1.class.getAnnotation(Anno3.class);
        assertTrue(anno instanceof Annotation);
    }    

    @Test
    public void testAnnotationMethodsNoMembers() {
        Anno3 anno = AnnoHost1.class.getAnnotation(Anno3.class);
        assertSame(Anno3.class, anno.annotationType());
        assertEquals(0, anno.hashCode());
        assertTrue(anno.equals(anno));
        assertEquals("@org.robovm.rt.annotation.AnnotationTest$Anno3()", anno.toString());
    }

    @Test
    public void testSingletonAnnotation() {
        Anno3 anno1 = AnnoHost1.class.getAnnotation(Anno3.class);
        Anno3 anno2 = AnnoHost2.class.getAnnotation(Anno3.class);
        assertSame(anno1, anno2);
    }

    @Test
    public void testAnnotationMembersWithoutDefaults() {
        Anno4 anno = AnnoHost3.class.getAnnotation(Anno4.class);
        assertEquals(false, anno.boolean1());
        assertEquals(101, anno.byte1());
        assertEquals(23001, anno.short1());
        assertEquals(46001, anno.char1());
        assertEquals(2_000_000_001, anno.int1());
        assertEquals(20_000_000_001L, anno.long1());
        assertEquals(2234567.654321f, anno.float1(), 0.0f);
        assertEquals(8654321.234567, anno.double1(), 0.0);
        assertEquals("string1value", anno.string1());
        assertEquals(Integer.class, anno.class1());
        assertEquals("bar", anno.anno1().symbol());
        assertEquals(true, anno.anno1().dynamic());
        assertTrue(Arrays.equals(new boolean[] {false, true}, anno.booleans1()));
        assertArrayEquals(new byte[] {102, 101, 100}, anno.bytes1());
        assertArrayEquals(new short[] {23002, 23001, 23000}, anno.shorts1());
        assertArrayEquals(new char[] {46002, 46001, 46000}, anno.chars1());
        assertArrayEquals(new int[] {2_000_000_002, 2_000_000_001, 2_000_000_000}, anno.ints1());
        assertArrayEquals(new long[] {20_000_000_002L, 20_000_000_001L, 20_000_000_000L}, anno.longs1());
        assertArrayEquals(new float[] {3234567.654321f, 2234567.654321f, 1234567.654321f}, anno.floats1(), 0.0f);
        assertArrayEquals(new double[] {9654321.234567, 8654321.234567, 7654321.234567}, anno.doubles1(), 0.0);
        assertArrayEquals(new String[] {"c", "b", "a"}, anno.strings1());
    }

    @Test
    public void testAnnotationMemberDefaults() {
        Anno4 anno = AnnoHost3.class.getAnnotation(Anno4.class);
        assertEquals(true, anno.boolean2());
        assertEquals(100, anno.byte2());
        assertEquals(23000, anno.short2());
        assertEquals(46000, anno.char2());
        assertEquals(2_000_000_000, anno.int2());
        assertEquals(20_000_000_000L, anno.long2());
        assertEquals(1234567.654321f, anno.float2(), 0.0f);
        assertEquals(7654321.234567, anno.double2(), 0.0);
        assertEquals("string2default", anno.string2());
        assertEquals(byte.class, anno.class2());
        assertEquals("foo", anno.anno2().symbol());
        assertEquals(true, anno.anno2().dynamic());
        assertTrue(Arrays.equals(new boolean[] {true, false, true}, anno.booleans2()));
        assertArrayEquals(new byte[] {100, 101, 102}, anno.bytes2());
        assertArrayEquals(new short[] {23000, 23001, 23002}, anno.shorts2());
        assertArrayEquals(new char[] {46000, 46001, 46002}, anno.chars2());
        assertArrayEquals(new int[] {2_000_000_000, 2_000_000_001, 2_000_000_002}, anno.ints2());
        assertArrayEquals(new long[] {20_000_000_000L, 20_000_000_001L, 20_000_000_002L}, anno.longs2());
        assertArrayEquals(new float[] {1234567.654321f, 2234567.654321f, 3234567.654321f}, anno.floats2(), 0.0f);
        assertArrayEquals(new double[] {7654321.234567, 8654321.234567, 9654321.234567}, anno.doubles2(), 0.0);
        assertArrayEquals(new String[] {"a", "b", "c"}, anno.strings2());
    }
    
    @Test
    public void testEquals() {
        Anno4 anno1 = AnnoHost3.class.getAnnotation(Anno4.class);
        Anno4 anno2 = AnnoHost4.class.getAnnotation(Anno4.class);
        Anno4 anno3 = AnnoHost5.class.getAnnotation(Anno4.class);
        assertNotSame(anno1,  anno2);
        assertTrue(anno1.equals(anno2));
        assertFalse(anno1.equals(anno3));
    }

    @Test
    public void testHashCodeWithMembers() {
        Anno4 anno1 = AnnoHost3.class.getAnnotation(Anno4.class);
        Anno4 anno2 = AnnoHost4.class.getAnnotation(Anno4.class);
        Anno4 anno3 = AnnoHost5.class.getAnnotation(Anno4.class);
        assertFalse(0 == anno1.hashCode());
        assertFalse(0 == anno3.hashCode());
        assertEquals(anno1.hashCode(), anno2.hashCode());
        assertFalse(anno1.hashCode() == anno3.hashCode());
    }
    
    @Test
    public void testAccessorsReturnCopies() throws Exception {
        Anno4 anno = AnnoHost3.class.getAnnotation(Anno4.class);
        assertSame(anno.class1(), anno.class1());
        assertSame(anno.class2(), anno.class2());
        assertSame(anno.string1(), anno.string1());
        assertSame(anno.string2(), anno.string2());
        assertSame(anno.anno1(), anno.anno1());
        assertSame(anno.anno2(), anno.anno2());
        assertNotSame(anno.booleans1(), anno.booleans1());
        assertNotSame(anno.booleans2(), anno.booleans2());
        assertNotSame(anno.bytes1(), anno.bytes1());
        assertNotSame(anno.bytes2(), anno.bytes2());
        assertNotSame(anno.shorts1(), anno.shorts1());
        assertNotSame(anno.shorts2(), anno.shorts2());
        assertNotSame(anno.chars1(), anno.chars1());
        assertNotSame(anno.chars2(), anno.chars2());
        assertNotSame(anno.ints1(), anno.ints1());
        assertNotSame(anno.ints2(), anno.ints2());
        assertNotSame(anno.longs1(), anno.longs1());
        assertNotSame(anno.longs2(), anno.longs2());
        assertNotSame(anno.floats1(), anno.floats1());
        assertNotSame(anno.floats2(), anno.floats2());
        assertNotSame(anno.double1(), anno.double1());
        assertNotSame(anno.double2(), anno.double2());
        assertNotSame(anno.strings1(), anno.strings1());
        assertNotSame(anno.strings2(), anno.strings2());
    }
    
    @Test
    public void testSingletonWhenOnlyDefaults() throws Exception {
        Anno5 anno1 = AnnoHost6.class.getAnnotation(Anno5.class);
        Anno5 anno2 = AnnoHost7.class.getAnnotation(Anno5.class);
        Anno5 anno3 = AnnoHost8.class.getAnnotation(Anno5.class);
        Anno5 anno4 = AnnoHost9.class.getAnnotation(Anno5.class);
        assertSame(anno1, anno2);
        assertNotSame(anno1, anno3);
        assertNotSame(anno3, anno4);
    }
    
    @Test
    public void testNonRuntimeVisibleAnnotationsAsDefaultValues() throws Exception {
        Anno6 anno = AnnoHost10.class.getAnnotation(Anno6.class);
        assertTrue(anno.anno1() instanceof Anno1);
        assertTrue(anno.anno2() instanceof Anno2);
    }
}
