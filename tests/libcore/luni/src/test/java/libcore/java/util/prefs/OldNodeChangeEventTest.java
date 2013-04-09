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
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeEvent;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class OldNodeChangeEventTest extends TestCase {

    public void testGetChild() throws BackingStoreException {
        AbstractPreferences parent = (AbstractPreferences) Preferences
                .userNodeForPackage(Preferences.class);

        AbstractPreferences pref = (AbstractPreferences) parent.node("mock");

        MockNodeChangeListener nl = new MockNodeChangeListener() {
            public synchronized void childAdded(NodeChangeEvent e) {
                Preferences child = e.getChild();
                if (child == null) {
                    addResult = false;
                } else {
                    if (child.name() == "mock1") {
                        addResult = true;
                    }
                }
                super.childAdded(e);
            }

            public synchronized void childRemoved(NodeChangeEvent e) {
                Preferences child = e.getChild();
                if (child == null) {
                    removeResult = false;
                } else {
                    if (child.name() == "mock1") {
                        removeResult = true;
                    }
                }
                super.childRemoved(e);
            }
        };
        try {
            pref.addNodeChangeListener(nl);
            Preferences child1 = pref.node("mock1");
            nl.waitForEvent();
            assertEquals(1, nl.getAdded());
            assertTrue(nl.getAddResult());
            nl.reset();
            child1.removeNode();
            nl.waitForEvent();
            assertEquals(1, nl.getRemoved());
            assertTrue(nl.getRemoveResult());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
        }
    }

    public void testGetParent() throws BackingStoreException {
        AbstractPreferences parent = (AbstractPreferences) Preferences
                .userNodeForPackage(Preferences.class);

        AbstractPreferences pref = (AbstractPreferences) parent.node("mock");

        MockNodeChangeListener nl = new MockNodeChangeListener() {
            public synchronized void childAdded(NodeChangeEvent e) {
                Preferences parent = e.getParent();
                if (parent == null) {
                    addResult = false;
                } else {
                    if (parent.name() == "mock") {
                        addResult = true;
                    }
                }
                super.childAdded(e);
            }

            public synchronized void childRemoved(NodeChangeEvent e) {
                Preferences parent = e.getParent();
                if (parent == null) {
                    removeResult = false;
                } else {
                    if (parent.name() == "mock") {
                        removeResult = true;
                    }
                }
                super.childRemoved(e);
            }
        };
        try {
            pref.addNodeChangeListener(nl);
            Preferences child1 = pref.node("mock1");
            nl.waitForEvent();
            assertEquals(1, nl.getAdded());
            assertTrue(nl.getAddResult());
            nl.reset();
            child1.removeNode();
            nl.waitForEvent();
            assertEquals(1, nl.getRemoved());
            assertTrue(nl.getRemoveResult());
            nl.reset();
        } finally {
            pref.removeNodeChangeListener(nl);
        }
    }

    private static class MockNodeChangeListener implements NodeChangeListener {
        private int added = 0;
        private int removed = 0;
        protected boolean addResult = false;
        protected boolean removeResult = false;

        public synchronized void waitForEvent() {
            try {
                wait(500);
            } catch (InterruptedException expected) {
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

        public synchronized boolean getAddResult() {
            return addResult;
        }

        public synchronized boolean getRemoveResult() {
            return removeResult;
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
}
