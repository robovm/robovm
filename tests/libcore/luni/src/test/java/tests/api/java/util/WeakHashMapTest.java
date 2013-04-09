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

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import tests.support.Support_MapTest2;

public class WeakHashMapTest extends junit.framework.TestCase {
    class MockMap extends AbstractMap {
        public Set entrySet() {
            return null;
        }
        public int size(){
            return 0;
        }
    }

    Object[] keyArray = new Object[100];

    Object[] valueArray = new Object[100];

    WeakHashMap whm;

    /**
     * java.util.WeakHashMap#WeakHashMap()
     */
    public void test_Constructor() {
        // Test for method java.util.WeakHashMap()
        new Support_MapTest2(new WeakHashMap()).runTest();

        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        for (int i = 0; i < 100; i++)
            assertTrue("Incorrect value retrieved",
                    whm.get(keyArray[i]) == valueArray[i]);

    }

    /**
     * java.util.WeakHashMap#WeakHashMap(int)
     */
    public void test_ConstructorI() {
        // Test for method java.util.WeakHashMap(int)
        whm = new WeakHashMap(50);
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        for (int i = 0; i < 100; i++)
            assertTrue("Incorrect value retrieved",
                    whm.get(keyArray[i]) == valueArray[i]);

        WeakHashMap empty = new WeakHashMap(0);
        assertNull("Empty weakhashmap access", empty.get("nothing"));
        empty.put("something", "here");
        assertTrue("cannot get element", empty.get("something") == "here");

        try {
            new WeakHashMap(-50);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    /**
     * java.util.WeakHashMap#WeakHashMap(int, float)
     */
    public void test_ConstructorIF() {
        // Test for method java.util.WeakHashMap(int, float)
        whm = new WeakHashMap(50, 0.5f);
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        for (int i = 0; i < 100; i++)
            assertTrue("Incorrect value retrieved",
                    whm.get(keyArray[i]) == valueArray[i]);

        WeakHashMap empty = new WeakHashMap(0, 0.75f);
        assertNull("Empty hashtable access", empty.get("nothing"));
        empty.put("something", "here");
        assertTrue("cannot get element", empty.get("something") == "here");

        try {
            new WeakHashMap(50, -0.5f);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            new WeakHashMap(-50, 0.5f);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    /**
     * java.util.WeakHashMap#WeakHashMap(java.util.Map)
     */
    public void test_ConstructorLjava_util_Map() {
        Map mockMap = new MockMap();
        WeakHashMap map = new WeakHashMap(mockMap);
        assertEquals("Size should be 0", 0, map.size());

        try {
            new WeakHashMap(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * java.util.WeakHashMap#clear()
     */
    public void test_clear() {
        // Test for method boolean java.util.WeakHashMap.clear()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        whm.clear();
        assertTrue("Cleared map should be empty", whm.isEmpty());
        for (int i = 0; i < 100; i++)
            assertNull("Cleared map should only return null", whm
                    .get(keyArray[i]));

    }

    /**
     * java.util.WeakHashMap#containsKey(java.lang.Object)
     */
    public void test_containsKeyLjava_lang_Object() {
        // Test for method boolean java.util.WeakHashMap.containsKey()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        for (int i = 0; i < 100; i++)
            assertTrue("Should contain referenced key", whm
                    .containsKey(keyArray[i]));
        keyArray[25] = null;
        keyArray[50] = null;
    }

    /**
     * java.util.WeakHashMap#containsValue(java.lang.Object)
     */
    public void test_containsValueLjava_lang_Object() {
        // Test for method boolean java.util.WeakHashMap.containsValue()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        for (int i = 0; i < 100; i++)
            assertTrue("Should contain referenced value", whm
                    .containsValue(valueArray[i]));
        keyArray[25] = null;
        keyArray[50] = null;
    }

    /**
     * java.util.WeakHashMap#entrySet()
     */
    public void test_entrySet() {
        // Test for method java.util.Set java.util.WeakHashMap.entrySet()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);
        List keys = Arrays.asList(keyArray);
        List values = Arrays.asList(valueArray);
        Set entrySet = whm.entrySet();
        assertTrue("Incorrect number of entries returned--wanted 100, got: "
                + entrySet.size(), entrySet.size() == 100);
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            assertTrue("Invalid map entry returned--bad key", keys
                    .contains(entry.getKey()));
            assertTrue("Invalid map entry returned--bad key", values
                    .contains(entry.getValue()));
        }
        keys = null;
        values = null;
        keyArray[50] = null;

        int count = 0;
        do {
            System.gc();
            System.gc();
            Runtime.getRuntime().runFinalization();
            count++;
        } while (count <= 5 && entrySet.size() == 100);

        assertTrue(
                "Incorrect number of entries returned after gc--wanted 99, got: "
                        + entrySet.size(), entrySet.size() == 99);
    }

    /**
     * java.util.WeakHashMap#isEmpty()
     */
    public void test_isEmpty() {
        // Test for method boolean java.util.WeakHashMap.isEmpty()
        whm = new WeakHashMap();
        assertTrue("New map should be empty", whm.isEmpty());
        Object myObject = new Object();
        whm.put(myObject, myObject);
        assertTrue("Map should not be empty", !whm.isEmpty());
        whm.remove(myObject);
        assertTrue("Map with elements removed should be empty", whm.isEmpty());
    }

    /**
     * java.util.WeakHashMap#put(java.lang.Object, java.lang.Object)
     */
    public void test_putLjava_lang_ObjectLjava_lang_Object() {
        // Test for method java.lang.Object
        // java.util.WeakHashMap.put(java.lang.Object, java.lang.Object)
        WeakHashMap map = new WeakHashMap();
        map.put(null, "value"); // add null key
        System.gc();
        System.runFinalization();
        map.remove("nothing"); // Cause objects in queue to be removed
        assertEquals("null key was removed", 1, map.size());
    }

    /**
     * java.util.WeakHashMap#putAll(java.util.Map)
     */
    public void test_putAllLjava_util_Map() {
        Map mockMap=new MockMap();
        WeakHashMap map = new WeakHashMap();
        map.putAll(mockMap);
        assertEquals("Size should be 0", 0, map.size());

        try {
            map.putAll(null);
            fail("NullPointerException exected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * java.util.WeakHashMap#remove(java.lang.Object)
     */
    public void test_removeLjava_lang_Object() {
        // Test for method java.lang.Object
        // java.util.WeakHashMap.remove(java.lang.Object)
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);

        assertTrue("Remove returned incorrect value",
                whm.remove(keyArray[25]) == valueArray[25]);
        assertNull("Remove returned incorrect value",
                whm.remove(keyArray[25]));
        assertEquals("Size should be 99 after remove", 99, whm.size());
    }

    /**
     * java.util.WeakHashMap#size()
     */
    public void test_size() {
        whm = new WeakHashMap();
        assertEquals(0, whm.size());
    }

    /**
     * java.util.WeakHashMap#keySet()
     */
    public void test_keySet() {
        // Test for method java.util.Set java.util.WeakHashMap.keySet()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);

        List keys = Arrays.asList(keyArray);
        List values = Arrays.asList(valueArray);

        Set keySet = whm.keySet();
        assertEquals("Incorrect number of keys returned,", 100, keySet.size());
        Iterator it = keySet.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            assertTrue("Invalid map entry returned--bad key", keys
                    .contains(key));
        }
        keys = null;
        values = null;
        keyArray[50] = null;

        int count = 0;
        do {
            System.gc();
            System.gc();
            Runtime.getRuntime().runFinalization();
            count++;
        } while (count <= 5 && keySet.size() == 100);

        assertEquals("Incorrect number of keys returned after gc,", 99, keySet
                .size());
    }

    /**
     * java.util.WeakHashMap#values()
     */
    public void test_values() {
        // Test for method java.util.Set java.util.WeakHashMap.values()
        whm = new WeakHashMap();
        for (int i = 0; i < 100; i++)
            whm.put(keyArray[i], valueArray[i]);

        List keys = Arrays.asList(keyArray);
        List values = Arrays.asList(valueArray);

        Collection valuesCollection = whm.values();
        assertEquals("Incorrect number of keys returned,", 100,
                valuesCollection.size());
        Iterator it = valuesCollection.iterator();
        while (it.hasNext()) {
            Object value = it.next();
            assertTrue("Invalid map entry returned--bad value", values
                    .contains(value));
        }
        keys = null;
        values = null;
        keyArray[50] = null;

        int count = 0;
        do {
            System.gc();
            System.gc();
            Runtime.getRuntime().runFinalization();
            count++;
        } while (count <= 5 && valuesCollection.size() == 100);

        assertEquals("Incorrect number of keys returned after gc,", 99,
                valuesCollection.size());
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        for (int i = 0; i < 100; i++) {
            keyArray[i] = new Object();
            valueArray[i] = new Object();
        }

    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
    }
}
