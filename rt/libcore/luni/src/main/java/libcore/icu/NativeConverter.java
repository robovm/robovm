/**
*******************************************************************************
* Copyright (C) 1996-2006, International Business Machines Corporation and    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*
*******************************************************************************
*/

package libcore.icu;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

public final class NativeConverter {
    public static native int decode(long converterHandle, byte[] input, int inEnd,
            char[] output, int outEnd, int[] data, boolean flush);

    public static native int encode(long converterHandle, char[] input, int inEnd,
            byte[] output, int outEnd, int[] data, boolean flush);

    public static native long openConverter(String charsetName);
    public static native void closeConverter(long converterHandle);

    public static native void resetByteToChar(long converterHandle);
    public static native void resetCharToByte(long converterHandle);

    public static native byte[] getSubstitutionBytes(long converterHandle);

    public static native int getMaxBytesPerChar(long converterHandle);
    public static native int getMinBytesPerChar(long converterHandle);
    public static native float getAveBytesPerChar(long converterHandle);
    public static native float getAveCharsPerByte(long converterHandle);

    public static native boolean contains(String converterName1, String converterName2);

    public static native String[] getAvailableCharsetNames();
    public static native Charset charsetForName(String charsetName);

    // Translates from Java's enum to the magic numbers #defined in "NativeConverter.cpp".
    private static int translateCodingErrorAction(CodingErrorAction action) {
        if (action == CodingErrorAction.REPORT) {
            return 0;
        } else if (action == CodingErrorAction.IGNORE) {
            return 1;
        } else if (action == CodingErrorAction.REPLACE) {
            return 2;
        } else {
            throw new AssertionError(); // Someone changed the enum.
        }
    }

    public static void setCallbackDecode(long converterHandle, CharsetDecoder decoder) {
        setCallbackDecode(converterHandle,
                          translateCodingErrorAction(decoder.malformedInputAction()),
                          translateCodingErrorAction(decoder.unmappableCharacterAction()),
                          decoder.replacement());
    }
    private static native void setCallbackDecode(long converterHandle, int onMalformedInput, int onUnmappableInput, String subChars);

    public static void setCallbackEncode(long converterHandle, CharsetEncoder encoder) {
        setCallbackEncode(converterHandle,
                          translateCodingErrorAction(encoder.malformedInputAction()),
                          translateCodingErrorAction(encoder.unmappableCharacterAction()),
                          encoder.replacement());
    }
    private static native void setCallbackEncode(long converterHandle, int onMalformedInput, int onUnmappableInput, byte[] subBytes);
}
