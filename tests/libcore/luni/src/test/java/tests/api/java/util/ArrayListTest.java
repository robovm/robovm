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
package tests.api.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ConcurrentModificationException;
import java.util.Vector;

import tests.support.Support_ListTest;

public class ArrayListTest extends junit.framework.TestCase {

    List alist;

    Object[] objArray;

    /**
     * java.util.ArrayList#ArrayList()
     */
    public void test_Constructor() {
        // Test for method java.util.ArrayList()
        new Support_ListTest("", alist).runTest();

        ArrayList subList = new ArrayList();
        for (int i = -50; i < 150; i++)
            subList.add(new Integer(i));
        new Support_ListTest("", subList.subList(50, 150)).runTest();
    }

    /**
     * java.util.ArrayList#ArrayList(int)
     */
    public void test_ConstructorI() {
        // Test for method java.util.ArrayList(int)
        ArrayList al = new ArrayList(5);
        assertEquals("Incorrect arrayList created", 0, al.size());

        try {
            new ArrayList(-10);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    /**
     * java.util.ArrayList#ArrayList(java.util.Collection)
     */
    public void test_ConstructorLjava_util_Collection() {
        // Test for method java.util.ArrayList(java.util.Collection)
        ArrayList al = new ArrayList(Arrays.asList(objArray));
        assertTrue("arrayList created from collection has incorrect size", al
                .size() == objArray.length);
        for (int counter = 0; counter < objArray.length; counter++)
            assertTrue(
                    "arrayList created from collection has incorrect elements",
                    al.get(counter) == objArray[counter]);
        try {
            new ArrayList(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

    }

    /**
     * java.util.ArrayList#add(int, java.lang.Object)
     */
    public void test_addILjava_lang_Object() {
        // Test for method void java.util.ArrayList.add(int, java.lang.Object)
        Object o;
        alist.add(50, o = new Object());
        assertTrue("Failed to add Object", alist.get(50) == o);
        assertTrue("Failed to fix up list after insert",
                alist.get(51) == objArray[50]
                        && (alist.get(52) == objArray[51]));
        Object oldItem = alist.get(25);
        alist.add(25, null);
        assertNull("Should have returned null", alist.get(25));
        assertTrue("Should have returned the old item from slot 25", alist
                .get(26) == oldItem);

        try {
            alist.add(-1, null);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            alist.add(alist.size() + 1, null);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }
    }

    /**
     * java.util.ArrayList#add(java.lang.Object)
     */
    public void test_addLjava_lang_Object() {
        // Test for method boolean java.util.ArrayList.add(java.lang.Object)
        Object o = new Object();
        alist.add(o);
        assertTrue("Failed to add Object", alist.get(alist.size() - 1) == o);
        alist.add(null);
        assertNull("Failed to add null", alist.get(alist.size() - 1));
    }

    /**
     * java.util.ArrayList#addAll(int, java.util.Collection)
     */
    public void test_addAllILjava_util_Collection() {
        // Test for method boolean java.util.ArrayList.addAll(int,
        // java.util.Collection)
        alist.addAll(50, alist);
        assertEquals("Returned incorrect size after adding to existing list",
                200, alist.size());
        for (int i = 0; i < 50; i++)
            assertTrue("Manipulated elements < index",
                    alist.get(i) == objArray[i]);
        for (int i = 0; i >= 50 && (i < 150); i++)
            assertTrue("Failed to ad elements properly",
                    alist.get(i) == objArray[i - 50]);
        for (int i = 0; i >= 150 && (i < 200); i++)
            assertTrue("Failed to ad elements properly",
                    alist.get(i) == objArray[i - 100]);
        ArrayList listWithNulls = new ArrayList();
        listWithNulls.add(null);
        listWithNulls.add(null);
        listWithNulls.add("yoink");
        listWithNulls.add("kazoo");
        listWithNulls.add(null);
        alist.addAll(100, listWithNulls);
        assertTrue("Incorrect size: " + alist.size(), alist.size() == 205);
        assertNull("Item at slot 100 should be null", alist.get(100));
        assertNull("Item at slot 101 should be null", alist.get(101));
        assertEquals("Item at slot 102 should be 'yoink'",
                "yoink", alist.get(102));
        assertEquals("Item at slot 103 should be 'kazoo'",
                "kazoo", alist.get(103));
        assertNull("Item at slot 104 should be null", alist.get(104));
        alist.addAll(205, listWithNulls);
        assertTrue("Incorrect size2: " + alist.size(), alist.size() == 210);

        try {
            alist.addAll(-1, listWithNulls);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            alist.addAll(alist.size() + 1, listWithNulls);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            alist.addAll(0, null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

// BEGIN android-removed
// The spec does not mandate that IndexOutOfBoundsException be thrown in
// preference to NullPointerException when the caller desserves both.
//
//    /**
//     * java.util.ArrayList#addAll(int, java.util.Collection)
//     */
//    public void test_addAllILjava_util_Collection_2() {
//        // Regression for HARMONY-467
//        ArrayList obj = new ArrayList();
//        try {
//            obj.addAll((int) -1, (Collection) null);
//            fail("IndexOutOfBoundsException expected");
//        } catch (IndexOutOfBoundsException e) {
//        }
//    }
// END android-removed

    /**
     * java.util.ArrayList#addAll(java.util.Collection)
     */
    public void test_addAllLjava_util_Collection() {
        // Test for method boolean
        // java.util.ArrayList.addAll(java.util.Collection)
        List l = new ArrayList();
        l.addAll(alist);
        for (int i = 0; i < alist.size(); i++)
            assertTrue("Failed to add elements properly", l.get(i).equals(
                    alist.get(i)));
        alist.addAll(alist);
        assertEquals("Returned incorrect size after adding to existing list",
                200, alist.size());
        for (int i = 0; i < 100; i++) {
            assertTrue("Added to list in incorrect order", alist.get(i)
                    .equals(l.get(i)));
            assertTrue("Failed to add to existing list", alist.get(i + 100)
                    .equals(l.get(i)));
        }
        Set setWithNulls = new HashSet();
        setWithNulls.add(null);
        setWithNulls.add(null);
        setWithNulls.add("yoink");
        setWithNulls.add("kazoo");
        setWithNulls.add(null);
        alist.addAll(100, setWithNulls);
        Iterator i = setWithNulls.iterator();
        assertTrue("Item at slot 100 is wrong: " + alist.get(100), alist
                .get(100) == i.next());
        assertTrue("Item at slot 101 is wrong: " + alist.get(101), alist
                .get(101) == i.next());
        assertTrue("Item at slot 103 is wrong: " + alist.get(102), alist
                .get(102) == i.next());


        // Regression test for Harmony-3481
        ArrayList<Integer> originalList = new ArrayList<Integer>(12);
        for (int j = 0; j < 12; j++) {
            originalList.add(j);
        }

        originalList.remove(0);
        originalList.remove(0);

        ArrayList<Integer> additionalList = new ArrayList<Integer>(11);
        for (int j = 0; j < 11; j++) {
            additionalList.add(j);
        }
        assertTrue(originalList.addAll(additionalList));
        assertEquals(21, originalList.size());

        try {
            alist.addAll(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * java.util.ArrayList#clear()
     */
    public void test_clear() {
        // Test for method void java.util.ArrayList.clear()
        alist.clear();
        assertEquals("List did not clear", 0, alist.size());
        alist.add(null);
        alist.add(null);
        alist.add(null);
        alist.add("bam");
        alist.clear();
        assertEquals("List with nulls did not clear", 0, alist.size());
        /*
         * for (int i = 0; i < alist.size(); i++) assertNull("Failed to clear
         * list", alist.get(i));
         */

    }

    /**
     * java.util.ArrayList#clone()
     */
    public void test_clone() {
        // Test for method java.lang.Object java.util.ArrayList.clone()
        ArrayList x = (ArrayList) (((ArrayList) (alist)).clone());
        assertTrue("Cloned list was inequal to original", x.equals(alist));
        for (int i = 0; i < alist.size(); i++)
            assertTrue("Cloned list contains incorrect elements",
                    alist.get(i) == x.get(i));

        alist.add(null);
        alist.add(25, null);
        x = (ArrayList) (((ArrayList) (alist)).clone());
        assertTrue("nulls test - Cloned list was inequal to original", x
                .equals(alist));
        for (int i = 0; i < alist.size(); i++)
            assertTrue("nulls test - Cloned list contains incorrect elements",
                    alist.get(i) == x.get(i));

    }

    /**
     * java.util.ArrayList#contains(java.lang.Object)
     */
    public void test_containsLjava_lang_Object() {
        // Test for method boolean
        // java.util.ArrayList.contains(java.lang.Object)
        assertTrue("Returned false for valid element", alist
                .contains(objArray[99]));
        assertTrue("Returned false for equal element", alist
                .contains(new Integer(8)));
        assertTrue("Returned true for invalid element", !alist
                .contains(new Object()));
        assertTrue("Returned true for null but should have returned false",
                !alist.contains(null));
        alist.add(null);
        assertTrue("Returned false for null but should have returned true",
                alist.contains(null));
    }

    /**
     * java.util.ArrayList#ensureCapacity(int)
     */
    public void test_ensureCapacityI() {
        // Test for method void java.util.ArrayList.ensureCapacity(int)
        // TODO : There is no good way to test this as it only really impacts on
        // the private implementation.

        Object testObject = new Object();
        int capacity = 20;
        ArrayList al = new ArrayList(capacity);
        int i;
        for (i = 0; i < capacity / 2; i++) {
            al.add(i, new Object());
        }
        al.add(i, testObject);
        int location = al.indexOf(testObject);
        try {
            al.ensureCapacity(capacity);
            assertTrue("EnsureCapacity moved objects around in array1.",
                    location == al.indexOf(testObject));
            al.remove(0);
            al.ensureCapacity(capacity);
            assertTrue("EnsureCapacity moved objects around in array2.",
                    --location == al.indexOf(testObject));
            al.ensureCapacity(capacity + 2);
            assertTrue("EnsureCapacity did not change location.",
                    location == al.indexOf(testObject));
        } catch (Exception e) {
            fail("Exception during test : " + e.getMessage());
        }
    }

    /**
     * java.util.ArrayList#get(int)
     */
    public void test_getI() {
        // Test for method java.lang.Object java.util.ArrayList.get(int)
        assertTrue("Returned incorrect element", alist.get(22) == objArray[22]);
        try {
            alist.get(8765);
            fail("Failed to throw expected exception for index > size");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * java.util.ArrayList#indexOf(java.lang.Object)
     */
    public void test_indexOfLjava_lang_Object() {
        // Test for method int java.util.ArrayList.indexOf(java.lang.Object)
        assertEquals("Returned incorrect index",
                87, alist.indexOf(objArray[87]));
        assertEquals("Returned index for invalid Object", -1, alist
                .indexOf(new Object()));
        alist.add(25, null);
        alist.add(50, null);
        assertTrue("Wrong indexOf for null.  Wanted 25 got: "
                + alist.indexOf(null), alist.indexOf(null) == 25);
    }

    /**
     * java.util.ArrayList#isEmpty()
     */
    public void test_isEmpty() {
        // Test for method boolean java.util.ArrayList.isEmpty()
        assertTrue("isEmpty returned false for new list", new ArrayList()
                .isEmpty());
        assertTrue("Returned true for existing list with elements", !alist
                .isEmpty());
    }

    /**
     * java.util.ArrayList#lastIndexOf(java.lang.Object)
     */
    public void test_lastIndexOfLjava_lang_Object() {
        // Test for method int java.util.ArrayList.lastIndexOf(java.lang.Object)
        alist.add(new Integer(99));
        assertEquals("Returned incorrect index",
                100, alist.lastIndexOf(objArray[99]));
        assertEquals("Returned index for invalid Object", -1, alist
                .lastIndexOf(new Object()));
        alist.add(25, null);
        alist.add(50, null);
        assertTrue("Wrong lastIndexOf for null.  Wanted 50 got: "
                + alist.lastIndexOf(null), alist.lastIndexOf(null) == 50);
    }

    /**
     * java.util.ArrayList#remove(int)
     */
    public void test_removeI() {
        // Test for method java.lang.Object java.util.ArrayList.remove(int)
        alist.remove(10);
        assertEquals("Failed to remove element",
                -1, alist.indexOf(objArray[10]));
        try {
            alist.remove(999);
            fail("Failed to throw exception when index out of range");
        } catch (IndexOutOfBoundsException e) {
        }

        ArrayList myList = (ArrayList) (((ArrayList) (alist)).clone());
        alist.add(25, null);
        alist.add(50, null);
        alist.remove(50);
        alist.remove(25);
        assertTrue("Removing nulls did not work", alist.equals(myList));

        List list = new ArrayList(Arrays.asList(new String[] { "a", "b", "c",
                "d", "e", "f", "g" }));
        assertTrue("Removed wrong element 1", list.remove(0) == "a");
        assertTrue("Removed wrong element 2", list.remove(4) == "f");
        String[] result = new String[5];
        list.toArray(result);
        assertTrue("Removed wrong element 3", Arrays.equals(result,
                new String[] { "b", "c", "d", "e", "g" }));

        List l = new ArrayList(0);
        l.add(new Object());
        l.add(new Object());
        l.remove(0);
        l.remove(0);
        try {
            l.remove(-1);
            fail("-1 should cause exception");
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            l.remove(0);
            fail("0 should case exception");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * java.util.ArrayList#set(int, java.lang.Object)
     */
    public void test_setILjava_lang_Object() {
        // Test for method java.lang.Object java.util.ArrayList.set(int,
        // java.lang.Object)
        Object obj;
        alist.set(65, obj = new Object());
        assertTrue("Failed to set object", alist.get(65) == obj);
        alist.set(50, null);
        assertNull("Setting to null did not work", alist.get(50));
        assertTrue("Setting increased the list's size to: " + alist.size(),
                alist.size() == 100);

        try {
            alist.set(-1, null);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            alist.set(alist.size() + 1, null);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }
    }

    /**
     * java.util.ArrayList#size()
     */
    public void test_size() {
        // Test for method int java.util.ArrayList.size()
        assertEquals("Returned incorrect size for exiting list",
                100, alist.size());
        assertEquals("Returned incorrect size for new list", 0, new ArrayList()
                .size());
    }

    /**
     * java.util.ArrayList#toArray()
     */
    public void test_toArray() {
        // Test for method java.lang.Object [] java.util.ArrayList.toArray()
        alist.set(25, null);
        alist.set(75, null);
        Object[] obj = alist.toArray();
        assertEquals("Returned array of incorrect size", objArray.length,
                obj.length);

        for (int i = 0; i < obj.length; i++) {
            if ((i == 25) || (i == 75))
                assertNull("Should be null at: " + i + " but instead got: "
                        + obj[i], obj[i]);
            else
                assertTrue("Returned incorrect array: " + i,
                        obj[i] == objArray[i]);
        }

    }

    /**
     * java.util.ArrayList#toArray(java.lang.Object[])
     */
    public void test_toArray$Ljava_lang_Object() {
        // Test for method java.lang.Object []
        // java.util.ArrayList.toArray(java.lang.Object [])
        alist.set(25, null);
        alist.set(75, null);
        Integer[] argArray = new Integer[100];
        Object[] retArray;
        retArray = alist.toArray(argArray);
        assertTrue("Returned different array than passed", retArray == argArray);
        argArray = new Integer[1000];
        retArray = alist.toArray(argArray);
        assertNull("Failed to set first extra element to null", argArray[alist
                .size()]);
        for (int i = 0; i < 100; i++) {
            if ((i == 25) || (i == 75))
                assertNull("Should be null: " + i, retArray[i]);
            else
                assertTrue("Returned incorrect array: " + i,
                        retArray[i] == objArray[i]);
        }

        String[] strArray = new String[100];
        try {
            alist.toArray(strArray);
            fail("ArrayStoreException expected");
        } catch (ArrayStoreException e) {
            //expected
        }
    }

    /**
     * java.util.ArrayList#trimToSize()
     */
    public void test_trimToSize_01() {
        // Test for method void java.util.ArrayList.trimToSize()
        for (int i = 99; i > 24; i--)
            alist.remove(i);
        ((ArrayList) alist).trimToSize();
        assertEquals("Returned incorrect size after trim", 25, alist.size());
        for (int i = 0; i < alist.size(); i++)
            assertTrue("Trimmed list contained incorrect elements", alist
                    .get(i) == objArray[i]);
        Vector v = new Vector();
        v.add("a");
    v.add("b");
        ArrayList al = new ArrayList(v);
        Iterator it = al.iterator();
    al.remove(0);
        al.trimToSize();
        try {
            it.next();
            fail("should throw a ConcurrentModificationException");
        } catch (ConcurrentModificationException ioobe) {
            // expected
        }
    }

    public void test_trimToSize_02() {
        ArrayList list = new ArrayList(Arrays.asList(new String[] { "a", "b", "c",
                "d", "e", "f", "g" }));
        list.remove("a");
        list.remove("f");
        list.trimToSize();
    }

    /**
     * @test java.util.ArrayList#addAll(int, Collection)
     */
    public void test_addAll() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add("two");
        assertEquals(2, list.size());

        list.remove(0);
        assertEquals(1, list.size());

        ArrayList collection = new ArrayList();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        assertEquals(3, collection.size());

        list.addAll(0, collection);
        assertEquals(4, list.size());

        list.remove(0);
        list.remove(0);
        assertEquals(2, list.size());

        collection.add("4");
        collection.add("5");
        collection.add("6");
        collection.add("7");
        collection.add("8");
        collection.add("9");
        collection.add("10");
        collection.add("11");
        collection.add("12");

        assertEquals(12, collection.size());

        list.addAll(0, collection);
        assertEquals(14, list.size());
    }

    public void test_removeLjava_lang_Object() {
        List list = new ArrayList(Arrays.asList(new String[] { "a", "b", "c",
                "d", "e", "f", "g" }));
        assertTrue("Removed wrong element 1", list.remove("a"));
        assertTrue("Removed wrong element 2", list.remove("f"));
        String[] result = new String[5];
        list.toArray(result);
        assertTrue("Removed wrong element 3", Arrays.equals(result,
                new String[] { "b", "c", "d", "e", "g" }));
    }

    class Mock_ArrayList extends ArrayList {
        public Mock_ArrayList() {
        }

        public void removeRange(int begin, int end) {
            super.removeRange(begin, end);
        }
    }

    public void test_removeRangeII() {
        Mock_ArrayList mal = new Mock_ArrayList();
        mal.add("a");
        mal.add("b");
        mal.add("c");
        mal.add("d");
        mal.add("e");
        mal.add("f");
        mal.add("g");
        mal.add("h");

        mal.removeRange(2, 4);

        String[] result = new String[6];
        mal.toArray(result);
        assertTrue("Removed wrong element 3", Arrays.equals(result,
                new String[] { "a", "b", "e", "f", "g", "h"}));
    }


    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() throws Exception {
        super.setUp();

        objArray = new Object[100];
        for (int i = 0; i < objArray.length; i++) {
            objArray[i] = new Integer(i);
        }

        alist = new ArrayList();
        for (int i = 0; i < objArray.length; i++) {
            alist.add(objArray[i]);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        objArray = null;
        alist = null;

        super.tearDown();
    }
}
