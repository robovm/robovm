/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.lang;

import junit.framework.TestCase;

public final class ArrayStoreExceptionTest extends TestCase {
    public void testArrayStoreException_store1() throws Exception {
        Object[] array = new String[10];
        Object o = new Exception();
        try {
            array[0] = o;
            fail();
        } catch (ArrayStoreException ex) {
            ex.printStackTrace();
            assertEquals("java.lang.Exception cannot be stored in an array of type "
                    + "java.lang.String[]",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_store2() throws Exception {
        Object[] array = new Nonce[10][];
        Object o = new Integer(5);
        try {
            array[0] = o;
            fail();
        } catch (ArrayStoreException ex) {
            assertEquals("java.lang.Integer cannot be stored in an array of type "
                    + "libcore.java.lang.ArrayStoreExceptionTest$Nonce[][]",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_store3() throws Exception {
        Object[] array = new Float[10][];
        Object o = new Nonce[1];
        try {
            array[0] = o;
            fail();
        } catch (ArrayStoreException ex) {
            assertEquals("libcore.java.lang.ArrayStoreExceptionTest$Nonce[] cannot be stored "
                    + "in an array of type java.lang.Float[][]",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy1() throws Exception {
        String[] src = new String[] { null, null, null, null, "hello", "goodbye" };
        Integer[] dest = new Integer[10];
        try {
            System.arraycopy(src, 1, dest, 0, 5);
        } catch (ArrayStoreException ex) {
            ex.printStackTrace();
            assertEquals("source[4] of type java.lang.String cannot be stored in destination "
                    + "array of type java.lang.Integer[]",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy2() throws Exception {
        String[] src = new String[1];
        int[] dest = new int[1];
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("java.lang.String[] and int[] are incompatible array types",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy3() throws Exception {
        float[] src = new float[1];
        Runnable[] dest = new Runnable[1];
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("float[] and java.lang.Runnable[] are incompatible array types",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy4() throws Exception {
        boolean[] src = new boolean[1];
        double[][] dest = new double[1][];
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("boolean[] and double[][] are incompatible array types",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy5() throws Exception {
        String src = "blort";
        Object[] dest = new Object[1];
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("source of type java.lang.String is not an array",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy6() throws Exception {
        Object[] src = new Object[1];
        Integer dest = new Integer(5);
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("destination of type java.lang.Integer is not an array",
                    ex.getMessage());
        }
    }

    public void testArrayStoreException_arraycopy7() throws Exception {
        /*
         * This test demonstrates that the exception message complains
         * about the source in cases where neither source nor
         * destination is an array.
         */
        Nonce src = new Nonce();
        String dest = "blort";
        try {
            System.arraycopy(src, 0, dest, 0, 1);
        } catch (ArrayStoreException ex) {
            assertEquals("source of type libcore.java.lang.ArrayStoreExceptionTest$Nonce "
                    + "is not an array",
                    ex.getMessage());
        }
    }

    /**
     * This class is just used so that we have an example of getting a
     * message that includes an inner class.
     */
    private static class Nonce {
        // This space intentionally left blank.
    }
}
