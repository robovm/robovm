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

import java.util.Locale;

public class AlphabeticIndexTest extends junit.framework.TestCase {
  private static AlphabeticIndex.ImmutableIndex createIndex(Locale locale) {
    return new AlphabeticIndex(locale).addLabels(Locale.US)
        .getImmutableIndex();
  }

  private static void assertHasLabel(AlphabeticIndex.ImmutableIndex ii, String string, String expectedLabel) {
    int index = ii.getBucketIndex(string);
    String label = ii.getBucketLabel(index);
    assertEquals(expectedLabel, label);
  }

  public void test_en() throws Exception {
    // English [A-Z]
    AlphabeticIndex.ImmutableIndex en = createIndex(Locale.ENGLISH);
    assertHasLabel(en, "Allen", "A");
    assertHasLabel(en, "allen", "A");
  }

  public void test_ja() throws Exception {
    AlphabeticIndex.ImmutableIndex ja = createIndex(Locale.JAPANESE);

    // Japanese
    //   sorts hiragana/katakana, Kanji/Chinese, English, other
    // …, あ, か, さ, た, な, は, ま, や, ら, わ, …
    // hiragana "a"
    assertHasLabel(ja, "Allen", "A");
    assertHasLabel(ja, "\u3041", "\u3042");
    // katakana "a"
    assertHasLabel(ja, "\u30a1", "\u3042");

    // Kanji (sorts to inflow section)
    assertHasLabel(ja, "\u65e5", "");
    // http://bugs.icu-project.org/trac/ticket/10423 / http://b/10809397
    assertHasLabel(ja, "\u95c7", "");

    // English
    assertHasLabel(ja, "Smith", "S");

    // Chinese (sorts to inflow section)
    assertHasLabel(ja, "\u6c88" /* Shen/Chen */, "");

    // Korean Hangul (sorts to overflow section)
    assertHasLabel(ja, "\u1100", "");
  }

  public void test_ko() throws Exception {
    // Korean (sorts Korean, then English)
    // …, ᄀ, ᄂ, ᄃ, ᄅ, ᄆ, ᄇ, ᄉ, ᄋ, ᄌ, ᄎ, ᄏ, ᄐ, ᄑ, ᄒ, …
    AlphabeticIndex.ImmutableIndex ko = createIndex(Locale.KOREAN);
    assertHasLabel(ko, "\u1100", "\u3131");
    assertHasLabel(ko, "\u3131", "\u3131");
    assertHasLabel(ko, "\u1101", "\u3131");
    assertHasLabel(ko, "\u1161", "\u314e");
  }

  public void test_cs() throws Exception {
    // Czech
    // …, [A-C], Č,[D-H], CH, [I-R], Ř, S, Š, [T-Z], Ž, …
    AlphabeticIndex.ImmutableIndex cs = createIndex(new Locale("cs"));
    assertHasLabel(cs, "Cena", "C");
    assertHasLabel(cs, "Čáp", "\u010c");
    assertHasLabel(cs, "Ruda", "R");
    assertHasLabel(cs, "Řada", "\u0158");
    assertHasLabel(cs, "Selka", "S");
    assertHasLabel(cs, "Šála", "\u0160");
    assertHasLabel(cs, "Zebra", "Z");
    assertHasLabel(cs, "Žába", "\u017d");
    assertHasLabel(cs, "Chata", "CH");
  }

  public void test_fr() throws Exception {
    // French: [A-Z] (no accented chars)
    AlphabeticIndex.ImmutableIndex fr = createIndex(Locale.FRENCH);
    assertHasLabel(fr, "Øfer", "O");
    assertHasLabel(fr, "Œster", "O");
  }

  public void test_da() throws Exception {
    // Danish: [A-Z], Æ, Ø, Å
    AlphabeticIndex.ImmutableIndex da = createIndex(new Locale("da"));
    assertHasLabel(da, "Ænes", "\u00c6");
    assertHasLabel(da, "Øfer", "\u00d8");
    assertHasLabel(da, "Œster", "\u00d8");
    assertHasLabel(da, "Ågård", "\u00c5");
  }

  public void test_de() throws Exception {
    // German: [A-S,Sch,St,T-Z] (no ß or umlauted characters in standard alphabet)
    AlphabeticIndex.ImmutableIndex de = createIndex(Locale.GERMAN);
    assertHasLabel(de, "ßind", "S");
    assertHasLabel(de, "Sacher", "S");
    assertHasLabel(de, "Schiller", "Sch");
    assertHasLabel(de, "Steiff", "St");
  }

  public void test_th() throws Exception {
    // Thai (sorts English then Thai)
    // …, ก, ข, ฃ, ค, ฅ, ฆ, ง, จ, ฉ, ช, ซ, ฌ, ญ, ฎ, ฏ, ฐ, ฑ, ฒ, ณ, ด, ต, ถ, ท, ธ, น, บ, ป, ผ, ฝ, พ, ฟ, ภ, ม, ย, ร, ฤ, ล, ฦ, ว, ศ, ษ, ส, ห, ฬ, อ, ฮ, …,
    AlphabeticIndex.ImmutableIndex th = createIndex(new Locale("th"));
    assertHasLabel(th, "\u0e2d\u0e07\u0e04\u0e4c\u0e40\u0e25\u0e47\u0e01", "\u0e2d");
    assertHasLabel(th, "\u0e2a\u0e34\u0e07\u0e2b\u0e40\u0e2a\u0e19\u0e35", "\u0e2a");
  }

  public void test_ar() throws Exception {
    // Arabic (sorts English then Arabic)
    // …, ا, ب, ت, ث, ج, ح, خ, د, ذ, ر, ز, س, ش, ص, ض, ط, ظ, ع, غ, ف, ق, ك, ل, م, ن, ه, و, ي, …
    AlphabeticIndex.ImmutableIndex ar = createIndex(new Locale("ar"));
    assertHasLabel(ar, "\u0646\u0648\u0631", /* Noor */ "\u0646");
  }

  public void test_he() throws Exception {
    // Hebrew (sorts English then Hebrew)
    // …, א, ב, ג, ד, ה, ו, ז, ח, ט, י, כ, ל, מ, נ, ס, ע, פ, צ, ק, ר, ש, ת, …
    AlphabeticIndex.ImmutableIndex he = createIndex(new Locale("he"));
    assertHasLabel(he, "\u05e4\u05e8\u05d9\u05d3\u05de\u05df", "\u05e4");
  }

  public void test_zh_CN() throws Exception {
    // Simplified Chinese (default collator Pinyin): [A-Z]
    // Shen/Chen (simplified): should be, usually, 'S' for name collator and 'C' for apps/other
    AlphabeticIndex.ImmutableIndex zh_CN = createIndex(new Locale("zh", "CN"));

    // Jia/Gu: should be, usually, 'J' for name collator and 'G' for apps/other
    assertHasLabel(zh_CN, "\u8d3e", "J");

    // Shen/Chen
    assertHasLabel(zh_CN, "\u6c88", "C"); // icu4c 50 does not specialize for names.
    // Shen/Chen (traditional)
    assertHasLabel(zh_CN, "\u700b", "S");
  }

  public void test_zh_HK() throws Exception {
    // Traditional Chinese (strokes).
    // …, [1-33, 35, 36, 39, 48]劃, …
    // Shen/Chen
    AlphabeticIndex.ImmutableIndex zh_HK = createIndex(new Locale("zh", "HK"));
    assertHasLabel(zh_HK, "\u6c88", "7\u5283");
    assertHasLabel(zh_HK, "\u700b", "18\u5283");
    // Jia/Gu
    assertHasLabel(zh_HK, "\u8d3e", "10\u5283");
  }

  public void test_constructor_NPE() throws Exception {
    try {
      new AlphabeticIndex(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void test_addLabels_NPE() throws Exception {
    AlphabeticIndex ai = new AlphabeticIndex(Locale.US);
    try {
      ai.addLabels(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  // ICU 51 default max label count is 99. Test to make sure can create an
  // index with a larger number of labels.
  public void test_setMaxLabelCount() throws Exception {
    final int MAX_LABEL_COUNT = 500;
    AlphabeticIndex ai = new AlphabeticIndex(Locale.US)
      .setMaxLabelCount(MAX_LABEL_COUNT)
      .addLabels(Locale.JAPANESE)
      .addLabels(Locale.KOREAN)
      .addLabels(new Locale("th"))
      .addLabels(new Locale("ar"))
      .addLabels(new Locale("he"))
      .addLabels(new Locale("el"))
      .addLabels(new Locale("ru"));
    assertEquals(MAX_LABEL_COUNT, ai.getMaxLabelCount());
    assertEquals(208, ai.getBucketCount());
    AlphabeticIndex.ImmutableIndex ii = ai.getImmutableIndex();
    assertEquals(ai.getBucketCount(), ii.getBucketCount());
  }

  public void test_getBucketIndex_NPE() throws Exception {
    AlphabeticIndex.ImmutableIndex ii = createIndex(Locale.US);
    try {
      ii.getBucketIndex(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void test_getBucketLabel_invalid() throws Exception {
    AlphabeticIndex.ImmutableIndex ii = createIndex(Locale.US);
    try {
      ii.getBucketLabel(-1);
      fail();
    } catch (IllegalArgumentException expected) {
    }

    try {
      ii.getBucketLabel(123456);
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }
}
