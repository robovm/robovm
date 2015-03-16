/*
 * Copyright (C) 2013 RoboVM AB
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
package org.robovm.rt.bro;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.MarshalsArray;
import org.robovm.rt.bro.annotation.MarshalsPointer;

/**
 * Contains marshalers for arrays of primitive types.
 */
public class ArrayMarshalers {

    private static void checkDimensions(String type, String suffix, int actual, int expected) {
        if (actual != expected) {
            throw new IllegalArgumentException("Expected " + type + "[" 
                    + expected + "]" + suffix + ". Got " 
                    + type + "[" + actual + "]" + suffix);
        }
    }

    /**
     * Marshals to/from {@code byte[]}, {@code byte[][]} and {@code byte[][][]}.
     */
    public static class ByteArrayMarshaler {
        private static final String TYPE = "byte";
        private static final int SHIFT = 0;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final byte[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static byte[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final byte[] o = new byte[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final byte[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static byte[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final byte[][] o = new byte[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final byte[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static byte[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final byte[][][] o = new byte[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final byte[][][] o, long handle, long flags, int d1, int d2, int d3) {
            byte[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code short[]}, {@code short[][]} and {@code short[][][]}.
     */
    public static class ShortArrayMarshaler {
        private static final String TYPE = "short";
        private static final int SHIFT = 1;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final short[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static short[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final short[] o = new short[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final short[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static short[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final short[][] o = new short[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final short[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static short[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final short[][][] o = new short[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final short[][][] o, long handle, long flags, int d1, int d2, int d3) {
            short[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code char[]}, {@code char[][]} and {@code char[][][]}.
     */
    public static class CharArrayMarshaler {
        private static final String TYPE = "char";
        private static final int SHIFT = 1;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final char[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static char[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final char[] o = new char[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final char[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static char[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final char[][] o = new char[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final char[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static char[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final char[][][] o = new char[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final char[][][] o, long handle, long flags, int d1, int d2, int d3) {
            char[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code int[]}, {@code int[][]} and {@code int[][][]}.
     */
    public static class IntArrayMarshaler {
        private static final String TYPE = "int";
        private static final int SHIFT = 2;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final int[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static int[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final int[] o = new int[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final int[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static int[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final int[][] o = new int[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final int[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static int[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final int[][][] o = new int[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final int[][][] o, long handle, long flags, int d1, int d2, int d3) {
            int[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code long[]}, {@code long[][]} and {@code long[][][]}.
     */
    public static class LongArrayMarshaler {
        private static final String TYPE = "long";
        private static final int SHIFT = 3;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final long[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static long[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final long[] o = new long[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final long[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static long[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final long[][] o = new long[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final long[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static long[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final long[][][] o = new long[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final long[][][] o, long handle, long flags, int d1, int d2, int d3) {
            long[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code float[]}, {@code float[][]} and
     * {@code float[][][]}.
     */
    public static class FloatArrayMarshaler {
        private static final String TYPE = "float";
        private static final int SHIFT = 2;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final float[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static float[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final float[] o = new float[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final float[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static float[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final float[][] o = new float[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final float[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static float[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final float[][][] o = new float[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final float[][][] o, long handle, long flags, int d1, int d2, int d3) {
            float[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }
    
    /**
     * Marshals to/from {@code double[]}, {@code double[][]} and
     * {@code double[][][]}.
     */
    public static class DoubleArrayMarshaler {
        private static final String TYPE = "double";
        private static final int SHIFT = 3;
        @MarshalsPointer(supportedCallTypes = MarshalerFlags.CALL_TYPE_BRIDGE)
        public static long toNative(final double[] o, long flags) {
            if (o == null) {
                return 0L;
            }
            return VM.getArrayValuesAddress(o);
        }
        @MarshalsArray
        public static double[] toObject(Class<?> arrayClass, long handle, long flags, int d1) {
            final double[] o = new double[d1];
            final int off = d1 << SHIFT;
            VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
            return o;
        }
        @MarshalsArray
        public static void toNative(final double[] o, long handle, long flags, int d1) {
            final int off = d1 << SHIFT;
            checkDimensions(TYPE, "", o.length, d1);
            VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
        }
        @MarshalsArray
        public static double[][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2) {
            final double[][] o = new double[d1][d2];
            final int off = d2 << SHIFT;
            for (int i = 0; i < d1; i++) {
                VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                handle += off;
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final double[][] o, long handle, long flags, int d1, int d2) {
            final int off = d2 << SHIFT;
            checkDimensions(TYPE, "[]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(TYPE + "[]", "", o[i].length, d2);
            }
            for (int i = 0; i < len1; i++) {
                VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                handle += off;
            }
        }
        @MarshalsArray
        public static double[][][] toObject(Class<?> arrayClass, long handle, long flags, int d1, int d2, int d3) {
            final double[][][] o = new double[d1][d2][d3];
            final int off = d3 << SHIFT;
            for (int i = 0; i < d1; i++) {
                for (int j = 0; j < d2; j++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                    handle += off;
                }
            }
            return o;
        }
        @MarshalsArray
        public static void toNative(final double[][][] o, long handle, long flags, int d1, int d2, int d3) {
            double[][] p;
            final int off = d3 << SHIFT;
            checkDimensions(TYPE, "[][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                p = o[i];
                checkDimensions(TYPE + "[]", "[]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                }
            }
            for (int i = 0; i < len1; i++) {
                p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                    handle += off;
                }
            }
        }
    }

}
