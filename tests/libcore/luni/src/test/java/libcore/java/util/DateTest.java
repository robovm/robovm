/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import junit.framework.TestCase;

public class DateTest extends TestCase {
    // http://code.google.com/p/android/issues/detail?id=6013
    public void test_toString() throws Exception {
        // Ensure that no matter where this is run, we know what time zone
        // to expect. (Though we still assume an "en" locale.)
        TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
        assertEquals("Wed Dec 31 18:00:00 CST 1969", new Date(0).toString());
    }

    public void test_toGMTString() throws Exception {
        // Based on https://issues.apache.org/jira/browse/HARMONY-501
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, 21);
        assertEquals("Wed Jan 01 00:00:00 PST 21", c.getTime().toString());
        assertEquals("1 Jan 21 08:00:00 GMT", c.getTime().toGMTString());
        c.set(Calendar.YEAR, 321);
        assertEquals("Sun Jan 01 00:00:00 PST 321", c.getTime().toString());
        assertEquals("1 Jan 321 08:00:00 GMT", c.getTime().toGMTString());
    }
}
