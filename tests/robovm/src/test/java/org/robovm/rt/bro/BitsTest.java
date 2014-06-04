/*
 * Copyright (C) 2012 Trillian Mobile AB
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

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Tests {@link Bits}.
 */
public class BitsTest {

    public static final class FlagsWithZero extends Bits<FlagsWithZero> {
        public static final FlagsWithZero V0 = new FlagsWithZero(0);
        public static final FlagsWithZero V1 = new FlagsWithZero(1 << 0);
        public static final FlagsWithZero V2 = new FlagsWithZero(1 << 1);
        public static final FlagsWithZero V4 = new FlagsWithZero(1 << 2);
        public static final FlagsWithZero V8 = new FlagsWithZero(1 << 3);
        
        private static final FlagsWithZero[] VALUES = _values(FlagsWithZero.class);
        
        private FlagsWithZero(long value) { super(value); }
        private FlagsWithZero(long value, long mask) { super(value, mask); }
        protected FlagsWithZero wrap(long value, long mask) {
            return new FlagsWithZero(value, mask);
        }
        protected FlagsWithZero[] _values() {
            return VALUES;
        }
    }
    public static final class FlagsNoZero extends Bits<FlagsNoZero> {
        public static final FlagsNoZero V1 = new FlagsNoZero(1 << 0);
        public static final FlagsNoZero V2 = new FlagsNoZero(1 << 1);
        public static final FlagsNoZero V4 = new FlagsNoZero(1 << 2);
        public static final FlagsNoZero V8 = new FlagsNoZero(1 << 3);
        
        private static final FlagsNoZero[] VALUES = _values(FlagsNoZero.class);
        
        private FlagsNoZero(long value) { super(value); }
        private FlagsNoZero(long value, long mask) { super(value, mask); }
        protected FlagsNoZero wrap(long value, long mask) {
            return new FlagsNoZero(value, mask);
        }
        protected FlagsNoZero[] _values() {
            return VALUES;
        }
    }
    public static final class FlagsAndValues extends Bits<FlagsAndValues> {
        public static final FlagsAndValues V1 = new FlagsAndValues(1 << 0);
        public static final FlagsAndValues V2 = new FlagsAndValues(1 << 1);
        public static final FlagsAndValues V4 = new FlagsAndValues(1 << 2);
        public static final FlagsAndValues V8 = new FlagsAndValues(1 << 3);
        public static final FlagsAndValues O0 = new FlagsAndValues(0 << 4, 0x30);
        public static final FlagsAndValues O1 = new FlagsAndValues(1 << 4, 0x30);
        public static final FlagsAndValues O2 = new FlagsAndValues(2 << 4, 0x30);
        public static final FlagsAndValues O3 = new FlagsAndValues(3 << 4, 0x30);
        public static final FlagsAndValues OMask = new FlagsAndValues(0x30, 0x0);
        public static final FlagsAndValues Unknown = new FlagsAndValues(0xffffffffL);
        
        private static final FlagsAndValues[] VALUES = _values(FlagsAndValues.class);
        
        private FlagsAndValues(long value) { super(value); }
        private FlagsAndValues(long value, long mask) { super(value, mask); }
        protected FlagsAndValues wrap(long value, long mask) {
            return new FlagsAndValues(value, mask);
        }
        protected FlagsAndValues[] _values() {
            return VALUES;
        }
    }

    private static <T> Set<T> set(T ... ts) {
        return new TreeSet<T>(Arrays.asList(ts));
    }
    
    @Test
    public void testValues() throws Exception {
        Method m = Bits.class.getDeclaredMethod("_values", Class.class);
        m.setAccessible(true);
        FlagsWithZero[] positionValues = (FlagsWithZero[]) m.invoke(null, FlagsWithZero.class);
        assertEquals(set(FlagsWithZero.V0, FlagsWithZero.V1, FlagsWithZero.V2, FlagsWithZero.V4, FlagsWithZero.V8), set(positionValues));
    }
    
    @Test
    public void testSimpleFlagsWithZero() {
        FlagsWithZero pos = FlagsWithZero.V0;
        assertEquals(0, pos.value());
        assertEquals(set(FlagsWithZero.V0), pos.asSet());
        assertEquals("FlagsWithZero(0x0 = V0(0x0))", pos.toString());
        
        pos = pos.set(FlagsWithZero.V1).set(FlagsWithZero.V4);
        assertEquals(1 | 4, pos.value());
        assertEquals(set(FlagsWithZero.V1, FlagsWithZero.V4), pos.asSet());
        assertEquals("FlagsWithZero(0x5 = V1(0x1) | V4(0x4))", pos.toString());
        
        pos = pos.clear(FlagsWithZero.V1);
        assertEquals(4, pos.value());
        assertEquals(set(FlagsWithZero.V4), pos.asSet());
        assertEquals("FlagsWithZero(0x4 = V4(0x4))", pos.toString());
        
        pos = pos.clear(FlagsWithZero.V4);
        assertEquals(0, pos.value());
        assertEquals(set(FlagsWithZero.V0), pos.asSet());
        assertEquals("FlagsWithZero(0x0 = V0(0x0))", pos.toString());
        
        pos = pos.set(FlagsWithZero.V8);
        assertEquals(8, pos.value());
        assertEquals(set(FlagsWithZero.V8), pos.asSet());
        assertEquals("FlagsWithZero(0x8 = V8(0x8))", pos.toString());
        
        pos = pos.clear(FlagsWithZero.V4);
        assertEquals(8, pos.value());
        assertEquals(set(FlagsWithZero.V8), pos.asSet());
        assertEquals("FlagsWithZero(0x8 = V8(0x8))", pos.toString());
        
        pos = pos.clear(FlagsWithZero.V0);
        assertEquals(8, pos.value());
        assertEquals(set(FlagsWithZero.V8), pos.asSet());
        assertEquals("FlagsWithZero(0x8 = V8(0x8))", pos.toString());
        
        pos = pos.set(FlagsWithZero.V0);
        assertEquals(0, pos.value());
        assertEquals(set(FlagsWithZero.V0), pos.asSet());
        assertEquals("FlagsWithZero(0x0 = V0(0x0))", pos.toString());
    }

    @Test
    public void testFlagsNoZero() {
        FlagsNoZero pos = FlagsNoZero.V1;
        pos = pos.set(FlagsNoZero.V1).set(FlagsNoZero.V4);
        assertEquals(1 | 4, pos.value());
        assertEquals(set(FlagsNoZero.V1, FlagsNoZero.V4), pos.asSet());
        assertEquals("FlagsNoZero(0x5 = V1(0x1) | V4(0x4))", pos.toString());
        
        pos = pos.clear(FlagsNoZero.V1);
        assertEquals(4, pos.value());
        assertEquals(set(FlagsNoZero.V4), pos.asSet());
        assertEquals("FlagsNoZero(0x4 = V4(0x4))", pos.toString());
        
        pos = pos.clear(FlagsNoZero.V4);
        assertEquals(0, pos.value());
        assertEquals("FlagsNoZero(0x0 = 0x0)", pos.toString());
        
        pos = pos.set(FlagsNoZero.V8);
        assertEquals(8, pos.value());
        assertEquals(set(FlagsNoZero.V8), pos.asSet());
        assertEquals("FlagsNoZero(0x8 = V8(0x8))", pos.toString());
        
        pos = pos.clear(FlagsNoZero.V4);
        assertEquals(8, pos.value());
        assertEquals(set(FlagsNoZero.V8), pos.asSet());
        assertEquals("FlagsNoZero(0x8 = V8(0x8))", pos.toString());
    }

    @Test
    public void testFlagsAndValues() {
        FlagsAndValues pos = FlagsAndValues.V1;
        pos = pos.set(FlagsAndValues.V1).set(FlagsAndValues.V4);
        assertEquals(1 | 4, pos.value());
        assertEquals(set(FlagsAndValues.V1, FlagsAndValues.V4, FlagsAndValues.O0), pos.asSet());
        assertEquals("FlagsAndValues(0x5 = V1(0x1) | V4(0x4) | O0(0x0))", pos.toString());
        
        pos = pos.set(FlagsAndValues.O1);
        assertEquals((1 << 4) | 1 | 4, pos.value());
        assertEquals(set(FlagsAndValues.V1, FlagsAndValues.V4, FlagsAndValues.O1), pos.asSet());
        assertEquals("FlagsAndValues(0x15 = V1(0x1) | V4(0x4) | O1(0x10))", pos.toString());
        
        pos = pos.set(FlagsAndValues.O2);
        assertEquals((2 << 4) | 1 | 4, pos.value());
        assertEquals(set(FlagsAndValues.V1, FlagsAndValues.V4, FlagsAndValues.O2), pos.asSet());
        assertEquals("FlagsAndValues(0x25 = V1(0x1) | V4(0x4) | O2(0x20))", pos.toString());
        
        pos = pos.set(FlagsAndValues.Unknown);
        assertEquals(0xffffffffL, pos.value());
        assertEquals(set(FlagsAndValues.Unknown), pos.asSet());
        assertEquals("FlagsAndValues(0xffffffff = Unknown(0xffffffff))", pos.toString());
    }
}
