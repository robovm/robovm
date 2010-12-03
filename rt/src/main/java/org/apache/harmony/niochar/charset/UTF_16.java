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

public class UTF_16 extends Charset {

    protected static final int UNKNOWN = -1;

    protected static final int BIG = 0;

    protected static final int LITTLE = 1;

    protected static final int ANY = 2;

    protected static final int NOT_DETECTED = 3;

    public UTF_16(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    public boolean contains(Charset cs) {
        return cs.name().equalsIgnoreCase("UTF-16")
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
            || cs.name().equalsIgnoreCase("UTF-8")
            || cs.name().equalsIgnoreCase("UTF-16LE")
            || cs.name().equalsIgnoreCase("UTF-16BE");
    }

    protected int getDefaultEndian() {
        return ANY;
    }

    protected int getDetectedEndian(int b1, int b2) {
        if (b1 == 0xFF && b2 == 0xFE) {
            return LITTLE;
        }
        if (b1 == 0xFE && b2 == 0xFF) {
            return BIG;
        }
        return NOT_DETECTED;
    }

    private final class Decoder extends CharsetDecoder {
        
        private int endian;
        
        private Decoder(Charset cs) {
            super(cs, 0.5f, 1.0f);
            implReset();
        }

        protected void implReset() {
            endian = UNKNOWN;
        }

        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
            int outRemaining = out.remaining();
            int pos = in.position();
            int limit = in.limit();
            try {
                while (pos < limit - 1) {
                    if (outRemaining == 0) {
                        return CoderResult.OVERFLOW;
                    }

                    int b1 = in.get() & 0xFF;
                    int b2 = in.get() & 0xFF;

                    if (endian == UNKNOWN) {
                        endian = getDetectedEndian(b1, b2);
                        if (endian == NOT_DETECTED) {
                            endian = getDefaultEndian();
                            if (endian == ANY) {
                                endian = BIG;
                            }
                        } else {
                            if (getDefaultEndian() == ANY) {
                                pos += 2;
                                continue;
                            }
                        }
                    }

                    int jchar = (endian == BIG) ? (b1 << 8) | b2 : (b2 << 8) | b1;

                    if (jchar >= 0xD800 && jchar <= 0xDFFF) {
                        // Determine if the surrogate pair starts with a
                        // low-surrogate.
                        if (jchar >= 0xDC00) {
                            return CoderResult.malformedForLength(2);
                        }

                        if (outRemaining < 2) {
                            return CoderResult.OVERFLOW;
                        }
                        if (pos + 3 >= limit) {
                            return CoderResult.UNDERFLOW;
                        }

                        int b3 = in.get() & 0xFF;
                        int b4 = in.get() & 0xFF;
                        int jchar2 = (endian == BIG) ? (b3 << 8) | b4
                                : (b4 << 8) | b3;

                        // Determine if the surrogate pair ends with a
                        // high-surrogate.
                        if (jchar2 < 0xDC00) {
                            return CoderResult.malformedForLength(4);
                        }

                        out.put((char) jchar);
                        out.put((char) jchar2);
                        outRemaining -= 2;
                        pos += 4;
                    } else {
                        out.put((char) jchar);
                        outRemaining--;
                        pos += 2;
                    }
                }
                return CoderResult.UNDERFLOW;
            } finally {
                in.position(pos);
            }
        }
    }

    private final class Encoder extends CharsetEncoder {

        private int endian;
        
        private Encoder(Charset cs) {
            super(cs, 2.0f, 2.0f, new byte[] {-1, -3});
            if(getDefaultEndian() == 1)
                replaceWith(new byte[] {-3, -1});
            implReset();
        }

        protected void implReset() {
            endian = UNKNOWN;
        }

        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
            int outRemaining = out.remaining();
            int pos = in.position();
            int limit = in.limit();
            try {
                while (pos < limit) {

                    if (endian == UNKNOWN) {
                        endian = getDefaultEndian();
                        if (endian == ANY) {
                            if (outRemaining < 2) {
                                endian = UNKNOWN;
                                return CoderResult.OVERFLOW;
                            }
                            endian = BIG;
                            out.put((byte) 0xFE);
                            out.put((byte) 0xFF);
                            outRemaining -= 2;
                        }
                    }

                    if (outRemaining == 0) {
                        return CoderResult.OVERFLOW;
                    }

                    int jchar = (in.get() & 0xFFFF);

                    if (jchar >= 0xD800 && jchar <= 0xDFFF) {

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

                        if (endian == BIG) {
                            out.put((byte) ((jchar >> 8) & 0xFF));
                            out.put((byte) (jchar & 0xFF));
                            out.put((byte) ((jchar2 >> 8) & 0xFF));
                            out.put((byte) (jchar2 & 0xFF));
                        } else {
                            out.put((byte) (jchar & 0xFF));
                            out.put((byte) ((jchar >> 8) & 0xFF));
                            out.put((byte) (jchar2 & 0xFF));
                            out.put((byte) ((jchar2 >> 8) & 0xFF));
                        }
                        outRemaining -= 4;
                        pos++;

                    } else {

                        if (outRemaining < 2) {
                            return CoderResult.OVERFLOW;
                        }

                        if (endian == BIG) {
                            out.put((byte) ((jchar >> 8) & 0xFF));
                            out.put((byte) (jchar & 0xFF));
                        } else {
                            out.put((byte) (jchar & 0xFF));
                            out.put((byte) ((jchar >> 8) & 0xFF));
                        }
                        outRemaining -= 2;
                    }
                    pos++;
                }
                return CoderResult.UNDERFLOW;
            } finally {
                in.position(pos);
            }
        }
    }
}
