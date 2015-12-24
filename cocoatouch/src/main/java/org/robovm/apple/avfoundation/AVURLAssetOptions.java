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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
@Marshaler(/*<name>*/AVURLAssetOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVURLAssetOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVURLAssetOptions toObject(Class<AVURLAssetOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVURLAssetOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVURLAssetOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVURLAssetOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVURLAssetOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (AVURLAssetOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVURLAssetOptions(NSDictionary data) {
        super(data);
    }
    public AVURLAssetOptions() {}
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
    public AVURLAssetOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean prefersPreciseDurationAndTiming() {
        if (has(Keys.PreferPreciseDurationAndTiming())) {
            NSNumber val = (NSNumber) get(Keys.PreferPreciseDurationAndTiming());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVURLAssetOptions setPrefersPreciseDurationAndTiming(boolean prefersPreciseDurationAndTiming) {
        set(Keys.PreferPreciseDurationAndTiming(), NSNumber.valueOf(prefersPreciseDurationAndTiming));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVAssetReferenceRestrictions getReferenceRestrictions() {
        if (has(Keys.ReferenceRestrictions())) {
            NSNumber val = (NSNumber) get(Keys.ReferenceRestrictions());
            return new AVAssetReferenceRestrictions(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVURLAssetOptions setReferenceRestrictions(AVAssetReferenceRestrictions referenceRestrictions) {
        set(Keys.ReferenceRestrictions(), NSNumber.valueOf(referenceRestrictions.value()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSArray<NSHTTPCookie> getHTTPCookies() {
        if (has(Keys.HTTPCookies())) {
            NSArray<NSHTTPCookie> val = (NSArray<NSHTTPCookie>) get(Keys.HTTPCookies());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public AVURLAssetOptions setHTTPCookies(NSArray<NSHTTPCookie> hTTPCookies) {
        set(Keys.HTTPCookies(), hTTPCookies);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVURLAssetPreferPreciseDurationAndTimingKey", optional=true)
        public static native NSString PreferPreciseDurationAndTiming();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVURLAssetReferenceRestrictionsKey", optional=true)
        public static native NSString ReferenceRestrictions();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVURLAssetHTTPCookiesKey", optional=true)
        public static native NSString HTTPCookies();
    }
    /*</keys>*/
}
