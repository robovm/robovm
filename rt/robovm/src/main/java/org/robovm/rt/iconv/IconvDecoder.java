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
 * @author David
 */
public class IconvDecoder extends CharsetDecoder{

    private long iconv_tPointer = -1;
    
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
        this.iconv_tPointer = IconvProvider.initConversion(((IconvCharset) cs).getIconvName(), "UTF-16LE");
    }

    /**
     * basic decoding loop, it decodes as many chars as possible until either:
     * 
     * * it runs out of input 
     * * it runs out of space in the output buffer 
     * * illegal char has been encountered 
     * * encounters encoding error
     * 
     * The out buffer data is assumed to be in UTF-16LE
     * 
     * @return a {@link CoderResult} object indicating current encoding state
     */
    @Override
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
        
        if (out.isDirect() && out.order() == ByteOrder.BIG_ENDIAN) {
            IconvProvider.release(iconv_tPointer);
            this.iconv_tPointer = IconvProvider.initConversion(((IconvCharset) charset()).getIconvName(), "UTF-16BE");
            
            //If an error occurs on the native side an error code can be return instead of
            //a pointer... error codes range from 1 - 10
            if (this.iconv_tPointer < 10) {
                return CoderResult.malformedForLength(1);
            }
        }
        
        IconvResult result = IconvProvider.decode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength(result.getBytesWrittenFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.malformedForLength(result.getBytesWrittenFromSource());
        case WEIRD_ERROR:
            return CoderResult.malformedForLength(1);  //this is iffy
        default:
            // @TODO handle this properly - should never happen but implemented
            // to get rid of warning
            break;
        }
        return CoderResult.UNDERFLOW;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        IconvProvider.release(iconv_tPointer);
    }

    
    
}
