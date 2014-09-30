package org.robovm.rt.iconv;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;

import org.junit.Test;

/**
 * Tests surrogate pairs
 */
public class SurrogatePairTest {
    
    @Test
    public void unicodeTest() {
      // little endian encoding
      IconvProvider p = new IconvProvider();
      CharsetDecoder decoder = p.charsetForName("UTF-16LE").newDecoder();
   
      // Code point: 120120 (mathematical double-struck capital A)
      ByteBuffer bytes = ByteBuffer.wrap(new byte[] {
          (byte)0x35, (byte)0xD8, (byte)0x38, (byte)0xDD
        });
      
      String decoded = null;
      try {
        decoded = decoder.decode(bytes).toString();
      } catch (CharacterCodingException e) {
        throw new RuntimeException(e);
      }
      String weirdoChar = new String(Character.toChars(120120));
      assertTrue(weirdoChar.equals(decoded));
    }
}
