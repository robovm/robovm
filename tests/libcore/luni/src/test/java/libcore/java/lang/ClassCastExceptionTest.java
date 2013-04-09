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

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.EnumMap;
import java.util.EnumSet;
import junit.framework.TestCase;

public final class ClassCastExceptionTest extends TestCase {
    public void testCast() throws Exception {
        Object o = new Exception();
        try {
            String s = (String) o;
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.Exception cannot be cast to java.lang.String", ex.getMessage());
        }
    }

    public void testClassCast() throws Exception {
        Object o = new Exception();
        try {
            String.class.cast(o);
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.Exception cannot be cast to java.lang.String", ex.getMessage());
        }
    }

    public void testClassAsSubclass() throws Exception {
        try {
            Exception.class.asSubclass(String.class);
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.Exception cannot be cast to java.lang.String",
                    ex.getMessage());
        }
    }

    public void testCastOperator() throws Exception {
        try {
            Object o = (InputStream) makeInteger();
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.Integer cannot be cast to java.io.InputStream",
                    ex.getMessage());
        }
    }

    public void testCastOperatorWithArrays() throws Exception {
        try {
            Object o = (E) makeArray(String.class);
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.String[] cannot be cast to "
                    + "libcore.java.lang.ClassCastExceptionTest$E",
                    ex.getMessage());
        }

        try {
            Object o = (E) makeArray(float.class);
            fail();
        } catch (ClassCastException ex) {
            assertEquals("float[] cannot be cast to libcore.java.lang.ClassCastExceptionTest$E",
                    ex.getMessage());
        }

        try {
            Object o = (E) makeArray(char[].class);
            fail();
        } catch (ClassCastException ex) {
            assertEquals("char[][] cannot be cast to libcore.java.lang.ClassCastExceptionTest$E",
                    ex.getMessage());
        }

        try {
            Object o = (Object[][][]) makeInteger();
            fail();
        } catch (ClassCastException ex) {
            assertEquals("java.lang.Integer cannot be cast to java.lang.Object[][][]",
                    ex.getMessage());
        }
    }

    /**
     * Helper for {@link #testCastOperator} and {@link
     * #testCastOperatorWithArrays}, above. It's important that the
     * return type is {@code Object}, since otherwise the compiler
     * will just reject the code.
     */
    private static Object makeInteger() {
        return new Integer(5);
    }

    /**
     * Helper for {@link #testCastOperatorWithArrays} above. It's important that
     * the return type is {@code Object}.
     */
    private static Object makeArray(Class clazz) {
        return Array.newInstance(clazz, 1);
    }

    enum E { A, B, C };
    enum F { A, B, C };

    public void testEnumMapPut() throws Exception {
        EnumMap m = new EnumMap(E.class);
        try {
            m.put(F.A, "world");
            fail();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            assertNotNull(ex.getMessage());
        }
    }

    public void testMiniEnumSetAdd() throws Exception {
        EnumSet m = EnumSet.noneOf(E.class);
        try {
            m.add(F.A);
            fail();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            assertNotNull(ex.getMessage());
        }
    }

    public void testMiniEnumSetAddAll() throws Exception {
        EnumSet m = EnumSet.noneOf(E.class);
        EnumSet n = EnumSet.allOf(F.class);
        try {
            m.addAll(n);
            fail();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            assertNotNull(ex.getMessage());
        }
    }

    enum HugeE {
        A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0, Z0,
        A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1, U1, V1, W1, X1, Y1, Z1,
        A2, B2, C2, D2, E2, F2, G2, H2, I2, J2, K2, L2, M2, N2, O2, P2, Q2, R2, S2, T2, U2, V2, W2, X2, Y2, Z2,
    };
    enum HugeF {
        A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0, Z0,
        A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1, U1, V1, W1, X1, Y1, Z1,
        A2, B2, C2, D2, E2, F2, G2, H2, I2, J2, K2, L2, M2, N2, O2, P2, Q2, R2, S2, T2, U2, V2, W2, X2, Y2, Z2,
    };

    public void testHugeEnumSetAdd() throws Exception {
        EnumSet m = EnumSet.noneOf(HugeE.class);
        try {
            m.add(HugeF.A0);
            fail();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            assertNotNull(ex.getMessage());
        }
    }

    public void testHugeEnumSetAddAll() throws Exception {
        EnumSet m = EnumSet.noneOf(HugeE.class);
        EnumSet n = EnumSet.allOf(HugeF.class);
        try {
            m.addAll(n);
            fail();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            assertNotNull(ex.getMessage());
        }
    }
}
