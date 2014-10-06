package org.robovm.rt.iconv;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Iconv char set class which encapsulates
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
     * Constructor for IconvCharset which encapsulates char set configs which
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
     * Checks if this charset subsumes the specified charset
     * 
     * @return whether this charset subsumes the specified.
     */
    @Override
    public boolean contains(Charset cs) {
        if (this == cs) {
            return true;
        }

        // @TODO do subsumes check here

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
