/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.niochar.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class UTF_8 extends Charset {

    // The next table contains information about UTF-8 charset and
    // correspondence of 1st byte to the length of sequence
    // For information please visit http://www.ietf.org/rfc/rfc3629.txt
    //
    // Please note, o means 0, actually.
    // -------------------------------------------------------------------
    // 0         1         2         3          Value
    // -------------------------------------------------------------------
    // oxxxxxxx                                 00000000 00000000 0xxxxxxx
    // 11oyyyyy  1oxxxxxx                       00000000 00000yyy yyxxxxxx
    // 111ozzzz  1oyyyyyy  1oxxxxxx             00000000 zzzzyyyy yyxxxxxx
    // 1111ouuu  1ouuzzzz  1oyyyyyy  1oxxxxxx   000uuuuu zzzzyyyy yyxxxxxx

    private static final int remainingBytes[] = {
            // 1owwwwww
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            // 11oyyyyy
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            // 111ozzzz
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            // 1111ouuu
            3, 3, 3, 3, 3, 3, 3, 3,
            // > 11110111
            -1, -1, -1, -1, -1, -1, -1, -1 };

    private static final int remainingNumbers[] = {
                   0, //                0                 1                2           3
                4224, // (01o00000b <<  6)+(1o000000b)
              401536, // (011o0000b << 12)+(1o000000b <<  6)+(1o000000b)
            29892736  // (0111o000b << 18)+(1o000000b << 12)+(1o000000b << 6)+(1o000000b)
    };
    
    private static final int lowerEncodingLimit[] = { -1, 0x80, 0x800, 0x10000 };

    public UTF_8(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    @Override
    @SuppressWarnings("nls")
    public boolean contains(Charset cs) {
        return cs.name().equalsIgnoreCase("UTF-8")
                || cs.name().equalsIgnoreCase("US-ASCII")
                || cs.name().equalsIgnoreCase("KOI8-R")
                || cs.name().equalsIgnoreCase("windows-1250")
                || cs.name().equalsIgnoreCase("windows-1251")
                || cs.name().equalsIgnoreCase("windows-1252")
                || cs.name().equalsIgnoreCase("windows-1253")
                || cs.name().equalsIgnoreCase("windows-1254")
                || cs.name().equalsIgnoreCase("windows-1257")
                || cs.name().equalsIgnoreCase("ISO-8859-1")
                || cs.name().equalsIgnoreCase("ISO-8859-2")
                || cs.name().equalsIgnoreCase("ISO-8859-4")
                || cs.name().equalsIgnoreCase("ISO-8859-5")
                || cs.name().equalsIgnoreCase("ISO-8859-7")
                || cs.name().equalsIgnoreCase("ISO-8859-9")
                || cs.name().equalsIgnoreCase("ISO-8859-10")
                || cs.name().equalsIgnoreCase("ISO-8859-13")
                || cs.name().equalsIgnoreCase("ISO-8859-14")
                || cs.name().equalsIgnoreCase("ISO-8859-15")
                || cs.name().equalsIgnoreCase("ISO-8859-16")
                || cs.name().equalsIgnoreCase("UTF-16")
                || cs.name().equalsIgnoreCase("UTF-16LE")
                || cs.name().equalsIgnoreCase("UTF-16BE");
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    private final class Decoder extends CharsetDecoder {

        private Decoder(Charset cs) {
            super(cs, 1.0f, 1.0f);
        }

        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
            int outRemaining = out.remaining();
            int pos = in.position();
            int limit = in.limit();
            if (in.hasArray() && out.hasArray()) {
                final byte[] bArr = in.array();
                final char[] cArr = out.array();
                final int inIndexLimit = limit + in.arrayOffset();

                int inIndex = pos + in.arrayOffset();
                int outIndex = out.position() + out.arrayOffset();

                // if someone would change the limit in process,
                // he would face consequences
                for (; inIndex < inIndexLimit && outRemaining > 0; inIndex++) {
                    int jchar = bArr[inIndex];
                    if (jchar < 0) {
                        jchar = jchar & 0x7F;
                        int tail = remainingBytes[jchar];

                        if (tail == -1) {
                            in.position(inIndex - in.arrayOffset());
                            out.position(outIndex - out.arrayOffset());
                            return CoderResult.malformedForLength(1);
                        }
                        if (inIndexLimit - inIndex < 1 + tail) {
                            break;
                        }

                        for (int i = 0; i < tail; i++) {
                            int nextByte = bArr[inIndex + i + 1] & 0xFF;
                            if ((nextByte & 0xC0) != 0x80) {
                                in.position(inIndex - in.arrayOffset());
                                out.position(outIndex - out.arrayOffset());
                                return CoderResult.malformedForLength(1 + i);
                            }
                            jchar = (jchar << 6) + nextByte;
                        }
                        jchar -= remainingNumbers[tail];
                        if (jchar < lowerEncodingLimit[tail]) {
                            // Should have been encoded in fewer octets
                            in.position(inIndex - in.arrayOffset());
                            out.position(outIndex - out.arrayOffset());
                            return CoderResult.malformedForLength(1);
                        }
                        inIndex += tail;
                    }
                    cArr[outIndex++] = (char) jchar;
                    outRemaining--;
                }
                in.position(inIndex - in.arrayOffset());
                out.position(outIndex - out.arrayOffset());
                return (outRemaining == 0 && inIndex < inIndexLimit) ? CoderResult.OVERFLOW
                        : CoderResult.UNDERFLOW;
            } else {
                try {
                    while (pos < limit) {
                        if (outRemaining == 0) {
                            return CoderResult.OVERFLOW;
                        }

                        int jchar = in.get();
                        if (jchar < 0) {
                            jchar = jchar & 0x7F;
                            int tail = remainingBytes[jchar];
                            if (tail == -1) {
                                return CoderResult.malformedForLength(1);
                            }
                            if (limit - pos < 1 + tail) {
                                return CoderResult.UNDERFLOW;
                            }

                            int nextByte;
                            for (int i = 0; i < tail; i++) {
                                nextByte = in.get() & 0xFF;
                                if ((nextByte & 0xC0) != 0x80) {
                                    return CoderResult
                                            .malformedForLength(1 + i);
                                }
                                jchar = (jchar << 6) + nextByte;
                            }
                            jchar -= remainingNumbers[tail];
                            if (jchar < lowerEncodingLimit[tail]) {
                                // Should have been encoded in a fewer octets
                                return CoderResult.malformedForLength(1);
                            }
                            pos += tail;
                        }
                        pos++;
                        out.put((char) jchar);
                        outRemaining--;
                    }
                    return CoderResult.UNDERFLOW;
                } finally {
                    in.position(pos);
                }
            }
        }
    }

    private final class Encoder extends CharsetEncoder {

        private Encoder(Charset cs) {
            super(cs, 1.1f, 4.0f);
        }

        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
            int outRemaining = out.remaining();
            int pos = in.position();
            int limit = in.limit();
            if (in.hasArray() && out.hasArray()) {
                byte[] bArr;
                char[] cArr;
                int x = pos;
                bArr = out.array();
                cArr = in.array();
                int outPos = out.position();
                int rem = in.remaining();
                for (x = pos; x < pos + rem; x++) {
                    int jchar = (cArr[x] & 0xFFFF);

                    if (jchar <= 0x7F) {
                        if (outRemaining < 1) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.OVERFLOW;
                        }
                        bArr[outPos++] = (byte) (jchar & 0xFF);
                        outRemaining--;
                    } else if (jchar <= 0x7FF) {

                        if (outRemaining < 2) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.OVERFLOW;
                        }
                        bArr[outPos++] = (byte) (0xC0 + ((jchar >> 6) & 0x1F));
                        bArr[outPos++] = (byte) (0x80 + (jchar & 0x3F));
                        outRemaining -= 2;

                    } else if (jchar >= 0xD800 && jchar <= 0xDFFF) {

                        // in has to have one byte more.
                        if (limit <= x + 1) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.UNDERFLOW;
                        }

                        if (outRemaining < 4) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.OVERFLOW;
                        }

                        // The surrogate pair starts with a low-surrogate.
                        if (jchar >= 0xDC00) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.malformedForLength(1);
                        }

                        int jchar2 = cArr[x + 1] & 0xFFFF;

                        // The surrogate pair ends with a high-surrogate.
                        if (jchar2 < 0xDC00) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.malformedForLength(1);
                        }

                        // Note, the Unicode scalar value n is defined
                        // as follows:
                        // n = (jchar-0xD800)*0x400+(jchar2-0xDC00)+0x10000
                        // Where jchar is a high-surrogate,
                        // jchar2 is a low-surrogate.
                        int n = (jchar << 10) + jchar2 + 0xFCA02400;

                        bArr[outPos++] = (byte) (0xF0 + ((n >> 18) & 0x07));
                        bArr[outPos++] = (byte) (0x80 + ((n >> 12) & 0x3F));
                        bArr[outPos++] = (byte) (0x80 + ((n >> 6) & 0x3F));
                        bArr[outPos++] = (byte) (0x80 + (n & 0x3F));
                        outRemaining -= 4;
                        x++;

                    } else {

                        if (outRemaining < 3) {
                            in.position(x);
                            out.position(outPos);
                            return CoderResult.OVERFLOW;
                        }
                        bArr[outPos++] = (byte) (0xE0 + ((jchar >> 12) & 0x0F));
                        bArr[outPos++] = (byte) (0x80 + ((jchar >> 6) & 0x3F));
                        bArr[outPos++] = (byte) (0x80 + (jchar & 0x3F));
                        outRemaining -= 3;
                    }
                    if (outRemaining == 0) {
                        in.position(x + 1);
                        out.position(outPos);
                        return CoderResult.OVERFLOW;
                    }

                }
                if (rem != 0) {
                    in.position(x);
                    out.position(outPos);
                }
            } else {
                try {
                    while (pos < limit) {
                        if (outRemaining == 0) {
                            return CoderResult.OVERFLOW;
                        }

                        int jchar = (in.get() & 0xFFFF);

                        if (jchar <= 0x7F) {

                            if (outRemaining < 1) {
                                return CoderResult.OVERFLOW;
                            }
                            out.put((byte) (jchar & 0xFF));
                            outRemaining--;

                        } else if (jchar <= 0x7FF) {

                            if (outRemaining < 2) {
                                return CoderResult.OVERFLOW;
                            }
                            out.put((byte) (0xC0 + ((jchar >> 6) & 0x1F)));
                            out.put((byte) (0x80 + (jchar & 0x3F)));
                            outRemaining -= 2;

                        } else if (jchar >= 0xD800 && jchar <= 0xDFFF) {

                            // in has to have one byte more.
                            if (limit <= pos + 1) {
                                return CoderResult.UNDERFLOW;
                            }

                            if (outRemaining < 4) {
                                return CoderResult.OVERFLOW;
                            }

                            // The surrogate pair starts with a low-surrogate.
                            if (jchar >= 0xDC00) {
                                return CoderResult.malformedForLength(1);
                            }

                            int jchar2 = (in.get() & 0xFFFF);

                            // The surrogate pair ends with a high-surrogate.
                            if (jchar2 < 0xDC00) {
                                return CoderResult.malformedForLength(1);
                            }

                            // Note, the Unicode scalar value n is defined
                            // as follows:
                            // n = (jchar-0xD800)*0x400+(jchar2-0xDC00)+0x10000
                            // Where jchar is a high-surrogate,
                            // jchar2 is a low-surrogate.
                            int n = (jchar << 10) + jchar2 + 0xFCA02400;

                            out.put((byte) (0xF0 + ((n >> 18) & 0x07)));
                            out.put((byte) (0x80 + ((n >> 12) & 0x3F)));
                            out.put((byte) (0x80 + ((n >> 6) & 0x3F)));
                            out.put((byte) (0x80 + (n & 0x3F)));
                            outRemaining -= 4;
                            pos++;

                        } else {

                            if (outRemaining < 3) {
                                return CoderResult.OVERFLOW;
                            }
                            out.put((byte) (0xE0 + ((jchar >> 12) & 0x0F)));
                            out.put((byte) (0x80 + ((jchar >> 6) & 0x3F)));
                            out.put((byte) (0x80 + (jchar & 0x3F)));
                            outRemaining -= 3;
                        }
                        pos++;
                    }
                } finally {
                    in.position(pos);
                }
            }

            return CoderResult.UNDERFLOW;
        }

    }
}
