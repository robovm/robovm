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
@Marshaler(CIDetectorFeatureOptions.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorFeatureOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
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

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CIDetectorFeatureOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public CIDetectorFeatureOptions() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(CIDetectorFeatureOptions.class); }/*</bind>*/
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
    public CGImagePropertyOrientation getImageOrientation() {
        if (data.containsKey(ImageOrientationKey())) {
            NSNumber val = (NSNumber)data.get(ImageOrientationKey());
            return CGImagePropertyOrientation.valueOf(val.intValue());
        }
        return CGImagePropertyOrientation.Up;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIDetectorFeatureOptions setImageOrientation(CGImagePropertyOrientation orientation) {
        data.put(ImageOrientationKey(), NSNumber.valueOf(orientation.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isRecognizingEyeBlinks() {
        if (data.containsKey(EyeBlinkKey())) {
            NSNumber val = (NSNumber)data.get(EyeBlinkKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CIDetectorFeatureOptions setRecognizeEyeBlinks(boolean recognize) {
        data.put(EyeBlinkKey(), NSNumber.valueOf(recognize));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isRecognizingSmiles() {
        if (data.containsKey(SmileKey())) {
            NSNumber val = (NSNumber)data.get(SmileKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CIDetectorFeatureOptions setRecognizeSmiles() {
        data.put(SmileKey(), NSNumber.valueOf(SmileKey()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getFocalLength() {
        if (data.containsKey(FocalLengthKey())) {
            NSNumber val = (NSNumber)data.get(FocalLengthKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIDetectorFeatureOptions setFocalLength(double focalLength) {
        data.put(FocalLengthKey(), NSNumber.valueOf(focalLength));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getAspectRatio() {
        if (data.containsKey(AspectRatioKey())) {
            NSNumber val = (NSNumber)data.get(AspectRatioKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIDetectorFeatureOptions setFAspectRatio(double aspectRatio) {
        data.put(AspectRatioKey(), NSNumber.valueOf(aspectRatio));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorImageOrientation", optional=true)
    protected static native NSString ImageOrientationKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CIDetectorEyeBlink", optional=true)
    protected static native NSString EyeBlinkKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CIDetectorSmile", optional=true)
    protected static native NSString SmileKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CIDetectorFocalLength", optional=true)
    protected static native NSString FocalLengthKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAspectRatio", optional=true)
    protected static native NSString AspectRatioKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
