/*
 * Copyright (C) 2008 The Android Open Source Project
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

import junit.framework.TestCase;

import java.util.Formattable;
import java.util.Formatter;

public class OldFormattableTest extends TestCase {

    class Mock_Formattable implements Formattable {
        boolean flag = false;

        public void formatTo(Formatter arg0, int arg1, int arg2, int arg3) {
            StringBuilder sb = new StringBuilder();

            if (arg3 == 1) {
                sb.append("single precision ");
            }
            if (arg3 == 2) {
                sb.append("double precision ");
            }

            arg0.format(sb.toString());
            flag = true;
        }

        public boolean isFormatToCalled() {
            return flag;
        }
    }

    public void testFormatTo() {
        Formatter fmt = new Formatter();
        Mock_Formattable mf = new Mock_Formattable();

        assertTrue(fmt.format("%1.1s", mf).toString().equals("single precision "));
        assertTrue(fmt.format("%2.1s", mf).toString().equals("single precision single precision "));
        assertTrue(fmt.format("%2.2s", mf).toString().equals("single precision single precision double precision "));
        assertTrue(mf.isFormatToCalled());
    }

}
