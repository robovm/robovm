/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.util.prefs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeEvent;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class OldPreferencesTest extends TestCase {

    final static String longKey;
    final static String longValue;

    static {
        StringBuilder key = new StringBuilder(Preferences.MAX_KEY_LENGTH);
        for (int i = 0; i < Preferences.MAX_KEY_LENGTH; i++) {
            key.append('a');
        }
        longKey = key.toString();

        StringBuilder value = new StringBuilder(Preferences.MAX_VALUE_LENGTH);
        for (int i = 0; i < Preferences.MAX_VALUE_LENGTH; i++) {
            value.append('a');
        }
        longValue = value.toString();
    }

    @Override protected void setUp() throws Exception {
        super.setUp();

        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        for (String child : pref.childrenNames()) {
            pref.node(child).removeNode();
        }
        pref.clear();
    }

    public void testAbstractMethods() throws IOException, BackingStoreException {
        Preferences p = new MockPreferences();
        p.absolutePath();
        p.childrenNames();
        p.clear();
        p.exportNode(null);
        p.exportSubtree(null);
        p.flush();
        p.get(null, null);
        p.getBoolean(null, false);
        p.getByteArray(null, null);
        p.getFloat(null, 0.1f);
        p.getDouble(null, 0.1);
        p.getInt(null, 1);
        p.getLong(null, 1l);
        p.isUserNode();
        p.keys();
        p.name();
        p.node(null);
        p.nodeExists(null);
        p.parent();
        p.put(null, null);
        p.putBoolean(null, false);
        p.putByteArray(null, null);
        p.putDouble(null, 1);
        p.putFloat(null, 1f);
        p.putInt(null, 1);
        p.putLong(null, 1l);
        p.remove(null);
        p.removeNode();
        p.addNodeChangeListener(null);
        p.addPreferenceChangeListener(null);
        p.removeNodeChangeListener(null);
        p.removePreferenceChangeListener(null);
        p.sync();
        p.toString();
    }

    public void testConstructor() {
        MockPreferences mp = new MockPreferences();
        assertEquals(mp.getClass(), MockPreferences.class);
    }

    public void testToString() {
        Preferences p1 = Preferences.userNodeForPackage(Preferences.class);
        assertNotNull(p1.toString());

        Preferences p2 = Preferences.systemRoot();
        assertNotNull(p2.toString());

        Preferences p3 = Preferences.userRoot();
        assertNotNull(p3.toString());
    }

    public void testAbsolutePath() {
        Preferences p = Preferences.userNodeForPackage(Preferences.class);
        assertEquals("/java/util/prefs", p.absolutePath());

    }

    public void testChildrenNames() throws BackingStoreException {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);

        Preferences child1 = pref.node("child1");

        pref.node("child2");
        pref.node("child3");
        child1.node("subchild1");

        assertSame(pref, child1.parent());
        assertEquals(3, pref.childrenNames().length);
        assertEquals("child1", pref.childrenNames()[0]);
        assertEquals(1, child1.childrenNames().length);
        assertEquals("subchild1", child1.childrenNames()[0]);
    }

    public void testClear() throws BackingStoreException {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        pref.put("testClearKey", "testClearValue");
        pref.put("testClearKey1", "testClearValue1");
        assertEquals("testClearValue", pref.get("testClearKey", null));
        assertEquals("testClearValue1", pref.get("testClearKey1", null));
        pref.clear();
        assertNull(pref.get("testClearKey", null));
        assertNull(pref.get("testClearKey1", null));
    }

    public void testGet() throws BackingStoreException {
        Preferences root = Preferences.userNodeForPackage(Preferences.class);
        Preferences pref = root.node("mock");
        assertNull(pref.get("", null));
        assertEquals("default", pref.get("key", "default"));
        assertNull(pref.get("key", null));
        pref.put("testGetkey", "value");
        assertNull(pref.get("testGetKey", null));
        assertEquals("value", pref.get("testGetkey", null));

        try {
            pref.get(null, "abc");
            fail();
        } catch (NullPointerException expected) {
        }
        pref.get("", "abc");
        pref.get("key", null);
        pref.get("key", "");
        pref.putFloat("floatKey", 1.0f);
        assertEquals("1.0", pref.get("floatKey", null));

        pref.removeNode();
        try {
            pref.get("key", "abc");
            fail();
        } catch (IllegalStateException expected) {
        }
        try {
            pref.get(null, "abc");
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void testGetBoolean() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getBoolean(null, false);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.put("testGetBooleanKey", "false");
        pref.put("testGetBooleanKey2", "value");
        assertFalse(pref.getBoolean("testGetBooleanKey", true));
        assertTrue(pref.getBoolean("testGetBooleanKey2", true));
    }

    public void testGetByteArray() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getByteArray(null, new byte[0]);
            fail();
        } catch (NullPointerException expected) {
        }
        byte[] b64Array = new byte[] { 0x59, 0x57, 0x4a, 0x6a };// BASE64

        pref.put("testGetByteArrayKey", "abc=");
        pref.put("testGetByteArrayKey2", new String(b64Array));
        pref.put("invalidKey", "<>?");
        assertTrue(Arrays.equals(new byte[] { 105, -73 }, pref.getByteArray(
                "testGetByteArrayKey", new byte[0])));
        assertTrue(Arrays.equals(new byte[] { 'a', 'b', 'c' }, pref
                .getByteArray("testGetByteArrayKey2", new byte[0])));
        assertTrue(Arrays.equals(new byte[0], pref.getByteArray("invalidKey",
                new byte[0])));

        pref.putByteArray("testGetByteArrayKey3", b64Array);
        pref.putByteArray("testGetByteArrayKey4", "abc".getBytes());
        assertTrue(Arrays.equals(b64Array, pref.getByteArray(
                "testGetByteArrayKey3", new byte[0])));
        assertTrue(Arrays.equals("abc".getBytes(), pref.getByteArray(
                "testGetByteArrayKey4", new byte[0])));
    }

    public void testGetDouble() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getDouble(null, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.put("testGetDoubleKey", "1");
        pref.put("testGetDoubleKey2", "value");
        pref.putDouble("testGetDoubleKey3", 1);
        pref.putInt("testGetDoubleKey4", 1);
        assertEquals(1.0, pref.getDouble("testGetDoubleKey", 0.0), 0);
        assertEquals(0.0, pref.getDouble("testGetDoubleKey2", 0.0), 0);
        assertEquals(1.0, pref.getDouble("testGetDoubleKey3", 0.0), 0);
        assertEquals(1.0, pref.getDouble("testGetDoubleKey4", 0.0), 0);
    }

    public void testGetFloat() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getFloat(null, 0f);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.put("testGetFloatKey", "1");
        pref.put("testGetFloatKey2", "value");
        assertEquals(1f, pref.getFloat("testGetFloatKey", 0f), 0);
        assertEquals(0f, pref.getFloat("testGetFloatKey2", 0f), 0);
    }

    public void testGetInt() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getInt(null, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.put("testGetIntKey", "1");
        pref.put("testGetIntKey2", "value");
        assertEquals(1, pref.getInt("testGetIntKey", 0));
        assertEquals(0, pref.getInt("testGetIntKey2", 0));
    }

    public void testGetLong() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.getLong(null, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.put("testGetLongKey", "1");
        pref.put("testGetLongKey2", "value");
        assertEquals(1, pref.getInt("testGetLongKey", 0));
        assertEquals(0, pref.getInt("testGetLongKey2", 0));
    }

    public void testIsUserNode() {
        Preferences pref1 = Preferences.userNodeForPackage(Preferences.class);
        assertTrue(pref1.isUserNode());

        Preferences pref2 = Preferences.systemNodeForPackage(Preferences.class);
        assertFalse(pref2.isUserNode());
    }

    public void testKeys() throws BackingStoreException {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        pref.clear();

        pref.put("key0", "value");
        pref.put("key1", "value1");
        pref.put("key2", "value2");
        pref.put("key3", "value3");

        String[] keys = pref.keys();
        assertEquals(4, keys.length);
        for (String key : keys) {
            assertEquals(0, key.indexOf("key"));
            assertEquals(4, key.length());
        }
    }

    public void testName() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        Preferences child = pref.node("mock");
        assertEquals("mock", child.name());
    }

    public void testNode() throws BackingStoreException {
        StringBuilder name = new StringBuilder(Preferences.MAX_NAME_LENGTH);
        for (int i = 0; i < Preferences.MAX_NAME_LENGTH; i++) {
            name.append('a');
        }
        String longName = name.toString();

        Preferences root = Preferences.userRoot();
        Preferences parent = Preferences
                .userNodeForPackage(Preferences.class);
        Preferences pref = parent.node("mock");

        try {
            pref.node(null);
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            pref.node("/java/util/prefs/");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pref.node("/java//util/prefs");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pref.node(longName + "a");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        assertNotNull(pref.node(longName));

        assertSame(root, pref.node("/"));

        Preferences prefs = pref.node("/java/util/prefs");
        assertSame(prefs, parent);

        assertSame(pref, pref.node(""));
    }

    public void testNodeExists() throws BackingStoreException {

        Preferences parent = Preferences
                .userNodeForPackage(Preferences.class);
        Preferences pref = parent.node("mock");

        try {
            pref.nodeExists(null);
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            pref.nodeExists("/java/util/prefs/");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pref.nodeExists("/java//util/prefs");
            fail();
        } catch (IllegalArgumentException expected) {
        }

        assertTrue(pref.nodeExists("/"));

        assertTrue(pref.nodeExists("/java/util/prefs"));

        assertTrue(pref.nodeExists(""));

        assertFalse(pref.nodeExists("child"));
        Preferences grandchild = pref.node("child/grandchild");
        assertTrue(pref.nodeExists("child"));
        assertTrue(pref.nodeExists("child/grandchild"));
        grandchild.removeNode();
        assertTrue(pref.nodeExists("child"));
        assertFalse(pref.nodeExists("child/grandchild"));
        assertFalse(grandchild.nodeExists(""));

        assertFalse(pref.nodeExists("child2/grandchild"));
        pref.node("child2/grandchild");
        assertTrue(pref.nodeExists("child2/grandchild"));
    }

    public void testParent() {
        Preferences parent = Preferences
                .userNodeForPackage(Preferences.class);
        Preferences pref = parent.node("mock");

        assertSame(parent, pref.parent());
    }

    public void testPut() throws BackingStoreException {
        Preferences pref = Preferences
        .userNodeForPackage(Preferences.class);
        pref.put("", "emptyvalue");
        assertEquals("emptyvalue", pref.get("", null));
        pref.put("testPutkey", "value1");
        assertEquals("value1", pref.get("testPutkey", null));
        pref.put("testPutkey", "value2");
        assertEquals("value2", pref.get("testPutkey", null));

        pref.put("", "emptyvalue");
        assertEquals("emptyvalue", pref.get("", null));

        try {
            pref.put(null, "value");
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            pref.put("key", null);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.put(longKey, longValue);
        try {
            pref.put(longKey + 1, longValue);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pref.put(longKey, longValue + 1);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        pref.removeNode();
        try {
            pref.put(longKey, longValue + 1);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        try {
            pref.put(longKey, longValue);
            fail();
        } catch (IllegalStateException expected) {
        }
    }

    public void testPutBoolean() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.putBoolean(null, false);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.putBoolean(longKey, false);
        try {
            pref.putBoolean(longKey + "a", false);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.putBoolean("testPutBooleanKey", false);
        assertEquals("false", pref.get("testPutBooleanKey", null));
        assertFalse(pref.getBoolean("testPutBooleanKey", true));
    }

    public void testPutDouble() {
        Preferences pref = Preferences
        .userNodeForPackage(Preferences.class);
        try {
            pref.putDouble(null, 3);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.putDouble(longKey, 3);
        try {
            pref.putDouble(longKey + "a", 3);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.putDouble("testPutDoubleKey", 3);
        assertEquals("3.0", pref.get("testPutDoubleKey", null));
        assertEquals(3, pref.getDouble("testPutDoubleKey", 0), 0);
    }

    public void testPutFloat() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.putFloat(null, 3f);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.putFloat(longKey, 3f);
        try {
            pref.putFloat(longKey + "a", 3f);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.putFloat("testPutFloatKey", 3f);
        assertEquals("3.0", pref.get("testPutFloatKey", null));
        assertEquals(3f, pref.getFloat("testPutFloatKey", 0), 0);
    }

    public void testPutInt() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.putInt(null, 3);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.putInt(longKey, 3);
        try {
            pref.putInt(longKey + "a", 3);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.putInt("testPutIntKey", 3);
        assertEquals("3", pref.get("testPutIntKey", null));
        assertEquals(3, pref.getInt("testPutIntKey", 0));
    }

    public void testPutLong() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.putLong(null, 3L);
            fail();
        } catch (NullPointerException expected) {
        }
        pref.putLong(longKey, 3L);
        try {
            pref.putLong(longKey + "a", 3L);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.putLong("testPutLongKey", 3L);
        assertEquals("3", pref.get("testPutLongKey", null));
        assertEquals(3L, pref.getLong("testPutLongKey", 0));
    }

    public void testPutByteArray() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.putByteArray(null, new byte[0]);
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            pref.putByteArray("testPutByteArrayKey4", null);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.putByteArray(longKey, new byte[0]);
        try {
            pref.putByteArray(longKey + "a", new byte[0]);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        byte[] longArray = new byte[(int) (Preferences.MAX_VALUE_LENGTH * 0.74)];
        byte[] longerArray = new byte[(int) (Preferences.MAX_VALUE_LENGTH * 0.75) + 1];
        pref.putByteArray(longKey, longArray);
        try {
            pref.putByteArray(longKey, longerArray);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        pref.putByteArray("testPutByteArrayKey", new byte[0]);
        assertEquals("", pref.get("testPutByteArrayKey", null));
        assertTrue(Arrays.equals(new byte[0], pref.getByteArray(
                "testPutByteArrayKey", null)));

        pref.putByteArray("testPutByteArrayKey3", new byte[] { 'a', 'b', 'c' });
        assertEquals("YWJj", pref.get("testPutByteArrayKey3", null));
        assertTrue(Arrays.equals(new byte[] { 'a', 'b', 'c' }, pref
                .getByteArray("testPutByteArrayKey3", null)));
    }

    public void testRemove() throws BackingStoreException {
        Preferences pref = Preferences
        .userNodeForPackage(Preferences.class);
        pref.remove("key");

        pref.put("key", "value");
        assertEquals("value", pref.get("key", null));
        pref.remove("key");
        assertNull(pref.get("key", null));

        pref.remove("key");

        try {
            pref.remove(null);
            fail();
        } catch (NullPointerException expected) {
        }

        pref.removeNode();
        try {
            pref.remove("key");
            fail();
        } catch (IllegalStateException expected) {
        }
    }

    public void testRemoveNode() throws BackingStoreException {
        Preferences pref = Preferences
        .userNodeForPackage(Preferences.class);
        Preferences child = pref.node("child");
        Preferences child1 = pref.node("child1");
        Preferences grandchild = child.node("grandchild");

        pref.removeNode();

        assertFalse(child.nodeExists(""));
        assertFalse(child1.nodeExists(""));
        assertFalse(grandchild.nodeExists(""));
        assertFalse(pref.nodeExists(""));
    }

    public void testAddNodeChangeListener() throws BackingStoreException {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.addNodeChangeListener(null);
            fail();
        } catch (NullPointerException expected) {
        }

        Preferences child1 = null;

        MockNodeChangeListener nl = null;
        // To get existed node doesn't create the change event
        try {
            nl = new MockNodeChangeListener();
            pref.addNodeChangeListener(nl);
            child1 = pref.node("mock1");
            nl.waitForEvent();
            assertEquals(1, nl.getAdded());
            nl.reset();
            pref.node("mock1");
            nl.waitForEvent();
            assertEquals(0, nl.getAdded());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
            child1.removeNode();
        }
        // same listener can be added twice, and must be removed twice
        try {
            nl = new MockNodeChangeListener();
            pref.addNodeChangeListener(nl);
            pref.addNodeChangeListener(nl);
            child1 = pref.node("mock2");
            nl.waitForEvent();
            assertEquals(2, nl.getAdded());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
            pref.removeNodeChangeListener(nl);
            child1.removeNode();
        }
        // test remove event
        try {
            nl = new MockNodeChangeListener();
            pref.addNodeChangeListener(nl);
            child1 = pref.node("mock3");
            child1.removeNode();
            nl.waitForEvent();
            assertEquals(1, nl.getRemoved());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
        }
        // test remove event with two listeners
        try {
            nl = new MockNodeChangeListener();
            pref.addNodeChangeListener(nl);
            pref.addNodeChangeListener(nl);
            child1 = pref.node("mock6");
            child1.removeNode();
            nl.waitForEvent();
            assertEquals(2, nl.getRemoved());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
            pref.removeNodeChangeListener(nl);
        }
        // test add/remove indirect children, or remove several children at the
        // same time
        Preferences child3;
        try {
            nl = new MockNodeChangeListener();
            child1 = pref.node("mock4");
            child1.addNodeChangeListener(nl);
            pref.node("mock4/mock5");
            nl.waitForEvent();
            assertEquals(1, nl.getAdded());
            nl.reset();
            child3 = pref.node("mock4/mock5/mock6");
            nl.waitForEvent();
            assertEquals(0, nl.getAdded());
            nl.reset();

            child3.removeNode();
            nl.waitForEvent();
            assertEquals(0, nl.getRemoved());
            nl.reset();

            pref.node("mock4/mock7");
            nl.waitForEvent();
            assertEquals(1, nl.getAdded());
            nl.reset();

            child1.removeNode();
            nl.waitForEvent();
            assertEquals(2, nl.getRemoved()); // fail 1
            nl.reset();
        } finally {
            try {
                child1.removeNode();
            } catch (Exception ignored) {
            }
        }
    }

    public void testAddPreferenceChangeListener() throws Exception {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        MockPreferenceChangeListener pl = null;

        try {
            pref.addPreferenceChangeListener(null);
            fail();
        } catch (NullPointerException expected) {
        }

        // To get existed node doesn't create the change event
        try {
            pl = new MockPreferenceChangeListener();
            pref.addPreferenceChangeListener(pl);
            pref.putInt("mock1", 123);
            pl.waitForEvent(1);
            assertEquals(1, pl.getChanged());
            pref.putLong("long_key", Long.MAX_VALUE);
            pl.waitForEvent(2);
            assertEquals(2, pl.getChanged());
            pl.reset();
            try {
                pref.clear();
                pl.waitForEvent(2);
                assertEquals(2, pl.getChanged()); // fail 1
            } catch(BackingStoreException bse) {
                pl.reset();
                fail("BackingStoreException is thrown");
            }
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
            //child1.removeNode();
        }

        // same listener can be added twice, and must be removed twice
        try {
            pl = new MockPreferenceChangeListener();
            pref.addPreferenceChangeListener(pl);
            pref.addPreferenceChangeListener(pl);
            pref.putFloat("float_key", Float.MIN_VALUE);
            pl.waitForEvent(2);
            assertEquals(2, pl.getChanged());
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
            pref.removePreferenceChangeListener(pl);

        }
        // test remove event
        try {
            pl = new MockPreferenceChangeListener();
            pref.addPreferenceChangeListener(pl);
            pref.putDouble("double_key", Double.MAX_VALUE);
            pl.waitForEvent(1);
            assertEquals(1, pl.getChanged());
            try {
                pref.clear();
                pl.waitForEvent(3);
                assertEquals(3, pl.getChanged()); // fails
            } catch(BackingStoreException bse) {
                fail("BackingStoreException is thrown");
            }
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
        }
        // test remove event with two listeners
        try {
            pl = new MockPreferenceChangeListener();
            pref.addPreferenceChangeListener(pl);
            pref.addPreferenceChangeListener(pl);
            pref.putByteArray("byte_array_key", new byte [] {1 ,2 , 3});
            try {
                pref.clear();
                pl.waitForEvent(4);
                assertEquals(4, pl.getChanged());
            } catch(BackingStoreException bse) {
                fail("BackingStoreException is thrown");
            }
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
            pref.removePreferenceChangeListener(pl);
        }

    }

    public void testRemoveNodeChangeListener() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.removeNodeChangeListener(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        MockNodeChangeListener l1 = new MockNodeChangeListener();
        MockNodeChangeListener l2 = new MockNodeChangeListener();
        pref.addNodeChangeListener(l1);
        pref.addNodeChangeListener(l1);

        pref.removeNodeChangeListener(l1);
        pref.removeNodeChangeListener(l1);
        try {
            pref.removeNodeChangeListener(l1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pref.removeNodeChangeListener(l2);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testRemovePreferenceChangeListener() {
        Preferences pref = Preferences.userNodeForPackage(Preferences.class);
        try {
            pref.removePreferenceChangeListener(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        MockPreferenceChangeListener l1 = new MockPreferenceChangeListener();
        MockPreferenceChangeListener l2 = new MockPreferenceChangeListener();
        pref.addPreferenceChangeListener(l1);
        pref.addPreferenceChangeListener(l1);
        try {
            pref.removePreferenceChangeListener(l2);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        pref.removePreferenceChangeListener(l1);
        pref.removePreferenceChangeListener(l1);
        try {
            pref.removePreferenceChangeListener(l1);
            fail();
        } catch (IllegalArgumentException expected) {
        }

    }

    static class MockNodeChangeListener implements NodeChangeListener {
        private int added = 0;
        private int removed = 0;

        public synchronized void waitForEvent() {
            try {
                wait(500);
            } catch (InterruptedException ignored) {
            }
        }

        public synchronized void childAdded(NodeChangeEvent e) {
            ++added;
            notifyAll();
        }

        public synchronized void childRemoved(NodeChangeEvent e) {
            removed++;
            notifyAll();
        }

        public synchronized  int getAdded() {
            return added;
        }

        public synchronized int getRemoved() {
            return removed;
        }

        public void reset() {
            added = 0;
            removed = 0;
        }
    }

    private static class MockPreferenceChangeListener implements PreferenceChangeListener {
        private int changed = 0;
        private boolean addDispatched = false;
        private boolean result = false;

        public void waitForEvent(int count) {
            for (int i = 0; i < count; i++) {
                try {
                    synchronized (this) {
                        wait(500);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        }

        public synchronized void preferenceChange(PreferenceChangeEvent pce) {
            changed++;
            addDispatched = true;
            notifyAll();
        }

        public boolean getResult() throws InterruptedException {
            if (!addDispatched) {
                // TODO: don't know why must add limitation
                wait(100);
            }
            addDispatched = false;
            return result;
        }

        public synchronized int getChanged() throws InterruptedException {
            if (!addDispatched) {
                // TODO: don't know why must add limitation
                wait(1000);
            }
            addDispatched = false;
            return changed;
        }

        public void reset() {
            changed = 0;
            result = false;
        }
    }

    @SuppressWarnings("unused")
    static class MockPreferences extends Preferences {

        @Override
        public String absolutePath() {
            return null;
        }

        @Override
        public String[] childrenNames() throws BackingStoreException {
            return null;
        }

        @Override
        public void clear() throws BackingStoreException {
        }

        @Override
        public void exportNode(OutputStream ostream) throws IOException, BackingStoreException {
        }

        @Override
        public void exportSubtree(OutputStream ostream) throws IOException, BackingStoreException {
        }

        @Override
        public void flush() throws BackingStoreException {
        }

        @Override
        public String get(String key, String deflt) {
            return null;
        }

        @Override
        public boolean getBoolean(String key, boolean deflt) {
            return false;
        }

        @Override
        public byte[] getByteArray(String key, byte[] deflt) {
            return null;
        }

        @Override
        public double getDouble(String key, double deflt) {
            return 0;
        }

        @Override
        public float getFloat(String key, float deflt) {
            return 0;
        }

        @Override
        public int getInt(String key, int deflt) {
            return 0;
        }

        @Override
        public long getLong(String key, long deflt) {
            return 0;
        }

        @Override
        public boolean isUserNode() {
            return false;
        }

        @Override
        public String[] keys() throws BackingStoreException {
            return null;
        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public Preferences node(String name) {
            return null;
        }

        @Override
        public boolean nodeExists(String name) throws BackingStoreException {
            return false;
        }

        @Override
        public Preferences parent() {
            return null;
        }

        @Override
        public void put(String key, String value) {
        }

        @Override
        public void putBoolean(String key, boolean value) {
        }

        @Override
        public void putByteArray(String key, byte[] value) {
        }

        @Override
        public void putDouble(String key, double value) {
        }

        @Override
        public void putFloat(String key, float value) {
        }

        @Override
        public void putInt(String key, int value) {
        }

        @Override
        public void putLong(String key, long value) {
        }

        @Override
        public void remove(String key) {
        }

        @Override
        public void removeNode() throws BackingStoreException {
        }

        @Override
        public void addNodeChangeListener(NodeChangeListener ncl) {
        }

        @Override
        public void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        }

        @Override
        public void removeNodeChangeListener(NodeChangeListener ncl) {
        }

        @Override
        public void removePreferenceChangeListener(PreferenceChangeListener pcl) {
        }

        @Override
        public void sync() throws BackingStoreException {
        }

        @Override
        public String toString() {
            return null;
        }
    }
}
