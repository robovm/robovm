/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.rt.iconv;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Iconv {@link Charset} class which encapsulates
 * char set configs to use in conjunction with
 * the iconv library
 */
public class IconvCharset extends Charset{

    private float averageCharsPerByte;
    private float maxCharsPerByte;
    private float averageBytesPerChar;
    private float maxBytesPerChar;

    private String iconvName;
    
    /**
     * Constructor for {@link IconvCharset} which encapsulates char set configs which
     * are used for buffer allocations during encoding / decoding operations.
     * 
     * @param canonicalName the canonical name used to identify this char set
     * @param iconvName
     * @param aliases aliases for given char set 1 - *
     * @param averageCharsPerByte
     * @param maxCharsPerByte
     * @param averageBytesPerChar
     * @param maxBytesPerChar
     * 
     */
    public IconvCharset(String canonicalName, String iconvName, String[] aliases, float averageCharsPerByte, float maxCharsPerByte,
            float averageBytesPerChar, float maxBytesPerChar) {
        super(canonicalName, aliases);
        
        this.iconvName = iconvName;
        this.averageCharsPerByte = averageCharsPerByte;
        this.maxCharsPerByte = maxCharsPerByte;
        this.averageBytesPerChar = averageBytesPerChar;
        this.maxBytesPerChar = maxBytesPerChar;
    }

    /**
     * Checks if this {@link Charset} subsumes the specified charset
     * 
     * @return whether this charset subsumes the specified.
     */
    @Override
    public boolean contains(Charset cs) {
        if (this == cs) {
            return true;
        }
        return false;
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new IconvDecoder(this, averageCharsPerByte, maxCharsPerByte);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new IconvEncoder(this, averageBytesPerChar, maxBytesPerChar);
    }

    public String getIconvName() {
        return iconvName;
    }
}
