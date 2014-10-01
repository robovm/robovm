package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

import org.junit.Test;

/**
 * Tests array backed encoders
 * 
 */
public class IconvEncoderArrayTest {
    
    @Test
    public void testIconvEncodeArraysUTF8() {
        
        String toEncode = "Hhhhöädglpdågplgdäglh";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        if (!byteBuffer.hasArray()) {
            System.out.println("how did this happen?");
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        
        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);
        
        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
    
    @Test
    public void testIconvEncodeEmptyInBuffer() {
        
        String toEncode = "";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        if (!byteBuffer.hasArray()) {
            System.out.println("how did this happen?");
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        
        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);
        
        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
    
    @Test
    public void testIconvEncodeArraysShiftJIS() {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String toEncode = "\uFF66\uFF73";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        if (!byteBuffer.hasArray()) {
            System.out.println("how did this happen?");
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);

        try {
            String utf8String = new String(byteBuffer.array(), "Shift_JIS");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        
        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);
        
        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
    
    
    @Test
    public void testIconvEncodeArraysNoErrorHandling() {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String toEncode = "lsdflsjfdösfäefk sdf jsfäsdfkäsökdf sdf hsdjfh sdfösädfi södfjs fd";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocate(toEncode.length()*2);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        
        try {
            cs.newEncoder().encode(charBuffer, byteBuffer, true);
            String utf8String = new String(byteBuffer.array(), "UTF-8");
            assertTrue(utf8String.trim().equals(toEncode));
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        } 
        
        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);
        
        charBuffer.flip();
        String output = charBuffer.toString();
        assertTrue(toEncode.equals(output));
    }
    
    @Test
    public void testIconvEncodeArraysNoErrorHandlingBigBuffer() throws Exception{
    	if (!System.getProperty("java.vendor").equals("RoboVM")) {
	        String iconv = getSmallBufStringIconv();
	        String java = getSmallBufStringJava();
	        assertTrue(iconv.equals(java));
    	} else {
    		assertTrue(true);
    	}
    }
    
    
    private String getSmallBufStringJava() throws UnsupportedEncodingException {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String toEncode = "det var en gång en vätte som bodde på en ö som hette Kortedala";

        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byte[] array = new byte[5];

        String utf8String = null;
        Charset cs = Charset.forName("UTF-8");
        CharsetEncoder encoder = cs.newEncoder();
        StringBuilder sb = new StringBuilder();
        try {
            CoderResult cr = null;
            do {
                cr = encoder.encode(charBuffer, byteBuffer, true);
                byteBuffer.flip();
                
                byteBuffer.get(array, 0, byteBuffer.remaining());
                sb.append(new String(array, "UTF-8"));
                byteBuffer.flip();
                byteBuffer.clear();
                Arrays.fill(byteBuffer.array(), (byte) 0);
            } while(cr.isOverflow());
            
            utf8String = new String(sb.toString().getBytes(), "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }
    
    private String getSmallBufStringIconv() {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String toEncode = "det var en gång en vätte som bodde på en ö som hette Kortedala";

        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byte[] array = new byte[5];

        
        String utf8String = null;
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        
        CharsetEncoder encoder = cs.newEncoder();
        StringBuilder sb = new StringBuilder();
        try {
            CoderResult cr = null;
            do {
                cr = encoder.encode(charBuffer, byteBuffer, true);
                byteBuffer.flip();
                
                byteBuffer.get(array, 0, byteBuffer.remaining());
                sb.append(new String(array, "UTF-8"));
                byteBuffer.flip();
                byteBuffer.clear();
                Arrays.fill(byteBuffer.array(), (byte) 0);
            } while(cr.isOverflow());
            
            utf8String = new String(sb.toString().getBytes(), "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

    
}
