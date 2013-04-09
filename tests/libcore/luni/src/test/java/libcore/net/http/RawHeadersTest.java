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

package libcore.net.http;

import java.util.Arrays;
import junit.framework.TestCase;

public class RawHeadersTest extends TestCase {
    // http://code.google.com/p/android/issues/detail?id=6684
    public void test_caseInsensitiveButCasePreserving() {
        RawHeaders h = new RawHeaders();
        h.add("Content-Type", "text/plain");
        // Case-insensitive:
        assertEquals("text/plain", h.get("Content-Type"));
        assertEquals("text/plain", h.get("Content-type"));
        assertEquals("text/plain", h.get("content-type"));
        assertEquals("text/plain", h.get("CONTENT-TYPE"));
        // ...but case-preserving:
        assertEquals("Content-Type", h.toMultimap().keySet().toArray()[0]);

        // We differ from the RI in that the Map we return is also case-insensitive.
        // Our behavior seems more consistent. (And code that works on the RI will work on Android.)
        assertEquals(Arrays.asList("text/plain"), h.toMultimap().get("Content-Type"));
        assertEquals(Arrays.asList("text/plain"), h.toMultimap().get("Content-type")); // RI fails this.
    }

    // The copy constructor used to be broken for headers with multiple values.
    // http://code.google.com/p/android/issues/detail?id=6722
    public void test_copyConstructor() {
        RawHeaders h1 = new RawHeaders();
        h1.add("key", "value1");
        h1.add("key", "value2");
        RawHeaders h2 = RawHeaders.fromMultimap(h1.toMultimap());
        assertEquals(2, h2.length());
        assertEquals("key", h2.getFieldName(0));
        assertEquals("value1", h2.getValue(0));
        assertEquals("key", h2.getFieldName(1));
        assertEquals("value2", h2.getValue(1));
    }
}
