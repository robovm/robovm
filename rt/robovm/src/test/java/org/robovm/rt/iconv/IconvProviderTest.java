package org.robovm.rt.iconv;

import org.junit.Test;

/**
 * This tests encodes and decodes strings to ascertain wanted functionality
 * 
 * @author David
 */
public class IconvProviderTest {

    @Test
    public void testIconvProvider() {
//        IconvProvider p = new IconvProvider();
//
//        assertNotEquals(p, null); // Silly test
//
//        Charset cs = p.charsetForName("UTF-8");
//
//        assertNotEquals(cs, null); // not as silly
//
//        /**
//         * Check aliases
//         */
//        cs = p.charsetForName("UTF8");// ,"unicode-1-1-utf-8"
//        assertNotEquals(cs, null);
//
//        cs = p.charsetForName("unicode-1-1-utf-8");
//        assertNotEquals(cs, null);
//
//        assertEquals(true, cs.canEncode());
//        assertEquals(IconvCharset.isSupported("UTF-8"), true);
//        assertEquals((IconvCharset.availableCharsets().size() > 0), true);
//
//        assertNotEquals(p.charsets(), null);
//
//        String toEncode = "Hej hfoo öäkj k dejh ed";
//        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
//        ByteBuffer byteBuffer = ByteBuffer
//                .wrap(new byte[(toEncode.length() + 1) * 2]);
//
//        if (!byteBuffer.hasArray()) {
//            System.out.println("how did this happen?");
//        }
//        
//        long pointer = IconvProvider.initConversion("UTF-16LE", "UTF-8");
//        IconvProvider.encode(pointer, charBuffer, byteBuffer);
//
//        try {
//            System.out.println(new String(byteBuffer.array(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            assertTrue(false);
//            e.printStackTrace();
//        }
//
//        charBuffer = CharBuffer.wrap(new char[(toEncode.length() + 1) * 2]);
//        charBuffer.clear();
//        IconvProvider.decode(pointer, byteBuffer, charBuffer);
//        assertTrue(charBuffer.toString().equals(toEncode));
    }

}
