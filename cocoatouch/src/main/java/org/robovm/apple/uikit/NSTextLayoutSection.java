/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSTextLayoutSection.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextLayoutSection/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSTextLayoutSection toObject(Class<NSTextLayoutSection> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSTextLayoutSection(o);
        }
        @MarshalsPointer
        public static long toNative(NSTextLayoutSection o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public NSTextLayoutSection(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSTextLayoutSection() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSTextLayoutSection.class); }/*</bind>*/
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
    public NSTextLayoutOrientation getOrientation() {
        if (data.containsKey(OrientationKey())) {
            NSNumber val = (NSNumber)data.get(OrientationKey());
            return NSTextLayoutOrientation.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSTextLayoutSection setOrientation(NSTextLayoutOrientation orientation) {
        data.put(OrientationKey(), NSNumber.valueOf((int)orientation.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSRange getRange() {
        if (data.containsKey(RangeKey())) {
            NSValue val = (NSValue)data.get(RangeKey());
            return val.rangeValue();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSTextLayoutSection setRange(NSRange range) {
        data.put(RangeKey(), NSValue.valueOf(range));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionOrientation", optional=true)
    protected static native NSString OrientationKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionRange", optional=true)
    protected static native NSString RangeKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
