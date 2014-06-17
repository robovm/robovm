/*
 * Copyright (C) 2013 The Android Open Source Project
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

package libcore.icu;

import java.util.Arrays;
import java.util.Locale;
import java.util.HashSet;
import java.util.TimeZone;

public class TimeZoneNamesTest extends junit.framework.TestCase {
  public void test_forLocale() throws Exception {
    String[] ids = TimeZoneNames.forLocale(Locale.CANADA);
    // Check that we got some ids.
    assertTrue(ids.length > 0);
    HashSet<String> allIds = new HashSet<String>(Arrays.asList(TimeZone.getAvailableIDs()));
    // Check that they're all real.
    for (String id : ids) {
      assertTrue(allIds.contains(id));
    }
    // Check that Toronto comes before Atikokan. http://b/8391426.
    int toronto = linearSearch(ids, "America/Toronto");
    assertTrue(toronto >= 0);
    int atikokan = linearSearch(ids, "America/Atikokan");
    assertTrue(atikokan >= 0);
    assertTrue(toronto < atikokan);
  }

  private int linearSearch(String[] xs, String x) {
    for (int i = 0; i < xs.length; ++i) {
      if (xs[i].equals(x)) {
        return i;
      }
    }
    return -1;
  }

  public void testBug9327819() throws Exception {
    // Check one specific example of a zone.tab line without a comment thoroughly.
    String[] ids = TimeZoneNames.forLocale(Locale.KOREA);
    assertEquals("Asia/Seoul", ids[0]);
    assertEquals(1, ids.length);

    // Now check we can parse all countries' lines.
    for (Locale l : Locale.getAvailableLocales()) {
      assertTrue(TimeZoneNames.forLocale(l) != null);
    }
  }
}
