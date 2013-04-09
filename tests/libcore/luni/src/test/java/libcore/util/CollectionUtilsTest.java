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

package libcore.util;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import junit.framework.TestCase;

public final class CollectionUtilsTest extends TestCase {

    public void testDereferenceIterable() {
        List<Reference<String>> refs = new ArrayList<Reference<String>>();
        refs.add(newLiteralReference("a"));
        refs.add(newLiteralReference("b"));
        refs.add(newLiteralReference("c"));
        refs.add(newLiteralReference("d"));
        refs.add(newLiteralReference("e"));

        Iterable<String> strings = CollectionUtils.dereferenceIterable(refs, true);
        assertEquals(Arrays.<String>asList("a", "b", "c", "d", "e"), toList(strings));

        refs.get(1).clear(); // b
        assertEquals(Arrays.<String>asList("a", "c", "d", "e"), toList(strings));
        assertEquals(4, refs.size());

        Iterator<String> i = strings.iterator();
        assertEquals("a", i.next());
        i.remove();
        assertEquals(3, refs.size());
        assertEquals("c", i.next());
        assertEquals("d", i.next());
        assertTrue(i.hasNext());
        try {
            i.remove();
            fail("Expected hasNext() to make remove() impossible.");
        } catch (IllegalStateException expected) {
        }
        assertEquals("e", i.next());
        i.remove();
        assertEquals(2, refs.size());
        assertFalse(i.hasNext());

        refs.get(0).clear(); // c
        refs.get(1).clear(); // d
        assertEquals(Arrays.<String>asList(), toList(strings));
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> result = new ArrayList<T>();
        for (T t : iterable) {
            result.add(t);
        }
        return result;
    }

    public void testRemoveDuplicatesOnEmptyCollection() {
        List<String> list = new ArrayList<String>();
        CollectionUtils.removeDuplicates(list, String.CASE_INSENSITIVE_ORDER);
        assertTrue(list.isEmpty());
    }

    public void testRemoveDuplicatesOnSingletonCollection() {
        List<String> list = Arrays.asList("A");
        CollectionUtils.removeDuplicates(list, String.CASE_INSENSITIVE_ORDER);
        assertEquals(Collections.singletonList("A"), list);
    }

    public void testRemoveDuplicates() {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("C");
        list.add("C");
        CollectionUtils.removeDuplicates(list, String.CASE_INSENSITIVE_ORDER);
        assertEquals(Arrays.asList("A", "B", "C"), list);
    }

    /**
     * A reference that must be manually cleared.
     */
    public Reference<String> newLiteralReference(String s) {
        return new WeakReference<String>(s);
    }
}
