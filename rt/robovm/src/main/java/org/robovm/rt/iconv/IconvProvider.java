package org.robovm.rt.iconv;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * IconvProvider
 * A provider of Iconv backed charsets enabling
 * encoding / decoding on all platforms with
 * iconv installed
 * 
 * Not thread safe at the moment
 */
public class IconvProvider extends CharsetProvider{

    /**
     * Result of an encoding / decoding operation
     * 
     * @author David
     */
    public enum ConversionResult {

        CONVERSION_OK,
        ILLEGAL_SEQUENCE,
        OUTPUT_BUFFER_TOO_SMALL,
        INCOMPLETE_SEQUENCE,
        ERROR_METHOD_ID,
        ERROR_ARRAY_FUNCTION_CALL,
        ERROR_DESCRIPTOR,
        WEIRD_ERROR;

        /**
         * Converts error code to corresponding enum
         * 
         * @param error error code returned of iconv
         * @return result of conversion
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
            case 4:
                return ERROR_METHOD_ID;
            case 5:
                return ERROR_ARRAY_FUNCTION_CALL;
            case 6:
                return ERROR_DESCRIPTOR;
            default:
                return WEIRD_ERROR;
            }
        }
    }

    private static Map<String, Charset> charsets = new HashMap<String, Charset>();

    static {
        //System.loadLibrary("iconv");
        if (!System.getProperty("java.vendor").equals("RoboVM")) {
            System.loadLibrary("iconvprovider");
        }
        
        charsets.put("Big5", new IconvCharset("Big5", "BIG5", new String[] { "csBig5" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("Big5-HKSCS", new IconvCharset("Big5-HKSCS", "BIG5-HKSCS", new String[] { "Big5_HKSCS", "big5-hkscs", "big5hk",
                "big5hkscs" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("EUC-JP", new IconvCharset("EUC-JP", "EUC-JP", new String[] { "eucjis",
                "Extended_UNIX_Code_Packed_Format_for_Japanese", "x-eucjp", "eucjp", "csEUCPkdFmtjapanese", "euc_jp",
                "x-euc-jp" }, 0.5f, 1.0f, 3.0f, 3.0f));
        charsets.put("EUC-KR", new IconvCharset("EUC-KR", "EUC-KR", new String[] { "5601", "ksc5601-1987", "ksc5601_1987",
                "euckr", "ksc5601", "ksc_5601", "ks_c_5601-1987", "euc_kr", "csEUCKR" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("GB18030", new IconvCharset("GB18030", "GB18030", new String[] { "gb18030-2000" }, 1.0f, 2.0f, 4.0f, 4.0f));
        charsets.put("GB2312", new IconvCharset("GB2312", "GB2312", new String[] { "euc-cn", "x-EUC-CN", "gb2312-1980", "gb2312",
                "gb2312-80", "euccn", "EUC_CN" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("GBK", new IconvCharset("GBK", "GBK", new String[] { "CP936", "windows-936" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("IBM437", new IconvCharset("IBM437", "IBM437", new String[] { "ibm-437", "windows-437", "cspc8codepage437",
                "437", "ibm437", "cp437" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM775", new IconvCharset("IBM775", "IBM775", new String[] { "ibm-775", "cp775", "ibm775", "775" }, 1.0f,
                1.0f, 1.0f, 1.0f));
        charsets.put("IBM850", new IconvCharset("IBM850", "IBM850", new String[] { "ibm-850", "cp850", "850",
                "cspc850multilingual", "ibm850" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM852", new IconvCharset("IBM852", "IBM852", new String[] { "ibm852", "csPCp852", "852", "ibm-852",
                "cp852" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM855", new IconvCharset("IBM855", "IBM855",new String[] { "cspcp855", "855", "ibm855", "ibm-855",
                "cp855" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM857", new IconvCharset("IBM857","IBM857", new String[] { "csIBM857", "857", "ibm-857", "cp857",
                "ibm857" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM860", new IconvCharset("IBM860", "IBM860", new String[] { "860", "cp860", "ibm-860", "csIBM860",
                "ibm860" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM861", new IconvCharset("IBM861", "IBM861", new String[] { "861", "cp-is", "ibm-861", "cp861",
                "csIBM861", "ibm861" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM862", new IconvCharset("IBM862", "IBM862", new String[] { "ibm-862", "ibm862", "csIBM862", "cp862",
                "cspc862latinhebrew", "862" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM863", new IconvCharset("IBM863", "IBM863", new String[] { "ibm863", "csIBM863", "cp863", "863",
                "ibm-863" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM864", new IconvCharset("IBM864", "IBM864", new String[] { "csIBM864", "ibm864", "864", "cp864",
                "ibm-864" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM865", new IconvCharset("IBM865", "IBM865", new String[] { "csIBM865", "ibm865", "865", "ibm-865",
                "cp865" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM866", new IconvCharset("IBM866", "IBM866", new String[] { "866", "ibm-866", "ibm866", "csIBM866",
                "cp866" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("IBM869", new IconvCharset("IBM869", "IBM869", new String[] { "869", "ibm-869", "cp869", "csIBM869",
                "cp-gr", "ibm869" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-2022-CN", new IconvCharset("ISO-2022-CN", "ISO-2022-CN", new String[] { "ISO2022CN", "csISO2022CN" }, 0.5f,
                0.5f, 2.0f, 2.0f));
        charsets.put("ISO-2022-JP", new IconvCharset("ISO-2022-JP", "ISO-2022-JP", new String[] { "jis_encoding", "csjisencoding",
                "jis", "iso2022jp", "csISO2022JP" }, 0.5f, 1.0f, 4.0f, 8.0f));
        charsets.put("ISO-2022-JP-2", new IconvCharset("ISO-2022-JP-2", "ISO-2022-JP-2", new String[] { "csISO2022JP2", "iso2022jp2" },
                0.5f, 1.0f, 4.0f, 9.0f));
        charsets.put("ISO-2022-KR", new IconvCharset("ISO-2022-KR", "ISO-2022-KR", new String[] { "csISO2022KR", "ISO2022KR" }, 1.0f,
                1.0f, 4.0f, 8.0f));
        charsets.put("ISO-8859-1", new IconvCharset("ISO-8859-1", "ISO-8859-1", new String[] { "csISOLatin1", "IBM-819",
                "iso-ir-100", "8859_1", "ISO_8859-1", "l1", "ISO8859-1", "ISO_8859_1", "cp819", "ISO8859_1", "latin1",
                "ISO_8859-1:1987", "819", "IBM819" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-13", new IconvCharset("ISO-8859-13", "ISO-8859-13", new String[] { "8859_13", "iso8859_13",
                "iso_8859-13", "ISO8859-13" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-15", new IconvCharset("ISO-8859-15", "ISO-8859-15", new String[] { "IBM923", "8859_15", "ISO_8859-15",
                "ISO-8859-15", "L9", "ISO8859-15", "ISO8859_15_FDIS", "923", "LATIN0", "csISOlatin9", "LATIN9",
                "csISOlatin0", "IBM-923", "ISO8859_15", "cp923" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-2", new IconvCharset("ISO-8859-2", "ISO-8859-2", new String[] { "iso-ir-101", "csISOLatin2",
                "ibm-912", "8859_2", "l2", "ISO_8859-2", "ibm912", "912", "ISO8859-2", "latin2", "iso8859_2",
                "ISO_8859-2:1987", "cp912" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-3", new IconvCharset("ISO-8859-3", "ISO-8859-3", new String[] { "ibm-913", "csISOLatin3",
                "iso-ir-109", "l3", "8859_3", "ibm913", "ISO_8859-3", "ISO8859-3", "913", "latin3", "iso8859_3",
                "ISO_8859-3:1988", "cp913" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-4", new IconvCharset("ISO-8859-4", "ISO-8859-4", new String[] { "iso-ir-110", "iso8859-4", "ibm914",
                "ibm-914", "l4", "csISOLatin4", "914", "8859_4", "latin4", "ISO_8859-4", "ISO_8859-4:1988",
                "iso8859_4", "cp914" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-5", new IconvCharset("ISO-8859-5", "ISO-8859-5", new String[] { "cp915", "ISO8859-5", "ibm915",
                "ISO_8859-5:1988", "ibm-915", "8859_5", "915", "cyrillic", "iso8859_5", "ISO_8859-5", "iso-ir-144",
                "csISOLatinCyrillic" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-6", new IconvCharset("ISO-8859-6", "ISO-8859-6", new String[] { "arabic", "ibm1089", "iso8859_6",
                "iso-ir-127", "8859_6", "cp1089", "ECMA-114", "ISO_8859-6", "csISOLatinArabic", "ibm-1089", "1089",
                "ISO8859-6", "ASMO-708", "ISO_8859-6:1987" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-7", new IconvCharset("ISO-8859-7", "ISO-8859-7", new String[] { "iso8859-7", "sun_eu_greek",
                "csISOLatinGreek", "813", "ISO_8859-7", "ISO_8859-7:1987", "ibm-813", "greek", "greek8", "iso8859_7",
                "ECMA-118", "iso-ir-126", "8859_7", "cp813", "ibm813", "ELOT_928" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-8", new IconvCharset("ISO-8859-8", "ISO-8859-8", new String[] { "ibm916", "cp916", "csISOLatinHebrew",
                "ISO_8859-8", "ISO8859-8", "ibm-916", "iso8859_8", "hebrew", "916", "iso-ir-138", "ISO_8859-8:1988",
                "8859_8" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("ISO-8859-9", new IconvCharset("ISO-8859-9", "ISO-8859-9", new String[] { "ISO_8859-9", "920", "iso8859_9",
                "csISOLatin5", "l5", "8859_9", "latin5", "ibm920", "iso-ir-148", "ISO_8859-9:1989", "ISO8859-9",
                "cp920", "ibm-920" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("JIS_X0201", new IconvCharset("JIS_X0201", "JIS_X0201", new String[] { "JIS0201", "JIS_X0201", "X0201",
                "csHalfWidthKatakana" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("JIS_X0212-1990", new IconvCharset("JIS_X0212-1990", "JIS_X0212-1990", new String[] { "csISO159JISX02121990",
                "x0212", "jis_x0212-1990", "iso-ir-159", "JIS0212" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("KOI8-R", new IconvCharset("KOI8-R", "KOI8-R", new String[] { "cskoi8r", "koi8_r", "koi8" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("KOI8-U", new IconvCharset("KOI8-U", "KOI8-U", new String[] { "koi8_u" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("Shift_JIS", new IconvCharset("Shift_JIS", "SHIFT_JIS", new String[] { "x-sjis", "shift_jis", "sjis",
                "ms_kanji", "shift-jis", "csShiftJIS" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("TIS-620", new IconvCharset("TIS-620", "TIS-620", new String[] { "tis620.2533", "tis620" }, 1.0f, 1.0f, 1.0f,
                1.0f));
        charsets.put("US-ASCII", new IconvCharset("US-ASCII", "US-ASCII", new String[] { "cp367", "ascii7", "ISO646-US", "646",
                "csASCII", "us", "iso_646.irv:1983", "ISO_646.irv:1991", "IBM367", "ASCII", "default",
                "ANSI_X3.4-1986", "ANSI_X3.4-1968", "iso-ir-6" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("UTF-16", new IconvCharset("UTF-16", "UTF-16", new String[] { "utf16", "UTF_16", "UnicodeBig", "unicode" },
                0.5f, 1.0f, 2.0f, 4.0f));
        charsets.put("UTF-16BE", new IconvCharset("UTF-16BE", "UTF-16BE", new String[] { "X-UTF-16BE", "UTF_16BE",
                "ISO-10646-UCS-2", "UnicodeBigUnmarked" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("UTF-16LE", new IconvCharset("UTF-16LE", "UTF-16LE", new String[] { "UnicodeLittleUnmarked", "UTF_16LE",
                "X-UTF-16LE" }, 0.5f, 1.0f, 2.0f, 2.0f));
        charsets.put("UTF-32", new IconvCharset("UTF-32", "UTF-32", new String[] { "UTF_32", "UTF32" }, 0.250000f, 1.0f, 4.0f,
                4.0f));
        charsets.put("UTF-32BE", new IconvCharset("UTF-32BE", "UTF-32BE", new String[] { "X-UTF-32BE", "UTF_32BE" }, 0.250000f,
                1.0f, 4.0f, 4.0f));
        charsets.put("UTF-32LE", new IconvCharset("UTF-32LE", "UTF-32LE", new String[] { "X-UTF-32LE", "UTF_32LE" }, 0.250000f,
                1.0f, 4.0f, 4.0f));
        charsets.put("UTF-8", new IconvCharset("UTF-8", "UTF-8", new String[] { "UTF8", "unicode-1-1-utf-8" }, 1.0f, 1.0f, 1.1f,
                3.0f));
        charsets.put("windows-1250", new IconvCharset("windows-1250", "WINDOWS-1250",  new String[] { "cp1250", "cp5346" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("windows-1251", new IconvCharset("windows-1251", "WINDOWS-1251" , new String[] { "ansi-1251", "cp5347", "cp1251" },
                1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("windows-1252", new IconvCharset("windows-1252", "WINDOWS-1252", new String[] { "cp1252", "cp5348" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("windows-1253", new IconvCharset("windows-1253", "WINDOWS-1253", new String[] { "cp1253", "cp5349" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("windows-1254", new IconvCharset("windows-1254", "WINDOWS-1254", new String[] { "cp1254", "cp5350" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("windows-1255",
                new IconvCharset("windows-1255", "WINDOWS-1255", new String[] { "cp1255" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("windows-1256",
                new IconvCharset("windows-1256", "WINDOWS-1256", new String[] { "cp1256" }, 1.0f, 1.0f, 1.0f, 1.0f));
        charsets.put("windows-1257", new IconvCharset("windows-1256", "WINDOWS-1257", new String[] { "cp1257", "cp5353" }, 1.0f, 1.0f,
                1.0f, 1.0f));
        charsets.put("windows-1258",
                new IconvCharset("windows-1258", "WINDOWS-1258", new String[] { "cp1258" }, 1.0f, 1.0f, 1.0f, 1.0f));
    
    }
	
    @Override
    public Iterator<Charset> charsets() {
        return charsets.values().iterator();
    }
	
    /**
     * Calls into jni / iconv to encode a sequence of chars to a specified
     * encoding and then checks of errors and sets new positions and new limits
     * 
     * @param from encoding of in buffer data
     * @param to wanted encoding of out buffer data
     * @param in in buffer
     * @param out out buffer
     * @return an IconvResult containing bytes transferred and result code
     */
    public static IconvResult encode(long pointer, CharBuffer in, ByteBuffer out) {
        boolean isInDirect = in.isDirect();
        boolean isOutDirect = out.isDirect();

        int res = 0;
        IconvResult result = new IconvResult();

        //CharBuffer bufferIn = verifyByteOrder(in);
        if (isInDirect && isOutDirect) {
            res = encodeNativeBuffer(pointer, in, out, result, in.position(), in.limit(), out.position(), out.limit());
        } else if(!isInDirect && !isOutDirect) {
            res = encodeNativeArray(pointer, in.array(), out.array(), result, in.position(), in.limit(), out.position(), out.limit());
        } else if(isInDirect && !isOutDirect) {
            res = encodeHybridBufferArray(pointer, in, out.array(), result, in.position(), in.limit(), out.position(), out.limit());
        } else {
            res = encodeHybridArrayBuffer(pointer, in.array(), out, result, in.position(), in.limit(), out.position(), out.limit());
        }
        
        //Set and check result of conversion
        ConversionResult conversionState = ConversionResult.getResult(res);
        result.setResultCode(conversionState);
        
        in.position(in.position() + result.getBytesWrittenFromSource()/2);
        out.position(out.position() + result.getBytesWrittenToDestination());
        out.limit(out.position());

        return result;
    }
    
    /**
     * Calls into jni / iconv to encode a sequence of chars to a specified
     * encoding and then checks of errors and sets new positions and new limits.
     * 
     * @param from encoding of in buffer data
     * @param to wanted encoding of out buffer data
     * @param in in buffer
     * @param out out buffer
     * @return an IconvResult containing bytes transferred and result code
     */
    public static IconvResult decode(long pointer, ByteBuffer in, CharBuffer out) {
        boolean isInDirect = in.isDirect();
        boolean isOutDirect = out.isDirect();
        
        //decode
        int res = -1;
        IconvResult result = new IconvResult();

        //CharBuffer outBuffer = verifyByteOrder(out);
        if (isInDirect && isOutDirect) {
           res = decodeNativeBuffer(pointer, in, out, result, in.position(), in.limit(), out.position(), out.limit()); 
        } else if (!isInDirect && !isOutDirect) {
           res = decodeNativeArray(pointer, in.array(), out.array(), result, in.position(), in.limit(), out.position(), out.limit());
        } else if(isInDirect && !isOutDirect) {
           res = decodeHybridBufferArray(pointer, in, out.array(), result, in.position(), in.limit(), out.position(), out.limit()); 
        } else {
           res = decodeHybridArrayBuffer(pointer, in.array(), out, result, in.position(), in.limit(), out.position(), out.limit());   
        }
        
        //set and check result of conversion
        ConversionResult conversionState = ConversionResult.getResult(res);
        result.setResultCode(conversionState);
        
        //set position according to conversion data returned
        in.position(in.position() + result.getBytesWrittenFromSource());
        out.position(out.position() + result.getBytesWrittenToDestination() / 2);
        out.limit(out.position());
        
        return result;
    }
    
    public static long initConversion(String fromEncoding, String toEncoding) {
        return initIconv(fromEncoding, toEncoding);
    }
    
    public static void release(long pointer) {
        releaseIconv(pointer);
    }
	
    private static native long initIconv(String fromEncoding, String toEncoding);
    
    private static native void releaseIconv( long pointer);
    
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
     * Returns Charset instance corresponding to specified name
     * @return a charset
     */
    @Override
    public Charset charsetForName(String charsetName) {
        
        if (charsets.containsKey(charsetName)) {
            return charsets.get(charsetName);
        }
        
        Iterator<Charset> it = charsets.values().iterator();
        while (it.hasNext()) {
            Charset cs = it.next();

            for (String alias : cs.aliases()) {
                if (alias.equals(charsetName)) {
                    return cs;
                }
            }
        }
        
        return null;
    }
	
    public static void main(String[] args) throws UnsupportedEncodingException {

        String toEncode = "Hhhhöädglpdågplgdäglh";
        CharBuffer charBuffer = CharBuffer.wrap(toEncode.toCharArray());
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[toEncode.length() * 2]);

        if (!byteBuffer.hasArray()) {
            System.out.println("how did this happen?");
        }

        IconvProvider p = new IconvProvider();
        Charset cs = p.charsetForName("UTF-8");
        cs.newEncoder().encode(charBuffer, byteBuffer, true);
        System.out.println(new String(byteBuffer.array(), "UTF-8"));
        
        charBuffer.clear();
        charBuffer = CharBuffer.allocate(toEncode.length());
        byteBuffer.position(0);
        cs.newDecoder().decode(byteBuffer, charBuffer, true);
        
        System.out.println(charBuffer.toString());
    }

}
