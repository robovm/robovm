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

import java.util.prefs.AbstractPreferences;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class OldPreferenceChangeEventTest extends TestCase {

    public void testGetKey() {
        AbstractPreferences parent = (AbstractPreferences) Preferences
                .userNodeForPackage(Preferences.class);

        AbstractPreferences pref = (AbstractPreferences) parent.node("mock");

        MockPreferenceChangeListener pl = new MockPreferenceChangeListener() {
            public void preferenceChange(PreferenceChangeEvent pce) {
                if (pce != null && pce.getKey().equals("key_int")) {
                    result = true;
                }
                super.preferenceChange(pce);
            }
        };
        pref.addPreferenceChangeListener(pl);
        try {
            pref.putInt("key_int", Integer.MAX_VALUE);
            assertEquals(1, pl.getChanged());
            assertTrue(pl.getResult());
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
        }
    }

    public void testGetNewValue() {
        AbstractPreferences parent = (AbstractPreferences) Preferences
                .userNodeForPackage(Preferences.class);

        AbstractPreferences pref = (AbstractPreferences) parent.node("mock");

        MockPreferenceChangeListener pl = new MockPreferenceChangeListener() {
            public void preferenceChange(PreferenceChangeEvent pce) {
                if (pce != null && pce.getNewValue().equals(Integer.toString(Integer.MAX_VALUE))) {
                    result = true;
                }
                super.preferenceChange(pce);
            }
        };
        pref.addPreferenceChangeListener(pl);
        try {
            pref.putInt("key_int", Integer.MAX_VALUE);
            assertEquals(1, pl.getChanged());
            assertTrue(pl.getResult());
            pl.reset();

            pref.putInt("key_int", Integer.MAX_VALUE);
            assertEquals(1, pl.getChanged());
            assertTrue(pl.getResult());
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
        }
    }

    public void testGetNode() {
        AbstractPreferences parent = (AbstractPreferences) Preferences
                .userNodeForPackage(Preferences.class);

        AbstractPreferences pref = (AbstractPreferences) parent.node("mock");

        MockPreferenceChangeListener pl = new MockPreferenceChangeListener() {
            public void preferenceChange(PreferenceChangeEvent pce) {
                if (pce != null && "mock".equals(pce.getNode().name())) {
                    result = true;
                }
                super.preferenceChange(pce);
            }
        };
        pref.addPreferenceChangeListener(pl);
        try {
            pref.putInt("key_int", Integer.MAX_VALUE);
            assertEquals(1, pl.getChanged());
            assertTrue(pl.getResult());
            pl.reset();
        } finally {
            pref.removePreferenceChangeListener(pl);
        }
    }

    private static class MockPreferenceChangeListener implements PreferenceChangeListener {
        private int changed = 0;
        private boolean addDispatched = false;
        protected boolean result = false;

        public synchronized void waitForEvent() {
            try {
                wait(500);
            } catch (InterruptedException expected) {
            }
        }

        public synchronized void preferenceChange(PreferenceChangeEvent pce) {
            changed++;
            addDispatched = true;
            notifyAll();
        }

        public synchronized boolean getResult() {
            if (!addDispatched) {
                try {
                    // TODO: don't know why must add limitation
                    this.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            addDispatched = false;
            return result;
        }

        public synchronized int getChanged() {
            if (!addDispatched) {
                try {
                    // TODO: don't know why must add limitation
                    this.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            addDispatched = false;
            return changed;
        }

        public void reset() {
            changed = 0;
            result = false;
        }
    }
}
