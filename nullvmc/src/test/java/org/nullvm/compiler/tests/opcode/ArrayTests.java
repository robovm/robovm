/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode;

/**
 *
 * @version $Id$
 */
public class ArrayTests extends AbstractOpcodeTests {
    
    public String run() {
        
        Object a = new Object();
        Object b = new Object();

        int[] intArray = ArrayOpcodes.newarray_int(3);
        assertNotNull("newarray_int", intArray);
        assertEqualsInt("arraylength_int", 3, ArrayOpcodes.arraylength_int(intArray));
        assertEqualsInt("iaload", 0, ArrayOpcodes.iaload(intArray, 0));
        assertEqualsInt("iaload", 0, ArrayOpcodes.iaload(intArray, 1));
        assertEqualsInt("iaload", 0, ArrayOpcodes.iaload(intArray, 2));
        ArrayOpcodes.iastore(intArray, 0, 1);
        ArrayOpcodes.iastore(intArray, 1, 0xffffffff);
        ArrayOpcodes.iastore(intArray, 2, 0x80000000);
        assertEqualsInt("iaload", 1, ArrayOpcodes.iaload(intArray, 0));
        assertEqualsInt("iaload", 0xffffffff, ArrayOpcodes.iaload(intArray, 1));
        assertEqualsInt("iaload", 0x80000000, ArrayOpcodes.iaload(intArray, 2));
        assertEqualsInt("arraylength_int", 0, ArrayOpcodes.arraylength_int(ArrayOpcodes.newarray_int(0)));

        byte[] byteArray = ArrayOpcodes.newarray_byte(3);
        assertNotNull("newarray_byte", byteArray);
        assertEqualsInt("arraylength_byte", 3, ArrayOpcodes.arraylength_byte(byteArray));
        assertEqualsInt("baload_byte", 0, ArrayOpcodes.baload_byte(byteArray, 0));
        assertEqualsInt("baload_byte", 0, ArrayOpcodes.baload_byte(byteArray, 1));
        assertEqualsInt("baload_byte", 0, ArrayOpcodes.baload_byte(byteArray, 2));
        ArrayOpcodes.bastore_byte(byteArray, 0, (byte) 1);
        ArrayOpcodes.bastore_byte(byteArray, 1, (byte) 0x7f);
        ArrayOpcodes.bastore_byte(byteArray, 2, (byte) 0x80);
        assertEqualsInt("baload_byte", 1, ArrayOpcodes.baload_byte(byteArray, 0));
        assertEqualsInt("baload_byte", (byte) 0x7f, ArrayOpcodes.baload_byte(byteArray, 1));
        assertEqualsInt("baload_byte", (byte) 0x80, ArrayOpcodes.baload_byte(byteArray, 2));

        short[] shortArray = ArrayOpcodes.newarray_short(3);
        assertNotNull("newarray_short", shortArray);
        assertEqualsInt("arraylength_short", 3, ArrayOpcodes.arraylength_short(shortArray));
        assertEqualsInt("saload", 0, ArrayOpcodes.saload(shortArray, 0));
        assertEqualsInt("saload", 0, ArrayOpcodes.saload(shortArray, 1));
        assertEqualsInt("saload", 0, ArrayOpcodes.saload(shortArray, 2));
        ArrayOpcodes.sastore(shortArray, 0, (short) 1);
        ArrayOpcodes.sastore(shortArray, 1, (short) 0x7fff);
        ArrayOpcodes.sastore(shortArray, 2, (short) 0x8000);
        assertEqualsInt("saload", 1, ArrayOpcodes.saload(shortArray, 0));
        assertEqualsInt("saload", (short) 0x7fff, ArrayOpcodes.saload(shortArray, 1));
        assertEqualsInt("saload", (short) 0x8000, ArrayOpcodes.saload(shortArray, 2));

        char[] charArray = ArrayOpcodes.newarray_char(3);
        assertNotNull("newarray_char", charArray);
        assertEqualsInt("arraylength_char", 3, ArrayOpcodes.arraylength_char(charArray));
        assertEqualsInt("caload", 0, ArrayOpcodes.caload(charArray, 0));
        assertEqualsInt("caload", 0, ArrayOpcodes.caload(charArray, 1));
        assertEqualsInt("caload", 0, ArrayOpcodes.caload(charArray, 2));
        ArrayOpcodes.castore(charArray, 0, (char) 1);
        ArrayOpcodes.castore(charArray, 1, (char) 0x7fff);
        ArrayOpcodes.castore(charArray, 2, (char) 0x8000);
        assertEqualsInt("caload", 1, ArrayOpcodes.caload(charArray, 0));
        assertEqualsInt("caload", 0x7fff, ArrayOpcodes.caload(charArray, 1));
        assertEqualsInt("caload", 0x8000, ArrayOpcodes.caload(charArray, 2));

        boolean[] booleanArray = ArrayOpcodes.newarray_boolean(2);
        assertNotNull("newarray_boolean", booleanArray);
        assertEqualsInt("arraylength_boolean", 2, ArrayOpcodes.arraylength_boolean(booleanArray));
        assertEqualsInt("baload_boolean", 0, ArrayOpcodes.baload_boolean(booleanArray, 0) ? 1 : 0);
        assertEqualsInt("baload_boolean", 0, ArrayOpcodes.baload_boolean(booleanArray, 1) ? 1 : 0);
        ArrayOpcodes.bastore_boolean(booleanArray, 0, true);
        ArrayOpcodes.bastore_boolean(booleanArray, 1, false);
        assertEqualsInt("baload_boolean", 1, ArrayOpcodes.baload_boolean(booleanArray, 0) ? 1 : 0);
        assertEqualsInt("baload_boolean", 0, ArrayOpcodes.baload_boolean(booleanArray, 1) ? 1 : 0);

        float[] floatArray = ArrayOpcodes.newarray_float(3);
        assertNotNull("newarray_float", floatArray);
        assertEqualsInt("arraylength_float", 3, ArrayOpcodes.arraylength_float(floatArray));
        assertEqualsFloat("faload", 0.0f, ArrayOpcodes.faload(floatArray, 0));
        assertEqualsFloat("faload", 0.0f, ArrayOpcodes.faload(floatArray, 1));
        assertEqualsFloat("faload", 0.0f, ArrayOpcodes.faload(floatArray, 2));
        ArrayOpcodes.fastore(floatArray, 0, 1.25f);
        ArrayOpcodes.fastore(floatArray, 1, 2.5f);
        ArrayOpcodes.fastore(floatArray, 2, 3.75f);
        assertEqualsFloat("faload", 1.25f, ArrayOpcodes.faload(floatArray, 0));
        assertEqualsFloat("faload", 2.5f, ArrayOpcodes.faload(floatArray, 1));
        assertEqualsFloat("faload", 3.75f, ArrayOpcodes.faload(floatArray, 2));

        long[] longArray = ArrayOpcodes.newarray_long(3);
        assertNotNull("newarray_long", longArray);
        assertEqualsInt("arraylength_long", 3, ArrayOpcodes.arraylength_long(longArray));
        assertEqualsLong("laload", 0, ArrayOpcodes.laload(longArray, 0));
        assertEqualsLong("laload", 0, ArrayOpcodes.laload(longArray, 1));
        assertEqualsLong("laload", 0, ArrayOpcodes.laload(longArray, 2));
        ArrayOpcodes.lastore(longArray, 0, 1);
        ArrayOpcodes.lastore(longArray, 1, 0xffffffffffffffffL);
        ArrayOpcodes.lastore(longArray, 2, 0x8000000000000000L);
        assertEqualsLong("laload", 1, ArrayOpcodes.laload(longArray, 0));
        assertEqualsLong("laload", 0xffffffffffffffffL, ArrayOpcodes.laload(longArray, 1));
        assertEqualsLong("laload", 0x8000000000000000L, ArrayOpcodes.laload(longArray, 2));

        double[] doubleArray = ArrayOpcodes.newarray_double(3);
        assertNotNull("newarray_double", doubleArray);
        assertEqualsInt("arraylength_double", 3, ArrayOpcodes.arraylength_double(doubleArray));
        assertEqualsDouble("daload", 0.0, ArrayOpcodes.daload(doubleArray, 0));
        assertEqualsDouble("daload", 0.0, ArrayOpcodes.daload(doubleArray, 1));
        assertEqualsDouble("daload", 0.0, ArrayOpcodes.daload(doubleArray, 2));
        ArrayOpcodes.dastore(doubleArray, 0, 1.25);
        ArrayOpcodes.dastore(doubleArray, 1, 2.5);
        ArrayOpcodes.dastore(doubleArray, 2, 3.75);
        assertEqualsDouble("daload", 1.25, ArrayOpcodes.daload(doubleArray, 0));
        assertEqualsDouble("daload", 2.5, ArrayOpcodes.daload(doubleArray, 1));
        assertEqualsDouble("daload", 3.75, ArrayOpcodes.daload(doubleArray, 2));

        Object[] objectArray = ArrayOpcodes.newarray_object(3);
        assertNotNull("newarray_object", objectArray);
        assertEqualsInt("arraylength_object", 3, ArrayOpcodes.arraylength_object(objectArray));
        assertNull("aaload", ArrayOpcodes.aaload(objectArray, 0));
        assertNull("aaload", ArrayOpcodes.aaload(objectArray, 1));
        assertNull("aaload", ArrayOpcodes.aaload(objectArray, 2));
        ArrayOpcodes.aastore(objectArray, 0, a);
        ArrayOpcodes.aastore(objectArray, 1, null);
        ArrayOpcodes.aastore(objectArray, 2, b);
        assertSameObject("aaload", a, ArrayOpcodes.aaload(objectArray, 0));
        assertNull("aaload", ArrayOpcodes.aaload(objectArray, 1));
        assertSameObject("aaload", b, ArrayOpcodes.aaload(objectArray, 2));
        
        int[][][] multiIntArray = null;
        byte[][] multiByteArray = null;
        int i, j, k;

        multiIntArray = ArrayOpcodes.multianewarray_int(4, 3, 2);
        assertNotNull("multianewarray_int", multiIntArray);
        assertEqualsInt("arraylength_object", 4, ArrayOpcodes.arraylength_object(multiIntArray));
        for (i = 0; i < 4; i++) {
            Object o1 = ArrayOpcodes.aaload(multiIntArray, i);
            assertNotNull("multiIntArray[i] != null", o1);
            assertEqualsInt("multiIntArray[i].length == 3", 3, ArrayOpcodes.arraylength_object((Object[]) o1));
            for (j = 0; j < 3; j++) {
                Object o2 = ArrayOpcodes.aaload((Object[]) o1, j);
                assertNotNull("multiIntArray[i][j] != null", o2);
                assertEqualsInt("multiIntArray[i][j].length == 3", 2, ArrayOpcodes.arraylength_int((int[]) o2));
                for (k = 0; k < 2; k++) {
                    assertEqualsInt("multiIntArray[i][j][k] == 0", 0, ArrayOpcodes.iaload((int[]) o2, k));
                }
            }
        }

        multiIntArray = ArrayOpcodes.multianewarray_int(2, 0, 0);
        assertNotNull("multianewarray_int", multiIntArray);
        assertEqualsInt("arraylength_object", 2, ArrayOpcodes.arraylength_object(multiIntArray));
        for (i = 0; i < 2; i++) {
            Object o1 = ArrayOpcodes.aaload(multiIntArray, i);
            assertNotNull("multiIntArray[i] != null", o1);
            assertEqualsInt("multiIntArray[i].length == 0", 0, ArrayOpcodes.arraylength_object((Object[]) o1));
        }

        multiIntArray = ArrayOpcodes.multianewarray_int_partial(2, 3);
        assertNotNull("multianewarray_int_partial", multiIntArray);
        assertEqualsInt("arraylength_object", 2, ArrayOpcodes.arraylength_object(multiIntArray));
        for (i = 0; i < 2; i++) {
            Object o1 = ArrayOpcodes.aaload(multiIntArray, i);
            assertNotNull("multiIntArray[i] != null", o1);
            assertEqualsInt("multiIntArray[i].length == 3", 3, ArrayOpcodes.arraylength_object((Object[]) o1));
            for (j = 0; j < 3; j++) {
                assertNull("multiIntArray[i][j] == null", ArrayOpcodes.aaload((Object[]) o1, j));
            }
        }

        multiIntArray = ArrayOpcodes.multianewarray_int_partial(0, 0);
        assertNotNull("multianewarray_int_partial", multiIntArray);
        assertEqualsInt("arraylength_object", 0, ArrayOpcodes.arraylength_object(multiIntArray));

        multiByteArray = ArrayOpcodes.multianewarray_byte(3, 2);
        assertNotNull("multianewarray_byte", multiByteArray);
        assertEqualsInt("arraylength_object", 3, ArrayOpcodes.arraylength_object(multiByteArray));
        for (i = 0; i < 3; i++) {
            Object o1 = ArrayOpcodes.aaload(multiByteArray, i);
            assertNotNull("multiByteArray[i] != null", o1);
            assertEqualsInt("multiByteArray[i].length == 2", 2, ArrayOpcodes.arraylength_byte((byte[]) o1));
            for (j = 0; j < 2; j++) {
                assertEqualsInt("multiByteArray[i][j] == 0", 0, ArrayOpcodes.baload_byte((byte[]) o1, j));
            }
        }
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new ArrayTests().run());
    }
    
}
