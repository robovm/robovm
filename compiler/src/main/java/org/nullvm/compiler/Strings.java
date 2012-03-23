/**
 * 
 */
package org.nullvm.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niklas
 *
 */
public class Strings {

    public static String getStringVarName(String name) {
        return getStringVarName(stringToModifiedUtf8(name));
    }
    
    public static String getStringVarName(byte[] bytes) {
        StringBuilder sb = new StringBuilder("str_");
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b >= 'a' && b <= 'z' || b >= 'A' && b <= 'Z' || b >= '0' && b <= '9') {
                sb.append((char) b);
            } else {
                sb.append(String.format("_%02X", b));
            }
        }
        return sb.toString();
    }
    
    public static byte[] stringToModifiedUtf8(String unicode) {
        List<Byte> s = new ArrayList<Byte>();
        for (int i = 0; i < unicode.length(); i++) {
            int ch = unicode.charAt(i);
            if (ch == 0) {
                s.add((byte) 0xc0);
                s.add((byte) 0x80);
            } else if (ch < 0x80) {
                s.add((byte) ch);
            } else if(ch < 0x800) {
                int b5_0 = ch & 0x3f;
                int b10_6 = (ch >> 6) & 0x1f;
                s.add((byte) (0xc0 | b10_6));
                s.add((byte) (0x80 | b5_0));
            } else {
                int b5_0 = ch & 0x3f;
                int b11_6 = (ch >> 6) & 0x3f;
                int b15_12 = (ch >> 12) & 0xf;
                s.add((byte) (0xe0 | b15_12));
                s.add((byte) (0x80 | b11_6));
                s.add((byte) (0x80 | b5_0));
            }
        }
        s.add((byte) 0);
        byte[] result = new byte[s.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.get(i);
        }
        return result;
    }
    
}
