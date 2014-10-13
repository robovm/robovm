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
 * This encoder class is used to wrap encodings of the current 60 encodings supported by
 * RoboVM in conjunction with utilizing iconv.
 * 
 * The encoding operations are performed via calls to encodeLoop.
 *  
 * encodeLoop calls iconv via JNI to do conversions between encodings.
 */
public class IconvEncoder extends CharsetEncoder{
	
    private long iconv_tPointer = 0;
    
    private ByteOrder currentByteOrder;
    
    /**
     * Constructor for {@link CharsetEncoder} with specified char encoding constants.
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
        
        if(!in.isDirect() && !in.hasArray()) {
            throw new IllegalArgumentException();
        }

        this.allocateContentDescriptor(in.isDirect(), in.order());
        IconvResult result = IconvProvider.encode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength(result.getBytesWrittenFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.malformedForLength(result.getBytesWrittenFromSource());
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
        
        if(iconv_tPointer == 0) {
            //Content descriptor needs to be allocated
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.iconv_tPointer = IconvProvider.initConversion("UTF-16BE", ((IconvCharset) charset()).getIconvName());
                this.currentByteOrder = ByteOrder.BIG_ENDIAN;
            } else {
                this.iconv_tPointer = IconvProvider.initConversion("UTF-16LE", ((IconvCharset) charset()).getIconvName());
                this.currentByteOrder = ByteOrder.LITTLE_ENDIAN;
            }
        } else if (byteOrder != this.currentByteOrder) {
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
                return CoderResult.unmappableForLength(result.getBytesWrittenFromSource());
            case INCOMPLETE_SEQUENCE:
                return CoderResult.malformedForLength(result.getBytesWrittenFromSource());
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
