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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.SimpleTimeZone;

public class TimeZoneTest extends junit.framework.TestCase {
    // http://code.google.com/p/android/issues/detail?id=877
    public void test_useDaylightTime_Taiwan() {
        TimeZone asiaTaipei = TimeZone.getTimeZone("Asia/Taipei");
        assertFalse("Taiwan doesn't use DST", asiaTaipei.useDaylightTime());
    }

    // http://code.google.com/p/android/issues/detail?id=8016
    public void test_useDaylightTime_Iceland() {
        TimeZone atlanticReykjavik = TimeZone.getTimeZone("Atlantic/Reykjavik");
        assertFalse("Reykjavik doesn't use DST", atlanticReykjavik.useDaylightTime());
    }

    // http://code.google.com/p/android/issues/detail?id=11542
    public void test_clone_SimpleTimeZone() {
        SimpleTimeZone stz = new SimpleTimeZone(21600000, "Central Standard Time");
        stz.setStartYear(1000);
        stz.inDaylightTime(new Date());
        stz.clone();
    }

    // http://b/3049014
    public void testCustomTimeZoneDisplayNames() {
        TimeZone tz0001 = new SimpleTimeZone(60000, "ONE MINUTE");
        TimeZone tz0130 = new SimpleTimeZone(5400000, "ONE HOUR, THIRTY");
        TimeZone tzMinus0130 = new SimpleTimeZone(-5400000, "NEG ONE HOUR, THIRTY");
        assertEquals("GMT+00:01", tz0001.getDisplayName(false, TimeZone.SHORT, Locale.US));
        assertEquals("GMT+01:30", tz0130.getDisplayName(false, TimeZone.SHORT, Locale.US));
        assertEquals("GMT-01:30", tzMinus0130.getDisplayName(false, TimeZone.SHORT, Locale.US));
    }

    // http://code.google.com/p/android/issues/detail?id=14395
    public void testPreHistoricInDaylightTime() throws Exception {
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        TimeZone.setDefault(tz);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = sdf.parse("1902-11-01T00:00:00.000+0800");
        assertEquals(-2119680000000L, date.getTime());
        assertEquals(-28800000, tz.getOffset(date.getTime()));
        assertFalse(tz.inDaylightTime(date));
        assertEquals("Fri Oct 31 08:00:00 PST 1902", date.toString());
        assertEquals("31 Oct 1902 16:00:00 GMT", date.toGMTString());
        // Any time before we have transition data is considered non-daylight, even in summer.
        date = sdf.parse("1902-06-01T00:00:00.000+0800");
        assertEquals(-28800000, tz.getOffset(date.getTime()));
        assertFalse(tz.inDaylightTime(date));
    }

    public void testPreHistoricOffsets() throws Exception {
        // The "Asia/Saigon" time zone has just a few transitions, and hasn't changed in a
        // long time, which is convenient for testing:
        //
        // libcore.util.ZoneInfo[Asia/Saigon,mRawOffset=25200000,mUseDst=false]
        // 0 : time=-2005974400 Fri Jun 08 16:53:20 1906 GMT+00:00 = Fri Jun 08 23:59:40 1906 ICT isDst=0 offset=  380 gmtOffset=25580
        // 1 : time=-1855983920 Fri Mar 10 16:54:40 1911 GMT+00:00 = Fri Mar 10 23:54:40 1911 ICT isDst=0 offset=    0 gmtOffset=25200
        // 2 : time=-1819954800 Tue Apr 30 17:00:00 1912 GMT+00:00 = Wed May 01 01:00:00 1912 ICT isDst=0 offset= 3600 gmtOffset=28800
        // 3 : time=-1220428800 Thu Apr 30 16:00:00 1931 GMT+00:00 = Thu Apr 30 23:00:00 1931 ICT isDst=0 offset=    0 gmtOffset=25200
        TimeZone tz = TimeZone.getTimeZone("Asia/Saigon");

        // Times before our first transition should assume we're still following that transition.
        // Note: the RI reports 25600 here because it has more transitions than we do.
        assertNonDaylightOffset(25580, -2005975000L, tz);

        assertNonDaylightOffset(25580, -2005974400L, tz); // 0
        assertNonDaylightOffset(25580, -2005974000L, tz);

        assertNonDaylightOffset(25200, -1855983920L, tz); // 1
        assertNonDaylightOffset(25200, -1855983900L, tz);

        assertNonDaylightOffset(28800, -1819954800L, tz); // 2
        assertNonDaylightOffset(28800, -1819954000L, tz);

        assertNonDaylightOffset(25200, -1220428800L, tz); // 3

        // Times after out last transition should assume we're still following that transition.
        assertNonDaylightOffset(25200, -1220428000L, tz);

        // There are plenty more examples. "Africa/Bissau" is one:
        //
        // libcore.util.ZoneInfo[Africa/Bissau,mRawOffset=0,mUseDst=false]
        // 0 : time=-1849388260 Fri May 26 01:02:20 1911 GMT+00:00 = Fri May 26 00:02:20 1911 GMT isDst=0 offset=-3600 gmtOffset=-3600
        // 1 : time=  157770000 Wed Jan 01 01:00:00 1975 GMT+00:00 = Wed Jan 01 01:00:00 1975 GMT isDst=0 offset=    0 gmtOffset=0
        tz = TimeZone.getTimeZone("Africa/Bissau");
        assertNonDaylightOffset(-3600, -1849388300L, tz);
        assertNonDaylightOffset(-3600, -1849388260L, tz); // 0
        assertNonDaylightOffset(-3600, -1849388200L, tz);
        assertNonDaylightOffset(0, 157770000L, tz); // 1
        assertNonDaylightOffset(0, 157780000L, tz);
    }

    private static void assertNonDaylightOffset(int expectedOffsetSeconds, long epochSeconds, TimeZone tz) {
        assertEquals(expectedOffsetSeconds * 1000, tz.getOffset(epochSeconds * 1000));
        assertFalse(tz.inDaylightTime(new Date(epochSeconds * 1000)));
    }

    public void testZeroTransitionZones() throws Exception {
        // Zones with no transitions historical or future seem ideal for testing.
        String[] ids = new String[] { "Africa/Bujumbura", "Indian/Cocos", "Pacific/Wake", "UTC" };
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            assertFalse(tz.useDaylightTime());
            assertFalse(tz.inDaylightTime(new Date(Integer.MIN_VALUE)));
            assertFalse(tz.inDaylightTime(new Date(0)));
            assertFalse(tz.inDaylightTime(new Date(Integer.MAX_VALUE)));
            int currentOffset = tz.getOffset(new Date(0).getTime());
            assertEquals(currentOffset, tz.getOffset(new Date(Integer.MIN_VALUE).getTime()));
            assertEquals(currentOffset, tz.getOffset(new Date(Integer.MAX_VALUE).getTime()));
        }
    }

    // http://code.google.com/p/android/issues/detail?id=16608
    public void testHelsinkiOverflow() throws Exception {
        long time = 2147483648000L;// Tue, 19 Jan 2038 03:14:08 GMT
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Helsinki");
        long offset = timeZone.getOffset(time);
        // This might cause us trouble if Europe/Helsinki changes its rules. See the bug for
        // details of the intent of this test.
        assertEquals(7200000, offset);
    }

    // http://code.google.com/p/android/issues/detail?id=11918
    public void testHasSameRules() throws Exception {
        TimeZone denver = TimeZone.getTimeZone ("America/Denver") ;
        TimeZone phoenix = TimeZone.getTimeZone ("America/Phoenix") ;
        assertFalse(denver.hasSameRules(phoenix));
    }

    // http://code.google.com/p/android/issues/detail?id=24036
    public void testNullId() throws Exception {
        try {
            TimeZone.getTimeZone(null);
            fail();
        } catch (NullPointerException expected) {
        }
    }
}
