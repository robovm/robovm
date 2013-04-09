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

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;

public class OldAbstractMapTest extends TestCase {

    public void test_Constructor() {
        AMT amt = new AMT();
        assertNotNull(amt);
    }

    public void test_equalsLjava_lang_Object() {
        AbstractMap<String, String> amt1 = new AMT();
        AbstractMap<String, String> amt2 = new AMT();
        assertTrue("assert 0", amt1.equals(amt2));
        assertTrue("assert 1", amt1.equals(amt1));
        assertTrue("assert 2", amt2.equals(amt1));
        amt1.put("1", "one");
        assertFalse("assert 3", amt1.equals(amt2));
        amt1.put("2", "two");
        amt1.put("3", "three");

        amt2.put("1", "one");
        amt2.put("2", "two");
        amt2.put("3", "three");
        assertTrue("assert 4", amt1.equals(amt2));
        assertFalse("assert 5", amt1.equals(this));
    }

    public void test_hashCode() {
        AMT amt1 = new AMT();
        AMT amt2 = new AMT();
        amt1.put("1", "one");

        assertNotSame(amt1.hashCode(), amt2.hashCode());
    }

    public void test_isEmpty() {
        AMT amt = new AMT();
        assertTrue(amt.isEmpty());
        amt.put("1", "one");
        assertFalse(amt.isEmpty());
    }

    public void test_put() {
        AMT amt = new AMT();
        assertEquals(0, amt.size());
        amt.put("1", "one");
        assertEquals(1, amt.size());
        amt.put("2", "two");
        assertEquals(2, amt.size());
        amt.put("3", "three");
        assertEquals(3, amt.size());
    }

    public void test_size() {
        AMT amt = new AMT();
        assertEquals(0, amt.size());
        amt.put("1", "one");
        assertEquals(1, amt.size());
        amt.put("2", "two");
        assertEquals(2, amt.size());
        amt.put("3", "three");
        assertEquals(3, amt.size());
    }

    public void test_toString() {
        AMT amt = new AMT();
        assertEquals("{}", amt.toString());
        amt.put("1", "one");
        assertEquals("{1=one}", amt.toString());
        amt.put("2", "two");
        assertEquals("{1=one, 2=two}", amt.toString());
        amt.put("3", "three");
        assertEquals("{1=one, 2=two, 3=three}", amt.toString());
    }

    static class AMT extends AbstractMap<String, String> {
        private final List<Entry<String, String>> entries = new ArrayList<Entry<String, String>>();

        @Override public String put(String key, String value) {
            String result = remove(key);
            entries.add(new AbstractMap.SimpleEntry<String, String>(key, value));
            return result;
        }

        @Override public Set<Entry<String, String>> entrySet() {
            return new AbstractSet<Entry<String, String>>() {
                @Override public Iterator<Entry<String, String>> iterator() {
                    return entries.iterator();
                }
                @Override public int size() {
                    return entries.size();
                }
            };
        }
    }
}
