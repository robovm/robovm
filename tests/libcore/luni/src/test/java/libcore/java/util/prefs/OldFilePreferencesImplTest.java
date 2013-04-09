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

import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class OldFilePreferencesImplTest extends TestCase {

    // AndroidOnly: the RI can't remove nodes created in the system root.
    public void testSystemChildNodes() throws Exception {
        Preferences sroot = Preferences.systemRoot().node("test");

        Preferences child1 = sroot.node("child1");
        Preferences child2 = sroot.node("child2");
        Preferences grandchild = child1.node("grand");

        String[] childNames = sroot.childrenNames();
        assertContains(childNames, "child1");
        assertContains(childNames, "child2");
        assertNotContains(childNames, "grand");

        childNames = child1.childrenNames();
        assertEquals(1, childNames.length);

        childNames = child2.childrenNames();
        assertEquals(0, childNames.length);

        child1.removeNode();
        childNames = sroot.childrenNames();
        assertNotContains(childNames, "child1");
        assertContains(childNames, "child2");
        assertNotContains(childNames, "grand");

        child2.removeNode();
        childNames = sroot.childrenNames();
        assertNotContains(childNames, "child1");
        assertNotContains(childNames, "child2");
        assertNotContains(childNames, "grand");
        sroot.removeNode();
    }

    private void assertContains(String[] childNames, String name) {
        for (String childName : childNames) {
            if (childName == name) {
                return;
            }
        }
        fail("No child with name " + name + " was found. It was expected to exist.");
    }

    private void assertNotContains(String[] childNames, String name) {
        for (String childName : childNames) {
            if (childName == name) {
                fail("Child with name " + name + " was found. This was unexpected.");
            }
        }
    }
}
