/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package libcore.java.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class OldTreeSetTest extends junit.framework.TestCase {

    TreeSet ts;

    Object objArray[] = new Object[1000];

    public void test_ConstructorLjava_util_Collection() {
        // Test for method java.util.TreeSet(java.util.Collection)
        TreeSet myTreeSet = new TreeSet(Arrays.asList(objArray));
        assertTrue("TreeSet incorrect size",
                myTreeSet.size() == objArray.length);
        for (int counter = 0; counter < objArray.length; counter++)
            assertTrue("TreeSet does not contain correct elements", myTreeSet
                    .contains(objArray[counter]));

        HashMap hm = new HashMap();
        hm.put("First", new Integer(1));
        hm.put(new Integer(2), "two");

        try {
            new TreeSet(hm.values());
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            new TreeSet((Collection)null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    public void test_ConstructorLjava_util_SortedSet() {
        try {
            new TreeSet((SortedSet)null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    public void test_addLjava_lang_Object() {
        // Test for method boolean java.util.TreeSet.add(java.lang.Object)
        ts.add(new Integer(-8));
        assertTrue("Failed to add Object", ts.contains(new Integer(-8)));
        ts.add(objArray[0]);
        assertTrue("Added existing element", ts.size() == objArray.length + 1);

        HashMap hm = new HashMap();
        hm.put("First", new Integer(1));
        hm.put(new Integer(2), "two");

        try {
            ts.add("Wrong element");
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }
    }

    public void test_addAllLjava_util_Collection() {
        // Test for method boolean
        // java.util.TreeSet.addAll(java.util.Collection)
        TreeSet s = new TreeSet();
        s.addAll(ts);
        assertTrue("Incorrect size after add", s.size() == ts.size());
        Iterator i = ts.iterator();
        while (i.hasNext())
            assertTrue("Returned incorrect set", s.contains(i.next()));

        HashMap hm = new HashMap();
        hm.put("First", new Integer(1));
        hm.put(new Integer(2), "two");

        try {
            s.addAll(hm.values());
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            s.addAll(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    public void test_first() {
        // Test for method java.lang.Object java.util.TreeSet.first()
        assertTrue("Returned incorrect first element",
                ts.first() == objArray[0]);

        ts = new TreeSet();
        try {
            ts.first();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            //expected
        }
    }

    public void test_headSetLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.TreeSet.headSet(java.lang.Object)
        Set s = ts.headSet(new Integer(100));
        assertEquals("Returned set of incorrect size", 100, s.size());
        for (int i = 0; i < 100; i++)
            assertTrue("Returned incorrect set", s.contains(objArray[i]));

        SortedSet sort = ts.headSet(new Integer(100));
        try {
            sort.headSet(new Integer(101));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            ts.headSet(this);
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            ts.headSet(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    public void test_last() {
        // Test for method java.lang.Object java.util.TreeSet.last()
        assertTrue("Returned incorrect last element",
                ts.last() == objArray[objArray.length - 1]);

        ts = new TreeSet();
        try {
            ts.last();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            //expected
        }
    }

    public void test_subSetLjava_lang_ObjectLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.TreeSet.subSet(java.lang.Object, java.lang.Object)
        final int startPos = objArray.length / 4;
        final int endPos = 3 * objArray.length / 4;
        SortedSet aSubSet = ts.subSet(objArray[startPos], objArray[endPos]);
        assertTrue("Subset has wrong number of elements",
                aSubSet.size() == (endPos - startPos));
        for (int counter = startPos; counter < endPos; counter++)
            assertTrue("Subset does not contain all the elements it should",
                    aSubSet.contains(objArray[counter]));

        try {
            ts.subSet(objArray[3], objArray[0]);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            ts.subSet(null, objArray[3]);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        try {
            ts.subSet(objArray[3], null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        try {
            ts.subSet(objArray[3], this);
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }
    }

    public void test_tailSetLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.TreeSet.tailSet(java.lang.Object)
        Set s = ts.tailSet(new Integer(900));
        assertEquals("Returned set of incorrect size", 100, s.size());
        for (int i = 900; i < objArray.length; i++)
            assertTrue("Returned incorrect set", s.contains(objArray[i]));

        SortedSet sort = ts.tailSet(new Integer(101));

        try {
            sort.tailSet(new Integer(100));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            ts.tailSet(this);
            fail("ClassCastException expected");
        } catch (ClassCastException e) {
            //expected
        }

        try {
            ts.tailSet(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        ts = new TreeSet();
        for (int i = 0; i < objArray.length; i++) {
            Object x = objArray[i] = new Integer(i);
            ts.add(x);
        }
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
    }
}
