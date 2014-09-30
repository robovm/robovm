package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import org.junit.Before;
import org.junit.Test;

public class IconvEncoderDirectTest {
    
    @Before
    public void init() {
        
    }
    
    @Test
    public void testIconvEncodeDirect() {
        String s = "äsödfksöjgsoignduh g rguh irgh";
        CharBuffer in = ByteBuffer.allocateDirect(s.length()*2)./*order(ByteOrder.nativeOrder()).*/asCharBuffer();
        ByteBuffer out = ByteBuffer.allocateDirect(s.length()*2);
        in.append(s);
        in.position(0);
        out.position(0);
        
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(in, out, true);
        
        out.flip();
        byte[] bb =new byte[out.remaining()];
        out.get(bb);
        String ss = null;
        try {
            ss = new String(bb,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(ss.equals(s));
    }
    
    @Test
    public void testIconvEncodeDirectSwiftJIS() {
        String s = "\uFF66\uFF73";
        CharBuffer in = ByteBuffer.allocateDirect(s.length()*2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer out = ByteBuffer.allocateDirect(s.length()*3);
        in.append(s);
        in.position(0);
        out.position(0);
        
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Shift_JIS");
        cs.newEncoder().encode(in, out, true);
        
        out.flip();
        byte[] byteArray =new byte[out.remaining()];
        out.get(byteArray);
        String ss = null;
        try {
            ss = new String(byteArray,"Shift_JIS");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(ss.equals(s));
    }
    
    @Test
    public void testIconvEncodeDirectNoErrorHandling() {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String s = "lsdflsjfdösfäefk sdf jsfäsdfkäsökdf sdf hsdjfh sdfösädfi södfjs fd";
        CharBuffer charBuffer = ByteBuffer.allocateDirect(s.length()*2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(s.length()*3);
        charBuffer.append(s);
        charBuffer.position(0);
        byteBuffer.position(0);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");

        cs.newEncoder().encode(charBuffer, byteBuffer, true);
        
        byteBuffer.flip();
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        String ss = null;
        try {
            ss = new String(byteArray,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(ss.equals(s));
    }
    
    @Test
    public void testIconvEncodeEmptyInBuffer() {
        
        String s = "";
        CharBuffer charBuffer = ByteBuffer.allocateDirect(s.length()*2).order(ByteOrder.nativeOrder()).asCharBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(s.length()*3);

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);
        
        charBuffer = CharBuffer.allocate(s.length());
        byteBuffer.position(0);
        
        charBuffer.flip();
        String output = charBuffer.toString();
        
        byteBuffer.flip();
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        
        assertTrue(s.equals(output));
    }
    
    @Test
    public void testIconvEncodeDirectNoErrorHandlingBigBuffer() throws Exception{
        String iconv = getSmallBufStringIconv();
        String java = getSmallBufStringJava();
        assertTrue(iconv.equals(java));
    }
    
    
    private String getSmallBufStringJava() throws UnsupportedEncodingException {
        //taken from charset at http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        //ｦｳ
        String toEncode = "det var en gång en vätte som bodde på en ö som hette Kortedala";
        
        CharBuffer charBuffer = ByteBuffer.allocateDirect(toEncode.length()*2).asCharBuffer();//CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
        charBuffer.append(toEncode);
        charBuffer.rewind();
        
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
               // Arrays.fill(byteBuffer.array(), (byte) 0);
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
        
        CharBuffer charBuffer = ByteBuffer.allocateDirect(toEncode.length()*2).asCharBuffer();//CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
        charBuffer.append(toEncode);
        charBuffer.rewind();
        
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
                //Arrays.fill(byteBuffer.array(), (byte) 0);
            } while(cr.isOverflow());
            
            utf8String = new String(sb.toString().getBytes(), "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

    
}
