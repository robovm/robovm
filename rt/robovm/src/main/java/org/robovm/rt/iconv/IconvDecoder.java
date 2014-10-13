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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/**
 * This decoder class is used to wrap decoding of the current 60 encodings supported by
 * RoboVM in conjunction with utilizing iconv.
 * 
 * The decoding operations are performed via calls to decodeLoop.
 *  
 * decodeLoop calls iconv via JNI to do conversions between encodings 
 */
public class IconvDecoder extends CharsetDecoder{
    
    private ByteOrder currentByteOrder;
    
    private long iconv_tPointer = 0;
    
    /**
     * Constructor for {@link CharsetDecoder} of specified char set
     * 
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

        if(!out.isDirect() && !out.hasArray()) {
            throw new IllegalArgumentException();
        }
        
        allocateContentDescriptor(out.isDirect(), out.order());
        IconvResult result = IconvProvider.decode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case CONVERSION_OK:
            return CoderResult.UNDERFLOW;
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength(result.getBytesWrittenFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.malformedForLength(result.getBytesWrittenFromSource());
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

        
        if(iconv_tPointer == 0) {
            //Content descriptor needs to be allocated
            if (isDirect && byteOrder == ByteOrder.BIG_ENDIAN) {
                this.iconv_tPointer = IconvProvider.initConversion(((IconvCharset) charset()).getIconvName(), "UTF-16BE");
                this.currentByteOrder = ByteOrder.BIG_ENDIAN;
            } else {
                this.iconv_tPointer = IconvProvider.initConversion(((IconvCharset) charset()).getIconvName(), "UTF-16LE");
                this.currentByteOrder = ByteOrder.LITTLE_ENDIAN;
            }
        } else if (byteOrder != this.currentByteOrder) {
            throw new IllegalStateException("Illegal change of byte order");
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (iconv_tPointer == 0) {
            IconvProvider.release(iconv_tPointer);
            iconv_tPointer = 0;
        }
    }

    @Override
    protected CoderResult implFlush(CharBuffer out) {
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
            System.err.println("ImplReset called");
        }
    }

}
