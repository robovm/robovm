// Copyright 2008 The Android Open Source Project


/**
 * Exercise arrays.
 */
public class Array {

    /*
     * Verify array contents.
     */
    static void checkBytes(byte[] bytes) {
        assert(bytes[0] == 0);
        assert(bytes[1] == -1);
        assert(bytes[2] == -2);
        assert(bytes[3] == -3);
        assert(bytes[4] == -4);
    }
    static void checkShorts(short[] shorts) {
        assert(shorts[0] == 20);
        assert(shorts[1] == 10);
        assert(shorts[2] == 0);
        assert(shorts[3] == -10);
        assert(shorts[4] == -20);
    }
    static void checkChars(char[] chars) {
        assert(chars[0] == 40000);
        assert(chars[1] == 40001);
        assert(chars[2] == 40002);
        assert(chars[3] == 40003);
        assert(chars[4] == 40004);
    }
    static void checkInts(int[] ints) {
        assert(ints[0] == 70000);
        assert(ints[1] == 70001);
        assert(ints[2] == 70002);
        assert(ints[3] == 70003);
        assert(ints[4] == 70004);
    }
    static void checkBooleans(boolean[] booleans) {
        assert(booleans[0]);
        assert(booleans[1]);
        assert(!booleans[2]);
        assert(booleans[3]);
        assert(!booleans[4]);
    }
    static void checkFloats(float[] floats) {
        assert(floats[0] == -1.5);
        assert(floats[1] == -0.5);
        assert(floats[2] == 0.0);
        assert(floats[3] == 0.5);
        assert(floats[4] == 1.5);
    }
    static void checkLongs(long[] longs) {
        assert(longs[0] == 0x1122334455667788L);
        assert(longs[1] == 0x8877665544332211L);
        assert(longs[2] == 0L);
        assert(longs[3] == 1L);
        assert(longs[4] == -1L);
    }
    static void checkStrings(String[] strings) {
        assert(strings[0].equals("zero"));
        assert(strings[1].equals("one"));
        assert(strings[2].equals("two"));
        assert(strings[3].equals("three"));
        assert(strings[4].equals("four"));
    }

    /*
     * Try bad range values, 32 bit get/put.
     */
    static void checkRange32(int[] ints, int[] empty, int negVal1, int negVal2){
        System.out.println("Array.checkRange32");
        int i = 0;

        assert(ints.length == 5);

        try {
            i = ints[5];            // exact bound
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            ints[5] = i;            // exact bound
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            i = ints[6];            // one past
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            i = ints[negVal1];      // -1
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            ints[negVal1] = i;      // -1
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            i = ints[negVal2];      // min int
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }


        try {
            i = empty[1];
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
    }

    /*
     * Try bad range values, 64 bit get/put.
     */
    static void checkRange64(long[] longs, int negVal1, int negVal2) {
        System.out.println("Array.checkRange64");
        long l = 0L;

        assert(longs.length == 5);

        try {
            l = longs[5];            // exact bound
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            longs[5] = l;            // exact bound
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            l = longs[6];            // one past
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            l = longs[negVal1];      // -1
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            longs[negVal1] = l;      // -1
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
        try {
            l = longs[negVal2];      // min int
            assert(false);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // good
        }
    }

    /*
     * Test negative allocations of object and primitive arrays.
     */
    static void checkNegAlloc(int count) {
        System.out.println("Array.checkNegAlloc");
        String[] strings;
        int[] ints;

        try {
            ints = new int[count];
            assert(false);
        } catch (NegativeArraySizeException nase) {
            // good
        }

        try {
            strings = new String[count];
            assert(false);
        } catch (NegativeArraySizeException nase) {
            // good
        }
    }

    public static void run() {
        System.out.println("Array check...");

        byte[] xBytes = new byte[] { 0, -1, -2, -3, -4 };
        short[] xShorts = new short[] { 20, 10, 0, -10, -20 };
        char[] xChars = new char[] { 40000, 40001, 40002, 40003, 40004 };
        int[] xInts = new int[] { 70000, 70001, 70002, 70003, 70004 };
        boolean[] xBooleans = new boolean[] { true, true, false, true, false };
        float[] xFloats = new float[] { -1.5f, -0.5f, 0.0f, 0.5f, 1.5f };
        long[] xLongs = new long[] {
            0x1122334455667788L, 0x8877665544332211L, 0L, 1L, -1l };
        String[] xStrings = new String[] {
            "zero", "one", "two", "three", "four" };

        int[] xEmpty = new int[0];

        checkBytes(xBytes);
        checkShorts(xShorts);
        checkChars(xChars);
        checkInts(xInts);
        checkBooleans(xBooleans);
        checkFloats(xFloats);
        checkLongs(xLongs);
        checkStrings(xStrings);

        checkRange32(xInts, xEmpty, -1, (int) 0x80000000);
        checkRange64(xLongs, -1, (int) 0x80000000);

        checkNegAlloc(-1);
    }
}
