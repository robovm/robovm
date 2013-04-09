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

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import tests.support.Support_MapTest2;

import org.apache.harmony.testframework.serialization.SerializationTest;

public class IdentityHashMapTest extends junit.framework.TestCase {
    private static final String ID = "hello";

    class MockMap extends AbstractMap {
        public Set entrySet() {
            return null;
        }
        public int size(){
            return 0;
        }
    }
    /*
     * TODO: change all the statements testing the keys and values with equals()
     * method to check for reference equality instead
     */

    IdentityHashMap hm;

    final static int hmSize = 1000;

    Object[] objArray;

    Object[] objArray2;

    /**
     * java.util.IdentityHashMap#IdentityHashMap()
     */
    public void test_Constructor() {
        // Test for method java.util.IdentityHashMap()
        new Support_MapTest2(new IdentityHashMap()).runTest();

        IdentityHashMap hm2 = new IdentityHashMap();
        assertEquals("Created incorrect IdentityHashMap", 0, hm2.size());
    }

    /**
     * java.util.IdentityHashMap#IdentityHashMap(int)
     */
    public void test_ConstructorI() {
        // Test for method java.util.IdentityHashMap(int)
        IdentityHashMap hm2 = new IdentityHashMap(5);
        assertEquals("Created incorrect IdentityHashMap", 0, hm2.size());
        try {
            new IdentityHashMap(-1);
            fail("Failed to throw IllegalArgumentException for initial capacity < 0");
        } catch (IllegalArgumentException e) {
            //expected
        }

        IdentityHashMap empty = new IdentityHashMap(0);
        assertNull("Empty IdentityHashMap access", empty.get("nothing"));
        empty.put("something", "here");
        assertTrue("cannot get element", empty.get("something") == "here");
    }

    /**
     * java.util.IdentityHashMap#IdentityHashMap(java.util.Map)
     */
    public void test_ConstructorLjava_util_Map() {
        // Test for method java.util.IdentityHashMap(java.util.Map)
        Map myMap = new TreeMap();
        for (int counter = 0; counter < hmSize; counter++)
            myMap.put(objArray2[counter], objArray[counter]);
        IdentityHashMap hm2 = new IdentityHashMap(myMap);
        for (int counter = 0; counter < hmSize; counter++)
            assertTrue("Failed to construct correct IdentityHashMap", hm
                    .get(objArray2[counter]) == hm2.get(objArray2[counter]));

        Map mockMap = new MockMap();
        hm2 = new IdentityHashMap(mockMap);
        assertEquals("Size should be 0", 0, hm2.size());

        try {
            new IdentityHashMap(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * java.util.IdentityHashMap#clear()
     */
    public void test_clear() {
        // Test for method void java.util.IdentityHashMap.clear()
        hm.clear();
        assertEquals("Clear failed to reset size", 0, hm.size());
        for (int i = 0; i < hmSize; i++)
            assertNull("Failed to clear all elements",
                    hm.get(objArray2[i]));

    }

    /**
     * java.util.IdentityHashMap#clone()
     */
    public void test_clone() {
        // Test for method java.lang.Object java.util.IdentityHashMap.clone()
        IdentityHashMap hm2 = (IdentityHashMap) hm.clone();
        assertTrue("Clone answered equivalent IdentityHashMap", hm2 != hm);
        for (int counter = 0; counter < hmSize; counter++)
            assertTrue("Clone answered unequal IdentityHashMap", hm
                    .get(objArray2[counter]) == hm2.get(objArray2[counter]));

        IdentityHashMap map = new IdentityHashMap();
        map.put("key", "value");
        // get the keySet() and values() on the original Map
        Set keys = map.keySet();
        Collection values = map.values();
        assertEquals("values() does not work",
                "value", values.iterator().next());
        assertEquals("keySet() does not work",
                "key", keys.iterator().next());
        AbstractMap map2 = (AbstractMap) map.clone();
        map2.put("key", "value2");
        Collection values2 = map2.values();
        assertTrue("values() is identical", values2 != values);
        // values() and keySet() on the cloned() map should be different
        assertEquals("values() was not cloned",
                "value2", values2.iterator().next());
        map2.clear();
        map2.put("key2", "value3");
        Set key2 = map2.keySet();
        assertTrue("keySet() is identical", key2 != keys);
        assertEquals("keySet() was not cloned",
                "key2", key2.iterator().next());
    }

    /**
     * java.util.IdentityHashMap#containsKey(java.lang.Object)
     */
    public void test_containsKeyLjava_lang_Object() {
        // Test for method boolean
        // java.util.IdentityHashMap.containsKey(java.lang.Object)
        assertTrue("Returned false for valid key", hm
                .containsKey(objArray2[23]));
        assertTrue("Returned true for copy of valid key", !hm
                .containsKey(new Integer(23).toString()));
        assertTrue("Returned true for invalid key", !hm.containsKey("KKDKDKD"));

        IdentityHashMap m = new IdentityHashMap();
        m.put(null, "test");
        assertTrue("Failed with null key", m.containsKey(null));
        assertTrue("Failed with missing key matching null hash", !m
                .containsKey(new Integer(0)));
    }

    /**
     * java.util.IdentityHashMap#containsValue(java.lang.Object)
     */
    public void test_containsValueLjava_lang_Object() {
        // Test for method boolean
        // java.util.IdentityHashMap.containsValue(java.lang.Object)
        assertTrue("Returned false for valid value", hm
                .containsValue(objArray[19]));
        assertTrue("Returned true for invalid valie", !hm
                .containsValue(new Integer(-9)));
    }

    /**
     * java.util.IdentityHashMap#entrySet()
     */
    public void test_entrySet() {
        // Test for method java.util.Set java.util.IdentityHashMap.entrySet()
        Set s = hm.entrySet();
        Iterator i = s.iterator();
        assertTrue("Returned set of incorrect size", hm.size() == s.size());
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            assertTrue("Returned incorrect entry set", hm.containsKey(m
                    .getKey())
                    && hm.containsValue(m.getValue()));
        }
    }

    /**
     * java.util.IdentityHashMap#get(java.lang.Object)
     */
    public void test_getLjava_lang_Object() {
        // Test for method java.lang.Object
        // java.util.IdentityHashMap.get(java.lang.Object)
        assertNull("Get returned non-null for non existent key",
                hm.get("T"));
        hm.put("T", "HELLO");
        assertEquals("Get returned incorecct value for existing key", "HELLO", hm.get("T")
                );

        IdentityHashMap m = new IdentityHashMap();
        m.put(null, "test");
        assertEquals("Failed with null key", "test", m.get(null));
        assertNull("Failed with missing key matching null hash", m
                .get(new Integer(0)));
    }

    /**
     * java.util.IdentityHashMap#isEmpty()
     */
    public void test_isEmpty() {
        // Test for method boolean java.util.IdentityHashMap.isEmpty()
        assertTrue("Returned false for new map", new IdentityHashMap()
                .isEmpty());
        assertTrue("Returned true for non-empty", !hm.isEmpty());
    }

    /**
     * java.util.IdentityHashMap#keySet()
     */
    public void test_keySet() {
        // Test for method java.util.Set java.util.IdentityHashMap.keySet()
        Set s = hm.keySet();
        assertTrue("Returned set of incorrect size()", s.size() == hm.size());
        for (int i = 0; i < objArray.length; i++) {
            assertTrue("Returned set does not contain all keys", s
                    .contains(objArray2[i]));
        }

        IdentityHashMap m = new IdentityHashMap();
        m.put(null, "test");
        assertTrue("Failed with null key", m.keySet().contains(null));
        assertNull("Failed with null key", m.keySet().iterator().next());

        Map map = new IdentityHashMap(101);
        map.put(new Integer(1), "1");
        map.put(new Integer(102), "102");
        map.put(new Integer(203), "203");
        Iterator it = map.keySet().iterator();
        Integer remove1 = (Integer) it.next();
        it.hasNext();
        it.remove();
        Integer remove2 = (Integer) it.next();
        it.remove();
        ArrayList list = new ArrayList(Arrays.asList(new Integer[] {
                new Integer(1), new Integer(102), new Integer(203) }));
        list.remove(remove1);
        list.remove(remove2);
        assertTrue("Wrong result", it.next().equals(list.get(0)));
        assertEquals("Wrong size", 1, map.size());
        assertTrue("Wrong contents", map.keySet().iterator().next().equals(
                list.get(0)));

        Map map2 = new IdentityHashMap(101);
        map2.put(new Integer(1), "1");
        map2.put(new Integer(4), "4");
        Iterator it2 = map2.keySet().iterator();
        Integer remove3 = (Integer) it2.next();
        Integer next;
        if (remove3.intValue() == 1)
            next = new Integer(4);
        else
            next = new Integer(1);
        it2.hasNext();
        it2.remove();
        assertTrue("Wrong result 2", it2.next().equals(next));
        assertEquals("Wrong size 2", 1, map2.size());
        assertTrue("Wrong contents 2", map2.keySet().iterator().next().equals(
                next));
    }

    /**
     * java.util.IdentityHashMap#put(java.lang.Object, java.lang.Object)
     */
    public void test_putLjava_lang_ObjectLjava_lang_Object() {
        // Test for method java.lang.Object
        // java.util.IdentityHashMap.put(java.lang.Object, java.lang.Object)
        hm.put("KEY", "VALUE");
        assertEquals("Failed to install key/value pair",
                "VALUE", hm.get("KEY"));

        IdentityHashMap m = new IdentityHashMap();
        Short s0 = new Short((short) 0);
        m.put(s0, "short");
        m.put(null, "test");
        Integer i0 = new Integer(0);
        m.put(i0, "int");
        assertEquals("Failed adding to bucket containing null",
                "short", m.get(s0));
        assertEquals("Failed adding to bucket containing null2", "int", m.get(i0)
                );
    }

    /**
     * java.util.IdentityHashMap#putAll(java.util.Map)
     */
    public void test_putAllLjava_util_Map() {
        // Test for method void java.util.IdentityHashMap.putAll(java.util.Map)
        IdentityHashMap hm2 = new IdentityHashMap();
        hm2.putAll(hm);
        for (int i = 0; i < 1000; i++)
            assertTrue("Failed to clear all elements", hm2.get(objArray2[i])
                    .equals((new Integer(i))));

        hm2 = new IdentityHashMap();
        Map mockMap = new MockMap();
        hm2.putAll(mockMap);
        assertEquals("Size should be 0", 0, hm2.size());

        try {
            hm2.putAll(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * java.util.IdentityHashMap#remove(java.lang.Object)
     */
    public void test_removeLjava_lang_Object() {
        // Test for method java.lang.Object
        // java.util.IdentityHashMap.remove(java.lang.Object)
        int size = hm.size();
        Integer x = ((Integer) hm.remove(objArray2[9]));
        assertTrue("Remove returned incorrect value", x.equals(new Integer(9)));
        assertNull("Failed to remove given key", hm.get(objArray2[9]));
        assertTrue("Failed to decrement size", hm.size() == (size - 1));
        assertNull("Remove of non-existent key returned non-null", hm
                .remove("LCLCLC"));

        IdentityHashMap m = new IdentityHashMap();
        m.put(null, "test");
        assertNull("Failed with same hash as null",
                m.remove(objArray[0]));
        assertEquals("Failed with null key", "test", m.remove(null));
    }

    /**
     * java.util.IdentityHashMap#size()
     */
    public void test_size() {
        // Test for method int java.util.IdentityHashMap.size()
        assertEquals("Returned incorrect size, ", (objArray.length + 2), hm
                .size());
    }

    /**
     * java.util.IdentityHashMap#equals(java.lang.Object)
     */
    public void test_equalsLjava_lang_Object() {
        IdentityHashMap mapOne = new IdentityHashMap();
        IdentityHashMap mapTwo = new IdentityHashMap();
        IdentityHashMap mapThree = new IdentityHashMap();
        IdentityHashMap mapFour = new IdentityHashMap();

        String one = "one";
        String alsoOne = new String(one); // use the new operator to ensure a
        // new reference is constructed
        String two = "two";
        String alsoTwo = new String(two); // use the new operator to ensure a
        // new reference is constructed

        mapOne.put(one, two);
        mapFour.put(one, two);

        // these two are not equal to the above two
        mapTwo.put(alsoOne, two);
        mapThree.put(one, alsoTwo);

        assertEquals("failure of equality of IdentityHashMaps", mapOne, mapFour);
        assertTrue("failure of non-equality of IdentityHashMaps one and two",
                !mapOne.equals(mapTwo));
        assertTrue("failure of non-equality of IdentityHashMaps one and three",
                !mapOne.equals(mapThree));
        assertTrue("failure of non-equality of IdentityHashMaps two and three",
                !mapTwo.equals(mapThree));

        HashMap hashMapTwo = new HashMap();
        HashMap hashMapThree = new HashMap();
        hashMapTwo.put(alsoOne, two);
        hashMapThree.put(one, alsoTwo);

        assertTrue(
                "failure of non-equality of IdentityHashMaps one and Hashmap two",
                !mapOne.equals(hashMapTwo));
        assertTrue(
                "failure of non-equality of IdentityHashMaps one and Hashmap three",
                !mapOne.equals(hashMapThree));
    }

    /**
     * java.util.IdentityHashMap#values()
     */
    public void test_values() {
        // Test for method java.util.Collection
        // java.util.IdentityHashMap.values()
        Collection c = hm.values();
        assertTrue("Returned collection of incorrect size()", c.size() == hm
                .size());
        for (int i = 0; i < objArray.length; i++)
            assertTrue("Returned collection does not contain all keys", c
                    .contains(objArray[i]));

        IdentityHashMap myIdentityHashMap = new IdentityHashMap();
        for (int i = 0; i < 100; i++)
            myIdentityHashMap.put(objArray2[i], objArray[i]);
        Collection values = myIdentityHashMap.values();
        values.remove(objArray[0]);
        assertTrue(
                "Removing from the values collection should remove from the original map",
                !myIdentityHashMap.containsValue(objArray2[0]));

    }

    /**
     * java.util.IdentityHashMap#Serialization()
     */
    public void test_Serialization() throws Exception {
        IdentityHashMap<String, String> map = new IdentityHashMap<String, String>();
        map.put(ID, "world");
        // BEGIN android-added
        // Regression test for null key in serialized IdentityHashMap (1178549)
        // Together with this change the IdentityHashMap.golden.ser resource
        // was replaced by a version that contains a map with a null key.
        map.put(null, "null");
        // END android-added
        SerializationTest.verifySelf(map, comparator);
        SerializationTest.verifyGolden(this, map, comparator);
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        objArray = new Object[hmSize];
        objArray2 = new Object[hmSize];
        for (int i = 0; i < objArray.length; i++) {
            objArray[i] = new Integer(i);
            // android-changed: the containsKey test requires unique strings.
            objArray2[i] = new String(objArray[i].toString());
        }

        hm = new IdentityHashMap();
        for (int i = 0; i < objArray.length; i++)
            hm.put(objArray2[i], objArray[i]);
        hm.put("test", null);
        hm.put(null, "test");
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
        objArray = null;
        objArray2 = null;
        hm = null;
    }

    private static final SerializationTest.SerializableAssert comparator = new
                             SerializationTest.SerializableAssert() {

        public void assertDeserialized(Serializable initial, Serializable deserialized) {
            IdentityHashMap<String, String> initialMap = (IdentityHashMap<String, String>) initial;
            IdentityHashMap<String, String> deseriaMap = (IdentityHashMap<String, String>) deserialized;
            assertEquals("should be equal", initialMap.size(), deseriaMap.size());
        }

    };
}
