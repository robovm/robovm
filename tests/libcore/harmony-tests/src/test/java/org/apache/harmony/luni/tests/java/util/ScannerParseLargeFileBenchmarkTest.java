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
package org.apache.harmony.luni.tests.java.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import junit.framework.TestCase;

public class ScannerParseLargeFileBenchmarkTest extends TestCase {

    /**
     * This test will check when parse a large file like more than 200M bytes if
     * the Scanner will exhaust all heap memory
     */
    public void testParseLargeFile() throws Exception {
        MyReader reader = new MyReader();
        String delimiter = "\r?\n";
        Scanner scanner = new Scanner(reader).useDelimiter(delimiter);

        while (scanner.hasNext()) {
            scanner.next();
        }
        scanner.close();
        reader.close();
    }

    private static class MyReader extends Reader {
        static final char[] CONTENT = "large file!\n".toCharArray();

        static long fileLength = (8 << 21) * 12;

        static boolean first = true;

        static int position = 0;

        private int count = 0;

        @Override
        public void close() throws IOException {
        }

        @Override
        public int read(char[] buf, int offset, int length) {
            if (count >= fileLength) {
                return -1;
            }
            if (first == true) {
                position = 0;
                first = false;
            }
            for (int i = offset; i < length; i++) {
                buf[i] = CONTENT[(i + position) % CONTENT.length];
                count++;
            }

            position = (length + position) % CONTENT.length;

            return length - offset;
        }
    }
}
