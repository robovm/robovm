package org.robovm.rt.iconv;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
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
 *  
 */
public class IconvEncoder extends CharsetEncoder{
	
    private long iconv_tPointer = -1;
    
    /**
     * Constructor for {@link CharsetEncoder} with specified char encoding constants.
     */
    public IconvEncoder(Charset cs, float averageBytesPerChar,
            float maxBytesPerChar) {
        super(cs, averageBytesPerChar, maxBytesPerChar);
        this.iconv_tPointer = IconvProvider.initConversion("UTF-16LE", ((IconvCharset) cs).getIconvName());
    }


    /**
     * Basic encoding loop, it encodes as many chars as possible until either:
     * 
     * * it runs out of input 
     * * it runs out of space in the output buffer 
     * * illegal char has been encountered 
     * * encounters encoding error
     * 
     * The in buffer us assumed to be in UTF-16LE.
     * 
     * @return a {@link CoderResult} object indicating current encoding state
     */
    @Override
    protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
        
        if (in.isDirect() && in.order() == ByteOrder.BIG_ENDIAN) {
            IconvProvider.release(iconv_tPointer);
            
            //If an error occurs on the native side an error code can be return instead of
            //a pointer... error codes range from 1 - 10
            this.iconv_tPointer = IconvProvider.initConversion("UTF-16BE", ((IconvCharset) charset()).getIconvName());
            if (this.iconv_tPointer < 10) {
                return CoderResult.malformedForLength(1);
            }
        }
        
        IconvResult result = IconvProvider.encode(iconv_tPointer, in, out);

        switch (result.getResultCode()) {
        case OUTPUT_BUFFER_TOO_SMALL:
            return CoderResult.OVERFLOW;
        case ILLEGAL_SEQUENCE:
            return CoderResult.unmappableForLength(result.getBytesWrittenFromSource());
        case INCOMPLETE_SEQUENCE:
            return CoderResult.malformedForLength(result.getBytesWrittenFromSource());
        case WEIRD_ERROR:
            return CoderResult.unmappableForLength(1); //this is iffy
        default:
            break;
        }
        return CoderResult.UNDERFLOW;
    }
	
    
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        IconvProvider.release(iconv_tPointer);
    }


    public static void main(String[] args) throws UnsupportedEncodingException, CharacterCodingException {
//        IconvProvider p = IconvProvider.getInstance();
//
//        String toEncode = "djfhsdf gogas dja";
//        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
//        ByteBuffer byteBuffer = null;// ByteBuffer.wrap(new
//                                     // byte[(toEncode.length()+1)*2]);
//
//        Charset cs = p.charsetForName("SHIFT_JIS");
//        IconvEncoder se = (IconvEncoder) cs.newEncoder();
//        byteBuffer = se.encode(charBuffer);// , byteBuffer, true);
//
//        for (byte b : byteBuffer.array()) {
//            System.out.println(String.format("%02x", b));
//        }
//
//        charBuffer = cs.newDecoder().decode(byteBuffer);
//        String ss = new String(charBuffer.toString().getBytes("SHIFT_JIS"));
//        System.out.println(ss);

    }

}
