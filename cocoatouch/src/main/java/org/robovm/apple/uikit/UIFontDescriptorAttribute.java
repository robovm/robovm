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
package org.robovm.apple.uikit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/UIFontDescriptorAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontDescriptorAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/UIFontDescriptorAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIFontDescriptorAttribute toObject(Class<UIFontDescriptorAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UIFontDescriptorAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(UIFontDescriptorAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<UIFontDescriptorAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIFontDescriptorAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(UIFontDescriptorAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIFontDescriptorAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (UIFontDescriptorAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Family = new UIFontDescriptorAttribute("Family");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Name = new UIFontDescriptorAttribute("Name");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Face = new UIFontDescriptorAttribute("Face");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Size = new UIFontDescriptorAttribute("Size");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute VisibleName = new UIFontDescriptorAttribute("VisibleName");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Matrix = new UIFontDescriptorAttribute("Matrix");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute CharacterSet = new UIFontDescriptorAttribute("CharacterSet");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute CascadeList = new UIFontDescriptorAttribute("CascadeList");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute Traits = new UIFontDescriptorAttribute("Traits");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute FixedAdvance = new UIFontDescriptorAttribute("FixedAdvance");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute FeatureSettings = new UIFontDescriptorAttribute("FeatureSettings");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIFontDescriptorAttribute TextStyle = new UIFontDescriptorAttribute("TextStyle");
    /*</constants>*/
    
    private static /*<name>*/UIFontDescriptorAttribute/*</name>*/[] values = new /*<name>*/UIFontDescriptorAttribute/*</name>*/[] {/*<value_list>*/Family, Name, Face, Size, VisibleName, Matrix, CharacterSet, CascadeList, Traits, FixedAdvance, FeatureSettings, TextStyle/*</value_list>*/};
    
    /*<name>*/UIFontDescriptorAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/UIFontDescriptorAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/UIFontDescriptorAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIFontDescriptorAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorFamilyAttribute", optional=true)
        public static native NSString Family();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorNameAttribute", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorFaceAttribute", optional=true)
        public static native NSString Face();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorSizeAttribute", optional=true)
        public static native NSString Size();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorVisibleNameAttribute", optional=true)
        public static native NSString VisibleName();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorMatrixAttribute", optional=true)
        public static native NSString Matrix();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorCharacterSetAttribute", optional=true)
        public static native NSString CharacterSet();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorCascadeListAttribute", optional=true)
        public static native NSString CascadeList();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorTraitsAttribute", optional=true)
        public static native NSString Traits();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorFixedAdvanceAttribute", optional=true)
        public static native NSString FixedAdvance();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorFeatureSettingsAttribute", optional=true)
        public static native NSString FeatureSettings();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIFontDescriptorTextStyleAttribute", optional=true)
        public static native NSString TextStyle();
        /*</values>*/
    }
}
