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

package libcore.java.util.prefs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.NodeChangeEvent;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class OldAbstractPreferencesTest extends TestCase {

    static final String nodeName = "mock";

    AbstractPreferences pref;
    AbstractPreferences root;
    AbstractPreferences parent = null;

    protected void setUp() throws Exception {
        super.setUp();

        root = (AbstractPreferences) Preferences.userRoot();
        for (String child : root.childrenNames()) {
            root.node(child).removeNode();
        }
        root.clear();

        parent = (AbstractPreferences) Preferences.userNodeForPackage(getClass());
        pref = (AbstractPreferences) parent.node(nodeName);
    }

    public void testToString() {
        assertTrue(pref.toString().contains(nodeName));
    }

    public void testPut() throws BackingStoreException {
        pref.put("Value", "String");
        pref.flush();

        assertEquals("String", pref.get("Value", ":"));

        try {
            pref.put(null, "Exception");
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.put(new String(sb), "Exception");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_VALUE_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.put("DoubleValue", new String(sb));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.put("DoubleValue", "Exception");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGet() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putBoolean("BoolValue", true);
        pref.flush();

        assertEquals("String", pref.get("Value", ":"));
        assertEquals("true", pref.get("BoolValue", ":"));
        assertEquals("9.10938188E-31", pref.get("DoubleValue", null));

        try {
            pref.get(null, "Exception");
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.get("DoubleValue", "Exception");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testRemove() throws BackingStoreException {
        String[] keyArray = new String[]{"Value", "DoubleValue", "LongValue", "IntValue"};
        pref.put(keyArray[0], "String");
        pref.putDouble(keyArray[1], new Double(9.10938188e-31));
        pref.putLong(keyArray[2], new Long(Long.MIN_VALUE));
        pref.putInt(keyArray[3], 299792458);
        pref.node("New node");
        pref.flush();

        String[] str = pref.keys();
        assertEquals(keyArray.length, str.length);
        for(int i = 0; i < keyArray.length; i++) {
            pref.remove(keyArray[i]);
            str = pref.keys();
            assertEquals(keyArray.length - i - 1, str.length);
        }
        assertEquals(1, pref.childrenNames().length);
        pref.remove("New node");
        assertEquals(1, pref.childrenNames().length);

        pref.removeNode();

        try {
            pref.remove("New node");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testClear() throws BackingStoreException {
        AbstractPreferences ap = (AbstractPreferences) pref.node("New node");
        pref.putInt("IntValue", 33);
        pref.putBoolean("BoolValue", true);
        pref.flush();
        assertTrue(pref.getBoolean("BoolValue", false));
        assertEquals(33, pref.getInt("IntValue", 22));
        assertEquals(1, pref.childrenNames().length);
        pref.clear();
        assertFalse(pref.getBoolean("BoolValue", false));
        assertEquals(22, pref.getInt("IntValue", 22));
        assertEquals(1, pref.childrenNames().length);

        pref.removeNode();

        try {
            pref.clear();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        try {
            ap.clear();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutInt() throws BackingStoreException {
        pref.putInt("IntValue", 299792458);
        pref.flush();

        assertEquals(299792458, pref.getInt("IntValue", new Integer(1)));

        try {
            pref.putInt(null, new Integer(1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putInt(new String(sb), new Integer(1));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putInt("IntValue", new Integer(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetInt() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putLong("LongValue", new Long(Long.MIN_VALUE));
        pref.putInt("IntValue", 299792458);
        pref.flush();

        assertEquals(1, pref.getInt("Value", new Integer(1)));
        assertEquals(1, pref.getInt("LongValue", new Integer(1)));
        assertEquals(1, pref.getInt("DoubleValue", new Integer(1)));
        assertEquals(299792458, pref.getInt("IntValue", new Integer(1)));

        try {
            pref.getInt(null, new Integer(1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getInt("IntValue", new Integer(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutLong() throws BackingStoreException {
        pref.putLong("LongValue", new Long(299792458));
        pref.flush();

        assertEquals(299792458L, pref.getLong("LongValue", new Long(1)));

        try {
            pref.putLong(null, new Long(1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putLong(new String(sb), new Long(1));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putLong("LongValue", new Long(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetLong() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putLong("LongValue", new Long(Long.MIN_VALUE));
        pref.putInt("IntValue", 299792458);
        pref.flush();

        assertEquals(1L, pref.getLong("Value", new Long(1)));
        assertEquals(Long.MIN_VALUE, pref.getLong("LongValue", new Long(1)));
        assertEquals(1L, pref.getLong("DoubleValue", new Long(1)));
        assertEquals(299792458L, pref.getLong("IntValue", new Long(1)));

        try {
            pref.getLong(null, new Long(1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getLong("LongValue", new Long(1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutBoolean() throws BackingStoreException {
        pref.putBoolean("BoolValue", true);
        pref.flush();

        assertTrue(pref.getBoolean("BoolValue", false));

        try {
            pref.putBoolean(null, true);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putBoolean(new String(sb), true);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putBoolean("DoubleValue", true);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetBoolean() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putBoolean("BoolValue", true);
        pref.flush();

        assertFalse(pref.getBoolean("Value", false));
        assertTrue(pref.getBoolean("BoolValue", false));
        assertFalse(pref.getBoolean("DoubleValue", false));

        try {
            pref.getBoolean(null, true);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getBoolean("DoubleValue", true);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutFloat() throws BackingStoreException {
        pref.putFloat("FloatValue", new Float(1.602e-19));
        pref.flush();

        assertEquals(new Float(1.602e-19), pref.getFloat("FloatValue", new Float(0.2)));

        try {
            pref.putFloat(null, new Float(0.1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putFloat(new String(sb), new Float(0.1));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putFloat("FloatValue", new Float(0.1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetFloat() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putFloat("FloatValue", new Float(-0.123));
        pref.putInt("IntValue", 299792458);
        pref.flush();

        assertEquals(new Float(0.1), pref.getFloat("Value", new Float(0.1)));
        assertEquals(new Float(-0.123), pref.getFloat("FloatValue", new Float(0.2)));
        assertEquals(new Float(9.109382e-31), pref.getFloat("DoubleValue", new Float(2.14)));
        assertEquals(new Float(2.99792448e8), pref.getFloat("IntValue", new Float(5)));

        try {
            pref.getFloat(null, new Float(0.1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getFloat("FloatValue", new Float(0.1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutDouble() throws BackingStoreException {
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.flush();

        assertEquals(new Double(9.10938188e-31), pref.getDouble("DoubleValue", new Double(2.14)));

        try {
            pref.putDouble(null, new Double(0.1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        int i;
        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putDouble(new String(sb), new Double(0.1));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putDouble("DoubleValue", new Double(0.1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetDouble() throws BackingStoreException {
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putBoolean("BoolValue", true);
        pref.putInt("IntValue", 299792458);
        pref.flush();

        assertEquals(new Double(0.1), pref.getDouble("Value", new Double(0.1)));
        assertEquals(new Double(0.2), pref.getDouble("BoolValue", new Double(0.2)));
        assertEquals(new Double(9.10938188e-31), pref.getDouble("DoubleValue", new Double(2.14)));
        assertEquals(new Double(2.99792458e8), pref.getDouble("IntValue", new Double(5)));

        try {
            pref.getDouble(null, new Double(0.1));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getDouble("DoubleValue", new Double(0.1));
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testPutByteArray() throws BackingStoreException {
        byte[] bArray = new byte[]{1, 2, 3, 4, 5};
        byte[] array  = null;
        int i;
        pref.putByteArray("Array", bArray);
        pref.flush();

        array = pref.getByteArray("Array", null);
        assertEquals(bArray.length, array.length);
        for(i = 0; i < bArray.length; i++) {
            assertEquals(bArray[i], array[i]);
        }

        try {
            pref.putByteArray(null, bArray);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        StringBuffer sb = new StringBuffer();

        for (i = 0; i < Preferences.MAX_KEY_LENGTH + 1; i++) {
            sb.append('c');
        }

        try {
            pref.putByteArray(new String(sb), bArray);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        bArray = new byte[Preferences.MAX_VALUE_LENGTH * 3 / 4 + 1];

        try {
            pref.putByteArray("Big array", bArray);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.putByteArray("Array", new byte[10]);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testGetByteArray() throws BackingStoreException {
        byte[] bArray = new byte[]{1, 2, 3, 4, 5};
        byte[] tmp    = new byte[]{5};
        byte[] array  = null;
        int i;
        pref.put("Value", "String");
        pref.putDouble("DoubleValue", new Double(9.10938188e-31));
        pref.putByteArray("Array", bArray);
        pref.flush();

        array = pref.getByteArray("Value", tmp);
        assertEquals(tmp.length, array.length);
        for(i = 0; i < tmp.length; i++) {
            assertEquals(tmp[i], array[i]);
        }

        array = pref.getByteArray("DoubleValue", tmp);
        assertEquals(tmp.length, array.length);
        for(i = 0; i < tmp.length; i++) {
            assertEquals(tmp[i], array[i]);
        }

        array = pref.getByteArray("Array", tmp);
        assertEquals(bArray.length, array.length);
        for(i = 0; i < bArray.length; i++) {
            assertEquals(bArray[i], array[i]);
        }

        try {
            pref.getByteArray(null, tmp);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.getByteArray("Array", tmp);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testKeys() throws BackingStoreException {
        String[] keyArray = new String[]{"Value", "DoubleValue", "BoolValue", "IntValue"};
        String nodeStr = "New node";
        pref.node(nodeStr);
        pref.put(keyArray[0], "String");
        pref.putDouble(keyArray[1], new Double(9.10938188e-31));
        pref.putBoolean(keyArray[2], true);
        pref.putInt(keyArray[3], 299792458);
        pref.flush();

        String[] str = pref.keys();
        assertEquals(keyArray.length, str.length);
        for(int i = 0; i < str.length; i++) {
            boolean flag = false;
            for(int j = 0; j < keyArray.length; j++) {
                if (str[i].compareTo(keyArray[j]) == 0) {
                    flag = true;
                    break;
                }
            }
            assertTrue(str[i].compareTo(nodeStr) != 0);
            assertTrue(flag);
        }

        pref.removeNode();

        try {
            pref.keys();
            fail("IllegalStateException expected");
        } catch(IllegalStateException e) {
            //expected
        }
    }

    public void testChildrenNames() throws BackingStoreException {
        AbstractPreferences first = (AbstractPreferences) pref.node("First node");
        AbstractPreferences second = (AbstractPreferences) pref.node("Second node");

        assertEquals(2, pref.childrenNames().length);
        assertEquals(0, first.childrenNames().length);
        assertEquals(0, second.childrenNames().length);

        second.removeNode();

        try {
            second.childrenNames();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        pref.removeNode();

        try {
            first.childrenNames();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void test_nodeExists() throws BackingStoreException {
        AbstractPreferences test = (AbstractPreferences) Preferences.userRoot()
                .node("test");
        try {
            test.nodeExists(null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }

        test.removeNode();
        try {
            test.nodeExists(null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    public void testParent() throws BackingStoreException {
        AbstractPreferences node = (AbstractPreferences) pref.node("First node/sub node");

        assertTrue(node.parent().name().compareTo("First node") == 0);

        pref.removeNode();

        try {
            node.parent();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testNode() throws BackingStoreException {
        AbstractPreferences first = (AbstractPreferences) pref.node("First node");
        AbstractPreferences second = (AbstractPreferences) pref.node("Second node");

        try {
            first.node("blabla/");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            first.node("///invalid");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < Preferences.MAX_NAME_LENGTH; i++) {
            sb.append('c');
        }
        first.node(new String(sb));
        sb.append('c');

        try {
            first.node(new String(sb));
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        second.removeNode();

        try {
            second.node("");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        pref.removeNode();
        try {
            first.node("");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testNodeExists() throws BackingStoreException {
        AbstractPreferences ap1 = (AbstractPreferences) pref.node("First node");
        AbstractPreferences ap2 = (AbstractPreferences) pref.node("Second node");
        pref.putInt("IntegerValue", 33);
        pref.putBoolean("BoolValue", true);
        pref.flush();

        assertTrue(pref.nodeExists("First node"));
        assertTrue(pref.nodeExists("Second node"));
        assertFalse(pref.nodeExists("IntegerValue"));
        assertFalse(pref.nodeExists("BoolValue"));
        assertFalse(pref.nodeExists("Value"));
        assertFalse(pref.nodeExists(nodeName));

        try {
            pref.nodeExists("///invalid");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        pref.removeNode();

        try {
            pref.nodeExists("Exception");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    public void testRemoveNode() throws BackingStoreException {
        String[] nodeArray = new String[]{"First node", "Second node", "Last node"};
        int i;
        pref.put("Key", "String");
        for (i = 0; i < nodeArray.length; i++) {
            pref.node(nodeArray[i]);
        }
        pref.flush();

        String[] str = pref.childrenNames();
        assertEquals(nodeArray.length, str.length);
        for(i = 0; i < nodeArray.length; i++) {
            pref.node(nodeArray[i]).removeNode();
            str = pref.childrenNames();
            assertEquals(nodeArray.length - i - 1, str.length);
        }
        assertEquals(1, pref.keys().length);
        pref.node("Key").removeNode();
        assertEquals(1, pref.keys().length);

        pref.removeNode();

        try {
            pref.removeNode();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        try {
            root.removeNode();
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    public void testName() {
        AbstractPreferences first = (AbstractPreferences) pref.node("First node");
        AbstractPreferences second = (AbstractPreferences) pref.node("Second node/sub node");

        assertTrue(first.name().compareTo("First node") == 0);
        assertFalse(first.name().compareTo("Second node") == 0);
        assertTrue(second.name().compareTo("sub node") == 0);
    }

    public void testAbsolutePath() {
        assertEquals(parent.absolutePath() + "/" + nodeName, pref.absolutePath());
        assertEquals(parent.absolutePath() + "/" + "new node", parent.node("new node").absolutePath());
    }

    public void testIsUserNode() {
        assertTrue(parent.isUserNode());
        assertFalse(Preferences.systemRoot().isUserNode());
    }

    public void testSync() throws BackingStoreException {
        pref.node("new node/sub node");
        pref.sync();

        pref.removeNode();

        try {
            pref.sync();
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
    }

    class MockPreferenceChangeListener implements PreferenceChangeListener {
        private boolean flagChange = false;

        public synchronized void preferenceChange(PreferenceChangeEvent arg0) {
            flagChange = true;
            notifyAll();
        }

        public synchronized void assertChanged(boolean expected) throws InterruptedException {
            wait(100);
            assertEquals(expected, flagChange);
            flagChange = false;
        }
    }

    public void testAddPreferenceChangeListener() throws Exception {
        MockPreferenceChangeListener mpcl = new MockPreferenceChangeListener();
        parent.addPreferenceChangeListener(mpcl);
        mpcl.assertChanged(false);
        pref.node("new node");
        mpcl.assertChanged(false);
        parent.node("new node");
        mpcl.assertChanged(false);
        parent.putInt("IntValue", 33);
        mpcl.assertChanged(true);
        assertEquals(33, parent.getInt("IntValue", 22));
        mpcl.assertChanged(false);
        assertEquals(22, parent.getInt("Missed Value", 22));
        mpcl.assertChanged(false);
    }

    public void testRemovePreferenceChangeListener() throws Exception {
        MockPreferenceChangeListener mpcl = new MockPreferenceChangeListener();
        parent.addPreferenceChangeListener(mpcl);
        mpcl.assertChanged(false);
        parent.putInt("IntValue", 33);
        mpcl.assertChanged(true);
        parent.removePreferenceChangeListener(mpcl);
        parent.putInt("IntValue", 33);
        mpcl.assertChanged(false);
    }

    class MockNodeChangeListener implements NodeChangeListener {
        private boolean flagAdded = false;
        private boolean flagRemoved = false;

        public synchronized void childAdded(NodeChangeEvent arg0) {
            flagAdded = true;
            notifyAll();
        }

        public synchronized void childRemoved(NodeChangeEvent arg0) {
            flagRemoved = true;
            notifyAll();
        }

        public synchronized void assertAdded(boolean expected) throws InterruptedException {
            wait(100);
            assertEquals(expected, flagAdded);
        }

        public synchronized void assertRemoved(boolean expected) throws InterruptedException {
            wait(100);
            assertEquals(expected, flagRemoved);
        }
    }

    public void testAddNodeChangeListener() throws Exception {
        MockNodeChangeListener mncl = new MockNodeChangeListener();
        parent.addNodeChangeListener(mncl);
        pref.node("test");
        mncl.assertAdded(false);
        mncl.assertRemoved(false);
        pref.removeNode();
        mncl.assertAdded(false);
        mncl.assertRemoved(true);
        parent.node("new node");
        mncl.assertAdded(true);
        mncl.assertRemoved(true);
    }

    public void testRemoveNodeChangeListener() throws BackingStoreException, InterruptedException {
        MockNodeChangeListener mncl = new MockNodeChangeListener();
        parent.addNodeChangeListener(mncl);
        pref.node("test");
        mncl.assertAdded(false);
        mncl.assertRemoved(false);
        parent.removeNodeChangeListener(mncl);
        pref.removeNode();
        mncl.assertAdded(false);
        mncl.assertRemoved(false);
        parent.node("new node");
        mncl.assertAdded(false);
        mncl.assertRemoved(false);
    }

    public void testExportNode() throws BackingStoreException, IOException, InvalidPreferencesFormatException {
        AbstractPreferences ap = (AbstractPreferences) pref.node("New node");
        pref.putInt("IntValue", 33);
        pref.putBoolean("BoolValue", true);
        pref.flush();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        pref.exportNode(baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        assertTrue(pref.getBoolean("BoolValue", false));
        assertEquals(33, pref.getInt("IntValue", 22));
        assertEquals(1, pref.childrenNames().length);

        String xmlData = new String(baos.toByteArray());

        assertTrue(xmlData.contains("IntValue"));
        assertTrue(xmlData.contains("BoolValue"));
        assertTrue(xmlData.contains("33"));
        assertTrue(xmlData.contains("true"));

        pref.removeNode();

        try {
            pref.exportNode(new ByteArrayOutputStream());
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        try {
            pref.getBoolean("BoolValue", false);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        pref = (AbstractPreferences) parent.node(nodeName);

        pref.importPreferences(bais);

        assertTrue(pref.getBoolean("BoolValue", false));
        assertEquals(33, pref.getInt("IntValue", 22));
        assertEquals(0, pref.childrenNames().length);
    }

    public void testExportSubtree() throws BackingStoreException, IOException, InvalidPreferencesFormatException {
        AbstractPreferences ap1 = (AbstractPreferences) pref.node("First node");
        AbstractPreferences ap2 = (AbstractPreferences) pref.node("Second node");
        pref.putInt("IntegerValue", 33);
        pref.putBoolean("BoolValue", true);
        pref.flush();

        ap1.putInt("FirstIntValue", 11);
        ap2.putDouble("DoubleValue", new Double(6.626e-34));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        pref.exportSubtree(baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        assertTrue(pref.getBoolean("BoolValue", false));
        assertEquals(33, pref.getInt("IntegerValue", 22));
        assertEquals(2, pref.childrenNames().length);
        assertEquals(11, ap1.getInt("FirstIntValue", 22));
        assertEquals(new Double(6.626e-34), ap2.getDouble("DoubleValue", new Double (3.14)));

        String xmlData = new String(baos.toByteArray());

        assertTrue(xmlData.contains("IntegerValue"));
        assertTrue(xmlData.contains("BoolValue"));
        assertTrue(xmlData.contains("FirstIntValue"));
        assertTrue(xmlData.contains("DoubleValue"));
        assertTrue(xmlData.contains("33"));
        assertTrue(xmlData.contains("true"));
        assertTrue(xmlData.contains("11"));
        assertTrue(xmlData.contains("6.626E-34"));

        pref.removeNode();

        try {
            pref.exportSubtree(new ByteArrayOutputStream());
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }

        try {
            pref.getBoolean("BoolValue", false);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            //expected
        }
        pref = (AbstractPreferences) parent.node(nodeName);
        pref.importPreferences(bais);

        ap1 = (AbstractPreferences) pref.node("First node");
        ap2 = (AbstractPreferences) pref.node("Second node");

        assertTrue(pref.getBoolean("BoolValue", false));
        assertEquals(33, pref.getInt("IntegerValue", 22));
        assertEquals(2, pref.childrenNames().length);
        assertEquals(11, ap1.getInt("FirstIntValue", 22));
        assertEquals(new Double(6.626e-34), ap2.getDouble("DoubleValue", new Double (3.14)));
    }

    class MockAbstractPreferences extends AbstractPreferences {
        protected MockAbstractPreferences(AbstractPreferences parent, String name) {
            super(parent, name);
        }

        @Override
        protected AbstractPreferences childSpi(String name) {
            return null;
        }

        @Override
        protected String[] childrenNamesSpi() throws BackingStoreException {
            return null;
        }

        @Override
        protected void flushSpi() throws BackingStoreException {
        }

        @Override
        protected String getSpi(String key) {
            return null;
        }

        @Override
        protected String[] keysSpi() throws BackingStoreException {
            return null;
        }

        @Override
        protected void putSpi(String key, String value) {
        }

        @Override
        protected void removeNodeSpi() throws BackingStoreException {
        }

        @Override
        protected void removeSpi(String key) {
        }

        @Override
        protected void syncSpi() throws BackingStoreException {
        }
    }

    public void testAbstractPreferences() {
        assertNotNull(new MockAbstractPreferences(pref, "node name"));
        try {
            new MockAbstractPreferences(pref, "node/name");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }

        try {
            new MockAbstractPreferences(null, "node");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    public void testCachedChildren() throws BackingStoreException {
        pref.node("First node");
        pref.node("Second node");

        assertEquals(2, pref.childrenNames().length);
    }
}
