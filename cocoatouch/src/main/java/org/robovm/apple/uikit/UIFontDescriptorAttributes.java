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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(UIFontDescriptorAttributes.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class UIFontDescriptorAttributes 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UIFontDescriptorAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public UIFontDescriptorAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(UIFontDescriptorAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public UIFontDescriptorAttributes set(String attribute, NSObject value) {
        data.put(new NSString(attribute), value);
        return this;
    }
    public UIFontDescriptorAttributes set(UIFontDescriptorAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public NSObject get(String attribute) {
        return data.get(new NSString(attribute));
    }
    public NSObject get(UIFontDescriptorAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(String attribute) {
        return data.containsKey(new NSString(attribute));
    }
    public boolean contains(UIFontDescriptorAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getFamily() {
        if (contains(UIFontDescriptorAttribute.Family)) {
            NSString val = (NSString)get(UIFontDescriptorAttribute.Family);
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
        if (contains(UIFontDescriptorAttribute.Name)) {
            NSString val = (NSString)get(UIFontDescriptorAttribute.Name);
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
        if (contains(UIFontDescriptorAttribute.Face)) {
            NSString val = (NSString)get(UIFontDescriptorAttribute.Face);
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
        if (contains(UIFontDescriptorAttribute.Size)) {
            NSNumber val = (NSNumber)get(UIFontDescriptorAttribute.Face);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setSize(double size) {
        set(UIFontDescriptorAttribute.Face, NSNumber.valueOf(size));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getVisibleName() {
        if (contains(UIFontDescriptorAttribute.VisibleName)) {
            NSString val = (NSString)get(UIFontDescriptorAttribute.VisibleName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setVisibleName(String name) {
        set(UIFontDescriptorAttribute.VisibleName, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public @ByVal CGAffineTransform getMatrix() {
        if (contains(UIFontDescriptorAttribute.Matrix)) {
            NSValue val = (NSValue)get(UIFontDescriptorAttribute.Matrix);
            return val.affineTransformValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorAttributes setMatrix(@ByVal CGAffineTransform matrix) {
        set(UIFontDescriptorAttribute.Matrix, NSValue.valueOf(matrix));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSCharacterSet getCharacterSet() {
        if (contains(UIFontDescriptorAttribute.CharacterSet)) {
            NSCharacterSet val = (NSCharacterSet)get(UIFontDescriptorAttribute.CharacterSet);
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
    @SuppressWarnings("unchecked")
    public NSArray<UIFontDescriptor> getCascadeList() {
        if (contains(UIFontDescriptorAttribute.CascadeList)) {
            NSArray<UIFontDescriptor> val = (NSArray<UIFontDescriptor>)get(UIFontDescriptorAttribute.CascadeList);
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
    @SuppressWarnings("unchecked")
    public UIFontDescriptorTraits getTraits() {
        if (contains(UIFontDescriptorAttribute.Traits)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)get(UIFontDescriptorAttribute.Traits);
            return new UIFontDescriptorTraits(val);
        }
        return new UIFontDescriptorTraits();
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
        if (contains(UIFontDescriptorAttribute.FixedAdvance)) {
            NSNumber val = (NSNumber)get(UIFontDescriptorAttribute.FixedAdvance);
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

    //    public static UIFontDescriptorAttribute FeatureSettings = new UIFontDescriptorAttribute("FeatureSettingsValue"); TODO
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontTextStyle getTextStyle() {
        if (contains(UIFontDescriptorAttribute.TextStyle)) {
            NSString val = (NSString)get(UIFontDescriptorAttribute.TextStyle);
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
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureTypeIdentifierKey", optional=true)
    protected static native NSString TypeIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureSelectorIdentifierKey", optional=true)
    protected static native NSString SelectorIdentifierKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
