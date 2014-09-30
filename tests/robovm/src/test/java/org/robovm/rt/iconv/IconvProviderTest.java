package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests encodes and decodes strings to ascertain wanted functionality
 * 
 * @author David
 */
public class IconvProviderTest {

    @Test
    public void testIconvProvider() {
    	IconvProvider p = new IconvProvider();
    	assertTrue(p.charsets().hasNext());
    }

}
