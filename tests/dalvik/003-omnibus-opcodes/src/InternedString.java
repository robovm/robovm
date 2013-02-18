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

import java.lang.ref.*;

public class InternedString {
    public static final String CONST = "Class InternedString";

    public static void run() {
        System.out.println("InternedString.run");
        testImmortalInternedString();
        testDeadInternedString();
    }

    private static void testDeadInternedString() {
        String s = "blah";
        s = s + s;
        WeakReference strRef = new WeakReference<String>(s.intern());
        // Kill s, otherwise the string object is still accessible from root set
        s = CONST;
        System.gc();
        // "blahblah" should disappear from the intern list
        assert(strRef.get() == null);
    }

    private static void testImmortalInternedString() {
        WeakReference strRef = new WeakReference<String>(CONST.intern());
        System.gc();
        // Class constant string should be entered to the interned table when
        // loaded
        assert(CONST == CONST.intern());
        // and it should survive the gc
        assert(strRef.get() != null);

        String s = CONST;
        // "Class InternedString" should remain on the intern list
        strRef = new WeakReference<String>(s.intern());
        // Kill s, otherwise the string object is still accessible from root set
        s = "";
        System.gc();
        assert(strRef.get() == CONST);
    }
}
