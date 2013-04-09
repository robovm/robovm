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
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import junit.framework.TestCase;
import tests.support.Support_PortManager;

public final class OldScannerTest extends TestCase {

    private Scanner s;

    public void test_findWithinHorizon_Ljava_lang_StringI() {
        // This method searches through the input up to the specified search
        // horizon(exclusive).
        s = new Scanner("123test");
        String result = s.findWithinHorizon("\\p{Lower}", 5);
        assertEquals("t", result);
        MatchResult mresult = s.match();
        assertEquals(3, mresult.start());
        assertEquals(4, mresult.end());

        s = new Scanner("12345test1234test next");
        /*
         * If the pattern is found the scanner advances past the input that
         * matched and returns the string that matched the pattern.
         */
        result = s.findWithinHorizon("\\p{Digit}+", 2);
        assertEquals("12", result);
        mresult = s.match();
        assertEquals(0, mresult.start());
        assertEquals(2, mresult.end());
        // Position is now pointing at the bar. "12|345test1234test next"

        result = s.findWithinHorizon("\\p{Digit}+", 6);
        assertEquals("345", result);

        mresult = s.match();
        assertEquals(2, mresult.start());
        assertEquals(5, mresult.end());
        // Position is now pointing at the bar. "12345|test1234test next"

        // If no such pattern is detected then the null is returned and the
        // scanner's position remains unchanged.
        result = s.findWithinHorizon("\\p{Digit}+", 3);
        assertNull(result);

        try {
            s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals("345", mresult.group());
        assertEquals(2, mresult.start());
        assertEquals(5, mresult.end());
        // Position is now still pointing at the bar. "12345|test1234test next"

        // If horizon is 0, then the horizon is ignored and this method
        // continues to search through the input looking for the specified
        // pattern without bound.
        result = s.findWithinHorizon("\\p{Digit}+", 0);
        mresult = s.match();
        assertEquals(9, mresult.start());
        assertEquals(13, mresult.end());
        // Position is now pointing at the bar. "12345test1234|test next"

        assertEquals("test", s.next());
        mresult = s.match();
        assertEquals(13, mresult.start());
        assertEquals(17, mresult.end());

        assertEquals("next", s.next());
        mresult = s.match();
        assertEquals(18, mresult.start());
        assertEquals(22, mresult.end());

        try {
            s.findWithinHorizon((String)null, 1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            s.findWithinHorizon("\\p{Digit}+", -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        s.close();
        // on RI throws NullPointerException
        /*
         * try { System.out.println("fsdfs"); s.findWithinHorizon((String) null,
         * -1); System.out.println("fsdfs"); fail("Should throw
         * IllegalStateException"); } catch (IllegalStateException e) { //
         * expected }
         */
        s = new Scanner("test");
        result = s.findWithinHorizon("\\w+", 10);
        assertEquals("test", result);

        s = new Scanner("aa\n\rb");
        String patternStr = "^(a)$";
        result = s.findWithinHorizon("a", 5);
        assertEquals("a", result);
        mresult = s.match();
        assertEquals(0, mresult.start());
        assertEquals(1, mresult.end());

        result = s.findWithinHorizon(patternStr, 5);
        assertNull(result);

        try {
            mresult = s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("");
        result = s.findWithinHorizon("^", 0);
        assertEquals("", result);
        MatchResult matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        result = s.findWithinHorizon("$", 0);
        assertEquals("", result);
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        s = new Scanner("1 fish 2 fish red fish blue fish");
        result = s.findWithinHorizon("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)", 10);
        assertNull(result);

        try {
            mresult = s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals(0, mresult.groupCount());

        result = s.findWithinHorizon("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)", 100);
        assertEquals("1 fish 2 fish red fish blue", result);
        mresult = s.match();
        assertEquals(4, mresult.groupCount());
        assertEquals("1", mresult.group(1));
        assertEquals("2", mresult.group(2));
        assertEquals("red", mresult.group(3));
        assertEquals("blue", mresult.group(4));

        s = new Scanner("test");
        s.close();
        try {
            s.findWithinHorizon("test", 1);
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("word1 WorD2  ");
        s.close();
        try {
            s.findWithinHorizon("\\d+", 10);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("word1 WorD2 wOrd3 ");
        patternStr = "\\d+";
        assertEquals("1", s.findWithinHorizon(patternStr, 10));
        assertEquals("WorD2", s.next());
        assertEquals("3", s.findWithinHorizon(patternStr, 15));

        // Regression test
        s = new Scanner(new MockStringReader("MockStringReader"));
        patternStr = "test";
        result = s.findWithinHorizon(patternStr, 10);
        assertEquals("test", result);

        // Test the situation when input length is longer than buffer size.
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        patternStr = "\\p{Lower}+";
        result = s.findWithinHorizon(patternStr, 1026);
        assertEquals(stringBuilder.toString(), result);

        // Test the situation when input length is longer than buffer size and
        // set horizon to buffer size.
        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        patternStr = "\\p{Lower}+";
        result = s.findWithinHorizon(patternStr, 1022);
        assertEquals(1022, result.length());
        assertEquals(stringBuilder.subSequence(0, 1022), result);

        // Test the situation, under which pattern is clipped by buffer.
        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1022; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append("bbc");
        assertEquals(1025, stringBuilder.length());
        s = new Scanner(stringBuilder.toString());
        patternStr = "bbc";
        result = s.findWithinHorizon(patternStr, 1025);
        assertEquals(3, result.length());
        assertEquals(stringBuilder.subSequence(1022, 1025), result);

        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        patternStr = "\\p{Lower}+";
        result = s.findWithinHorizon(patternStr, 0);
        assertEquals(stringBuilder.toString(), result);

        stringBuilder = new StringBuilder();
        for (int i = 0; i < 10240; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.replace(0, 2, "aa");
        s = new Scanner(stringBuilder.toString());
        result = s.findWithinHorizon("aa", 0);
        assertEquals("aa", result);

        s = new Scanner("aaaa");
        result = s.findWithinHorizon("a*", 0);
        assertEquals("aaaa", result);
    }

    public void test_findInLine_LString() {
        s = new Scanner("test");
        try {
            s.findInLine((String) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        s.close();
        try {
            s.findInLine((String) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        try {
            s.findInLine("test");
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // exptected
        }

        s = new Scanner("");

        String result = s.findInLine("^");
        assertEquals("", result);
        MatchResult matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        result = s.findInLine("$");
        assertEquals("", result);
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        /*
         * When we use the operation of findInLine(Pattern), the match region
         * should not span the line separator.
         */
        s = new Scanner("aa\nb.b");
        result = s.findInLine("a\nb*");
        assertNull(result);

        s = new Scanner("aa\nbb.b");
        result = s.findInLine("\\.");
        assertNull(result);

        s = new Scanner("abcd1234test\n");
        result = s.findInLine("\\p{Lower}+");
        assertEquals("abcd", result);
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(4, matchResult.end());

        result = s.findInLine("\\p{Digit}{5}");
        assertNull(result);
        try {
            matchResult = s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals(0, matchResult.start());
        assertEquals(4, matchResult.end());

        result = s.findInLine("\\p{Lower}+");
        assertEquals("test", result);
        matchResult = s.match();
        assertEquals(8, matchResult.start());
        assertEquals(12, matchResult.end());

        char[] chars = new char[2048];
        Arrays.fill(chars, 'a');
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars);
        stringBuilder.append("1234");
        s = new Scanner(stringBuilder.toString());
        result = s.findInLine("\\p{Digit}+");
        assertEquals("1234", result);
        matchResult = s.match();
        assertEquals(2048, matchResult.start());
        assertEquals(2052, matchResult.end());

        s = new Scanner("test1234\n1234 test");
        result = s.findInLine("test");
        assertEquals("test", result);
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(4, matchResult.end());

        int number = s.nextInt();
        assertEquals(1234, number);
        matchResult = s.match();
        assertEquals(4, matchResult.start());
        assertEquals(8, matchResult.end());

        result = s.next();
        assertEquals("1234", result);
        matchResult = s.match();
        assertEquals(9, matchResult.start());
        assertEquals(13, matchResult.end());

        result = s.findInLine("test");
        assertEquals("test", result);
        matchResult = s.match();
        assertEquals(14, matchResult.start());
        assertEquals(18, matchResult.end());

        s = new Scanner("test\u0085\ntest");
        result = s.findInLine("est");
        assertEquals("est", result);
        result = s.findInLine("est");
        assertEquals("est", result);

        s = new Scanner("test\ntest");
        result = s.findInLine("est");
        assertEquals("est", result);
        result = s.findInLine("est");
        assertEquals("est", result);

        s = new Scanner("test\n123\ntest");
        result = s.findInLine("est");
        assertEquals("est", result);
        result = s.findInLine("est");
    }

    public void test_skip_LPattern() {
        s = new Scanner("test");
        try {
            s.skip((String) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // If pattern does not match, NoSuchElementException will be thrown out.
        s = new Scanner("1234");
        try {
            s.skip(Pattern.compile("\\p{Lower}"));
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
        // Then, no matchResult will be thrown out.
        try {
            s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s.skip(Pattern.compile("\\p{Digit}"));
        MatchResult matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(1, matchResult.end());

        s.skip(Pattern.compile("\\p{Digit}+"));
        matchResult = s.match();
        assertEquals(1, matchResult.start());
        assertEquals(4, matchResult.end());

        s.close();
        try {
            s.skip(Pattern.compile("test"));
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        MockStringReader2Read reader = new MockStringReader2Read("test");
        s = new Scanner(reader);
        try {
            s.skip(Pattern.compile("\\p{Digit}{4}"));
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
        try {
            s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        s.skip(Pattern.compile("\\p{Digit}{3}\\p{Lower}"));
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(4, matchResult.end());

        s.close();
        try {
            s.skip((Pattern) null);
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        StringBuilder stringBuilder = new StringBuilder();
        char [] chars = new char[1024];
        Arrays.fill(chars, 'a');
        stringBuilder.append(chars);
        stringBuilder.append('3');
        s = new Scanner(stringBuilder.toString());
        s.skip(Pattern.compile("\\p{Lower}+\\p{Digit}"));
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(1025, matchResult.end());

        // Large amount of input may be cached
        chars = new char[102400];
        Arrays.fill(chars, 'a');
        stringBuilder = new StringBuilder();
        stringBuilder.append(chars);
        s = new Scanner(stringBuilder.toString());
        s.skip(Pattern.compile(".*"));
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(102400, matchResult.end());

        // skip something without risking a NoSuchElementException
        s.skip(Pattern.compile("[ \t]*"));
        matchResult = s.match();
        assertEquals(102400, matchResult.start());
        assertEquals(102400, matchResult.end());
    }

    public void test_Constructor_LReadableByteChannel()
            throws IOException {
        InetSocketAddress localAddr = new InetSocketAddress("127.0.0.1",
                Support_PortManager.getNextPort());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr);

        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr);
        sc.configureBlocking(false);
        assertFalse(sc.isBlocking());

        ssc.accept().close();
        ssc.close();
        assertFalse(sc.isBlocking());

        Scanner s = new Scanner(sc);
        try {
            s.hasNextInt();
            fail();
        } catch (IllegalBlockingModeException expected) {
        }

        sc.close();
    }

    private static class MockStringReader extends StringReader {

        public MockStringReader(String param) {
            super(param);
        }

        public int read(CharBuffer target) throws IOException {
            target.append('t');
            target.append('e');
            target.append('s');
            target.append('t');
            throw new IOException();
        }
    }

    private static class MockStringReader2Read extends StringReader {
        private int timesRead = 1;

        public MockStringReader2Read(String param) {
            super(param);
        }

        public int read(CharBuffer target) throws IOException {
            if (timesRead == 1) {
                target.append('1');
                target.append('2');
                target.append('3');
                timesRead++;
                return 3;
            } else if (timesRead == 2) {
                target.append('t');
                timesRead++;
                return 1;
            } else {
                throw new IOException();
            }
        }
    }
}
