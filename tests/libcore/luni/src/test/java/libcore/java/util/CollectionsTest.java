/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import junit.framework.TestCase;

public final class CollectionsTest extends TestCase {

    public void testEmptyEnumeration() {
        Enumeration<Object> e = Collections.emptyEnumeration();
        assertFalse(e instanceof Serializable);
        assertFalse(e.hasMoreElements());
        try {
            e.nextElement();
            fail();
        } catch (NoSuchElementException expected) {
        }
    }

    public void testEmptyIterator() {
        testEmptyIterator(Collections.emptyIterator());
        testEmptyIterator(Collections.emptyList().iterator());
        testEmptyIterator(Collections.emptySet().iterator());
        testEmptyIterator(Collections.emptyMap().keySet().iterator());
        testEmptyIterator(Collections.emptyMap().entrySet().iterator());
        testEmptyIterator(Collections.emptyMap().values().iterator());
    }

    private void testEmptyIterator(Iterator<?> i) {
        assertFalse(i instanceof Serializable);
        assertFalse(i.hasNext());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException expected) {
        }
        try {
            i.remove();
            fail();
        } catch (IllegalStateException expected) {
        }
    }

    public void testEmptyListIterator() {
        testEmptyListIterator(Collections.emptyListIterator());
        testEmptyListIterator(Collections.emptyList().listIterator());
        testEmptyListIterator(Collections.emptyList().listIterator(0));
    }

    private void testEmptyListIterator(ListIterator<?> i) {
        assertFalse(i instanceof Serializable);
        assertFalse(i.hasNext());
        assertFalse(i.hasPrevious());
        assertEquals(0, i.nextIndex());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException expected) {
        }
        assertEquals(-1, i.previousIndex());
        try {
            i.previous();
            fail();
        } catch (NoSuchElementException expected) {
        }
        try {
            i.add(null);
            fail();
        } catch (UnsupportedOperationException expected) {
        }
        try {
            i.remove();
            fail();
        } catch (IllegalStateException expected) {
        }
    }
}
