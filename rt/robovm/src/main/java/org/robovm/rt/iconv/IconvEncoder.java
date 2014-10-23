/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.rt.iconv;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * This encoder class is used to wrap encodings of the encodings supported by
 * RoboVM in conjunction with utilizing iconv. The encoding operations are performed via 
 * calls to encodeLoop which calls iconv via JNI to do conversions between encodings.
 */
public class IconvEncoder extends CharsetEncoder{
	
    private long iconv_tPointer = 0;

    private String currentEncoding;
    
    /**
     * Constructor for {@link CharsetEncoder} for specified {@link Charset}.
     */
    public IconvEncoder(Charset cs, float averageBytesPerChar,
            float maxBytesPerChar) {
        super(cs, averageBytesPerChar, maxBytesPerChar);
    }

    /**
     * Basic encoding loop, it encodes as many chars as possible until either:
     * it runs out of input, or it runs out of space in the output buffer, 
     * or an illegal char has been encountered or it encounters an encoding error.
     * @return a {@link CoderResult} object indicating current encoding state
     */
    @Override
    protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {

        this.allocateContentDescriptor(in.isDirect(), in.order());
        IconvResult result = IconvProvider.encode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength( result.getBytesReadFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.UNDERFLOW;
        default:
            break;
        }
        return CoderResult.UNDERFLOW;
    }
    
    /**
     * Allocates an content descriptor
     * @param isDirect is it direct
     * @param byteOrder {@link ByteOrder} of Buffer
     */
    private void allocateContentDescriptor(boolean isDirect, ByteOrder byteOrder) {
        
        String encoding = byteOrder == ByteOrder.BIG_ENDIAN && isDirect ? "UTF-16BE" : "UTF-16LE";
        if(iconv_tPointer == 0) {
            this.iconv_tPointer = IconvProvider.initConversion(encoding, ((IconvCharset) charset()).getIconvName());
            this.currentEncoding = encoding;
        }

        if (!encoding.equals(this.currentEncoding)) {
            throw new IllegalStateException("Illegal change of byte order");
        }
        
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (iconv_tPointer != 0) {
            IconvProvider.release(iconv_tPointer);
            iconv_tPointer = 0;
        }
    }

    @Override
    protected void implReset() {
        super.implReset();
        if (iconv_tPointer != 0) {
            IconvProvider.release(iconv_tPointer);
            iconv_tPointer = 0;
        }
    }

}
