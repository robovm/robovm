/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSStringTransform/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStringTransform/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSStringTransform/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSStringTransform toObject(Class<NSStringTransform> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStringTransform.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStringTransform o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSStringTransform> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSStringTransform> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSStringTransform.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSStringTransform> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSStringTransform o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToKatakana = new NSStringTransform("LatinToKatakana");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToHiragana = new NSStringTransform("LatinToHiragana");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToHangul = new NSStringTransform("LatinToHangul");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToArabic = new NSStringTransform("LatinToArabic");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToHebrew = new NSStringTransform("LatinToHebrew");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToThai = new NSStringTransform("LatinToThai");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToCyrillic = new NSStringTransform("LatinToCyrillic");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform LatinToGreek = new NSStringTransform("LatinToGreek");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform ToLatin = new NSStringTransform("ToLatin");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform MandarinToLatin = new NSStringTransform("MandarinToLatin");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform HiraganaToKatakana = new NSStringTransform("HiraganaToKatakana");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform FullwidthToHalfwidth = new NSStringTransform("FullwidthToHalfwidth");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform ToXMLHex = new NSStringTransform("ToXMLHex");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform ToUnicodeName = new NSStringTransform("ToUnicodeName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform StripCombiningMarks = new NSStringTransform("StripCombiningMarks");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final NSStringTransform StripDiacritics = new NSStringTransform("StripDiacritics");
    /*</constants>*/
    
    private static /*<name>*/NSStringTransform/*</name>*/[] values = new /*<name>*/NSStringTransform/*</name>*/[] {/*<value_list>*/LatinToKatakana, LatinToHiragana, LatinToHangul, LatinToArabic, LatinToHebrew, LatinToThai, LatinToCyrillic, LatinToGreek, ToLatin, MandarinToLatin, HiraganaToKatakana, FullwidthToHalfwidth, ToXMLHex, ToUnicodeName, StripCombiningMarks, StripDiacritics/*</value_list>*/};
    
    /*<name>*/NSStringTransform/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSStringTransform/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSStringTransform/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStringTransform/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToKatakana", optional=true)
        public static native NSString LatinToKatakana();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToHiragana", optional=true)
        public static native NSString LatinToHiragana();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToHangul", optional=true)
        public static native NSString LatinToHangul();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToArabic", optional=true)
        public static native NSString LatinToArabic();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToHebrew", optional=true)
        public static native NSString LatinToHebrew();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToThai", optional=true)
        public static native NSString LatinToThai();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToCyrillic", optional=true)
        public static native NSString LatinToCyrillic();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformLatinToGreek", optional=true)
        public static native NSString LatinToGreek();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformToLatin", optional=true)
        public static native NSString ToLatin();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformMandarinToLatin", optional=true)
        public static native NSString MandarinToLatin();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformHiraganaToKatakana", optional=true)
        public static native NSString HiraganaToKatakana();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformFullwidthToHalfwidth", optional=true)
        public static native NSString FullwidthToHalfwidth();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformToXMLHex", optional=true)
        public static native NSString ToXMLHex();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformToUnicodeName", optional=true)
        public static native NSString ToUnicodeName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformStripCombiningMarks", optional=true)
        public static native NSString StripCombiningMarks();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="NSStringTransformStripDiacritics", optional=true)
        public static native NSString StripDiacritics();
        /*</values>*/
    }
}
