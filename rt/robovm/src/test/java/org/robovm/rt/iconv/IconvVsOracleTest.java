package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

public class IconvVsOracleTest {

    @Test
    public void testIconvEncodeDirectNoErrorHandlingBigBuffer() throws Exception {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            String iconv = getSmallBufStringIconv();
            String java = getSmallBufStringJava();
            assertTrue(iconv.equals(java));
        }
    }

    private String getSmallBufStringJava() throws UnsupportedEncodingException {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        String toEncode = "det var en gång en vätte";

        CharBuffer charBuffer = ByteBuffer.allocateDirect(toEncode.length() * 2).asCharBuffer();
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
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

    private String getSmallBufStringIconv() {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
        String toEncode = "det var en gång en vätte";

        CharBuffer charBuffer = ByteBuffer.allocateDirect(toEncode.length() * 2).asCharBuffer();
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
                // Arrays.fill(byteBuffer.array(), (byte) 0);
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

    @Test
    public void testIconvEncodeArraysNoErrorHandlingBigBuffer() throws Exception {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            String iconv = getSmallBufStringArrayIconv();
            String java = getSmallBufStringArrayJava();
            assertTrue(iconv.equals(java));
        } else {
            assertTrue(true);
        }
    }
    
    private String getSmallBufStringArrayJava() throws UnsupportedEncodingException {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
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
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }

    private String getSmallBufStringArrayIconv() {
        // taken from charset at
        // http://www.kreativekorp.com/charset/encoding.php?name=Shift-JIS
        // ｦｳ
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
            } while (cr.isOverflow());

            utf8String = new String(sb.toString().getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            assertTrue(false);
            e.printStackTrace();
        }
        return utf8String;
    }
    
    @Test
    @Ignore
    public void testCharsetsOracleVsIconv() {
        @SuppressWarnings("rawtypes")
        Iterator it = new IconvProvider().charsets();
        
        while (it.hasNext()) {
            Charset iconvCharset = (Charset) it.next();
            Charset oracleCharset = Charset.forName(iconvCharset.name());
            
            CharsetEncoder iconvEncoder = null;
            CharsetEncoder oracleEncoder = null;
            try {
                iconvEncoder = iconvCharset.newEncoder();
                oracleEncoder = oracleCharset.newEncoder();
            } catch (IllegalArgumentException e) {
                continue;
            }

            for (int code = 32; code <= 65533; code ++) {
                if (!(iconvEncoder.canEncode((char) code) == oracleEncoder.canEncode((char) code))) {
                    System.out.println("oracle: " + oracleEncoder.canEncode((char) code) + " iconv: "+ iconvEncoder.canEncode((char) code) + " " + iconvCharset.name() + ":" + code);
                }
            }
        }
    }
}
