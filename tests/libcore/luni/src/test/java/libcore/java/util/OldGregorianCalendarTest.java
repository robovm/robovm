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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import junit.framework.TestCase;

public final class OldGregorianCalendarTest extends TestCase {

    public void testGetHour() {
        // Test for method java.util.GregorianCalendar(java.util.TimeZone, java.util.Locale)
        Date now = new Date();

        GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"), Locale.FRANCE);
        gc1.setTime(now);
        GregorianCalendar gc2 = new GregorianCalendar(TimeZone.getTimeZone("GMT+2"), Locale.US);
        gc2.setTime(now);
        // CST is 1 hour before EST, add 1 to the CST time and convert to 0-12 value
        assertTrue(gc2.get(Calendar.HOUR) == ((gc1.get(Calendar.HOUR) + 1) % 12));
    }

    public void test_computeTime() {
        GregorianCalendar g1 = new GregorianCalendar(TimeZone.getTimeZone("Europe/Moscow"));
        g1.clear();
        g1.set(2006, -9, 29, 02, 50, 00); // transition from DST
        g1.setLenient(false);

        try {
            g1.getTimeInMillis();
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void test_computeFields() {
        GregorianCalendar g = new GregorianCalendar(
            TimeZone.getTimeZone("Europe/London"),
            new Locale("en", "GB"));
        g.clear();
        g.setTimeInMillis(1222185600225L);
        assertEquals(1,                  g.get(Calendar.ERA));
        assertEquals(2008, g.get(Calendar.YEAR));
        assertEquals(Calendar.SEPTEMBER, g.get(Calendar.MONTH));
        assertEquals(23,                 g.get(Calendar.DAY_OF_MONTH));
        assertEquals(17,                 g.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, g.get(Calendar.MINUTE));
    }

    public void test_hashCode() {
        GregorianCalendar g = new GregorianCalendar(
                TimeZone.getTimeZone("Europe/London"),
                new Locale("en", "GB"));
        g.clear();
        g.setTimeInMillis(1222185600225L);

        GregorianCalendar g1 = new GregorianCalendar(
                TimeZone.getTimeZone("Europe/Moscow"));
        g1.clear();
        g1.set(2008, Calendar.SEPTEMBER, 23, 18, 0, 0);
        assertNotSame(g.hashCode(), g1.hashCode());
    }

    public void test_setFirstDayOfWeekI() {
        GregorianCalendar g = new GregorianCalendar(
                TimeZone.getTimeZone("Europe/London"),
                new Locale("en", "GB"));

        for (int i = 0; i < 10; i++) {
            g.setFirstDayOfWeek(i);
            assertEquals(i, g.getFirstDayOfWeek());
        }
        g.setLenient(false);
        g.setFirstDayOfWeek(10);
        g.setFirstDayOfWeek(-10);
    }

    public void test_setMinimalDaysInFirstWeekI() {
        GregorianCalendar g = new GregorianCalendar(
                TimeZone.getTimeZone("Europe/London"),
                new Locale("en", "GB"));

        for (int i = 0; i < 10; i++) {
            g.setMinimalDaysInFirstWeek(i);
            assertEquals(i, g.getMinimalDaysInFirstWeek());
        }
        g.setLenient(false);
        g.setMinimalDaysInFirstWeek(10);
        g.setMinimalDaysInFirstWeek(-10);
    }
}
