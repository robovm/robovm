/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.compiler.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility methods for calculating hashes.
 */
public class DigestUtil {
    private static final String HEX_CHARS = "0123456789abcdef";

    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new Error("Could not find MessageDigest algorithm " + algorithm, ex);
        }
    }

    public static String sha1(String s) {
        try {
            return encodeHex(digest("SHA1", s.getBytes("utf8")));
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }
    
    private static byte[] digest(String algorithm, byte[] bytes) {
        return getDigest(algorithm).digest(bytes);
    }

    private static String encodeHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff;
            sb.append(HEX_CHARS.charAt((b >> 4) & 0xf));
            sb.append(HEX_CHARS.charAt(b & 0xf));
        }
        return sb.toString();
    }
}
