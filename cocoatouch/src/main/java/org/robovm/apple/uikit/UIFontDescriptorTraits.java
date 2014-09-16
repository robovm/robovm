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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class UIFontDescriptorTraits 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIFontDescriptorTraits toObject(Class<UIFontDescriptorTraits> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIFontDescriptorTraits(o);
        }
        @MarshalsPointer
        public static long toNative(UIFontDescriptorTraits o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UIFontDescriptorTraits(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public UIFontDescriptorTraits() {
        this.data = new NSMutableDictionary<>();
    }
    
    /*<bind>*/static { Bro.bind(UIFontDescriptorTraits.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIFontDescriptorSymbolicTraits getSymbolicTraits() {
        if (data.containsKey(SymbolicTrait())) {
            NSNumber val = (NSNumber)data.get(SymbolicTrait());
            return new UIFontDescriptorSymbolicTraits(val.intValue());
        }
        return UIFontDescriptorSymbolicTraits.None;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setSymbolicTraits(UIFontDescriptorSymbolicTraits symbolicTraits) {
        data.put(SymbolicTrait(), NSNumber.valueOf((int)symbolicTraits.value()));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getWeightTrait() {
        if (data.containsKey(WeightTrait())) {
            NSNumber val = (NSNumber)data.get(WeightTrait());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setWeightTrait(double weight) {
        data.put(WeightTrait(), NSNumber.valueOf(weight));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getWidthTrait() {
        if (data.containsKey(WidthTrait())) {
            NSNumber val = (NSNumber)data.get(WidthTrait());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setWidthTrait(double width) {
        data.put(WidthTrait(), NSNumber.valueOf(width));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getSlantTrait() {
        if (data.containsKey(SlantTrait())) {
            NSNumber val = (NSNumber)data.get(SlantTrait());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setSlantTrait(double slant) {
        data.put(SlantTrait(), NSNumber.valueOf(slant));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontSymbolicTrait", optional=true)
    protected static native NSString SymbolicTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontWeightTrait", optional=true)
    protected static native NSString WeightTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontWidthTrait", optional=true)
    protected static native NSString WidthTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontSlantTrait", optional=true)
    protected static native NSString SlantTrait();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
