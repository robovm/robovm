package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

public class IconvProviderTest {
	
	@Test
	public void testLoadingCharset() {
		Charset cs = Charset.forName("Big5");
		cs.newEncoder();
		cs.newDecoder();
		System.out.println("testing iconv");
		assertTrue(cs != null);
	}

}
