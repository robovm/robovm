/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshaler(/*<name>*/CFStringTransform/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStringTransform/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFStringTransform/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFStringTransform toObject(Class<CFStringTransform> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFStringTransform.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFStringTransform o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFStringTransform> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFStringTransform> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFStringTransform.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFStringTransform> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFStringTransform i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFStringTransform StripCombiningMarks = new CFStringTransform("StripCombiningMarks");
    public static final CFStringTransform ToLatin = new CFStringTransform("ToLatin");
    public static final CFStringTransform FullwidthHalfwidth = new CFStringTransform("FullwidthHalfwidth");
    public static final CFStringTransform LatinKatakana = new CFStringTransform("LatinKatakana");
    public static final CFStringTransform LatinHiragana = new CFStringTransform("LatinHiragana");
    public static final CFStringTransform HiraganaKatakana = new CFStringTransform("HiraganaKatakana");
    public static final CFStringTransform MandarinLatin = new CFStringTransform("MandarinLatin");
    public static final CFStringTransform LatinHangul = new CFStringTransform("LatinHangul");
    public static final CFStringTransform LatinArabic = new CFStringTransform("LatinArabic");
    public static final CFStringTransform LatinHebrew = new CFStringTransform("LatinHebrew");
    public static final CFStringTransform LatinThai = new CFStringTransform("LatinThai");
    public static final CFStringTransform LatinCyrillic = new CFStringTransform("LatinCyrillic");
    public static final CFStringTransform LatinGreek = new CFStringTransform("LatinGreek");
    public static final CFStringTransform ToXMLHex = new CFStringTransform("ToXMLHex");
    public static final CFStringTransform ToUnicodeName = new CFStringTransform("ToUnicodeName");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStringTransform StripDiacritics = new CFStringTransform("StripDiacritics");
    /*</constants>*/
    
    private static /*<name>*/CFStringTransform/*</name>*/[] values = new /*<name>*/CFStringTransform/*</name>*/[] {/*<value_list>*/StripCombiningMarks, ToLatin, FullwidthHalfwidth, LatinKatakana, LatinHiragana, HiraganaKatakana, MandarinLatin, LatinHangul, LatinArabic, LatinHebrew, LatinThai, LatinCyrillic, LatinGreek, ToXMLHex, ToUnicodeName, StripDiacritics/*</value_list>*/};
    
    /*<name>*/CFStringTransform/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFStringTransform/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFStringTransform/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFStringTransform/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFStringTransformStripCombiningMarks", optional=true)
        public static native CFString StripCombiningMarks();
        @GlobalValue(symbol="kCFStringTransformToLatin", optional=true)
        public static native CFString ToLatin();
        @GlobalValue(symbol="kCFStringTransformFullwidthHalfwidth", optional=true)
        public static native CFString FullwidthHalfwidth();
        @GlobalValue(symbol="kCFStringTransformLatinKatakana", optional=true)
        public static native CFString LatinKatakana();
        @GlobalValue(symbol="kCFStringTransformLatinHiragana", optional=true)
        public static native CFString LatinHiragana();
        @GlobalValue(symbol="kCFStringTransformHiraganaKatakana", optional=true)
        public static native CFString HiraganaKatakana();
        @GlobalValue(symbol="kCFStringTransformMandarinLatin", optional=true)
        public static native CFString MandarinLatin();
        @GlobalValue(symbol="kCFStringTransformLatinHangul", optional=true)
        public static native CFString LatinHangul();
        @GlobalValue(symbol="kCFStringTransformLatinArabic", optional=true)
        public static native CFString LatinArabic();
        @GlobalValue(symbol="kCFStringTransformLatinHebrew", optional=true)
        public static native CFString LatinHebrew();
        @GlobalValue(symbol="kCFStringTransformLatinThai", optional=true)
        public static native CFString LatinThai();
        @GlobalValue(symbol="kCFStringTransformLatinCyrillic", optional=true)
        public static native CFString LatinCyrillic();
        @GlobalValue(symbol="kCFStringTransformLatinGreek", optional=true)
        public static native CFString LatinGreek();
        @GlobalValue(symbol="kCFStringTransformToXMLHex", optional=true)
        public static native CFString ToXMLHex();
        @GlobalValue(symbol="kCFStringTransformToUnicodeName", optional=true)
        public static native CFString ToUnicodeName();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStringTransformStripDiacritics", optional=true)
        public static native CFString StripDiacritics();
        /*</values>*/
    }
}
