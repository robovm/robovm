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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/**
 * This decoder class is used to wrap decoding of the current encodings supported by
 * RoboVM in conjunction with utilizing iconv. The decoding operations are performed via
 * calls to decodeLoop which calls iconv via JNI to do conversions between encodings 
 */
public class IconvDecoder extends CharsetDecoder{
    
    private long iconv_tPointer = 0;

    private String currentEncoding;
    
    /**
     * Constructor for {@link CharsetDecoder} for specified {@link Charset}
     * @param cs charset instance
     * @param averageCharsPerByte
     * @param maxCharsPerByte
     */
    public IconvDecoder(Charset cs, float averageCharsPerByte,
            float maxCharsPerByte) {
        super(cs, averageCharsPerByte, maxCharsPerByte);
    }

    /**
     * basic decoding loop, it decodes as many chars as possible until either:
     * it runs out of input, or runs out of space in the output buffer, 
     * or an illegal char has been encountered, or it encounters encoding error
     * @return a {@link CoderResult} object indicating current encoding state
     */
    @Override
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
        
        allocateContentDescriptor(out.isDirect(), out.order());
        IconvResult result = IconvProvider.decode(iconv_tPointer, in, out);

        int bytesFromSource = result.getBytesReadFromSource();
        switch (result.getResultCode()) {
        case CONVERSION_OK:
            return CoderResult.UNDERFLOW;
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength(bytesFromSource);
        case INCOMPLETE_SEQUENCE:
            return CoderResult.UNDERFLOW;
        default:
            throw new IllegalStateException();
        }
    }

    /**
     * Allocates an content descriptor
     * @param isDirect is it direct
     * @param byteOrder {@link ByteOrder}
     */
    private void allocateContentDescriptor(boolean isDirect, ByteOrder byteOrder) {

        String encoding = byteOrder == ByteOrder.BIG_ENDIAN && isDirect ? "UTF-16BE" : "UTF-16LE";
        if(iconv_tPointer == 0) {
            this.iconv_tPointer = IconvProvider.initConversion(((IconvCharset) charset()).getIconvName(), encoding);
            this.currentEncoding = encoding;
        }

        if (!encoding.equals(this.currentEncoding)) {
            throw new IllegalStateException("Illegal change of byte order");
        }
        
    }
    
    private void release() {
        if (iconv_tPointer == 0) {
            IconvProvider.release(iconv_tPointer);
            iconv_tPointer = 0;
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.release();
    }

    @Override
    protected void implReset() {
        super.implReset();
        this.release();
    }

}
