/*
 * Copyright 2006 The Android Open Source Project
 */

import java.lang.reflect.Array;

/**
 * Test java.lang.reflect.Array.
 */
public class Main {
    public static void main(String[] args) {
        testSingleInt();
        testSingleChar();
        testSingleShort();
        testSingleLong();
        testSingle();
        testMultiInt();
        testMulti();

        System.out.println("ReflectArrayTest passed");
    }

    static void testSingleInt() {
        Object intArray;

        intArray = Array.newInstance(Integer.TYPE, 2);

        int[] array = (int[]) intArray;
        array[0] = 5;
        Array.setInt(intArray, 1, 6);

        if (Array.getInt(intArray, 0) != 5)
            throw new RuntimeException();
        if (array[1] != 6)
            throw new RuntimeException();
        try {
            array[2] = 27;
            throw new RuntimeException("store should have failed");
        } catch (ArrayIndexOutOfBoundsException abe) { }
        try {
            Array.setInt(intArray, 2, 27);
            throw new RuntimeException("store should have failed");
        } catch (ArrayIndexOutOfBoundsException abe) { }
        if (array.length != Array.getLength(intArray) ||
            array.length != 2)
        {
            throw new RuntimeException("bad len");
        }

        Integer x123 = Integer.valueOf(123);
        Integer x456 = Integer.valueOf(456);

        Array.set(intArray, 0, x123);
        Array.set(intArray, 1, x456);
        if (!Array.get(intArray, 0).equals(x123) || !Array.get(intArray, 1).equals(x456)) {
            throw new RuntimeException("bad 123 or 456");
        }

        int[][] wrongArray;
        try {
            wrongArray = (int[][]) intArray;
            throw new RuntimeException("cast should have failed");
        } catch (ClassCastException cce) { }

        intArray = Array.newInstance(Integer.TYPE, 0);
        if (Array.getLength(intArray) != 0)
            throw new RuntimeException();
        System.out.println("ReflectArrayTest.testSingleInt passed");
    }

    static void testSingleChar() {
        Object charArray = Array.newInstance(Character.TYPE, 7);

        char[] array = (char[]) charArray;
        array[0] = '0';
        array[1] = 'W';
        array[2] = '2';
        array[3] = '3';
        array[4] = 'X';
        array[5] = '5';
        array[6] = '6';
        Array.setChar(charArray, 1, '1');
        Array.setChar(charArray, 4, '4');
        try {
            Array.setShort(charArray, 3, (short) 'Y');
            throw new RuntimeException("shouldn't allow short in char array");
        } catch (IllegalArgumentException iae) {}
        try {
            Array.setInt(charArray, 5, 'Z');
            throw new RuntimeException("shouldn't allow int in char array");
        } catch (IllegalArgumentException iae) {}

        try {
            for (int i = 0; i < array.length; i++) {
                if (Array.getInt(charArray, i) - '0' != i) {
                    throw new RuntimeException("mismatch: " + i + " is " + array[i]);
                }
            }

            if (Array.getInt(charArray, 4) != '4') {
                throw new RuntimeException("load should have worked");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println("Couldn't Array.getInt(charArray)");
        }
        try {
            Array.getByte(charArray, 2);
            throw new RuntimeException("shouldn't allow read of char as byte");
        } catch (IllegalArgumentException iae) {}

        Array.setChar(charArray, 3, (char) 0xffff);
        try {
            if (Array.getInt(charArray, 3) != 0xffff) {
                throw new RuntimeException("char got sign-extended? "
                    + Array.getInt(charArray, 3));
            }
        } catch (IllegalArgumentException iae) {
            System.err.println("Couldn't Array.getInt(charArray)");
        }

        System.out.println("ReflectArrayTest.testSingleChar passed");
    }

    static void testSingleShort() {
        Object shortArray = Array.newInstance(Short.TYPE, 1);
        Array.setShort(shortArray, 0, (short) -1);
        if (Array.getInt(shortArray, 0) != -1) {
            throw new RuntimeException("short didn't get sign-extended");
        }

        Short box = (Short) Array.get(shortArray, 0);

        System.out.println("ReflectArrayTest.testSingleShort passed");
    }

    static void testSingleLong() {
        Object longArray = Array.newInstance(Long.TYPE, 2);
        Array.setInt(longArray, 0, 123);
        Array.setLong(longArray, 1, 0x1122334455667788L);
        try {
            Array.getInt(longArray, 0);
            throw new RuntimeException("shouldn't allow read of long as int");
        } catch (IllegalArgumentException iae) {}

        long[] array = (long[]) longArray;
        if (array[0] != 123 || array[1] != 0x1122334455667788L) {
            throw new RuntimeException();
        }

        float f = Array.getFloat(longArray, 0);
        if (f < 122.9 || f > 123.1) {
            throw new RuntimeException("long-as-float failed - " + f);
        }
        if (Array.getLong(longArray, 1) != 0x1122334455667788L) {
            throw new RuntimeException("long1 failed");
        }

        System.out.println("ReflectArrayTest.testSingleLong passed");
    }

    static void testSingle() {
        Object strArray;

        strArray = Array.newInstance(String.class, 2);

        String[] array = (String[]) strArray;
        array[0] = "entry zero";
        Array.set(strArray, 1, "entry one");
        try {
            Array.set(strArray, 2, "entry two");
            throw new RuntimeException("store should have failed");
        } catch (ArrayIndexOutOfBoundsException abe) { }

        //System.out.println("array: " + array);

        if (!"entry zero".equals(Array.get(strArray, 0)))
            throw new RuntimeException();
        if (!"entry one".equals(array[1]))
            throw new RuntimeException();

        if (array.length != Array.getLength(strArray) ||
            array.length != 2)
        {
            throw new RuntimeException("bad len");
        }

        try {
            Array.set(strArray, 0, new Integer(5));
            throw new RuntimeException("store of Integer should have failed");
        } catch (IllegalArgumentException iae) {}
        System.out.println("ReflectArrayTest.testSingle passed");
    }

    static void testMultiInt() {
        Object intIntIntArray;
        int[] dimensions = { 3, 2, 1 };

        intIntIntArray = Array.newInstance(Integer.TYPE, dimensions);
        int[][][] array3 = (int[][][]) intIntIntArray;

        array3[0][0][0] = 123;      // trouble
        array3[2][1][0] = 456;

        try {
            array3[2][1][1] = 768;
            throw new RuntimeException("store should have failed");
        }
        catch (ArrayIndexOutOfBoundsException abe) {
        }
        System.out.println("ReflectArrayTest.testMultiInt passed");
    }

    static void testMulti() {
        Object strStrStrArray;
        int[] dimensions = { 1, 2, 3 };

        strStrStrArray = Array.newInstance(String.class, dimensions);
        String[][][] array3 = (String[][][]) strStrStrArray;

        array3[0][0][0] = "zero zero zero";
        array3[0][1][2] = "zero one two";

        try {
            array3[1][0][0] = "bad store";
            throw new RuntimeException("store should have failed");
        }
        catch (ArrayIndexOutOfBoundsException abe) {
        }

        try {
            String[][] array2 = (String[][]) strStrStrArray;
            throw new RuntimeException("expecting bad cast");
        }
        catch (ClassCastException cce) {
        }

        String[] strar = new String[4];
        strar[2] = "zero one two ++";
        array3[0][1] = strar;
        System.out.println(array3[0][1][2]);
        //System.out.println("array3: " + array3);


        int[] dimensions2 = { 1, 2 };
        strStrStrArray = Array.newInstance(String[].class, dimensions2);
        array3 = (String[][][]) strStrStrArray;

        array3[0][1] = new String[3];
        array3[0][1][2] = "zero one two";
        try {
            array3[1][0][0] = "bad store";
            throw new RuntimeException("store should have failed");
        }
        catch (ArrayIndexOutOfBoundsException abe) {
        }
        System.out.println("ReflectArrayTest.testMulti passed");
    }
}
