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
package org.robovm.apple.coretext;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTAttributedStringAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTAttributedStringAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CTAttributedStringAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTAttributedStringAttribute toObject(Class<CTAttributedStringAttribute> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CTAttributedStringAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CTAttributedStringAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTAttributedStringAttribute> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTAttributedStringAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CTAttributedStringAttribute.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTAttributedStringAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTAttributedStringAttribute i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute Font = new CTAttributedStringAttribute("Font");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute ForegroundColorFromContext = new CTAttributedStringAttribute("ForegroundColorFromContext");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute Kern = new CTAttributedStringAttribute("Kern");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute Ligature = new CTAttributedStringAttribute("Ligature");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute ForegroundColor = new CTAttributedStringAttribute("ForegroundColor");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute ParagraphStyle = new CTAttributedStringAttribute("ParagraphStyle");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute StrokeWidth = new CTAttributedStringAttribute("StrokeWidth");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute StrokeColor = new CTAttributedStringAttribute("StrokeColor");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute UnderlineStyle = new CTAttributedStringAttribute("UnderlineStyle");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute Superscript = new CTAttributedStringAttribute("Superscript");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute UnderlineColor = new CTAttributedStringAttribute("UnderlineColor");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final CTAttributedStringAttribute VerticalForms = new CTAttributedStringAttribute("VerticalForms");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute GlyphInfo = new CTAttributedStringAttribute("GlyphInfo");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute CharacterShape = new CTAttributedStringAttribute("CharacterShape");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTAttributedStringAttribute Language = new CTAttributedStringAttribute("Language");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTAttributedStringAttribute RunDelegate = new CTAttributedStringAttribute("RunDelegate");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTAttributedStringAttribute BaselineClass = new CTAttributedStringAttribute("BaselineClass");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTAttributedStringAttribute BaselineInfo = new CTAttributedStringAttribute("BaselineInfo");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTAttributedStringAttribute BaselineReferenceInfo = new CTAttributedStringAttribute("BaselineReferenceInfo");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTAttributedStringAttribute WritingDirection = new CTAttributedStringAttribute("WritingDirection");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CTAttributedStringAttribute RubyAnnotation = new CTAttributedStringAttribute("RubyAnnotation");
    /*</constants>*/
    
    private static /*<name>*/CTAttributedStringAttribute/*</name>*/[] values = new /*<name>*/CTAttributedStringAttribute/*</name>*/[] {/*<value_list>*/Font, ForegroundColorFromContext, Kern, Ligature, ForegroundColor, ParagraphStyle, StrokeWidth, StrokeColor, UnderlineStyle, Superscript, UnderlineColor, VerticalForms, GlyphInfo, CharacterShape, Language, RunDelegate, BaselineClass, BaselineInfo, BaselineReferenceInfo, WritingDirection, RubyAnnotation/*</value_list>*/};
    
    /*<name>*/CTAttributedStringAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CTAttributedStringAttribute/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CTAttributedStringAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTAttributedStringAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreText")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontAttributeName", optional=true)
        public static native CFString Font();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTForegroundColorFromContextAttributeName", optional=true)
        public static native CFString ForegroundColorFromContext();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTKernAttributeName", optional=true)
        public static native CFString Kern();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTLigatureAttributeName", optional=true)
        public static native CFString Ligature();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTForegroundColorAttributeName", optional=true)
        public static native CFString ForegroundColor();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTParagraphStyleAttributeName", optional=true)
        public static native CFString ParagraphStyle();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTStrokeWidthAttributeName", optional=true)
        public static native CFString StrokeWidth();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTStrokeColorAttributeName", optional=true)
        public static native CFString StrokeColor();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTUnderlineStyleAttributeName", optional=true)
        public static native CFString UnderlineStyle();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTSuperscriptAttributeName", optional=true)
        public static native CFString Superscript();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTUnderlineColorAttributeName", optional=true)
        public static native CFString UnderlineColor();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCTVerticalFormsAttributeName", optional=true)
        public static native CFString VerticalForms();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTGlyphInfoAttributeName", optional=true)
        public static native CFString GlyphInfo();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTCharacterShapeAttributeName", optional=true)
        public static native CFString CharacterShape();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCTLanguageAttributeName", optional=true)
        public static native CFString Language();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTRunDelegateAttributeName", optional=true)
        public static native CFString RunDelegate();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassAttributeName", optional=true)
        public static native CFString BaselineClass();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineInfoAttributeName", optional=true)
        public static native CFString BaselineInfo();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineReferenceInfoAttributeName", optional=true)
        public static native CFString BaselineReferenceInfo();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTWritingDirectionAttributeName", optional=true)
        public static native CFString WritingDirection();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCTRubyAnnotationAttributeName", optional=true)
        public static native CFString RubyAnnotation();
        /*</values>*/
    }
}
