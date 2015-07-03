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
@Marshaler(/*<name>*/CIDetectorFeatureOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorFeatureOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CIDetectorFeatureOptions toObject(Class<CIDetectorFeatureOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIDetectorFeatureOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CIDetectorFeatureOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CIDetectorFeatureOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIDetectorFeatureOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CIDetectorFeatureOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIDetectorFeatureOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (CIDetectorFeatureOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CIDetectorFeatureOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public CIDetectorFeatureOptions() {}
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
    public CIDetectorFeatureOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean recognizesEyeBlinks() {
        if (has(Keys.EyeBlink())) {
            NSNumber val = (NSNumber) get(Keys.EyeBlink());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CIDetectorFeatureOptions setRecognizesEyeBlinks(boolean recognizesEyeBlinks) {
        set(Keys.EyeBlink(), NSNumber.valueOf(recognizesEyeBlinks));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean recognizesSmiles() {
        if (has(Keys.Smile())) {
            NSNumber val = (NSNumber) get(Keys.Smile());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CIDetectorFeatureOptions setRecognizesSmiles(boolean recognizesSmiles) {
        set(Keys.Smile(), NSNumber.valueOf(recognizesSmiles));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getFocalLength() {
        if (has(Keys.FocalLength())) {
            NSNumber val = (NSNumber) get(Keys.FocalLength());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIDetectorFeatureOptions setFocalLength(double focalLength) {
        set(Keys.FocalLength(), NSNumber.valueOf(focalLength));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getAspectRatio() {
        if (has(Keys.AspectRatio())) {
            NSNumber val = (NSNumber) get(Keys.AspectRatio());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIDetectorFeatureOptions setAspectRatio(double aspectRatio) {
        set(Keys.AspectRatio(), NSNumber.valueOf(aspectRatio));
        return this;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    public CGImagePropertyOrientation getImageOrientation() {
        if (has(Keys.ImageOrientation())) {
            NSNumber val = (NSNumber) get(Keys.ImageOrientation());
            return CGImagePropertyOrientation.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    public CIDetectorFeatureOptions setImageOrientation(CGImagePropertyOrientation imageOrientation) {
        set(Keys.ImageOrientation(), NSNumber.valueOf(imageOrientation.value()));
        return this;
    }
    
    /*<keys>*/
    @Library("CoreImage")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="CIDetectorImageOrientation", optional=true)
        public static native NSString ImageOrientation();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CIDetectorEyeBlink", optional=true)
        public static native NSString EyeBlink();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CIDetectorSmile", optional=true)
        public static native NSString Smile();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CIDetectorFocalLength", optional=true)
        public static native NSString FocalLength();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CIDetectorAspectRatio", optional=true)
        public static native NSString AspectRatio();
    }
    /*</keys>*/
}
