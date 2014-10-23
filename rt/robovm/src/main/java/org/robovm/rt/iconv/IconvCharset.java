/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.rt.iconv;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Iconv {@link Charset} class which encapsulates
 * {@link Charset} configs to use with the iconv library
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
     * @param canonicalName the canonical name used to identify this {@link Charset}
     * @param iconvName canonical name of {@link Charset} in iconv
     * @param aliases aliases for given char set 
     * @param averageCharsPerByte
     * @param maxCharsPerByte
     * @param averageBytesPerChar
     * @param maxBytesPerChar
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
     * Checks if this {@link Charset} subsumes the specified {@link Charset}
     * @return whether this {@link Charset} subsumes the specified.
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

    /**
     * returns iconv {@link Charset} name
     * @return name of {@link Charset}
     */
    public String getIconvName() {
        return iconvName;
    }
    
}
