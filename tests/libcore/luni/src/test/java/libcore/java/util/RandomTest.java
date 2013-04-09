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

package libcore.java.util;

import java.util.Random;

public class RandomTest extends junit.framework.TestCase {
    public void test_subclassing() throws Exception {
        // http://b/2502231
        // Ensure that Random's constructors call setSeed by emulating the active ingredient
        // from the bug: the subclass' setSeed had a side-effect necessary for the correct
        // functioning of next.
        class MyRandom extends Random {
            public String state;
            public MyRandom() { super(); }
            public MyRandom(long l) { super(l); }
            @Override protected synchronized int next(int bits) { return state.length(); }
            @Override public synchronized void setSeed(long seed) { state = Long.toString(seed); }
        }
        // Test the 0-argument constructor...
        MyRandom r1 = new MyRandom();
        r1.nextInt();
        assertNotNull(r1.state);
        // Test the 1-argument constructor...
        MyRandom r2 = new MyRandom(123L);
        r2.nextInt();
        assertNotNull(r2.state);
    }
}
