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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIFilterAttribute.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CIFilterAttribute toObject(Class<CIFilterAttribute> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIFilterAttribute(o);
        }
        @MarshalsPointer
        public static long toNative(CIFilterAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CIFilterAttribute(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CIFilterAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    public String getAttributeClassName() {
        if (data.containsKey(ClassKey())) {
            NSString val = (NSString)data.get(ClassKey());
            return val.toString();
        }
        return null;
    }
    public CIFilterAttributeType getType() {
        if (data.containsKey(TypeKey())) {
            NSString val = (NSString)data.get(TypeKey());
            return CIFilterAttributeType.valueOf(val);
        }
        return null;
    }
    public double getMin() {
        if (data.containsKey(MinKey())) {
            NSNumber val = (NSNumber)data.get(MinKey());
            return val.doubleValue();
        }
        return Double.NEGATIVE_INFINITY;
    }
    public double getMax() {
        if (data.containsKey(MaxKey())) {
            NSNumber val = (NSNumber)data.get(MaxKey());
            return val.doubleValue();
        }
        return Double.POSITIVE_INFINITY;
    }
    public double getSliderMin() {
        if (data.containsKey(SliderMinKey())) {
            NSNumber val = (NSNumber)data.get(SliderMinKey());
            return val.doubleValue();
        }
        return Double.NEGATIVE_INFINITY;
    }
    public double getSliderMax() {
        if (data.containsKey(SliderMaxKey())) {
            NSNumber val = (NSNumber)data.get(SliderMaxKey());
            return val.doubleValue();
        }
        return Double.POSITIVE_INFINITY;
    }
    public double getDefault() {
        if (data.containsKey(DefaultKey())) {
            NSNumber val = (NSNumber)data.get(DefaultKey());
            return val.doubleValue();
        }
        return 0;
    }
    public double getIdentity() {
        if (data.containsKey(IdentityKey())) {
            NSNumber val = (NSNumber)data.get(IdentityKey());
            return val.doubleValue();
        }
        return 0;
    }
    public String getName() {
        if (data.containsKey(NameKey())) {
            NSString val = (NSString)data.get(NameKey());
            return val.toString();
        }
        return null;
    }
    public String getDisplayName() {
        if (data.containsKey(DisplayNameKey())) {
            NSString val = (NSString)data.get(DisplayNameKey());
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="kCIAttributeClass", optional=true)
    protected static native NSString ClassKey();
    @GlobalValue(symbol="kCIAttributeType", optional=true)
    protected static native NSString TypeKey();
    @GlobalValue(symbol="kCIAttributeMin", optional=true)
    protected static native NSString MinKey();
    @GlobalValue(symbol="kCIAttributeMax", optional=true)
    protected static native NSString MaxKey();
    @GlobalValue(symbol="kCIAttributeSliderMin", optional=true)
    protected static native NSString SliderMinKey();
    @GlobalValue(symbol="kCIAttributeSliderMax", optional=true)
    protected static native NSString SliderMaxKey();
    @GlobalValue(symbol="kCIAttributeDefault", optional=true)
    protected static native NSString DefaultKey();
    @GlobalValue(symbol="kCIAttributeIdentity", optional=true)
    protected static native NSString IdentityKey();
    @GlobalValue(symbol="kCIAttributeName", optional=true)
    protected static native NSString NameKey();
    @GlobalValue(symbol="kCIAttributeDisplayName", optional=true)
    protected static native NSString DisplayNameKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
