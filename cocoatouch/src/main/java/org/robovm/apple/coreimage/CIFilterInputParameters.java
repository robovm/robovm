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
@Marshaler(CIFilterInputParameters.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterInputParameters/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CIFilterInputParameters toObject(Class<CIFilterInputParameters> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIFilterInputParameters(o);
        }
        @MarshalsPointer
        public static long toNative(CIFilterInputParameters o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CIFilterInputParameters(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public CIFilterInputParameters() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(CIFilterInputParameters.class); }/*</bind>*/
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
    public CIImage getOutputImage() {
        if (data.containsKey(OutputImageKey())) {
            CIImage val = (CIImage)data.get(OutputImageKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIFilterInputParameters setOutputImage(CIImage image) {
        data.put(OutputImageKey(), image);
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImage getBackgroundImage() {
        if (data.containsKey(BackgroundImageKey())) {
            CIImage val = (CIImage)data.get(BackgroundImageKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIFilterInputParameters setBackgroundImage(CIImage image) {
        data.put(BackgroundImageKey(), image);
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImage getImage() {
        if (data.containsKey(ImageKey())) {
            CIImage val = (CIImage)data.get(ImageKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIFilterInputParameters setImage(CIImage image) {
        data.put(ImageKey(), image);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getVersion() {
        if (data.containsKey(VersionKey())) {
            NSNumber val = (NSNumber)data.get(VersionKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CIFilterInputParameters setVersion(long version) {
        data.put(VersionKey(), NSNumber.valueOf(version));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIOutputImageKey", optional=true)
    protected static native NSString OutputImageKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIInputBackgroundImageKey", optional=true)
    protected static native NSString BackgroundImageKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIInputImageKey", optional=true)
    protected static native NSString ImageKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIInputVersionKey", optional=true)
    protected static native NSString VersionKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
