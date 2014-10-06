package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * Tests array backed decoders
 */
public class IconvDecoderArrayTest {

    @Test
    public void testIconvDecoderUTF8() throws Exception {
        String toDecode = "\u0078\u0079\u00D6";

        ByteBuffer byteBuffer = ByteBuffer.wrap(toDecode.getBytes("UTF-8"));

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        CharBuffer charBuffer = null;
        try {
            charBuffer = cs.newDecoder().decode(byteBuffer);
        } catch (CharacterCodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        String output = charBuffer.toString();
        assertTrue("xyÖ".equals(output));
    }

    @Test
    public void testIconvDecoderShiftJIS() {
        String s = "\uFF66\uFF73";

        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = ByteBuffer.wrap(s.getBytes("Shift_JIS"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        CharBuffer charBuffer = null;
        try {
            charBuffer = cs.newDecoder().decode(byteBuffer);
        } catch (CharacterCodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        String output = charBuffer.toString();
        String checkValue = null;

        checkValue = new String("\uFF66\uFF73");

        assertTrue(output.equals(checkValue));
    }

}
