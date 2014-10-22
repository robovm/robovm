/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
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

        int posIn = in.position();
        int posOut = out.position();
        this.allocateContentDescriptor(in.isDirect(), in.order());
        IconvResult result = IconvProvider.encode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            IconvProvider.enableDiscardIllegalSequence(iconv_tPointer);
            in.position(posIn);
            out.position(posOut);
            result = IconvProvider.encode(iconv_tPointer, in, out);
            IconvProvider.disableDiscardIllegalSequence(iconv_tPointer);
            return CoderResult.unmappableForLength(result.getBytesReadFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.malformedForLength(result.getBytesReadFromSource());
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
    protected CoderResult implFlush(ByteBuffer out) {
        if (iconv_tPointer != 0) { 
            
            IconvResult result = IconvProvider.flush(iconv_tPointer, out);

            switch (result.getResultCode()) {
            case OUTPUT_BUFFER_TOO_SMALL:
                return CoderResult.OVERFLOW;
            case ILLEGAL_SEQUENCE:
                return CoderResult.unmappableForLength(result.getBytesReadFromSource());
            case INCOMPLETE_SEQUENCE:
                return CoderResult.malformedForLength(result.getBytesReadFromSource());
            default:
                break;
            }

        }
        return super.implFlush(out);
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
