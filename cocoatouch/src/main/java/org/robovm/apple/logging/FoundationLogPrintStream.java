/*
 * Copyright 2015 Trillian Mobile AB.
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
package org.robovm.apple.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.robovm.apple.foundation.Foundation;

public class FoundationLogPrintStream extends PrintStream {

    StringBuffer st = new StringBuffer();

    public FoundationLogPrintStream() {
        super(new OutputStream() {
            @Override
            public void write(int arg0) throws IOException {
            }
        });
    }

    public void write(char ch) {
        if (ch == 0xa) {
        } else {
            st.append(ch);
        }
    }

    @Override
    public void flush() {
        if (st.length() > 0) {
            Foundation.log(st.toString());
            st.setLength(0);
        }
    }

    @Override
    public void print(char[] s) {
        for (char ch : s) {
            write(ch);
        }
    }

    @Override
    public void print(boolean b) {
        print(b + "");
    }

    @Override
    public void print(char c) {
        write(c);
    }

    @Override
    public void print(double d) {
        print(d + "");
    }

    @Override
    public void print(float f) {
        print(f + "");
    }

    @Override
    public void print(int i) {
        print(i + "");
    }

    @Override
    public void print(long l) {
        print(l + "");
    }

    @Override
    public void print(Object obj) {
        print((obj + "").toCharArray());
    }

    @Override
    public void print(String s) {
        print((s + "").toCharArray());
    }

    @Override
    public void println() {
        flush();
    }

    @Override
    public void println(boolean x) {
        print(x);
        flush();
    }

    @Override
    public void println(char x) {
        print(x);
        flush();
    }

    @Override
    public void println(char[] x) {
        print(x);
        flush();
    }

    @Override
    public void println(double x) {
        print(x);
        flush();
    }

    @Override
    public void println(float x) {
        print(x);
        flush();
    }

    @Override
    public void println(int x) {
        print(x);
        flush();
    }

    @Override
    public void println(long x) {
        print(x);
        flush();
    }

    @Override
    public void println(Object x) {
        print(x);
        flush();
    }

    @Override
    public void println(String x) {
        print(x);
        flush();
    }

}
