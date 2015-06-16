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
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
@Marshaler(/*<name>*/CIDetectorOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CIDetectorOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIDetectorOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CIDetectorOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIDetectorOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (CIDetectorOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CIDetectorOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public CIDetectorOptions() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public CIDetectorOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIDetectorAccuracy getAccuracy() {
        if (has(Keys.Accuracy())) {
            NSString val = (NSString) get(Keys.Accuracy());
            return CIDetectorAccuracy.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIDetectorOptions setAccuracy(CIDetectorAccuracy accuracy) {
        set(Keys.Accuracy(), accuracy.value());
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isTracking() {
        if (has(Keys.Tracking())) {
            NSNumber val = (NSNumber) get(Keys.Tracking());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CIDetectorOptions setTracking(boolean tracking) {
        set(Keys.Tracking(), NSNumber.valueOf(tracking));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getMinFeatureSize() {
        if (has(Keys.MinFeatureSize())) {
            NSNumber val = (NSNumber) get(Keys.MinFeatureSize());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CIDetectorOptions setMinFeatureSize(double minFeatureSize) {
        set(Keys.MinFeatureSize(), NSNumber.valueOf(minFeatureSize));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreImage")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="CIDetectorAccuracy", optional=true)
        public static native NSString Accuracy();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="CIDetectorTracking", optional=true)
        public static native NSString Tracking();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="CIDetectorMinFeatureSize", optional=true)
        public static native NSString MinFeatureSize();
    }
    /*</keys>*/
}
