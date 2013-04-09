/*
 * Copyright (C) 2011 The Android Open Source Project
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.prefs.Preferences;
import junit.framework.TestCase;

public final class PreferencesTest extends TestCase {

    /**
     * The preferences API is designed to be hostile towards files that exist
     * where it wants to store its XML data. http://b/3431233
     */
    public void testPreferencesClobbersExistingFiles() throws Exception {
        File userPrefs = new File(System.getProperty("user.home") + "/.java/.userPrefs/prefs.xml");
        FileWriter writer = new FileWriter(userPrefs);
        writer.write("lamb");
        writer.close();
        userPrefs.setReadable(false);
        userPrefs.setWritable(false);
        long oldLength = userPrefs.length();

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.sync();
        userPreferences.put("a", "lion");
        userPreferences.flush();
        assertTrue("Expected to exist " + userPrefs, userPrefs.exists());
        assertTrue("Expected file to be clobbered", oldLength != userPrefs.length());
    }

    public void testHtmlEncoding() throws Exception {
        Preferences parent = Preferences.userNodeForPackage(this.getClass());
        Preferences p = parent.node("testHtmlEncoding");
        p.put("key", "a<>&'\"\\b");
        p.flush();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        p.exportNode(baos);

        String s = new String(baos.toByteArray(), "UTF-8");
        assertTrue(s, s.contains("value=\"a&lt;&gt;&amp;'&quot;\\b\""));
    }
}
