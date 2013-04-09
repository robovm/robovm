/*
 * Copyright (C) 2008 The Android Open Source Project
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

public class OldThreadStateTest extends TestCase {

    Thread.State [] exStates = { Thread.State.NEW, Thread.State.RUNNABLE,
            Thread.State.BLOCKED, Thread.State.WAITING,
            Thread.State.TIMED_WAITING, Thread.State.TERMINATED };

    public void test_valueOfLString(){
        String [] spNames = {"NEW", "RUNNABLE", "BLOCKED", "WAITING",
                "TIMED_WAITING", "TERMINATED"};

        for(int i = 0; i < exStates.length; i++) {
            assertEquals(exStates[i], Thread.State.valueOf(spNames[i]));
        }

        String [] illegalNames = {"New", "new", "", "NAME", "TIME"};
        for(String s:illegalNames) {
            try {
                Thread.State.valueOf(s);
                fail("IllegalArgumentException was not thrown for string: "+s);
            } catch(IllegalArgumentException iae) {
                //expected
            }
        }
    }

    public void test_values() {
        Thread.State [] thStates = Thread.State.values();
        assertEquals(exStates.length, thStates.length);
        for(Thread.State ts:thStates) {
            assertTrue(isContain(ts));
        }
    }

    boolean isContain(Thread.State state) {
        for(Thread.State ts:exStates) {
            if(ts.equals(state)) return true;
        }
        return false;
    }
}
