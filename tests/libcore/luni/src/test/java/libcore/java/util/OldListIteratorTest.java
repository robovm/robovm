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

package libcore.java.util;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OldListIteratorTest extends TestCase {

    ListIterator<Integer> l = null;

    static Object[] objArray;
    {
        objArray = new Object[100];
        for (int i = 0; i < objArray.length; i++)
            objArray[i] = new Integer(i);
    }

    public void testHasNext() {
        for (int i = 0; i < objArray.length; i++) {
            assertTrue(l.hasNext());
            l.next();
        }
        assertFalse(l.hasNext());
    }

    public void testNext() {
        for (int i = 0; i < objArray.length; i++) {
            assertTrue(objArray[i].equals(l.next()));
        }

        try {
            l.next();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            //expected
        }
    }

    class Mock_ListIterator implements ListIterator {
        public void add(Object o) {
            if(((String) o).equals("Wrong element")) throw new IllegalArgumentException();
            if(o.getClass() == Double.class) throw new ClassCastException();
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return false;
        }

        public boolean hasPrevious() {
            return false;
        }

        public Object next() {
            return null;
        }

        public int nextIndex() {
            return 0;
        }

        public Object previous() {
            return null;
        }

        public int previousIndex() {
            return 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(Object o) {
            if(((String) o).equals("Wrong element")) throw new IllegalArgumentException();
            if(o.getClass() == Double.class) throw new ClassCastException();
            throw new UnsupportedOperationException();
        }
    }

    public void testRemove() {
        try {
            l.remove();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        for (int i = 0; i < objArray.length; i++) {
            l.next();
            l.remove();
            assertFalse(l.hasPrevious());
        }

        try {
            l.remove();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        Mock_ListIterator ml = new Mock_ListIterator();
        try {
            ml.remove();
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    public void testHasPrevious() {
        assertFalse(l.hasPrevious());
        for (int i = 0; i < objArray.length; i++) {
            l.next();
            assertTrue(l.hasPrevious());
        }
    }

    public void testPrevious() {
        try {
            l.previous();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            //expected
        }
        while(l.hasNext()) {
            l.next();
        }

        for (int i = objArray.length - 1; i > -1 ; i--) {
            assertTrue(objArray[i].equals(l.previous()));
        }

        try {
            l.previous();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            //expected
        }
    }

    public void testNextIndex() {
        for (int i = 0; i < objArray.length; i++) {
            assertTrue(objArray[i].equals(l.nextIndex()));
            l.next();
        }
    }

    public void testPreviousIndex() {
        for (int i = 0; i < objArray.length; i++) {
            assertTrue(objArray[i].equals(l.previousIndex() + 1));
            l.next();
        }
    }

    public void testSet() {
        try {
            l.set(new Integer(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        for (int i = 0; i < objArray.length; i++) {
            l.next();
            l.set((Integer)objArray[objArray.length - i - 1]);
        }

        l.remove();
        try {
            l.set(new Integer(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        Mock_ListIterator ml = new Mock_ListIterator();
        ml.next();
        try {
            ml.set("Wrong element");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            ml.set(new Double("3.14"));
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            ml.set("");
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    public void testAdd() {
        l.add(new Integer(33));

        Mock_ListIterator ml = new Mock_ListIterator();
        ml.next();
        try {
            ml.add("Wrong element");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            ml.add(new Double("3.14"));
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            ml.add("");
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        LinkedList ll = new LinkedList();
        for (int i = 0; i < objArray.length; i++) {
            ll.add(objArray[i]);
        }
        l = ll.listIterator();
    }
}
