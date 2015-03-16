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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIDetectorOptions.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CIDetectorOptions toObject(Class<CIDetectorOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIDetectorOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CIDetectorOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CIDetectorOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public CIDetectorOptions() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(CIDetectorOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIDetectorAccuracy getAccuracy() {
        if (data.containsKey(AccuracyKey())) {
            NSString val = (NSString)data.get(AccuracyKey());
            return CIDetectorAccuracy.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIDetectorOptions setAccuracy(CIDetectorAccuracy accuracy) {
        data.put(AccuracyKey(), accuracy.value());
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isTracking() {
        if (data.containsKey(TrackingKey())) {
            NSNumber val = (NSNumber)data.get(TrackingKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CIDetectorOptions setTracking(boolean tracking) {
        data.put(TrackingKey(), NSNumber.valueOf(tracking));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getMinFeatureSize() {
        if (data.containsKey(MinFeatureSizeKey())) {
            NSNumber val = (NSNumber)data.get(MinFeatureSizeKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CIDetectorOptions setMinFeatureSize(double minFeatureSize) {
        data.put(MinFeatureSizeKey(), NSNumber.valueOf(minFeatureSize));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracy", optional=true)
    protected static native NSString AccuracyKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTracking", optional=true)
    protected static native NSString TrackingKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CIDetectorMinFeatureSize", optional=true)
    protected static native NSString MinFeatureSizeKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
