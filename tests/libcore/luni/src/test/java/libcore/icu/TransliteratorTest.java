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

public class TransliteratorTest extends junit.framework.TestCase {
  public void testAll() throws Exception {
    for (String id : Transliterator.getAvailableIDs()) {
      System.err.println(id);
      Transliterator t = new Transliterator(id);
      t.transliterate("hello");
    }
  }

  public void test_Unknown() throws Exception {
    try {
      Transliterator t = new Transliterator("Unknown");
      fail();
    } catch (RuntimeException expected) {
    }
  }

  public void test_null_id() throws Exception {
    try {
      Transliterator t = new Transliterator(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void test_null_string() throws Exception {
    try {
      Transliterator t = new Transliterator("Any-Upper");
      t.transliterate(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void test_Any_Upper() throws Exception {
    Transliterator t = new Transliterator("Any-Upper");
    assertEquals("HELLO WORLD!", t.transliterate("HeLlO WoRlD!"));
    assertEquals("STRASSE", t.transliterate("Straße"));
  }

  public void test_Any_Lower() throws Exception {
    Transliterator t = new Transliterator("Any-Lower");
    assertEquals("hello world!", t.transliterate("HeLlO WoRlD!"));
  }

  public void test_Greek_Latin() throws Exception {
    String greek = "Καλημέρα κόσμε!";

    // Transliterate Greek to Latin, then to plain ASCII.
    Transliterator t = new Transliterator("Greek-Latin");
    String latin = t.transliterate(greek);
    t = new Transliterator("Latin-Ascii");
    String ascii = t.transliterate(latin);
    assertEquals("Kalēméra kósme!", latin);
    assertEquals("Kalemera kosme!", ascii);

    // Use alternative transliteration variants.
    t = new Transliterator("Greek-Latin/BGN");
    assertEquals("Kaliméra kósme!", t.transliterate(greek));
    t = new Transliterator("Greek-Latin/UNGEGN");
    assertEquals("Kali̱méra kósme!",t.transliterate(greek));
  }

  public void test_Han_Latin() throws Exception {
    Transliterator t = new Transliterator("Han-Latin");
    assertEquals("hàn zì/hàn zì", t.transliterate("汉字/漢字"));

    assertEquals("chén", t.transliterate("\u6c88"));
    assertEquals("shěn", t.transliterate("\u700b"));
    assertEquals("jiǎ", t.transliterate("\u8d3e"));

    t = new Transliterator("Han-Latin/Names");
    assertEquals("shěn", t.transliterate("\u6c88"));
    assertEquals("shěn", t.transliterate("\u700b"));
    assertEquals("jiǎ", t.transliterate("\u8d3e"));

    t = new Transliterator("Han-Latin/Names; Latin-Ascii; Any-Upper");
    assertEquals("SHEN", t.transliterate("\u6c88"));
    assertEquals("SHEN", t.transliterate("\u700b"));
    assertEquals("JIA", t.transliterate("\u8d3e"));
  }
}
