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
package libcore.java.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formattable;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.Locale;
import junit.framework.TestCase;

public final class OldFormatterTest extends TestCase {

    public void test_Formattable() {
        Formattable ones = new Formattable() {
            public void formatTo(Formatter formatter, int flags, int width, int precision) throws IllegalFormatException {
                try {
                    formatter.out().append("111");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Formattable twos = new Formattable() {
            public void formatTo(Formatter formatter, int flags, int width, int precision) throws IllegalFormatException {
                try {
                    formatter.out().append("222");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        assertEquals("aaa 111?", new Formatter().format("aaa %s?", ones).toString());
        assertEquals("aaa 111 bbb 222?", new Formatter().format("aaa %s bbb %s?", ones, twos).toString());
    }

    public void test_formatLjava_util_LocaleLjava_lang_StringLjava_lang_Object() {
        Double val = new Double(3.14);
        Calendar cal = Calendar.getInstance();
        Formatter fLoc = null;
        Formatter fNoL = null;
        cal.set(2008, Calendar.SEPTEMBER, 23, 18, 30);

        fLoc = new Formatter(Locale.GERMAN);
        fNoL = new Formatter(Locale.GERMAN);
        fLoc.format(Locale.US, "%f", val);
        fNoL.format("%f", val);
        assertFalse(fLoc.toString().equals(fNoL.toString()));

        fLoc = new Formatter(Locale.FRANCE);
        fNoL = new Formatter(Locale.FRANCE);
        fLoc.format(Locale.US, "%f", val);
        fNoL.format("%f", val);
        assertFalse(fLoc.toString().equals(fNoL.toString()));

        fLoc = new Formatter(Locale.US);
        fNoL = new Formatter(Locale.US);
        fLoc.format(Locale.US, "%f", val);
        fNoL.format("%f", val);
        assertTrue(fLoc.toString().equals(fNoL.toString()));

        fLoc = new Formatter(Locale.GERMAN);
        fNoL = new Formatter(Locale.GERMAN);
        fLoc.format(Locale.US, "%tA %tB %td %tT", cal, cal,  cal, cal);
        fNoL.format("%tA %tB %td %tT", cal, cal,  cal, cal);
        assertFalse(fLoc.toString().equals(fNoL.toString()));

        fLoc = new Formatter(Locale.FRANCE);
        fNoL = new Formatter(Locale.FRANCE);
        fLoc.format(Locale.US, "%tA %tB %td %tT", cal, cal,  cal, cal);
        fNoL.format("%tA %tB %td %tT", cal, cal,  cal, cal);
        assertFalse(fLoc.toString().equals(fNoL.toString()));

        fLoc = new Formatter(Locale.US);
        fNoL = new Formatter(Locale.US);
        fLoc.format(Locale.US, "%tA %tB %td %tT", cal, cal,  cal, cal);
        fNoL.format("%tA %tB %td %tT", cal, cal,  cal, cal);
        assertTrue(fLoc.toString().equals(fNoL.toString()));

        final String[] illFlags = { "%+ e", "%+ E", "%+ g", "%+ G", "%+ f",
                "%+ a", "%+ A", "%-03e", "%-03E", "%-03g", "%-03G", "%-03f",
                "%-03a", "%-03A" };
        for (int i = 0; i < illFlags.length; i++) {
            try {
                fLoc = new Formatter(Locale.US);
                fLoc.format(Locale.FRANCE, illFlags[i], 1.23d);
                fail("should throw IllegalFormatFlagsException");
            } catch (IllegalFormatFlagsException expected) {
            }
            try {
                fLoc = new Formatter(Locale.CANADA);
                fLoc.format(Locale.GERMAN, illFlags[i], (Double) null);
                fail("should throw IllegalFormatFlagsException");
            } catch (IllegalFormatFlagsException expected) {
            }
        }

        fLoc.close();
        try {
            fLoc.format(Locale.GERMAN, "%f", val);
            fail();
        } catch (FormatterClosedException expected) {
        }
    }
}
