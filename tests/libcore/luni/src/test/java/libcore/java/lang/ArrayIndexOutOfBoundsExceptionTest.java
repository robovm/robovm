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

public final class ArrayIndexOutOfBoundsExceptionTest extends TestCase {
    public void testAput() throws Exception {
        byte[] bs = new byte[1];
        try {
            bs[2] = 0;
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }

    public void testAget() throws Exception {
        byte[] bs = new byte[1];
        try {
            byte b = bs[2];
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }

    public void testAputWide() throws Exception {
        double[] ds = new double[1];
        try {
            ds[2] = 0.0;
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }

    public void testAgetWide() throws Exception {
        double[] ds = new double[1];
        try {
            double d = ds[2];
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }

    public void testAputObject() throws Exception {
        Object[] os = new Object[1];
        try {
            os[2] = null;
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }

    public void testAgetObject() throws Exception {
        Object[] os = new Object[1];
        try {
            Object o = os[2];
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("length=1; index=2", ex.getMessage());
        }
    }
}
