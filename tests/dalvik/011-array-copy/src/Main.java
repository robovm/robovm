/*
 * Copyright (C) 2007 The Android Open Source Project
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

import java.util.Arrays;

/**
 * System.arraycopy cases
 */
public class Main {
    public static void main(String args[]) {
        testObjectCopy();
        testOverlappingMoves();
    }

    public static void testObjectCopy() {
        String[] stringArray = new String[8];
        Object[] objectArray = new Object[8];

        for (int i = 0; i < stringArray.length; i++)
            stringArray[i] = new String(Integer.toString(i));

        System.out.println("string -> object");
        System.arraycopy(stringArray, 0, objectArray, 0, stringArray.length);
        System.out.println("object -> string");
        System.arraycopy(objectArray, 0, stringArray, 0, stringArray.length);
        System.out.println("object -> string (modified)");
        objectArray[4] = new ImplA();
        try {
            System.arraycopy(objectArray, 0, stringArray, 0,stringArray.length);
        }
        catch (ArrayStoreException ase) {
            System.out.println("caught ArrayStoreException (expected)");
        }
    }

    static final int ARRAY_SIZE = 8;

    static void initByteArray(byte[] array) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (byte) i;
        }
    }
    static void initShortArray(short[] array) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (short) i;
        }
    }
    static void initIntArray(int[] array) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) i;
        }
    }
    static void initLongArray(long[] array) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (long) i;
        }
    }

    /*
     * Perform an array copy operation on primitive arrays with different
     * element widths.
     */
    static void makeCopies(int srcPos, int dstPos, int length) {
        byte[] byteArray = new byte[ARRAY_SIZE];
        short[] shortArray = new short[ARRAY_SIZE];
        int[] intArray = new int[ARRAY_SIZE];
        long[] longArray = new long[ARRAY_SIZE];

        initByteArray(byteArray);
        initShortArray(shortArray);
        initIntArray(intArray);
        initLongArray(longArray);

        System.arraycopy(byteArray, srcPos, byteArray, dstPos, length);
        System.arraycopy(shortArray, srcPos, shortArray, dstPos, length);
        System.arraycopy(intArray, srcPos, intArray, dstPos, length);
        System.arraycopy(longArray, srcPos, longArray, dstPos, length);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (intArray[i] != byteArray[i]) {
                System.out.println("mismatch int vs byte at " + i + " : " +
                    Arrays.toString(byteArray));
                break;
            } else if (intArray[i] != shortArray[i]) {
                System.out.println("mismatch int vs short at " + i + " : " +
                    Arrays.toString(shortArray));
                break;
            } else if (intArray[i] != longArray[i]) {
                System.out.println("mismatch int vs long at " + i + " : " +
                    Arrays.toString(longArray));
                break;
            }
        }

        System.out.println("copy: " + srcPos + "," + dstPos + "," + length +
            ": " + Arrays.toString(intArray));
    }

    public static void testOverlappingMoves() {
        /* do nothing */
        makeCopies(0, 0, 0);

        /* do more nothing */
        makeCopies(0, 0, ARRAY_SIZE);

        /* copy forward, even alignment */
        makeCopies(0, 2, 4);

        /* copy backward, even alignment */
        makeCopies(2, 0, 4);

        /* copy forward, odd alignment */
        makeCopies(1, 3, 4);

        /* copy backward, odd alignment */
        makeCopies(3, 1, 4);

        /* copy backward, odd length */
        makeCopies(3, 1, 5);

        /* copy forward, odd length */
        makeCopies(1, 3, 5);

        /* copy forward, mixed alignment */
        makeCopies(0, 3, 5);

        /* copy backward, mixed alignment */
        makeCopies(3, 0, 5);

        /* copy forward, mixed alignment, trivial length */
        makeCopies(0, 5, 1);
    }
}
