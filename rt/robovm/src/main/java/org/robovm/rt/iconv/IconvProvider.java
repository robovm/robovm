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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**

 * A {@link CharsetProvider} enabling encoding / decoding of
 * textual data 
 */
public class IconvProvider extends CharsetProvider{

    /**
     * Result of an encoding / decoding operation
     */
    public enum ConversionResult {

        CONVERSION_OK,
        ILLEGAL_SEQUENCE,
        OUTPUT_BUFFER_TOO_SMALL,
        INCOMPLETE_SEQUENCE;

        /**
         * Converts error code to corresponding enum
         * @param error error code returned of iconv
         * @return A {@link ConversionResult}
         */
        public static ConversionResult getResult(int error) {
            switch (error) {
            case 0:
                return CONVERSION_OK;
            case 1:
                return ILLEGAL_SEQUENCE;
            case 2:
                return OUTPUT_BUFFER_TOO_SMALL;
            case 3:
                return INCOMPLETE_SEQUENCE;
            default:
                throw new IllegalArgumentException();
            }
        }
    }

    private static Map<String, Charset> charsets = new HashMap<String, Charset>();

    private static void addCharset(String name, IconvCharset cs) {
        charsets.put(name.toLowerCase(), cs);
    }
    
    static {
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            System.loadLibrary("iconvprovider");
        }
        
        addCharset("Big5", new IconvCharset("Big5", "BIG5", new String[] { "csBig5" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("Big5-HKSCS", new IconvCharset("Big5-HKSCS", "BIG5-HKSCS", new String[] { "Big5_HKSCS", "big5-hkscs", "big5hk",
                "big5hkscs" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("EUC-JP", new IconvCharset("EUC-JP", "EUC-JP", new String[] { "eucjis",
                "Extended_UNIX_Code_Packed_Format_for_Japanese", "x-eucjp", "eucjp", "csEUCPkdFmtjapanese", "euc_jp",
                "x-euc-jp" }, 0.5f, 1.0f, 3.0f, 3.0f));
        addCharset("EUC-KR", new IconvCharset("EUC-KR", "EUC-KR", new String[] { "5601", "ksc5601-1987", "ksc5601_1987",
                "euckr", "ksc5601", "ksc_5601", "ks_c_5601-1987", "euc_kr", "csEUCKR" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("GB18030", new IconvCharset("GB18030", "GB18030", new String[] { "gb18030-2000" }, 1.0f, 2.0f, 4.0f, 4.0f));
        addCharset("GB2312", new IconvCharset("GB2312", "GB2312", new String[] { "euc-cn", "x-EUC-CN", "gb2312-1980", "gb2312",
                "gb2312-80", "euccn", "EUC_CN" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("GBK", new IconvCharset("GBK", "GBK", new String[] { "CP936", "windows-936" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("IBM437", new IconvCharset("IBM437", "IBM437", new String[] { "ibm-437", "windows-437", "cspc8codepage437",
                "437", "ibm437", "cp437" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM775", new IconvCharset("IBM775", "IBM775", new String[] { "ibm-775", "cp775", "ibm775", "775" }, 1.0f,
                1.0f, 1.0f, 1.0f));
        addCharset("IBM850", new IconvCharset("IBM850", "IBM850", new String[] { "ibm-850", "cp850", "850",
                "cspc850multilingual", "ibm850" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM852", new IconvCharset("IBM852", "IBM852", new String[] { "ibm852", "csPCp852", "852", "ibm-852",
                "cp852" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM855", new IconvCharset("IBM855", "IBM855",new String[] { "cspcp855", "855", "ibm855", "ibm-855",
                "cp855" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM857", new IconvCharset("IBM857","IBM857", new String[] { "csIBM857", "857", "ibm-857", "cp857",
                "ibm857" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM860", new IconvCharset("IBM860", "IBM860", new String[] { "860", "cp860", "ibm-860", "csIBM860",
                "ibm860" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM861", new IconvCharset("IBM861", "IBM861", new String[] { "861", "cp-is", "ibm-861", "cp861",
                "csIBM861", "ibm861" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM862", new IconvCharset("IBM862", "IBM862", new String[] { "ibm-862", "ibm862", "csIBM862", "cp862",
                "cspc862latinhebrew", "862" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM863", new IconvCharset("IBM863", "IBM863", new String[] { "ibm863", "csIBM863", "cp863", "863",
                "ibm-863" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM864", new IconvCharset("IBM864", "IBM864", new String[] { "csIBM864", "ibm864", "864", "cp864",
                "ibm-864" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM865", new IconvCharset("IBM865", "IBM865", new String[] { "csIBM865", "ibm865", "865", "ibm-865",
                "cp865" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM866", new IconvCharset("IBM866", "IBM866", new String[] { "866", "ibm-866", "ibm866", "csIBM866",
                "cp866" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("IBM869", new IconvCharset("IBM869", "IBM869", new String[] { "869", "ibm-869", "cp869", "csIBM869",
                "cp-gr", "ibm869" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-2022-CN", new IconvCharset("ISO-2022-CN", "ISO-2022-CN", new String[] { "ISO2022CN", "csISO2022CN" }, 0.5f,
                0.5f, 2.0f, 2.0f));
        addCharset("ISO-2022-JP", new IconvCharset("ISO-2022-JP", "ISO-2022-JP", new String[] { "jis_encoding", "csjisencoding",
                "jis", "iso2022jp", "csISO2022JP" }, 0.5f, 1.0f, 4.0f, 8.0f));
        addCharset("ISO-2022-JP-2", new IconvCharset("ISO-2022-JP-2", "ISO-2022-JP-2", new String[] { "csISO2022JP2", "iso2022jp2" },
                0.5f, 1.0f, 4.0f, 9.0f));
        addCharset("ISO-2022-KR", new IconvCharset("ISO-2022-KR", "ISO-2022-KR", new String[] { "csISO2022KR", "ISO2022KR" }, 1.0f,
                1.0f, 4.0f, 8.0f));
        addCharset("ISO-8859-1", new IconvCharset("ISO-8859-1", "ISO-8859-1", new String[] { "csISOLatin1", "IBM-819",
                "iso-ir-100", "8859_1", "ISO_8859-1", "l1", "ISO8859-1", "ISO_8859_1", "cp819", "ISO8859_1", "latin1",
                "ISO_8859-1:1987", "819", "IBM819" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-13", new IconvCharset("ISO-8859-13", "ISO-8859-13", new String[] { "8859_13", "iso8859_13",
                "iso_8859-13", "ISO8859-13" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-15", new IconvCharset("ISO-8859-15", "ISO-8859-15", new String[] { "IBM923", "8859_15", "ISO_8859-15",
                "ISO-8859-15", "L9", "ISO8859-15", "ISO8859_15_FDIS", "923", "LATIN0", "csISOlatin9", "LATIN9",
                "csISOlatin0", "IBM-923", "ISO8859_15", "cp923" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-2", new IconvCharset("ISO-8859-2", "ISO-8859-2", new String[] { "iso-ir-101", "csISOLatin2",
                "ibm-912", "8859_2", "l2", "ISO_8859-2", "ibm912", "912", "ISO8859-2", "latin2", "iso8859_2",
                "ISO_8859-2:1987", "cp912" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-3", new IconvCharset("ISO-8859-3", "ISO-8859-3", new String[] { "ibm-913", "csISOLatin3",
                "iso-ir-109", "l3", "8859_3", "ibm913", "ISO_8859-3", "ISO8859-3", "913", "latin3", "iso8859_3",
                "ISO_8859-3:1988", "cp913" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-4", new IconvCharset("ISO-8859-4", "ISO-8859-4", new String[] { "iso-ir-110", "iso8859-4", "ibm914",
                "ibm-914", "l4", "csISOLatin4", "914", "8859_4", "latin4", "ISO_8859-4", "ISO_8859-4:1988",
                "iso8859_4", "cp914" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-5", new IconvCharset("ISO-8859-5", "ISO-8859-5", new String[] { "cp915", "ISO8859-5", "ibm915",
                "ISO_8859-5:1988", "ibm-915", "8859_5", "915", "cyrillic", "iso8859_5", "ISO_8859-5", "iso-ir-144",
                "csISOLatinCyrillic" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-6", new IconvCharset("ISO-8859-6", "ISO-8859-6", new String[] { "arabic", "ibm1089", "iso8859_6",
                "iso-ir-127", "8859_6", "cp1089", "ECMA-114", "ISO_8859-6", "csISOLatinArabic", "ibm-1089", "1089",
                "ISO8859-6", "ASMO-708", "ISO_8859-6:1987" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-7", new IconvCharset("ISO-8859-7", "ISO-8859-7", new String[] { "iso8859-7", "sun_eu_greek",
                "csISOLatinGreek", "813", "ISO_8859-7", "ISO_8859-7:1987", "ibm-813", "greek", "greek8", "iso8859_7",
                "ECMA-118", "iso-ir-126", "8859_7", "cp813", "ibm813", "ELOT_928" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-8", new IconvCharset("ISO-8859-8", "ISO-8859-8", new String[] { "ibm916", "cp916", "csISOLatinHebrew",
                "ISO_8859-8", "ISO8859-8", "ibm-916", "iso8859_8", "hebrew", "916", "iso-ir-138", "ISO_8859-8:1988",
                "8859_8" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("ISO-8859-9", new IconvCharset("ISO-8859-9", "ISO-8859-9", new String[] { "ISO_8859-9", "920", "iso8859_9",
                "csISOLatin5", "l5", "8859_9", "latin5", "ibm920", "iso-ir-148", "ISO_8859-9:1989", "ISO8859-9",
                "cp920", "ibm-920" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("JIS_X0201", new IconvCharset("JIS_X0201", "JIS_X0201", new String[] { "JIS0201", "JIS_X0201", "X0201",
                "csHalfWidthKatakana" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("JIS_X0212-1990", new IconvCharset("JIS_X0212-1990", "JIS_X0212-1990", new String[] { "csISO159JISX02121990",
                "x0212", "jis_x0212-1990", "iso-ir-159", "JIS0212" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("KOI8-R", new IconvCharset("KOI8-R", "KOI8-R", new String[] { "cskoi8r", "koi8_r", "koi8" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("KOI8-U", new IconvCharset("KOI8-U", "KOI8-U", new String[] { "koi8_u" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("Shift_JIS", new IconvCharset("Shift_JIS", "SHIFT_JIS", new String[] { "x-sjis", "shift_jis", "sjis",
                "ms_kanji", "shift-jis", "csShiftJIS" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("TIS-620", new IconvCharset("TIS-620", "TIS-620", new String[] { "tis620.2533", "tis620" }, 1.0f, 1.0f, 1.0f,
                1.0f));
        addCharset("US-ASCII", new IconvCharset("US-ASCII", "US-ASCII", new String[] { "cp367", "ascii7", "ISO646-US", "646",
                "csASCII", "us", "iso_646.irv:1983", "ISO_646.irv:1991", "IBM367", "ASCII", "default",
                "ANSI_X3.4-1986", "ANSI_X3.4-1968", "iso-ir-6" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("UTF-16", new IconvCharset("UTF-16", "UTF-16", new String[] { "utf16", "UTF_16", "UnicodeBig", "unicode" },
                0.5f, 1.0f, 2.0f, 4.0f));
        addCharset("UTF-16BE", new IconvCharset("UTF-16BE", "UTF-16BE", new String[] { "X-UTF-16BE", "UTF_16BE",
                "ISO-10646-UCS-2", "UnicodeBigUnmarked" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("UTF-16LE", new IconvCharset("UTF-16LE", "UTF-16LE", new String[] { "UnicodeLittleUnmarked", "UTF_16LE",
                "X-UTF-16LE" }, 0.5f, 1.0f, 2.0f, 2.0f));
        addCharset("UTF-32", new IconvCharset("UTF-32", "UTF-32", new String[] { "UTF_32", "UTF32" }, 0.250000f, 1.0f, 4.0f,
                4.0f));
        addCharset("UTF-32BE", new IconvCharset("UTF-32BE", "UTF-32BE", new String[] { "X-UTF-32BE", "UTF_32BE" }, 0.250000f,
                1.0f, 4.0f, 4.0f));
        addCharset("UTF-32LE", new IconvCharset("UTF-32LE", "UTF-32LE", new String[] { "X-UTF-32LE", "UTF_32LE" }, 0.250000f,
                1.0f, 4.0f, 4.0f));
        addCharset("UTF-8", new IconvCharset("UTF-8", "UTF-8", new String[] { "UTF8", "unicode-1-1-utf-8" }, 1.0f, 1.0f, 1.1f,
                3.0f));
        addCharset("windows-1250", new IconvCharset("windows-1250", "WINDOWS-1250",  new String[] { "cp1250", "cp5346" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("windows-1251", new IconvCharset("windows-1251", "WINDOWS-1251" , new String[] { "ansi-1251", "cp5347", "cp1251" },
                1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("windows-1252", new IconvCharset("windows-1252", "WINDOWS-1252", new String[] { "cp1252", "cp5348" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("windows-1253", new IconvCharset("windows-1253", "WINDOWS-1253", new String[] { "cp1253", "cp5349" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("windows-1254", new IconvCharset("windows-1254", "WINDOWS-1254", new String[] { "cp1254", "cp5350" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("windows-1255",
                new IconvCharset("windows-1255", "WINDOWS-1255", new String[] { "cp1255" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("windows-1256",
                new IconvCharset("windows-1256", "WINDOWS-1256", new String[] { "cp1256" }, 1.0f, 1.0f, 1.0f, 1.0f));
        addCharset("windows-1257", new IconvCharset("windows-1257", "WINDOWS-1257", new String[] { "cp1257", "cp5353" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        addCharset("windows-1258",
                new IconvCharset("windows-1258", "WINDOWS-1258", new String[] { "cp1258" }, 1.0f, 1.0f, 1.0f, 1.0f));
    
    }
	
    @Override
    public Iterator<Charset> charsets() {
        return charsets.values().iterator();
    }
	
    /**
     * Calls into jni / iconv to encode a sequence of chars to a specified
     * encoding and then sets conversion result and new positions and new limits
     * @param in {@link CharBuffer} containing data to encode
     * @param out resulting {@link ByteBuffer}
     * @return an IconvResult containing bytes transferred and result code
     */
    public static IconvResult encode(long pointer, CharBuffer in, ByteBuffer out) {
        boolean isInDirect = in.isDirect();
        boolean isOutDirect = out.isDirect();

        int res = 0;
        IconvResult result = new IconvResult();

        CharBuffer bufferInData = copyCharBufferData(in);
        ByteBuffer bufferOutData = copyByteBufferData(out);
        
        if (isInDirect && isOutDirect) {
            res = encodeNativeBuffer(pointer, in, out, result, in.position(), in.limit(), out.position(), out.limit());
        } else if(!isInDirect && !isOutDirect) {
            res = encodeNativeArray(pointer, bufferInData.array(), bufferOutData.array(), result, bufferInData.position(), bufferInData.limit(), bufferOutData.position(), bufferOutData.limit());
        } else if(isInDirect && !isOutDirect) {
            res = encodeHybridBufferArray(pointer, in, bufferOutData.array(), result, in.position(), in.limit(), bufferOutData.position(), bufferOutData.limit());
        } else {
            res = encodeHybridArrayBuffer(pointer, bufferInData.array(), out, result, bufferInData.position(), bufferInData.limit(), out.position(), out.limit());
        }
        
        //Set and check result of conversion
        ConversionResult conversionState = ConversionResult.getResult(res);
        result.setResultCode(conversionState);
        
        in.position(in.position() + result.getBytesReadFromSource()/2);
        //Copy out data to original out buffer
        if (out != bufferOutData) {
            bufferOutData.limit(result.getBytesWrittenToDestination());
            out.put(bufferOutData);
        } else {
            out.position(out.position() + result.getBytesWrittenToDestination());
        }

        return result;
    }
    
    /**
     * Copies content of buffer to array if needed
     * @param {@link CharBuffer} to copy
     * @return chararray with buffer content
     */
    static private CharBuffer copyCharBufferData(CharBuffer buffer) {
        if(!buffer.isDirect() && !buffer.hasArray()) {
            int pos = buffer.position();
            CharBuffer cb = CharBuffer.allocate(buffer.remaining()).put(buffer);
            cb.position(0);
            buffer.position(pos);
            return cb;
        } 
        return buffer;
    }
    
    /**
     * Copies content of {@Buffer} to array if needed
     * @param {@link ByteBuffer} to copy
     * @return bytearray with {@Buffer} content
     */
    static private ByteBuffer copyByteBufferData(ByteBuffer buffer) {
        if(!buffer.isDirect() && !buffer.hasArray()) {
            int pos = buffer.position();
            ByteBuffer bb = ByteBuffer.allocate(buffer.remaining()).put(buffer);
            bb.position(0);
            buffer.position(pos);
            return bb;
        } 
        return buffer;
    }
    
    /**
     * Calls into jni / iconv to encode a sequence of chars to a specified
     * encoding and then sets conversion result and new positions and new limits.
     * @param in containing data to decode {@link ByteBuffer}
     * @param out resulting {@link CharBuffer}
     * @return an IconvResult containing bytes transferred and result code
     */
    public static IconvResult decode(long pointer, ByteBuffer in, CharBuffer out) {
        boolean isInDirect = in.isDirect();
        boolean isOutDirect = out.isDirect();
        
        ByteBuffer bufferInData = copyByteBufferData(in);
        CharBuffer bufferOutData = copyCharBufferData(out);
        
        //decode
        int res = -1;
        IconvResult result = new IconvResult();

        if (isInDirect && isOutDirect) {
           res = decodeNativeBuffer(pointer, in, out, result, in.position(), in.limit(), out.position(), out.limit()); 
        } else if (!isInDirect && !isOutDirect) {
           res = decodeNativeArray(pointer, bufferInData.array(), bufferOutData.array(), result, bufferInData.position(), bufferInData.limit(), bufferOutData.position(), bufferOutData.limit());
        } else if(isInDirect && !isOutDirect) {
           res = decodeHybridBufferArray(pointer, in, bufferOutData.array(), result, in.position(), in.limit(), bufferOutData.position(), bufferOutData.limit()); 
        } else {
           res = decodeHybridArrayBuffer(pointer, bufferInData.array(), out, result, bufferInData.position(), bufferInData.limit(), out.position(), out.limit());   
        }
        
        //set and check result of conversion
        ConversionResult conversionState = ConversionResult.getResult(res);
        result.setResultCode(conversionState);
        
        in.position(in.position() + result.getBytesReadFromSource());
        //Copy out data to original out buffer
        if (out != bufferOutData) {
            bufferOutData.limit(result.getBytesWrittenToDestination()/2);
            out.put(bufferOutData);
        } else {
            //set position according to conversion data returned
            out.position(out.position() + result.getBytesWrittenToDestination() / 2);
        }
        
        return result;
    }
    
    /**
     * Sets up a native content_descriptor which stores conversion state
     * @param fromEncoding encoding to encode from
     * @param toEncoding encoding to encode to
     * @return pointer to content_descriptor on native side
     */
    public static long initConversion(String fromEncoding, String toEncoding) {
        return initIconv(fromEncoding, toEncoding);
    }
    
    /**
     * release content_descriptor on the native side
     * @param pointer
     */
    public static void release(long pointer) {
        releaseIconv(pointer);
    }
    
    private static native long initIconv(String fromEncoding, String toEncoding);
    
    private static native void releaseIconv( long pointer);

    //Native functions interfacing with shared native lib

    private static native int encodeNativeArray( long pointer, char[] in, byte[] out, IconvResult iconvResult, int positionIn,
          int limitIn, int positionOut, int limitOut);
    
    private static native int decodeNativeArray( long pointer, byte[] in, char[] out, IconvResult iconvResult, int positionIn,
          int limitIn, int positionOut, int limitOut);
      
    private static native int encodeNativeBuffer( long pointer, CharBuffer in, ByteBuffer out, IconvResult iconvResult, int positionIn,
          int limitIn, int positionOut, int limitOut);
    
    private static native int decodeNativeBuffer( long pointer, ByteBuffer in, CharBuffer out, IconvResult iconvResult, int positionIn,
          int limitIn, int positionOut, int limitOut);
    
    private static native int encodeHybridArrayBuffer( long pointer, char[] in, ByteBuffer out, IconvResult iconvResult, int positionIn,
            int limitIn, int positionOut, int limitOut);
      
    private static native int decodeHybridArrayBuffer( long pointer, byte[] in, CharBuffer out, IconvResult iconvResult, int positionIn,
        int limitIn, int positionOut, int limitOut);
    
    private static native int encodeHybridBufferArray( long pointer, CharBuffer in, byte[] out, IconvResult iconvResult, int positionIn,
            int limitIn, int positionOut, int limitOut);
      
    private static native int decodeHybridBufferArray( long pointer, ByteBuffer in, char[] out, IconvResult iconvResult, int positionIn,
        int limitIn, int positionOut, int limitOut);

    /**
     * Returns a {@link Charset} instance corresponding to specified name
     * @return a {@link Charset}
     */
    @Override
    public Charset charsetForName(String charsetName) {
        
        String name = charsetName.toLowerCase();
        if (charsets.containsKey(name)) {
            return charsets.get(name);
        }
        
        Iterator<Charset> it = charsets.values().iterator();
        while (it.hasNext()) {
            Charset cs = it.next();

            for (String alias : cs.aliases()) {
                if (alias.toLowerCase().equals(name)) {
                    return cs;
                }
            }
        }
        
        return null;
    }
}
