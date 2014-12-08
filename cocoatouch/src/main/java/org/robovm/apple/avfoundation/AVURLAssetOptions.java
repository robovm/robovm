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
@Marshaler(AVURLAssetOptions.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVURLAssetOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVURLAssetOptions toObject(Class<AVURLAssetOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVURLAssetOptions(o);
        }
        @MarshalsPointer
        public static long toNative(AVURLAssetOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVURLAssetOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVURLAssetOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVURLAssetOptions.class); }/*</bind>*/
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
    public boolean prefersPreciseDurationAndTiming() {
        if (data.containsKey(PreferPreciseDurationAndTimingKey())) {
            NSNumber val = (NSNumber) data.get(PreferPreciseDurationAndTimingKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVURLAssetOptions setPrefersPreciseDurationAndTiming(boolean preferPrecise) {
        data.put(PreferPreciseDurationAndTimingKey(), NSNumber.valueOf(preferPrecise));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVAssetReferenceRestrictions getReferenceRestrictions() {
        if (data.containsKey(ReferenceRestrictionsKey())) {
            NSNumber val = (NSNumber) data.get(ReferenceRestrictionsKey());
            return new AVAssetReferenceRestrictions(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVURLAssetOptions setReferenceRestrictions(AVAssetReferenceRestrictions restrictions) {
        data.put(ReferenceRestrictionsKey(), NSNumber.valueOf(restrictions.value()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSHTTPCookie> getHTTPCookies() {
        if (data.containsKey(HTTPCookiesKey())) {
            NSArray<NSHTTPCookie> val = (NSArray<NSHTTPCookie>) data.get(HTTPCookiesKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public AVURLAssetOptions setHTTPCookies(NSArray<NSHTTPCookie> cookies) {
        data.put(HTTPCookiesKey(), cookies);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetPreferPreciseDurationAndTimingKey", optional=true)
    protected static native NSString PreferPreciseDurationAndTimingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetReferenceRestrictionsKey", optional=true)
    protected static native NSString ReferenceRestrictionsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetHTTPCookiesKey", optional=true)
    protected static native NSString HTTPCookiesKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
