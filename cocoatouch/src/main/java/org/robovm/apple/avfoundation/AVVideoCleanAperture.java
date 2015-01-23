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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVVideoCleanAperture.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoCleanAperture/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVVideoCleanAperture toObject(Class<AVVideoCleanAperture> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVVideoCleanAperture(o);
        }
        @MarshalsPointer
        public static long toNative(AVVideoCleanAperture o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVVideoCleanAperture(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVVideoCleanAperture() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVVideoCleanAperture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getWidth() {
        if (data.containsKey(WidthKey())) {
            NSNumber val = (NSNumber) data.get(WidthKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCleanAperture setWidth(long width) {
        data.put(WidthKey(), NSNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHeight() {
        if (data.containsKey(HeightKey())) {
            NSNumber val = (NSNumber) data.get(HeightKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCleanAperture setHeight(long height) {
        data.put(HeightKey(), NSNumber.valueOf(height));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHorizontalOffset() {
        if (data.containsKey(HorizontalOffsetKey())) {
            NSNumber val = (NSNumber) data.get(HorizontalOffsetKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCleanAperture setHorizontalOffset(long offset) {
        data.put(HorizontalOffsetKey(), NSNumber.valueOf(offset));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getVerticalOffset() {
        if (data.containsKey(VerticalOffsetKey())) {
            NSNumber val = (NSNumber) data.get(VerticalOffsetKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCleanAperture setVerticalOffset(long offset) {
        data.put(VerticalOffsetKey(), NSNumber.valueOf(offset));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureWidthKey", optional=true)
    protected static native NSString WidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureHeightKey", optional=true)
    protected static native NSString HeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureHorizontalOffsetKey", optional=true)
    protected static native NSString HorizontalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureVerticalOffsetKey", optional=true)
    protected static native NSString VerticalOffsetKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
