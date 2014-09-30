package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.junit.Test;

/**
 * 
 * @author David
 */
public class IconvCharsetTest {
    
    @Test
    public void testIconvCharset() {
        
        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("Big5");
        
        assertTrue(cs != null);
        assertTrue(cs instanceof IconvCharset);
        
        CharsetDecoder cd = cs.newDecoder();
        CharsetEncoder ce = cs.newEncoder();
        
        assertTrue(cd instanceof IconvDecoder);
        assertTrue(ce instanceof IconvEncoder);
        
        assertTrue(cd != null);
        assertTrue(ce != null);
        
    }
}
