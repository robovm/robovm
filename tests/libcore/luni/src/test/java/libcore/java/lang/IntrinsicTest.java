/*
 * Copyright (C) 2011 The Android Open Source Project
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

public final class IntrinsicTest extends TestCase {
    public void testString_charAt() throws Exception {
        "hello".charAt(0);
        String.class.getMethod("charAt", int.class).invoke("hello", 0);
    }

    public void testString_compareTo() throws Exception {
        "hello".compareTo("world");
        String.class.getMethod("compareTo", String.class).invoke("hello", "world");
    }

    public void testString_equals() throws Exception {
        "hello".equals("world");
        String.class.getMethod("equals", Object.class).invoke("hello", "world");
    }

    public void testString_fastIndexOf_II() throws Exception {
        "hello".indexOf('l');
        String.class.getMethod("indexOf", int.class).invoke("hello", 'l');
    }

    public void testString_isEmpty() throws Exception {
        "hello".isEmpty();
        String.class.getMethod("isEmpty").invoke("hello");
    }

    public void testString_length() throws Exception {
        "hello".length();
        String.class.getMethod("length").invoke("hello");
    }

    public void testMath_abs() throws Exception {
        Math.abs(1);
        Math.class.getMethod("abs", int.class).invoke(null, 1);
        Math.abs(1L);
        Math.class.getMethod("abs", long.class).invoke(null, 1L);
        Math.abs(1.0f);
        Math.class.getMethod("abs", float.class).invoke(null, 1.0f);
        Math.abs(1.0);
        Math.class.getMethod("abs", double.class).invoke(null, 1.0);
    }

    public void testMath_min() throws Exception {
        Math.min(1, 2);
        Math.class.getMethod("min", int.class, int.class).invoke(null, 1, 2);
    }

    public void testMath_max() throws Exception {
        Math.max(1, 2);
        Math.class.getMethod("max", int.class, int.class).invoke(null, 1, 2);
    }

    public void testMath_sqrt() throws Exception {
        Math.sqrt(2.0);
        Math.class.getMethod("sqrt", double.class).invoke(null, 2.0);
    }

    public void testMath_cos() throws Exception {
        Math.cos(Math.PI);
        Math.class.getMethod("cos", double.class).invoke(null, Math.PI);
    }

    public void testMath_sin() throws Exception {
        Math.sin(Math.PI);
        Math.class.getMethod("sin", double.class).invoke(null, Math.PI);
    }

    public void testFloat_floatToIntBits() throws Exception {
        Float.floatToIntBits(0.0f);
        Float.class.getMethod("floatToIntBits", float.class).invoke(null, 0.0f);
    }

    public void testFloat_floatToRawIntBits() throws Exception {
        Float.floatToRawIntBits(0.0f);
        Float.class.getMethod("floatToRawIntBits", float.class).invoke(null, 0.0f);
    }

    public void testFloat_intBitsToFloat() throws Exception {
        Float.intBitsToFloat(0);
        Float.class.getMethod("intBitsToFloat", int.class).invoke(null, 0);
    }

    public void testDouble_doubleToLongBits() throws Exception {
        Double.doubleToLongBits(0.0);
        Double.class.getMethod("doubleToLongBits", double.class).invoke(null, 0.0);
    }

    public void testDouble_doubleToRawLongBits() throws Exception {
        Double.doubleToRawLongBits(0.0);
        Double.class.getMethod("doubleToRawLongBits", double.class).invoke(null, 0.0);
    }

    public void testDouble_longBitsToDouble() throws Exception {
        Double.longBitsToDouble(0L);
        Double.class.getMethod("longBitsToDouble", long.class).invoke(null, 0L);
    }
}
