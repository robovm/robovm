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

package libcore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.TimeZone;

public class ZoneInfoDBTest extends junit.framework.TestCase {
  private static final String CURRENT_VERSION = ZoneInfoDB.getInstance().getVersion();

  // Any new file in /data...
  private static final String TZDATA_IN_DATA = System.getenv("ANDROID_DATA") + "/misc/zoneinfo/tzdata";
  // ...overrides any existing file in /system.
  private static final String TZDATA_IN_ROOT = System.getenv("ANDROID_ROOT") + "/usr/share/zoneinfo/tzdata";

  // An empty override file should fall back to the default file.
  public void testEmptyOverrideFile() throws Exception {
     // RoboVM note: Use ZoneInfoDB.TzDataAndroid.
    ZoneInfoDB.TzData data = new ZoneInfoDB.TzDataAndroid(makeEmptyFile(), TZDATA_IN_DATA, TZDATA_IN_ROOT);
    assertEquals(CURRENT_VERSION, data.getVersion());
    assertEquals(TimeZone.getAvailableIDs().length, data.getAvailableIDs().length);
  }

  // A corrupt override file should fall back to the default file.
  public void testCorruptOverrideFile() throws Exception {
     // RoboVM note: Use ZoneInfoDB.TzDataAndroid.
    ZoneInfoDB.TzData data = new ZoneInfoDB.TzDataAndroid(makeCorruptFile(), TZDATA_IN_DATA, TZDATA_IN_ROOT);
    assertEquals(CURRENT_VERSION, data.getVersion());
    assertEquals(TimeZone.getAvailableIDs().length, data.getAvailableIDs().length);
  }

  // Given no tzdata files we can use, we should fall back to built-in "GMT".
  public void testNoGoodFile() throws Exception {
     // RoboVM note: Use ZoneInfoDB.TzDataAndroid.
    ZoneInfoDB.TzData data = new ZoneInfoDB.TzDataAndroid(makeEmptyFile());
    assertEquals("missing", data.getVersion());
    assertEquals(1, data.getAvailableIDs().length);
    assertEquals("GMT", data.getAvailableIDs()[0]);
  }

  // Given a valid override file, we should find ourselves using that.
  public void testGoodOverrideFile() throws Exception {
    // We copy /system/usr/share/zoneinfo/tzdata because we know that always exists.
    RandomAccessFile in = new RandomAccessFile(TZDATA_IN_ROOT, "r");
    byte[] content = new byte[(int) in.length()];
    in.readFully(content);
    // Bump the version number to one long past where humans will be extinct.
    content[6] = '9';
    content[7] = '9';
    content[8] = '9';
    content[9] = '9';
    content[10] = 'z';
    in.close();

    String goodFile = makeTemporaryFile(content);
    try {
     // RoboVM note: Use ZoneInfoDB.TzDataAndroid.
      ZoneInfoDB.TzData data = new ZoneInfoDB.TzDataAndroid(goodFile, TZDATA_IN_DATA, TZDATA_IN_ROOT);
      assertEquals("9999z", data.getVersion());
      assertEquals(TimeZone.getAvailableIDs().length, data.getAvailableIDs().length);
    } finally {
      new File(goodFile).delete();
    }
  }

  private static String makeCorruptFile() throws Exception {
    return makeTemporaryFile("invalid content".getBytes());
  }

  private static String makeEmptyFile() throws Exception {
    return makeTemporaryFile(new byte[0]);
  }

  private static String makeTemporaryFile(byte[] content) throws Exception {
    File f = File.createTempFile("temp-", ".txt");
    FileOutputStream fos = new FileOutputStream(f);
    fos.write(content);
    fos.close();
    return f.getPath();
  }
}
