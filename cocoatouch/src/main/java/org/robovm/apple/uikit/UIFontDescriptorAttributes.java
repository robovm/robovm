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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(/*<name>*/UIFontDescriptorAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontDescriptorAttributes/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIFontDescriptorAttributes toObject(Class<UIFontDescriptorAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIFontDescriptorAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(UIFontDescriptorAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UIFontDescriptorAttributes> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIFontDescriptorAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UIFontDescriptorAttributes(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIFontDescriptorAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (UIFontDescriptorAttributes i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UIFontDescriptorAttributes(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public UIFontDescriptorAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(UIFontDescriptorAttribute key) {
        return data.containsKey(key.value());
    }
    public NSObject get(UIFontDescriptorAttribute key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public UIFontDescriptorAttributes set(UIFontDescriptorAttribute key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getFamily() {
        if (has(UIFontDescriptorAttribute.Family)) {
            NSString val = (NSString) get(UIFontDescriptorAttribute.Family);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setFamily(String family) {
        set(UIFontDescriptorAttribute.Family, new NSString(family));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getName() {
        if (has(UIFontDescriptorAttribute.Name)) {
            NSString val = (NSString) get(UIFontDescriptorAttribute.Name);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setName(String name) {
        set(UIFontDescriptorAttribute.Name, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getFace() {
        if (has(UIFontDescriptorAttribute.Face)) {
            NSString val = (NSString) get(UIFontDescriptorAttribute.Face);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setFace(String face) {
        set(UIFontDescriptorAttribute.Face, new NSString(face));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getSize() {
        if (has(UIFontDescriptorAttribute.Size)) {
            NSNumber val = (NSNumber) get(UIFontDescriptorAttribute.Size);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setSize(double size) {
        set(UIFontDescriptorAttribute.Size, NSNumber.valueOf(size));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getVisibleName() {
        if (has(UIFontDescriptorAttribute.VisibleName)) {
            NSString val = (NSString) get(UIFontDescriptorAttribute.VisibleName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setVisibleName(String visibleName) {
        set(UIFontDescriptorAttribute.VisibleName, new NSString(visibleName));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGAffineTransform getMatrix() {
        if (has(UIFontDescriptorAttribute.Matrix)) {
            NSValue val = (NSValue) get(UIFontDescriptorAttribute.Matrix);
            return val.affineTransformValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setMatrix(CGAffineTransform matrix) {
        set(UIFontDescriptorAttribute.Matrix, NSValue.valueOf(matrix));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSCharacterSet getCharacterSet() {
        if (has(UIFontDescriptorAttribute.CharacterSet)) {
            NSCharacterSet val = (NSCharacterSet) get(UIFontDescriptorAttribute.CharacterSet);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setCharacterSet(NSCharacterSet characterSet) {
        set(UIFontDescriptorAttribute.CharacterSet, characterSet);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSArray<UIFontDescriptor> getCascadeList() {
        if (has(UIFontDescriptorAttribute.CascadeList)) {
            NSArray<UIFontDescriptor> val = (NSArray<UIFontDescriptor>) get(UIFontDescriptorAttribute.CascadeList);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setCascadeList(NSArray<UIFontDescriptor> cascadeList) {
        set(UIFontDescriptorAttribute.CascadeList, cascadeList);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorTraits getTraits() {
        if (has(UIFontDescriptorAttribute.Traits)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) get(UIFontDescriptorAttribute.Traits);
            return new UIFontDescriptorTraits(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setTraits(UIFontDescriptorTraits traits) {
        set(UIFontDescriptorAttribute.Traits, traits.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getFixedAdvance() {
        if (has(UIFontDescriptorAttribute.FixedAdvance)) {
            NSNumber val = (NSNumber) get(UIFontDescriptorAttribute.FixedAdvance);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setFixedAdvance(double fixedAdvance) {
        set(UIFontDescriptorAttribute.FixedAdvance, NSNumber.valueOf(fixedAdvance));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public List<UIFontFeatureSetting> getFeatureSettings() {
        if (has(UIFontDescriptorAttribute.FeatureSettings)) {
            NSArray<?> val = (NSArray<?>) get(UIFontDescriptorAttribute.FeatureSettings);
            List<UIFontFeatureSetting> list = new ArrayList<>();
            NSDictionary<NSString, NSObject>[] array = (NSDictionary<NSString, NSObject>[]) val.toArray(new NSDictionary[val.size()]);
            for (NSDictionary<NSString, NSObject> d : array) {
               list.add(new UIFontFeatureSetting(d));
            }
            return list;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setFeatureSettings(List<UIFontFeatureSetting> featureSettings) {
        NSArray<NSDictionary<NSString, NSObject>> val = new NSMutableArray<>();
        for (UIFontFeatureSetting e : featureSettings) {
            val.add(e.getDictionary());
        }
        set(UIFontDescriptorAttribute.FeatureSettings, val);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontTextStyle getTextStyle() {
        if (has(UIFontDescriptorAttribute.TextStyle)) {
            NSString val = (NSString) get(UIFontDescriptorAttribute.TextStyle);
            return UIFontTextStyle.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setTextStyle(UIFontTextStyle textStyle) {
        set(UIFontDescriptorAttribute.TextStyle, textStyle.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
