/*
 * Copyright (C) 2012 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niklas
 *
 */
public class Strings {

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
        return stringToModifiedUtf8(unicode, false);
    }
    
    public static byte[] stringToModifiedUtf8Z(String unicode) {
        return stringToModifiedUtf8(unicode, true);
    }
    
    private static byte[] stringToModifiedUtf8(String unicode, boolean zeroTerminate) {
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
        if (zeroTerminate) {
            s.add((byte) 0);
        }
        byte[] result = new byte[s.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.get(i);
        }
        return result;
    }
    
}
