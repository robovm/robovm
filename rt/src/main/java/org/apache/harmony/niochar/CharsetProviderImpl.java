/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package org.apache.harmony.niochar;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is an implementation of the java.nio.charset.spi.CharsetProvider
 * class, in spite of the fact that it is abstract. It is a base class of a
 * concrete character set provider implementation Please note, a derived class
 * should define the getPackageName() and getCharsetsInfo() methods. The first
 * of them has to return a string with a package name where the derived class is
 * located. The second one has to construct an array, the structure of which is
 * described below. See CharsetProviderImplStd or CharsetProviderImplExt for
 * example.
 */
public class CharsetProviderImpl extends CharsetProvider {

    /**
     * Flags whether the default providers have got the native implementation
     * loaded. These are optional and used to improve performance in some
     * circumstances.
     */
    private static boolean HAS_LOADED_NATIVES = false;

    static {
        try {
            System.loadLibrary("hyniochar"); //$NON-NLS-1$
            HAS_LOADED_NATIVES = true;
        } catch (UnsatisfiedLinkError e) {
            // Ignore - leave as natives unavailable.
        }
    }

    /**
     * The named index of the 0th element of the <code>charsets[]</code> array.
     * It means a charset class name.
     */
    protected static final int CHARSET_CLASS = 0;

    /**
     * The named index of the 1st element of the <code>charsets[]</code> array.
     * It means a charset instance.
     */
    protected static final int CHARSET_INSTANCE = 1;

    /**
     * The named index of the 2nd element of the <code>charsets[]</code> array.
     * It means a charset aliases array.
     */
    protected static final int CHARSET_ALIASES = 2;

    /**
     * Answers whether the provider has loaded the native implementation of the
     * encoders/decoders.
     * 
     * @return true if the natives are loaded.
     */
    public static boolean hasLoadedNatives() {
        return HAS_LOADED_NATIVES;
    }

    /**
     * A cache of the charset instances.
     */
    protected Map<String, Object[]> cache;

    /**
     * An array returned by <code>getCharsetsInfo()</code>.
     */
    protected Object charsets[][];

    /**
     * A package name returned by <code>getPackageName()</code>.
     */
    protected String packageName;

    /**
     * Default constructor for the built-in charset provider implementation.
     */
    public CharsetProviderImpl() {
        cache = Collections.synchronizedMap(new HashMap<String, Object[]>());
        charsets = getCharsetsInfo();
        packageName = getPackageName();
        for (int i = 0; i < charsets.length; i++) {
            String aliases[] = (String[]) charsets[i][CHARSET_ALIASES];
            for (int a = 0; a < aliases.length; a++) {
                cache.put(aliases[a].toUpperCase(), charsets[i]);
            }
        }
    }

    /**
     * Answers an iterator over the list of available charsets.
     * 
     * @return available charsets.
     */
    @Override
    public Iterator<Charset> charsets() {
        ArrayList<Charset> list = new ArrayList<Charset>();
        for (int i = 0; i < charsets.length; i++) {
            list
                    .add(charsetForName(((String[]) charsets[i][CHARSET_ALIASES])[0]));
        }
        return list.iterator();
    }

    /**
     * Answers the charset with the given canonical or alias name.
     * 
     * Subsequent requests for the same charset will answer the same instance.
     * If the charset is unavailable the method returns <code>null</code>.
     * 
     * @param charsetName
     *            the name of a character set.
     * @return the charset requested, or <code>null</code> if unavailable.
     */
    @Override
    public Charset charsetForName(String charsetName) {
        Object arr[] = cache.get(charsetName.toUpperCase());
        if (arr == null) {
            return null;
        }
        // Make an instance of the found charset.
        if (arr[CHARSET_INSTANCE] == null) {
            final String className = packageName
                    + "." + (String) arr[CHARSET_CLASS]; //$NON-NLS-1$
            final String canonicalName = ((String[]) arr[CHARSET_ALIASES])[0];
            final String aliases[] = (String[]) arr[CHARSET_ALIASES];
            arr[CHARSET_INSTANCE] = AccessController
                    .doPrivileged(new PrivilegedAction<Object>() {
                        public Object run() {
                            try {
                                Class<?> cls = Class.forName(className);
                                Constructor<?> ctor = cls
                                        .getConstructor(new Class[] {
                                                String.class, String[].class });
                                ctor.setAccessible(true);
                                return ctor.newInstance(new Object[] {
                                        canonicalName, aliases });
                            } catch (Exception e) {
                                return null;
                            }
                        }
                    });
        }
        return (Charset) arr[CHARSET_INSTANCE];
    }

    /**
     * A helper method for answering all the available charsets in this
     * provider.
     * 
     * The method adds to the given map by storing charset canonical names as
     * the keys, with associated charsets as the value.
     * 
     * @param map
     *            for storing the descriptions of the charsets.
     */
    public final void putCharsets(Map<String, Charset> map) {
        Object[][] charsetInfo = getCharsetsInfo();
        for (int i = 0; i < charsetInfo.length; i++) {
            final String canonicalName = ((String[]) charsetInfo[i][CHARSET_ALIASES])[0];
            Charset cs = charsetForName(canonicalName);
            if (cs != null) {
                map.put(canonicalName, cs);
            }
        }
    }

    protected String getPackageName() {
        return "org.apache.harmony.niochar.charset"; //$NON-NLS-1$
    }

    protected Object[][] getCharsetsInfo() {
        /* The next charset aliases corresponds IANA registry 
         * http://www.iana.org/assignments/character-sets.
         * 
         *
         * Array structure:
         *
         * charsetsInfo[][0] - String: A charset class name.
         *                     The named index is CHARSET_CLASS.
         * charsetsInfo[][1] - Charset: A charset instance.
         *                     The named index is CHARSET_INSTANCE.
         * charsetsInfo[][2] - String[]: A charset aliases array.
         *                     The named index is CHARSET_ALIASES.
         *                     THE FIRST ELEMENT OF THE ALIASES ARRAY MUST BE 
         *                     A CANONICAL CHARSET NAME.
         */
        @SuppressWarnings("nls")
        Object charsetsInfo[][] = {
            { "US_ASCII",    null,new String[] { "US-ASCII", 
                                                 "ANSI_X3.4-1968", 
                                                 "ANSI_X3.4-1986", 
                                                 "iso-ir-6", 
                                                 "iso_646.irv:1983", 
                                                 "ISO_646.irv:1991", 
                                                 "ASCII", 
                                                 "ISO646-US", 
                                                 "us", 
                                                 "cp367", 
                                                 "ascii7", 
                                                 "646", 
                                                 "csASCII" } },
                                  
            { "KOI8_R",      null,new String[] { "KOI8-R", 
                                                 "csKOI8R" } },
                                  
            { "CP_1250",     null,new String[] { "windows-1250", 
                                                 "cp1250" } },
                                  
            { "CP_1251",     null,new String[] { "windows-1251", 
                                                 "cp1251" } },
                                  
            { "CP_1252",     null,new String[] { "windows-1252", 
                                                 "cp1252" } },
                                  
            { "CP_1253",     null,new String[] { "windows-1253", 
                                                 "cp1253" } },
                                  
            { "CP_1254",     null,new String[] { "windows-1254", 
                                                 "cp1254" } },
                                  
            { "CP_1257",     null,new String[] { "windows-1257", 
                                                 "cp1257" } },
                                  
            { "ISO_8859_1",  null,new String[] { "ISO-8859-1",
                                                 "8859_1", /*not in IANA Registry*/
                                                 "ISO8859-1", /*not in IANA Registry*/
                                                 "ISO8859_1", /*not in IANA Registry*/
                                                 "ISO_8859-1:1987",
                                                 "iso-ir-100", 
                                                 "ISO_8859-1", 
                                                 "latin1", 
                                                 "l1",
                                                 "IBM819",
                                                 "ISO_8859_1",
                                                 "IBM-819",
                                                 "CP819",
                                                 "819",
                                                 "csISOLatin1" } },
                                  
            { "ISO_8859_2",  null,new String[] { "ISO-8859-2",
                                                 "8859_2", /*not in IANA Registry*/
                                                 "ISO_8859-2:1987",
                                                 "iso-ir-101",
                                                 "ISO_8859-2",
                                                 "latin2", 
                                                 "l2",
                                                 "csISOLatin2" } },
                                  
            { "ISO_8859_4",  null,new String[] { "ISO-8859-4",
                                                 "8859_4", /*not in IANA Registry*/
                                                 "ISO_8859-4:1988",
                                                 "iso-ir-110",
                                                 "ISO_8859-4",
                                                 "latin4", 
                                                 "l4",
                                                 "csISOLatin4" } },
                                  
            { "ISO_8859_5",  null,new String[] { "ISO-8859-5",
                                                 "8859_5", /*not in IANA Registry*/
                                                 "ISO_8859-5:1988",
                                                 "iso-ir-144",
                                                 "ISO_8859-5",
                                                 "cyrillic", 
                                                 "csISOLatinCyrillic" } },
                                  
            { "ISO_8859_7",  null,new String[] { "ISO-8859-7", 
                                                 "ISO_8859-7:1987",
                                                 "ISO_8859-7",
                                                 "iso-ir-126",
                                                 "ELOT_928",
                                                 "ECMA-118",
                                                 "greek", 
                                                 "greek8", 
                                                 "csISOLatinGreek" } },
                                  
            { "ISO_8859_9",  null,new String[] { "ISO-8859-9", 
                                                 "ISO_8859-9:1989",
                                                 "iso-ir-148",
                                                 "ISO_8859-9",
                                                 "latin5", 
                                                 "l5",
                                                 "csISOLatin5" } },
            { "ISO_8859_13", null,new String[] { "ISO-8859-13"} },
                                
            { "ISO_8859_15", null,new String[] { "ISO-8859-15", 
                                                 "ISO_8859-15",
                                                 "Latin-9" } },
                                
            { "UTF_8",       null,new String[] { "UTF-8", 
                                                 "UTF8" /*not in IANA Registry*/} },
                                  
            { "UTF_16",      null,new String[] { "UTF-16",
                                                 "UTF16",
                                                 "UTF_16" } },
                              
            { "UTF_16LE",    null,new String[] { "UTF-16LE",
                                                 "X-UTF-16LE",
                                                 "UTF_16LE" } },
                                  
            { "UTF_16BE",    null,new String[] { "UTF-16BE",
                                                  "X-UTF-16BE",
                                                  "UTF_16BE" } },

            { "IBM866",      null, new String[] { "cp866",
                                                  "IBM866",
                                                  "866",
                                                  "csIBM866" } }, 

//additional charsets

            { "additional.windows_1255",null, new String[] { "windows-1255", 
                                                  "cp1255" } },

            { "additional.windows_1256",null, new String[] { "windows-1256", 
                                                  "cp1256" } },

            { "additional.IBM1026",     null, new String[] { "IBM1026", 
                                                  "CP1026",
                                                  "csIBM1026" } },

            { "additional.IBM1047",    null, new String[] {  "IBM1047",
                                                  "1047",
                                                  "cp1047",
                                                  "ibm-1047" } },

            { "additional.IBM037",      null, new String[] { "IBM037", 
                                                  "cp037",
                                                  "ebcdic-cp-us", 
                                                  "ebcdic-cp-ca", 
                                                  "ebcdic-cp-wt", 
                                                  "ebcdic-cp-nl", 
                                                  "csIBM037" } },

            { "additional.IBM424",      null, new String[] { "IBM424",
                                                  "cp424",
                                                  "ebcdic-cp-he",
                                                  "csIBM424" } },

            { "additional.IBM437",      null, new String[] { "IBM437",
                                                  "cp437",
                                                  "437",
                                                  "csPC8CodePage437" } },

            { "additional.IBM500",      null, new String[] { "IBM500", 
                                                  "CP500",
                                                  "ebcdic-cp-be", 
                                                  "ebcdic-cp-ch", 
                                                  "csIBM500" } },

            { "additional.IBM775",      null, new String[] { "IBM775", 
                                                  "cp775",
                                                  "csPC775Baltic" } },

            { "additional.IBM850",      null, new String[] { "cp850",
                                                  "IBM850",
                                                  "850",
                                                  "csPC850Multilingual" } },

            { "additional.IBM852",      null, new String[] { "IBM852",
                                                  "cp852",
                                                  "852",
                                                  "csPCp852" } },

            { "additional.IBM855",      null, new String[] { "IBM855",
                                                  "cp855",
                                                  "855",
                                                  "csIBM855" } },

            { "additional.IBM857",      null, new String[] { "cp857",
                                                  "IBM857",
                                                  "857",
                                                  "csIBM857" } },

            { "additional.IBM860",      null, new String[] { "cp860",
                                                  "IBM860",
                                                  "860",
                                                  "csIBM860" } },

            { "additional.IBM861",      null, new String[] { "cp861",
                                                  "IBM861",
                                                  "861",
                                                  "cp-is",
                                                  "csIBM861" } },

            { "additional.IBM862",      null, new String[] { "cp862",
                                                  "IBM862",
                                                  "862",
                                                  "csPC862LatinHebrew" } },

            { "additional.IBM863",      null, new String[] { "cp863",
                                                  "IBM863",
                                                  "863",
                                                  "csIBM863" } },

            { "additional.IBM865",      null, new String[] { "cp865",
                                                  "IBM865",
                                                  "865",
                                                  "csIBM865" } },

            { "additional.IBM869",      null, new String[] { "cp869",
                                                  "IBM869",
                                                  "869",
                                                  "cp-gr",
                                                  "csIBM869" } },


            { "additional.IBM00858",    null, new String[] { "cp858",
                                                  "IBM00858",
                                                  "CCSID00858",
                                                  "CP00858" } },

            { "additional.IBM01140",    null, new String[] { "IBM01140",
                                                  "cp1140",
                                                  "CCSID01140",
                                                  "CP01140" } },

            { "additional.IBM01141",    null, new String[] { "IBM01141",
                                                  "cp1141",
                                                  "CCSID01141",
                                                  "CP01141" } },

            { "additional.IBM01142",    null, new String[] { "IBM01142",
                                                  "cp1142",
                                                  "CCSID01142",
                                                  "CP01142" } },

            { "additional.IBM01143",    null, new String[] { "IBM01143",
                                                  "cp1143",
                                                  "CCSID01143",
                                                  "CP01143" } },

            { "additional.IBM01144",    null, new String[] { "IBM01144",
                                                  "cp1144",
                                                  "CCSID01144",
                                                  "CP01144" } },

            { "additional.IBM01145",    null, new String[] { "IBM01145",
                                                  "cp1145",
                                                  "CCSID01145",
                                                  "CP01145" } },

            { "additional.IBM01146",    null, new String[] { "IBM01146",
                                                  "cp1146",
                                                  "CCSID01146",
                                                  "CP01146" } },

            { "additional.IBM01147",    null, new String[] { "IBM01147",
                                                  "cp1147",
                                                  "CCSID01147",
                                                  "CP01147" } },

            { "additional.IBM01148",    null, new String[] { "IBM01148",
                                                  "cp1148",
                                                  "CCSID01148",
                                                  "CP01148" } },

            { "additional.IBM01149",    null, new String[] { "IBM01149",
                                                  "cp1149",
                                                  "CCSID01149",
                                                  "CP01149" } },

            { "additional.IBM273",      null, new String[] { "IBM273",
                                                  "cp273",
                                                  "csIBM273" } },

            { "additional.IBM277",      null, new String[] { "IBM277",
                                                  "cp277",
                                                  "EBCDIC-CP-DK",
                                                  "EBCDIC-CP-NO",
                                                  "csIBM277" } },

            { "additional.IBM278",      null, new String[] { "IBM278",
                                                  "cp278",
                                                  "ebcdic-cp-fi",
                                                  "ebcdic-cp-se",
                                                  "csIBM278" } },

            { "additional.IBM280",      null, new String[] { "IBM280",
                                                  "cp280",
                                                  "ebcdic-cp-it",
                                                  "csIBM280" } },

            { "additional.IBM284",      null, new String[] { "IBM284",
                                                  "cp284",
                                                  "ebcdic-cp-es",
                                                  "csIBM284" } },

            { "additional.IBM285",      null, new String[] { "IBM285",
                                                  "cp285",
                                                  "ebcdic-cp-gb",
                                                  "csIBM285" } },

            { "additional.IBM297",      null, new String[] { "IBM297",
                                                  "cp297",
                                                  "ebcdic-cp-fr",
                                                  "csIBM297" } },

            { "additional.IBM870",      null, new String[] { "IBM870",
                                                  "cp870",
                                                  "ebcdic-cp-roece",
                                                  "ebcdic-cp-yu",
                                                  "csIBM870" } },

            { "additional.IBM871",      null, new String[] { "IBM871",
                                                  "cp871",
                                                  "ebcdic-cp-is",
                                                  "csIBM871" } },

            { "additional.IBM918",      null, new String[] { "IBM918",
                                                  "cp918",
                                                  "ebcdic-cp-ar2",
                                                  "csIBM918" } },

            { "additional.IBM420",      null, new String[] { "IBM420",
                                                  "cp420",
                                                  "ebcdic-cp-ar1",
                                                  "csIBM420" } },

            { "additional.IBM864",      null, new String[] { "cp864",
                                                  "IBM864",
                                                  "csIBM864" } },

            { "additional.IBM868",      null, new String[] { "cp868",
                                                  "IBM868",
                                                  "cp-ar",
                                                  "csIBM868" } },

            { "additional.ISO_8859_3",  null, new String[] { "ISO-8859-3",
                                                  "8859_3", /*not in IANA Registry*/
                                                  "ISO_8859_3", 
                                                  "ISO_8859_2:1998",
                                                  "iso-ir-109",
                                                  "ISO_8859-3",
                                                  "latin3", 
                                                  "l3",
                                                  "csISOLatin3" } },

            { "additional.ISO_8859_6",  null, new String[] { "ISO-8859-6", 
                                                  "ISO_8859_6", 
                                                  "ISO_8859-6:1987",
                                                  "iso-ir-127",
                                                  "ISO_8859-6",
                                                  "ECMA-114", 
                                                  "ASMO-708",
                                                  "arabic",
                                                  "csISOLatinArabic" } },
            
            { "additional.ISO_8859_8",  null, new String[] { "ISO-8859-8", 
                                                  "ISO_8859_8", 
                                                  "ISO_8859-8:1988",
                                                  "iso-ir-138",
                                                  "ISO_8859-8",
                                                  "hebrew", 
                                                  "csISOLatinHebrew" } },
            
            { "additional.IBM_Thai",    null, new String[] { "IBM-Thai",
                                                  "cp838" } },

            { "additional.x_IBM737",    null, new String[] { "x-ibm-737_P100-1997",
                                                  "x-IBM737",
                                                  "cp737" } },

            { "additional.x_IBM856",    null, new String[] { "cp856",
                                                  "x-IBM856"} },

            { "additional.x_IBM874",    null, new String[] { "TIS-620",
                                                  "x-IBM874",
                                                  "cp874"} },

            { "additional.x_IBM875",    null, new String[] { "x-ibm-875_P100-1995",
                                                  "x-IBM875",
                                                  "cp875"} },

            { "additional.x_IBM922",    null, new String[] { "cp922",
                                                  "x-IBM922"} },

            { "additional.x_IBM1006",   null, new String[] { "x-ibm-1006_P100-1995",
                                                  "x-IBM1006",
                                                  "cp1006"} },

            { "additional.x_IBM1025",   null, new String[] { "x-ibm-1025_P100-1995",
                                                  "x-IBM1025",
                                                  "cp1025"} },

            { "additional.x_IBM1112",   null, new String[] { "x-ibm-1112_P100-1995",
                                                  "x-IBM1112",
                                                  "cp1112"} },

            { "additional.x_IBM1122",   null, new String[] { "x-ibm-1122_P100-1999",
                                                  "x-IBM1122",
                                                  "cp1122"} },

            { "additional.x_IBM1123",   null, new String[] { "x-ibm-1123_P100-1995",
                                                  "x-IBM1123",
                                                  "cp1123"} },

            { "additional.x_IBM1124",   null, new String[] { "x-ibm-1124_P100-1996",
                                                  "x-IBM1124",
                                                  "cp1124"} },

            { "additional.x_IBM1097",   null, new String[] { "x-ibm-1097_P100-1995",
                                                  "x-IBM1097",
                                                  "cp1097"} },

            { "additional.x_IBM1098",   null, new String[] { "x-ibm-1098_P100-1995",
                                                  "x-IBM1098",
                                                  "cp1098"} },

            { "additional.x_MacCyrillic",           null, new String[] { "x-mac-cyrillic",
                                                              "x-MacCyrillic",
                                                              "MacCyrillic"} },

            { "additional.x_MacGreek",              null, new String[] { "x-mac-greek",
                                                              "x-MacGreek",
                                                              "MacGreek"} },

            { "additional.x_MacTurkish",              null, new String[] { "x-mac-turkish",
                                                              "x-MacTurkish",
                                                              "MacTurkish"} },

            { "additional.windows_31j",            null, new String[] { "Shift_JIS",
                                                             "windows-31j",
                                                             "MS932",
                                                             "windows-932",
                                                             "cp932",
                                                             "csWindows31J",
                                                             "cp943c",
                                                             "x-ms-cp932",
                                                             "ibm-943"} },

            { "additional.Big5",                  null, new String[] { "Big5",
                                                            "csBig5",
                                                            "windows-950"} },

            { "additional.Big5_HKSCS",            null, new String[] { "Big5-HKSCS",
                                                             "ibm-1375"} },

            { "additional.EUC_KR",                null, new String[] { "EUC-KR",
                                                            "windows-51949",
                                                            "ibm-970" } },

            { "additional.GBK",                   null, new String[] { "GBK" } },

            { "additional.x_MS950_HKSCS",         null, new String[] { "x-ibm-1375_P100-2003",
                                                            "x-MS950-HKSCS",
                                                            "MS950_HKSCS" } },

            { "additional.x_windows_949",         null, new String[] { "x-windows-949",
                                                            "MS949" } },

            { "additional.GB18030",                null, new String[] { "GB18030",
                                                             "windows-54936",
                                                             "ibm-1392" } },

            { "additional.GB2312",                 null, new String[] { "GB2312",
                                                             "cp1383",
                                                             "EUC_CN" } }
        
        
        };
        return charsetsInfo;
    }


}
